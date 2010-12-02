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
	A printer to create SQL code for the Oracle 8i database system.
	
	@see tudresden.ocl.codegen.decl.treegen.Printer
	@author Sten Loecher
*/
public class Oracle8iPrinter extends Printer {
	
	private String tabs = "";
	private static String funcWithoutPar = "SYSDATE";
		
	private void appendTabs() {
		if (!tabs.equals("")) code.append("\n");
		code.append(tabs);
		tabs += "  ";
	}

	public void caseAParUnaryExpression(AParUnaryExpression node)
    {
        inAParUnaryExpression(node);
        if(node.getUnaryExpression() != null)
        {
        	code.append("(");
            node.getUnaryExpression().apply(this);
            code.append(")");
        }
        outAParUnaryExpression(node);
    }
	
	public void caseAQuerySpecQueryExpression(AQuerySpecQueryExpression node) {		
        if(node.getSelectClause() != null)
        {
        	appendTabs();
        	code.append("select ");
            node.getSelectClause().apply(this);            
        }
        if(node.getFromClause() != null)
        {
        	code.append(" from ");
            node.getFromClause().apply(this);
        }
        if(node.getWhereClause() != null)
        {
        	code.append(" where ");
            node.getWhereClause().apply(this);
        }
	}
	
	public void caseAUnionQueryExpression(AUnionQueryExpression node)
    {
    	String all = "";
    	
        inAUnionQueryExpression(node);
        if(node.getAll() != null)
        {
            node.getAll().apply(this);
            all = "all ";
        }
        {
            Object temp[] = node.getQueryExpression().toArray();
            for(int i = 0; i < temp.length; i++)
            {
                ((PQueryExpression) temp[i]).apply(this);
                if (i<(temp.length-1)) code.append(" union " + all);
            }
        }
        outAUnionQueryExpression(node);
    }
    
    public void caseAIntersectQueryExpression(AIntersectQueryExpression node)
    {
    	String all = "";
    	
        inAIntersectQueryExpression(node);
        if(node.getAll() != null)
        {
            node.getAll().apply(this);
            all = "all ";
        }
        {
            Object temp[] = node.getQueryExpression().toArray();
            for(int i = 0; i < temp.length; i++)
            {
                ((PQueryExpression) temp[i]).apply(this);
                if (i<(temp.length-1)) code.append(" intersect " + all);
            }
        }
        outAIntersectQueryExpression(node);
    }
    
    public void caseAExceptQueryExpression(AExceptQueryExpression node)
    {
    	String all = "";
    	
        inAExceptQueryExpression(node);
        if(node.getAll() != null)
        {
            node.getAll().apply(this);
            all = "all ";
        }
        if(node.getOp1() != null)
        {
        	code.append("(");
            node.getOp1().apply(this);
            code.append(")");
        }
        code.append(" minus " + all);
        if(node.getOp2() != null)
        {
        	code.append("(");
            node.getOp2().apply(this);
            code.append(")");
        }
        outAExceptQueryExpression(node);
    }
    
    public void caseADistinctSetQuantifier(ADistinctSetQuantifier node)
    {
        code.append(" distinct ");
    }
    
    public void caseAAllSetQuantifier(AAllSetQuantifier node)
    {
        code.append(" all ");
    }
	
	public void caseAFunctionSelectList(AFunctionSelectList node)
    {
        inAFunctionSelectList(node);
        if(node.getFunctionName() != null)
        {
        	code.append(node.getFunctionName().toString().trim());
            node.getFunctionName().apply(this);
        }
        if (node.getParameter() != null)
        {        	
        	code.append("(");
            node.getParameter().apply(this);
            code.append(")");
        }
        outAFunctionSelectList(node);
    }
    
    public void caseAAsteriskSelectList(AAsteriskSelectList node)
    {
        inAAsteriskSelectList(node);
        if(node.getTableQualifier() != null)
        {
        	code.append(node.getTableQualifier().toString().trim() + ".");
            node.getTableQualifier().apply(this);
        }
        code.append("*");
        outAAsteriskSelectList(node);
    }
    
    public void caseAStringSelectSubListItem(AStringSelectSubListItem node)
    {
        inAStringSelectSubListItem(node);
        if(node.getString() != null)
        {
        	code.append(node.getString().toString().trim());
            node.getString().apply(this);
        }
        if(node.getAsClause() != null)
        {
        	code.append(" " + node.getAsClause().toString().trim());
            node.getAsClause().apply(this);
        }
        outAStringSelectSubListItem(node);
    }
	
	public void caseATableNameTableReference(ATableNameTableReference node)
    {
        inATableNameTableReference(node);
        if(node.getTableName() != null)
        {
        	code.append(node.getTableName().toString().trim());
            node.getTableName().apply(this);
        }
        if(node.getCorrelationName() != null)
        {
        	code.append(" " + node.getCorrelationName().toString().trim());
            node.getCorrelationName().apply(this);
        }
        outATableNameTableReference(node);
    }
	
