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
  
  public static String ModelsView_AccessRootNamespace;
  public static String ModelsView_Error;
  
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME,ModelBusUIMessages.class);
  }

  private ModelBusUIMessages() {
  }
}
