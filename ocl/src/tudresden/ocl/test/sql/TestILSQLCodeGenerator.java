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
import tudresden.ocl.test.*;
import java.io.*;
import java.util.*;
import java.net.URL;

public class TestILSQLCodeGenerator extends TestCase {
        ORMappingScheme orm;
        OclTree tree;
        ModelFacade mf;
        final URL xmiSrc = TestILSQLCodeGenerator.class.getResource("university_example.xmi");
        String ruleSrc = TestILSQLCodeGenerator.class.getResource("../../codegen/decl/OCL2SQL4Oracle.xml").toString();
        ILSQLCodeGenerator theCG;

	public TestILSQLCodeGenerator(String n) {
		super(n);
	}

	protected void setUp() throws Exception {
      super.setUp();
		orm = new ObjectViewSchema(new UniversityExampleSchema(), new OracleSQLBuilder());
                theCG = new ILSQLCodeGenerator(ruleSrc);
                theCG.setORMappingScheme(orm);
                Model model = XmiParser.createModel(xmiSrc, "university example in classic mode");
                final File actual = new File(new File(System.getProperty(tudresden.ocl.injection.test.TestInjection.TEMP_DIR)), "university_example.xmi.debug");
                model.printData(new PrintStream(new FileOutputStream(actual)));
                Diff.diff(new DiffSource(getClass().getResource("university_example.xmi.debug")), new DiffSource(actual));
                mf = model;
	}

