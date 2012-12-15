/*
 * Copyright (C) 2008-2009 by Michael Thiele & Claas Wilke (claaswilke@gmx.net)
 * This file is part of the UML2 Meta Model of Dresden OCL2 for Eclipse. Dresden
 * OCL2 for Eclipse is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version. Dresden OCL2 for Eclipse is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU Lesser General Public License for more details. You should have
 * received a copy of the GNU Lesser General Public License along with Dresden
 * OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.metamodels.uml2.internal.model;

import org.apache.log4j.Logger;

import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.metamodels.uml2.UML2MetamodelPlugin;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.Parameter;
import org.dresdenocl.pivotmodel.ParameterDirectionKind;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.base.AbstractParameter;

/**
 * <p>
 * An implementation of the Pivot Model {@link Parameter} concept for UML2.
 * </p>
 * 
 * @author Michael Thiele
 * 
 * @generated
 */
public class UML2Parameter extends AbstractParameter implements Parameter {

	/**
	 * <p>
	 * Logger for this class
	 * </p>
	 * 
	 * @generated NOT
	 */
	private static final Logger LOGGER = UML2MetamodelPlugin
			.getLogger(UML2Parameter.class);

	/**
	 * <p>
	 * The adapted UML2 {@link org.eclipse.uml2.uml.Parameter}.
	 * </p>
	 * 
	 * @generated
	 */
	private org.eclipse.uml2.uml.Parameter dslParameter;

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
	 * Creates a new <code>UML2Parameter</code> instance.
	 * </p>
	 * 
	 * @param dslParameter
	 *            the {@link org.eclipse.uml2.uml.Parameter} that is adopted by
	 *            this class
	 * @param factory
	 *            The {@link UML2AdapterFactory} used to create nested elements.
	 * 
	 * @generated NOT
	 */
	public UML2Parameter(org.eclipse.uml2.uml.Parameter dslParameter,
			UML2AdapterFactory factory) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("UML2Parameter(dslParameter = " + dslParameter + ", factory = " + factory + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize adapted parameter
		this.dslParameter = dslParameter;
		this.factory = factory;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("UML2Parameter() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see org.dresdenocl.pivotmodel.base.AbstractParameter#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {

		return this.dslParameter.getName();
	}

	/**
	 * @see org.dresdenocl.pivotmodel.base.AbstractParameter#getOperation()
	 * 
	 * @generated NOT
	 */
	@Override
	public Operation getOperation() {

		return this.factory.createOperation(this.dslParameter.getOperation());
	}

	/**
	 * @see org.dresdenocl.pivotmodel.base.AbstractParameter#getType()
	 * 
	 * @generated NOT
	 */
	@Override
	public Type getType() {

		Type result;
		Type elementType;

		elementType = this.factory.createType(this.dslParameter.getType());

		/* Probably adapt type into a collection. */
		if (this.dslParameter.isMultivalued()) {

			if (this.dslParameter.isOrdered()) {

				/* OrderedSet. */
				if (this.dslParameter.isUnique()) {
					result = EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOrderedSetType(elementType);
				}

				/* Sequence. */
				else {
					result = EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getSequenceType(elementType);
				}
			}

			else {
				/* Set. */
				if (this.dslParameter.isUnique()) {
					result = EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getSetType(elementType);
				}

				/* Bag. */
				else {
					result = EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getBagType(elementType);
				}
			}
		}

		else {
			result = elementType;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.pivotmodel.impl.ParameterImpl#getKind()
	 * 
	 * @generated NOT
	 */
	public ParameterDirectionKind getKind() {

		org.eclipse.uml2.uml.ParameterDirectionKind dslKind;
		dslKind = this.dslParameter.getDirection();

		if (dslKind.getValue() == org.eclipse.uml2.uml.ParameterDirectionKind.IN) {
			this.kind = ParameterDirectionKind.IN;
		}

		else if (dslKind.getValue() == org.eclipse.uml2.uml.ParameterDirectionKind.INOUT) {
			this.kind = ParameterDirectionKind.INOUT;
		}

		else if (dslKind.getValue() == org.eclipse.uml2.uml.ParameterDirectionKind.OUT) {
			this.kind = ParameterDirectionKind.OUT;
		}

		else if (dslKind.getValue() == org.eclipse.uml2.uml.ParameterDirectionKind.RETURN) {
			this.kind = ParameterDirectionKind.RETURN;
		}

		return this.kind;
	}
}