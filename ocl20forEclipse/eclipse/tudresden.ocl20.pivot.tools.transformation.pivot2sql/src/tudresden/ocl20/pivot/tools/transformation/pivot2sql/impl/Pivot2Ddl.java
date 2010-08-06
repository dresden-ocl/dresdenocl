package tudresden.ocl20.pivot.tools.transformation.pivot2sql.impl;

import orgomg.cwm.resource.relational.Schema;

import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.tools.transformation.SequentialTransformations;

/**
 * The Uml2Ddl transformation implements the serial composition of the Uml2Cwm
 * transformation and the Cwm2Ddl transformation.
 * 
 * @author Christian Wende
 * @author Bjoern Freitag
 * 
 */
public class Pivot2Ddl extends
		SequentialTransformations<Namespace, Schema, String> {

	/**
	 * The type of the transformations in model.
	 */
	public static String in_type = "PM";
	/**
	 * The type of the transformations out model.
	 */
	public static String out_type = "A DDL";

	/**
	 * The constructor for a Uml2Ddl transformation.
	 * 
	 * @param modelInName
	 *          The name of the in model.
	 * @param outName
	 *          The name of the out entity.
	 * @throws Exception
	 */
	public Pivot2Ddl(String modelInName, String outName) throws Exception {

		super(modelInName, outName, Pivot2CwmImpl.class.getSimpleName(),
				Cwm2DdlImpl.class.getSimpleName());

	}

	public Pivot2Ddl() {

		super();
	}

}
