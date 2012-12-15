package tudresden.ocl20.pivot.modelbus.test;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;

import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * <p>
 * Utility class to load {@link IModel}s and {@link IModelInstance}s for
 * testing.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ModelBusTestUtility {

	/**
	 * <p>
	 * Loads a Java {@link IModelInstance} for a given path as an instance of
	 * the given {@link IModel}.
	 * </p>
	 * 
	 * @param path
	 *            The relative path in this plug-in to the model that shall be
	 *            loaded.
	 * @param model
	 *            The {@link IModel} the {@link IModelInstance} is an instance
	 *            of.
	 * @throws RuntimeException
	 *             Thrown, if an error during {@link IModelInstance}
	 *             initialization occurs.
	 * 
	 * @return The loaded {@link IModelInstance}.
	 * @throws ModelAccessException
	 * @throws RuntimeException
	 * @throws IllegalArgumentException
	 */
	public static IModelInstance getJavaModelInstance(String path, IModel model)
			throws RuntimeException, ModelAccessException {

		File modelInstanceFile;
		try {
			modelInstanceFile = AbstractDresdenOclTest.getFile(path,
					Activator.PLUGIN_ID);
		} catch (IOException e) {
			throw new ModelAccessException(e.getMessage(), e);
		}

		return Ocl2ForEclipseFacade.getModelInstance(modelInstanceFile, model,
				Ocl2ForEclipseFacade.JAVA_MODEL_INSTANCE_TYPE);
	}

	/**
	 * <p>
	 * Loads an IModel for a given path.
	 * </p>
	 * 
	 * @param path
	 *            The relative path in this plug-in to the model that shall be
	 *            loaded.
	 * @return The current {@link IModel} or <code>null</code>.
	 * @throws ModelAccessException
	 */
	public static IModel getUML2Model(String path) throws ModelAccessException {

		File modelFile;
		try {
			modelFile = AbstractDresdenOclTest.getFile(path,
					Activator.PLUGIN_ID);
		} catch (IOException e) {
			throw new ModelAccessException(e.getMessage(), e);
		}
		return Ocl2ForEclipseFacade.getModel(modelFile,
				Ocl2ForEclipseFacade.UML2_MetaModel);
	}

	/**
	 * <p>
	 * Creates an empty Java {@link IModelInstance} for a given {@link IModel}.
	 * </p>
	 * 
	 * @param model
	 *            The {@link IModel} the {@link IModelInstance} shall belong to.
	 * @return The created {@link IModelInstance}.
	 */
	public static IModelInstance createEmptyJavaModelInstance(IModel model) {

		IModelInstance result;

		try {
			result = Ocl2ForEclipseFacade.getEmptyModelInstance(model,
					Ocl2ForEclipseFacade.JAVA_MODEL_INSTANCE_TYPE);
		}

		catch (IllegalArgumentException e) {
			result = null;
		}

		catch (ModelAccessException e) {
			result = null;
		}

		return result;
	}

	/**
	 * <p>
	 * Parses OCL {@link Constraint}s from a given file for a given
	 * {@link IModel} .
	 * </p>
	 * 
	 * @param constraintLocation
	 *            The name of the file containing the {@link Constraint}s.
	 * @param model
	 *            The {@link IModel}.
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws IllegalArgumentException
	 */
	public static Collection<Constraint> parseConstraints(
			String constraintLocation, IModel model)
			throws IllegalArgumentException, ParseException,
			ModelAccessException {

		try {
			File constraintFile = AbstractDresdenOclTest.getFile(
					constraintLocation, Activator.PLUGIN_ID);
			return Ocl2ForEclipseFacade.parseConstraints(constraintFile, model,
					true);
		} catch (IOException e) {
			throw new ModelAccessException(e.getMessage(), e);
		}
	}
}