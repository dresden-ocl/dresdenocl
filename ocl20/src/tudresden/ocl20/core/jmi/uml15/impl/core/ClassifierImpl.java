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

package tudresden.ocl20.jmi.uml15.impl.core;

import java.util.*;


import tudresden.ocl20.jmi.uml15.uml15.Uml15Package;
import tudresden.ocl20.jmi.uml15.core.*;
import tudresden.ocl20.jmi.uml15.commonbehavior.Reception;
import tudresden.ocl20.jmi.uml15.commonbehavior.Signal;

import tudresden.ocl20.jmi.uml15.uml15ocl.types.*;

//import tudresden.ocl20.jmi.impl.uml15ocl.OclLibrary;

import org.netbeans.mdr.handlers.InstanceHandler;
import org.netbeans.mdr.storagemodel.StorableObject;

/**
 *
 * @author  Administrator
 */
public abstract class ClassifierImpl extends ModelElementImpl implements Classifier{
    
    /** Creates a new instance of ClassifierImpl */
    protected ClassifierImpl(StorableObject storable) {
        super(storable);
    }
    
    public List getParents(){
        List result = new ArrayList();
        Iterator it = getGeneralization().iterator();
        while(it.hasNext()){
            Generalization g = (Generalization)it.next(); 
            result.add(g.getParent());
            //System.out.println(" "+getName()+"   Parent:" +g.getParent().getName());
        }
        return result;
    }
    
    //Ocl Submission version 1.6, chapter 3.2.1
    //MOF-UML-Common
    public boolean conformsTo(tudresden.ocl20.jmi.ocl.commonmodel.Classifier c) {
        //System.out.println(getName()+" conformsTo "+((Classifier)c).getName()+" ?");
        
        if(equals(c)){
            return true;
        }
        
        Iterator it = getParents().iterator();
        while(it.hasNext()){
            tudresden.ocl20.jmi.ocl.commonmodel.Classifier parent = (tudresden.ocl20.jmi.ocl.commonmodel.Classifier) it.next();
            
                //System.out.println("Consider parent:" + ((Classifier)parent).getName());
                if(parent.equals(c)){
                    return true;
                }
                if(parent.conformsTo(c)){
                    return true;
                }
        }
        //System.out.println("No!");
        return false;
    }
    
    //Ocl Submission version 1.6, chapter 3.3.8, p 3-22
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
    
    public tudresden.ocl20.jmi.ocl.types.CollectionType getCollectionType(){
        return getOclLibrary().getCollectionType(this);
    }
    public tudresden.ocl20.jmi.ocl.types.SetType getSetType(){
        return getOclLibrary().getSetType(this);
    }
    public tudresden.ocl20.jmi.ocl.types.BagType getBagType(){
        return getOclLibrary().getBagType(this);
    }
    public tudresden.ocl20.jmi.ocl.types.SequenceType getSequenceType(){
        return getOclLibrary().getSequenceType(this);
    }
    
    /*public Collection allAssociationClasses() {
        return null;
    }
     
    public Collection allAssociationEnds() {
        return null;
    }
    */ 
    public List allAttributes() {
        List allAttributes = new ArrayList();
        Iterator featuresIt =  getFeature().iterator();
        while(featuresIt.hasNext()){
            Feature feature  = (Feature) featuresIt.next();
            if(feature instanceof Attribute){
                allAttributes.add(feature);
            }
        }
        Iterator parentsIt = getGeneralization().iterator();
        while(parentsIt.hasNext()){
            Classifier parent = (Classifier) ((Generalization) parentsIt.next()).getParent();
            allAttributes.addAll(parent.allAttributes());
        }
        return allAttributes;
    }
    
     
    public List allOperations() {
        List allOperations = new ArrayList();
        Iterator featuresIt =  getFeature().iterator();
        while(featuresIt.hasNext()){
            Feature feature  = (Feature) featuresIt.next();
            if(feature instanceof Operation){
                allOperations.add(feature);
            }
        }
        Iterator parentsIt = getGeneralization().iterator();
        while(parentsIt.hasNext()){
            Classifier parent = (Classifier) ((Generalization) parentsIt.next()).getParent();
            allOperations.addAll(parent.allOperations());
        }
        return allOperations;
    }
    
    /*
    public Collection allReceptions() {
        return null;
    }*/
    
    //NOTE: We implement the lookupOperations without using the "all..."-Operations,
    //in order to get some more efficient code.
    //But, of course, they  still conform to the specification.
    
    public tudresden.ocl20.jmi.ocl.commonmodel.AssociationClass lookupAssociationClass(java.lang.String name) {
        //the "association" reference is missing in the UML-Metamodel!
        Iterator ownEnds = ((CorePackage)this.refImmediatePackage()).getAParticipantAssociation().getAssociation(this).iterator();
        while(ownEnds.hasNext()){
            AssociationEnd ownEnd = (AssociationEnd) ownEnds.next();
            Association a = ownEnd.getAssociation();
            if(a instanceof UmlAssociationClass && name.equals(a.getName())){
                return (UmlAssociationClass) a;
            }
        }
        Iterator parentsIt = getGeneralization().iterator();
        while(parentsIt.hasNext()){
            Classifier parent = (Classifier) ((Generalization) parentsIt.next()).getParent();
            UmlAssociationClass ac = (UmlAssociationClass) parent.lookupAssociationClass(name);
            if(ac != null){
                return ac;
            }
        }
        return null;
    }
    
