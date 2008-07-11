package tudresden.ocl20.pivot.metamodels.uml.internal.provider;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.jmi.model.ModelPackage;
import javax.jmi.reflect.RefPackage;
import javax.jmi.xmi.MalformedXMIException;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

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

public class UmlModelProvider extends AbstractModelProvider implements
		IModelProvider {

	// Logger for this class
	private static final Logger logger = Logger
			.getLogger(UmlModelProvider.class);

	private Repository repository = null;
	private ModelManager modelmanager = null;
	private ModelPackage metamodel = null;

	public IModel getModel(URL modelURL) throws ModelAccessException {

		if (logger.isDebugEnabled()) {
			logger.debug("getModel(modelURL=" + modelURL + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		IModel model;
		URI modelURI;
		RefPackage rp = null;

		init();

		// try to create a URI
		try {
			modelURI = URI.createURI(modelURL.toString());
		} catch (IllegalArgumentException e) {
			throw new ModelAccessException("Invalid URL: " + modelURL, e); //$NON-NLS-1$
		}

		try {
			rp = modelmanager.getModel(modelURI.toString());
			// TODO: Delete existing model or use it?
			if (rp != null)
				modelmanager.deleteModel(rp);
			rp = modelmanager.loadModel(metamodel, modelURI.toString(),
					modelURI.toString());
		} catch (ModelManagerException e) {
			logger.error("getModel(URL)", e);

			throw new ModelAccessException(
					"Problem in ModelManager loading model " + modelURI, e);
		} catch (IOException e) {
			logger.error("getModel(URL)", e);

			throw new ModelAccessException(
					"Problem in ModelManager loading model " + modelURI, e);
		} catch (MalformedXMIException e) {
			logger.error("getModel(URL)", e);

			throw new ModelAccessException("Malformed model file " + modelURI,
					e);
		}

		model = new UmlModel(modelURI.toString());
		// model = null;

		if (logger.isDebugEnabled()) {
			logger.debug("getModel(URL) - exit - return value=" + model);
		}
		return model;

	}

	private void init() throws ModelAccessException {
		String resPath = null;
		try {
			resPath = FileLocator.resolve(
					FileLocator.find(Platform
							.getBundle("tudresden.ocl20.mdrepository"),
							new Path("resources"), null)).getPath();
		} catch (IOException e) {
			throw new ModelAccessException(
					"Error resolving path plugindir/resources/repository", e); //$NON-NLS-1$
		}

		if (repository == null) {
			// repPath = "D:\\rep";
			System.setProperty(NetBeansRepository.MDR, resPath + "/repository");
			repository = RepositoryManager.getRepository();
		}

		if (modelmanager == null)
			modelmanager = ModelManager.getInstance();

		String metamodelname = "file:" + resPath
				+ "metamodels/UML15_plus_OCLMetamodel.xml_as_MM";

		if (metamodel == null) {
			try {
				metamodel = repository.getMetaModel(metamodelname);

				if (metamodel == null) {
					metamodel = modelmanager.loadMetaModel("file:" + resPath
							+ "metamodels/UML15_plus_OCLMetamodel.xml",
							metamodelname);
				}
			} catch (Exception e) {
				throw new ModelAccessException(
						"Metamodel UML not found in repository " + repository,
						e);
			}
		}
	}
}
