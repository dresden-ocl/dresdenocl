package tudresden.ocl20.pivot.standardlibrary.java.test.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Contains all test suites for the Java Standard Library.
 * 
 * @author Michael Thiele
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { JavaOclBooleanTest.class, JavaOclIntegerTest.class,
		JavaOclRealTest.class, JavaOclCollectionTest.class,
		JavaOclSequenceTest.class, JavaOclSortedCollectionTest.class })
public class AllTests {

}
