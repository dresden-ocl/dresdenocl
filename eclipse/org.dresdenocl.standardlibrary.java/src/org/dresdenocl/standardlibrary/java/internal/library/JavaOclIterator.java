package org.dresdenocl.standardlibrary.java.internal.library;

import java.util.Iterator;

import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.essentialocl.standardlibrary.OclAny;
import org.dresdenocl.essentialocl.standardlibrary.OclBoolean;
import org.dresdenocl.essentialocl.standardlibrary.OclCollection;
import org.dresdenocl.essentialocl.standardlibrary.OclIterator;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.standardlibrary.java.factory.JavaStandardLibraryFactory;

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
	 * org.dresdenocl.essentialocl.standardlibrary.OclIterator#hasNext()
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
	 * org.dresdenocl.essentialocl.standardlibrary.OclIterator#next()
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
