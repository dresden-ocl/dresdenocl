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
import ru.novosoft.uml.foundation.core.MAttribute;
import ru.novosoft.uml.foundation.core.MClassifier;
import tudresden.ocl.codegen.decl.Table;
import tudresden.ocl.codegen.decl.ObjectView;

/**
 * DatatypeMappingStrategy exchanging unknown attribute datatypes
 * for simple datatypes, as for example provided provided by TypeManager
 *
 * @see tudresden.ocl.sql.TypeManager
 *
 * @author Andrea Kling
 * */
public class SimpleTypeMapping implements DatatypeStrategy{
  private String type;

 /**
  * @param type the name of the simple datatype tht should be attatcht to any Attribute
  * */
  public SimpleTypeMapping(String type){
    this.type = type;
  }

  public void map(MAttribute attribute, Map classToTables, Map classViews){
    MClassifier owner = attribute.getOwner();
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
    if(ownerTable == null){
      ownerTable = (Table) ownerTables.get(0);
      try{
        ownerTable.removeColumn(ownerTable.getAttributeColumn(attribute.getName()));
      }catch(Exception ex){}
      ownerTable.addColumn(attribute.getName(), type, attribute.getName().toUpperCase(),false);
      ObjectView ov = (ObjectView) classViews.get(owner);
      ov.addColumn(attribute.getName(),
        attribute.getName().toUpperCase(), ownerTable);
    }
    ownerTable.setColumnType(ownerTable.getAttributeColumn(attribute.getName()), type);

  }

  /**
   * a short description of the Strategy,.. the datatype used
   * */
  public String toString(){
    return type;
  }
}

