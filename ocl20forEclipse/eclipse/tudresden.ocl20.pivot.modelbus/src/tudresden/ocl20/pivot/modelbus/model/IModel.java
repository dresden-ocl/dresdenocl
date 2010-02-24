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
package tudresden.ocl20.pivot.modelbus.model;

import java.util.Collection;
import java.util.List;

import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.metamodel.IMetamodel;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Represents a model imported into Dresden OCL2 for Eclipse.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public interface IModel {

	/**
	 * <p>
	 * This operation allows to find a {@link Namespace} anywhere in the
	 * corresponding {@link IModel}. It is an additional operation defined in the
	 * OCL Specification, Section 12.12. The path name needs to be fully
	 * qualified. If no {@link Namespace} with this path name is found,
	 * <code>null</code> is returned. An empty path name results in the
	 * {@link #getRootNamespace()}.
	 * </p>
	 * 
	 * @param pathName
	 *          A fully qualified name identifying a {@link Namespace}.
	 * 
	 * @return A {@link Namespace} instance or <code>null</code>.
	 * 
	 * @throws ModelAccessException
	 *           If an error occurs when accessing the adapted model.
	 */
	Namespace findNamespace(List<String> pathName) throws ModelAccessException;

	/**
	 * <p>
	 * This operation allows to find a {@link Type} anywhere in the corresponding
	 * {@link IModel}. It is an additional operation defined in the OCL
	 * Specification, Section 12.12. The specification states that "the pathName
	 * need not be a fully qualified name (it may be), as long as it can uniquely
	 * identify the [type] somewhere in the [..] model. If a [type] name occurs
	 * more than once, it needs to be qualified with its owning [namespace]
	 * (recursively) until the qualified name is unique. If more than one [type]
	 * is found, the operation returns [null]" (UML-specific references in the
	 * quotation have been adapted to fit the Pivot Model).
	 * </p>
	 * 
	 * @param pathName
	 *          A path name that uniquely identifies a {@link Type}.
	 * 
	 * @return A {@link Type} instance or <code>null</code>.
	 * 
	 * @throws ModelAccessException
	 *           If an error occurs when accessing the adapted model.
	 */
	Type findType(List<String> pathName) throws ModelAccessException;

	/**
	 * <p>
	 * Returns all {@link Constraint}s that are contained in this {@link IModel}.
	 * </p>
	 * 
	 * @return All {@link Constraint}s that are contained in this {@link IModel}.
	 * @throws ModelAccessException
	 *           Thrown, if the given {@link IModel} is in an invalid state.
	 */
	public Collection<Constraint> getConstraints() throws ModelAccessException;

	/**
	 * <p>
	 * Returns a name that can be used to render a visual representation of the
	 * model. This may be the file name or another characteristic string.
	 * </p>
	 * 
	 * @return A {@link String} that identifies this {@link IModel}.
	 */
	String getDisplayName();

	/**
	 * <p>
	 * Returns the {@link IModelFactory} of this {@link IModel}.
	 * </p>
	 * 
	 * @return The {@link IModelFactory} of this {@link IModel}.
	 */
	IModelFactory getFactory();

	/**
	 * <p>
	 * Returns the {@link IMetamodel} for this {@link IModel}.
	 * </p>
	 * 
	 * @return An {@link IMetamodel} instance.
	 */
	IMetamodel getMetamodel();

	/**
	 * <p>
	 * Returns the {@link IOclLibraryProvider} of this {@link IModel}.
	 * </p>
	 * 
	 * @return The {@link IOclLibraryProvider} of this {@link IModel}.
	 */
	IOclLibraryProvider getOclLibraryProvider();

	/**
	 * <p>
	 * Returns the root {@link Namespace} of the {@link IModel}. This
	 * {@link Namespace} represents the root of the model hierarchy. It is not
	 * itself part of the {@link IModel}, but merely serves as a container for
	 * other model elements (including other {@link Namespace}s and {@link Type}
	 * s). In particular, this {@link Namespace} is not taken into account when
	 * looking for {@link Namespace} and {@link Type}s using
	 * {@link #findNamespace(List)} or {@link #findType(List)}, respectively.
	 * </p>
	 * 
	 * @return A {@link Namespace} instance.
	 * 
	 * @throws ModelAccessException
	 *           If the root {@link Namespace} cannot be retrieved from the
	 *           {@link IModel}.
	 */
	Namespace getRootNamespace() throws ModelAccessException;

	/**
	 * <p>
	 * Returns a {@link ITypeResolver} object that can be used to find types in
	 * the OCL standard library and in this {@link IModel}.
	 * </p>
	 * 
	 * @return An {@link ITypeResolver} instance.
	 */
	ITypeResolver getTypeResolver();

	/**
	 * <p>
	 * Removes all {@link Constraint}s from this {@link IModel}. If the
	 * {@link Constraint}s define new {@link Property}s or {@link Operation}s,
	 * these elements are removed as well.
	 * </p>
	 * 
	 * @return <code>true</code> if all {@link Constraint}s have been removed.
	 * @throws IllegalArgumentException
	 *           Thrown, if the given parameter is invalid.
	 * @throws ModelAccessException
	 *           Thrown, if the given {@link IModel} is in an invalid state.
	 */
	public boolean removeAllConstraints() throws IllegalArgumentException,
			ModelAccessException;

	/**
	 * <p>
	 * Removes all {@link Constraint}s from this {@link IModel} that are contained
	 * in a given {@link Collection}. If the {@link Constraint}s define new
	 * {@link Property}s or {@link Operation}s, these elements are removed as
	 * well. <strong>Attention: This can lead to inconsistencies if other
	 * constraints refer to the define features that have been removed!</strong>
	 * </p>
	 * 
	 * @param constraints
	 *          The {@link Constraint}s that shall be removed.
	 * @return <code>true</code> if the {@link Constraint}s have been removed.
	 * @throws IllegalArgumentException
	 *           Thrown, if the given parameter is invalid.
	 * @throws ModelAccessException
	 *           Thrown, if the given {@link IModel} is in an invalid state.
	 */
	public boolean removeConstraints(Collection<Constraint> constraints)
			throws IllegalArgumentException, ModelAccessException;
}