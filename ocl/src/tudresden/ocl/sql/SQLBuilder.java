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
 * statements.
 */
public interface SQLBuilder {
	
	public String getCode(); 
	public void reset(); 
			
	// views
	public void createView(String name, boolean alias); 
	public void addAlias(String name); 
		
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
