package tudresden.ocl20.pivot.standardlibrary.java.internal.library;

import java.util.Iterator;

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclIterator;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * 
 * @author Michael Thiele
 * 
 * @param <T>
 *            the type of the collection
 */
public class JavaOclIterator<T extends OclAny> implements OclIterator<T> {

	protected Iterator<IModelInstanceElement> iterator;

	protected Throwable invalidReason;

	protected Type genericType;

	public JavaOclIterator(OclCollection<? extends OclAny> collection) {

		if (collection.oclIsInvalid().isTrue())
			invalidReason = collection.getInvalidReason();
		else if (collection.oclIsUndefined().isTrue())
			invalidReason = new RuntimeException(
					"Iterator on undefined collection.");
		else
			this.iterator = collection.getModelInstanceCollection()
					.getCollection().iterator();

		this.genericType = collection.getGenericType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclIterator#hasNext()
	 */
	public OclBoolean hasNext() {

		if (invalidReason != null)
			return JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
					EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclBoolean(), invalidReason);
		return JavaStandardLibraryFactory.INSTANCE.createOclBoolean(iterator
				.hasNext());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclIterator#next()
	 */
	@SuppressWarnings("unchecked")
	public T next() {

		if (invalidReason != null)
			return JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
					genericType, invalidReason);
		IModelInstanceElement imiElement = iterator.next();
		return (T) JavaStandardLibraryFactory.INSTANCE.createOclAny(imiElement);
	}

}
