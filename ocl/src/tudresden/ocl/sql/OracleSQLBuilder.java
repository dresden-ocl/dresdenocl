/*
Copyright (C) 2001  Sten Loecher

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
import java.io.*;


/**
 * Implements the SQLBuilder interface for Oracle specific SQL code.
 * See the documentation of the SQLBuilder interface for more information.
 * @see SQLBuilder
 * @see TypeManager
 *
 * @author Sten Löcher
 * @version 1.1 (revised by Andrea Kling 2002, added usage of TypeManager)
 */
public class OracleSQLBuilder implements SQLBuilder {
        /*protected String TYPE_FOR_BOOLEAN = " NUMBER(1)";
        protected String TYPE_FOR_CHAR = " VARCHAR2(1)";
        protected String TYPE_FOR_BYTE = " NUMBER(3)";
        protected String TYPE_FOR_SHORT	= " NUMBER(5)";
        protected String TYPE_FOR_INT = " NUMBER(10)";
        protected String TYPE_FOR_LONG = " NUMBER(19)";
        protected String TYPE_FOR_FLOAT	= " NUMBER";
        protected String TYPE_FOR_DOUBLE = " NUMBER";
        protected String TYPE_FOR_STRING = " VARCHAR2(255)";
        protected String TYPE_FOR_REAL = " NUMBER";
        protected String TYPE_FOR_DATE = " DATE";
        protected String TYPE_FOR_ID = " NUMBER(19)";
          */
	private StringBuffer theCode;
	private int nextAliasPos;
	private int aliasCount;
  private Hashtable datatypes;
  private String pks = null;
  private HashSet reserved;

	public OracleSQLBuilder() {
		theCode = new StringBuffer();
    datatypes = new Hashtable();
    datatypes.put(TypeManager.INTEGER, "NUMBER(10)");
    datatypes.put(TypeManager.SMALLINT, "NUMBER(5)");
    datatypes.put(TypeManager.BIT_1, "NUMBER(1)");
    datatypes.put(TypeManager.LONG, "NUMBER(19)");
    datatypes.put(TypeManager.ID, "NUMBER(19)");
    datatypes.put(TypeManager.CHAR, "VARCHAR2(1)");
    datatypes.put(TypeManager.VARCHAR, "VARCHAR2(255)");
    datatypes.put(TypeManager.FLOAT, "NUMBER");
    datatypes.put(TypeManager.DOUBLE, "NUMBER");
    datatypes.put(TypeManager.DATE, "DATE");
    datatypes.put(TypeManager.TIMESTAMP, "DATE");
    reserved = new HashSet();
    try{
        BufferedReader r = new BufferedReader(new InputStreamReader(
          //getClass().getResourceAsStream("oracleReserved.txt")
          OracleSQLBuilder.class.getResourceAsStream("oracleReserved.txt")
        ));
        if(r != null)
            try{
              String line="";
              while((line = r.readLine())!=null)
                reserved.add(line.trim().toLowerCase());
            }finally{
                r.close();
            }
    }
    catch (IOException e){
        throw new RuntimeException(e.getMessage());
    }
	}

	public String getCode() {
		return theCode.toString();
	}

	public void reset() {
		theCode = new StringBuffer();
	}

	private void append(String s) {
		theCode.append(s);
	}

	private void insert(String s) {
		theCode.insert(nextAliasPos, s);
	}

	public void createView(String name, boolean alias) {
    append("create or replace view " + check(name));
		if (alias) {
			append(" ()");
			nextAliasPos = theCode.length()-1;
			aliasCount = 0;
		}
		append(" as\n");
	}

	public void addAlias(String name) {
		if (aliasCount > 0) {
			insert(", " + name);
			nextAliasPos += name.length() + 2;
		} else {
			insert(name);
			nextAliasPos += name.length();
		}
		aliasCount++;
	}

	public void createSelect() {
		append("select ");
		aliasCount = 0;
	}

	public void addColumn(String name) {
    if (aliasCount > 0) append(", " + check(name)); else append(check(name));
		aliasCount++;
	}

	public void createFrom() {
		append(" from ");
		aliasCount = 0;
	}

	public void addTable(String name) {
    if (aliasCount > 0) append(", " + check(name)); else append(check(name));
		aliasCount++;
	}

	public void createWhere() {
		append("\nwhere ");
	}

	public void createUnion() {
		append("\nunion\n");
	}

	public void addEquation(String op1, String op2) {
		append(op1 + "=" + op2);
	}

	public void addAnd() {
		append("\nand ");
	}

        public void createAssertionReplacement(String triggerName,String tableName,String viewName,String errMsg) {
                append("create or replace trigger " + check(triggerName) + "\n");
                append("after insert or update or delete on " + check(tableName) + "\n");
                append("declare tmp number;\n");
                append("begin\n");
                append("  select NVL(COUNT(*),0) into tmp from " + check(viewName) + ";\n");
                append("  if (tmp > 0) then\n");
                append("     raise_application_error(-20000,'" + errMsg +"');\n");
                append("  end if;\n");
                append("end;\n");
        }

