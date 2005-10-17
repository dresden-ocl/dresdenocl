package tudresden.ocl20.codegen.sql.test;

import java.util.ArrayList;
import java.util.List;

import tudresden.ocl20.codegen.sql.codegen.Guide;
import tudresden.ocl20.codegen.sql.codegen.ObjectViewSchema;
import tudresden.ocl20.codegen.sql.codegen.OracleSQLBuilder;
import tudresden.ocl20.codegen.sql.codegen.SQLCodeGenerator;
import tudresden.ocl20.codegen.sql.orm.ORMappingScheme;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestSQLCodeGenerator extends TestCase {
	
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

		return str1.equals(str2);
	}
	
	public void testPrepareDerivedTable() throws Exception{
		
		String sqlCodeGenPatternCatalogue = "D:/uni/gb/ocl20/resources/sql/OCL2SQL4Oracle.xml";
		ORMappingScheme orm = new ObjectViewSchema(new UniversityExampleSchema(), new OracleSQLBuilder());;
		SQLCodeGenerator theCG = new SQLCodeGenerator(sqlCodeGenPatternCatalogue);
		theCG.setORMappingScheme(orm);
		
        List<Guide> guides = new ArrayList<Guide>();
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
				"(select AFK from C where CPK = SELF.CPK))"));
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
                     "(select DPK from D where DPK = SELF.DPK)))))))"));
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
                     "(select RAPK from A where APK = SELF.APK))"));
		assertTrue(theCG.getJoinRepresentation().equals(""));
	}

	public TestSQLCodeGenerator(String n) {
		super(n);
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for tudresden.ocl20.sql.test");
		//$JUnit-BEGIN$
		
		suite.addTest(new TestSQLCodeGenerator("testPrepareDerivedTable"));

		//$JUnit-END$
		return suite;
	}

}
