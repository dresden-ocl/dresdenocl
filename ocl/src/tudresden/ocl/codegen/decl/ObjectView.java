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
package tudresden.ocl.codegen.decl;

import java.util.Vector;

/**
 * An ObjectView is a View on relational tables of a database containing
 * all columns that are part of the relational mapping of a class
 * that has been mapped to the database during an object-relational mapping
 *
 * very complex attributes as attributes containing muliple values or
 * class references are excluded from the view
 *
 * @author Andrea Kling
 * */
public class ObjectView{
  private String viewName, className;
  private Vector columns;
  private Vector connections;

   /**
    * creates a new ObjectView named viewName for a classifier
    * named className
    * */
  public ObjectView(String viewName, String className){
    this.viewName = viewName;
    this.className = className;
    columns = new Vector();
    connections = new Vector();
  }

   /**
    * adds a Column to the view
    *
    * @param attribute the name of the class attribute the new column
    * is representing (partly) in the view
    * @param columnName the name of the column (in its original table)
    * @param table the original table the column is viewed from
    * */
  public void addColumn(String attribute, String columnName, Table table)
  throws IllegalArgumentException{
    if(getColumn(columnName, table) != null)
      throw new IllegalArgumentException("ObjectView: Column already exists: "+
      table.getTableName()+"."+columnName);
    columns.add(new Column(attribute, columnName, table));
  }

   /**
    * adds a restriction to the 'where'-clause of the view,
    * the restriction is formed like: <BR>
    * table1Name.column1 = table2Name.column2<BR>
    * this is used to realize foreign key references
    * */
  public void addConnection(String column1, Table table1, String column2, Table table2)
  throws IllegalArgumentException{
    /*if(!table1.getColumnType(column1).toLowerCase().
    equals(table2.getColumnType(column2).toLowerCase()) ||
    (!table1.getColumnType(column1).toLowerCase().equals("id") &&
    !table2.getColumnType(column2).toLowerCase().equals("id")))
      throw new IllegalArgumentException("ObjectView: Types do not match: "+
      table1.getTableName()+"."+column1+" and "+table2.getTableName()+
      "."+column2);*/
    connections.add(new Connection(column1, table1, column2, table2));
  }

   /**
    * defines a column of the view as being part of the 'primary key'
    * of the view, meaning the 'primary key' will be referred for example
    * by Guides to associationends of Associations between classifiers
    *
    * @see tudresden.ocl.codegen.decl.Guide
    * */
  public void setPrimaryKey(String column, Table table){
    Column c = getColumn(column, table);
    if(c != null)
      c.pk = true;
  }

   /**
    * returns all Information about all defined columns
    * the field is formed like:<BR>
    * Object[x][0] = String classAttribute<BR>
    * Object[x][1] = String columnName (in the original Table)<BR>
    * Object[x][2] = Table originalTable of the Column
    *
    * @see tudresden.ocl.codegen.decl.Table
    * */
  public Object[][] getColumns(){
    Object[][] cols = new Object[columns.size()][3];
    for(int i=0; i<columns.size(); i++){
      Column c = (Column) columns.get(i);
      cols[i][0] = c.attribute;
      cols[i][1] = c.name;
      cols[i][2] = c.table;
    }
    return cols;
  }

  /**
   * removes all columns from view that are part of mapping of
   * the given class attribute.
   * May be used for attributes with collection types
   * */
  public void removeColumnsFor(String attribute){
    for(int i=columns.size()-1; i>=0; i--){
      Column c = (Column) columns.get(i);
      if(c.attribute.equals(attribute))
        columns.remove(c);
    }
  }

   /**
    * removes a certain column from View
    *
    * @param name the name of the Column as used in addColumn()
    * @param table the original Table of the column as used in addColumn()
    * */
  public void removeColumn(String name, Table table){
    for(int i=columns.size()-1; i>=0; i--){
      Column c = (Column) columns.get(i);
      if(c.name.equals(name) && c.table == table)
        columns.remove(c);
    }
  }

