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

import java.util.List;
import java.util.Map;
import java.util.Vector;
import ru.novosoft.uml.foundation.core.MAttribute;
import ru.novosoft.uml.foundation.core.MClassifier;
import tudresden.ocl.codegen.decl.Table;
import tudresden.ocl.codegen.decl.ObjectView;

/**
 * an 'object-relational mapping strategy' for attributes containing
 * modeled classes as type
 * */
public class ClassTypeMapping implements DatatypeStrategy{
  private MClassifier type;
  private Vector objectViews;
  private String attributeName;
  private Table ownerTable;
  private Map classViews;

   /**
    * @param type the MClassifier representing an Attributes type
    * */
  public ClassTypeMapping(MClassifier type){
    this.type = type;
    objectViews = new Vector();

  }

  /**
   * Adds the primary key of the type's main table as foreign key
   * to the table containing the attribute and to the ObjectView of
   * the attribute's class. If other classes inherit attribute the
   * reference is added to their ObjectViews, too.
   * The main table is the first Table contained in a classifiers list of table
   * in classToTables.
   *
   * @param attribute the attribute having a modeled classifier as type
   * @param classToTables a Map of MClassifier -> (List of Table)
   * @param classViews a Map of MClassifier -> ObjectView
   *
   * @see tudresden.ocl.codegen.decl.Table
   * */
  public void map(MAttribute attribute, Map classToTables, Map classViews){
    this.classViews = classViews;
    MClassifier owner = attribute.getOwner();
    attributeName = attribute.getName();
    List ownerTables = (List) classToTables.get(owner);
    boolean found = false;
    ownerTable = null;
    for (int i=0; i<ownerTables.size() && !found; i++){
      String str = ((Table) ownerTables.get(i)).getAttributeColumn(attribute.getName());
      if(str != null){
        ownerTable = (Table) ownerTables.get(i);
        found = true;
      }
    }
    if(ownerTable == null)
      ownerTable = (Table) ownerTables.get(0);
    objectViews.add(classViews.get(owner));
    fillObjectViews(owner);
    try{
      ownerTable.removeColumn(ownerTable.getAttributeColumn(attribute.getName()));
      for(int i=0; i<objectViews.size(); i++)
        ((ObjectView) objectViews.get(i)).removeColumnsFor(attribute.getName());
    }catch(Exception ex){}
    Table typeTable = (Table) ((List) classToTables.get(type)).get(0);
    String[] typePK = typeTable.getPrimaryKeyColumns();
    String[] tFK = new String[typePK.length];
    for(int i=0; i<typePK.length; i++){
      ownerTable.addColumn(attribute.getName(), typeTable.getColumnType(typePK[i]),
      typeTable.getTableName()+"_"+typePK[i], false);
      tFK[i] = typeTable.getTableName()+"_"+typePK[i];
      for(int a=0; a<objectViews.size(); a++){
        ((ObjectView) objectViews.get(a)).addColumn(attribute.getName(),
        typeTable.getTableName()+"_"+typePK[i], ownerTable);
      }
    }
    ownerTable.setForeignKey(tFK, typeTable, typePK);
  }

  /**
   * adds references to ObjectViews of subclasses of the attribute's class
   * */
  private void fillObjectViews(MClassifier owner){
    List l = owner.getChildren();
    for (int i=0; i<l.size(); i++){
      ObjectView ov = (ObjectView) classViews.get(l.get(i));
      Object[][] attCols = ov.getAttributeColumns(attributeName);
      if(attCols != null)
        if(attCols.length == 1 && ((Table) attCols[0][1]) == ownerTable)
          objectViews.add(ov);
      fillObjectViews((MClassifier) l.get(i));
    }
  }


  /**
   * @return the classifier that is the attribute's type
   * */
  public String toString(){
    return type.getName();
  }
}
