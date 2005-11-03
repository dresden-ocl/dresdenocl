package tudresden.ocl20.codegen.sql.test;

import tudresden.ocl20.codegen.sql.orm.Table;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestTable extends TestCase {

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
		assertTrue(tmp.length == 5);
		assertTrue(tmp[0].equals("a1"));
		assertTrue(tmp[1].equals("a2"));
		assertTrue(tmp[2].equals("a3"));
		assertTrue(tmp[3].equals("a4"));
		assertTrue(tmp[4].equals("a5"));

	}

	public void testPrimaryKeyAccess() {
		String tmp[];
		Table t = new Table("A");

		t.addColumn(null, null, "a1", true);
		t.addColumn("a2");
		t.addColumn("a3");
		t.addColumn("a4");
		t.addColumn("a5");

		assertTrue(t.getPrimaryKeyRepresentation().equals("a1"));
		t.setPrimaryKey("a2");
		assertTrue(t.getPrimaryKeyRepresentation().equals("(a1,a2)"));

		tmp = t.getPrimaryKeyColumns();
		assertTrue(tmp.length == 2);
		assertTrue(tmp[0].equals("a1"));
		assertTrue(tmp[1].equals("a2"));

		assertTrue(t.isPrimaryKeyColumn("a1"));
		assertTrue(!t.isPrimaryKeyColumn("a3"));

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
		t.setForeignKey("a3", "C", "c1");

		tmp = t.getForeignKeyColumns();
		assertTrue(tmp.length == 2);
		assertTrue(tmp[0].equals("a2"));
		assertTrue(tmp[1].equals("a3"));

		assertTrue(t.isForeignKeyColumn("a2"));
		assertTrue(t.isForeignKeyColumn("a3"));
		assertTrue(!t.isForeignKeyColumn("a4"));
		assertTrue(t.getForeignTable("a2").equals("B"));
		assertTrue(t.getForeignColumn("a2").equals("b1"));
		assertTrue(t.getForeignTable("a3").equals("C"));
		assertTrue(t.getForeignColumn("a3").equals("c1"));

                /*
		try {
			t.setForeignKey((String)null, (Table)null, null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}
                */

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

		assertTrue(t.getTableName().equals("A"));

		assertTrue(t.getAttributeColumn("att2").equals("a2"));

		tmp = t.getAttributeColumns("att3");
		assertTrue(tmp.length == 2);
		assertTrue(tmp[0].equals("a3"));
		assertTrue(tmp[1].equals("a4"));

		tmp = t.getAttributeColumns("att1");
		assertTrue(tmp.length == 1);
		assertTrue(tmp[0].equals("a1"));

		tmp = t.getColumns();
		assertTrue(tmp.length == 5);
		assertTrue(tmp[0].equals("a1"));
		assertTrue(tmp[1].equals("a2"));
		assertTrue(tmp[2].equals("a3"));
		assertTrue(tmp[3].equals("a4"));
		assertTrue(tmp[4].equals("a5"));

		assertTrue(t.isColumn("a5"));
		assertTrue(!t.isColumn("a6"));

		assertTrue(t.getColumnAttribute("a2").equals("att2"));

		assertTrue(t.getColumnType("a1").equals("int"));
		assertTrue(t.getColumnType("a2").equals("boolean"));
		assertTrue(t.getColumnType("a3").equals("String"));
		assertTrue(t.getColumnType("a4").equals("String"));

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

        public void testAttributeAccess() {
		Table t = new Table("A");

		t.addColumn(" ", "int", "a1", true);
		t.addColumn("att2", "boolean", "a2", false);
		t.addColumn("att3", "String", "a3", false);
		t.addColumn("att3", "String", "a4", false);
		t.addColumn("att4", "long", "a5");

                assertTrue(t.attributes().size() == 3);
                assertTrue(t.attributes().contains("att2"));
                assertTrue(t.attributes().contains("att3"));
                assertTrue(t.attributes().contains("att4"));
        }

	public static Test suite() {
		TestSuite t=new TestSuite();

    		t.addTest(new TestTable("testColumnAccess"));
    		t.addTest(new TestTable("testPrimaryKeyAccess"));
    		t.addTest(new TestTable("testForeignKeyAccess"));
    		t.addTest(new TestTable("testPropertyAccess"));
                t.addTest(new TestTable("testAttributeAccess"));

    		return t;
	}
}
