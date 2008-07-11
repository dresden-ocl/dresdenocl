package tudresden.ocl20.pivot.models.mdr.internal.provider;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.jmi.model.ModelPackage;
import javax.jmi.reflect.RefPackage;
import javax.jmi.xmi.MalformedXMIException;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;

import tudresden.ocl20.core.ModelManager;
import tudresden.ocl20.core.ModelManagerException;
import tudresden.ocl20.core.NetBeansRepository;
import tudresden.ocl20.core.Repository;
import tudresden.ocl20.core.RepositoryManager;
import tudresden.ocl20.core.jmi.uml15.core.ModelElement;
import tudresden.ocl20.core.jmi.uml15.impl.modelmanagement.ModelHelper;
import tudresden.ocl20.core.jmi.uml15.uml15.Uml15Package;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelInstanceProvider;
import tudresden.ocl20.pivot.models.mdr.internal.modelinstance.UmlModelInstance;

public class MdrModelInstanceProvider extends AbstractModelInstanceProvider
		implements IModelInstanceProvider {

  private Repository repository = null;
  private ModelManager modelmanager = null;
	
	private MdrModelInstanceProvider() {
		
	}
	
	private ModelPackage metamodel;
	
	private String modelname;
	
	public MdrModelInstanceProvider(String modelname) {
		this.modelname = modelname;
	}
	
	public IModelInstance getModelInstance(URL modelURL)
			throws ModelAccessException {
		
    IModelInstance modelInstance = null;
    URI modelURI;
    RefPackage rp = null;
    
    init();
        
    // try to create a URI
    try {
      modelURI = URI.createURI(modelURL.toString());
    }
    catch (IllegalArgumentException e) {
      throw new ModelAccessException("Invalid URL: " + modelURL,e); //$NON-NLS-1$
    }
    
    try {
			rp = modelmanager.getModel(modelname);
      if (rp instanceof tudresden.ocl20.core.jmi.mof14.model.ModelPackage)
      	throw new ModelAccessException("Instances of MOF-Models not yet supported");
      else if (rp instanceof Uml15Package) {
      	Uml15Package uml15Package = (Uml15Package)rp;
    		String path = modelURL.getFile();
    		int pathnamestart = path.lastIndexOf(":") + 1;
    		int extensionstart = path.lastIndexOf(".java");
    		String filenameWOExtension = path.substring(pathnamestart, extensionstart);
    		String[] parts = filenameWOExtension.split("/");
    		Class clazz = null;
    		ArrayList<String> partsList = new ArrayList<String>(Arrays.asList(parts));
    		String currentPath = partsList.remove(partsList.size()-1);
    		
    		while (clazz == null && partsList.size() > 0) {
    			try {
						clazz = Class.forName(currentPath);
					} catch (ClassNotFoundException e) {
					} finally {
						currentPath = partsList.remove(partsList.size()-1) + "." + currentPath;
					}
    		}

    		if (clazz == null)
    			throw new ModelAccessException("ModelProviderClass not found, maybe not in classpath");
    		try {
					clazz.getDeclaredMethod("getModelObjects", null);
				} catch (Exception e) {
    			throw new ModelAccessException("Class " + clazz + " doesn't provide needed methode getModelObjects()");
				}
				modelInstance = new UmlModelInstance(clazz, uml15Package);

      }
		} catch (ModelManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return modelInstance;
	}

	private void init() throws ModelAccessException {
		String resPath = null;
		try {
			resPath = FileLocator.resolve(FileLocator.find(Platform.
					getBundle("tudresden.ocl20.mdrepository"),
					new Path("resources") ,null)).getPath();
		} catch (IOException e) {
	    throw new ModelAccessException("Error resolving path plugindir/resources/repository",e); //$NON-NLS-1$
		}
		
		if (repository == null) {
			//repPath = "D:\\rep";
			System.setProperty(NetBeansRepository.MDR, resPath+"/repository");
			repository = RepositoryManager.getRepository();	
		}
		
		if (modelmanager == null)
			modelmanager = ModelManager.getInstance();
	
	}
}
