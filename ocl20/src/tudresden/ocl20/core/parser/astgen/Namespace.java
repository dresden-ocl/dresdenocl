/*
 * Namespace.java
 *
 * Created on 5. August 2004, 12:14
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

import java.util.*;
import tudresden.ocl20.jmi.ocl.commonmodel.Classifier;
import tudresden.ocl20.jmi.ocl.commonmodel.Package;
//import tudresden.ocl20.jmi.ocl.commonmodel.StateMachine;
//import tudresden.ocl20.jmi.ocl.commonmodel.Subsystem;

/**
 *
 * Interface of OCL/UML metaclass Namespace with OCL16 extensions as defined
 * in [OCL16] on page 4-27.
 *
 * @author Ansgar Konermann
 * @version
 */
public interface Namespace {
        
//    @@TODO@@: move this code snippet to a new class AbstractNamespace
//    
//    private Set ownedElement = null;
//    /**
//     * Returns a reference to the collection of owned elements. To add or
//     * remove owned elements, use the add() and remove() operations of the
//     * returned collection.
//     */
//
//    @@TODO@@: (ffs): declare method in interface + implement in abstract NS?
//
//    public Collection getOwnedElement() {
//        return null;        
//    }
    
    /**
     * Sets the parent namespace. A value of null indicates "no parent namespace".
     */
    public abstract void setNamespace(Namespace ns);
    /**
     * Returns a reference to the parent namespace or null if no parent namespace
     * has been set.
     */
    public abstract Namespace getNamespace();
    
    

    /**
     * Returns the information of the contents of the namespace in the form of 
     * an Environment object. Note that the parent association of Environment
     * is not filled.
     * @see [OCL16], p. 4-27, section 4.4.3
     *
     * <PRE>
     *   context Namespace::getEnvironmentWithoutParents() : Environment
     *   post: self.isTypeOf(Classifier) implies 
     *         -- TBD when aligning with UML 2.0 Infrastrcuture 
     *         -- include all class features and contained classifiers
     *   post: self.isTypeOf(Package) implies
     *         -- TBD when aligning with UML 2.0 Infrastrcuture 
     *         -- include all classifiers and subpackages
     *   post: self.isTypeOf(StateMachine)implies
     *         -- TBD when aligning with UML 2.0 Infrastrcuture
     *         -- include all states
     *   post: self.isTypeOf(Subsystem) implies
     *         -- TBD when aligning with UML 2.0 Infrastrcuture
     *         -- include all classifiers and subpackages    
     * </PRE>
     *
     */
    public abstract Environment getEnvironmentWithoutParents() throws DuplicateNameException;

    
    /**
     * The following operation returns an Environment that contains a reference
     * to its parent environment, which is itself created by this operation by
     * means of a recursive call, and therefore contains a parent environment
     * too.
     * @see [OCL16], p. 4-27, section 4.4.3
     * <PRE>
     *  context Namespace::getEnvironmentWithParents() : Environment 
     *  post: result.NamedElements = self.getEnvironmentWithoutParents()
     *  post: if self.namespace->notEmpty() -- this namespace has an owning namespace
     *        then result.parent = self.namespace.getEnvironmentWithParents()
     *        else result.parent = OclUndefined
     *        endif
     * </PRE>
     */
    public abstract Environment getEnvironmentWithParents() throws DuplicateNameException;

}
