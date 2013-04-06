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

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dresdenocl.metamodels.cwmrelational.CWMMetamodelPlugin;
import org.dresdenocl.pivotmodel.AssociationProperty;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.impl.PropertyImpl;
import orgomg.cwm.resource.relational.ForeignKey;

/**
 * <p>
 * An implementation of the Pivot Model {@link Property} concept for UML2.
 * </p>
 * 
 * @author Bjoern Freitag
 * 
 * @generated NOT
 */
public class CWMForeignKey extends PropertyImpl implements AssociationProperty {

	/**
	 * <p>
	 * Logger for this class
	 * </p>
	 * 
	 * @generated NOT
	 */
	private static final Logger LOGGER =
			CWMMetamodelPlugin.getLogger(CWMForeignKey.class);

	/** The adapted {@link ForeignKey} class. */
	private ForeignKey dslForeignKey;

	/**
	 * <p>
	 * The {@link CWMAdapterFactory} used to create nested elements.
	 * </p>
	 * 
	 * @generated NOT
	 */
	private CWMAdapterFactory factory;
	
	/**
	 * 
	 * @generated NOT
	 */
	private List<AssociationProperty> inverseAssociationProperties;

	/**
	 * <p>
	 * Creates a new <code>CWMForeignKey</code> instance.
	 * </p>
	 * 
	 * @param dslForeignKey
	 *          the {@link ForeignKey} that is adopted by this
	 *          class
	 * @param factory
	 *            The {@link CWMAdapterFactory} used to create nested elements.
	 * 
	 * 
	 * @generated NOT
	 */
	public CWMForeignKey(ForeignKey dslForeignKey, CWMAdapterFactory factory) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("CWMForeignKey(dslProperty=" + dslForeignKey + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslForeignKey = dslForeignKey;
		this.factory = factory;
	
	
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("CWMForeignKey() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.AssociationProperty#addAssociation(AssociationProperty)
	 * 
	 * @generated NOT
	 */
	public void addAssociation(AssociationProperty bProperty) {
		if (!isInverseAssociation(bProperty) && bProperty != this && bProperty != null) this.getInverseAssociationProperties().add(bProperty);	
	}
	
	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.AssociationProperty#getAssociation(String)
	 * 
	 * @generated NOT
	 */
	public AssociationProperty getAssociation(String propName) {
		AssociationProperty property = null;
		for (AssociationProperty prop : getInverseAssociationProperties()) {
			if (prop.getName().equals(propName)) {
				property = prop;
				break;
			}
		}
		return property;
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.AssociationProperty#removeAssociation(AssociationProperty)
	 * 
	 * @generated NOT
	 */
	public void removeAssociation(AssociationProperty bProperty) {
		this.getInverseAssociationProperties().remove(bProperty);
		
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.AssociationProperty#getInverseAssociationProperties()
	 * 
	 * @generated NOT
	 */
	public List<AssociationProperty> getInverseAssociationProperties() {
		if (this.inverseAssociationProperties == null) {
			this.inverseAssociationProperties = new LinkedList<AssociationProperty>();
		}
		return this.inverseAssociationProperties;
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.AssociationProperty#isInverseAssociation(AssociationProperty)
	 * 
	 * @generated NOT
	 */
	public boolean isInverseAssociation(AssociationProperty bProperty) {
		return getInverseAssociationProperties().contains(bProperty);
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.AssociationProperty#addAssociations(List)
	 * 
	 * @generated NOT
	 */
	public void addAssociations(List<AssociationProperty> bProperty) {
		for (AssociationProperty prob : bProperty) {
			addAssociation(prob);
		}
	}

	public boolean isNavigable() {
		return true;
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl#getName()
	 */
	@Override
	public String getName() {
		return this.dslForeignKey.getName();
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#getType()
	 */
	@Override
	public Type getType() {
		return this.factory.createType(dslForeignKey.getUniqueKey().getNamespace());
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#getOwningType()
	 */
	@Override
	public Type getOwningType() {
		return this.factory.createType(dslForeignKey.getNamespace());
	}
}