package tudresden.ocl20.pivot.tracer.test.standardlibrary;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite containing all Dresden OCL Tracer Tests
 * 
 * @author Lars Schuetze
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestBag.class,
	TestBoolean.class,
	TestCollection.class,
	TestIterator.class,
	TestOclInvalid.class,
	TestString.class
	})
public class AllStandardLibraryTracerTests extends AbstractDresdenOclTest {
	/* Remains empty */
}
