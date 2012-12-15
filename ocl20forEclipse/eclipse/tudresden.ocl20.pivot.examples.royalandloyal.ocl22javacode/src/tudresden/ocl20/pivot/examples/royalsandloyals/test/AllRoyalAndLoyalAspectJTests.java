package tudresden.ocl20.pivot.examples.royalsandloyals.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * <p>
 * Provides a jUnit Test Suite containing all test cases for the generated
 * aspect files.
 * </p>
 * 
 * @author Claas Wilke
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { TestBodyAspects.class, TestDefAspects.class,
		TestDeriveAspects.class, TestInitAspects.class, TestInvAspects.class,
		TestPostAspects.class, TestPreAspects.class })
public class AllRoyalAndLoyalAspectJTests {
	// this class remains completely empty,
	// being used only as a holder for the above annotations
}
