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

/** 
 * Implements the SQLBuilder interface for Oracle specific SQL code.
 */
public class OracleSQLBuilder implements SQLBuilder {
        protected String TYPE_FOR_BOOLEAN = " NUMBER(1)";
        protected String TYPE_FOR_CHAR = " VARCHAR2(1)";
        protected String TYPE_FOR_BYTE = " NUMBER(3)";
        protected String TYPE_FOR_SHORT	= " NUMBER(5)";
        protected String TYPE_FOR_INT = " NUMBER(10)";
        protected String TYPE_FOR_LONG = " NUMBER(19)";
        protected String TYPE_FOR_FLOAT	= " NUMBER";
        protected String TYPE_FOR_DOUBLE = " NUMBER";
        protected String TYPE_FOR_STRING = " VARCHAR2(255)";
    
	private StringBuffer theCode;
	private int nextAliasPos;
	private int aliasCount;
	
	public OracleSQLBuilder() {
		theCode = new StringBuffer();		
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
		append("create or replace view " + name);
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
		if (aliasCount > 0) append(", " + name); else append(name);
		aliasCount++;
	}
		
	public void createFrom() {
		append(" from ");		
		aliasCount = 0;
	}
	
	public void addTable(String name) {
		if (aliasCount > 0) append(", " + name); else append(name);
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
                append("create or replace trigger " + triggerName + "\n");
                append("after insert or update or delete on " + tableName + "\n");
                append("begin");
                append("  if ((select NVL(COUNT(*),0) from " + viewName + ") > 0) then\n");  
                append("     raise_application_error(-20000,\"" + errMsg +"\");\n");
                append("  end if;\n");
                append("end;\n");
        }
        
        public void createECATriggerTemplate(String triggerName,String tableName,String viewName) {
                append("create or replace trigger " + triggerName + "\n");
                append("after insert or update or delete on " + tableName + "\n");
                append("begin");
                append("  if ((select NVL(COUNT(*),0) from " + viewName + ") > 0) then\n");  
                append("     // todo: add action code here\n");
                append("  end if;\n");
                append("end;\n");
        }        
        
        public void beginTable(String tableName) {
                append("create table " + tableName + "\n(");
        }
        
        public void addColumn(String colName,String colType,boolean pk) {
               String indentStr = new String();
  	       int indent = 20 - colName.length();
               String oraColType = "";
  		
               // indention between name and type
               if (indent <= 0) {
  	            indent = 1;
               } else {
  		    for (int j=0; j<indent; j++) {
  			indentStr += " ";
  		    }
  	       }
               
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
  		}
            
                append("\t" + colName + indentStr + oraColType);
                if (pk) append("\t" + "primary key");
        }
        
        public void endTable() {
                append("\n);\n\n");
        }
        
        public void addColumnSeparator() {
                append("\n,");
        }
        
        public void addFKConstraint(String conName, String tableName, String colName, String fkTable,String fkColName) {
                append("alter table " + tableName + " add constraint " + conName + "\n");
                append("foreign key (" + colName + ") references " + fkTable + "(" + fkColName + ");\n\n");
        }
        
}
