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
package tudresden.ocl20.pivot.essentialocl.standardlibrary.base;

import java.util.Collection;

import org.eclipse.core.runtime.Platform;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumLiteral;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInvalid;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclObject;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclPrimitiveType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclTuple;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclVoid;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.StandardlibraryAdapterFactory;

/**
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public abstract class AbstractStandardlibraryAdapterFactory implements
		StandardlibraryAdapterFactory {

	// The supported types
	protected static Class[] SUPPORTED_TYPES = new Class[] { OclInteger.class,
			OclReal.class, OclSet.class, OclBag.class, OclSequence.class,
			OclOrderedSet.class, OclInvalid.class, OclVoid.class,
			OclBoolean.class, OclString.class, OclObject.class, OclRoot.class,
			OclType.class, OclPrimitiveType.class, OclTuple.class,
			OclEnumLiteral.class, OclEnumType.class };

	// Has the factory been registered
	private boolean registered = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.StandardlibraryAdapterFactory#registerAdapters()
	 */
	public void registerAdapters() {
		Platform.getAdapterManager().unregisterAdapters(this);
		Platform.getAdapterManager().registerAdapters(this, Integer.class);
		Platform.getAdapterManager().registerAdapters(this, Float.class);
		Platform.getAdapterManager().registerAdapters(this, Collection.class);
		Platform.getAdapterManager().registerAdapters(this, String.class);
		Platform.getAdapterManager().registerAdapters(this, Boolean.class);
		Platform.getAdapterManager().registerAdapters(this, Object.class);
		Platform.getAdapterManager().registerAdapters(this, Class.class);
		registered = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.StandardlibraryAdapterFactory#unregisterAdapters()
	 */
	public void unregisterAdapters() {
		Platform.getAdapterManager().unregisterAdapters(this);
		registered = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.StandardlibraryAdapterFactory#isRegistered()
	 */
	public boolean isRegistered() {
		return registered;
	}

	/**
	 * The adaption for the types is delegated to individual methods that have
	 * to be overwritten
	 * 
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object,
	 *      java.lang.Class)
	 */
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		Object result = null;
		if (OclVoid.class.equals(adapterType))
			result = getOclVoid(adaptableObject);
		else if (OclInvalid.class.equals(adapterType))
			result = getOclInvalid(adaptableObject);
		else if (OclInteger.class.equals(adapterType))
			result = getOclInteger(adaptableObject);
		else if (OclReal.class.equals(adapterType))
			result = getOclReal(adaptableObject);
		else if (OclString.class.equals(adapterType))
			result = getOclString(adaptableObject);
		else if (OclBoolean.class.equals(adapterType))
			result = getOclBoolean(adaptableObject);
		else if (OclSet.class.equals(adapterType))
			result = getOclSet(adaptableObject);
		else if (OclBag.class.equals(adapterType))
			result = getOclBag(adaptableObject);
		else if (OclSequence.class.equals(adapterType))
			result = getOclSequence(adaptableObject);
		else if (OclOrderedSet.class.equals(adapterType))
			result = getOclOrderedSet(adaptableObject);
		else if (OclObject.class.equals(adapterType))
			result = getOclObject(adaptableObject);
		else if (OclType.class.equals(adapterType))
			result = getOclType(adaptableObject);
		else if (OclPrimitiveType.class.equals(adapterType))
			result = getOclPrimitiveType(adaptableObject);
		else if (OclTuple.class.equals(adapterType))
			result = getOclTuple(adaptableObject);
		else if (OclEnumLiteral.class.equals(adapterType))
			result = getOclEnumLiteral(adaptableObject);
		else if (OclEnumType.class.equals(adapterType))
			result = getOclEnumType(adaptableObject);
		else if (OclRoot.class.equals(adapterType))
			result = getOclRoot(adaptableObject);

		if (result == null) {
			result = adapterNotFound(adaptableObject);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
	 */
	public Class[] getAdapterList() {
		return SUPPORTED_TYPES;
	}

	/**
	 * No specific adapter has been found.
	 * 
	 * @param adaptableObject
	 *            the adaptable object
	 * 
	 * @return the object
	 */
	abstract protected Object adapterNotFound(Object adaptableObject);

	/**
	 * Gets OCL tuple for adaptable object.
	 * 
	 * @param adaptableObject
	 *            the adaptable object
	 * 
	 * @return the ocl tuple
	 */
	abstract protected Object getOclTuple(Object adaptableObject);

	/**
	 * Gets OCL primitive type for adaptable object.
	 * 
	 * @param adaptableObject
	 *            the adaptable object
	 * 
	 * @return the ocl primitive type
	 */
	abstract protected Object getOclPrimitiveType(Object adaptableObject);

	/**
	 * Gets OCL type for adaptable object.
	 * 
	 * @param adaptableObject
	 *            the adaptable object
	 * 
	 * @return the ocl type
	 */
	abstract protected Object getOclType(Object adaptableObject);

	/**
	 * Gets OCL object for adaptable object.
	 * 
	 * @param adaptableObject
	 *            the adaptable object
	 * 
	 * @return the ocl object
	 */
	abstract protected Object getOclObject(Object adaptableObject);

	/**
	 * Gets OCL ordered set for adaptable object.
	 * 
	 * @param adaptableObject
	 *            the adaptable object
	 * 
	 * @return the ocl ordered set
	 */
	abstract protected Object getOclOrderedSet(Object adaptableObject);

	/**
	 * Gets OCL sequence for adaptable object.
	 * 
	 * @param adaptableObject
	 *            the adaptable object
	 * 
	 * @return the ocl sequence
	 */
	abstract protected Object getOclSequence(Object adaptableObject);

	/**
	 * Gets OCL bag for adaptable object.
	 * 
	 * @param adaptableObject
	 *            the adaptable object
	 * 
	 * @return the ocl bag
	 */
	abstract protected Object getOclBag(Object adaptableObject);

	/**
	 * Gets OCL set for adaptable object.
	 * 
	 * @param adaptableObject
	 *            the adaptable object
	 * 
	 * @return the ocl set
	 */
	abstract protected Object getOclSet(Object adaptableObject);

	/**
	 * Gets OCL boolean for adaptable object.
	 * 
	 * @param adaptableObject
	 *            the adaptable object
	 * 
	 * @return the ocl boolean
	 */
	abstract protected Object getOclBoolean(Object adaptableObject);

	/**
	 * Gets OCL string for adaptable object.
	 * 
	 * @param adaptableObject
	 *            the adaptable object
	 * 
	 * @return the ocl string
	 */
	abstract protected Object getOclString(Object adaptableObject);

	/**
	 * Gets OCL integer for adaptable object.
	 * 
	 * @param adaptableObject
	 *            the adaptable object
	 * 
	 * @return the ocl integer
	 */
	abstract protected Object getOclInteger(Object adaptableObject);

	/**
	 * Gets OCL invalid for adaptable object.
	 * 
	 * @param adaptableObject
	 *            the adaptable object
	 * 
	 * @return the ocl invalid
	 */
	abstract protected Object getOclInvalid(Object adaptableObject);

	/**
	 * Gets  for adaptable object void for adaptable object.
	 * 
	 * @param adaptableObject
	 *            the adaptable object
	 * 
	 * @return the ocl void
	 */
	abstract protected Object getOclVoid(Object adaptableObject);

	/**
	 * Gets OCL root object for adaptable object.
	 * 
	 * @param adaptableObject
	 *            the adaptable object
	 * 
	 * @return the ocl root
	 */
	abstract protected Object getOclRoot(Object adaptableObject);

	/**
	 * Gets OCL real for adaptable object.
	 * 
	 * @param adaptableObject
	 *            the adaptable object
	 * 
	 * @return the ocl real
	 */
	abstract protected Object getOclReal(Object adaptableObject);

	/**
	 * Gets OCL enum literal for adaptable object.
	 * 
	 * @param adaptableObject
	 *            the adaptable object
	 * 
	 * @return the ocl enum literal
	 */
	abstract protected Object getOclEnumLiteral(Object adaptableObject);

	/**
	 * Gets OCL enum type for adaptable object.
	 * 
	 * @param adaptableObject
	 *            the adaptable object
	 * 
	 * @return the ocl enum type
	 */
	abstract protected Object getOclEnumType(Object adaptableObject);
}
