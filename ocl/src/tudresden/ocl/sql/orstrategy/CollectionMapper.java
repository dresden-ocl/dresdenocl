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
import tudresden.ocl.sql.TypeManager;

/**
 * a DatatypeStrategy for mapping unknown datatypes representing
 * any kind of ordered or unordered collection to it's own table
 * in the database schema
 *
 * @author Andrea Kling
 * */
public class CollectionMapper implements DatatypeStrategy{

  private List dataTypes, attributeNames;
  private Map classViews;

 /**
  * @param attributeNames a List of String containing column names for the
  * collection table
  * @param dataTypes a List of String containing the column datatypes
  * corresponding attributeNames
  * */
  public CollectionMapper(List attributeNames, List dataTypes){
    this.attributeNames = attributeNames;
    this.dataTypes = dataTypes;
  }

 /**
  * Maps attribute to its own collection table. the attribute is removed
  * from the ObjectView of it's classifier and of all ObjectViews of this
  * classifiers subclasses
  *
  * @param attribute an Attribute having an collection datatype
  * @param classToTables a Map containing for every MClassifier a List of Table
  * @param classViews contains an ObjectView for every MClassifier
  *
  * @see tudresden.ocl.codegen.decl.ObjectView
  * */
  public void map(MAttribute attribute, Map classToTables, Map classViews){
    this.classViews = classViews;
    MClassifier owner = attribute.getOwner();
    List ownerTables = (List) classToTables.get(owner);
    removeFromOV(owner, attribute.getName());
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
    String[] ownerPK = ownerTable.getPrimaryKeyColumns();
    String[] tFK = new String[ownerPK.length];
    Table t = new Table(owner.getName().toUpperCase()+"_"+attribute.getName().toUpperCase());
    for(int i=0; i<ownerPK.length; i++){
      t.addColumn(null, ownerTable.getColumnType(ownerPK[i]),
      ownerTable.getTableName()+"_"+ownerPK[i], true);
      tFK[i] = ownerTable.getTableName()+"_"+ownerPK[i];
    }
    t.setForeignKey(tFK, ownerTable, ownerPK);
    t.addColumn(null, "int", "SEQUENCE", true);
    for(int i=0; i<attributeNames.size(); i++){
      if(! TypeManager.getInstance().isDefined((String) dataTypes.get(i)) ){
        found = false;
        MClassifier m = null;
        Iterator it = classToTables.keySet().iterator();
        while(it.hasNext() && !found){
          m = (MClassifier) it.next();
          if(m.getName().equals((String)dataTypes.get(i)))
            found = true;
        }
        if(m != null){
          Table mTable = (Table) ((List) classToTables.get(m)).get(0);
          String[] mPK = ownerTable.getPrimaryKeyColumns();
          tFK = new String[mPK.length];
          for(int a=0; a<mPK.length; a++){
            t.addColumn(null, mTable.getColumnType(mPK[a]),
            mTable.getTableName()+"_"+mPK[a], false);
            tFK[a] = mTable.getTableName()+"_"+mPK[a];
          }
          t.setForeignKey(tFK, mTable, mPK);
        }
      }else{
        t.addColumn((String) attributeNames.get(i), (String) dataTypes.get(i),
          ((String) attributeNames.get(i)).toUpperCase(), false);
      }
    }
    List l = (List) classToTables.get(attribute.getOwner());
    l.add(t);
  }

  /**
   * removes the attribute from ObjectViews
   * */
  private void removeFromOV(MClassifier owner, String attribute){
    ObjectView ov = (ObjectView) classViews.get(owner);
    try{
      ov.removeColumnsFor(attribute);
    }catch(Exception ex){}
    List l = owner.getChildren();
    for (int i=0; i<l.size(); i++)
      removeFromOV((MClassifier) l.get(i), attribute);
  }

  /**
   * a short description of the strategy
   * */
  public String toString(){
    return "external collection table";
  }
}
