/*
 * EnvironmentImpl.java
 *
 * Created on 22. November 2004, 15:16
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
import tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier;

import java.util.*;

/**
 *
 * Environment implementation based on the definition in [OCL16]. This 
 * implementation uses Java <i>null</i> references to represent OclUndefined.
 *
 * @author Ansgar Konermann
 * @version
 */
public class DefaultEnvironmentImpl extends AbstractEnvironment {
    
    /**
     * Returns a new, empty environment 
     */
    public static final WritableEnvironment EMPTY_ENV() {
        WritableEnvironment result = new DefaultEnvironmentImpl();
        return result;
    }

    /** 
     * Contains those named elements of this Environment which have their 
     * attribute "mayBeImplicit" set to "true". This map is a subset of
     * namedElements.
     * 
     */
    private java.util.Map implicitElements;
    /** 
     * Contains those named elements of this Environment which have their 
     * attribute "mayBeImplicit" set to "false". This map is a subset of
     * namedElements.
     */    
    private java.util.Map explicitElements;
    /**
     * Contains all named elements of this environment. This map is disjointly
     * decomposed into the maps implicitElements and explicitElements.
     */
    private java.util.Map namedElements;
    
    /** 
     * Adds a NamedElement to the internal maps used to manage the elements
     * contained in this Environment.
     */
    protected void addElement(NamedElement elem) throws DuplicateNameException {
        String name = elem.getName();
        // check precondition
        NamedElement ne = (NamedElement) namedElements.get(name);
        if ( ne != null ) {
            throw new DuplicateNameException(name);
        }
        
        boolean imp = elem.mayBeImplicit();
        if ( imp ) {
            implicitElements.put(name, elem);
        } else {
            explicitElements.put(name, elem);
        }
        namedElements.put(name, elem);
    }
    /** Removes a named element by name. */
    protected void removeElement(String name) {
        namedElements.remove(name);
        implicitElements.remove(name);
        explicitElements.remove(name);
    }
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
    
    /** Creates new, empty Environment with unknown nesting level*/
    public DefaultEnvironmentImpl() {
        super(-1);
        explicitElements = new HashMap();
        implicitElements = new HashMap();
        namedElements = new HashMap();
    }
    
    /** Creates new, empty Environment with the specified nesting level */
    public DefaultEnvironmentImpl(int nestingLevel) {
        super(nestingLevel);
        explicitElements = new HashMap();
        implicitElements = new HashMap();
        namedElements = new HashMap();
    }
        
    
    
    /**
     * Find a named element in the current environment, not in its parents, 
     * based on a single name.
     * @see [OCL16], p. 4-25, ch. 4.4.1 [2]
     *
     * @return  a NamedElement instance if an element with the given name exists,
     *          or null if no such element exists.
     */
    public NamedElement lookupLocal(String name) {
        // context Environment::lookupLocal(name : String) : NamedElement 
        // post: result = namedElements->any(v | v.name = name)
        assert ( name != null ):
            "name must not be null";
        Object item = namedElements.get(name);
        if ( item == null ) {
            return null;
        } else {
            NamedElement result = (NamedElement) item;
            return result;
        }
    }
    
    /**
     * Find a named element in the current environment or recursively in its 
     * parent environment, based on a single name.
     * @see [OCL16], p. 4-25, ch. 4.4.1 [3]
     *
     * @return  a NamedElement instance if an element with the given name exists,
     *          or null if no such element exists, neither in this Environment
     *          nor in any of its parent Environments.
     */
    public NamedElement lookup(String name) {
        // context Environment::lookup(name: String) : ModelElement 
        // post: result = if not lookupLocal(name).oclIsUndefined() 
        //      then lookupLocal(name).referredElement
        //      else parent.lookup(name)
        //      endif
        
        // we don't return the referred element, but the named element
        // itself (specification is inconsistent here).
        NamedElement result = lookupLocal(name);
        if ( result == null ) {
            if ( parent != null ) {
                result = parent.lookup(name);
            } else {
                // if no parent set, then parent has value OclUndefined 
                // => parent.lookup(...) returns OclUndefined
                // => result == null
            }            
        }
        return result;
    }
    
