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
import tudresden.ocl.check.types.xmifacade.*;
import tudresden.ocl.check.types.*;
import java.io.*;
import java.util.*;

public class TestORMappingImp extends TestCase {
	Model theModel;	
	
	public TestORMappingImp(String n) {
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
	
	// default mapping
	public void testCaseA() {
		ORMappingImp map = new ORMappingImp(theModel);
		List tables, guides;
		Table table, tableA, tableB, tableC;
		String cols[];
		Guide guide;
		
		assert(map.tables().size() == 7);
		assert(map.classifiers().size() == 6);
		
		tables = map.getClassTables("A");	
		assert(tables.size() == 1);
		table = (Table)tables.get(0);
		tableA = table;
		assert(table.getPrimaryKeyColumns().length == 1);
		assert(table.getColumns().length == 6); 
		assert(table.isColumn("A1"));
		assert(table.isColumn("A2"));
		assert(table.isColumn("A3"));
		
		tables = map.getClassTables("B");	
		assert(tables.size() == 1);
		table = (Table)tables.get(0);
		tableB = table;
		assert(table.getPrimaryKeyColumns().length == 1);
		assert(table.getColumns().length == 5);
		assert(table.isColumn("B1"));
		assert(table.isColumn("B2"));
		assert(table.isColumn("B3"));
		
		tables = map.getClassTables("C");	
		assert(tables.size() == 1);
		table = (Table)tables.get(0);
		tableC = table;
		assert(table.getPrimaryKeyColumns().length == 1);
		assert(table.getColumns().length == 6);
		assert(table.isColumn("C1"));
		assert(table.isColumn("C2"));
		assert(table.isColumn("C3"));
		
		tables = map.getClassTables("D");	
		assert(tables.size() == 1);
		table = (Table)tables.get(0);
		assert(table.getPrimaryKeyColumns().length == 1);
		assert(table.getColumns().length == 2);
		assert(table.isColumn("D1"));
						
		tables = map.getClassTables("E");	
		assert(tables.size() == 1);
		table = (Table)tables.get(0);
		assert(table.getPrimaryKeyColumns().length == 1);
		assert(table.getColumns().length == 2);
		assert(table.isColumn("E1"));
		
		tables = map.getClassTables("F");	
		assert(tables.size() == 1);
		table = (Table)tables.get(0);
		assert(table.getPrimaryKeyColumns().length == 1);
		assert(table.getColumns().length == 4);
		assert(table.isColumn("F1"));
		assert(table.isColumn("F2"));
		assert(table.isColumn("F3"));
		
		table = null;
		for (Iterator i=map.tables().iterator(); i.hasNext(); ) {
			table = (Table)i.next();
			if (table.getTableName().equals(ORMappingImp.ASSTABNAME + "1")) break;
		}
		cols = table.getColumns();
		assert(table.isForeignKeyColumn(cols[0]) && table.isForeignKeyColumn(cols[1]));		
		assert(tableA.isColumn(cols[0]) || tableA.isColumn(cols[1]));
		assert(tableB.isColumn(cols[1]) || tableB.isColumn(cols[1]));
		
		assert(map.directSupertypeNames("D").contains("B"));
		assert(map.directSupertypeNames("E").contains("B"));
		assert(map.directSupertypeNames("D").size() == 1);
		assert(map.directSupertypeNames("E").size() == 1);
		assert(map.directSupertypeNames("A").size() == 0);
		assert(map.directSupertypeNames("B").size() == 0);
		assert(map.directSupertypeNames("C").size() == 0);
		assert(map.directSupertypeNames("F").size() == 0);
		
		assert(map.operations("B").contains("operationB"));
		assert(map.operations("B").size() == 1);
		assert(map.operations("A").size() == 0);
		assert(map.operations("C").size() == 0);
		assert(map.operations("D").size() == 0);
		assert(map.operations("E").size() == 0);
		assert(map.operations("F").size() == 0);
		
		guides = map.guidesToAssociationEnds("A", "c");
		assert(guides.size() == 1);
		guide = (Guide)guides.get(0);
		System.err.println(guide.toString());
		guide.next();
		assert(tableC.isPrimaryKeyColumn(guide.getSelect()));
		assert(tableC.getTableName().equals(guide.getFrom()));
		assert(tableC.isForeignKeyColumn(guide.getWhere()));
		guide.next();
		assert(tableA.isPrimaryKeyColumn(guide.getSelect()));
		assert(tableA.getTableName().equals(guide.getFrom()));
		assert(tableA.isPrimaryKeyColumn(guide.getWhere()));
		assert(!guide.hasMoreSteps());
		
		guides = map.guidesToAssociationEnds("C", "a");
		assert(guides.size() == 1);
		guide = (Guide)guides.get(0);
		System.err.println(guide.toString());
		guide.next();
		assert(tableA.isPrimaryKeyColumn(guide.getSelect()));
		assert(tableA.getTableName().equals(guide.getFrom()));
		assert(tableA.isPrimaryKeyColumn(guide.getWhere()));
		guide.next();
		assert(tableC.isForeignKeyColumn(guide.getSelect()));
		assert(tableC.getTableName().equals(guide.getFrom()));
		assert(tableC.isPrimaryKeyColumn(guide.getWhere()));
		assert(!guide.hasMoreSteps());
		
		guides = map.guidesToAssociationEnds("A", "ra1");
		assert(guides.size() == 1);
		guide = (Guide)guides.get(0);
		System.err.println(guide.toString());
		guide.next();
		assert(tableA.isPrimaryKeyColumn(guide.getSelect()));
		assert(tableA.getTableName().equals(guide.getFrom()));
		assert(tableA.isPrimaryKeyColumn(guide.getWhere()));
		guide.next();
		assert(tableA.isForeignKeyColumn(guide.getSelect()));
		assert(tableA.getTableName().equals(guide.getFrom()));
		assert(tableA.isPrimaryKeyColumn(guide.getWhere()));
		assert(!guide.hasMoreSteps());
		
		
				
	}
	
	// classToTableMode = 0, numOfPKCols = 2, oneTablePerAssociation = true 
	public void testCaseB() {
	}

	// classToTableMode = 1, numOfPKCols = 1, oneTablePerAssociation = false 
	public void testCaseC() {
	}
		
	public static Test suite() {
		TestSuite t=new TestSuite();
    		
    		t.addTest(new TestORMappingImp("testCaseA"));
    		t.addTest(new TestORMappingImp("testCaseB"));
    		t.addTest(new TestORMappingImp("testCaseC"));
	  	
    		return t;
	}
}