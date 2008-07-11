/*
    Copyright (C) 2007  Nils (s0006383@inf.tu-dresden.de)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package tudresden.ocl20.pivot.ocl2parser.test.parsertests;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import tudresden.ocl20.pivot.modelbus.IMetamodel;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelProvider;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.ocl2parser.parser.OCL2Parser;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * This testcase tests the parser by letting the parser parse all test data files
 * that are in the directory <i>testdata</i>. For each file one test method exists,
 * so if an error occurs we can located the file in which the error occurred.
 * All messages are written to the console.
 * @author Nils
 *
 */
public class OclFilesTest extends TestCase {
	public void testOclFile1() {
		String fileName = "oclTestFiles/oclFile01.ocl";
		try {
			TestPerformer test = TestPerformer.getDefault();
			test.setUMLModel("LoyalRoyalOCL2Parser_4.xmi");
			test.parseFile(fileName);
		} catch(Throwable ex) {
			String message = " This error occured for file " + fileName + ".";
			System.err.println(message);
			ex.printStackTrace();
			
			fail();
			return;
		}
		
		assertTrue(true);
	}
	
	public void testOclFile2() {
		String fileName = "oclTestFiles/oclFile02.ocl";
		try {
			TestPerformer test = TestPerformer.getDefault();
			test.parseFile(fileName);
		} catch(Throwable ex) {
			String message = " This error occured for file " + fileName + ".";
			System.err.println(message);
			ex.printStackTrace();
			
			fail();
			return;
		}
		
		assertTrue(true);
	}
	
	public void testOclFile3() {
		String fileName = "oclTestFiles/oclFile03.ocl";
		try {
			TestPerformer test = TestPerformer.getDefault();
			test.parseFile(fileName);
		} catch(Throwable ex) {
			String message = " This error occured for file " + fileName + ".";
			System.err.println(message);
			ex.printStackTrace();
			
			fail();
			return;
		}
		
		assertTrue(true);
	}
	
	public void testOclFile4() {
		String fileName = "oclTestFiles/oclFile04.ocl";
		try {
			TestPerformer test = TestPerformer.getDefault();
			test.parseFile(fileName);
		} catch(Throwable ex) {
			String message = " This error occured for file " + fileName + ".";
			System.err.println(message);
			ex.printStackTrace();
			
			fail();
			return;
		}
		
		assertTrue(true);
	}
	
	public void testOclFile5() {
		String fileName = "oclTestFiles/oclFile05.ocl";
		try {
			TestPerformer test = TestPerformer.getDefault();
			test.parseFile(fileName);
		} catch(Throwable ex) {
			String message = " This error occured for file " + fileName + ".";
			System.err.println(message);
			ex.printStackTrace();
			
			fail();
			return;
		}
		
		assertTrue(true);
	}
	
	/*public void testOclFile6() {
		String fileName = "oclTestFiles/oclFile06.ocl";
		try {
			OclFileTest test = OclFileTest.getDefault();
			test.parseFile(fileName);
		} catch(Throwable ex) {
			String message = " This error occured for file " + fileName + ".";
			System.err.println(message);
			ex.printStackTrace();
			
			fail();
			return;
		}
		
		assertTrue(true);
	}*/
	
	public void testOclFile7() {
		String fileName = "oclTestFiles/oclFile07.ocl";
		try {
			TestPerformer test = TestPerformer.getDefault();
			test.parseFile(fileName);
		} catch(Throwable ex) {
			String message = " This error occured for file " + fileName + ".";
			System.err.println(message);
			ex.printStackTrace();
			
			fail();
			return;
		}
		
		assertTrue(true);
	}
	
	public void testOclFile8() {
		String fileName = "oclTestFiles/oclFile08.ocl";
		try {
			TestPerformer test = TestPerformer.getDefault();
			test.parseFile(fileName);
		} catch(Throwable ex) {
			String message = " This error occured for file " + fileName + ".";
			System.err.println(message);
			ex.printStackTrace();
			
			fail();
			return;
		}
		
		assertTrue(true);
	}
	
	public void testOclFile9() {
		String fileName = "oclTestFiles/oclFile09.ocl";
		try {
			TestPerformer test = TestPerformer.getDefault();
			test.parseFile(fileName);
		} catch(Throwable ex) {
			String message = " This error occured for file " + fileName + ".";
			System.err.println(message);
			ex.printStackTrace();
			
			fail();
			return;
		}
		
		assertTrue(true);
	}
	
	public void testOclFile10() {
		String fileName = "oclTestFiles/oclFile10.ocl";
		try {
			TestPerformer test = TestPerformer.getDefault();
			test.parseFile(fileName);
		} catch(Throwable ex) {
			String message = " This error occured for file " + fileName + ".";
			System.err.println(message);
			ex.printStackTrace();
			
			fail();
			return;
		}
		
		assertTrue(true);
	}
	
