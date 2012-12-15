/*
 * Created on 09.02.2006
 *
 */
package tudresden.ocl20.pivot.tools.transformation.pivot2sql.impl;

import orgomg.cwm.resource.relational.Schema;

import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclSettings;
import tudresden.ocl20.pivot.tools.codegen.declarativ.mapping.IMappedModel;
import tudresden.ocl20.pivot.tools.transformation.ITransformation;
import tudresden.ocl20.pivot.tools.transformation.ParallelTransformation;
import tudresden.ocl20.pivot.tools.transformation.impl.Tuple;

/**
 * The class Pivot2CwmAndMappedModel represents the parallel composition of a
 * {@link Pivot2Cwm} transformation and a {@link Pivot2MappedModelImpl}
 * transformation.
 * 
 * @author Christian Wende
 * @author Bjoern Freitag
 * 
 */
public class Pivot2CwmAndMappedModel extends
		ParallelTransformation<Namespace, IOcl2DeclSettings, Schema, IMappedModel>
		implements
		ITransformation<Namespace, IOcl2DeclSettings, Tuple<Schema, IMappedModel>> {

	/**
	 * The Standard constructor for a Uml2CwmAndMappedModel transformation.
	 * 
	 * @param modelInName
	 *          The name of the in model.
	 * @param outName
	 *          The name for the out model.
	 * @throws ModelAccessException
	 */
	public Pivot2CwmAndMappedModel(String modelInName, String outName) {

		super(modelInName, outName, Pivot2CwmImpl.class.getSimpleName(),
				Pivot2MappedModelImpl.class.getSimpleName(), Namespace.class,
				Schema.class, IMappedModel.class, IOcl2DeclSettings.class);
	}

}
