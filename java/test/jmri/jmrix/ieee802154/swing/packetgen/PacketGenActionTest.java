package jmri.jmrix.ieee802154.swing.packetgen;

import apps.tests.Log4JFixture;
import jmri.util.JUnitUtil;
import jmri.jmrix.ieee802154.IEEE802154SystemConnectionMemo;
import jmri.jmrix.ieee802154.IEEE802154TrafficController;
import jmri.InstanceManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import java.awt.GraphicsEnvironment;

/**
 * Test simple functioning of PacketGenAction
 *
 * @author	Paul Bender Copyright (C) 2016
 */
public class PacketGenActionTest {

    @Test
    public void testStringMemoCtor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        PacketGenAction action = new PacketGenAction("IEEE 802.15.4 test Action", new IEEE802154SystemConnectionMemo());
        Assert.assertNotNull("exists", action);
    }

    @Test
    public void testMemoCtor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        PacketGenAction action = new PacketGenAction( new IEEE802154SystemConnectionMemo());
        Assert.assertNotNull("exists", action);
    }

    @Test
    public void testStringCtor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        IEEE802154SystemConnectionMemo memo = new IEEE802154SystemConnectionMemo();
        IEEE802154TrafficController tc = new IEEE802154TrafficController(){
            public void setInstance() {
            }
            protected jmri.jmrix.AbstractMRReply newReply() {
                return null;
            }
            public jmri.jmrix.ieee802154.IEEE802154Node newNode() {
                return null;
            }
        };
        InstanceManager.setDefault(IEEE802154SystemConnectionMemo.class, memo);
        PacketGenAction action = new PacketGenAction("IEEE 802.15.4 test Action");
        Assert.assertNotNull("exists", action);
    }

    @Test
    public void testDefaultCtor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        IEEE802154SystemConnectionMemo memo = new IEEE802154SystemConnectionMemo();
        IEEE802154TrafficController tc = new IEEE802154TrafficController(){
            public void setInstance() {
            }
            protected jmri.jmrix.AbstractMRReply newReply() {
                return null;
            }
            public jmri.jmrix.ieee802154.IEEE802154Node newNode() {
                return null;
            }
        };
        InstanceManager.setDefault(IEEE802154SystemConnectionMemo.class, memo);
        PacketGenAction action = new PacketGenAction();
        Assert.assertNotNull("exists", action);
    }

    @Before
    public void setUp() {
        Log4JFixture.setUp();
        JUnitUtil.resetInstanceManager();
    }

    @After
    public void tearDown() {
        JUnitUtil.resetInstanceManager();
        Log4JFixture.tearDown();
    }
}
