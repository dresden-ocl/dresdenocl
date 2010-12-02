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
package tudresden.ocl20.pivot.metamodels.ecore.internal.model;

import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.osgi.util.NLS;

/**
 * <p>
 * Localized {@link String} constants for the {@link EcorePlugin}.
 * 
 * @author Matthias Braeuer
 */
public class EcoreModelMessages extends NLS {

	/** The name of the message resource bundle. */
	private static final String BUNDLE_NAME = "tudresden.ocl20.pivot.metamodels.ecore.internal.model.messages"; //$NON-NLS-1$

	public static String EcoreAdapterFactory_CreatingPivotModelAdapter;

	public static String EcoreModel_LoadingEcoreModel;

	static {
		/* Initialize resource bundle. */
		NLS.initializeMessages(BUNDLE_NAME, EcoreModelMessages.class);
	}

	/**
	 * <p>
	 * Creates a new {@link EcoreModelMessages} instance.
	 * </p>
	 */
	private EcoreModelMessages() {
		/* No implementation necessary. */
	}
}