    /**
     * Find a named element in the current environment or recursively in its
     * parent environment, based on a path name.
     * @see [OCL16], p. 4-25, ch. 4.4.1 [4]
     */
    public NamedElement lookupPathName(List pathname) throws DuplicateNameException {
        // context Environment::lookupPathName(names: Sequence(String)) : ModelElement
        //   post: let firstNamespace : ModelElement = lookupLocal( names->first() ).referredElement
        //         in 
        //           if firstNamespace.isOclKind(Namespace) -- indicates a sub namespace of the 
        //                                                  -- namespace in which self is present
        //           then result = self.nestedEnvironment().addNamespace( firstNamespace ).
        //                    lookupPathName( names->tail() )
        //           else -- search in surrounding namespace
        //                result = parent.lookupPathName( names )
        //           endif 

        int pnsize = pathname.size();
        String firstElement = (String) pathname.get(0);
        NamedElement result = null;
        
        NamedElement neFirstNamespace = lookupLocal(firstElement);
        if ( neFirstNamespace != null ) {            
            if ( pnsize == 1 ) {
                // path name size is one, local lookup matched => element found 
                return neFirstNamespace;
            }
            // length > 1 => continue search in child namespaces (if any)       
            ModelElement firstNamespace = neFirstNamespace.getReferredElement();
            if ( NamespaceUtility.isKindOfNamespace(firstNamespace) ) {
                // create namespace decorator wrapper for concrete instance of ModelElement 
                // supported instance types depend on NamespaceUtility implementation       
                Namespace nsWrapper = NamespaceUtility.createNamespaceFor(firstNamespace);
                WritableEnvironment nested = this.nestedEnvironment();
                nested.addNamespace(nsWrapper);
                // subList returns list up to, but excluding toIndex
                result = nested.lookupPathName(pathname.subList(1, pnsize));
                return result;
            } else {
                // first element's name matched name head, but is not a         
                // namespace. we assume this lookup should fail and             
                // return null                                                  
                // @@TODO@@ evaluate this lookup option: continue search in parent environments if element with matching name in *this* environment is not a namespace  
                return null;
            }
        // <-- end: matching ne found for first path name element
        } else {
            Environment parent = this.getParent();
            if ( parent == null ) {
                return null;
            } else {
                result = parent.lookupPathName(pathname);
                return result;
            }            
        }
    }
    
    /**
     * Add a new named element to the environment. Note that this operation is defined as a 
     * query operation so that it can be used in OCL constraints.
     * <br>
     * Implementation note: this operation modifies this Environment and returns
     * "this", it does not return a modified copy of this Environment.
     * <br>
     * @see [OCL16], p. 4-25, ch. 4.4.1 [5]
     */
    public WritableEnvironment addElement(String name, ModelElement elem, boolean implicit) throws DuplicateNameException {        
        // context Environment::addElement (name : String, elem : ModelElement, imp : Boolean) : Environment 
        // pre : -- the name must not clash with names already existing in this environment 
        //   self.lookupLocal(name).oclIsUndefined()
        // post: result.parent = self.parent and 
        //         result.namedElements->includesAll(self.namedElements) and
        //         result.namedElements->count (v | v.oclIsNew()) = 1 and
        //         result.namedElements->forAll (v | v.oclIsNew() 
        //             implies v.name = name and v.referredElement = elem and v.mayBeImplicit = imp )
        
        // precondition will be checked by private method addElement(NamedElement elem)
        assert ( name != null ):
            "name must not be null";
        assert ( elem != null ):
            "element to add must not be null";
            
        NamedElement ne = new NamedElement(name, elem, implicit);
        this.addElement(ne);
        return this;
    }
    
