package tudresden.ocl20.pivot.tools.transformation.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclSettings;
import tudresden.ocl20.pivot.tools.codegen.declarativ.mapping.IMappedModel;
import tudresden.ocl20.pivot.tools.transformation.ITransformation;
import tudresden.ocl20.pivot.tools.transformation.TransformationFactory;
import tudresden.ocl20.pivot.tools.transformation.TransformationPlugin;
import tudresden.ocl20.pivot.tools.transformation.impl.Tuple;

/**
 * This test will test the class TestTransformationFactory.java of the package
 * tudresden.ocl20.pivot.tools.transformation.
 * 
 * @see tudresden.ocl20.pivot.tools.transformation.TestTransformationFactory
 */
public class TestTransformationFactory {

	private ITransformation<?,?,?> itrans;
	
	@Before
	public void setUp() {
		itrans = TransformationFactory.getInstance().getTransformation("Pivot2Ddl", "", "");
	}
	
	@Test
	public void checkGetTransformationSimple() {
		TransformationPlugin.getTransformationRegistry().addTransformation(itrans);
		ITransformation<?,?,?> trans = TransformationFactory.getInstance().getTransformation("Pivot2DdlAndMappedModelX", "","");
		assertNull("A not exists transformation is created",trans);
		trans = TransformationFactory.getInstance().getTransformation("Pivot2DdlAndMappedModel", "","");
		assertNotNull(trans);
		trans = TransformationFactory.getInstance().getTransformation(itrans.getClass().getSimpleName(), "","");
		assertNotNull(trans);
		assertNotSame("Isn't generate a new instance",trans,itrans);
		assertEquals("Isn't of same transformation type.",trans.getClass().getName(),itrans.getClass().getName());
	}
	
	@Test
	public void checkGetTransformationParameter() {
		
		ITransformation<Namespace,IOcl2DeclSettings,String> trans = TransformationFactory.getInstance().getTransformation("Pivot2DdlAndMappedModel", Namespace.class, String.class, IOcl2DeclSettings.class, "", "");
		assertNull("The transformation is created with false paramter",trans);
		trans = TransformationFactory.getInstance().getTransformation(itrans.getClass().getSimpleName(), Namespace.class, String.class, IOcl2DeclSettings.class, "", "");
		assertNotNull(trans);
		assertEquals("Isn't of same transformation type.",trans.getClass().getName(),itrans.getClass().getName());
	}
	
	@Test
	public void checkGetTransformationParameterParallel() {		
		ITransformation<Namespace,IOcl2DeclSettings,Tuple<String,IMappedModel>> trans = TransformationFactory.getInstance().getParallelTransformation(itrans.getClass().getSimpleName(), Namespace.class, String.class,IMappedModel.class, IOcl2DeclSettings.class, "", "");
		assertNull("The transformation is created with false paramter",trans);
		trans = TransformationFactory.getInstance().getParallelTransformation("Pivot2DdlAndMappedModel", Namespace.class, String.class,IMappedModel.class, IOcl2DeclSettings.class, "", "");
		assertNotNull(trans);
		assertEquals("Isn't of same transformation type.",trans.getClass().getSimpleName(),"Pivot2DdlAndMappedModel");
	
		
	}
}
