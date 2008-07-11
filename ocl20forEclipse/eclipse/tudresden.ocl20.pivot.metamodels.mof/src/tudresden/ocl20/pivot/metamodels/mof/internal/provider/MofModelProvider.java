package tudresden.ocl20.pivot.metamodels.mof.internal.provider;

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

import tudresden.ocl20.core.ModelManager;
import tudresden.ocl20.core.ModelManagerException;
import tudresden.ocl20.core.NetBeansRepository;
import tudresden.ocl20.core.Repository;
import tudresden.ocl20.core.RepositoryException;
import tudresden.ocl20.core.RepositoryManager;
import tudresden.ocl20.pivot.metamodels.mof.internal.model.MofModel;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelProvider;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelProvider;

public class MofModelProvider extends AbstractModelProvider implements
		IModelProvider {

	// Logger for this class
	private static final Logger logger = Logger
			.getLogger(MofModelProvider.class);

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
			javax.jmi.reflect.RefPackage mofmodel = modelmanager
					.getModel(modelURI.toString());
			// TODO: Delete existing model or use it?
			if (mofmodel != null)
				modelmanager.deleteModel(mofmodel);
			rp = modelmanager.loadModel(metamodel, modelURI.toString(),
					modelURI.toString());
			ModelPackage modelasMM = repository.getMetaModel(modelURI
					.toString()
					+ "_as_MM");
			if (modelasMM == null)
				modelmanager.loadMetaModel(modelURI.toString(), modelURI
						.toString()
						+ "_as_MM");
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
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model = new MofModel(modelURI.toString());
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

		String metamodelname = "file:"+resPath+"metamodels/MOF14_plus_OCLMetamodel.xml_as_MM";

		if (modelmanager == null)
			modelmanager = ModelManager.getInstance();

		if (metamodel == null) {
			try {
				metamodel = repository.getMetaModel(metamodelname);

				if (metamodel == null) {
					// System.out.println("resPathURI: " +
					// resPath.toURI().getPath());
					metamodel = modelmanager.loadMetaModel("file:" + resPath
							+ "metamodels/MOF14_plus_OCLMetamodel.xml",
							metamodelname);
				}
			} catch (Exception e) {
				throw new ModelAccessException(
						"Metamodel MOF not found in repository " + repository,
						e);
			}
		}
	}
}
