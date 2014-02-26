/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
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
 *
 * $Id$
 */
package org.dresdenocl.essentialocl.types.impl;

import java.util.List;

import org.eclipse.emf.ecore.EClass;

import org.dresdenocl.essentialocl.types.CollectionType;
import org.dresdenocl.essentialocl.types.SequenceType;
import org.dresdenocl.essentialocl.types.TypesFactory;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.TypeParameter;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Sequence Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class SequenceTypeImpl extends CollectionTypeImpl implements
		SequenceType {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected SequenceTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypesPackageImpl.Literals.SEQUENCE_TYPE;
	}

	/**
	 * Overridden to check whether the other {@link CollectionType collection type} also is a
	 * <code>SequenceType</code>. In this case, return a <code>SequenceType</code> with the given
	 * element type, otherwise default to <code>CollectionType</code>.
	 * 
	 * @see org.dresdenocl.essentialocl.types.impl.CollectionTypeImpl#getCommonCollectionType(org.dresdenocl.essentialocl.types.CollectionType,
	 *      org.dresdenocl.pivotmodel.Type)
	 */
	@Override
	protected CollectionType getCommonCollectionType(
			CollectionType otherCollectionType, Type commonElementType) {

		// check invariant
		if (getOclLibrary() == null) {
			throw new IllegalStateException(
					"The reference to the OCL library was null for " + this + "."); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (otherCollectionType instanceof SequenceType) {
			return getOclLibrary().getSequenceType(commonElementType);
		}

		return getOclLibrary().getCollectionType(commonElementType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.essentialocl.types.impl.CollectionTypeImpl#bindTypeParameter(java.util.List,
	 *      java.util.List)
	 */
	@Override
	public SequenceType bindTypeParameter(List<TypeParameter> parameters,
			List<? extends Type> types) {

		return (SequenceType) super.bindTypeParameter(parameters, types);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.essentialocl.types.impl.SequenceType#clone()
	 */

	@Override
	public SequenceType clone() {

		return (SequenceType) initialize(TypesFactory.INSTANCE
				.createSequenceType());
	}

} // SequenceTypeImpl
