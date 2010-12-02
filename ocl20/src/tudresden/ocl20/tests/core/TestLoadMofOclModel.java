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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.jmi.reflect.RefClass;
import javax.jmi.reflect.RefObject;
import javax.jmi.reflect.RefPackage;

import org.junit.Before;
import org.junit.Test;

import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.OclModel;
import tudresden.ocl20.core.OclModelException;
import tudresden.ocl20.core.WellFormednessException;
import tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier;
import tudresden.ocl20.core.jmi.ocl.commonmodel.Enumeration;
import tudresden.ocl20.core.jmi.ocl.commonmodel.EnumerationLiteral;
import tudresden.ocl20.core.jmi.ocl.commonmodel.Operation;
import tudresden.ocl20.core.jmi.ocl.expressions.BooleanLiteralExp;
import tudresden.ocl20.core.jmi.ocl.expressions.CollectionItem;
import tudresden.ocl20.core.jmi.ocl.expressions.CollectionLiteralExp;
import tudresden.ocl20.core.jmi.ocl.expressions.CollectionRange;
import tudresden.ocl20.core.jmi.ocl.expressions.EnumLiteralExp;
import tudresden.ocl20.core.jmi.ocl.expressions.IfExp;
import tudresden.ocl20.core.jmi.ocl.expressions.IntegerLiteralExp;
import tudresden.ocl20.core.jmi.ocl.expressions.LetExp;
import tudresden.ocl20.core.jmi.ocl.expressions.OclExpressionFactory;
import tudresden.ocl20.core.jmi.ocl.expressions.OperationCallExp;
import tudresden.ocl20.core.jmi.ocl.expressions.VariableDeclaration;
import tudresden.ocl20.core.jmi.ocl.expressions.VariableExp;
import tudresden.ocl20.core.jmi.ocl.types.OclLibrary;
import tudresden.ocl20.core.jmi.ocl.types.OclMessageType;

/**
 * A simple test case that loads a metamodel as instance of MOF14OCL an creates
 * some OCL Expressions within it.
 * 
 * Note, that the metamodel has to be MOF1.4 compliant!
 * 
 * A feature like transparently converting MOF1.3 to MOF1.4 is only available
 * with the "native" MOF (without OCL) of the NetBeans Repository
 * 
 * @author Stefan Ocke.
 */
/*
 * Refined and changed into a jUnit Test by Claas Wilke in February 2008.
 */
public class TestLoadMofOclModel {

	// Path defines the Folder in the resources, where the OCLMetamodel is
	// located.
	// TODO [Claas Wilke] must be MetaModelsWithOcl/, but the Ant Script which creates
	// the resources does not use
	// this path.
	private final static String METAMODELSWITHOCLDIR = "";

	private String modelXmi;
	private OclModel model = null;

	/**
	 * Set up the environment for the test cases.
	 */
	@Before
	/*
	 * java.net.URLDecoder(String) is deprecated, but the new method
	 * java.net.URLDecoder(String, String) cannot be initialized without
	 * knowledge about its second parameter. The default encryption is not
	 * public, thus it cannot be used here.
	 */
	@SuppressWarnings("deprecation")
	public void setUp() {
		// load MOF14OCL as "instance of itself"...
		modelXmi = (ClassLoader.getSystemClassLoader()
				.getResource(METAMODELSWITHOCLDIR
						+ "MOF14_plus_OCLMetamodel.xml")).toString();
	}

