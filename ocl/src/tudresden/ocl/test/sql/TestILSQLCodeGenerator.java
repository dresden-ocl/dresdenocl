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
import tudresden.ocl.*;
import tudresden.ocl.sql.*;
import tudresden.ocl.codegen.*;
import tudresden.ocl.codegen.decl.*;
import tudresden.ocl.check.types.*;
import tudresden.ocl.parser.node.*;
import tudresden.ocl.check.types.xmifacade.*;
import java.io.*;
import java.util.*;

public class TestILSQLCodeGenerator extends TestCase {
	Model theRoughModel, theModel;
	ORMappingScheme theORMS1, theORMS2;
	ILSQLCodeGenerator theCG;
	OclTree tree;
	String constraint, sqlCode;
	CodeFragment frags[];

	public TestILSQLCodeGenerator(String n) {
		super(n);
	}
	
	protected void setUp() {
		// the type information Model for the OCL compiler
		theModel = new Model("test model");
		theModel.setRoughMode(false);
		
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
		ma.dissolve(theModel);
				
		ma = new ModelAssociation(null);
		ma.addEnd(new ModelAssociationEnd("a2",a,"0..*",false,null));
		ma.addEnd(new ModelAssociationEnd("b2",b,"0..*",false,null));
		ma.dissolve(theModel);
		
		ma = new ModelAssociation(null);
		ma.addEnd(new ModelAssociationEnd("ra1",a,"1",false,null));
		ma.addEnd(new ModelAssociationEnd("ra2",a,"1",false,null));
		ma.dissolve(theModel);
		
		ma = new ModelAssociation(null);
		ma.addEnd(new ModelAssociationEnd("a",a,"1",false,null));
		ma.addEnd(new ModelAssociationEnd("c",c,"1..*",false,null));
		ma.dissolve(theModel);
		
		theModel.flatten();	
		
		// the Model for the object relational mapping information
		theRoughModel = new Model("test model");
		theRoughModel.setRoughMode(true);
		
		pp = new ArrayList();
		pp.add("test_model");		
		a = new ModelClass(pp, "A");
		b = new ModelClass(pp, "B");
		c = new ModelClass(pp, "C");
		d = new ModelClass(pp, "D");
		e = new ModelClass(pp, "E");
		f = new ModelClass(pp, "F");
		
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
		
		b.addOperation(new ModelOperation("operationB", params, Basic.BOOLEAN, true));
		
		theRoughModel.putClassifier(a);
		theRoughModel.putClassifier(b);
		theRoughModel.putClassifier(c);
		theRoughModel.putClassifier(d);
		theRoughModel.putClassifier(e);
		theRoughModel.putClassifier(f);
		
		ma = new ModelAssociation(null);
		ma.addEnd(new ModelAssociationEnd("a1",a,"1",false,null));
		ma.addEnd(new ModelAssociationEnd("b1",b,"1..*",false,null));
		theRoughModel.putAssociation(ma);
		
		ma = new ModelAssociation(null);
		ma.addEnd(new ModelAssociationEnd("a2",a,"0..*",false,null));
		ma.addEnd(new ModelAssociationEnd("b2",b,"0..*",false,null));
		theRoughModel.putAssociation(ma);
		
		ma = new ModelAssociation(null);
		ma.addEnd(new ModelAssociationEnd("ra1",a,"1",false,null));
		ma.addEnd(new ModelAssociationEnd("ra2",a,"1",false,null));
		theRoughModel.putAssociation(ma);
		
		ma = new ModelAssociation(null);
		ma.addEnd(new ModelAssociationEnd("a",a,"1",false,null));
		ma.addEnd(new ModelAssociationEnd("c",c,"1..*",false,null));
		theRoughModel.putAssociation(ma);
		
		theRoughModel.determineAllSupertypes();		
		
		theORMS1 = new ObjectViewSchema(new ORMappingImp(theRoughModel), new OracleSQLBuilder());
		theORMS2 = new ObjectViewSchema(new ORMappingImp(theRoughModel, 1, 1, "int", false), new OracleSQLBuilder());
		theCG = new ILSQLCodeGenerator((TestILSQLCodeGenerator.class.getResource("../../codegen/decl/OCL2SQL4Oracle.xml")).toString());
		
	}	
	
	private CodeFragment[] getSQLCode(String constraint) 
	throws IOException{
		tree = OclTree.createTree(constraint, theModel);
		tree.applyDefaultNormalizations();
		return theCG.getCode(tree);
	}
	
	private boolean equal(String str1, String str2) {
		StringBuffer sb1 = new StringBuffer(), sb2 = new StringBuffer();
		
		for (int i=0; i<str1.length(); i++) {
			if ((str1.charAt(i) != '\n') && (str1.charAt(i) != ' ')) sb1.append(str1.charAt(i));
		}
		
		for (int i=0; i<str2.length(); i++) {
			if ((str2.charAt(i) != '\n') && (str2.charAt(i) != ' ')) sb2.append(str2.charAt(i));
		}
		
		str1 = sb1.toString().toUpperCase();
		str2 = sb2.toString().toUpperCase();
		
		//System.err.println(str1);
		//System.err.println(str2);
				
		return str1.equals(str2);		
	}
		
