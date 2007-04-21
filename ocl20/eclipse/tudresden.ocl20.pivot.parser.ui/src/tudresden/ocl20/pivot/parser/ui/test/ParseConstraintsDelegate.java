package tudresden.ocl20.pivot.parser.ui.test;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.actions.ActionDelegate;

import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.parser.IOclParser;
import tudresden.ocl20.pivot.parser.ParserPlugin;

/**
 * 
 * 
 * @author Matthias Braeuer
 * @version 1.0 18.04.2007
 */
public class ParseConstraintsDelegate extends ActionDelegate implements
    IWorkbenchWindowActionDelegate {

  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(ParseConstraintsDelegate.class);

  private IWorkbenchWindow window;

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
   */
  public void init(IWorkbenchWindow window) {
    this.window = window;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.actions.ActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  public void run(IAction action) {
    IOclParser parser = ParserPlugin.getParser(ModelBusPlugin.getModelRegistry().getActiveModel());

    URL constraints;

    try {
      constraints = FileLocator
          .resolve(FileLocator.find(Platform.getBundle("tudresden.ocl20.pivot.examples.pml"),
              new Path("model/constraints.xocl"),null));

      parser.parse(constraints);
    }
    catch (Exception e) {
      MessageDialog.openError(window.getShell(),"Error","Error: " + e.getMessage()
          + (e.getCause() != null ? "\n" + e.getCause().getMessage() : ""));
      logger.error("Error",e);
      return;
    }

  }

}