        public void createECATriggerTemplate(String triggerName,String tableName,String viewName) {
                append("create or replace trigger " + check(triggerName) + "\n");
                append("after insert or update or delete on " + check(tableName) + "\n");
                append("declare tmp number;\n");
                append("begin\n");
                append("  select NVL(COUNT(*),0) into tmp from " + check(viewName) + ";\n");
                append("  if (tmp > 0) then\n");
                append("     -- todo: add action code here\n");
                append("  end if;\n");
                append("end;\n");
        }

        public void beginTable(String tableName) {
                append("create table " + check(tableName) + "\n(");
        }

        public void addColumn(String colName,String colType,boolean pk) {
               String indentStr = new String(" ");
               String oraColName = check(colName);
           int indent = 20 - oraColName.length();
               //String oraColType = "";

               // indention between name and type
               if (indent <= 0) {
  	            indent = 1;
               } else {
  		    for (int j=0; j<indent; j++) {
  			indentStr += " ";
  		    }
  	       }
/*
               // determine corresponding Oracle type to colType
               	if (colType.equals("boolean")) {
  			oraColType = TYPE_FOR_BOOLEAN;
  		} else if (colType.equals("char")) {
  			oraColType = TYPE_FOR_CHAR;
  		} else if (colType.equals("byte")) {
  			oraColType = TYPE_FOR_BYTE;
	  	} else if (colType.equals("short")) {
  			oraColType = TYPE_FOR_SHORT;
	  	} else if (colType.equals("int")) {
  			oraColType = TYPE_FOR_INT;
	  	} else if (colType.equals("long")) {
  			oraColType = TYPE_FOR_LONG;
  		} else if (colType.equals("float")) {
  			oraColType = TYPE_FOR_FLOAT;
  		} else if (colType.equals("double")) {
  			oraColType = TYPE_FOR_DOUBLE;
  		} else if (colType.equals("Boolean")) {
  			oraColType = TYPE_FOR_BOOLEAN;
  		} else if (colType.equals("Character")) {
  			oraColType = TYPE_FOR_CHAR;
  		} else if (colType.equals("Byte")) {
  			oraColType = TYPE_FOR_BYTE;
  		} else if (colType.equals("Short")) {
  			oraColType = TYPE_FOR_SHORT;
  		} else if (colType.equals("Integer")) {
  			oraColType = TYPE_FOR_INT;
  		} else if (colType.equals("Long")) {
  			oraColType = TYPE_FOR_LONG;
  		} else if (colType.equals("Float")) {
  			oraColType = TYPE_FOR_FLOAT;
  		} else if (colType.equals("Double")) {
  			oraColType = TYPE_FOR_DOUBLE;
  		} else if (colType.equals("String")) {
  			oraColType = TYPE_FOR_STRING;
                } else if (colType.equals("Real")) {
  			oraColType = TYPE_FOR_REAL;
                } else if (colType.equals("Date")) {
  			oraColType = TYPE_FOR_DATE;
                } else if (colType.equals("id")){
        oraColType = TYPE_FOR_ID;
  */
    String oraColType = (String) datatypes.get(TypeManager.getInstance().getSQLType(colType));
    if(oraColType == null)
      oraColType = colType;
    if(! datatypes.contains(oraColType)){
      System.err.println("OracleSQLBuilder unknown type: " + colType);
    }
    append("\t" + oraColName + indentStr +oraColType);
    if (pk) {
      if(pks == null){
        pks = new String(oraColName);
      }else{
        pks += ", "+oraColName;
      }
      //append("\t" + "primary key");
    }
        }

  private String check(String word){
    String oraColName = new String(word);
    if(reserved.contains(word.trim().toLowerCase())){
      oraColName = "MY_"+oraColName;
      System.err.println("OracleSQLBuilder reserved word: "+word+
      " changed for: "+oraColName);
    }
    return oraColName;
  }

        public void addColumn(String colName, boolean optional, String colType){
          addColumn(colName, colType, false);
          if(optional)
            append(" null");
          else
            append(" not null");
        }

        public void endTable() {
          if(pks != null){
            append("\n, PRIMARY KEY ("+pks+")");
            pks = null;
          }
                append("\n);\n\n");
        }

        public void addColumnSeparator() {
                append("\n,");
        }

        public void addFKConstraint(String conName, String tableName, String colName, String fkTable,String fkColName) {
                append("alter table " + check(tableName) + " add constraint " + check(conName) + "\n");
                append("foreign key ");
                if(colName.startsWith("(")){
                  append(colName + " references " + check(fkTable));
                }else{
                  append("(" + check(colName) + ") references " + check(fkTable));
                }
                if(fkColName.startsWith("(")){
                  append(fkColName + ";\n\n");
                }else{
                  append("(" + check(fkColName) + ");\n\n");
                }
        }

        public void addFKConstraint(String conName, String tableName, String[] colName, String fkTable,String[] fkColName) {
          if(colName.length != fkColName.length){
            System.err.println("OracleSQLBuilder "+conName+" foreign key columns do not match.");
            return;
          }
          String colNames = new String("("+check(colName[0]));
          String fkColNames = new String("("+check(fkColName[0]));
          for(int i=1; i<colName.length; i++){
            colNames += ", "+check(colName[i]);
            fkColNames += ", "+check(fkColName[i]);
          }
          addFKConstraint(conName, tableName, colNames+")", fkTable, fkColNames+")");
        }

        public void endView() {
                append(";");
        }

        public void endStatement(){}

        public String getDescription(){
          return "Oracle";
        }

}
