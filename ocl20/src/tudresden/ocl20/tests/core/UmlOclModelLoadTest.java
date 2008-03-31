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
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.OclModel;
import tudresden.ocl20.core.OclModelException;
import tudresden.ocl20.core.WellFormednessException;
import tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier;
import tudresden.ocl20.core.jmi.ocl.commonmodel.Operation;
import tudresden.ocl20.core.jmi.ocl.expressions.CollectionItem;
import tudresden.ocl20.core.jmi.ocl.expressions.CollectionLiteralExp;
import tudresden.ocl20.core.jmi.ocl.expressions.IntegerLiteralExp;
import tudresden.ocl20.core.jmi.ocl.expressions.OclExpressionFactory;
import tudresden.ocl20.core.jmi.ocl.expressions.OperationCallExp;

/**
 * A simple test case that loads a metamodel as instance of UML15OCL an creates
 * some OCL Expressions within it.
 * 
 * @author Stefan Ocke.
 */
/*
 * Refined and changed into a jUnit Test by Claas Wilke in February 2008.
 */
public class UmlOclModelLoadTest {

	@SuppressWarnings("unchecked")
	@Test
	// TODO [Claas Wilke] This test method was reconstructed from a main
	// method. Thus, it does not contain that many assertions.
	// Obviously a refactoring canidate.
	public void testUmlAndOclModelLoading() {

		// Get UML15OCL model path.
		String modelXmi = (ClassLoader.getSystemClassLoader()
				.getResource("UMLSamples/poseidonExport.xml")).toString();
		OclModel model = null;

		try {
			model = new OclModel(MetaModelConst.UML15, modelXmi);
		} catch (OclModelException e) {
			fail("An OclModelException which was not expected was thrown.");
		}

		// Starts an Transaction.
		model.beginTrans(true);

		// Get Top Package of the OCL Model, OCL Expression Factory and Type
		// Evaluator.
		tudresden.ocl20.core.jmi.ocl.commonmodel.Package topPackage = model
				.getTopPackage();
		OclExpressionFactory factory = model.getOclExpressionFactory();
		tudresden.ocl20.core.TypeEvaluator typeEvl = model.getTypeEvaluator();

		Classifier type;

		// Try to get 'Class_1' from the loaded model and get its type.
		List pathName = new ArrayList();
		pathName.add("Class_1");
		Classifier c = topPackage.findClassifier(pathName);
		Operation allInstOp = c.lookupOperation("allInstances", null);
		OperationCallExp oce1 = factory.createOperationCallExp();
		oce1.setReferredOperation(allInstOp);
		oce1.setSrcType(c);

		// The type should be 'Set(Class_1)'.
		try {
			type = typeEvl.getType(oce1);
			assertEquals(type.getNameA(), "Set(Class_1)");
		} catch (WellFormednessException e) {
			// Roll back Transaction and close model.
			model.endTrans(false);
			model.close();
			fail("An WellFormednessException which was not expected was thrown.");
		}

		// Create two IntegerLiteralExpressions
		IntegerLiteralExp ile1 = factory.createIntegerLiteralExp();
		ile1.setIntegerSymbol(1);
		IntegerLiteralExp ile2 = factory.createIntegerLiteralExp();
		ile1.setIntegerSymbol(2);

		// Create two CollectionItems using the two IntegerLiteralExpression..
		CollectionItem ci1 = factory.createCollectionItem();
		ci1.setItem(ile1);
		CollectionItem ci2 = factory.createCollectionItem();
		ci2.setItem(ile2);

		// Create an CollectionLiteralExpression (Sequence) and add the two
		// CollectionItems.
		CollectionLiteralExp cle = factory.createCollectionLiteralExp();
		cle
				.setKind(tudresden.ocl20.core.jmi.ocl.expressions.CollectionKindEnum.SEQUENCE);
		cle.getParts().add(ci1);
		cle.getParts().add(ci2);

		// The Type of the Collection should be 'Sequence(Integer)'.
		try {
			type = typeEvl.getType(cle);
			assertEquals(type.getNameA(), "Sequence(Integer)");
		} catch (WellFormednessException e) {
			// Roll back Transaction and close model.
			model.endTrans(false);
			model.close();
			fail("An WellFormednessException which was not expected was thrown.");
		}

		// Get the Operation 'product', create an OperationCallExpression and
		// check its type.
		List parameters = new ArrayList();
		parameters.add(oce1.getType());
		Operation op = cle.getType().lookupOperation("product", parameters);
		if (op != null) {
			OperationCallExp oce = factory.createOperationCallExp();
			oce.getArguments().add(oce1);
			oce.setReferredOperation(op);
			oce.setSource(cle);
			// The Type of the OperationCallExpression should be
			// 'Set(Tuple(first:Integer,second:Class_1))'.
			try {
				type = typeEvl.getType(oce);
				assertEquals(type.getNameA(),
						"Set(Tuple(first:Integer,second:Class_1))");
			} catch (WellFormednessException e) {
				// Roll back Transaction and close model.
				model.endTrans(false);
				model.close();
				fail("An WellFormednessException which was not expected was thrown.");
			}
		}

		// Roll back Transaction and close model.
		model.endTrans(false);
		model.close();
	}

}
