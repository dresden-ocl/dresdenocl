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
//import tudresden.ocl.sql.ORMapping;
import tudresden.ocl.codegen.decl.Table;
import tudresden.ocl.codegen.decl.ObjectView;
import tudresden.ocl.sql.orstrategy.*;

 /**
  * Implementation of the ORMapping interface.
  * This class represents an object-relational mapping for UML models
  * to relational database Tables.
  *
  * To make the actual mapping as flexible as possible special mapping
  * strategies can be assigned to different modelelements or be set as
  * default strategies for mapping of kinds of modelelements.
  *
 * @see tudresden.ocl.sql.orstrategy.ClassStrategy
 * @see tudresden.ocl.sql.orstrategy.KeyStrategy
 * @see tudresden.ocl.sql.orstrategy.DatatypeStrategy
 * @see tudresden.ocl.sql.orstrategy.InheritanceStrategy
 * @see tudresden.ocl.sql.orstrategy.OrderedStrategy
 * @see tudresden.ocl.sql.orstrategy.AssociationStrategy
  *
  * @author Andrea Kling
  */
public class ORMappingImpl implements ORMapping{

  /**
   * marks an InheritanceStrategy
   */
  public static String INHERIT   = "Inheritance Strategy";
  /**
   * marks a ClassStrategy
   * */
  public static String CLASS     = "Class Mapping Strategy";
  /**
   * marks a DatatypeStrategy
   */
  public static String TYPE      = "Data Type Mapping";
  /**
   * marks a KeyStrategy
   */
  public static String PK        = "Primary Key Strategy";
  /**
   * marks an AssociationStrategy that can be used to map 1:1 or 0..1:1 Associations
   */
  public static String ONE_ONE   = "1:1 Association Mapping";
  /**
   * marks an AssociationStrategy that can be used to map 1:N or 0..1:N Associations
   */
  public static String ONE_MANY  = "1:n Association Mapping";
  /**
   * marks an AssociationStrategy that can be used to map N:M Associations
   */
  public static String MANY_MANY = "n:m Association Mapping";
  /**
   * marks an OrderedStrategy
   */
  public static String ORDERED   = "ordered Association Mapping";


  private Hashtable classStrategies;        //MClassifier -> ClassStrategy
  private Hashtable datatypeStrategies;     //MAttribute -> DatatypeStrategy
  private Hashtable keyStrategies;          //MClassifier -> KeyStrategy
  private Hashtable inheritanceStrategies;  //MClassifier GeneralizationRoot -> InheritanceStrategy
  private Hashtable associationStrategies;  //MAssociation -> AssociationStrategy
  private Hashtable orderStrategies;        //MAssociationEnd -> OrderStrategy
  private Map defaultStrategies;

  private Hashtable classToTables;           //MClassifier -> List of Table
  private Hashtable nameToClassifier;        //String -> MClassifier
  private Hashtable associationEnds;         //String classifier -> (String assEndName(roleName?) -> String assEndClassifier)
  private Hashtable navigationGuides;        //String classifier -> (String roleName -> Guide associationEnd)
  private Hashtable associationTables;       //MAssociation -> Table associationTable
  private Hashtable classViews;              //MClassifier -> Objectview

  private MModel theModel;
  private Set tables;
  private Set classifiers;
  private Set associations;
  private Set generalizationRoots;
  private Set undefinedAttributes;
  private Set classAttributes;

   /**
    * @param theModel to be mapped to a database scheme
    * @param ma a ModelAdjuster filtering the interesting model data
    */
  public ORMappingImpl(MModel theModel, ModelAdjuster ma){
    this.theModel = theModel;
    ma.adjust(theModel);
    classifiers = ma.getClassifiers();
    associations = ma.getAssociations();
    generalizationRoots = ma.getGeneralizationRoots();
    undefinedAttributes = ma.getUndefinedAttributes();
    classAttributes = ma.getClassReferenceAttributes();
    classStrategies = new Hashtable();
    datatypeStrategies = new Hashtable();
    keyStrategies = new Hashtable();
    inheritanceStrategies = new Hashtable();
    associationStrategies = new Hashtable();
    orderStrategies = new Hashtable();
    defaultStrategies = new Hashtable();
    tables = new HashSet();
    associationTables = new Hashtable();
    nameToClassifier = new Hashtable();
    classToTables = new Hashtable();
    classViews = new Hashtable();
    Iterator it = classifiers.iterator();
    while(it.hasNext()){
      MClassifier classifier = (MClassifier) it.next();
      nameToClassifier.put(classifier.getName(), classifier);
    }
  }

