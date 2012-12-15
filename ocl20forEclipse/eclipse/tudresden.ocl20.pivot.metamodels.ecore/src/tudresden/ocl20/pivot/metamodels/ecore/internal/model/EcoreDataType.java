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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EDataType;

import tudresden.ocl20.pivot.metamodels.ecore.EcoreMetamodelPlugin;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractType;

/**
 * <p>
 * An implementation of the Pivot Model {@link Type} concept for
 * {@link EDataType}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class EcoreDataType extends AbstractType implements Type {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			EcoreMetamodelPlugin.getLogger(EcoreDataType.class);

	/** The adapted {@link EDataType}. */
	private EDataType eDataType;
	
	/**
	 * <p>
	 * The {@link EcoreAdapterFactory} used to create nested elements.
	 * </p>
	 */
	private EcoreAdapterFactory factory;
	/**
	 * <p>
	 * Creates a new {@link EcoreDataType} instance.
	 * </p>
	 * 
	 * @param eDataType
	 *          The adapted {@link EDataType}.
	 * @param factory
	 *            The {@link EcoreAdapterFactory} used to create nested elements.
	 */
	public EcoreDataType(EDataType eDataType,EcoreAdapterFactory factory) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreDataType(";
			msg += "eDataType = " + eDataType;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		/* Initialize adapted EDataType. */
		this.eDataType = eDataType;
		this.factory = factory;
		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreDataType() - exit";

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getName()
	 */
	@Override
	public String getName() {

		return this.eDataType.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getNamespace()
	 */
	@Override
	public Namespace getNamespace() {

		return factory.createNamespace(this.eDataType
				.getEPackage());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getOwnedOperationImpl ()
	 */
	@Override
	protected List<Operation> getOwnedOperationImpl() {

		/* EDataTypes do not have operations. */
		return new ArrayList<Operation>();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getOwnedPropertyImpl()
	 */
	@Override
	protected List<Property> getOwnedPropertyImpl() {

		/* EDataTypes do not have properties. */
		return new ArrayList<Property>();
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getSuperTypeImpl()
	 */
	@Override
	protected List<Type> getSuperTypeImpl() {

		/* EDataTypes do not have super types. */
		return new ArrayList<Type>();
	}
}