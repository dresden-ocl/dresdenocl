/*
 * UML15ClassifierNamespace.java
 *
 * Created on 10. August 2004, 15:52
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

import tudresden.ocl20.jmi.uml15.core.Classifier;
import tudresden.ocl20.jmi.ocl.commonmodel.Attribute;
import tudresden.ocl20.jmi.ocl.commonmodel.Operation;
import java.util.*;

/**
 *
 * @author Ansgar Konermann
 * @version
 */
public class UML15ClassifierNamespace extends UML15Namespace {
    
    /** Creates new UML15ClassifierNamespace */
    public UML15ClassifierNamespace(Classifier decorateMe) {
        super(decorateMe);
    }

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
    public Environment getEnvironmentWithoutParents() throws DuplicateNameException {
        //   post: self.isTypeOf(Classifier) implies 
        //         -- TBD when aligning with UML 2.0 Infrastrcuture 
        //         -- include all class features and contained classifiers
        
        Classifier ns = (Classifier) this.umlNamespace;
        WritableEnvironment wrEnv = EnvironmentFactory.EMPTY_ENV();
        NamedElement ne = null;
        
        Iterator i = null;
        
        // add all operations
        List operations = ns.allOperations();
        i = operations.iterator();
        while ( i.hasNext() ) {
            Operation oper = (Operation) i.next();
            String name = oper.getNameA();
            wrEnv.addElement(name, oper, false);
        }
        
        // add all attributes
        List attributes = ns.allAttributes();
        i = attributes.iterator();
        while ( i.hasNext() ) {
            Attribute attr = (Attribute) i.next();
            String name = attr.getNameA();
            wrEnv.addElement(name, attr, false);            
        }
        
        // add all methods (?)
        //
        // what's the difference between an operation and a method ?
        // [OCL16] specification is unclear about this (Ch. 2.5). 
        // In UML, there is a Metaclass Operation as well as a Metaclass
        // Method. In OCL, there is only Operation. How do they fit
        // together?
        
        return wrEnv;        
    }    
    
}
