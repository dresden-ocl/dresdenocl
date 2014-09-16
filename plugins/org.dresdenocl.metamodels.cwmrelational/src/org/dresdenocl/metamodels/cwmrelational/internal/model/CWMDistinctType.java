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
import orgomg.cwm.resource.relational.SQLDistinctType;

/**
 * <p>
 * An implementation of the Pivot Model {@link PrimitiveType} concept for CWMrelational.
 * </p>
 * 
 * @author Bjoern Freitag
 * @generated NOT
 */
public class CWMDistinctType extends CWMPrimitiveType<SQLDistinctType> implements PrimitiveType {


	/**
	 * <p>
	 * Creates a new <code>CWMDistinctType</code> instance.
	 * </p>
	 * 
	 * @param dslDataType
	 *            the {@link SQLDistinctType} that is adopted by this
	 *            class
	 * @param factory
	 *            The {@link CWMAdapterFactory} used to create nested elements.
	 * 
	 * @generated
	 */
	public CWMDistinctType(SQLDistinctType dslDataType,
			CWMAdapterFactory factory) {

		super(dslDataType,CWMDistinctType.class,factory);
		
	}

	
	/**
	 * <p>
	 * This method implements a type mapping from
	 * {@link SQLDistinctType} types to the predefined
	 * primitive types of the Pivot Model.
	 * </p>
	 * 
	 * @generated NOT
	 */
	@Override
	public PrimitiveTypeKind getKind() {
		
		PrimitiveTypeKind result;

		result = getKind(dslDataType.getSqlSimpleType());

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


	/* (non-Javadoc)
	 * @see org.dresdenocl.metamodels.cwmrelational.internal.model.CWMPrimitiveType#getName()
	 */
	@Override
	public String getName() {
		return super.getName().replace(" ARRAY", "").replace("[]", "").replace("[ ]", "");
	}
	
	

}