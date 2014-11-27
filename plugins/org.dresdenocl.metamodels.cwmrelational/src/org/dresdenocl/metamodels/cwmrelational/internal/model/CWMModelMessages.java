/*
Copyright (C) 2012 by Bjoern Freitag (claaswilke@gmx.net)

This file is part of the CWM Meta Model of Dresden OCL2 for Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.metamodels.cwmrelational.internal.model;

import org.eclipse.osgi.util.NLS;

/**
 * <p>
 * Provides localized String constants for the CWM Plug-in.
 * </p>
 * 
 * @author Bjoern Freitag
 */
public class CWMModelMessages extends NLS {

	private static final String BUNDLE_NAME = "org.dresdenocl.pivot.metamodels.cwmrelational.internal.model.messages"; //$NON-NLS-1$

	public static String CWMAdapterFactory_CreatingPivotModelAdapter;

	public static String CWMModel_LoadingCWMModel;
	
	public static String CWM_GetOwningType;
	
	public static String CWM_UnknownPrimitiveTypeKind;

	static {
		/* Initialize resource bundle. */
		NLS.initializeMessages(BUNDLE_NAME, CWMModelMessages.class);
	}

	private CWMModelMessages() {
		/* No implementation necessary. */
	}
}