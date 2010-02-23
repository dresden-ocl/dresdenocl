package tudresden.ocl20.pivot.modelbus.test;

import java.io.File;
import java.util.Collection;

import tudresden.ocl20.pivot.facade.OCL2ParsingException;
import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
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
	 * Loads a Java {@link IModelInstance} for a given path as an instance of the
	 * given {@link IModel}.
	 * </p>
	 * 
	 * @param path
	 *          The relative path in this plug-in to the model that shall be
	 *          loaded.
	 * @param model
	 *          The {@link IModel} the {@link IModelInstance} is an instance of.
	 * @throws RuntimeException
	 *           Thrown, if an error during {@link IModelInstance} initialization
	 *           occurs.
	 * 
	 * @return The loaded {@link IModelInstance}.
	 * @throws ModelAccessException
	 * @throws RuntimeException
	 * @throws IllegalArgumentException
	 */
	public static IModelInstance getJavaModelInstance(String path, IModel model)
			throws RuntimeException, ModelAccessException {

		/* Get the bundle location for the model files. */
		String bundleDirectory;
		bundleDirectory = Activator.getDefault().getBundle().getLocation();

		/* Remove the 'reference:file:' from the beginning. */
		bundleDirectory = bundleDirectory.substring(15);

		File modelInstanceFile;
		modelInstanceFile = new File(bundleDirectory + path);

		/* Check if the given file exist. */
		if (!modelInstanceFile.exists()) {
			throw new RuntimeException("The model instance for the path "
					+ modelInstanceFile + " cannot be found.");
		}
		// no else.

		return Ocl2ForEclipseFacade.getModelInstance(modelInstanceFile, model,
				Ocl2ForEclipseFacade.JAVA_MODEL_INSTANCE_TYPE);
	}

	/**
	 * <p>
	 * Loads an IModel for a given path.
	 * </p>
	 * 
	 * @param path
	 *          The relative path in this plug-in to the model that shall be
	 *          loaded.
	 * @return The current {@link IModel} or <code>null</code>.
	 * @throws ModelAccessException
	 */
	public static IModel getUML2Model(String path) throws ModelAccessException {

		String bundleDirectory;
		File modelFile;

		/* Get the bundle location for the model files. */
		bundleDirectory = Activator.getDefault().getBundle().getLocation();

		/* Remove the 'reference:file:' from the beginning. */
		bundleDirectory = bundleDirectory.substring(15);

		modelFile = new File(bundleDirectory + path);

		/* Check if the given file does not exist. */
		if (!modelFile.exists()) {
			throw new RuntimeException("The model for the path " + bundleDirectory
					+ path + " cannot be found.");
		}

		/* Else try to load the model. */
		else {
			return Ocl2ForEclipseFacade.getModel(modelFile,
					Ocl2ForEclipseFacade.UML2_MetaModel);
		}
		// end else.
	}

	/**
	 * <p>
	 * Creates an empty Java {@link IModelInstance} for a given {@link IModel}.
	 * </p>
	 * 
	 * @param model
	 *          The {@link IModel} the {@link IModelInstance} shall belong to.
	 * @return The created {@link IModelInstance}.
	 */
	public static IModelInstance createEmptyJavaModelInstance(IModel model) {

		IModelInstance result;

		try {
			result =
					Ocl2ForEclipseFacade.getEmptyModelInstance(model,
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
	 * Parses OCL {@link Constraint}s from a given file for a given {@link IModel}
	 * .
	 * </p>
	 * 
	 * @param constraintLocation
	 *          The name of the file containing the {@link Constraint}s.
	 * @param model
	 *          The {@link IModel}.
	 * @throws ModelAccessException
	 * @throws OCL2ParsingException
	 * @throws IllegalArgumentException
	 */
	public static Collection<Constraint> parseConstraints(
			String constraintLocation, IModel model) throws IllegalArgumentException,
			OCL2ParsingException, ModelAccessException {

		String bundleDirectory;
		File constraintFile;

		/* Get the bundle location for the model files. */
		bundleDirectory = Activator.getDefault().getBundle().getLocation();

		/* Remove the 'reference:file:' from the beginning. */
		bundleDirectory = bundleDirectory.substring(15);

		constraintFile = new File(bundleDirectory + constraintLocation);

		/* Check if the given file does not exist. */
		if (!constraintFile.exists()) {
			throw new RuntimeException("The model for the path " + bundleDirectory
					+ constraintLocation + " cannot be found.");
		}

		/* Else try to parse the constraints. */
		else {
			return Ocl2ForEclipseFacade.parseConstraints(constraintFile, model, true);
		}
		// end else.
	}
}