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

package tudresden.ocl20.jmi.mof14.impl.model;

import tudresden.ocl20.jmi.mof14.model.*;
import tudresden.ocl20.jmi.mof14.mof14ocl.expressions.*;

import org.netbeans.mdr.handlers.InstanceHandler;
import org.netbeans.mdr.storagemodel.StorableObject;

import java.util.*;
/** MOF1.4-specific implementations for Operations defined in
 * CommonModel::Classifier
 * @author Administrator
 */
public abstract class MofClassImpl extends ClassifierImpl implements MofClass{
    
    protected MofClassImpl(StorableObject storable) {
        super(storable);
    }
    
    //MOF-specific
    /** get all operations (inclusive inherited) of this Classifier */    
    public List allOperations() {
        List allOperations = new ArrayList();
        Iterator contentsIt =  getContents().iterator();
        while(contentsIt.hasNext()){
            ModelElement me  = (ModelElement) contentsIt.next();
            if(me instanceof Operation){
                allOperations.add(me);
            }
        }
        Iterator parentsIt = getParents().iterator();
        while(parentsIt.hasNext()){
            MofClass parent = (MofClass) parentsIt.next();
            allOperations.addAll(parent.allOperations());
        }
        return allOperations;
    }
    
    //MOF-specific
    /** get all attributes (inclusive inherited) of this Classifier */    
    public java.util.List allAttributes() {
        List allAttributes = new ArrayList();
        Iterator contentsIt =  getContents().iterator();
        while(contentsIt.hasNext()){
            ModelElement me  = (ModelElement) contentsIt.next();
            if(me instanceof Attribute){
                allAttributes.add(me);
            }
        }
        Iterator parentsIt = getParents().iterator();
        while(parentsIt.hasNext()){
            MofClass parent = (MofClass) parentsIt.next();
            allAttributes.addAll(parent.allAttributes());
        }
        return allAttributes;
    }
       
    /** get all signals (inclusive inherited) of this Classifier. For MOF always an
     * empty set.
     */    
    public tudresden.ocl20.jmi.ocl.commonmodel.Signal lookupSignal(java.lang.String sigName, java.util.List paramTypes) {
        //MOF does not have Signals
        return null;
    }
  

    /** find an operation of this classifier by its name and in/inout - parametertypes */    
    public tudresden.ocl20.jmi.ocl.commonmodel.Operation lookupOperation(java.lang.String name, java.util.List paramTypes) {
    
        Iterator allOperationsIt =  allOperations().iterator();
        while(allOperationsIt.hasNext()){
            Operation op = (Operation) allOperationsIt.next();               
            if(name.equals(op.getNameA()) && op.hasMatchingSignature(paramTypes)){
                return op;
            }           
        }

        return null;
    }
    
    /** find an attribute of this classifier by its name */    
    public tudresden.ocl20.jmi.ocl.commonmodel.Attribute lookupAttribute(java.lang.String attName) {
        Iterator allAttributesIt =  allAttributes().iterator();
        while(allAttributesIt.hasNext()){
            tudresden.ocl20.jmi.ocl.commonmodel.Attribute a = (tudresden.ocl20.jmi.ocl.commonmodel.Attribute) allAttributesIt.next();               
            //tbd: make UML-MOF-Common
            if(attName.equals(((Attribute)a).getNameA())){
                return a;
            }           
        }
        return null;
    }
    
    //MOF-specific
    /** find an opposite association end of this classifier by its name */    
    public tudresden.ocl20.jmi.ocl.commonmodel.AssociationEnd lookupAssociationEnd(java.lang.String name) {
        //in MOF, we only have binary Associations. That makes it a bit easier.
        //the name is interpreted to be the  name of a  reference ! (MOF1.4 chapter 3.9.3.1)
        Iterator contentsIt = getContents().iterator();
        while(contentsIt.hasNext()){
            ModelElement me  = (ModelElement) contentsIt.next();
            if(me instanceof Reference && me.getNameA().equals(name)){
                return ((Reference) me).getReferencedEnd();
            }
        }
        Iterator parentsIt = getParents().iterator();
        while(parentsIt.hasNext()){
            tudresden.ocl20.jmi.ocl.commonmodel.Classifier parent = (tudresden.ocl20.jmi.ocl.commonmodel.Classifier) parentsIt.next();
            tudresden.ocl20.jmi.ocl.commonmodel.AssociationEnd ae = parent.lookupAssociationEnd(name);
            if(ae!=null){
                return ae;
            }
        }
        return null;
    }
    
    /** find an association class of this classifier by its name.
     * for MOF always null.
     */    
    public tudresden.ocl20.jmi.ocl.commonmodel.AssociationClass lookupAssociationClass(java.lang.String name) {
        //MOF does not have AssociationClasses
        return null;
    }
    
    //MOF-specific
    protected tudresden.ocl20.jmi.ocl.types.OclLibrary getOclLibrary() {
        return ((ModelPackage)refOutermostPackage()).getMof14ocl().getTypes().getOclLibrary().getInstance();
    }
     
    //MOF-UML-Common
    /** Get the Set-Type that has this Classifier as element type. */    
    public tudresden.ocl20.jmi.ocl.types.SetType getSetType() {
        return getOclLibrary().getSetType(this);
    }
    
