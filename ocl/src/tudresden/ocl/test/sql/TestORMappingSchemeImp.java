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
import tudresden.ocl.check.types.*;
import tudresden.ocl.check.types.xmifacade.*;
import java.io.*;
import java.util.*;

public class TestORMappingSchemeImp extends TestCase {
	Model theModel;

	public TestORMappingSchemeImp(String n) {
		super(n);
	}
	
	protected void setUp() {
		theModel = new Model("test model");
		theModel.setRoughMode(true);
		
		List pp = new ArrayList();
		pp.add("test_model");		
		ModelClass a = new ModelClass(pp, "A");
		ModelClass b = new ModelClass(pp, "B");
		ModelClass c = new ModelClass(pp, "C");
		ModelClass d = new ModelClass(pp, "D");
		ModelClass e = new ModelClass(pp, "E");
		ModelClass f = new ModelClass(pp, "F");
		
		a.addAttribute(new ModelAttribute("a1", Basic.INTEGER));
		a.addAttribute(new ModelAttribute("a2", Basic.BOOLEAN));
		a.addAttribute(new ModelAttribute("a3", Basic.STRING));
		
		b.addAttribute(new ModelAttribute("b1", Basic.INTEGER));
		b.addAttribute(new ModelAttribute("b2", Basic.BOOLEAN));
		b.addAttribute(new ModelAttribute("b3", Basic.STRING));
		
		c.addAttribute(new ModelAttribute("c1", Basic.INTEGER));
		c.addAttribute(new ModelAttribute("c2", Basic.BOOLEAN));
		c.addAttribute(new ModelAttribute("c3", Basic.STRING));
		c.addAttribute(new ModelAttribute("c4", f));
		
		d.addAttribute(new ModelAttribute("d1", Basic.INTEGER));
		
		e.addAttribute(new ModelAttribute("e1", Basic.STRING));
		
		f.addAttribute(new ModelAttribute("f1", Basic.INTEGER));
		f.addAttribute(new ModelAttribute("f2", Basic.BOOLEAN));
		f.addAttribute(new ModelAttribute("f3", Basic.STRING));
		
		d.addDirectSupertype(b);
		e.addDirectSupertype(b);
		
		Type params[] = {};
		b.addOperation(new ModelOperation("operationB", params, Basic.BOOLEAN, true));
		
		theModel.putClassifier(a);
		theModel.putClassifier(b);
		theModel.putClassifier(c);
		theModel.putClassifier(d);
		theModel.putClassifier(e);
		theModel.putClassifier(f);
		
		ModelAssociation ma;
		
		ma = new ModelAssociation(null);
		ma.addEnd(new ModelAssociationEnd("a1",a,"1",false,null));
		ma.addEnd(new ModelAssociationEnd("b1",b,"1..*",false,null));
		theModel.putAssociation(ma);
		
		ma = new ModelAssociation(null);
		ma.addEnd(new ModelAssociationEnd("a2",a,"0..*",false,null));
		ma.addEnd(new ModelAssociationEnd("b2",b,"0..*",false,null));
		theModel.putAssociation(ma);
		
		ma = new ModelAssociation(null);
		ma.addEnd(new ModelAssociationEnd("ra1",a,"1",false,null));
		ma.addEnd(new ModelAssociationEnd("ra2",a,"1",false,null));
		theModel.putAssociation(ma);
		
		ma = new ModelAssociation(null);
		ma.addEnd(new ModelAssociationEnd("a",a,"1",false,null));
		ma.addEnd(new ModelAssociationEnd("c",c,"1..*",false,null));
		theModel.putAssociation(ma);
		
		theModel.determineAllSupertypes();		
	}	

