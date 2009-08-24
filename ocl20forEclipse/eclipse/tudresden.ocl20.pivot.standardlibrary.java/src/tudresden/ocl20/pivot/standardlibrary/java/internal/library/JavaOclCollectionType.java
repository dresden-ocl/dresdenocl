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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollectionType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclIterator;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;

/**
 * <p>
 * This class implements the OCL type {@link OclCollectionType} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 */
public class JavaOclCollectionType extends JavaOclType implements
		OclCollectionType {

	/** The kind of this {@link OclCollectionType}. */
	private OclCollectionTypeKind kind;

	/** The generic type of this {@link OclCollectionType}. */
	private OclType elementType;

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclCollectionType}.
	 * </p>
	 * 
	 * @param kind
	 *            The kind of this {@link OclCollectionType}.
	 * @param elementType
	 *            The generic type of this {@link OclCollectionType}.
	 */
	public JavaOclCollectionType(OclCollectionTypeKind kind, OclType elementType) {
		super(Collection.class, kind + "(" + elementType.getName() + ")");

		this.kind = kind;
		this.elementType = elementType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclType
	 * #createInstance
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public OclRoot createInstance(OclRoot anOclRoot) {

		OclRoot result;

		/* Check which kind of OclCollectionType shall be returned. */
		if (isOfKind(anOclRoot).isTrue()) {

			if (this.kind == OclCollectionTypeKind.BAG) {

				List<OclRoot> adaptedList;

				adaptedList = new ArrayList<OclRoot>(
						(Collection<OclRoot>) anOclRoot.getAdaptee());

				result = new JavaOclBag(adaptedList);
			}

			else if (this.kind == OclCollectionTypeKind.ORDEREDSET) {

				List<OclRoot> adaptedList;

				adaptedList = new ArrayList<OclRoot>(
						(Collection<OclRoot>) anOclRoot.getAdaptee());

				result = new JavaOclOrderedSet(adaptedList);
			}

			else if (this.kind == OclCollectionTypeKind.SEQUENCE) {

				List<OclRoot> adaptedList;

				adaptedList = new ArrayList<OclRoot>(
						(Collection<OclRoot>) anOclRoot.getAdaptee());

				result = new JavaOclSequence(adaptedList);
			}

			else if (this.kind == OclCollectionTypeKind.SET) {
				Set<OclRoot> adaptedSet;

				adaptedSet = new HashSet<OclRoot>(
						(Collection<OclRoot>) anOclRoot.getAdaptee());

				result = new JavaOclSet(adaptedSet);
			}

			else {
				result = JavaOclVoid.getInstance();
			}
		}

		else {
			result = JavaOclVoid.getInstance();
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclType
	 * #isOfKind(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public OclBoolean isOfKind(OclRoot anOclRoot) {

		OclBoolean result;

		result = JavaOclBoolean.getInstance(false);

		/* Check if the given OclRoot is a Collection. */
		if (anOclRoot instanceof OclCollection) {
			OclCollection<OclRoot> aCollection;
			OclCollectionTypeKind aKind;

			aCollection = (OclCollection<OclRoot>) anOclRoot;
			aKind = ((JavaOclCollectionType) aCollection.getType()).kind;

			/* Check if the kinds are the same. */
			if (this.kind.equals(aKind)
					|| this.kind.equals(OclCollectionTypeKind.COLLECTION)) {

				OclIterator<OclRoot> aCollectionIt;

				aCollectionIt = aCollection.getIterator();

				result = JavaOclBoolean.getInstance(true);

				/*
				 * Iterate over the collection and check if all elements have
				 * the right type.
				 */
				while (aCollectionIt.hasNext().isTrue()) {
					if (!elementType.isOfKind(aCollectionIt.next()).isTrue()) {
						result = JavaOclBoolean.getInstance(false);
						break;
					}
				}
			}
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclType
	 * #isOfType(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public OclBoolean isOfType(OclRoot anOclRoot) {

		OclBoolean result;
		boolean booleanResult;

		booleanResult = false;

		/* Check if the given OclRoot is a Collection. */
		if (anOclRoot instanceof OclCollection) {
			OclCollection<OclRoot> aCollection;
			OclCollectionTypeKind aKind;

			aCollection = (OclCollection<OclRoot>) anOclRoot;
			aKind = ((JavaOclCollectionType) aCollection.getType()).kind;

			/* Check if the kinds are the same. */
			if (this.kind.equals(aKind)
					|| this.kind.equals(OclCollectionTypeKind.COLLECTION)) {

				OclIterator<OclRoot> aCollectionIt = aCollection.getIterator();

				booleanResult = true;

				/*
				 * Iterate over the collection and check if all elements have
				 * the right kind.
				 */
				while (aCollectionIt.hasNext().isTrue()) {
					if (!elementType.isOfKind(aCollectionIt.next()).isTrue()) {
						booleanResult = false;
						break;
					}
				}

				/* Also check, if all elements have the right type. */
				if (booleanResult) {

					aCollectionIt = aCollection.getIterator();

					while (aCollectionIt.hasNext().isTrue()) {
						if (!elementType.isOfType(aCollectionIt.next())
								.isTrue()) {
							booleanResult = false;
						}
						// break;
					}
				}
				// no else.
			}
			// no else.
		}
		// no else.

		result = JavaOclBoolean.getInstance(booleanResult);

		return result;
	}

	/**
	 * <p>
	 * Gets the {@link OclType} of this {@link OclCollectionType}.
	 * </p>
	 * 
	 * @param kind
	 *            The kind of this {@link OclCollectionType}.
	 * @param elementType
	 *            The generic type of this {@link OclCollectionType}.
	 * 
	 * @return The {@link OclType} of this {@link OclCollectionType}.</p>
	 */
	public static OclType getType(OclCollectionTypeKind kind,
			OclType elementType) {

		OclType result;

		result = getType(kind + "(" + elementType.getName() + ")");

		if (result == null) {
			result = new JavaOclCollectionType(kind, elementType);
		}
		// no else.

		return result;
	}
}
