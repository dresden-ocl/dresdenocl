package tudresden.ocl20.pivot.standardlibrary.java.internal.library;

import java.util.Iterator;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclIterator;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
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

	@SuppressWarnings("unchecked")
	public JavaOclIterator(OclCollection<? extends OclAny> collection) {

		this.iterator =
				((IModelInstanceCollection<IModelInstanceElement>) collection
						.getModelInstanceElement()).getCollection().iterator();
	}

	public OclBoolean hasNext() {

		return JavaStandardLibraryFactory.INSTANCE.createOclBoolean(iterator
				.hasNext());
	}

	@SuppressWarnings("unchecked")
	public T next() {

		IModelInstanceElement imiElement = iterator.next();
		return (T) JavaStandardLibraryFactory.INSTANCE.createOclAny(imiElement);
	}

}