  /**
   * returns a Map containing the name of the classifier for every
   * association (opposite roleName) classifier takes part in
   *
   * @return Map (String opposite roleName -> String classifier of opposite association end)
   * */
  public Map associationEnds(String classifier){  //(String assEndName(roleName?) -> String assEndClassifier)

    Hashtable assEnds = new Hashtable();
    MClassifier c = (MClassifier) nameToClassifier.get(classifier);
    Iterator it = c.getAssociationEnds().iterator();
    while(it.hasNext()){
      MAssociationEnd opposite = ((MAssociationEnd) it.next()).getOppositeEnd();
      assEnds.put(opposite.getName(), opposite.getType().getName());
    }
    return assEnds;
  }

   /**
    * returns a Set containing the names of all attributes of classifier
    *
    * @return a Set of String
    */
  public Set attributes(String classifier){
    HashSet attributes = new HashSet();
    MClassifier c = (MClassifier) nameToClassifier.get(classifier);
    Iterator it = c.getStructuralFeatures().iterator();
    while(it.hasNext()){
      MFeature attrib = (MFeature) it.next();
      if(attrib instanceof MAttribute)
        attributes.add(attrib.getName());
    }
    return attributes;
  }
  /**
   * returns the names of all classifiers the ModelAdjuster selected for mapping
   *
   * @return a Set of String
   *
   * @see ModelAdjuster
   */
  public Set classifiers(){
    return nameToClassifier.keySet();
  }

   /**
    * @return the MClassifier with the name 'name'
    */
  public MClassifier getClassifier(String name){
    return (MClassifier) nameToClassifier.get(name);
  }

    /**
     * @return a Set of String containing the names of classifiers direct supertypes
     */
  public Set directSupertypeNames(String classifier){
    Set superTypes = new HashSet();
    MClassifier c = (MClassifier) nameToClassifier.get(classifier);
    if(! c.isRoot()){
      Iterator it = c.getParents().iterator();
      while(it.hasNext()){
        MGeneralizableElement parent = (MGeneralizableElement) it.next();
        if(parent instanceof MClassifier)
          superTypes.add(((MClassifier) parent).getName());
      }
    }
    return superTypes;
  }
  /**
   * contains all Tables classifier has been mapped to, including Tables
   * containing inherited attributes and external attribute tables
   *
   * @return a List of Table
   *
   * @see tudresden.ocl.codegen.decl.Table
   */
  public List getClassTables(String classifier){
    return (List) classToTables.get(nameToClassifier.get(classifier));
  }

  /**
   * returns the ObjectView for classifier.
   * this View does not contain multivalue attributes mapped to additional Tables
   */
  public ObjectView getClassView(String classifier){
    return (ObjectView) classViews.get(nameToClassifier.get(classifier));
  }

  /**
   * returns a Set of ObjectView containg ObjectViews for all classifiers mapped
   *
   * @see tudresden.ocl.codegen.decl.ObjectView
   */
  public Set getClassViews(){
    return new HashSet(classViews.values());
  }

   /**
    * returns a List containing the Guide to the ObjectView of the
    * classifier corresponding the Associationend role name assEnd
    *
    * @see tudresden.ocl.codegen.decl.Guide
    * @see tudresden.ocl.codegen.decl.ObjectView
    */
  public List guidesToAssociationEnds(String classifier, String assEnd){
    Vector v = new Vector();
    v.add(((Map) navigationGuides.get(classifier)).get(assEnd));
    return v;
  }

   /**
    * returns a Set containing the names of all operations of classifier
    *
    * @return a Set of String
    */
  public Set operations(String classifier){
    Set operations = new HashSet();
    MClassifier c = (MClassifier) nameToClassifier.get(classifier);
    Iterator it = c.getFeatures().iterator();
    while(it.hasNext()){
      MFeature operation = (MFeature) it.next();
      if(operation instanceof MOperation)
        operations.add(operation.getName());
    }
    return operations;
  }

  /**
   * returns a List containing all Tables tht were created during mapping
   * the sequence of this list is random
   *
   * @return a List of Table
   *
   * @see tudresden.ocl.codegen.decl.Table
   */
  public List tables(){
    List returnTables = new Vector();
    returnTables.addAll(tables);
    return returnTables;
  }

  /**
   * sets a special mapping strategy for classifier
   */
  public void setStrategy(MClassifier classifier, ClassStrategy strategy){
    classStrategies.put(classifier, strategy);
  }

  /**
   * sets a special mapping strategy for attribute's datatype
   */
  public void setStrategy(MAttribute attribute, DatatypeStrategy strategy){
    datatypeStrategies.put(attribute, strategy);
  }

