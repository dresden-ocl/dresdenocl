/*
Copyright (C) 2008-2010 by Claas Wilke (claas.wilke@tu-dresden.de)

This file is part of the OCL 2 Java Code Generator of Dresden OCL.

Dresden OCL is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL. If not, see <http://www.gnu.org/licenses/>.
 */

package tudresden.ocl20.pivot.tools.codegen.ocl2java.test.tests;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.osgi.framework.Bundle;

import tudresden.ocl20.pivot.language.ocl.resource.ocl.Ocl22Parser;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.IOcl2Java;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.IOcl2JavaSettings;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.Ocl2JavaFactory;

/**
 * <p>
 * Contains some test cases to create the aspectJ code for the project
 * <code>tudresden.ocl20.pivot.ocl22java.test.aspectj</code>.
 * </p>
 * 
 * @author Claas Wilke
 */
public class GenerateModelicaCode extends AbstractDiffTest {

	/** The location of the {@link IModel} used for testing. */
	private static final String MODEL_NAME = "resources/modelica/Modelica.ecore";

	/**
	 * <p>
	 * Initializes the test cases.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUp() throws Exception {

		File modelFile;
		modelFile = AbstractDiffTest.getFile(MODEL_NAME);

		testModel = ModelBusPlugin.getMetamodelRegistry()
				.getMetamodel("tudresden.ocl20.pivot.metamodels.ecore")
				.getModelProvider().getModel(modelFile);
	}

	/**
	 * <p>
	 * Tears down the test cases.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@AfterClass
	public static void tearDown() throws IllegalArgumentException,
			ModelAccessException {

		AbstractDiffTest.tearDown();
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void generateCode() throws Exception {

		List<String[]> constraints;
		constraints = new ArrayList<String[]>();

		constraints.add(new String[] { "modelica", "def01" });
		constraints.add(new String[] { "modelica", "def02" });
		constraints.add(new String[] { "modelica", "fast01" });
		constraints.add(new String[] { "modelica", "fast02" });

		this.createInstrumentationCode("tudresden.tests.modelica", constraints);
	}

	/**
	 * <p>
	 * Creates instrumentation code for a given constraints file's name.
	 * </p>
	 * 
	 * @param targetBundleId
	 *            The ID of the {@link Bundle}, whose source directory to save
	 *            the instrumentation code.
	 * @param directory
	 *            the directory of the file relative to <code>resources/</code>
	 * @param fileNames
	 *            The the directories of the files relative to
	 *            <code>resources/</code> and the names of the files for that
	 *            code shall be generated as a {@link List} containing
	 *            {@link String} arrays.
	 * @throws Exception
	 */
	protected void createInstrumentationCode(String targetBundleId,
			List<String[]> fileNames) throws Exception {

		/* Get the bundle location for the model files. */
		String sourceDirectory;
		sourceDirectory = Platform.getBundle(targetBundleId).getLocation();

		/* Remove the 'reference:file:' from the beginning. */
		sourceDirectory = sourceDirectory.substring(15);
		sourceDirectory += "src/";

		IOcl2JavaSettings settings;
		settings = Ocl2JavaFactory.getInstance()
				.createJavaCodeGeneratorSettings();
		settings.setSourceDirectory(sourceDirectory);
		settings.setSaveCode(true);
		settings.setDefaultInvariantCheckMode(IOcl2JavaSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
		settings.setGettersForDefinedAttributesEnabled(true);
		settings.setDefaultInheritanceDisabled(false);
		settings.setBasisPackage("on.eclipse.xtext");
		settings.setGettersForPropertyCallsEnabled(true);

		List<Constraint> parsedConstraints;
		parsedConstraints = new ArrayList<Constraint>();

		/* Parse the constraint. */
		for (String[] constraintFilePair : fileNames) {

			File constraintFile;
			constraintFile = AbstractDiffTest.getFile("resources/"
					+ constraintFilePair[0] + "/" + constraintFilePair[1]
					+ ".ocl");

			parsedConstraints.addAll(Ocl22Parser.INSTANCE.doParse(testModel,
					URI.createFileURI(constraintFile.getAbsolutePath()), true));
		}
		// end for.

		/* Generate and save the code. */
		IOcl2Java ocl2Java = Ocl2JavaFactory.getInstance()
				.createJavaCodeGenerator();
		ocl2Java.resetEnvironment();
		ocl2Java.setSettings(settings);
		ocl2Java.transformInstrumentationCode(parsedConstraints);
	}
}