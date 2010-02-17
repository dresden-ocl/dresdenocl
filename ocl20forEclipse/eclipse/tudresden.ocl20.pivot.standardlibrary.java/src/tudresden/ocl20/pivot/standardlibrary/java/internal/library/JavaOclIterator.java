package tudresden.ocl20.pivot.standardlibrary.java.internal.library;

import java.util.Iterator;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclIterator;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * 
 * @author Michael Thiele
 * 
 * @param <T>
 *          the type of the collection
 */
public class JavaOclIterator<T extends OclAny> implements OclIterator<T> {

	protected Iterator<IModelInstanceElement> iterator;
	
	protected boolean isInvalid;
	

	public JavaOclIterator(OclCollection<? extends OclAny> collection) {
		
		this.iterator =
				collection.getModelInstanceCollection().getCollection().iterator();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclIterator#hasNext()
	 */
	public OclBoolean hasNext() {

		return JavaStandardLibraryFactory.INSTANCE.createOclBoolean(iterator
				.hasNext());
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclIterator#next()
	 */
	@SuppressWarnings("unchecked")
	public T next() {

		IModelInstanceElement imiElement = iterator.next();
		return (T) JavaStandardLibraryFactory.INSTANCE.createOclAny(imiElement);
	}

}
