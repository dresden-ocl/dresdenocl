/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Ronny Brandt (Ronny_Brandt@web.de).                    *
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
package tudresden.ocl20.pivot.modelbus.base;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Platform;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInvalid;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclPrimitiveType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclVoid;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.StandardlibraryAdapterFactory;
import tudresden.ocl20.pivot.modelbus.IModelInstance;

/**
 * Abstract implementation of {@link IModelInstance}
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public abstract class AbstractModelInstance implements IModelInstance {

	/** The default StandardlibraryAdapterFactory for this instance */
	protected static StandardlibraryAdapterFactory DEFAULTSLAF = null;

	/** The current StandardlibraryAdapterFactory for this instance */
	protected StandardlibraryAdapterFactory currentSlAF = null;

	// The instance name.
	protected String instanceName;

	/**
	 * Contains the operation names which are different in the standard library
	 * than in the OCL specification. The names are separated in sub maps
	 * depending on their number of arguments.
	 */
	protected static Map<Integer, Map<String, String>> operationNames = new HashMap<Integer, Map<String, String>>();

	/* Initializes the map. */
	static {
		Map<String, String> unaryOperations;
		Map<String, String> binaryOperations;

		unaryOperations = new HashMap<String, String>();
		unaryOperations.put("-", "negative");
		unaryOperations.put("oclIsUndefined", "isOclUndefined");
		operationNames.put(1, unaryOperations);

		binaryOperations = new HashMap<String, String>();
		binaryOperations.put("<=", "isLessEqual");
		binaryOperations.put("<", "isLessThan");
		binaryOperations.put("=", "isEqualTo");
		binaryOperations.put("<>", "isNotEqualTo");
		binaryOperations.put(">=", "isGreaterEqual");
		binaryOperations.put(">", "isGreaterThan");
		binaryOperations.put("-", "subtract");
		binaryOperations.put("+", "add");
		binaryOperations.put("*", "multiply");
		binaryOperations.put("/", "divide");
		binaryOperations.put(".", "getPropertyValue");
		binaryOperations.put("->", "asSet");
		operationNames.put(2, binaryOperations);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#getOperationName(java.lang
	 * .String, int)
	 */
	public String getOperationName(String name, int operatorCount) {
		Map<String, String> opMap = operationNames.get(operatorCount);
		if (opMap != null) {
			String ret = opMap.get(name);
			if (ret != null)
				return ret;
		}
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getAnyType()
	 */
	public OclType getAnyType() {
		return (OclType) Platform.getAdapterManager().getAdapter("OclAny",
				OclType.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getInvalid()
	 */
	public OclInvalid getInvalid() {
		return (OclInvalid) Platform.getAdapterManager().getAdapter("Invalid",
				OclInvalid.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getInvalidType()
	 */
	public OclType getInvalidType() {
		return (OclType) Platform.getAdapterManager().getAdapter("OclInvalid",
				OclType.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#getPrimitiveType(java.lang
	 * .String)
	 */
	public OclPrimitiveType getPrimitiveType(String name) {
		return (OclPrimitiveType) Platform.getAdapterManager().getAdapter(name,
				OclPrimitiveType.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getTypeType()
	 */
	public OclType getTypeType() {
		return (OclType) Platform.getAdapterManager().getAdapter("OclType",
				OclType.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getUndefined()
	 */
	public OclVoid getUndefined() {
		return (OclVoid) Platform.getAdapterManager().getAdapter("Undefined",
				OclVoid.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getVoidType()
	 */
	public OclType getVoidType() {
		return (OclType) Platform.getAdapterManager().getAdapter("OclVoid",
				OclType.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getDefaultSlAF()
	 */
	public StandardlibraryAdapterFactory getDefaultSlAF() {
		return DEFAULTSLAF;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#setCurrentSlAF(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.StandardlibraryAdapterFactory)
	 */
	public void setCurrentSlAF(StandardlibraryAdapterFactory slAF) {
		this.currentSlAF = slAF;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getCurrentSlAF()
	 */
	public StandardlibraryAdapterFactory getCurrentSlAF() {
		return currentSlAF;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getDisplayName()
	 */
	public String getDisplayName() {
		return instanceName;
	}
}
