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

import org.dresdenocl.metrics.test.AllMetricsTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.dresdenocl.essentialocl.types.tests.EssentialOCLAllTests;
import org.dresdenocl.examples.pain.test.AllPainTests;
import org.dresdenocl.examples.royalsandloyals.test.AllRoyalAndLoyalAspectJTests;
import org.dresdenocl.interpreter.test.AllOclInterpreterTests;
import org.dresdenocl.metamodels.ecore.test.tests.AllEcoreMetaModelTests;
import org.dresdenocl.metamodels.java.test.tests.TestJavaMetaModel;
import org.dresdenocl.metamodels.uml2.test.tests.AllUml2MetaModelTests;
import org.dresdenocl.metamodels.xsd.test.AllXmlSchemaMetamodelTests;
import org.dresdenocl.modelbus.test.AllModelbusTests;
import org.dresdenocl.modelinstancetype.ecore.test.AllEcoreModelInstanceTypeTests;
import org.dresdenocl.modelinstancetype.java.test.AllJavaModelInstanceTypeTests;
import org.dresdenocl.modelinstancetype.xml.test.AllXmlInstanceTests;
import org.dresdenocl.ocl2parser.test.AllOCL2ParserTests;
import org.dresdenocl.standardlibrary.java.test.tests.AllStandardLibraryTests;
import org.dresdenocl.tools.codegen.declarativ.ocl2sql.test.FastOcl2SqlTests;
import org.dresdenocl.tools.codegen.ocl2java.test.AllOcl2JavaTests;
import org.dresdenocl.tools.codegen.ocl2java.test.aspectj.AllAspectJTests;
import org.dresdenocl.tools.template.test.AllTestsTemplate;
import org.dresdenocl.tools.transformation.pivot2sql.test.AllTestsPivot2Sql;
import org.dresdenocl.tools.transformation.test.AllTestsTransformation;
import org.dresdenocl.tracer.test.AllTracerTests;

/**
 * <p>
 * Collects all test suites of Dresden OCL.
 * </p>
 * 
 * @author Claas Wilke
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({

/* Core Tests: */
EssentialOCLAllTests.class, AllModelbusTests.class,

/* Metamodel tests: */
AllEcoreMetaModelTests.class, TestJavaMetaModel.class,
		AllUml2MetaModelTests.class, AllXmlSchemaMetamodelTests.class,

		/* Parser tests: */
		AllOCL2ParserTests.class,

		/* Metrics tests: */
		AllMetricsTests.class,

		/* Model Instance Type tests: */
		AllEcoreModelInstanceTypeTests.class,
		AllJavaModelInstanceTypeTests.class, AllXmlInstanceTests.class,

		/* Interpreter Tests: */
		AllStandardLibraryTests.class, AllOclInterpreterTests.class,
		AllPainTests.class,

		/* Code Generator tests: */
		AllTestsTemplate.class,

		/* OCL2Java tests: */
		AllOcl2JavaTests.class, AllAspectJTests.class,
		AllRoyalAndLoyalAspectJTests.class,

		/* OCL2SQL tests: */
		AllTestsTransformation.class, AllTestsPivot2Sql.class,
		FastOcl2SqlTests.class,
		
		/* Tracer tests: */
		AllTracerTests.class})
public class DresdenOclTestSuite {
	/*
	 * This class remains completely empty, being used only as a holder for the
	 * above annotations.
	 */
}