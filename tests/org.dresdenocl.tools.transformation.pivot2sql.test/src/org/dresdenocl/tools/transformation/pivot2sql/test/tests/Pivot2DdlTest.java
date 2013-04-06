package org.dresdenocl.tools.transformation.pivot2sql.test.tests;

import static org.junit.Assert.fail;

import org.dresdenocl.model.IModel;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.tools.codegen.declarativ.IOcl2DeclSettings;
import org.dresdenocl.tools.transformation.ITransformation;
import org.dresdenocl.tools.transformation.TransformationFactory;
import org.dresdenocl.tools.transformation.pivot2sql.impl.SchemaStringMap;
import org.dresdenocl.tools.transformation.pivot2sql.test.tests.util.ModelChecker;
import org.dresdenocl.tools.transformation.pivot2sql.test.tests.util.TestPerformer;
import org.dresdenocl.tools.transformation.pivot2sql.test.tests.util.TransformationTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import orgomg.cwm.resource.relational.Catalog;

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

		// run Pivot2Ddl:
		ITransformation<Namespace, IOcl2DeclSettings, SchemaStringMap> p2di =
				TransformationFactory.getInstance().getTransformation("Pivot2Ddl",
						Namespace.class, SchemaStringMap.class, IOcl2DeclSettings.class, "pivot",
						"ddl");
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

		// run Pivot2CWM & CWM2Ddl
		ITransformation<Namespace, IOcl2DeclSettings, Catalog> p2ci =
				TransformationFactory.getInstance().getTransformation("Pivot2CwmImpl",
						Namespace.class, Catalog.class, IOcl2DeclSettings.class, "pivot",
						"cwm");
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
		ITransformation<Catalog, IOcl2DeclSettings, SchemaStringMap> c2di =
				TransformationFactory.getInstance().getTransformation("Cwm2DdlImpl",
						Catalog.class, SchemaStringMap.class, IOcl2DeclSettings.class, "cwm", "ddl");
		c2di.setSettings(oclSettings);
		c2di.setParameterIN(p2ci.getResult());
		try {
			c2di.invoke();
		} catch (Exception e) {
			fail("Can't transformation cwm");
		}

		ModelChecker.sameDdl(p2di.getResult().toFullString(), c2di.getResult().toFullString());
	}

}
