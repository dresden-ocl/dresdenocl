/*
 * Copyright (C) 2008-2009 by Michael Thiele & Claas Wilke (claaswilke@gmx.net)
 * This file is part of the UML2 Meta Model of Dresden OCL2 for Eclipse. Dresden
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
package org.dresdenocl.metamodels.uml2.internal.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;

import org.dresdenocl.metamodels.uml2.UML2MetamodelPlugin;
import org.dresdenocl.model.IModel;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.PrimitiveType;
import org.dresdenocl.pivotmodel.PrimitiveTypeKind;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.base.AbstractPrimitiveType;

/**
 * <p>
 * An implementation of the Pivot Model {@link PrimitiveType} concept for UML2.
 * </p>
 * 
 * @author Michael Thiele
 * 
 * @generated
 */
public class UML2PrimitiveType extends AbstractPrimitiveType implements
		PrimitiveType {

	/**
	 * <p>
	 * Logger for this class
	 * </p>
	 * 
	 * @generated NOT
	 */
	private static final Logger LOGGER = UML2MetamodelPlugin
			.getLogger(UML2PrimitiveType.class);

	/**
	 * An array of all Names of {@link Type}s representing the
	 * {@link PrimitiveTypeKind#BOOLEAN}.
	 */
	private static String booleanKindNames[] = new String[] { "Boolean",
			boolean.class.getCanonicalName(), Boolean.class.getCanonicalName(),
			"EBoolean", "EBooleanObject" };

	/**
	 * An array of all Names of {@link Type}s representing the
	 * {@link PrimitiveTypeKind#INTEGER}.
	 */
	private static String integerKindNames[] = new String[] { "Integer",
			"UnlimitedNatural", byte.class.getCanonicalName(),
			Byte.class.getCanonicalName(), short.class.getCanonicalName(),
			Short.class.getCanonicalName(), int.class.getCanonicalName(),
			Integer.class.getCanonicalName(), long.class.getCanonicalName(),
			Long.class.getCanonicalName(), BigInteger.class.getCanonicalName(),
			BigDecimal.class.getCanonicalName(), "EByte", "EByteObject",
			"EShort", "EShortObject", "EInt", "EIntegerObject", "ELong",
			"ELongObject", "EBigInteger", "EBigDecimal" };

	/**
	 * An array of all Names of {@link Type}s representing the
	 * {@link PrimitiveTypeKind#REAL}.
	 */
	private static String realKindNames[] = new String[] {
			float.class.getCanonicalName(), Float.class.getCanonicalName(),
			double.class.getCanonicalName(), Double.class.getCanonicalName(),
			"EFloat", "EFloatObject", "EDouble", "EDoubleObject" };

	/**
	 * An array of all Names of {@link Type}s representing the
	 * {@link PrimitiveTypeKind#STRING}.
	 */
	private static String stringKindNames[] = new String[] { "String",
			char.class.getCanonicalName(), Character.class.getCanonicalName(),
			String.class.getCanonicalName(), "EChar", "ECharacter", "EString" };

	/**
	 * <p>
	 * the adapted org.eclipse.uml2.uml.PrimitiveType data type
	 * </p>
	 * 
	 * @generated
	 */
	private org.eclipse.uml2.uml.PrimitiveType dslPrimitiveType;

	/**
	 * <p>
	 * The {@link UML2AdapterFactory} used to create nested elements.
	 * </p>
	 * 
	 * @generate NOT
	 */
	private UML2AdapterFactory factory;

	/**
	 * <p>
	 * Creates a new <code>UML2PrimitiveType</code> instance.
	 * </p>
	 * 
	 * @param dslPrimitiveType
	 *            the {@link org.eclipse.uml2.uml.PrimitiveType} that is adopted
	 *            by this class
	 * @param factory
	 *            The {@link UML2AdapterFactory} used to create nested elements.
	 * 
	 * @generated NOT
	 */
	public UML2PrimitiveType(
			org.eclipse.uml2.uml.PrimitiveType dslPrimitiveType,
			UML2AdapterFactory factory) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("UML2PrimitiveType(dslPrimitiveType = " + dslPrimitiveType + ", factory = " + factory + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslPrimitiveType = dslPrimitiveType;
		this.factory = factory;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("org.eclipse.uml2.uml.PrimitiveType() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * <p>
	 * This method can be used to check whether or not a given
	 * {@link org.eclipse.uml2.uml.Type} shall be mapped to a
	 * {@link PrimitiveType} in the {@link IModel}. If not, the
	 * {@link PrimitiveTypeKind#UNKNOWN} is returned.
	 * </p>
	 * 
	 * @param aUmlType
	 *            The {@link org.eclipse.uml2.uml.Type} that shall be checked.
	 * 
	 * @return The {@link PrimitiveTypeKind} of the given
	 *         {@link org.eclipse.uml2.uml.Type} or
	 *         {@link PrimitiveTypeKind#UNKNOWN}.
	 * @generated NOT
	 */
	public static PrimitiveTypeKind getKind(org.eclipse.uml2.uml.Type aUmlType) {

		PrimitiveTypeKind result;

		result = null;

		/* Check if the adapted type is a boolean. */
		for (String aName : booleanKindNames) {

			if (aName.equals(aUmlType.getName())) {
				result = PrimitiveTypeKind.BOOLEAN;
				break;
			}
			// no else.
		}

		/* Else check if the adapted type is an integer. */
		if (result == null) {

			for (String aName : integerKindNames) {

				if (aName.equals(aUmlType.getName())) {
					result = PrimitiveTypeKind.INTEGER;
					break;
				}
				// no else.
			}
		}

		/* Else check if the adapted type is a real. */
		if (result == null) {

			for (String aName : realKindNames) {

				if (aName.equals(aUmlType.getName())) {
					result = PrimitiveTypeKind.REAL;
					break;
				}
				// no else.
			}
		}

		/* Else check if the adapted type is a string. */
		if (result == null) {

			for (String aName : stringKindNames) {

				if (aName.equals(aUmlType.getName())) {
					result = PrimitiveTypeKind.STRING;
					break;
				}
				// no else.
			}
		}

		/* Else the kind is unknown. */
		if (result == null) {
			result = PrimitiveTypeKind.UNKNOWN;
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * This method implements a type mapping from
	 * {@link org.eclipse.uml2.uml.PrimitiveType} types to the predefined
	 * primitive types of the Pivot Model.
	 * </p>
	 * 
	 * @generated NOT
	 */
	@Override
	public PrimitiveTypeKind getKind() {

		PrimitiveTypeKind result;

		result = getKind(this.dslPrimitiveType);

		/* Eventually log a warning. */
		if (result == PrimitiveTypeKind.UNKNOWN && LOGGER.isInfoEnabled()) {
			String msg;

			msg = NLS.bind(UML2ModelMessages.UML2_UnknownPrimitiveTypeKind,
					this.dslPrimitiveType.getName());

			LOGGER.warn(msg);
		}
		// no else.

		return result;
	}

	/**
	 * @see org.dresdenocl.pivotmodel.base.AbstractPrimitiveType#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {

		String result;
		result = this.dslPrimitiveType.getName();

		if (result == null) {
			result = this.getKind().getName();
		}
		// no else.

		return result;
	}

	/**
	 * @see org.dresdenocl.pivotmodel.base.AbstractPrimitiveType#getNamespace()
	 * 
	 * @generated NOT
	 */
	@Override
	public Namespace getNamespace() {

		return this.factory.createNamespace(this.dslPrimitiveType.getPackage());
	}
}