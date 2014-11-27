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

import org.dresdenocl.pivotmodel.Type;

import orgomg.cwm.resource.relational.SQLDistinctType;

public class CWMTypeDistinctType extends CWMTypePrimitiveType<SQLDistinctType> implements Type {

	/**
	 * <p>
	 * Creates a new <code>CWMTypeDistinctType</code> instance.
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
	public CWMTypeDistinctType(SQLDistinctType dslDataType,
			CWMAdapterFactory factory) {

		super(dslDataType,CWMTypeDistinctType.class,factory);
		
	}
}
