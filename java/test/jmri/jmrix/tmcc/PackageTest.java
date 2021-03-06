package jmri.jmrix.tmcc;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Tests for the jmri.jmrix.tmcc package.
 *
 * @author Bob Jacobsen Copyright 2003
 */
public class PackageTest extends TestCase {

    // from here down is testing infrastructure
    public PackageTest(String s) {
        super(s);
    }

    // Main entry point
    static public void main(String[] args) {
        String[] testCaseName = {"-noloading", PackageTest.class.getName()};
        junit.textui.TestRunner.main(testCaseName);
    }

    // test suite from all defined tests
    public static Test suite() {
        apps.tests.AllTest.initLogging();
        TestSuite suite = new TestSuite("jmri.jmrix.tmcc.SerialTest");
        suite.addTest(SerialTurnoutTest.suite());
        suite.addTest(new JUnit4TestAdapter(SerialTurnoutManagerTest.class));
        suite.addTest(SerialMessageTest.suite());
        suite.addTest(SerialReplyTest.suite());
        suite.addTest(new JUnit4TestAdapter(SerialTrafficControllerTest.class));
        suite.addTest(SerialAddressTest.suite());
        suite.addTest(new JUnit4TestAdapter(jmri.jmrix.tmcc.serialdriver.PackageTest.class));
        suite.addTest(new JUnit4TestAdapter(jmri.jmrix.tmcc.configurexml.PackageTest.class));
        suite.addTest(new JUnit4TestAdapter(jmri.jmrix.tmcc.packetgen.PackageTest.class));
        suite.addTest(new JUnit4TestAdapter(TMCCMenuTest.class));
        suite.addTest(new JUnit4TestAdapter(jmri.jmrix.tmcc.serialmon.SerialMonFrameTest.class));
        suite.addTest(new JUnit4TestAdapter(TMCCSystemConnectionMemoTest.class));
        suite.addTest(new JUnit4TestAdapter(SerialPortControllerTest.class));
        return suite;
    }

    // The minimal setup for log4J
    protected void setUp() {
        apps.tests.Log4JFixture.setUp();
    }

    protected void tearDown() {
        apps.tests.Log4JFixture.tearDown();
    }
}
