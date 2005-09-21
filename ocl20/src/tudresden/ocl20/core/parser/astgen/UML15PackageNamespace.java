/*
 * UML15PackageNamespace.java
 *
 * Created on 11. August 2004, 16:48
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

import tudresden.ocl20.core.jmi.uml15.modelmanagement.Package;
import tudresden.ocl20.core.jmi.uml15.core.ModelElement;
import tudresden.ocl20.core.jmi.uml15.core.Classifier;
import java.util.*;

/**
 *
 * @author Ansgar Konermann
 * @version
 */
public class UML15PackageNamespace extends UML15Namespace {
    
    /** Creates new UML15PackageNamespace */
    public UML15PackageNamespace(Package decorateMe) {
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
        
        WritableEnvironment result = EnvironmentFactory.EMPTY_ENV();
        Package pk = (Package) this.umlNamespace;
        
        // add all classifiers and subpackages
        Collection owned = pk.getOwnedElement();
        Iterator i = owned.iterator();
        while ( i.hasNext() ) {
            ModelElement el = (ModelElement) i.next();
            //
            // System.out.println("UML15PackageNamespace: processing element of class " + el.getClass().getName());
            
            String name = el.getNameA();
            if ( el instanceof tudresden.ocl20.core.jmi.uml15.core.Classifier ) {
                // add classifier 
                // System.out.println("  Adding UML15 classifier '" + name + "'");
                result.addElement(name, el, false);
            } else if ( el instanceof tudresden.ocl20.core.jmi.uml15.modelmanagement.Package ) {
                // add package
                // System.out.println("  Adding UML15 Package '" + name + "'");
                result.addElement(name, el, false);
            } else if ( el instanceof tudresden.ocl20.core.jmi.uml15.core.Association ) {
                // do nothing 
            } else if ( el instanceof tudresden.ocl20.core.jmi.uml15.core.AssociationEnd ) {
                // do nothing 
            } else if ( el instanceof tudresden.ocl20.core.jmi.uml15.core.TagDefinition ) {
                // do nothing
            } else if ( el instanceof tudresden.ocl20.core.jmi.uml15.core.Stereotype ) {
                // do nothing
            } else if ( el instanceof tudresden.ocl20.core.jmi.uml15.core.Generalization ) {
                // do nothing
            } else {
                System.out.println("Warning: unknown type of member in package namespace: " + el.getClass().getName() + " for name " + name);
            }
        }
        return result;        
    }
    
}
