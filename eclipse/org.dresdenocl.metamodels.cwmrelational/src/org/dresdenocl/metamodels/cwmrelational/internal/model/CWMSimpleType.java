/*
 * Copyright (C) 2012 by Bjoern Freitag (bjoern.freitag@inf.tu-dresden.de)
 * This file is part of the CWM Meta Model of Dresden OCL2 for Eclipse. Dresden
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
package org.dresdenocl.metamodels.cwmrelational.internal.model;

import org.dresdenocl.pivotmodel.PrimitiveType;
import org.dresdenocl.pivotmodel.PrimitiveTypeKind;
import org.eclipse.osgi.util.NLS;
import orgomg.cwm.resource.relational.SQLSimpleType;

/**
 * <p>
 * An implementation of the Pivot Model {@link PrimitiveType} concept for CWMrelational.
 * </p>
 * 
 * @author Bjoern Freitag
 * @generated NOT
 */
public class CWMSimpleType extends CWMPrimitiveType<SQLSimpleType> implements PrimitiveType {


	/**
	 * <p>
	 * Creates a new <code>CWMSimpleType</code> instance.
	 * </p>
	 * 
	 * @param dslDataType
	 *            the {@link SQLSimpleType} that is adopted by this
	 *            class
	 * @param factory
	 *            The {@link CWMAdapterFactory} used to create nested elements.
	 * 
	 * @generated
	 */
	public CWMSimpleType(SQLSimpleType dslDataType,
			CWMAdapterFactory factory) {

		super(dslDataType,CWMSimpleType.class,factory);
		
	}

	
	/**
	 * <p>
	 * This method implements a type mapping from
	 * {@link SQLSimpleType} types to the predefined
	 * primitive types of the Pivot Model.
	 * </p>
	 * 
	 * @generated NOT
	 */
	@Override
	public PrimitiveTypeKind getKind() {
		
		PrimitiveTypeKind result;

		result = getKind(dslDataType);

		/* Eventually log a warning. */
		if (result == PrimitiveTypeKind.UNKNOWN && LOGGER.isInfoEnabled()) {
			String msg;

			msg = NLS.bind(CWMModelMessages.CWM_UnknownPrimitiveTypeKind,
					this.dslDataType.getName());

			LOGGER.warn(msg);
		}
		// no else.

		return result;
	}

}