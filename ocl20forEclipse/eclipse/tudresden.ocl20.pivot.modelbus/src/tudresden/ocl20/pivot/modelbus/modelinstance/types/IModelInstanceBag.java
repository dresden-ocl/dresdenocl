package tudresden.ocl20.pivot.modelbus.modelinstance.types;

import java.util.List;

/**
 * <p>
 * A wrapper for an unordered multiset.
 * </p>
 * 
 * @author Michael Thiele
 * 
 */
public interface IModelInstanceBag<T> extends IModelInstanceCollection<T> {

	/**
	 * <p>
	 * Returns the underlying {@link List Java list}. Since {@link List} is
	 * ordered it also can represent unordered lists.
	 * </p>
	 * 
	 * @return the underlying {@link List Java list}
	 */
	List<Object> getList();

}