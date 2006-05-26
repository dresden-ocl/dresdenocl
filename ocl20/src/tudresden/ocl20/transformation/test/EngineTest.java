/*
 * Created on 07.02.2006
 *
 */
package tudresden.ocl20.transformation.test;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.jmi.reflect.RefPackage;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import tudresden.ocl20.codegen.decl.mapping.Guide;
import tudresden.ocl20.codegen.decl.mapping.MappedClass;
import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.OclModel;
import tudresden.ocl20.core.OclModelException;
import tudresden.ocl20.core.Repository;
import tudresden.ocl20.core.RepositoryException;
import tudresden.ocl20.core.RepositoryManager;
import tudresden.ocl20.transformation.TransformationEngine;
import tudresden.ocl20.transformation.exception.InvalidModelException;
import tudresden.ocl20.transformation.exception.TransformationException;
import tudresden.ocl20.transformation.impl.Uml2MappedModelImpl;
import tudresden.ocl20.transformation.inspect.interfaces.MappedModelImpl;

public class EngineTest extends TestCase {
	
	private MappedModelImpl mm;
	private String guidesSql;
	public EngineTest(String n) {
		super(n);
	}
	
	public static void main(String[] args) {
		junit.swingui.TestRunner.run(EngineTest.class);
	}
	
