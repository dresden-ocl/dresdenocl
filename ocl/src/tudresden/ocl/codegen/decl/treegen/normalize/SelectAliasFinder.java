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
package tudresden.ocl.codegen.decl.treegen.normalize;

import java.util.*;
import tudresden.ocl.codegen.decl.*;
import tudresden.ocl.codegen.decl.treegen.node.*;
import tudresden.ocl.codegen.decl.treegen.analysis.*;

/**
	Helper class to determine for queries if they contain 
	subqueries and if a certain table alias is used.
 */
public class SelectAliasFinder extends DepthFirstAdapter {
	
	private boolean selectInUse;
	private boolean aliasInUse;
	private String  alias;
	
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public boolean selectInUse() {
		return selectInUse;
	}
	
	public boolean aliasInUse() {
		return aliasInUse;
	}
	
	public void inStart(Start node) {
		selectInUse = false;
		aliasInUse = false;
		if (alias == null) alias = "";
	}
	
	public void inAQuerySpecQueryExpression(AQuerySpecQueryExpression node) {
		selectInUse = true;
	}
	
	public void inAColumn(AColumn node) {
		if (node.getTableQualifier() != null) {
			String a = node.getTableQualifier().toString().trim();
			if (a.equals(alias)) {
				aliasInUse = true;
			}
		}
	}
}