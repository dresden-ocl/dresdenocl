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
package tudresden.ocl20.pivot.metamodels.ecore.internal.model;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;

import tudresden.ocl20.pivot.metamodels.ecore.EcoreMetamodelPlugin;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.ParameterDirectionKind;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter;

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
	private static final Logger LOGGER =
			EcoreMetamodelPlugin.getLogger(EcoreReturnParameter.class);

	/** The adapted {@link EParameter}. */
	private EOperation eOperation;

	/**
	 * <p>
	 * Creates a new {@link EcoreReturnParameter}.
	 * </p>
	 * 
	 * @param eOperation
	 *          The adapted {@link EOperation}.
	 */
	public EcoreReturnParameter(EOperation eOperation) {

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
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter#getName()
	 */
	@Override
	public String getName() {

		return "returnParameter";
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter#getOperation()
	 */
	@Override
	public Operation getOperation() {

		return EcoreAdapterFactory.INSTANCE.createOperation(this.eOperation);
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter#getType()
	 */
	@Override
	public Type getType() {

		return EcoreAdapterFactory.INSTANCE.createType(this.eOperation.getEType());
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl#getKind()
	 */
	@Override
	public ParameterDirectionKind getKind() {

		return ParameterDirectionKind.RETURN;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl#isMultiple()
	 */
	@Override
	public boolean isMultiple() {

		return this.eOperation.isMany();
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl#isOrdered()
	 */
	@Override
	public boolean isOrdered() {

		return this.eOperation.isOrdered();
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl#isUnique()
	 */
	@Override
	public boolean isUnique() {

		return this.eOperation.isUnique();
	}
}