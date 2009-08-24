package tudresden.ocl20.pivot.modelbus.modelinstance.types;

import java.util.List;

/**
 * <p>
 * A wrapper for an ordered multiset.
 * </p>
 * 
 * @author Michael Thiele
 */
public interface IModelInstanceSequence<T> extends IModelInstanceCollection<T> {

	/**
	 * <p>
	 * Returns the underlying {@link List Java list}.
	 * </p>
	 * 
	 * @return the underlying {@link List Java list}.
	 */
	List<Object> getList();
}