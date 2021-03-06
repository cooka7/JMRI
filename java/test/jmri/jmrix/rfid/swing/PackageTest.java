package jmri.jmrix.rfid.swing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   jmri.jmrix.rfid.swing.serialmon.PackageTest.class,
   RfidMenuTest.class,
   RfidComponentFactoryTest.class
})
/**
 * Tests for the jmri.jmrix.rfid.swing package
 *
 * @author Paul Bender Copyright (C) 2016
 */
public class PackageTest {
}
