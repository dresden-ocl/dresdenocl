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

public class TestObjectViewSchema extends TestCase {
	Model theModel;

	public TestObjectViewSchema(String n) {
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
		ObjectViewSchema ovs = new ObjectViewSchema(new ORMappingImp(theModel),
							    new OracleSQLBuilder());
		MappedClass a, b, c, d, e, f;
		Table ta, tb, tc;
		Guide guide;
		
		assert((a = ovs.getMappedClass("A")) != null);	
		assert((b = ovs.getMappedClass("B")) != null);	
		assert((c = ovs.getMappedClass("C")) != null);	
		assert((d = ovs.getMappedClass("D")) != null);	
		assert((e = ovs.getMappedClass("E")) != null);	
		assert((f = ovs.getMappedClass("F")) != null);	
		
		assert(a.getClassName().equals("A"));
		assert(b.getClassName().equals("B"));
		assert(c.getClassName().equals("C"));
		assert(d.getClassName().equals("D"));
		assert(e.getClassName().equals("E"));
		assert(f.getClassName().equals("F"));
		
		assert(a.hasTable("OV_A"));
		assert(b.hasTable("OV_B"));
		assert(c.hasTable("OV_C"));
		assert(d.hasTable("OV_D"));
		assert(e.hasTable("OV_E"));
		assert(f.hasTable("OV_F"));
		
		
		assert(a.getTables().size() == 1);
		assert(b.getTables().size() == 1);
		assert(c.getTables().size() == 1);
		assert(d.getTables().size() == 1);
		assert(e.getTables().size() == 1);
		assert(f.getTables().size() == 1);
		
		assert(b.isQuery("operationB"));
		assert(d.isQuery("operationB"));
		assert(e.isQuery("operationB"));
		assert(!a.isQuery("operationB"));
		
		assert(a.isAssociationEnd("ra1"));
		assert(a.isAssociationEnd("ra2"));
		assert(a.isAssociationEnd("b1"));
		assert(a.isAssociationEnd("b2"));
		assert(a.isAssociationEnd("c"));
		assert(!a.isAssociationEnd("b"));
		
		assert(b.isAssociationEnd("a1"));
		assert(b.isAssociationEnd("a2"));
		
		assert(c.isAssociationEnd("a"));
		assert(c.isAssociationEnd("c4"));
		assert(!c.isAssociationEnd("ra1"));
		assert(!c.isAssociationEnd("f"));
		
		assert(a.isAttribute("a1"));
		assert(a.isAttribute("a2"));
		assert(a.isAttribute("a3"));
		assert(!a.isAttribute("a4"));
		assert(!a.isAttribute("b1"));
		
		assert(b.isAttribute("b1"));
		assert(b.isAttribute("b2"));
		assert(b.isAttribute("b3"));
		
		assert(c.isAttribute("c1"));
		assert(c.isAttribute("c2"));
		assert(c.isAttribute("c3"));
		assert(!c.isAttribute("c4"));
		
		assert(d.isAttribute("d1"));
		assert(d.isAttribute("b1"));
		assert(d.isAttribute("b2"));
		assert(d.isAttribute("b3"));
		
		assert(e.isAttribute("e1"));
		assert(e.isAttribute("b1"));
		assert(e.isAttribute("b2"));
		assert(e.isAttribute("b3"));
		
		assert(f.isAttribute("f1"));
		assert(f.isAttribute("f2"));
		assert(f.isAttribute("f3"));
		
		assert(a.getAssociationEnd("c") == c);
		assert(a.getAssociationEnd("ra1") == a);
		assert(a.getAssociationEnd("ra2") == a);
		assert(a.getAssociationEnd("b1") == b);
		assert(a.getAssociationEnd("b2") == b);
		
		assert(b.getAssociationEnd("a1") == a);
		assert(b.getAssociationEnd("a2") == a);
		
		assert(c.getAssociationEnd("a") == a);
		assert(c.getAssociationEnd("c4") == f);
		
		assert(d.getAssociationEnd("a1") == a);
		assert(d.getAssociationEnd("a2") == a);
		
		assert(e.getAssociationEnd("a1") == a);
		assert(e.getAssociationEnd("a2") == a);
		
		
		guide = a.getAttributeGuide("a1");
		guide.next();
		assert(guide.getSelect().equals("A1"));
		assert(guide.getFrom().equals("OV_A"));
		
		assert(!guide.hasMoreSteps());
		
		guide = a.getAttributeGuide("a2");
		guide.next();
		assert(guide.getSelect().equals("A2"));
		assert(guide.getFrom().equals("OV_A"));
		
		assert(!guide.hasMoreSteps());
		
		guide = a.getAttributeGuide("a3");
		guide.next();
		assert(guide.getSelect().equals("A3"));
		assert(guide.getFrom().equals("OV_A"));
		
		assert(!guide.hasMoreSteps());
		
		guide = b.getAttributeGuide("b1");
		guide.next();
		assert(guide.getSelect().equals("B1"));
		assert(guide.getFrom().equals("OV_B"));
		
		assert(!guide.hasMoreSteps());
		
		guide = b.getAttributeGuide("b2");
		guide.next();
		assert(guide.getSelect().equals("B2"));
		assert(guide.getFrom().equals("OV_B"));
		
		assert(!guide.hasMoreSteps());
		
		guide = b.getAttributeGuide("b3");
		guide.next();
		assert(guide.getSelect().equals("B3"));
		assert(guide.getFrom().equals("OV_B"));
		
		assert(!guide.hasMoreSteps());
		
		guide = c.getAttributeGuide("c1");
		guide.next();
		assert(guide.getSelect().equals("C1"));
		assert(guide.getFrom().equals("OV_C"));
		
		assert(!guide.hasMoreSteps());
		
		guide = c.getAttributeGuide("c2");
		guide.next();
		assert(guide.getSelect().equals("C2"));
		assert(guide.getFrom().equals("OV_C"));
		
		assert(!guide.hasMoreSteps());
		
		guide = c.getAttributeGuide("c3");
		guide.next();
		assert(guide.getSelect().equals("C3"));
		assert(guide.getFrom().equals("OV_C"));
		
		assert(!guide.hasMoreSteps());
		
		guide = d.getAttributeGuide("d1");
		guide.next();
		assert(guide.getSelect().equals("D1"));
		assert(guide.getFrom().equals("OV_D"));
		
		assert(!guide.hasMoreSteps());
		
		guide = d.getAttributeGuide("b1");
		guide.next();
		assert(guide.getSelect().equals("B1"));
		assert(guide.getFrom().equals("OV_D"));
		
		assert(!guide.hasMoreSteps());
		
		guide = d.getAttributeGuide("b2");
		guide.next();
		assert(guide.getSelect().equals("B2"));
		assert(guide.getFrom().equals("OV_D"));
		
		assert(!guide.hasMoreSteps());
		
		guide = d.getAttributeGuide("b3");
		guide.next();
		assert(guide.getSelect().equals("B3"));
		assert(guide.getFrom().equals("OV_D"));
		
		assert(!guide.hasMoreSteps());
		
		guide = e.getAttributeGuide("e1");
		guide.next();
		assert(guide.getSelect().equals("E1"));
		assert(guide.getFrom().equals("OV_E"));
		
		assert(!guide.hasMoreSteps());
		
		guide = e.getAttributeGuide("b1");
		guide.next();
		assert(guide.getSelect().equals("B1"));
		assert(guide.getFrom().equals("OV_E"));
		
		assert(!guide.hasMoreSteps());
		
		guide = e.getAttributeGuide("b2");
		guide.next();
		assert(guide.getSelect().equals("B2"));
		assert(guide.getFrom().equals("OV_E"));
		
		assert(!guide.hasMoreSteps());
		
		guide = e.getAttributeGuide("b3");
		guide.next();
		assert(guide.getSelect().equals("B3"));
		assert(guide.getFrom().equals("OV_E"));
		
		assert(!guide.hasMoreSteps());
		
		guide = f.getAttributeGuide("f1");
		guide.next();
		assert(guide.getSelect().equals("F1"));
		assert(guide.getFrom().equals("OV_F"));
		
		assert(!guide.hasMoreSteps());
		
		guide = f.getAttributeGuide("f2");
		guide.next();
		assert(guide.getSelect().equals("F2"));
		assert(guide.getFrom().equals("OV_F"));
		
		assert(!guide.hasMoreSteps());
		
		guide = f.getAttributeGuide("f3");
		guide.next();
		assert(guide.getSelect().equals("F3"));
		assert(guide.getFrom().equals("OV_F"));
		
		assert(!guide.hasMoreSteps());
		
		ta = (Table)a.getTables().get(0);
		tb = (Table)b.getTables().get(0);
		tc = (Table)c.getTables().get(0);
		
		guide = a.getJoinGuide("c");
		//System.err.println(guide.toString());
		guide.next();
		assert(tc.isPrimaryKeyColumn(guide.getSelect()));
		assert(tc.getTableName().equals(guide.getFrom()));
		assert(tc.isForeignKeyColumn(guide.getWhere()));
		guide.next();
		assert(ta.isPrimaryKeyColumn(guide.getSelect()));
		assert(ta.getTableName().equals(guide.getFrom()));
		assert(ta.isPrimaryKeyColumn(guide.getWhere()));
		assert(!guide.hasMoreSteps());
		
		guide = a.getJoinGuide("b2");
		//System.err.println(guide.toString());
		guide.next();
		assert(tb.isPrimaryKeyColumn(guide.getSelect()));
		assert(tb.getTableName().equals(guide.getFrom()));
		assert(tb.isPrimaryKeyColumn(guide.getWhere()));
		guide.next();
		guide.next();
		assert(ta.isPrimaryKeyColumn(guide.getSelect()));
		assert(ta.getTableName().equals(guide.getFrom()));
		assert(ta.isPrimaryKeyColumn(guide.getWhere()));
		assert(!guide.hasMoreSteps());	
		
		guide = a.getJoinGuide("ra1");
		//System.err.println(guide.toString());
		guide.next();
		assert(ta.isPrimaryKeyColumn(guide.getSelect()));
		assert(ta.getTableName().equals(guide.getFrom()));
		assert(ta.isPrimaryKeyColumn(guide.getWhere()));
		guide.next();
		assert(ta.isForeignKeyColumn(guide.getSelect()));
		assert(ta.getTableName().equals(guide.getFrom()));
		assert(ta.isPrimaryKeyColumn(guide.getWhere()));
		assert(!guide.hasMoreSteps());
		
		assert(a.getJoinGuide("ra2") != null);	
		assert(a.getJoinGuide("b1") != null);	
		
		assert(c.getJoinGuide("a") != null);	
		assert(c.getJoinGuide("c4") != null);	
		
		assert(b.getJoinGuide("a1") != null);	
		assert(b.getJoinGuide("a2") != null);
		
		assert(((View)tb).getTableNames().size() == 1);					
	}
	