	/** 
	 * the access to attributes is possible in the following ways:
	 *   - access from the context to attributes that map to a single column
	 *   - access at the end of a navigation to attributes that map to a single column
	 *   - access to attributes from superclasses that are overwritten 
	 */
	public void testAttributeAccess() 
	throws IOException{
		/*
		// access from the context to attributes that map to one column
		theCG.setORMappingScheme(theORMS2);
		constraint = "context A inv testInv: a1 > 5";
		frags = getSQLCode(constraint);
		
		sqlCode =  "CREATE OR REPLACE VIEW testInv AS";
		sqlCode += "(select * from OV_A SELF";
		sqlCode += "where not (SELF.A1 > 5)))";
		
		assert(equal(frags[0].getCode(), sqlCode));
		
		// access at the end of a navigation to attributes that map to one column
		theCG.setORMappingScheme(theORMS1);
		constraint = "context C inv: a.a1 > 5";
		frags = getSQLCode(constraint);
				
		sqlCode =  "CREATE OR REPLACE VIEW testInv AS";
		sqlCode += "(select * from OV_C SELF";
		sqlCode += "where not ((select A1 from A, C ";
		sqlCode += "            where A.PK1 = C.APK1 ";
		sqlCode += "            and C.PK3 = SELF.PK3)";
		sqlCode += "           > 5)))";
		
		//assert(equal(frags[0].getCode(), sqlCode));		
		
		*/
		theCG.setORMappingScheme(theORMS2);
		constraint = "context A inv testInv: b2->forAll(b1 > 5)";
		frags = getSQLCode(constraint);
	}
	
	public void testPrepareJoin() {
		List guides = new ArrayList();
		Guide guide;
		
		// attribute access with navigation
		guide = new Guide(false);
		guide.add("A1","A","APK");
		guides.add(guide);
		
		guide = new Guide(true);
		guide.add("APK","A","APK");
		guide.add("AFK","C","CPK");
		guide.setAlias("SELF");
		guides.add(guide);
		
		theCG.reset();
		theCG.prepareJoin(guides);
		
		//System.err.println(theCG.getTableRepresentation());
		//System.err.println(theCG.getJoinRepresentation());
		
		assert(theCG.getTableRepresentation().equals("A TA0,C TA1"));
		assert(theCG.getJoinRepresentation().equals("(TA0.APK = TA1.AFK) and " +
							    "(TA1.CPK = SELF.CPK)"));
		
		// pur navigation over several guides
		guides.clear();
		
		guide = new Guide(true);
		guide.add("APK","A","APK");
		guide.add("AFK","AB","BFK");
		guide.add("BPK","B","BPK");
		guides.add(guide);
		
		guide = new Guide(true);
		guide.add("BPK","B","BPK");
		guide.add("BFK","BC","CFK");
		guide.add("CPK","C","CPK");
		guides.add(guide);
		
		guide = new Guide(true);
		guide.add("CPK","C","CPK");
		guide.add("CFK","CD","DFK");
		guide.add("DPK","D","DPK");
		guide.setAlias("SELF");
		guides.add(guide);
		
		theCG.reset();
		theCG.prepareJoin(guides);
		
		//System.err.println(theCG.getTableRepresentation());
		//System.err.println(theCG.getJoinRepresentation());
		
		assert(theCG.getTableRepresentation().equals("A TA0,AB TA1,B TA2,BC TA3,C TA4,CD TA5,D TA6"));
		assert(theCG.getJoinRepresentation().equals("(TA0.APK = TA1.AFK) and " +
							    "(TA1.BFK = TA2.BPK) and " +
							    "(TA2.BPK = TA3.BFK) and " +
							    "(TA3.CFK = TA4.CPK) and " +
							    "(TA4.CPK = TA5.CFK) and " +
							    "(TA5.DFK = TA6.DPK) and " +
							    "(TA6.DPK = SELF.DPK)"));
		
		// navigation to same table
		guides.clear();
		
		guide = new Guide(true);
		guide.add("APK","A","APK");
		guide.add("RAPK","A","APK");
		guide.setAlias("SELF");
		guides.add(guide);
				
		theCG.reset();
		theCG.prepareJoin(guides);
		
		//System.err.println(theCG.getTableRepresentation());
		//System.err.println(theCG.getJoinRepresentation());
		
		assert(theCG.getTableRepresentation().equals("A TA0,A TA1"));
		assert(theCG.getJoinRepresentation().equals("(TA0.APK = TA1.RAPK) and " +
							    "(TA1.APK = SELF.APK)"));				
	}
		
	public static Test suite() {
		TestSuite t=new TestSuite();

    		t.addTest(new TestILSQLCodeGenerator("testAttributeAccess"));
    		t.addTest(new TestILSQLCodeGenerator("testPrepareJoin"));
    		
    		return t;
	}
}
