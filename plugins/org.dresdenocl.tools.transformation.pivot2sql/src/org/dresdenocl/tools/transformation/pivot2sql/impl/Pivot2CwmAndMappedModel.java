/*
 * Created on 09.02.2006
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
import orgomg.cwm.resource.relational.Catalog;

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
		ParallelTransformation<Namespace, IOcl2DeclSettings, Catalog, IMappedModel>
		implements
		ITransformation<Namespace, IOcl2DeclSettings, Tuple<Catalog, IMappedModel>> {

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
				Catalog.class, IMappedModel.class, IOcl2DeclSettings.class);
	}

}
