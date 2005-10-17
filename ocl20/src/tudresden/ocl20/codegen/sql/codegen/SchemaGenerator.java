/*
 * SchemaGenerator.java
 * 
 * Copyright (c) 2001 Sten Loecher
 *
 * This file is part of the Dresden OCL2.0 Toolkit
 * created at Technische Universitaet Dresden (TUD), Germany.
 * Visit http://dresden-ocl.sourceforge.net/ for details.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA  02111-1307  USA
 *
 */

package tudresden.ocl20.codegen.sql.codegen;

import java.util.Iterator;

import tudresden.ocl20.codegen.sql.orm.ORMapping;
import tudresden.ocl20.codegen.sql.orm.Table;

/**
 * A DDL (Data Definition Language) generator. The generator takes a ORMapping implementation
 * as input and generates a script that contains a number of table definitions
 * including primary and foreign key references.
 * Which tables are going to be created is determined by the implementation of
 * ORMapping, which does the actual object relational mapping.
 * The SchemaGenerator just queries the information produced by the implementation of
 * ORMapping and generates the appropriate DDL script.
 * The actual SQL code depends on the SQLBuilder that must be provided to the SchemaGenerator.
 * 
 * @author Sten Loecher
 * @see ORMapping
 * @see SQLBuilder
 */
public class SchemaGenerator implements SQLDirector {
	private String theScript;
	private int constraintCount = 0;
	private boolean scriptGenerated = false;
	private SQLBuilder theSQLBuilder = null;
	private ORMapping theORMapping = null;
	
	private static String CONSTRAINT_NAME = "CON_";
	
	/**
	 * For command line and debugging operations only.
	 * Generates a SQL DDL script from a xmi source and stores it to
	 * the specified destination.
	 * @param arg[0] url of a xmi source file
	 * @param arg[1] the destination and name of the target file
	 */
	/*  public static void main(String[] arg) {
	 String src, dest;
	 SchemaGenerator theSchemaGenerator = new SchemaGenerator();
	 OclModel model = null;
	 
	 if ((arg.length > 1) && (arg[0] != null) && (arg[1] != null)) {
	 // command line operation
	  src = arg[0];
	  dest = arg[1];
	  } else {
	  // use default files for debbuging run
	   src = (ClassLoader.getSystemClassLoader().getResource("PoseidonProjects/uni.xmi")).toString();
	   dest = "sql.ddl";
	   
	   System.err.println("Source file is: " + src);
	   }
	   
	   System.err.println("DDL is determined for target system: Oracle 8i");
	   System.err.println("DDL Generator running ...");
	   
	   try {
	   model = new OclModel(MetaModelConst.UML15, src);
	   model.beginTrans(true);
	   theSchemaGenerator.createDDL(new ORMappingImpl(model), new OracleSQLBuilder());
	   } catch (Exception e) {
	   System.err.println("Cannot create DDL script:"  + e.toString());
	   }
	   finally {
	   if (model != null) {
	   model.endTrans(false);
	   model.close();
	   }
	   }
	   
	   try {
	   FileWriter fw = new FileWriter(dest);
	   fw.write(theSchemaGenerator.getDDLScript());
	   fw.close();
	   } catch (Exception e) {
	   System.err.println("Cannot write sql.ddl:" + e.toString());
	   }
	   
	   System.err.println("\n...done.");
	   }*/
	
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
	 * @exception IllegalStateException if no SQLBuilder nor ORMapping was set
	 */
	public void createDDL() throws IllegalStateException {
		if ((theSQLBuilder == null) || (theORMapping == null))
			throw new IllegalStateException(
					"Missing SQLBuilder and/or ORMapping.");

		Iterator<Table> i = theORMapping.getTables().iterator();
		theSQLBuilder.reset();

		while (i.hasNext()) {
			createTable(i.next());
		}

		i = theORMapping.getTables().iterator();
		while (i.hasNext()) {
			setForeignKeys(i.next());
		}

		theScript = theSQLBuilder.getCode();
		scriptGenerated = true;
	}
	
	/**
	 * Creates the DDL script.
	 * @param orm a table schema
	 * @param sqlb a SQLBuilder
	 */
	public void createDDL(ORMapping orm, SQLBuilder sqlb) {
		theORMapping = orm;
		theSQLBuilder = sqlb;
		createDDL();
	}
	
	/**
	 * Generates the table definition including the primary key constraints for a table.
	 * @param t the table for which the definitions should be created
	 */
	private void createTable(Table t) {
		String cols[] = t.getColumns();
		
		theSQLBuilder.beginTable(t.getTableName());
		for (int i = 0; i < cols.length; i++) {
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
	
	/**
	 * @param orm an implementation of the ORMapping Interface that provides a table schema
	 */
	public void setORMapping(ORMapping orm) {
		theORMapping = orm;
	}
	
	/**
	 * @param sqlb a builder used by the director to build database specific code
	 */
	public void setBuilder(SQLBuilder sqlb) {
		theSQLBuilder = sqlb;
	}
	
	/**
	 * Does the construction of the SQL code.
	 */
	public void construct() {
		createDDL();
	}
	
	/**
	 * @return the resulting SQL code from the construction process
	 */ 
	public String getCode() {
		return getDDLScript();
	}  
}
