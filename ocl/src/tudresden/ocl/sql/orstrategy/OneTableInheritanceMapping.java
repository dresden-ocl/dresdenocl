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
package tudresden.ocl.sql.orstrategy;

import java.util.*;
import ru.novosoft.uml.foundation.core.*;
import tudresden.ocl.codegen.decl.Table;
import tudresden.ocl.codegen.decl.ObjectView;

/**
 * an Inheritance strategy mapping a whole inheritance tree into one
 * common table
 *
 * @author Andrea Kling
 * */
public class OneTableInheritanceMapping implements InheritanceStrategy{

  private static OneTableInheritanceMapping myInstance;
  private List tables;
  private Map classToTables;
  private Map classViews;
  private ObjectView ov;

  private OneTableInheritanceMapping(){}

  public static OneTableInheritanceMapping getInstance(){
    if(myInstance == null)
      myInstance = new OneTableInheritanceMapping();
    return myInstance;
  }

  /**
   * maps an inheritance tree into one table
   * ObjectViews for every classifier show only relevant columns
   * but can not exclude rows of other subclasses
   *
   * @param root the root of an inheritance tree
   * @param classToTables contains a List of Table for every MClassifier
   * @param classViews contains an ObjectView for every MClassifier
   *
   * @see tudresden.ocl.codegen.decl.Table
   * @see tudresden.ocl.codegen.decl.ObjectView
   * */
  public synchronized void map(MClassifier root, Map classToTables, Map classViews){
    this.classToTables = classToTables;
    this.classViews = classViews;
    ov = (ObjectView) classViews.get(root);
    tables = (List) classToTables.get(root);
    Table t = (Table) tables.get(0);
    if(tables == null) return;
    getAttributes(root, ov);
  }

  private void getAttributes(MClassifier parent, ObjectView ov){
    List l = parent.getChildren();
    if(l.size() == 0){
      Collection dependencies = parent.getSupplierDependencies();
      if(dependencies.size() == 0) return;
      Iterator it = dependencies.iterator();
      while(it.hasNext()){
        MDependency realize = (MDependency) it.next();
        if(realize instanceof MAbstraction && realize.getStereotype() != null)
           if(realize.getStereotype().getName().toLowerCase().trim().equals("realize"))
             l.addAll(realize.getClients());
      }
    }
    Iterator it = l.iterator();
    while(it.hasNext()){
      MClassifier child = (MClassifier) it.next();
      ObjectView childOV = ov.copy();
      childOV.setViewNames("OV_"+child.getName().toUpperCase(),
        child.getName());
      Iterator attributes = child.getFeatures().iterator();
      while(attributes.hasNext()){
        MFeature attrib = (MFeature) attributes.next();
        if(attrib instanceof MAttribute){
          boolean contained = false;
          for(int i=0; i<tables.size(); i++){
            if(((Table) tables.get(i)).attributes().contains(attrib.getName()))
              contained = true;
          }
          if(! contained){
            Table t = (Table) tables.get(0);
            t.addColumn(attrib.getName(), ((MAttribute) attrib).getType().getName(),
              attrib.getName().toUpperCase(), false);
            t.setOptional(attrib.getName().toUpperCase());
            childOV.addColumn(attrib.getName(),
              attrib.getName().toUpperCase(), t);
          }
        }
      }
      classToTables.put(child, tables);
      classViews.put(child, childOV);
      getAttributes(child, childOV);
    }
  }

   /**
    * a short description of the mapping method
    * */
  public String toString(){
    return "all subclasses in one superclass table";
  }

}
