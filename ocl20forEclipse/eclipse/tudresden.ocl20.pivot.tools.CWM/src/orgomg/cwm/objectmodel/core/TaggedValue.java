/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tagged Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A tagged value allows information to be attached to any model element in the form of a "tagged value" pair (i.e., name = value). The interpretation of tagged value semantics is intentionally beyond the scope of CWM. It must be determined by user or tool conventions. It is expected that tools will define tags to supply information needed for their operations beyond the basic semantics of CWM. Such information could include code generation options, model management information, or user-specified semantics.
 * 
 * Even though TaggedValues are a simple and straightforward extension technique, their use restricts semantic interchange of metadata to only those tools that share a common understanding of the specific tagged value names.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.core.TaggedValue#getTag <em>Tag</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.TaggedValue#getValue <em>Value</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.TaggedValue#getStereotype <em>Stereotype</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.TaggedValue#getModelElement <em>Model Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.core.CorePackage#getTaggedValue()
 * @model
 * @generated
 */
public interface TaggedValue extends Element {
	/**
	 * Returns the value of the '<em><b>Tag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Contains the name of the TaggedValue. This name determines the semantics that are applicable to the contents of the value attribute.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Tag</em>' attribute.
	 * @see #setTag(String)
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getTaggedValue_Tag()
	 * @model dataType="orgomg.cwm.objectmodel.core.Name"
	 * @generated
	 */
	String getTag();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.core.TaggedValue#getTag <em>Tag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tag</em>' attribute.
	 * @see #getTag()
	 * @generated
	 */
	void setTag(String value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Contains the current value of the TaggedValue.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getTaggedValue_Value()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.core.TaggedValue#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Stereotype</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Stereotype#getRequiredTag <em>Required Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies a Stereotype instance that owns the TaggedValue instance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Stereotype</em>' container reference.
	 * @see #setStereotype(Stereotype)
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getTaggedValue_Stereotype()
	 * @see orgomg.cwm.objectmodel.core.Stereotype#getRequiredTag
	 * @model opposite="requiredTag"
	 * @generated
	 */
	Stereotype getStereotype();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.core.TaggedValue#getStereotype <em>Stereotype</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stereotype</em>' container reference.
	 * @see #getStereotype()
	 * @generated
	 */
	void setStereotype(Stereotype value);

	/**
	 * Returns the value of the '<em><b>Model Element</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.ModelElement#getTaggedValue <em>Tagged Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the ModelElement instance that owns the TaggedValue instance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Model Element</em>' container reference.
	 * @see #setModelElement(ModelElement)
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getTaggedValue_ModelElement()
	 * @see orgomg.cwm.objectmodel.core.ModelElement#getTaggedValue
	 * @model opposite="taggedValue"
	 * @generated
	 */
	ModelElement getModelElement();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.core.TaggedValue#getModelElement <em>Model Element</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Element</em>' container reference.
	 * @see #getModelElement()
	 * @generated
	 */
	void setModelElement(ModelElement value);

} // TaggedValue
