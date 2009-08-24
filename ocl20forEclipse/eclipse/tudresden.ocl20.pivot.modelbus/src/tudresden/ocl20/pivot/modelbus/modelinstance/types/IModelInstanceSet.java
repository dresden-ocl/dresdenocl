package tudresden.ocl20.pivot.modelbus.modelinstance.types;

import java.util.Set;

/**
 * <p>A wrapper for an unordered set.</p>
 * 
 * @author Michael Thiele
 */
public interface IModelInstanceSet<T> extends IModelInstanceCollection<T> {

	/**
	 * <p>
	 * Returns the underlying {@link Set Java set}.
	 * </p>
	 * 
	 * @return the underlying {@link Set Java set}.
	 */
	Set<Object> getSet();
}