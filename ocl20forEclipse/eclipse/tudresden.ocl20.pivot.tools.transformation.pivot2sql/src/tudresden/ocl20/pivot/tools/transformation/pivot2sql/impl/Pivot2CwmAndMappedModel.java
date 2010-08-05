/*
 * Created on 09.02.2006
 *
 */
package tudresden.ocl20.pivot.tools.transformation.pivot2sql.impl;

import orgomg.cwm.resource.relational.RelationalPackage;

import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.tools.codegen.declarativ.mapping.IMappedModel;
import tudresden.ocl20.pivot.tools.transformation.ParallelTransformations;
import tudresden.ocl20.pivot.tools.transformation.exception.ModelAccessException;

/**
 * The class Uml2CwmAndMappedModel represents the parallel composition of a Uml2Cwm transformation and 
 * a Uml2MappedModel transformation.
 * @author Christian Wende
 *
 */
public class Pivot2CwmAndMappedModel extends ParallelTransformations<Namespace, RelationalPackage, IMappedModel> {

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
	public Pivot2CwmAndMappedModel(String modelInName, String outName) throws ModelAccessException {
		super(modelInName, outName, Pivot2CwmImpl.class.getSimpleName(), Pivot2MappedModelImpl.class.getSimpleName());
	}
	
	public Pivot2CwmAndMappedModel() {
		super();
	}
	

}
