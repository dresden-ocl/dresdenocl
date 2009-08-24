package tudresden.ocl20.pivot.modelbus.modelinstance;

import java.util.WeakHashMap;

import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstancePrimitiveType;

/**
 * <p>
 * A factory for {@link IModelInstanceElement}s.
 * </p>
 * 
 * @author Michael Thiele
 * 
 */
public interface IModelInstanceFactory {

	/**
	 * <p>
	 * Creates a new adapter for the given object. Uses a {@link WeakHashMap} to
	 * cache adaptions to {@link IModelInstanceObject}s, but not to
	 * {@link IModelInstancePrimitiveType}s and {@link IModelInstanceCollection}s
	 * (since copies of those elements can be created with the same contents).
	 * </p>
	 * 
	 * @param adapted
	 *          the object to adapt
	 * @return the adapter for the given object
	 */
	IModelInstanceElement createIModelInstanceElement(Object adapted);

}
