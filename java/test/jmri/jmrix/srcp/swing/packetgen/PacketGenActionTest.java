package jmri.jmrix.srcp.swing.packetgen;

import org.junit.After;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import java.awt.GraphicsEnvironment;

/**
 * @author Paul Bender Copyright(C) 2016
 */
public class PacketGenActionTest {

    private jmri.jmrix.srcp.SRCPSystemConnectionMemo memo = null;

    @Test
    public void testCtor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        Assert.assertNotNull("PacketGenAction exists",new PacketGenAction("Test",memo) );
    }

    // The minimal setup for log4J
    @Before
    public void setUp() {
        apps.tests.Log4JFixture.setUp();
        jmri.util.JUnitUtil.resetInstanceManager();

        memo = new jmri.jmrix.srcp.SRCPSystemConnectionMemo();
        jmri.InstanceManager.setDefault(jmri.jmrix.srcp.SRCPSystemConnectionMemo.class,memo);

    }

    @After
    public void tearDown() {
        jmri.util.JUnitUtil.resetInstanceManager();
        apps.tests.Log4JFixture.tearDown();
    }
}
