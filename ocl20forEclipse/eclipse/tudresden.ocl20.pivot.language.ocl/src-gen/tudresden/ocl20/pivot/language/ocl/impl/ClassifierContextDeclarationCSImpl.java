/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tudresden.ocl20.pivot.language.ocl.ClassifierContextDeclarationCS;
import tudresden.ocl20.pivot.language.ocl.InvariantOrDefinitionCS;
import tudresden.ocl20.pivot.language.ocl.OclPackage;
import tudresden.ocl20.pivot.language.ocl.TypeCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Classifier Context Declaration CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.ClassifierContextDeclarationCSImpl#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.ClassifierContextDeclarationCSImpl#getInvariantsAndDefinitions <em>Invariants And Definitions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassifierContextDeclarationCSImpl extends ContextDeclarationCSImpl implements ClassifierContextDeclarationCS {
	/**
   * The cached value of the '{@link #getTypeName() <em>Type Name</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getTypeName()
   * @generated
   * @ordered
   */
	protected TypeCS typeName;

	/**
   * The cached value of the '{@link #getInvariantsAndDefinitions() <em>Invariants And Definitions</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getInvariantsAndDefinitions()
   * @generated
   * @ordered
   */
	protected EList<InvariantOrDefinitionCS> invariantsAndDefinitions;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ClassifierContextDeclarationCSImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OclPackage.Literals.CLASSIFIER_CONTEXT_DECLARATION_CS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public TypeCS getTypeName() {
    return typeName;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetTypeName(TypeCS newTypeName, NotificationChain msgs) {
    TypeCS oldTypeName = typeName;
    typeName = newTypeName;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS__TYPE_NAME, oldTypeName, newTypeName);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setTypeName(TypeCS newTypeName) {
    if (newTypeName != typeName)
    {
      NotificationChain msgs = null;
      if (typeName != null)
        msgs = ((InternalEObject)typeName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS__TYPE_NAME, null, msgs);
      if (newTypeName != null)
        msgs = ((InternalEObject)newTypeName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS__TYPE_NAME, null, msgs);
      msgs = basicSetTypeName(newTypeName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS__TYPE_NAME, newTypeName, newTypeName));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<InvariantOrDefinitionCS> getInvariantsAndDefinitions() {
    if (invariantsAndDefinitions == null)
    {
      invariantsAndDefinitions = new EObjectContainmentEList<InvariantOrDefinitionCS>(InvariantOrDefinitionCS.class, this, OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS__INVARIANTS_AND_DEFINITIONS);
    }
    return invariantsAndDefinitions;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID)
    {
      case OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS__TYPE_NAME:
        return basicSetTypeName(null, msgs);
      case OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS__INVARIANTS_AND_DEFINITIONS:
        return ((InternalEList<?>)getInvariantsAndDefinitions()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID)
    {
      case OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS__TYPE_NAME:
        return getTypeName();
      case OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS__INVARIANTS_AND_DEFINITIONS:
        return getInvariantsAndDefinitions();
    }
    return super.eGet(featureID, resolve, coreType);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
    switch (featureID)
    {
      case OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS__TYPE_NAME:
        setTypeName((TypeCS)newValue);
        return;
      case OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS__INVARIANTS_AND_DEFINITIONS:
        getInvariantsAndDefinitions().clear();
        getInvariantsAndDefinitions().addAll((Collection<? extends InvariantOrDefinitionCS>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void eUnset(int featureID) {
    switch (featureID)
    {
      case OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS__TYPE_NAME:
        setTypeName((TypeCS)null);
        return;
      case OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS__INVARIANTS_AND_DEFINITIONS:
        getInvariantsAndDefinitions().clear();
        return;
    }
    super.eUnset(featureID);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public boolean eIsSet(int featureID) {
    switch (featureID)
    {
      case OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS__TYPE_NAME:
        return typeName != null;
      case OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS__INVARIANTS_AND_DEFINITIONS:
        return invariantsAndDefinitions != null && !invariantsAndDefinitions.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ClassifierContextDeclarationCSImpl
