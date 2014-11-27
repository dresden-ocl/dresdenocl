/*
 * Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net) This file is part of
 * the EMF Ecore Meta Model of Dresden OCL2 for Eclipse. Dresden OCL2 for
 * Eclipse is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version. Dresden OCL2 for Eclipse is distributed in the hope that it
 * will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with Dresden OCL2 for Eclipse. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.metamodels.ecore.internal.model;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;

import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.metamodels.ecore.EcoreMetamodelPlugin;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.Parameter;
import org.dresdenocl.pivotmodel.ParameterDirectionKind;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.base.AbstractParameter;

/**
 * <p>
 * An implementation of the Pivot Model {@link Parameter} concept for the return
 * {@link Parameter} Ecore {@link EOperation}s.
 * </p>
 * 
 * @author Claas Wilke
 */
public class EcoreReturnParameter extends AbstractParameter implements
		Parameter {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = EcoreMetamodelPlugin
			.getLogger(EcoreReturnParameter.class);

	/** The adapted {@link EParameter}. */
	private EOperation eOperation;

	/**
	 * <p>
	 * The {@link EcoreAdapterFactory} used to create nested elements.
	 * </p>
	 */
	private EcoreAdapterFactory factory;
	
	/**
	 * <p>
	 * Creates a new {@link EcoreReturnParameter}.
	 * </p>
	 * 
	 * @param eOperation
	 *            The adapted {@link EOperation}.
	 * @param factory
	 *            The {@link EcoreAdapterFactory} used to create nested elements.
	 */
	public EcoreReturnParameter(EOperation eOperation,EcoreAdapterFactory factory) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreReturnParameter(";
			msg += "eOperation = " + eOperation;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		/* Initialize adapted EOperation. */
		this.eOperation = eOperation;
		this.factory = factory;
		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreParameter() - exit";

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.pivotmodel.base.AbstractParameter#getName()
	 */
	@Override
	public String getName() {

		return "returnParameter";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.pivotmodel.base.AbstractParameter#getOperation()
	 */
	@Override
	public Operation getOperation() {

		return factory.createOperation(this.eOperation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.pivotmodel.base.AbstractParameter#getType()
	 */
	@Override
	public Type getType() {

		Type result;

		Type elementType;
		elementType = factory.createType(eOperation
				.getEType());

		/* Probably put the type into a collection. */
		if (this.eOperation.isMany()) {

			if (this.eOperation.isUnique()) {

				/* Adapt to OrderedSet. */
				if (this.eOperation.isOrdered()) {
					result = EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOrderedSetType(elementType);
				}

				/* Adapt to Set. */
				else {
					result = EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getSetType(elementType);
				}
			}

			else {

				/* Adapt to Sequence. */
				if (this.eOperation.isOrdered()) {
					result = EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getSequenceType(elementType);
				}

				/* Adapt to Bag. */
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
	 */
	@Override
	public ParameterDirectionKind getKind() {

		return ParameterDirectionKind.RETURN;
	}
}