package jmri.util;

import org.junit.Assert;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Log4J Appender that works with JUnit tests
 * to check for expected vs unexpected log messages
 *
 * Much of the interface is static to avoid lots of instance() calls, but this is not
 * a problem as there should be only one of these while tests are running
 *
 * @see apps.tests.Log4JFixture
 *
 * @author	Bob Jacobsen - Copyright 2007
 */
public class JUnitAppender extends org.apache.log4j.ConsoleAppender {

    static java.util.ArrayList<LoggingEvent> list = new java.util.ArrayList<>();

    
    /**
     * Called for each logging event.
     */
    public synchronized void append(LoggingEvent event) {
        if (hold) {
            list.add(event);
        } else {
            super.append(event);
        }
    }

    /**
     * Called once options are set.
     *
     * Currently just reflects back to super-class.
     */
    public void activateOptions() {
        if (JUnitAppender.instance != null) {
            System.err.println("JUnitAppender initialized more than once"); // can't count on logging here
        } else {
            JUnitAppender.instance = this;
        }
        super.activateOptions();
    }

    /**
     * Do clean-up at end.
     *
     * Currently just reflects back to super-class.
     */
    public synchronized void close() {
        list.clear();
        super.close();
    }

    static boolean hold = false;

    static private JUnitAppender instance = null;
    
    // package-level access for testing
    static boolean unexpectedFatalSeen = false;
    static boolean unexpectedErrorSeen = false;
    static boolean unexpectedWarnSeen  = false;
    static boolean unexpectedInfoSeen  = false;
    
    public static boolean unexpectedMessageSeen(Level l) {
        if (l == Level.FATAL) return unexpectedFatalSeen;
        if (l == Level.ERROR) return unexpectedFatalSeen || unexpectedErrorSeen;
        if (l == Level.WARN) return unexpectedFatalSeen || unexpectedErrorSeen || unexpectedWarnSeen;
        if (l == Level.INFO) return unexpectedFatalSeen || unexpectedErrorSeen || unexpectedWarnSeen || unexpectedInfoSeen;
        throw new java.lang.IllegalArgumentException("Did not expect "+l);
    }

    /**
     * Tell appender that a JUnit test is starting.
     * <P>
     * This causes log messages to be held for examination.
     */
    public static void start() {
        hold = true;
    }

    /**
     * Tell appender that the JUnit test is ended.
     * <P>
     * Any queued messages at this point will be passed through to the actual log.
     */
    public static void end() {
        hold = false;
        while (!list.isEmpty()) {
            LoggingEvent evt = list.remove(0);
            instance().superappend(evt);
        }
    }

    void superappend(LoggingEvent l) {
        if (l.getLevel() == Level.FATAL) unexpectedFatalSeen = true;
        if (l.getLevel() == Level.ERROR) {
            if (compare((String) l.getMessage(),"Uncaught Exception caught by jmri.util.exceptionhandler.UncaughtExceptionHandler")) {
                // still an error, just suppressed
            } else {
                unexpectedErrorSeen = true;
            }
        }
        if (l.getLevel() == Level.WARN) unexpectedWarnSeen = true;
        if (l.getLevel() == Level.INFO) unexpectedInfoSeen = true;
        super.append(l);
    }

    /**
     * Remove any messages stored up, returning how many there were. This is
     * used to skip over messages that don't matter, e.g. during setting up a
     * test. Removed messages are not sent for further logging.
     * @param level lowest level counted in return value, e.g. WARN means WARN and higher will be counted
     * @return count of skipped messages
     * @see #clearBacklog()
     */
    public static int clearBacklog(Level level) {
        if (list.isEmpty()) {
            return 0;
        }
        int retval = 0;
        for (LoggingEvent event : list) {
            if (event != null && event.getLevel() != null && event.getLevel().toInt() >= level.toInt()) retval++;  // higher number -> more severe, specific, limited
            // with Log4J 2, this could have used isMoreSpecificThan(level)
        }
        list.clear();
        return retval;
    }
    /**
     * Remove any messages stored up, returning how many of WARN or higher severity there are. This is
     * used to skip over messages that don't matter, e.g. during setting up a
     * test. Removed messages are not sent for further logging.
     * @return count of skipped messages of WARN or more specific level
     * @see #clearBacklog(Level)
     */
    public static int clearBacklog() {
        return clearBacklog(Level.WARN);
    }
    
