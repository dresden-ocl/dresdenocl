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
	Normalization step to eliminate references to correlation variables
	in derived tables in from clauses, where the correlation variables are 
	defined outside the from clause.
	
	<pre>
	select c1 from ( select c2 from t2 where p2) where p1		
																
	is normalized to
	
	if ((c1 == c2) || (c1 == some function on *)) {
		select c1 from t2 where p1 and p2
	}
	</pre>
	
 */
public class SelectElimination extends DepthFirstAdapter {
	
	public void inAQuerySpecQueryExpression(AQuerySpecQueryExpression node) {
			List tabRef, andList;
			PTableReference ptr;
			PQueryExpression pqe;
			AQuerySpecQueryExpression subquery;
			PSelectList nodeSelectList, subquerySelectList, funcSelectList, compSelectList;
			PSelectSubListItem pssli1, pssli2;
			PBooleanExpression nodeBoolExp, subqueryBoolExp, pbe3;
			PWhereClause nodePWhereClause, subqueryPWhereClause;
		
	        // first check if there is a subquery in the from clause
	        tabRef = ((AFromClause)node.getFromClause()).getTableReference();
	        if (tabRef.size() != 1) return;
	        ptr = (PTableReference)tabRef.get(0);
	        if (!(ptr instanceof ADerivedTableTableReference)) return;
	        pqe = ((ADerivedTableTableReference)ptr).getQueryExpression();
	        if (!(pqe instanceof AQuerySpecQueryExpression)) return;
	        subquery = (AQuerySpecQueryExpression)pqe;
	        
	        // if there is a subqery, compare select list
			nodeSelectList = ((ASelectClause)node.getSelectClause()).getSelectList(); 
	        subquerySelectList = ((ASelectClause)subquery.getSelectClause()).getSelectList(); 
	        
	        if (nodeSelectList instanceof AFunctionSelectList) {
	        	funcSelectList = ((AFunctionSelectList)nodeSelectList).getParameter();
	        	if (funcSelectList instanceof ASubListSelectList) {
	        		if (!(funcSelectList.toString().equals(subquerySelectList.toString()))) return;
	        	} else if (funcSelectList instanceof AAsteriskSelectList) {
	        	} else {
	        		return;
	        	}
	        } else {
	        	if (!(nodeSelectList.toString().equals(subquerySelectList.toString()))) return;
	        }
	        	        	        
	        // if select list is identical, eliminate outer query
	        nodePWhereClause = node.getWhereClause();
	        subqueryPWhereClause = subquery.getWhereClause();
	        subquerySelectList.replaceBy(nodeSelectList);
	        node.replaceBy(subquery);	        
	        
	        // adapt where clause of inner query
	        if (nodePWhereClause != null) {
		        nodeBoolExp = ((AWhereClause)nodePWhereClause).getBooleanExpression();
		        
		        if (subqueryPWhereClause == null) {
		        	subqueryPWhereClause = new AWhereClause(nodeBoolExp);
		        	subquery.setWhereClause(subqueryPWhereClause);
		        	
		        } else {
		        	subqueryBoolExp = ((AWhereClause)subqueryPWhereClause).getBooleanExpression();
		        	
		        	if (subqueryBoolExp instanceof AAndBooleanExpression) {
			        	((List)((AAndBooleanExpression)subqueryBoolExp).getBooleanExpression()).add(nodeBoolExp);
			        } else {
			        	andList = new ArrayList();
			        	andList.add(nodeBoolExp.clone());
			        	andList.add(subqueryBoolExp.clone());
			        	pbe3 = new AAndBooleanExpression(andList);
			        	subqueryBoolExp.replaceBy(pbe3);
			        }
		        }
	     	}
	        
	        
	        //inAQuerySpecQueryExpression(subquery);
	        subquery.apply(this);
	}
}