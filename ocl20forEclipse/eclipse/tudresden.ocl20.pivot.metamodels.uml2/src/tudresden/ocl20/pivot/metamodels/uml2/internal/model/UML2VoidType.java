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
package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.metamodels.uml2.UML2MetamodelPlugin;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType;

/**
 * <p>
 * An implementation of the Pivot Model {@link PrimitiveType} concept for the
 * {@link PrimitiveTypeKind#VOID}.
 * </p>
 * 
 * @author Class Wilke
 * 
 * @generated NOT
 */
public class UML2VoidType extends AbstractPrimitiveType implements
		PrimitiveType {

	/**
	 * <p>
	 * Logger for this class
	 * </p>
	 * 
	 * @generated NOT
	 */
	private static final Logger LOGGER =
			UML2MetamodelPlugin.getLogger(UML2VoidType.class);

	/** The {@link UML2VoidType} instance. */
	private static UML2VoidType myInstance;

	/**
	 * <p>
	 * Creates a new {@link UML2VoidType}.
	 * </p>
	 * 
	 * @generated NOT
	 */
	private UML2VoidType() {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("UML2VoidType) - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("org.eclipse.uml2.uml.PrimitiveType() - exit"); //$NON-NLS-1$
		}
		// no else.
	}

	/**
	 * <p>
	 * Returns the singleton instance of {@link UML2VoidType}.
	 * </p>
	 * 
	 * @return The singleton instance of {@link UML2VoidType}.
	 */
	public static UML2VoidType getInstance() {

		/* Eventually initialize the instance. */
		if (myInstance == null) {
			myInstance = new UML2VoidType();
		}

		return myInstance;
	}

	/**
	 * <p>
	 * This method implements a type mapping from
	 * {@link org.eclipse.uml2.uml.PrimitiveType} types to the predefined
	 * primitive types of the Pivot Model.
	 * </p>
	 * 
	 * @generated NOT
	 */
	@Override
	public PrimitiveTypeKind getKind() {

		return PrimitiveTypeKind.VOID;
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {

		return this.getKind().getName();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getNamespace()
	 * 
	 * @generated NOT
	 */
	@Override
	public Namespace getNamespace() {

		return null;
	}
}