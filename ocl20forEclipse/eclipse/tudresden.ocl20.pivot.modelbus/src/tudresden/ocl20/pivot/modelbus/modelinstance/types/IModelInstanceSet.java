package tudresden.ocl20.pivot.modelbus.modelinstance.types;

import java.util.Set;

/**
 * A wrapper for an unordered set.
 * 
 * @author Michael Thiele
 * 
 */
public interface IModelInstanceSet extends IModelInstanceCollection {

	/**
	 * <p>
	 * Returns the underlying {@link Set Java set}.
	 * </p>
	 * 
	 * @return the underlying {@link Set Java set}.
	 */
	Set<Object> getSet();

}
