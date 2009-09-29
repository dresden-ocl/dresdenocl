package tudresden.ocl20.pivot.essentialocl.standardlibrary;

public interface OclUnsortedCollection<T extends OclAny> extends OclCollection<T> {

	/**
	 * 
	 * @param s
	 * @return the intersection of <code>this</code> and <code>s</code> (i.e., the 
	 * set of all elements that are in both <code>this</code> and <code>s</code>).
	 */
	OclSet<T> intersection(OclSet<T> s);
	
	/**
	 * 
	 * @param s
	 * 
	 * @return The union of <code>this</code> and <code>s</code>.
	 */
	OclBag<T> union(OclBag<T> s);
}
