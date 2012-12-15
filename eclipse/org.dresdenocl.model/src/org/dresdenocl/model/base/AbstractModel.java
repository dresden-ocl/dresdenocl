/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de). * All rights
 * reserved. * * This work was done as a project at the Chair for Software
 * Technology, * Dresden University Of Technology, Germany
 * (http://st.inf.tu-dresden.de). * It is understood that any modification not
 * identified as such is not * covered by the preceding statement. * * This work
 * is free software; you can redistribute it and/or modify it * under the terms
 * of the GNU Library General Public License as published * by the Free Software
 * Foundation; either version 2 of the License, or * (at your option) any later
 * version. * * This work is distributed in the hope that it will be useful, but
 * WITHOUT * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Library General Public *
 * License for more details. * * You should have received a copy of the GNU
 * Library General Public License * along with this library; if not, you can
 * view it online at * http://www.fsf.org/licensing/licenses/gpl.html. * * To
 * submit a bug report, send a comment, or get the latest news on this *
 * project, please visit the website: http://dresden-ocl.sourceforge.net. * For
 * more information on OCL and related projects visit the OCL Portal: *
 * http://st.inf.tu-dresden.de/ocl * * * * * * * * * * * * * * * * * * * * * * *
 * * * * * * * * * * * * * * * * *
 * 
 * $Id$
 */
package org.dresdenocl.model.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import org.dresdenocl.model.IModel;
import org.dresdenocl.model.IModelListener;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.model.ModelConstants;
import org.dresdenocl.model.ModelPlugin;
import org.dresdenocl.model.metamodel.IMetamodel;
import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.PivotModelFactory;
import org.dresdenocl.pivotmodel.PrimitiveType;
import org.dresdenocl.pivotmodel.PrimitiveTypeKind;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * Abstract base implementation of the {@link IModel} interface.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public abstract class AbstractModel implements IModel {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = ModelPlugin
			.getLogger(AbstractModel.class);

	/** The {@link IModel}'s name as displayed to clients. */
	private String displayName;

	/**
	 * Indicates whether or not this {@link IModel} changed since notifying its
	 * {@link IModelListener}s for the last time.
	 */
	private boolean hasChanged = false;

	/** The {@link IModelListener} of this {@link IModel}. */
	private Set<IModelListener> listeners = new HashSet<IModelListener>();

	/** The {@link IMetamodel} of this {@link IModel}. */
	private IMetamodel metamodel;

	/**
	 * <p>
	 * Constructor to be called by subclasses. The <code>displayName</code> is a
	 * name that should be used to identify this model in a graphical user
	 * interface. This may be the file name or another identifier.
	 * </p>
	 * 
	 * @param displayName
	 *            A name for this {@link IModel}.
	 * @param metamodel
	 *            The {@link IMetamodel} for this {@link IModel}.
	 */
	protected AbstractModel(String displayName, IMetamodel metamodel) {

		/* Use an empty string if display name is null. */
		this.displayName = StringUtils.defaultString(displayName);

		if (metamodel == null) {
			throw new IllegalArgumentException(
					"The metamodel reference must not be null."); //$NON-NLS-1$
		}
		// no else.

		this.metamodel = metamodel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.modelbus.model.IModel#addListener(org.dresdenocl
	 * .pivot.modelbus.model.IModelListener)
	 */
	public boolean addListener(IModelListener listener) {

		if (listener == null) {
			throw new IllegalArgumentException(
					"Parameter listener must not be null.");
		}
		// no else.

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "addListener(";
			msg += "listener = " + listener; //$NON-NLS-1$ //$NON-NLS-2$
			msg += ") - enter";

			LOGGER.debug(msg);
		}
		// no else.

		boolean result;

		result = this.listeners.add(listener);

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("addListener(IModelListener) - exit - return value=" + result); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.modelbus.IModel#findNamespace(java.util.List)
	 */
	public Namespace findNamespace(List<String> pathName)
			throws ModelAccessException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "findNamespace(";
			msg += "pathName = " + pathName;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		/* The path name must not be null. */
		if (pathName == null) {
			throw new IllegalArgumentException(
					"The path name must not be null."); //$NON-NLS-1$
		}
		// no else.

		/* Clone the path name to avoid side effects. */
		pathName = new ArrayList<String>(pathName);

		/* By default search in the root name space. */
		Namespace namespace = getRootNamespace();

		if (pathName.size() > 0) {

			/* Probably remove the root package from the path name. */
			if (pathName.get(0).equals(ModelConstants.ROOT_PACKAGE_NAME)) {
				pathName.remove(0);
			}
			// no else.

			/* Iterate through the name space hierarchy. */
			for (String namespaceName : pathName) {

				/* Search for the next nested name space. */
				namespace = namespace.lookupNamespace(namespaceName);

				/* Probably cancel the search. */
				if (namespace == null) {
					break;
				}
				// no else.
			}
			// end for.
		}
		// no else (path name is empty).

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "findNamespace() - exit - ";
			msg += "return value = " + namespace;

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.

		return namespace;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.modelbus.IModel#findType(java.util.List)
	 */
	public Type findType(List<String> pathName) throws ModelAccessException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("findType(pathName=" + pathName + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		/* Avoid illegal parameters. */
		if (pathName == null || pathName.size() == 0) {
			throw new IllegalArgumentException(
					"The path name must not be null or empty."); //$NON-NLS-1$
		}
		// no else.

		Type result = null;

		/* Copy list to avoid side effects. */
		pathName = new ArrayList<String>(pathName);

		boolean isAbsolutePath = false;

		/* Probably remove the root package from the pathName. */
		if (pathName.get(0).equals(ModelConstants.ROOT_PACKAGE_NAME)) {

			pathName.remove(0);

			isAbsolutePath = true;

			if (pathName.size() == 0) {
				throw new IllegalArgumentException(
						"The path name must not be the root package name."); //$NON-NLS-1$
			}
			// no else.
		}
		// no else.

		List<Type> foundTypes;
		foundTypes = new ArrayList<Type>();

		/* Search for Types that match this path name. */
		foundTypes.addAll(this.findTypeHere(this.getRootNamespace(), pathName,
				!isAbsolutePath));

		/* Check if more than one Type was found. */
		if (foundTypes.size() > 1) {
			String msg;

			msg = "More than one type with path name " + pathName + " were found: " + foundTypes; //$NON-NLS-1$//$NON-NLS-2$
			LOGGER.warn(msg);

			result = null;
		}

		/* Else check if at least one type has been found. */
		else if (foundTypes.size() == 0) {

			/* If the path has only one element check for a primitive type. */
			if (pathName.size() == 1) {
				String primitiveName;
				
				PrimitiveType primitiveType;

				primitiveName = pathName.get(0);

				for (PrimitiveTypeKind aKind : PrimitiveTypeKind.VALUES) {

					if (primitiveName.equals(aKind.getName())) {
						primitiveType = PivotModelFactory.eINSTANCE
								.createPrimitiveType();
						primitiveType.setKind(aKind);
						primitiveType.setName(aKind.getName());
						foundTypes.add(primitiveType);
					}
					// no else.
				}
				// end for.
			}
			// no else.

			if (foundTypes.size() == 0) {
				String msg;

				msg = "Type with path name " + pathName + " was not found: " + foundTypes; //$NON-NLS-1$//$NON-NLS-2$
				LOGGER.warn(msg);

				result = null;
			}

			else {
				result = foundTypes.get(0);
			}
		}

		/* Else return the found type. */
		else {
			result = foundTypes.get(0);
		}
		// end else.

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("findType() - exit - return value=" + result); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.modelbus.model.IModel#getConstraints()
	 */
	public Collection<Constraint> getConstraints() throws ModelAccessException {

		return this.getRootNamespace().getOwnedAndNestedRules();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.modelbus.IModel#getDisplayName()
	 */
	public String getDisplayName() {

		return this.displayName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.modelbus.IModel#getMetamodel()
	 */
	public IMetamodel getMetamodel() {

		return this.metamodel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.modelbus.model.IModel#hasChanged()
	 */
	public boolean hasChanged() {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "hasChanged() - enter";

			LOGGER.debug(msg);
		}
		// no else.

		boolean result;

		result = this.hasChanged;

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("hasChanged() - exit - return value=" + result); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.modelbus.model.IModel#notifiyListeners()
	 */
	public synchronized boolean notifiyListeners() {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "notifiyListeners() - enter";

			LOGGER.debug(msg);
		}
		// no else.

		boolean result;
		result = this.hasChanged;

		if (result) {

			for (IModelListener listener : this.listeners) {
				listener.modelChanged(this);
			}
			// end for.

			this.hasChanged = false;
		}
		// no else.

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("notifiyListeners() - exit - return value=" + result); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.modelbus.model.IModel#removeAllConstraints()
	 */
	public boolean removeAllConstraints() throws IllegalArgumentException,
			ModelAccessException {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "removeAllConstraints() - enter";

			LOGGER.debug(msg);
		}
		// no else.

		boolean result;

		if (this.getConstraints().size() > 0) {
			result = this.getRootNamespace().removeOwnedAndNestedRules();
		}

		else {
			result = false;
		}

		if (result) {
			this.setChanged();
		}
		// no else.

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("removeAllConstraints() - exit - return value=" + result); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.modelbus.model.IModel#removeConstraints(java.util
	 * .Collection)
	 */
	public boolean removeConstraints(Collection<Constraint> constraints)
			throws IllegalArgumentException, ModelAccessException {

		if (constraints == null) {
			throw new IllegalArgumentException(
					"Parameter 'constraints' must not be null.");
		}
		// no else.

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "removeConstraints(";
			msg += "constraints = " + constraints; //$NON-NLS-1$ //$NON-NLS-2$
			msg += ") - enter";

			LOGGER.debug(msg);
		}
		// no else.

		boolean result;

		result = this.getRootNamespace().removeOwnedAndNestedRules(
				new ArrayList<Constraint>(constraints));

		if (result) {
			this.setChanged();
		}
		// no else.

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("removeConstraints() - exit"); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.modelbus.model.IModel#removeListener(tudresden.
	 * ocl20.pivot.modelbus.model.IModelListener)
	 */
	public boolean removeListener(IModelListener listener) {

		if (listener == null) {
			throw new IllegalArgumentException(
					"Parameter listener must not be null.");
		}
		// no else.

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "removeListener(";
			msg += "listener = " + listener; //$NON-NLS-1$ //$NON-NLS-2$
			msg += ") - enter";

			LOGGER.debug(msg);
		}
		// no else.

		boolean result;

		result = this.listeners.remove(listener);

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("removeListener(IModelListener) - exit - return value=" + result); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.modelbus.model.IModel#setChanged()
	 */
	public void setChanged() {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "setChanged() - enter";

			LOGGER.debug(msg);
		}
		// no else.

		this.hasChanged = true;

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("setChanged() - exit"); //$NON-NLS-1$
		}
		// no else.
	}

	/**
	 * <p>
	 * A helper method that recursively looks for a {@link Type} with the given
	 * path name in the given {@link Namespace}.
	 * </p>
	 * 
	 * @param namespace
	 *            The {@link Namespace} to start the search in.
	 * @param pathName
	 *            The path name to look for.
	 * @param searchAllNestedNamespaces
	 *            Indicates whether a recursive search in all nested
	 *            {@link Namespace}s with the full path name is required (for
	 *            non-fully-qualified path names).
	 * 
	 * @return A {@link List} containing all {@link Type}s found matching to the
	 *         given pathName.
	 */
	private List<Type> findTypeHere(Namespace namespace, List<String> pathName,
			boolean searchAllNestedNamespaces) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "findTypeHere(";
			msg += "namespace = " + namespace; //$NON-NLS-1$ //$NON-NLS-2$
			msg += ", pathName = " + pathName; //$NON-NLS-1$ //$NON-NLS-2$
			msg += ", searchAllNestedNamespaces = " + searchAllNestedNamespaces; //$NON-NLS-1$ //$NON-NLS-2$
			msg += ") - enter";

			LOGGER.debug(msg);
		}
		// no else.

		List<Type> result;

		result = new LinkedList<Type>();

		/*
		 * Search in all nested name spaces with the given path name (in case it
		 * was not fully qualified).
		 */
		if (searchAllNestedNamespaces) {
			for (Namespace nestedNamespace : namespace.getNestedNamespace()) {
				result.addAll(findTypeHere(nestedNamespace, pathName, true));
			}
		}
		// no else.

		/* Get the first path segment. */
		String firstPathSegment = pathName.get(0);

		/* Look for a type in this name space if no more name segments left. */
		if (pathName.size() == 1) {
			Type aType;

			aType = namespace.lookupType(firstPathSegment);

			if (aType != null) {
				result.add(aType);
			}
			// no else.
		}

		/* Else recursively look in nested name spaces. */
		else {
			namespace = namespace.lookupNamespace(firstPathSegment);

			if (namespace != null) {
				result.addAll(findTypeHere(namespace, pathName.subList(1,
						pathName.size()), false));
			}
		}

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("findTypeHere() - exit - return value=" + result); //$NON-NLS-1$
		}
		// no else.

		return result;
	}
}