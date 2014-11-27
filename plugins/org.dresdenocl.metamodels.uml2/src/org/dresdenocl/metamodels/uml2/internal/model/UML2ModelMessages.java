/*
Copyright (C) 2008-2009 by Michael Thiele & Claas Wilke (claaswilke@gmx.net)

This file is part of the UML2 Meta Model of Dresden OCL2 for Eclipse.

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
package org.dresdenocl.metamodels.uml2.internal.model;

import org.eclipse.osgi.util.NLS;

/**
 * <p>
 * Provides localized String constants for the UML2 Plug-sin.
 * </p>
 * 
 * @author Michael Thiele
 */
public class UML2ModelMessages extends NLS {

	private static final String BUNDLE_NAME = "org.dresdenocl.metamodels.uml2.internal.model.messages"; //$NON-NLS-1$

	public static String UML2AdapterFactory_CreatingPivotModelAdapter;

	public static String UML2Model_LoadingUML2Model;

	public static String UML2_GetOwningType;
	
	public static String UML2_UnknownPrimitiveTypeKind;

	static {
		/* Initialize resource bundle. */
		NLS.initializeMessages(BUNDLE_NAME, UML2ModelMessages.class);
	}

	private UML2ModelMessages() {
		/* No implementation necessary. */
	}
}