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
import orgomg.cwm.objectmodel.core.Feature;
import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.resource.relational.Column;
import orgomg.cwm.resource.relational.ForeignKey;
import orgomg.cwm.resource.relational.NamedColumnSet;

/**
 * <p>
 * An abstract implementation of the Pivot Model {@link Type} concept for CWMrelational.
 * </p>
 * 
 * @author Bjoern Freitag
 * @generated NOT
 */
public abstract class CWMNamedColumnSet<T extends NamedColumnSet> extends AbstractType implements Type {

	/**
	 * <p>
	 * Logger for this class
	 * </p>
	 * 
	 * @generated NOT
	 */
	protected Logger LOGGER; 

	/** The adapted {@link T} class. */
	protected T dslNamedColumnSet;

	
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
	 * Is the constructor for <code>CWMNamedColumnSet</code>.
	 * </p>
	 * 
	 * @param namedColumnSet
	 *            the {@link T} that is adopted by this
	 *            class
	 * @param factory
	 *            The {@link CWMAdapterFactory} used to create nested elements.
	 * @param CWMNamedColumnSet
	 * 				the subclass of this class.
	 * 
	 * @generated NoT
	 */
	public CWMNamedColumnSet(T namedColumnSet,Class<? extends CWMNamedColumnSet<T>> CWMNamedColumnSet,
			CWMAdapterFactory factory ) {
		LOGGER = CWMMetamodelPlugin
				.getLogger(CWMNamedColumnSet);

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug(CWMNamedColumnSet.getName()+"(namedColumnSet = " + namedColumnSet + "factory = " + factory + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslNamedColumnSet = namedColumnSet;
		this.factory = factory;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(CWMNamedColumnSet.getName()+"() - exit"); //$NON-NLS-1$
		}

	}
	
	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {

		return this.dslNamedColumnSet.getName();
	}
	
	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getNamespace()
	 * 
	 * @generated NOT
	 */
	@Override
	public Namespace getNamespace() {
		return this.factory.createNamespace(dslNamedColumnSet.getNamespace());
	}

	
	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getOwnedPropertyImpl()
	 * 
	 * @generated NOT
	 */
	@Override
	protected List<Property> getOwnedPropertyImpl() {

		List<Property> result;

		result = new ArrayList<Property>();
		for (Feature property : this.dslNamedColumnSet.getFeature()) {
			if (property instanceof Column) result.add(this.factory.createColumn((Column) property));
		}
		for (ModelElement property : this.dslNamedColumnSet.getOwnedElement()) {
			if (property instanceof Column) result.add(this.factory.createColumn((Column) property));
			if (property instanceof ForeignKey) result.add(this.factory.createForeignKey((ForeignKey) property));
		}

		return result;
	}
	
	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getOwnedOperationImpl()
	 * 
	 * @generated NOT
	 */
	@Override
	protected List<Operation> getOwnedOperationImpl() {
		return new ArrayList<Operation>();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getSuperTypeImpl()
	 * 
	 * @generated NOT
	 */
	@Override
	protected List<Type> getSuperTypeImpl() {
		return new ArrayList<Type>();
	}
}
