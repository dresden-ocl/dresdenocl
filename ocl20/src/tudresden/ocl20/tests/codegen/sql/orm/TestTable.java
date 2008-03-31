/*
 * TestTable.java
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
package tudresden.ocl20.tests.codegen.sql.orm;

import tudresden.ocl20.codegen.sql.orm.Table;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class Provides some quite simple test cases for creating SQL integrity
 * views for OclExpressions by the SQLCodeGenerator.
 * 
 * <b>Attention:</b> Some of the tested methods and classes are depricated by now.
 *
 *@see tudresden.ocl20.codegen.sql.orm.Table
 */
/*
 * JavaDoc and comments added and changed to jUnit4 by Claas Wilke in 2007
 */
@SuppressWarnings("deprecation")
public class TestTable {

	/**
	 * This method tests the acces on columns of a Table
	 */
	@Test public void testColumnAccess() {
		String tmp[];
		// Create a new Table called 'A'
		Table t = new Table("A");

		// Add five columns to 'A'
		t.addColumn("a1");
		t.addColumn("a2");
		t.addColumn("a3");
		t.addColumn("a4");
		t.addColumn("a5");

		// Get the columns of 'A'
		tmp = t.getColumns();
		// A must have five columns with the given names
		assertTrue(tmp.length == 5);
		assertTrue(tmp[0].equals("a1"));
		assertTrue(tmp[1].equals("a2"));
		assertTrue(tmp[2].equals("a3"));
		assertTrue(tmp[3].equals("a4"));
		assertTrue(tmp[4].equals("a5"));
	}

