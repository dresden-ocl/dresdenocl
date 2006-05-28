/*
 * View.java
 * 
 * Copyright (c) 2000 Sten Loecher
 * Slightly modified 2005 by Florian Heidenreich
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

package tudresden.ocl20.codegen.sql.orm;

import java.util.Collections;
import java.util.List;


/**
 * A virtual table, which contains rows defined by an SQL Select statement 
 * in terms of other tables. The class is a direct subclass of the Table 
 * class and therefore provides the full range of functionality of a Table 
 * including foreign key and primary key handling. Additionally, the table
 * names and the SQL Statement can be stored in a View object.
 * 
 * @author Sten Loecher, Florian Heidenreich
 * @deprecated See tudresden.ocl20.codegen.decl.tools.sql
 */
public class View extends Table {
	/** 
	 * A List that contains all table names, which are used to specify
	 * the SQL statement.
	 */
	private List<String> tableNames;
	/**
	 * The SQL statement.
	 */
	private String statement;
	/**
	 * A description of the view object.
	 */
	private String description;
	
	/**
	 * @param name the name of the view
	 */
	public View(String name) {
		this(name, null, null, null);
	}
		
	/**
	 * @param name the name of the view
	 * @param statement the SQL statement (is allowed to be null, if it is not of interest)
	 * @param tableNames a List that contains all table names, which are used to specify the SQL statement
	 *  		     (is allowed to be null, if it is not of interest)
	 * @param description a description of this object (is allowed to be null, if it is not of interest)
	 */
	public View(String name, String statement, List<String> tableNames, String description) {
		super(name);
		this.statement = statement;
		this.tableNames = tableNames;
		this.description = description;
	}
	
	/**
	 * @param statement the SQL statement (is allowed to be null)
	 */
	public void setStatement(String statement) {
		this.statement = statement;
	}
	
	/**
	 * @param tableNames a List that contains all table names, which are used to specify the SQL statement
	 */
	public void setTableNames(List<String> tableNames) {
		this.tableNames = tableNames;
	}
		
	/**
	 * @return a List that contains all table names, which are used to specify the SQL statement
	 * @exception IllegalStateException if no table names are specified
	 */
	public List<String> getTableNames() 
	throws IllegalStateException {
		if (tableNames == null) throw new IllegalStateException("no table names specified in View: " + getTableName());
		return Collections.unmodifiableList(tableNames);
	}
	
	/**
	 * @return the SQL statement
	 * @exception IllegalStateException if no statement is specified
	 */
	public String getStatement() 
	throws IllegalStateException {
		if (statement == null) throw new IllegalStateException("no statement specified in View: " + getTableName());
		return new String(statement);
	}
	
	/**
	 * @return the description of this object
	 * @exception IllegalStateException if no description is specified
	 */	
	public String getDescription() {
		if (description == null) throw new IllegalStateException("no description specified in View: " + getTableName());
		return new String(description);
	}	
	
	/**
	 *	@return a select statement that results from an union over all tables, presupposed they have similar columns 
	 *	@return columns a List of column names (String) to be used; they must match the defined columns; if columns is null, all defined columns will be used
	 *	@exception IllegalArgumentException if columns is not null, but contains undefined columns
	 */
	public String getUnionOverTables(List<String> columns) 
	throws IllegalArgumentException {
		String result = "(";
		String cols[];
		
		for (int i=0; (columns != null) && (i<columns.size()); i++) {
			if (!isColumn((String)columns.get(i))) throw new IllegalArgumentException("Columns must match defined columns !");
		}
		
		if (columns == null) {
			cols = getColumns();
		} else {
			cols = (String[])columns.toArray(new String[columns.size()]);
		}	 
		
		for (int i=0; i<tableNames.size(); i++) {
			result += "select ";
			for (int k=0; k<cols.length; k++) {
				if (k==0) result += cols[k]; else result += "," + cols[k];
			}
			result += " from " + tableNames.get(i);
			if (i < (tableNames.size()-1)) result += "\nunion\n";
		}
				
		return result + ")";
	}
	
	/**
     * @return a string representation of a view object
     */
    public String toString() {
		StringBuffer retVal = new StringBuffer();
		String pk[] = getPrimaryKeyColumns(),
	  	fk[] = getForeignKeyColumns(),
	   	ac[] = getColumns();

    	retVal.append("view (\n");
    	retVal.append("name: " + getTableName() + "\n");
    	retVal.append("description: " + description + "\n");
    	retVal.append("statement: " + statement + "\n");
    	retVal.append("table names: " + tableNames.toString() + "\n");

    	retVal.append("primary key columns:\n");
    	for (String pkName : pk) {
    		retVal.append(pkName);
    		if (getColumnType(pkName) != null) retVal.append(" of type " + getColumnType(pkName));
    		if (getColumnAttribute(pkName) != null) retVal.append(" associated to " + getColumnAttribute(pkName));
    		retVal.append("\n");
    	}

    	retVal.append("foreign key columns:\n");
    	for (String fkName : fk) {
    		retVal.append(fkName);
    		if (getColumnType(fkName) != null) retVal.append(" of type " + getColumnType(fkName));
    		if (getColumnAttribute(fkName) != null) retVal.append(" associated to " + getColumnAttribute(fkName));
    		retVal.append("\n");
    	}

    	retVal.append("non key columns:\n");
    	for (String colName : ac) {
    		if ((!isPrimaryKeyColumn(colName)) &&(!isForeignKeyColumn(colName))) {
    			retVal.append(colName);
    			if (getColumnType(colName) != null) retVal.append(" of type " + getColumnType(colName));
    			if (getColumnAttribute(colName) != null) retVal.append(" associated to " + getColumnAttribute(colName));
    			retVal.append("\n");
    		}
    	}

    	retVal.append(")");

    	return retVal.toString();
    }
}
