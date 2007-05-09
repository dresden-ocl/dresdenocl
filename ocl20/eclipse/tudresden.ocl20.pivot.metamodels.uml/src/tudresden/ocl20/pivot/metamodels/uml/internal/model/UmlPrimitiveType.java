/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Ronny Brandt (Ronny_Brandt@web.de).            *
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
 */
package tudresden.ocl20.pivot.metamodels.uml.internal.model;

import org.apache.log4j.Logger;

import java.util.Arrays;

import tudresden.ocl20.core.jmi.uml15.core.DataType;
import tudresden.ocl20.core.jmi.uml15.modelmanagement.Package;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType;

/**
 * An implementation of the Pivot Model {@link PrimitiveType} concept for
 * UML metamodel in MDR.
 * 
 * @author Ronny Brandt
 * @version 1.0 09.05.2007
 */
public class UmlPrimitiveType extends AbstractPrimitiveType implements Type {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UmlPrimitiveType.class);

	// the adapted UML data type
	private DataType dataType;
	
	// Strings representing integer types in UML
	private static String[] integerNames = {"int", "short", "long", "byte"};
	
	// Strings representing real types in UML
	private static String[] realNames = {"float", "double"};
	
	/**
	 * Creates a new <code>UmlPrimitive type</code> instance.
	 * 
	 * @param dataType the {@link DataType} adapted by this class
	 */
	public UmlPrimitiveType(DataType dataType) {
		if (logger.isDebugEnabled()) {
			logger.debug("UmlPrimitiveType(DataType dataType=" + dataType
					+ ") - enter");
		}

		this.dataType = dataType;

		if (logger.isDebugEnabled()) {
			logger.debug("UmlPrimitiveType(DataType) - exit");
		}
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getKind()
	 */
	@Override
	public PrimitiveTypeKind getKind() {
		if (logger.isDebugEnabled()) {
			logger.debug("getKind() - enter");
		}

		String kind = dataType.getName();
		if (Arrays.asList(integerNames).contains(kind))
			return PrimitiveTypeKind.INTEGER;
		else if (Arrays.asList(realNames).contains(kind))
			return PrimitiveTypeKind.REAL;
/*		if (kind.equals("int"))
			return PrimitiveTypeKind.INTEGER;
		else if (kind.equals("short"))
			return PrimitiveTypeKind.INTEGER;
		else if (kind.equals("long"))
			return PrimitiveTypeKind.INTEGER;
		else if (kind.equals("byte"))
			return PrimitiveTypeKind.INTEGER;
		else if (kind.equals("float"))
			return PrimitiveTypeKind.REAL;
		else if (kind.equals("double"))
			return PrimitiveTypeKind.REAL;*/
		else if (kind.equals("boolean"))
			return PrimitiveTypeKind.BOOLEAN;
		else if (kind.equals("String"))
			return PrimitiveTypeKind.STRING;
		else 
			return PrimitiveTypeKind.UNKNOWN;
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getName()
	 */
	@Override
	public String getName() {
		if (logger.isDebugEnabled()) {
			logger.debug("getName() - enter");
		}

		String returnString = dataType.getName();
		if (logger.isDebugEnabled()) {
			logger.debug("getName() - exit - return value=" + returnString);
		}
		return returnString;
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getNamespace()
	 */
	@Override
	public Namespace getNamespace() {
		if (logger.isDebugEnabled()) {
			logger.debug("getNamespace() - enter");
		}

		Namespace returnNamespace = UmlAdapterFactory.INSTANCE
				.createNamespace((Package) dataType.getNamespace());
		if (logger.isDebugEnabled()) {
			logger.debug("getNamespace() - exit - return value=" + returnNamespace);
		}
		return returnNamespace;
	}

}
