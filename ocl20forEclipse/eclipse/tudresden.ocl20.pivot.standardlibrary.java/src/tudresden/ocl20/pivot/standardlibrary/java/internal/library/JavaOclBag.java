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
package tudresden.ocl20.pivot.standardlibrary.java.internal.library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * <p>
 * This class implements the OCL type {@link OclBag} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 * @author Michael Thiele
 */
@SuppressWarnings("unchecked")
public class JavaOclBag<T extends OclAny> extends JavaOclUnsortedCollection<T>
		implements OclBag<T> {

	public JavaOclBag(
			IModelInstanceCollection<IModelInstanceElement> imiCollection,
			Type genericType) {

		super(imiCollection, genericType);
	}

	public JavaOclBag(String undefinedReason, Type genericType) {

		super(undefinedReason, genericType);
	}

	public JavaOclBag(Throwable invalidReason, Type genericType) {

		super(invalidReason, genericType);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#isEqualTo(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclAny)
	 */
	public OclBoolean isEqualTo(OclAny that) {

		OclBoolean result = null;

		result = checkIsEqualTo(that);

		if (result == null) {

			/* Check if the given object is no OclBag. */
			if (!(that instanceof OclBag)) {
				result = JavaOclBoolean.getInstance(false);
			}

			/* Else compare the two Bags. */
			else {

				boolean booleanResult;

				// bagList needs to be a copy
				Collection<IModelInstanceElement> bagList1 =
						new ArrayList<IModelInstanceElement>(this
								.getModelInstanceCollection().getCollection());
				Collection<IModelInstanceElement> bagList2 =
						((IModelInstanceCollection) that.getModelInstanceElement())
								.getCollection();

				/* Check if bagList1 and bagList2 have the same size. */
				if (bagList1.size() != bagList2.size()) {
					booleanResult = false;
				}
				else if (bagList1.isEmpty() && bagList2.isEmpty()) {
					booleanResult = true;
				}

				/* Else iterate over the elements and compare them. */
				else {
					for (IModelInstanceElement anElement : bagList2) {

						/* check if anElement is in both lists. */
						if (bagList1.contains(anElement)) {
							bagList1.remove(anElement);
						}

						else {
							booleanResult = false;
							break;
						}
					}

					/*
					 * If bagList1 is not empty, there are more elements in bagList1 ->
					 * not equal
					 */
					booleanResult = bagList1.isEmpty();

				}

				result = JavaOclBoolean.getInstance(booleanResult);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag#excluding(java
	 * .lang.Object)
	 */
	public OclBag<T> excluding(T that) {

		OclBag<T> result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getBagType(genericType), this, that);

		if (result == null)
			result =
					checkUndefined("excluding", EssentialOclPlugin
							.getOclLibraryProvider().getOclLibrary().getBagType(genericType),
							this);

		if (result == null) {
			/* Else try to remove the given Object. */

			List<IModelInstanceElement> resultCollection;

			resultCollection =
					new ArrayList<IModelInstanceElement>(getModelInstanceCollection()
							.getCollection());

			while (resultCollection.remove(that.getModelInstanceElement())) {
				/* Remove the given object as often as possible. */
			}

			result =
					JavaStandardLibraryFactory.INSTANCE.createOclBag(resultCollection,
							genericType);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#flatten ()
	 */
	public <T2 extends OclAny> OclBag<T2> flatten() {

		OclBag<T2> result = null;

		// FIXME Michael: better way to determine real return type
		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getBagType(
								EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
										.getOclAny()), this);

		if (result == null)
			result =
					checkUndefined("flatten", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getBagType(
									EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
											.getOclAny()), this);

		if (result == null) {

			List<IModelInstanceElement> flat = new ArrayList<IModelInstanceElement>();
			Type resultType =
					flatRec(getModelInstanceCollection().getCollection(), flat);

			result =
					JavaStandardLibraryFactory.INSTANCE.createOclBag(flat, resultType);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag#including(java
	 * .lang.Object)
	 */
	public OclBag<T> including(T anObject) {

		OclBag<T> result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getBagType(genericType), this, anObject);

		if (result == null)
			result =
					checkUndefined("including", EssentialOclPlugin
							.getOclLibraryProvider().getOclLibrary().getBagType(genericType),
							this);

		if (result == null) {
			List<IModelInstanceElement> include =
					new ArrayList<IModelInstanceElement>();

			checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
					.getBagType(genericType), this);

			include.addAll(getModelInstanceCollection().getCollection());
			include.add(anObject.getModelInstanceElement());

			result =
					JavaStandardLibraryFactory.INSTANCE
							.createOclBag(include, genericType);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag#intersection
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag)
	 */
	public OclBag<T> intersection(OclBag<T> that) {

		OclBag<T> result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getBagType(genericType), this, that);

		if (result == null)
			result =
					checkUndefined("intersection", EssentialOclPlugin
							.getOclLibraryProvider().getOclLibrary().getBagType(genericType),
							this, that);

		List<IModelInstanceElement> intersection =
				new ArrayList<IModelInstanceElement>();

		List<IModelInstanceElement> otherCollectionCopy =
				new ArrayList<IModelInstanceElement>(that.getModelInstanceCollection()
						.getCollection());

		/* Iterate over this bag. */
		for (IModelInstanceElement element : getModelInstanceCollection()
				.getCollection()) {
			/*
			 * if other collection has the same element, then it is in the
			 * intersection; remove it, so that it is not counted multiple times
			 */
			if (otherCollectionCopy.remove(element)) {
				intersection.add(element);
			}
		}

		result =
				JavaStandardLibraryFactory.INSTANCE.createOclBag(intersection,
						genericType);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag#union(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclSet)
	 */
	public OclBag<T> union(OclSet<T> that) {

		OclBag<T> result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getBagType(genericType), this, that);

		if (result == null)
			result =
					checkUndefined("union", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getBagType(genericType), this, that);

		if (result == null) {
			List<IModelInstanceElement> union =
					new ArrayList<IModelInstanceElement>();

			Collection<IModelInstanceElement> otherCollection =
					that.getModelInstanceCollection().getCollection();

			union.addAll(getModelInstanceCollection().getCollection());
			union.addAll(otherCollection);

			result =
					JavaStandardLibraryFactory.INSTANCE.createOclBag(union, genericType);
		}

		return result;
	}
}
