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

*/ 
package tudresden.ocl.test.sql;

import junit.framework.*;
import tudresden.ocl.sql.*;
import tudresden.ocl.codegen.decl.*;
import org.xml.sax.SAXException;
import java.io.*;
import java.util.*;

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
		assert(mc1.getClassName().equals("A"));
				
		assert(mc1.isAttribute("a1"));
		assert(mc1.isAttribute("a2"));
		assert(mc1.isAttribute("a3"));
		
		assert(mc1.isAssociationEnd("b"));
		assert(!mc1.isAssociationEnd("c"));
		
		assert(mc1.isQuery("operationA"));
		assert(!mc1.isQuery("operationC"));
		
		assert(mc1.hasTable("TableA1"));
		assert(mc1.hasTable("TableA2"));
		assert(!mc1.hasTable("TableA3"));
		
		assert(mc1.getTables().size() == 2);
		
		Guide g = mc1.getJoinGuide("b");
		g.next();
		assert(g.getSelect().equals("B1"));
		assert(g.getFrom().equals("TableB"));
		assert(g.getWhere().equals("B1"));
		assert(g.hasMoreSteps());
		g.next();
		assert(g.getSelect().equals("B1"));
		assert(g.getFrom().equals("AB"));
		assert(g.getWhere().equals("A1"));
		assert(g.hasMoreSteps());
		g.next();
		assert(g.getSelect().equals("A1"));
		assert(g.getFrom().equals("TableA"));
		assert(g.getWhere().equals("A1"));
		assert(!g.hasMoreSteps());
		
		assert(mc1.getAssociationEnd("b").getClassName().equals("B"));
		
		try {
			mc1.getAttributeGuide("a1");
			fail("Missing Exception !");
		} catch(IllegalStateException e) {
		}
	
		List l = mc1.getAttributeGuides("a1");
		assert(l.size() == 2);
		g = (Guide)l.get(0);
		g.next();
		assert(g.getSelect().equals("A1"));
		assert(g.getFrom().equals("TableA1"));
		assert(g.getWhere().equals(t1a.getPrimaryKeyRepresentation()));
		assert(!g.hasMoreSteps());
		g = (Guide)l.get(1);
		g.next();
		assert(g.getSelect().equals("A1"));
		assert(g.getFrom().equals("TableA2"));
		assert(g.getWhere().equals(t1b.getPrimaryKeyRepresentation()));
		assert(!g.hasMoreSteps());						
	}

	// inheritance structures
	public void testCaseB() {
		assert(mc3.getClassName().equals("C"));
		
		assert(mc3.isAttribute("a1"));
		assert(mc3.isAttribute("a2"));
		assert(mc3.isAttribute("a3"));
		assert(mc3.isAttribute("c1"));
		assert(mc3.isAttribute("c2"));
		assert(mc3.isAttribute("c3"));
		
		assert(mc3.isAssociationEnd("b"));
		assert(mc3.isAssociationEnd("foo"));
		assert(!mc3.isAssociationEnd("c"));
		
		assert(mc3.isQuery("operationA"));
		assert(mc3.isQuery("operationC"));
		assert(!mc3.isQuery("operationB"));
		
		assert(mc3.hasTable("TableC"));
		assert(!mc3.hasTable("TableA1"));
		
		assert(mc3.getTables().size() == 1);
		
		Guide g = mc3.getJoinGuide("b");
		g.next();
		assert(g.getSelect().equals("B1"));
		assert(g.getFrom().equals("TableB"));
		assert(g.getWhere().equals("B1"));
		assert(g.hasMoreSteps());
		g.next();
		assert(g.getSelect().equals("B1"));
		assert(g.getFrom().equals("AB"));
		assert(g.getWhere().equals("A1"));
		assert(g.hasMoreSteps());
		g.next();
		assert(g.getSelect().equals("A1"));
		assert(g.getFrom().equals("TableA"));
		assert(g.getWhere().equals("A1"));
		assert(!g.hasMoreSteps());
		
		g = mc3.getJoinGuide("foo");
		g.next();
		assert(g.getSelect().equals("B1"));
		assert(g.getFrom().equals("TableC"));
		assert(g.getWhere().equals("C1"));
		assert(!g.hasMoreSteps());
		
		assert(mc3.getAssociationEnd("b").getClassName().equals("B"));
		assert(mc3.getAssociationEnd("foo").getClassName().equals("B"));
		
		try {
			mc3.getAttributeGuide("a1");
			fail("Missing Exception !");
		} catch(IllegalStateException e) {
		}
	
		List l = mc3.getAttributeGuides("a1");
		assert(l.size() == 2);
		g = (Guide)l.get(0);
		g.next();
		assert(g.getSelect().equals("A1"));
		assert(g.getFrom().equals("TableA1"));
		assert(g.getWhere().equals(t1a.getPrimaryKeyRepresentation()));
		assert(!g.hasMoreSteps());
		g = (Guide)l.get(1);
		g.next();
		assert(g.getSelect().equals("A1"));
		assert(g.getFrom().equals("TableA2"));
		assert(g.getWhere().equals(t1a.getPrimaryKeyRepresentation()));
		assert(!g.hasMoreSteps());
		
		g = mc3.getAttributeGuide("c1");
		g.next();
		assert(g.getSelect().equals("C1"));
		assert(g.getFrom().equals("TableC"));
		assert(g.getWhere().equals(t3.getPrimaryKeyRepresentation()));
		assert(!g.hasMoreSteps());
		
		g = mc3.getAttributeGuide("c2");
		g.next();
		assert(g.getSelect().equals("C2"));
		assert(g.getFrom().equals("TableC"));
		assert(g.getWhere().equals(t3.getPrimaryKeyRepresentation()));
		assert(!g.hasMoreSteps());
		
		g = mc3.getAttributeGuide("c3");
		g.next();
		assert(g.getSelect().equals("C3"));
		assert(g.getFrom().equals("TableC"));
		assert(g.getWhere().equals(t3.getPrimaryKeyRepresentation()));
		assert(!g.hasMoreSteps());
		
		g = mc3.getAttributeGuide("a2");
		g.next();
		assert(g.getSelect().equals("A2"));
		assert(g.getFrom().equals("TableA1"));
		assert(g.getWhere().equals(t1a.getPrimaryKeyRepresentation()));
		assert(!g.hasMoreSteps());
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
	
	public static Test suite() {
		TestSuite t=new TestSuite();
    		
    		t.addTest(new TestMappedClass("testCaseA"));
    		t.addTest(new TestMappedClass("testCaseB"));
    		t.addTest(new TestMappedClass("testCaseC"));
	  	
    		return t;
	}
}