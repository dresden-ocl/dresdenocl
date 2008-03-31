/*
 * TestGuide.java
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

import tudresden.ocl20.codegen.sql.codegen.Guide;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class Provides some quite simple test cases for creating object relational mappings.
 * <b>Attention:</b> Some of the tested methods and classes are depricated by now.
 *
 *@see tudresden.ocl20.codegen.sql.codegen.Guide
 */
/*
 * JavaDoc added and changed to jUnit4 by Claas Wilke in 2007
 */
@SuppressWarnings("deprecation")
public class TestGuide {

	/**
	 * This TestRun will test the Guide class with some default values.
	 * 
	 * @see For details tudresden.ocl20.codegen.sql.codegen;
	 */
	@Test public void run1() {
		Guide g = new Guide(true, "someAlias");
		
		/* Test some initial values of the guide */
		assertTrue(g.isNavigation());
		assertTrue(g.numberOfSteps() == 0);
		assertTrue(!g.hasMoreSteps());
		assertTrue(g.getAlias().equals("someAlias"));
		
		/* Set and test a new Alias */
		g.setAlias("newAlias");
		assertTrue(g.getAlias().equals("newAlias"));
		
		/* Add some select-from-where clauese to the guide */
		g.add("PID", "PERSON", "PID");
		g.add("PID", "EMPLOYMENT", "CID");
		g.add("CID", "COMPANY", "CID");
		
		/* Test values after adding the clauses */
		assertTrue(g.numberOfSteps() == 3);
		
		/* Test reset of navigation pointer */
		g.reset();
		assertTrue(g.hasMoreSteps());
		
		/* Test the navigation pointer an the next method */
		g.next();
		assertTrue(g.getSelect().equals("PID"));
		assertTrue(g.getFrom().equals("PERSON"));
		assertTrue(g.getWhere().equals("PID"));
		assertTrue(g.hasMoreSteps());
		g.next();
		assertTrue(g.getSelect().equals("PID"));
		assertTrue(g.getFrom().equals("EMPLOYMENT"));
		assertTrue(g.getWhere().equals("CID"));
		assertTrue(g.hasMoreSteps());
		g.next();
		assertTrue(g.getSelect().equals("CID"));
		assertTrue(g.getFrom().equals("COMPANY"));
		assertTrue(g.getWhere().equals("CID"));
		assertTrue(!g.hasMoreSteps());
	}
	
	/**
	 * This TestRun will test the Guide class with NULL values.
	 * 
	 * @see For details tudresden.ocl20.codegen.sql.codegen;
	 */
	@Test public void run2() {
		/* Create a Guide with a NULL value as context alias */
		Guide g = new Guide(false);
		
		assertTrue(!g.isNavigation());
		assertTrue(g.numberOfSteps() == 0);
		
		/* Try to add a select-from-where clause with a NULL value */
		try {
			g.add(null, "something", "something");
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}
		
		/* Try to move the navigate pointer, 
		 * although there are no clauses added */
		try {
			g.next();
			fail("Missing Exception !");
		} catch(IllegalStateException e) {
		}
		
		/* Try to get the 'select' part of the selected clause, allthough 
		 * there are no clauses added */		
		try {
			g.getSelect();
			fail("Missing Exception !");
		} catch(IllegalStateException e) {
		}
	
		/* Try to get the 'from' part of the selected clause, allthough 
		 * there are no clauses added */		
		try {
			g.getFrom();
			fail("Missing Exception !");
		} catch(IllegalStateException e) {
		}
	
		/* Try to get the 'where' part of the selected clause, allthough 
		 * there are no clauses added */		
		try {
			g.getWhere();
			fail("Missing Exception !");
		} catch(IllegalStateException e) {
		}
	
		/* Try to get the alias of the context table, although the
		 * alias is a NULL value */
		try {
			g.getAlias();
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}
	}
		
}