    /**
     * Combine two environments resulting in a new environment. Note that this
     * operation is defined as a query operation so that it can be used in OCL constraints.
     * <br>
     * Implementation note: this operation modifies this Environment and returns
     * "this", it does not return a modified copy of this Environment.
     * <br>
     * @see [OCL16], p. 4-25, ch. 4.4.1 [6]
     */
    public WritableEnvironment addEnvironment(Environment env) throws DuplicateNameException {
        // context Environment::addEnvironment(env : Environment) : Environment
        // pre :   -- the names must not clash with names already existing in this environment 
        //         env.namedElements->forAll(nm | self.lookupLocal(nm).oclIsUndefined() )
        // post:   result.parent = self.parent and 
        //         result.namedElements = self.namedElements->union(env.namedElements)   
        
        Map elementsToAdd = env.getAllElements();
        Iterator i = elementsToAdd.values().iterator();
        while ( i.hasNext() ) {
            NamedElement el = (NamedElement) i.next();
            // this call will throw an Exception if name already used in this
            // Environment (= precondition check)
            this.addElement(el);
        }
        return this;
    }
    
    /**
     * Add all elements in the namespace to the environment.
     * @see [OCL16], pp. 4-25f, ch. 4.4.1 [7]
     * <br>
     * Implementation note: this operation modifies this Environment and returns
     * "this", it does not return a modified copy of this Environment.
     * <br>
     */
    public WritableEnvironment addNamespace(Namespace ns) throws DuplicateNameException {
        // context Environment::addNamespace(ns: Namespace) : Environment 
        // post: result.namedElements = 
        //         ns.getEnvironmentWithoutParents().namedElements->union( self.namedElements)
        // post: result.parent = self.parent 
        
        Environment nsAsEnv = ns.getEnvironmentWithoutParents();
        this.addEnvironment(nsAsEnv);
        return this;
    }

    
    /**
     * Creates a new empty environment as child environment of this environment 
     * @see [OCL16], p. 4-26, ch. 4.4.1 [8]
     */
    public WritableEnvironment nestedEnvironment() {
        WritableEnvironment result = EMPTY_ENV();
        result.setParent(this);
        result.setNestingLevel(this.nestingLevel+1);
        return result;
    }
    
    /**
     * Returns a Map of all named elements contained in this Environment as
     * (String, NamedElement) pairs.
     * This method was added to simplify implementation of method addEnvironment
     * and is not contained in the OCL2.0 specification.
     * @return a map containing all named elements contained in this Environment,
     *          this map is empty iff the environment is empty.
     */
    public Map getAllElements() {
        return Collections.unmodifiableMap(this.namedElements);
    }
    
    
    
    /**
     * Lookup a given attribute name of an implicitly named element in the 
     * current environment, including its parents.
     * @see [OCL16], p. 4-26, ch. 4.4.1 [9]
     */
    public Attribute lookupImplicitAttribute(String name) {
        //    context Environment::lookupImplicitAttribute(name: String) : Attribute
        //    pre: -- none
        //    post: result = lookupImplicitSourceForAttribute(name).referredElement.oclAsType(Attribute)
        
        AttributeWithSource attrWithSource = lookupImplicitAttributeWithSource(name);
        if ( attrWithSource == null ) {
            return null;
        } else {
            return attrWithSource.getAttribute();
        }
    }
    

