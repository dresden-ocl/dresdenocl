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

import tudresden.ocl20.jmi.ocl.expressions.*;
import tudresden.ocl20.jmi.ocl.types.*;

import javax.jmi.reflect.*;
import javax.jmi.model.*;

import java.util.*;

/**
 * OclModel represents an instance of the integrated OCL metamodel (that is an instance of MOF + MOF-OCL or UML + UML-OCL).
 * Every OclModel has a package containig the OCL standard library.
 * @author  Stefan Ocke
 *
 */
public class OclModel {
    

    private OclExpressionFactory factory;   
    private tudresden.ocl20.TypeEvaluator typeEvl;
    private OclLibrary oclLib;
    private tudresden.ocl20.jmi.ocl.commonmodel.Package topPackage;

    //For MOF-OCL, model is an Instance of ModelPackage. 
    //For UML-OCL, model is an Instance of UmlPackage
    private RefPackage model;
    
    
    private static ModelManager mm = ModelManager.getInstance();

    private OclModel(){
        
    }
    
    /** Loads an OCL model from an XMI Document. The OCL standard library package will be created, if not already existent.
     * The name of the OCL model in the repository will be a generated one.
     * @param metamodelname should be one of {@link MetaModelConst#MOF14 MOF14}, {@link MetaModelConst#UML15 UML15}
     * @param modelxmi the URL of the XMI document to load
     * @throws OclModelException
     */
    public OclModel(String metamodelname, String modelxmi) throws OclModelException {
        try{
            model = mm.loadOclModel(metamodelname, modelxmi);
            init(metamodelname);
        }
        catch (Exception e){
            e.printStackTrace();
            close();
            throw new OclModelException(e);
        }
    }
    
    /** Loads an OCL model from an XMI Document. The OCL standard library package will be created, if not already existent.
     * @param metamodelname should be one of {@link MetaModelConst#MOF14 MOF14}, {@link MetaModelConst#UML15 UML15}
     * @param modelxmi the URL of the XMI document to load
     * @param name the name of the OCL model in the repository
     * @throws OclModelException
     */    
    public OclModel(String metamodelname, String modelxmi, String name) throws OclModelException {
        try{
            model = mm.loadOclModel(metamodelname, modelxmi, name);
            //models.add(modelPackage);
            init(metamodelname);
        }
        catch (Exception e){
            e.printStackTrace();
            close();
            throw new OclModelException(e);
        }
    }
    
    /**
     * Starts an transaction.
     */
    public void beginTrans(boolean writeAccess){
        this.mm.beginTrans(writeAccess);
    }
    
    /**
     * Commits a transaction or rolls it back.
     */
    public void endTrans(boolean rollback){
        this.mm.endTrans(rollback);
    }
    