	// one table per leaf class
	public void testCaseB() {
		ObjectViewSchema ovs = new ObjectViewSchema(new ORMappingImp(theModel, 1, 1, "int", false),
							    new OracleSQLBuilder());
		MappedClass a, b, c, d, e, f;
		Table ta, tb, tc;
		Guide guide;
		
		//System.err.println(ovs.getViewDefinition("B"));
		
		assert((a = ovs.getMappedClass("A")) != null);	
		assert((b = ovs.getMappedClass("B")) != null);	
		assert((c = ovs.getMappedClass("C")) != null);	
		assert((d = ovs.getMappedClass("D")) != null);	
		assert((e = ovs.getMappedClass("E")) != null);	
		assert((f = ovs.getMappedClass("F")) != null);	
		
		assert(a.getClassName().equals("A"));
		assert(b.getClassName().equals("B"));
		assert(c.getClassName().equals("C"));
		assert(d.getClassName().equals("D"));
		assert(e.getClassName().equals("E"));
		assert(f.getClassName().equals("F"));
		
		assert(a.hasTable("OV_A"));
		assert(b.hasTable("OV_B"));
		assert(c.hasTable("OV_C"));
		assert(d.hasTable("OV_D"));
		assert(e.hasTable("OV_E"));
		assert(f.hasTable("OV_F"));
		
		
		assert(a.getTables().size() == 1);
		assert(b.getTables().size() == 1);
		assert(c.getTables().size() == 1);
		assert(d.getTables().size() == 1);
		assert(e.getTables().size() == 1);
		assert(f.getTables().size() == 1);
		
		assert(b.isQuery("operationB"));
		assert(d.isQuery("operationB"));
		assert(e.isQuery("operationB"));
		assert(!a.isQuery("operationB"));
		
		assert(a.isAssociationEnd("ra1"));
		assert(a.isAssociationEnd("ra2"));
		assert(a.isAssociationEnd("b1"));
		assert(a.isAssociationEnd("b2"));
		assert(a.isAssociationEnd("c"));
		assert(!a.isAssociationEnd("b"));
		
		assert(b.isAssociationEnd("a1"));
		assert(b.isAssociationEnd("a2"));
		
		assert(c.isAssociationEnd("a"));
		assert(c.isAssociationEnd("c4"));
		assert(!c.isAssociationEnd("ra1"));
		assert(!c.isAssociationEnd("f"));
		
		assert(a.isAttribute("a1"));
		assert(a.isAttribute("a2"));
		assert(a.isAttribute("a3"));
		assert(!a.isAttribute("a4"));
		assert(!a.isAttribute("b1"));
		
		assert(b.isAttribute("b1"));
		assert(b.isAttribute("b2"));
		assert(b.isAttribute("b3"));
		
		assert(c.isAttribute("c1"));
		assert(c.isAttribute("c2"));
		assert(c.isAttribute("c3"));
		assert(!c.isAttribute("c4"));
		
		assert(d.isAttribute("d1"));
		assert(d.isAttribute("b1"));
		assert(d.isAttribute("b2"));
		assert(d.isAttribute("b3"));
		
		assert(e.isAttribute("e1"));
		assert(e.isAttribute("b1"));
		assert(e.isAttribute("b2"));
		assert(e.isAttribute("b3"));
		
		assert(f.isAttribute("f1"));
		assert(f.isAttribute("f2"));
		assert(f.isAttribute("f3"));
		
		assert(a.getAssociationEnd("c") == c);
		assert(a.getAssociationEnd("ra1") == a);
		assert(a.getAssociationEnd("ra2") == a);
		assert(a.getAssociationEnd("b1") == b);
		assert(a.getAssociationEnd("b2") == b);
		
		assert(b.getAssociationEnd("a1") == a);
		assert(b.getAssociationEnd("a2") == a);
		
		assert(c.getAssociationEnd("a") == a);
		assert(c.getAssociationEnd("c4") == f);
		
		assert(d.getAssociationEnd("a1") == a);
		assert(d.getAssociationEnd("a2") == a);
		
		assert(e.getAssociationEnd("a1") == a);
		assert(e.getAssociationEnd("a2") == a);
		
		
		guide = a.getAttributeGuide("a1");
		guide.next();
		assert(guide.getSelect().equals("A1"));
		assert(guide.getFrom().equals("OV_A"));
		
		assert(!guide.hasMoreSteps());
		
		guide = a.getAttributeGuide("a2");
		guide.next();
		assert(guide.getSelect().equals("A2"));
		assert(guide.getFrom().equals("OV_A"));
		
		assert(!guide.hasMoreSteps());
		
		guide = a.getAttributeGuide("a3");
		guide.next();
		assert(guide.getSelect().equals("A3"));
		assert(guide.getFrom().equals("OV_A"));
		
		assert(!guide.hasMoreSteps());
		
		guide = b.getAttributeGuide("b1");
		guide.next();
		assert(guide.getSelect().equals("B1"));
		assert(guide.getFrom().equals("OV_B"));
		
		assert(!guide.hasMoreSteps());
		
		guide = b.getAttributeGuide("b2");
		guide.next();
		assert(guide.getSelect().equals("B2"));
		assert(guide.getFrom().equals("OV_B"));
		
		assert(!guide.hasMoreSteps());
		
		guide = b.getAttributeGuide("b3");
		guide.next();
		assert(guide.getSelect().equals("B3"));
		assert(guide.getFrom().equals("OV_B"));
		
		assert(!guide.hasMoreSteps());
		
		guide = c.getAttributeGuide("c1");
		guide.next();
		assert(guide.getSelect().equals("C1"));
		assert(guide.getFrom().equals("OV_C"));
		
		assert(!guide.hasMoreSteps());
		
		guide = c.getAttributeGuide("c2");
		guide.next();
		assert(guide.getSelect().equals("C2"));
		assert(guide.getFrom().equals("OV_C"));
		
		assert(!guide.hasMoreSteps());
		
		guide = c.getAttributeGuide("c3");
		guide.next();
		assert(guide.getSelect().equals("C3"));
		assert(guide.getFrom().equals("OV_C"));
		
		assert(!guide.hasMoreSteps());
		
		guide = d.getAttributeGuide("d1");
		guide.next();
		assert(guide.getSelect().equals("D1"));
		assert(guide.getFrom().equals("OV_D"));
		
		assert(!guide.hasMoreSteps());
		
		guide = d.getAttributeGuide("b1");
		guide.next();
		assert(guide.getSelect().equals("B1"));
		assert(guide.getFrom().equals("OV_D"));
		
		assert(!guide.hasMoreSteps());
		
		guide = d.getAttributeGuide("b2");
		guide.next();
		assert(guide.getSelect().equals("B2"));
		assert(guide.getFrom().equals("OV_D"));
		
		assert(!guide.hasMoreSteps());
		
		guide = d.getAttributeGuide("b3");
		guide.next();
		assert(guide.getSelect().equals("B3"));
		assert(guide.getFrom().equals("OV_D"));
		
		assert(!guide.hasMoreSteps());
		
		guide = e.getAttributeGuide("e1");
		guide.next();
		assert(guide.getSelect().equals("E1"));
		assert(guide.getFrom().equals("OV_E"));
		
		assert(!guide.hasMoreSteps());
		
		guide = e.getAttributeGuide("b1");
		guide.next();
		assert(guide.getSelect().equals("B1"));
		assert(guide.getFrom().equals("OV_E"));
		
		assert(!guide.hasMoreSteps());
		
		guide = e.getAttributeGuide("b2");
		guide.next();
		assert(guide.getSelect().equals("B2"));
		assert(guide.getFrom().equals("OV_E"));
		
		assert(!guide.hasMoreSteps());
		
		guide = e.getAttributeGuide("b3");
		guide.next();
		assert(guide.getSelect().equals("B3"));
		assert(guide.getFrom().equals("OV_E"));
		
		assert(!guide.hasMoreSteps());
		
		guide = f.getAttributeGuide("f1");
		guide.next();
		assert(guide.getSelect().equals("F1"));
		assert(guide.getFrom().equals("OV_F"));
		
		assert(!guide.hasMoreSteps());
		
		guide = f.getAttributeGuide("f2");
		guide.next();
		assert(guide.getSelect().equals("F2"));
		assert(guide.getFrom().equals("OV_F"));
		
		assert(!guide.hasMoreSteps());
		
		guide = f.getAttributeGuide("f3");
		guide.next();
		assert(guide.getSelect().equals("F3"));
		assert(guide.getFrom().equals("OV_F"));
		
		assert(!guide.hasMoreSteps());
		
		ta = (Table)a.getTables().get(0);
		tb = (Table)b.getTables().get(0);
		tc = (Table)c.getTables().get(0);
		
		guide = a.getJoinGuide("c");
		//System.err.println(guide.toString());
		guide.next();
		assert(tc.isPrimaryKeyColumn(guide.getSelect()));
		assert(tc.getTableName().equals(guide.getFrom()));
		assert(tc.isForeignKeyColumn(guide.getWhere()));
		guide.next();
		assert(ta.isPrimaryKeyColumn(guide.getSelect()));
		assert(ta.getTableName().equals(guide.getFrom()));
		assert(ta.isPrimaryKeyColumn(guide.getWhere()));
		assert(!guide.hasMoreSteps());
		
		guide = a.getJoinGuide("b2");
		//System.err.println(guide.toString());
		guide.next();
		assert(tb.isPrimaryKeyColumn(guide.getSelect()));
		assert(tb.getTableName().equals(guide.getFrom()));
		assert(tb.isPrimaryKeyColumn(guide.getWhere()));
		guide.next();
		guide.next();
		assert(ta.isPrimaryKeyColumn(guide.getSelect()));
		assert(ta.getTableName().equals(guide.getFrom()));
		assert(ta.isPrimaryKeyColumn(guide.getWhere()));
		assert(!guide.hasMoreSteps());	
		
		guide = a.getJoinGuide("ra1");
		//System.err.println(guide.toString());
		guide.next();
		assert(ta.isPrimaryKeyColumn(guide.getSelect()));
		assert(ta.getTableName().equals(guide.getFrom()));
		assert(ta.isPrimaryKeyColumn(guide.getWhere()));
		guide.next();
		assert(ta.isForeignKeyColumn(guide.getSelect()));
		assert(ta.getTableName().equals(guide.getFrom()));
		assert(ta.isPrimaryKeyColumn(guide.getWhere()));
		assert(!guide.hasMoreSteps());
		
		guide = b.getJoinGuide("a2");
		//System.err.println(guide.toString());
		guide.next();
		assert(ta.isPrimaryKeyColumn(guide.getSelect()));
		assert(ta.getTableName().equals(guide.getFrom()));
		assert(ta.isPrimaryKeyColumn(guide.getWhere()));
		guide.next();
		guide.next();
		assert(tb.isPrimaryKeyColumn(guide.getSelect()));
		assert(tb.getTableName().equals(guide.getFrom()));
		assert(tb.isPrimaryKeyColumn(guide.getWhere()));
		assert(!guide.hasMoreSteps());	
		
		assert(a.getJoinGuide("ra2") != null);	
		assert(a.getJoinGuide("b1") != null);	
		
		assert(c.getJoinGuide("a") != null);	
		assert(c.getJoinGuide("c4") != null);	
		
		assert(b.getJoinGuide("a1") != null);		
		
		assert(((View)tb).getTableNames().size() == 2);
	}
	
