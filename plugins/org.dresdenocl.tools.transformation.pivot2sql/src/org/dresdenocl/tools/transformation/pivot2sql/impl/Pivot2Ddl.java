package org.dresdenocl.tools.transformation.pivot2sql.impl;

import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.tools.codegen.declarativ.IOcl2DeclSettings;
import org.dresdenocl.tools.transformation.ITransformation;
import org.dresdenocl.tools.transformation.SequentialTransformation;
import orgomg.cwm.resource.relational.Catalog;

/**
 * The Pivot2Ddl transformation implements the serial composition of the
 * {@link Pivot2CwmImpl} transformation and the {@link Cwm2DdlImpl}
 * transformation.
 * 
 * @author Christian Wende
 * @author Bjoern Freitag
 * 
 */
public class Pivot2Ddl extends
		SequentialTransformation<Namespace, IOcl2DeclSettings, Catalog, SchemaStringMap>
		implements ITransformation<Namespace, IOcl2DeclSettings, SchemaStringMap> {

	/**
	 * The constructor for a Uml2Ddl transformation.
	 * 
	 * @param modelInName
	 *          The name of the in model.
	 * @param outName
	 *          The name of the out entity.
	 * @throws Exception
	 */
	public Pivot2Ddl(String modelInName, String outName) {

		super(modelInName, outName, Pivot2CwmImpl.class.getSimpleName(),
				Cwm2DdlImpl.class.getSimpleName(), Namespace.class, SchemaStringMap.class,
				Catalog.class, IOcl2DeclSettings.class);

	}

}
