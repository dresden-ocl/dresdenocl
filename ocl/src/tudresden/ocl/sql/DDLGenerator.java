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

import tudresden.ocl.codegen.decl.*;
import tudresden.ocl.check.types.xmifacade.*;

import java.util.*;
import java.io.*;

/**
 * A DDL (Data Definition Language) generator. The generator takes a ORMapping implementation
 * as input and generates a script that contains a number of table definitions
 * including primary and foreign key references.
 * Which tables are going to be created is determined by the implementation of
 * ORMapping, which does the actual object relational mapping.
 * The DDLGenerator just queries the information produced by the implementation of
 * ORMapping and generates the appropriate DDL script.
 * The generated DDL script is executable on Oracle 8i. To adapt the Generator for
 * other database systems, just subclass DDLGenerator and replace the String
 * variables that contain the DDL tokens.
 * @author Sten Loecher
 * @see ORMapping
 */
class DDLGenerator {
  private String ddlScript;
  private StringBuffer theScript;
  private boolean indentTypes = true;
  private int indentionWidth = 20;
  private int constraintCount = 0;
  private boolean scriptGenerated = false;

  protected String BEGIN_CREATE_TABLE	= "create table ";
  protected String BEGIN_TABLE_BODY	= "\n(\t";
  protected String REL_PROP_SEP		= "\n,\t";
  protected String END_TABLE_BODY	= "\n)";
  protected String END_CREATE_TABLE	= ";\n\n";

  protected String TAB_CON_PRIM_KEY	= " primary key";
  protected String BEGIN_ALTER_TABLE	= "alter table ";
  protected String ADD_CONSTRAINT	= " add constraint ";
  protected String CONSTRAINT_NAME	= "CON_";
  protected String BEGIN_FOREIGN_KEY	= "\nforeign key (";
  protected String END_FOREIGN_KEY	= ") ";
  protected String BEGIN_REFERENCES	= " references ";
  protected String REFERENCES_SEP	= "(";
  protected String END_REFERENCES	= ")";
  protected String END_ALTER_TABLE	= ";\n\n";

  protected String TYPE_FOR_BOOLEAN	= " NUMBER(1)";
  protected String TYPE_FOR_CHAR	= " VARCHAR2(1)";
  protected String TYPE_FOR_BYTE	= " NUMBER(3)";
  protected String TYPE_FOR_SHORT	= " NUMBER(5)";
  protected String TYPE_FOR_INT		= " NUMBER(10)";
  protected String TYPE_FOR_LONG	= " NUMBER(19)";
  protected String TYPE_FOR_FLOAT	= " NUMBER";
  protected String TYPE_FOR_DOUBLE	= " NUMBER";
  protected String TYPE_FOR_STRING	= " VARCHAR2(255)";

  /**
   * For command line and debugging operations only.
   * Generates a SQL DDL script from a xmi source and stores it to
   * the specified destination.
   * @param arg[0] url of a xmi source file
   * @param arg[1] the destination and name of the target file
   */
  public static void main(String[] arg) {
    String src, dest;
    DDLGenerator theDDLGenerator = new DDLGenerator();

    if ((arg.length > 1) && (arg[0] != null) && (arg[1] != null)) {
    	// command line operation
    	src = arg[0];
    	dest = arg[1];
    } else {
    	// use default files for debbuging run
    	src = DDLGenerator.class.getResource("test_diagramm_argo07.xmi").toString();
    	dest = "sql.ddl";

        System.err.println("Source file is: " + src);
    }

    System.err.println("DDL Generator running ...");

    try {
    	theDDLGenerator.createDDL(new ORMappingImp(XmiParser.createRoughModel(src, src)));
    } catch (Exception e) {
    	System.err.println("Cannot create DDL script:"  + e.toString());
    }

    try {
    	FileWriter fw = new FileWriter(dest);
        fw.write(theDDLGenerator.getDDLScript());
        fw.close();
    } catch (Exception e) {
    	System.err.println("Cannot write sql.ddl:" + e.toString());
    }

    System.err.println("\n...done.");
  }

  /**
   * @return the DDL script
   * @exception IllegalStateException if no script was generated before calling this methode
   */
  public String getDDLScript()
  throws IllegalStateException {
  	if (!scriptGenerated) throw new IllegalStateException();
  	return theScript.toString();
  }

