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
		Table table, tableA, tableB, tableC, tableF;
		String cols[];
		Guide guide;
		
		assertTrue(map.tables().size() == 7);
		assertTrue(map.classifiers().size() == 6);
		
		tables = map.getClassTables("A");	
		assertTrue(tables.size() == 1);
		table = (Table)tables.get(0);
		tableA = table;
		assertTrue(table.getPrimaryKeyColumns().length == 1);
		assertTrue(table.getColumns().length == 6); 
		assertTrue(table.isColumn("A1"));
		assertTrue(table.isColumn("A2"));
		assertTrue(table.isColumn("A3"));
		
		tables = map.getClassTables("B");	
		assertTrue(tables.size() == 1);
		table = (Table)tables.get(0);
		tableB = table;
		assertTrue(table.getPrimaryKeyColumns().length == 1);
		assertTrue(table.getColumns().length == 5);
		assertTrue(table.isColumn("B1"));
		assertTrue(table.isColumn("B2"));
		assertTrue(table.isColumn("B3"));
		
		tables = map.getClassTables("C");	
		assertTrue(tables.size() == 1);
		table = (Table)tables.get(0);
		tableC = table;
		assertTrue(table.getPrimaryKeyColumns().length == 1);
		assertTrue(table.getColumns().length == 6);
		assertTrue(table.isColumn("C1"));
		assertTrue(table.isColumn("C2"));
		assertTrue(table.isColumn("C3"));
		assertTrue(!table.isColumn("C4"));
		assertTrue(map.associationEnds("C").size() == 2);
		
		tables = map.getClassTables("D");	
		assertTrue(tables.size() == 1);
		table = (Table)tables.get(0);
		assertTrue(table.getPrimaryKeyColumns().length == 1);
		assertTrue(table.getColumns().length == 2);
		assertTrue(table.isColumn("D1"));
						
		tables = map.getClassTables("E");	
		assertTrue(tables.size() == 1);
		table = (Table)tables.get(0);
		assertTrue(table.getPrimaryKeyColumns().length == 1);
		assertTrue(table.getColumns().length == 2);
		assertTrue(table.isColumn("E1"));
		
		tables = map.getClassTables("F");
		assertTrue(tables.size() == 1);
		table = (Table)tables.get(0);
		tableF = table;
		assertTrue(table.getPrimaryKeyColumns().length == 1);
		assertTrue(table.getColumns().length == 4);
		assertTrue(table.isColumn("F1"));
		assertTrue(table.isColumn("F2"));
		assertTrue(table.isColumn("F3"));
		
		table = null;
		for (Iterator i=map.tables().iterator(); i.hasNext(); ) {
			table = (Table)i.next();
			if (table.getTableName().equals(ORMappingImp.ASSTABNAME + "1")) break;
		}
		cols = table.getColumns();
		assertTrue(table.isForeignKeyColumn(cols[0]) && table.isForeignKeyColumn(cols[1]));		
		assertTrue(tableA.isPrimaryKeyColumn(table.getForeignColumn(cols[0])) || tableA.isPrimaryKeyColumn(table.getForeignColumn(cols[1])));
		assertTrue(tableB.isPrimaryKeyColumn(table.getForeignColumn(cols[0])) || tableB.isPrimaryKeyColumn(table.getForeignColumn(cols[1])));
		
		assertTrue(map.directSupertypeNames("D").contains("B"));
		assertTrue(map.directSupertypeNames("E").contains("B"));
		assertTrue(map.directSupertypeNames("D").size() == 1);
		assertTrue(map.directSupertypeNames("E").size() == 1);
		assertTrue(map.directSupertypeNames("A").size() == 0);
		assertTrue(map.directSupertypeNames("B").size() == 0);
		assertTrue(map.directSupertypeNames("C").size() == 0);
		assertTrue(map.directSupertypeNames("F").size() == 0);
		
		assertTrue(map.operations("B").contains("operationB"));
		assertTrue(map.operations("B").size() == 1);
		assertTrue(map.operations("A").size() == 0);
		assertTrue(map.operations("C").size() == 0);
		assertTrue(map.operations("D").size() == 0);
		assertTrue(map.operations("E").size() == 0);
		assertTrue(map.operations("F").size() == 0);
		
		guides = map.guidesToAssociationEnds("A", "c");
		assertTrue(guides.size() == 1);
		guide = (Guide)guides.get(0);
		//System.err.println(guide.toString());
		guide.next();
		assertTrue(tableC.isPrimaryKeyColumn(guide.getSelect()));
		assertTrue(tableC.getTableName().equals(guide.getFrom()));
		assertTrue(tableC.isForeignKeyColumn(guide.getWhere()));
		guide.next();
		assertTrue(tableA.isPrimaryKeyColumn(guide.getSelect()));
		assertTrue(tableA.getTableName().equals(guide.getFrom()));
		assertTrue(tableA.isPrimaryKeyColumn(guide.getWhere()));
		assertTrue(!guide.hasMoreSteps());
		
		guides = map.guidesToAssociationEnds("C", "a");
		assertTrue(guides.size() == 1);
		guide = (Guide)guides.get(0);
		//System.err.println(guide.toString());
		guide.next();
		assertTrue(tableA.isPrimaryKeyColumn(guide.getSelect()));
		assertTrue(tableA.getTableName().equals(guide.getFrom()));
		assertTrue(tableA.isPrimaryKeyColumn(guide.getWhere()));
		guide.next();
		assertTrue(tableC.isForeignKeyColumn(guide.getSelect()));
		assertTrue(tableC.getTableName().equals(guide.getFrom()));
		assertTrue(tableC.isPrimaryKeyColumn(guide.getWhere()));
		assertTrue(!guide.hasMoreSteps());
		
		guides = map.guidesToAssociationEnds("C", "c4");
		assertTrue(guides.size() == 1);
		guide = (Guide)guides.get(0);
		//System.err.println(guide.toString());
		guide.next();
		assertTrue(tableF.isPrimaryKeyColumn(guide.getSelect()));
		assertTrue(tableF.getTableName().equals(guide.getFrom()));
		assertTrue(tableF.isPrimaryKeyColumn(guide.getWhere()));
		guide.next();
		assertTrue(tableC.isForeignKeyColumn(guide.getSelect()));
		assertTrue(tableC.getTableName().equals(guide.getFrom()));
		assertTrue(tableC.isPrimaryKeyColumn(guide.getWhere()));
		assertTrue(!guide.hasMoreSteps());
		
		guides = map.guidesToAssociationEnds("A", "ra1");
		assertTrue(guides.size() == 1);
		guide = (Guide)guides.get(0);
		//System.err.println(guide.toString());
		guide.next();
		assertTrue(tableA.isPrimaryKeyColumn(guide.getSelect()));
		assertTrue(tableA.getTableName().equals(guide.getFrom()));
		assertTrue(tableA.isPrimaryKeyColumn(guide.getWhere()));
		guide.next();
		assertTrue(tableA.isForeignKeyColumn(guide.getSelect()));
		assertTrue(tableA.getTableName().equals(guide.getFrom()));
		assertTrue(tableA.isPrimaryKeyColumn(guide.getWhere()));
		assertTrue(!guide.hasMoreSteps());
		
		guides = map.guidesToAssociationEnds("A", "ra2");
		assertTrue(guides.size() == 1);
		guide = (Guide)guides.get(0);
		//System.err.println(guide.toString());
		guide.next();
		assertTrue(tableA.isPrimaryKeyColumn(guide.getSelect()));
		assertTrue(tableA.getTableName().equals(guide.getFrom()));
		assertTrue(tableA.isPrimaryKeyColumn(guide.getWhere()));
		guide.next();
		assertTrue(tableA.isForeignKeyColumn(guide.getSelect()));
		assertTrue(tableA.getTableName().equals(guide.getFrom()));
		assertTrue(tableA.isPrimaryKeyColumn(guide.getWhere()));
		assertTrue(!guide.hasMoreSteps());
		
		guides = map.guidesToAssociationEnds("A", "b1");
		assertTrue(guides.size() == 1);
		guide = (Guide)guides.get(0);
		//System.err.println(guide.toString());
		guide.next();
		assertTrue(tableB.isPrimaryKeyColumn(guide.getSelect()));
		assertTrue(tableB.getTableName().equals(guide.getFrom()));
		assertTrue(tableB.isForeignKeyColumn(guide.getWhere()));
		guide.next();
		assertTrue(tableA.isPrimaryKeyColumn(guide.getSelect()));
		assertTrue(tableA.getTableName().equals(guide.getFrom()));
		assertTrue(tableA.isPrimaryKeyColumn(guide.getWhere()));
		assertTrue(!guide.hasMoreSteps());
		
		guides = map.guidesToAssociationEnds("B", "a1");
		assertTrue(guides.size() == 1);
		guide = (Guide)guides.get(0);
		//System.err.println(guide.toString());
		guide.next();
		assertTrue(tableA.isPrimaryKeyColumn(guide.getSelect()));
		assertTrue(tableA.getTableName().equals(guide.getFrom()));
		assertTrue(tableA.isPrimaryKeyColumn(guide.getWhere()));
		guide.next();
		assertTrue(tableB.isForeignKeyColumn(guide.getSelect()));
		assertTrue(tableB.getTableName().equals(guide.getFrom()));
		assertTrue(tableB.isPrimaryKeyColumn(guide.getWhere()));
		assertTrue(!guide.hasMoreSteps());
		
		guides = map.guidesToAssociationEnds("A", "b2");
		assertTrue(guides.size() == 1);
		guide = (Guide)guides.get(0);
		//System.err.println(guide.toString());
		guide.next();
		assertTrue(tableB.isPrimaryKeyColumn(guide.getSelect()));
		assertTrue(tableB.getTableName().equals(guide.getFrom()));
		assertTrue(tableB.isPrimaryKeyColumn(guide.getWhere()));
		guide.next();
		table = map.getTable(guide.getFrom());
		assertTrue(table.isForeignKeyColumn(guide.getSelect()));
		assertTrue(table.isForeignKeyColumn(guide.getWhere()));
		guide.next();
		assertTrue(tableA.isPrimaryKeyColumn(guide.getSelect()));
		assertTrue(tableA.getTableName().equals(guide.getFrom()));
		assertTrue(tableA.isPrimaryKeyColumn(guide.getWhere()));
		assertTrue(!guide.hasMoreSteps());
		
		guides = map.guidesToAssociationEnds("B", "a2");
		assertTrue(guides.size() == 1);
		guide = (Guide)guides.get(0);
		//System.err.println(guide.toString());
		guide.next();
		assertTrue(tableA.isPrimaryKeyColumn(guide.getSelect()));
		assertTrue(tableA.getTableName().equals(guide.getFrom()));
		assertTrue(tableA.isPrimaryKeyColumn(guide.getWhere()));
		guide.next();
		table = map.getTable(guide.getFrom());
		assertTrue(table.isForeignKeyColumn(guide.getSelect()));
		assertTrue(table.isForeignKeyColumn(guide.getWhere()));
		guide.next();
		assertTrue(tableB.isPrimaryKeyColumn(guide.getSelect()));
		assertTrue(tableB.getTableName().equals(guide.getFrom()));
		assertTrue(tableB.isPrimaryKeyColumn(guide.getWhere()));
		assertTrue(!guide.hasMoreSteps());		
	}
	
	// classToTableMode = 0, numOfPKCols = 2, oneTablePerAssociation = true 
	public void testCaseB() {
		ORMappingImp map = new ORMappingImp(theModel, 0, 2, "String", true);
		List tables, guides;
		Table table, tableA, tableB, tableC;
		String cols[];
		Guide guide;
		
		assertTrue(map.tables().size() == 8);
		assertTrue(map.classifiers().size() == 6);
				
		tables = map.getClassTables("A");	
		assertTrue(tables.size() == 1);
		table = (Table)tables.get(0);
		tableA = table;
		assertTrue(table.getPrimaryKeyColumns().length == 2);
		assertTrue(table.getColumns().length == 5); 
		assertTrue(table.isColumn("A1"));
		assertTrue(table.isColumn("A2"));
		assertTrue(table.isColumn("A3"));
		
		tables = map.getClassTables("B");	
		assertTrue(tables.size() == 1);
		table = (Table)tables.get(0);
		tableB = table;
		assertTrue(table.getPrimaryKeyColumns().length == 2);
		assertTrue(table.getColumns().length == 7);
		assertTrue(table.isColumn("B1"));
		assertTrue(table.isColumn("B2"));
		assertTrue(table.isColumn("B3"));
		assertTrue(table.isColumn("D1"));
		assertTrue(table.isColumn("E1"));
		
		tables = map.getClassTables("D");	
		assertTrue(tables.size() == 1);
		table = (Table)tables.get(0);
		tableB = table;
		assertTrue(table.getPrimaryKeyColumns().length == 2);
		assertTrue(table.getColumns().length == 7);
		assertTrue(table.isColumn("B1"));
		assertTrue(table.isColumn("B2"));
		assertTrue(table.isColumn("B3"));
		assertTrue(table.isColumn("D1"));
		assertTrue(table.isColumn("E1"));
		
		tables = map.getClassTables("E");	
		assertTrue(tables.size() == 1);
		table = (Table)tables.get(0);
		tableB = table;
		assertTrue(table.getPrimaryKeyColumns().length == 2);
		assertTrue(table.getColumns().length == 7);
		assertTrue(table.isColumn("B1"));
		assertTrue(table.isColumn("B2"));
		assertTrue(table.isColumn("B3"));
		assertTrue(table.isColumn("D1"));
		assertTrue(table.isColumn("E1"));
		
		tables = map.getClassTables("C");	
		assertTrue(tables.size() == 1);
		table = (Table)tables.get(0);
		tableC = table;
		assertTrue(table.getPrimaryKeyColumns().length == 2);
		assertTrue(table.getColumns().length == 7);
		assertTrue(table.isColumn("C1"));
		assertTrue(table.isColumn("C2"));
		assertTrue(table.isColumn("C3"));
				
		tables = map.getClassTables("F");	
		assertTrue(tables.size() == 1);
		table = (Table)tables.get(0);
		assertTrue(table.getPrimaryKeyColumns().length == 2);
		assertTrue(table.getColumns().length == 5);
		assertTrue(table.isColumn("F1"));
		assertTrue(table.isColumn("F2"));
		assertTrue(table.isColumn("F3"));
		
		map.getTable(ORMappingImp.ASSTABNAME + 1);
		map.getTable(ORMappingImp.ASSTABNAME + 2);
		map.getTable(ORMappingImp.ASSTABNAME + 3);
		map.getTable(ORMappingImp.ASSTABNAME + 4);
		
		assertTrue(map.directSupertypeNames("D").contains("B"));
		assertTrue(map.directSupertypeNames("E").contains("B"));
		assertTrue(map.directSupertypeNames("D").size() == 1);
		assertTrue(map.directSupertypeNames("E").size() == 1);
		assertTrue(map.directSupertypeNames("A").size() == 0);
		assertTrue(map.directSupertypeNames("B").size() == 0);
		assertTrue(map.directSupertypeNames("C").size() == 0);
		assertTrue(map.directSupertypeNames("F").size() == 0);
		
		assertTrue(map.operations("B").contains("operationB"));
		assertTrue(map.operations("B").size() == 1);
		assertTrue(map.operations("A").size() == 0);
		assertTrue(map.operations("C").size() == 0);
		assertTrue(map.operations("D").size() == 0);
		assertTrue(map.operations("E").size() == 0);
		assertTrue(map.operations("F").size() == 0);
		
		guides = map.guidesToAssociationEnds("A", "c");
		assertTrue(guides.size() == 1);
		guide = (Guide)guides.get(0);
		//System.err.println(guide.toString());
		assertTrue(guide.numberOfSteps() == 3);
		
		guides = map.guidesToAssociationEnds("C", "a");
		assertTrue(guides.size() == 1);
		guide = (Guide)guides.get(0);
		//System.err.println(guide.toString());
		assertTrue(guide.numberOfSteps() == 3);
		
		guides = map.guidesToAssociationEnds("A", "ra1");
		assertTrue(guides.size() == 1);
		guide = (Guide)guides.get(0);
		//System.err.println(guide.toString());
		assertTrue(guide.numberOfSteps() == 3);
		
		guides = map.guidesToAssociationEnds("A", "ra2");
		assertTrue(guides.size() == 1);
		guide = (Guide)guides.get(0);
		//System.err.println(guide.toString());
		assertTrue(guide.numberOfSteps() == 3);
		
		guides = map.guidesToAssociationEnds("A", "b1");
		assertTrue(guides.size() == 1);
		guide = (Guide)guides.get(0);
		//System.err.println(guide.toString());
		assertTrue(guide.numberOfSteps() == 3);
		
		guides = map.guidesToAssociationEnds("B", "a1");
		assertTrue(guides.size() == 1);
		guide = (Guide)guides.get(0);
		//System.err.println(guide.toString());
		assertTrue(guide.numberOfSteps() == 3);
		
		guides = map.guidesToAssociationEnds("A", "b2");
		assertTrue(guides.size() == 1);
		guide = (Guide)guides.get(0);
		//System.err.println(guide.toString());
		assertTrue(guide.numberOfSteps() == 3);
		
		guides = map.guidesToAssociationEnds("B", "a2");
		assertTrue(guides.size() == 1);
		guide = (Guide)guides.get(0);
		//System.err.println(guide.toString());
		assertTrue(guide.numberOfSteps() == 3);			
	}

	// classToTableMode = 1, numOfPKCols = 1, oneTablePerAssociation = false 
	public void testCaseC() {
		ORMappingImp map = new ORMappingImp(theModel, 1, 1, "int", false);
		List tables, guides;
		Table table, tableA, tableBD, tableBE, tableC;
		String cols[];
		Guide guide;
		
		assertTrue(map.tables().size() == 6);
		assertTrue(map.classifiers().size() == 6);
		
		tables = map.getClassTables("A");	
		assertTrue(tables.size() == 1);
		table = (Table)tables.get(0);
		tableA = table;
		assertTrue(table.getPrimaryKeyColumns().length == 1);
		assertTrue(table.getColumns().length == 6); 
		assertTrue(table.isColumn("A1"));
		assertTrue(table.isColumn("A2"));
		assertTrue(table.isColumn("A3"));
		
		tables = map.getClassTables("B");	
		assertTrue(tables.size() == 2);
						
		tables = map.getClassTables("C");	
		assertTrue(tables.size() == 1);
		table = (Table)tables.get(0);
		tableC = table;
		assertTrue(table.getPrimaryKeyColumns().length == 1);
		assertTrue(table.getColumns().length == 6);
		assertTrue(table.isColumn("C1"));
		assertTrue(table.isColumn("C2"));
		assertTrue(table.isColumn("C3"));
		
		tables = map.getClassTables("D");	
		assertTrue(tables.size() == 1);
		table = (Table)tables.get(0);
		tableBD = table;
		assertTrue(table.getPrimaryKeyColumns().length == 1);
		assertTrue(table.getColumns().length == 6);
		assertTrue(table.isColumn("D1"));
		assertTrue(table.isColumn("B1"));
		assertTrue(table.isColumn("B2"));
		assertTrue(table.isColumn("B3"));
						
		tables = map.getClassTables("E");	
		assertTrue(tables.size() == 1);
		table = (Table)tables.get(0);
		tableBE = table;
		assertTrue(table.getPrimaryKeyColumns().length == 1);
		assertTrue(table.getColumns().length == 6);
		assertTrue(table.isColumn("E1"));
		assertTrue(table.isColumn("B1"));
		assertTrue(table.isColumn("B2"));
		assertTrue(table.isColumn("B3"));
		
		tables = map.getClassTables("F");	
		assertTrue(tables.size() == 1);
		table = (Table)tables.get(0);
		assertTrue(table.getPrimaryKeyColumns().length == 1);
		assertTrue(table.getColumns().length == 4);
		assertTrue(table.isColumn("F1"));
		assertTrue(table.isColumn("F2"));
		assertTrue(table.isColumn("F3"));
		
		table = null;
		for (Iterator i=map.tables().iterator(); i.hasNext(); ) {
			table = (Table)i.next();
			if (table.getTableName().equals(ORMappingImp.ASSTABNAME + "1")) break;
		}
		cols = table.getColumns();
		assertTrue(table.isForeignKeyColumn(cols[0]) && table.isForeignKeyColumn(cols[1]) && table.isForeignKeyColumn(cols[2]));		
		assertTrue(tableA.isPrimaryKeyColumn(table.getForeignColumn(cols[0])) || 
		       tableA.isPrimaryKeyColumn(table.getForeignColumn(cols[1])) ||
		       tableA.isPrimaryKeyColumn(table.getForeignColumn(cols[2])));
		assertTrue(tableBD.isPrimaryKeyColumn(table.getForeignColumn(cols[0])) || 
		       tableBD.isPrimaryKeyColumn(table.getForeignColumn(cols[1])) ||
		       tableBD.isPrimaryKeyColumn(table.getForeignColumn(cols[2])));
		assertTrue(tableBE.isPrimaryKeyColumn(table.getForeignColumn(cols[0])) || 
		       tableBE.isPrimaryKeyColumn(table.getForeignColumn(cols[1])) ||
		       tableBE.isPrimaryKeyColumn(table.getForeignColumn(cols[2])));
		
		assertTrue(map.directSupertypeNames("D").contains("B"));
		assertTrue(map.directSupertypeNames("E").contains("B"));
		assertTrue(map.directSupertypeNames("D").size() == 1);
		assertTrue(map.directSupertypeNames("E").size() == 1);
		assertTrue(map.directSupertypeNames("A").size() == 0);
		assertTrue(map.directSupertypeNames("B").size() == 0);
		assertTrue(map.directSupertypeNames("C").size() == 0);
		assertTrue(map.directSupertypeNames("F").size() == 0);
		
				
		assertTrue(map.operations("B").contains("operationB"));
		assertTrue(map.operations("B").size() == 1);
		assertTrue(map.operations("A").size() == 0);
		assertTrue(map.operations("C").size() == 0);
		assertTrue(map.operations("D").size() == 0);
		assertTrue(map.operations("E").size() == 0);
		assertTrue(map.operations("F").size() == 0);
		
		
		guides = map.guidesToAssociationEnds("A", "c");
		assertTrue(guides.size() == 1);
		guide = (Guide)guides.get(0);
		//System.err.println(guide.toString());
		guide.next();
		assertTrue(tableC.isPrimaryKeyColumn(guide.getSelect()));
		assertTrue(tableC.getTableName().equals(guide.getFrom()));
		assertTrue(tableC.isForeignKeyColumn(guide.getWhere()));
		guide.next();
		assertTrue(tableA.isPrimaryKeyColumn(guide.getSelect()));
		assertTrue(tableA.getTableName().equals(guide.getFrom()));
		assertTrue(tableA.isPrimaryKeyColumn(guide.getWhere()));
		assertTrue(!guide.hasMoreSteps());
		
		guides = map.guidesToAssociationEnds("C", "a");
		assertTrue(guides.size() == 1);
		guide = (Guide)guides.get(0);
		//System.err.println(guide.toString());
		guide.next();
		assertTrue(tableA.isPrimaryKeyColumn(guide.getSelect()));
		assertTrue(tableA.getTableName().equals(guide.getFrom()));
		assertTrue(tableA.isPrimaryKeyColumn(guide.getWhere()));
		guide.next();
		assertTrue(tableC.isForeignKeyColumn(guide.getSelect()));
		assertTrue(tableC.getTableName().equals(guide.getFrom()));
		assertTrue(tableC.isPrimaryKeyColumn(guide.getWhere()));
		assertTrue(!guide.hasMoreSteps());
		
		guides = map.guidesToAssociationEnds("A", "ra1");
		assertTrue(guides.size() == 1);
		guide = (Guide)guides.get(0);
		//System.err.println(guide.toString());
		guide.next();
		assertTrue(tableA.isPrimaryKeyColumn(guide.getSelect()));
		assertTrue(tableA.getTableName().equals(guide.getFrom()));
		assertTrue(tableA.isPrimaryKeyColumn(guide.getWhere()));
		guide.next();
		assertTrue(tableA.isForeignKeyColumn(guide.getSelect()));
		assertTrue(tableA.getTableName().equals(guide.getFrom()));
		assertTrue(tableA.isPrimaryKeyColumn(guide.getWhere()));
		assertTrue(!guide.hasMoreSteps());
		
		guides = map.guidesToAssociationEnds("A", "ra2");
		assertTrue(guides.size() == 1);
		guide = (Guide)guides.get(0);
		//System.err.println(guide.toString());
		guide.next();
		assertTrue(tableA.isPrimaryKeyColumn(guide.getSelect()));
		assertTrue(tableA.getTableName().equals(guide.getFrom()));
		assertTrue(tableA.isPrimaryKeyColumn(guide.getWhere()));
		guide.next();
		assertTrue(tableA.isForeignKeyColumn(guide.getSelect()));
		assertTrue(tableA.getTableName().equals(guide.getFrom()));
		assertTrue(tableA.isPrimaryKeyColumn(guide.getWhere()));
		assertTrue(!guide.hasMoreSteps());
		
		
		guides = map.guidesToAssociationEnds("A", "b1");
		assertTrue(guides.size() == 2);
		for (int i=0; i<2; i++) {
			guide = (Guide)guides.get(i);
			//System.err.println(guide.toString());
			guide.next();
			if (guide.getFrom().equals("D")) {
				assertTrue(tableBD.isPrimaryKeyColumn(guide.getSelect()));
				assertTrue(tableBD.getTableName().equals(guide.getFrom()));
				assertTrue(tableBD.isForeignKeyColumn(guide.getWhere()));
			} else {
				assertTrue(tableBE.isPrimaryKeyColumn(guide.getSelect()));
				assertTrue(tableBE.getTableName().equals(guide.getFrom()));
				assertTrue(tableBE.isForeignKeyColumn(guide.getWhere()));
			}
			guide.next();
			assertTrue(tableA.isPrimaryKeyColumn(guide.getSelect()));
			assertTrue(tableA.getTableName().equals(guide.getFrom()));
			assertTrue(tableA.isPrimaryKeyColumn(guide.getWhere()));
			assertTrue(!guide.hasMoreSteps());
		}
		
		
		guides = map.guidesToAssociationEnds("B", "a1");
		assertTrue(guides.size() == 2);
		for (int i=0; i<2; i++) {
			guide = (Guide)guides.get(i);
			//System.err.println(guide.toString());
			guide.next();
			assertTrue(tableA.isPrimaryKeyColumn(guide.getSelect()));
			assertTrue(tableA.getTableName().equals(guide.getFrom()));
			assertTrue(tableA.isPrimaryKeyColumn(guide.getWhere()));
			guide.next();
			if (guide.getFrom().equals("D")) {
				assertTrue(tableBD.isForeignKeyColumn(guide.getSelect()));
				assertTrue(tableBD.getTableName().equals(guide.getFrom()));
				assertTrue(tableBD.isPrimaryKeyColumn(guide.getWhere()));
			} else {
				assertTrue(tableBE.isForeignKeyColumn(guide.getSelect()));
				assertTrue(tableBE.getTableName().equals(guide.getFrom()));
				assertTrue(tableBE.isPrimaryKeyColumn(guide.getWhere()));
			}
			assertTrue(!guide.hasMoreSteps());
		}
		
		guides = map.guidesToAssociationEnds("A", "b2");
		assertTrue(guides.size() == 2);
		for (int i=0; i<2; i++) {
			guide = (Guide)guides.get(i);
			//System.err.println(guide.toString());
			guide.next();
			if (guide.getFrom().equals("D")) {
				assertTrue(tableBD.isPrimaryKeyColumn(guide.getSelect()));
				assertTrue(tableBD.getTableName().equals(guide.getFrom()));
				assertTrue(tableBD.isPrimaryKeyColumn(guide.getWhere()));
			} else {
				assertTrue(tableBE.isPrimaryKeyColumn(guide.getSelect()));
				assertTrue(tableBE.getTableName().equals(guide.getFrom()));
				assertTrue(tableBE.isPrimaryKeyColumn(guide.getWhere()));
			}
			guide.next();
			table = map.getTable(guide.getFrom());
			assertTrue(table.isForeignKeyColumn(guide.getSelect()));
			assertTrue(table.isForeignKeyColumn(guide.getWhere()));
			guide.next();
			assertTrue(tableA.isPrimaryKeyColumn(guide.getSelect()));
			assertTrue(tableA.getTableName().equals(guide.getFrom()));
			assertTrue(tableA.isPrimaryKeyColumn(guide.getWhere()));
			assertTrue(!guide.hasMoreSteps());
		}
		
		guides = map.guidesToAssociationEnds("B", "a2");
		assertTrue(guides.size() == 2);
		for (int i=0; i<2; i++) {
			guide = (Guide)guides.get(i);
			//System.err.println(guide.toString());
			guide.next();
			assertTrue(tableA.isPrimaryKeyColumn(guide.getSelect()));
			assertTrue(tableA.getTableName().equals(guide.getFrom()));
			assertTrue(tableA.isPrimaryKeyColumn(guide.getWhere()));
			guide.next();
			table = map.getTable(guide.getFrom());
			assertTrue(table.isForeignKeyColumn(guide.getSelect()));
			assertTrue(table.isForeignKeyColumn(guide.getWhere()));
			guide.next();
			if (guide.getFrom().equals("D")) {
				assertTrue(tableBD.isPrimaryKeyColumn(guide.getSelect()));
				assertTrue(tableBD.getTableName().equals(guide.getFrom()));
				assertTrue(tableBD.isPrimaryKeyColumn(guide.getWhere()));
			} else {
				assertTrue(tableBE.isPrimaryKeyColumn(guide.getSelect()));
				assertTrue(tableBE.getTableName().equals(guide.getFrom()));
				assertTrue(tableBE.isPrimaryKeyColumn(guide.getWhere()));
			}			
			assertTrue(!guide.hasMoreSteps());
		}		
	}
	
	// test of methode associationEnds
	public void testAssociationEndAccess() {
		ORMappingImp map = new ORMappingImp(theModel);
		Map assEnds;
		
		assEnds = map.associationEnds("A");
		assertTrue(assEnds.size() == 5);
		assertTrue(assEnds.get("ra1").equals("A"));
		assertTrue(assEnds.get("ra2").equals("A"));
		assertTrue(assEnds.get("b1").equals("B"));
		assertTrue(assEnds.get("b2").equals("B"));
		assertTrue(assEnds.get("c").equals("C"));
		
		assEnds = map.associationEnds("B");
		assertTrue(assEnds.size() == 2);
		assertTrue(assEnds.get("a1").equals("A"));
		assertTrue(assEnds.get("a2").equals("A"));
		
		assEnds = map.associationEnds("C");
		assertTrue(assEnds.size() == 2);
		assertTrue(assEnds.get("a").equals("A"));
		
		assEnds = map.associationEnds("D");
		assertTrue(assEnds.size() == 0);
			
		assEnds = map.associationEnds("E");
		assertTrue(assEnds.size() == 0);
			
		assEnds = map.associationEnds("F");
		assertTrue(assEnds.size() == 0);						
	}
			
	public static Test suite() {
		TestSuite t=new TestSuite();
    		
    		t.addTest(new TestORMappingImp("testCaseA"));
    		t.addTest(new TestORMappingImp("testCaseB"));
    		t.addTest(new TestORMappingImp("testCaseC"));
    		t.addTest(new TestORMappingImp("testAssociationEndAccess"));
	  	
    		return t;
	}
}