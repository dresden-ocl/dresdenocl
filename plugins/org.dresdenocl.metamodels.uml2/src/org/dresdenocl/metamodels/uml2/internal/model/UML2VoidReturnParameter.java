/*
 * Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)
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

import org.dresdenocl.metamodels.uml2.UML2MetamodelPlugin;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.Parameter;
import org.dresdenocl.pivotmodel.ParameterDirectionKind;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.base.AbstractParameter;

/**
 * <p>
 * An implementation of the Pivot Model {@link Parameter} concept for UML2
 * return {@link Parameter}s of the {@link Type} <code>void</code>.
 * </p>
 * 
 * @author Claas Wilke
 * 
 * @generated NOT
 */
public class UML2VoidReturnParameter extends AbstractParameter implements
		Parameter {

	/**
	 * <p>
	 * Logger for this class.
	 * </p>
	 * 
	 * @generated NOT
	 */
	private static final Logger LOGGER = UML2MetamodelPlugin
			.getLogger(UML2VoidReturnParameter.class);

	/**
	 * <p>
	 * The adapted UML2 {@link org.eclipse.uml2.uml.Operation}.
	 * </p>
	 * 
	 * @generated NOT
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
	 * Creates a new {@link UML2VoidReturnParameter} instance.
	 * </p>
	 * 
	 * @param dslOperation
	 *            the {@link org.eclipse.uml2.uml.Operation} that is adopted by
	 *            this class.
	 * @param factory
	 *            The {@link UML2AdapterFactory} used to create nested elements.
	 * 
	 * @generated NOT
	 */
	public UML2VoidReturnParameter(org.eclipse.uml2.uml.Operation dslOperation,
			UML2AdapterFactory factory) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "UML2VoidReturnParameter("; //$NON-NLS-1$ //$NON-NLS-2$
			msg += "dslOperation = " + dslOperation; //$NON-NLS-1$ //$NON-NLS-2$
			msg += ", factory = " + factory; //$NON-NLS-1$ //$NON-NLS-2$
			msg += ") - enter"; //$NON-NLS-1$ //$NON-NLS-2$

			LOGGER.debug(msg);
		}
		// no else.

		/* Initialize adapted operation. */
		this.dslOperation = dslOperation;
		this.factory = factory;

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("UML2Parameter() - exit"); //$NON-NLS-1$
		}
		// no else.
	}

	/**
	 * @see org.dresdenocl.pivotmodel.base.AbstractParameter#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {

		return this.getKind().getName();
	}

	/**
	 * @see org.dresdenocl.pivotmodel.base.AbstractParameter#getOperation()
	 * 
	 * @generated NOT
	 */
	@Override
	public Operation getOperation() {

		return this.factory.createOperation(this.dslOperation);
	}

	/**
	 * @see org.dresdenocl.pivotmodel.base.AbstractParameter#getType()
	 * 
	 * @generated NOT
	 */
	@Override
	public Type getType() {

		return this.factory.createType(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.pivotmodel.impl.ParameterImpl#getKind()
	 * 
	 * @generated NOT
	 */
	public ParameterDirectionKind getKind() {

		return ParameterDirectionKind.RETURN;
	}
}