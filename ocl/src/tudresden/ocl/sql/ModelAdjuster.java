/*
Copyright (C) 2002 Andrea Kling

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/
package tudresden.ocl.sql;

import java.util.*;
import ru.novosoft.uml.foundation.core.*;
import ru.novosoft.uml.model_management.*;
import ru.novosoft.uml.foundation.data_types.*;

/**
 *  a kind of filter for a MModel<BR>
 * the data for object-relational mappings in ORMappingImpl is here
 * made available. This includes:
 * <UL>
 *   <LI>all MClassifiers being either a MClass or a MInterface and
 * containing at least one Attribute or being part of and association or generalization</LI>
 *   <LI>all Inheritance root MClassifiers as above</LI>
 *   <LI>all Associations between MClassifiers and/or MInterfaces</LI>
 *   <LI>all Attributes having an undefined datatype as not being
 * one defined in TypeManager nor an newly defined MClassifier </LI>
 * </UL>
 *
 * Also <tt>null</tt>-entries are filled here
 *
 * @see ORMappingImpl
 * @see ru.novosoft.uml.model_management.MModel
 * @see TypeManager
 * @author Andrea Kling
 * */
public class ModelAdjuster{
  private MModel theModel;
  private Set classifiers;
  private Set associations;
  private Set generalizations;
  private Set generalizationRoots;
  private Set undefinedAttributes;
  private Set classAttributes;
  private MMultiplicity defaultMultiplicity;
  //private Set interfaces;

 /**
  * creates a new ModelAdjuster.
  * undefined Multiplicities at Associationends get a default multiplicity
  * of 1
  * */
  public ModelAdjuster(){
    this.defaultMultiplicity = MMultiplicity.M1_1;
  }

 /**
  * creates a new ModelAdjuster.
  * undefined Multiplicities at Associationends get a default multiplicity
  * of the given parameter
  * */

  public ModelAdjuster(MMultiplicity defaultMultiplicity){
    if (defaultMultiplicity != null){
      this.defaultMultiplicity = defaultMultiplicity;
    }else{
      this.defaultMultiplicity = MMultiplicity.M1_1;
    }

  }

  /**
   * start correcting and analysing the model
   * */
  public void adjust(MModel theModel){
    this.theModel = theModel;
    classifiers = new HashSet();
    associations = new HashSet();
    generalizations = new HashSet();
    generalizationRoots = new HashSet();
    undefinedAttributes = new HashSet();
    classAttributes = new HashSet();
    //interfaces = new HashSet();
    getAll(theModel);
    addAssociationRoles();
    addAssociationMultiplicities(defaultMultiplicity);
    addAssociationNames();
    getAttributes();
  }

  /**
   * sets the default multiplicity for AssociationEnds without
   * defined multiplicities
   * */
  public void setDefaultMultiplicity(MMultiplicity multi){
    if(multi != null)
      defaultMultiplicity = multi;
  }
   /**
    * gets all MClassifiers, inheritance roots and associations
    * */
  private void getAll(MModelElement element){
    if(element instanceof MClass || element instanceof MInterface) {
      Iterator it = ((MClassifier) element).getFeatures().iterator();
      boolean add = false;
      while(it.hasNext()){
        MFeature attrib = (MFeature) it.next();
        if(attrib instanceof MAttribute){
          add = true;
        }
      }
      if(add) classifiers.add(element);
      //if(add && element instanceof MInterface) interfaces.add(element);
      return;
    }
    if(element instanceof MAssociation){
      MAssociationEnd one = ((MAssociation) element).getConnection(0);
      if((one.getType() instanceof MClass || one.getType() instanceof MInterface) &&
        (one.getOppositeEnd().getType() instanceof MClass ||
        one.getOppositeEnd().getType() instanceof MInterface))
        associations.add(element);
      if(! classifiers.contains(one.getType()))
        classifiers.add(one.getType());
      if(! classifiers.contains(one.getOppositeEnd().getType()))
        classifiers.add(one.getOppositeEnd().getType());
      /*if(one.getType() instanceof MInterface) interfaces.add(one.getType());
      if(one.getOppositeEnd().getType() instanceof MInterface)
        interfaces.add(one.getOppositeEnd().getType());*/
      return;
    }
    if(element instanceof MGeneralization){
      MGeneralizableElement parent = ((MGeneralization)element).getParent();
      if( (parent instanceof MClass || parent instanceof MInterface) &&
        (((MGeneralization)element).getChild() instanceof MClass ||
        ((MGeneralization)element).getChild() instanceof MInterface)){
        generalizations.add(element);
        if(parent.getParents().size() == 0) {
          Collection superinterfaces = ((MModelElement)parent).getClientDependencies();
          if(superinterfaces.size() == 0){
            parent.setRoot(true);
          }else{
            Iterator it = superinterfaces.iterator();
            boolean root = true;
            while( it.hasNext() && root){
              MDependency dep = (MDependency) it.next();
              if(dep instanceof MAbstraction && dep.getStereotype() != null)
                if(dep.getStereotype().getName().toLowerCase().trim().equals("realize"))
                  root = false;
            }
            parent.setRoot(root);
          }
        }
        if (parent.isRoot())
          generalizationRoots.add(parent);
        if(! classifiers.contains(parent))
          classifiers.add(parent);
        if(! classifiers.contains(((MGeneralization)element).getChild()) )
          classifiers.add(((MGeneralization)element).getChild());
      }

      return;
    }
    if(element instanceof MAbstraction && element.getStereotype() != null)
      if(element.getStereotype().getName().toLowerCase().trim().equals("realize")){
        Collection supply = ((MDependency) element).getSuppliers();
        if(supply.size() > 0){
          MInterface parent = (MInterface) supply.iterator().next();
          if(parent.getParents().size() == 0 && parent.getAssociationEnds().size() > 0){
            parent.setRoot(true);
            generalizationRoots.add(parent);
          }
        }
      }

        //realizations.add(element);
    Iterator it= element.getModelElementContents().iterator();
    while(it.hasNext()){
      getAll((MModelElement) it.next());
    }
  }

