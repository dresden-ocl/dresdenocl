/*
Copyright (C) 2008-2009 by Matthias Bräuer

This file is part of the Model Bus GUI of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.modelbus.ui.internal;

import org.eclipse.osgi.util.NLS;

/**
 * <p>
 * This class contains localized string constants.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public class ModelBusUIMessages extends NLS {

	private static final String BUNDLE_NAME = "tudresden.ocl20.pivot.modelbus.ui.internal.messages"; //$NON-NLS-1$

	public static String LoadModelPage_BrowseFileSystemButtonText;

	public static String LoadModelPage_BrowseWorkspaceButtonText;

	public static String LoadModelPage_BrowseWorkspaceDialogDescription;

	public static String LoadModelPage_BrowseWorkspaceDialogTitle;

	public static String LoadModelPage_Description;

	public static String LoadModelPage_ErrorMsgInvalidVariables;

	public static String LoadModelPage_ErrorMsgModelFileNotExisting;

	public static String LoadModelPage_LocationLabelText;

	public static String LoadModelPage_MessagePleaseChooseModel;

	public static String LoadModelPage_ModelFileGroupText;

	public static String LoadModelPage_SelectMetamodelErrorMessage;

	public static String LoadModelPage_SelectMetamodelLabel;

	public static String LoadModelPage_Title;

	public static String LoadModelWizard_CheckLog;

	public static String LoadModelWizard_ErrorMessageDialogTitle;

	public static String LoadModelWizard_ErrorOccured;

	public static String LoadModelWizard_Title;

	public static String ModelsView_AccessRootNamespace;

	public static String ModelsView_Error;
	
	public static String ModelsView_ReloadModelTitle;

	public static String ModelsView_ReloadModelQestion;

	public static String LoadModelInstancePage_BrowseFileSystemButtonText;

	public static String LoadModelInstancePage_BrowseWorkspaceButtonText;

	public static String LoadModelInstancePage_BrowseWorkspaceDialogDescription;

	public static String LoadModelInstancePage_BrowseWorkspaceDialogTitle;

	public static String LoadModelInstancePage_Description;

	public static String LoadModelInstancePage_ErrorMsgInvalidVariables;

	public static String LoadModelInstancePage_ErrorMsgModelFileNotExisting;

	public static String LoadModelInstancePage_LocationLabelText;
	
	public static String LoadModelInstancePage_MessagePleaseLoadModelFirst;

	public static String LoadModelInstancePage_MessagePleaseChooseModel;

	public static String LoadModelInstancePage_ModelFileGroupText;

	public static String LoadModelInstancePage_SelectModelErrorMessage;

	public static String LoadModelInstancePage_SelectMetamodelLabel;

	public static String LoadModelInstancePage_SelectModelInstanceTypeLabel;
	
	public static String LoadModelInstancePage_SelectModelInstanceTypeErrorMessage;

	public static String LoadModelInstancePage_Title;

	public static String LoadModelInstanceWizard_Title;

	public static String LoadModelInstanceWizard_ErrorMessageDialogTitle;

	public static String LoadModelInstanceWizard_ErrorOccured;

	public static String LoadModelInstanceWizard_CheckLog;

	static {
		/* Initialize the resource bundle. */
		NLS.initializeMessages(BUNDLE_NAME, ModelBusUIMessages.class);
	}

	private ModelBusUIMessages() {
		/* No implementation necessary. */
	}
}