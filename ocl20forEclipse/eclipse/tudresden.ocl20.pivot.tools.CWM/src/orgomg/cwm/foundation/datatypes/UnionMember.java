/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.datatypes;

import orgomg.cwm.objectmodel.core.Attribute;
import orgomg.cwm.objectmodel.core.Expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Union Member</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * UnionMembers are described as features of a Union and each represents one of the members of a Union. Note, however, that multiple case values can map to a single UnionMember. If isDefault is true, the union member is the default member.
 * 
 * UnionMember instances are allowed to have a memberCase and be the default case. UnionMember instances often represent structured storage areas. A particular UnionMember may be associated with a Classifier that describes its internal structure via the StructuralFeatureType association (defined in the ObjectModel::Core package). For example, the Record::Group class, itself a Classifier, can be used as the type of a UnionMember in a manner completely analogous to how it is used to describe the type of a structured field (see the instance diagrams in the Record metamodel chapter for details).
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.datatypes.UnionMember#getMemberCase <em>Member Case</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.datatypes.UnionMember#isIsDefault <em>Is Default</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.datatypes.DatatypesPackage#getUnionMember()
 * @model
 * @generated
 */
public interface UnionMember extends Attribute {
	/**
	 * Returns the value of the '<em><b>Member Case</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Contains the value of the Union’s discriminator for this UnionMember.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Member Case</em>' containment reference.
	 * @see #setMemberCase(Expression)
	 * @see orgomg.cwm.foundation.datatypes.DatatypesPackage#getUnionMember_MemberCase()
	 * @model containment="true"
	 * @generated
	 */
	Expression getMemberCase();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.datatypes.UnionMember#getMemberCase <em>Member Case</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Member Case</em>' containment reference.
	 * @see #getMemberCase()
	 * @generated
	 */
	void setMemberCase(Expression value);

	/**
	 * Returns the value of the '<em><b>Is Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates if this UnionMember is the default member of the Union (implying that when unstated, the Union’s discriminator would assume this instance’s memberCase value).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Default</em>' attribute.
	 * @see #setIsDefault(boolean)
	 * @see orgomg.cwm.foundation.datatypes.DatatypesPackage#getUnionMember_IsDefault()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsDefault();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.datatypes.UnionMember#isIsDefault <em>Is Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Default</em>' attribute.
	 * @see #isIsDefault()
	 * @generated
	 */
	void setIsDefault(boolean value);

} // UnionMember
