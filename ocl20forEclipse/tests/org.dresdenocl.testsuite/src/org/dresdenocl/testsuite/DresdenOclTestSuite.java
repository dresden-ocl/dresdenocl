/*
 * Copyright (C) 2011 by Claas Wilke (claas.wilke@tu-dresden.de) This file is part of
 * Dresden OCL. Dresden OCL is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version. Dresden OCL is distributed in the hope that it
 * will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with Dresden OCL. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tudresden.ocl20.pivot.essentialocl.types.tests.EssentialOCLAllTests;
import tudresden.ocl20.pivot.examples.pain.test.AllPainTests;
import tudresden.ocl20.pivot.examples.royalsandloyals.test.AllRoyalAndLoyalAspectJTests;
import tudresden.ocl20.pivot.interpreter.test.AllOclInterpreterTests;
import tudresden.ocl20.pivot.metamodels.ecore.test.tests.AllEcoreMetaModelTests;
import tudresden.ocl20.pivot.metamodels.java.test.tests.TestJavaMetaModel;
import tudresden.ocl20.pivot.metamodels.uml2.test.tests.AllUml2MetaModelTests;
import tudresden.ocl20.pivot.modelbus.test.AllModelbusTests;
import tudresden.ocl20.pivot.modelinstancetype.java.test.AllJavaModelInstanceTypeTests;
import tudresden.ocl20.pivot.ocl2parser.test.AllOCL2ParserTests;
import tudresden.ocl20.pivot.standardlibrary.java.test.tests.AllStandardLibraryTests;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.test.AllOcl2JavaTests;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.test.aspectj.AllAspectJTests;
import tudresden.ocl20.pivot.tools.template.test.AllTestsTemplate;
import tudresden.ocl20.pivot.tools.transformation.test.AllTestsTransformation;

/**
 * <p>
 * Collects all test suites of Dresden OCL.
 * </p>
 * 
 * @author Claas Wilke
 */
@RunWith(Suite.class)
// FIXME Claas: Collect all test suites.
@Suite.SuiteClasses({

/* Core Tests: */
EssentialOCLAllTests.class, AllModelbusTests.class,

/* Metamodel tests: */
AllEcoreMetaModelTests.class, TestJavaMetaModel.class,
		AllUml2MetaModelTests.class,
		/* TODO AllXmlSchemaMetamodelTests .class, */

		/* Parser tests: */
		AllOCL2ParserTests.class,

		/* Model Instance Type tests: */
		/* TODO AllEcoreModelInstanceTypeTests.class, */
		AllJavaModelInstanceTypeTests.class, /* TODO AllXmlInstanceTests.class, */

		/* Interpreter Tests: */
		AllStandardLibraryTests.class, AllOclInterpreterTests.class,
		AllPainTests.class,

		/* Code Generator tests: */
		AllTestsTemplate.class,

		/* OCL2Java tests: */
		AllOcl2JavaTests.class, AllAspectJTests.class,
		AllRoyalAndLoyalAspectJTests.class,

		/* OCL2SQL tests: */
		AllTestsTransformation.class })
public class DresdenOclTestSuite {
	/*
	 * This class remains completely empty, being used only as a holder for the
	 * above annotations.
	 */
}