/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL 2 Compiler                                                    *
 * Copyright (C) 2002, 2003 Stefan Ocke (stefan.ocke@gmx.de).        *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Technology, Dresden University Of Technology, Germany             *
 * (http://www-st.inf.tu-dresden.de).  It is understood that any     *
 * modification not identified as such is not covered by the         *
 * preceding statement.                                              *
 *                                                                   *
 * This work is free software; you can redistribute it and/or        *
 * modify it under the terms of the GNU Library General Public       *
 * License as published by the Free Software Foundation; either      *
 * version 2 of the License, or (at your option) any later version.  *
 *                                                                   *
 * This work is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *
 * Library General Public License for more details.                  *
 *                                                                   *
 * You should have received a copy of the GNU Library General Public *
 * License along with this library; if not, write to the             *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *
 * Boston, MA  02111-1307, USA.                                      *
 *                                                                   *
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other projects, please visit the web site:       *
 * http://www-st.inf.tu-dresden.de/ (Chair home page) or             *
 * http://www-st.inf.tu-dresden.de/ocl/ (project home page)          *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package tudresden.ocl20.tests.core;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jmi.model.Attribute;
import javax.jmi.model.CollectionType;
import javax.jmi.model.DirectionKindEnum;
import javax.jmi.model.EnumerationType;
import javax.jmi.model.ModelPackage;
import javax.jmi.model.MofClass;
import javax.jmi.model.MofPackage;
import javax.jmi.model.MultiplicityType;
import javax.jmi.model.Operation;
import javax.jmi.model.Parameter;
import javax.jmi.model.PrimitiveType;
import javax.jmi.model.ScopeKindEnum;
import javax.jmi.model.StructureField;
import javax.jmi.model.StructureType;
import javax.jmi.model.VisibilityKindEnum;
import javax.jmi.reflect.RefObject;
import javax.jmi.reflect.RefPackage;
import javax.jmi.xmi.XmiWriter;

import org.junit.Test;

import tudresden.ocl20.codegen.java.CodeGenerator;
import tudresden.ocl20.codegen.java.JmiCodeGenerator;
import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.ModelManager;
import tudresden.ocl20.core.OclModel;
import tudresden.ocl20.core.OclModelException;
import tudresden.ocl20.core.Repository;
import tudresden.ocl20.core.RepositoryException;
import tudresden.ocl20.core.RepositoryManager;
import tudresden.ocl20.core.WellFormednessException;

/**
 * This Test tests the MOF OCL MetaModel.
 * 
 * @author Stefan Ocke
 */
/*
 * Refined, documented and changed into a jUnit Test by Claas Wilke in February
 * 2008.
 */
public class TestMetamodel {

	java.net.URL metamodelxmi;
	java.net.URL modelxmi;

	ModelManager mmManager = ModelManager.getInstance();
	ModelPackage metamodel = null;
	RefPackage model = null;
	OclModel metamodelAsMof14Ocl;

	RefObject self;

	java.net.URL dir;

	@Test
	public void testMetaModel() {

		// TODO [Claas Wilke] This test method was reconstructed from a main
		// method. Thus, it does not contain any assertions.
		// Obviously a refactoring canidate.

		// Try to load the Meta Model.
		metamodelxmi = ClassLoader.getSystemResource("TstMetamodel/TestMM.xml");
		modelxmi = ClassLoader.getSystemResource("TstMetamodel/Test.xml");

		if (metamodelxmi == null || modelxmi == null)
			fail("Could not load Meta Model or Model. Test failed.");

		// Create the a meta model and model as XMI files.
		createModel();

		// Create an OCL Model based on the meta model created before.
		createOclExpression();

		// finally delete the model, and the meta model from the ModelManager.
		if (model != null)
			mmManager.deleteModel(model);
		if (metamodel != null)
			mmManager.deleteMetaModel(metamodel);
		if (metamodelAsMof14Ocl != null)
			metamodelAsMof14Ocl.close();
	}

	/**
	 * Creates a meta model and a meta model instance for the testMetaModel
	 * method.
	 */
	@SuppressWarnings("unchecked")
	private void createModel() {

		// Get the repository.
		Repository repository = RepositoryManager.getRepository();

		// Check if the meta model was already created.
		// If it was already created, delete it before recreation.
		try {
			metamodel = repository.getMetaModel("METAMODEL1");
			if (metamodel != null)
				mmManager.deleteMetaModel(metamodel);

			// Create a meta model.
			metamodel = (ModelPackage) repository.createMetaModel("METAMODEL1");
		} catch (RepositoryException e) {
			fail("An unexpected RepositoryException occured. Test failed. See StackTrace for details.");
		}

		// Create MOF package.
		MofPackage metamodelPackage = metamodel.getMofPackage()
				.createMofPackage();
		metamodelPackage.setName("mmpkg");

		// Create a MofClass for testing.
		MofClass testClass = metamodel.getMofClass().createMofClass(
				"TestClass", "", false, false, false,
				VisibilityKindEnum.PUBLIC_VIS, false);

		// Add the testClass to the meta model package.
		metamodelPackage.getContents().add(testClass);

		// Create the primitive tape 'Integer'.
		PrimitiveType integer = metamodel.getPrimitiveType()
				.createPrimitiveType("Integer", "", false, false, false,
						VisibilityKindEnum.PUBLIC_VIS);
		metamodelPackage.getContents().add(integer);

		// Create the primitive tape 'Long'.
		PrimitiveType longType = metamodel.getPrimitiveType()
				.createPrimitiveType("Long", "", false, false, false,
						VisibilityKindEnum.PUBLIC_VIS);
		metamodelPackage.getContents().add(longType);

		// Create the primitive tape 'Double'.
		PrimitiveType doubleType = metamodel.getPrimitiveType()
				.createPrimitiveType("Double", "", false, false, false,
						VisibilityKindEnum.PUBLIC_VIS);
		metamodelPackage.getContents().add(doubleType);

		// Create the primitive tape 'Float'.
		PrimitiveType floatType = metamodel.getPrimitiveType()
				.createPrimitiveType("Float", "", false, false, false,
						VisibilityKindEnum.PUBLIC_VIS);
		metamodelPackage.getContents().add(floatType);

		// Create the primitive tape 'Boolean'.
		PrimitiveType booleanType = metamodel.getPrimitiveType()
				.createPrimitiveType("Boolean", "", false, false, false,
						VisibilityKindEnum.PUBLIC_VIS);
		metamodelPackage.getContents().add(booleanType);

		// Create the primitive tape 'String'.
		PrimitiveType stringType = metamodel.getPrimitiveType()
				.createPrimitiveType("String", "", false, false, false,
						VisibilityKindEnum.PUBLIC_VIS);
		metamodelPackage.getContents().add(stringType);

		// Create a structured field of the type 'Integer'.
		StructureField sf1 = metamodel.getStructureField()
				.createStructureField("field1", "");
		sf1.setType(integer);

		// Create a structured field of the type 'Long'.
		StructureField sf2 = metamodel.getStructureField()
				.createStructureField("field2", "");
		sf2.setType(longType);

		// Create a structured type containing the structured fields created
		// before.
		StructureType st = metamodel.getStructureType().createStructureType(
				"struct", "", false, false, false,
				VisibilityKindEnum.PUBLIC_VIS);
		st.setContainer(metamodelPackage);
		st.getContents().add(sf1);
		st.getContents().add(sf2);

		// Create an Operation 'testOp' with a lot of Parameters declared below.
		Operation op = metamodel.getOperation().createOperation("testOp", "",
				ScopeKindEnum.INSTANCE_LEVEL, VisibilityKindEnum.PUBLIC_VIS,
				true);

		// Create a CollectionType 'blub'
		MultiplicityType cm = metamodel
				.createMultiplicityType(1, 1, true, true);
		CollectionType ct = metamodel.getCollectionType().createCollectionType(
				"blub", "blub", false, false, false,
				VisibilityKindEnum.PUBLIC_VIS, cm);
		ct.setType(integer);
		metamodelPackage.getContents().add(ct);

		// Create the Parameter 'x'
		MultiplicityType mult1 = metamodel.createMultiplicityType(1, -1, true,
				true);
		Parameter param1 = metamodel.getParameter().createParameter("x", "",
				DirectionKindEnum.INOUT_DIR, mult1);
		param1.setType(integer);
		op.getContents().add(param1);

		// Create the return parameter
		MultiplicityType mult = metamodel.createMultiplicityType(1, 1, false,
				false);
		Parameter param = metamodel.getParameter().createParameter("*return",
				"", DirectionKindEnum.RETURN_DIR, mult);
		param.setType(st);
		op.getContents().add(param);

		List labels = new ArrayList();
		labels.add("a");
		labels.add("b");
		labels.add("c");

		// Create an EnumerarionType 'ABC'.
		EnumerationType et = metamodel.getEnumerationType()
				.createEnumerationType("ABC", "", false, false, false,
						VisibilityKindEnum.PUBLIC_VIS, labels);
		et.setContainer(metamodelPackage);

		// Create a Parameter 'abc'.
		MultiplicityType mult3 = metamodel.createMultiplicityType(0, 1, true,
				true);
		Parameter param3 = metamodel.getParameter().createParameter("abc", "",
				DirectionKindEnum.IN_DIR, mult3);
		param3.setType(et);
		op.getContents().add(param3);

		// Create a Parameter 'l'.
		MultiplicityType mult4 = metamodel.createMultiplicityType(0, 1, true,
				true);
		Parameter param4 = metamodel.getParameter().createParameter("l", "",
				DirectionKindEnum.OUT_DIR, mult4);
		param4.setType(longType);
		op.getContents().add(param4);

		// Create a Parameter 'd'.
		MultiplicityType mult5 = metamodel.createMultiplicityType(0, 1, true,
				true);
		Parameter param5 = metamodel.getParameter().createParameter("d", "",
				DirectionKindEnum.IN_DIR, mult5);
		param5.setType(doubleType);
		op.getContents().add(param5);

		// Create a Parameter 'f'.
		MultiplicityType mult6 = metamodel.createMultiplicityType(0, 1, true,
				true);
		Parameter param6 = metamodel.getParameter().createParameter("f", "",
				DirectionKindEnum.INOUT_DIR, mult6);
		param6.setType(floatType);
		op.getContents().add(param6);

		// Create a Parameter 'b'.
		MultiplicityType mult7 = metamodel.createMultiplicityType(0, 1, true,
				true);
		Parameter param7 = metamodel.getParameter().createParameter("b", "",
				DirectionKindEnum.IN_DIR, mult7);
		param7.setType(booleanType);
		op.getContents().add(param7);

		// Create a Parameter 's'.
		MultiplicityType mult8 = metamodel.createMultiplicityType(0, 1, true,
				true);
		Parameter param8 = metamodel.getParameter().createParameter("s", "",
				DirectionKindEnum.IN_DIR, mult8);
		param8.setType(stringType);
		op.getContents().add(param8);

		// Create a Parameter 'structure'.
		MultiplicityType mult9 = metamodel.createMultiplicityType(0, 1, true,
				true);
		Parameter param9 = metamodel.getParameter().createParameter(
				"structure", "", DirectionKindEnum.IN_DIR, mult9);
		param9.setType(st);
		op.getContents().add(param9);

		testClass.getContents().add(op);
		// End of operation creation for Operation 'testOp'.

		// Create an Attribute 'y'.
		MultiplicityType am = metamodel.createMultiplicityType(1, 1, false,
				true);
		Attribute att = metamodel.getAttribute().createAttribute("y", "y",
				ScopeKindEnum.INSTANCE_LEVEL, VisibilityKindEnum.PUBLIC_VIS,
				am, true, false);
		att.setType(st);
		testClass.getContents().add(att);

		// Now instantiate that metamodel
		try {
			model = repository.createModel("MODEL1", metamodelPackage);

			// now instantiate the Multiplicity using the reflective API
			self = model.refClass("TestClass").refCreateInstance(null);

			// Write the created meta model and model in XMI files.
			XmiWriter xmiwriter = repository.createXMIWriter();
			xmiwriter.write(
					new java.io.FileOutputStream(metamodelxmi.getPath()),
					metamodel, "1.2");
			xmiwriter.write(new java.io.FileOutputStream(modelxmi.getPath()),
					model, "1.2");
		} catch (RepositoryException e) {
			fail("An unexpected RepositoryException occured. Test failed. See StackTrace for details.");
		} catch (IOException e) {
			fail("An unexpected IOException occured. Test failed. See StackTrace for details.");
		}

	}

	/**
	 * Creates an OCL Model based on the meta model created before.
	 */
	@SuppressWarnings("unchecked")
	private void createOclExpression() {

		try {
			// Create a new OCL Model based on the meta model created before.
			metamodelAsMof14Ocl = new OclModel(MetaModelConst.MOF14,
					metamodelxmi.toString());
		} catch (OclModelException e) {
			e.printStackTrace();
			fail("An unepxected OclModelException occured during testing. See stack trace for details.");
		}

		tudresden.ocl20.core.jmi.ocl.commonmodel.Package topPackage = metamodelAsMof14Ocl
				.getTopPackage();
		tudresden.ocl20.core.jmi.ocl.expressions.OclExpressionFactory factory = metamodelAsMof14Ocl
				.getOclExpressionFactory();
		tudresden.ocl20.core.TypeEvaluator typeEvl = metamodelAsMof14Ocl
				.getTypeEvaluator();

		List pathName;
		pathName = new ArrayList();
		pathName.add("mmpkg");
		pathName.add("TestClass");
		tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier context = topPackage
				.findClassifier(pathName);

		// declaration of variable "self"
		tudresden.ocl20.core.jmi.ocl.expressions.VariableDeclaration selfDecl = factory
				.createVariableDeclaration();
		selfDecl.setNameA("self");
		selfDecl.setType(context);

		// a reference to the self variable
		tudresden.ocl20.core.jmi.ocl.expressions.VariableExp self = factory
				.createVariableExp();
		self.setReferredVariable(selfDecl);

		// Two IntegerLiteralExpressions
		tudresden.ocl20.core.jmi.ocl.expressions.IntegerLiteralExp ile1a = factory
				.createIntegerLiteralExp();
		ile1a.setIntegerSymbol(10);
		tudresden.ocl20.core.jmi.ocl.expressions.IntegerLiteralExp ile1b = factory
				.createIntegerLiteralExp();
		ile1b.setIntegerSymbol(18);

		// A CollectionLiteralExp with its CollectionRange
		tudresden.ocl20.core.jmi.ocl.expressions.CollectionRange cr1 = factory
				.createCollectionRange();
		cr1.setFirst(ile1a);
		cr1.setLast(ile1b);
		tudresden.ocl20.core.jmi.ocl.expressions.CollectionLiteralExp cle1 = factory
				.createCollectionLiteralExp();
		cle1
				.setKind(tudresden.ocl20.core.jmi.ocl.expressions.CollectionKindEnum.SEQUENCE);
		cle1.getParts().add(cr1);

		pathName = new ArrayList();
		pathName.add("mmpkg");
		pathName.add("ABC");
		tudresden.ocl20.core.jmi.ocl.commonmodel.Enumeration en = (tudresden.ocl20.core.jmi.ocl.commonmodel.Enumeration) topPackage
				.findClassifier(pathName);
		Iterator it = en.getLiteralA().iterator();
		tudresden.ocl20.core.jmi.ocl.commonmodel.EnumerationLiteral el = null;
		// Search for the EnumerationLiteral which name is 'a'
		while (it.hasNext()) {
			tudresden.ocl20.core.jmi.ocl.commonmodel.EnumerationLiteral el1 = (tudresden.ocl20.core.jmi.ocl.commonmodel.EnumerationLiteral) it
					.next();
			if (el1.getNameA().equals("a")) {
				el = el1;
				break;
			}
		}
		tudresden.ocl20.core.jmi.ocl.expressions.EnumLiteralExp ele1 = factory
				.createEnumLiteralExp();
		ele1.setReferredEnumLiteral(el);

		// Two IntegerLiteralExpressions
		tudresden.ocl20.core.jmi.ocl.expressions.IntegerLiteralExp ile2 = factory
				.createIntegerLiteralExp();
		ile2.setIntegerSymbol(20);

		tudresden.ocl20.core.jmi.ocl.expressions.IntegerLiteralExp ile3 = factory
				.createIntegerLiteralExp();
		ile3.setIntegerSymbol(25);

		// A RealLiteralExpression
		tudresden.ocl20.core.jmi.ocl.expressions.RealLiteralExp rle1 = factory
				.createRealLiteralExp();
		rle1.setRealSymbol(1.1415);

		// A BooleanLiteralExpression
		tudresden.ocl20.core.jmi.ocl.expressions.BooleanLiteralExp ble1 = factory
				.createBooleanLiteralExp();
		ble1.setBooleanSymbol(true);

		// A StringLiteralExpression
		tudresden.ocl20.core.jmi.ocl.expressions.StringLiteralExp sle1 = factory
				.createStringLiteralExp();
		sle1.setStringSymbol("dumdidum");

		// Two IntegerLiteralExpressions
		tudresden.ocl20.core.jmi.ocl.expressions.IntegerLiteralExp ile4 = factory
				.createIntegerLiteralExp();
		ile4.setIntegerSymbol(200);

		tudresden.ocl20.core.jmi.ocl.expressions.IntegerLiteralExp ile5 = factory
				.createIntegerLiteralExp();
		ile5.setIntegerSymbol(250);

		// Two VariableDeclarions 'field1' and 'field2'
		tudresden.ocl20.core.jmi.ocl.expressions.VariableDeclaration vd1 = factory
				.createVariableDeclaration();
		vd1.setNameA("field1");
		vd1.setInitExpression(ile4);

		tudresden.ocl20.core.jmi.ocl.expressions.VariableDeclaration vd2 = factory
				.createVariableDeclaration();
		vd2.setNameA("field2");
		vd2.setInitExpression(ile5);

		// A TupleLiteralExpression
		tudresden.ocl20.core.jmi.ocl.expressions.TupleLiteralExp tle1 = factory
				.createTupleLiteralExp();
		tle1.getTuplePart().add(vd1);
		tle1.getTuplePart().add(vd2);

		// Get the repository.
		Repository repository = RepositoryManager.getRepository();

		try {
			// Starting Transaction in write mode.
			repository.beginTrans(true);

			// An OperationCallExp
			tudresden.ocl20.core.jmi.ocl.expressions.OperationCallExp testOpExp = factory
					.createOperationCallExp();
			List paramTypes = new ArrayList();
			paramTypes.add(typeEvl.getType(cle1));
			paramTypes.add(typeEvl.getType(ele1));
			paramTypes.add(typeEvl.getType(ile3));
			paramTypes.add(typeEvl.getType(rle1));
			paramTypes.add(typeEvl.getType(ble1));
			paramTypes.add(typeEvl.getType(sle1));
			paramTypes.add(typeEvl.getType(tle1));

			// An Operation 'testOp'
			tudresden.ocl20.core.jmi.ocl.commonmodel.Operation testOp = context
					.lookupOperation("testOp", paramTypes);
			testOpExp.setReferredOperation(testOp);
			testOpExp.getArguments().add(cle1);
			testOpExp.getArguments().add(ele1);
			testOpExp.getArguments().add(ile3);
			testOpExp.getArguments().add(rle1);
			testOpExp.getArguments().add(ble1);
			testOpExp.getArguments().add(sle1);
			testOpExp.getArguments().add(tle1);
			testOpExp.setSource(self);

			// An AttributeCallExpression
			tudresden.ocl20.core.jmi.ocl.expressions.AttributeCallExp attrExp = factory
					.createAttributeCallExp();
			attrExp.setSource(testOpExp);

			// An Attribute 'f'
			tudresden.ocl20.core.jmi.ocl.commonmodel.Attribute field2 = typeEvl
					.getType(testOpExp).lookupAttribute("f");
			attrExp.setReferredAttribute(field2);

			typeEvl.getType(attrExp);

			// Create a Code Generator and generate the Code.
			CodeGenerator cg = new JmiCodeGenerator(metamodelAsMof14Ocl);
			cg.getCode(attrExp);

			// Commiting Transaction to the repository.
			repository.endTrans(false);

		} catch (WellFormednessException e) {
			e.printStackTrace();
			repository.endTrans(true);
			fail("An unepxected WellFormednessException occured during testing. See stack trace for details.");
		}
	}

}
