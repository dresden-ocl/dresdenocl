/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.modelinstancetype.test.testmodel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage;



/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Class1</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.Class1Impl#getNonMultipleProperty <em>Non Multiple Property</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.Class1Impl#getMultipleUniqueOrderedProperty <em>Multiple Unique Ordered Property</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.Class1Impl#getMultipleUniqueUnorderedProperty <em>Multiple Unique Unordered Property</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.Class1Impl#getMultipleNonuniqueOrderedProperty <em>Multiple Nonunique Ordered Property</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.Class1Impl#getMultipleNonuniqueUnorderedProperty <em>Multiple Nonunique Unordered Property</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.Class1Impl#isArgumentPropertyBooleanNonMultiple <em>Argument Property Boolean Non Multiple</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.Class1Impl#getArgumentPropertyStringNonMultiple <em>Argument Property String Non Multiple</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.Class1Impl#getArgumentPropertyBooleanMultiple <em>Argument Property Boolean Multiple</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.Class1Impl#getArgumentPropertyStringMultiple <em>Argument Property String Multiple</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.Class1Impl#getArgumentPropertyObjectNonMultiple <em>Argument Property Object Non Multiple</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.Class1Impl#getArgumentPropertyObjectMultiple <em>Argument Property Object Multiple</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.Class1Impl#getArgumentPropertyEnumerationLiteralNonMultiple <em>Argument Property Enumeration Literal Non Multiple</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.Class1Impl#getArgumentPropertyEnumerationLiteralMultiple <em>Argument Property Enumeration Literal Multiple</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Class1Impl extends EObjectImpl implements Class1 {

	/**
	 * The default value of the '{@link #getNonMultipleProperty() <em>Non Multiple Property</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getNonMultipleProperty()
	 * @generated
	 * @ordered
	 */
	protected static final String NON_MULTIPLE_PROPERTY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNonMultipleProperty() <em>Non Multiple Property</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getNonMultipleProperty()
	 * @generated
	 * @ordered
	 */
	protected String nonMultipleProperty = NON_MULTIPLE_PROPERTY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMultipleUniqueOrderedProperty()
	 * <em>Multiple Unique Ordered Property</em>}' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMultipleUniqueOrderedProperty()
	 * @generated
	 * @ordered
	 */
	protected EList<String> multipleUniqueOrderedProperty;

	/**
	 * The cached value of the '{@link #getMultipleUniqueUnorderedProperty()
	 * <em>Multiple Unique Unordered Property</em>}' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMultipleUniqueUnorderedProperty()
	 * @generated
	 * @ordered
	 */
	protected EList<String> multipleUniqueUnorderedProperty;

	/**
	 * The cached value of the '{@link #getMultipleNonuniqueOrderedProperty()
	 * <em>Multiple Nonunique Ordered Property</em>}' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMultipleNonuniqueOrderedProperty()
	 * @generated
	 * @ordered
	 */
	protected EList<String> multipleNonuniqueOrderedProperty;

	/**
	 * The cached value of the '{@link #getMultipleNonuniqueUnorderedProperty()
	 * <em>Multiple Nonunique Unordered Property</em>}' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMultipleNonuniqueUnorderedProperty()
	 * @generated
	 * @ordered
	 */
	protected EList<String> multipleNonuniqueUnorderedProperty;

	/**
	 * The default value of the '{@link #isArgumentPropertyBooleanNonMultiple()
	 * <em>Argument Property Boolean Non Multiple</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isArgumentPropertyBooleanNonMultiple()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ARGUMENT_PROPERTY_BOOLEAN_NON_MULTIPLE_EDEFAULT =
			false;

	/**
	 * The cached value of the '{@link #isArgumentPropertyBooleanNonMultiple()
	 * <em>Argument Property Boolean Non Multiple</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isArgumentPropertyBooleanNonMultiple()
	 * @generated
	 * @ordered
	 */
	protected boolean argumentPropertyBooleanNonMultiple =
			ARGUMENT_PROPERTY_BOOLEAN_NON_MULTIPLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getArgumentPropertyStringNonMultiple()
	 * <em>Argument Property String Non Multiple</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getArgumentPropertyStringNonMultiple()
	 * @generated
	 * @ordered
	 */
	protected static final String ARGUMENT_PROPERTY_STRING_NON_MULTIPLE_EDEFAULT =
			null;

	/**
	 * The cached value of the '{@link #getArgumentPropertyStringNonMultiple()
	 * <em>Argument Property String Non Multiple</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getArgumentPropertyStringNonMultiple()
	 * @generated
	 * @ordered
	 */
	protected String argumentPropertyStringNonMultiple =
			ARGUMENT_PROPERTY_STRING_NON_MULTIPLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getArgumentPropertyBooleanMultiple()
	 * <em>Argument Property Boolean Multiple</em>}' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getArgumentPropertyBooleanMultiple()
	 * @generated
	 * @ordered
	 */
	protected EList<Boolean> argumentPropertyBooleanMultiple;

	/**
	 * The cached value of the '{@link #getArgumentPropertyStringMultiple()
	 * <em>Argument Property String Multiple</em>}' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getArgumentPropertyStringMultiple()
	 * @generated
	 * @ordered
	 */
	protected EList<String> argumentPropertyStringMultiple;

	/**
	 * The cached value of the '{@link #getArgumentPropertyObjectNonMultiple()
	 * <em>Argument Property Object Non Multiple</em>}' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getArgumentPropertyObjectNonMultiple()
	 * @generated
	 * @ordered
	 */
	protected Class1 argumentPropertyObjectNonMultiple;

	/**
	 * The cached value of the '{@link #getArgumentPropertyObjectMultiple()
	 * <em>Argument Property Object Multiple</em>}' reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getArgumentPropertyObjectMultiple()
	 * @generated
	 * @ordered
	 */
	protected EList<Class1> argumentPropertyObjectMultiple;

	/**
	 * The default value of the '{@link #getArgumentPropertyEnumerationLiteralNonMultiple() <em>Argument Property Enumeration Literal Non Multiple</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getArgumentPropertyEnumerationLiteralNonMultiple()
	 * @generated
	 * @ordered
	 */
	protected static final Enumeration1 ARGUMENT_PROPERTY_ENUMERATION_LITERAL_NON_MULTIPLE_EDEFAULT =
			Enumeration1.LITERAL1;

	/**
	 * The cached value of the '{@link #getArgumentPropertyEnumerationLiteralNonMultiple() <em>Argument Property Enumeration Literal Non Multiple</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getArgumentPropertyEnumerationLiteralNonMultiple()
	 * @generated
	 * @ordered
	 */
	protected Enumeration1 argumentPropertyEnumerationLiteralNonMultiple =
			ARGUMENT_PROPERTY_ENUMERATION_LITERAL_NON_MULTIPLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getArgumentPropertyEnumerationLiteralMultiple() <em>Argument Property Enumeration Literal Multiple</em>}' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getArgumentPropertyEnumerationLiteralMultiple()
	 * @generated
	 * @ordered
	 */
	protected EList<Enumeration1> argumentPropertyEnumerationLiteralMultiple;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected Class1Impl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestmodelPackage.Literals.CLASS1;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getNonMultipleProperty() {
		return nonMultipleProperty;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setNonMultipleProperty(String newNonMultipleProperty) {
		String oldNonMultipleProperty = nonMultipleProperty;
		nonMultipleProperty = newNonMultipleProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelPackage.CLASS1__NON_MULTIPLE_PROPERTY, oldNonMultipleProperty, nonMultipleProperty));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getMultipleUniqueOrderedProperty() {
		if (multipleUniqueOrderedProperty == null) {
			multipleUniqueOrderedProperty = new EDataTypeUniqueEList<String>(String.class, this, TestmodelPackage.CLASS1__MULTIPLE_UNIQUE_ORDERED_PROPERTY);
		}
		return multipleUniqueOrderedProperty;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getMultipleUniqueUnorderedProperty() {
		if (multipleUniqueUnorderedProperty == null) {
			multipleUniqueUnorderedProperty = new EDataTypeUniqueEList<String>(String.class, this, TestmodelPackage.CLASS1__MULTIPLE_UNIQUE_UNORDERED_PROPERTY);
		}
		return multipleUniqueUnorderedProperty;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getMultipleNonuniqueOrderedProperty() {
		if (multipleNonuniqueOrderedProperty == null) {
			multipleNonuniqueOrderedProperty = new EDataTypeEList<String>(String.class, this, TestmodelPackage.CLASS1__MULTIPLE_NONUNIQUE_ORDERED_PROPERTY);
		}
		return multipleNonuniqueOrderedProperty;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getMultipleNonuniqueUnorderedProperty() {
		if (multipleNonuniqueUnorderedProperty == null) {
			multipleNonuniqueUnorderedProperty = new EDataTypeEList<String>(String.class, this, TestmodelPackage.CLASS1__MULTIPLE_NONUNIQUE_UNORDERED_PROPERTY);
		}
		return multipleNonuniqueUnorderedProperty;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isArgumentPropertyBooleanNonMultiple() {
		return argumentPropertyBooleanNonMultiple;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setArgumentPropertyBooleanNonMultiple(
			boolean newArgumentPropertyBooleanNonMultiple) {
		boolean oldArgumentPropertyBooleanNonMultiple = argumentPropertyBooleanNonMultiple;
		argumentPropertyBooleanNonMultiple = newArgumentPropertyBooleanNonMultiple;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_BOOLEAN_NON_MULTIPLE, oldArgumentPropertyBooleanNonMultiple, argumentPropertyBooleanNonMultiple));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getArgumentPropertyStringNonMultiple() {
		return argumentPropertyStringNonMultiple;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setArgumentPropertyStringNonMultiple(
			String newArgumentPropertyStringNonMultiple) {
		String oldArgumentPropertyStringNonMultiple = argumentPropertyStringNonMultiple;
		argumentPropertyStringNonMultiple = newArgumentPropertyStringNonMultiple;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_STRING_NON_MULTIPLE, oldArgumentPropertyStringNonMultiple, argumentPropertyStringNonMultiple));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Boolean> getArgumentPropertyBooleanMultiple() {
		if (argumentPropertyBooleanMultiple == null) {
			argumentPropertyBooleanMultiple = new EDataTypeUniqueEList<Boolean>(Boolean.class, this, TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_BOOLEAN_MULTIPLE);
		}
		return argumentPropertyBooleanMultiple;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getArgumentPropertyStringMultiple() {
		if (argumentPropertyStringMultiple == null) {
			argumentPropertyStringMultiple = new EDataTypeUniqueEList<String>(String.class, this, TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_STRING_MULTIPLE);
		}
		return argumentPropertyStringMultiple;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Class1 getArgumentPropertyObjectNonMultiple() {
		if (argumentPropertyObjectNonMultiple != null && argumentPropertyObjectNonMultiple.eIsProxy()) {
			InternalEObject oldArgumentPropertyObjectNonMultiple = (InternalEObject)argumentPropertyObjectNonMultiple;
			argumentPropertyObjectNonMultiple = (Class1)eResolveProxy(oldArgumentPropertyObjectNonMultiple);
			if (argumentPropertyObjectNonMultiple != oldArgumentPropertyObjectNonMultiple) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_OBJECT_NON_MULTIPLE, oldArgumentPropertyObjectNonMultiple, argumentPropertyObjectNonMultiple));
			}
		}
		return argumentPropertyObjectNonMultiple;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Class1 basicGetArgumentPropertyObjectNonMultiple() {
		return argumentPropertyObjectNonMultiple;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setArgumentPropertyObjectNonMultiple(
			Class1 newArgumentPropertyObjectNonMultiple) {
		Class1 oldArgumentPropertyObjectNonMultiple = argumentPropertyObjectNonMultiple;
		argumentPropertyObjectNonMultiple = newArgumentPropertyObjectNonMultiple;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_OBJECT_NON_MULTIPLE, oldArgumentPropertyObjectNonMultiple, argumentPropertyObjectNonMultiple));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Class1> getArgumentPropertyObjectMultiple() {
		if (argumentPropertyObjectMultiple == null) {
			argumentPropertyObjectMultiple = new EObjectResolvingEList<Class1>(Class1.class, this, TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_OBJECT_MULTIPLE);
		}
		return argumentPropertyObjectMultiple;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Enumeration1 getArgumentPropertyEnumerationLiteralNonMultiple() {
		return argumentPropertyEnumerationLiteralNonMultiple;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setArgumentPropertyEnumerationLiteralNonMultiple(
			Enumeration1 newArgumentPropertyEnumerationLiteralNonMultiple) {
		Enumeration1 oldArgumentPropertyEnumerationLiteralNonMultiple = argumentPropertyEnumerationLiteralNonMultiple;
		argumentPropertyEnumerationLiteralNonMultiple = newArgumentPropertyEnumerationLiteralNonMultiple == null ? ARGUMENT_PROPERTY_ENUMERATION_LITERAL_NON_MULTIPLE_EDEFAULT : newArgumentPropertyEnumerationLiteralNonMultiple;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_NON_MULTIPLE, oldArgumentPropertyEnumerationLiteralNonMultiple, argumentPropertyEnumerationLiteralNonMultiple));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Enumeration1> getArgumentPropertyEnumerationLiteralMultiple() {
		if (argumentPropertyEnumerationLiteralMultiple == null) {
			argumentPropertyEnumerationLiteralMultiple = new EDataTypeUniqueEList<Enumeration1>(Enumeration1.class, this, TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_MULTIPLE);
		}
		return argumentPropertyEnumerationLiteralMultiple;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void voidOperation() {

		/* Empty implementation. */
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean nonMultipleOperation() {

		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<String> multipleUniqueOrderedOperation() {

		return this.argumentPropertyStringMultiple;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<String> multipleUniqueUnorderedOperation() {

		return this.argumentPropertyStringMultiple;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<String> multipleNonuniqueOrderedOperation() {

		return this.argumentPropertyStringMultiple;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<String> multipleNonuniqueUnorderedOperation() {

		return this.argumentPropertyStringMultiple;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void voidOperationWithBooleanArgument(boolean arg1) {

		/* Empty implementation. */
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void voidOperationWithBooleanMultipleArgument(EList<Boolean> arg1) {

		if (arg1 == null) {
			throw new NullPointerException("Arg1 should not be null.");
		}
		// no else.
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void voidOperationWithStringArgument(String arg1) {

		if (arg1 == null) {
			throw new NullPointerException("Arg1 should not be null.");
		}
		// no else.
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void voidOperationWithStringMultipleArgument(EList<String> arg1) {

		if (arg1 == null) {
			throw new NullPointerException("Arg1 should not be null.");
		}
		// no else.
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void voidOperationWithObjectArgument(Class1 arg1) {

		if (arg1 == null) {
			throw new NullPointerException("Arg1 should not be null.");
		}
		// no else.
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void voidOperationWithObjectMultipleArgument(EList<Class1> arg1) {

		if (arg1 == null) {
			throw new NullPointerException("Arg1 should not be null.");
		}
		// no else.
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void voidOperationWithEnumerationLiteralArgument(Enumeration1 arg1) {

		if (arg1 == null) {
			throw new NullPointerException("Arg1 should not be null.");
		}
		// no else.
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void voidOperationWithEnumerationLiteralMultipleArgument(
			EList<Enumeration1> arg1) {

		if (arg1 == null) {
			throw new NullPointerException("Arg1 should not be null.");
		}
		// no else.
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestmodelPackage.CLASS1__NON_MULTIPLE_PROPERTY:
				return getNonMultipleProperty();
			case TestmodelPackage.CLASS1__MULTIPLE_UNIQUE_ORDERED_PROPERTY:
				return getMultipleUniqueOrderedProperty();
			case TestmodelPackage.CLASS1__MULTIPLE_UNIQUE_UNORDERED_PROPERTY:
				return getMultipleUniqueUnorderedProperty();
			case TestmodelPackage.CLASS1__MULTIPLE_NONUNIQUE_ORDERED_PROPERTY:
				return getMultipleNonuniqueOrderedProperty();
			case TestmodelPackage.CLASS1__MULTIPLE_NONUNIQUE_UNORDERED_PROPERTY:
				return getMultipleNonuniqueUnorderedProperty();
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_BOOLEAN_NON_MULTIPLE:
				return isArgumentPropertyBooleanNonMultiple();
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_STRING_NON_MULTIPLE:
				return getArgumentPropertyStringNonMultiple();
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_BOOLEAN_MULTIPLE:
				return getArgumentPropertyBooleanMultiple();
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_STRING_MULTIPLE:
				return getArgumentPropertyStringMultiple();
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_OBJECT_NON_MULTIPLE:
				if (resolve) return getArgumentPropertyObjectNonMultiple();
				return basicGetArgumentPropertyObjectNonMultiple();
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_OBJECT_MULTIPLE:
				return getArgumentPropertyObjectMultiple();
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_NON_MULTIPLE:
				return getArgumentPropertyEnumerationLiteralNonMultiple();
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_MULTIPLE:
				return getArgumentPropertyEnumerationLiteralMultiple();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TestmodelPackage.CLASS1__NON_MULTIPLE_PROPERTY:
				setNonMultipleProperty((String)newValue);
				return;
			case TestmodelPackage.CLASS1__MULTIPLE_UNIQUE_ORDERED_PROPERTY:
				getMultipleUniqueOrderedProperty().clear();
				getMultipleUniqueOrderedProperty().addAll((Collection<? extends String>)newValue);
				return;
			case TestmodelPackage.CLASS1__MULTIPLE_UNIQUE_UNORDERED_PROPERTY:
				getMultipleUniqueUnorderedProperty().clear();
				getMultipleUniqueUnorderedProperty().addAll((Collection<? extends String>)newValue);
				return;
			case TestmodelPackage.CLASS1__MULTIPLE_NONUNIQUE_ORDERED_PROPERTY:
				getMultipleNonuniqueOrderedProperty().clear();
				getMultipleNonuniqueOrderedProperty().addAll((Collection<? extends String>)newValue);
				return;
			case TestmodelPackage.CLASS1__MULTIPLE_NONUNIQUE_UNORDERED_PROPERTY:
				getMultipleNonuniqueUnorderedProperty().clear();
				getMultipleNonuniqueUnorderedProperty().addAll((Collection<? extends String>)newValue);
				return;
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_BOOLEAN_NON_MULTIPLE:
				setArgumentPropertyBooleanNonMultiple((Boolean)newValue);
				return;
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_STRING_NON_MULTIPLE:
				setArgumentPropertyStringNonMultiple((String)newValue);
				return;
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_BOOLEAN_MULTIPLE:
				getArgumentPropertyBooleanMultiple().clear();
				getArgumentPropertyBooleanMultiple().addAll((Collection<? extends Boolean>)newValue);
				return;
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_STRING_MULTIPLE:
				getArgumentPropertyStringMultiple().clear();
				getArgumentPropertyStringMultiple().addAll((Collection<? extends String>)newValue);
				return;
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_OBJECT_NON_MULTIPLE:
				setArgumentPropertyObjectNonMultiple((Class1)newValue);
				return;
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_OBJECT_MULTIPLE:
				getArgumentPropertyObjectMultiple().clear();
				getArgumentPropertyObjectMultiple().addAll((Collection<? extends Class1>)newValue);
				return;
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_NON_MULTIPLE:
				setArgumentPropertyEnumerationLiteralNonMultiple((Enumeration1)newValue);
				return;
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_MULTIPLE:
				getArgumentPropertyEnumerationLiteralMultiple().clear();
				getArgumentPropertyEnumerationLiteralMultiple().addAll((Collection<? extends Enumeration1>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TestmodelPackage.CLASS1__NON_MULTIPLE_PROPERTY:
				setNonMultipleProperty(NON_MULTIPLE_PROPERTY_EDEFAULT);
				return;
			case TestmodelPackage.CLASS1__MULTIPLE_UNIQUE_ORDERED_PROPERTY:
				getMultipleUniqueOrderedProperty().clear();
				return;
			case TestmodelPackage.CLASS1__MULTIPLE_UNIQUE_UNORDERED_PROPERTY:
				getMultipleUniqueUnorderedProperty().clear();
				return;
			case TestmodelPackage.CLASS1__MULTIPLE_NONUNIQUE_ORDERED_PROPERTY:
				getMultipleNonuniqueOrderedProperty().clear();
				return;
			case TestmodelPackage.CLASS1__MULTIPLE_NONUNIQUE_UNORDERED_PROPERTY:
				getMultipleNonuniqueUnorderedProperty().clear();
				return;
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_BOOLEAN_NON_MULTIPLE:
				setArgumentPropertyBooleanNonMultiple(ARGUMENT_PROPERTY_BOOLEAN_NON_MULTIPLE_EDEFAULT);
				return;
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_STRING_NON_MULTIPLE:
				setArgumentPropertyStringNonMultiple(ARGUMENT_PROPERTY_STRING_NON_MULTIPLE_EDEFAULT);
				return;
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_BOOLEAN_MULTIPLE:
				getArgumentPropertyBooleanMultiple().clear();
				return;
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_STRING_MULTIPLE:
				getArgumentPropertyStringMultiple().clear();
				return;
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_OBJECT_NON_MULTIPLE:
				setArgumentPropertyObjectNonMultiple((Class1)null);
				return;
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_OBJECT_MULTIPLE:
				getArgumentPropertyObjectMultiple().clear();
				return;
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_NON_MULTIPLE:
				setArgumentPropertyEnumerationLiteralNonMultiple(ARGUMENT_PROPERTY_ENUMERATION_LITERAL_NON_MULTIPLE_EDEFAULT);
				return;
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_MULTIPLE:
				getArgumentPropertyEnumerationLiteralMultiple().clear();
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
			case TestmodelPackage.CLASS1__NON_MULTIPLE_PROPERTY:
				return NON_MULTIPLE_PROPERTY_EDEFAULT == null ? nonMultipleProperty != null : !NON_MULTIPLE_PROPERTY_EDEFAULT.equals(nonMultipleProperty);
			case TestmodelPackage.CLASS1__MULTIPLE_UNIQUE_ORDERED_PROPERTY:
				return multipleUniqueOrderedProperty != null && !multipleUniqueOrderedProperty.isEmpty();
			case TestmodelPackage.CLASS1__MULTIPLE_UNIQUE_UNORDERED_PROPERTY:
				return multipleUniqueUnorderedProperty != null && !multipleUniqueUnorderedProperty.isEmpty();
			case TestmodelPackage.CLASS1__MULTIPLE_NONUNIQUE_ORDERED_PROPERTY:
				return multipleNonuniqueOrderedProperty != null && !multipleNonuniqueOrderedProperty.isEmpty();
			case TestmodelPackage.CLASS1__MULTIPLE_NONUNIQUE_UNORDERED_PROPERTY:
				return multipleNonuniqueUnorderedProperty != null && !multipleNonuniqueUnorderedProperty.isEmpty();
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_BOOLEAN_NON_MULTIPLE:
				return argumentPropertyBooleanNonMultiple != ARGUMENT_PROPERTY_BOOLEAN_NON_MULTIPLE_EDEFAULT;
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_STRING_NON_MULTIPLE:
				return ARGUMENT_PROPERTY_STRING_NON_MULTIPLE_EDEFAULT == null ? argumentPropertyStringNonMultiple != null : !ARGUMENT_PROPERTY_STRING_NON_MULTIPLE_EDEFAULT.equals(argumentPropertyStringNonMultiple);
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_BOOLEAN_MULTIPLE:
				return argumentPropertyBooleanMultiple != null && !argumentPropertyBooleanMultiple.isEmpty();
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_STRING_MULTIPLE:
				return argumentPropertyStringMultiple != null && !argumentPropertyStringMultiple.isEmpty();
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_OBJECT_NON_MULTIPLE:
				return argumentPropertyObjectNonMultiple != null;
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_OBJECT_MULTIPLE:
				return argumentPropertyObjectMultiple != null && !argumentPropertyObjectMultiple.isEmpty();
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_NON_MULTIPLE:
				return argumentPropertyEnumerationLiteralNonMultiple != ARGUMENT_PROPERTY_ENUMERATION_LITERAL_NON_MULTIPLE_EDEFAULT;
			case TestmodelPackage.CLASS1__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_MULTIPLE:
				return argumentPropertyEnumerationLiteralMultiple != null && !argumentPropertyEnumerationLiteralMultiple.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (nonMultipleProperty: ");
		result.append(nonMultipleProperty);
		result.append(", multipleUniqueOrderedProperty: ");
		result.append(multipleUniqueOrderedProperty);
		result.append(", multipleUniqueUnorderedProperty: ");
		result.append(multipleUniqueUnorderedProperty);
		result.append(", multipleNonuniqueOrderedProperty: ");
		result.append(multipleNonuniqueOrderedProperty);
		result.append(", multipleNonuniqueUnorderedProperty: ");
		result.append(multipleNonuniqueUnorderedProperty);
		result.append(", argumentPropertyBooleanNonMultiple: ");
		result.append(argumentPropertyBooleanNonMultiple);
		result.append(", argumentPropertyStringNonMultiple: ");
		result.append(argumentPropertyStringNonMultiple);
		result.append(", argumentPropertyBooleanMultiple: ");
		result.append(argumentPropertyBooleanMultiple);
		result.append(", argumentPropertyStringMultiple: ");
		result.append(argumentPropertyStringMultiple);
		result.append(", argumentPropertyEnumerationLiteralNonMultiple: ");
		result.append(argumentPropertyEnumerationLiteralNonMultiple);
		result.append(", argumentPropertyEnumerationLiteralMultiple: ");
		result.append(argumentPropertyEnumerationLiteralMultiple);
		result.append(')');
		return result.toString();
	}

} // Class1Impl
