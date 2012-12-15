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

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EDataType;

import tudresden.ocl20.pivot.metamodels.ecore.EcoreMetamodelPlugin;
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
	private static final Logger LOGGER =
			EcoreMetamodelPlugin.getLogger(EcorePrimitiveType.class);

	/**
	 * An array of all Java {@link Class}es representing the
	 * {@link PrimitiveTypeKind#BOOLEAN}.
	 */
	private static Class<?> javaBooleanClasses[] =
			new Class<?>[] { boolean.class, Boolean.class };

	/**
	 * An array of all Java {@link Class}es representing the
	 * {@link PrimitiveTypeKind#INTEGER}.
	 */
	private static Class<?> javaIntegerClasses[] =
			new Class<?>[] { byte.class, Byte.class, short.class, Short.class,
					int.class, Integer.class, long.class, Long.class, BigInteger.class,
					BigDecimal.class };

	/**
	 * An array of all Java {@link Class}es representing the
	 * {@link PrimitiveTypeKind#REAL}.
	 */
	private static Class<?> javaRealClasses[] =
			new Class<?>[] { float.class, Float.class, double.class, Double.class };

	/**
	 * An array of all Java {@link Class}es representing the
	 * {@link PrimitiveTypeKind#STRING}.
	 */
	private static Class<?> javaStringClasses[] =
			new Class<?>[] { char.class, Character.class, String.class };

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
	 * Creates a new {@link EcorePrimitiveType} instance.
	 * </p>
	 * 
	 * @param eDataType
	 *          The {@link EDataType} that is adapted by this class.
	 * @param factory
	 *            The {@link EcoreAdapterFactory} used to create nested elements.
	 */
	public EcorePrimitiveType(EDataType eDataType,EcoreAdapterFactory factory) {

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
		this.factory = factory;
		
		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcorePrimitiveType() - exit";

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.
	}

	/**
	 * <p>
	 * Returns the {@link PrimitiveTypeKind} of a given {@link EDataType}. If the
	 * given {@link EDataType} shall not be mapped to a {@link PrimitiveType}, the
	 * {@link PrimitiveTypeKind#UNKNOWN} is returned.
	 * </p>
	 * 
	 * @param aDataType
	 *          The {@link EDataType} those {@link PrimitiveTypeKind} shall be
	 *          returned.
	 * @return The {@link PrimitiveTypeKind} of the given {@link EDataType}.
	 */
	public static PrimitiveTypeKind getKind(EDataType aDataType) {

		PrimitiveTypeKind result;

		result = null;

		/* Check if the adapted class is a boolean. */
		for (Class<?> aClass : javaBooleanClasses) {

			if (aClass.getCanonicalName().equals(aDataType.getInstanceTypeName())) {
				result = PrimitiveTypeKind.BOOLEAN;
				break;
			}
			// no else.
		}

		/* Else check if the adapted class is an integer. */
		if (result == null) {

			for (Class<?> aClass : javaIntegerClasses) {

				if (aClass.getCanonicalName().equals(aDataType.getInstanceTypeName())) {
					result = PrimitiveTypeKind.INTEGER;
					break;
				}
				// no else.
			}
		}

		/* Else check if the adapted class is a real. */
		if (result == null) {

			for (Class<?> aClass : javaRealClasses) {

				if (aClass.getCanonicalName().equals(aDataType.getInstanceTypeName())) {
					result = PrimitiveTypeKind.REAL;
					break;
				}
				// no else.
			}
		}

		/* Else check if the adapted class is a string. */
		if (result == null) {

			for (Class<?> aClass : javaStringClasses) {

				if (aClass.getCanonicalName().equals(aDataType.getInstanceTypeName())) {
					result = PrimitiveTypeKind.STRING;
					break;
				}
				// no else.
			}
		}

		/* Else the EDataType is not a primitive type. */
		if (result == null) {
			result = PrimitiveTypeKind.UNKNOWN;
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getKind()
	 */
	@Override
	public PrimitiveTypeKind getKind() {

		PrimitiveTypeKind result;

		result = getKind(this.eDataType);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getName()
	 */
	@Override
	public String getName() {

		String result;		
		result = this.eDataType.getName();
		
		if (result == null) {
		 result = this.getKind().getName();
		}
		// no else.
		
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getNamespace ()
	 */
	@Override
	public Namespace getNamespace() {

		return factory.createNamespace(this.eDataType
				.getEPackage());
	}
}