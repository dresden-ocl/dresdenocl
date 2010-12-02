/*
 * Created on 15.01.2006
 *
 */
package tudresden.ocl20.transformation.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.jmi.model.ModelPackage;
import javax.jmi.reflect.RefPackage;

import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.Repository;
import tudresden.ocl20.core.RepositoryException;
import tudresden.ocl20.core.RepositoryManager;
import tudresden.ocl20.core.jmi.cwm.relational.RelationalPackage;
import tudresden.ocl20.core.jmi.uml15.uml15.Uml15Package;

/**
 * This is a helper class to access models in the repository.
 * 
 * @author Christian Wende
 *
 */
public class RepositoryFacade {
	
	private static final Class MOF_META_PKG_CLASS = ModelPackage.class;
	public static final String MOF_METAMODEL = MetaModelConst.getMMInfo(MetaModelConst.MOF14).name;
	private static final Class UML_PKG_CLASS = Uml15Package.class;
	public static final String UML_MODEL = MetaModelConst.getMMInfo(MetaModelConst.UML15).name;
	private static final Class CWM_PKG_CLASS = RelationalPackage.class;
	public static final String CWM_MODEL = MetaModelConst.getMMInfo(MetaModelConst.CWM).name;;
	private static final String UNKNOWN_MODELTYPE ="Unbekannter Modeltyp";
	private static RepositoryFacade theInstance;
	private Repository repository;
	private Map<String,Class> modeltypes;
	
	/**
	 * The constructor for the RepositoryFacade.
	 *
	 */
	private RepositoryFacade() {
		repository = RepositoryManager.getRepository();
		initModeltypes();
	}
	
	private void initModeltypes() {
		modeltypes = new HashMap<String, Class>();
		modeltypes.put(MOF_METAMODEL, MOF_META_PKG_CLASS);
		modeltypes.put(UML_MODEL, UML_PKG_CLASS);
		modeltypes.put(CWM_MODEL, CWM_PKG_CLASS);
	}

	/**
	 * Returns the single instance of the RepositoryFacade.
	 * @return Returns the single instance of the RepositoryFacade.
	 */
	public static RepositoryFacade getInstance() {
		if (theInstance == null) {
			theInstance = new RepositoryFacade();
		}
		return theInstance;
	}
	
	/**
	 * Returns all models and metamodels in the repository.
	 * @return Returns all models in the repository.
	 * @throws RepositoryException
	 */
	public Collection<String> getAllModels() throws RepositoryException {
		Collection<String> modelNames = null;
		try {
			modelNames = repository.getAllNames();
		} catch (RepositoryException e) {
			throw e; 
		}
		return modelNames;
	}
	
	/**
	 * Returns all models in the repository.
	 * @return Returns all models in the repository.
	 * @throws RepositoryException
	 */
	public Collection<String> getModels() throws RepositoryException {
		Collection<String> modelNames = null;
		Collection<String> modelsOnly = new ArrayList<String>();
		modelNames = repository.getAllNames();
		RefPackage modelPkg;
		for(String name: modelNames) {
			modelPkg = repository.getModel(name);
			if (!Arrays.asList(modelPkg.getClass().getInterfaces()).contains(ModelPackage.class)){
				modelsOnly.add(name);
			}
			
		}
		
		return modelsOnly;
	}
	
	/**
	 * Returns all metamodels in the repository.
	 * @return Returns all metamodels in the repository.
	 * @throws RepositoryException
	 */
	public Collection<String> getMetamodels() throws RepositoryException {
		return getModelsForType(MOF_METAMODEL);
	}
	
	/**
	 * Returns the model for the given name.
	 * @param modelname The name of the model that should be returned.
	 * @return Returns the model for the given name.
	 * @throws RepositoryException
	 */
	public RefPackage getModel(String modelname) throws RepositoryException {
		return repository.getModel(modelname);
	}
	
	/**
	 * Returns the type of the model with the given name.
	 * @param modelName The name of the model the type should be returned for.
	 * @return Returns the type of the model with the given name.
	 * @throws RepositoryException
	 */
	public String getModeltype(String modelName) throws RepositoryException {
		return getModeltype(repository.getModel(modelName));
	}

	/**
	 * Returns the type of the model with the given RefPackage.
	 * @param modelName The RefPackage of the model the type should be returned for.
	 * @return Returns the type of the model with the RefPackage.
	 * @throws RepositoryException
	 */
	public String getModeltype(RefPackage model) {
		Collection<String> typeNames = modeltypes.keySet();
		
		for(String typeName : typeNames) {
			if(Arrays.asList(model.getClass().getInterfaces()).contains(modeltypes.get(typeName))) {
				return typeName;			
			}
		}
		return UNKNOWN_MODELTYPE + Arrays.toString(model.getClass().getInterfaces());
	}
	
	/**
	 * Returns all models for the given type.
	 * @param modeltype The searched modeltype.
	 * @return Returns all models for the given type.
	 * @throws RepositoryException
	 */
	public Collection<String> getModelsForType(String modeltype) throws RepositoryException {
		Class type = null;
		Collection<String> modelsOfType = new ArrayList<String>();
		
		Collection<String> typeNames = modeltypes.keySet();
		
		for(String typeName : typeNames) {
			if(modeltype.equals(typeName)) {
				type = modeltypes.get(typeName);			
			}
		}
		
		if (type == null) {
			return modelsOfType;
		}
		
		Collection<String> modelNames = repository.getAllNames();
		RefPackage modelPkg;
		for(String name: modelNames) {
			modelPkg = repository.getModel(name);
			if (Arrays.asList(modelPkg.getClass().getInterfaces()).contains(type)){
				modelsOfType.add(name);
			}
			
		}
		
	return modelsOfType;	
	}
	
}
