package org.dresdenocl.standardlibrary.java.test.tests;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Contains all test suites for the Java Standard Library.
 * 
 * @author Michael Thiele
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ JavaOclAnyTest.class, JavaOclBagTest.class,
		JavaOclBooleanTest.class, JavaOclCollectionTest.class,
		JavaOclIntegerTest.class, JavaOclInvalidTest.class,
		JavaOclOrderedSetTest.class, JavaOclRealTest.class,
		JavaOclSequenceTest.class, JavaOclSetTest.class,
		JavaOclSortedCollectionTest.class, JavaOclStringTest.class })
public class AllStandardLibraryTests extends AbstractDresdenOclTest {

}
