/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL 2 Compiler                                                    *
 * Copyright (C) 2002, 2003 Stefan Ocke (stefan.ocke@gmx.de).        *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Technology, Dresden University Of Technology, Germany             *
 * (http://www-st.inf.tu-dresden.de).  It is understood that any     *
 * modification not identified as such is not covered by the         *
 * preceding statement.                                              *
 *                                                                   *
 * This work is free software; you can redistribute it and/or        *
 * modify it under the terms of the GNU Library General Public       *
 * License as published by the Free Software Foundation; either      *
 * version 2 of the License, or (at your option) any later version.  *
 *                                                                   *
 * This work is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *
 * Library General Public License for more details.                  *
 *                                                                   *
 * You should have received a copy of the GNU Library General Public *
 * License along with this library; if not, write to the             *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *
 * Boston, MA  02111-1307, USA.                                      *
 *                                                                   *
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other projects, please visit the web site:       *
 * http://www-st.inf.tu-dresden.de/ (Chair home page) or             *
 * http://www-st.inf.tu-dresden.de/ocl/ (project home page)          *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package tudresden.ocl20.core;

import javax.jmi.xmi.XmiReader;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.jmi.model.Association;
import javax.jmi.model.AssociationEnd;
import javax.jmi.model.Classifier;
import javax.jmi.model.GeneralizableElement;
import javax.jmi.model.Generalizes;
import javax.jmi.model.IsOfType;
import javax.jmi.model.ModelElement;
import javax.jmi.model.ModelPackage;
import javax.jmi.model.MofClass;
import javax.jmi.model.MofClassClass;
import javax.jmi.model.MofPackage;
import javax.jmi.model.NameNotFoundException;
import javax.jmi.model.Reference;
import javax.jmi.model.RefersTo;
import javax.jmi.model.TypedElement;
import javax.jmi.model.VisibilityKindEnum;


/**
 * This class implements the integration of the OCL-Metamodell with the UML-Metamodell or with MOF. 
 * During this integration, the specialized Types and Expressions packages for UML or MOF generated as well. 
 * @author Stefan Ocke
 */
public class MetaModelIntegrator {
    
    /** Creates a new instance of MetaModelIntegrator */
    private MetaModelIntegrator() {
    }
    
           
    /** Integrates the OCL-Metamodell with UML and MOF and generates the JMI-Interfaces.
     * The destination directory for the JMI-Interfaces is determined by
     * MetamodelConst.GENERATEDJMIDIR.
     */
    public static void main(String[] args) throws java.lang.Exception{
              
        // mmManager.initMetamodel(UML13, UMLPCKG, UML13XMI, OCLXMI, GENERATEDJMIDIR);
        // mmManager.initMetamodel(UML14, UMLPCKG, UML14XMI, OCLXMI, GENERATEDJMIDIR);
        MetaModelIntegrator.initOclMetaModel(MetaModelConst.UML15);
        MetaModelIntegrator.initOclMetaModel(MetaModelConst.MOF14);
        RepositoryManager.getRepository().shutdown();
        
    }
     
    
    /** Generates the specialized OCL packages (Types and Expressions) for MOF or UML. */    
    private static MofPackage createSpecializedOcl(ModelPackage metamodel, String name) throws NameNotFoundException{
        Generalizes generalizes  = metamodel.getGeneralizes();
        
        //Find the Common-OCL package
        MofPackage oclPackage = MetaModelUtil.findMofPackage(metamodel,MetaModelConst.OCLPCKG);
        
        //Find the CommonModel package
        MofPackage commonModelPackage = (MofPackage) oclPackage.lookupElement(MetaModelConst.COMMONMODELPCKG);
        
        //Find the spezialiced OCL-Package (UML15OCL or MOF14OCL)
        //The generated Types and Expressions package will be placed in this package.
        //Furthermore, this is the package to look for the Proxy package and the adapters package
        MofPackage specializedOclPackage = MetaModelUtil.findMofPackage(metamodel, MetaModelConst.getMMInfo(name).getSpecializedOclName());
        MofPackage proxyPackage = (MofPackage) specializedOclPackage.lookupElement(MetaModelConst.PROXYPCKG);
        MofPackage adapterPackage = null;
        try{
            adapterPackage = (MofPackage) specializedOclPackage.lookupElement(MetaModelConst.ADAPTERPCKG);
        } catch (NameNotFoundException nfe){
            //that's ok
            //for UML we need no adapters
        }
   
        //Find the Types and Expressions package in Common-OCL
        MofPackage [] oclSubPackages = new MofPackage[2];
        oclSubPackages[0] = (MofPackage) oclPackage.lookupElement(MetaModelConst.TYPESPCKG);
        oclSubPackages[1] = (MofPackage) oclPackage.lookupElement(MetaModelConst.EXPPCKG);
        
        for(int i = 0; i<oclSubPackages.length; i++){
                       
            //create the specific Types/Expressions package
            System.out.println("Create package "+oclSubPackages[i].getName() + " in " +  MetaModelConst.getMMInfo(name).getSpecializedOclName());
            MofPackage oclSpecializedSubPackage = metamodel.getMofPackage().createMofPackage(oclSubPackages[i].getName(), "", false, false, false, VisibilityKindEnum.PUBLIC_VIS);
            oclSpecializedSubPackage.setContainer(specializedOclPackage);
            
            
            //find all Classes in the Common-OCL Types/Expressions package
            MofClassClass clc = metamodel.getMofClass();
            Collection srcClasses = oclSubPackages[i].findElementsByType((MofClass) metamodel.getMofClass().refMetaObject(),false);
            
            //copy  the  classes into the specialized Types/Expressions package
            Iterator srcClassesIt = srcClasses.iterator();
            while(srcClassesIt.hasNext()){
                MofClass srcClass = (MofClass) srcClassesIt.next();
                //System.out.println(srcClass.getName());
                MofClass destClass = clc.createMofClass(srcClass.getName(), srcClass.getAnnotation(), srcClass.isRoot(),
                srcClass.isLeaf(), srcClass.isAbstract(),
                srcClass.getVisibility(), srcClass.isSingleton());
                destClass.setContainer(oclSpecializedSubPackage);
                
            }
            
            //now establish the generalizations  
            //  -between the specialized Types/Expressions package and the Common-OCL Types/Expressions package
            //  -between the specialized Types/Expressions package and the Proxy/Adapter package
            
            srcClassesIt = srcClasses.iterator();
            while(srcClassesIt.hasNext()){
                MofClass srcClass = (MofClass) srcClassesIt.next();
                
                //find the just generated copy in the specialized Types/Expressions package 
                MofClass destClass = (MofClass) oclSpecializedSubPackage.lookupElement(srcClass.getName());
                
                //establish the Generalization between the class in Common-OCL and the specialized class
                //for instances: UML15OCL::Types::SetType inherits from OCL::Types::SetType                
                List destSupertypes = destClass.getSupertypes();
                destSupertypes.add(srcClass);
                
                //now consider the supertypes of the class in Common-OCL
                List srcSupertypes = srcClass.getSupertypes();
                Iterator it = srcSupertypes.iterator();
                while(it.hasNext()){
                    MofClass srcParentClass = (MofClass) it.next();
                    
                    MofClass destParentClass = null;
                    if(srcParentClass.getContainer().equals(commonModelPackage)){
                        //the class inherits from a class in the CommonModel package 
                        //use the Proxy or Adapter package to determine the right supertype for the specialized class
                        //example:  
                        //    - OCL::Types::TupleType inherits from OCL::CommonModel::DataType
                        //    - for MOF14 the adapter MOF14OCL::Adapters::AdDataType exists for OCL::CommonModel::DataType  
                        //    conclusion: establish a Generalization between MOF14OCL::Types::TupleType and MOF14OCL::Adapters::AdDataType
                        
                        //looking for subtypes of the class from CommonModel 
                        //there should be exactly one such a subtype per specialized OCL (UML/MOF)
                        //this subtype may reside in the Proxy package or in the Adapters Package
                        Iterator subclassIt = generalizes.getSubtype(srcParentClass).iterator();
                        while(subclassIt.hasNext()){
                            MofClass subClass = (MofClass) subclassIt.next();

                            if(MetaModelUtil.contains(proxyPackage, subClass) || (adapterPackage != null && MetaModelUtil.contains(adapterPackage, subClass))){
                                if(destParentClass != null){
                                    System.out.println("Error: Found more than one subclass for "+ srcParentClass.getName() + "." + subClass.getName() + " and " + destParentClass.getName());
                                }
                                destParentClass = subClass;
                            }
                        }
                        if(destParentClass==null){
                            System.out.println("Warning: Found no subclass for "+ srcParentClass.getName());
                        }
                        
                    }
                    else if(srcParentClass.getContainer().equals(oclSubPackages[i])){
                        //The class does not inherit from a class in the CommonModel package,
                        //but from another class in the Common-OCL Types/Expressions Package.
                        //In this case, the supertype of the specialized class will be the
                        //specialized class for the supertype of the Common-OCL class (*urgs*)
                        //An Example:
                        // OCL::Types::SetType inherits from OCL::Types::CollectionType
                        // conclusion:  MOF14OCL::Types::SetType inherits from MOF14OCL::Types::CollectionType
                        destParentClass = (MofClass) oclSpecializedSubPackage.lookupElement(srcParentClass.getName());
                    }
                    
                    if(destParentClass!=null){
                        destSupertypes.add(destParentClass);
                    }
                }
            }
        }
        
        return specializedOclPackage;
    }
    
    /** Integrates the OCL-Metamodel with UML/MOF by generating the specialized OCL-Package and replacing the the meta-proxies. 
     Furthermore, the JMI-Interfaces for the integrated metamodell are generated.*/
    private static void integrateMetaModels(ModelPackage metamodel, String name, String topPckgName, String jmiPrfx) {
        System.out.println("Integration of OCL-Metamodell and "+name+". JMI-Interfaces will have the prefix "+jmiPrfx);
        try{
            IsOfType isOfType = metamodel.getIsOfType();
            Generalizes generalizes  = metamodel.getGeneralizes();
            RefersTo refersTo = metamodel.getRefersTo();
            
            MofPackage topPackage;
            
            //Find the top package. For UML, this is "UML". For MOF, this is "Model"
            topPackage = MetaModelUtil.findMofPackage(metamodel, topPckgName);
            
            //Find the Common-OCL package       
            MofPackage oclPackage = MetaModelUtil.findMofPackage(metamodel,MetaModelConst.OCLPCKG);
            
            //Find the CommonModel package
            MofPackage commonModelPackage = (MofPackage) oclPackage.lookupElement(MetaModelConst.COMMONMODELPCKG);
            
            //Create the specialized Types/Expressions Package
            MofPackage specializedOclPackage = createSpecializedOcl(metamodel, name);
            
            //Find the package containing the meta proxies
            MofPackage proxyPackage = (MofPackage) specializedOclPackage.lookupElement(MetaModelConst.PROXYPCKG);

            //Consider all subpackages in the proxy package 
            //This subpackages correspond to the packages in UML (Core, Data_types ...) and MOF (Model)      
            List content = proxyPackage.findElementsByType((MofClass) metamodel.getMofPackage().refMetaObject(), false);
            for(int n=0; n<content.size(); n++){
                MofPackage sourcePackage = (MofPackage) content.get(n);
                
                try{
                    //find the corresponding package in MOF/UML metamodel
                    MofPackage destPackage;                    
                    if(topPackage.getName().equals(sourcePackage.getName())){
                        destPackage = topPackage;  //for instance Model package in MOF
                    } else {
                        destPackage = (MofPackage) MetaModelUtil.lookupElementRecursive(topPackage, (MofClass) metamodel.getMofPackage().refMetaObject(), sourcePackage.getName());
                    }
                   
                    //The classes in UML/MOF metamodel can only inherit from CommonModel, if this package is imported
                    MetaModelUtil.createImport(destPackage, commonModelPackage, true); 
                    
                    System.out.println("Processing metaproxy package "+sourcePackage.getName());
                    
                    //Consider the associations contained in the proxy package
                    Iterator associationsIt = sourcePackage.findElementsByType((MofClass) metamodel.getAssociation().refMetaObject(), false).iterator();
                    while(associationsIt.hasNext()){
                        Association association = (Association) associationsIt.next();
                        try{
                            Association destAssociation = (Association) destPackage.lookupElementExtended(association.getName());
                            
                            //Association with same name already exists in the destination package. 
                            //Removing the proxy-association and all references pointing to it.
                            Iterator assocEndsIt = association.findElementsByType((MofClass) metamodel.getAssociationEnd().refMetaObject(),false).iterator();
                            while(assocEndsIt.hasNext()){
                                AssociationEnd ae = (AssociationEnd) assocEndsIt.next();
                                Iterator refIt = refersTo.getReferent(ae).iterator();
                                while(refIt.hasNext()){
                                    Reference ref = (Reference) refIt.next();
                                    //System.out.println("      Removing Reference "+ref.getName());
                                    refIt.remove();
                                    ref.refDelete();
                                }
                            }
                            association.refDelete();
                            
                        } catch (NameNotFoundException nnfe){
                            //moving the association from the proxy package to the destination package
                            association.setContainer(destPackage);
                        } catch (ClassCastException cce){
                            System.out.println("   Error: ModelElement with name "+association.getName() + " already exists and is not an Association.");
                            
                        }
                        
                    }
                    
                    //Consider the classes contained in the proxy package
                    Iterator classesIt = sourcePackage.findElementsByType((MofClass) metamodel.getClassifier().refMetaObject(), true).iterator();
                    while(classesIt.hasNext()){
                        Classifier srcClass = (Classifier) classesIt.next();
                        
                        try{
                            
                            String destName = srcClass.getName().substring(1); //remove  prefix "P"
                            
                            //find the corresponding class in UML/MOF metamodel
                            Classifier destClass = (Classifier) destPackage.lookupElementExtended(destName);
                            //System.out.println("   Replacing  Metaproxy "+srcClass.getName());
                            
                            //consider the contents of the proxy class (Attributes, References, Operations ...)
                            Iterator contentIterator = srcClass.getContents().iterator();
                            Collection destContent =  destClass.getContents();
                            while(contentIterator.hasNext()){
                                ModelElement contentElement = (ModelElement) contentIterator.next();
                                try{
                                    //dont allow duplicates or  "overriding" of  Attributes, References or Operations
                                    
                                    destClass.lookupElementExtended(contentElement.getName());
                                    //System.out.println("      ContentElement "+contentElement.getName()+" already exists!");
                                } catch (NameNotFoundException e){
                                    
                                    //System.out.println("      Moving ContentElement "+contentElement.getName());
                                    contentIterator.remove();
                                    destContent.add(contentElement);
                                    
                                }
                            }
                            
                            //now consider all typed elements (attributes, references, assocition ends ...), whose type is the meta-proxy 
                            Iterator typedElementsIt = isOfType.getTypedElements(srcClass).iterator();
                            
                            //...and change there type to the according class from UML/MOF metamodel 
                            while(typedElementsIt.hasNext()){
                                TypedElement te = (TypedElement) typedElementsIt.next();
                                //System.out.println("      Changing Type of "+te.getName()+" in "+te.getContainer().getName());
                                typedElementsIt.remove();
                                isOfType.add(destClass, te);
                            }
                            
                            // consider all Generalizations the meta-proxy is taking part in
                            // and move them in such a way, that the according class from UML/MOF metamodel
                            // takes the place of the meta-proxy.
                            
                            //at first, consider the generalizations, the proxy is subtype in
                            Iterator supertypesIt = generalizes.getSupertype(srcClass).iterator();
                            while(supertypesIt.hasNext()){
                                GeneralizableElement supertype = (GeneralizableElement) supertypesIt.next();
                                supertypesIt.remove();
                                if(!generalizes.exists(supertype, destClass)){
                                    generalizes.add(supertype, destClass);
                                }
                                //System.out.println("      Replacing Generalization with Supertype "+supertype.getName());
                            }
                            
                            //now, consider the generalizations, the proxy is supertype in
                            Iterator subtypesIt = generalizes.getSubtype(srcClass).iterator();
                            while(subtypesIt.hasNext()){
                                GeneralizableElement subtype = (GeneralizableElement) subtypesIt.next();
                                subtypesIt.remove();
                                if(!generalizes.exists(destClass, subtype)){
                                    generalizes.add(destClass, subtype);
                                }
                                //System.out.println("      Replacing Generalization with Subtype "+subtype.getName());
                            }
                            
                            srcClass.refDelete();
                            
                        } catch (ClassCastException e){
                            System.out.println("   Error: The Metaproxy "+srcClass.getName() + " is something different than a Class in "+name);
                            
                        } catch (NameNotFoundException e){
                            System.out.println("   Error: No Counterpart for Metaproxy: "+srcClass.getName());
                            //srcClass.setContainer(destPackage);
                        }
                    }
                    /*if(sourcePackage.getContents().isEmpty()){
                        sourcePackage.refDelete();
                        System.out.println("Proxy subpackage " +sourcePackage.getName()+" deleted");
                    } else {
                        System.out.println("Proxy subpackage " +sourcePackage.getName()+"still contains: ");
                            Iterator it = sourcePackage.getContents().iterator();
                            while(it.hasNext()){
                                System.out.println("     "+it.next());
                            }
                    }*/
                } catch (ClassCastException e){
                    System.out.println("Error: The proxy-package "+sourcePackage.getName() + " is not a Package in "+name);
                } catch (NameNotFoundException e){
                    System.out.println("Error: The proxy-package "+sourcePackage.getName() + " has no counterpart in "+name);
                }
                
            }
            proxyPackage.refDelete();
               /* if(proxyPackage.getContents().isEmpty()){
                    proxyPackage.refDelete();
                    System.out.println("Proxy package deleted");
                } else {
                    System.out.println("Proxy package still contains: ");
                    Iterator it = proxyPackage.getContents().iterator();
                    while(it.hasNext()){
                        System.out.println("     "+it.next());
                    }
                }*/
            
            //make the specialized and the common OCL package reachable from the UML/Model package
            MetaModelUtil.createImport(topPackage, oclPackage, true);
            MetaModelUtil.createImport(topPackage, specializedOclPackage, true);

            //set the package prefix for the  JMI Interfaces
            MetaModelUtil.setJmiPckgPrefix(specializedOclPackage, jmiPrfx);
            
            
        } catch (NameNotFoundException e){
            System.out.println("Error: The MofPackage "+e.getName()+" could not be  found");
        }
        
    }
    
    /** Loads the OCL-Metamodel into the Repository and integrates it with MOF or OCL. */    
    static ModelPackage initOclMetaModel(final String name) throws Exception{
        Repository repository = RepositoryManager.getRepository();
        
        MetaModelConst.MetaModelInfo mminfo = MetaModelConst.getMMInfo(name);
        if(mminfo == null){
            //not a predefined metamodel like MOF14 or UML15
            throw new ModelManagerException("Unknown Metamodel: "+name);
        }
        
        
        String topPckgName = mminfo.packageToInstantiate;  //"Model" for MOF, "UML" for UML
        String umlxmi = mminfo.xmi;    //the XML document containing the UML metamodel or MOF
        String oclxmi = mminfo.oclxmi; //the XML document containing the Common-OCL metamodel
        String jmiPrfx = mminfo.pckgPrefix; //the package prefix for the  JMI Interfaces
        String integratedxmi = java.net.URLDecoder.decode(mminfo.getIntegratedXmiPath().getPath(), "UTF-8"); 
        //the pathname for the xmi document that represents the result of metamodel integration
        
        
        //Is there already a metamodel in repository named "UML15" or "MOF14", respectively?
        //if this is the case, remove it at first.
        ModelPackage metamodel = (ModelPackage) repository.getMetaModel(name);
        if(metamodel != null){            
            //delete the old one
            repository.deleteMetaModel(metamodel);
        }
        
        //create a metamodel named  "UML15" or "MOF14", respectively, in the repository.      
        metamodel = (ModelPackage) repository.createMetaModel(name);
        
        //read MOF/UML metamodel from the according XMI document
        System.out.println("Reading Metamodel from "+umlxmi+" into extend "+name);
        java.net.URL umlxmiUrl = ClassLoader.getSystemClassLoader().getResource(umlxmi);
        XmiReader xmiReader = repository.createXMIReader();
        Collection topElements = xmiReader.read(umlxmiUrl.toString(),metamodel);
        
        //set the package prefix for the jmi interfaces 
        //this is especially important for MOF to not to come in conflict 
        //with the "original" interfaces in javax.jmi.model.*
        String pckgPrefix = MetaModelConst.getMMInfo(name).pckgPrefix;
        setJmiPckgPrefix(topElements, pckgPrefix);
        
        //read the Common-OCL metamodel from the according XMI document
        java.net.URL oclxmiUrl = ClassLoader.getSystemClassLoader().getResource(oclxmi);
        topElements = xmiReader.read(oclxmiUrl.toString(),metamodel);
        
        //find the Common-OCL Package and set its JMI pacakge prefix.
        //note, that this prefix is always the same and does not depend on the prefix for MOF or OCL.
        MofPackage oclPackage = MetaModelUtil.findMofPackage(metamodel,MetaModelConst.OCLPCKG);
        MetaModelUtil.setJmiPckgPrefix(oclPackage, MetaModelConst.OCLPCKGPRFX);
        
        
        //now integrate the OCL-Metamodel with MOF or UML
        MetaModelIntegrator.integrateMetaModels(metamodel, name, topPckgName, jmiPrfx);
        
        //generate the JMI interfaces
        System.out.print("Generating JMI Interfaces");
        String generatedjmidirPath = java.net.URLDecoder.decode(ClassLoader.getSystemClassLoader().getResource(MetaModelConst.GENERATEDJMIDIR+java.io.File.separator).getPath(), "UTF-8");
        System.out.println(" to "+generatedjmidirPath);
        repository.generateJMIInterfaces(metamodel, new java.io.File(generatedjmidirPath+java.io.File.separator));
               
        //export the result of the integration
        System.out.println("Exporting the integrated metamodel to "+integratedxmi);        
        ModelManager.getInstance().saveModel(metamodel, integratedxmi);
        
        return metamodel;
    }
    
    //sets the package prefix for the JMI interfaces for a collection of model elements
    private static void setJmiPckgPrefix(Collection topElements, String pckgPrfx){
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
