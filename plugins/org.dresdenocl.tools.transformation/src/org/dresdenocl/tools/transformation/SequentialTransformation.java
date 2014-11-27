package org.dresdenocl.tools.transformation;

import org.dresdenocl.tools.transformation.exception.InvalidModelException;
import org.dresdenocl.tools.transformation.exception.TransformationException;
import org.eclipse.emf.ecore.EObject;

/**
 * SequentialTransformations is a abstract implementation of a sequential
 * transformation composition.
 * 
 * @author Christian Wende
 * @author Bjoern Freitag
 * 
 * @param <IN>
 * @param <OUT>
 */
public abstract class SequentialTransformation<IN, SETTINGS, BET, OUT>
		extends Transformation<IN, SETTINGS, OUT> {

	private ITransformation<IN, SETTINGS, BET> trans1;
	private ITransformation<BET, SETTINGS, OUT> trans2;

	/**
	 * The constructor for SequentialTransformation.
	 * 
	 * @param modelInName
	 *          The name of the in model.
	 * @param outName
	 *          The name ouf the out entity.
	 * @param tid1
	 *          The id of the first transformation.
	 * @param tid2
	 *          The id of the second transformation.
	 * @param in
	 *          the type of the input
	 * @param out
	 *          the type of the output
	 * @param between
	 *          the type of the output from the first transformation and the input
	 *          of the second transformation
	 * @param settings
	 *          the type of the settings
	 */
	protected SequentialTransformation(String modelInName, String outName,
			String tid1, String tid2, Class<IN> in, Class<OUT> out,
			Class<BET> between, Class<SETTINGS> settings) {

		super(modelInName, outName);

		trans1 =
				TransformationFactory.getInstance().getTransformation(tid1, in,
						between, settings, modelInName, "intermediateResult");
		trans2 =
				TransformationFactory.getInstance().getTransformation(tid2, between,
						out, settings, "intermediateResult", outName);

	}

	public void invoke() throws TransformationException, InvalidModelException {

		trans1.invoke();
		// A a = (A) te.getResult(id1);
		trans2.setParameterIN(trans1.getResult());
		trans2.invoke();
		OUT b = trans2.getResult();

		this.out = b;
	}

	public void setSettings(SETTINGS settings) {

		super.setSettings(settings);
		trans1.setSettings(settings);
		trans2.setSettings(settings);
	}

	public void setParameterIN(IN in) {

		super.setParameterIN(in);
		trans1.setParameterIN(in);
	}

}
