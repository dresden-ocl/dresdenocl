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
package tudresden.ocl20.pivot.modelbus;

import java.util.List;
import java.util.Set;

import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceTypeObject;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
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
	 * Returns the {@link IModel} of this {@link IModelInstance}.
	 * </p>
	 * 
	 * @return The {@link IModel} of this {@link IModelInstance}.
	 */
	IModel getModel();

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
	 * Searches in this {@link IModelInstance} for a implementation of the given
	 * {@link EnumerationLiteral}.
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
	 * Maps OCL operation names to standardlibrary operation names.
	 * 
	 * @param name
	 *          the name of the operation
	 * @param operatorCount
	 *          the operator count
	 * 
	 * @return the standardlibrary operation name
	 */
	String getOperationName(String name, int operatorCount);

	/**
	 * Gets all objects of the model instance.
	 * 
	 * @return the {@link IModelObject}s for this model instance
	 */
	List<IModelObject> getObjects();

	/**
	 * Gets all objects of an specific type of the model instance.
	 * 
	 * @param typePath
	 *          the type path for the object type
	 * 
	 * @return the {@link IModelObject}s for this model instance
	 */
	List<IModelObject> getObjectsOfType(List<String> typePath);

	/**
	 * Gets the object kinds.
	 * 
	 * @return the available types of {@link IModelObject}s for this model
	 *         instance.
	 */
	Set<Type> getObjectTypes();

	/**
	 * Gets the display name.
	 * 
	 * @return the display name
	 */
	String getDisplayName();
}
