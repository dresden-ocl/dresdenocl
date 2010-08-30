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

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclUnsortedCollection;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * <p>
 * Provides an implementation of {@link OclUnsortedCollection} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 * @author Michael Thiele
 */
public abstract class JavaOclUnsortedCollection<T extends OclAny> extends
		JavaOclCollection<T> implements OclUnsortedCollection<T> {

	public JavaOclUnsortedCollection(
			IModelInstanceCollection<IModelInstanceElement> imiCollection,
			Type genericType) {

		super(imiCollection, genericType);
	}

	public JavaOclUnsortedCollection(String undefinedReason, Type genericType) {

		super(undefinedReason, genericType);
	}

	public JavaOclUnsortedCollection(Throwable invalidReason, Type genericType) {

		super(invalidReason, genericType);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclUnsortedCollection
	 * #intersection(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet)
	 */
	public OclSet<T> intersection(OclSet<T> aSet) {
	
		OclSet<T> result;
	
		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getSetType(genericType), this, aSet);
	
		if (result == null)
			result =
					checkUndefined("intersection", EssentialOclPlugin
							.getOclLibraryProvider().getOclLibrary().getSetType(genericType),
							this, aSet);
	
		if (result == null) {
			/* Else compute the result. */
			Collection<IModelInstanceElement> otherSet;
			Set<IModelInstanceElement> intersection;
	
			otherSet = aSet.getModelInstanceCollection().getCollection();
			intersection = new HashSet<IModelInstanceElement>();
	
			for (IModelInstanceElement anElement : otherSet) {
				if (this.getModelInstanceCollection().getCollection().contains(
						anElement)
						&& !intersection.contains(anElement)) {
					intersection.add(anElement);
				}
			}
	
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclSet(intersection,
							genericType);
		}
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclUnsortedCollection
	 * #union(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag)
	 */
	public OclBag<T> union(OclBag<T> aBag) {

		OclBag<T> result;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getBagType(genericType), this, aBag);

		if (result == null)
			result =
					checkUndefined("union", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getBagType(genericType), this, aBag);

		if (result == null) {
			/* Else compute the result. */
			List<IModelInstanceElement> union =
					new ArrayList<IModelInstanceElement>();

			union.addAll(this.getModelInstanceCollection().getCollection());
			union.addAll(aBag.getModelInstanceCollection().getCollection());

			result =
					JavaStandardLibraryFactory.INSTANCE.createOclBag(union, genericType);
		}

		return result;
	}

}
