/*
 * OclOperationConstraint.java
 *
 * Created on 27. Oktober 2004, 05:00
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
 * AST node class for an operation constraint definition comprising
 * an operation constraint stereotype, an optional simple name and an
 * ocl expression.
 *
 * @author Ansgar Konermann
 * @version
 */
public class OclOperationConstraint extends Object {
    
    /**
     * Holds value of property stereotype.
     */
    private OclOperationConstraintStereotype stereotype;
    
    /**
     * Holds value of property name. This is syntactically optional and thus
     * may be null.
     */
    private String name;
    
    /**
     * Holds value of property expression.
     */
    private OclExpression expression;
    
    /** Creates new OclOperationConstraint */
    public OclOperationConstraint() {
    }
    
    /**
     * Getter for property stereotype.
     * @return Value of property stereotype.
     */
    public OclOperationConstraintStereotype getStereotype() {
        return this.stereotype;
    }    
    
    /**
     * Setter for property stereotype. This is syntactically optional and thus
     * may be null.
     * @param stereotype New value of property stereotype.
     */
    public void setStereotype(OclOperationConstraintStereotype stereotype) {
        this.stereotype = stereotype;
    }
    
    /**
     * Getter for property name. This is syntactically optional and thus
     * may be null.
     * @return Value of property name.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Setter for property name.
     * @param name New value of property name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
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