  /**
   * sets a special mapping strategy for classifier's primary key
   */
  public void setStrategy(MClassifier classifier, KeyStrategy strategy){
    keyStrategies.put(classifier, strategy);
  }

  /**
   * sets a special mapping strategy for a generalization
   */
  public void setStrategy(MClassifier generalizationRoot, InheritanceStrategy strategy){
    inheritanceStrategies.put(generalizationRoot, strategy);
  }

  /**
   * sets a special mapping strategy for association
   */
  public void setStrategy(MAssociation association, AssociationStrategy strategy){
    associationStrategies.put(association, strategy);
  }

  /**
   * sets a special mapping strategy for an ordered association end
   */
  public void setStrategy(MAssociationEnd associationEnd, OrderedStrategy strategy){
    orderStrategies.put(associationEnd, strategy);
  }

  /**
   * sets default strategies for modelelements
   * strategies should map Strategykinds as defined in ORMappingImpl
   * to concrete strategies
   *
   * @param strategies a Map (String -> Strategy)
   *
 * @see tudresden.ocl.sql.orstrategy.ClassStrategy
 * @see tudresden.ocl.sql.orstrategy.KeyStrategy
 * @see tudresden.ocl.sql.orstrategy.DatatypeStrategy
 * @see tudresden.ocl.sql.orstrategy.InheritanceStrategy
 * @see tudresden.ocl.sql.orstrategy.OrderedStrategy
 * @see tudresden.ocl.sql.orstrategy.AssociationStrategy
   */
  public void setDefaultStrategies(Map strategies){
    if(strategies == null) return;
    defaultStrategies = strategies;
  }

     /**
      * returns all generalization roots marked for mapping
      *
      * @return a Set of MClassifier
      */
  public Set getGeneralizationRoots(){
    return generalizationRoots;
  }

 /**
  * @return a Set of MAssociation
  */
  public Set getAssociations(){
    return associations;
  }
  /**
   * contains all attributes having a type undefined in TypeManager and
   * no classifier defined in this Model
   *
   * @return a Set of MAttribute
   */
  public Set getUndefinedAttributes(){
    return undefinedAttributes;
  }

