/*
 * Created on 09.07.2010
 *
 */
package org.dresdenocl.tools.transformation.pivot2sql.impl;

import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.tools.codegen.declarativ.IOcl2DeclSettings;
import org.dresdenocl.tools.codegen.declarativ.mapping.IMappedModel;
import org.dresdenocl.tools.transformation.ITransformation;
import org.dresdenocl.tools.transformation.ParallelTransformation;
import org.dresdenocl.tools.transformation.impl.Tuple;

/**
 * The class Pivot2DdlAndMappedModel represents the parallel composition of
 * {@link Pivot2Ddl} transformation and a {@link Pivot2MappedModelImpl}
 * transformation.
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
