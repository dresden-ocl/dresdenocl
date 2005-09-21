/*
 * AttributeWithSource.java
 *
 * Created on 6. Dezember 2004, 11:39
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

package tudresden.ocl20.core.parser.astgen;

import tudresden.ocl20.core.jmi.ocl.commonmodel.Attribute;

/**
 *
 * Data container class for an attribute and the attribute's source.
 *
 * @author Ansgar Konermann
 * @version
 */
public class AttributeWithSource {
    
    /**
     * Holds value of property source.
     */
    private NamedElement source;
    
    /**
     * Holds value of property attribute.
     */
    private Attribute attribute;
    
    /** Creates new AttributeWithSource */
    public AttributeWithSource() {
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
     * Getter for property attribute.
     * @return Value of property attribute.
     */
    public Attribute getAttribute() {
        return this.attribute;
    }
    
    /**
     * Setter for property attribute.
     * @param attribute New value of property attribute.
     */
    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
    
}
