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
	Helper class to replace alias definitions for tables.
 */
public class TableQualifierReplacer extends DepthFirstAdapter {
	
	private String oldAlias = null;
	private String newAlias = null; 
		
	public void setAlias(String oldAlias, String newAlias) {
		this.oldAlias = oldAlias;
		this.newAlias = newAlias;
	}		
	
	public void inAColumn(AColumn node) {
		String a = node.getTableQualifier().toString().trim();
		if ((a == null) && (oldAlias != null)) return;
		if (((a == null) && (oldAlias == null)) ||
		    ((a != null) && (oldAlias != null) && (a.equals(oldAlias)))) {
		    if (newAlias != null) {
		    	node.setTableQualifier(new TIdentifier(newAlias));
		    } else {
		    	node.setTableQualifier(null);
		    }
		}
	}	
}