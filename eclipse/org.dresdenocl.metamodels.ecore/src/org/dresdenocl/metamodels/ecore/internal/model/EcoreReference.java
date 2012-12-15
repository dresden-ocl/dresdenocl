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
package org.dresdenocl.metamodels.ecore.internal.model;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.dresdenocl.metamodels.ecore.EcoreMetamodelPlugin;
import org.dresdenocl.pivotmodel.AssociationProperty;
import org.dresdenocl.pivotmodel.Property;

/**
 * <p>
 * An implementation of the Pivot Model {@link Property} concept for Ecore.
 * </p>
 * 
 * @author Matthias Braeuer
 * @uauthor Bjoern Freitag
 */
public class EcoreReference extends EcoreProperty implements AssociationProperty {

	/**
	 * <p>
	 * Logger for this class
	 * </p>
	 * 
	 * @generated NOT
	 */
	private static final Logger LOGGER =
			EcoreMetamodelPlugin.getLogger(EcoreReference.class);
	
	/**
	 * 
	 * @generated NOT
	 */
	private List<AssociationProperty> inverseAssociationProperties;
	
	/**
	 * <p>
	 * Creates a new {@link EcoreReference} instance.
	 * </p>
	 * 
	 * @param eStructuralFeature
	 *            The adapted {@link EAttribute} or {@link EReference}.
	 * @param factory
	 *            The {@link EcoreAdapterFactory} used to create nested elements.
	 */
	public EcoreReference(EStructuralFeature eStructuralFeature,EcoreAdapterFactory factory) {

		super(eStructuralFeature, factory);
		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreProperty(";
			msg += "eStructuralFeature = " + eStructuralFeature;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.
	
		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreProperty() - exit";

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.
	}

	@Override
	public List<AssociationProperty> getInverseAssociationProperties() {
		if (this.inverseAssociationProperties == null) {
			this.inverseAssociationProperties = new LinkedList<AssociationProperty>();
		}
		return this.inverseAssociationProperties;
	}
	@Override
	public void addAssociation(AssociationProperty bProperty) {
		if (!isInverseAssociation(bProperty) && bProperty != this && bProperty != null) getInverseAssociationProperties().add(bProperty);
	}

	@Override
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

	@Override
	public void removeAssociation(AssociationProperty bProperty) {
		this.getInverseAssociationProperties().remove(bProperty);	
	}

	@Override
	public boolean isInverseAssociation(AssociationProperty bProperty) {
		return getInverseAssociationProperties().contains(bProperty);
	}

	@Override
	public void addAssociations(List<AssociationProperty> bProperty) {
		for (AssociationProperty prob : bProperty) {
			addAssociation(prob);
		}
		
	}

	@Override
	public boolean isNavigable() {
		return true;
	}

}