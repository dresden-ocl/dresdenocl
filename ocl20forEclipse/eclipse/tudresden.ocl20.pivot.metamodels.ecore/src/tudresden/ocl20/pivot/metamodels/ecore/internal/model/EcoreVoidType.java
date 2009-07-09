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

import tudresden.ocl20.pivot.metamodels.ecore.EcoreMetamodelPlugin;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType;

/**
 * <p>
 * An implementation of the Pivot Model {@link PrimitiveType} with the
 * {@link PrimitiveTypeKind#VOID} for the EMF Ecore meta-model.
 * </p>
 * 
 * @author Claas Wilke
 * 
 * @generated NOT
 */
public class EcoreVoidType extends AbstractPrimitiveType implements
		PrimitiveType {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			EcoreMetamodelPlugin.getLogger(EcoreVoidType.class);

	/** The singleton instance of {@link EcoreVoidType}. */
	private static EcoreVoidType myInstance;

	/**
	 * <p>
	 * Creates a new {@link EcoreVoidType} instance.
	 * </p>
	 */
	private EcoreVoidType() {

		super();

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreVoidType() - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreVoidType() - exit";

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.
	}

	/**
	 * <p>
	 * Returns the singleton instance of {@link EcoreVoidType}.
	 * </p>
	 * 
	 * @return The singleton instance of {@link EcoreVoidType}.
	 */
	public static EcoreVoidType getInstance() {

		/* Eventually initialize the singleton. */
		if (myInstance == null) {
			myInstance = new EcoreVoidType();
		}
		// no else.

		return myInstance;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getKind()
	 */
	@Override
	public PrimitiveTypeKind getKind() {

		return PrimitiveTypeKind.VOID;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getName()
	 */
	@Override
	public String getName() {

		return this.getKind().getName();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getNamespace ()
	 */
	@Override
	public Namespace getNamespace() {

		return null;
	}
}