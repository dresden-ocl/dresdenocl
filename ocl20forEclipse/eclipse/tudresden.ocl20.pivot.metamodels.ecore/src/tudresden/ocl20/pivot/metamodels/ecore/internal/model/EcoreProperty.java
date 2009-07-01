/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
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
package tudresden.ocl20.pivot.metamodels.ecore.internal.model;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty;

/**
 * <p>
 * An implementation of the Pivot Model {@link Property} concept for Ecore.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public class EcoreProperty extends AbstractProperty implements Property {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = Logger.getLogger(EcoreProperty.class);

	/** The adapted {@link EAttribute} or {@link EReference}. */
	private EStructuralFeature eStructuralFeature;

	/**
	 * <p>
	 * Creates a new {@link EcoreProperty} instance.
	 * </p>
	 * 
	 * @param eStructuralFeature
	 *            The adapted {@link EAttribute} or {@link EReference}.
	 */
	public EcoreProperty(EStructuralFeature eStructuralFeature) {

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
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getName()
	 */
	@Override
	public String getName() {
		return this.eStructuralFeature.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getOwningType()
	 */
	@Override
	public Type getOwningType() {
		return EcoreAdapterFactory.INSTANCE.createType(this.eStructuralFeature
				.getEContainingClass());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getType()
	 */
	@Override
	public Type getType() {
		return EcoreAdapterFactory.INSTANCE.createType(this.eStructuralFeature
				.getEType());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isMultiple()
	 */
	@Override
	public boolean isMultiple() {
		return this.eStructuralFeature.isMany();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isOrdered()
	 */
	@Override
	public boolean isOrdered() {
		return this.eStructuralFeature.isOrdered();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isUnique()
	 */
	@Override
	public boolean isUnique() {
		return this.eStructuralFeature.isUnique();
	}
}