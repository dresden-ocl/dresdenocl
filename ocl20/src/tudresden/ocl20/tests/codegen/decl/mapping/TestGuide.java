/*
 * TestGuide.java
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

package tudresden.ocl20.tests.codegen.decl.mapping;

import tudresden.ocl20.codegen.decl.mapping.Guide;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This test class will tests the class Guide.java of the package
 * tudresden.ocl20.codegen.sql.codegen.Guide
 * 
 * The Guide class holds information about navigation in target models as kind of
 * steps of select-from-where clauses. One guide is dedicated to describe either
 * navigations over association ends or simple attribute access.
 *
 *@see tudresden.ocl20.codegen.decl.mapping.Guide
 */
/*
 * JavaDoc added and changed to jUnit4 by Claas Wilke in 2007
 */
public class TestGuide {

	/**
	 * This Test Case creates a Guide and tests its methods
	 *
	 */
	@Test public void run1() {
		// Create a new Guide g which represents a navigation
		// and has the name 'someAlias'
		Guide g = new Guide(true, "someAlias");
		
		// The Guide g should describe a navigation, should
		// contain zero steps, should have no next step available
		// and should have the alias 'someAlias'
		assertTrue(g.isNavigation());
		assertTrue(g.numberOfSteps() == 0);
		assertTrue(!g.hasNext());
		assertTrue(g.getAlias().equals("someAlias"));
		
		// Change and test the alias of g
		g.setAlias("newAlias");
		assertTrue(g.getAlias().equals("newAlias"));
		
		// Add three Select-From-Where clauses to g
		g.add("PID", "PERSON", "PID");
		g.add("PID", "EMPLOYMENT", "CID");
		g.add("CID", "COMPANY", "CID");
		
		// g should now contain three clauses
		assertTrue(g.numberOfSteps() == 3);
		
		// set the navigation pointer of g to the 
		// beginning of the added clauses
		g.reset();
		// g should have some clauses available
		assertTrue(g.hasNext());
		// check the first clause
		assertTrue(g.getSelect().equals("PID"));
		assertTrue(g.getFrom().equals("PERSON"));
		assertTrue(g.getWhere().equals("PID"));
		assertTrue(g.hasNext());
		// set the pointer to the second query
		g.next();
		// check the second clause
		assertTrue(g.getSelect().equals("PID"));
		assertTrue(g.getFrom().equals("EMPLOYMENT"));
		assertTrue(g.getWhere().equals("CID"));
		assertTrue(g.hasNext());
		// set the pointer to the third query
		g.next();
		// check the third clause
		assertTrue(g.getSelect().equals("CID"));
		assertTrue(g.getFrom().equals("COMPANY"));
		assertTrue(g.getWhere().equals("CID"));
		// g shouldn't contain a fourth clause
		assertTrue(!g.hasNext());
	}
	
	/**
	 * This Test Case creates a Guide and checks its
	 * default values and settings
	 */
	@Test public void run2() {
		// Create a new Guide g which doesn't describe a navigation
		Guide g = new Guide(false);
		
		// g shouldn't describe a navigation and shouldn't
		// contain any Select-From-Where clauses
		assertTrue(!g.isNavigation());
		assertTrue(g.numberOfSteps() == 0);
		
		// Adding a clause with a NULL value as Select part
		// should cause a NullPointerException
		try {
			g.add(null, "something", "something");
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}

		// Getting the Select part of the actual pointer position
		// allthough the guide doesn't contain any clauses
		// should cause an IllegalStateException
		try {
			g.getSelect();
			fail("Missing Exception !");
		} catch(IllegalStateException e) {
		}
	
		// Getting the From part of the actual pointer position
		// allthough the guide doesn't contain any clauses
		// should cause an IllegalStateException
		try {
			g.getFrom();
			fail("Missing Exception !");
		} catch(IllegalStateException e) {
		}
	
		// Getting the Where part of the actual pointer position
		// allthough the guide doesn't contain any clauses
		// should cause an IllegalStateException
		try {
			g.getWhere();
			fail("Missing Exception !");
		} catch(IllegalStateException e) {
		}
	
		// Getting the alias should cause an IllegalStateException
		// if no context alias was specified
		try {
			g.getAlias();
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}
	}
		
}