	public void testOclFile11() {
		String fileName = "oclTestFiles/oclFile11.ocl";
		try {
			TestPerformer test = TestPerformer.getDefault();
			test.parseFile(fileName);
		} catch(Throwable ex) {
			String message = " This error occured for file " + fileName + ".";
			System.err.println(message);
			ex.printStackTrace();
			
			fail();
			return;
		}
		
		assertTrue(true);
	}
	
/*
 * 
 * 
 * Here we switch the model to Person.xmi
 * 
 * 
 * 
 * 
 */
	public void testAllInstanceTest() {
		
		String fileName = "oclTestFiles/allInstanceTest.ocl";
		try {
			TestPerformer test = TestPerformer.getDefault();
			test.setUMLModel("PersonTest.xmi");
			test.parseFile(fileName);
		} catch(Throwable ex) {
			String message = " This error occured for file " + fileName + ".";
			System.err.println(message);
			ex.printStackTrace();
			
			fail();
			return;
		}
		
		assertTrue(true);

		/*IMetamodel umlMetaModel = null;
		IModelProvider modelProvider = null;
		IModel model = null;
		String fileDirectory = null;
		
		try {
			umlMetaModel = ModelBusPlugin.getMetamodelRegistry().getMetamodel("tudresden.ocl20.pivot.metamodels.uml");
			if (umlMetaModel == null) {
				throw new Exception("Unable to load uml metmodel.   ");
			}
		} catch(Exception ex) {
			System.err.println("Unable to load uml metmodel.   ");
			ex.printStackTrace();
			return;
		}
		
		
		
		modelProvider = umlMetaModel.getModelProvider();
		File currentDir = new File(".");
		System.out.println("Current directory: " + currentDir.getAbsolutePath());
		fileDirectory = "./src/testData/";
		File modelFile = new File(fileDirectory+ "PersonTest.xmi");
		if (!modelFile.exists()) {
			System.err.println("The model file doesn't exists.");
			fail();
			return;
		}
		
		try {
			model = modelProvider.getModel(modelFile);
		} catch(ModelAccessException ex) {
			System.err.println("An error occured while loading the model.");
			ex.printStackTrace();
			fail();
			return;
		}
		
		try {
			System.out.println("Model namespace: " + model.getRootNamespace().getName());
			List<String> nm = new ArrayList<String>();
			nm.add("Test");
			Namespace namespace = model.findNamespace(nm);
			if (namespace == null) {
				System.out.println("No namespace was found");
			} else {
				System.out.println("The namespace that was found is: " + namespace.getName());
			}
			
			List<String> pathNamePerson = new ArrayList<String>();
			pathNamePerson.add("Person");
			Type personType = model.findType(pathNamePerson);
			System.out.println("Operations of person:");
			for(Operation op : personType.allOperations()) {
				System.out.println("Operation of person: " + op.getName());
			}
		} catch (ModelAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		String filename = "oclTestFiles/allInstanceTest.ocl"; 
		try {
			
			File oclFile = new File(fileDirectory + filename);
			System.out.println("The file is searched at: " + fileDirectory + filename);
			if (!oclFile.exists()) throw new Exception("The ocl test file doesn't exists. File name: " + filename);
			
			FileReader oclFileReader = new FileReader(oclFile);
			
			OCL2Parser parser = new OCL2Parser(model, oclFileReader);
			parser.parse();
		} catch(Throwable ex) {
			String message = " This error occured for file " + filename + ".";
			System.err.println(message);
			ex.printStackTrace();
			
			fail();
			return;
		}
		
		assertTrue(true);*/
	}
	
	public void testDefinitionTest() {
		String fileName = "oclTestFiles/definitionTest.ocl";
		try {
			TestPerformer test = TestPerformer.getDefault();
			test.parseFile(fileName);
		} catch(Throwable ex) {
			String message = " This error occured for file " + fileName + ".";
			System.err.println(message);
			ex.printStackTrace();
			
			fail();
			return;
		}
		
		assertTrue(true);
	}
	
	public void testCollectionTest() {
		String fileName = "oclTestFiles/collectionTest.ocl";
		try {
			TestPerformer test = TestPerformer.getDefault();
			test.parseFile(fileName);
		} catch(Throwable ex) {
			String message = " This error occured for file " + fileName + ".";
			System.err.println(message);
			ex.printStackTrace();
			
			fail();
			return;
		}
		
		assertTrue(true);
	}
	
	public void testIteratorCollectionTest() {
		String fileName = "oclTestFiles/iteratorCollectionTest.ocl";
		try {
			TestPerformer test = TestPerformer.getDefault();
			test.parseFile(fileName);
		} catch(Throwable ex) {
			String message = " This error occured for file " + fileName + ".";
			System.err.println(message);
			ex.printStackTrace();
			
			fail();
			return;
		}
		
		assertTrue(true);
	}
	
	
}
