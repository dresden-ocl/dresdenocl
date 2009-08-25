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

import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceTypeObject;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
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
	 *FIXME Claas: Ask Micha: Shouldn't we add primitive types and collections to
	 * the instance. Since they have a type now, that wouldn't be a problem.
	 * 
	 * @param object
	 *          the object to add to the {@link IModelInstance}
	 * @return The adapted object or <code>null</code> if the given {@link Object}
	 *         could not be adapted.
	 */
	IModelInstanceElement addModelInstanceElement(Object object);

	/**
	 * <p>
	 * Searches in this {@link IModelInstance} for a implementation of the given
	 * {@link EnumerationLiteral}.
	 * </p>
	 * 
	 * @param literal
	 *          The {@link EnumerationLiteral} whose implementation shall be
	 *          found.
	 * @return The found {@link IModelInstanceEnumerationLiteral} or
	 *         <code>null</code>.
	 */
	IModelInstanceEnumerationLiteral findEnumerationLiteral(
			EnumerationLiteral literal);

	/**
	 * <p>
	 * Searches for and probably returns the {@link IModelInstanceTypeObject} of a
	 * given {@link Type} if the given {@link Type} is implemented by an
	 * {@link IModelInstanceTypeObject} in this {@link IModelInstance}.
	 * </p>
	 * 
	 * @param type
	 *          The {@link Type} that shall be found.
	 * @return The found {@link IModelInstanceTypeObject} or <code>null</code>.
	 */
	public IModelInstanceTypeObject findModelTypeObject(Type type);

	/**
	 * <p>
	 * Gets all {@link IModelInstanceObject}s of the {@link IModelInstance}.
	 * </p>
	 * 
	 * <p>
	 * FIXME Probably: <strong>Please note, that primitive types, enumeration
	 * literals and collections are not returned by this operation.</strong>
	 * </p>
	 * 
	 * @return the {@link IModelInstanceElement}s for this model instance.
	 */
	List<IModelInstanceElement> getAllElements();

	/**
	 * <p>
	 * Returns a {@link Set} of all {@link Type}s that are at least implemented by
	 * one {@link IModelInstanceElement} of this {@link IModelInstance}.
	 * </p>
	 * 
	 * <p>
	 * FIXME: Change this probably: <strong>Please note, that the result will not
	 * contain primitive types nor collection types.</strong>
	 * </p>
	 * 
	 * @return A {@link Set} of all {@link Type}s that are at least implemented by
	 *         one {@link IModelInstanceElement} of this {@link IModelInstance}.
	 */
	Set<Type> getAllImplementedTypes();

	/**
	 * <p>
	 * Returns all {@link IModelInstanceObject}s of the given {@link Type}. If the
	 * given {@link Type} does not exist in the {@link IModel} of this
	 * {@link IModelInstance}, <code>null</code> is returned.
	 * </p>
	 * 
	 * <p>
	 * <strong>Note: This operation can be really expensive!</strong> Try avoiding
	 * it at any rate.
	 * </p>
	 * 
	 * <p>
	 * <strong>Note: This operation will only work for Types defined in the
	 * {@link IModel}!</strong> For {@link PrimitiveType}s, {@link CollectionType}
	 * s, or {@link Enumeration}s, the result will be <code>null</code>, whether
	 * or not, they have instances in the {@link IModelInstanceElement}.
	 * </p>
	 * 
	 * @param type
	 *          the {@link Type} of which all instances should be returned
	 * @return All adapted instances of the given {@link Type} or
	 *         <code>null</code> if the {@link Type} does not belong to the
	 *         {@link IModelInstance}'s {@link IModel}.
	 */
	Set<IModelInstanceObject> getAllInstances(Type type);

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
	 * {@link IModelInstanceElement}s of this {@link IModelInstance}. This method
	 * is required to adapt collection content lazily during the work of the
	 * standard library.
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
	 * FIXME Claas: Exceptions?
	 * 
	 * @param property
	 *          the {@link Property} is used to determine the name of the
	 *          property, the {@link Type} providing the static property, and the
	 *          {@link Type} of the fetched property. If
	 *          {@link Property#isMultiple()} is <code>true</code> create an
	 *          {@link IModelInstanceCollection} based on
	 *          {@link Property#isOrdered()} and {@link Property#isUnique()}.
	 * @return the adapted property value
	 */
	IModelInstanceElement getStaticProperty(Property property);

	/**
	 * <p>
	 * Invokes a static operation on the given type with the given arguments.
	 * </p>
	 * 
	 * FIXME Claas: Exceptions?
	 * 
	 * @param operation
	 *          the {@link Operation} is used to determine the name of the static
	 *          operation, the {@link Type} on which the operation shall be
	 *          invoked, and the {@link Type return type} of the invoked
	 *          operation. If {@link Operation#isMultiple()} is <code>true</code>
	 *          create an {@link IModelInstanceCollection} based on
	 *          {@link Operation#isOrdered()} and {@link Operation#isUnique()}.
	 * @param args
	 *          the arguments of the static operation
	 * @return the adapted return value of the static operation invocation
	 */
	IModelInstanceElement invokeStaticOperation(Operation operation,
			List<IModelInstanceElement> args);

	/**
	 * <p>
	 * Checks whether or not this {@link IModelInstance} is an instance of a given
	 * {@link IModel}.
	 * </p>
	 * 
	 * @param aModel
	 *          The {@link IModel} to which the instance of relationship shall be
	 *          checked.
	 * @return <code>True</code>, if this {@link IModelInstance} is an instance of
	 *         the given {@link IModel}. Else <code>false</code>.
	 */
	boolean isInstanceOf(IModel aModel);

	/**
	 * <p>
	 * Maps OCL operation names to standard library operation names.
	 * </p>
	 * 
	 * FIXME Claas: Ask Micha why this operation is required. Shouldn't be this a
	 * task of the standard library itself?
	 * 
	 * @param name
	 *          the name of the operation
	 * @param operatorCount
	 *          the operator count
	 * 
	 * @return the standardlibrary operation name
	 */
	String getOperationName(String name, int operatorCount);
}