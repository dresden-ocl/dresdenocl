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
package org.dresdenocl.essentialocl.types.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.essentialocl.types.OclLibrary;
import org.dresdenocl.model.IModel;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.model.TypeNotFoundException;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * An {@link ITypeResolver} helps in finding {@link Type}s. These can either be
 * located in the {@link OclLibrary} or in an associated {@link IModel}.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public class TypeResolver {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			EssentialOclPlugin.getLogger(TypeResolver.class);

	/** The associated {@link OclLibrary}. */
	private OclLibrary oclLibrary;

	/** Cache for the predefined {@link Type}s from the OCL Standard library. */
	private Map<String, Type> predefinedTypes;

	/**
	 * <p>
	 * Creates a new default {@link TypeResolver}.
	 * </p>
	 * 
	 * @param oclLibrary
	 *          The associated {@link OclLibrary}, must not be <code>null</code>.
	 */
	public TypeResolver(OclLibrary oclLibrary) {

		/*
		 * Should not happen in the default implementation, but clients may create
		 * own type resolvers.
		 */
		if (oclLibrary == null) {
			throw new IllegalArgumentException(
					"The reference to the associated OCL standard library must not be null."); //$NON-NLS-1$
		}
		// no else.

		this.oclLibrary = oclLibrary;
	}

	/**
	 * <p>
	 * Looks up a {@link Type} in the OCL library or in the associated
	 * {@link IModel}.
	 * </p>
	 * 
	 * @param pathName
	 *          The path name of the {@link Type} to look for.
	 * 
	 * @return A {@link Type} instance.
	 * 
	 * @throws IllegalArgumentException
	 *           If the given pathname is either <code>null</code> or empty.
	 * @throws ModelAccessException
	 *           If something goes wrong when accessing the {@link IModel}.
	 * @throws TypeNotFoundException
	 *           If a {@link Type} with the given path name cannot be found.
	 * @throws ModelBusException
	 *           If a general configuration error occurs.
	 */
	public Type findType(List<String> pathName, IModel model)
			throws TypeNotFoundException, ModelAccessException {

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

		/* If no primitive type found, try to look in the oclLibrary. */
		if (type == null) {
			type = model.findType(pathName);
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

			/* Instantiate the map for the primitive types and initialize it. */
			this.predefinedTypes = new HashMap<String, Type>();
			this.initializePredefinedTypes(oclLibrary);
		}

		return this.predefinedTypes;
	}
}