	// uses the default mapping of ORMappingImp
	public void testCaseA() {
		ORMappingScheme theScheme = new ORMappingSchemeImp(new ORMappingImp(theModel));
		MappedClass a, b, c, d, e, f;
		Table ta, tb, tc;
		Guide guide;
		
		assertTrue((a = theScheme.getMappedClass("A")) != null);	
		assertTrue((b = theScheme.getMappedClass("B")) != null);	
		assertTrue((c = theScheme.getMappedClass("C")) != null);	
		assertTrue((d = theScheme.getMappedClass("D")) != null);	
		assertTrue((e = theScheme.getMappedClass("E")) != null);	
		assertTrue((f = theScheme.getMappedClass("F")) != null);	
		
		assertTrue(a.getClassName().equals("A"));
		assertTrue(b.getClassName().equals("B"));
		assertTrue(c.getClassName().equals("C"));
		assertTrue(d.getClassName().equals("D"));
		assertTrue(e.getClassName().equals("E"));
		assertTrue(f.getClassName().equals("F"));
		
		assertTrue(a.hasTable("A"));
		assertTrue(b.hasTable("B"));
		assertTrue(c.hasTable("C"));
		assertTrue(d.hasTable("D"));
		assertTrue(e.hasTable("E"));
		assertTrue(f.hasTable("F"));
		
		assertTrue(a.getTables().size() == 1);
		assertTrue(b.getTables().size() == 1);
		assertTrue(c.getTables().size() == 1);
		assertTrue(d.getTables().size() == 1);
		assertTrue(e.getTables().size() == 1);
		assertTrue(f.getTables().size() == 1);
		
		assertTrue(b.isQuery("operationB"));
		assertTrue(d.isQuery("operationB"));
		assertTrue(e.isQuery("operationB"));
		assertTrue(!a.isQuery("operationB"));
		
		assertTrue(a.isAssociationEnd("ra1"));
		assertTrue(a.isAssociationEnd("ra2"));
		assertTrue(a.isAssociationEnd("b1"));
		assertTrue(a.isAssociationEnd("b2"));
		assertTrue(a.isAssociationEnd("c"));
		assertTrue(!a.isAssociationEnd("b"));
		
		assertTrue(b.isAssociationEnd("a1"));
		assertTrue(b.isAssociationEnd("a2"));
		
		assertTrue(c.isAssociationEnd("a"));
		assertTrue(c.isAssociationEnd("c4"));
		assertTrue(!c.isAssociationEnd("ra1"));
		assertTrue(!c.isAssociationEnd("f"));
		
		assertTrue(a.isAttribute("a1"));
		assertTrue(a.isAttribute("a2"));
		assertTrue(a.isAttribute("a3"));
		assertTrue(!a.isAttribute("a4"));
		assertTrue(!a.isAttribute("b1"));
		
		assertTrue(b.isAttribute("b1"));
		assertTrue(b.isAttribute("b2"));
		assertTrue(b.isAttribute("b3"));
		
		assertTrue(c.isAttribute("c1"));
		assertTrue(c.isAttribute("c2"));
		assertTrue(c.isAttribute("c3"));
		assertTrue(!c.isAttribute("c4"));
		
		assertTrue(d.isAttribute("d1"));
		assertTrue(d.isAttribute("b1"));
		assertTrue(d.isAttribute("b2"));
		assertTrue(d.isAttribute("b3"));
		
		assertTrue(e.isAttribute("e1"));
		assertTrue(e.isAttribute("b1"));
		assertTrue(e.isAttribute("b2"));
		assertTrue(e.isAttribute("b3"));
		
		assertTrue(f.isAttribute("f1"));
		assertTrue(f.isAttribute("f2"));
		assertTrue(f.isAttribute("f3"));
		
		assertTrue(a.getAssociationEnd("c") == c);
		assertTrue(a.getAssociationEnd("ra1") == a);
		assertTrue(a.getAssociationEnd("ra2") == a);
		assertTrue(a.getAssociationEnd("b1") == b);
		assertTrue(a.getAssociationEnd("b2") == b);
		
		assertTrue(b.getAssociationEnd("a1") == a);
		assertTrue(b.getAssociationEnd("a2") == a);
		
		assertTrue(c.getAssociationEnd("a") == a);
		assertTrue(c.getAssociationEnd("c4") == f);
		
		assertTrue(d.getAssociationEnd("a1") == a);
		assertTrue(d.getAssociationEnd("a2") == a);
		
		assertTrue(e.getAssociationEnd("a1") == a);
		assertTrue(e.getAssociationEnd("a2") == a);
		
		guide = a.getAttributeGuide("a1");
		guide.next();
		assertTrue(guide.getSelect().equals("A1"));
		assertTrue(guide.getFrom().equals("A"));
		
		assertTrue(!guide.hasMoreSteps());
		
		guide = a.getAttributeGuide("a2");
		guide.next();
		assertTrue(guide.getSelect().equals("A2"));
		assertTrue(guide.getFrom().equals("A"));
		
		assertTrue(!guide.hasMoreSteps());
		
		guide = a.getAttributeGuide("a3");
		guide.next();
		assertTrue(guide.getSelect().equals("A3"));
		assertTrue(guide.getFrom().equals("A"));
		
		assertTrue(!guide.hasMoreSteps());
		
		guide = b.getAttributeGuide("b1");
		guide.next();
		assertTrue(guide.getSelect().equals("B1"));
		assertTrue(guide.getFrom().equals("B"));
		
		assertTrue(!guide.hasMoreSteps());
		
		guide = b.getAttributeGuide("b2");
		guide.next();
		assertTrue(guide.getSelect().equals("B2"));
		assertTrue(guide.getFrom().equals("B"));
		
		assertTrue(!guide.hasMoreSteps());
		
		guide = b.getAttributeGuide("b3");
		guide.next();
		assertTrue(guide.getSelect().equals("B3"));
		assertTrue(guide.getFrom().equals("B"));
		
		assertTrue(!guide.hasMoreSteps());
		
		guide = c.getAttributeGuide("c1");
		guide.next();
		assertTrue(guide.getSelect().equals("C1"));
		assertTrue(guide.getFrom().equals("C"));
		
		assertTrue(!guide.hasMoreSteps());
		
		guide = c.getAttributeGuide("c2");
		guide.next();
		assertTrue(guide.getSelect().equals("C2"));
		assertTrue(guide.getFrom().equals("C"));
		
		assertTrue(!guide.hasMoreSteps());
		
		guide = c.getAttributeGuide("c3");
		guide.next();
		assertTrue(guide.getSelect().equals("C3"));
		assertTrue(guide.getFrom().equals("C"));
		
		assertTrue(!guide.hasMoreSteps());
		
		guide = d.getAttributeGuide("d1");
		guide.next();
		assertTrue(guide.getSelect().equals("D1"));
		assertTrue(guide.getFrom().equals("D"));
		
		assertTrue(!guide.hasMoreSteps());
		
		guide = d.getAttributeGuide("b1");
		guide.next();
		assertTrue(guide.getSelect().equals("B1"));
		assertTrue(guide.getFrom().equals("B"));
		
		assertTrue(!guide.hasMoreSteps());
		
		guide = d.getAttributeGuide("b2");
		guide.next();
		assertTrue(guide.getSelect().equals("B2"));
		assertTrue(guide.getFrom().equals("B"));
		
		assertTrue(!guide.hasMoreSteps());
		
		guide = d.getAttributeGuide("b3");
		guide.next();
		assertTrue(guide.getSelect().equals("B3"));
		assertTrue(guide.getFrom().equals("B"));
		
		assertTrue(!guide.hasMoreSteps());
		
		guide = e.getAttributeGuide("e1");
		guide.next();
		assertTrue(guide.getSelect().equals("E1"));
		assertTrue(guide.getFrom().equals("E"));
		
		assertTrue(!guide.hasMoreSteps());
		
		guide = e.getAttributeGuide("b1");
		guide.next();
		assertTrue(guide.getSelect().equals("B1"));
		assertTrue(guide.getFrom().equals("B"));
		
		assertTrue(!guide.hasMoreSteps());
		
		guide = e.getAttributeGuide("b2");
		guide.next();
		assertTrue(guide.getSelect().equals("B2"));
		assertTrue(guide.getFrom().equals("B"));
		
		assertTrue(!guide.hasMoreSteps());
		
		guide = e.getAttributeGuide("b3");
		guide.next();
		assertTrue(guide.getSelect().equals("B3"));
		assertTrue(guide.getFrom().equals("B"));
		
		assertTrue(!guide.hasMoreSteps());
		
		guide = f.getAttributeGuide("f1");
		guide.next();
		assertTrue(guide.getSelect().equals("F1"));
		assertTrue(guide.getFrom().equals("F"));
		
		assertTrue(!guide.hasMoreSteps());
		
		guide = f.getAttributeGuide("f2");
		guide.next();
		assertTrue(guide.getSelect().equals("F2"));
		assertTrue(guide.getFrom().equals("F"));
		
		assertTrue(!guide.hasMoreSteps());
		
		guide = f.getAttributeGuide("f3");
		guide.next();
		assertTrue(guide.getSelect().equals("F3"));
		assertTrue(guide.getFrom().equals("F"));
		
		assertTrue(!guide.hasMoreSteps());
		
		ta = (Table)a.getTables().get(0);
		tb = (Table)b.getTables().get(0);
		tc = (Table)c.getTables().get(0);
				
		guide = a.getJoinGuide("c");
		guide.next();
		assertTrue(tc.isPrimaryKeyColumn(guide.getSelect()));
		assertTrue(tc.getTableName().equals(guide.getFrom()));
		assertTrue(tc.isForeignKeyColumn(guide.getWhere()));
		guide.next();
		assertTrue(ta.isPrimaryKeyColumn(guide.getSelect()));
		assertTrue(ta.getTableName().equals(guide.getFrom()));
		assertTrue(ta.isPrimaryKeyColumn(guide.getWhere()));
		assertTrue(!guide.hasMoreSteps());
		
		guide = a.getJoinGuide("b2");
		guide.next();
		assertTrue(tb.isPrimaryKeyColumn(guide.getSelect()));
		assertTrue(tb.getTableName().equals(guide.getFrom()));
		assertTrue(tb.isPrimaryKeyColumn(guide.getWhere()));
		guide.next();
		guide.next();
		assertTrue(ta.isPrimaryKeyColumn(guide.getSelect()));
		assertTrue(ta.getTableName().equals(guide.getFrom()));
		assertTrue(ta.isPrimaryKeyColumn(guide.getWhere()));
		assertTrue(!guide.hasMoreSteps());	
		
		assertTrue(a.getJoinGuide("ra1") != null);		
		assertTrue(a.getJoinGuide("ra2") != null);	
		assertTrue(a.getJoinGuide("b1") != null);	
		
		assertTrue(c.getJoinGuide("a") != null);	
		assertTrue(c.getJoinGuide("c4") != null);	
		
		assertTrue(b.getJoinGuide("a1") != null);	
		assertTrue(b.getJoinGuide("a2") != null);			
	}
	
