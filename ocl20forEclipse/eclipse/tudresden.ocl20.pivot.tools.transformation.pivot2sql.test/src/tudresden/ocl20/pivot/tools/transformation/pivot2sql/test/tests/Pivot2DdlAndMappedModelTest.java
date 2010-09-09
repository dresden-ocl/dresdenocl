package tudresden.ocl20.pivot.tools.transformation.pivot2sql.test.tests;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclSettings;
import tudresden.ocl20.pivot.tools.codegen.declarativ.mapping.IMappedModel;
import tudresden.ocl20.pivot.tools.transformation.ITransformation;
import tudresden.ocl20.pivot.tools.transformation.TransformationFactory;
import tudresden.ocl20.pivot.tools.transformation.impl.Tuple;
import tudresden.ocl20.pivot.tools.transformation.pivot2sql.test.tests.util.ModelChecker;
import tudresden.ocl20.pivot.tools.transformation.pivot2sql.test.tests.util.TestPerformer;
import tudresden.ocl20.pivot.tools.transformation.pivot2sql.test.tests.util.TransformationTest;

public class Pivot2DdlAndMappedModelTest {


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
			ITransformation<Namespace,IOcl2DeclSettings,Tuple<String,IMappedModel>> p2dmm = TransformationFactory.getInstance().getParallelTransformation("Pivot2DdlAndMappedModel",Namespace.class,String.class,IMappedModel.class,IOcl2DeclSettings.class,"pivot","ddl&mappedmodel");
			p2dmm.setSettings(oclSettings);
			try {
				p2dmm.setParameterIN(model.getRootNamespace());
			} catch (ModelAccessException e) {
				fail("Can't get Namespace in Pivot2Ddl");
				e.printStackTrace();
			}
			try {
				p2dmm.invoke();
			} catch (Exception e) {
				fail("Can't transformation namespace in Pivot2Ddl");
			}
			
			//run Pivot2CWM & CWM2Ddl
			ITransformation<Namespace,IOcl2DeclSettings,String> p2d = TransformationFactory.getInstance().getTransformation("Pivot2Ddl",Namespace.class,String.class,IOcl2DeclSettings.class,"pivot","ddl");
			p2d.setSettings(oclSettings);
			try {
				p2d.setParameterIN(model.getRootNamespace());
			} catch (ModelAccessException e) {
				fail("Can't get Namespace in Pivot2Cwm");
				e.printStackTrace();
			}
			try {
				p2d.invoke();
			} catch (Exception e) {
				fail("Can't transformation namespace in Pivot2Cwm");
			}
			ITransformation<Namespace,IOcl2DeclSettings,IMappedModel> p2mm = TransformationFactory.getInstance().getTransformation("Pivot2MappedModelImpl",Namespace.class,IMappedModel.class,IOcl2DeclSettings.class,"pivot","mappedmodel");
			p2mm.setSettings(oclSettings);
			try {
				p2mm.setParameterIN(model.getRootNamespace());
			} catch (ModelAccessException e) {
				fail("Can't get Namespace in Pivot2Cwm");
				e.printStackTrace();
			}
			try {
				p2mm.invoke();
			} catch (Exception e) {
				fail("Can't transformation cwm");
			} 
			
			ModelChecker.sameDdl(p2dmm.getResult().getElem1(),p2d.getResult());
			ModelChecker.sameMappedModel(p2dmm.getResult().getElem2(), p2mm.getResult());
		}
		
	}

