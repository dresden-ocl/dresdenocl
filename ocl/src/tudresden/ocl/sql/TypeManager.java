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

/**
 * This class maps a number of commonly used datatypes to something
 * like standard SQL datatypes. It is used to reduce the number of
 * possible datatypes used in softwaredesign to a defined Set of
 * datatypes every implementation of SQLBuilder should know.
 *
 * @see tudresden.ocl.sql.SQLBuilder
 *
 * @author Andrea Kling
 * */
public class TypeManager{

  private static TypeManager myInstance;
  private Hashtable types;
  private List l;
  public final static String INTEGER   = "INTEGER";
  public final static String SMALLINT  = "SMALLINT";
  public final static String BIT_1     = "BIT(1)";
  public final static String LONG      = "NUMERIC(19)";
  public final static String ID        = "NUMERIC(19) IDENTITY";
  public final static String CHAR      = "CHARACTER(1)";
  public final static String VARCHAR   = "VARCHAR(255)";
  public final static String FLOAT     = "FLOAT";
  public final static String DOUBLE    = "DOUBLE PRECISION";
  public final static String DATE      = "DATE";
  public final static String TIMESTAMP = "TIMESTAMP";

  private TypeManager(){
    types = new Hashtable();
    types.put("int", INTEGER);
    types.put("integer", INTEGER);
    types.put("numeric", INTEGER);
    types.put("smallint", SMALLINT);
    types.put("byte", SMALLINT);
    types.put("short", SMALLINT);
    types.put("boolean", BIT_1);
    types.put("bool", BIT_1);
    types.put("bit", BIT_1);
    types.put("long", LONG);
    types.put("char", CHAR);
    types.put("character", CHAR);
    types.put("string", VARCHAR);
    types.put("varchar", VARCHAR);
    types.put("float", FLOAT);
    types.put("real", FLOAT);
    types.put("double", DOUBLE);
    types.put("date", DATE);
    types.put("datetime", TIMESTAMP);
    types.put("timestamp", TIMESTAMP);
    types.put("id", ID);
    l = new Vector();
    l.add("boolean");
    l.add("byte");
    l.add("char");
    l.add("date");
    l.add("double");
    l.add("float");
    l.add("integer");
    l.add("long");
    l.add("string");
    l.add("timestamp");
  }

  public static TypeManager getInstance(){
    if(myInstance == null){
      myInstance = new TypeManager();
    }
    return myInstance;
  }

 /**
  * maps the datatype given to one of the datatypes given in the
  * static fields. If no matching datatype can be found the type is returned
  * */
  public String getSQLType(String type){
    String str= (String) types.get(type.toLowerCase().trim());
    if(str == null)
      return type;
    return str;
  }

 /**
  * returns a list of all defined datatypes. oOly one instance of
  * every Type is contained, meaning as 'integer' is contained in
  * the List 'int' is not.
  *
  * @return a List of String
  * */
  public List getTypes(){
    return l;
  }

  /**
   * @return true if type can be mapped to a standard datatype
   * */
  public boolean isDefined(String type){
    String str= (String) types.get(type.toLowerCase().trim());
    if(str == null)
      return false;
    return true;
  }
}
