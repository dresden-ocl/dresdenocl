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
  private String theScript;
  private int constraintCount = 0;
  private boolean scriptGenerated = false;
  private SQLBuilder theSQLBuilder;
  
  private static String CONSTRAINT_NAME = "CON_6";

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

    System.err.println("DDL is determined for target system: Oracle 8i");
    System.err.println("DDL Generator running ...");

    try {
    	theDDLGenerator.createDDL(new ORMappingImp(XmiParser.createRoughModel(src, src)), new OracleSQLBuilder());
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
  public void createDDL(ORMapping orm, SQLBuilder sqlb) {
  	Iterator i = orm.tables().iterator();
        theSQLBuilder = sqlb;
        sqlb.reset();

  	while (i.hasNext()) {
  		createTable((Table)i.next());
  	}

  	i = orm.tables().iterator();
  	while (i.hasNext()) {
  		setForeignKeys((Table)i.next());
  	}

        theScript = theSQLBuilder.getCode();
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

  	theSQLBuilder.beginTable(t.getTableName());
  	for (int i=0; i<cols.length; i++) {
  		// column 
  		theSQLBuilder.addColumn(cols[i], t.getColumnType(cols[i]), t.isPrimaryKeyColumn(cols[i]));

  		// column separator
  		if (i < (cols.length-1)) {
  			theSQLBuilder.addColumnSeparator();
  		}
  	}
  	theSQLBuilder.endTable();
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
                        theSQLBuilder.addFKConstraint(CONSTRAINT_NAME + constraintCount,
                                                      t.getTableName(),
                                                      cols[i], 
                                                      t.getForeignTable(cols[i]), 
                                                      t.getForeignColumn(cols[i]));  			
 		}
	}
  }
}