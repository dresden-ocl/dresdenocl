/*
 * Created on 09.07.2010
 *
 */
package tudresden.ocl20.pivot.tools.transformation.pivot2sql.impl;

import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclSettings;
import tudresden.ocl20.pivot.tools.codegen.declarativ.mapping.IMappedModel;
import tudresden.ocl20.pivot.tools.transformation.ITransformation;
import tudresden.ocl20.pivot.tools.transformation.ParallelTransformation;
import tudresden.ocl20.pivot.tools.transformation.impl.Tuple;

/**
 * The class Pivot2DdlAndMappedModel represents the parallel composition of
 * Pivot2Ddl transformation and a Pivot2MappedModel transformation.
 * 
 * @author Bjoern Freitag
 * 
 */
public class Pivot2DdlAndMappedModel extends
		ParallelTransformation<Namespace, IOcl2DeclSettings, String, IMappedModel>
		implements
		ITransformation<Namespace, IOcl2DeclSettings, Tuple<String, IMappedModel>> {

	/**
	 * The Standard constructor for a Uml2CwmAndMappedModel transformation.
	 * 
	 * @param modelInName
	 *          The name of the in model.
	 * @param outName
	 *          The name for the out model.
	 * @throws ModelAccessException
	 */
	public Pivot2DdlAndMappedModel(String modelInName, String outName) {

		super(modelInName, outName, Pivot2Ddl.class.getSimpleName(),
				Pivot2MappedModelImpl.class.getSimpleName(), Namespace.class,
				String.class, IMappedModel.class, IOcl2DeclSettings.class);
	}

}