    /** 
     * Lookup the implicit source belonging to a given attribute name in the
     * current environment, including the parents.
     * @see [OCL16], p. 4-26, ch. 4.4.1 [10]
     */
    public NamedElement lookupImplicitSourceForAttribute(String name) {
        // context Environment::lookupImplicitSourceForAttribute(name: String) : NamedElement
        // pre: -- none
        // post: let foundElement : NamedElement = namedElements->select(mayBeImplicit)
        //         ->any( ne | not ne.getType().lookupAttribute(name).oclIsUndefined() ) 
        //       in 
        //         result = if foundAttribute.oclIsUndefined() 
        //                  then self.parent.lookupImplicitSourceForAttribute(name)
        //                  else foundElement
        //                  endif

        AttributeWithSource attrWithSource = lookupImplicitAttributeWithSource(name);
        if ( attrWithSource == null ) {
            return null; 
        } else {
            return attrWithSource.getSource();
        }
    }
    
    public AttributeWithSource lookupImplicitAttributeWithSource(String name) {
        Iterator it = implicitElements.entrySet().iterator();
        AttributeWithSource result = null;
        while ( it.hasNext() ) {
            Map.Entry entry = (Map.Entry) it.next();
            String currentName = (String) entry.getKey();
            NamedElement ne = (NamedElement) entry.getValue();
            
            Classifier elemType = ne.getType();
            Attribute attr = elemType.lookupAttribute(name);
            
            if ( attr != null ) {
                result = new AttributeWithSource();
                result.setAttribute(attr);
                result.setSource(ne);
                return result;
            }
        }
        Environment parent = this.getParent();
        if ( parent == null ) {
            return null;
        } else {
            return parent.lookupImplicitAttributeWithSource(name);
        }
    }
    
    

    /**
     * Lookup up a given association end name of an implicitly named element in
     * the current environment, including its parents. 
     * @see [OCL16], p. 4-26, ch. 4.4.1 [11]
     */
    public AssociationEnd lookupImplicitAssociationEnd(String name) {
        // context Environment::lookupImplicitAssociationEnd(name: String) : AssociationEnd
        // pre: -- none
        // post: let foundAssociationEnd : AssociationEnd
        //             = namedElements->select(mayBeImplicit) ->any( 
        //                 ne | not ne.getType().lookupAssociationEnd(name).oclIsUndefined()
        //               ) 
        //       in result = 
        //         if foundAssociationEnd.oclIsUndefined() 
        //         then self.parent.lookupImplicitAssociationEnd(name) 
        //         else foundAssociationEnd 
        //         endif
        
        AssociationEndWithSource aews = lookupImplicitAssociationEndWithSource(name);
        if ( aews == null ) {
            return null;
        } else {
            return aews.getAssociationEnd();
        }
    }
    
    public NamedElement lookupImplicitSourceForAssociationEnd(String name) {
        AssociationEndWithSource aews = lookupImplicitAssociationEndWithSource(name);
        if ( aews == null ) {
            return null;
        } else {
            return aews.getSource();
        }
    }
    
    public AssociationEndWithSource lookupImplicitAssociationEndWithSource(String name) {
        AssociationEndWithSource result = null;
        
        Iterator it = implicitElements.entrySet().iterator();
        while ( it.hasNext() ) {
            Map.Entry entry = (Map.Entry) it.next();
            String currentName = (String) entry.getKey();
            NamedElement ne = (NamedElement) entry.getValue();
            Classifier elemType = ne.getType();
            // check existence of association end   
            AssociationEnd assocEnd = elemType.lookupAssociationEnd(name);
            if ( assocEnd != null ) {
                result = new AssociationEndWithSource();
                result.setAssociationEnd(assocEnd);
                result.setSource(ne);
                return result;
            }
        }
        // not found, check for parent  
        Environment parent = this.getParent();
        if ( parent == null ) {
            return null;
        } else {
            return parent.lookupImplicitAssociationEndWithSource(name);
        }        
    }
    
