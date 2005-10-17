package tudresden.ocl20.codegen.sql.test;

import java.util.List;
import java.util.Set;

import tudresden.ocl20.codegen.sql.codegen.Guide;
import tudresden.ocl20.codegen.sql.codegen.MappedClass;
import tudresden.ocl20.codegen.sql.orm.Table;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestMappedClass extends TestCase {
	MappedClass mc1;
	MappedClass mc2;
	MappedClass mc3;
	Table t1a, t1b, t2, t3;
	
	public TestMappedClass(String n) {
		super(n);
	}
	
	protected void setUp() {
		t1a = new Table("TableA1");
		t1b = new Table("TableA2");
		t2 = new Table("TableB");
		t3 = new Table("TableC");
		
		Guide g;
		
		t1a.addColumn("a1", "A1", true);
		t1a.addColumn("a2", "A2", false);
		
		t1b.addColumn("a1", "A1", true);
		t1b.addColumn("a3", "A3", false);
		
		t2.addColumn("b1", "B1", true);
				
		t3.addColumn("c1", "C1", true);
		t3.addColumn("c2", "C2", false);
		t3.addColumn("c3", "C3", false);
				
		mc1 = new MappedClass("A");
		mc2 = new MappedClass("B");
		mc3 = new MappedClass("C");
		
		mc1.addTable(t1a);
		mc1.addTable(t1b);
		mc2.addTable(t2);
		mc3.addTable(t3);
		
		mc1.addQuery("operationA");
		mc3.addQuery("operationC");
		
		mc3.addSuperclass("A", mc1);	
		
		mc1.addAssociationEnd(null, mc2);
		mc2.addAssociationEnd(null, mc1);			
		mc3.addAssociationEnd("foo", mc2);
		mc2.addAssociationEnd(null, mc3);
		
		g = new Guide(true);
		g.add("B1", "TableB", "B1");
		g.add("B1", "AB", "A1");
		g.add("A1", "TableA", "A1");
		mc1.addJoinGuide("b", g);
		
		g = new Guide(true);
		g.add("A1", "TableA1", "A1");
		g.add("A1", "AB", "B1");
		g.add("B1", "TableB", "B1");
		mc2.addJoinGuide("a", g);
		
		g = new Guide(true);
		g.add("A1", "TableA2", "A1");
		g.add("A1", "AB", "B1");
		g.add("B1", "TableB", "B1");
		mc2.addJoinGuide("a", g);
		
		g = new Guide(true);	
		g.add("B1", "TableC", "C1");
		mc3.addJoinGuide("foo", g);
		
		g = new Guide(true);	
		g.add("C1", "TableC", "B1");
		mc2.addJoinGuide("c", g);
	}
	
	// simple case	
	public void testCaseA() {
		assertTrue(mc1.getClassName().equals("A"));
				
		assertTrue(mc1.isAttribute("a1"));
		assertTrue(mc1.isAttribute("a2"));
		assertTrue(mc1.isAttribute("a3"));
		
		assertTrue(mc1.isAssociationEnd("b"));
		assertTrue(!mc1.isAssociationEnd("c"));
		
		assertTrue(mc1.isQuery("operationA"));
		assertTrue(!mc1.isQuery("operationC"));
		
		assertTrue(mc1.hasTable("TableA1"));
		assertTrue(mc1.hasTable("TableA2"));
		assertTrue(!mc1.hasTable("TableA3"));
		
		assertTrue(mc1.getTables().size() == 2);
		
		Guide g = mc1.getJoinGuide("b");
		g.next();
		assertTrue(g.getSelect().equals("B1"));
		assertTrue(g.getFrom().equals("TableB"));
		assertTrue(g.getWhere().equals("B1"));
		assertTrue(g.hasMoreSteps());
		g.next();
		assertTrue(g.getSelect().equals("B1"));
		assertTrue(g.getFrom().equals("AB"));
		assertTrue(g.getWhere().equals("A1"));
		assertTrue(g.hasMoreSteps());
		g.next();
		assertTrue(g.getSelect().equals("A1"));
		assertTrue(g.getFrom().equals("TableA"));
		assertTrue(g.getWhere().equals("A1"));
		assertTrue(!g.hasMoreSteps());
		
		assertTrue(mc1.getAssociationEnd("b").getClassName().equals("B"));
		
		try {
			mc1.getAttributeGuide("a1");
			fail("Missing Exception !");
		} catch(IllegalStateException e) {
		}
	
		List<Guide> l = mc1.getAttributeGuides("a1");
		assertTrue(l.size() == 2);
		g = l.get(0);
		g.next();
		assertTrue(g.getSelect().equals("A1"));
		assertTrue(g.getFrom().equals("TableA1"));
		assertTrue(g.getWhere().equals(t1a.getPrimaryKeyRepresentation()));
		assertTrue(!g.hasMoreSteps());
		g = l.get(1);
		g.next();
		assertTrue(g.getSelect().equals("A1"));
		assertTrue(g.getFrom().equals("TableA2"));
		assertTrue(g.getWhere().equals(t1b.getPrimaryKeyRepresentation()));
		assertTrue(!g.hasMoreSteps());						
	}

	// inheritance structures
	public void testCaseB() {
		assertTrue(mc3.getClassName().equals("C"));
		
		assertTrue(mc3.isAttribute("a1"));
		assertTrue(mc3.isAttribute("a2"));
		assertTrue(mc3.isAttribute("a3"));
		assertTrue(mc3.isAttribute("c1"));
		assertTrue(mc3.isAttribute("c2"));
		assertTrue(mc3.isAttribute("c3"));
		
		assertTrue(mc3.isAssociationEnd("b"));
		assertTrue(mc3.isAssociationEnd("foo"));
		assertTrue(!mc3.isAssociationEnd("c"));
		
		assertTrue(mc3.isQuery("operationA"));
		assertTrue(mc3.isQuery("operationC"));
		assertTrue(!mc3.isQuery("operationB"));
		
		assertTrue(mc3.hasTable("TableC"));
		assertTrue(!mc3.hasTable("TableA1"));
		
		assertTrue(mc3.getTables().size() == 1);
		
		Guide g = mc3.getJoinGuide("b");
		g.next();
		assertTrue(g.getSelect().equals("B1"));
		assertTrue(g.getFrom().equals("TableB"));
		assertTrue(g.getWhere().equals("B1"));
		assertTrue(g.hasMoreSteps());
		g.next();
		assertTrue(g.getSelect().equals("B1"));
		assertTrue(g.getFrom().equals("AB"));
		assertTrue(g.getWhere().equals("A1"));
		assertTrue(g.hasMoreSteps());
		g.next();
		assertTrue(g.getSelect().equals("A1"));
		assertTrue(g.getFrom().equals("TableA"));
		assertTrue(g.getWhere().equals("A1"));
		assertTrue(!g.hasMoreSteps());
		
		g = mc3.getJoinGuide("foo");
		g.next();
		assertTrue(g.getSelect().equals("B1"));
		assertTrue(g.getFrom().equals("TableC"));
		assertTrue(g.getWhere().equals("C1"));
		assertTrue(!g.hasMoreSteps());
		
		assertTrue(mc3.getAssociationEnd("b").getClassName().equals("B"));
		assertTrue(mc3.getAssociationEnd("foo").getClassName().equals("B"));
		
		try {
			mc3.getAttributeGuide("a1");
			fail("Missing Exception !");
		} catch(IllegalStateException e) {
		}
	
		List<Guide> l = mc3.getAttributeGuides("a1");
		assertTrue(l.size() == 2);
		g = l.get(0);
		g.next();
		assertTrue(g.getSelect().equals("A1"));
		assertTrue(g.getFrom().equals("TableA1"));
		assertTrue(g.getWhere().equals(t1a.getPrimaryKeyRepresentation()));
		assertTrue(!g.hasMoreSteps());
		g = l.get(1);
		g.next();
		assertTrue(g.getSelect().equals("A1"));
		assertTrue(g.getFrom().equals("TableA2"));
		assertTrue(g.getWhere().equals(t1a.getPrimaryKeyRepresentation()));
		assertTrue(!g.hasMoreSteps());
		
		g = mc3.getAttributeGuide("c1");
		g.next();
		assertTrue(g.getSelect().equals("C1"));
		assertTrue(g.getFrom().equals("TableC"));
		assertTrue(g.getWhere().equals(t3.getPrimaryKeyRepresentation()));
		assertTrue(!g.hasMoreSteps());
		
		g = mc3.getAttributeGuide("c2");
		g.next();
		assertTrue(g.getSelect().equals("C2"));
		assertTrue(g.getFrom().equals("TableC"));
		assertTrue(g.getWhere().equals(t3.getPrimaryKeyRepresentation()));
		assertTrue(!g.hasMoreSteps());
		
		g = mc3.getAttributeGuide("c3");
		g.next();
		assertTrue(g.getSelect().equals("C3"));
		assertTrue(g.getFrom().equals("TableC"));
		assertTrue(g.getWhere().equals(t3.getPrimaryKeyRepresentation()));
		assertTrue(!g.hasMoreSteps());
		
		g = mc3.getAttributeGuide("a2");
		g.next();
		assertTrue(g.getSelect().equals("A2"));
		assertTrue(g.getFrom().equals("TableA1"));
		assertTrue(g.getWhere().equals(t1a.getPrimaryKeyRepresentation()));
		assertTrue(!g.hasMoreSteps());
                
                g = mc3.getJoinGuide("C");
                g.next();
                assertTrue(g.getSelect().equals("C1"));
                assertTrue(g.getFrom().equals("TableC"));
                assertTrue(g.getWhere().equals(""));
	}
	
	// exception handling
	public void testCaseC() {
		try {
			new MappedClass(null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}
		
		try {
			mc2.addTable(null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}
		
		try {
			mc2.addQuery(null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}
	
		try {
			mc2.addAssociationEnd(null,null);	
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}
		
		try {
			mc2.addJoinGuide("foo",null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}
		
		try {
			mc2.getJoinGuides("foo");
			fail("Missing Exception !");
		} catch(IllegalArgumentException e) {
		}
		
		try {
			mc2.getAttributeGuides("b2");
			fail("Missing Exception !");
		} catch(IllegalArgumentException e) {
		}
	}
	
        public void testMetaInfoAccess() {
                Set tmp;
                
                tmp = mc3.attributes();
                assertTrue(tmp.size() == 6);
                assertTrue(tmp.contains("c1"));
                assertTrue(tmp.contains("c2"));
                assertTrue(tmp.contains("c3"));
                assertTrue(tmp.contains("a1"));
                assertTrue(tmp.contains("a2"));
                assertTrue(tmp.contains("a3"));
                
                tmp = mc3.associationEnds();
                assertTrue(tmp.size() == 1);
                assertTrue(tmp.contains("foo"));
                
                tmp = mc3.supertypes();
                assertTrue(tmp.size() == 1);
                assertTrue(tmp.contains("A"));
                
                tmp = mc3.allSupertypes();
                assertTrue(tmp.size() == 1);
                assertTrue(tmp.contains("A"));                                     
        }
        
	public static Test suite() {
		TestSuite t=new TestSuite();
    		
    		t.addTest(new TestMappedClass("testCaseA"));
    		t.addTest(new TestMappedClass("testCaseB"));
    		t.addTest(new TestMappedClass("testCaseC"));
                t.addTest(new TestMappedClass("testMetaInfoAccess"));
	  	
    		return t;
	}
}