    //MOF-UML-Common
    /** Get the Sequence-Type that has this Classifier as element type. */    
    public tudresden.ocl20.jmi.ocl.types.SequenceType getSequenceType() {
        return getOclLibrary().getSequenceType(this);
    }
    
    //MOF-UML-Common
    /** Get the Collection-Type that has this Classifier as element type. */    
    public tudresden.ocl20.jmi.ocl.types.CollectionType getCollectionType() {
        return getOclLibrary().getCollectionType(this);
    }
    
    //MOF-UML-Common
    /** Get the Bag-Type that has this Classifier as element type. */    
    public tudresden.ocl20.jmi.ocl.types.BagType getBagType() {
        return getOclLibrary().getBagType(this);
    }
    
    //MOF-UML-Common
    /** conforms this classifier to the other one? */    
    public boolean conformsTo(tudresden.ocl20.jmi.ocl.commonmodel.Classifier c) {
        //System.out.println(getName()+" conformsTo "+((MofClass)c).getName()+" ?");
        
        if(equals(c)){
            return true;
        }
        
        Iterator it = getParents().iterator();
        while(it.hasNext()){
            tudresden.ocl20.jmi.ocl.commonmodel.Classifier parent = (tudresden.ocl20.jmi.ocl.commonmodel.Classifier) it.next();
            
                //System.out.println("Consider parent:" + parent.getName());
                if(parent.equals(c)){
                    return true;
                }
                if(parent.conformsTo(c)){
                    return true;
                }
        }
        
        return false;
    }
    
    //MOF-UML-Common
    /** Get the common supertype of two classifiers. */    
    public tudresden.ocl20.jmi.ocl.commonmodel.Classifier commonSuperType(tudresden.ocl20.jmi.ocl.commonmodel.Classifier c) {
        
        Set thisParents = new HashSet(); ;
        thisParents.add(this);
        Set cParents = new HashSet(); ;
        cParents.add(c);
        
        //the transitive closure of the Classifier and all  of its  parents
        Set thisTC = new HashSet();
        Set cTC = new HashSet();
        
        while(!(thisParents.isEmpty() && cParents.isEmpty())){
            thisTC.addAll(thisParents);
            cTC.addAll(cParents);
            
            Set thisParentsTemp = new HashSet();
            Iterator thisParentsIt = thisParents.iterator();
            while(thisParentsIt.hasNext()){
                tudresden.ocl20.jmi.ocl.commonmodel.Classifier aThisParent = (tudresden.ocl20.jmi.ocl.commonmodel.Classifier) thisParentsIt.next();
                if(cTC.contains(aThisParent)){
                    return aThisParent;
                }
                thisParentsTemp.addAll(aThisParent.getParents());
            }
            thisParents=thisParentsTemp;
            
            Set cParentsTemp = new HashSet();
            Iterator cParentsIt = cParents.iterator();
            while(cParentsIt.hasNext()){
                tudresden.ocl20.jmi.ocl.commonmodel.Classifier aCParent = (tudresden.ocl20.jmi.ocl.commonmodel.Classifier) cParentsIt.next();
                if(thisTC.contains(aCParent)){
                    return aCParent;
                }
                cParentsTemp.addAll(aCParent.getParents());
            }
            cParents=cParentsTemp;
        }
        return null; //should only occure if c is a collection type
    }
    
    //MOF-specific
    /** get the direct superclasses */    
    public List getParents(){       
        return getSupertypes();
    }
     
    //workaround for missing reference (probably just a Rose MOF Export error)
    /** get all Expressions in Ocl that have this classifier as contextual classifier */    
    public java.util.Collection getExpressionInOclA() {
        return ((ModelPackage)this.refImmediatePackage()).getMof14ocl().getOcl().getExpressions().getAContextualClassifierExpressionInOcl().getExpressionInOcl(this);
    }    
   
    public tudresden.ocl20.jmi.ocl.commonmodel.Operation createOperation(java.lang.String name, tudresden.ocl20.jmi.ocl.commonmodel.Classifier resultType, java.util.List params){
        ModelPackage modelPackage = (ModelPackage)this.refImmediatePackage();
        
        Operation operation = modelPackage.getOperation().createOperation(name, "", ScopeKindEnum.INSTANCE_LEVEL, VisibilityKindEnum.PUBLIC_VIS, true);
        operation.setContainer(this);
        
        Parameter returnParam = modelPackage.getParameter().createParameter("**result**", "", DirectionKindEnum.RETURN_DIR, modelPackage.createMultiplicityType(1,1,false,false));
        returnParam.setContainer(operation);
        returnParam.setType((MofClass) resultType);
            
        Iterator paramVarsIt = params.iterator();
        while(paramVarsIt.hasNext()){
            VariableDeclaration vd = (VariableDeclaration) paramVarsIt.next();
            Parameter param = modelPackage.getParameter().createParameter(vd.getName(), "", DirectionKindEnum.IN_DIR, modelPackage.createMultiplicityType(1,1,false,false));
            param.setContainer(operation);
            param.setType((MofClass) vd.getType());
        }
        
        return operation;
    }
   
    
}
