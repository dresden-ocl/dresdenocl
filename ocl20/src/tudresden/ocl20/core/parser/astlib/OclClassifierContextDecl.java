/*
 * OclClassifierContextDecl.java
 *
 * Created on 3. November 2004, 03:51
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

import java.util.List;
import java.util.LinkedList;

/**
 *
 * AST node for a classifier context declaration (grammar: alternative {classifier}
 * of production context_declaration_cs).
 *
 * @author Ansgar Konermann
 * @version
 */
public class OclClassifierContextDecl extends OclContextDeclaration {
        
    /**
     * Holds value of property constraints.
     */
    private List constraints;
    
    /** Creates new OclClassifierContextDecl */
    public OclClassifierContextDecl() {
        super(ContextType.CTX_CLASSIFIER);
    }

    /**
     * Getter for property constraints.
     * @return Value of property constraints.
     */
    public List getConstraints() {
        if ( this.constraints != null ) {
            this.constraints = new LinkedList();
        }
        return this.constraints;
    }    
    
    /**
     * Setter for property constraints.
     * @param constraints New value of property constraints.
     */
    public void setConstraints(List constraints) {
        this.constraints = constraints;
    }
    
}
