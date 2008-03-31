/*
 * TestDeclarativeCodeGenerator.java
 * 
 * Copyright (c) 2006 Florian Heidenreich
 * Contact: <mail@fheidenreich.de>
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

package tudresden.ocl20.tests.codegen.decl.codegen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PushbackReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.jmi.reflect.RefPackage;

import tudresden.ocl20.codegen.decl.codegen.DeclarativeCodeGenerator;
import tudresden.ocl20.codegen.decl.mapping.MappedModel;
import tudresden.ocl20.codegen.decl.template.DeclarativeTemplateEngine;
import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.OclModel;
import tudresden.ocl20.core.Repository;
import tudresden.ocl20.core.RepositoryException;
import tudresden.ocl20.core.RepositoryManager;
import tudresden.ocl20.core.jmi.uml15.core.Classifier;
import tudresden.ocl20.core.jmi.uml15.uml15.Uml15Package;
import tudresden.ocl20.core.jmi.uml15.uml15ocl.expressions.ExpressionInOcl;
import tudresden.ocl20.core.parser.astgen.Heritage;
import tudresden.ocl20.core.parser.astgen.LAttrAstGenerator;
import tudresden.ocl20.core.parser.sablecc.lexer.Lexer;
import tudresden.ocl20.core.parser.sablecc.node.Start;
import tudresden.ocl20.core.parser.sablecc.parser.Parser;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;

/**
 * This test class tests the DeclarativeCodeGenerator
 *
 *@see tudresden.ocl20.codegen.decl.codegen.DeclarativeCodeGenerator
 */
/*
 * JavaDoc added changed to jUnit by Claas Wilke in 2007
 */
public class TestDeclarativeCodeGenerator {

	private DeclarativeCodeGenerator codeGen;
	private DeclarativeTemplateEngine templateEngine;
	private Map<String, ExpressionInOcl> constraints;
	private MappedModel mappedModel;
	private OclModel model;	
	
	// Specifies, if the setUp method was finished successfully
	private boolean initialized = false;

	/**
	 * This method removes all blank spaces from a string and
	 * returns the string flatten to lower cases.
	 * 
	 * @param str The String which should be transformed
	 * @return The transformed String
	 */
	private String normalize(String str) {
		return str.replaceAll("\\s", "").toLowerCase();
	}

	/**
	 * Prepares the test of the DeclarativeCodeGenerator
	 * 
	 * <p><strong>ATTENTION: This test case uses some files located
	 * in the <code>resources</code> folder of the Dresden OCL2 Toolkit!
	 * Make sure, that the <code>resources</code> folder is listed as a
	 * library in the classpath of your project!</strong></p>
	 * 
	 * <p><strong>ATTENTION: This test case uses the Java class 
	 * <code>FileReader</code> which seems to have some problems with
	 * paths containing white spaces. Make sure, that your project
	 * folder and its absolute path does not contain any white spaces!</strong></p>
	 */
	@SuppressWarnings("unchecked")
	@Before public void setUp() {
		try {
			// Test is not initialized yet but will be initialized if no error
			// will occure during setUp method.
			initialized = true;
			
			// Create a HashMap for constraints in OCL
			constraints = new HashMap<String, ExpressionInOcl>();

			String name = "testmodel";

			// Get the repository and try to get the 'testmodel'
			Repository r = RepositoryManager.getRepository();
			RefPackage modelRef = r.getModel(name);
			if (modelRef != null) {
				try {
					r.deleteModel(modelRef);
				} catch (RepositoryException e) {
					initialized = false;
					e.printStackTrace();
				}
			}

			// Load the OclModel
			String xmiFileUrl = (new File(ClassLoader.getSystemClassLoader()
					.getResource("decl/test/models/university_example.xmi").getFile()))
					.toURL().toString();
			model = new OclModel(MetaModelConst.UML15, xmiFileUrl, name);
			model.beginTrans(true);

			// create an ExampleMappedModel
			mappedModel = new ExampleMappedModel();

			// Create TemplateEngine and load templates from the resources
			// to map the model to SQL
			LinkedList<String> groupFiles = new LinkedList<String>();
			System.out.println(ClassLoader.getSystemResource("decl/sql/sql92.stg"));
			System.out.println(ClassLoader.getSystemResource("decl/sql/sqloracle8i.stg"));

			groupFiles.add("decl/sql/sql92.stg");
			groupFiles.add("decl/sql/sqloracle8i.stg");
			templateEngine = new DeclarativeTemplateEngine(groupFiles);

			// Create DeclarativeCodeGenerator with mappedModel and templateEngine
			codeGen = new DeclarativeCodeGenerator(mappedModel, templateEngine);

			// ASM
			// Try to load OCL constraints and parse concrete syntax tree
			try {
				Lexer lexer = 
				new Lexer(new PushbackReader(new BufferedReader(
						new FileReader(new File(ClassLoader.getSystemClassLoader()
								.getResource("decl/test/constraints/university_example.ocl")
								.getFile()))
						, 1024)));
				Parser parser = new Parser(lexer);
				Start cst = parser.parse();
				LAttrAstGenerator astgen = new LAttrAstGenerator(model);
				Heritage hrtg = new Heritage();

				cst.apply(astgen, hrtg);
			} 		
			// If an Exception occurs, set initialize=false to fail the test
			// and print StackTrace
			/* ATTENTION: This code block uses the Java class 
			 * FileReader which seems to have some problems with
			 * paths containing white spaces. Make sure, that your project
			 * folder and its absolute path does not contain any white spaces! 
			 */
			catch (Exception e) {
				initialized = false;
				e.printStackTrace(System.err);
			}

			// OclExpressions
			Iterator i = ((Uml15Package) model.getModel()).getCore()
					.getClassifier().refAllOfType().iterator();
			while (i.hasNext()) {
				Collection expressions = ((Classifier) i.next())
						.getExpressionInOclA();
				Iterator ie = expressions.iterator();
				while (ie.hasNext()) {
					ExpressionInOcl eio = (ExpressionInOcl) ie.next();
					constraints.put(eio.getConstraintA().getNameA(), eio);
				}
			}

		// If an Exception occurs, set initialize=false to fail the test
		// and print StackTrace
		} catch (Exception e) {
			initialized = false;
			e.printStackTrace();
			if (model != null) {
				model.close();
				model = null;
			}
		}
	}