   protected void tearDown() throws Exception {
     mf=null;
     super.tearDown();
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

	public void testPrepareJoin() throws Exception {
                List guides = new ArrayList();
		Guide guide;
                theCG.setJoinMode(true);

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
                theCG.prepareNavigation(guides);

		//System.err.println(theCG.getTableRepresentation());
		//System.err.println(theCG.getJoinRepresentation());

		assertTrue(theCG.getTableRepresentation().equals("A TA0,C TA1"));
		assertTrue(theCG.getJoinRepresentation().equals("(TA0.APK = TA1.AFK) and " +
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
		theCG.prepareNavigation(guides);

		//System.err.println(theCG.getTableRepresentation());
		//System.err.println(theCG.getJoinRepresentation());

		assertTrue(theCG.getTableRepresentation().equals("A TA0,AB TA1,B TA2,BC TA3,C TA4,CD TA5,D TA6"));
		assertTrue(theCG.getJoinRepresentation().equals("(TA0.APK = TA1.AFK) and " +
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
		theCG.prepareNavigation(guides);

		//System.err.println(theCG.getTableRepresentation());
		//System.err.println(theCG.getJoinRepresentation());

		assertTrue(theCG.getTableRepresentation().equals("A TA0,A TA1"));
		assertTrue(theCG.getJoinRepresentation().equals("(TA0.APK = TA1.RAPK) and " +
							    "(TA1.APK = SELF.APK)"));

                theCG.setJoinMode(false);
	}

        public void testPrepareDerivedTable() throws Exception{
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
		theCG.prepareNavigation(guides);

                //System.err.println(theCG.getTableRepresentation());

        	assertTrue(equal(theCG.getTableRepresentation(),
                             "(select A1 from A where APK in" +
                             "(select AFK from C where CPK = SELF.CPK))"
                             )
                );
		assertTrue(theCG.getJoinRepresentation().equals(""));

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
		theCG.prepareNavigation(guides);

                //System.err.println(theCG.getTableRepresentation());

		assertTrue(equal(theCG.getTableRepresentation(),
                             "(select APK from A where APK in\n" +
                             "(select AFK from AB where BFK in\n" +
                             "(select BPK from B where BPK in\n" +
                             "(select BFK from BC where CFK in\n" +
                             "(select CPK from C where CPK in\n" +
                             "(select CFK from CD where DFK in\n" +
                             "(select DPK from D where DPK = SELF.DPK)))))))"
                ));
		assertTrue(theCG.getJoinRepresentation().equals(""));

		// navigation to same table
		guides.clear();

		guide = new Guide(true);
		guide.add("APK","A","APK");
		guide.add("RAPK","A","APK");
		guide.setAlias("SELF");
		guides.add(guide);

		theCG.reset();
		theCG.prepareNavigation(guides);

                //System.err.println(theCG.getTableRepresentation());

                assertTrue(equal(theCG.getTableRepresentation(),
                             "(select APK from A where APK in\n" +
                             "(select RAPK from A where APK = SELF.APK))"
                ));
		assertTrue(theCG.getJoinRepresentation().equals(""));
        }

        public void testUniversityExampleInv() throws Exception{
            String expres;
            String genres;

            //System.err.println("... generate university example invariants");

            // inv1
            genres = getSQLCode("context Person inv inv1: self.supervisor.grade.value > self.grade.value");
            expres = new String();
            expres += "create or replace view inv1 as (                                                             ";
            expres += " select * from OV_PERSON SELF                                                                ";
            expres += " where not (                                                                                 ";
            expres += "     (select VALUE from OV_GRADE where GID in                                                ";
            expres += "     (select GRADE from OV_PERSON where PID in                                               ";
            expres += "     (select SUPERVISOR from OV_PERSON where PID = SELF.PID)))                               ";
            expres += "     >                                                                                       ";
            expres += "     (select VALUE from OV_GRADE where GID in                                                ";
            expres += "     (select GRADE from OV_PERSON where PID = SELF.PID))                                     ";
            expres += "))                                                                                           ";
            assertTrue(equal(genres, expres));

            // inv2
            genres = getSQLCode("context Faculty inv inv2: self.subFacility->size >= 2");
            expres = new String();
            expres += "create or replace view inv2 as (                                                             ";
            expres += " select * from OV_FACULTY SELF                                                               ";
            expres += " where not ((select NVL(COUNT(*),0)                                                          ";
            expres += "             from OV_FACILITY                                                                ";
            expres += "             where FID in                                                                    ";
            expres += "             (select FID from OV_FACILITY where SUPERFACILITY in                             ";
            expres += "              (select FID from OV_FACILITY where FID = SELF.FID)))                           ";
            expres += "            >= 2))                                                                           ";
            assertTrue(equal(genres, expres));

            // inv3
            genres = getSQLCode("context Faculty inv inv3: self.subFacility->forAll(oclIsTypeOf(Institute))");
            expres = new String();
            expres += "create or replace view inv3 as (                                                             ";
            expres += " select * from OV_FACULTY SELF                                                               ";
            expres += " where not (not exists (                                                                     ";
            expres += "                        (select FID from OV_FACILITY where SUPERFACILITY in                  ";
            expres += "                        (select FID from OV_FACILITY where FID = SELF.FID))                  ";
            expres += "                         minus                                                               ";
            expres += "                        select FID from OV_FACILITY TUDOCLITER0                              ";
            expres += "                        where exists (                                                       ";
            expres += "                                      select FID from OV_INSTITUTE                           ";
            expres += "                                      where FID = TUDOCLITER0.FID)                           ";
            expres += "                              and not exists (                                               ";
            expres += "                                      select FID from OV_FACILITY                            ";
            expres += "                                      where FID = TUDOCLITER0.FID)                           ";
            expres += "            )                                                                                ";
            expres += "))                                                                                           ";
            assertTrue(equal(genres, expres));

            // inv4
            genres = getSQLCode("context Employee inv inv4: ((self.grade.name = 'diploma') implies (self.taxClass = 'tc1'))" +
                                "                      and  ((self.grade.name = 'doctor') implies (self.taxClass = 'tc2'))" +
                                "                      and  ((self.grade.name = 'professor') implies (self.taxClass = 'tc3'))"
            );
            expres = new String();
            expres += "create or replace view inv4 as (                                                             ";
            expres += " select * from OV_EMPLOYEE SELF                                                              ";
            expres += " where not ((NOT ((select NAME from OV_GRADE where GID in                                    ";
            expres += "                  (select GRADE from OV_PERSON where PID = SELF.PID))                        ";
            expres += "                  = 'diploma')                                                               ";
            expres += "              OR (SELF.TAXCLASS= 'tc1'))                                                     ";
            expres += "        AND (NOT ((select NAME from OV_GRADE where GID in                                    ";
            expres += "                  (select GRADE from OV_PERSON where PID = SELF.PID))                        ";
            expres += "                  = 'doctor')                                                                ";
            expres += "              OR (SELF.TAXCLASS = 'tc2'))                                                    ";
            expres += "        AND (NOT ((select NAME from OV_GRADE where GID in                                    ";
            expres += "                  (select GRADE from OV_PERSON where PID = SELF.PID))                        ";
            expres += "                  = 'professor')                                                             ";
            expres += "              OR (SELF.TAXCLASS = 'tc3'))                                                    ";
            expres += "))                                                                                           ";
            assertTrue(equal(genres, expres));

            // inv5
            genres = getSQLCode("context Facility inv inv5: not(self.member->exists(p:Person|p.owner->excludes(self)))");
            expres = new String();
            expres += "create or replace view inv5 as (                                                             ";
            expres += " select * from OV_FACILITY SELF                                                              ";
            expres += " where not (NOT ((                                                                           ";
            expres += "             exists (                                                                        ";
            expres += "              ((select PID from OV_PERSON where PID in                                       ";
            expres += "               (select PID from MEMBERSHIP where FID in                                      ";
            expres += "                (select FID from OV_FACILITY where FID = SELF.FID))))                        ";
            expres += "              intersect                                                                      ";
            expres += "              select PID from OV_PERSON P                                                    ";
            expres += "              where not exists (                                                             ";
            expres += "                           select SELF.FID from DUAL                                         ";
            expres += "                           intersect                                                         ";
            expres += "                           ((select FID from OV_FACILITY where FID in                        ";
            expres += "                              (select FID from MEMBERSHIP where PID in                       ";
            expres += "                                (select PID from OV_PERSON where PID = P.PID))))             ";
            expres += "                        )                                                                    ";
            expres += "             )                                                                               ";
            expres += "))))                                                                                         ";
            assertTrue(equal(genres, expres));

            // inv6
            genres = getSQLCode("context Paper inv inv6: ((purpose = 'Diplom') and (inProgress = true)) implies author->forAll(p:Person|p.oclIsTypeOf(Student))");
            expres = new String();
            expres += "create or replace view inv6 as (                                                                     ";
            expres += " select * from OV_PAPER SELF                                                                         ";
            expres += " where not (NOT ((SELF.PURPOSE = 'Diplom')                                                           ";
            expres += "                 AND                                                                                 ";
            expres += "                 ((((SELF.INPROGRESS = 1) AND (1=1)) OR (NOT (SELF.INPROGRESS = 1) AND NOT (1=1))))  ";
            expres += "                )                                                                                    ";
            expres += "            OR                                                                                       ";
            expres += "            not exists (                                                                             ";
            expres += "                (select PID from OV_PERSON where PID in                                              ";
            expres += "                (select PID from PAPERS where PAID in                                                ";
            expres += "                (select PAID from OV_PAPER where PAID = SELF.PAID)))                                 ";
            expres += "                minus                                                                                ";
            expres += "                select PID from OV_PERSON P                                                          ";
            expres += "                where exists (                                                                       ";
            expres += "                              select PID from OV_STUDENT                                             ";
            expres += "                              where PID = P.PID                                                      ";
            expres += "                      )                                                                              ";
            expres += "                      and                                                                            ";
            expres += "                      not exists (                                                                    ";
            expres += "                               select PID from OV_PERSON                                             ";
            expres += "                               where PID = P.PID                                                     ";
            expres += "                      )                                                                              ";
            expres += "            )                                                                                        ";
            expres += "))                                                                                                   ";
            assertTrue(equal(genres, expres));

            // inv7
            genres = getSQLCode("context Faculty inv inv7: self.headOfFacility.grade.name = 'professor'");
            expres = new String();
            expres += "create or replace view inv7 as (                                                             ";
            expres += " select * from OV_FACULTY SELF                                                               ";
            expres += " where not ((select NAME from OV_GRADE where GID in                                          ";
            expres += "            (select GRADE from OV_PERSON where PID in                                        ";
            expres += "            (select HEADOFFACILITY from OV_FACILITY where FID = SELF.FID)))                  ";
            expres += "            = 'professor'                                                                    ";
            expres += "))                                                                                           ";
            assertTrue(equal(genres, expres));

            // inv8
            genres = getSQLCode("context Grade inv inv8: Set{'none','diploma','doctor','professor'}->includes(self.name)");
            expres = new String();
            expres += "create or replace view inv8 as (                                                             ";
            expres += " select * from OV_GRADE SELF                                                                 ";
            expres += "where not (exists (                                                                          ";
            expres += "                select SELF.NAME from DUAL                                                   ";
            expres += "                intersect (                                                                  ";
            expres += "                select 'none' as elem from DUAL                                              ";
            expres += "                union                                                                        ";
            expres += "                select 'diploma' as elem from DUAL                                           ";
            expres += "                union                                                                        ";
            expres += "                select 'doctor' as elem from DUAL                                            ";
            expres += "                union                                                                        ";
            expres += "                select 'professor' as elem from DUAL)                                        ";
            expres += "            )                                                                                ";
            expres += "))                                                                                           ";
            assertTrue(equal(genres, expres));

            //inv9
            genres = getSQLCode("context Employee inv inv9: (self.grade.name = 'doctor') implies " +
                                "(Paper.allInstances->count(self.paper->select(purpose = 'Dissertation')) = 1)"
            );
            expres = new String();
            expres += "create or replace view inv9 as (                                                             ";
            expres += "select * from OV_EMPLOYEE SELF                                                               ";
            expres += "where not (NOT ((select NAME from OV_GRADE where GID in                                      ";
            expres += "                (select GRADE from OV_PERSON where PID = SELF.PID))                          ";
            expres += "                = 'doctor')                                                                  ";
            expres += "            OR ((select NVL(COUNT(*),0)                                                      ";
            expres += "                 from OV_PAPER                                                               ";
            expres += "                 where PAID in (select PAID from OV_PAPER)                                   ";
            expres += "                       and PAID = ((select PAID from OV_PAPER where PAID in                  ";
            expres += "                                   (select PAID from PAPERS where PID in                     ";
            expres += "                                   (select PID from OV_PERSON where PID = SELF.PID)))        ";
            expres += "                                   minus                                                     ";
            expres += "                                   select PAID from OV_PAPER TUDOCLITER0                     ";
            expres += "                                   where not (TUDOCLITER0.PURPOSE = 'Dissertation')))        ";
            expres += "                = 1)                                                                         ";
            expres += "))                                                                                           ";
            assertTrue(equal(genres, expres));

        }

	public static Test suite() {
		TestSuite t=new TestSuite();

    		//--> todo: t.addTest(new TestILSQLCodeGenerator("testPrepareJoin"));
                t.addTest(new TestILSQLCodeGenerator("testPrepareDerivedTable"));
                t.addTest(new TestILSQLCodeGenerator("testUniversityExampleInv"));

    		return t;
	}
}
