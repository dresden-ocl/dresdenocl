/*
 * NamedElement.java
 *
 * Created on 4. August 2004, 16:17
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

import tudresden.ocl20.core.jmi.ocl.commonmodel.*;
import tudresden.ocl20.core.jmi.ocl.expressions.*;

/**
 * 
 * @author Ansgar Konermann
 * @version
 */
public class NamedElement extends Object {
    
    private ModelElement referredElement;
    
    private String name;
    
    private boolean mayBeImplicit;
    
    /** 
     * Creates new NamedElement
     */
    public NamedElement(String name, ModelElement referred, boolean mayBeImplicit) {
        this.name = name;
        this.referredElement = referred;
        this.mayBeImplicit = mayBeImplicit;
    }
    
    /** 
     * Creates new NamedElement having its mayBeImplicit attribute set to 
     * false.
     */
    public NamedElement(String name, ModelElement referred) {
        this(name, referred, false);
        this.name = name;
    }
    
    
    public ModelElement getReferredElement() {
        return this.referredElement;
    }
    public String getName() {
        return this.name;
    }
    public boolean mayBeImplicit() {
        return this.mayBeImplicit;
    }
    
    
    /**
     *
     * <PRE>
     *   context NamedElement::getType() : Classifier 
     *   pre: -- none 
     *   post: referredElement.oclIsKindOf(VariableDeclaration) 
     *           implies result = referredElement.oclAsType(VariableDeclaration).type
     *   post: referredElement.oclIsKindOf(Classifier) implies
     *           result = referredElement
     *   post: referredElement.oclIsKindOf(State) implies
     *           result = -- TBD: when aligning with UML 2.0 Infrastructure
     * </PRE>
     */
    public Classifier getType() {
        ModelElement ref = this.referredElement;
        Classifier result = null;
        if ( ref instanceof VariableDeclaration ) {
            VariableDeclaration vd = (VariableDeclaration) ref;
            result = vd.getType();
        } else if ( ref instanceof VariableExp ) {
            VariableExp vx = (VariableExp) ref;
            result = vx.getType();
        } else if ( ref instanceof Classifier ) {
            result = (Classifier) ref;
        } else {
            throw new RuntimeException("Unimplemented type of NamedElement: " + ref.getClass().getName());
        }
        return result;
    }
    
}
