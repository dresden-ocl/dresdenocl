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

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.metamodels.uml2.UML2MetamodelPlugin;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.ParameterDirectionKind;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter;

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
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {

		return this.dslParameter.getName();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter#getOperation()
	 * 
	 * @generated NOT
	 */
	@Override
	public Operation getOperation() {

		return this.factory.createOperation(this.dslParameter.getOperation());
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter#getType()
	 * 
	 * @generated NOT
	 */
	@Override
	public Type getType() {

		return this.factory.createType(this.dslParameter.getType());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl#getKind()
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

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl#isMultiple()
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isMultiple() {

		return this.dslParameter.isMultivalued();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl#isOrdered()
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isOrdered() {

		return this.dslParameter.isOrdered();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl#isUnique()
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isUnique() {

		return this.dslParameter.isUnique();
	}
}