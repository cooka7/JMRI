package jmri.jmrix.lenz;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Tests for the jmri.jmrix.lenz package
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
        TestSuite suite = new TestSuite("jmri.jmrix.lenz.XNetTest");  // no tests in this class itself
        suite.addTest(new TestSuite(LenzCommandStationTest.class));
        suite.addTest(new JUnit4TestAdapter(LenzConnectionTypeListTest.class));
        suite.addTest(new TestSuite(XNetMessageTest.class));
        suite.addTest(new TestSuite(XNetReplyTest.class));
        suite.addTest(new TestSuite(XNetTurnoutTest.class));
        suite.addTest(new TestSuite(XNetSensorTest.class));
        suite.addTest(new TestSuite(XNetLightTest.class));
        suite.addTest(new TestSuite(XNetPacketizerTest.class));
        suite.addTest(new JUnit4TestAdapter(XNetTurnoutManagerTest.class));
        suite.addTest(new JUnit4TestAdapter(XNetSensorManagerTest.class));
        suite.addTest(new JUnit4TestAdapter(XNetLightManagerTest.class));
        suite.addTest(new JUnit4TestAdapter(XNetTrafficControllerTest.class));
        suite.addTest(new TestSuite(XNetTrafficRouterTest.class));
        suite.addTest(new TestSuite(XNetSystemConnectionMemoTest.class));
        suite.addTest(new JUnit4TestAdapter(XNetThrottleTest.class));
        suite.addTest(new TestSuite(XNetConsistManagerTest.class));
        suite.addTest(new JUnit4TestAdapter(XNetConsistTest.class));
        suite.addTest(new TestSuite(XNetInitializationManagerTest.class));
        suite.addTest(new TestSuite(XNetProgrammerTest.class));
        suite.addTest(new TestSuite(XNetProgrammerManagerTest.class));
        suite.addTest(new TestSuite(XNetOpsModeProgrammerTest.class));
        suite.addTest(new TestSuite(XNetPowerManagerTest.class));
        suite.addTest(new JUnit4TestAdapter(XNetThrottleManagerTest.class));
        suite.addTest(new TestSuite(XNetExceptionTest.class));
        suite.addTest(new TestSuite(XNetMessageExceptionTest.class));
        suite.addTest(new JUnit4TestAdapter(XNetStreamPortControllerTest.class));
        suite.addTest(jmri.jmrix.lenz.li100.PackageTest.suite());
        suite.addTest(jmri.jmrix.lenz.li100f.PackageTest.suite());
        suite.addTest(new JUnit4TestAdapter(jmri.jmrix.lenz.li101.PackageTest.class));
        suite.addTest(jmri.jmrix.lenz.liusb.PackageTest.suite());
        suite.addTest(jmri.jmrix.lenz.xntcp.PackageTest.suite());
        suite.addTest(jmri.jmrix.lenz.liusbserver.PackageTest.suite());
        suite.addTest(jmri.jmrix.lenz.liusbethernet.PackageTest.suite());
        suite.addTest(jmri.jmrix.lenz.xnetsimulator.PackageTest.suite());
        suite.addTest(jmri.jmrix.lenz.hornbyelite.PackageTest.suite());
        suite.addTest(jmri.jmrix.lenz.ztc640.PackageTest.suite());
        suite.addTest(new JUnit4TestAdapter(BundleTest.class));
        suite.addTest(new JUnit4TestAdapter(jmri.jmrix.lenz.swing.PackageTest.class));
        suite.addTest(new JUnit4TestAdapter(jmri.jmrix.lenz.configurexml.PackageTest.class));
        suite.addTest(new JUnit4TestAdapter(XNetNetworkPortControllerTest.class));
        suite.addTest(new JUnit4TestAdapter(XNetSerialPortControllerTest.class));
        suite.addTest(new JUnit4TestAdapter(XNetSimulatorPortControllerTest.class));

        return suite;
    }

}
