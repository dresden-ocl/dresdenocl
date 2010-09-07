package tudresden.ocl20.pivot.tools.transformation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import tudresden.ocl20.pivot.tools.transformation.impl.Tuple;

public class TransformationFactory {

	private static TransformationFactory myInstance;

	/**
	 * <p>
	 * A private constructor to enforce the Singleton.
	 * </p>
	 */
	private TransformationFactory() {

		/* Remains empty. */
	}

	/**
	 * @return The only instance of {@link TransformationFactory}.
	 */
	public static TransformationFactory getInstance() {

		if (myInstance == null) {
			myInstance = new TransformationFactory();
		}
		// no else.

		return myInstance;
	}

	private <T> T createObject(Class<T> clazz, String modelIn, String modelOut) {

		try {
			Constructor<T> cons =
					clazz.getConstructor(new Class[] { String.class, String.class });
			return cons.newInstance(new Object[] { modelIn, modelOut });
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}

	private ITransformation<?, ?, ?> createInstance(Class<?> clazz,
			String modelIn, String modelOut) {

		if (clazz != null) {
			Object obj = createObject(clazz, modelIn, modelOut);
			if (isITransformation(obj)) {
				return ITransformation.class.cast(obj);
			}
		}
		return null;
	}

	/**
	 * <p>
	 * Returns a new instance of {@link ITransformation} with the given id or
	 * <code>null</code> if no {@link ITransformation} with that id is registered.
	 * </p>
	 * 
	 * @param id
	 *          An identifier for a {@link ITransformation} is the
	 *          simpleClassName.
	 * @param model_inName
	 *          the name of the in model
	 * @param model_OutName
	 *          the name of the out model
	 * @return A new {@link ITransformation} instance or <code>null</code>.
	 */
	public ITransformation<?, ?, ?> getTransformation(String transformationId,
			String model_inName, String model_outName) {

		return createInstance(TransformationPlugin.getTransformationRegistry()
				.getTransformationClass(transformationId), model_inName, model_outName);
	}

	/**
	 * <p>
	 * Returns an {@link ITransformation} instance with the parameters.
	 * </p>
	 * 
	 * @param transId
	 *          the name of the transformation
	 * @param modelIn
	 *          the type of the transformation input
	 * @param modelOut
	 *          the type of the transformation output
	 * @param settings
	 *          the settings of the transformation
	 * @param modelInName
	 *          the input name of the transformation
	 * @param modelOutName
	 *          the output name of the transformation
	 * @return a new instance of {@link ITransformation} or <i>null</i> if there
	 *         no transformation with the parameters.
	 */
	@SuppressWarnings("unchecked")
	public <I, S, O> ITransformation<I, S, O> getTransformation(String transId,
			Class<I> modelIn, Class<O> modelOut, Class<S> settings,
			String modelInName, String modelOutName) {

		ITransformation<?, ?, ?> returnTrans =
				getTransformation(transId, modelInName, modelOutName);
		if (returnTrans == null)
			return null;
		Type[] types =
				((ParameterizedType) returnTrans.getClass().getGenericSuperclass())
						.getActualTypeArguments();
		if (types[0].equals(modelIn)) {
			if (types[1].equals(settings)) {
				if (types[types.length - 1].equals(modelOut)) {
					return (ITransformation<I, S, O>) returnTrans;
				}
			}
		}
		return null;
	}

	/**
	 * <p>
	 * Returns an {@link ITransformation} instance with the parameters.
	 * </p>
	 * 
	 * @param transId
	 *          the name of the transformation
	 * @param modelIn
	 *          the type of the transformation input
	 * @param modelOut1
	 *          the type of the first element of the output tuple
	 * @param modelOut2
	 *          the type of the second element of the output tuple
	 * @param settings
	 *          the settings of the transformation
	 * @param modelInName
	 *          the input name of the transformation
	 * @param modelOutName
	 *          the output name of the transformation
	 * @return a new instance of {@link ITransformation} or <i>null</i> if there
	 *         no transformation with the parameters.
	 */
	@SuppressWarnings("unchecked")
	public <I, S, O1, O2> ITransformation<I, S, Tuple<O1, O2>> getTransformation(
			String transId, Class<I> modelIn, Class<O1> modelOut1,
			Class<O2> modelOut2, Class<S> settings, String modelInName,
			String modelOutName) {

		ITransformation<?, ?, ?> returnTrans =
				getTransformation(transId, modelInName, modelOutName);
		if (returnTrans == null)
			return null;
		Type[] types =
				((ParameterizedType) returnTrans.getClass().getGenericSuperclass())
						.getActualTypeArguments();
		if (types.length != 4)
			return null;
		if (types[0].equals(modelIn)) {
			if (types[1].equals(settings)) {
				if (types[2].equals(modelOut1)) {
					if (types[3].equals(modelOut2)) {
						return (ITransformation<I, S, Tuple<O1, O2>>) returnTrans;
					}
				}
			}
		}
		return null;
	}

	private boolean isITransformation(Object transformation) {

		return (transformation instanceof ITransformation);
	}

}
