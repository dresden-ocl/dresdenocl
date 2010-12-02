/*
 * TestModelFacade.java
 * 
 * Copyright (c) 2005 Mirko Stölzel
 *
 * This file is part of the Dresden OCL2.0 Toolkit
 * created at Technische Universitaet Dresden (TUD), Germany.
 * Visit http://dresden-ocl.sourceforge.net/ for details.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA  02111-1307  USA
 *
 */
/*
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tudresden.ocl20.tests.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.ModelManager;
import tudresden.ocl20.core.ModelManagerException;
import tudresden.ocl20.core.jmi.uml15.core.Classifier;
import tudresden.ocl20.core.jmi.uml15.core.Operation;
import tudresden.ocl20.core.jmi.uml15.core.Parameter;
import tudresden.ocl20.core.jmi.uml15.impl.uml15ocl.types.OclLibraryHelper;
import tudresden.ocl20.core.jmi.uml15.modelmanagement.Model;
import tudresden.ocl20.core.jmi.uml15.uml15.Uml15Package;

/**
 * This class provides Tests to test the class ModelFacade.
 * 
 * @author Mirko Stölzel
 */
/*
 * Refactored, changed into a jUnit Test Case and documented by Claas Wilke in
 * March 2008.
 */
public class TestModelFacade {

//	// FIXME [Claas Wilke] This methods were called recursively. Shall they be reimplemented in a TestCase?
//	public static void main(String args[]) {
//
//		try {
//			TestModelFacade myIntegtrationTest = new TestModelFacade();
//			
//			ModelManager mm = ModelManager.getInstance();  
//			
//			mm.beginTrans(true);
//			Uml15Package model = (Uml15Package) mm.createOclModel(MetaModelConst.UML15, myIntegtrationTest.getUniqueName(mm));
//			Model topPackage = model.getModelManagement().getModel().createModel();
//			topPackage.setNameA("rootPackage");
//			mm.endTrans(false); 
////		    ModelFacade.addModelFacade(this.model.refMofId(), facade);
////			ModelFacade instance = ModelFacade.getInstance(this.model.refMofId());
////			instance.addRefObject(topPackage.refMofId(), root);		
//			
//			topPackage = ModelHelper.getInstance(model).getTopPackage();
//			mm.beginTrans(true);
//			myIntegtrationTest.testOwnedElements((Namespace) topPackage, "TopPackage", "");
//			mm.beginTrans(false);
//			
//		} catch (ModelManagerException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	protected void testOwnedElements(Namespace topPackage, String s,
//			String offset) {
//		// topPackage = ModelHelper.getInstance(this.model).getTopPackage();
//		
//
//		Collection list = topPackage.getOwnedElement();
//		System.out.println(list.size());
//		Iterator it = list.iterator();
//
//		while (it.hasNext()) {
//			Object o = it.next();
//			if (o instanceof Package) {
//				System.out.println("");
//				System.out.println(offset + "Paket :"
//						+ ((ModelElement) o).getName());
//				System.out.println("");
//				this.testOwnedElements((Namespace) o, "Paket "
//						+ ((ModelElement) o).getName(), offset + "##");
//			}
//			if (o instanceof Classifier && !(o instanceof Enumeration)
//					&& !(o instanceof Interface)) {
//				System.out.println(offset + "Classifier :"
//						+ ((Classifier) o).getPathNameA());
//				Collection parents = ((Classifier) o).getParents();
//				Iterator itParents = parents.iterator();
//				if (itParents.hasNext())
//					System.out.println(offset + "##Oberklassen :");
//				else
//					System.out.println(offset + "##Oberklassen : keine");
//				Object p = null;
//				while (itParents.hasNext()) {
//					p = itParents.next();
//					System.out.println(offset + "####"
//							+ ((Classifier) p).getNameA());
//				}
//				Collection children = ((ClassifierImpl) o).getChildren();
//				Iterator itChilds = children.iterator();
//				if (itChilds.hasNext())
//					System.out.println(offset + "##Unterklassen :");
//				else
//					System.out.println(offset + "##Unterklassen : keine");
//
//				while (itChilds.hasNext()) {
//					p = itChilds.next();
//					System.out.println(offset + "####"
//							+ ((Classifier) p).getNameA());
//				}
//
//				testClassifier((Classifier) o, "Classifier "
//						+ ((ModelElement) o).getName(), offset + "##");
//				this.testAllAssociations((Classifier) o, "Classifier "
//						+ ((ModelElement) o).getName(), offset + "##");
//				this.testOwnedElements((Namespace) o, "Classifier "
//						+ ((ModelElement) o).getName(), offset + "##");
//				System.out.println("");
//			}
//			if (o instanceof Enumeration && !(o instanceof Interface)) {
//				System.out.println(offset + "Aufzählung :");
//				Collection literal = ((Enumeration) o).getLiteralA();
//				Iterator itLiteral = literal.iterator();
//				EnumerationLiteral p = null;
//				if (itLiteral.hasNext()) {
//					p = (EnumerationLiteral) itLiteral.next();
//					System.out.println(offset + "##Literal:"
//							+ ((EnumerationLiteral) p).getNameA());
//				}
//				System.out.println("");
//			}
//			if (o instanceof Interface) {
//				System.out.println(offset + "Interface :"
//						+ ((Classifier) o).getPathNameA());
//				Collection parents = ((Classifier) o).getParents();
//				Iterator itParents = parents.iterator();
//				if (itParents.hasNext())
//					System.out
//							.println(offset + "##implementierte Interfaces :");
//				else
//					System.out.println(offset
//							+ "##implementierte Interfaces : keine");
//				Object p = null;
//				while (itParents.hasNext()) {
//					p = itParents.next();
//					System.out.println(offset + "####"
//							+ ((Classifier) p).getNameA());
//				}
//				Collection children = ((ClassifierImpl) o).getChildren();
//				Iterator itChilds = children.iterator();
//				if (itChilds.hasNext())
//					System.out.println(offset + "##implementiert von :");
//				else
//					System.out.println(offset + "##implementiert von : keine");
//
//				while (itChilds.hasNext()) {
//					p = itChilds.next();
//					System.out.println(offset + "####"
//							+ ((Classifier) p).getNameA());
//				}
//
//				testClassifier((Classifier) o, "Interface "
//						+ ((ModelElement) o).getName(), offset + "##");
//				this.testAllAssociations((Classifier) o, "Interface "
//						+ ((ModelElement) o).getName(), offset + "##");
//				this.testOwnedElements((Namespace) o, "Interface "
//						+ ((ModelElement) o).getName(), offset + "##");
//				System.out.println("");
//			}
//		}
//	}
//
//	/**
//	 * TestCase which checks all operations and their parameters belonging to
//	 * Classifier.
//	 */
//	@SuppressWarnings("unchecked")
//	protected void testClassifier(Classifier c, String s, String offset) {
//		Collection list = c.allAttributes();
//		Iterator it = list.iterator();
//		Object o = null;
//		System.out.println(offset + "Attribute:");
//		while (it.hasNext()) {
//			o = it.next();
//			System.out.println(offset + "####Attribut :"
//					+ ((ModelElement) o).getName());
//			System.out.println(offset + "######Typ :"
//					+ ((Attribute) o).getType().getName());
//			System.out.println(offset
//					+ "######Kardinalität :"
//					+ ((MultiplicityRange) ((Attribute) o).getMultiplicity()
//							.getRange().iterator().next()).getUpper());
//			// System.out.println(offset+"######Namensraum :" +
//			// ((Attribute)o).getNamespace().getName());
//			System.out.println(offset + "######Owner :"
//					+ ((Attribute) o).getOwner().getName());
//			System.out.println(offset + "######OrderingKind :"
//					+ ((Attribute) o).getOrdering());
//			System.out.println(offset + "######OwnerScope :"
//					+ ((Attribute) o).getOwnerScope());
//		}
//		list = c.allOperations();
//		it = list.iterator();
//		System.out.println(offset + "Methoden:");
//		while (it.hasNext()) {
//			o = it.next();
//			System.out.println(offset + "####Methode :"
//					+ ((ModelElement) o).getName());
//			System.out.println(offset + "######Owner :"
//					+ ((Operation) o).getOwner().getName());
//			System.out.println(offset + "######OwnerScope :"
//					+ ((Operation) o).getOwnerScope());
//
//			if (!((Operation) o).getParameter().isEmpty())
//				System.out.println(offset + "######Parameter:");
//
//			Parameter p = (Parameter) ((Operation) o).getReturnParameterA();
//			System.out.println(offset + "########Result :" + p.getName());
//			System.out.println(offset + "##########Name :" + p.getName());
//			System.out.println(offset + "##########Art  :" + p.getKind());
//			System.out.println(offset + "##########Typ  :"
//					+ p.getType().getName());
//			Iterator itParam = ((Operation) o).getInParametersA().iterator();
//			while (itParam.hasNext()) {
//				p = (Parameter) itParam.next();
//				System.out.println(offset + "########InParam :" + p.getName());
//				System.out.println(offset + "##########Name  :" + p.getName());
//				System.out.println(offset + "##########Art   :" + p.getKind());
//				System.out.println(offset + "##########Typ   :"
//						+ p.getType().getName());
//			}
//			itParam = ((Operation) o).getOutParametersA().iterator();
//			while (itParam.hasNext()) {
//				p = (Parameter) itParam.next();
//				System.out.println(offset + "########InParam :" + p.getName());
//				System.out.println(offset + "##########Name  :" + p.getName());
//				System.out.println(offset + "##########Art   :" + p.getKind());
//				System.out.println(offset + "##########Typ   :"
//						+ p.getType().getName());
//			}
//		}
//	}
//
//	/**
//	 * @param classifier
//	 * @param string
//	 */
//	@SuppressWarnings("unchecked")
//	protected void testAllAssociations(Classifier c, String s, String offset) {
//		ModelFacade instance = ModelFacade.getInstance(c.refOutermostPackage()
//				.refMofId());
//		Iterator it = instance.getAssociationEnds(c.refMofId()).iterator();
//
//		while (it.hasNext()) {
//			AssociationEnd e = (AssociationEnd) it.next();
//			Association a = e.getAssociation();
//			System.out.println(offset + "Assoziation :"
//					+ ((ModelElement) a).getName());
//			System.out.println(offset + "####Assoziationendenden:");
//			int i = 1;
//			Iterator oppositeEnds = a.getConnection().iterator();
//			while (oppositeEnds.hasNext()) {
//				AssociationEnd end2 = (AssociationEnd) oppositeEnds.next();
//				System.out.println(offset + "######Name :" + end2.getName());
//				System.out.println(offset
//						+ "######Multiplizität :"
//						+ ((MultiplicityRange) (end2).getMultiplicity()
//								.getRange().iterator().next()).getUpper());
//				i++;
//			}
//		}
//	}

