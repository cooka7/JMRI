package jmri.jmrix.sprog.serialdriver;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * tests for the jmri.jmrix.sprog.serialdriver package
 *
 * @author  Paul Bender Copyright (C) 2016	
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
   SerialDriverAdapterTest.class,
   ConnectionConfigTest.class,
   jmri.jmrix.sprog.serialdriver.configurexml.PackageTest.class

})
public class PackageTest {
}
