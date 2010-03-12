package tudresden.ocl20.pivot.modelbus.metamodel.internal;

import java.util.HashMap;
import java.util.Map;

import tudresden.ocl20.pivot.modelbus.metamodel.IMetamodel;
import tudresden.ocl20.pivot.modelbus.metamodel.IMetamodelRegistry;

/**
 * <p>
 * A simple implementation of the {@link IMetamodelRegistry} interface that
 * internally just uses a {@link Map} to keep track of registered meta-models.
 * </p>
 * <p>
 * In a stand-alone application of DresdenOCL, this implies that new meta-models
 * have to be added by hand to this registry.
 * </p>
 * 
 * @author Michael Thiele
 * 
 */
public class StandaloneMetamodelRegistry implements IMetamodelRegistry {

	protected Map<String, IMetamodel> metaModels =
			new HashMap<String, IMetamodel>();

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.metamodel.IMetamodelRegistry#addMetamodel
	 * (tudresden.ocl20.pivot.modelbus.metamodel.IMetamodel)
	 */
	public void addMetamodel(IMetamodel metamodel) {

		metaModels.put(metamodel.getId(), metamodel);
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.metamodel.IMetamodelRegistry#dispose()
	 */
	public void dispose() {

		metaModels.clear();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.metamodel.IMetamodelRegistry#getMetamodel
	 * (java.lang.String)
	 */
	public IMetamodel getMetamodel(String id) {

		return metaModels.get(id);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.metamodel.IMetamodelRegistry#getMetamodels()
	 */
	public IMetamodel[] getMetamodels() {

		return metaModels.values().toArray(new IMetamodel[0]);
	}

}
