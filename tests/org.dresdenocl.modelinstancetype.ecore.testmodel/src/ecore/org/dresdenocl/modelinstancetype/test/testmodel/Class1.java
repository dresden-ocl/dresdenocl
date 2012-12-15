/**
 */
package ecore.org.dresdenocl.modelinstancetype.test.testmodel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class1</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getNonMultipleProperty <em>Non Multiple Property</em>}</li>
 *   <li>{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getMultipleUniqueOrderedProperty <em>Multiple Unique Ordered Property</em>}</li>
 *   <li>{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getMultipleUniqueUnorderedProperty <em>Multiple Unique Unordered Property</em>}</li>
 *   <li>{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getMultipleNonuniqueOrderedProperty <em>Multiple Nonunique Ordered Property</em>}</li>
 *   <li>{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getMultipleNonuniqueUnorderedProperty <em>Multiple Nonunique Unordered Property</em>}</li>
 *   <li>{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#isArgumentPropertyBooleanNonMultiple <em>Argument Property Boolean Non Multiple</em>}</li>
 *   <li>{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyStringNonMultiple <em>Argument Property String Non Multiple</em>}</li>
 *   <li>{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyBooleanMultiple <em>Argument Property Boolean Multiple</em>}</li>
 *   <li>{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyStringMultiple <em>Argument Property String Multiple</em>}</li>
 *   <li>{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyObjectNonMultiple <em>Argument Property Object Non Multiple</em>}</li>
 *   <li>{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyObjectMultiple <em>Argument Property Object Multiple</em>}</li>
 *   <li>{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyEnumerationLiteralNonMultiple <em>Argument Property Enumeration Literal Non Multiple</em>}</li>
 *   <li>{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyEnumerationLiteralMultiple <em>Argument Property Enumeration Literal Multiple</em>}</li>
 * </ul>
 * </p>
 *
 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage#getClass1()
 * @model
 * @generated
 */
public interface Class1 extends EObject {
	/**
	 * Returns the value of the '<em><b>Non Multiple Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Non Multiple Property</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Non Multiple Property</em>' attribute.
	 * @see #setNonMultipleProperty(String)
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage#getClass1_NonMultipleProperty()
	 * @model
	 * @generated
	 */
	String getNonMultipleProperty();

	/**
	 * Sets the value of the '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getNonMultipleProperty <em>Non Multiple Property</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Non Multiple Property</em>' attribute.
	 * @see #getNonMultipleProperty()
	 * @generated
	 */
	void setNonMultipleProperty(String value);

	/**
	 * Returns the value of the '<em><b>Multiple Unique Ordered Property</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multiple Unique Ordered Property</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multiple Unique Ordered Property</em>' attribute list.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage#getClass1_MultipleUniqueOrderedProperty()
	 * @model
	 * @generated
	 */
	EList<String> getMultipleUniqueOrderedProperty();

	/**
	 * Returns the value of the '<em><b>Multiple Unique Unordered Property</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multiple Unique Unordered Property</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multiple Unique Unordered Property</em>' attribute list.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage#getClass1_MultipleUniqueUnorderedProperty()
	 * @model ordered="false"
	 * @generated
	 */
	EList<String> getMultipleUniqueUnorderedProperty();

	/**
	 * Returns the value of the '<em><b>Multiple Nonunique Ordered Property</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multiple Nonunique Ordered Property</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multiple Nonunique Ordered Property</em>' attribute list.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage#getClass1_MultipleNonuniqueOrderedProperty()
	 * @model unique="false"
	 * @generated
	 */
	EList<String> getMultipleNonuniqueOrderedProperty();

	/**
	 * Returns the value of the '<em><b>Multiple Nonunique Unordered Property</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multiple Nonunique Unordered Property</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multiple Nonunique Unordered Property</em>' attribute list.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage#getClass1_MultipleNonuniqueUnorderedProperty()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	EList<String> getMultipleNonuniqueUnorderedProperty();

	/**
	 * Returns the value of the '<em><b>Argument Property Boolean Non Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Argument Property Boolean Non Multiple</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Argument Property Boolean Non Multiple</em>' attribute.
	 * @see #setArgumentPropertyBooleanNonMultiple(boolean)
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage#getClass1_ArgumentPropertyBooleanNonMultiple()
	 * @model
	 * @generated
	 */
	boolean isArgumentPropertyBooleanNonMultiple();

	/**
	 * Sets the value of the '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#isArgumentPropertyBooleanNonMultiple <em>Argument Property Boolean Non Multiple</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Argument Property Boolean Non Multiple</em>' attribute.
	 * @see #isArgumentPropertyBooleanNonMultiple()
	 * @generated
	 */
	void setArgumentPropertyBooleanNonMultiple(boolean value);

	/**
	 * Returns the value of the '<em><b>Argument Property String Non Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Argument Property String Non Multiple</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Argument Property String Non Multiple</em>' attribute.
	 * @see #setArgumentPropertyStringNonMultiple(String)
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage#getClass1_ArgumentPropertyStringNonMultiple()
	 * @model
	 * @generated
	 */
	String getArgumentPropertyStringNonMultiple();

	/**
	 * Sets the value of the '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyStringNonMultiple <em>Argument Property String Non Multiple</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Argument Property String Non Multiple</em>' attribute.
	 * @see #getArgumentPropertyStringNonMultiple()
	 * @generated
	 */
	void setArgumentPropertyStringNonMultiple(String value);

	/**
	 * Returns the value of the '<em><b>Argument Property Boolean Multiple</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Boolean}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Argument Property Boolean Multiple</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Argument Property Boolean Multiple</em>' attribute list.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage#getClass1_ArgumentPropertyBooleanMultiple()
	 * @model
	 * @generated
	 */
	EList<Boolean> getArgumentPropertyBooleanMultiple();

	/**
	 * Returns the value of the '<em><b>Argument Property String Multiple</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Argument Property String Multiple</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Argument Property String Multiple</em>' attribute list.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage#getClass1_ArgumentPropertyStringMultiple()
	 * @model
	 * @generated
	 */
	EList<String> getArgumentPropertyStringMultiple();

	/**
	 * Returns the value of the '<em><b>Argument Property Object Non Multiple</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Argument Property Object Non Multiple</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Argument Property Object Non Multiple</em>' reference.
	 * @see #setArgumentPropertyObjectNonMultiple(Class1)
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage#getClass1_ArgumentPropertyObjectNonMultiple()
	 * @model
	 * @generated
	 */
	Class1 getArgumentPropertyObjectNonMultiple();

	/**
	 * Sets the value of the '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyObjectNonMultiple <em>Argument Property Object Non Multiple</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Argument Property Object Non Multiple</em>' reference.
	 * @see #getArgumentPropertyObjectNonMultiple()
	 * @generated
	 */
	void setArgumentPropertyObjectNonMultiple(Class1 value);

	/**
	 * Returns the value of the '<em><b>Argument Property Object Multiple</b></em>' reference list.
	 * The list contents are of type {@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Argument Property Object Multiple</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Argument Property Object Multiple</em>' reference list.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage#getClass1_ArgumentPropertyObjectMultiple()
	 * @model
	 * @generated
	 */
	EList<Class1> getArgumentPropertyObjectMultiple();

	/**
	 * Returns the value of the '<em><b>Argument Property Enumeration Literal Non Multiple</b></em>' attribute.
	 * The literals are from the enumeration {@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Argument Property Enumeration Literal Non Multiple</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Argument Property Enumeration Literal Non Multiple</em>' attribute.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1
	 * @see #setArgumentPropertyEnumerationLiteralNonMultiple(Enumeration1)
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage#getClass1_ArgumentPropertyEnumerationLiteralNonMultiple()
	 * @model
	 * @generated
	 */
	Enumeration1 getArgumentPropertyEnumerationLiteralNonMultiple();

	/**
	 * Sets the value of the '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyEnumerationLiteralNonMultiple <em>Argument Property Enumeration Literal Non Multiple</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Argument Property Enumeration Literal Non Multiple</em>' attribute.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1
	 * @see #getArgumentPropertyEnumerationLiteralNonMultiple()
	 * @generated
	 */
	void setArgumentPropertyEnumerationLiteralNonMultiple(Enumeration1 value);

	/**
	 * Returns the value of the '<em><b>Argument Property Enumeration Literal Multiple</b></em>' attribute list.
	 * The list contents are of type {@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1}.
	 * The literals are from the enumeration {@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Argument Property Enumeration Literal Multiple</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Argument Property Enumeration Literal Multiple</em>' attribute list.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage#getClass1_ArgumentPropertyEnumerationLiteralMultiple()
	 * @model
	 * @generated
	 */
	EList<Enumeration1> getArgumentPropertyEnumerationLiteralMultiple();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void voidOperation();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean nonMultipleOperation();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	EList<String> multipleUniqueOrderedOperation();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model ordered="false"
	 * @generated
	 */
	EList<String> multipleUniqueUnorderedOperation();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model unique="false"
	 * @generated
	 */
	EList<String> multipleNonuniqueOrderedOperation();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	EList<String> multipleNonuniqueUnorderedOperation();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void voidOperationWithBooleanArgument(boolean arg1);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model arg1Many="true"
	 * @generated
	 */
	void voidOperationWithBooleanMultipleArgument(EList<Boolean> arg1);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void voidOperationWithStringArgument(String arg1);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model arg1Many="true"
	 * @generated
	 */
	void voidOperationWithStringMultipleArgument(EList<String> arg1);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void voidOperationWithObjectArgument(Class1 arg1);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model arg1Many="true"
	 * @generated
	 */
	void voidOperationWithObjectMultipleArgument(EList<Class1> arg1);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void voidOperationWithEnumerationLiteralArgument(Enumeration1 arg1);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model arg1Many="true"
	 * @generated
	 */
	void voidOperationWithEnumerationLiteralMultipleArgument(EList<Enumeration1> arg1);

} // Class1
