package tudresden.ocl20.testautomation;

import java.io.IOException;

import tudresden.ocl20.testautomation.builder.SimpleInvarianceBuilder;
import tudresden.ocl20.testautomation.exceptions.TestCreationException;
import tudresden.ocl20.testautomation.tools.TestingUnit;

public class GenerateMain {

	private SimpleInvarianceBuilder invBuilder;

	public GenerateMain() throws TestCreationException {

		this.invBuilder = new SimpleInvarianceBuilder();
	}

	/**
	 * @param args
	 * @throws TestCreationException
	 * @throws IOException
	 */
	public static void main(String[] args) throws TestCreationException {

		GenerateMain me = new GenerateMain();

		// SQUAM-Testsuite 
//		me
//				.buildInvarianceTests(
//						"squam",
//						"tudresden.ocl20.testautomation/bin/umlmodel.uml",
//						"tudresden.ocl20.testautomation/bin/uml/ModelInstanceProviderClass.class",
//						"tests/squam/");
		
		me
		.buildInvarianceTests(
				"ocl20tests",
				"tudresden.ocl20.testautomation/bin/umlmodel.uml",
				"tudresden.ocl20.testautomation/bin/uml/ModelInstanceProviderClass.class",
				"tests/ocl20tests/");

		// Bremen Benchmark (B5)
//		String benchmarkSubdir =
//				"tudresden.ocl20.testautomation/bin/tudresden/ocl20/benchmark/testdata/";
//		me.buildInvarianceTests("benchmark", benchmarkSubdir + "common/DummyWorld.uml",
//				benchmarkSubdir + "common/ModelInstance.class",
//				"tests/benchmark");


	}

	protected void buildInvarianceTests(String name, String model,
			String modelInstance, String statements) throws TestCreationException {

		TestingUnit group = TestingUnit.createTestingUnit(name,//
				model, modelInstance, statements);

		this.invBuilder.generate(group);

	}
}