	// one table for entire hierarchy
	public void testCaseC() {
		ObjectViewSchema ovs = new ObjectViewSchema(new ORMappingImp(theModel, 0, 1, "int", false),
							    new OracleSQLBuilder());
		MappedClass a, b, c, d, e, f;
		Table ta, tb, tc;
		Guide guide;
		
		//System.err.println(ovs.getViewDefinition("B"));
		
		assert((a = ovs.getMappedClass("A")) != null);	
		assert((b = ovs.getMappedClass("B")) != null);	
		assert((c = ovs.getMappedClass("C")) != null);	
		assert((d = ovs.getMappedClass("D")) != null);	
		assert((e = ovs.getMappedClass("E")) != null);	
		assert((f = ovs.getMappedClass("F")) != null);	
		
		assert(a.getClassName().equals("A"));
		assert(b.getClassName().equals("B"));
		assert(c.getClassName().equals("C"));
		assert(d.getClassName().equals("D"));
		assert(e.getClassName().equals("E"));
		assert(f.getClassName().equals("F"));
		
		assert(a.hasTable("OV_A"));
		assert(b.hasTable("OV_B"));
		assert(c.hasTable("OV_C"));
		assert(d.hasTable("OV_D"));
		assert(e.hasTable("OV_E"));
		assert(f.hasTable("OV_F"));
		
		
		assert(a.getTables().size() == 1);
		assert(b.getTables().size() == 1);
		assert(c.getTables().size() == 1);
		assert(d.getTables().size() == 1);
		assert(e.getTables().size() == 1);
		assert(f.getTables().size() == 1);
		
		assert(b.isQuery("operationB"));
		assert(d.isQuery("operationB"));
		assert(e.isQuery("operationB"));
		assert(!a.isQuery("operationB"));
		
		assert(a.isAssociationEnd("ra1"));
		assert(a.isAssociationEnd("ra2"));
		assert(a.isAssociationEnd("b1"));
		assert(a.isAssociationEnd("b2"));
		assert(a.isAssociationEnd("c"));
		assert(!a.isAssociationEnd("b"));
		
		assert(b.isAssociationEnd("a1"));
		assert(b.isAssociationEnd("a2"));
		
		assert(c.isAssociationEnd("a"));
		assert(c.isAssociationEnd("c4"));
		assert(!c.isAssociationEnd("ra1"));
		assert(!c.isAssociationEnd("f"));
		
		assert(a.isAttribute("a1"));
		assert(a.isAttribute("a2"));
		assert(a.isAttribute("a3"));
		assert(!a.isAttribute("a4"));
		assert(!a.isAttribute("b1"));
		
		assert(b.isAttribute("b1"));
		assert(b.isAttribute("b2"));
		assert(b.isAttribute("b3"));
		
		assert(c.isAttribute("c1"));
		assert(c.isAttribute("c2"));
		assert(c.isAttribute("c3"));
		assert(!c.isAttribute("c4"));
		
		assert(d.isAttribute("d1"));
		assert(d.isAttribute("b1"));
		assert(d.isAttribute("b2"));
		assert(d.isAttribute("b3"));
		
		assert(e.isAttribute("e1"));
		assert(e.isAttribute("b1"));
		assert(e.isAttribute("b2"));
		assert(e.isAttribute("b3"));
		
		assert(f.isAttribute("f1"));
		assert(f.isAttribute("f2"));
		assert(f.isAttribute("f3"));
		
		assert(a.getAssociationEnd("c") == c);
		assert(a.getAssociationEnd("ra1") == a);
		assert(a.getAssociationEnd("ra2") == a);
		assert(a.getAssociationEnd("b1") == b);
		assert(a.getAssociationEnd("b2") == b);
		
		assert(b.getAssociationEnd("a1") == a);
		assert(b.getAssociationEnd("a2") == a);
		
		assert(c.getAssociationEnd("a") == a);
		assert(c.getAssociationEnd("c4") == f);
		
		assert(d.getAssociationEnd("a1") == a);
		assert(d.getAssociationEnd("a2") == a);
		
		assert(e.getAssociationEnd("a1") == a);
		assert(e.getAssociationEnd("a2") == a);
		
		
		guide = a.getAttributeGuide("a1");
		guide.next();
		assert(guide.getSelect().equals("A1"));
		assert(guide.getFrom().equals("OV_A"));
		
		assert(!guide.hasMoreSteps());
		
		guide = a.getAttributeGuide("a2");
		guide.next();
		assert(guide.getSelect().equals("A2"));
		assert(guide.getFrom().equals("OV_A"));
		
		assert(!guide.hasMoreSteps());
		
		guide = a.getAttributeGuide("a3");
		guide.next();
		assert(guide.getSelect().equals("A3"));
		assert(guide.getFrom().equals("OV_A"));
		
		assert(!guide.hasMoreSteps());
		
		guide = b.getAttributeGuide("b1");
		guide.next();
		assert(guide.getSelect().equals("B1"));
		assert(guide.getFrom().equals("OV_B"));
		
		assert(!guide.hasMoreSteps());
		
		guide = b.getAttributeGuide("b2");
		guide.next();
		assert(guide.getSelect().equals("B2"));
		assert(guide.getFrom().equals("OV_B"));
		
		assert(!guide.hasMoreSteps());
		
		guide = b.getAttributeGuide("b3");
		guide.next();
		assert(guide.getSelect().equals("B3"));
		assert(guide.getFrom().equals("OV_B"));
		
		assert(!guide.hasMoreSteps());
		
		guide = c.getAttributeGuide("c1");
		guide.next();
		assert(guide.getSelect().equals("C1"));
		assert(guide.getFrom().equals("OV_C"));
		
		assert(!guide.hasMoreSteps());
		
		guide = c.getAttributeGuide("c2");
		guide.next();
		assert(guide.getSelect().equals("C2"));
		assert(guide.getFrom().equals("OV_C"));
		
		assert(!guide.hasMoreSteps());
		
		guide = c.getAttributeGuide("c3");
		guide.next();
		assert(guide.getSelect().equals("C3"));
		assert(guide.getFrom().equals("OV_C"));
		
		assert(!guide.hasMoreSteps());
		
		guide = d.getAttributeGuide("d1");
		guide.next();
		assert(guide.getSelect().equals("D1"));
		assert(guide.getFrom().equals("OV_D"));
		
		assert(!guide.hasMoreSteps());
		
		guide = d.getAttributeGuide("b1");
		guide.next();
		assert(guide.getSelect().equals("B1"));
		assert(guide.getFrom().equals("OV_D"));
		
		assert(!guide.hasMoreSteps());
		
		guide = d.getAttributeGuide("b2");
		guide.next();
		assert(guide.getSelect().equals("B2"));
		assert(guide.getFrom().equals("OV_D"));
		
		assert(!guide.hasMoreSteps());
		
		guide = d.getAttributeGuide("b3");
		guide.next();
		assert(guide.getSelect().equals("B3"));
		assert(guide.getFrom().equals("OV_D"));
		
		assert(!guide.hasMoreSteps());
		
		guide = e.getAttributeGuide("e1");
		guide.next();
		assert(guide.getSelect().equals("E1"));
		assert(guide.getFrom().equals("OV_E"));
		
		assert(!guide.hasMoreSteps());
		
		guide = e.getAttributeGuide("b1");
		guide.next();
		assert(guide.getSelect().equals("B1"));
		assert(guide.getFrom().equals("OV_E"));
		
		assert(!guide.hasMoreSteps());
		
		guide = e.getAttributeGuide("b2");
		guide.next();
		assert(guide.getSelect().equals("B2"));
		assert(guide.getFrom().equals("OV_E"));
		
		assert(!guide.hasMoreSteps());
		
		guide = e.getAttributeGuide("b3");
		guide.next();
		assert(guide.getSelect().equals("B3"));
		assert(guide.getFrom().equals("OV_E"));
		
		assert(!guide.hasMoreSteps());
		
		guide = f.getAttributeGuide("f1");
		guide.next();
		assert(guide.getSelect().equals("F1"));
		assert(guide.getFrom().equals("OV_F"));
		
		assert(!guide.hasMoreSteps());
		
		guide = f.getAttributeGuide("f2");
		guide.next();
		assert(guide.getSelect().equals("F2"));
		assert(guide.getFrom().equals("OV_F"));
		
		assert(!guide.hasMoreSteps());
		
		guide = f.getAttributeGuide("f3");
		guide.next();
		assert(guide.getSelect().equals("F3"));
		assert(guide.getFrom().equals("OV_F"));
		
		assert(!guide.hasMoreSteps());
		
		ta = (Table)a.getTables().get(0);
		tb = (Table)b.getTables().get(0);
		tc = (Table)c.getTables().get(0);
		
		guide = a.getJoinGuide("c");
		//System.err.println(guide.toString());
		guide.next();
		assert(tc.isPrimaryKeyColumn(guide.getSelect()));
		assert(tc.getTableName().equals(guide.getFrom()));
		assert(tc.isForeignKeyColumn(guide.getWhere()));
		guide.next();
		assert(ta.isPrimaryKeyColumn(guide.getSelect()));
		assert(ta.getTableName().equals(guide.getFrom()));
		assert(ta.isPrimaryKeyColumn(guide.getWhere()));
		assert(!guide.hasMoreSteps());
		
		guide = a.getJoinGuide("b2");
		//System.err.println(guide.toString());
		guide.next();
		assert(tb.isPrimaryKeyColumn(guide.getSelect()));
		assert(tb.getTableName().equals(guide.getFrom()));
		assert(tb.isPrimaryKeyColumn(guide.getWhere()));
		guide.next();
		guide.next();
		assert(ta.isPrimaryKeyColumn(guide.getSelect()));
		assert(ta.getTableName().equals(guide.getFrom()));
		assert(ta.isPrimaryKeyColumn(guide.getWhere()));
		assert(!guide.hasMoreSteps());	
		
		guide = a.getJoinGuide("ra1");
		//System.err.println(guide.toString());
		guide.next();
		assert(ta.isPrimaryKeyColumn(guide.getSelect()));
		assert(ta.getTableName().equals(guide.getFrom()));
		assert(ta.isPrimaryKeyColumn(guide.getWhere()));
		guide.next();
		assert(ta.isForeignKeyColumn(guide.getSelect()));
		assert(ta.getTableName().equals(guide.getFrom()));
		assert(ta.isPrimaryKeyColumn(guide.getWhere()));
		assert(!guide.hasMoreSteps());
		
		guide = b.getJoinGuide("a2");
		//System.err.println(guide.toString());
		guide.next();
		assert(ta.isPrimaryKeyColumn(guide.getSelect()));
		assert(ta.getTableName().equals(guide.getFrom()));
		assert(ta.isPrimaryKeyColumn(guide.getWhere()));
		guide.next();
		guide.next();
		assert(tb.isPrimaryKeyColumn(guide.getSelect()));
		assert(tb.getTableName().equals(guide.getFrom()));
		assert(tb.isPrimaryKeyColumn(guide.getWhere()));
		assert(!guide.hasMoreSteps());	
		
		assert(a.getJoinGuide("ra2") != null);	
		assert(a.getJoinGuide("b1") != null);	
		
		assert(c.getJoinGuide("a") != null);	
		assert(c.getJoinGuide("c4") != null);	
		
		assert(b.getJoinGuide("a1") != null);		
		
		assert(((View)tb).getTableNames().size() == 1);
	}

	public static Test suite() {
		TestSuite t=new TestSuite();

    		t.addTest(new TestObjectViewSchema("testCaseA"));
    		t.addTest(new TestObjectViewSchema("testCaseB"));
    		t.addTest(new TestObjectViewSchema("testCaseC"));

    		return t;
	}
}
