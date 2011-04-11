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
package tudresden.ocl20.pivot.metamodels.ecore.internal.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;

import tudresden.ocl20.pivot.metamodels.ecore.EcoreMetamodelPlugin;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration;

/**
 * <p>
 * Adapts {@link EEnum}s as {@link Enumeration}s to the pivot model.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public class EcoreEnumeration extends AbstractEnumeration implements
		Enumeration {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			EcoreMetamodelPlugin.getLogger(EcoreEnumeration.class);

	/** The adapted {@link EEnum}. */
	private EEnum eEnum;

	/**
	 * <p>
	 * Creates a new {@link EcoreEnumeration} instance.
	 * </p>
	 * 
	 * @param eEnum
	 *          The {@link EEnum} adapted by this object.
	 */
	public EcoreEnumeration(EEnum eEnum) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreEnumeration(";
			msg += "eEnum = " + eEnum;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		/* Initialize adapted enumeration. */
		this.eEnum = eEnum;

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreEnumeration() - exit";

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration#getName()
	 */
	public String getName() {

		return this.eEnum.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration#getNamespace()
	 */
	@Override
	public Namespace getNamespace() {

		return EcoreAdapterFactory.INSTANCE.createNamespace(this.eEnum
				.getEPackage());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration#getOwnedLiteral
	 * ()
	 */
	@Override
	public List<EnumerationLiteral> getOwnedLiteral() {

		List<EnumerationLiteral> result;

		result = new BasicEList<EnumerationLiteral>();

		for (EEnumLiteral literal : eEnum.getELiterals()) {
			result
					.add(EcoreAdapterFactory.INSTANCE.createEnumerationLiteral(literal));
		}

		return result;
	}
}