  /**
   * Creates the DDL script.
   * @param xmiSourceURL the url of the xmi source file
   */
  public void createDDL(ORMapping orm) {
  	Iterator i = orm.tables().iterator();
  	theScript = new StringBuffer();

  	while (i.hasNext()) {
  		createTable((Table)i.next());
  	}

  	i = orm.tables().iterator();
  	while (i.hasNext()) {
  		setForeignKeys((Table)i.next());
  	}

  	scriptGenerated = true;
  }

  /**
   * Generates the table definition including the primary key constraints for a table.
   * @param t the table for which the definitions should be created
   */
  private void createTable(Table t) {
  	String cols[] = t.getColumns();
  	String colAttType, indentStr;
  	int indent;

  	theScript.append(BEGIN_CREATE_TABLE + t.getTableName() + BEGIN_TABLE_BODY);
  	for (int i=0; i<cols.length; i++) {
  		// column name
  		theScript.append(cols[i]);

  		// separator spaces for proper indention
  		if (indentTypes) {
  			indentStr = new String();
  			indent = indentionWidth - cols[i].length();
  			if (indent <= 0) {
  				indent = 1;
  			}
  			for (int j=0; j<indent; j++) {
  				indentStr += " ";
  			}
  			theScript.append(indentStr);
  		}

  		// column type
  		colAttType = t.getColumnType(cols[i]);
  		if (colAttType.equals("boolean")) {
  			theScript.append(TYPE_FOR_BOOLEAN);
  		} else if (colAttType.equals("char")) {
  			theScript.append(TYPE_FOR_CHAR);
  		} else if (colAttType.equals("byte")) {
  			theScript.append(TYPE_FOR_BYTE);
	  	} else if (colAttType.equals("short")) {
  			theScript.append(TYPE_FOR_SHORT);
	  	} else if (colAttType.equals("int")) {
  			theScript.append(TYPE_FOR_INT);
	  	} else if (colAttType.equals("long")) {
  			theScript.append(TYPE_FOR_LONG);
  		} else if (colAttType.equals("float")) {
  			theScript.append(TYPE_FOR_FLOAT);
  		} else if (colAttType.equals("double")) {
  			theScript.append(TYPE_FOR_DOUBLE);
  		} else if (colAttType.equals("Boolean")) {
  			theScript.append(TYPE_FOR_BOOLEAN);
  		} else if (colAttType.equals("Character")) {
  			theScript.append(TYPE_FOR_CHAR);
  		} else if (colAttType.equals("Byte")) {
  			theScript.append(TYPE_FOR_BYTE);
  		} else if (colAttType.equals("Short")) {
  			theScript.append(TYPE_FOR_SHORT);
  		} else if (colAttType.equals("Integer")) {
  			theScript.append(TYPE_FOR_INT);
  		} else if (colAttType.equals("Long")) {
  			theScript.append(TYPE_FOR_LONG);
  		} else if (colAttType.equals("Float")) {
  			theScript.append(TYPE_FOR_FLOAT);
  		} else if (colAttType.equals("Double")) {
  			theScript.append(TYPE_FOR_DOUBLE);
  		} else if (colAttType.equals("String")) {
  			theScript.append(TYPE_FOR_STRING);
  		}

  		// primary key
  		if (t.isPrimaryKeyColumn(cols[i])) {
  			theScript.append(TAB_CON_PRIM_KEY);
  		}

    		// column separator
  		if (i < (cols.length-1)) {
  			theScript.append(REL_PROP_SEP);
  		}
  	}
  	theScript.append(END_TABLE_BODY + END_CREATE_TABLE);
  }

  /**
   * Generates alter table statements to create foreign key references.
   * @param t the table for which the alter table statements should be created
   */
  private void setForeignKeys(Table t) {
  	String cols[] = t.getColumns();

  	for (int i=0; i<cols.length; i++) {
  		if (t.isForeignKeyColumn(cols[i])) {
  			constraintCount++;
  			theScript.append(BEGIN_ALTER_TABLE + t.getTableName());
  			theScript.append(ADD_CONSTRAINT + CONSTRAINT_NAME + constraintCount);
  			theScript.append(BEGIN_FOREIGN_KEY + cols[i] + END_FOREIGN_KEY);
  			theScript.append(BEGIN_REFERENCES + t.getForeignTable(cols[i]) + REFERENCES_SEP + t.getForeignColumn(cols[i]) + END_REFERENCES);
  			theScript.append(END_ALTER_TABLE);
 		}
	}
  }
}