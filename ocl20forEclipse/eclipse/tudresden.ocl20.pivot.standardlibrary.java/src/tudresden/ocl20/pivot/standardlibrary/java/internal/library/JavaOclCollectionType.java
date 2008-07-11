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
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class JavaOclCollectionType extends JavaOclType implements
		OclCollectionType {

	// The collection kind
	private OclCollectionTypeKind kind;

	// The element type
	private OclType elementType;

	/**
	 * Instantiates a new java ocl collection type.
	 * 
	 * @param kind
	 *            the kind
	 * @param elementType
	 *            the element type
	 */
	public JavaOclCollectionType(OclCollectionTypeKind kind, OclType elementType) {
		super(Collection.class, kind + "(" + elementType.getName() + ")");
		this.kind = kind;
		this.elementType = elementType;
	}

	/**
	 * Gets the type.
	 * 
	 * @param kind
	 *            the kind
	 * @param elementType
	 *            the element type
	 * 
	 * @return the type
	 */
	public static OclType getType(OclCollectionTypeKind kind,
			OclType elementType) {
		OclType type = getType(kind + "(" + elementType.getName() + ")");
		if (type == null)
			type = new JavaOclCollectionType(kind, elementType);
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclType#isOfType(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	@Override
	public OclBoolean isOfType(OclRoot o) {
		if (o instanceof OclCollection) {
			OclCollection<OclRoot> col = (OclCollection<OclRoot>) o;
			OclCollectionTypeKind oKind = ((JavaOclCollectionType) col
					.getType()).kind;
			if (kind.equals(oKind)
					|| kind.equals(OclCollectionTypeKind.COLLECTION)) {
				OclIterator<OclRoot> it = col.getIterator();
				while (it.hasNext().isTrue()) {
					if (!elementType.isOfKind(it.next()).isTrue())
						return JavaOclBoolean.getInstance(false);
				}
				OclIterator<OclRoot> it2 = col.getIterator();
				while (it2.hasNext().isTrue()) {
					if (elementType.isOfType(it2.next()).isTrue())
						return JavaOclBoolean.getInstance(true);
				}
			}
		}
		return JavaOclBoolean.getInstance(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclType#isOfKind(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	@Override
	public OclBoolean isOfKind(OclRoot o) {
		if (o instanceof OclCollection) {
			OclCollection<OclRoot> col = (OclCollection<OclRoot>) o;
			OclCollectionTypeKind oKind = ((JavaOclCollectionType) col
					.getType()).kind;
			if (kind.equals(oKind)
					|| kind.equals(OclCollectionTypeKind.COLLECTION)) {
				OclIterator<OclRoot> it = col.getIterator();
				while (it.hasNext().isTrue()) {
					if (!elementType.isOfKind(it.next()).isTrue())
						return JavaOclBoolean.getInstance(false);
				}
				return JavaOclBoolean.getInstance(true);
			}
		}
		return JavaOclBoolean.getInstance(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclType#createInstance(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	@Override
	public OclRoot createInstance(OclRoot o) {
		if (isOfKind(o).isTrue()) {
			if (this.kind == OclCollectionTypeKind.BAG) {
				List<OclRoot> l = new ArrayList<OclRoot>(
						(Collection<OclRoot>) o.getAdaptee());
				return new JavaOclBag(l);
			}
			if (this.kind == OclCollectionTypeKind.ORDEREDSET) {
				List<OclRoot> l = new ArrayList<OclRoot>(
						(Collection<OclRoot>) o.getAdaptee());
				return new JavaOclOrderedSet(l);
			}
			if (this.kind == OclCollectionTypeKind.SEQUENCE) {
				List<OclRoot> l = new ArrayList<OclRoot>(
						(Collection<OclRoot>) o.getAdaptee());
				return new JavaOclSequence(l);
			}
			if (this.kind == OclCollectionTypeKind.SET) {
				Set<OclRoot> s = new HashSet<OclRoot>((Collection<OclRoot>) o
						.getAdaptee());
				return new JavaOclSet(s);
			}
		}
		return JavaOclVoid.getInstance();
	}
}
