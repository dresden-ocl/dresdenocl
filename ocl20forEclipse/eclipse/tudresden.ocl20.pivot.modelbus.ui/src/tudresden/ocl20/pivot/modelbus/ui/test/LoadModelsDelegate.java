package tudresden.ocl20.pivot.modelbus.ui.test;

import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.actions.ActionDelegate;

import tudresden.ocl20.pivot.modelbus.IMetamodel;
import tudresden.ocl20.pivot.modelbus.IMetamodelRegistry;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelProvider;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;

/**
 * 
 * 
 * @author Matthias Braeuer
 * @version 1.0 05.04.2007
 */
public class LoadModelsDelegate extends ActionDelegate implements IWorkbenchWindowActionDelegate {

  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(LoadModelsDelegate.class);

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

	    Iterator<IMetamodel> it = Arrays.asList(metamodels).iterator();
	    
	    IMetamodel ecore = null, uml = null, mof = null;

	    while (it.hasNext()) {
	    	IMetamodel temp = it.next();
	    	if (temp.getName().equals("Eclipse Modeling Framework (EMF) Ecore"))
	    		ecore = temp;
	    	else if (temp.getName().equals("Uml"))
	    		uml = temp;
	    	else if (temp.getName().equals("Mof"))
	    		mof = temp;
	    }

	    IModelProvider ecoreModelProvider = ecore.getModelProvider();
	    IModelProvider mofModelProvider = mof.getModelProvider();
	    IModelProvider umlModelProvider = uml.getModelProvider();    

	    IModel pml,pivotmodel,umlmetamodel,testszenario,umlbeispiel;
	    IModelInstance umlmodel,pmlInstance;

	    try {
	      pml = ecoreModelProvider.getModel(FileLocator.resolve(FileLocator.find(Platform
	          .getBundle("tudresden.ocl20.pivot.examples.pml"),new Path("model/pml.ecore"),null)));
	      pivotmodel = ecoreModelProvider.getModel(FileLocator.resolve(FileLocator.find(Platform
	          .getBundle("tudresden.ocl20.pivot.pivotmodel"),new Path("model/pivotmodel.ecore"),null)));
	      
	      umlbeispiel = umlModelProvider.getModel(new URL("file:/d:/UML-Beispiel.xmi"));
	      ModelBusPlugin.getModelRegistry().addModel(umlbeispiel);
	/*
	      testszenario = umlModelProvider.getModel(FileLocator.resolve(FileLocator.find(Platform.
	      		getBundle("tudresden.ocl20.pivot.metamodels.uml"),
	      		new Path("resources/examples/Testszenario.xmi"), null)));
	      ModelBusPlugin.getModelRegistry().addModel(testszenario);
	*/      
	      ModelBusPlugin.getModelRegistry().addModel(pml);
	      ModelBusPlugin.getModelRegistry().addModel(pivotmodel);
	      
//	      IModelInstanceProvider emip = pml.getModelInstanceProvider();
//	      pmlInstance = emip.getModelInstance(FileLocator.resolve(FileLocator.find(Platform
//	          .getBundle("tudresden.ocl20.pivot.examples.pml"),new Path("model/ExpressionTestSuite.pml"),null)));

//	      umlmetamodel = mofModelProvider.getModel(FileLocator.resolve(FileLocator.find(Platform.
//	      		getBundle("tudresden.ocl20.mdrepository"),
//	      		new Path("resources/metamodels/MOF14_plus_OCLMetamodel.xml"), null)));
//	      		new Path("resources/metamodels/UML15_plus_OCLMetamodel.xml"), null)));

//	      IModelInstanceProvider mip = umlmetamodel.getModelInstanceProvider();
//	      umlmodel = mip.getModelInstance(FileLocator.resolve(FileLocator.find(Platform.
//	    		getBundle("tudresden.ocl20.mdrepository"),
//	    		new Path("resources/metamodels/UML15_plus_OCLMetamodel.xml"), null)));
//	    		getBundle("tudresden.ocl20.pivot.metamodels.uml"),
//	    		new Path("resources/examples/Testszenario.xmi"), null)));
	      
//	      System.out.println(umlmodel.getObjects());

	/*      testszenario = umlModelProvider.getModel(FileLocator.resolve(FileLocator.find(Platform.
//	    		getBundle("tudresden.ocl20.mdrepository"),
//	    		new Path("resources/metamodels/UML15_plus_OCLMetamodel.xml"), null)));
	    		getBundle("tudresden.ocl20.pivot.metamodels.uml"),
	    		new Path("resources/examples/Testszenario.xmi"), null)));
	      
	      ModelBusPlugin.getModelRegistry().addModel(umlmetamodel);
	      ModelBusPlugin.getModelRegistry().addModel(testszenario);
	      System.out.println("UmlModel: " + umlmodel.getClass());
	      */
	      
//	      JavaStandardlibraryAdapterFactory.getInstance().registerAdapters();
	/*      System.out.println("PML-Instanz: " + pmlInstance);
	      System.out.println("PML-Instanz invalid: " + pmlInstance.getInvalid());
	      System.out.println("PML-Instanz undefined: " + pmlInstance.getUndefined());
	*/      

	      window.getActivePage().showView("tudresden.ocl20.pivot.modelbus.ui.views.models");
	    }
	    
	    catch (Exception e) {
	      MessageDialog.openError(window.getShell(),"Error","Error: " + e.getMessage());
	      logger.error("Error",e);
	      return;
	    }


  }

}
