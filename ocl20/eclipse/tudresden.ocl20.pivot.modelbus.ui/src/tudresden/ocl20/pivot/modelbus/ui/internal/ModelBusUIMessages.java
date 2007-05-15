package tudresden.ocl20.pivot.modelbus.ui.internal;

import org.eclipse.osgi.util.NLS;

/**
 * This class contains localized string constants.
 * 
 * @author Matthias Braeuer
 * @version 1.0 13.04.2007
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

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME,ModelBusUIMessages.class);
  }

  private ModelBusUIMessages() {
    // no implementation necessary
  }
}
