package tudresden.ocl20.benchmark.common;

import java.net.URL;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelInstanceProvider;

/**
 * 
 * @author Franz Eichhorn
 */
public class ModelInstanceProvider extends AbstractModelInstanceProvider {

	
	
	/**
	 * returns model instance when supplied as url (not needed, not used)
	 */
	public IModelInstance getModelInstance(URL modelInstanceUrl, IModel model)
			throws ModelAccessException {
		
		return null;
	}

}