	/**
	 * This method is called after the test was performed.
	 */
	@After public void tearDown() {
		// close model
		if (model != null) {
			model.close();
			model = null;
		}
		// Specify that the setUp method wasn't called already for another test run.
		initialized = false;
	}

	/**
	 * This method contains a test which test the DeclarativeCodeGenerator 
	 * with some OCL constraints (9 invariants) and an UML model. Both were loaded and 
	 * prepared during the setUp method.
	 */
	@Test public void testCodeGeneration() {
		// If the setUp method wasn't finished correctly, the test should fail.
		if (initialized == false) fail ("Some error occured during setUp method. See " +
				"StackTrace for details.");

		// Variable for diffs between code and expected code
		String code;
		String expCode;

		// check 'tudOclInv1'
		code = codeGen.getCode(constraints.get("tudOclInv1"));
		expCode = new String();
		expCode += "create or replace view tudOclInv1 as (                                                       ";
		expCode += " select * from OV_PERSON SELF                                                                ";
		expCode += " where not ((                                                                                ";
		expCode += "     (select VALUE from OV_GRADE where PK_GRADE in                                           ";
		expCode += "     (select FK_GRADE from OV_PERSON where PK_PERSON in                                      ";
		expCode += "     (select FK_SUPERVISOR from OV_PERSON where PK_PERSON = SELF.PK_PERSON)))                ";
		expCode += "     >                                                                                       ";
		expCode += "     (select VALUE from OV_GRADE where PK_GRADE in                                           ";
		expCode += "     (select FK_GRADE from OV_PERSON where PK_PERSON = SELF.PK_PERSON))                      ";
		expCode += ")))                                                                                          ";
		// normalize code and expCode (remove white spaces etc.) and compare them
		code = normalize(code);
		expCode = normalize(expCode);
		assertEquals(code, expCode);

		// check 'tudOclInv2'
		code = codeGen.getCode(constraints.get("tudOclInv2"));
		expCode = new String();
		expCode += "create or replace view tudOclInv2 as (                                                       ";
		expCode += " select * from OV_FACULTY SELF                                                               ";
		expCode += " where not (((select NVL(COUNT(*),0)                                                          ";
		expCode += "             from OV_FACULTY                                                                 ";
		expCode += "             where PK_FACILITY in                                                            ";
		expCode += "             (select PK_FACILITY from OV_FACULTY where FK_SUPERFACILITY = SELF.PK_FACILITY)) ";
		expCode += "            >= 2)))                                                                           ";
		// normalize code and expCode (remove white spaces etc.) and compare them
		code = normalize(code);
		expCode = normalize(expCode);
		assertEquals(code, expCode);

		// check 'tudOclInv3'
		code = codeGen.getCode(constraints.get("tudOclInv3"));
		expCode = new String();
		expCode += "create or replace view tudOclInv3 as (                                                       ";
		expCode += " select * from OV_FACULTY SELF                                                               ";
		expCode += " where not (not exists (                                                                     ";
		expCode += "     (select PK_FACILITY from OV_FACULTY where FK_SUPERFACILITY = SELF.PK_FACILITY)          ";
		expCode += "     minus                                                                                   ";
		expCode += "     select PK_FACILITY from OV_FACULTY ALIAS2                                               ";
		expCode += "     where exists (                                                                          ";
		expCode += "           select PK_FACILITY from OV_INSTITUTE                                              ";
		expCode += "           where PK_FACILITY = ALIAS2.PK_FACILITY)                                           ";
		expCode += "     )                                                                                       ";
		expCode += "))                                                                                           ";
		// normalize code and expCode (remove white spaces etc.) and compare them
		code = normalize(code);
		expCode = normalize(expCode);
		assertEquals(code, expCode);

		// check 'tudOclInv4'
		code = codeGen.getCode(constraints.get("tudOclInv4"));
		expCode = new String();
		expCode += "create or replace view tudOclInv4 as (                                                             ";
		expCode += " select * from OV_EMPLOYEE SELF                                                              ";
		expCode += " where not ((((NOT ((select NAME from OV_GRADE where PK_GRADE in                                    ";
		expCode += "                  (select FK_GRADE from OV_EMPLOYEE where PK_PERSON = SELF.PK_PERSON))                        ";
		expCode += "                  = 'diploma')                                                               ";
		expCode += "              OR (SELF.TAXCLASS = 'tc1'))                                                     ";
		expCode += "        AND (NOT ((select NAME from OV_GRADE where PK_GRADE in                                    ";
		expCode += "                  (select FK_GRADE from OV_EMPLOYEE where PK_PERSON = SELF.PK_PERSON))                        ";
		expCode += "                  = 'doctor')                                                                ";
		expCode += "              OR (SELF.TAXCLASS = 'tc2')))                                                    ";
		expCode += "        AND (NOT ((select NAME from OV_GRADE where PK_GRADE in                                    ";
		expCode += "                  (select FK_GRADE from OV_EMPLOYEE where PK_PERSON = SELF.PK_PERSON))                        ";
		expCode += "                  = 'professor')                                                             ";
		expCode += "              OR (SELF.TAXCLASS = 'tc3')))                                                    ";
		expCode += "))                                                                                           ";
		// normalize code and expCode (remove white spaces etc.) and compare them
		code = normalize(code);
		expCode = normalize(expCode);
		assertEquals(code, expCode);

		// check 'tudOclInv5'
		code = codeGen.getCode(constraints.get("tudOclInv5"));
		expCode = new String();
		expCode += "create or replace view tudOclInv5 as (";
		expCode += " select * from OV_FACILITY SELF";
		expCode += " where not (exists (";
		expCode += "   select (select FK_HEADOFFACILITY from OV_FACILITY";
		expCode += "          where PK_FACILITY = SELF.PK_FACILITY) from DUAL";
		expCode += "   intersect";
		expCode += "  ((select PK_PERSON from OV_PERSON where PK_PERSON in";
		expCode += "   (select FK_MEMBER from ASS_ASSTAB3 where FK_OWNER in";
		expCode += "   (select PK_FACILITY from OV_FACILITY where PK_FACILITY = SELF.PK_FACILITY)))))";
		expCode += "))";
		// normalize code and expCode (remove white spaces etc.) and compare them
		assertEquals(normalize(code), normalize(expCode));

		// check 'tudOclInv6'
		code = codeGen.getCode(constraints.get("tudOclInv6"));
		expCode = new String();
		expCode += "create or replace view tudOclInv6 as (                                                                     ";
		expCode += " select * from OV_PAPER SELF                                                                         ";
		expCode += " where not ((NOT ((SELF.PURPOSE = 'Diplom')                                                           ";
		expCode += "                 AND                                                                                 ";
		expCode += "                 (((SELF.INPROGRESS = 1) AND (1=1)) OR (NOT (SELF.INPROGRESS = 1) AND NOT (1=1)))  ";
		expCode += "                )                                                                                    ";
		expCode += "            OR                                                                                       ";
		expCode += "            not exists (                                                                             ";
		expCode += "                (select PK_PERSON from OV_PERSON where PK_PERSON in                                              ";
		expCode += "                (select FK_AUTHOR from ASS_ASSTAB2 where FK_PAPERS in                                                ";
		expCode += "                (select PK_PAPER from OV_PAPER where PK_PAPER = SELF.PK_PAPER)))                                 ";
		expCode += "                minus                                                                                ";
		expCode += "                select PK_PERSON from OV_PERSON ALIAS3                                                          ";
		expCode += "                where exists (                                                                       ";
		expCode += "                              select PK_PERSON from OV_STUDENT                                             ";
		expCode += "                              where PK_PERSON = ALIAS3.PK_PERSON                                                      ";
		expCode += "                      )                                                                              ";
		expCode += "            )                                                                                        ";
		expCode += ")))                                                                                                   ";
		// normalize code and expCode (remove white spaces etc.) and compare them
		assertEquals(normalize(code), normalize(expCode));

		// check 'tudOclInv7'
		code = codeGen.getCode(constraints.get("tudOclInv7"));
		expCode = new String();
		expCode += "create or replace view tudOclInv7 as (                                                             ";
		expCode += " select * from OV_FACULTY SELF                                                               ";
		expCode += " where not (((select NAME from OV_GRADE where PK_GRADE in                                          ";
		expCode += "            (select FK_GRADE from OV_PERSON where PK_PERSON in                                        ";
		expCode += "            (select FK_HEADOFFACILITY from OV_FACULTY where PK_FACILITY = SELF.PK_FACILITY)))                  ";
		expCode += "            = 'professor'                                                                    ";
		expCode += ")))                                                                                           ";
		// normalize code and expCode (remove white spaces etc.) and compare them
		assertEquals(normalize(code), normalize(expCode));

		// check 'tudOclInv8'
		code = codeGen.getCode(constraints.get("tudOclInv8"));
		expCode = new String();
		expCode += "create or replace view tudOclInv8 as (                                                             ";
		expCode += " select * from OV_GRADE SELF                                                                 ";
		expCode += "where not (exists (                                                                          ";
		expCode += "                select SELF.NAME from DUAL                                                   ";
		expCode += "                intersect (                                                                  ";
		expCode += "                select 'none' as elem from DUAL                                              ";
		expCode += "                union                                                                        ";
		expCode += "                select 'diploma' as elem from DUAL                                           ";
		expCode += "                union                                                                        ";
		expCode += "                select 'doctor' as elem from DUAL                                            ";
		expCode += "                union                                                                        ";
		expCode += "                select 'professor' as elem from DUAL)                                        ";
		expCode += "            )                                                                                ";
		expCode += "))                                                                                           ";
		// normalize code and expCode (remove white spaces etc.) and compare them
		assertEquals(normalize(code), normalize(expCode));

		// check 'tudOclInv9'
		code = codeGen.getCode(constraints.get("tudOclInv9"));
		expCode = new String();
		expCode += "create or replace view tudOclInv9 as (                                                             ";
		expCode += "select * from OV_EMPLOYEE SELF                                                               ";
		expCode += "where not ((NOT ((select NAME from OV_GRADE where PK_GRADE in                                      ";
		expCode += "                (select FK_GRADE from OV_EMPLOYEE where PK_PERSON = SELF.PK_PERSON))                          ";
		expCode += "                = 'doctor')                                                                  ";
		expCode += "            OR ((select NVL(COUNT(*),0)                                                      ";
		expCode += "                 from OV_PAPER                                                               ";
		expCode += "                 where PK_PAPER in (select PK_PAPER from OV_PAPER where PK_PAPER in                  ";
		expCode += "                                   (select FK_PAPERS from ASS_ASSTAB2 where FK_AUTHOR in                     ";
		expCode += "                                   (select PK_PERSON from OV_EMPLOYEE where PK_PERSON = SELF.PK_PERSON)))        ";
		expCode += "                                   minus                                                     ";
		expCode += "                                   select PK_PAPER from OV_PAPER ALIAS4                     ";
		expCode += "                                   where not ((ALIAS4.PURPOSE = 'Dissertation'))))        ";
		expCode += "                = 1)                                                                         ";
		expCode += ")))                                                                                           ";
		// normalize code and expCode (remove white spaces etc.) and compare them
		assertEquals(normalize(code), normalize(expCode));
	}
	
}
