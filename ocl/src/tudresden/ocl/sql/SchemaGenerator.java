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

import java.util.List;
import java.util.HashSet;
import java.util.Set;
import tudresden.ocl.codegen.decl.Table;
//import tudresden.ocl.sql.SQLDirector;
//import tudresden.ocl.sql.SQLBuilder;
//import tudresden.ocl.sql.ORMapping;

/**
 * Implementation of SQLDirector
 * uses the data provided by an Implementation of the ORMapping
 * interface to generate SQL-scripts for creating database tables
 * according to the SQL DDL provided
 * The actual SQL code depends on the SQLBuilder that must be provided to the SchemaGenerator.
 *
 * @see SQLBuilder
 * @see tudresden.ocl.codegen.decl.Table
 *
 * @author Andrea Kling
 * @version 2.0 (reimplemented a non-startable version, using new features of Tables)
 * */


public class SchemaGenerator implements SQLDirector{

  private ORMapping mapping;
  private SQLBuilder builder;
  //private StringBuffer theCode;

  /**
   * @param mapping the object relational mapping information
   * @param builder an SQLBuilder providing information on the SQL DDL
   * for construction of SQL scripts
   */
  public SchemaGenerator(ORMapping mapping, SQLBuilder builder){
    this.mapping = mapping;
    this.builder = builder;
    //theCode = new StringBuffer();
    builder.reset();
  }

   /**
   * @param builder an SQLBuilder providing information on the SQL DDL
   * for construction of SQL scripts
   */
  public void setBuilder(SQLBuilder builder){
    this.builder = builder;
    builder.reset();
  }

   /**
    * initiates the construction of the SQL script
    */
  public void construct(){
    //theCode = new StringBuffer();
    builder.reset();
    List tables = mapping.tables();
    for(int i=0; i<tables.size(); i++){
      Table t = (Table) tables.get(i);
      builder.beginTable(t.getTableName());
      String[] columns = t.getColumns();
      for ( int c=0; c<columns.length; c++ ) {
        if(c>0) builder.addColumnSeparator();
        if (t.isPrimaryKeyColumn(columns[c])){
         builder.addColumn(columns[c], t.getColumnType(columns[c]), true);
        }else{
          builder.addColumn(columns[c], t.isOptional(columns[c]),
          t.getColumnType(columns[c]));
        }
      }
      builder.endTable();
      builder.endStatement();
    }
    for(int i=0; i<tables.size(); i++){
      Table t = (Table) tables.get(i);
      String[] columns = t.getForeignKeyColumns();
      Set doneColumns = new HashSet();
      for(int c = 0; c<columns.length; c++){
        if( !doneColumns.contains(columns[c])){
          builder.addFKConstraint("FK_"+t.getTableName()+c,
          t.getTableName(), t.getAllForeignKeyColumns(columns[c]),
          t.getReferredTable(columns[c]).getTableName(),
          t.getReferredTable(columns[c]).getPrimaryKeyColumns());
          builder.endStatement();
          String[] fullFK = t.getAllForeignKeyColumns(columns[c]);
          for(int a = 0; a<fullFK.length; a++)
            doneColumns.add(fullFK[a]);
        }
      }
    }
  }

   /**
    * returns the SQL script
    */
  public String getCode(){
    return builder.getCode();
  }
}
