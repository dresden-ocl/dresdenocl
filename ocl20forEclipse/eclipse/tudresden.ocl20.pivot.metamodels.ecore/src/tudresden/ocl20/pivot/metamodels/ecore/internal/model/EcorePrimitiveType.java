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
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EcorePackage;

import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType;

/**
 * <p>
 * An implementation of the Pivot Model {@link PrimitiveType} concept for Ecore.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public class EcorePrimitiveType extends AbstractPrimitiveType implements
		PrimitiveType {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = Logger
			.getLogger(EcorePrimitiveType.class);

	/** The adapted {@link EDataType}. */
	private EDataType eDataType;

	/**
	 * <p>
	 * Creates a new {@link EcorePrimitiveType} instance.
	 * </p>
	 * 
	 * @param eDataType
	 *            The {@link EDataType} that is adapted by this class.
	 */
	public EcorePrimitiveType(EDataType eDataType) {

		super();

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcorePrimitiveType(";
			msg += "eDataType = " + eDataType;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		/* Initialize adapted EDataType. */
		this.eDataType = eDataType;

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcorePrimitiveType() - exit";

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getKind()
	 */
	@Override
	public PrimitiveTypeKind getKind() {
	
		PrimitiveTypeKind result;
	
		switch (this.eDataType.getClassifierID()) {
	
		case EcorePackage.EBYTE:
		case EcorePackage.EBYTE_OBJECT:
		case EcorePackage.ESHORT:
		case EcorePackage.ESHORT_OBJECT:
		case EcorePackage.EINT:
		case EcorePackage.EINTEGER_OBJECT:
		case EcorePackage.ELONG:
		case EcorePackage.ELONG_OBJECT:
		case EcorePackage.EBIG_INTEGER:
		case EcorePackage.EBIG_DECIMAL: {
			result = PrimitiveTypeKind.INTEGER;
		}
	
		case EcorePackage.EFLOAT:
		case EcorePackage.EFLOAT_OBJECT:
		case EcorePackage.EDOUBLE:
		case EcorePackage.EDOUBLE_OBJECT: {
			result = PrimitiveTypeKind.REAL;
		}
	
		case EcorePackage.EBOOLEAN:
		case EcorePackage.EBOOLEAN_OBJECT: {
			result = PrimitiveTypeKind.BOOLEAN;
		}
	
		case EcorePackage.ECHAR:
		case EcorePackage.ECHARACTER_OBJECT:
		case EcorePackage.ESTRING: {
			result = PrimitiveTypeKind.STRING;
		}
	
		default: {
			result = PrimitiveTypeKind.UNKNOWN;
		}
		}
		// end switch.
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getName()
	 */
	@Override
	public String getName() {
		return this.eDataType.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getNamespace
	 * ()
	 */
	@Override
	public Namespace getNamespace() {
		return EcoreAdapterFactory.INSTANCE.createNamespace(this.eDataType
				.getEPackage());
	}
}