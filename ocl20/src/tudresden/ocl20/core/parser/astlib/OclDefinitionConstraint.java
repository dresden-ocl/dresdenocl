/*
 * OclDefinitionConstraint.java
 *
 * Created on 27. Oktober 2004, 20:36
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
 * AST node for a definition constraint (grammar: definition_constraint_cs).
 *
 * @author Ansgar Konermann
 * @version
 */
public class OclDefinitionConstraint extends Object {
    
    /**
     * Holds value of property entity.
     */
    private OclDefinedEntityDecl entity;
    
    /**
     * Holds value of property definition.
     */
    private OclExpression definition;
    
    /** Creates new OclDefinitionConstraint */
    public OclDefinitionConstraint() {
    }
    
    /**
     * Getter for property entity.
     * @return Value of property entity.
     */
    public OclDefinedEntityDecl getEntity() {
        return this.entity;
    }
    
    /**
     * Setter for property entity.
     * @param entity New value of property entity.
     */
    public void setEntity(OclDefinedEntityDecl entity) {
        this.entity = entity;
    }
    
    /**
     * Getter for property definition.
     * @return Value of property definition.
     */
    public OclExpression getDefinition() {
        return this.definition;
    }
    
    /**
     * Setter for property definition.
     * @param definition New value of property definition.
     */
    public void setDefinition(OclExpression definition) {
        this.definition = definition;
    }
    
}
