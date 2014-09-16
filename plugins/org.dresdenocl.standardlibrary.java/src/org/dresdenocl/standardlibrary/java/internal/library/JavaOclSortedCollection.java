/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Ronny Brandt (Ronny_Brandt@web.de).                    *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package org.dresdenocl.standardlibrary.java.internal.library;

import java.util.List;

import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.essentialocl.standardlibrary.OclAny;
import org.dresdenocl.essentialocl.standardlibrary.OclInteger;
import org.dresdenocl.essentialocl.standardlibrary.OclSortedCollection;
import org.dresdenocl.modelinstancetype.types.IModelInstanceCollection;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.modelinstancetype.types.IModelInstanceInteger;
import org.dresdenocl.modelinstancetype.types.base.BasisJavaModelInstanceFactory;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * <p>
 * Provides an implementation of {@link OclSortedCollection} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 * @author Michael Thiele
 */
@SuppressWarnings("unchecked")
public abstract class JavaOclSortedCollection<T extends OclAny> extends
		JavaOclCollection<T> implements OclSortedCollection<T> {

	/**
	 * <p>
	 * Instantiates a {@link JavaOclSet}.
	 * </p>
	 * 
	 * @param imiCollection
	 *          The adapted model instance object of this
	 *          {@link OclSortedCollection}.
	 */
	public JavaOclSortedCollection(
			IModelInstanceCollection<IModelInstanceElement> imiCollection,
			Type genericType) {

		super(imiCollection, genericType);
	}

	public JavaOclSortedCollection(String undefinedReason, Type genericType) {

		super(undefinedReason, genericType);
	}

	public JavaOclSortedCollection(Throwable invalidReason, Type genericType) {

		super(invalidReason, genericType);
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclSortedCollection
	 * #at(org.dresdenocl.essentialocl.standardlibrary.OclInteger)
	 */
	public T at(OclInteger index) {

		T result = null;

		if (this.invalidReason != null)
			result =
					(T) JavaStandardLibraryFactory.INSTANCE.createOclInvalid(genericType,
							this.invalidReason);

		if (result == null)
			result = checkUndefined("concat", genericType, this, index);

		if (result == null) {
			/* Else compute the result. */
			int intIndex =
					((IModelInstanceInteger) index.getModelInstanceElement()).getLong()
							.intValue();

			/* Try to get the element of the given index. */
			try {

				/*
				 * intIndex <= 0 -> NPE in BasicEList, therefore throw
				 * IndexOutOfBoundsException
				 */
				if (intIndex <= 0)
					throw new IndexOutOfBoundsException(
							"Index <= 0 is not valid for OCL collections as they start at index 1.");

				IModelInstanceElement element =
						((List<IModelInstanceElement>) getModelInstanceCollection()
								.getCollection()).get(intIndex - 1);
				result = (T) JavaStandardLibraryFactory.INSTANCE.createOclAny(element);

			} catch (IndexOutOfBoundsException e) {
				result =
						JavaStandardLibraryFactory.INSTANCE
								.createOclInvalid(genericType, e);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclSortedCollection
	 * #first()
	 */
	public T first() {

		IModelInstanceInteger imiInteger =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(1L);

		return this.at(new JavaOclInteger(imiInteger));
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclSortedCollection
	 * #indexOf(java.lang.Object)
	 */
	public OclInteger indexOf(T anObject) {
	
		OclInteger result;
	
		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclInteger(), this, anObject);
	
		if (result == null)
			result = checkUndefined("indexOf", EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclInteger(), this);
	
		if (result == null) {
			/* Else compute the result. */
			int intResult;
	
			intResult =
					((List<IModelInstanceElement>) getModelInstanceCollection()
							.getCollection()).indexOf(anObject.getModelInstanceElement()) + 1;
			// FIXME Michael: -1 / not found? / invalid?
	
			IModelInstanceInteger imiResult =
					BasisJavaModelInstanceFactory.createModelInstanceInteger(new Long(
							intResult));
			result = new JavaOclInteger(imiResult);
		}
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclSortedCollection
	 * #last()
	 */
	public T last() {

		return this.at(this.size());
	}

}
