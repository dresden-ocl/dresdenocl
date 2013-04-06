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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dresdenocl.metamodels.cwmrelational.CWMMetamodelPlugin;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.base.AbstractType;
import orgomg.cwm.resource.relational.SQLDataType;

/**
 * <p>
 * An implementation of the Pivot Model {@link Type} concept for CWMrelational.
 * </p>
 * 
 * @author Bjoern Freitag
 * @generated NOT
 */
public abstract class CWMTypePrimitiveType<T extends SQLDataType> extends AbstractType implements Type {

	/**
	 * <p>
	 * Logger for this class
	 * </p>
	 * 
	 * @generated NOT
	 */
	protected Logger LOGGER;

	/** The adapted {@link T} class. */
	protected T dslDataType;

	/**
	 * <p>
	 * The {@link CWMAdapterFactory} used to create nested elements.
	 * </p>
	 * 
	 * @generated NOT
	 */
	protected CWMAdapterFactory factory;


	/**
	 * <p>
	 * Creates a new <code>CWMTypePrimitiveType</code> instance.
	 * </p>
	 * 
	 * @param dslDataType
	 *            the {@link T} that is adopted by this
	 *            class
	 * @param factory
	 *            The {@link CWMAdapterFactory} used to create nested elements.
	 * 
	 * @generated
	 */
	public CWMTypePrimitiveType(T dslDataType,Class<? extends CWMTypePrimitiveType<T>> CWMPrimitiveType,
			CWMAdapterFactory factory) {
		
		LOGGER = CWMMetamodelPlugin.getLogger(CWMPrimitiveType);
		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug(CWMPrimitiveType.getName()+"(dslDataType = " + dslDataType + "factory = " + factory + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslDataType = dslDataType;
		this.factory = factory;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(CWMPrimitiveType.getName()+"() - exit"); //$NON-NLS-1$
		}
	}
	
	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getNamespace()
	 * 
	 * @generated NOT
	 */
	@Override
	public Namespace getNamespace() {
		return this.factory.createNamespace(dslDataType.getNamespace());
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {
		return this.dslDataType.getName();
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getOwnedPropertyImpl()
	 */
	@Override
	protected List<Property> getOwnedPropertyImpl() {
		return new ArrayList<Property>();
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getOwnedOperationImpl()
	 */
	@Override
	protected List<Operation> getOwnedOperationImpl() {
		return new ArrayList<Operation>();
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getSuperTypeImpl()
	 */
	@Override
	protected List<Type> getSuperTypeImpl() {
		return new ArrayList<Type>();
	}


}