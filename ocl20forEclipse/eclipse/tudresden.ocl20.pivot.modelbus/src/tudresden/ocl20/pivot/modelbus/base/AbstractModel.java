/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology      *
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
package tudresden.ocl20.pivot.modelbus.base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.modelbus.IMetamodel;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
import tudresden.ocl20.pivot.modelbus.IModelFactory;
import tudresden.ocl20.pivot.modelbus.IOclLibraryProvider;
import tudresden.ocl20.pivot.modelbus.ITypeResolver;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.internal.ModelFactory;
import tudresden.ocl20.pivot.modelbus.internal.OclLibraryProvider;
import tudresden.ocl20.pivot.modelbus.internal.TypeResolver;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * Abstract base implementation of the {@link IModel} interface.
 * 
 * @author Matthias Braeuer
 */
public abstract class AbstractModel implements IModel {

	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER =
			ModelBusPlugin.getLogger(AbstractModel.class);

	// this model's name as displayed to clients
	private String displayName;

	// the metamodel of this model
	private IMetamodel metamodel;

	// a cached instance of the OCL Library provider
	private IOclLibraryProvider oclLibraryProvider;

	// cached instance of the model factory
	private IModelFactory modelFactory;

	// the type resolver of this model
	private ITypeResolver typeResolver;

	/**
	 * Constructor to be called by subclasses. The <code>displayName</code> is a
	 * name that should be used to identify this model in a graphical user
	 * interface. This may be the file name or another identifier.
	 * 
	 * @param displayName
	 *          a name for this model
	 * @param metamodel
	 *          the metamodel for this model
	 */
	protected AbstractModel(String displayName, IMetamodel metamodel) {

		// use an empty string if display name is null
		this.displayName = StringUtils.defaultString(displayName);

		if (metamodel == null) {
			throw new IllegalArgumentException(
					"The metamodel reference must not be null."); //$NON-NLS-1$
		}

		this.metamodel = metamodel;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModel#findNamespace(java.util.List)
	 */
	public Namespace findNamespace(List<String> pathName)
			throws ModelAccessException {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "findNamespace(";
			msg += "pathName = " + pathName;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		/* Clone the path name to avoid side effects. */
		pathName = new ArrayList<String>(pathName);

		/* The path name must not be null. */
		if (pathName == null) {
			throw new IllegalArgumentException("The path name must not be null."); //$NON-NLS-1$
		}
		// no else.

		/* By default search in the root name space. */
		Namespace namespace = getRootNamespace();

		/* Eventually remove the root package from the path name. */
		if (pathName.get(0).equals(IModelBusConstants.ROOT_PACKAGE_NAME)) {
			pathName.remove(0);
		}
		// no else.

		/* Iterate through the namespace hierarchy. */
		for (String namespaceName : pathName) {

			/* Search for the next nested name space. */
			namespace = namespace.lookupNamespace(namespaceName);

			/* Eventually cancel the search. */
			if (namespace == null) {
				break;
			}
			// no else.
		}

		/* Eventually log the exit from this method. */
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
	 * @see tudresden.ocl20.pivot.modelbus.IModel#findType(java.util.List)
	 */
	public Type findType(List<String> pathName) throws ModelAccessException {

		/* Eventually log the entry into this method. */
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

		Type result;
		List<Type> foundTypes;

		/* Eventually remove the root package from the pathName. */
		if (pathName.get(0).equals(IModelBusConstants.ROOT_PACKAGE_NAME)) {
			pathName.remove(0);
		}
		// no else.

		/* Search for Types that match this path name. */
		foundTypes = findTypeHere(this.getRootNamespace(), pathName, true);

		/* Check if more than one Type was found. */
		if (foundTypes.size() > 1) {
			String msg;

			msg =
					"More than one type with path name " + pathName + " were found: " + foundTypes; //$NON-NLS-1$//$NON-NLS-2$
			LOGGER.warn(msg);

			result = null;
		}

		/* Else check if at least one type has been found. */
		else if (foundTypes.size() == 0) {
			String msg;

			msg = "Type with path name " + pathName + " was not found: " + foundTypes; //$NON-NLS-1$//$NON-NLS-2$
			LOGGER.warn(msg);

			result = null;
		}

		/* Else return the found type. */
		else {
			result = foundTypes.get(0);
		}

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("findType() - exit - return value=" + result); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * A helper method that recursively looks for a {@link Type} with the given
	 * path name in the given {@link Namespace}.
	 * </p>
	 * 
	 * @param namespace
	 *          The {@link Namespace} to start the search in.
	 * @param pathName
	 *          The path name to look for.
	 * @param searchAllNestedNamespaces
	 *          Indicates whether a recursive search in all nested
	 *          {@link Namespace}s with the full path name is required (for
	 *          non-fully-qualified path names).
	 * 
	 * @return A {@link List} containing all {@link Type}s found matching to the
	 *         given pathName.
	 */
	private List<Type> findTypeHere(Namespace namespace, List<String> pathName,
			boolean searchAllNestedNamespaces) {

		/* Eventually log the entry into this method. */
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
		 * Search in all nested name spaces with the given path name (in case it was
		 * not fully qualified).
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
				result.addAll(findTypeHere(namespace, pathName.subList(1, pathName
						.size()), false));
			}
		}

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("findTypeHere() - exit - return value=" + result); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModel#getDisplayName()
	 */
	public String getDisplayName() {

		return displayName;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModel#getMetamodel()
	 */
	public IMetamodel getMetamodel() {

		return metamodel;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModel#getFactory()
	 */
	public IModelFactory getFactory() {

		// lazily create the model factory
		if (modelFactory == null) {
			modelFactory = new ModelFactory(this);
		}

		return modelFactory;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModel#getOclLibraryProvider()
	 */
	public IOclLibraryProvider getOclLibraryProvider() {

		// lazily create an OCL Library provider
		if (oclLibraryProvider == null) {
			oclLibraryProvider = new OclLibraryProvider();
		}

		return oclLibraryProvider;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModel#getTypeResolver()
	 */
	public ITypeResolver getTypeResolver() {

		if (typeResolver == null) {
			typeResolver = new TypeResolver(this);
		}

		return typeResolver;

	}
}