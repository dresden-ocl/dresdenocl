/*
 * Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net) This file is part of
 * the UML2 Meta Model of Dresden OCL2 for Eclipse. Dresden OCL2 for Eclipse is
 * free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version. Dresden OCL2 for Eclipse is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with Dresden OCL2 for Eclipse. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.metamodels.uml2.internal.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import org.dresdenocl.metamodels.uml2.UML2MetamodelPlugin;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.base.AbstractType;

/**
 * <p>
 * An implementation of the Pivot Model {@link Type} concept for UML2.
 * </p>
 * 
 * @author Claas Wilke
 * @generated NOT
 */
public class UML2TypePrimitiveType extends AbstractType implements Type {

	/**
	 * <p>
	 * Logger for this class
	 * </p>
	 * 
	 * @generated NOT
	 */
	private static final Logger LOGGER = UML2MetamodelPlugin
			.getLogger(UML2TypePrimitiveType.class);

	/** The adapted {@link org.eclipse.uml2.uml.PrimitiveType} class. */
	private org.eclipse.uml2.uml.PrimitiveType dslPrimitiveType;

	/**
	 * <p>
	 * The {@link UML2AdapterFactory} used to create nested elements.
	 * </p>
	 * 
	 * @generate NOT
	 */
	private UML2AdapterFactory factory;

	/**
	 * <p>
	 * Creates a new <code>UML2Class</code> instance.
	 * </p>
	 * 
	 * @param dslPrimitiveType
	 *            the {@link org.eclipse.uml2.uml.PrimitiveType} that is adopted
	 *            by this class
	 * @param factory
	 *            The {@link UML2AdapterFactory} used to create nested elements.
	 * 
	 * @generated NOT
	 */
	public UML2TypePrimitiveType(
			org.eclipse.uml2.uml.PrimitiveType dslPrimitiveType,
			UML2AdapterFactory factory) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "UML2TypePrimitiveType("; //$NON-NLS-1$ //$NON-NLS-2$
			msg += "dslPrimitiveType = " + dslPrimitiveType; //$NON-NLS-1$ //$NON-NLS-2$
			msg += ", factory = " + factory; //$NON-NLS-1$ //$NON-NLS-2$
			msg += ") - enter"; //$NON-NLS-1$ //$NON-NLS-2$

			LOGGER.debug(msg);
		}
		// no else.

		/* Initialize the adapted object. */
		this.dslPrimitiveType = dslPrimitiveType;
		this.factory = factory;

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("UML2TypePrimitiveType() - exit"); //$NON-NLS-1$
		}
		// no else.
	}

	/**
	 * @see org.dresdenocl.pivotmodel.base.AbstractType#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {

		return this.dslPrimitiveType.getName();
	}

	/**
	 * @see org.dresdenocl.pivotmodel.base.AbstractType#getNamespace()
	 * 
	 * @generated NOT
	 */
	@Override
	public Namespace getNamespace() {

		return this.factory.createNamespace(dslPrimitiveType.getPackage());
	}

	/**
	 * @see org.dresdenocl.pivotmodel.base.AbstractType#getOwnedPropertyImpl()
	 * 
	 * @generated NOT
	 */
	@Override
	protected List<Property> getOwnedPropertyImpl() {

		List<Property> result;

		result = new ArrayList<Property>();

		for (org.eclipse.uml2.uml.Property property : this.dslPrimitiveType
				.getOwnedAttributes()) {

			result.add(this.factory.createProperty(property));
		}

		return result;
	}

	/**
	 * @see org.dresdenocl.pivotmodel.base.AbstractType#getOwnedOperationImpl()
	 * 
	 * @generated NOT
	 */
	@Override
	protected List<Operation> getOwnedOperationImpl() {

		List<Operation> result;

		result = new ArrayList<Operation>();

		for (org.eclipse.uml2.uml.Operation operation : this.dslPrimitiveType
				.getOwnedOperations()) {

			result.add(this.factory.createOperation(operation));
		}

		return result;
	}

	/**
	 * @see org.dresdenocl.pivotmodel.base.AbstractType#getSuperTypeImpl()
	 * 
	 * @generated NOT
	 */
	@Override
	protected List<Type> getSuperTypeImpl() {

		List<Type> result;

		result = new ArrayList<Type>();

		/* Primitive types in UML do not have super types. */

		return result;
	}
}