	public void caseADerivedTableTableReference(ADerivedTableTableReference node)
    {
    	code.append("(");
        inADerivedTableTableReference(node);
        if(node.getQueryExpression() != null)
        {
            node.getQueryExpression().apply(this);
        }
        if(node.getCorrelationName() != null)
        {
            node.getCorrelationName().apply(this);
        }
        outADerivedTableTableReference(node);
        code.append(")");
    }
    
    public void caseAAndBooleanExpression(AAndBooleanExpression node)
    {
        inAAndBooleanExpression(node);
        {
            Object temp[] = node.getBooleanExpression().toArray();
            for(int i = 0; i < temp.length; i++)
            {
            	if (i>0) code.append(" and ");
            	code.append("(");
                ((PBooleanExpression) temp[i]).apply(this);
                code.append(")");
            }
        }
        outAAndBooleanExpression(node);
    }
    
    public void caseAOrBooleanExpression(AOrBooleanExpression node)
    {
        inAOrBooleanExpression(node);
        {
            Object temp[] = node.getBooleanExpression().toArray();
            for(int i = 0; i < temp.length; i++)
            {
            	if (i>0) code.append(" or ");
            	code.append("(");
                ((PBooleanExpression) temp[i]).apply(this);
                code.append(")");
            }
        }
        outAOrBooleanExpression(node);
    }
    
    public void caseANotBooleanExpression(ANotBooleanExpression node)
    {
    	code.append("not (");
        inANotBooleanExpression(node);
        if(node.getBooleanExpression() != null)
        {
            node.getBooleanExpression().apply(this);
        }
        outANotBooleanExpression(node);
        code.append(")");
    }
    
    public void caseANullBooleanExpression(ANullBooleanExpression node)
    {
        inANullBooleanExpression(node);
        if(node.getUnaryExpression() != null)
        {
        	code.append("(");
            node.getUnaryExpression().apply(this);
            code.append(") is null");
        }
        outANullBooleanExpression(node);
    }
    
    public void caseAInBooleanExpression(AInBooleanExpression node)
    {
        inAInBooleanExpression(node);
        if(node.getUnaryExpression() != null)
        {
            node.getUnaryExpression().apply(this);
        }
        code.append(" in ");
        if(node.getQueryExpression() != null)
        {
        	code.append("(");
            node.getQueryExpression().apply(this);
            code.append(")");
        }
        outAInBooleanExpression(node);
    }
    
    public void caseAExistsBooleanExpression(AExistsBooleanExpression node)
    {
        inAExistsBooleanExpression(node);
        if(node.getQueryExpression() != null)
        {
        	code.append("exists (");
            node.getQueryExpression().apply(this);
            code.append(")");
        }
        outAExistsBooleanExpression(node);
    }
    
    public void caseAEqRelationalExpression(AEqRelationalExpression node)
    {
        inAEqRelationalExpression(node);
        if(node.getOp1() != null)
        {
            node.getOp1().apply(this);
        }
        code.append(" = ");
        if(node.getOp2() != null)
        {
            node.getOp2().apply(this);
        }
        outAEqRelationalExpression(node);
    }
    
    public void caseANeqRelationalExpression(ANeqRelationalExpression node)
    {
    	code.append("not (");
        inANeqRelationalExpression(node);
        if(node.getOp1() != null)
        {
            node.getOp1().apply(this);
        }
        code.append(" = ");
        if(node.getOp2() != null)
        {
            node.getOp2().apply(this);
        }
        outANeqRelationalExpression(node);
        code.append(")");
    }
    
    public void caseAGtRelationalExpression(AGtRelationalExpression node)
    {
        inAGtRelationalExpression(node);
        if(node.getOp1() != null)
        {
            node.getOp1().apply(this);
        }
        code.append(" > ");
        if(node.getOp2() != null)
        {
            node.getOp2().apply(this);
        }
        outAGtRelationalExpression(node);
    }
    
    public void caseALtRelationalExpression(ALtRelationalExpression node)
    {
        inALtRelationalExpression(node);
        if(node.getOp1() != null)
        {
            node.getOp1().apply(this);
        }
        code.append(" < ");
        if(node.getOp2() != null)
        {
            node.getOp2().apply(this);
        }
        outALtRelationalExpression(node);
    }
    
    public void caseAGteqRelationalExpression(AGteqRelationalExpression node)
    {
        inAGteqRelationalExpression(node);
        if(node.getOp1() != null)
        {
            node.getOp1().apply(this);
        }
        code.append(" >= ");
        if(node.getOp2() != null)
        {
            node.getOp2().apply(this);
        }
        outAGteqRelationalExpression(node);
    }
    
