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
package tudresden.ocl20.pivot.modelbus.modelinstance;

import java.util.List;
import java.util.Set;

import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstancePrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Represents an instance of an {@link IModel}.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public interface IModelInstance {

	/**
	 * <p>
	 * Adds a not adapted object to the {@link IModelInstance}. Before it is
	 * added, the given object is adapted.
	 * </p>
	 * 
	 * <p>
	 * <strong>Please be aware, that only {@link Object}s that are adapted to
	 * {@link IModelInstanceObject}s are added!</strong> Neither
	 * {@link IModelInstancePrimitiveType}s nor {@link IModelInstanceCollection}
	 * s nor {@link IModelInstanceEnumerationLiteral} are added to the
	 * {@link IModelInstance}'s element list. Although they are adapted as well.
	 * </p>
	 * 
	 * @param object
	 *            the object to add to the {@link IModelInstance}
	 * @return The adapted object or <code>null</code> if the given
	 *         {@link Object} could not be adapted.
	 * @throws TypeNotFoundInModelException
	 *             Thrown if a given Object, cannot be adapted to a {@link Type}
	 *             in the {@link IModel}.
	 */
	IModelInstanceElement addModelInstanceElement(Object object)
			throws TypeNotFoundInModelException;

	/**
	 * <p>
	 * Returns a {@link Set} of all {@link Type}s that are at least implemented
	 * by one {@link IModelInstanceElement} of this {@link IModelInstance}.
	 * </p>
	 * 
	 * <p>
	 * <strong>Please be aware, that only {@link Type}s that represent
	 * {@link Object}s that are adapted to {@link IModelInstanceObject}s are
	 * added!</strong> Neither {@link IModelInstancePrimitiveType}'s nor
	 * {@link IModelInstanceCollection}'s nor
	 * {@link IModelInstanceEnumerationLiteral}'s {@link Type}s will be part of
	 * the result.
	 * </p>
	 * 
	 * @return A {@link Set} of all {@link Type}s that are at least implemented
	 *         by one {@link IModelInstanceElement} of this
	 *         {@link IModelInstance}.
	 */
	Set<Type> getAllImplementedTypes();

	/**
	 * <p>
	 * Returns all {@link IModelInstanceObject}s of the given {@link Type} and
	 * the {@link IModelInstanceObject}s of sub {@link Type}s of this
	 * {@link Type}. If the given {@link Type} cannot be found in the
	 * {@link IModel} of this {@link IModelInstance} or not
	 * {@link IModelInstanceObject} implementing the {@link Type} can be found,
	 * an empty {@link Set} will be returned.
	 * </p>
	 * 
	 * <p>
	 * <strong>Note: This operation can be really expensive!</strong> Try
	 * avoiding it at any rate.
	 * </p>
	 * 
	 * <p>
	 * <strong>Please be aware, that only {@link IModelInstanceObject}s are
	 * returned!</strong> Neither {@link IModelInstancePrimitiveType}s nor
	 * {@link IModelInstanceCollection}s nor
	 * {@link IModelInstanceEnumerationLiteral}s will be part of the result.
	 * </p>
	 * 
	 * @param type
	 *            the {@link Type} of which all instances should be returned
	 * @return All adapted instances of the given {@link Type}.
	 */
	Set<IModelInstanceObject> getAllInstances(Type type);

	/**
	 * <p>
	 * Gets all {@link IModelInstanceObject}s of the {@link IModelInstance}.
	 * </p>
	 * 
	 * <p>
	 * <strong>Please be aware, that only {@link Object}s that are adapted to
	 * {@link IModelInstanceObject}s are returned!</strong> Neither
	 * {@link IModelInstancePrimitiveType}s nor {@link IModelInstanceCollection}
	 * s nor {@link IModelInstanceEnumerationLiteral} will be part of the
	 * result.
	 * </p>
	 * 
	 * @return the {@link IModelInstanceElement}s for this model instance.
	 */
	List<IModelInstanceObject> getAllModelInstanceObjects();

	/**
	 * <p>
	 * Returns the display name of this {@link IModelInstance}.
	 * </p>
	 * 
	 * @return The display name of this {@link IModelInstance}.
	 */
	String getDisplayName();

	/**
	 * <p>
	 * Returns the {@link IModel} of this {@link IModelInstance}.
	 * </p>
	 * 
	 * @return The {@link IModel} of this {@link IModelInstance}.
	 */
	IModel getModel();

	/**
	 * <p>
	 * Returns the {@link IModelInstanceFactory} that is is used to adapt
	 * {@link IModelInstanceElement}s of this {@link IModelInstance}. This
	 * method is required to adapt collection content lazily during the work of
	 * the standard library.
	 * </p>
	 * 
	 * @return The {@link IModelInstanceFactory} that is is used to adapt
	 *         {@link IModelInstanceElement}s of this {@link IModelInstance}.
	 */
	IModelInstanceFactory getModelInstanceFactory();

	/**
	 * <p>
	 * Tries to fetch a static property of the given type with the given name.
	 * </p>
	 * 
	 * @param sourceType
	 *            The {@link Type} to which the static {@link Property} belongs.
	 * @param property
	 *            the {@link Property} is used to determine the name of the
	 *            property, the {@link Type} providing the static property, and
	 *            the {@link Type} of the fetched property. If
	 *            {@link Property#isMultiple()} is <code>true</code> create an
	 *            {@link IModelInstanceCollection} based on
	 *            {@link Property#isOrdered()} and {@link Property#isUnique()}.
	 * @return the adapted property value
	 * @throws PropertyAccessException
	 *             Thrown, if an {@link Exception} occurs during the
	 *             {@link Property} access in the adapted programming language.
	 * @throws PropertyNotFoundException
	 *             Thrown if the given {@link Property} cannot be found as a
	 *             static {@link Property}.
	 */
	IModelInstanceElement getStaticProperty(Property property)
			throws PropertyAccessException, PropertyNotFoundException;

	/**
	 * <p>
	 * Invokes a static operation on the given type with the given arguments.
	 * </p>
	 * 
	 * @param operation
	 *            the {@link Operation} is used to determine the name of the
	 *            static operation, the {@link Type} on which the operation
	 *            shall be invoked, and the {@link Type return type} of the
	 *            invoked operation. If {@link Operation#isMultiple()} is
	 *            <code>true</code> create an {@link IModelInstanceCollection}
	 *            based on {@link Operation#isOrdered()} and
	 *            {@link Operation#isUnique()}.
	 * @param args
	 *            the arguments of the static operation
	 * @return the adapted return value of the static operation invocation.
	 * @throws OperationAccessException
	 *             Thrown, if an {@link Exception} occurs during the
	 *             {@link Operation} invocation in the adapted programming
	 *             language.
	 * @throws OperationNotFoundException
	 *             Thrown if the given {@link Operation} cannot be found as a
	 *             static {@link Operation}.
	 */
	IModelInstanceElement invokeStaticOperation(Operation operation,
			List<IModelInstanceElement> args) throws OperationAccessException,
			OperationNotFoundException;

	/**
	 * <p>
	 * Checks whether or not this {@link IModelInstance} is an instance of a
	 * given {@link IModel}.
	 * </p>
	 * 
	 * @param aModel
	 *            The {@link IModel} to which the instance of relationship shall
	 *            be checked.
	 * @return <code>True</code>, if this {@link IModelInstance} is an instance
	 *         of the given {@link IModel}. Else <code>false</code>.
	 */
	boolean isInstanceOf(IModel aModel);
}