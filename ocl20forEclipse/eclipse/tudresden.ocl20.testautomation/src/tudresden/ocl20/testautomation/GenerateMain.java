package tudresden.ocl20.testautomation;

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;

import tudresden.ocl20.testautomation.builder.StaticTestBuilder;
import tudresden.ocl20.testautomation.exceptions.TestCreationException;
import tudresden.ocl20.testautomation.tools.TestGroup;

public class GenerateMain {

	private StaticTestBuilder testBuilder;

	static {
		PropertyConfigurator.configure("log4j.properties");
	}

	public GenerateMain() throws TestCreationException {

		this.testBuilder = new StaticTestBuilder();

	}

	/**
	 * @param args
	 * @throws TestCreationException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TestCreationException {

		GenerateMain me = new GenerateMain();


		
		TestGroup benchmarkB1 =
				new TestGroup(
						"benchmarkb1",
						"tudresden.ocl20.testautomation/bin/benchmark.model.ecore", //
						"tudresden.ocl20.testautomation/bin/benchmark/b1/ModelFirstState.class",//
						"tests/benchmark/b1");

		benchmarkB1.createStatementTestUnits();
		me.testBuilder.generate(benchmarkB1);
		
		
		TestGroup benchmarkB4 =
			new TestGroup(
					"benchmarkb4",
					"tudresden.ocl20.testautomation/bin/umlmodel.uml", //
					"tudresden.ocl20.testautomation/bin/uml/ModelInstanceProviderClass.class",//
					"tests/benchmark/b4");

	benchmarkB4.createStatementTestUnits();
	me.testBuilder.generate(benchmarkB4);

		// SQUAM-Testsuite
		 TestGroup squamTestGroup =
		 new TestGroup(
		 "squam",
		 "tudresden.ocl20.testautomation/bin/umlmodel.uml", //
		 "tudresden.ocl20.testautomation/bin/uml/ModelInstanceProviderClass.class",//
		 "tests/squam/");
		 // Extending the Standardlibrary is not implemented currently
		 squamTestGroup.addPathToIgnore("/Definitions/StringExtensions.oclx.ocl");
				
		 squamTestGroup.createStatementTestUnits();
				
		 me.testBuilder.generate(squamTestGroup);

		// ocl20-internal tests
		 me
		 .buildSimpleInvarianceTests(
		 "ocl20tests",
		 "tudresden.ocl20.testautomation/bin/umlmodel.uml",
		 "tudresden.ocl20.testautomation/bin/uml/ModelInstanceProviderClass.class",
		 "tests/ocl20tests/");
		//		
		// // bug3007222
		// String dir =
		// "tudresden.ocl20.testautomation/bin/sourceforge/bug3007222/";
		// me
		// .buildSimpleInvarianceTests(
		// "bug3007222",
		// dir + "model.uml",
		// dir + "ModelInstanceProviderClass.class",
		// "tests/sourceforge/Bug3007222.ocl");
		//
		// TestGroup benchmarkCommon =
		// new TestGroup(
		// "benchmark",
		// "tudresden.ocl20.testautomation/bin/umlmodel.uml", //
		// "tudresden.ocl20.testautomation/bin/uml/ModelInstanceProviderClass.class",//
		// "tests/benchmark");
		// benchmarkCommon.addPathToIgnore("/b1");
		// benchmarkCommon.addPathToIgnore("/b2");
		// benchmarkCommon.addPathToIgnore("/b3");
		// benchmarkCommon.addPathToIgnore("/b4");
		// benchmarkCommon.createStatementTestUnits();
		// me.testBuilder.generate(benchmarkCommon);

		// ----------------
		// parser tests
		// ----------------
		// TestGroup parsertest =
		// new TestGroup(
		// "parsertests",
		// "tudresden.ocl20.testautomation/bin/parsertest.model.uml", //
		// "",//
		// "tests/parsertest");
		//		
		//	
		// parsertest.createStatementTestUnits();
		//
		// me.testBuilder.generate(parsertest);

	}

	protected void buildSimpleInvarianceTests(String name, String model,
			String modelInstance, String statements) throws TestCreationException {

		TestGroup group = new TestGroup(name, model, modelInstance, statements);
		group.createStatementTestUnits();

		this.testBuilder.generate(group);

	}
}