    public void caseALteqRelationalExpression(ALteqRelationalExpression node)
    {
        inALteqRelationalExpression(node);
        if(node.getOp1() != null)
        {
            node.getOp1().apply(this);
        }
        code.append(" <= ");
        if(node.getOp2() != null)
        {
            node.getOp2().apply(this);
        }
        outALteqRelationalExpression(node);
    }
    
    public void caseANumericUnaryExpression(ANumericUnaryExpression node)
    {
        inANumericUnaryExpression(node);
        if(node.getNumericValue() != null)
        {
        	code.append(node.getNumericValue().toString().trim());
            node.getNumericValue().apply(this);
        }
        outANumericUnaryExpression(node);
    }
    
    public void caseAStringUnaryExpression(AStringUnaryExpression node)
    {
        inAStringUnaryExpression(node);
        if(node.getIdentifier() != null)
        {
        	code.append(node.getIdentifier().toString());
            node.getIdentifier().apply(this);
        }
        outAStringUnaryExpression(node);
    }
    
    public void caseATrueBooleanExpression(ATrueBooleanExpression node)
    {
        code.append("(1=1)");
    }
    
    public void caseAFalseBooleanExpression(AFalseBooleanExpression node)
    {
        code.append("(1=0)");
    }
	
	public void caseAColumn(AColumn node)
    {
        inAColumn(node);
        if(node.getTableQualifier() != null)
        {
        	code.append(node.getTableQualifier().toString().trim() + ".");
            node.getTableQualifier().apply(this);
        }
        if(node.getColumnName() != null)
        {
        	code.append(node.getColumnName().toString().trim());
            node.getColumnName().apply(this);
        }
        if(node.getAsClause() != null)
        {
        	code.append(" as \"" + node.getAsClause().toString().trim() + "\"");
            node.getAsClause().apply(this);
        }
        outAColumn(node);
    }
    
    public void caseAMultNumericExpression(AMultNumericExpression node)
    {
        inAMultNumericExpression(node);
        if(node.getOp1() != null)
        {
            node.getOp1().apply(this);
        }
        code.append(" * ");
        if(node.getOp2() != null)
        {
            node.getOp2().apply(this);
        }
        outAMultNumericExpression(node);
    }
    
    public void caseADivNumericExpression(ADivNumericExpression node)
    {
        inADivNumericExpression(node);
        if(node.getOp1() != null)
        {
            node.getOp1().apply(this);
        }
        code.append(" / ");        
        if(node.getOp2() != null)
        {
            node.getOp2().apply(this);
        }
        outADivNumericExpression(node);
    }
    
    public void caseAPlusNumericExpression(APlusNumericExpression node)
    {
        inAPlusNumericExpression(node);
        if(node.getOp1() != null)
        {
            node.getOp1().apply(this);
        }
        code.append(" + ");
        if(node.getOp2() != null)
        {
            node.getOp2().apply(this);
        }
        outAPlusNumericExpression(node);
    }
    
    public void caseAMinusNumericExpression(AMinusNumericExpression node)
    {
        inAMinusNumericExpression(node);
        if(node.getOp1() != null)
        {
            node.getOp1().apply(this);
        }
        code.append(" - ");
        if(node.getOp2() != null)
        {
            node.getOp2().apply(this);
        }
        outAMinusNumericExpression(node);
    }
        
    public void caseAQueryUnaryExpression(AQueryUnaryExpression node)
    {
        inAQueryUnaryExpression(node);
        if(node.getQueryExpression() != null)
        {
        	code.append("(");
            node.getQueryExpression().apply(this);
            code.append(")");
        }
        outAQueryUnaryExpression(node);
    }
    
    public void caseAFunctionUnaryExpression(AFunctionUnaryExpression node)
    {
        inAFunctionUnaryExpression(node);
        if(node.getIdentifier() != null)
        {
        	code.append(node.getIdentifier().toString().trim()); 
        	if (funcWithoutPar.indexOf(node.getIdentifier().toString().trim()) == -1) code.append("(");                
        }
        {
            Object temp[] = node.getUnaryExpression().toArray();
            for(int i = 0; i < temp.length; i++)
            {
                ((PUnaryExpression) temp[i]).apply(this);
                if ((temp.length > 1) && (i<(temp.length-1))) code.append(",");
            }
        }
        if(node.getIdentifier() != null)
        {
        	if (funcWithoutPar.indexOf(node.getIdentifier().toString().trim()) == -1) code.append(")");
        }
        outAFunctionUnaryExpression(node);
    }
    
    public void caseAMinusUnaryExpression(AMinusUnaryExpression node)
    {
        inAMinusUnaryExpression(node);
        if(node.getUnaryExpression() != null)
        {
        	code.append("-");
            node.getUnaryExpression().apply(this);
        }
        outAMinusUnaryExpression(node);
    }
    
}