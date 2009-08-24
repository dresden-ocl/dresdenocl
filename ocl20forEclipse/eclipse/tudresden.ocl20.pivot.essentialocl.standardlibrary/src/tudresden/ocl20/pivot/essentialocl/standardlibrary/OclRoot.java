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
package tudresden.ocl20.pivot.essentialocl.standardlibrary;


/**
 * <code>OclRoot</code> is the base interface for the OCL Standard Library
 * type hierarchy. It defines common operations of {@link OclObject objects} and
 * {@link OclType types}. In essence, these provide the means to reflectively
 * retrieve property values and invoke operations. Note that classes realizing
 * this interface (or one of its sub-interfaces) always represent a
 * domain-specific implementation of the corresponding OCL concept. Thus, any
 * property values or operations return values have to be wrapped into a
 * corresponding <code>OclRoot</code> object again. <code>OclRoot</code>
 * extends {@link OclAdapter} to enable easy access to the adapted
 * domain-specific object.
 * 
 * @author Matthias Braeuer
 * @version 1.0 30.03.2007
 */
public interface OclRoot extends OclAdapter {

	/**
	 * Returns the value of the property with the given name.
	 * 
	 * @param propertyName
	 *            the name of the property
	 * 
	 * @return the value of the property as an <code>OclRoot</code>
	 */
	OclRoot getPropertyValue(String propertyName) throws NoSuchFieldException,
			IllegalAccessException;

	/**
	 * Returns the qualified property value with the given name. This is meant
	 * to support hashtable properties or the access to multi-valued properties
	 * via an index.
	 * 
	 * @param propertyName
	 *            the name of the property
	 * @param qualified
	 *            an array of qualifying objects
	 * 
	 * @return the value of the corresponding qualified property as an
	 *         <code>OclRoot</code>
	 */
	OclRoot getPropertyValue(String propertyName, OclRoot... qualifier)
			throws NoSuchFieldException, IllegalAccessException;

	/**
	 * @param propertyName
	 * @return
	 */
	OclSequence<OclRoot> getPropertyValueAsSequence(String propertyName)
			throws NoSuchFieldException, IllegalAccessException;

	/**
	 * @param propertyName
	 * @return
	 */
	OclBag<OclRoot> getPropertyValueAsBag(String propertyName)
			throws NoSuchFieldException, IllegalAccessException;

	/**
	 * @param propertyName
	 * @return
	 */
	OclSet<OclRoot> getPropertyValueAsSet(String propertyName)
			throws NoSuchFieldException, IllegalAccessException;

	/**
	 * @param propertyName
	 * @return
	 */
	OclOrderedSet<OclRoot> getPropertyValueAsOrderedSet(String propertyName)
			throws NoSuchFieldException, IllegalAccessException;

	/**
	 * @param operationName
	 * @param referredOperation
	 * @param parameters
	 * @return
	 */
	OclRoot invokeOperation(String operationName, OclRoot... parameters)
			throws NoSuchMethodException;

	/**
	 * 
	 * @param object2
	 * @return true if <code>self</code> is the same object as
	 *         <code>object2</code>.
	 */
	OclBoolean isEqualTo(OclRoot object2);

	/**
	 * 
	 * @param object2
	 * @return true if <code>this</code> is a different object from
	 *         <code>object2</code>.
	 */
	OclBoolean isNotEqualTo(OclRoot object2);

	/**
	 * Can only be used in a postcondition.
	 * 
	 * @return true if the this object is created during performing the
	 *         operation (for instance, it didn’t exist at precondition time).
	 */
	OclBoolean oclIsNew();

	/**
	 * 
	 * @return true if <code>this</code> is equal to OclInvalid or equal to
	 *         null.
	 */
	OclBoolean isOclUndefined();

	/**
	 * 
	 * @return true if <code>this</code> is equal to OclInvalid.
	 */
	OclBoolean oclIsInvalid();

	/**
	 * 
	 * @param typespec
	 * @return <code>this</code>, where <code>this</code> is of the type
	 *         identified by <code>typespec</code>.
	 */
	<T extends OclRoot> T oclAsType(OclType typespec);

	/**
	 * 
	 * @param typespec
	 * @return true if <code>this</code> is of the type identified by
	 *         <code>typespec</code>.
	 */
	OclBoolean oclIsTypeOf(OclType typespec);

	/**
	 * 
	 * @param typespec
	 * @return true if <code>this</code> conforms to the type identified by
	 *         <code>typespec</code>.
	 */
	OclBoolean oclIsKindOf(OclType typespec);

	/**
	 * May only be used for classifiers that have a finite number of instances.
	 * This is the case for, for instance, user defined classes because
	 * instances need to be created explicitly. This is not the case for, for
	 * instance, the standard String, Integer, and Real types.
	 * 
	 * @return all instances of <code>this</code>. Type of
	 *         <code>OclSet</code> is equal to <code>this</code>
	 */
	<T extends OclRoot> OclSet<T> allInstances();

	/**
	 * Sets the reason for undefined object.
	 * 
	 * @param undefinedreason
	 *            the new undefinedreason
	 */
	void setUndefinedreason(String undefinedreason);

	/**
	 * Gets the reason for undefined object.
	 * 
	 * @return the undefinedreason
	 */
	String getUndefinedreason();

	/**
	 * OCL operation asSet()
	 * 
	 * @return the ocl set
	 */
	<T extends OclRoot> OclSet<T> asSet();

	/**
	 * Gets the type of this object.
	 * 
	 * @return the type
	 */
	OclType getType();
}
