/*
 * EngineTest.java
 * 
 * Created on 07.02.2006
 * Copyright (c) 2006 
 * Contact:
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
package tudresden.ocl20.pivot.tools.transformation.pivot2sql.test.tests;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclSettings;
import tudresden.ocl20.pivot.tools.codegen.declarativ.mapping.IMappedModel;
import tudresden.ocl20.pivot.tools.transformation.ITransformation;
import tudresden.ocl20.pivot.tools.transformation.TransformationFactory;
import tudresden.ocl20.pivot.tools.transformation.exception.InvalidModelException;
import tudresden.ocl20.pivot.tools.transformation.exception.TransformationException;
import tudresden.ocl20.pivot.tools.transformation.pivot2sql.test.tests.util.ModelChecker;
import tudresden.ocl20.pivot.tools.transformation.pivot2sql.test.tests.util.TestPerformer;
import tudresden.ocl20.pivot.tools.transformation.pivot2sql.test.tests.util.TransformationTest;

/**
 * <p>
 * This test class tests the MappedModel & Pivot2MappedModel.
 * </p>
 * 
 * @see tudresden.ocl20.pivot.tools.transformation.MappedModelImpl; ;
 */
public class Pivot2MappedModelTest extends TransformationTest {

	private List<String> classes = new ArrayList<String>();

	private Map<String, List<String>> class2assEnds =
			new HashMap<String, List<String>>();

	private Map<String, List<String>> class2attributes =
			new HashMap<String, List<String>>();

	@Before
	public void setUp() {

		classes.clear();
		class2assEnds.clear();
		class2attributes.clear();
	}

	/**
	 * <p>
	 * Tests a mapped Model.
	 * </p>
	 * 
	 * <p>
	 * Checks if a class mapped correctly.
	 * </p>
	 */
	public void testClass() {

		IMappedModel mm = null;
		try {
			mm = generateMappedModel(TEST_CLASS);
		} catch (Exception e) {
			fail("The mapped model can't generate");
		}

		classes.add("Person");

		ModelChecker.checkMappedModel(mm, classes, class2attributes, class2assEnds);

	}

	/**
	 * <p>
	 * Tests a mapped Model.
	 * </p>
	 * 
	 * <p>
	 * Checks if a property mapped correctly.
	 * </p>
	 */
	public void testProperty() {

		List<String> attributes = new ArrayList<String>();
		IMappedModel mm = null;
		try {
			mm = generateMappedModel(TEST_PROPERTY);
		} catch (TransformationException e) {
			fail(e.getMessage());
		} catch (Exception e) {
			fail("The mapped model can't generate");
		}

		classes.add("Person");
		attributes.add("firstName");
		attributes.add("lastName");
		attributes.add("birthDate");
		attributes.add("age");
		attributes.add("isMarried");
		attributes.add("salaries");
		class2attributes.put(classes.get(0), attributes);
		ModelChecker.checkMappedModel(mm, classes, class2attributes, class2assEnds);

	}

	/**
	 * <p>
	 * Tests a mapped Model.
	 * </p>
	 * 
	 * <p>
	 * Checks if a inheritance mapped correctly.
	 * </p>
	 */
	public void testInheritance() {

		List<String> attributes1 = new ArrayList<String>();
		List<String> attributes2 = new ArrayList<String>();
		IMappedModel mm = null;
		try {
			mm = generateMappedModel(TEST_INHERITANCE);
		} catch (Exception e) {
			fail("The mapped model can't generate");
		}

		classes.add("Person");
		attributes1.add("lastName");
		class2attributes.put(classes.get(0), attributes1);

		classes.add("Student");
		attributes2.addAll(attributes1);
		attributes2.add("matNr");
		class2attributes.put(classes.get(1), attributes2);
		ModelChecker.checkMappedModel(mm, classes, class2attributes, class2assEnds);

	}

	/**
	 * <p>
	 * Tests a mapped Model.
	 * </p>
	 * 
	 * <p>
	 * Checks if a navigable 1to1 relation mapped correctly.
	 * </p>
	 */
	public void testRelation1to1() {

		List<String> assEnds1 = new ArrayList<String>();
		IMappedModel mm = null;
		try {
			mm = generateMappedModel(TEST_RELATION_1TO1);
		} catch (Exception e) {
			fail("The mapped model can't generate");
		}

		classes.add("Person");
		assEnds1.add("currentPaper");
		class2assEnds.put(classes.get(0), assEnds1);
		classes.add("Paper");
		ModelChecker.checkMappedModel(mm, classes, class2attributes, class2assEnds);
	}

	/**
	 * <p>
	 * Tests a mapped Model.
	 * </p>
	 * 
	 * <p>
	 * Checks if a 1toN relation mapped correctly.
	 * </p>
	 */
	public void testRelation1toN() {

		List<String> assEnds1 = new ArrayList<String>();
		IMappedModel mm = null;
		try {
			mm = generateMappedModel(TEST_RELATION_1TON);
		} catch (Exception e) {
			fail("The mapped model can't generate");
		}

		classes.add("Facility");
		assEnds1.add("subFacility");
		assEnds1.add("superFacility");
		class2assEnds.put(classes.get(0), assEnds1);
		ModelChecker.checkMappedModel(mm, classes, class2attributes, class2assEnds);
	}

	/**
	 * <p>
	 * Tests a mapped Model.
	 * </p>
	 * 
	 * <p>
	 * Checks if a Nto1 relation mapped correctly.
	 * </p>
	 */
	public void testRelationNto1() {

		List<String> assEnds1 = new ArrayList<String>();
		IMappedModel mm = null;
		try {
			mm = generateMappedModel(TEST_RELATION_NTO1);
		} catch (Exception e) {
			fail("The mapped model can't generate");
		}

		classes.add("Facility");
		assEnds1.add("subFacility");
		assEnds1.add("superFacility");
		class2assEnds.put(classes.get(0), assEnds1);
		ModelChecker.checkMappedModel(mm, classes, class2attributes, class2assEnds);
	}

