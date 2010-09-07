package tudresden.ocl20.pivot.tools.transformation.pivot2sql.impl;

import orgomg.cwm.resource.relational.Schema;

import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclSettings;
import tudresden.ocl20.pivot.tools.transformation.ITransformation;
import tudresden.ocl20.pivot.tools.transformation.SequentialTransformation;

/**
 * The Uml2Ddl transformation implements the serial composition of the Uml2Cwm
 * transformation and the Cwm2Ddl transformation.
 * 
 * @author Christian Wende
 * @author Bjoern Freitag
 * 
 */
public class Pivot2Ddl extends
		SequentialTransformation<Namespace, IOcl2DeclSettings, Schema, String>
		implements ITransformation<Namespace, IOcl2DeclSettings, String> {

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
				Cwm2DdlImpl.class.getSimpleName(), Namespace.class, String.class,
				Schema.class, IOcl2DeclSettings.class);

	}

}
