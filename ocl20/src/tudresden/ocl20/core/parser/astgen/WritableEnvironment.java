/*
 * WritableEnvironment.java
 *
 * Created on 22. November 2004, 14:29
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

import tudresden.ocl20.jmi.ocl.commonmodel.ModelElement;

/**
 * Support for modification (writing) of an Environment.
 *
 * @author Ansgar Konermann
 */
public interface WritableEnvironment extends Environment {
    
    /** 
     * Sets the nesting level where 0 means top-level and nesting levels are
     * numbered with ascending non-negative numbers. For debugging purposes
     * only; not part of OCL2.0 specification. -1 may be used for unknown
     * nesting levels.
     */    
    public void setNestingLevel(int nestingLevel);
    
    
    
    /**
     * Add a new named element to the environment. Note that this operation is defined as a 
     * query operation so that it can be used in OCL constraints.
     * <br>
     * Implementation note: this operation modifies this Environment and returns
     * "this", it does not return a modified copy of this Environment.
     * <br>
     * @see [OCL16], p. 4-25, ch. 4.4.1 [5]
     */
    public WritableEnvironment addElement(String name, ModelElement elem, boolean implicit) throws DuplicateNameException;

    /**
     * Combine two environments resulting in a new environment. Note that this
     * operation is defined as a query operation so that it can be used in OCL constraints.
     * <br>
     * Implementation note: this operation modifies this Environment and returns
     * "this", it does not return a modified copy of this Environment.
     * <br>
     * @see [OCL16], p. 4-25, ch. 4.4.1 [6]
     */
    public WritableEnvironment addEnvironment(Environment env) throws DuplicateNameException;
    
    /**
     * Add all elements in the namespace to the environment.
     * @see [OCL16], pp. 4-25f, ch. 4.4.1 [7]
     * <br>
     * Implementation note: this operation modifies this Environment and returns
     * "this", it does not return a modified copy of this Environment.
     * <br>
     */
    public WritableEnvironment addNamespace(Namespace ns) throws DuplicateNameException;

}
