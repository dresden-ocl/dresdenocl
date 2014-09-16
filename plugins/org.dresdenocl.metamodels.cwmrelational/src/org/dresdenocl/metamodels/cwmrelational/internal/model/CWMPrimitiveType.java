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

import org.apache.log4j.Logger;
import org.dresdenocl.metamodels.cwmrelational.CWMMetamodelPlugin;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.PrimitiveType;
import org.dresdenocl.pivotmodel.PrimitiveTypeKind;
import org.dresdenocl.pivotmodel.base.AbstractPrimitiveType;
import orgomg.cwm.resource.relational.SQLDataType;

/**
 * <p>
 * An implementation of the Pivot Model {@link PrimitiveType} concept for CWMrelational.
 * </p>
 * 
 * @author Bjoern Freitag
 * @generated NOT
 */
public abstract class CWMPrimitiveType<T extends SQLDataType> extends AbstractPrimitiveType implements PrimitiveType {

	/**
	 * <p>
	 * Logger for this class
	 * </p>
	 * 
	 * @generated NOT
	 */
	protected Logger LOGGER;

	/** The adapted {@link T} class. */
	protected T dslDataType;

	/**
	 * <p>
	 * The {@link CWMAdapterFactory} used to create nested elements.
	 * </p>
	 * 
	 * @generated NOT
	 */
	protected CWMAdapterFactory factory;


	/**
	 * An array of all Names of {@link Type}s representing the
	 * {@link PrimitiveTypeKind#BOOLEAN}.
	 */
	private static String booleanKindNames[] = new String[] { "BOOLEAN",
			boolean.class.getCanonicalName(), Boolean.class.getCanonicalName(),
			"EBoolean", "EBooleanObject" };

	/**
	 * An array of all Names of {@link Type}s representing the
	 * {@link PrimitiveTypeKind#INTEGER}.
	 */
	private static String integerKindNames[] = new String[] { "Integer",
			"UnlimitedNatural", "NUMERIC","SMALLINT", "INTEGER","INT","BIGINT","EByte", "EByteObject",
			"EShort", "EShortObject", "EInt", "EIntegerObject", "ELong",
			"ELongObject", "EBigInteger", "EBigDecimal" };

	/**
	 * An array of all Names of {@link Type}s representing the
	 * {@link PrimitiveTypeKind#REAL}.
	 */
	private static String realKindNames[] = new String[] {"FLOAT","REAL","DOUBLE PRESCISION", "DECIMAL", "DEC",
			"EFloat", "EFloatObject", "EDouble", "EDoubleObject" };

	/**
	 * An array of all Names of {@link Type}s representing the
	 * {@link PrimitiveTypeKind#STRING}.
	 */
	private static String stringKindNames[] = new String[] { "String","CHARACTER","CHAR","CHARACTER VARYING",
		"CHAR VARYING","VARCHAR","CHARACTER LARGE OBJECT","CHAR LARGE OBJECT", "CLOB"
			,"NATIONAL CHARACTER","NATIONAL CHAR","NCHAR","NATIONAL CHARACTER VARYING",
			"NATIONAL CHAR VARING","NATIONAL CHARACTER LARGE OBJECT","NCHAR LARGE OBJECT", "NCLOB",
			"BINARY","BINARY VARYING","VARBINARY","BINARY LARGE OBJECT", "BLOB"
			,"EChar", "ECharacter", "EString" };
	/**
	 * <p>
	 * Creates a new <code>CWMPrimitiveType</code> instance.
	 * </p>
	 * 
	 * @param dslDataType
	 *            the {@link T} that is adopted by this
	 *            class
	 * @param factory
	 *            The {@link CWMAdapterFactory} used to create nested elements.
	 * 
	 * @generated
	 */
	public CWMPrimitiveType(T dslDataType,Class<? extends CWMPrimitiveType<T>> CWMPrimitiveType,
			CWMAdapterFactory factory) {
		
		LOGGER = CWMMetamodelPlugin.getLogger(CWMPrimitiveType);
		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug(CWMPrimitiveType.getName()+"(dslDataType = " + dslDataType + "factory = " + factory + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslDataType = dslDataType;
		this.factory = factory;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(CWMPrimitiveType.getName()+"() - exit"); //$NON-NLS-1$
		}
	}
	
	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getNamespace()
	 * 
	 * @generated NOT
	 */
	@Override
	public Namespace getNamespace() {
		return this.factory.createNamespace(dslDataType.getNamespace());
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {

		String result;
		result = this.dslDataType.getName();

		if (result == null) {
			result = getKind().getName();
		}
		// no else.

		return result;
	}

	private static boolean getKind(String name,String[] liste) {
		for (String aName : liste) {

			if (aName.equals(name) || aName.replace(" ", "_").equals(name)) {
				return true;
			}
			// no else.
		}
		return false;
	}
	
	/**
	 * <p>
	 * This method can be used to check whether or not a given
	 * {@link SQLDataType} shall be mapped to a
	 * {@link PrimitiveType} in the {@link IModel}. If not, the
	 * {@link PrimitiveTypeKind#UNKNOWN} is returned.
	 * </p>
	 * 
	 * @param sqlDataType
	 *            The {@linkSQLDataType} that shall be checked.
	 * 
	 * @return The {@link PrimitiveTypeKind} of the given
	 *         {@link SQLDataType} or
	 *         {@link PrimitiveTypeKind#UNKNOWN}.
	 * @generated NOT
	 */
	public static PrimitiveTypeKind getKind(SQLDataType sqlDataType) {

		PrimitiveTypeKind result;

		result = null;

		/* Check if the adapted type is a boolean. */
		result =  getKind(sqlDataType.getName(),booleanKindNames) ? PrimitiveTypeKind.BOOLEAN : null ;

		/* Else check if the adapted type is an integer. */
		if (result == null) {
			result =  getKind(sqlDataType.getName(),integerKindNames) ? PrimitiveTypeKind.INTEGER : null ;
		}

		/* Else check if the adapted type is a real. */
		if (result == null) {
			result =  getKind(sqlDataType.getName(),realKindNames) ? PrimitiveTypeKind.REAL : null ;
		}

		/* Else check if the adapted type is a string. */
		if (result == null) {
			result =  getKind(sqlDataType.getName(),stringKindNames) ? PrimitiveTypeKind.STRING : null ;
		}
		
		/* Else the kind is unknown. */
		if (result == null) {
			result = PrimitiveTypeKind.UNKNOWN;
		}
		// no else.

		return result;
	}

}