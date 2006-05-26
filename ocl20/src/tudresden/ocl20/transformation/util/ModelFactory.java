/*
 * Created on 18.01.2006
 *
 */
package tudresden.ocl20.transformation.util;

import javax.jmi.model.ModelPackage;
import javax.jmi.model.MofPackage;
import javax.jmi.model.NameNotFoundException;
import javax.jmi.reflect.RefPackage;

import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.MetaModelUtil;
import tudresden.ocl20.core.Repository;
import tudresden.ocl20.core.RepositoryException;
import tudresden.ocl20.core.RepositoryManager;
import tudresden.ocl20.transformation.exception.ModelAccessException;

/**
 * The ModelFactory provides a simple way to access or create a given model in the Repository.
 * 
 * @author Christian Wende
 *
 * @param <OUT>
 */
public class ModelFactory<OUT extends RefPackage> {
	
	private static final String ERROR_ACCESS = "An internal error occured while accessing a model extent in the MDR";
	private static final String ERROR_CREATE = "An internal error occured while creating a model extent in the MDR";

	/**
	 * This method creates a model for the given type with the given name in the repository.
	 * @param modeltype The type of the model that should be created.
	 * @param modelname The name of the model that should be created.
	 * @param overwrite Flags if an exiting model with the given name should be overwritten or not.
	 * @return Returns the created model.
	 * @throws ModelAccessException
	 */
	public OUT createExtent(String modeltype, String modelname, boolean overwrite) throws ModelAccessException {
		Repository repository = RepositoryManager.getRepository();
		ModelPackage metamodel;
	    OUT modelExtent;

		try {
		    modelExtent = (OUT) repository.getModel(modelname);
				    
		    if (modelExtent!=null) {
		    	if (overwrite) {
		    		repository.deleteModel(modelExtent);
		    	}
		    	else {
		    		return modelExtent;
		    	}
		    }
		} catch (RepositoryException e) {
			throw new ModelAccessException(ERROR_ACCESS, modeltype, modelname);
		}
		
		try {
			metamodel = repository.getMetaModel(MetaModelConst.getMMInfo(modeltype).name);
			String topPackageName = MetaModelConst.getMMInfo(modeltype).packageToInstantiate;
		    MofPackage topPackage;
			topPackage = MetaModelUtil.findMofPackage(metamodel, topPackageName);
			modelExtent = (OUT) repository.createModel(modelname,topPackage);
		    return modelExtent;
			
		} catch (NameNotFoundException e) {
			throw new ModelAccessException(ERROR_CREATE , modeltype, modelname);
		} catch (RepositoryException e) {
			throw new ModelAccessException(ERROR_CREATE, modeltype, modelname);
		}
	
		    
		
	}

	/**
	 * Access a model for the given type name the repository.
	 * @param modelname the name of the model that should be accessed.
	 * @return The model for the given name.
	 * @throws ModelAccessException
	 */
	public OUT accessExtent(String modelname) throws ModelAccessException {
		Repository repository = RepositoryManager.getRepository();
		OUT modelExtent;
		
		try {
			modelExtent = (OUT) repository.getModel(modelname);
		} catch (RepositoryException e) {
			throw new ModelAccessException(ERROR_ACCESS, modelname);
		}	
		
		if (modelExtent == null) {
			throw new ModelAccessException(ERROR_ACCESS, modelname);
		}
		return modelExtent;
	}

}
