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
        ORMappingScheme orm;
        OclTree tree;
        ModelFacade mf;
        String xmiSrc = (TestILSQLCodeGenerator.class.getResource("university_example.xmi")).toString();
        String ruleSrc = (TestILSQLCodeGenerator.class.getResource("../../codegen/decl/OCL2SQL4Oracle.xml")).toString();
        ILSQLCodeGenerator theCG;

	public TestILSQLCodeGenerator(String n) {
		super(n);
	}
	
	protected void setUp() {
		orm = new ObjectViewSchema(new UniversityExampleSchema(), new OracleSQLBuilder());
                theCG = new ILSQLCodeGenerator(ruleSrc);
                theCG.setORMappingScheme(orm);
                try {
                    mf = XmiParser.createModel(xmiSrc, "university example in classic mode");
                } catch(Exception e) {
                    System.err.println("XmiParser error: " + e.getMessage());
                }
	}	
	
	private String getSQLCode(String constraint) 
	throws IOException{
                CodeFragment cf[];
            
		tree = OclTree.createTree(constraint, mf);
		tree.applyDefaultNormalizations();
                cf = theCG.getCode(tree);
                return cf[0].getCode();
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
		
        public void testUniversityExampleInv() {
            String expres;
            String genres
            
            System.err.println("... generate university example invariants");
                        
            try {
            // inv1
            genres = getSQLCode("context Person inv inv1: self.supervisor.grade.value > self.grade.value");
            expres = new String();
            expres += "create or replace view inv1 as (                                                             ";
            expres += " select * from OV_PERSON SELF                                                                ";
            expres += " where not (                                                                                 ";
            expres += "     (select VALUE                                                                           ";
            expres += "      from OV_GRADE TA2,OV_PERSON TA3,OV_PERSON TA4                                          ";
            expres += "      where (TA2.GID = TA3.GRADE) and (TA3.PID = TA4.SUPERVISOR) and (TA4.PID = SELF.PID))   ";
            expres += "     >                                                                                       ";
            expres += "     (select VALUE                                                                           ";         
            expres += "      from OV_GRADE TA0,OV_PERSON TA1                                                        ";
            expres += "      where (TA0.GID = TA1.GRADE) and (TA1.PID = SELF.PID))                                  ";
            expres += "))                                                                                           ";
            assert(equal(genres, expres));
            
            // inv2
            
    
            
            } catch(IOException e) {
                System.err.println(e.toString());
            }                        
        }
        
	public static Test suite() {
		TestSuite t=new TestSuite();

    		t.addTest(new TestILSQLCodeGenerator("testPrepareJoin"));
                t.addTest(new TestILSQLCodeGenerator("testUniversityExampleInv"));
    		
    		return t;
	}
}
