/*
 * TestMappedClass.java
 * 
 * Copyright (c) 2000 Sten Loecher
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

import java.util.List;
import java.util.Set;
import tudresden.ocl20.codegen.sql.codegen.Guide;
import tudresden.ocl20.codegen.sql.codegen.MappedClass;
import tudresden.ocl20.codegen.sql.orm.Table;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * This class Provides some quite simple test cases for creating object relational mappings
 * between classes and sql objects like tables or columns.
 * <b>Attention:</b> Some of the tested methods and classes are depricated by now.
 *
 *@see tudresden.ocl20.codegen.sql.codegen.MappedClass
 */
/*
 * JavaDoc and comments added and changed to jUnit4 by Claas Wilke in 2007
 */
@SuppressWarnings("deprecation")
public class TestMappedClass {
	MappedClass mc1;
	MappedClass mc2;
	MappedClass mc3;
	Table t1a, t1b, t2, t3;
	
	/**
	 * Prepare the instances which are needed to run the tests implemented in 
	 * this class.
	 */
	@Before public void setUp() {
		// Create four tables and a Guide
		t1a = new Table("TableA1");
		t1b = new Table("TableA2");
		t2 = new Table("TableB");
		t3 = new Table("TableC");
		
		Guide g;
		
		// Add two columns to table t1a
		// a1 is primary key
		t1a.addColumn("a1", "A1", true);
		t1a.addColumn("a2", "A2", false);
		
		// Add two columns to table t1b
		// a1 is primary key
		t1b.addColumn("a1", "A1", true);
		t1b.addColumn("a3", "A3", false);
		
		// Add one column to table t2
		// b1 is primary key
		t2.addColumn("b1", "B1", true);
				
		// Add three columns to table c1
		// c1 is primary key
		t3.addColumn("c1", "C1", true);
		t3.addColumn("c2", "C2", false);
		t3.addColumn("c3", "C3", false);
				
		// Create mappings
		mc1 = new MappedClass("A");
		mc2 = new MappedClass("B");
		mc3 = new MappedClass("C");
		
		// Mapping between tables t1a and t1b
		mc1.addTable(t1a);
		mc1.addTable(t1b);
		mc2.addTable(t2);
		mc3.addTable(t3);
		
		// Add a Query to mapping mc1 and mc3
		mc1.addQuery("operationA");
		mc3.addQuery("operationC");
		
		// Set mc1 as superclass of mc3
		mc3.addSuperclass("A", mc1);	
		
		// Add an association between mc1 and mc2 without a rolename
		mc1.addAssociationEnd(null, mc2);
		mc2.addAssociationEnd(null, mc1);
		// Add an assosication 'foo' between mc2 and mc3
		mc3.addAssociationEnd("foo", mc2);
		mc2.addAssociationEnd(null, mc3);
		
		// Create a Guide with three select-from-where clauses
		g = new Guide(true);
		g.add("B1", "TableB", "B1");
		g.add("B1", "AB", "A1");
		g.add("A1", "TableA", "A1");
		// Add the Guide to the mappedClass mc1 and the assiciation end of 'b'
		mc1.addJoinGuide("b", g);
		
		// Create a Guide with three select-from-where clauses
		g = new Guide(true);
		g.add("A1", "TableA1", "A1");
		g.add("A1", "AB", "B1");
		g.add("B1", "TableB", "B1");
		// Add the Guide to the mappedClass mc2 and the assiciation end of 'a'
		mc2.addJoinGuide("a", g);
		
		// Create a Guide with three select-from-where clauses
		g = new Guide(true);
		g.add("A1", "TableA2", "A1");
		g.add("A1", "AB", "B1");
		g.add("B1", "TableB", "B1");
		// Add the Guide to the mappedClass mc2 and the assiciation end of 'a'
		mc2.addJoinGuide("a", g);
		
		// Create a Guide with one select-from-where clause
		g = new Guide(true);	
		g.add("B1", "TableC", "C1");
		// Add the Guide to the mappedClass mc3 and the assiciation end of 'foo'
		mc3.addJoinGuide("foo", g);
		
		// Create a Guide with one select-from-where clause
		g = new Guide(true);	
		g.add("C1", "TableC", "B1");
		// Add the Guide to the mappedClass mc3 and the assiciation end of 'g'
		mc2.addJoinGuide("c", g);
	}
	
