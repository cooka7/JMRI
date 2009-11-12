
// LRouteTableActionTest.java

package jmri.jmrit.beantable;

import junit.framework.*;

//import junit.extensions.jfcunit.*;
//import junit.extensions.jfcunit.finder.*;

import java.util.ResourceBundle;

import jmri.InstanceManager;
import jmri.Conditional;
import jmri.Light;
import jmri.Route;
import jmri.Sensor;
import jmri.SignalHead;
import jmri.Turnout;

import jmri.util.JUnitUtil;

/**
 * Tests for the jmri.jmrit.beantable.LRouteTableAction class
 * @author	Pete Cressman  Copyright 2009
 */
public class LRouteTableActionTest extends jmri.util.SwingTestCase //TestCase // jmri.util.SwingTestCase 
{
	static final ResourceBundle rbx = ResourceBundle
			.getBundle("jmri.jmrit.beantable.LRouteTableBundle");

    private LRouteTableAction _lRouteTable;
    private LogixTableAction _logixTable;

    public void testCreate() {
        _lRouteTable.actionPerformed(null);
        _lRouteTable.addPressed(null);
        _lRouteTable._userName.setText("TestLRoute");    
        _lRouteTable._systemName.setText("T");
        _lRouteTable._alignList.get(5).setIncluded(true);
        for (int i=0; i<25; i++)
        {
            _lRouteTable._inputList.get(3*i).setIncluded(true);
            _lRouteTable._outputList.get(3*i+1).setIncluded(true);
        }
        _lRouteTable.createPressed(null);
        java.util.List<String> l = InstanceManager.logixManagerInstance().getSystemNameList();
        assertEquals("Logix Count", 1, l.size());

        _lRouteTable.m.setValueAt(rbx.getString("ButtonEdit"), 0, 
                                  LRouteTableAction.LBeanTableDataModel.EDITCOL);
        // now close window
        //TestHelper.disposeWindow(_lRouteTable._addFrame,this);
    }
/*
    @SuppressWarnings("unchecked")
	public void testPrompt() {
        assertNotNull("LRouteTableAction is null!", _lRouteTable);        // test has begun
        _lRouteTable.addPressed(null);
        _lRouteTable._userName.setText("TestLRoute2");    
        _lRouteTable._systemName.setText("T2");
        for (int i=0; i<25; i++)
        {
            _lRouteTable._inputList.get(3*i).setIncluded(true);
            _lRouteTable._outputList.get(3*i).setIncluded(true);
        }
        _lRouteTable._alignList.get(5).setIncluded(true);

        // find the "Update" button in add/edit window and press it,
        // so the window is marked as dirty
        NamedComponentFinder finder = new NamedComponentFinder(JComponent.class, "CreateButton" );
        JButton updateButton = ( JButton ) finder.find( _lRouteTable._addFrame, 0);
        Assert.assertNotNull( "Could not find the updateButton", updateButton );
        //getHelper().enterClickAndLeave( new MouseEventData( this, updateButton ) );
        
        // now close window
        TestHelper.disposeWindow(_lRouteTable._addFrame,this);
        
        // cancel the Reminder dialog
        DialogFinder dFinder = new DialogFinder( "Reminder" );
        java.util.List<JDialog> showingDialogs = dFinder.findAll();
        //Assert.assertEquals( "Number of dialogs showing is wrong", 1, showingDialogs.size( ) );
        JDialog dialog = showingDialogs.get( 0 );
        Assert.assertEquals( "Wrong dialog showing up", "Reminder", dialog.getTitle( ) );
        getHelper().disposeWindow( dialog, this );
        
    }
*/
    // from here down is testing infrastructure

    public LRouteTableActionTest(String s) {
        super(s);
    }

    // Main entry point
    static public void main(String[] args) {
        String[] testCaseName = {LRouteTableActionTest.class.getName()};
        junit.swingui.TestRunner.main(testCaseName);
    }

    // test suite from all defined tests
    public static Test suite() {
        Test suite = new TestSuite(LRouteTableActionTest.class);
        return suite;
    }

    // The minimal setup for log4J
    protected void setUp() throws Exception { 
        apps.tests.Log4JFixture.setUp(); 
        super.setUp();

        JUnitUtil.resetInstanceManager();
        JUnitUtil.initInternalTurnoutManager();
        JUnitUtil.initInternalLightManager();
        JUnitUtil.initInternalSensorManager();
        JUnitUtil.initInternalSignalHeadManager();

        _lRouteTable = new LRouteTableAction("LRoute");
        assertNotNull("LRouteTableAction is null!", _lRouteTable);        // test has begun
        _logixTable = new LogixTableAction();
        assertNotNull("LogixTableAction is null!", _logixTable);
        
        for (int i=1; i<20; i++)
        {
            Sensor s = InstanceManager.sensorManagerInstance().newSensor("IS"+i, "Sensor"+i);
            assertNotNull("Sensor is null!", s);
            Turnout t = InstanceManager.turnoutManagerInstance().newTurnout("IT"+i, "Turnout"+i);
            assertNotNull("Turnout is null!", t);
            Light l = InstanceManager.lightManagerInstance().newLight("IL"+(i), "Light"+i);
            assertNotNull(i+"th Light is null!", l);
            Conditional c = InstanceManager.conditionalManagerInstance().createNewConditional(
                                                        "Conditional"+i, "Conditional"+i);
            assertNotNull(i+"th Conditional is null!", c);
            SignalHead sh = new jmri.implementation.VirtualSignalHead("Signal"+i);
            assertNotNull(i+"th SignalHead is null!", sh);
            InstanceManager.signalHeadManagerInstance().register(sh);
            Route r = new jmri.implementation.DefaultRoute("Route"+i);
            assertNotNull(i+"th Route is null!", r);
            InstanceManager.routeManagerInstance().register(r);
        }
    }
    protected void tearDown() throws Exception { 
        super.tearDown();
        JUnitUtil.resetInstanceManager();
    }
}