	/**
	 * This method tests the access on primary
	 * keys of a Table
	 */
	@Test public void testPrimaryKeyAccess() {
		String tmp[];
		// Create a new Table 'A'
		Table t = new Table("A");

		// Add five columns
		// a1 is primary key
		t.addColumn(null, null, "a1", true);
		t.addColumn("a2");
		t.addColumn("a3");
		t.addColumn("a4");
		t.addColumn("a5");

		// The string representation of primary keys should
		// only contain a1
		assertTrue(t.getPrimaryKeyRepresentation().equals("a1"));
		// Set a2 as primary key
		t.setPrimaryKey("a2");
		// The string representation of primary keys should
		// contain a1 and a2
		assertTrue(t.getPrimaryKeyRepresentation().equals("(a1,a2)"));

		// Table A must have the two primary keys a1 and a2
		tmp = t.getPrimaryKeyColumns();
		assertTrue(tmp.length == 2);
		assertTrue(tmp[0].equals("a1"));
		assertTrue(tmp[1].equals("a2"));

		assertTrue(t.isPrimaryKeyColumn("a1"));
		assertTrue(!t.isPrimaryKeyColumn("a3"));

		// Setting a NULL value as primary key should cause an NullPointerException
		try {
			t.setPrimaryKey(null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}

		// Setting a value which isn't a column of the Table as primary key 
		// should cause an IllegalArgumentException
		try {
			t.setPrimaryKey("a6");
			fail("Missing Exception !");
		} catch(IllegalArgumentException e) {
		}

		// Checking if a NULL value is a primary key should 
		// cause an NullPointerException
		try {
			t.isPrimaryKeyColumn(null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}
	}

	/**
	 * This method tests acces on foreign keys of a Table
	 */
	@Test public void testForeignKeyAccess() {
		String tmp[];
		// Create a new Table calles 'A'
		Table t = new Table("A");

		// Add five columns. Column 'a1' is primary key
		t.addColumn(null, null, "a1", true);
		t.addColumn("a2");
		t.addColumn("a3");
		t.addColumn("a4");
		t.addColumn("a5");
		// Set 'a2' as foreign key to column 'b1' in table 'B'
		t.setForeignKey("a2", "B", "b1");
		// Set 'a3' as foreign key to column 'c1' in table 'C'
		t.setForeignKey("a3", "C", "c1");

		// Table 'A' schould habe two foreign keys called 'a2' and 'a3'
		tmp = t.getForeignKeyColumns();
		assertTrue(tmp.length == 2);
		assertTrue(tmp[0].equals("a2"));
		assertTrue(tmp[1].equals("a3"));

		// 'a2' and 'a3' must be foreign keys
		assertTrue(t.isForeignKeyColumn("a2"));
		assertTrue(t.isForeignKeyColumn("a3"));
		// 'a4' shouldn't be a foreign key
		assertTrue(!t.isForeignKeyColumn("a4"));
		// 'a2' must guide to the foreign table 'B'
		assertTrue(t.getForeignTable("a2").equals("B"));
		// 'a2' must guide to the forein column 'b1'
		assertTrue(t.getForeignColumn("a2").equals("b1"));
		// 'a3' must guide to the foreign table 'C'
		assertTrue(t.getForeignTable("a3").equals("C"));
		// 'a3' must guide to the forein column 'c1'
		assertTrue(t.getForeignColumn("a3").equals("c1"));

                
		// Setting a foreign key to a NULL value table
		// must cause an IllegalArgumentException
		try {
			t.setForeignKey("a5", null, null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}
                
		// Setting a not existing column as foreign key
		// must cause an IllegalArgumentException
		try {
			t.setForeignKey("a6", "B", "b1");
			fail("Missing Exception !");
		} catch(IllegalArgumentException e) {
		}

		// Checking if a NULL value is a foreign key
		// must cause a NullPointerException
		try {
			t.isForeignKeyColumn(null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}
	}

	/**
	 * This method tests property Access on a Table
	 */
	@Test public void testPropertyAccess() {
		String tmp[];
		// Create a new table t called 'A'
		Table t = new Table("A");

		// Add five Columns. Column 'a1' ist primary key
		t.addColumn("att1", "int", "a1", true);
		t.addColumn("att2", "boolean", "a2", false);
		t.addColumn("att3", "String", "a3", false);
		t.addColumn("att3", "String", "a4", false);
		t.addColumn("att4", "long", "a5");

		// The name of t should be 'A'
		assertTrue(t.getTableName().equals("A"));

		// The attribute called 'att2' should return column 'a2'
		assertTrue(t.getAttributeColumn("att2").equals("a2"));

		// The attriubte called 'att3' should return
		// two columns 'a3' and 'a4'
		tmp = t.getAttributeColumns("att3");
		assertTrue(tmp.length == 2);
		assertTrue(tmp[0].equals("a3"));
		assertTrue(tmp[1].equals("a4"));

		// The attribute called 'att2' should return column 'a1'
		tmp = t.getAttributeColumns("att1");
		assertTrue(tmp.length == 1);
		assertTrue(tmp[0].equals("a1"));

		// The Table t should habe five colums
		tmp = t.getColumns();
		assertTrue(tmp.length == 5);
		assertTrue(tmp[0].equals("a1"));
		assertTrue(tmp[1].equals("a2"));
		assertTrue(tmp[2].equals("a3"));
		assertTrue(tmp[3].equals("a4"));
		assertTrue(tmp[4].equals("a5"));

		// Check the method isColumn
		assertTrue(t.isColumn("a5"));
		assertTrue(!t.isColumn("a6"));

		// The column 'a2' should be part of the attriubte 'att2'
		assertTrue(t.getColumnAttribute("a2").equals("att2"));

		// Check the types of the columns 'a1' to 'a4'
		assertTrue(t.getColumnType("a1").equals("int"));
		assertTrue(t.getColumnType("a2").equals("boolean"));
		assertTrue(t.getColumnType("a3").equals("String"));
		assertTrue(t.getColumnType("a4").equals("String"));

		// An attribute containing more than one column shouldn't
		// return its one column an should fail with an
		// IllegalStateException
		try {
			t.getAttributeColumn("att3");
			fail("Missing Exception !");
		} catch(IllegalStateException e) {
		}

		// Getting the column of a NULL value attribute should
		// cause a NullPointerException
		try {
			t.getAttributeColumn(null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}

		// Getting the columns of a NULL value attribute should
		// cause a NullPointerException
		try {
			t.getAttributeColumns(null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}

		// Getting the column attribute of a NULL value column should
		// cause a NullPointerException
		try {
			t.getColumnAttribute(null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}

		// Getting a not existing column should cause an
		// IllegalStateException
		try {
			System.err.println(t.getColumnType("a6"));
			fail("Missing Exception !");
		} catch(IllegalArgumentException e) {
		}

		// Getting the type of a NULL value column schould retunr
		// a NullPointerException
		try {
			t.getColumnType(null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}
	}

	/**
	 * This method will test the attribute access on
	 * a table
	 */
    @Test public void testAttributeAccess() {
    	// Create a new table t called 'A'
		Table t = new Table("A");

		// Add five columns to t, 'a1' is primary key
		t.addColumn(" ", "int", "a1", true);
		t.addColumn("att2", "boolean", "a2", false);
		t.addColumn("att3", "String", "a3", false);
		t.addColumn("att3", "String", "a4", false);
		t.addColumn("att4", "long", "a5");

		// Table t should have three different attributes
        assertTrue(t.attributes().size() == 3);
        assertTrue(t.attributes().contains("att2"));
        assertTrue(t.attributes().contains("att3"));
        assertTrue(t.attributes().contains("att4"));
    }

}
