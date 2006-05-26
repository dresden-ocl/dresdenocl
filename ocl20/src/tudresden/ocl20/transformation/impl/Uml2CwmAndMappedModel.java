/*
 * Created on 09.02.2006
 *
 */
package tudresden.ocl20.transformation.impl;

import tudresden.ocl20.codegen.decl.mapping.MappedModel;
import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.jmi.cwm.relational.RelationalPackage;
import tudresden.ocl20.core.jmi.uml15.uml15.Uml15Package;
import tudresden.ocl20.transformation.exception.ModelAccessException;
import tudresden.ocl20.transformation.interfaces.ParallelTransformations;

/**
 * The class Uml2CwmAndMappedModel represents the parallel composition of a Uml2Cwm transformation and 
 * a Uml2MappedModel transformation.
 * @author Christian Wende
 *
 */
public class Uml2CwmAndMappedModel extends ParallelTransformations<Uml15Package, RelationalPackage, MappedModel> {

	/**
	 * The type of the transformations in model.
	 */
	public static String in_type = MetaModelConst.UML15;
	/**
	 * The type of the transformations out model.
	 */
	public static String out_type = MetaModelConst.CWM + " and MappedModel";
	
	/**
	 * The Standard constructor for a Uml2CwmAndMappedModel transformation.
	 * @param modelInName The name of the in model.
	 * @param outName The name for the out model.
	 * @throws ModelAccessException
	 */
	public Uml2CwmAndMappedModel(String modelInName, String outName) throws ModelAccessException {
		super(modelInName, outName, Uml2CwmImpl.class.getSimpleName(), Uml2MappedModelImpl.class.getSimpleName());
	}

}
