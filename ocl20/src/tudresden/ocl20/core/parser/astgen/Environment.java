/*
 * Environment.java
 *
 * Created on 28. Juli 2004, 19:57
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

import tudresden.ocl20.core.jmi.ocl.commonmodel.ModelElement;
import tudresden.ocl20.core.jmi.ocl.commonmodel.Attribute;
import tudresden.ocl20.core.jmi.ocl.commonmodel.AssociationEnd;
import tudresden.ocl20.core.jmi.ocl.commonmodel.AssociationClass;
import tudresden.ocl20.core.jmi.ocl.commonmodel.Operation;

import java.util.*;

/**
 *
 * Read-only part of the interface of the "Environment" of an OCLExpression,
 * based on the definition in [OCL16].
 * All implementations must use Java <i>null</i> references to represent
 * OclUndefined.
 *
 * @author Ansgar Konermann
 * @version
 */
public interface Environment {

    /**
     * Sets this Environment's reference to parent environment (environments can be nested).
     * All Environments can have its parent changed, even read-only environments.
     */
    public void setParent(Environment parent);

    /** Returns a reference to parent environment (environments can be nested) */
    public Environment getParent();

    /** 
     * Returns nesting level where 0 means top-level and nesting levels are
     * numbered with ascending non-negative numbers. For debugging purposes
     * only; not part of OCL2.0 specification. -1 may be used for unknown
     * nesting levels.
     */
    public int getNestingLevel();
    
    
    /**
     * Find a named element in the current environment, not in its parents, 
     * based on a single name.
     * @see [OCL16], p. 4-25, ch. 4.4.1 [2]
     *
     * @return  a NamedElement instance if an element with the given name exists,
     *          or null if no such element exists.
     */
    public NamedElement lookupLocal(String name);
    
    /**
     * Find a named element in the current environment or recursively in its 
     * parent environment, based on a single name.
     * @see [OCL16], p. 4-25, ch. 4.4.1 [3]
     *
     * @return  a NamedElement instance if an element with the given name exists,
     *          or null if no such element exists, neither in this Environment
     *          nor in any of its parent Environments.
     */
    public NamedElement lookup(String name);
    
    /**
     * Find a named element in the current environment or recursively in its
     * parent environment, based on a path name.
     * @see [OCL16], p. 4-25, ch. 4.4.1 [4]
     */
    public NamedElement lookupPathName(List pathname) throws DuplicateNameException;
    
    
    
    /**
     * Creates a new empty environment as child environment of this environment.
     * The newly created environment can be modified since it implements WritableEnvironment.
     * @see [OCL16], p. 4-26, ch. 4.4.1 [8]
     */
    public WritableEnvironment nestedEnvironment();
    
    /**
     * Lookup a given attribute name of an implicitly named element in the 
     * current environment, including its parents.
     * @see [OCL16], p. 4-26, ch. 4.4.1 [9]
     */
    public Attribute lookupImplicitAttribute(String name);
    

    /** 
     * Lookup the implicit source belonging to a given attribute name in the
     * current environment, including the parents.
     * @see [OCL16], p. 4-26, ch. 4.4.1 [10]
     */
    public NamedElement lookupImplicitSourceForAttribute(String name);
    
    /**
     * Lookup a given attribute name of an implicitly named element in the
     * current environment and return the attribute and it's containing
     * classifier (source).
     * This method saves one of two (possibly very expensive) searches if called
     * instead of consecutive calls to lookupImplicitAttribute and
     * lookupSourceforImplicitAttribute.
     * @return attribute with source or null if no matching attribute found
     */
    public AttributeWithSource lookupImplicitAttributeWithSource(String name);
    

    /**
     * Lookup up a given association end name of an implicitly named element in
     * the current environment, including its parents. 
     * @see [OCL16], p. 4-26, ch. 4.4.1 [11]
     */
    public AssociationEnd lookupImplicitAssociationEnd(String name);
    
    public NamedElement lookupImplicitSourceForAssociationEnd(String name);
    
    /**
     * Lookup a given association end name of an implicitly named element in
     * the current environment and return the association end and it's containing
     * classifier (source).
     * This method saves one wo two (possibly very expensive) searches if called
     * instead of consecutive calls to lookupImplicitAssociationEnd and
     * lookupSourceForImplicitAssociationEnd
     * @return  association end with source or null if no matching association
     *          end found
     */
    public AssociationEndWithSource lookupImplicitAssociationEndWithSource(String name);
    
    /**
     * Lookup a given association class name of an implicitly named element in
     * the current environment and return the association end and it's containing
     * classifier (source).
     * This method saves one wo two (possibly very expensive) searches if called
     * instead of consecutive calls to lookupImplicitAssociationEnd and
     * lookupSourceForImplicitAssociationEnd
     * @return  association class with source or null if no matching association
     *          class found
     */
    public AssociationClassWithSource lookupImplicitAssociationClassWithSource(String name);
    
    /**
     * Lookup up a given association end name of an implicitly named element in
     * the current environment, including its parents. 
     * @see [OCL16], p. 4-26, ch. 4.4.1 [11]
     */
    public AssociationClass lookupImplicitAssociationClass(String name);
    
    public NamedElement lookupImplicitSourceForAssociationClass(String name);
    
    
    
    /**
     * Lookup an operation of an implicitly named element with given name and
     * parameter types in the current environment, including its parents.
     * @see [OCL16], p. 4-26, ch. 4.4.1 [12]
     * @param FIXME FIXME FIXME (add documentation from OCL specification string)
     */
    public Operation lookupImplicitOperation(String name, List params);
    
    /**
     * Lookup an operation of an implicitly named element with given name and
     * parameter types in the current environment, including its parents.
     * Return the operation and its implicit source.
     * @return  operation with source or null if no matching operation found
     */
    public OperationWithSource lookupImplicitOperationWithSource(String name, List params);
    
    /** 
     * Returns a string representation of the object. In general, the
     * <code>toString</code> method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The <code>toString</code> method for class <code>Object</code>
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `<code>@</code>', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return  a string representation of the object.
     */
    public String toString();
    
    /**
     * Returns a Map of all named elements contained in this Environment as
     * (String, NamedElement) pairs.
     * This method was added to simplify implementation of method addEnvironment
     * and is not contained in the OCL2.0 specification.
     * @return a map containing all named elements contained in this Environment,
     *          this map is empty iff the environment is empty.
     */
    public Map getAllElements();
    
}
    
