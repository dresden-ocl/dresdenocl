/*
 * OclBinaryExpTail.java
 *
 * Created on 20. Oktober 2004, 04:34
 *
 * Copyright (c) 2004, 2005 Ansgar Konermann
 * Contact: <konermann@itikko.net>
 *
 * This file is part of the OCL2.0 parser and compiler libraries
 * created at Technische Universitaet Dresden (TUD), Germany.
 * Visit http://dresden-ocl.sourceforge.net/ for details.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA  02111-1307  USA
 *
 */

package tudresden.ocl20.parser.astlib;

import tudresden.ocl20.jmi.ocl.expressions.OclExpression;

/**
 *
 * Temporary AST node representing an OCL Expression Tail (operator
 * plus operand).
 *
 * @author Ansgar Konermann
 * @version
 */
public class OclBinaryExpTail extends Object {
    
    /**
     * Holds value of property operator.
     */
    private String operator;
    
    /**
     * Holds value of property operand.
     */
    private OclExpression operand;
    
    /** Creates new OclLogExpTail */
    public OclBinaryExpTail() {
    }
    
    /**
     * Getter for property operator.
     * @return Value of property operator.
     */
    public String getOperator() {
        return this.operator;
    }
    
    /**
     * Setter for property operator.
     * @param operator New value of property operator.
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }
    
    /**
     * Getter for property operand.
     * @return Value of property operand.
     */
    public OclExpression getOperand() {
        return this.operand;
    }
    
    /**
     * Setter for property operand.
     * @param operand New value of property operand.
     */
    public void setOperand(OclExpression operand) {
        this.operand = operand;
    }
    
}
