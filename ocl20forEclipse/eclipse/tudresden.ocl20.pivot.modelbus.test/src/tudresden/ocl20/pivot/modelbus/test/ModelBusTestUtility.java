package tudresden.ocl20.pivot.modelbus.test;

import java.io.File;

import tudresden.ocl20.pivot.metamodels.uml2.UML2MetamodelPlugin;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.metamodel.IMetamodel;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceType;
import tudresden.ocl20.pivot.modelinstancetype.java.JavaModelInstanceTypePlugin;

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

		if (model == null) {
			throw new IllegalArgumentException("The model must not be null.");
		}
		// no else.

		IModelInstance result;

		/* Load the model instance. */
		IModelInstanceProvider javaModelInstanceProvider;
		javaModelInstanceProvider =
				ModelBusPlugin.getModelInstanceTypeRegistry().getModelInstanceType(
						JavaModelInstanceTypePlugin.PLUGIN_ID).getModelInstanceProvider();

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

		result =
				javaModelInstanceProvider.getModelInstance(modelInstanceFile, model);

		return result;
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

		IModel result;

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

			IMetamodel metaModel;

			/* Get the metaModel. */
			metaModel =
					ModelBusPlugin.getMetamodelRegistry().getMetamodel(
							UML2MetamodelPlugin.ID);

			if (metaModel != null) {
				result = metaModel.getModelProvider().getModel(modelFile);
			}

			else {
				throw new RuntimeException("The meta-model " + UML2MetamodelPlugin.ID
						+ " has not been found.");
			}
		}
		// end else.

		return result;
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

		IModelInstanceType javaModelInstanceType;
		javaModelInstanceType =
				ModelBusPlugin.getModelInstanceTypeRegistry().getModelInstanceType(
						JavaModelInstanceTypePlugin.PLUGIN_ID);

		result =
				javaModelInstanceType.getModelInstanceProvider()
						.createEmptyModelInstance(model);

		return result;
	}
}