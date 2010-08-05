/*
 * Created on 09.07.2010
 *
 */
package tudresden.ocl20.pivot.tools.transformation.pivot2sql.impl;

import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.tools.codegen.declarativ.mapping.IMappedModel;
import tudresden.ocl20.pivot.tools.transformation.ParallelTransformations;
import tudresden.ocl20.pivot.tools.transformation.exception.ModelAccessException;

/**
 * The class Pivot2DdlAndMappedModel represents the parallel composition of Pivot2Ddl transformation and 
 * a Pivot2MappedModel transformation.
 * @author Bjoern Freitag
 *
 */
public class Pivot2DdlAndMappedModel extends ParallelTransformations<Namespace, String, IMappedModel> {

	/**PO
	 * The type of the transformations in model.
	 */
	public static String in_type = "PM";
	/**
	 * The type of the transformations out model.
	 */
	public static String out_type = "CWM and MappedModel";
	
	/**
	 * The Standard constructor for a Uml2CwmAndMappedModel transformation.
	 * @param modelInName The name of the in model.
	 * @param outName The name for the out model.
	 * @throws ModelAccessException
	 */
	public Pivot2DdlAndMappedModel(String modelInName, String outName) throws ModelAccessException {
		super(modelInName, outName, Pivot2Ddl.class.getSimpleName(), Pivot2MappedModelImpl.class.getSimpleName());
	}

}