	/**
	 * Runs the test that loads a metamodel as instance of MOF14OCL an creates
	 * some OCL Expressions within it.
	 * 
	 * Note, that the metamodel has to be MOF1.4 compliant!
	 * 
	 * A feature like transparently converting MOF1.3 to MOF1.4 is only
	 * available with the "native" MOF (without OCL) of the NetBeans Repository
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testLoadingOfMofOclModel() {

		// TODO [Claas Wilke] This test method was reconstructed from a main
		// method. Thus, it does not contain that many assertions.
		// Obviously a refactoring canidate.

		// Try to load the OCL Model
		try {
			model = new OclModel(MetaModelConst.MOF14, modelXmi);
		} catch (OclModelException e) {
			fail("An OclModelException which was not expected was thrown.");
		}

		// Start Transaction
		model.beginTrans(true);

		// Get the top Package of the OCL Model
		tudresden.ocl20.core.jmi.ocl.commonmodel.Package topPackage = model
				.getTopPackage();

		// Get OclExpressionFactory, the TypeEvaluator and the OclLibrary.
		OclExpressionFactory factory = model.getOclExpressionFactory();
		tudresden.ocl20.core.TypeEvaluator typeEvl = model.getTypeEvaluator();
		OclLibrary oclLib = model.getOclLibrary();

		// Try to get the Classifier for the pathname 'Model::Class'
		List pathName = new ArrayList();
		pathName = new ArrayList();
		pathName.add("Model");
		pathName.add("Class");
		Classifier c = topPackage.findClassifier(pathName);
		// The path name should be 'Model::Claas'
		assertEquals(c.getPathNameA(), "Model::Class");

		List paramTypes = new ArrayList();
		paramTypes.add(c);
		Operation allInstOp = c.lookupOperation("allInstances", null);
		OperationCallExp oce1 = factory.createOperationCallExp();
		oce1.setReferredOperation(allInstOp);
		// its a classifier level operation ... we have to set the source type
		oce1.setSrcType(c);

		try {
			Classifier type;
			type = typeEvl.getType(oce1);
			// The name of the attribute nameA should be 'Set(Class)'
			assertEquals(type.getNameA(), "Set(Class)");

			BooleanLiteralExp ble = factory.createBooleanLiteralExp();
			ble.setNameA("true");
			ble.setBooleanSymbol(true);
			// The name of the attribute nameA should be 'Boolean'
			assertEquals(typeEvl.getType(ble).getNameA(), "Boolean");

			// Evaluation
			RefPackage outerMostPckg = ble.refOutermostPackage();
			RefPackage oclPackage = outerMostPckg.refPackage("OCL");
			RefPackage expressions = oclPackage.refPackage("Expressions");

			// Iterate through the Boolean Literal Expression
			RefClass blec = expressions.refClass("BooleanLiteralExp");
			Collection bles = blec.refAllOfType();
			Iterator bleit = bles.iterator();

			boolean result = true;
			while (bleit.hasNext()) {
				RefObject ro = (RefObject) bleit.next();

				RefObject bletype = (RefObject) ro.refGetValue("type");
				Object name = bletype.refGetValue("nameA");
				// FIXME [Claas Wilke] Why is this useful?
				result = result & name.equals("boolean");
			}

			// Check the Collection names
			pathName.add("OCL");
			pathName.add("Expressions");
			pathName.add("CollectionKind");
			Enumeration en = (Enumeration) topPackage.findClassifier(pathName);
			Iterator it = en.getLiteralA().iterator();
			EnumerationLiteral vkpublic = null;
			EnumerationLiteral el = null;

			// The first Literal must have the name "Collection"
			el = (EnumerationLiteral) it.next();
			assertEquals(el.getNameA(), "Collection");
			// Save the Collection in vkpublic.
			vkpublic = el;

			// The next Literal must have the name "Bag"
			el = (EnumerationLiteral) it.next();
			assertEquals(el.getNameA(), "Bag");

			// The next Literal must have the name "Set"
			el = (EnumerationLiteral) it.next();
			assertEquals(el.getNameA(), "Set");

			// The next Literal must have the name "Sequence"
			el = (EnumerationLiteral) it.next();
			assertEquals(el.getNameA(), "Sequence");

			// There must be no more elements.
			assertFalse(it.hasNext());

			// The type of the EnumLiteralExp ele must be "CollectionKind"
			EnumLiteralExp ele = factory.createEnumLiteralExp();
			ele.setReferredEnumLiteral(vkpublic);
			type = typeEvl.getType(ele);
			assertEquals(type.getNameA(), "CollectionKind");

			List conformsToParams = new ArrayList();
			conformsToParams.add(c);
			Operation conformsToOperation = c.lookupOperation("conformsTo",
					conformsToParams);

			// conformsTo should be no ocl lib operation
			assertFalse(oclLib.contains(conformsToOperation));

			IntegerLiteralExp ile1 = factory.createIntegerLiteralExp();
			ile1.setIntegerSymbol(1);

			IntegerLiteralExp ile2 = factory.createIntegerLiteralExp();
			ile2.setIntegerSymbol(2);

			IntegerLiteralExp ile3 = factory.createIntegerLiteralExp();
			ile3.setIntegerSymbol(5);

			IntegerLiteralExp ile4 = factory.createIntegerLiteralExp();
			ile4.setIntegerSymbol(8);

			CollectionItem ci1 = factory.createCollectionItem();
			ci1.setItem(ile1);
			CollectionItem ci2 = factory.createCollectionItem();
			ci2.setItem(ile2);

			CollectionRange cr = factory.createCollectionRange();
			cr.setFirst(ile3);
			cr.setLast(ile4);

			CollectionLiteralExp cle = factory.createCollectionLiteralExp();
			cle
					.setKind(tudresden.ocl20.core.jmi.ocl.expressions.CollectionKindEnum.SEQUENCE);
			cle.getParts().add(ci1);
			cle.getParts().add(ci2);
			cle.getParts().add(cr);

			// The type should be "Sequence(Integer)"
			type = typeEvl.getType(cle);
			assertEquals(type.getNameA(), "Sequence(Integer)");

			List parameters = new ArrayList();
			parameters.add(oce1.getType());
			Operation op = cle.getType().lookupOperation("product", parameters);

			VariableDeclaration vd = factory.createVariableDeclaration();
			vd.setInitExpression(ble);

			VariableExp ve = factory.createVariableExp();
			ve.setReferredVariable(vd);

			IfExp ifExp = factory.createIfExp();
			ifExp.setCondition(ve);
			ifExp.setThenExpression(cle);
			ifExp.setElseExpression(oce1);

			LetExp letExp = factory.createLetExp();
			letExp.setIn(ifExp);
			letExp.setVariable(vd);
			typeEvl.getType(letExp);

			OclMessageType omt = oclLib.makeOclMessageType(op);
			System.out.println();
			assertTrue(omt.commonSuperType(c) == null);

			// Commit transaction
			model.endTrans(false);

		} catch (WellFormednessException e) {
			// Rollback transaction
			model.endTrans(true);
			fail("An WellFormednessException which was not expected was thrown.");
		}
	}

}
