/*
Copyright (C) 2000  Sten Loecher

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

import java.lang.*;
import java.util.*;

/**
 * A virtual table, which contains rows defined by an SQL Select statement 
 * in terms of other tables. The class is a direct subclass of the Table 
 * class and therefore provides the full range of functionality of a Table 
 * including foreign key and primary key handling. Additionally, the table
 * names and the SQL Statement can be stored in a View object.
 * @author Sten Loecher
 */
public class View extends Table {
	/** 
	 * A List that contains all table names, which are used to specify
	 * the SQL statement.
	 * @element-type String
	 */
	private List tableNames;
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
	 * @param tabelNames a List that contains all table names, which are used to specify the SQL statement
	 *  		     (is allowed to be null, if it is not of interest)
	 * @param description a description of this object (is allowed to be null, if it is not of interest)
	 */
	public View(String name, String statement, List tableNames, String description) {
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
	 * @param tabelNames a List that contains all table names, which are used to specify the SQL statement
	 */
	public void setTableNames(List tableNames) {
		this.tableNames = tableNames;
	}
		
	/**
	 * @return a List that contains all table names, which are used to specify the SQL statement
	 * @exception IllegalStateException if no table names are specified
	 */
	public List getTableNames() 
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
	public String getUnionOverTables(List columns) 
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
    	for (int i=0; i<pk.length; i++) {
    		retVal.append(pk[i]);
    		if (getColumnType(pk[i]) != null) retVal.append(" of type " + getColumnType(pk[i]));
    		if (getColumnAttribute(pk[i]) != null) retVal.append(" associated to " + getColumnAttribute(pk[i]));
    		retVal.append("\n");
    	}

    	retVal.append("foreign key columns:\n");
    	for (int i=0; i<fk.length; i++) {
    		retVal.append(fk[i]);
    		if (getColumnType(fk[i]) != null) retVal.append(" of type " + getColumnType(fk[i]));
    		if (getColumnAttribute(fk[i]) != null) retVal.append(" associated to " + getColumnAttribute(fk[i]));
    		retVal.append("\n");
    	}

    	retVal.append("non key columns:\n");
    	for (int i=0; i<ac.length; i++) {
    		if ((!isPrimaryKeyColumn(ac[i])) &&(!isForeignKeyColumn(ac[i]))) {
    			retVal.append(ac[i]);
    			if (getColumnType(ac[i]) != null) retVal.append(" of type " + getColumnType(ac[i]));
    			if (getColumnAttribute(ac[i]) != null) retVal.append(" associated to " + getColumnAttribute(ac[i]));
    			retVal.append("\n");
    		}
    	}

    	retVal.append(")");

    	return retVal.toString();
    }
}
