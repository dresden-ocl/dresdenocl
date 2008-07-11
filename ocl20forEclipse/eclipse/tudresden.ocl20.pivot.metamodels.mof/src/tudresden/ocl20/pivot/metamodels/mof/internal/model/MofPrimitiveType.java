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
package tudresden.ocl20.pivot.metamodels.mof.internal.model;

import org.apache.log4j.Logger;

import java.util.Arrays;

import tudresden.ocl20.core.jmi.mof14.model.DataType;
import tudresden.ocl20.core.jmi.mof14.model.MofPackage;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType;

/**
 * An implementation of the Pivot Model {@link Type} concept for
 * primitive type of MOF metamodel in MDR.
 * 
 * @author Ronny Brandt
 * @version 1.0 09.05.2007
 */
public class MofPrimitiveType extends AbstractPrimitiveType implements Type {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MofPrimitiveType.class);

	// the adapted MOF data type
	private DataType primitiveType;
	
	// Strings representing integer types in MOF
	private static String[] integerNames = {"Integer", "Short", "Long"};
	
	// Strings representing real types in MOF
	private static String[] realNames = {"Float", "Double"};
	
	/**
	 * Creates a new <code>MofPrimitiveType</code> instance.
	 * 
	 * @param primitiveType the MOF {@link DataType} adapted by this class
	 */
	public MofPrimitiveType(DataType primitiveType) {
		if (logger.isDebugEnabled()) {
			logger.debug("MofPrimitiveType(DataType primitiveType=" + primitiveType
					+ ") - enter");
		}

		this.primitiveType = primitiveType;

		if (logger.isDebugEnabled()) {
			logger.debug("MofPrimitiveType(DataType) - exit");
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

		String kind = primitiveType.getName();
		if (Arrays.asList(integerNames).contains(kind))
			return PrimitiveTypeKind.INTEGER;
		else if (kind.equals("Boolean"))
			return PrimitiveTypeKind.BOOLEAN;
		else if (Arrays.asList(realNames).contains(kind))
			return PrimitiveTypeKind.REAL;
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

		String returnString = primitiveType.getName();
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

		Namespace returnNamespace = MofAdapterFactory.INSTANCE
				.createNamespace((MofPackage) primitiveType.getContainer());
		if (logger.isDebugEnabled()) {
			logger.debug("getNamespace() - exit - return value=" + returnNamespace);
		}
		return returnNamespace;
	}

}
