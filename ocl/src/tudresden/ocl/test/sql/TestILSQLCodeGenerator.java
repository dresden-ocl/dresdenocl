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
		
		theORMS1 = new ORMappingSchemeImp(new ORMappingImp(theRoughModel));
		theORMS2 = new ORMappingSchemeImp(new ORMappingImp(theRoughModel, 1, 1, "int", false));
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
	 *   - access from the context to attributes that map to
	 *            a) one column
	 *            b) more than one column
	 *   - access at the end of a navigation to attributes that map to
	 *            a) one column
	 *            b) more than one column
	 *   - access to inherited attributes
	 *            a) one column
	 *            b) more than one column
	 */
	public void testAttributeAccess() 
	throws IOException{
		// access from the context to attributes that map to one column
		theCG.setORMappingScheme(theORMS2);
		constraint = "context A inv testInv: a1 > 5";
		frags = getSQLCode(constraint);
		
		sqlCode =  "CREATE OR REPLACE VIEW testInv AS";
		sqlCode += "(select * from A SELF";
		sqlCode += "where not (SELF.A1 > 5)))";
		
		assert(equal(frags[0].getCode(), sqlCode));
		
		// access from the context to attributes that map to more than one column
		constraint = "context B inv: b1 > 5";
		frags = getSQLCode(constraint);
		
		System.err.println(frags[0].getCode());	
		
		/*
		// access at the end of a navigation to attributes that map to one column
		theCG.setORMappingScheme(theORMS1);
		constraint = "context C inv: a.a1 > 5";
		frags = getSQLCode(constraint);
		
		System.err.println(frags[0].getCode());	
						
		// access at the end of a navigation to attributes that map to more than one column
		theCG.setORMappingScheme(theORMS1);
		constraint = "context A inv: a1 > 5";
		
		// access to inherited attributes one column
		theCG.setORMappingScheme(theORMS1);
		constraint = "context A inv: a1 > 5";
		
		// access to inherited attributes more than one column
		theCG.setORMappingScheme(theORMS1);
		constraint = "context A inv: a1 > 5";			
		*/		
	}
	
	public static Test suite() {
		TestSuite t=new TestSuite();

    		t.addTest(new TestILSQLCodeGenerator("testAttributeAccess"));
    		
    		return t;
	}
}
