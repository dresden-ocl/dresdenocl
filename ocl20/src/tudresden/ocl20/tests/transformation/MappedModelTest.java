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
package tudresden.ocl20.tests.transformation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.jmi.reflect.RefPackage;

import tudresden.ocl20.codegen.decl.mapping.Guide;
import tudresden.ocl20.codegen.decl.mapping.MappedClass;
import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.OclModel;
import tudresden.ocl20.core.OclModelException;
import tudresden.ocl20.core.Repository;
import tudresden.ocl20.core.RepositoryException;
import tudresden.ocl20.core.RepositoryManager;
import tudresden.ocl20.transformation.TransformationEngine;
import tudresden.ocl20.transformation.impl.Uml2MappedModelImpl;
import tudresden.ocl20.transformation.inspect.interfaces.MappedModelImpl;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * <p>This test class tests the MappedModel.</p>
 *
 *@see tudresden.ocl20.transformation.inspect.interfaces.MappedModelImpl;
 */
/*
 * JavaDoc added changed to jUnit by Claas Wilke in 2007
 */
public class MappedModelTest {
	
	// Specifies whether or not the GuidesSQL should be printed out on the Console
	private static boolean PRINT_GUIDES_SQL = false;

	// Specifies whether or not the GuidesSQL should be written to the file guides.sql
	private static boolean WRITE_GUIDES_SQL = false;
	
	// The MappedModel which should be checked during this test
	private MappedModelImpl mm;
	
	// Specifies whether the setUp method was successful or not.
	private boolean initialized = false;
	