    /**
     * Lookup up a given association class name of an implicitly named element in
     * the current environment, including its parents. 
     * @see [OCL16], p. 4-26, ch. 4.4.1 [11]
     */
    public AssociationClass lookupImplicitAssociationClass(String name) {
        // this operation is not specified in [OCL16], we implement it the same
        // way as for association ends:
        
        // context Environment::lookupImplicitAssociationEnd(name: String) : AssociationEnd
        // pre: -- none
        // post: let foundAssociationEnd : AssociationEnd
        //             = namedElements->select(mayBeImplicit) ->any( 
        //                 ne | not ne.getType().lookupAssociationEnd(name).oclIsUndefined()
        //               ) 
        //       in result = 
        //         if foundAssociationEnd.oclIsUndefined() 
        //         then self.parent.lookupImplicitAssociationEnd(name) 
        //         else foundAssociationEnd 
        //         endif
        
        AssociationClassWithSource acws = lookupImplicitAssociationClassWithSource(name);
        if ( acws == null ) {
            return null;
        } else {
            return acws.getAssociationClass();
        }
    }
    
    public NamedElement lookupImplicitSourceForAssociationClass(String name) {
        AssociationClassWithSource acws = lookupImplicitAssociationClassWithSource(name);
        if ( acws == null ) {
            return null;
        } else {
            return acws.getSource();
        }
    }
    
    public AssociationClassWithSource lookupImplicitAssociationClassWithSource(String name) {
        AssociationClassWithSource result = null;
        
        Iterator it = implicitElements.entrySet().iterator();
        while ( it.hasNext() ) {
            Map.Entry entry = (Map.Entry) it.next();
            String currentName = (String) entry.getKey();
            NamedElement ne = (NamedElement) entry.getValue();
            Classifier elemType = ne.getType();
            // check existence of association end   
            AssociationClass assocClass = elemType.lookupAssociationClass(name);
            if ( assocClass != null ) {
                result = new AssociationClassWithSource();
                result.setAssociationClass(assocClass);
                result.setSource(ne);
                return result;
            }
        }
        // not found, check for parent  
        Environment parent = this.getParent();
        if ( parent == null ) {
            return null;
        } else {
            return parent.lookupImplicitAssociationClassWithSource(name);
        }        
    }
    
    
    /**
     * Lookup up an operation of an implicitly named element with given name and
     * parameter types in the current environment, including its parents.
     * @see [OCL16], p. 4-26, ch. 4.4.1 [12]
     * @param FIXME FIXME FIXME (add documentation from OCL specification string)
     */
    public Operation lookupImplicitOperation(String name, List params) {
        // context Environment::lookupImplicitOperation(name: String, params : Sequence(Classifier)) : Operation
        // pre: -- none
        // post: let foundOperation : Operation 
        //             = namedElements->select(mayBeImplicit) ->any(
        //                 ne | not ne.getType().lookupOperation(name, params).oclIsUndefined() 
        //               ) 
        //             in result =
        //             if foundOperation.oclIsUndefined()
        //             then self.parent.lookupImplicitOperation(name)
        //             else foundOperation
        //             endif
        
        OperationWithSource ows = lookupImplicitOperationWithSource(name, params);
        if ( ows == null ) {
            return null;
        } else {
            return ows.getOperation();
        }
    }

    public OperationWithSource lookupImplicitOperationWithSource(String name, List params) {
        OperationWithSource result = null;
        
        Iterator it = implicitElements.entrySet().iterator();
        while ( it.hasNext() ) {
            Map.Entry entry = (Map.Entry) it.next();
            String currentName = (String) entry.getKey();
            NamedElement ne = (NamedElement) entry.getValue();            
            Classifier elemType = ne.getType();            
            Operation oper = elemType.lookupOperation(name, params);
            if ( oper != null ) {
                result = new OperationWithSource();
                result.setOperation(oper);
                result.setSource(ne);
                return result;
            }            
        }
        // not found, check for parent  
        Environment parent = this.getParent();
        if ( parent == null ) {
            return null;
        } else {
            return this.getParent().lookupImplicitOperationWithSource(name, params);
        }        
        
    }

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
    public String toString() {
        return "Environment[" + this.hashCode() + "]";
    }
    
    
}
