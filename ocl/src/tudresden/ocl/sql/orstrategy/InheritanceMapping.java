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

import java.util.Map;
import java.util.List;
import java.util.Collection;
import java.util.Iterator;
import tudresden.ocl.codegen.decl.Table;
import tudresden.ocl.codegen.decl.ObjectView;
import ru.novosoft.uml.foundation.core.MClassifier;
import ru.novosoft.uml.foundation.core.MAbstraction;
import ru.novosoft.uml.foundation.core.MDependency;

/**
 * This strategy uses real inheritance in mapping. It is assumed that
 * each class is already mapped to one or more tables, according to
 * its attributes. To map inheritance each Table of a subclass
 * gets a foreign key to the Table of it's superclass so that
 * in combining a classes table with those of all superclasses you
 * get the full object with all inherited attributes.
 *
 * Handles only single inheritance so far
 * */
public class InheritanceMapping implements InheritanceStrategy{
  private static InheritanceMapping myInstance;
  private Map classToTables;
  private Map classViews;

  private InheritanceMapping(){}

  public static InheritanceMapping getInstance(){
    if ( myInstance == null ) {
      myInstance = new InheritanceMapping();
    }
    return myInstance;
  }

/**
 * To map inheritance each Table of a subclass
 * gets a foreign key to the Table of it's superclass so that
 * in combining a classes table with those of all superclasses you
 * get the full object with all inherited attributes. All Tables
 * needed for that will be contained in the the Classifiers List of Table.
 * The classifiers ObjectView already contains that combination
 *
 * @param root the class or interface that is the highest defined 'superclassifier'
 * of an inheritance tree
 * @param classToTables contains a List of Table for every MClassifier mapped
 * @param classViews contains an ObjectView for every MClassifier
 *
 * @see tudresden.ocl.codegen.decl.ObjectView
 * @see tudresden.ocl.codegen.decl.Table
 * */
  public synchronized void map(MClassifier root, Map classToTables, Map classViews){
    this.classToTables = classToTables;
    this.classViews = classViews;
    pushDownKey(root, (ObjectView) classViews.get(root));
  }

 /**
  * runs through the inheritance tree to perform mapping
  * */
  private void pushDownKey(MClassifier root, ObjectView copyDownView){
    List l = root.getChildren();
    if(l.size() == 0){
      Collection dependencies = root.getSupplierDependencies();
      if(dependencies.size() == 0) return;
      Iterator it = dependencies.iterator();
      while(it.hasNext()){
        MDependency realize = (MDependency) it.next();
        if(realize instanceof MAbstraction && realize.getStereotype() != null)
           if(realize.getStereotype().getName().toLowerCase().trim().equals("realize"))
             l.addAll(realize.getClients());
      }
    }
    Table rootTable = (Table) ((List) classToTables.get(root)).get(0);
    String[] rootPK = rootTable.getPrimaryKeyColumns();
    for(int i=0; i<l.size(); i++) {
      MClassifier child = (MClassifier) l.get(i);
      ObjectView ov = (ObjectView) classViews.get(child);
      List childTables = (List) classToTables.get(child);
      Table first = (Table) childTables.get(0);
      String[] firstPK = first.getPrimaryKeyColumns();
      boolean addPK = false;
      if(childTables.size() == 1){
        try{
          first.removePrimaryKey();
          first.removeColumn("OID");
          ov.removePrimaryKey();
          ov.removeColumn("OID", first);
          addPK = true;
        }catch(Exception ex){}
      }
      String[] firstFK = new String[rootPK.length];
      for(int c=0; c<rootPK.length; c++){
        first.addColumn(rootTable.getColumnAttribute(rootPK[c]),
          rootTable.getColumnType(rootPK[c]), "FK_"+rootPK[c], addPK);
        if(! addPK)
          ov.addColumn(rootTable.getColumnAttribute(rootPK[c]),
            rootPK[c], rootTable);
        ov.setPrimaryKey(rootPK[c], rootTable);
        firstFK[c] = "FK_"+rootPK[c];
      }
      first.setForeignKey(firstFK, rootTable, rootPK);
      childTables.addAll((List) classToTables.get(root));
      Object[][] ovCols = copyDownView.getColumns();
      for(int a=0; a<ovCols.length; a++){
        try{
         ov.addColumn((String) ovCols[a][0], (String) ovCols[a][1],
           (Table) ovCols[a][2]);
        }catch(Exception ex){}
      }
      for (int a=0; a<firstFK.length; a++)
        ov.addConnection(firstFK[a], first, rootPK[a], rootTable);
      pushDownKey(child, ov);
    }
  }

  /**
   * a short description of the mapping strategy
   * */
  public String toString(){
    return "split subclass into class and superclass tables";
  }
}
