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
	Normalization step to eliminate redundant context navigation.
	
	<pre>
	select ... from t1 as a1
	where select c1 from t2 where c2 in 
	 	   (select c3 from t3 where c4 = a1.c5)
	 
	is normalized to
	
	if ((c3 == c4 == c5) && (t3 = t1)) {
		select ... from t1 as a1
		where select c1 from t2 where c2 = a1.c5 
	}
	</pre>
 */
public class ContNavElimination extends DepthFirstAdapter {
	
	Map aliasToTable = new HashMap();
	
	public void inATableNameTableReference(ATableNameTableReference node) {
		try {
			aliasToTable.put(node.getCorrelationName().toString().trim(), node.getTableName().toString().trim());
		} catch(NullPointerException ex) {
		}		        
    }
	
	public void inAInBooleanExpression(AInBooleanExpression node) {
		PQueryExpression pqe;
		PBooleanExpression pbe;
		PWhereClause pwc;
		PRelationalExpression pre;
		PNumericExpression pneC4, pneC5;
		PUnaryExpression pue, pueC4, pueC5;
		PTableReference ptr;
		AColumn ac, acC4, acC5;
		PSelectList psl;
        PSelectSubListItem pssli;
		String c2, c3, c4, c5, t;
		List list;
		
		// join in where clause of subquery ?
		pqe = node.getQueryExpression();
		if (!(pqe instanceof AQuerySpecQueryExpression)) return;
		
		pwc = ((AQuerySpecQueryExpression)pqe).getWhereClause();
		if (pwc == null) return;
		
		pbe = ((AWhereClause)pwc).getBooleanExpression();
		if (!(pbe instanceof ARExpBooleanExpression)) return;
		pre = ((ARExpBooleanExpression)pbe).getRelationalExpression();
		if (!(pre instanceof AEqRelationalExpression)) return;
		
		pneC4 = ((AEqRelationalExpression)pre).getOp1();
		if (!(pneC4 instanceof AValueNumericExpression)) return;
		pneC5 = ((AEqRelationalExpression)pre).getOp2();
		if (!(pneC5 instanceof AValueNumericExpression)) return;
		
		pueC4 = ((AValueNumericExpression)pneC4).getUnaryExpression();
		if (!(pueC4 instanceof AColumnUnaryExpression)) return;
		pueC5 = ((AValueNumericExpression)pneC5).getUnaryExpression();
		if (!(pueC5 instanceof AColumnUnaryExpression)) return;
		
		acC4 = (AColumn)((AColumnUnaryExpression)pueC4).getColumn();
		if (acC4.getTableQualifier() != null) return;
		c4 = acC4.getColumnName().toString().trim();
		
		acC5 = (AColumn)((AColumnUnaryExpression)pueC5).getColumn();
		if (acC5.getTableQualifier() == null) return;
		c5 = acC5.getColumnName().toString().trim();
		
		if (!(c4.equals(c5))) return;
		
		// table of from clause equals of table table qualifier of c5 ?
		list = ((AFromClause)((AQuerySpecQueryExpression)pqe).getFromClause()).getTableReference();
        if (list.size() != 1) return;
        
        ptr = (PTableReference)list.get(0);
        if (!(ptr instanceof ATableNameTableReference)) return;
        t = ((ATableNameTableReference)ptr).getTableName().toString().trim();
        
        if (!(t.equals(aliasToTable.get(acC5.getTableQualifier().toString().trim())))) return;
        
         // c3 is appropriate column ?
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
        
        if (!(c3.equals(c4))) return;
        
         // c2 is appropriate column ?
        pue = node.getUnaryExpression();
        if (!(pue instanceof AColumnUnaryExpression)) return;
        ac = (AColumn)((AColumnUnaryExpression)pue).getColumn();
        if (ac.getTableQualifier() != null) return;
        if (ac.getAsClause() != null) return;
        c2 = ac.getColumnName().toString().trim();
		
		// replace AInBooleanExpression by simple join expression
		pbe = new ARExpBooleanExpression(
			new AEqRelationalExpression(
				new AValueNumericExpression(new AColumnUnaryExpression(new AColumn(null, new TIdentifier(c2), null))),
				new AValueNumericExpression(new AColumnUnaryExpression(new AColumn(acC5.getTableQualifier(), new TIdentifier(c5), null)))
			)
		);
				
		node.replaceBy(pbe);
	}
}