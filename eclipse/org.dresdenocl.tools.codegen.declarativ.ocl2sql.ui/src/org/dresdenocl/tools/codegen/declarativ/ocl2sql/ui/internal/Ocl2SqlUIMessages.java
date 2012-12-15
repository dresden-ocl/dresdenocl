/*
Copyright (C) 2008-2010 by Bjoern Freitag (Bjoern Freitag@inf.tu-dresden.de)

This file is part of the OCL 2 SQL Code Generator of Dresden OCL2 for Eclipse.

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

package org.dresdenocl.tools.codegen.declarativ.ocl2sql.ui.internal;

import org.eclipse.osgi.util.NLS;

/**
 * <p>
 * This class contains localized string constants.
 * </p>
 * 
 * @author Bjoern Freitag
 */
public class Ocl2SqlUIMessages extends NLS {

	private static final String BUNDLE_NAME =
			"org.dresdenocl.tools.codegen.declarativ.ocl2sql.ui.internal.messages";

	public static String SettingsPage_Description;

	public static String SettingsPage_Title;

	public static String SettingsPage_DestinationLanguageLabel;

	public static String SettingsPage_TablePrefixLabel;

	public static String SettingsPage_KeyPrefixLabel;

	public static String SettingsPage_RestoreDefaults;

	public static String SettingsPage_TablePrefixGroup;

	public static String SettingsPage_ObjectViewPrefixLabel;

	public static String SettingsPage_AssociationPrefixLabel;

	public static String SettingsPage_PrimaryPrefixLabel;

	public static String SettingsPage_ForeignPrefixLabel;

	public static String SettingsPage_ModusLabel;

	public static String SettingsPage_ModusTypedLabel;

	public static String SettingsPage_ModusVerticalLabel;

	public static String TransformCodeWizard_Title;

	public static String SettingsPage_GenerateDDL;

	public static String SettingsPage_GenerateDDL_Both;

	public static String SettingsPage_GenerateDDL_Integrity;

	static {
		/* Initialize resource bundle. */
		NLS.initializeMessages(BUNDLE_NAME, Ocl2SqlUIMessages.class);
	}

	private Ocl2SqlUIMessages() {

		/* No implementation necessary. */
	}
}
