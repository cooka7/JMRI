package jmri.jmrix.mrc.swing;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Tests for the jmri.jmrix.mrc.swing package
 *
 * @author	Bob Jacobsen
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
        TestSuite suite = new TestSuite("jmri.jmrix.mrc.swing.PackageTest");  // no tests in this class itself
        suite.addTest(new junit.framework.JUnit4TestAdapter(BundleTest.class));

        suite.addTest(jmri.jmrix.mrc.swing.packetgen.PackageTest.suite());

        if (!System.getProperty("java.awt.headless", "false").equals("true")) {
        }

        return suite;
    }

}
