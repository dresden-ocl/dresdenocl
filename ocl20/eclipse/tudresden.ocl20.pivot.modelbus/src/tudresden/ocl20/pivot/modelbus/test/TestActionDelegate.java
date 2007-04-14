package tudresden.ocl20.pivot.modelbus.test;

import org.apache.log4j.Logger;

import java.io.IOException;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.actions.ActionDelegate;

import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.modelbus.IMetamodel;
import tudresden.ocl20.pivot.modelbus.IMetamodelDescriptor;
import tudresden.ocl20.pivot.modelbus.IMetamodelRegistry;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelProvider;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.pivotmodel.Namespace;

/**
 * 
 * 
 * @author Matthias Braeuer
 * @version 1.0 05.04.2007
 */
public class TestActionDelegate extends ActionDelegate implements IWorkbenchWindowActionDelegate {

  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(TestActionDelegate.class);

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
  @Override
  public void run(IAction action) {
    StringBuilder message = new StringBuilder();

    IMetamodelRegistry registry = ModelBusPlugin.getMetamodelRegistry();
    IMetamodel[] metamodels = registry.getMetamodels();

    IMetamodel ecore = metamodels[0];

    IModelProvider ecoreModelProvider = ecore.getModelProvider();

    IModel pml,pivotmodel;

    try {
      pml = ecoreModelProvider.getModel(FileLocator.resolve(FileLocator.find(Platform
          .getBundle("tudresden.ocl20.pivot.examples.pml"),new Path("model/pml.ecore"),null)));
      pivotmodel = ecoreModelProvider.getModel(FileLocator.resolve(FileLocator.find(Platform
          .getBundle("tudresden.ocl20.pivot.pivotmodel"),new Path("model/pivotmodel.ecore"),null)));
      
      ModelBusPlugin.getModelRegistry().addModel(pml);
      ModelBusPlugin.getModelRegistry().addModel(pivotmodel);
      
      window.getActivePage().showView("tudresden.ocl20.pivot.modelbus.ui.views.models");
    }
    
    catch (Exception e) {
      MessageDialog.openError(window.getShell(),"Error","Error: " + e.getMessage());
      logger.error("Error",e);
      return;
    }
    
    OclLibrary oclLibrary = pml.getOclLibraryProvider().getOclLibrary();
    MessageDialog.openInformation(window.getShell(),"Test",oclLibrary.toString());

  }

}
