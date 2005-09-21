/*
 * OclAttrOrAssocConstraint.java
 *
 * Created on 1. November 2004, 18:56
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

package tudresden.ocl20.core.parser.astlib;

import tudresden.ocl20.core.jmi.ocl.expressions.OclExpression;

/**
 *
 * AST node for an Attribute or Association constraint (grammar:
 * init_or_der_value_cs).
 *
 * @author Ansgar Konermann
 * @version
 */
public abstract class OclAttrOrAssocConstraint extends Object {
    
    public static class ConstraintType {
        private int code;
        private ConstraintType(int code) {
            this.code = code;
        }
        public static final ConstraintType INIT   = new ConstraintType(1);
        public static final ConstraintType DERIVE = new ConstraintType(2);
    }
    
    private ConstraintType type;
    
    /**
     * Holds value of property expression.
     */
    private OclExpression expression;
    
    /** Creates new OclAttrOrAssocConstraint */
    protected OclAttrOrAssocConstraint(ConstraintType type) {
        this.type = type;
    }
    
    /**
     * Returns the type of this constraint.
     */
    public ConstraintType getType() { return this.type; }
    
    /**
     * Getter for property expression.
     * @return Value of property expression.
     */
    public OclExpression getExpression() {
        return this.expression;
    }    
    
    /**
     * Setter for property expression.
     * @param expression New value of property expression.
     */
    public void setExpression(OclExpression expression) {
        this.expression = expression;
    }
    
}