	// check of more than one table per MappedClass object (no full check --> only table consistency)
	public void testCaseB() {
		ORMappingScheme theScheme = new ORMappingSchemeImp(new ORMappingImp(theModel, 1, 1, "int", false));
		MappedClass a, b, c, d, e, f;
		
		assertTrue((a = theScheme.getMappedClass("A")) != null);	
		assertTrue((b = theScheme.getMappedClass("B")) != null);	
		assertTrue((c = theScheme.getMappedClass("C")) != null);	
		assertTrue((d = theScheme.getMappedClass("D")) != null);	
		assertTrue((e = theScheme.getMappedClass("E")) != null);	
		assertTrue((f = theScheme.getMappedClass("F")) != null);	
		
		assertTrue(a.getClassName().equals("A"));
		assertTrue(b.getClassName().equals("B"));
		assertTrue(c.getClassName().equals("C"));
		assertTrue(d.getClassName().equals("D"));
		assertTrue(e.getClassName().equals("E"));
		assertTrue(f.getClassName().equals("F"));
		
		assertTrue(a.hasTable("A"));
		assertTrue(b.hasTable("D"));
		assertTrue(b.hasTable("E"));
		assertTrue(c.hasTable("C"));
		assertTrue(d.hasTable("D"));
		assertTrue(e.hasTable("E"));
		assertTrue(f.hasTable("F"));
		
		assertTrue(a.getTables().size() == 1);
		assertTrue(b.getTables().size() == 2);
		assertTrue(c.getTables().size() == 1);
		assertTrue(d.getTables().size() == 1);
		assertTrue(e.getTables().size() == 1);
		assertTrue(f.getTables().size() == 1);		
	}

	public static Test suite() {
		TestSuite t=new TestSuite();

    		t.addTest(new TestORMappingSchemeImp("testCaseA"));
    		t.addTest(new TestORMappingSchemeImp("testCaseB"));

    		return t;
	}
}
