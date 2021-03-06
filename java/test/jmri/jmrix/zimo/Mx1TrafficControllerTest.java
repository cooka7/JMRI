package jmri.jmrix.zimo;

import jmri.InstanceManager;
import jmri.util.JUnitUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit tests for the Mx1TrafficController class
 * <p>
 *
 * @author      Paul Bender Copyright (C) 2016
 */
public class Mx1TrafficControllerTest {

    private Mx1TrafficController tc = null;

    @Test
    public void testCtor(){
       Assert.assertNotNull("exists",tc);
    }

    @Before
    public void setUp(){
       apps.tests.Log4JFixture.setUp();
       JUnitUtil.resetInstanceManager();
       tc = new Mx1TrafficController(){
          @Override
          public boolean status() {return true;}
          @Override
          public void sendMx1Message(Mx1Message m, Mx1Listener reply){}
       };
    }

    @After
    public void tearDown(){
       JUnitUtil.resetInstanceManager();
       apps.tests.Log4JFixture.tearDown();
    }

}
