/*
 * OclClassifierConstraint.java
 *
 * Created on 27. Oktober 2004, 17:52
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

/**
 *
 * AST node for an OCL classifier context declaration (grammar: 
 * classifier_context_decl_cs).
 *
 * @author Ansgar Konermann
 * @version
 */
public class OclClassifierConstraint extends Object {
    
    public static class ConstraintType {
        private int code;
        private ConstraintType(int code) {
            this.code = code;
        }
        public static final ConstraintType INVARIANT  = new ConstraintType(1);
        public static final ConstraintType DEFINITION = new ConstraintType(2);
    }
    
    private ConstraintType type;
    
    /**
     * Holds value of property name, which is syntactically optional and may
     * be null.
     */
    private String name;
    
    /** Creates new OclClassifierConstraint */
    protected OclClassifierConstraint(ConstraintType type) {
        this.type = type;
    }
    
    /**
     * Getter for property name, which is syntactically optional and may
     * be null.
     * @return Value of property name.
     */
    public String getName() {
        return this.name;
    }    
    
    /**
     * Setter for property name, which is syntactically optional and may
     * be null
     * @param name New value of property name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Getter for property type.
     * @return Value of property type.
     */
    public ConstraintType getType() {
        return this.type;
    }
    
    public OclInvariantClassifierConstraint asInvariant() {
        return (OclInvariantClassifierConstraint) this;
    }
    
    public OclDefinitionClassifierConstraint asDefinition() {
        return (OclDefinitionClassifierConstraint) this;
    }
    
}
