/*
 * OclDefinedEntityDecl.java
 *
 * Created on 27. Oktober 2004, 17:35
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
 * AST node class for an entity definition (grammar: defined_entity_cs).
 *
 * @author Ansgar Konermann
 * @version
 */
public abstract class OclDefinedEntityDecl extends Object {
    
    public static class EntityType {
        private int code;
        private EntityType(int code) {
            this.code = code;
        }
        /** EntityType for attribute definition entities */
        public static final EntityType ATTRIBUTE = new EntityType(1);
        /** EntityType for operation definition entities */
        public static final EntityType OPERATION = new EntityType(2);
    }
    
    
    private EntityType type;
    
    /** Creates new OclDefinedEntityDecl, only allowed for concrete subclasses. */
    protected OclDefinedEntityDecl(EntityType type) {
        this.type = type;
    }
    public OclAttributeDefinedEntityDecl asAttributeDeclaration() {
        return (OclAttributeDefinedEntityDecl) this;
    }
    public OclOperationDefinedEntityDecl asOperationDeclaration() {
        return (OclOperationDefinedEntityDecl) this;
    }
    
    /**
     * Getter for property type.
     * @return Value of property type.
     */
    public EntityType getType() {
        return this.type;
    }
    
}
