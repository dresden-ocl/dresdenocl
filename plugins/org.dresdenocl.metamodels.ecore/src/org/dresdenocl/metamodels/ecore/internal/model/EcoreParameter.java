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
import org.eclipse.emf.ecore.EParameter;

import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.metamodels.ecore.EcoreMetamodelPlugin;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.Parameter;
import org.dresdenocl.pivotmodel.ParameterDirectionKind;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.base.AbstractParameter;

/**
 * <p>
 * An implementation of the Pivot Model {@link Parameter} concept for Ecore.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public class EcoreParameter extends AbstractParameter implements Parameter {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = EcoreMetamodelPlugin
			.getLogger(EcoreParameter.class);

	/** The adapted {@link EParameter}. */
	private EParameter eParameter;

	/**
	 * <p>
	 * The {@link EcoreAdapterFactory} used to create nested elements.
	 * </p>
	 */
	private EcoreAdapterFactory factory;
	
	/**
	 * <p>
	 * Creates a new {@link EcoreParameter}.
	 * </p>
	 * 
	 * @param eParameter
	 *            The adapted {@link EParameter}.
	 * @param factory
	 *            The {@link EcoreAdapterFactory} used to create nested elements.
	 */
	public EcoreParameter(EParameter eParameter,EcoreAdapterFactory factory) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreParameter(";
			msg += "eParameter = " + eParameter;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		/* Initialize adapted EParameter. */
		this.eParameter = eParameter;
		this.factory = factory;
		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreParameter() - exit";

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.pivotmodel.base.AbstractParameter#getName()
	 */
	@Override
	public String getName() {

		return this.eParameter.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.pivotmodel.base.AbstractParameter#getOperation()
	 */
	@Override
	public Operation getOperation() {

		return factory.createOperation(this.eParameter
				.getEOperation());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.pivotmodel.base.AbstractParameter#getType()
	 */
	@Override
	public Type getType() {

		Type result;

		Type elementType;
		elementType = factory.createType(eParameter
				.getEType());

		/* Probably put the type into a collection. */
		if (this.eParameter.isMany()) {

			if (this.eParameter.isUnique()) {

				/* Adapt to OrderedSet. */
				if (this.eParameter.isOrdered()) {
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
				if (this.eParameter.isOrdered()) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.pivotmodel.impl.ParameterImpl#getKind()
	 */
	@Override
	public ParameterDirectionKind getKind() {

		return ParameterDirectionKind.IN;
	}
}