/*
 * AbstractEnvironment.java
 *
 * Created on 22. November 2004, 11:36
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
import tudresden.ocl20.jmi.ocl.commonmodel.Attribute;
import tudresden.ocl20.jmi.ocl.commonmodel.AssociationEnd;
import tudresden.ocl20.jmi.ocl.commonmodel.Operation;

import java.util.*;

/**
 *
 * Abstract read-write Environment implementation based on the definition in [OCL16]. 
 * This implementation uses Java <i>null</i> references to represent OclUndefined.
 *
 * @author Ansgar Konermann
 * @version
 */
public abstract class AbstractEnvironment implements Environment, WritableEnvironment {
    
    /** 
     * Adds a NamedElement to the internal maps used to manage the elements
     * contained in this Environment.
     */
    protected abstract void addElement(NamedElement elem) throws DuplicateNameException;
    
    /** Removes a named element by name. */
    protected abstract void removeElement(String name);
    
    
    /**
     * Removes all named elements from this environment having the same name
     * as the given one.
     */
    protected void removeElement(NamedElement elem) {
        removeElement(elem.getName());
    }
    
    
    /** Reference to parent environment (environments can be nested) */
    private Environment parent;
    public Environment getParent() {
        return this.parent;
    }
    public void setParent(Environment parent) {
        this.parent = parent;
    }
    
    /**
     * Level of nesting of this environment (for debugging purposes, not part of
     * OCL1.6 specification). 
     * 0 means top-level, 1 means one below top and so on. -1 means unknown.
     */
    private int nestingLevel = -1;
    public int getNestingLevel() { return nestingLevel; }
    public void setNestingLevel(int nestingLevel) {
        this.nestingLevel = nestingLevel;
    }
    
    
    /** Creates new, empty Environment with the specified nesting level */
    public AbstractEnvironment(int nestingLevel) {
        this.nestingLevel = nestingLevel;
    }
   
}