 /* private void checkInheritanceTrees(){              //////////////////////// TODO !!!
    Iterator realize = realizations.iterator();
    while(realize.hasNext()){
      MAbstraction m = (MAbstraction) realize.next();
      if(classifiers.contains(m.getSuppliers().iterator().next()){
        Iterator implementers = m.getClients
   */
   /**
    * adds AssociationRoles, by default the associated classifier name
    * with the first letter in lower case.
    * if more than one unnamed associationend with this type exists
    * a sequence number is attached to the new roleName
    * */
  private void addAssociationRoles(){
    Iterator classes = classifiers.iterator();
    while(classes.hasNext()){
      MClassifier c = (MClassifier) classes.next();
      Iterator associationEnds = c.getAssociationEnds().iterator();
      int i = 2;
      MAssociationEnd firstAE = null;
      while ( associationEnds.hasNext() ){
        MAssociationEnd end = (MAssociationEnd) associationEnds.next();
        if(end.getName() == null){
          if(firstAE == null){
            firstAE = end;
            end.setName(c.getName().substring(0,1).toLowerCase()+c.getName().substring(1));
          } else {
            end.setName(c.getName().substring(0,1).toLowerCase()+c.getName().substring(1) + i);
            i++;
          }
        }else{
          if(end.getName().trim().equals("")){
            if(firstAE == null){
              firstAE = end;
              end.setName(c.getName().substring(0,1).toLowerCase()+c.getName().substring(1));
            } else {
              end.setName(c.getName().substring(0,1).toLowerCase()+c.getName().substring(1) + i);
              i++;
            }
          }
        }
      }
      if(i>2)
        firstAE.setName(firstAE.getName()+"1");
    }
  }

  private void addAssociationMultiplicities(MMultiplicity defaultMultiplicity){
    Iterator classes = classifiers.iterator();
    while(classes.hasNext()){
      MClassifier c = (MClassifier) classes.next();
      Iterator associationEnds = c.getAssociationEnds().iterator();
      while ( associationEnds.hasNext() ){
        MAssociationEnd end = (MAssociationEnd) associationEnds.next();
        if(end.getMultiplicity() == null)
          end.setMultiplicity(defaultMultiplicity);
      }
    }
  }

   /**
    * adds names to unnamed Associations
    * by default the name consits of the rolenames of the Association ends
    * connected by an underscore '_'
    * */
  private void addAssociationNames(){
    Iterator it = associations.iterator();
    while(it.hasNext()){
      MAssociation a = (MAssociation) it.next();
      if(a.getName() == null){
        a.setName(a.getConnection(0).getName() +"_"+
          a.getConnection(1).getName());
      }else{
        if(a.getName().trim().equals("")){
          a.setName(a.getConnection(0).getName() +"_"+
            a.getConnection(1).getName());
        }
      }
    }
  }

  /**
   * gets all attributes with unknown types
   * */
  private void getAttributes(){
    Iterator classes = classifiers.iterator();
    while(classes.hasNext()){
      Iterator it = ((MClassifier) classes.next()).getFeatures().iterator();
      while(it.hasNext()){
        MFeature attrib = (MFeature) it.next();
        if(attrib instanceof MAttribute){
          if(((MAttribute) attrib).getType() instanceof MClassifier)  {
            if(!classifiers.contains(((MAttribute) attrib).getType()) &&
             !TypeManager.getInstance().isDefined(((MAttribute) attrib).getType().getName())){
              undefinedAttributes.add(attrib);
            }else{
              if(classifiers.contains(((MAttribute) attrib).getType()))
                classAttributes.add(attrib);
            }
          }
        }
      }
    }
  }

  /**
   * returns a Set of all MClassifiers being either a MClass or a MInterface and
 * containing at least one Attribute or being part of and association or generalization
   * */
  public Set getClassifiers(){
    return classifiers;
  }

  /**
   * returns a Set of MAssociation of all Associations between classes and/or interfaces
   * */
  public Set getAssociations(){
    return associations;
  }

  /*
   * returns a Set of all MGeneralizations between classes and/or interfaces
   * */
 /* public Set getGeneralizations(){
    return generalizations;
  }/*

   /**
    * returns a Set of MClassifier of all defined generalzation roots being either
    * a class or an interface
    * the isRoot-parameter of those MClassifiers was added (if nessecary) while
    * 'adjusting' the MModel
    * */
  public Set getGeneralizationRoots(){
    return generalizationRoots;
  }

   /**
    * returns a Set of MAttribute of all attributes having an unknown datatype
    * */
  public Set getUndefinedAttributes(){
    return undefinedAttributes;
  }

   /**
    * returns a Set of MAttribute of all attributes referencing a classifier
    * defined in the MModel
    * */
  public Set getClassReferenceAttributes(){
    return classAttributes;
  }
}