	/**
	 * A testCase that checks some settings of the MappedClass mc1 which should be true
	 * after the setUp() method.
	 *
	 */
	@Test public void testCaseA() {
		assertTrue(mc1.getClassName().equals("A"));
		
		// Check attributes of mc1
		assertTrue(mc1.isAttribute("a1"));
		assertTrue(mc1.isAttribute("a2"));
		assertTrue(mc1.isAttribute("a3"));
		
		// Check associations of mc1
		assertTrue(mc1.isAssociationEnd("b"));
		assertTrue(!mc1.isAssociationEnd("c"));
		
		// Check queries of mc1
		assertTrue(mc1.isQuery("operationA"));
		assertTrue(!mc1.isQuery("operationC"));
		
		// Check tables of mc1
		assertTrue(mc1.hasTable("TableA1"));
		assertTrue(mc1.hasTable("TableA2"));
		assertTrue(!mc1.hasTable("TableA3"));
		
		assertTrue(mc1.getTables().size() == 2);
		
		// Test the Guide added to mc1
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
		
		// Check the class name of the associationEnd 'b'
		assertTrue(mc1.getAssociationEnd("b").getClassName().equals("B"));
		
		// Should cause a IllegalStateException because mc1 has more than one Guide
		try {
			mc1.getAttributeGuide("a1");
			fail("Missing Exception !");
		} catch(IllegalStateException e) {
		}
	
		// Get a List of all Guides added to mc1
		List<Guide> l = mc1.getAttributeGuides("a1");
		// getAttriubuteGuides must return two Guides
		assertTrue(l.size() == 2);
		// Test the content of the Guides
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

	/**
	 * A testCase that checks the inheritance structures of the MappedClass 
	 * mc3 which was created in the setUp() method.	 *
	 */
	@Test public void testCaseB() {
		// Check name of mc3
		assertTrue(mc3.getClassName().equals("C"));
		
		// Check attributes of mc3
		assertTrue(mc3.isAttribute("a1"));
		assertTrue(mc3.isAttribute("a2"));
		assertTrue(mc3.isAttribute("a3"));
		assertTrue(mc3.isAttribute("c1"));
		assertTrue(mc3.isAttribute("c2"));
		assertTrue(mc3.isAttribute("c3"));
		
		// Check assiciations of mc3
		assertTrue(mc3.isAssociationEnd("b"));
		assertTrue(mc3.isAssociationEnd("foo"));
		assertTrue(!mc3.isAssociationEnd("c"));
		
		// Check queries of mc3
		assertTrue(mc3.isQuery("operationA"));
		assertTrue(mc3.isQuery("operationC"));
		assertTrue(!mc3.isQuery("operationB"));
		
		// Check tables of mc3
		assertTrue(mc3.hasTable("TableC"));
		assertTrue(!mc3.hasTable("TableA1"));	
		assertTrue(mc3.getTables().size() == 1);
		
		// Get the JoinGuide 'b' ant check the 
		// SelectFromWhere statements of mc3
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
		
		// Get the JoinGuide 'foo' ant check the 
		// SelectFromWhere statements of mc3
		g = mc3.getJoinGuide("foo");
		g.next();
		assertTrue(g.getSelect().equals("B1"));
		assertTrue(g.getFrom().equals("TableC"));
		assertTrue(g.getWhere().equals("C1"));
		assertTrue(!g.hasMoreSteps());
		
		// Check that the AssiciationEnd 'b' leads to mc2 which has the name 'B'
		assertTrue(mc3.getAssociationEnd("b").getClassName().equals("B"));
		// Check that the AssiciationEnd 'foo' leads to mc2 which has the name 'B'
		assertTrue(mc3.getAssociationEnd("foo").getClassName().equals("B"));
		
		// Should cause a IllegalStateException because mc3 has more than one Guide
		try {
			mc3.getAttributeGuide("a1");
			fail("Missing Exception !");
		} catch(IllegalStateException e) {
		}
	
		// Get a List of all Guides added to mc3
		List<Guide> l = mc3.getAttributeGuides("a1");
		// getAttriubuteGuides must return two Guides
		assertTrue(l.size() == 2);
		// Test the content of the Guides
		// And check SelectFromWhere Statements
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

	/**
	 * A TestCase which checks the exception handling of MappedClasses
	 */
	@Test public void testCaseC() {
		
		// Create a MappedClass without a name should cause an NullPoiterException
		try {
			new MappedClass(null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}
		
		// Add a NULL value as a table to a MappedClass should cause an NullPoiterException
		try {
			mc2.addTable(null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}
		
		// Add a NULL value as a query to a MappedClass should cause an NullPoiterException
		try {
			mc2.addQuery(null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}
	
		// Add a NULL value as an AssiociationEnd to a MappedClass
		// should cause an NullPoiterException
		try {
			mc2.addAssociationEnd(null,null);	
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}
		
		// Add a Null value as a JoinGuide to a MappedClass
		// should cause an NullPoiterException
		try {
			mc2.addJoinGuide("foo",null);
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}
		
		// Get a JoinGuide from a MappedClass which does not exist
		// should cause an IllegalStatementException
		try {
			mc2.getJoinGuides("foo");
			fail("Missing Exception !");
		} catch(IllegalArgumentException e) {
		}
		
		// Get an AttributeGuide from a MappedClass which does not exist
		// should cause an IllegalStatementException
		try {
			mc2.getAttributeGuides("b2");
			fail("Missing Exception !");
		} catch(IllegalArgumentException e) {
		}
	}
	
	/**
	 * This TestCase checks all the attribute names added to
	 * the MappedClass mc3 created in the setUp method
	 */
    @SuppressWarnings("unchecked")
	@Test public void testMetaInfoAccess() {
            Set tmp;
            
            // Get a Set which contains all attributes of mc3
            tmp = mc3.attributes();
            
            // mc3 must have six attributes
            assertTrue(tmp.size() == 6);
            
            // Check the attribute names
            assertTrue(tmp.contains("c1"));
            assertTrue(tmp.contains("c2"));
            assertTrue(tmp.contains("c3"));
            assertTrue(tmp.contains("a1"));
            assertTrue(tmp.contains("a2"));
            assertTrue(tmp.contains("a3"));
            
            // mc3 must have one assiciationEnd which is called 'foo'
            tmp = mc3.associationEnds();
            assertTrue(tmp.size() == 1);
            assertTrue(tmp.contains("foo"));
            
            // mc3 must have one supertype which is called 'A' (mc1)
            tmp = mc3.supertypes();
            assertTrue(tmp.size() == 1);
            assertTrue(tmp.contains("A"));
            
            // mc3 must habe only one supertype
            tmp = mc3.allSupertypes();
            assertTrue(tmp.size() == 1);
            assertTrue(tmp.contains("A"));                                     
    }
        
}
