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

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.metamodels.ecore.EcoreMetamodelPlugin;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.base.AbstractProperty;

/**
 * <p>
 * An implementation of the Pivot Model {@link Property} concept for Ecore.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public class EcoreProperty extends AbstractProperty implements Property {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = EcoreMetamodelPlugin
			.getLogger(EcoreProperty.class);

	/** The adapted {@link EAttribute} or {@link EReference}. */
	protected EStructuralFeature eStructuralFeature;

	/**
	 * <p>
	 * The {@link EcoreAdapterFactory} used to create nested elements.
	 * </p>
	 */
	protected EcoreAdapterFactory factory;
	
	/**
	 * <p>
	 * Creates a new {@link EcoreProperty} instance.
	 * </p>
	 * 
	 * @param eStructuralFeature
	 *            The adapted {@link EAttribute} or {@link EReference}.
	 * @param factory
	 *            The {@link EcoreAdapterFactory} used to create nested elements.
	 */
	public EcoreProperty(EStructuralFeature eStructuralFeature,EcoreAdapterFactory factory) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreProperty(";
			msg += "eStructuralFeature = " + eStructuralFeature;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		/* Initialize adapted feature. */
		this.eStructuralFeature = eStructuralFeature;
		this.factory = factory;
		
		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreProperty() - exit";

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.pivotmodel.base.AbstractProperty#getName()
	 */
	@Override
	public String getName() {

		return this.eStructuralFeature.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.pivotmodel.base.AbstractProperty#getOwningType()
	 */
	@Override
	public Type getOwningType() {

		return factory.createType(this.eStructuralFeature
				.getEContainingClass());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.pivotmodel.base.AbstractProperty#getType()
	 */
	@Override
	public Type getType() {

		Type result;

		Type elementType;
		elementType = factory
				.createType(eStructuralFeature.getEType());

		/* Probably put the type into a collection. */
		if (this.eStructuralFeature.isMany()) {

			if (this.eStructuralFeature.isUnique()) {

				/* Adapt to OrderedSet. */
				if (this.eStructuralFeature.isOrdered()) {
					result = EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOrderedSetType(elementType);
				}

				/* Adapt to Set. */
				else {
					result = EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getSetType(elementType);
				}
			}

			else {

				/* Adapt to Sequence. */
				if (this.eStructuralFeature.isOrdered()) {
					result = EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getSequenceType(elementType);
				}

				/* Adapt to Bag. */
				else {
					result = EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getBagType(elementType);
				}
			}
		}

		else {
			result = elementType;
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see org.dresdenocl.pivotmodel.impl.PropertyImpl#isIdentifier()
	 */
	@Override
	public boolean isIdentifier() {
		return (eStructuralFeature instanceof EAttribute && ((EAttribute)eStructuralFeature).isID());
	}
}