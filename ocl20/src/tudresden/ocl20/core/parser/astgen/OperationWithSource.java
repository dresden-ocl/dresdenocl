/*
 * OperationWithSource.java
 *
 * Created on 6. Dezember 2004, 13:22
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

package tudresden.ocl20.parser.astgen;

import tudresden.ocl20.jmi.ocl.commonmodel.Operation;

/**
 *
 * Container class for an operation and its implicit source expression.
 *
 * @author Ansgar Konermann
 * @version
 */
public class OperationWithSource extends Object {
    
    /**
     * Holds value of property source.
     */
    private NamedElement source;
    
    /**
     * Holds value of property operation.
     */
    private Operation operation;
    
    /** Creates new OperationWithSource */
    public OperationWithSource() {
    }
    
    /**
     * Getter for property source.
     * @return Value of property source.
     */
    public NamedElement getSource() {
        return this.source;
    }
    
    /**
     * Setter for property source.
     * @param source New value of property source.
     */
    public void setSource(NamedElement source) {
        this.source = source;
    }
    
    /**
     * Getter for property operation.
     * @return Value of property operation.
     */
    public Operation getOperation() {
        return this.operation;
    }
    
    /**
     * Setter for property operation.
     * @param operation New value of property operation.
     */
    public void setOperation(Operation operation) {
        this.operation = operation;
    }
    
}