   /**
    * initiates the mapping process
    */
  public void map(){
    tables = new HashSet();
    associationTables = new Hashtable();
    classToTables = new Hashtable();
    navigationGuides = new Hashtable();
    Iterator it=classifiers.iterator();
   //// get class-strategies
    ClassStrategy cs;
    if(defaultStrategies.containsKey(CLASS)){
      cs = (ClassStrategy) defaultStrategies.get(CLASS);
    }else{
      cs = SimpleClassToTableMapping.getInstance();
    }
    //// get key-strategies
    KeyStrategy ks;
    if(defaultStrategies.containsKey(PK)){
      ks = (KeyStrategy) defaultStrategies.get(PK);
    }else{
      ks = OIDKeyMapping.getInstance();
    }
    while(it.hasNext()){
      MClassifier classifier = (MClassifier) it.next();
   //// use class-strategies
      if(classStrategies.get(classifier) == null){
        cs.map(classifier, classToTables, classViews);
      }else{
        ((ClassStrategy) classStrategies.get(classifier)).map(classifier, classToTables, classViews);
      }
    //// use key-strategies
      if(keyStrategies.get(classifier) == null){
        ks.map(classifier, classToTables, classViews);
      }else{
        ((KeyStrategy) keyStrategies.get(classifier)).map(classifier, classToTables, classViews);
      }
    }
     // inheritance-strategies
    it = generalizationRoots.iterator();
    InheritanceStrategy is;
    if(defaultStrategies.containsKey(INHERIT)){
      is = (InheritanceStrategy) defaultStrategies.get(INHERIT);
    }else{
      is = InheritanceMapping.getInstance();
    }
    while(it.hasNext()){
      MClassifier generalizationRoot = (MClassifier) it.next();
      if(inheritanceStrategies.get(generalizationRoot) == null){
        is.map(generalizationRoot, classToTables, classViews);
      }else{
        ((InheritanceStrategy) inheritanceStrategies.get(generalizationRoot)).
          map(generalizationRoot, classToTables, classViews);
      }
    }
    //datatypeStrategies
    it = datatypeStrategies.keySet().iterator();
    while(it.hasNext()){
      MAttribute att = (MAttribute) it.next();
      ((DatatypeStrategy) datatypeStrategies.get(att)).map(att, classToTables, classViews);
    }
    it = classAttributes.iterator();
    while(it.hasNext()){
      MAttribute attribute = (MAttribute) it.next();
      ClassTypeMapping ctm = new ClassTypeMapping((MClassifier)
        nameToClassifier.get(attribute.getType().getName()));
      ctm.map(attribute, classToTables, classViews);
      /*MClassifier owner = attribute.getOwner();
      List ownerTables = (List) classToTables.get(owner);
      boolean found = false;
      Table ownerTable = null;
      for (int i=0; i<ownerTables.size() && !found; i++){
        String str = ((Table) ownerTables.get(i)).getAttributeColumn(attribute.getName());
        if(str != null){
          ownerTable = (Table) ownerTables.get(i);
          found = true;
        }
      }
      if(ownerTable == null)
        ownerTable = (Table) ownerTables.get(0);
      try{
        ownerTable.removeColumn(ownerTable.getAttributeColumn(attribute.getName()));
      }catch(Exception ex){}
      MClassifier m = (MClassifier) nameToClassifier.get(attribute.getType().getName());
      Table mTable = (Table) ((List) classToTables.get(m)).get(0);
      String[] mPK = ownerTable.getPrimaryKeyColumns();
      String[] tFK = new String[mPK.length];
      for(int a=0; a<mPK.length; a++){
        ownerTable.addColumn(null, mTable.getColumnType(mPK[a]),
          mTable.getTableName()+"_"+mPK[a], false);
        tFK[a] = mTable.getTableName()+"_"+mPK[a];
      }
      ownerTable.setForeignKey(tFK, mTable, mPK);
       */
    }
    // associationStrategies
    it = associations.iterator();
    OrderedStrategy os;
    if(defaultStrategies.containsKey(ORDERED)){
      os = (OrderedStrategy) defaultStrategies.get(ORDERED);
    }else{
      os = OrderColumnMapping.getInstance();
    }
    AssociationStrategy oneToOne;
    if(defaultStrategies.containsKey(ONE_ONE)){
      oneToOne = (AssociationStrategy) defaultStrategies.get(ONE_ONE);
    }else{
      oneToOne = StandardAssociationMapping.getInstance();
    }
    AssociationStrategy oneToMany;
    if(defaultStrategies.containsKey(ONE_MANY)){
      oneToMany = (AssociationStrategy) defaultStrategies.get(ONE_MANY);
    }else{
      oneToMany = StandardAssociationMapping.getInstance();
    }
    AssociationStrategy manyToMany;
    if(defaultStrategies.containsKey(MANY_MANY)){
      manyToMany = (AssociationStrategy) defaultStrategies.get(MANY_MANY);
    }else{
      manyToMany = StandardAssociationMapping.getInstance();
    }
    while(it.hasNext()){
      MAssociation association = (MAssociation) it.next();
      MAssociationEnd one = association.getConnection(0);
      MAssociationEnd two = association.getConnection(1);
      // no associationStrategy set
      if(associationStrategies.get(association) == null){
        int oneUpper = one.getMultiplicity().getUpper();
        int twoUpper = two.getMultiplicity().getUpper();
        if(oneUpper == 1 && twoUpper == 1){  // 1:1 _ association ?
          oneToOne.map(association, classToTables, associationTables, navigationGuides, classViews);
        }else if(oneUpper == 1 && (twoUpper > 1 || twoUpper < 0)){  // 1: n association?
            oneToMany.map(association, classToTables, associationTables, navigationGuides, classViews);
        }else if((oneUpper > 1 || oneUpper < 0) && twoUpper == 1){  // n: 1 asoociation ?
            oneToMany.map(association, classToTables, associationTables, navigationGuides, classViews);
        }else {                          // n:m association !
            manyToMany.map(association, classToTables, associationTables, navigationGuides, classViews);
        }
      }else{                            // other
        ((AssociationStrategy) associationStrategies.get(association)).map(association,
        classToTables, associationTables, navigationGuides, classViews);
      }
      // map ordering, if neccessary
      if(one.getOrdering() == MOrderingKind.ORDERED)
        if(orderStrategies.get(one) == null){
          os.map(one, classToTables, associationTables, classViews);
        }else{
          ((OrderedStrategy) orderStrategies.get(one)).map(one,
          classToTables, associationTables, classViews);
        }
      if(two.getOrdering() == MOrderingKind.ORDERED)
        if(orderStrategies.get(two) == null){
          os.map(two, classToTables, associationTables, classViews);
        }else{
          ((OrderedStrategy) orderStrategies.get(two)).map(two,
          classToTables, associationTables, classViews);
        }
    }
    Enumeration en = classToTables.keys();
    while(en.hasMoreElements()){
      tables.addAll((List) classToTables.get(en.nextElement()));
    }
    tables.addAll(associationTables.values());
  }
}
