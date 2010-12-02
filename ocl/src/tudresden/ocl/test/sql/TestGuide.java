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
import tudresden.ocl.codegen.decl.*;

public class TestGuide extends TestCase {
	
	public TestGuide(String n) {
		super(n);
	}
	
	public void testRun1() {
		Guide g = new Guide(true, "someAlias");
		
		assertTrue(g.isNavigation());
		assertTrue(g.numberOfSteps() == 0);
		assertTrue(!g.hasMoreSteps());
		assertTrue(g.getAlias().equals("someAlias"));
		
		g.setAlias("newAlias");
		assertTrue(g.getAlias().equals("newAlias"));
		
		g.add("PID", "PERSON", "PID");
		g.add("PID", "EMPLOYMENT", "CID");
		g.add("CID", "COMPANY", "CID");
		
		assertTrue(g.numberOfSteps() == 3);
		
		g.reset();
		assertTrue(g.hasMoreSteps());
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
	
	public void testRun2() {
		Guide g = new Guide(false);
		
		assertTrue(!g.isNavigation());
		assertTrue(g.numberOfSteps() == 0);
		
		try {
			g.add(null, "something", "something");
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}
		
		try {
			g.next();
			fail("Missing Exception !");
		} catch(IllegalStateException e) {
		}
		
		try {
			g.getSelect();
			fail("Missing Exception !");
		} catch(IllegalStateException e) {
		}
	
		try {
			g.getFrom();
			fail("Missing Exception !");
		} catch(IllegalStateException e) {
		}
	
		try {
			g.getWhere();
			fail("Missing Exception !");
		} catch(IllegalStateException e) {
		}
	
		try {
			g.getAlias();
			fail("Missing Exception !");
		} catch(NullPointerException e) {
		}
	}
		
	public static Test suite() {
		TestSuite t=new TestSuite();

    		t.addTest(new TestGuide("testRun1"));
    		t.addTest(new TestGuide("testRun2"));

    		return t;
	}
}