  /**
   * returns all columns the class attribute given was mapped to,
   * null if the attribute isn't contained in the view<BR>
   * Object[x][0] = String column name (as used in addColumn())<BR>
   * Object[x][1] = Table original table of colum
   * */
  public Object[][] getAttributeColumns(String attribute){
    Vector v = new Vector();
    for(int i=columns.size()-1; i>=0; i--){
      Column c = (Column) columns.get(i);
      if(c.attribute.equals(attribute))
        v.add(c);
    }
    if(v.size() == 0) return null;
    Object[][] attrCols = new Object[v.size()][2];
    for(int i=0; i<v.size(); i++){
      Column c = (Column) columns.get(i);
      attrCols[i][0] = c.name;
      attrCols[i][1] = c.table;
    }
    return attrCols;
  }

  /**
   * resets the primary key flag of all column to false
   * */
  public void removePrimaryKey(){
    for(int i=columns.size()-1; i>=0; i--){
      Column c = (Column) columns.get(i);
      c.pk = false;
    }
  }

   /**
    * @param viewName the name of the ObjectView
    * @param className the name of the classifier the ObjectView represents
    * */
  public void setViewNames(String viewName, String className){
    this.viewName = viewName;
    this.className = className;
  }

   /**
    * @return the name of the view
    * */
  public String getName(){
    return viewName;
  }

  /**
   * @return the name of the classifier this view represents
   * */
  public String getClassifier(){
    return className;
  }

  /**
   * returns all foreign key connections for the where clause of this view
   * contents of Object[][]: <BR>
   * Object[x][0] = String column1 name<BR>
   * Object[x][1] = Table table of column1<BR>
   * Object[x][2] = String column2 name<BR>
   * Object[x][3] = Table table of column2<BR>
   * the connection should be:
   * table1name.column1 = table2name.column2
   *
   * @see tudresden.ocl.codegen.decl.Table
   * */
  public Object[][] getConnections(){
    Object[][] o = new Object[connections.size()][4];
    for(int i=0; i<connections.size(); i++){
      Connection c = (Connection) connections.get(i);
      o[i][0] = c.column1;
      o[i][1] = c.table1;
      o[i][2] = c.column2;
      o[i][3] = c.table2;
    }
    return o;
  }

  /**
   * returns a representation of this views 'primary key' meaning
   * row identifying columns
   * the String is formed '(pk1, pk2,..)'
   * */
  public String getPrimaryKeyRepresentation(){
    StringBuffer str = new StringBuffer();
    for ( int i=0; i<columns.size(); i++) {
      Column c = (Column) columns.get(i);
      if(c.pk == true)
        str.append(", "+c.name);
    }
    return "("+str.substring(2)+")";
  }

   /**
    * sets the columns of this view
    *
    * @param columns a Vector of Column
    * */
  protected void setColumns(Vector columns){
    this.columns = columns;
  }

    /**
     * sets the connections of this View
     *
     * @param connections a Vector of Connection
     * */
  protected void setConnections(Vector connections){
    this.connections = connections;
  }

     /**
      * a cind of cloneof the current ObjectView containing the same
      * columns and connections
      * */
  public ObjectView copy(){
    ObjectView ov = new ObjectView(viewName, className);
    ov.setColumns((Vector) columns.clone());
    ov.setConnections((Vector) connections.clone());
    return ov;
  }

  private Column getColumn(String name, Table table){
    Column col = null;
    for(int i=0; i<columns.size(); i++){
      Column c = (Column) columns.get(i);
      if(name.equals(c.name) && table == c.table)
        col = c;
    }
    return col;
  }

  private class Connection{
    public Connection(String column1, Table table1, String column2, Table table2){
      this.column1 = column1;
      this.table1 = table1;
      this.column2 = column2;
      this.table2 = table2;
    }
    String column1, column2;
    Table table1, table2;
  }

  private class Column{
    public Column(String attribute, String name, Table table){
      this.attribute = attribute;
      this.name = name;
      this.table = table;
      connections = new Vector();
    }
    String attribute, name;
    Table table;
    Vector connections;
    boolean pk;
  }
}
