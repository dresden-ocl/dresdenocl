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
 * This interface specifies methodes to build SQL code.
 * It should be implemented by classes to build database specific
 * statements. This interface realizes the builder interface of the
 * Builder pattern from Gamma. The director part is realized by
 * implementations of the SQLDirector interface. The product is a 
 * String. See the OracleSQLBuilder for an example how to implement a SQLBuilder.
 * Basically, the product should be build by concatenating single parts.
 * @see SQLDirector
 */
public interface SQLBuilder {
	
        /**
         *  @return the product that was build (database specific DDL)
         */
	public String getCode(); 
        
        /**
         *  Resets the Builder to an empty product.
         */
	public void reset(); 
	
        // tables
        public void beginTable(String tableName);
        public void addColumn(String colName, String colType, boolean pk);
        public void addColumnSeparator();
        public void endTable();
        public void addFKConstraint(String conName, String tableName, String colName, String fkTable,String fkColName);
        
	// views
        public void createView(String name, boolean alias); 
	public void addAlias(String name); 
        public void endView();
        
        // trigger
        public void createAssertionReplacement(String triggerName, String tableName, String viewName, String errMsg);
        public void createECATriggerTemplate(String triggerName, String tableName, String viewName);
  
	// select-from-where statements
	public void createSelect(); 
	public void addColumn(String name);
	public void createFrom();
	public void addTable(String name);
	public void createWhere();
	
	// operators
	public void createUnion();	
	public void addEquation(String op1, String op2);
        public void addAnd();
}
