package jmri.jmrix.roco.z21.simulator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import jmri.jmrix.lenz.XNetMessage;
import jmri.jmrix.lenz.XNetReply;

/**
 * Z21XNetSimulatorAdapterTest.java
 * Description:	tests for the jmri.jmrix.roco.z21.simulator.z21XNetSimulatorAdapter
 * class
 *
 * @author	Paul Bender Copyright (C) 2016
 */
public class Z21XNetSimulatorAdapterTest {

    @Test
    public void testCtor() {
        Z21XNetSimulatorAdapter a = new Z21XNetSimulatorAdapter();
        Assert.assertNotNull(a);
    }

    @Test
    public void testGenerateCSVersionReply(){
        Z21XNetSimulatorAdapter a = new Z21XNetSimulatorAdapter();
        Assert.assertEquals("CS Version Reply",new XNetReply("63 21 30 12 60"),a.generateReply(new XNetMessage("21 21 00")));
    }

    @Test
    public void testGenerateResumeOperationsReply(){
        Z21XNetSimulatorAdapter a = new Z21XNetSimulatorAdapter();
        Assert.assertEquals("CS Resume Operations Reply",new XNetReply("61 82 E3"),a.generateReply(new XNetMessage("21 81 A0")));
    }

    @Test
    public void testGenerateEmergencyStopReply(){
        Z21XNetSimulatorAdapter a = new Z21XNetSimulatorAdapter();
        Assert.assertEquals("CS Emergency Stop Reply",new XNetReply("61 82 E3"),a.generateReply(new XNetMessage("21 80 A1")));
    }

    @Test
    public void testGenerateEmergencyStopAllReply(){
        Z21XNetSimulatorAdapter a = new Z21XNetSimulatorAdapter();
        Assert.assertEquals("CS Emergeny Stop All Reply",new XNetReply("81 00 81"),a.generateReply(new XNetMessage("80 80")));
    }

    @Test
    public void testGenerateEmergencyStopLocoReply(){
        Z21XNetSimulatorAdapter a = new Z21XNetSimulatorAdapter();
        Assert.assertEquals("CS Emergeny Stop Specific Loco (XNetV4)",new XNetReply("01 04 05"),a.generateReply(new XNetMessage("92 00 02 90")));
    }

    @Test
    public void testGenerateEmergencyStopLocoReplyV1V2(){
        Z21XNetSimulatorAdapter a = new Z21XNetSimulatorAdapter();
        Assert.assertEquals("CS Emergeny Stop Specific Loco (XNetV1,V2)",new XNetReply("01 04 05"),a.generateReply(new XNetMessage("91 02 93")));
    }

    // The minimal setup for log4J
    @Before
    public void setUp() {
        apps.tests.Log4JFixture.setUp();
    }

    @After
    public void tearDown() {
        apps.tests.Log4JFixture.tearDown();
    }

}