	/**
	 * <p>Method to prepare the tests.</p>
	 * 
	 * <p>Loads in and out models for transformation, invokes it and
	 * saves the result in the variable <code>mm</code>
	 * (<code>MappedModelImpl</code>).</p>
	 */
	@Before public void setUp() {
		try {
			// Specifies whether the setUp method was successful or not.
			// True until an error will be caught.
			this.initialized = true;
			
			String model_in = "testmodel";
			String model_out = "generated";
				
			// Load UMLModel
			addUMLModel(model_in);
			
			// Get TransformationEngine and  set parameters of the models
			// which are used during transformation.
			TransformationEngine te = TransformationEngine.getInstance();
			te.setModel_inName(model_in);
			te.setModel_inType(MetaModelConst.UML15);
			te.setModel_outName(model_out);
			
			// Instanciate the Transformation and invoke it.
			te.loadTransformation(Uml2MappedModelImpl.class.getSimpleName());
			te.invoke();
			
			// Set mm to the last invoked transformation
			mm = (MappedModelImpl) te.getResult();
		} 
		// Catch Exceptions
		catch (UnsupportedEncodingException e) {
			// Set initialized = false, so the tests won't be executed.
			this.initialized = false;
			System.out.println("SetUp method failed. See StackTrace for Details.");
			e.printStackTrace();
		} 
		catch (RepositoryException e) {
			// Set initialized = false, so the tests won't be executed.
			this.initialized = false;
			System.out.println("SetUp method failed. See StackTrace for Details.");
			e.printStackTrace();
		} 
		catch (Exception e) {
			// Set initialized = false, so the tests won't be executed.
			this.initialized = false;
			System.out.println("SetUp method failed. See StackTrace for Details.");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * <p>Tests a mapped Model.</p>
	 * 
	 * <p>Checks if all Classes, Attributes and Association Ends were mapped correctly.</p>
	 */
	@Test public void run1() {
		// Check if setUp was finished successfully.
		if (!this.initialized) fail("SetUp method was not finished correctly. Test will fail.");

		// Used to transform the Guides of Classes in the test Model to
		// an SQL String which could be printed out on the Console 
		String guidesSql = "";
		
		// Create a String Array containing the Class names.
		String[] classes = new String[] {
				"Person",
				"Student",
				"Professor",
				"JuniorProfessor",
				"Examination",
				"GraduateExamination",
				"Lecture",
				"Faculty"
		};
		
		// Create a HashMap containing the Classes and their Attributes.
		Map<String, String[]> class2attributes = new HashMap<String, String[]>();
		class2attributes.put("Person", new String[]{"lastname","firstname", "age" });
		class2attributes.put("Student", new String[]{"lastname","firstname", "age", "studentID" });
		class2attributes.put("Professor", new String[]{"lastname","firstname", "age" });
		class2attributes.put("JuniorProfessor", new String[]{"lastname","firstname", "age", "juniorYear" });
		class2attributes.put("Lecture", new String[]{"topic" });
		class2attributes.put("GraduateExamination", new String[]{"topic" });
		
		// Create a HashMap containing the Classes and their Association Ends.
		Map<String, String[]> class2assEnds = new HashMap<String, String[]>();
		class2assEnds.put("Student", new String[]{"examination","lecture"});
		class2assEnds.put("Professor", new String[]{"faculty", "lecture"});
		class2assEnds.put("JuniorProfessor", new String[]{"faculty", "lecture"});
		class2assEnds.put("Faculty", new String[]{"head"});
		class2assEnds.put("Lecture", new String[]{"professor", "listener", "examination"});
		class2assEnds.put("Examination", new String[]{"participant", "lecture"});
		class2assEnds.put("GraduateExamination", new String[]{"participant", "lecture"});
		
		// The mapped Model should not contain the mapped Class 'falsePositive'
		assertFalse(mm.isClass("falsePositive"));
		// It also should not return this Class
		try {
			mm.getClass("FalsePositive");
			// This should not happen.
			fail("IllegalArgumentException expected.");
		} 
		catch (IllegalArgumentException e){
			// Expected Exception
		}		
		
		// Check the Classes names
		for (String classname : classes) {
			// Each name should return a Class
			MappedClass mc = mm.getClass(classname);
			assertNotNull(mc);
			
			// The returned Class should have the given name
			assertEquals(mc.getName(), classname);
			
			// Each name should represent a Class
			assertTrue(mm.isClass(classname));
			
			// The class should not have the atrribute 'falsePositive' 
			String[] attributes = class2attributes.get(classname);
			assertFalse(mc.isAttribute("falsePositve"));

			// Each Class should have a Guide Object
			Guide cGuide = mc.getClassGuide();
			assertNotNull(cGuide);
			
			// Create a piece of the guidesSql String
			guidesSql += toSqlString(cGuide, "Class Guide " + classname);
			
			// The Class should not have the Attribute Guide 'falsePositive'
			try {
				mc.getAttributeGuide("falsePositive");	
				// This should not happen
				fail("Illegal ArgumentException expected");
			} 
			catch (IllegalArgumentException e) {
				// Expected Exception
			}
			
			// Check if the mapped Class has all attributes specified in the HashMap
			if (attributes != null) {
				for (String attribute : attributes) {
					assertTrue(mc.isAttribute(attribute));
					// Check if each Attribute has its Attribute Guide
					Guide attG = mc.getAttributeGuide(attribute);
					assertNotNull(attG);
					// Create a piece of the guidesSql String
					guidesSql += toSqlString(attG, "Attribute Guide " + classname + "." + attribute);
				}
			}
			
			// The mapped Class should not have the AssociationEnd 'falsePositive'
			assertFalse(mc.isAssociationEnd("falsePositive"));
			try {
				// This Attribute should also not have an AssociationEndGuide
				mc.getAssociationEndGuide("falsePositive");	
				// This should not happen.
				fail("Illegal ArgumentException expected");
			} 
			catch (IllegalArgumentException e) {
				// Expected Exception
			}
			
			// Check if the mapped Class has all AssociationEnds specified in the HashMap
			String[] assEnds = class2assEnds.get(classname);
			if (assEnds != null) {
				for (String assEnd : assEnds) {
					assertTrue(mc.isAssociationEnd(assEnd));
					// Check if each AssociationEnd has its AssociationEndGuide 
					Guide assEG = mc.getAssociationEndGuide(assEnd);
					assertNotNull(assEG);
					// Create a piece of the guidesSql String
					guidesSql += toSqlString(assEG, "Association End Guide " + classname + "->" + assEnd);
				}
			}
		}
		
		// Prints the guidesSQL String on the console if specified
		if (PRINT_GUIDES_SQL) System.out.println(guidesSql);
		
		// Write the guidesSQL into a file if specified
		if (WRITE_GUIDES_SQL) {
			FileOutputStream fos;
			try {
				fos = new FileOutputStream( "src/tudresden/ocl20/tests/results/guides.sql" );
				fos.write( guidesSql.getBytes() );
				fos.close();
			} catch (FileNotFoundException e) {
				System.out.println("File 'guides.sql' was not found.");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("IO Exception occured. 'guides.sql' could not be written correctly.");
				e.printStackTrace();
			}
		}
	
	}
	
	/**
	 * <p>Method used to print SQL Strings on the console and into files.</p>
	 * 
	 * <p>This method transforms a given Guide and its name into an SQL Statement.</p>
	 * 
	 * @param guide The Guide
	 * @param guidename Its name
	 * @return The SQL Statement.
	 */
	private String toSqlString(Guide guide, String guidename) {
		String sql = "\n\n/* " + guidename + " */\n";
		while(guide.hasNext()) {
			guide.next();
			sql += "( SELECT "  + guide.getSelect();
			sql += " FROM " + guide.getFrom();
			sql += " WHERE " + guide.getWhere();
			if (!guide.hasNext()) {
				sql += "=1";
			}
			else {
				sql += " IN ";
			}
			
		}
		for(int i=0; i<guide.numberOfSteps(); i++) {
			sql += ")";
		}
		sql += ";";
		return sql;
	}

	/**
	 * Method which helps the setUp method to load an UMLModel and communicate
	 * with the repository.
	 * 
	 * @param name The name of the model which should be added.
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws RepositoryException
	 * @throws MalformedURLException
	 */
	private void addUMLModel(String name) throws UnsupportedEncodingException, RepositoryException, MalformedURLException {
		
		//Path for UMLModel (as XMI)
		URL xmiPath = new URL("file:resources/cwm/UmlModels/university.xmi");
		
		// Tries to get the UML model (given by 'name') from the repository
		Repository r = RepositoryManager.getRepository();
		RefPackage model = r.getModel(name);
		if (model != null) {
			try {
				r.deleteModel(model);
			} catch (RepositoryException e) {
				// Set initialized = false, so the tests won't be executed.
				this.initialized = false;
				System.out.println("addUMLModel method during SetUp failed. See StackTrace for Details.");
				e.printStackTrace();
			}
		}
		
		// Tries to load the OCLModel given by a xmiPath
		try {
			// OclModel seems to be unused but is important!
			@SuppressWarnings("unused")
			OclModel oclModel = null;
			oclModel = new OclModel(MetaModelConst.UML15,xmiPath.toString(),name);
		} catch (OclModelException e) {
			// Set initialized = false, so the tests won't be executed.
			this.initialized = false;
			System.out.println("addUMLModel method during SetUp failed. See StackTrace for Details.");
			e.printStackTrace();
		}
	}

}
