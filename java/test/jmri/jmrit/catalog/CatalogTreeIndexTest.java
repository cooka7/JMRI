package jmri.jmrit.catalog;

import jmri.NamedBean;
import org.junit.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Tests for the CatalogTreeIndex class
 *
 * @author	Bob Jacobsen Copyright (C) 2009
 */
public class CatalogTreeIndexTest extends TestCase {

    // class carries its own implementation of the
    // get/set parameter code, so we test that here
    public void testSetProperty() {
        NamedBean n = new CatalogTreeIndex("sys", "usr") {
            public int getState() {
                return 0;
            }

            public void setState(int i) {
            }
        };

        n.setProperty("foo", "bar");
    }

    public void testGetParameter() {
        NamedBean n = new CatalogTreeIndex("sys", "usr") {
            public int getState() {
                return 0;
            }

            public void setState(int i) {
            }
        };

        n.setProperty("foo", "bar");
        Assert.assertEquals("bar", n.getProperty("foo"));
    }

    public void testGetSetNull() {
        NamedBean n = new CatalogTreeIndex("sys", "usr") {
            public int getState() {
                return 0;
            }

            public void setState(int i) {
            }
        };

        n.setProperty("foo", "bar");
        Assert.assertEquals("bar", n.getProperty("foo"));
        n.setProperty("foo", null);
        Assert.assertEquals(null, n.getProperty("foo"));
    }

    // from here down is testing infrastructure
    public CatalogTreeIndexTest(String s) {
        super(s);
    }

    // Main entry point
    static public void main(String[] args) {
        String[] testCaseName = {CatalogTreeIndexTest.class.getName()};
        junit.textui.TestRunner.main(testCaseName);
    }

    // test suite from all defined tests
    public static Test suite() {
        TestSuite suite = new TestSuite(CatalogTreeIndexTest.class);
        return suite;
    }

}
