package jmri.jmrix.srcp;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * SRCPThrottleManagerTest.java
 *
 * Description:	tests for the jmri.jmrix.srcp.SRCPThrottleManager class
 *
 * @author	Bob Jacobsen
 * @author      Paul Bender Copyright (C) 2016	
 */
public class SRCPThrottleManagerTest extends jmri.managers.AbstractThrottleManagerTestBase {

    // The minimal setup for log4J
    @Override
    @Before
    public void setUp() {
        apps.tests.Log4JFixture.setUp();
        SRCPBusConnectionMemo sm = new SRCPBusConnectionMemo(new SRCPTrafficController() {
            @Override
            public void sendSRCPMessage(SRCPMessage m, SRCPListener reply) {
            }
        }, "A", 1);

        tm = new SRCPThrottleManager(sm);
    }

    @After
    public void tearDown() {
        apps.tests.Log4JFixture.tearDown();
    }
}
