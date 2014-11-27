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

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.osgi.util.NLS;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;

import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.metamodels.uml2.UML2MetamodelPlugin;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.Parameter;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.base.AbstractOperation;

/**
 * <p>
 * An implementation of the Pivot Model {@link Operation} concept for UML2.
 * </p>
 * 
 * @generated
 */
public class UML2Operation extends AbstractOperation implements Operation {

	/**
	 * <p>
	 * Logger for this class
	 * </p>
	 * 
	 * @generated NOT
	 */
	private static final Logger LOGGER = UML2MetamodelPlugin
			.getLogger(UML2Operation.class);

	/**
	 * <p>
	 * the adapted UML2 org.eclipse.uml2.uml.Operation
	 * </p>
	 * 
	 * @generated
	 */
	private org.eclipse.uml2.uml.Operation dslOperation;

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
	 * Creates a new <code>UML2Operation</code> instance.
	 * </p>
	 * 
	 * @param dslOperation
	 *            the {@link org.eclipse.uml2.uml.Operation} that is adopted by
	 *            this class
	 * @param factory
	 *            The {@link UML2AdapterFactory} used to create nested elements.
	 * 
	 * @generated NOT
	 */
	public UML2Operation(org.eclipse.uml2.uml.Operation dslOperation,
			UML2AdapterFactory factory) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("UML2Operation(dslOperation = " + dslOperation + ", factory = " + factory + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslOperation = dslOperation;
		this.factory = factory;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("UML2Operation() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see org.dresdenocl.pivotmodel.base.AbstractOperation#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {

		return this.dslOperation.getName();
	}

	/**
	 * @see org.dresdenocl.pivotmodel.base.AbstractOperation#getOwnedParameter()
	 * 
	 * @generated NOT
	 */
	@Override
	public List<Parameter> getOwnedParameter() {

		List<Parameter> result;

		result = new BasicEList<Parameter>();

		for (org.eclipse.uml2.uml.Parameter dslOwnedParameter : dslOperation
				.getOwnedParameters()) {
			result.add(this.factory.createParameter(dslOwnedParameter));
		}

		/* Eventually add the void return parameter manually. */
		if (this.dslOperation.getType() == null) {
			result.add(this.getReturnParameter());
		}
		// no else.

		return result;
	}

	/**
	 * @see org.dresdenocl.pivotmodel.base.AbstractOperation#getOwningType()
	 * 
	 * @generated NOT
	 */
	@Override
	public Type getOwningType() {

		Type result;

		if (this.dslOperation.getOwner() instanceof org.eclipse.uml2.uml.Type) {
			result = this.factory
					.createType(((org.eclipse.uml2.uml.Type) this.dslOperation
							.getOwner()));
		}

		else {

			if (LOGGER.isInfoEnabled()) {
				LOGGER.info(NLS.bind(UML2ModelMessages.UML2_GetOwningType,
						this.toString()));
			}
			// no else.

			result = null;
		}

		return result;
	}

	/**
	 * @see org.dresdenocl.pivotmodel.base.AbstractOperation#getType()
	 * 
	 * @generated NOT
	 */
	@Override
	public Type getType() {

		Type result;
		Type elementType;

		elementType = this.factory.createType(this.dslOperation.getType());

		/* Probably adapt type into a collection. */
		if (this.dslOperation.getUpper() > 1
				|| this.dslOperation.getUpper() == LiteralUnlimitedNatural.UNLIMITED) {

			if (this.dslOperation.isOrdered()) {

				/* OrderedSet. */
				if (this.dslOperation.isUnique()) {
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
				if (this.dslOperation.isUnique()) {
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

	/**
	 * @see org.dresdenocl.pivotmodel.impl.OperationImpl#getReturnParameter()
	 * 
	 * @generated NOT
	 */
	public Parameter getReturnParameter() {

		Parameter result;

		/* Check if the return parameter is null. */
		if (this.dslOperation.getReturnResult() == null) {
			result = this.factory.createVoidReturnParameter(this.dslOperation);
		}

		/* Else adapt the return parameter. */
		else {
			result = this.factory.createParameter(this.dslOperation
					.getReturnResult());
		}

		return result;
	}

	/**
	 * @see org.dresdenocl.pivotmodel.impl.FeatureImpl#isStatic()
	 * 
	 * @generated NOT
	 */
	public boolean isStatic() {

		return this.dslOperation.isStatic();
	}
}