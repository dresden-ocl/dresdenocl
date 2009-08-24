package tudresden.ocl20.pivot.modelbus.modelinstance.types;

import java.util.List;

/**
 * <p>
 * A wrapper for an ordered multiset.
 * </p>
 * 
 * @author Michael Thiele
 * 
 */
public interface IModelInstanceSequence extends IModelInstanceCollection {

	/**
	 * Returns the underlying {@link List Java list}.
	 * 
	 * @return the underlying {@link List Java list}.
	 */
	List<Object> getList();

}
