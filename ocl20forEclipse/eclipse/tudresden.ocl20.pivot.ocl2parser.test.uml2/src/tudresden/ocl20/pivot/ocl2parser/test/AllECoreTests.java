package tudresden.ocl20.pivot.ocl2parser.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tudresden.ocl20.pivot.ocl2parser.test.ecore.*;
import tudresden.ocl20.pivot.ocl2parser.test.simpletests.SimpleModelLoadTest;

/**
 * This test wraps all others and runs them as a suite.
 * 
 * @author Christoph Dähne
 */

//specify a runner class: Suite.class
@RunWith(Suite.class)

// specify an array of test classes
@Suite.SuiteClasses({
  AtPreTest.class,
  BodySetTest.class,
  CollectionRangeTest.class,
  CollectionTest.class,
  DefinitionTest.class,
  IncludesAllTest.class,
  IncludesTest.class,
  IsUniqueTest.class,
  IteratorOclIsTypeOfTest.class,
  LetExpTest.class,
  OclFilesTest.class,
  PropertyOutput.class,
  StaticPropertyTest.class,
  SimpleModelLoadTest.class
})

public class AllECoreTests {
	public static final String MODEL_BUNDLE =
			"tudresden.ocl20.pivot.examples.royalandloyal";
	public static final String MODEL_BUNDLE_PATH = "model/";
	public static final String META_MODEL_NAME =
			"tudresden.ocl20.pivot.metamodels.ecore";
}
