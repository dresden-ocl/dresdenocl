/*
 * AssociationEndWithSource.java
 *
 * Created on 6. Dezember 2004, 13:11
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

import tudresden.ocl20.jmi.ocl.commonmodel.AssociationEnd;

/**
 *
 * Data container class for an association end with it's containing classifier
 * (source).
 *
 * @author Ansgar Konermann
 * @version
 */
public class AssociationEndWithSource extends Object {
    
    /**
     * Holds value of property associationEnd.
     */
    private AssociationEnd associationEnd;
    
    /**
     * Holds value of property source.
     */
    private NamedElement source;
    
    /** Creates new AssociationEndWithSource */
    public AssociationEndWithSource() {
    }
    
    /**
     * Getter for property associationEnd.
     * @return Value of property associationEnd.
     */
    public AssociationEnd getAssociationEnd() {
        return this.associationEnd;
    }
    
    /**
     * Setter for property associationEnd.
     * @param associationEnd New value of property associationEnd.
     */
    public void setAssociationEnd(AssociationEnd associationEnd) {
        this.associationEnd = associationEnd;
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
    
}
