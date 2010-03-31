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
package tudresden.ocl20.pivot.essentialocl.standardlibrary.provider;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import tudresden.ocl20.pivot.essentialocl.StandardLibraryPlugin;
import tudresden.ocl20.pivot.essentialocl.internal.StandardLibraryMessages;
import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.essentialocl.types.util.TypeResolver;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;

/**
 * <p>
 * Standard implementation of the {@link IOclLibraryProvider} interface that
 * simply loads the model file with the OCL Standard Library from the resources
 * of this plug.in.
 * </p>
 * 
 * @author Matthias Braeuer
 * @version 1.0 03.04.2007
 */
public class OclLibraryProvider implements IOclLibraryProvider {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			ModelBusPlugin.getLogger(OclLibraryProvider.class);

	/** The file name of the OCL Standard Library model. */
	private static final String OCL_LIBRARY_FILE =
			"/resources/oclstandardlibrary.types"; //$NON-NLS-1$

	/** The cached {@link OclLibrary} instance. */
	private OclLibrary oclLibrary;

	private TypeResolver typeResolver;

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IOclLibraryProvider#getOclLibrary()
	 */
	public OclLibrary getOclLibrary() {

		if (this.oclLibrary == null) {
			this.oclLibrary = this.loadOclLibrary();
		}
		// no else.

		return this.oclLibrary;
	}

	/**
	 * <p>
	 * A helper method that performs the actual loading. This should work without
	 * errors unless the Model Bus plug-in is changed.
	 * </p>
	 * 
	 * @return An {@link OclLibrary} instance.
	 */
	protected OclLibrary loadOclLibrary() {

		EObject result;

		ResourceSet resourceSet;
		resourceSet = new ResourceSetImpl();

		Resource resource;
		resource =
				resourceSet.createResource(URI.createPlatformPluginURI(
						StandardLibraryPlugin.ID + OCL_LIBRARY_FILE, false));

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(StandardLibraryMessages.OclLibraryProvider_LoadOclLibrary);
		}
		// no else.

		try {
			resource.load(null);
		}

		catch (IOException e) {
			throw new IllegalStateException(
					"Failed to load the OCL Standard Library.", e); //$NON-NLS-1$
		}

		result = resource.getContents().get(0);

		if (!(result instanceof OclLibrary)) {
			throw new IllegalStateException(
					"The root object of model '" + OCL_LIBRARY_FILE //$NON-NLS-1$
							+ "' is not an instance of OclLibrary."); //$NON-NLS-1$
		}
		// no else.

		return (OclLibrary) result;
	}

	public TypeResolver getTypeResolver() {

		if (typeResolver == null) {
			typeResolver = new TypeResolver(this.getOclLibrary());
		}

		return typeResolver;
	}
}