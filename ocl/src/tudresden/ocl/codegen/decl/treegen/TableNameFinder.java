/*
Copyright (C) 2002  Sten Loecher

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
package tudresden.ocl.codegen.decl.treegen;

import java.util.*;
import tudresden.ocl.codegen.decl.*;
import tudresden.ocl.codegen.decl.treegen.node.*;
import tudresden.ocl.codegen.decl.treegen.analysis.*;

/**
	This class is a service class for the SQLCodeGenerator to
	determine all involved table names in a SQL query.
	
	@see tudresden.ocl.codegen.decl.treegen.SQLCodeGenerator
	@author Sten Loecher
*/
public class TableNameFinder extends DepthFirstAdapter {
	
	private List tableNames;
	
	public String[] getInvolvedTables() {
		return (String[])tableNames.toArray(new String[tableNames.size()]);
	}
	
	public void inStart(Start node) {
        tableNames = new ArrayList();
    }	    
    
    public void inATableNameTableReference(ATableNameTableReference node) {
        tableNames.add(node.getTableName().toString().trim());
    }
        	
}