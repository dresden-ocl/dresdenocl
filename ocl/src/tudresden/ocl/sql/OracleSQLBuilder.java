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
		
	// views
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
		
	// select-from-where statements
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
}
