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
	Normalization step to eliminate redundant navigation steps.
	
	<pre>
	select c1 from t1 where c2 in
	 (select c3 from t2 where c4 in (...))
	 
	is normalized to
	
	if ((t1 == t2) && (c2 == c3)) {
		select c1 from t1 where c4 in (...)
	}
	</pre>
 */
public class RedNavElimination extends DepthFirstAdapter {
	
	public void inAQuerySpecQueryExpression(AQuerySpecQueryExpression node) {
		AWhereClause awc;
        PBooleanExpression pbe1, pbe2;
        PQueryExpression pqe;
        PTableReference ptr;
        PSelectList psl;
        PUnaryExpression pue;
        PSelectSubListItem pssli;
        AColumn ac;
        List list;
        String t1, t2, c2, c3;
        
        // navigation structure for first query ?
        awc = (AWhereClause)node.getWhereClause();
        if (awc == null) return;
        
        pbe1 = (awc).getBooleanExpression();
        if (!(pbe1 instanceof AInBooleanExpression)) return;
        
        // navigation structure for second query ?
        pqe = ((AInBooleanExpression)pbe1).getQueryExpression();
        if (!(pqe instanceof AQuerySpecQueryExpression)) return;
        
        awc = (AWhereClause)((AQuerySpecQueryExpression)pqe).getWhereClause();
        if (awc == null) return;
        
        pbe2 = (awc).getBooleanExpression();
        if (!(pbe2 instanceof AInBooleanExpression)) return;
        
        // tables of both queries are simple and equal ?
        list = ((AFromClause)node.getFromClause()).getTableReference();
        if (list.size() != 1) return;
        
        ptr = (PTableReference)list.get(0);
        if (!(ptr instanceof ATableNameTableReference)) return;
        if (((ATableNameTableReference)ptr).getCorrelationName() != null) return;
        t1 = ((ATableNameTableReference)ptr).getTableName().toString().trim();
        
        list = ((AFromClause)((AQuerySpecQueryExpression)pqe).getFromClause()).getTableReference();
        if (list.size() != 1) return;
        
        ptr = (PTableReference)list.get(0);
        if (!(ptr instanceof ATableNameTableReference)) return;
        if (((ATableNameTableReference)ptr).getCorrelationName() != null) return;
        t2 = ((ATableNameTableReference)ptr).getTableName().toString().trim();
        
        if (!(t1.equals(t2))) return;
        
        // matching columns simple and equal ?
        pue = ((AInBooleanExpression)pbe1).getUnaryExpression();
        if (!(pue instanceof AColumnUnaryExpression)) return;
        ac = (AColumn)((AColumnUnaryExpression)pue).getColumn();
        if (ac.getTableQualifier() != null) return;
        if (ac.getAsClause() != null) return;
        c2 = ac.getColumnName().toString().trim();
        
        psl = ((ASelectClause)((AQuerySpecQueryExpression)pqe).getSelectClause()).getSelectList();
        if (!(psl instanceof ASubListSelectList)) return;
        list = ((ASubListSelectList)psl).getSelectSubListItem();
        if (list.size() != 1) return;
        pssli = (PSelectSubListItem)list.get(0);
        if (!(pssli instanceof AColumnSelectSubListItem)) return;
        ac = (AColumn)((AColumnSelectSubListItem)pssli).getColumn();
        if (ac.getTableQualifier() != null) return;
        if (ac.getAsClause() != null) return;
        c3 = ac.getColumnName().toString().trim(); 
        
        if (!(c2.equals(c3))) return;
        
        // normalization possible -> eliminate redundant query
        pbe1.replaceBy(pbe2);                
    }
}