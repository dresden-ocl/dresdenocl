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
import tudresden.ocl.codegen.decl.Table;
import tudresden.ocl.codegen.decl.ObjectView;
import ru.novosoft.uml.foundation.core.*;
import ru.novosoft.uml.foundation.data_types.MOrderingKind;

/**
 * mapping Strategy for ordered Associationends
 * adds a sequence column to the main table of the associationend type
 * @author Andrea Kling
 * */
public class OrderColumnMapping implements OrderedStrategy{

  private static OrderColumnMapping myInstance;

  private OrderColumnMapping(){}

  public static OrderColumnMapping getInstance(){
    if(myInstance == null)
      myInstance = new OrderColumnMapping();
    return myInstance;
  }

 /**
  * adds an order column to the main table of the associationend type
  *
  * @param end the ordered association end
  * @param classToTables contains a List of Table for every MClassifier
  * the first Table in List is used as main table
  * @param associationTable contains a Table for every MAssociation needing
  * an association table for realization
  * @param classViews contains an ObjectView for every MClassifier
  *
  * @see tudresden.ocl.codegen.decl.Table
  * @see tudresden.ocl.codegen.decl.ObjectView
  * */
  public synchronized void map(MAssociationEnd end, Map classToTables,
    Map associationTables, Map classViews){
      int endUpper = end.getMultiplicity().getUpper();
    if(end.getMultiplicity().getUpper() == 1 ||
    end.getOrdering() != MOrderingKind.ORDERED) return;
    Table t = null;
    if(end.getOppositeEnd().getMultiplicity().getUpper() == 1){
      t = (Table) ((List) classToTables.get(end.getType())).get(0);
    }else{
      t = (Table) associationTables.get(end.getAssociation());
    }
    t.addColumn(null, "int", end.getName().toUpperCase()+"_ORDERING");
    ObjectView ov = (ObjectView) classViews.get(end.getType());
    ov.addColumn(null, end.getName().toUpperCase()+"_ORDERING", t);
  }

  /**
   * a short description of the strategy
   * */
  public String toString(){
    return "add sequencing column (integer)";
  }
}