	/**
	 * TestCase which checks all operations and their parameters belonging to
	 * OclAny.
	 */
	// Some collection operations in this test does not use generics. This
	// annotation prevents warnings in Eclipse.
	@SuppressWarnings("unchecked")
	@Test
	public void testOCLAny() {

		Uml15Package model;
		ModelManager mm = ModelManager.getInstance();

		try {
			// Begin a transaction with write access.
			mm.beginTrans(true);

			model = (Uml15Package) mm.createOclModel(MetaModelConst.UML15, this
					.getUniqueName(mm));
			Model topPackage = model.getModelManagement().getModel()
					.createModel();
			topPackage.setNameA("rootPackage");

			// Get all Operations of OCLAny and create an Iterator.
			List<Operation> ops = OclLibraryHelper.getInstance(model).getAny()
					.allOperations();
			Iterator<Operation> itOps = ops.iterator();

			Operation op = null;
			Parameter param = null;
			List<Parameter> params = null;
			Iterator<Parameter> itParams = null;

			// Check Method '='
			op = itOps.next();
			// Check Name
			assertEquals(op.getName(), "=");
			// Check Owner
			assertEquals(op.getOwner().getName(), "OclAny");
			// Check OwnerScope
			assertEquals(op.getOwnerScope().toString(), "sk_instance");

			// Check Parameters
			// Method must have Parameters
			assertFalse(op.getParameter().isEmpty());

			// Check return Parameter
			param = (Parameter) op.getReturnParameterA();
			// Check parameter name
			assertEquals(param.getName(), "result");
			// Check parameter kind
			assertEquals(param.getKind().toString(), "pdk_return");
			// Check parameter type
			assertEquals(param.getType().getName(), "Boolean");

			// Check in parameters
			params = op.getInParametersA();
			// Operation should have one in parameters
			assertTrue(params.size() == 1);
			itParams = params.iterator();

			// Check in parameter 1
			param = itParams.next();
			// Check parameter name
			assertEquals(param.getName(), "object");
			// Check parameter kind
			assertEquals(param.getKind().toString(), "pdk_in");
			// Check parameter type
			assertEquals(param.getType().getName(), "OclAny");

			// Check out parameters
			params = op.getOutParametersA();
			// Operation should have no out parameters
			assertTrue(params.size() == 0);

			// Check Method '<>'
			op = itOps.next();
			// Check Name
			assertEquals(op.getName(), "<>");
			// Check Owner
			assertEquals(op.getOwner().getName(), "OclAny");
			// Check OwnerScope
			assertEquals(op.getOwnerScope().toString(), "sk_instance");

			// Check Parameters
			// Method must have Parameters
			assertFalse(op.getParameter().isEmpty());

			// Check return Parameter
			param = (Parameter) op.getReturnParameterA();
			// Check parameter name
			assertEquals(param.getName(), "result");
			// Check parameter kind
			assertEquals(param.getKind().toString(), "pdk_return");
			// Check parameter type
			assertEquals(param.getType().getName(), "Boolean");

			// Check in parameters
			params = op.getInParametersA();
			// Operation should have one in parameters
			assertTrue(params.size() == 1);
			itParams = params.iterator();

			// Check in parameter 1
			param = itParams.next();
			// Check parameter name
			assertEquals(param.getName(), "object");
			// Check parameter kind
			assertEquals(param.getKind().toString(), "pdk_in");
			// Check parameter type
			assertEquals(param.getType().getName(), "OclAny");

			// Check out parameters
			params = op.getOutParametersA();
			// Operation should have no out parameters
			assertTrue(params.size() == 0);

			// Check Method 'oclIsNew'
			op = itOps.next();
			// Check Name
			assertEquals(op.getName(), "oclIsNew");
			// Check Owner
			assertEquals(op.getOwner().getName(), "OclAny");
			// Check OwnerScope
			assertEquals(op.getOwnerScope().toString(), "sk_instance");

			// Check Parameters
			// Method must have Parameters
			assertFalse(op.getParameter().isEmpty());

			// Check return Parameter
			param = (Parameter) op.getReturnParameterA();
			// Check parameter name
			assertEquals(param.getName(), "result");
			// Check parameter kind
			assertEquals(param.getKind().toString(), "pdk_return");
			// Check parameter type
			assertEquals(param.getType().getName(), "Boolean");

			// Check in parameters
			params = op.getInParametersA();
			// Operation should have no in parameters
			assertTrue(params.size() == 0);
			itParams = params.iterator();

			// Check out parameters
			params = op.getOutParametersA();
			// Operation should have no out parameters
			assertTrue(params.size() == 0);

			// Check Method 'oclIsUndefined'
			op = itOps.next();
			// Check Name
			assertEquals(op.getName(), "oclIsUndefined");
			// Check Owner
			assertEquals(op.getOwner().getName(), "OclAny");
			// Check OwnerScope
			assertEquals(op.getOwnerScope().toString(), "sk_instance");

			// Check Parameters
			// Method must have Parameters
			assertFalse(op.getParameter().isEmpty());

			// Check return Parameter
			param = (Parameter) op.getReturnParameterA();
			// Check parameter name
			assertEquals(param.getName(), "result");
			// Check parameter kind
			assertEquals(param.getKind().toString(), "pdk_return");
			// Check parameter type
			assertEquals(param.getType().getName(), "Boolean");

			// Check in parameters
			params = op.getInParametersA();
			// Operation should have no in parameters
			assertTrue(params.size() == 0);
			itParams = params.iterator();

			// Check out parameters
			params = op.getOutParametersA();
			// Operation should have no out parameters
			assertTrue(params.size() == 0);

			// Check Method 'asSet'
			op = itOps.next();
			// Check Name
			assertEquals(op.getName(), "asSet");
			// Check Owner
			assertEquals(op.getOwner().getName(), "OclAny");
			// Check OwnerScope
			assertEquals(op.getOwnerScope().toString(), "sk_instance");

			// Check Parameters
			// Method must have Parameters
			assertFalse(op.getParameter().isEmpty());

			// Check return Parameter
			param = (Parameter) op.getReturnParameterA();
			// Check parameter name
			assertEquals(param.getName(), "result");
			// Check parameter kind
			assertEquals(param.getKind().toString(), "pdk_return");
			// Check parameter type
			assertEquals(param.getType().getName(), "Set(OclAny)");

			// Check in parameters
			params = op.getInParametersA();
			// Operation should have no in parameters
			assertTrue(params.size() == 0);
			itParams = params.iterator();

			// Check out parameters
			params = op.getOutParametersA();
			// Operation should have no out parameters
			assertTrue(params.size() == 0);

			// Check Method 'atPre'
			op = itOps.next();
			// Check Name
			assertEquals(op.getName(), "atPre");
			// Check Owner
			assertEquals(op.getOwner().getName(), "OclAny");
			// Check OwnerScope
			assertEquals(op.getOwnerScope().toString(), "sk_instance");

			// Check Parameters
			// Method must have Parameters
			assertFalse(op.getParameter().isEmpty());

			// Check return Parameter
			param = (Parameter) op.getReturnParameterA();
			// Check parameter name
			assertEquals(param.getName(), "result");
			// Check parameter kind
			assertEquals(param.getKind().toString(), "pdk_return");
			// Check parameter type
			assertEquals(param.getType().getName(), "OclAny");

			// Check in parameters
			params = op.getInParametersA();
			// Operation should have no in parameters
			assertTrue(params.size() == 0);
			itParams = params.iterator();

			// Check out parameters
			params = op.getOutParametersA();
			// Operation should have no out parameters
			assertTrue(params.size() == 0);

			// Check Method 'oclIsInState'
			op = itOps.next();
			// Check Name
			assertEquals(op.getName(), "oclIsInState");
			// Check Owner
			assertEquals(op.getOwner().getName(), "OclAny");
			// Check OwnerScope
			assertEquals(op.getOwnerScope().toString(), "sk_instance");

			// Check Parameters
			// Method must have Parameters
			assertFalse(op.getParameter().isEmpty());

			// Check return Parameter
			param = (Parameter) op.getReturnParameterA();
			// Check parameter name
			assertEquals(param.getName(), "result");
			// Check parameter kind
			assertEquals(param.getKind().toString(), "pdk_return");
			// Check parameter type
			assertEquals(param.getType().getName(), "Boolean");

			// Check in parameters
			params = op.getInParametersA();
			// Operation should have one in parameters
			assertTrue(params.size() == 1);
			itParams = params.iterator();

			// Check in parameter 1
			param = itParams.next();
			// Check parameter name
			assertEquals(param.getName(), "statename");
			// Check parameter kind
			assertEquals(param.getKind().toString(), "pdk_in");
			// Check parameter type
			assertEquals(param.getType().getName(), "OclState");

			// Check out parameters
			params = op.getOutParametersA();
			// Operation should have no out parameters
			assertTrue(params.size() == 0);

			// Check Method 'allInstances'
			op = itOps.next();
			// Check Name
			assertEquals(op.getName(), "allInstances");
			// Check Owner
			assertEquals(op.getOwner().getName(), "OclAny");
			// Check OwnerScope
			assertEquals(op.getOwnerScope().toString(), "sk_classifier");

			// Check Parameters
			// Method must have Parameters
			assertFalse(op.getParameter().isEmpty());

			// Check return Parameter
			param = (Parameter) op.getReturnParameterA();
			// Check parameter name
			assertEquals(param.getName(), "result");
			// Check parameter kind
			assertEquals(param.getKind().toString(), "pdk_return");
			// Check parameter type
			assertEquals(param.getType().getName(), "Set(OclAny)");

			// Check in parameters
			params = op.getInParametersA();
			// Operation should have no in parameters
			assertTrue(params.size() == 0);
			itParams = params.iterator();

			// Check out parameters
			params = op.getOutParametersA();
			// Operation should have no out parameters
			assertTrue(params.size() == 0);

			// OclAny should'nt have more operations
			assertFalse(itOps.hasNext());

			// Commit the transaction.
			mm.endTrans(false);

		} catch (ModelManagerException e) {
			// Roll back the transaction.
			mm.endTrans(true);

			fail("An ModelManagerException which was not expected was thrown.");
		}
	}