    /** Seeks the repository for OCL-Models.
     *@param metamodelname should be one of {@link MetaModelConst#MOF14 MOF14}, {@link MetaModelConst#UML15 UML15}
     */
    public static List findOclModels(String metamodelname) throws OclModelException{
        try{
            List result = new ArrayList();
            
            Iterator it = mm.getModels(metamodelname).iterator();
            
            while(it.hasNext()){
                RefPackage rp = (RefPackage) it.next();
                OclModel model = new OclModel();
                try{
                    model.model = rp;
                    //System.out.println("MODEL: "+mm.getName(rp));
                    model.init(metamodelname);
                    result.add(model);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            
            return result;
            
        } catch (Exception e){
            e.printStackTrace();
            throw new OclModelException(e);
        }
    }
    
    /**
     * Exports the OCL-Model as XMI.
     */
    public void save(String modelxmi) throws OclModelException{
        try{
            mm.saveModel(model, modelxmi);
        } catch (Exception e){
            e.printStackTrace();
            throw new OclModelException(e);
        }
    }
    
    /**
     * Yields the name of this OCL model in the repository.
     */
    public String getName(){
        try{
            return mm.getName(model);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * The package that represents this model in the repository.
     * For MOF-OCL, model is an Instance of {@link  tudresden.ocl20.jmi.mof14.model.ModelPackage ModelPackage} 
     * For UML-OCL, model is an Instance of {@link  tudresden.ocl20.jmi.uml15.uml15.Uml15Package UmlPackage} 
     */
    public RefPackage getModel(){
        return model;
    }
   
        
    private void init(String metamodelname) throws Exception{
        
        //by getting the OclExpressionFactory, the OclLibrary and the topPackage by using reflective APIs
        //we dont have to make explicit distinctions between MOF-OCL and UML-OCL
        
        //find the specific OCL packages
        RefPackage specificOclPackage = model.refPackage( MetaModelConst.getMMInfo(metamodelname).getSpecializedOclName());
        RefPackage specificExpressionsPackage = specificOclPackage.refPackage(MetaModelConst.EXPPCKG);
        RefPackage specificTypesPackage = specificOclPackage.refPackage(MetaModelConst.TYPESPCKG);
        
        //find the CommonModel package
        RefPackage commonModelPackage = specificOclPackage.refPackage(MetaModelConst.OCLPCKG).refPackage(MetaModelConst.COMMONMODELPCKG);
        
        //find the topmost package    
        RefClass packageClass = getPackageClass(commonModelPackage);       
        topPackage = (tudresden.ocl20.jmi.ocl.commonmodel.Package)packageClass.refInvokeOperation("getTopPackage",new ArrayList());
        
        //find the singleton instance of the OclExpressionFactory
        factory = (OclExpressionFactory)specificExpressionsPackage.refClass("OclExpressionFactory").refInvokeOperation("getInstance",new ArrayList());
        
        //find the singleton instance of OclLibrary
        //note, that the creation of the OCL library package is implemented in the getInstance() method of OclLibrary
        oclLib = (OclLibrary)specificTypesPackage.refClass("OclLibrary").refInvokeOperation("getInstance",new ArrayList());
        
        typeEvl = new TypeEvaluator(this);
        
    }
    
    //find the specific subtype of the commonmodel class "package"
    //and return its class proxy
    private RefClass getPackageClass(RefPackage modelFacadePackage){
        javax.jmi.model.MofClass mc = (javax.jmi.model.MofClass)modelFacadePackage.refClass("Package").refMetaObject();
        javax.jmi.model.Generalizes gen = ((javax.jmi.model.ModelPackage)mc.refOutermostPackage()).getGeneralizes();
        
        //Iterates trough all the different subclasses of the class "Package"
        //in  the proxy-packages (mofproxies,umlproxies)
        Iterator packageSubClassesIt = gen.getSubtype(mc).iterator();
        
        MofClass packageSubClass = null;
        MofPackage modelPackageMeta = (MofPackage) model.refMetaObject();
        
        while(packageSubClassesIt.hasNext()){
            packageSubClass = (javax.jmi.model.MofClass)packageSubClassesIt.next();
            
            List nsList = new ArrayList();
            
            if(MetaModelUtil.findPathReverse(packageSubClass, modelPackageMeta, nsList)){
                //we  have found a path from modelPackage to packageSubClass
                //use this path to get the Class-Proxy for packageSubClass
                RefPackage refPackage = model;
                for(int i = nsList.size()-1; i>=0; i--){
                    refPackage = refPackage.refPackage((Namespace)nsList.get(i));
                }
                return refPackage.refClass(packageSubClass);
            }
        }
        return null;
    }
    
    
    /**Removes the OCL model from repository.*/
    public void delete(){
        if(model!=null){
            this.mm.deleteModel(model);
            model = null;
        }
    }
    
    /**Removes the OCL model from repository.*/
    public void close(){
        delete();
    }
    
    /**Removes the OCL model from repository and shuts down the repository.*/
    public void closeAndShutDown(){
        if(model!=null){
            this.mm.deleteModel(model);
            model = null;           
        }
        mm.shutdown();
    }
    
    
    /** 
     * This factory allows the creation of instances of the subclasses of {@link tudresden.ocl20.jmi.ocl.expressions.OclExpression OclExpression} within this OCL model.
     */
    public tudresden.ocl20.jmi.ocl.expressions.OclExpressionFactory getOclExpressionFactory() {
        return factory;
    }
    
    
    /** 
     * This type evaluator determines the type of an {@link tudresden.ocl20.jmi.ocl.expressions.OclExpression OclExpression} within this OCL model.
     */
    public tudresden.ocl20.TypeEvaluator getTypeEvaluator() {
        return typeEvl;
    }
    
    /** 
     * This instance of OclLibrary allows easy access to the predefined types in the OCL standard library package  within this OCL model.
     */
    public tudresden.ocl20.jmi.ocl.types.OclLibrary getOclLibrary() {
        return oclLib;
    }
    
    
    /**
     * This package is the topmost package of this OCL model. All lookups 
     * for a class by its qualified name ( {@link tudresden.ocl20.jmi.ocl.commonmodel.Package#findClassifier findClassifier}) should be done by using this package as starting point.
     */
    public tudresden.ocl20.jmi.ocl.commonmodel.Package getTopPackage() {
        return topPackage;
    }
    
    //    public tudresden.ocl20.jmi.oclcs.OclcsPackage getOclcsPackage(){
    //        return oclcsPackage;
    //    }
    
}
