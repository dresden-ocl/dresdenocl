/*
 * OclFormalParameter.java
 *
 * Created on 28. September 2004, 22:14
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

import tudresden.ocl20.jmi.ocl.types.*;
import tudresden.ocl20.jmi.ocl.expressions.*;
import tudresden.ocl20.jmi.ocl.commonmodel.*;

/**
 *
 * AST node for a formal parameter consisting of a name and a type.
 * The Java "null" reference represents OclUndefined.
 *
 * @author Ansgar Konermann
 * @version
 *
 */
public class OclFormalParameter {
    
    /**
     * Holds value of property name.
     */
    private String name = null;
    
    /**
     * Holds value of property type.
     */
    private Classifier type = null;
    
    /** Creates new OclFormalParameter */
    public OclFormalParameter() { }
    
    /**
     * Getter for property name.
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
     * Getter for property type.
     * @return Value of property type.
     */
    public Classifier getType() {
        return this.type;
    }
    
    /**
     * Setter for property type.
     * @param type New value of property type.
     */
    public void setType(Classifier type) {
        this.type = type;
    }
    
}
