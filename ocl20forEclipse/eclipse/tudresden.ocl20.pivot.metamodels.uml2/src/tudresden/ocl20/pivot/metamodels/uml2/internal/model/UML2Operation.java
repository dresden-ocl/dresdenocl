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
package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;

import tudresden.ocl20.pivot.metamodels.uml2.UML2MetamodelPlugin;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractOperation;

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
			LOGGER
					.debug("UML2Operation(dslOperation = " + dslOperation + ", factory = " + factory + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslOperation = dslOperation;
		this.factory = factory;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("UML2Operation() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractOperation#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {

		return this.dslOperation.getName();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractOperation#getOwnedParameter()
	 * 
	 * @generated NOT
	 */
	@Override
	public List<Parameter> getOwnedParameter() {

		List<Parameter> result;

		result = new ArrayList<Parameter>();

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
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractOperation#getOwningType()
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
				LOGGER.info(NLS.bind(UML2ModelMessages.UML2_GetOwningType, this
						.toString()));
			}
			// no else.

			result = null;
		}

		return result;
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractOperation#getType()
	 * 
	 * @generated NOT
	 */
	@Override
	public Type getType() {

		return this.factory.createType(dslOperation.getType());
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#getReturnParameter()
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
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#isMultiple()
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isMultiple() {

		boolean result;

		/*
		 * see: UML Infrastructure (07-11-04), p. 97: There is no operation
		 * isMultiple(), since Operation does not directly inherit from
		 * MultiplicityElement.
		 */
		result = this.dslOperation.getUpper() > 1
				|| this.dslOperation.getUpper() == LiteralUnlimitedNatural.UNLIMITED;

		return result;
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#isOrdered()
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isOrdered() {

		return this.dslOperation.isOrdered();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.FeatureImpl#isStatic()
	 * 
	 * @generated NOT
	 */
	public boolean isStatic() {

		return this.dslOperation.isStatic();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#isUnique()
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isUnique() {

		return this.dslOperation.isUnique();
	}
}