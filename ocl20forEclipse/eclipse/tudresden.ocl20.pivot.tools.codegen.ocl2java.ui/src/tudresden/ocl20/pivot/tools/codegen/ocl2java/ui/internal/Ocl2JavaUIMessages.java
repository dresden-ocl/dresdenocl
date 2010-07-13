/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the OCL 2 Java Code Generator of Dresden OCL2 for Eclipse.

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

package tudresden.ocl20.pivot.ocl2java.ui.internal;

import org.eclipse.osgi.util.NLS;

/**
 * <p>
 * This class contains localized string constants.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Ocl2JavaUIMessages extends NLS {

	private static final String BUNDLE_NAME = "tudresden.ocl20.pivot.ocl2java.ui.internal.messages";

	public static String TransformCodeWizard_Title;

	public static String TransformCodeWizard_ErrorMessageDialogTitle;

	public static String TransformCodeWizard_InitErrorOccured;

	public static String TransformCodeWizard_CodeGenErrorOccured;

	public static String TransformCodeWizard_CheckLog;

	public static String SelectModelPage_Title;

	public static String SelectModelPage_Description;

	public static String SelectModelPage_SelectModelLabel;

	public static String SelectModelPage_LoadModelButtonText;

	public static String SelectModelPage_LoadConstraintsButtonText;

	public static String SelectModelPage_MessagePleaseChooseModel;

	public static String SelectModelPage_MessageNoConstraints;

	public static String SelectModelPage_ErrorNoModelLoaded;

	public static String SelectConstraintsPage_Title;

	public static String SelectConstraintsPage_Description;

	public static String SelectConstraintsPage_SelectConstraintsLabel;

	public static String SelectConstraintsPage_MessageNoConstraints;

	public static String SelectConstraintsPage_SelectAllConstraints;

	public static String SelectConstraintsPage_DeselectAllConstraints;

	public static String SelectConstraintsPage_SelectAllBodyExpressions;

	public static String SelectConstraintsPage_SelectAllDefinitions;

	public static String SelectConstraintsPage_SelectAllDerivedValues;

	public static String SelectConstraintsPage_SelectAllInitialExpressions;

	public static String SelectConstraintsPage_SelectAllInvariants;

	public static String SelectConstraintsPage_SelectAllPostconditions;

	public static String SelectConstraintsPage_SelectAllPreconditions;

	public static String SelectDirectoryPage_Title;

	public static String SelectDirectoryPage_Description;

	public static String SelectDirectoryPage_DirectoryFileGroupText;

	public static String SelectDirectoryPage_SubDirectoryGroupText;

	public static String SelectDirectoryPage_LocationLabelText;

	public static String SelectDirectoryPage_SubDirectoryLabelText;

	public static String SelectDirectoryPage_DefaultSubDirectory;

	public static String SelectDirectoryPage_BrowseWorkspaceButtonText;

	public static String SelectDirectoryPage_BrowseFileSystemButtonText;

	public static String SelectDirectoryPage_BrowseWorkspaceDialogTitle;

	public static String SelectDirectoryPage_BrowseWorkspaceDialogDescription;

	public static String SelectDirectoryPage_MessagePleaseChooseDirectory;

	public static String SelectDirectoryPage_ErrorMsgInvalidVariables;

	public static String SelectDirectoryPage_ErrorMsgDirectoryNotExisting;

	public static String SelectDirectoryPage_ErrorMsgIsNoDirectory;

	public static String SelectDirectoryPage_ErrorMsgInvalidSubDirectory;

	public static String SettingsPage_Title;

	public static String SettingsPage_Description;

	public static String SettingsPage_ViolationMacroGroupLabel;

	public static String SettingsPage_ErrorNoMacroSet;

	public static String SettingsPage_RestoreDefaults;

	public static String SettingsPage_DefaultViolationMacro;

	public static String SettingsPage_ButtonGroupLabel;

	public static String SettingsPage_DisableInheritance;

	public static String SettingsPage_GenerateGetters;

	public static String SettingsPage_InvariantModeGroupLabel;

	public static String SettingsPage_InvariantMode1;

	public static String SettingsPage_InvariantMode2;

	public static String SettingsPage_InvariantMode3;

	public static String SpecificSettingsPage_Title;

	public static String SpecificSettingsPage_Description;

	public static String SpecificSettingsPage_SelectConstraintsLabel;

	public static String SpecificSettingsPage_ButtonGroupLabel;

	public static String SpecificSettingsPage_DisableInheritance;

	public static String SpecificSettingsPage_ViolationMacroGroupLabel;

	static {
		/* Initialize resource bundle. */
		NLS.initializeMessages(BUNDLE_NAME, Ocl2JavaUIMessages.class);
	}

	private Ocl2JavaUIMessages() {
		/* No implementation necessary. */
	}
}
