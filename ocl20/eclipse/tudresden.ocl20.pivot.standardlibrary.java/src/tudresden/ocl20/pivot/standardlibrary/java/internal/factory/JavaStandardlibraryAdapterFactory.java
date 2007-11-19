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
package tudresden.ocl20.pivot.standardlibrary.java.internal.factory;

import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.StandardlibraryAdapterFactory;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.base.AbstractStandardlibraryAdapterFactory;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclBag;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclBoolean;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclEnumLiteral;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclEnumType;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclInteger;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclInvalid;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclObject;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclOrderedSet;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclPrimitiveType;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclReal;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclSequence;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclSet;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclString;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclTuple;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclType;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclVoid;

/**
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class JavaStandardlibraryAdapterFactory extends
		AbstractStandardlibraryAdapterFactory implements
		StandardlibraryAdapterFactory {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(JavaStandardlibraryAdapterFactory.class);

	// The INSTANCE
	private static JavaStandardlibraryAdapterFactory INSTANCE = null;

	/**
	 * Instantiates a new java standardlibrary adapter factory.
	 */
	private JavaStandardlibraryAdapterFactory() {
	}

	/**
	 * Gets the single instance of JavaStandardlibraryAdapterFactory.
	 * 
	 * @return single instance of JavaStandardlibraryAdapterFactory
	 */
	public static JavaStandardlibraryAdapterFactory getInstance() {
		if (logger.isDebugEnabled()) {
			logger.debug("getInstance() - start");
		}

		if (INSTANCE == null)
			INSTANCE = new JavaStandardlibraryAdapterFactory();

		if (logger.isDebugEnabled()) {
			logger.debug("getInstance() - end - return value=" + INSTANCE);
		}
		return INSTANCE;
	}

	// The names of the integer types in Java
	private static String[] INTEGER_TYPES = new String[] { "java.lang.Integer",
			"java.lang.Short", "java.lang.Long", "int", "short", "long" };

	// The names of the real types in Java
	private static String[] REAL_TYPES = new String[] { "java.lang.Double",
			"java.lang.Float", "float", "double" };

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.base.AbstractStandardlibraryAdapterFactory#adapterNotFound(java.lang.Object)
	 */
	@Override
	protected Object adapterNotFound(Object adaptableObject) {
		if (logger.isDebugEnabled()) {
			logger.debug("adapterNotFound(Object) - start");
		}

		Object returnObject = getOclObject(adaptableObject);
		if (logger.isDebugEnabled()) {
			logger.debug("adapterNotFound(Object) - end - return value="
					+ returnObject);
		}
		return returnObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.base.AbstractStandardlibraryAdapterFactory#getOclTuple(java.lang.Object)
	 */
	@Override
	protected Object getOclTuple(Object adaptableObject) {
		if (logger.isDebugEnabled()) {
			logger.debug("getOclTuple(Object) - start");
		}

		if (adaptableObject instanceof Map) {
			Object returnObject = new JavaOclTuple(
					(Map<String, OclRoot>) adaptableObject);

			if (logger.isDebugEnabled()) {
				logger.debug("getOclTuple(Object) - end - return value="
						+ returnObject);
			}
			return returnObject;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getOclTuple(Object) - end - return value=" + null);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.base.AbstractStandardlibraryAdapterFactory#getOclPrimitiveType(java.lang.Object)
	 */
	@Override
	protected Object getOclPrimitiveType(Object adaptableObject) {
		if (logger.isDebugEnabled()) {
			logger.debug("getOclPrimitiveType(Object) - start");
		}

		if (adaptableObject instanceof String) {
			Object returnObject = JavaOclPrimitiveType
					.getPrimitiveType((String) adaptableObject);

			if (logger.isDebugEnabled()) {
				logger
						.debug("getOclPrimitiveType(Object) - end - return value="
								+ returnObject);
			}
			return returnObject;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getOclPrimitiveType(Object) - end - return value="
					+ null);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.base.AbstractStandardlibraryAdapterFactory#getOclType(java.lang.Object)
	 */
	@Override
	protected Object getOclType(Object adaptableObject) {
		if (logger.isDebugEnabled()) {
			logger.debug("getOclType(Object) - start");
		}

		if (adaptableObject instanceof Class) {
			if (((Class) adaptableObject).getName().equals("java.lang.String")) {
				Object returnObject2 = JavaOclPrimitiveType
						.getPrimitiveType("String");
				if (logger.isDebugEnabled()) {
					logger.debug("getOclType(Object) - end - return value="
							+ returnObject2);
				}
				return returnObject2;
			}
			if (Arrays.asList(INTEGER_TYPES).contains(
					((Class) adaptableObject).getName())) {
				Object returnObject2 = JavaOclPrimitiveType
						.getPrimitiveType("Integer");
				if (logger.isDebugEnabled()) {
					logger.debug("getOclType(Object) - end - return value="
							+ returnObject2);
				}
				return returnObject2;
			}
			if (Arrays.asList(REAL_TYPES).contains(
					((Class) adaptableObject).getName())) {
				Object returnObject2 = JavaOclPrimitiveType
						.getPrimitiveType("Real");
				if (logger.isDebugEnabled()) {
					logger.debug("getOclType(Object) - end - return value="
							+ returnObject2);
				}
				return returnObject2;
			}
			Object returnObject = new JavaOclType((Class) adaptableObject);

			if (logger.isDebugEnabled()) {
				logger.debug("getOclType(Object) - end - return value="
						+ returnObject);
			}
			return returnObject;
		}
		if (adaptableObject instanceof String) {
			Object returnObject = JavaOclType.getType((String) adaptableObject);

			if (logger.isDebugEnabled()) {
				logger.debug("getOclType(Object) - end - return value="
						+ returnObject);
			}
			return returnObject;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getOclType(Object) - end - return value=" + null);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.base.AbstractStandardlibraryAdapterFactory#getOclObject(java.lang.Object)
	 */
	@Override
	protected Object getOclObject(Object adaptableObject) {
		if (logger.isDebugEnabled()) {
			logger.debug("getOclObject(Object) - start");
		}

		Object returnObject = new JavaOclObject(adaptableObject);
		if (logger.isDebugEnabled()) {
			logger.debug("getOclObject(Object) - end - return value="
					+ returnObject);
		}
		return returnObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.base.AbstractStandardlibraryAdapterFactory#getOclOrderedSet(java.lang.Object)
	 */
	@Override
	protected Object getOclOrderedSet(Object adaptableObject) {
		if (logger.isDebugEnabled()) {
			logger.debug("getOclOrderedSet(Object) - start");
		}

		if (adaptableObject instanceof List) {
			Object returnObject = new JavaOclOrderedSet((List) adaptableObject);
			if (logger.isDebugEnabled()) {
				logger.debug("getOclOrderedSet(Object) - end - return value="
						+ returnObject);
			}
			return returnObject;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getOclOrderedSet(Object) - end - return value="
					+ null);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.base.AbstractStandardlibraryAdapterFactory#getOclSequence(java.lang.Object)
	 */
	@Override
	protected Object getOclSequence(Object adaptableObject) {
		if (logger.isDebugEnabled()) {
			logger.debug("getOclSequence(Object) - start");
		}

		if (adaptableObject instanceof List) {
			Object returnObject = new JavaOclSequence((List) adaptableObject);
			if (logger.isDebugEnabled()) {
				logger.debug("getOclSequence(Object) - end - return value="
						+ returnObject);
			}
			return returnObject;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getOclSequence(Object) - end - return value=" + null);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.base.AbstractStandardlibraryAdapterFactory#getOclBag(java.lang.Object)
	 */
	@Override
	protected Object getOclBag(Object adaptableObject) {
		if (logger.isDebugEnabled()) {
			logger.debug("getOclBag(Object) - start");
		}

		if (adaptableObject instanceof List) {
			Object returnObject = new JavaOclBag((List) adaptableObject);
			if (logger.isDebugEnabled()) {
				logger.debug("getOclBag(Object) - end - return value="
						+ returnObject);
			}
			return returnObject;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getOclBag(Object) - end - return value=" + null);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.base.AbstractStandardlibraryAdapterFactory#getOclSet(java.lang.Object)
	 */
	@Override
	protected Object getOclSet(Object adaptableObject) {
		if (logger.isDebugEnabled()) {
			logger.debug("getOclSet(Object) - start");
		}

		if (adaptableObject instanceof Set) {
			Object returnObject = new JavaOclSet((Set) adaptableObject);
			if (logger.isDebugEnabled()) {
				logger.debug("getOclSet(Object) - end - return value="
						+ returnObject);
			}
			return returnObject;
		}
		if (adaptableObject instanceof List) {
			Set<OclRoot> set = new HashSet<OclRoot>((List) adaptableObject);
			Object returnObject = new JavaOclSet(set);
			if (logger.isDebugEnabled()) {
				logger.debug("getOclSet(Object) - end - return value="
						+ returnObject);
			}
			return returnObject;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getOclSet(Object) - end - return value=" + null);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.base.AbstractStandardlibraryAdapterFactory#getOclBoolean(java.lang.Object)
	 */
	@Override
	protected Object getOclBoolean(Object adaptableObject) {
		if (logger.isDebugEnabled()) {
			logger.debug("getOclBoolean(Object) - start");
		}

		if (adaptableObject instanceof Boolean) {
			Object returnObject = JavaOclBoolean
					.getInstance((Boolean) adaptableObject);
			if (logger.isDebugEnabled()) {
				logger.debug("getOclBoolean(Object) - end - return value="
						+ returnObject);
			}
			return returnObject;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getOclBoolean(Object) - end - return value=" + null);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.base.AbstractStandardlibraryAdapterFactory#getOclString(java.lang.Object)
	 */
	@Override
	protected Object getOclString(Object adaptableObject) {
		if (logger.isDebugEnabled()) {
			logger.debug("getOclString(Object) - start");
		}

		if (adaptableObject instanceof String) {
			Object returnObject = new JavaOclString((String) adaptableObject);
			if (logger.isDebugEnabled()) {
				logger.debug("getOclString(Object) - end - return value="
						+ returnObject);
			}
			return returnObject;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getOclString(Object) - end - return value=" + null);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.base.AbstractStandardlibraryAdapterFactory#getOclInteger(java.lang.Object)
	 */
	@Override
	protected Object getOclInteger(Object adaptableObject) {
		if (logger.isDebugEnabled()) {
			logger.debug("getOclInteger(Object) - start");
		}

		if (adaptableObject instanceof Integer) {
			Object returnObject = new JavaOclInteger((Integer) adaptableObject);
			if (logger.isDebugEnabled()) {
				logger.debug("getOclInteger(Object) - end - return value="
						+ returnObject);
			}
			return returnObject;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getOclInteger(Object) - end - return value=" + null);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.base.AbstractStandardlibraryAdapterFactory#getOclInvalid(java.lang.Object)
	 */
	@Override
	protected Object getOclInvalid(Object adaptableObject) {
		if (logger.isDebugEnabled()) {
			logger.debug("getOclInvalid(Object) - start");
		}

		Object returnObject = JavaOclInvalid.getInstance();
		if (logger.isDebugEnabled()) {
			logger.debug("getOclInvalid(Object) - end - return value="
					+ returnObject);
		}
		return returnObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.base.AbstractStandardlibraryAdapterFactory#getOclVoid(java.lang.Object)
	 */
	@Override
	protected Object getOclVoid(Object adaptableObject) {
		if (logger.isDebugEnabled()) {
			logger.debug("getOclVoid(Object) - start");
		}

		Object returnObject = JavaOclVoid.getInstance();
		if (logger.isDebugEnabled()) {
			logger.debug("getOclVoid(Object) - end - return value="
					+ returnObject);
		}
		return returnObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.base.AbstractStandardlibraryAdapterFactory#getOclRoot(java.lang.Object)
	 */
	@Override
	protected Object getOclRoot(Object adaptableObject) {
		if (logger.isDebugEnabled()) {
			logger.debug("getOclRoot(Object) - start");
		}

		if (adaptableObject instanceof Integer) {
			Object returnObject = new JavaOclInteger((Integer) adaptableObject);
			if (logger.isDebugEnabled()) {
				logger.debug("getOclRoot(Object) - end - return value="
						+ returnObject);
			}
			return returnObject;
		}
		if (adaptableObject instanceof Float) {
			Object returnObject = new JavaOclReal((Float) adaptableObject);
			if (logger.isDebugEnabled()) {
				logger.debug("getOclRoot(Object) - end - return value="
						+ returnObject);
			}
			return returnObject;
		}
		if (adaptableObject instanceof String) {
			Object returnObject = new JavaOclString((String) adaptableObject);
			if (logger.isDebugEnabled()) {
				logger.debug("getOclRoot(Object) - end - return value="
						+ returnObject);
			}
			return returnObject;
		}
		if (adaptableObject instanceof Boolean) {
			Object returnObject = JavaOclBoolean
					.getInstance((Boolean) adaptableObject);
			if (logger.isDebugEnabled()) {
				logger.debug("getOclRoot(Object) - end - return value="
						+ returnObject);
			}
			return returnObject;
		}
		if (adaptableObject instanceof EList) {
			Iterator<Object> it = ((EList) adaptableObject).iterator();
			Set<OclRoot> set = new HashSet<OclRoot>();
			while (it.hasNext()) {
				Object current = it.next();
				if (current instanceof OclRoot)
					set.add((OclRoot) current);
				else
					set.add((OclRoot) getAdapter(current, OclRoot.class));
			}
			Object returnObject = getAdapter(set, OclSet.class);
			if (logger.isDebugEnabled()) {
				logger.debug("getOclRoot(Object) - end - return value="
						+ returnObject);
			}
			return returnObject;
		}
		if (adaptableObject instanceof Collection) {
			Iterator<Object> it = ((Collection) adaptableObject).iterator();
			Set<OclRoot> set = new HashSet<OclRoot>();
			while (it.hasNext()) {
				Object current = it.next();
				if (current instanceof OclRoot)
					set.add((OclRoot) current);
				else
					set.add((OclRoot) getAdapter(current, OclRoot.class));
			}
			Object returnObject = getAdapter(set, OclSet.class);
			if (logger.isDebugEnabled()) {
				logger.debug("getOclRoot(Object) - end - return value="
						+ returnObject);
			}
			return returnObject;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getOclRoot(Object) - end - return value=" + null);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.base.AbstractStandardlibraryAdapterFactory#getOclReal(java.lang.Object)
	 */
	@Override
	protected Object getOclReal(Object adaptableObject) {
		if (logger.isDebugEnabled()) {
			logger.debug("getOclReal(Object) - start");
		}

		if (adaptableObject instanceof Float) {
			Object returnObject = new JavaOclReal((Float) adaptableObject);
			if (logger.isDebugEnabled()) {
				logger.debug("getOclReal(Object) - end - return value="
						+ returnObject);
			}
			return returnObject;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getOclReal(Object) - end - return value=" + null);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.base.AbstractStandardlibraryAdapterFactory#getOclEnumLiteral(java.lang.Object)
	 */
	@Override
	protected Object getOclEnumLiteral(Object adaptableObject) {
		if (logger.isDebugEnabled()) {
			logger.debug("getOclEnumLiteral(Object) - start");
		}

		Object returnObject = new JavaOclEnumLiteral(adaptableObject);
		if (logger.isDebugEnabled()) {
			logger.debug("getOclEnumLiteral(Object) - end - return value="
					+ returnObject);
		}
		return returnObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.base.AbstractStandardlibraryAdapterFactory#getOclEnumType(java.lang.Object)
	 */
	@Override
	protected Object getOclEnumType(Object adaptableObject) {
		if (logger.isDebugEnabled()) {
			logger.debug("getOclEnumType(Object) - start");
		}

		if (adaptableObject instanceof Class) {
			Object returnObject = new JavaOclEnumType((Class) adaptableObject);
			if (logger.isDebugEnabled()) {
				logger.debug("getOclEnumType(Object) - end - return value="
						+ returnObject);
			}
			return returnObject;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getOclEnumType(Object) - end - return value=" + null);
		}
		return null;
	}
}