    /**
     * Verify that no messages were emitted, logging any that were. Does not
     * stop the logging. Clears the accumulated list.
     *
     * @return true if no messages logged
     */
    public static boolean verifyNoBacklog() {
        if (list.isEmpty()) {
            return true;
        }
        while (!list.isEmpty()) { // should probably add a skip of lower levels?
            LoggingEvent evt = list.remove(0);
            instance().superappend(evt);
        }
        return false;
    }

    /**
     * Check that the next queued message was of Error severity, and has a
     * specific message.
     * <P>
     * Invokes a JUnit Assert if the message doesn't match
     */
    public static void assertErrorMessage(String msg) {
        if (list.isEmpty()) {
            Assert.fail("No message present: " + msg);
            return;
        }

        LoggingEvent evt = list.remove(0);

        // next piece of code appears three times, should be refactored away during Log4J 2 migration
        while ((evt.getLevel() == Level.INFO) || (evt.getLevel() == Level.DEBUG) || (evt.getLevel() == Level.TRACE)) { // better in Log4J 2
            if (list.isEmpty()) {
                Assert.fail("Only debug/info messages present: " + msg);
                return;
            }
            evt = list.remove(0);
        }

        // check the remaining message, if any
        if (evt.getLevel() != Level.ERROR) {
            Assert.fail("Level mismatch when looking for ERROR message: \"" + msg + "\" found \"" + (String) evt.getMessage() + "\"");
        }

        if (!compare((String) evt.getMessage(), msg)) {
            Assert.fail("Looking for ERROR message \"" + msg + "\" got \"" + evt.getMessage() + "\"");
        }
    }

    /**
     * If there's a next matching message of Error severity, just ignore it.
     * Not an error if not present; mismatch is an error
     */
    public static void suppressErrorMessage(String msg) {
        if (list.isEmpty()) {
            return;
        }

        LoggingEvent evt = list.remove(0);

        while ((evt.getLevel() == Level.INFO) || (evt.getLevel() == Level.DEBUG) || (evt.getLevel() == Level.TRACE)) { // better in Log4J 2
            if (list.isEmpty()) {
                Assert.fail("Only debug/info messages present: " + msg);
                return;
            }
            evt = list.remove(0);
        }

        // check the remaining message, if any
        if (evt.getLevel() != Level.ERROR) {
            Assert.fail("Level mismatch when looking for ERROR message: \"" + msg + "\" found \"" + (String) evt.getMessage() + "\"");
        }

        if (!compare((String) evt.getMessage(), msg)) {
            Assert.fail("Looking for ERROR message \"" + msg + "\" got \"" + evt.getMessage() + "\"");
        }
    }

    /**
     * Check that the next queued message was of Warn severity, and has a
     * specific message.
     * <P>
     * Invokes a JUnit Assert if the message doesn't match
     */
    public static void assertWarnMessage(String msg) {
        if (list.isEmpty()) {
            Assert.fail("No message present: " + msg);
            return;
        }
        LoggingEvent evt = list.remove(0);

        while ((evt.getLevel() == Level.INFO) || (evt.getLevel() == Level.DEBUG) || (evt.getLevel() == Level.TRACE)) { // better in Log4J 2
            if (list.isEmpty()) {
                Assert.fail("Only debug/info messages present: " + msg);
                return;
            }
            evt = list.remove(0);
        }

        // check the remaining message, if any
        if (evt.getLevel() != Level.WARN) {
            Assert.fail("Level mismatch when looking for WARN message: \"" + msg + "\" found \"" + (String) evt.getMessage() + "\"");
        }

        if (!compare((String) evt.getMessage(), msg)) {
            Assert.fail("Looking for WARN message \"" + msg + "\" got \"" + evt.getMessage() + "\"");
        }
    }

    protected static boolean compare(String s1, String s2) {
        return org.apache.commons.lang3.StringUtils.deleteWhitespace(s1).equals(org.apache.commons.lang3.StringUtils.deleteWhitespace(s2));
    }
    
    public static JUnitAppender instance() {
        return JUnitAppender.instance;
    }
}