	/**
	 * <p>
	 * Tests a mapped Model.
	 * </p>
	 * 
	 * <p>
	 * Checks if a MtoN relation mapped correctly.
	 * </p>
	 */
	public void testRelationMtoN() {

		List<String> assEnds1 = new ArrayList<String>();
		List<String> assEnds2 = new ArrayList<String>();
		IMappedModel mm = null;
		try {
			mm = generateMappedModel(TEST_RELATION_MTON);
		} catch (Exception e) {
			fail("The mapped model can't generate");
		}

		classes.add("Person");
		assEnds1.add("papers");
		class2assEnds.put(classes.get(0), assEnds1);
		classes.add("Paper");
		assEnds2.add("author");
		class2assEnds.put(classes.get(1), assEnds2);
		ModelChecker.checkMappedModel(mm, classes, class2attributes, class2assEnds);
	}

	/**
	 * <p>
	 * Tests a mapped Model.
	 * </p>
	 * 
	 * <p>
	 * Checks if a complex university example mapped correctly.
	 * </p>
	 */
	public void testComplexUniversity() {

		List<String> attributes = new ArrayList<String>();
		List<String> attributes2 = new ArrayList<String>();
		List<String> assEnds = new ArrayList<String>();
		IMappedModel mm = null;
		try {
			mm = generateMappedModel(TEST_COMPLEX);
		} catch (Exception e) {
			fail("The mapped model can't generate");
		}

		// Create Person:
		classes.add("Person");
		attributes.add("firstName");
		attributes.add("lastName");
		attributes.add("birthDate");
		attributes.add("age");
		attributes.add("isMarried");
		attributes.add("salaries");
		assEnds.add("supervised");
		assEnds.add("supervisor");
		assEnds.add("papers");
		assEnds.add("currentPaper");
		assEnds.add("grade");
		assEnds.add("owner");
		assEnds.add("theFacility");
		class2attributes.put(classes.get(0), attributes);
		class2assEnds.put(classes.get(0), assEnds);

		// Create Student
		classes.add("Student");
		attributes2.addAll(attributes);
		attributes2.add("matNr");
		attributes2.add("matDate");
		class2attributes.put(classes.get(1), attributes2);
		class2assEnds.put(classes.get(1), assEnds);

		// Create Employee
		attributes2 = new ArrayList<String>();
		classes.add("Employee");
		attributes2.addAll(attributes);
		attributes2.add("soSecNr");
		attributes2.add("taxClass");
		attributes2.add("wage");
		class2attributes.put(classes.get(2), attributes2);
		class2assEnds.put(classes.get(2), assEnds);

		// Create PhDStudent
		attributes2 = new ArrayList<String>();
		classes.add("PhDStudent");
		attributes2.addAll(attributes);
		attributes2.add("soSecNr");
		attributes2.add("taxClass");
		attributes2.add("wage");
		attributes2.add("dissSubject");
		class2attributes.put(classes.get(3), attributes2);
		class2assEnds.put(classes.get(3), assEnds);

		attributes = new ArrayList<String>();
		attributes2 = new ArrayList<String>();
		assEnds = new ArrayList<String>();

		// Create Grade:
		classes.add("Grade");
		attributes.add("name");
		attributes.add("value");
		class2attributes.put(classes.get(4), attributes);
		class2assEnds.put(classes.get(4), assEnds);

		attributes = new ArrayList<String>();
		assEnds = new ArrayList<String>();

		// Create Paper
		classes.add("Paper");
		attributes.add("title");
		attributes.add("edition");
		attributes.add("purpose");
		attributes.add("category");
		attributes.add("inProgress");
		assEnds.add("author");
		class2attributes.put(classes.get(5), attributes);
		class2assEnds.put(classes.get(5), assEnds);

		attributes = new ArrayList<String>();
		assEnds = new ArrayList<String>();

		// Create Facility
		classes.add("Facility");
		attributes.add("name");
		assEnds.add("subFacility");
		assEnds.add("superFacility");
		assEnds.add("member");
		assEnds.add("headOfFacility");
		class2attributes.put(classes.get(6), attributes);
		class2assEnds.put(classes.get(6), assEnds);

		// Create Faculty
		classes.add("Faculty");
		class2attributes.put(classes.get(7), attributes);
		class2assEnds.put(classes.get(7), assEnds);

		// Create Chair
		classes.add("Chair");
		class2attributes.put(classes.get(8), attributes);
		class2assEnds.put(classes.get(8), assEnds);

		// Create Institute
		classes.add("Institute");
		class2attributes.put(classes.get(9), attributes);
		class2assEnds.put(classes.get(9), assEnds);

		ModelChecker.checkMappedModel(mm, classes, class2attributes, class2assEnds);
	}

	private IMappedModel generateMappedModel(File file)
			throws IllegalArgumentException, ModelAccessException,
			TransformationException, InvalidModelException {

		IModel model = TestPerformer.addUMLModel(file);
		ITransformation<Namespace, IOcl2DeclSettings, IMappedModel> p2mmi =
				TransformationFactory.getInstance().getTransformation(
						"Pivot2MappedModelImpl", Namespace.class, IMappedModel.class,
						IOcl2DeclSettings.class, "pivot", "mappedmodel");
		p2mmi.setParameterIN(model.getRootNamespace());
		p2mmi.setSettings(TestPerformer.getSettings());
		p2mmi.invoke();
		TestPerformer.removeUMLModel(model);
		return p2mmi.getResult();
	}

}
