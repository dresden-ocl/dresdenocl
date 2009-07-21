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

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.StandardlibraryAdapterFactory;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.base.AbstractStandardlibraryAdapterFactory;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.Type;
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
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclSequence;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclSet;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclString;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclTuple;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclType;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclVoid;

/**
 * <p>
 * This class is a factory which provides methods to create instances of the
 * types of the OCL standard library.
 * </p>
 * 
 * <p>
 * This class implements the design pattern Singleton.
 * </p>
 * 
 * @author Ronny Brandt
 */
public class JavaStandardlibraryAdapterFactory extends
		AbstractStandardlibraryAdapterFactory implements
		StandardlibraryAdapterFactory {

	/** The Logger for this class. */
	private static final Logger logger =
			Logger.getLogger(JavaStandardlibraryAdapterFactory.class);

	/** The single instance of this class. */
	private static JavaStandardlibraryAdapterFactory MY_INSTANCE = null;

	/** The names of the integer types in Java. */
	private static String[] INTEGER_TYPES =
			new String[] { "java.lang.Integer", "java.lang.Short", "java.lang.Long",
					"int", "short", "long" };

	/** The names of the real types in Java. */
	private static String[] REAL_TYPES =
			new String[] { "java.lang.Double", "java.lang.Float", "float", "double" };

	/**
	 * <p>
	 * Instantiates a new {@link JavaStandardlibraryAdapterFactory}.
	 * </p>
	 */
	private JavaStandardlibraryAdapterFactory() {

	}

	/**
	 * @return The single instance of {@link JavaStandardlibraryAdapterFactory}.
	 */
	public static JavaStandardlibraryAdapterFactory getInstance() {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getInstance() - start");
		}
		// no else.

		/* Eventually create the singleton instance. */
		if (MY_INSTANCE == null) {
			MY_INSTANCE = new JavaStandardlibraryAdapterFactory();
		}
		// no else.

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getInstance() - end - return value=" + MY_INSTANCE);
		}
		// no else.

		return MY_INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.base.
	 * AbstractStandardlibraryAdapterFactory#adapterNotFound(java.lang.Object)
	 */
	@Override
	protected Object adapterNotFound(Object adaptableObject) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("adapterNotFound(Object) - start");
		}
		// no else.

		Object result;

		result = getOclObject(adaptableObject);

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("adapterNotFound(Object) - end - return value=" + result);
		}
		// no else.
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.base.
	 * AbstractStandardlibraryAdapterFactory#getOclBag(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Object getOclBag(Object adaptableObject) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclBag(Object) - start");
		}
		// no else.

		OclRoot result;

		/*
		 * we do not want to adapt an object; instead create an instance that is
		 * undefined
		 */
		if (adaptableObject == null) {
			result = new JavaOclBag<OclRoot>(null);
			result.setUndefinedreason(result.getClass().getCanonicalName()
					+ " is undefined");
		}

		/* Check if the adaptableObject is a List. */
		else if (adaptableObject instanceof List) {
			result = new JavaOclBag<OclRoot>((List<OclRoot>) adaptableObject);
		}
		/* Else return null. */
		else {
			result = null;
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclBag(Object) - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.base.
	 * AbstractStandardlibraryAdapterFactory#getOclBoolean(java.lang.Object)
	 */
	@Override
	protected Object getOclBoolean(Object adaptableObject) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclBoolean(Object) - start");
		}
		// no else.

		OclRoot result;

		/*
		 * we do not want to adapt an object; instead create an instance that is
		 * undefined
		 */
		if (adaptableObject == null) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(result.getClass().getCanonicalName()
					+ " is undefined");
		}

		/* Check if the adaptableObject is a boolean. */
		else if (adaptableObject instanceof Boolean) {
			result = JavaOclBoolean.getInstance((Boolean) adaptableObject);
		}
		/* Else return null. */
		else {
			result = null;
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclBoolean(Object) - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.base.
	 * AbstractStandardlibraryAdapterFactory#getOclEnumLiteral(java.lang.Object)
	 */
	@Override
	protected Object getOclEnumLiteral(Object adaptableObject) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclEnumLiteral(Object) - start");
		}
		// no else.

		OclRoot result;

		/*
		 * we do not want to adapt an object; instead create an instance that is
		 * undefined
		 */
		if (adaptableObject == null) {
			result = new JavaOclEnumLiteral(null);
			result.setUndefinedreason(result.getClass().getCanonicalName()
					+ " is undefined");
		}
		else
			result = new JavaOclEnumLiteral(adaptableObject);

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclEnumLiteral(Object) - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.base.
	 * AbstractStandardlibraryAdapterFactory#getOclEnumType(java.lang.Object)
	 */
	@Override
	protected Object getOclEnumType(Object adaptableObject) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclEnumType(Object) - start");
		}
		// no else.

		OclRoot result;

		/*
		 * we do not want to adapt an object; instead create an instance that is
		 * undefined
		 */
		if (adaptableObject == null) {
			result = new JavaOclEnumType(null);
			result.setUndefinedreason(result.getClass().getCanonicalName()
					+ " is undefined");
		}

		/* Check if the adaptableObject is a class. */
		else if (adaptableObject instanceof Class) {
			result = new JavaOclEnumType((Class<?>) adaptableObject);
		}

		/* Else check if the adaptableObject is a meta model element. */
		else if (adaptableObject instanceof Enumeration) {
			result =
					JavaOclEnumType.getType(((Enumeration) adaptableObject).getName());
		}

		/* Else return null. */
		else {
			result = null;
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclEnumType(Object) - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.base.
	 * AbstractStandardlibraryAdapterFactory#getOclInteger(java.lang.Object)
	 */
	@Override
	protected Object getOclInteger(Object adaptableObject) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclInteger(Object) - start");
		}
		// no else.

		OclRoot result;

		/*
		 * we do not want to adapt an object; instead create an instance that is
		 * undefined
		 */
		if (adaptableObject == null) {
			result = new JavaOclInteger(null);
			result.setUndefinedreason(result.getClass().getCanonicalName()
					+ " is undefined");
		}

		/* Check if the adaptableObject is an integer. */
		else if (adaptableObject instanceof Integer) {
			result = new JavaOclInteger((Integer) adaptableObject);
		}
		/* Else return null. */
		else {
			result = null;
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclInteger(Object) - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.base.
	 * AbstractStandardlibraryAdapterFactory#getOclInvalid(java.lang.Object)
	 */
	@Override
	protected Object getOclInvalid(Object adaptableObject) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclInvalid(Object) - start");
		}
		// no else.

		Object result;

		result = JavaOclInvalid.getInstance();

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclInvalid(Object) - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.base.
	 * AbstractStandardlibraryAdapterFactory#getOclObject(java.lang.Object)
	 */
	@Override
	protected Object getOclObject(Object adaptableObject) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclObject(Object) - start");
		}
		// no else.

		OclRoot result;

		/*
		 * we do not want to adapt an object; instead create an instance that is
		 * undefined
		 */
		if (adaptableObject == null) {
			result = new JavaOclObject(null);
			result.setUndefinedreason(result.getClass().getCanonicalName()
					+ " is undefined");
		}
		else
			result = new JavaOclObject(adaptableObject);

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclObject(Object) - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.base.
	 * AbstractStandardlibraryAdapterFactory#getOclOrderedSet(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Object getOclOrderedSet(Object adaptableObject) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclOrderedSet(Object) - start");
		}
		// no else.

		OclRoot result;

		/*
		 * we do not want to adapt an object; instead create an instance that is
		 * undefined
		 */
		if (adaptableObject == null) {
			result = new JavaOclOrderedSet<OclRoot>(null);
			result.setUndefinedreason(result.getClass().getCanonicalName()
					+ " is undefined");
		}

		/* Check if adaptableObject is a List. */
		else if (adaptableObject instanceof List) {
			result = new JavaOclOrderedSet<OclRoot>((List<OclRoot>) adaptableObject);
		}
		/* Else return null. */
		else {
			result = null;
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclOrderedSet(Object) - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.base.
	 * AbstractStandardlibraryAdapterFactory
	 * #getOclPrimitiveType(java.lang.Object)
	 */
	@Override
	protected Object getOclPrimitiveType(Object adaptableObject) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclPrimitiveType(Object) - start");
		}
		// no else.

		OclRoot result;

		/*
		 * we do not want to adapt an object; instead create an instance that is
		 * undefined
		 */
		if (adaptableObject == null) {
			result = JavaOclVoid.getInstance();
		}

		/* Check if the adaptableObject is a String. */
		else if (adaptableObject instanceof String) {
			result = JavaOclPrimitiveType.getPrimitiveType((String) adaptableObject);
		}

		/* Else check if the adaptableObject is a meta model element. */
		else if (adaptableObject instanceof PrimitiveType) {
			result =
					JavaOclPrimitiveType
							.getPrimitiveType(((PrimitiveType) adaptableObject).getName());
		}

		/* Else return null. */
		else {
			result = null;
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger
					.debug("getOclPrimitiveType(Object) - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.base.
	 * AbstractStandardlibraryAdapterFactory#getOclReal(java.lang.Object)
	 */
	@Override
	protected Object getOclReal(Object adaptableObject) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclReal(Object) - start");
		}
		// no else.

		OclRoot result;

		/*
		 * we do not want to adapt an object; instead create an instance that is
		 * undefined
		 */
		if (adaptableObject == null) {
			result = new JavaOclReal(null);
			result.setUndefinedreason(result.getClass().getCanonicalName()
					+ " is undefined");
		}

		/* Check if the adaptableObject is a float. */
		else if (adaptableObject instanceof Float) {
			result = new JavaOclReal((Float) adaptableObject);
		}
		/* Else return null. */
		else {
			result = null;
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclReal(Object) - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.base.
	 * AbstractStandardlibraryAdapterFactory#getOclRoot(java.lang.Object)
	 */
	@Override
	protected Object getOclRoot(Object adaptableObject) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclRoot(Object) - start");
		}
		// no else.

		OclRoot result;

		/*
		 * we do not want to adapt an object; instead create an instance that is
		 * undefined
		 */
		if (adaptableObject == null) {
			result = new JavaOclRoot(null);
			result.setUndefinedreason(result.getClass().getCanonicalName()
					+ " is undefined");
		}

		/* Check if the adaptableObject is an integer. */
		else if (adaptableObject instanceof Integer) {
			result = new JavaOclInteger((Integer) adaptableObject);
		}

		/* Else check if the adaptableObject is a float. */
		else if (adaptableObject instanceof Float) {
			result = new JavaOclReal((Float) adaptableObject);
		}

		/* Else check if the adaptableObject is a string. */
		else if (adaptableObject instanceof String) {
			result = new JavaOclString((String) adaptableObject);
		}

		/* Else check if the adaptableObject is a boolean. */
		if (adaptableObject instanceof Boolean) {
			result = JavaOclBoolean.getInstance((Boolean) adaptableObject);
		}

		/* Else check if the adaptableObject is a list. */
		if (adaptableObject instanceof EList) {

			List<?> adaptableList;
			Set<OclRoot> adaptedSet;

			adaptableList = (List<?>) adaptableObject;

			adaptedSet = new HashSet<OclRoot>();

			/* Iterate through the adaptable objects. */
			for (Object anElement : adaptableList) {

				/* Add anElement or an adaption of anElement. */
				if (anElement instanceof OclRoot) {
					adaptedSet.add((OclRoot) anElement);
				}
				else {
					adaptedSet.add((OclRoot) getAdapter(anElement, OclRoot.class));
				}
			}

			/* Adapt result to OclSet. */
			result = (OclRoot) getAdapter(adaptedSet, OclSet.class);
		}

		/* Else check if the adaptableObject is a collection. */
		else if (adaptableObject instanceof Collection) {

			Collection<?> adaptableCollection;
			Set<OclRoot> adaptedSet;

			adaptableCollection = (Collection<?>) adaptableObject;

			adaptedSet = new HashSet<OclRoot>();

			/* Iterate through the adaptable objects. */
			for (Object anElement : adaptableCollection) {

				/* Add anElement or an adaption of anElement. */
				if (anElement instanceof OclRoot) {
					adaptedSet.add((OclRoot) anElement);
				}
				else {
					adaptedSet.add((OclRoot) getAdapter(anElement, OclRoot.class));
				}
			}

			/* Adapt result to OclSet. */
			result = (OclRoot) getAdapter(adaptedSet, OclSet.class);
		}

		/* Else return null. */
		else {
			result = null;
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclRoot(Object) - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.base.
	 * AbstractStandardlibraryAdapterFactory#getOclSet(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Object getOclSet(Object adaptableObject) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclSet(Object) - start");
		}
		// no else.

		OclRoot result;

		/*
		 * we do not want to adapt an object; instead create an instance that is
		 * undefined
		 */
		if (adaptableObject == null) {
			result = new JavaOclSet<OclRoot>(null);
			result.setUndefinedreason(result.getClass().getCanonicalName()
					+ " is undefined");
		}

		/* Check if the adaptableObject is a Set. */
		else if (adaptableObject instanceof Set) {
			result = new JavaOclSet<OclRoot>((Set<OclRoot>) adaptableObject);
		}

		/* Else check if the adaptableObject is a List. */
		else if (adaptableObject instanceof List) {
			Set<OclRoot> set;

			/* Convert the List into a Set. */
			set = new HashSet<OclRoot>((List<OclRoot>) adaptableObject);
			result = new JavaOclSet<OclRoot>(set);
		}

		/* Else return null. */
		else {
			result = null;
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclSet(Object) - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.base.
	 * AbstractStandardlibraryAdapterFactory#getOclSequence(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Object getOclSequence(Object adaptableObject) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclSequence(Object) - start");
		}
		// no else.

		OclRoot result;

		/*
		 * we do not want to adapt an object; instead create an instance that is
		 * undefined
		 */
		if (adaptableObject == null) {
			result = new JavaOclSequence<OclRoot>(null);
			result.setUndefinedreason(result.getClass().getCanonicalName()
					+ " is undefined");
		}

		/* Check if the adaptableObject is a List. */
		else if (adaptableObject instanceof List) {
			result = new JavaOclSequence<OclRoot>((List<OclRoot>) adaptableObject);
		}
		/* Else return null. */
		else {
			result = null;
		}

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclSequence(Object) - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.base.
	 * AbstractStandardlibraryAdapterFactory#getOclString(java.lang.Object)
	 */
	@Override
	protected Object getOclString(Object adaptableObject) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclString(Object) - start");
		}
		// no else.

		OclRoot result;

		/*
		 * we do not want to adapt an object; instead create an instance that is
		 * undefined
		 */
		if (adaptableObject == null) {
			result = new JavaOclString(null);
			result.setUndefinedreason(result.getClass().getCanonicalName()
					+ " is undefined");
		}

		/* Check if the adaptableObject is a string. */
		else if (adaptableObject instanceof String) {
			result = new JavaOclString((String) adaptableObject);
		}
		/* Else return null. */
		else {
			result = null;
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclString(Object) - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.base.
	 * AbstractStandardlibraryAdapterFactory#getOclTuple(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Object getOclTuple(Object adaptableObject) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclTuple(Object) - start");
		}
		// no else.

		OclRoot result;

		/*
		 * we do not want to adapt an object; instead create an instance that is
		 * undefined
		 */
		if (adaptableObject == null) {
			result = new JavaOclTuple(null);
			result.setUndefinedreason(result.getClass().getCanonicalName()
					+ " is undefined");
		}
		
		/* Check if the adatableObject is a Map. */
		else if (adaptableObject instanceof Map) {
			result = new JavaOclTuple((Map<String, OclRoot>) adaptableObject);
		}

		/* Else return null. */
		else {
			result = null;
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclTuple(Object) - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.base.
	 * AbstractStandardlibraryAdapterFactory#getOclType(java.lang.Object)
	 */
	@Override
	protected Object getOclType(Object adaptableObject) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclType(Object) - start");
		}
		// no else.

		OclRoot result;

		/*
		 * we do not want to adapt an object; instead create an instance that is
		 * undefined
		 */
		if (adaptableObject == null) {
			result = new JavaOclType(null);
			result.setUndefinedreason(result.getClass().getCanonicalName()
					+ " is undefined");
		}

		/* Check if the adaptableObject is a Class. */
		else if (adaptableObject instanceof Class) {

			/* Eventually adapt to String. */
			if (((Class<?>) adaptableObject).getName().equals("java.lang.String")) {
				result = JavaOclPrimitiveType.getPrimitiveType("String");
			}

			/* Eventually adapt to Integer. */
			else if (Arrays.asList(INTEGER_TYPES).contains(
					((Class<?>) adaptableObject).getName())) {
				result = JavaOclPrimitiveType.getPrimitiveType("Integer");
			}

			/* Eventually adapt to Real. */
			if (Arrays.asList(REAL_TYPES).contains(
					((Class<?>) adaptableObject).getName())) {
				result = JavaOclPrimitiveType.getPrimitiveType("Real");
			}

			/* Else adapt to OclType. */
			else {
				result = new JavaOclType((Class<?>) adaptableObject);
			}
		}

		/* Else check if the adaptableObject is a String. */
		else if (adaptableObject instanceof String) {
			Object returnObject = JavaOclType.getType((String) adaptableObject);

			if (logger.isDebugEnabled()) {
				logger.debug("getOclType(Object) - end - return value=" + returnObject);
			}
			return returnObject;
		}

		/* Else check if the adaptableObject is a meta model element. */
		else if (adaptableObject instanceof Type) {
			result = JavaOclType.getType(((Type) adaptableObject).getName());
		}

		/* Else return null. */
		else {
			result = null;
		}

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclType(Object) - end - return value=" + result);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.base.
	 * AbstractStandardlibraryAdapterFactory#getOclVoid(java.lang.Object)
	 */
	@Override
	protected Object getOclVoid(Object adaptableObject) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclVoid(Object) - start");
		}
		// no else.

		Object result;

		result = JavaOclVoid.getInstance();

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("getOclVoid(Object) - end - return value=" + result);
		}
		// no else.

		return result;
	}
}
