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

package tudresden.ocl20;

import javax.jmi.model.*;
import javax.jmi.xmi.*;
import javax.jmi.reflect.*;
import java.util.*;

//import org.omg.uml.*;
import java.io.*;

import tudresden.ocl20.NetBeansRepository;

/**
 * This class provides operations for managing the metamodels and models in the repository.
 * @author  Stefan Ocke
 */
public class ModelManager {
    
    private Repository repository;
    
    /** Creates a new instance of OclTest */
    private ModelManager() {
        repository = RepositoryManager.getRepository();
    }
    
    private static ModelManager instance;
    
    /**
     * @return the singleton instance of ModelManager
     */
    public static ModelManager getInstance(){
        if(instance == null){
            instance = new ModelManager();
        }
        return instance;
    }
    
    
    /** Starts a transaction.
     * @param writeAccess true, if the transaction changes a model
     */
    public void beginTrans(boolean writeAccess){
        this.repository.beginTrans(writeAccess);
    }
    
    /** Commits a transaction or rolls it back.
     * @param rollback
     */
    public void endTrans(boolean rollback){
        this.repository.endTrans(rollback);
    }
    
    /** Shutdown the repository.
     *
     */
    public void shutdown(){
        repository.shutdown();
    }
    
    /** Find a metamodel in the repository by its name.
     * @param metamodelname the name of the metamodel
     * @throws ModelManagerException
     * @return the metamodel
     */
    public ModelPackage getMetaModel(String metamodelname) throws ModelManagerException{
        try{
            ModelPackage metamodel = (ModelPackage) repository.getMetaModel(metamodelname);
            return metamodel;
        } catch (Exception e) {
            throw new ModelManagerException(e);
        }
    }
    
    
    /**
     * Seeks the repository for the UML + OCL or MOF + OCL metamodel. If not found, they are loaded from there XMI documents.
     * @param metamodelname  should be one of {@link MetaModelConst#MOF14 MOF14}, {@link MetaModelConst#UML15 UML15}
     * @throws ModelManagerException
     * @return the OCL metamodel
     */
    private ModelPackage getOclMetaModel(String metamodelname) throws ModelManagerException{
        try{
            ModelPackage metamodel = (ModelPackage) repository.getMetaModel(metamodelname);
            if(metamodel == null){
                metamodel = loadMetaModel(MetaModelConst.getMMInfo(metamodelname).getIntegratedXmiPath().toString(), metamodelname);
            }
            return metamodel;
        } catch (Exception e) {
            throw new ModelManagerException(e);
        }
    }
    
    /** Creates an instance of the UML + OCL or MOF + OCL metamodel. If the required metamodel is not found, it will be loaded from the according XMI document.
     * @return the OCL model
     * @param name the name for the model
     * @param metamodelname should be one of {@link MetaModelConst#MOF14 MOF14}, {@link MetaModelConst#UML15 UML15}
     * @throws ModelManagerException
     */
    public RefPackage createOclModel(String metamodelname, String name) throws ModelManagerException{
        try{
            ModelPackage metamodel = getOclMetaModel(metamodelname);
            
            String topPackageName = MetaModelConst.getMMInfo(metamodelname).packageToInstantiate;
            MofPackage topPackage = MetaModelUtil.findMofPackage(metamodel, topPackageName);
            return repository.createModel(name, topPackage);
        } catch (NameNotFoundException e){
            throw new ModelManagerException("No Package named UML was found in the Metamodel "+metamodelname);
        } catch (RepositoryException e) {
            throw new ModelManagerException(e);
        }
    }
    
    /** Find a model in the repository by its name.
     * @param modelname the name of the model
     * @throws ModelManagerException
     * @return the model
     */
    public RefPackage getModel(String modelname) throws ModelManagerException{
        try{
            RefPackage model = repository.getModel(modelname);
            return model;
        } catch (Exception e) {
            throw new ModelManagerException(e);
        }
    }
    
    
    
    /**
     * Creates an instance of instance of the UML + OCL or MOF + OCL metamodel and loads the model from an XMI document.
     * The name of the model in the repository is a generated one.
     * @param metamodelname should be one of {@link MetaModelConst#MOF14 MOF14}, {@link MetaModelConst#UML15 UML15}
     * @param uri the path to the XMI document of the model
     * @throws ModelManagerException
     * @throws IOException
     * @throws MalformedXMIException
     * @return the OCL model
     */
    public RefPackage loadOclModel(String metamodelname, String uri) throws ModelManagerException{
        return loadOclModel(metamodelname, uri, metamodelname+"instance"+System.currentTimeMillis());
    }
    
