/*
 * Created on 20.12.2005
 *
 */
package tudresden.ocl20.cwm.init;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;

import javax.jmi.model.ModelElement;
import javax.jmi.model.ModelPackage;
import javax.jmi.model.MofPackage;
import javax.jmi.xmi.MalformedXMIException;
import javax.jmi.xmi.XmiReader;

import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.MetaModelUtil;
import tudresden.ocl20.core.ModelManager;
import tudresden.ocl20.core.ModelManagerException;
import tudresden.ocl20.core.Repository;
import tudresden.ocl20.core.RepositoryException;
import tudresden.ocl20.core.RepositoryManager;
/**
 * This class performs the integration of the CWM metamodell into the Netbeans MDR
 * @author Christian Wende
 *
 */
public class CWMIntegrator {

	/**
	 * The starting point for the CWM Metamodel-Integration.
	 */
	public static void main(String[] args) {
		CWMIntegrator cwmi = new CWMIntegrator();
		ModelPackage metamodel;
		try {
			cwmi.integrateCWM();
		} catch (RepositoryException e) {
			System.out.println("A RepositoryException occured while trying to integrate the CWM Metamodel.");
			e.printStackTrace();
		}
		RepositoryManager.getRepository().shutdown();
	}
	
	/**
	 * Loads the CWM Metamodel from the according XMI document and generates the corresponding JMI interfaces.
	 * @throws RepositoryException 
	 */
	private void integrateCWM() throws RepositoryException {
		Repository repository = RepositoryManager.getRepository();
		
		String name = MetaModelConst.getMMInfo(MetaModelConst.CWM).name;
        String jmiPrfx = MetaModelConst.getMMInfo(MetaModelConst.CWM).pckgPrefix;
        String xmiUrl = MetaModelConst.getMMInfo(MetaModelConst.CWM).xmi;
        URL cwmXMIUrl = ClassLoader.getSystemClassLoader().getResource(xmiUrl);
        URL outputDir = ClassLoader.getSystemClassLoader().getResource("cwm/");
        String integratedXmiUrl = null;
		try {
			integratedXmiUrl = java.net.URLDecoder.decode(new URL(outputDir ,"CWM.integrated.xml").getPath(), "UTF-8");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e){
			System.out.println("The encoding of the jmi export path is not supported.");
			e.printStackTrace();
 			
		}
        
        
        // If there is already a Metamodel named CWM in the repository, remove it first.
        ModelPackage metamodel = (ModelPackage) repository.getMetaModel(name);
	    if(metamodel != null){            
	          // delete the existing one
	          // repository.deleteMetaModel(metamodel);
	    	  // OR CWM allready integrated
	    		return;
	    }
	        
	    // Create a metamodel named  "CWM", in the repository.      
	    metamodel = (ModelPackage) repository.createMetaModel(name);
	        
	    // Read the Metamodel from the according XMI document
	    System.out.println("Reading Metamodel from \n"+cwmXMIUrl+" \n into extend "+name);
	    XmiReader xmiReader = repository.createXMIReader();
	    
	    Collection topElements = null;
		try {
			topElements = xmiReader.read(cwmXMIUrl.toString(),metamodel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedXMIException e) {
			System.out.println("The URL of the XMI document was malformed.");
			e.printStackTrace();
		}
	        
        // Sets the package prefix for the generated JMI interfaces
	    setJmiPckgPrefix(topElements, jmiPrfx);
	        
	        
	    // Generate the JMI interfaces
        System.out.print("Generating JMI Interfaces");
        String generatedjmidirPath;
        try {
			generatedjmidirPath = java.net.URLDecoder.decode(ClassLoader.getSystemClassLoader().getResource(MetaModelConst.GENERATEDJMIDIR+java.io.File.separator).getPath(), "UTF-8");
			System.out.println(" to " + generatedjmidirPath);
	        repository.generateJMIInterfaces(metamodel, new java.io.File(generatedjmidirPath+java.io.File.separator));
	        
        } catch (UnsupportedEncodingException e) {
			System.out.println("The encoding of the jmi export path is not supported.");
			e.printStackTrace();
        }
        
        System.out.println("Exporting the integrated metamodel to "+integratedXmiUrl);        
        try {
			ModelManager.getInstance().saveModel(metamodel, integratedXmiUrl);
		} catch (ModelManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
          
	}
	
	/**
	 *  Sets the package prefix for the JMI interfaces for a collection of model elements
   	 * @param topElements The collection of model element the prefix should be set for.
	 * @param pckgPrfx The prefix, that should be set.
	 */
	private void setJmiPckgPrefix(Collection topElements, String pckgPrfx){
        if(pckgPrfx!=null){
            Iterator topElementsIt = topElements.iterator();
            while(topElementsIt.hasNext()){
                ModelElement me = (ModelElement)topElementsIt.next();
                if(me instanceof MofPackage){
                    MetaModelUtil.setJmiPckgPrefix((MofPackage)me, pckgPrfx);
                }
            }
        }
	}
	
}
