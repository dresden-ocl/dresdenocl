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
import ru.novosoft.uml.foundation.core.MClassifier;
import tudresden.ocl.codegen.decl.Table;
import tudresden.ocl.codegen.decl.ObjectView;

  /**
   * Inserts an additional object identity
   * column (oid) as primary key into the tables a class was mapped to
   *
   * @author Andrea Kling
   */
public class OIDKeyMapping implements KeyStrategy{

  private static OIDKeyMapping myInstance;

  private OIDKeyMapping(){
  }

  public static OIDKeyMapping getInstance(){
    if ( myInstance == null ) {
      myInstance = new OIDKeyMapping();
    }
    return myInstance;
  }

  /**
   * Assumes that MClassifier classifier has been mappped to tables,
   * but has no primary key yet. An oid column is added to the classifiers
   * main table (first table in classtoTable list). All other tables of
   * classifier get a foreign key reference to that primary key.
   * If a primary key is found in a table, no key will be set for
   * this table.
   *
   * @param classifier the class that need a primary key for its tables
   * @param classToTables a List of Table for every MClassifier
   * @param classViews contains an ObjectView for every MClassifier
   *
   * @see tudresden.ocl.codegen.decl.Table
   * @see tudresden.ocl.codegen.decl.ObjectView
   */
  public synchronized void map(MClassifier classifier, Map classToTables, Map classViews){
    if(!classToTables.containsKey(classifier)) return;
    List l = (List) classToTables.get(classifier);
    ObjectView ov = (ObjectView) classViews.get(classifier);
    Table referredTable = null;
    for( int i=0; i<l.size(); i++) {
      Table t = (Table) l.get(i);
      if(t.getPrimaryKeyColumns().length == 0) {
        if(referredTable == null){
          referredTable = t;
          t.addColumn("oid", "id", "OID", true);
          ov.addColumn("oid", "OID", t);
          ov.setPrimaryKey("OID", t);
        }else{
          t.addColumn("oid", "id", "OID", true);
          t.setForeignKey("OID", referredTable, "OID");
        }
      }
    }
  }

/**
 * a short description of the strategy
 * */
  public String toString(){
    return "additional object identity column";
  }
}



