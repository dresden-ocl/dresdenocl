/*
 * OclAttrOrAssocContextDecl.java
 *
 * Created on 13. August 2004, 00:36
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

import tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier;
import java.util.List;
import java.util.LinkedList;

/**
 *
 * AST node for an attr_or_assoc_context_decl_cs
 *
 * @author Ansgar W. Konermann
 * @version
 */
public class OclAttrOrAssocContextDecl extends OclContextDeclaration {
    
    private Classifier typeSpecifier = null;
    
    /**
     * Holds value of property constraintList.
     */
    private List constraintList;
    
    public void setTypeSpecifier(Classifier t) {
        this.typeSpecifier = t;
    }
    public Classifier getTypeSpecifier() {
        return this.typeSpecifier;
    }
    
    /**
     * Getter for property constraintList.
     * @return Value of property constraintList.
     */
    public List getConstraintList() {
        if ( this.constraintList == null ) {
            // lazy initialization
            this.constraintList = new LinkedList();
        } 
        return this.constraintList;
    }
    
    /**
     * Setter for property constraintList.
     * @param constraintList New value of property constraintList.
     */
    public void setConstraintList(List constraintList) {
        this.constraintList = constraintList;
    }
    
    /** Creates new OclAttrOrAssocContextTail */
    public OclAttrOrAssocContextDecl() {
        super(ContextType.CTX_ATTR_OR_ASSOC);
    }
    
}
