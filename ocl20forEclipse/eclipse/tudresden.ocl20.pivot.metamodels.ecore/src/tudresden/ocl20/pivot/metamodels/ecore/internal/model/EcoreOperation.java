/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology      *
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
package tudresden.ocl20.pivot.metamodels.ecore.internal.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;

import tudresden.ocl20.pivot.metamodels.ecore.EcoreMetamodelPlugin;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractOperation;

/**
 * <p>
 * An implementation of the Pivot Model {@link Operation} concept for Ecore.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public class EcoreOperation extends AbstractOperation implements Operation {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			EcoreMetamodelPlugin.getLogger(EcoreOperation.class);

	/** The adapted {@link EOperation}. */
	private EOperation eOperation;

	/**
	 * <p>
	 * Creates a new {@link EcoreOperation}.
	 * </p>
	 * 
	 * @param eOperation
	 *          The adapted {@link EOperation}.
	 */
	public EcoreOperation(EOperation eOperation) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreOperation(";
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

			msg = "EcoreOperation() - exit";

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractOperation#getType()
	 */
	@Override
	public Type getType() {

		return EcoreAdapterFactory.INSTANCE.createType(eOperation.getEType());
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#isMultiple()
	 */
	@Override
	public boolean isMultiple() {

		return this.eOperation.isMany();
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#isOrdered()
	 */
	@Override
	public boolean isOrdered() {

		return this.eOperation.isOrdered();
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#isUnique()
	 */
	@Override
	public boolean isUnique() {

		return this.eOperation.isUnique();
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractOperation#getName()
	 */
	@Override
	public String getName() {

		return this.eOperation.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractOperation#getOwnedParameter
	 * ()
	 */
	@Override
	public List<Parameter> getOwnedParameter() {

		List<Parameter> result;

		result = new ArrayList<Parameter>();

		for (EParameter parameter : this.eOperation.getEParameters()) {
			result.add(EcoreAdapterFactory.INSTANCE.createParameter(parameter));
		}

		/* Add the return parameter. */
		result.add(EcoreAdapterFactory.INSTANCE
				.createReturnParameter(this.eOperation));

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractOperation#getOwningType()
	 */
	@Override
	public Type getOwningType() {

		return EcoreAdapterFactory.INSTANCE.createType(this.eOperation
				.getEContainingClass());
	}
}