	/**
	 * A test case which checks the parents of OCLVoid in the OclMetaModel.
	 */
	@Test
	public void testOCLVoid() {

		// The Model which shall be tested.
		Uml15Package model;
		// Get ModelManager
		ModelManager mm = ModelManager.getInstance();

		try {
			// Load the MetaModel which shall be tested into the repository.
			model = (Uml15Package) mm.createOclModel(MetaModelConst.UML15, this
					.getUniqueName(mm));

			// Get parents of void and create an Iterator.
			@SuppressWarnings("unchecked")
			List<Classifier> voidParents = OclLibraryHelper.getInstance(model)
					.getVoid().getParents();
			Iterator<Classifier> voidIterator = voidParents.iterator();
			Classifier aClassifier;

			// The first element should have the name 'Integer'.
			aClassifier = voidIterator.next();
			assertEquals(aClassifier.getName(), "Integer");

			// The second element should have the name 'Boolean'.
			aClassifier = voidIterator.next();
			assertEquals(aClassifier.getName(), "Boolean");

			// The third element should have the name 'String'.
			aClassifier = voidIterator.next();
			assertEquals(aClassifier.getName(), "String");
		} catch (ModelManagerException e) {
			fail("An ModelManagerException which was not expected was thrown.");
		}
	}

	/**
	 * Returns the name of a Model by its given ModelManager.
	 * 
	 * @param mm
	 *            The ModelManager which model's name should be returned.
	 * @return the name of a Model.
	 */
	public String getUniqueName(ModelManager mm) {
		try {
			@SuppressWarnings("unchecked")
			Collection col = mm.getAllModelNames();
			String name = "WorkingModel";
			int i = col.size() + 1;
			while (true) {
				name = "WorkingModel" + i;
				if (!col.contains(name))
					return name;
				i++;
			}
		} catch (ModelManagerException e) {
			e.printStackTrace();
			return "";
		}
	}

}
