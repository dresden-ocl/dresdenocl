package tudresden.ocl20.pivot.modelbus.modelinstance.types;

import org.eclipse.emf.common.util.UniqueEList;

/**
 * <p>
 * A wrapper for an ordered set.
 * </p>
 * 
 * @author Michael Thiele
 * 
 */
public interface IModelInstanceOrderedSet extends IModelInstanceCollection {

	/**
	 * <p>
	 * Returns the underlying {@link UniqueEList Ecore UniqueEList}.
	 * </p>
	 * 
	 * @return the underlying {@link UniqueEList Ecore UniqueEList}.
	 */
	UniqueEList<Object> getUniqueEList();

}
