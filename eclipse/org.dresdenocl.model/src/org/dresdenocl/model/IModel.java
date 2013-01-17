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
package org.dresdenocl.model;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;

import org.dresdenocl.model.metamodel.IMetamodel;
import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * Represents a model imported into Dresden OCL2 for Eclipse.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public interface IModel {
	
	public Map<EObject, EObject> getAllMappings();
	
	public boolean addAllMappings(final Map<EObject, EObject> mappings);

	/**
	 * <p>
	 * Adds an {@link IModelListener} to this {@link IModel}.
	 * </p>
	 * 
	 * @param listener
	 *            The {@link IModelListener} to be added.
	 * @return <code>true</code> if the {@link IModelListener} has been added
	 *         successfully.
	 */
	public boolean addListener(IModelListener listener);

	/**
	 * <p>
	 * Can be used to close references resources of this {@link IModel} before
	 * it is removed from the DresdenOCL respository. This is f.e. sensible if
	 * {@link ResourceSet}s must be closed that may contain elements that do not
	 * belong to the {@link IModel}, e.g. parsed {@link Constraint}s.
	 * </p>
	 * 
	 * <p>
	 * If you call {@link IModel#getRootNamespace()} after invocation of
	 * {@link IModel#dispose()}, the {@link IModel} will be re-opened again.
	 * </p>
	 */
	public void dispose();

	/**
	 * <p>
	 * This operation allows to find a {@link Namespace} anywhere in the
	 * corresponding {@link IModel}. It is an additional operation defined in
	 * the OCL Specification, Section 12.12. The path name needs to be fully
	 * qualified. If no {@link Namespace} with this path name is found,
	 * <code>null</code> is returned. An empty path name results in the
	 * {@link #getRootNamespace()}.
	 * </p>
	 * 
	 * @param pathName
	 *            A fully qualified name identifying a {@link Namespace}.
	 * 
	 * @return A {@link Namespace} instance or <code>null</code>.
	 * 
	 * @throws ModelAccessException
	 *             If an error occurs when accessing the adapted model.
	 */
	Namespace findNamespace(List<String> pathName) throws ModelAccessException;

	/**
	 * <p>
	 * This operation allows to find a {@link Type} anywhere in the
	 * corresponding {@link IModel}. It is an additional operation defined in
	 * the OCL Specification, Section 12.12. The specification states that "the
	 * pathName need not be a fully qualified name (it may be), as long as it
	 * can uniquely identify the [type] somewhere in the [..] model. If a [type]
	 * name occurs more than once, it needs to be qualified with its owning
	 * [namespace] (recursively) until the qualified name is unique. If more
	 * than one [type] is found, the operation returns [null]" (UML-specific
	 * references in the quotation have been adapted to fit the Pivot Model).
	 * </p>
	 * 
	 * @param pathName
	 *            A path name that uniquely identifies a {@link Type}.
	 * 
	 * @return A {@link Type} instance or <code>null</code>.
	 * 
	 * @throws ModelAccessException
	 *             If an error occurs when accessing the adapted model.
	 */
	Type findType(List<String> pathName) throws ModelAccessException;

	/**
	 * <p>
	 * Returns all {@link Constraint}s that are contained in this {@link IModel}
	 * .
	 * </p>
	 * 
	 * @return All {@link Constraint}s that are contained in this {@link IModel}
	 *         .
	 * @throws ModelAccessException
	 *             Thrown, if the given {@link IModel} is in an invalid state.
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
	 * Returns the {@link IMetamodel} for this {@link IModel}.
	 * </p>
	 * 
	 * @return An {@link IMetamodel} instance.
	 */
	IMetamodel getMetamodel();

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
	 *             If the root {@link Namespace} cannot be retrieved from the
	 *             {@link IModel}.
	 */
	Namespace getRootNamespace() throws ModelAccessException;

	/**
	 * <p>
	 * Returns <code>true</code> if this {@link IModel} changed since notifying
	 * its {@link IModelListener} for the last time.
	 * </p>
	 * 
	 * @return <code>true</code> if this {@link IModel} changed since notifying
	 *         its {@link IModelListener} for the last time.
	 */
	public boolean hasChanged();

	/**
	 * <p>
	 * Notifies the {@link IModelListener}s of this {@link IModel} that the
	 * {@link IModel} changed. <strong>The {@link IModelListener}s will only be
	 * modified, if the {@link IModel#setChanged()} method has been called
	 * before!
	 * </p>
	 * 
	 * @return <code>true</code> if the {@link IModelListener}s have been
	 *         notified. Else <code>false</code>.
	 */
	public boolean notifiyListeners();

	/**
	 * <p>
	 * Removes all {@link Constraint}s from this {@link IModel}. If the
	 * {@link Constraint}s define new {@link Property}s or {@link Operation}s,
	 * these elements are removed as well.
	 * </p>
	 * 
	 * @return <code>true</code> if all {@link Constraint}s have been removed.
	 * @throws IllegalArgumentException
	 *             Thrown, if the given parameter is invalid.
	 * @throws ModelAccessException
	 *             Thrown, if the given {@link IModel} is in an invalid state.
	 */
	public boolean removeAllConstraints() throws IllegalArgumentException,
			ModelAccessException;

	/**
	 * <p>
	 * Removes all {@link Constraint}s from this {@link IModel} that are
	 * contained in a given {@link Collection}. If the {@link Constraint}s
	 * define new {@link Property}s or {@link Operation}s, these elements are
	 * removed as well. <strong>Attention: This can lead to inconsistencies if
	 * other constraints refer to the define features that have been
	 * removed!</strong>
	 * </p>
	 * 
	 * @param constraints
	 *            The {@link Constraint}s that shall be removed.
	 * @return <code>true</code> if the {@link Constraint}s have been removed.
	 * @throws IllegalArgumentException
	 *             Thrown, if the given parameter is invalid.
	 * @throws ModelAccessException
	 *             Thrown, if the given {@link IModel} is in an invalid state.
	 */
	public boolean removeConstraints(Collection<Constraint> constraints)
			throws IllegalArgumentException, ModelAccessException;

	/**
	 * <p>
	 * Removes an {@link IModelListener} from this {@link IModel}.
	 * </p>
	 * 
	 * @param listener
	 *            The {@link IModelListener} to be removed.
	 * @return <code>true</code> if the {@link IModelListener} has been removed
	 *         successfully.
	 */
	public boolean removeListener(IModelListener listener);

	/**
	 * <p>
	 * This method can be called by a tool working on this model to inform the
	 * {@link IModel} that the tools has modified the {@link IModel}. The
	 * {@link IModel} will remain marked as changed until the method
	 * {@link IModel#notifiyListeners()} has been called.
	 * </p>
	 */
	public void setChanged();
}