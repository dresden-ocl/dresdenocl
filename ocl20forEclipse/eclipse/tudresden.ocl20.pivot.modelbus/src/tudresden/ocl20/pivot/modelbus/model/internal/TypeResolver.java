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
package tudresden.ocl20.pivot.modelbus.model.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.model.IOclLibraryProvider;
import tudresden.ocl20.pivot.modelbus.model.ITypeResolver;
import tudresden.ocl20.pivot.modelbus.model.exception.TypeNotFoundException;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * A default implementation of the {@link ITypeResolver} interface.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public class TypeResolver implements ITypeResolver {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = Logger.getLogger(TypeResolver.class);

	/** The associated {@link IModel}. */
	private IModel model;

	/** Cache for the predefined {@link Type}s from the OCL Standard library. */
	private Map<String, Type> predefinedTypes;

	/**
	 * <p>
	 * Creates a new default {@link TypeResolver}.
	 * </p>
	 * 
	 * @param model
	 *          The associated {@link IModel}, must not be <code>null</code>.
	 */
	public TypeResolver(IModel model) {

		/*
		 * Should not happen in the default implementation, but clients may create
		 * own type resolvers.
		 */
		if (model == null) {
			throw new IllegalArgumentException(
					"The reference to the associated model must not be null."); //$NON-NLS-1$
		}
		// no else.

		this.model = model;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.ITypeResolver#findType(java.util.List)
	 */
	public Type findType(List<String> pathName) throws TypeNotFoundException,
			ModelAccessException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("findType(pathName=" + pathName + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		Type type = null;

		if (pathName == null || pathName.isEmpty()) {
			throw new IllegalArgumentException(
					"The path name must not be null or empty."); //$NON-NLS-1$
		}
		// no else.

		/* Try to look up a primitive type. */
		if (pathName.size() == 1) {
			type = this.getPredefinedTypes().get(pathName.get(0));
		}
		// no else.

		/* If no primitive type found, try to look in the model. */
		if (type == null) {
			type = this.model.findType(pathName);
		}
		// no else.

		if (type == null) {
			throw new TypeNotFoundException("Unable to find type '" + pathName + "'."); //$NON-NLS-1$//$NON-NLS-2$
		}
		// no else.

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("findType() - exit - return value=" + type); //$NON-NLS-1$
		}
		// no else.

		return type;
	}

	/**
	 * <p>
	 * Helper method to initialize the cache with the primitive types.
	 * </p>
	 */
	protected void initializePredefinedTypes(OclLibrary oclLibrary) {

		this.predefinedTypes.put(oclLibrary.getOclAny().getName(), oclLibrary
				.getOclAny());
		this.predefinedTypes.put(oclLibrary.getOclType().getName(), oclLibrary
				.getOclType());
		this.predefinedTypes.put(oclLibrary.getOclVoid().getName(), oclLibrary
				.getOclVoid());
		this.predefinedTypes.put(oclLibrary.getOclInvalid().getName(), oclLibrary
				.getOclInvalid());

		this.predefinedTypes.put(oclLibrary.getOclBoolean().getName(), oclLibrary
				.getOclBoolean());
		this.predefinedTypes.put(oclLibrary.getOclInteger().getName(), oclLibrary
				.getOclInteger());
		this.predefinedTypes.put(oclLibrary.getOclReal().getName(), oclLibrary
				.getOclReal());
		this.predefinedTypes.put(oclLibrary.getOclString().getName(), oclLibrary
				.getOclString());

		this.predefinedTypes.put(oclLibrary.getOclCollection().getName(),
				oclLibrary.getOclCollection());
		this.predefinedTypes.put(oclLibrary.getOclSequence().getName(), oclLibrary
				.getOclSequence());
		this.predefinedTypes.put(oclLibrary.getOclBag().getName(), oclLibrary
				.getOclBag());
		this.predefinedTypes.put(oclLibrary.getOclSet().getName(), oclLibrary
				.getOclSet());
		this.predefinedTypes.put(oclLibrary.getOclOrderedSet().getName(),
				oclLibrary.getOclOrderedSet());
	}

	/**
	 * <p>
	 * A helper method to lazily get the OCL standard {@link Type}s.
	 * </p>
	 */
	private Map<String, Type> getPredefinedTypes() {

		if (this.predefinedTypes == null) {

			/* Get the OCL library. */
			IOclLibraryProvider provider;
			provider = ModelBusPlugin.getOclLibraryProvider();

			if (provider == null) {
				throw new ModelBusException("The model '" + model.getDisplayName() //$NON-NLS-1$
						+ "' did not return a valid provider for the OCL Standard Library."); //$NON-NLS-1$
			}
			// no else.

			OclLibrary oclLibrary;
			oclLibrary = provider.getOclLibrary();

			/* Instantiate the map for the primitive types and initialize it. */
			this.predefinedTypes = new HashMap<String, Type>();
			this.initializePredefinedTypes(oclLibrary);
		}

		return this.predefinedTypes;
	}
}