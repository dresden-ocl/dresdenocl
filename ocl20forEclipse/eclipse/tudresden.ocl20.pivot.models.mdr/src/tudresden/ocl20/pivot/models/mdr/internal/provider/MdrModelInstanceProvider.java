package tudresden.ocl20.pivot.models.mdr.internal.provider;

import java.io.File;
import java.net.URL;

import javax.jmi.reflect.RefPackage;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;

import tudresden.ocl20.core.ModelManager;
import tudresden.ocl20.core.ModelManagerException;
import tudresden.ocl20.core.NetBeansRepository;
import tudresden.ocl20.core.Repository;
import tudresden.ocl20.core.RepositoryManager;
import tudresden.ocl20.core.jmi.uml15.uml15.Uml15Package;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelInstanceProvider;
import tudresden.ocl20.pivot.models.mdr.internal.modelinstance.UmlModelInstance;

public class MdrModelInstanceProvider extends AbstractModelInstanceProvider
		implements IModelInstanceProvider {

	protected static String REPOSITORY_PATH = "/.metadata/.tudresden/repository";

	private Repository repository = null;

	private ModelManager modelManager = null;

	private String modelName;

	/**
	 * <p>
	 * Creates a new MdrModelInstanceProvider.
	 * </p>
	 * 
	 * @param modelName
	 *            The name of the model for which model instances shall be
	 *            loaded.
	 */
	public MdrModelInstanceProvider(String modelName) {

		this.modelName = modelName;
	}

	/**
	 * <p>
	 * Loads a model instance to a given URL.
	 * </p>
	 * 
	 * @param instanceURL
	 *            the {@link URL} of the model instance which shall be loaded.
	 */
	public IModelInstance getModelInstance(URL instanceURL)
			throws ModelAccessException {

		IModelInstance result;
		RefPackage refPackage;

		this.init();

		result = null;
		refPackage = null;

		/* try to create an URI */
		try {
			URI.createURI(instanceURL.toString());
		}

		catch (IllegalArgumentException e) {
			String msg;

			msg = "Invalid URL: " + instanceURL + ".";

			throw new ModelAccessException(msg, e);
		}

		/* Try to get the model. */
		try {
			refPackage = modelManager.getModel(modelName);

			/* Check if the given modelName specifies UML1.5 models. */
			/* FIXME Remove MOF-Models from the model bus. */
			if (!(refPackage instanceof Uml15Package)) {
				String msg;

				msg = "Only moodel Instances of UML1.5-Models are supported.";
				msg += "Found model was instance of ";
				msg += refPackage.getClass().getCanonicalName();

				throw new ModelAccessException(msg);
			}

			else {
				Uml15Package uml15Package;

				String instancePath;
				String instanceClassName;

				int pathNameStart;
				int extensionStart;
				int index;

				uml15Package = (Uml15Package) refPackage;
				instancePath = instanceURL.getFile();

				String[] filePathParts;
				String currentPath;

				Class<?> instanceProviderClass;

				/*
				 * Decode the path and get the path of the instance provider
				 * Class without protocol and file extension.
				 */
				pathNameStart = instancePath.lastIndexOf(":") + 2;
				extensionStart = instancePath.lastIndexOf(".java");
				instanceClassName = instancePath.substring(pathNameStart,
						extensionStart);

				/* Split the filePath into directories. */
				filePathParts = instanceClassName.split("/");

				/* Get the last element. */
				currentPath = filePathParts[filePathParts.length - 1];
				index = filePathParts.length - 1;

				instanceProviderClass = null;

				/*
				 * Iterate through all directories and decode into a canonical
				 * class name.
				 */
				while (instanceProviderClass == null && index > 0) {

					/* Try to load the class. */
					try {
						instanceProviderClass = Class.forName(currentPath);
					}

					catch (ClassNotFoundException e) {
						/* Ignored the canonical name is not fully decoded. */
					}

					finally {
						/*
						 * Remove the last element of the filePathList and
						 * complete the canonical name of the class.
						 */
						index--;
						currentPath = filePathParts[index] + "." + currentPath;
					}
				}
				/* The no class has been found, throw an exception. */
				if (instanceProviderClass == null) {
					String msg;

					msg = "ModelProviderClass not found, maybe not in classpath. ";
					msg += "The last class name searched for was "
							+ currentPath;

					throw new ModelAccessException(msg);
				}
				// no else.

				/* Try to execute method which provides all model objects. */
				try {
					instanceProviderClass.getDeclaredMethod("getModelObjects",
							null);
				}

				catch (Exception e) {
					String msg;

					msg = "Class " + instanceProviderClass;
					msg += " doesn't provide needed methode getModelObjects().";

					throw new ModelAccessException(msg);
				}

				result = new UmlModelInstance(instanceProviderClass,
						uml15Package);
			}
		} catch (ModelManagerException e) {

			throw new ModelAccessException(e.getMessage());
		}

		return result;
	}

	/**
	 * <p>
	 * Initializes the MdrModelInstanceProvider
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	protected void init() throws ModelAccessException {

		/* If the repository is not set yet, initialize it. */
		if (this.repository == null) {

			IPath workspacePath;
			File repositoryDirectory;

			/* Get workspace path and repository directory. */
			workspacePath = Platform.getLocation();
			repositoryDirectory = new File(workspacePath + REPOSITORY_PATH);

			/* Eventually create the directory. */
			if (!repositoryDirectory.exists()) {
				repositoryDirectory.mkdirs();
			}
			// no else.

			/* Get the repository. */
			System.setProperty(NetBeansRepository.MDR, repositoryDirectory
					.getPath());
			this.repository = RepositoryManager.getRepository();
		}
		// no else.

		/* If the model manager is not set yet, initialize it. */
		if (this.modelManager == null) {

			this.modelManager = ModelManager.getInstance();
		}
		// no else.
	}
}
