package tudresden.ocl20.pivot.essentialocl.standardlibrary;

public interface OclSortedCollection<T extends OclAny> extends OclCollection<T> {

	/**
	 * 
	 * @param i
	 * @return the <code>i</code>-th element of <code>this</code>.
	 */
	T at(OclInteger i);

	/**
	 * 
	 * @return the first element in <code>this</code>.
	 */
	T first();

	/**
	 * 
	 * @param obj
	 * @return the index of object <code>obj</code> in the sequence.
	 */
	OclInteger indexOf(T obj);

	/**
	 * 
	 * @return the last element in <code>this</code>.
	 */
	T last();
}