    /** Creates an instance of instance of the UML + OCL or MOF + OCL metamodel and loads the model from an XMI document.
     * 
     * @return the OCL model
     * @param name the name, the model shall have in the repository
     * @param metamodelname should be one of {@link MetaModelConst#MOF14 MOF14}, {@link MetaModelConst#UML15 UML15}
     * @param uri the path to the XMI document of the model
     * @throws ModelManagerException
     * @throws IOException
     * @throws MalformedXMIException
     */
    public RefPackage loadOclModel(String metamodelname, String uri, String name) throws ModelManagerException{
        RefPackage refPackage=null;
        try{
            refPackage = createOclModel(metamodelname, name);
            XmiReader xmiReader = repository.createXMIReader();
            Collection topelements = xmiReader.read(uri, refPackage);
            return refPackage;
        } catch (Exception e) {
            if(refPackage!=null){
                refPackage.refDelete();
            }
            throw new ModelManagerException(e);
        }
    }
    
    
    /** Instantiates a certain package of a metamodel and loads a model from an XMI document.
     * @param metamodelPackage the package to instantiate
     * @param uri the path to the XMI document of the model
     * @param name the name, the model shall have in the repository
     * @throws ModelManagerException
     * @throws IOException
     * @throws MalformedXMIException
     * @return the model
     */    
    public RefPackage loadModel(MofPackage metamodelPackage, String uri, String name) throws ModelManagerException{
        RefPackage refPackage=null;
        try{
            refPackage = repository.createModel(name, metamodelPackage);
            XmiReader xmiReader = repository.createXMIReader();
            Collection topelements = xmiReader.read(uri, refPackage);
            return refPackage;
        } catch (Exception e) {
            if(refPackage!=null){
                refPackage.refDelete();
            }
            throw new ModelManagerException(e);
        }
    }
    
    /** Instantiates a metamodel by using its top-level package. If there are multiple top level packages,
     *   one is created that clusters all the others. A model is loaded into the metamodel instance per XMI.
     * @param metamodel the name of a metamodel in the repository
     * @param uri the path to the XMI document of the model
     * @param name the name, the model shall have in the repository
     * @throws ModelManagerException
     * @throws IOException
     * @throws MalformedXMIException
     * @return
     */
    public RefPackage loadModel(ModelPackage metamodel, String uri, String name) throws ModelManagerException, IOException, MalformedXMIException{
        return loadModel(MetaModelUtil.getTopLevelPackage(metamodel), uri, name);
    }
    
    /** Exports a model into an XMI document.
     * @param model the model.
     * @param filename the filename of the XMI document
     * @throws ModelManagerException
     */    
    public void saveModel(RefPackage model, String filename) throws ModelManagerException{
        try{
            XmiWriter xmiWriter = repository.createXMIWriter();
            xmiWriter.write(new FileOutputStream(filename), model, "1.2");
        } catch (Exception e) {
            throw new ModelManagerException(e);
        }
    }
    
    
    /** Loads a metamodel into the repository.
     * @param uri the path to the XMI document of the metamodel
     * @param name the name, the metamodel shall have in the repository
     * @throws ModelManagerException
     * @return
     */    
    public ModelPackage loadMetaModel(String uri, String name) throws ModelManagerException{
        System.out.println("LoadMetamodel" +uri+" "+name);
        ModelPackage refPackage=null;
        try{
            refPackage = repository.getMetaModel(name);
            if(refPackage != null){
                repository.deleteMetaModel(refPackage);
            }
            refPackage = repository.createMetaModel(name);
            XmiReader xmiReader = repository.createXMIReader();
            Collection topelements = xmiReader.read(uri, refPackage);
            return refPackage;
        } catch (Exception e) {
            try{
                if(refPackage != null){ repository.deleteMetaModel(refPackage); }
            }catch (Exception e1) {
                e1.printStackTrace();
            }
            throw new ModelManagerException(e);
        }
    }
    
    /** Get all the models in repository that are instances of a certain metamodel.
     * @param metamodelName the name of the metamodel
     * @throws ModelManagerException
     * @return the models in the repository that are instances of the metamodel
     */
    public Collection getModels(String metamodelName) throws ModelManagerException{
        try{
            ModelPackage metaModel = (ModelPackage) repository.getMetaModel(metamodelName);
            if(metaModel == null){
                return new HashSet();
            }
            return repository.getModels(metaModel);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ModelManagerException(e);
        }
        
    }
    
    /**
     * @throws ModelManagerException
     * @return the names of all models and metamodels in the repository
     */    
    public Collection getAllModelNames() throws ModelManagerException{
        try{
            return repository.getAllNames();
        } catch (Exception e) {
            throw new ModelManagerException(e);
        }
    }
    
    /** Get all the models in repository that are instances of a certain metamodel.
     * @param metaModel the metamodel
     * @throws ModelManagerException
     * @return the models in the repository that are instances of the metamodel
     */    
    public Collection getModels(ModelPackage metaModel) throws ModelManagerException{
        try{
            return repository.getModels(metaModel);
        } catch (Exception e) {
            
            throw new ModelManagerException(e);
        }
    }
    
    /** Get the name of a model or metamodel.
     * @param model the model
     * @throws ModelManagerException
     * @return the name of the model
     */
    public String getName(RefPackage model) throws ModelManagerException{
        try{
            return repository.getName(model);
        } catch (Exception e) {
            throw new ModelManagerException(e);
        }
    }
    
    
    /** Removes a model from the repository.
     * @param model the model to delete
     */    
    public void deleteModel(RefPackage model){
        try{
            repository.deleteModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /** Removes a metamodel from the repository.
     * @param metamodel the metamodel to delete
     */  
    public void deleteMetaModel(ModelPackage metamodel){
        try{
            repository.deleteMetaModel(metamodel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
