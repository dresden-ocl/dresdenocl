package tudresden.ocl20.pivot.tools.transformation.pivot2sql.test.tests;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import orgomg.cwm.resource.relational.Schema;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclSettings;
import tudresden.ocl20.pivot.tools.transformation.ITransformation;
import tudresden.ocl20.pivot.tools.transformation.TransformationFactory;
import tudresden.ocl20.pivot.tools.transformation.pivot2sql.test.tests.util.ModelChecker;
import tudresden.ocl20.pivot.tools.transformation.pivot2sql.test.tests.util.TestPerformer;
import tudresden.ocl20.pivot.tools.transformation.pivot2sql.test.tests.util.TransformationTest;

public class Pivot2DdlTest {

	private IModel model = null;
	
	@Before
	public void setUp() {
		try {
			 model = TestPerformer.addUMLModel(TransformationTest.TEST_COMPLEX);
		} catch (Exception e) {
			fail("Can't initialize model");
		} 
	}
	
	@After
	public void tear_down() {
		if (model != null) {
			TestPerformer.removeUMLModel(model);
			model = null;
		}
	}
	
	@Test
	public void runTyped() {
		run(IOcl2DeclSettings.MODUS_TYPED);
	}

	@Test
	public void runVertical() {
		run(IOcl2DeclSettings.MODUS_VERTICAL);
	}
	
	private void run(int modus) {
		IOcl2DeclSettings oclSettings = TestPerformer.getSettings();
		oclSettings.setModus(modus);
		
		//run Pivot2Ddl:
		ITransformation<Namespace,IOcl2DeclSettings,String> p2di = TransformationFactory.getInstance().getTransformation("Pivot2Ddl",Namespace.class,String.class,IOcl2DeclSettings.class,"pivot","ddl");
		p2di.setSettings(oclSettings);
		try {
			p2di.setParameterIN(model.getRootNamespace());
		} catch (ModelAccessException e) {
			fail("Can't get Namespace in Pivot2Ddl");
			e.printStackTrace();
		}
		try {
			p2di.invoke();
		} catch (Exception e) {
			fail("Can't transformation namespace in Pivot2Ddl");
		}
		
		//run Pivot2CWM & CWM2Ddl
		ITransformation<Namespace,IOcl2DeclSettings,Schema> p2ci = TransformationFactory.getInstance().getTransformation("Pivot2CwmImpl",Namespace.class,Schema.class,IOcl2DeclSettings.class,"pivot","cwm");
		p2ci.setSettings(oclSettings);
		try {
			p2ci.setParameterIN(model.getRootNamespace());
		} catch (ModelAccessException e) {
			fail("Can't get Namespace in Pivot2Cwm");
			e.printStackTrace();
		}
		try {
			p2ci.invoke();
		} catch (Exception e) {
			fail("Can't transformation namespace in Pivot2Cwm");
		}
		ITransformation<Schema,IOcl2DeclSettings,String> c2di = TransformationFactory.getInstance().getTransformation("Cwm2DdlImpl",Schema.class,String.class,IOcl2DeclSettings.class,"cwm","ddl");
		c2di.setSettings(oclSettings);
		c2di.setParameterIN(p2ci.getResult());
		try {
			c2di.invoke();
		} catch (Exception e) {
			fail("Can't transformation cwm");
		} 
		
		ModelChecker.sameDdl(p2di.getResult(),c2di.getResult());
	}
	
}
