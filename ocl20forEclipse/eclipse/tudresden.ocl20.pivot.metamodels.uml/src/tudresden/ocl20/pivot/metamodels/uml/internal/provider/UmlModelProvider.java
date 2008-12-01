package tudresden.ocl20.pivot.metamodels.uml.internal.provider;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.jmi.model.ModelPackage;
import javax.jmi.reflect.RefPackage;
import javax.jmi.xmi.MalformedXMIException;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;

import tudresden.ocl20.core.ModelManager;
import tudresden.ocl20.core.ModelManagerException;
import tudresden.ocl20.core.NetBeansRepository;
import tudresden.ocl20.core.Repository;
import tudresden.ocl20.core.RepositoryManager;
import tudresden.ocl20.pivot.metamodels.uml.internal.model.UmlModel;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelProvider;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelProvider;

/**
 * <p>
 * This class provides the NetBeans MDR Repository used by the UML 1.5 meta
 * model.
 * </p>
 * 
 * @author Ronny Brandt
 */
public class UmlModelProvider extends AbstractModelProvider implements
		IModelProvider {

	protected static final String REPOSITORY_PATH = "/.metadata/.tudresden/repository";
	protected static final String META_MODEL_FILE = "UML15_plus_OCLMetamodel.xml";
	protected static final String META_MODEL_PATH = "/resources/metamodel/"
			+ META_MODEL_FILE;
	protected static final String META_MODEL_NAME = "UML1.5";

	/* The Logger of this class. */
	private static final Logger logger = Logger
			.getLogger(UmlModelProvider.class);

	private Repository repository = null;
	private ModelManager modelmanager = null;
	private ModelPackage metaModel = null;

	/**
	 * <p>
	 * Loads a model from the NetBeans MDR Repository
	 * </p>
	 * 
	 * @param modelURL
	 *            The URL of the Model which shall be loaded.
	 * @return an {@link IModel} loaded from the repository.
	 */
	public IModel getModel(URL modelURL) throws ModelAccessException {

		if (logger.isDebugEnabled()) {
			logger.debug("getModel(modelURL=" + modelURL + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		IModel result;
		URI modelURI;
		RefPackage refPackage = null;

		this.init();

		/* try to create a URI */
		try {
			modelURI = URI.createURI(modelURL.toString());
		} catch (IllegalArgumentException e) {
			throw new ModelAccessException("Invalid URL: " + modelURL, e);
		}

		try {
			refPackage = this.modelmanager.getModel(modelURI.toString());

			/* Delete existing model. */
			if (refPackage != null) {
				this.modelmanager.deleteModel(refPackage);
			}
			// no else.

			refPackage = this.modelmanager.loadModel(this.metaModel, modelURI
					.toString(), modelURI.toString());

		}

		catch (ModelManagerException e) {

			logger.error("getModel(URL)", e);
			throw new ModelAccessException(
					"Problem in ModelManager loading model " + modelURI, e);
		}

		catch (IOException e) {

			logger.error("getModel(URL)", e);
			throw new ModelAccessException(
					"Problem in ModelManager loading model " + modelURI, e);
		}

		catch (MalformedXMIException e) {

			logger.error("getModel(URL)", e);
			throw new ModelAccessException("Malformed model file " + modelURI,
					e);
		}

		result = new UmlModel(modelURI.toString());

		if (logger.isDebugEnabled()) {
			logger.debug("getModel(URL) - exit - return value=" + result);
		}

		return result;
	}

	/**
	 * <p>
	 * Initializes the UmlModelProvider.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	private void init() throws ModelAccessException {

		/* If the repository is not set yet, initialize it. */
		if (this.repository == null) {

			IPath workspacePath;
			File repositoryDirectory;

			/* Get workspace path and repository directory. */
			workspacePath = Platform.getLocation();
			repositoryDirectory = new File(workspacePath
					+ UmlModelProvider.REPOSITORY_PATH);

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
		if (this.modelmanager == null) {

			this.modelmanager = ModelManager.getInstance();
		}
		// no else.

		/* If the meta model is not set yet, initialize it. */
		if (this.metaModel == null) {

			try {
				this.metaModel = this.repository
						.getMetaModel(UmlModelProvider.META_MODEL_NAME);

				/* If the meta model has not been in the repository, load it. */
				if (this.metaModel == null) {

					String metaModelTarget;
					File metaModelFile;

					metaModelTarget = System
							.getProperty(NetBeansRepository.MDR)
							+ "/" + META_MODEL_FILE;

					/*
					 * Check if the meta model file has already been copied to
					 * the .metadata folder.
					 */
					metaModelFile = new File("file:" + metaModelTarget);
					if (!metaModelFile.exists()) {
						this.copyFile(META_MODEL_PATH, metaModelTarget);
					}
					// no else.

					metaModelTarget = "file:" + metaModelTarget;

					this.metaModel = modelmanager.loadMetaModel(
							metaModelTarget, UmlModelProvider.META_MODEL_NAME);
				}
				// no else.

			}

			catch (Exception e) {
				String msg;

				msg = "Error during loading meta model. ";
				msg += e.getMessage();

				e.printStackTrace();

				throw new ModelAccessException(msg, e);
			}
		}
		// no else.
	}

	/**
	 * Copies a file from a given location inside this eclipse plug-in / package
	 * to a target path.
	 * 
	 * @param source
	 *            The source of this plug-in which shall be copied.
	 * @param target
	 *            The path to which the source shall be copied.
	 */
	public void copyFile(String source, String target)
			throws ModelAccessException {

		InputStream input;
		BufferedInputStream bufferedInput;
		BufferedOutputStream bufOutput;

		input = getClass().getResourceAsStream(source);
		bufferedInput = new BufferedInputStream(input);

		bufOutput = null;

		/* Try to open output and copy the file. */
		try {
			int count;
			byte[] inByte;
			
			bufOutput = new BufferedOutputStream(new FileOutputStream(target));
			inByte = new byte[4096];
			
			count = -1;
			while ((count = bufferedInput.read(inByte)) != -1) {
				bufOutput.write(inByte, 0, count);
			}

			bufOutput.close();
			bufferedInput.close();
		} 
		
		catch (IOException e) {
			String msg;

			msg = "Error during copying meta model. ";
			msg += e.getMessage();

			e.printStackTrace();

			throw new ModelAccessException(msg, e);
		}
	}

}
