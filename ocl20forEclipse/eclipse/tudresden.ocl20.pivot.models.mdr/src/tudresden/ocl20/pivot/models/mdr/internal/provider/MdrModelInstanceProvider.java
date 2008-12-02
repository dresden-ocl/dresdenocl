package tudresden.ocl20.pivot.models.mdr.internal.provider;

import java.io.File;
import java.net.URL;

import javax.jmi.reflect.RefPackage;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;

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

		/* If no class has been found, throw an exception. */
		if (!instanceURL.getFile().endsWith(".class")) {
			String msg;

			msg = "The given class is no class file. ";
			msg += "Only files with the suffix '.class' are allowed.";
			
			throw new ModelAccessException(msg);
		}
		// no else.

		this.init();

		result = null;
		refPackage = null;

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

				FileClassLoader classLoader;
				Class<?> instanceProviderClass;

				String instancePath;
				String packagePath;
				String className;

				int index;

				uml15Package = (Uml15Package) refPackage;
				instancePath = instanceURL.getFile();

				instanceProviderClass = null;

				/* Split the path into class name and directory path. */
				index = instancePath.lastIndexOf('/');
				packagePath = instancePath;

				/*
				 * Try to load the class directory for directory, decoding the
				 * canonical class name.
				 */
				while (instanceProviderClass == null && index > 0) {

					/* Try to load the class. */
					try {
						packagePath = instancePath.substring(0, index);

						className = instancePath.substring(index + 1);
						className = className.replaceAll("/", ".");

						/* Remove the end '.class' from the class name. */
						className = className.substring(0,
								className.length() - 6);

						/* Try to load the class. */
						classLoader = new FileClassLoader(packagePath
								.substring(1)
								+ "/");
						instanceProviderClass = classLoader
								.findClass(className);
					}

					/*
					 * Ignored because the canonical name may not be fully
					 * decoded.
					 */
					catch (ClassNotFoundException e) {
					} catch (NoClassDefFoundError e) {
					}

					/* Jump to the next upper directory. */
					finally {
						index = packagePath.lastIndexOf('/');
					}
				}
				// end while.

				/* If no class has been found, throw an exception. */
				if (instanceProviderClass == null) {
					String msg;

					msg = "ModelProviderClass could not be found.";
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
					msg += " doesn't provide the needed method getModelObjects().";

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