    //Ocl Submission version 1.6, chapter 3.3.8 p.3-23
    public tudresden.ocl20.jmi.ocl.commonmodel.AssociationEnd lookupAssociationEnd(java.lang.String name) {
        //Iterator ownEnds = this.getAssociation().
        //the "association" reference is missing in the UML-Metamodel!
        Iterator ownEnds = ((CorePackage)this.refImmediatePackage()).getAParticipantAssociation().getAssociation(this).iterator();
        while(ownEnds.hasNext()){
            AssociationEnd ownEnd = (AssociationEnd) ownEnds.next();
            Association a = ownEnd.getAssociation();
            Iterator oppositeEnds = a.getConnection().iterator();
            while(oppositeEnds.hasNext()){
                AssociationEnd oppositeEnd = (AssociationEnd) oppositeEnds.next();
                if(!oppositeEnd.equals(ownEnd) && name.equals(oppositeEnd.getName())){
                    return oppositeEnd;
                    //Issue: What about unnamed AssociationEnds?
                }
            }
        }
        Iterator parentsIt = getGeneralization().iterator();
        while(parentsIt.hasNext()){
            Classifier parent = (Classifier) ((Generalization) parentsIt.next()).getParent();
            AssociationEnd ae = (AssociationEnd) parent.lookupAssociationEnd(name);
            if(ae != null){
                return ae;
            }
        }
        
        //In addition to the specification, we consider the case, where this is
        //an  AssociationClass as well and look for the AssociationEnds being parts
        //of  this  Association
        if(this instanceof AssociationClass){
            Iterator ends = ((Association) this).getConnection().iterator();
            while(ends.hasNext()){
                AssociationEnd ae = (AssociationEnd) ends.next();
                if(name.equals(ae.getName())){
                    return ae;
                    //Issue: What about unnamed AssociationEnds?
                }
            }
        }
        
        return null;
    }
    
    
    //Ocl Submission version 1.6, chapter 3.3.8 p.3-23
    public tudresden.ocl20.jmi.ocl.commonmodel.Attribute lookupAttribute(java.lang.String attName) {
        Iterator featuresIt =  getFeature().iterator();
        while(featuresIt.hasNext()){
            Feature feature  = (Feature) featuresIt.next();
            if(feature instanceof Attribute && feature.getName().equals(attName)){
                return (Attribute) feature;
            }
        }
        Iterator parentsIt = getGeneralization().iterator();
        while(parentsIt.hasNext()){
            Classifier parent = (Classifier) ((Generalization) parentsIt.next()).getParent();
            Attribute a = (Attribute) parent.lookupAttribute(attName);
            if(a != null){
                return a;
            }
        }
        
        return null;
    }
    
    //Issue: overloading ... what about operation(Object) vs operation(String) ?
    public tudresden.ocl20.jmi.ocl.commonmodel.Operation lookupOperation(java.lang.String name, List paramTypes) {
        Operation op;
        
        //UML-MOF-Common
        Iterator allOperationsIt =  allOperations().iterator();
        while(allOperationsIt.hasNext()){
            op = (Operation) allOperationsIt.next();               
            if(name.equals(op.getName()) && op.hasMatchingSignature(paramTypes)){
                return op;
            }           
        }

        return null;
    }
    
    public tudresden.ocl20.jmi.ocl.commonmodel.Signal lookupSignal(java.lang.String sigName, List paramTypes) {
        Iterator featuresIt =  getFeature().iterator();
        while(featuresIt.hasNext()){
            Feature feature  = (Feature) featuresIt.next();
            if(feature instanceof Reception){
                Signal s = ((Reception) feature).getSignal();
                
                if(sigName.equals(s.getName()) && s.hasMatchingSignature(paramTypes)){
                    return s;
                }
            }
        }
        Iterator parentsIt = getGeneralization().iterator();
        while(parentsIt.hasNext()){
            Classifier parent = (Classifier) ((Generalization) parentsIt.next()).getParent();
            Signal s = (Signal) parent.lookupSignal(sigName, paramTypes);
            if(s != null){
                return s;
            }
        }
        
        return null;
    }
    
    public tudresden.ocl20.jmi.ocl.commonmodel.Classifier toOclType() {
         //Problem: no predefined types in  OCL -> every CASE Tool does, "whatever it likes"
        if(this instanceof DataType){
            String name = getName().toLowerCase();
            if(name.equals("int") || name.toLowerCase().equals("integer")||
               name.toLowerCase().equals("long") || name.toLowerCase().equals("byte")
            ){
                return this.getOclLibrary().getOclInteger(); 
            } 
            else if(name.equals("double") || name.toLowerCase().equals("float")||
               name.toLowerCase().equals("real") 
            )
            {
                return this.getOclLibrary().getOclReal(); 
            } 
            else if(name.equals("string")){
                return this.getOclLibrary().getOclString(); 
            }
            else if(name.equals("bool") || name.equals("boolean")){
                return this.getOclLibrary().getOclBoolean(); 
            }
            else return this;
        }
        return this;
        
       
    }
    
    public java.util.Collection getExpressionInOclA() {
        return ((Uml15Package)this.refOutermostPackage()).getUml15ocl().getOcl().getExpressions().getAContextualClassifierExpressionInOcl().getExpressionInOcl(this);
    } 
    
}
