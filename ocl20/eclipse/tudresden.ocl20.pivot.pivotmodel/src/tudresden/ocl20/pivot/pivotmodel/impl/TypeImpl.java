/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * $Id$
 */
package tudresden.ocl20.pivot.pivotmodel.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import tudresden.ocl20.pivot.pivotmodel.ConstrainableElement;
import tudresden.ocl20.pivot.pivotmodel.GenericElement;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.PivotModelFactory;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.TypeParameter;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Type</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl#getOwnedTypeParameter <em>Owned Type Parameter</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl#getSuperType <em>Super Type</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl#getOwnedOperation <em>Owned Operation</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl#getOwnedProperty <em>Owned Property</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl#getNamespace <em>Namespace</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeImpl extends NamedElementImpl implements Type {

  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(TypeImpl.class);

  /**
   * The cached value of the '{@link #getOwnedTypeParameter() <em>Owned Type Parameter</em>}' containment reference list.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getOwnedTypeParameter()
   * @generated
   * @ordered
   */
  protected EList<TypeParameter> ownedTypeParameter = null;

  /**
   * The cached value of the '{@link #getSuperType() <em>Super Type</em>}' reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getSuperType()
   * @generated
   * @ordered
   */
  protected EList<Type> superType = null;

  /**
   * The cached value of the '{@link #getOwnedOperation() <em>Owned Operation</em>}' containment reference list.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getOwnedOperation()
   * @generated
   * @ordered
   */
  protected EList<Operation> ownedOperation = null;

  /**
   * The cached value of the '{@link #getOwnedProperty() <em>Owned Property</em>}' containment reference list.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #getOwnedProperty()
   * @generated
   * @ordered
   */
  protected EList<Property> ownedProperty = null;

  // a map that contains instances of this Type with some or all of their type parameters bound
  private static Map<String, Type> boundTypes;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  protected TypeImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return PivotModelPackageImpl.Literals.TYPE;
  }

  /**
   * Overridden to return the {@link #getNamespace() namespace} of this <code>Type</code>.
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl#getOwner()
   */
  @Override
  public NamedElement getOwner() {
    return getNamespace();
  }

  /**
   * The implementation in this class simply redirects to {{@link #getSuperTypeGen()} which
   * contains the code generated by EMF. Client may, however, override this method to provide
   * specific behaviour, e.g., adapt to other model repositories.
   * 
   * @generated NOT
   */
  public List<Type> getSuperType() {
    return getSuperTypeGen();
  }

  /**
   * <!-- begin-user-doc -->The code generated for {{@link #getSuperType()} is redirected to this
   * method. <!-- end-user-doc -->
   * @generated
   */
  protected final List<Type> getSuperTypeGen() {
    if (superType == null) {
      superType = new EObjectEList<Type>(Type.class,this,PivotModelPackageImpl.TYPE__SUPER_TYPE);
    }
    return superType;
  }

  /**
   * The implementation in this class simply redirects to {{@link #getOwnedOperationGen()} which
   * contains the code generated by EMF. Clients may, however, override this method to provide
   * specific behaviour, e.g., adapt to other model repositories.
   * 
   * @generated NOT
   */
  public List<Operation> getOwnedOperation() {
    return getOwnedOperationGen();
  }

  /**
   * The code generated for {{@link #getOwnedOperation()} is redirected to this method.
   * 
   * @generated
   */
  protected final List<Operation> getOwnedOperationGen() {
    if (ownedOperation == null) {
      ownedOperation = new EObjectContainmentWithInverseEList<Operation>(Operation.class,this,
          PivotModelPackageImpl.TYPE__OWNED_OPERATION,PivotModelPackageImpl.OPERATION__OWNING_TYPE);
    }
    return ownedOperation;
  }

  /**
   * The implementation in this class simply redirects to {{@link #getOwnedPropertyGen()} which
   * contains the code generated by EMF. Clients may, however, override this method to provide
   * specific behaviour, e.g., adapt to other model repositories.
   * 
   * @generated NOT
   */
  public List<Property> getOwnedProperty() {
    return getOwnedPropertyGen();
  }

  /**
   * The code generated for {{@link #getOwnedProperty()} is redirected to this method.
   * 
   * @generated
   */
  protected final List<Property> getOwnedPropertyGen() {
    if (ownedProperty == null) {
      ownedProperty = new EObjectContainmentWithInverseEList<Property>(Property.class,this,
          PivotModelPackageImpl.TYPE__OWNED_PROPERTY,PivotModelPackageImpl.PROPERTY__OWNING_TYPE);
    }
    return ownedProperty;
  }

  /**
   * The implementation in this class simply redirects to {{@link #getOwnedTypeParameterGen()}
   * which contains the code generated by EMF. Clients may, however, override this method to provide
   * specific behaviour, e.g., adapt to other model repositories.
   * 
   * @generated NOT
   */
  public List<TypeParameter> getOwnedTypeParameter() {
    return getOwnedTypeParameterGen();
  }

  /**
   * <!-- begin-user-doc -->The code generated for {{@link #getOwnedTypeParameterGen()} is
   * redirected to this method.<!-- end-user-doc -->
   * @generated
   */
  protected final List<TypeParameter> getOwnedTypeParameterGen() {
    if (ownedTypeParameter == null) {
      ownedTypeParameter = new EObjectContainmentWithInverseEList<TypeParameter>(
          TypeParameter.class,this,PivotModelPackageImpl.TYPE__OWNED_TYPE_PARAMETER,
          PivotModelPackageImpl.TYPE_PARAMETER__GENERIC_ELEMENT);
    }
    return ownedTypeParameter;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public Namespace getNamespace() {
    if (eContainerFeatureID != PivotModelPackageImpl.TYPE__NAMESPACE) return null;
    return (Namespace) eContainer();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetNamespace(Namespace newNamespace, NotificationChain msgs) {
    msgs = eBasicSetContainer((InternalEObject) newNamespace,PivotModelPackageImpl.TYPE__NAMESPACE,
        msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public void setNamespace(Namespace newNamespace) {
    if (newNamespace != eInternalContainer()
        || (eContainerFeatureID != PivotModelPackageImpl.TYPE__NAMESPACE && newNamespace != null)) {
      if (EcoreUtil.isAncestor(this,(EObject) newNamespace))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
      NotificationChain msgs = null;
      if (eInternalContainer() != null) msgs = eBasicRemoveFromContainer(msgs);
      if (newNamespace != null)
        msgs = ((InternalEObject) newNamespace).eInverseAdd(this,
            PivotModelPackageImpl.NAMESPACE__OWNED_TYPE,Namespace.class,msgs);
      msgs = basicSetNamespace(newNamespace,msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,PivotModelPackageImpl.TYPE__NAMESPACE,
          newNamespace,newNamespace));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public boolean conformsTo(Type other) {
    if (logger.isDebugEnabled()) {
      logger.debug("conformsTo(other=" + other + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    boolean conformant;

    // by default a type does not conform to another
    conformant = false;

    // a type conforms to itself
    if (this.equals(other)) {
      conformant = true;
    }

    // a type conforms to another type if one if its supertypes conforms to the other type
    else {
      for (Type superType : getSuperType()) {
        conformant = superType.conformsTo(other);
        if (conformant) break;
      }
    }

    if (logger.isDebugEnabled()) {
      logger.debug("conformsTo() - exit - return value=" + conformant); //$NON-NLS-1$
    }

    return conformant;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public Type commonSuperType(Type other) {
    Type commonSuperType;

    // by default there is no common supertype
    commonSuperType = null;

    // this type is the common supertype if types are equal or the other conforms to this one
    if (this.equals(other) || other == null || other.conformsTo(this)) {
      commonSuperType = this;
    }

    // else check inheritance hierachies of this and the other type
    else {
      // the super types in one inheritance level for this and the other type, respectively, and
      // the transitive closure of all types in both hierachies
      Set<Type> thisSuperTypes, otherSuperTypes, allThisTypes, allOtherTypes;

      // initialize
      thisSuperTypes = new HashSet<Type>();
      otherSuperTypes = new HashSet<Type>();
      allThisTypes = new HashSet<Type>();
      allOtherTypes = new HashSet<Type>();

      // add the parents of both types to the corresponding sets
      thisSuperTypes.addAll(this.getSuperType());
      otherSuperTypes.addAll(other.getSuperType());

      // add this and the other type to the transitive closure
      allThisTypes.add(this);
      allOtherTypes.add(other);

      // go up both inheritance hierarchies to the top
      while (!(thisSuperTypes.isEmpty() && otherSuperTypes.isEmpty())) {
        Set<Type> temp = new HashSet<Type>();

        // add the current super types to the transitive closure of each hierarchy
        allThisTypes.addAll(thisSuperTypes);
        allOtherTypes.addAll(otherSuperTypes);

        // check if one of this type's current supertypes is contained in the other type's hierarchy
        for (Type type : thisSuperTypes) {

          if (allOtherTypes.contains(type)) {
            commonSuperType = type;
            break;
          }

          // remember the types of the next hierarchy level
          temp.addAll(type.getSuperType());
        }

        // quit if we have found a super type
        if (commonSuperType != null) {
          break;
        }

        // save the next hierarchy level types for the next iteration
        thisSuperTypes = temp;
        temp.clear();

        // check if one of the other type's current supertypes is contained in this type's hierarchy
        for (Type type : otherSuperTypes) {

          if (allThisTypes.contains(type)) {
            commonSuperType = type;
            break;
          }

          // remember the types of the next hierarchy level
          temp.addAll(type.getSuperType());
        }

        // quit if we have found a super type
        if (commonSuperType != null) {
          break;
        }

        // save the next hierarchy level types for the next iteration
        thisSuperTypes = temp;
        temp.clear();
      }
    }

    return commonSuperType;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public List<Property> allProperties() {
    List<Property> allProperties = new ArrayList<Property>();

    // add the properties of this type
    allProperties.addAll(getOwnedProperty());

    // add the properties of super types
    for (Type superType : getSuperType()) {
      allProperties.addAll(superType.allProperties());
    }

    return allProperties;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public List<Operation> allOperations() {
    List<Operation> allOperations = new ArrayList<Operation>();

    // add the properties of this type
    allOperations.addAll(getOwnedOperation());

    // add the properties of super types
    for (Type superType : getSuperType()) {
      allOperations.addAll(superType.allOperations());
    }

    return allOperations;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public Property lookupProperty(String name) {
    if (logger.isDebugEnabled()) {
      logger.debug("lookupProperty(name=" + name + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    Property property = null;

    // precondition check
    if (name == null) {
      logger.warn("Tried to lookup a property using a null name!"); //$NON-NLS-1$
      return null;
    }

    for (Property p : allProperties()) {
      if (name.equals(p.getName())) {
        property = p;
        break;
      }
    }

    if (logger.isDebugEnabled()) {
      logger.debug("lookupProperty() - exit - return value=" + property); //$NON-NLS-1$
    }

    return property;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public Operation lookupOperation(String name, List<Type> paramTypes) {
    if (logger.isDebugEnabled()) {
      logger.debug("lookupOperation(name=" + name + ", paramTypes=" + paramTypes + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    Operation operation = null;

    // precondition check
    if (name == null || paramTypes == null) {
      logger.warn("Tried to lookup an operation with one of the parameters being null!"); //$NON-NLS-1$
      return null;
    }

    for (Operation o : allOperations()) {
      if (name.equals(o.getName()) && o.hasMatchingSignature(paramTypes)) {
        operation = o;
        break;
      }
    }

    if (logger.isDebugEnabled()) {
      logger.debug("lookupOperation() - exit - return value=" + operation); //$NON-NLS-1$
    }

    return operation;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public Type addProperty(Property property) {
    if (logger.isDebugEnabled()) {
      logger.debug("addProperty(p=" + property + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // use the generated method, not the client-specific list obtained through getOwnedOperation()
    getOwnedPropertyGen().add(property);

    if (logger.isDebugEnabled()) {
      logger.debug("addProperty(p=" + property + ") - exit"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    return this;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public Type addOperation(Operation operation) {
    if (logger.isDebugEnabled()) {
      logger.debug("addOperation(o=" + operation + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // use the generated method, not the client-specific list obtained through getOwnedOperation()
    getOwnedOperationGen().add(operation);

    if (logger.isDebugEnabled()) {
      logger.debug("addOperation() - exit"); //$NON-NLS-1$
    }

    return this;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public Type addSuperType(Type type) {
    if (logger.isDebugEnabled()) {
      logger.debug("addSuperType(t=" + type + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // use the generated method, not the client-specific list obtained through getSuperType()
    getSuperTypeGen().add(type);

    if (logger.isDebugEnabled()) {
      logger.debug("addSuperType(t=" + type + ") - exit"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    return this;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public Type addTypeParameter(TypeParameter typeParameter) {
    if (logger.isDebugEnabled()) {
      logger.debug("addTypeParameter(typeParameter=" + typeParameter + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // use the generated method, not the one that may be overridden by clients
    getOwnedTypeParameterGen().add(typeParameter);

    if (logger.isDebugEnabled()) {
      logger.debug("addTypeParameter() - exit"); //$NON-NLS-1$
    }
    return this;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public Type bindTypeParameter(List<TypeParameter> parameters, List<? extends Type> types) {
    if (logger.isDebugEnabled()) {
      logger.debug("bindTypeParameter(parameters=" + parameters + ", types=" + types + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    String bindingKey;
    Type boundType;

    // precondition check
    if (parameters == null || types == null || parameters.size() != types.size()) {
      throw new IllegalArgumentException(
          "Illegal arguments: parameters=" + parameters + ", types=" + types); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // determine the String identifier for the binding
    bindingKey = GenericElements.determineBindingKey(this,parameters,types);

    // try to find a previously bound type and create a new one if necessary
    boundType = getBoundTypes().get(bindingKey);

    if (boundType == null) {
      boundType = this.clone();

      // remove the type parameter that is going to be bound; note that we cannot simply use
      // removeAll because EObjectEList uses object identity, not object equality for comparison
      for (Iterator<TypeParameter> it = boundType.getOwnedTypeParameter().iterator(); it.hasNext();) {
        TypeParameter ownedTypeParameter = it.next();

        for (TypeParameter typeParameterToBind : parameters) {
          if (ownedTypeParameter.equals(typeParameterToBind)) {
            it.remove();
          }
        }
      }

      // cache early to prevent endless loop if properties or operations have the same generic type
      boundTypes.put(bindingKey,boundType);

      // bind all properties
      for (Property property : boundType.allProperties()) {
        if (property.getType() == null && property.getGenericType() != null) {
          property.getGenericType().bindTypedElement(parameters,types);
        }
      }

      // bind all operations
      for (Operation operation : boundType.allOperations()) {
        if (operation.getType() == null && operation.getGenericType() != null) {
          operation.getGenericType().bindTypedElement(parameters,types);
        }

        // bind the parameters of the operation
        for (Parameter parameter : operation.getOwnedParameter()) {
          if (parameter.getType() == null && parameter.getGenericType() != null) {
            parameter.getGenericType().bindTypedElement(parameters,types);
          }
        }
      }
    }

    if (logger.isDebugEnabled()) {
      logger.debug("bindTypeParameter() - exit - return value=" + boundType); //$NON-NLS-1$
    }

    return boundType;
  }

  /**
   * Helper method that lazily creates the map with cached bound types
   * 
   * @return a {@code Map<String,TypeParameter>} instance
   */
  protected Map<String, Type> getBoundTypes() {

    if (boundTypes == null) {
      boundTypes = new HashMap<String, Type>();
    }

    return boundTypes;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#clone()
   */
  @Override
  public Type clone() {
    return initialize(PivotModelFactory.INSTANCE.createType());
  }

  /**
   * Convenience method for subclasses that initializes a cloned <code>Type</code> with the
   * properties of this <code>Type</code>.
   */
  protected Type initialize(Type clone) {
    super.initialize(clone);

    // clone type parameters
    for (TypeParameter typeParameter : getOwnedTypeParameter()) {
      clone.addTypeParameter(typeParameter.clone());
    }

    // clone properties
    for (Property property : getOwnedProperty()) {
      clone.addProperty(property.clone());
    }

    // clone operations
    for (Operation operation : getOwnedOperation()) {
      clone.addOperation(operation.clone());
    }

    // copy supertype list; do not clone the super types because they are not contained by this type
    for (Type superType : getSuperType()) {
      clone.addSuperType(superType);
    }

    return clone;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID,
      NotificationChain msgs) {
    switch (featureID) {
      case PivotModelPackageImpl.TYPE__OWNED_TYPE_PARAMETER:
        return ((InternalEList<InternalEObject>) (InternalEList<?>) getOwnedTypeParameter())
            .basicAdd(otherEnd,msgs);
      case PivotModelPackageImpl.TYPE__OWNED_OPERATION:
        return ((InternalEList<InternalEObject>) (InternalEList<?>) getOwnedOperation()).basicAdd(
            otherEnd,msgs);
      case PivotModelPackageImpl.TYPE__OWNED_PROPERTY:
        return ((InternalEList<InternalEObject>) (InternalEList<?>) getOwnedProperty()).basicAdd(
            otherEnd,msgs);
      case PivotModelPackageImpl.TYPE__NAMESPACE:
        if (eInternalContainer() != null) msgs = eBasicRemoveFromContainer(msgs);
        return basicSetNamespace((Namespace) otherEnd,msgs);
    }
    return super.eInverseAdd(otherEnd,featureID,msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID,
      NotificationChain msgs) {
    switch (featureID) {
      case PivotModelPackageImpl.TYPE__OWNED_TYPE_PARAMETER:
        return ((InternalEList<?>) getOwnedTypeParameter()).basicRemove(otherEnd,msgs);
      case PivotModelPackageImpl.TYPE__OWNED_OPERATION:
        return ((InternalEList<?>) getOwnedOperation()).basicRemove(otherEnd,msgs);
      case PivotModelPackageImpl.TYPE__OWNED_PROPERTY:
        return ((InternalEList<?>) getOwnedProperty()).basicRemove(otherEnd,msgs);
      case PivotModelPackageImpl.TYPE__NAMESPACE:
        return basicSetNamespace(null,msgs);
    }
    return super.eInverseRemove(otherEnd,featureID,msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
    switch (eContainerFeatureID) {
      case PivotModelPackageImpl.TYPE__NAMESPACE:
        return eInternalContainer().eInverseRemove(this,
            PivotModelPackageImpl.NAMESPACE__OWNED_TYPE,Namespace.class,msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case PivotModelPackageImpl.TYPE__OWNED_TYPE_PARAMETER:
        return getOwnedTypeParameter();
      case PivotModelPackageImpl.TYPE__SUPER_TYPE:
        return getSuperType();
      case PivotModelPackageImpl.TYPE__OWNED_OPERATION:
        return getOwnedOperation();
      case PivotModelPackageImpl.TYPE__OWNED_PROPERTY:
        return getOwnedProperty();
      case PivotModelPackageImpl.TYPE__NAMESPACE:
        return getNamespace();
    }
    return super.eGet(featureID,resolve,coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case PivotModelPackageImpl.TYPE__OWNED_TYPE_PARAMETER:
        getOwnedTypeParameter().clear();
        getOwnedTypeParameter().addAll((Collection<? extends TypeParameter>) newValue);
        return;
      case PivotModelPackageImpl.TYPE__SUPER_TYPE:
        getSuperType().clear();
        getSuperType().addAll((Collection<? extends Type>) newValue);
        return;
      case PivotModelPackageImpl.TYPE__OWNED_OPERATION:
        getOwnedOperation().clear();
        getOwnedOperation().addAll((Collection<? extends Operation>) newValue);
        return;
      case PivotModelPackageImpl.TYPE__OWNED_PROPERTY:
        getOwnedProperty().clear();
        getOwnedProperty().addAll((Collection<? extends Property>) newValue);
        return;
      case PivotModelPackageImpl.TYPE__NAMESPACE:
        setNamespace((Namespace) newValue);
        return;
    }
    super.eSet(featureID,newValue);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
      case PivotModelPackageImpl.TYPE__OWNED_TYPE_PARAMETER:
        getOwnedTypeParameter().clear();
        return;
      case PivotModelPackageImpl.TYPE__SUPER_TYPE:
        getSuperType().clear();
        return;
      case PivotModelPackageImpl.TYPE__OWNED_OPERATION:
        getOwnedOperation().clear();
        return;
      case PivotModelPackageImpl.TYPE__OWNED_PROPERTY:
        getOwnedProperty().clear();
        return;
      case PivotModelPackageImpl.TYPE__NAMESPACE:
        setNamespace((Namespace) null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
      case PivotModelPackageImpl.TYPE__OWNED_TYPE_PARAMETER:
        return ownedTypeParameter != null && !ownedTypeParameter.isEmpty();
      case PivotModelPackageImpl.TYPE__SUPER_TYPE:
        return superType != null && !superType.isEmpty();
      case PivotModelPackageImpl.TYPE__OWNED_OPERATION:
        return ownedOperation != null && !ownedOperation.isEmpty();
      case PivotModelPackageImpl.TYPE__OWNED_PROPERTY:
        return ownedProperty != null && !ownedProperty.isEmpty();
      case PivotModelPackageImpl.TYPE__NAMESPACE:
        return getNamespace() != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
    if (baseClass == ConstrainableElement.class) {
      switch (derivedFeatureID) {
        default:
          return -1;
      }
    }
    if (baseClass == GenericElement.class) {
      switch (derivedFeatureID) {
        case PivotModelPackageImpl.TYPE__OWNED_TYPE_PARAMETER:
          return PivotModelPackageImpl.GENERIC_ELEMENT__OWNED_TYPE_PARAMETER;
        default:
          return -1;
      }
    }
    return super.eBaseStructuralFeatureID(derivedFeatureID,baseClass);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
    if (baseClass == ConstrainableElement.class) {
      switch (baseFeatureID) {
        default:
          return -1;
      }
    }
    if (baseClass == GenericElement.class) {
      switch (baseFeatureID) {
        case PivotModelPackageImpl.GENERIC_ELEMENT__OWNED_TYPE_PARAMETER:
          return PivotModelPackageImpl.TYPE__OWNED_TYPE_PARAMETER;
        default:
          return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID,baseClass);
  }

  /**
   * Returns a string representing this <code>Type</code>. Includes its name and namespace.
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE).append("name",getName()) //$NON-NLS-1$
        .append("namespace",getNamespace()).toString(); //$NON-NLS-1$
  }

} // TypeImpl
