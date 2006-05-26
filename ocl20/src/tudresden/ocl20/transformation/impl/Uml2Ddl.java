package tudresden.ocl20.transformation.impl;

import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.transformation.interfaces.SequentialTransformations;

/**
 * The Uml2Ddl transformation implements the serial composition of the Uml2Cwm transformation and the Cwm2Ddl 
 * transformation.
 * @author Christian Wende
 *
 */
public class Uml2Ddl extends SequentialTransformations {

	/**
	 * The type of the transformations in model.
	 */
	public static String in_type = MetaModelConst.UML15;
	/**
	 * The type of the transformations out model.
	 */
	public static String out_type = "A DDL";
	
	/**
	 * The constructor for a Uml2Ddl transformation.
	 * @param modelInName The name of the in model.
	 * @param outName The name of the out entity.
	 * @throws Exception 
	 */
	public Uml2Ddl(String modelInName, String outName) throws Exception {
		super(modelInName, outName, Uml2CwmImpl.class.getSimpleName(), Cwm2DdlImpl.class.getSimpleName());
	}
	
}
