/*
 * TestSQLCodeGenerator.java
 * 
 * Copyright (c) 2001 Sten Loecher
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

package tudresden.ocl20.tests.codegen.sql.codegen;

import java.util.ArrayList;
import java.util.List;

import tudresden.ocl20.codegen.sql.codegen.Guide;
import tudresden.ocl20.codegen.sql.codegen.ObjectViewSchema;
import tudresden.ocl20.codegen.sql.codegen.OracleSQLBuilder;
import tudresden.ocl20.codegen.sql.codegen.SQLCodeGenerator;
import tudresden.ocl20.codegen.sql.orm.ORMappingScheme;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class Provides some quite simple test cases for creating SQL integrity
 * views for OclExpressions by the SQLCodeGenerator.
 * 
 * <b>Attention:</b> Some of the tested methods and classes are depricated by now.
 *
 *@see tudresden.ocl20.codegen.sql.codegen.SQLCodeGenerator 
 */
/*
 * JavaDoc and comments added and changed to jUnit4 by Claas Wilke in 2007
 */
@SuppressWarnings("deprecation")
public class TestSQLCodeGenerator {
	
	/**
	 * Compares to Strings <b>str1</b> and <b>str2</b> and returns true if they are equal.
	 * The method deletes the chars ' ' and '\n' before the Strings are compared.
	 * The method doesn't differentiate between Lower and Upper Cases. 
	 * 
	 * @param str1 The first String which should be compared
	 * @param str2 The second String which should be compared
	 * @return True if the Strings <b>str1</b> and <b>str2</b> are equal
	 */
	private boolean equal(String str1, String str2) {
		StringBuffer sb1 = new StringBuffer(), sb2 = new StringBuffer();

		// Remove ' ' and '\n' chars from str1
		for (int i=0; i<str1.length(); i++) {
			if ((str1.charAt(i) != '\n') && (str1.charAt(i) != ' ')) sb1.append(str1.charAt(i));
		}

		// Remove ' ' and '\n' chars from str2
		for (int i=0; i<str2.length(); i++) {
			if ((str2.charAt(i) != '\n') && (str2.charAt(i) != ' ')) sb2.append(str2.charAt(i));
		}

		// Set 'str1' and 'str2' toUpperCase
		str1 = sb1.toString().toUpperCase();
		str2 = sb2.toString().toUpperCase();

		// compare 'str1' and 'str2'
		return str1.equals(str2);
	}
	
	/**
	 * This testMethod tests the Method prepareDereviedTable of the SQLCodeGenerator
	 * 
	 * @throws Exception
	 */
	@Test public void testPrepareDerivedTable() throws Exception{
		
		// Create a String containing an URL of the file containing the mapping rules
		String sqlCodeGenPatternCatalogue = "resources/sql/OCL2SQL4Oracle.xml";
		
		// Create a OobjetViewSchema whith the UniversityExample as ORMappingSchema and
		// a OracleSQLBuilder as SQLBuilder
		ORMappingScheme orm = new ObjectViewSchema(new UniversityExampleSchema(), new OracleSQLBuilder());;
		
		// Create a new Codegenerator and set its ORMappingSchema
		SQLCodeGenerator theCG = new SQLCodeGenerator(sqlCodeGenPatternCatalogue);
		theCG.setORMappingScheme(orm);
		
		// Create a List of Guides
        List<Guide> guides = new ArrayList<Guide>();
		Guide guide;

		/* Create Guides with SelectFromWhere Statements and add them to the Guide List
		 * to check the Statement
		 * 		(select A1 from A where APK in 
		 *		(select AFK from C where CPK = SELF.CPK))
		 * and to test attribute access with navigation
		 */
		guide = new Guide(false);
		guide.add("A1","A","APK");
		guides.add(guide);

		guide = new Guide(true);
		guide.add("APK","A","APK");
		guide.add("AFK","C","CPK");
		guide.setAlias("SELF");
		guides.add(guide);

		// Reset the CodeGenerator to initial values
		theCG.reset();
		
		// Prepare Navigation with the created Guide List
		theCG.prepareNavigation(guides);

		// Could be used to print the Statements added to the guide List
		// out of the console
        //System.err.println(theCG.getTableRepresentation());

		// Check if the Codegenerator created the right SQL statement
		assertTrue(equal(theCG.getTableRepresentation(),
				"(select A1 from A where APK in" +
				"(select AFK from C where CPK = SELF.CPK))"));
	    // The created Statement should not contain any JoinRepresentations
		assertTrue(theCG.getJoinRepresentation().equals(""));

		/* Clear the Guide List and add a new SQL-Query called
		 *		(select APK from A where APK in 
		 *		(select AFK from AB where BFK in 
		 *		(select BPK from B where BPK in 
		 *		(select BFK from BC where CFK in 
		 *		(select CPK from C where CPK in 
		 *		(select CFK from CD where DFK in 
		 *		(select DPK from D where DPK = SELF.DPK)))))))
		 * to check the pure navigation over several guides
		 */
		guides.clear();

		guide = new Guide(true);
		guide.add("APK","A","APK");
		guide.add("AFK","AB","BFK");
		guide.add("BPK","B","BPK");
		guides.add(guide);

		guide = new Guide(true);
		guide.add("BPK","B","BPK");
		guide.add("BFK","BC","CFK");
		guide.add("CPK","C","CPK");
		guides.add(guide);

		guide = new Guide(true);
		guide.add("CPK","C","CPK");
		guide.add("CFK","CD","DFK");
		guide.add("DPK","D","DPK");
		guide.setAlias("SELF");
		guides.add(guide);
		
		// Reset the CodeGenerator to initial values
		theCG.reset();
		
		// Prepare Navigation with the created Guide List
		theCG.prepareNavigation(guides);

		// Could be used to print the Statements added to the guide List
		// out of the console
        //System.err.println(theCG.getTableRepresentation());

		// Check if the Codegenerator created the right SQL statement
		assertTrue(equal(theCG.getTableRepresentation(),
                     "(select APK from A where APK in\n" +
                     "(select AFK from AB where BFK in\n" +
                     "(select BPK from B where BPK in\n" +
                     "(select BFK from BC where CFK in\n" +
                     "(select CPK from C where CPK in\n" +
                     "(select CFK from CD where DFK in\n" +
                     "(select DPK from D where DPK = SELF.DPK)))))))"));
	    // The created Statement should not contain any JoinRepresentations
		assertTrue(theCG.getJoinRepresentation().equals(""));


		/* Clear the Guide List and add a new SQL-Query called
		 *		(select APK from A where APK in 
		 *		(select RAPK from A where APK = SELF.APK))		
		 * to check the navigation to same table
		 */
		guides.clear();

		guide = new Guide(true);
		guide.add("APK","A","APK");
		guide.add("RAPK","A","APK");
		guide.setAlias("SELF");
		guides.add(guide);

		theCG.reset();
		theCG.prepareNavigation(guides);

		// Could be used to print the Statements added to the guide List
		// out of the console
        //System.err.println(theCG.getTableRepresentation());

		// Check if the Codegenerator created the right SQL statement
        assertTrue(equal(theCG.getTableRepresentation(),
                     "(select APK from A where APK in\n" +
                     "(select RAPK from A where APK = SELF.APK))"));
        // The created Statement should not contain any JoinRepresentations
		assertTrue(theCG.getJoinRepresentation().equals(""));
	}

}
