/*
 * OclContextDeclaration.java
 *
 * Created on 12. August 2004, 15:37
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

import java.util.List;

/**
 *
 * AST node for an OCL2.0 context declaration.
 *
 * @author Ansgar W. Konermann
 * @version
 */
public abstract class OclContextDeclaration extends Object {

    
    public static class ContextType {
        private int code;
        private ContextType(int code) {
            this.code = code;
        }
        public static final ContextType CTX_CLASSIFIER  = new ContextType(1);
        public static final ContextType CTX_OPERATION = new ContextType(2);
        public static final ContextType CTX_ATTR_OR_ASSOC = new ContextType(3);
    }
    private ContextType type;
    public ContextType getType() {
        return this.type;
    }

    /**
     * Returns this instance type-casted to a OclClassifierContextDecl.
     * May only be used if getType() returns CTX_CLASSIFIER.
     */
    public OclClassifierContextDecl asClassifierContextDecl() {
        return (OclClassifierContextDecl) this;
    }
    
    /**
     * Returns this instance type-casted to a OclClassifierContextDecl.
     * May only be used if getType() returns CTX_OPERATION.
     */
    public OclOperationContextDecl asOperationContextDecl() {
        return (OclOperationContextDecl) this;
    }

    /**
     * Returns this instance type-casted to a OclClassifierContextDecl.
     * May only be used if getType() returns CTX_ATTR_OR_ASSOC.
     */
    public OclAttrOrAssocContextDecl asAttrOrAssocContextDecl() {
        return (OclAttrOrAssocContextDecl) this;
    }
    

    /**
     * Holds value of property contextName.
     */
    private List contextName;    
        
    /** Creates new OclContextDeclaration */
    public OclContextDeclaration(ContextType type) {
        this.type = type;
    }
    
    /**
     * Returns true if o is an OclContextDeclaration or a subclass
     * thereof and has the same type code as this instance.
     */
    public boolean hasSameTypeAs(Object o) {
        if ( ! ( o instanceof OclContextDeclaration ) ) {
            return false;
        }
        OclContextDeclaration other = (OclContextDeclaration) o;
        return ( this.getType() == other.getType() );        
    }
    
    
    /**
     * Getter for property contextName.
     * @return Value of property contextName.
     */
    public List getContextName() {
        return this.contextName;
    }
    
    /**
     * Setter for property contextName.
     * @param contextName New value of property contextName.
     */
    public void setContextName(List contextName) {
        this.contextName = contextName;
    }
    
}
