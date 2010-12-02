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
	Normalization step to optimize constant subselects  in where clauses:		
 */
public class WhereSelectElimination extends DepthFirstAdapter {
	
	private Stack tables = new Stack();
	
	public void inAQuerySpecQueryExpression(AQuerySpecQueryExpression node) {
		PTableReference ptr;
		String table = "$$$";
		AFromClause afc = (AFromClause)node.getFromClause();
		List list = afc.getTableReference();
		
		if (list.size() == 1) {
			ptr = (PTableReference)list.get(0);
			
			if (ptr instanceof ATableNameTableReference) {
				table = ((ATableNameTableReference)ptr).getTableName().toString().trim();
			}
		}
		
		tables.push(table);
	}

	public void outAQuerySpecQueryExpression(AQuerySpecQueryExpression node) {
		tables.pop();
	}
	
	public void inAInBooleanExpression(AInBooleanExpression node) {
		PQueryExpression pqe;
		PTableReference ptr;
		PUnaryExpression pue;
		PSelectList psl;
        PSelectSubListItem pssli;
        PBooleanExpression pbe, pbe1;
        PWhereClause pwc;
        TableQualifierReplacer tqr;
		AColumn ac;
		String t1, t2, a2, c1, c2;
		List list;
		SelectAliasFinder saf;
		
		// alias for T2 ?
		pqe = node.getQueryExpression();
		if (!(pqe instanceof AQuerySpecQueryExpression)) return;
		
		list = ((AFromClause)((AQuerySpecQueryExpression)pqe).getFromClause()).getTableReference();
        if (list.size() != 1) return;
        
        ptr = (PTableReference)list.get(0);
        if (!(ptr instanceof ATableNameTableReference)) return;
        t2 = ((ATableNameTableReference)ptr).getTableName().toString().trim();
        if (((ATableNameTableReference)ptr).getCorrelationName() == null) return;
		a2 = ((ATableNameTableReference)ptr).getCorrelationName().toString().trim();
		if (a2 == null) return;
				
		// T1 = T2 ?
		t1 = (String)tables.peek();
		if (!(t1.equals(t2))) return;
		
		// c1 = c2 ?
		pue = node.getUnaryExpression();
        if (!(pue instanceof AColumnUnaryExpression)) return;
        ac = (AColumn)((AColumnUnaryExpression)pue).getColumn();
        if (ac.getTableQualifier() != null) return;
        if (ac.getAsClause() != null) return;
        c1 = ac.getColumnName().toString().trim();
        
        psl = ((ASelectClause)((AQuerySpecQueryExpression)pqe).getSelectClause()).getSelectList();
        if (!(psl instanceof ASubListSelectList)) return;
        list = ((ASubListSelectList)psl).getSelectSubListItem();
        if (list.size() != 1) return;
        pssli = (PSelectSubListItem)list.get(0);
        if (!(pssli instanceof AColumnSelectSubListItem)) return;
        ac = (AColumn)((AColumnSelectSubListItem)pssli).getColumn();
        if (!(ac.getTableQualifier().toString().trim().equals(a2))) return;
        if (ac.getAsClause() != null) return;
        c2 = ac.getColumnName().toString().trim();
        
        if (!(c1.equals(c2))) return;
        		
		// alias in use
		pwc = ((AQuerySpecQueryExpression)pqe).getWhereClause();
		if (pwc == null) return;
		pbe = ((AWhereClause)pwc).getBooleanExpression();
		
		saf = new SelectAliasFinder();
		saf.setAlias(a2);
		pbe.apply(saf);
		
		if (saf.aliasInUse()) {
			// alias is in use
			if (saf.selectInUse()) {
				// replace AInBooleanExpression by AExistsBooleanExpression
				if (pbe instanceof AAndBooleanExpression) {
					list = ((AAndBooleanExpression)pbe).getBooleanExpression();
				} else {
					list = new ArrayList();
					list.add(pbe);
				}
				pbe1 = new ARExpBooleanExpression(
					new AEqRelationalExpression(
						new AValueNumericExpression(new AColumnUnaryExpression(new AColumn(null, new TIdentifier(c1), null))),
						new AValueNumericExpression(new AColumnUnaryExpression(new AColumn(new TIdentifier(a2), new TIdentifier(c2), null)))
					)
				);
				list.add(0, pbe1);
				((AWhereClause)pwc).setBooleanExpression(new AAndBooleanExpression(list));
				pbe1 = new AExistsBooleanExpression(pqe);							
				node.replaceBy(pbe1);
				pbe1.apply(new WhereSelectElimination());
			} else {
				// eliminate AInBooleanExpression and redundant select clause
				tqr = new TableQualifierReplacer();
				tqr.setAlias(a2, null);
				pbe.apply(tqr);
				node.replaceBy(pbe);
				pbe.apply(new WhereSelectElimination());
			}
		} else {
			// alias is not in use -> replace this node bey boolean expression of where clause
			node.replaceBy(pbe);
			pbe.apply(new WhereSelectElimination());
		}
		
	}
	
}