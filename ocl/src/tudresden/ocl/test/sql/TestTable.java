/*
Copyright (C) 2000  Sten Loecher

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

To submit a bug report, send a comment, or get the latest news on
this project, please see the contactReadme.txt file in this package.
*/
package tudresden.ocl.test.sql;

import junit.framework.*;
import tudresden.ocl.codegen.decl.*;

public class TestTable extends TestCase {
	private Table t;

	public TestTable(String n) {
		super(n);
	}

	public void testColumnAccess() {
		String tmp[];
		Table t = new Table("A");

		t.addColumn("a1");
		t.addColumn("a2");
		t.addColumn("a3");
		t.addColumn("a4");
		t.addColumn("a5");

		tmp = t.getColumns();
		assert(tmp.length == 5);
		assert(tmp[0].equals("a1"));
		assert(tmp[1].equals("a2"));
		assert(tmp[2].equals("a3"));
		assert(tmp[3].equals("a4"));
		assert(tmp[4].equals("a5"));

	}

	public void testPrimaryKeyAccess() {
		String tmp[];
		Table t = new Table("A");

		t.addColumn(null, null, "a1", true);
		t.addColumn("a2");
		t.addColumn("a3");
		t.addColumn("a4");
		t.addColumn("a5");
		t.setPrimaryKey("a2");

		tmp = t.getPrimaryKeyColumns();
		assert(tmp.length == 2);
		assert(tmp[0].equals("a1"));
		assert(tmp[1].equals("a2"));

		assert(t.isPrimaryKeyColumn("a1"));
		assert(!t.isPrimaryKeyColumn("a3"));

		try {
			t.setPrimaryKey(null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}

		try {
			t.setPrimaryKey("a6");
			fail("Missing Exception !");
		} catch(IllegalArgumentException e) {
		}

		try {
			t.isPrimaryKeyColumn(null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}
	}

	public void testForeignKeyAccess() {
		String tmp[];
		Table t = new Table("A");

		t.addColumn(null, null, "a1", true);
		t.addColumn("a2");
		t.addColumn("a3");
		t.addColumn("a4");
		t.addColumn("a5");
		t.setForeignKey("a2", "B", "b1");

		tmp = t.getForeignKeyColumns();
		assert(tmp.length == 1);
		assert(tmp[0].equals("a2"));

		assert(t.isForeignKeyColumn("a2"));
		assert(!t.isForeignKeyColumn("a3"));
		assert(t.getForeignTable("a2").equals("B"));
		assert(t.getForeignColumn("a2").equals("b1"));

		try {
			t.setForeignKey(null, null, null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}

		try {
			t.setForeignKey("a6", "B", "b1");
			fail("Missing Exception !");
		} catch(IllegalArgumentException e) {
		}

		try {
			t.isForeignKeyColumn(null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}
	}

	public void testPropertyAccess() {
		String tmp[];
		Table t = new Table("A");

		t.addColumn("att1", "int", "a1", true);
		t.addColumn("att2", "boolean", "a2", false);
		t.addColumn("att3", "String", "a3", false);
		t.addColumn("att3", "String", "a4", false);
		t.addColumn("att4", "long", "a5");

		assert(t.getTableName().equals("A"));

		assert(t.getAttributeColumn("att2").equals("a2"));

		tmp = t.getAttributeColumns("att3");
		assert(tmp.length == 2);
		assert(tmp[0].equals("a3"));
		assert(tmp[1].equals("a4"));

		tmp = t.getAttributeColumns("att1");
		assert(tmp.length == 1);
		assert(tmp[0].equals("a1"));

		tmp = t.getColumns();
		assert(tmp.length == 5);
		assert(tmp[0].equals("a1"));
		assert(tmp[1].equals("a2"));
		assert(tmp[2].equals("a3"));
		assert(tmp[3].equals("a4"));
		assert(tmp[4].equals("a5"));

		assert(t.isColumn("a5"));
		assert(!t.isColumn("a6"));

		assert(t.getColumnAttribute("a2").equals("att2"));

		assert(t.getColumnType("a1").equals("int"));
		assert(t.getColumnType("a2").equals("boolean"));
		assert(t.getColumnType("a3").equals("String"));
		assert(t.getColumnType("a4").equals("String"));

		try {
			t.getAttributeColumn("att3");
			fail("Missing Exception !");
		} catch(IllegalStateException e) {
		}

		try {
			t.getAttributeColumn(null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}

		try {
			t.getAttributeColumns(null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}

		try {
			t.getColumnAttribute(null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}

		try {
			System.err.println(t.getColumnType("a6"));
			fail("Missing Exception !");
		} catch(IllegalArgumentException e) {
		}

		try {
			t.getColumnType(null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}
	}

	public static Test suite() {
		TestSuite t=new TestSuite();

    		t.addTest(new TestTable("testColumnAccess"));
    		t.addTest(new TestTable("testPrimaryKeyAccess"));
    		t.addTest(new TestTable("testForeignKeyAccess"));
    		t.addTest(new TestTable("testPropertyAccess"));

    		return t;
	}
}
