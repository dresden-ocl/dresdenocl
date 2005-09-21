/*
 * ClassifierNamespace.java
 *
 * Created on 9. August 2004, 19:36
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

import tudresden.ocl20.core.jmi.uml15.core.Classifier;
import tudresden.ocl20.core.jmi.uml15.modelmanagement.Package;

/**
 * Implements extended namespace functionality for the parser. Constructor
 * is a factory method for more specialized subclasses of UMLNamespace.
 * 
 * @author Ansgar Konermann
 * @version
 */
public abstract class UML15Namespace implements Namespace {
    
    protected tudresden.ocl20.core.jmi.uml15.core.Namespace umlNamespace = null;
    public tudresden.ocl20.core.jmi.uml15.core.Namespace getDecorated() {
        return umlNamespace;
    }
    
    public UML15Namespace(tudresden.ocl20.core.jmi.uml15.core.Namespace decorateMe) {
        this.umlNamespace = decorateMe;
    }
    
    private Namespace parentNamespace = null;
    public Namespace getNamespace() {
        // lazy initialization of wrapper for parent uml namespace  
        if ( umlNamespace.getNamespace() != null ) {
            // create wrapper   
            parentNamespace = NamespaceUtility.createNamespaceFor(umlNamespace.getNamespace());
        }
        return parentNamespace;
    }
    
    public void setNamespace(Namespace ns) {
        this.parentNamespace = ns;
    }
    
    
    public static UML15Namespace createDecorator(tudresden.ocl20.core.jmi.uml15.core.Namespace decorateMe) {
        if ( decorateMe instanceof tudresden.ocl20.core.jmi.uml15.core.Classifier ) {
            return new UML15ClassifierNamespace( (Classifier) decorateMe);
        } else if ( decorateMe instanceof tudresden.ocl20.core.jmi.uml15.modelmanagement.Package ) {
            return new UML15PackageNamespace( (tudresden.ocl20.core.jmi.uml15.modelmanagement.Package) decorateMe );
        } else {
            throw new RuntimeException("Unimplemented UML15 namespace type: " + decorateMe.getClass().getName());
        }
        
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
    public abstract Environment getEnvironmentWithoutParents() throws DuplicateNameException;
    
    /**
     * The following operation returns an Environment that contains a reference
     * to its parent environment, which is itself created by this operation by
     * means of a recursive call, and therefore contains a parent environment
     * too.
     * @see [OCL16], p. 4-27, section 4.4.3
     *
     * <PRE>
     *  context Namespace::getEnvironmentWithParents() : Environment 
     *  post: result.NamedElements = self.getEnvironmentWithoutParents()
     *  post: if self.namespace->notEmpty() -- this namespace has an owning namespace
     *        then result.parent = self.namespace.getEnvironmentWithParents()
     *        else result.parent = OclUndefined
     *        endif
     * </PRE>
     */
    public Environment getEnvironmentWithParents() throws DuplicateNameException {    
        Environment result = null;
        
        result = this.getEnvironmentWithoutParents(); 
        // => result.namedElements = self.getEnvironmentWithoutParents().namedElements
        // getEnvironmentWithoutParents is specialized by subclasses, so it returns
        // the appropriate environment content in "namedElements" matching the namespace
        // kind
        
        tudresden.ocl20.core.parser.astgen.Namespace parent = this.getNamespace();
        if ( parent != null ) {
            result.setParent(this.getNamespace().getEnvironmentWithParents());
        } else {
            result.setParent(null);
        }

        return result;
    }
    
}
