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

import tudresden.ocl.codegen.CodeFragment;

/**
 *	Objects of this class represent SQL code fragments.
 *	Since by time certain information became important for applications 
 *	which are using the SQL code generator the SQLCodeFragment was introduced.
 *	The following information must be provided for SQL code fragments:<br>
 *	- the names of all tables which are queried;<br> 
 *	- the names of all primary key colums of the constrained table.<br>
 *	Be aware that the inherited getAdditionalInfo methode will return  
 *	the involved tables. This is to provide backward compatibility.
 * 	@author Sten Loecher
 */
public class SQLCodeFragment extends DeclarativeCodeFragment {
	
	protected String[] primaryKeyCols;
	protected String[] involvedTables;
	
	/**
	 *	Creates a new SQLCodeFragment.
	 *	@param name the name of the SQL code fragment
	 *	@param type the constrained type
	 *	@param code the SQL code
	 *	@param involvedTables the names of all queried tables in the SQL statement
	 *	@param primaryKeyCols the names of the primary key columns of the constrained table
	 */
	public SQLCodeFragment(String name, String type, String code, String[] involvedTables, String[] primaryKeyCols) {
		super(name, type, code, involvedTables);
		this.primaryKeyCols = primaryKeyCols;
		this.involvedTables = involvedTables;
	}
	
	/**
	 *	@return the names of the involved tables
	 */
	public String[] getInvolvedTables() {
		return involvedTables;
	}
	 
	/**
	 *	@return the names of the primary key columns of the constrained table
	 */
	public String[] getPrimaryKeyCols() {
		return primaryKeyCols;
	}
}