/*
 * Created on 09.02.2006
 *
 */
package org.dresdenocl.tools.transformation;

import org.eclipse.emf.ecore.EObject;

import org.dresdenocl.tools.transformation.exception.InvalidModelException;
import org.dresdenocl.tools.transformation.exception.TransformationException;
import org.dresdenocl.tools.transformation.impl.Tuple;

/**
 * ParallelTransformation is an abstract implementation of a parallel
 * transformation composition.
 * 
 * To implement a composition you must a class which implements
 * ParallelTransformation and define the involved tranformations using for
 * example super(modelInName, outName, Pivot2CwmImpl.class.getSimpleName(),
 * Pivot2MappedModelImpl.class.getSimpleName()); in the constructor.
 * 
 * @author Christian Wende
 * 
 */
public abstract class ParallelTransformation<IN extends EObject, SETTINGS, A, B>
		extends M2XTransformation<IN, SETTINGS, Tuple<A, B>> {

	private ITransformation<IN, SETTINGS, A> trans1;
	private ITransformation<IN, SETTINGS, B> trans2;

	/**
	 * The constructor for ParallelTransformation.
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
	 * @param out1
	 *          the type of the first element of output tuple
	 * @param out2
	 *          the type of the second element of output tuple
	 * @param settings
	 *          the type of the settings
	 */
	protected ParallelTransformation(String modelInName, String outName,
			String tid1, String tid2, Class<IN> in, Class<A> out1, Class<B> out2,
			Class<SETTINGS> settings) {

		super(modelInName, outName);

		trans1 =
				TransformationFactory.getInstance().getTransformation(tid1, in, out1,
						settings, modelInName, outName);
		trans2 =
				TransformationFactory.getInstance().getTransformation(tid2, in, out2,
						settings, modelInName, outName);

	}

	public void invoke() throws TransformationException, InvalidModelException {

		trans1.invoke();
		A a = trans1.getResult();

		trans2.invoke();
		B b = trans2.getResult();

		this.out = new Tuple<A, B>(a, b);

	}

	public void setSettings(SETTINGS settings) {

		super.setSettings(settings);
		trans1.setSettings(settings);
		trans2.setSettings(settings);
	}

	public void setParameterIN(IN in) {

		super.setParameterIN(in);
		trans1.setParameterIN(in);
		trans2.setParameterIN(in);

	}

}