	protected void setUp() {
		try {
			String model_in = "testmodel";
			String model_out = "generated";
				
			addUMLModel(model_in);
			TransformationEngine te = TransformationEngine.getInstance();
			te.setModel_inName(model_in);
			te.setModel_inType(MetaModelConst.UML15);
			te.setModel_outName(model_out);
			te.loadTransformation(Uml2MappedModelImpl.class.getSimpleName());
			te.invoke();
			mm = (MappedModelImpl) te.getResult();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testTransformationEngine() {
		TransformationEngine te = TransformationEngine.getInstance();
		te.cleanUp();
		
		try {
			te.setModel_inName(null);
			fail("Expected NullPointerException");
		} catch(NullPointerException e) {
		}
		te.setModel_inName("inModel");
		assertEquals(te.getModel_inName(), "inModel");
		try {
			te.setModel_outName(null);
			fail("Expected NullPointerException");
		} catch(NullPointerException e) {
		}
		te.setModel_outName("outName");
		assertEquals(te.getModel_outName(), "outName");
		
		try {
			te.invoke();
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		} catch (TransformationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			te.invoke("xxx");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		} catch (TransformationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			te.getRequiredParameters();
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}
		
		try {
			te.getRequiredParameters("xxx");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}
		
		try {
			te.getResult();
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}
		
		try {
			te.getResult("xxx");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}
		
		
		try {
			te.getTrace();
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}
		
		try {
			te.getTrace("xxx");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}

		try {
			te.getCurrentTransformation();
			fail("Expected NullPointerException");
		} catch (NullPointerException e) {
		}
	
		try {
			te.setConfigurationParameter("ccc", "kkk", "ooo");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}
		
		try {
			te.setConfigurationParameter("kkk", "ooo");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}
	}
	public void testMappedModel() {
		guidesSql = "";
		String[] classes = new String[] {
				"Person",
				"Student",
				"Professor",
				"JuniorProfessor",
				"Examination",
				"GraduateExamination",
				"Lecture",
				"Faculty"
		};
		
		Map<String, String[]> class2attributes = new HashMap<String, String[]>();
		class2attributes.put("Person", new String[]{"lastname","firstname", "age" });
		class2attributes.put("Student", new String[]{"lastname","firstname", "age", "studentID" });
		class2attributes.put("Professor", new String[]{"lastname","firstname", "age" });
		class2attributes.put("JuniorProfessor", new String[]{"lastname","firstname", "age", "juniorYear" });
		class2attributes.put("Lecture", new String[]{"topic" });
		class2attributes.put("GraduateExamination", new String[]{"topic" });
		
		
		Map<String, String[]> class2assEnds = new HashMap<String, String[]>();
		class2assEnds.put("Student", new String[]{"examination","lecture"});
		class2assEnds.put("Professor", new String[]{"faculty", "lecture"});
		class2assEnds.put("JuniorProfessor", new String[]{"faculty", "lecture"});
		class2assEnds.put("Faculty", new String[]{"head"});
		class2assEnds.put("Lecture", new String[]{"professor", "listener", "examination"});
		class2assEnds.put("Examination", new String[]{"participant", "lecture"});
		class2assEnds.put("GraduateExamination", new String[]{"participant", "lecture"});
		
		assertFalse(mm.isClass("falsePositive"));
		try {
			mm.getClass("FalsePositive");
			fail("IllegalArgumentException expected.");
		} catch (IllegalArgumentException e){}
		
		
		
		for (String classname : classes) {
			MappedClass mc = mm.getClass(classname);
			assertTrue(mm.isClass(classname));
			assertNotNull(mc);
			Guide cGuide = mc.getClassGuide();
			assertNotNull(cGuide);
			guidesSql += toSqlString(cGuide, "Class Guide " + classname);
			assertEquals(mc.getName(), classname);
			String[] attributes = class2attributes.get(classname);
			assertFalse(mc.isAttribute("falsePositve"));
			try {
				mc.getAttributeGuide("falsePositive");				
				fail("Illegal ArgumentException expected");
			} catch (IllegalArgumentException e) {
			}
			if (attributes != null) {
				for (String attribute : attributes) {
					assertTrue(mc.isAttribute(attribute));
					Guide attG = mc.getAttributeGuide(attribute);
					guidesSql += toSqlString(attG, "Attribute Guide " + classname + "." + attribute);
					assertNotNull(attG);
				}
			}
			
			String[] assEnds = class2assEnds.get(classname);
			assertFalse(mc.isAssociationEnd("falsePositive"));
			try {
				mc.getAssociationEndGuide("falsePositive");				
				fail("Illegal ArgumentException expected");
			} catch (IllegalArgumentException e) {
			}
			
			if (assEnds != null) {
				for (String assEnd : assEnds) {
					assertTrue(mc.isAssociationEnd(assEnd));
					Guide assEG = mc.getAssociationEndGuide(assEnd);
					guidesSql += toSqlString(assEG, "Association End Guide " + classname + "->" + assEnd);
					assertNotNull(assEG);
				}
			}
		}
		//System.out.println(guidesSql);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream( "guides.sql" );
			fos.write( guidesSql.getBytes() );
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	private String toSqlString(Guide guide, String guidename) {
		String sql = "\n\n/* " + guidename + " */\n";
		while(guide.hasNext()) {
			guide.next();
			sql += "( SELECT "  + guide.getSelect();
			sql += " FROM " + guide.getFrom();
			sql += " WHERE " + guide.getWhere();
			if (!guide.hasNext()) {
				sql += "=1";
			}
			else {
				sql += " IN ";
			}
			
		}
		for(int i=0; i<guide.numberOfSteps(); i++) {
			sql += ")";
		}
		sql += ";";
		return sql;
	}

	private void addUMLModel(String name) throws UnsupportedEncodingException, RepositoryException, MalformedURLException {
		
		URL xmiPath = new URL("file:\\C:/Programme/eclipse/workspace/oclTK/resources/wags/UmlModels/university.xmi");
		//System.out.println("Loading Model from XMI-File:\n "+xmiPath);
			
		Repository r = RepositoryManager.getRepository();
		RefPackage model = r.getModel(name);
		if (model != null) {
			try {
				r.deleteModel(model);
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		OclModel oclModel = null;
		try {
			oclModel = new OclModel(MetaModelConst.UML15,xmiPath.toString(),name);
		} catch (OclModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Test suite() {
		TestSuite t = new TestSuite();
    
   		t.addTest(new EngineTest("testMappedModel"));
	  	t.addTest(new EngineTest("testTransformationEngine"));
   		return t;
	}
}
