/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.datatypes;

import orgomg.cwm.objectmodel.core.Classifier;
import orgomg.cwm.objectmodel.core.StructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Union</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The Union class represents programming language unions and similarly structured data types. Because of the diversity of union semantics found across software systems, the Union and UnionMember classes are likely candidates for specialization to better capture union semantics in specific language extension packages.
 * 
 * A discriminated Union has a collection of UnionMembers that determine the sets of contents that the Union may contain. Such Unions have an attribute called the discriminator that identifies the memberCase value of the UnionMember that the Union currently contains. The discriminator is found via the UnionDiscriminator association to StructuralFeature. The discriminator may be embedded within UnionMembers or may be located outside the discriminator. If it is located within UnionMembers, the discriminator should occur in every UnionMember at the same location (often, the first).
 * 
 * Undiscriminated unions (for example, a C language union) are also supported, but have an empty discriminator reference, and the memberCase attribute of the UnionMembers it contains is ignored.
 * 
 * Undiscriminated Unions are often used to represent alternate views of a single physical
 * storage area. A fine degree of control over this aspect of Unions may be obtained by
 * creating a class that derives from both UnionMember and FixedOffsetField (in the
 * CWM Record package) and setting the offset attribute instances of that class
 * accordingly.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.datatypes.Union#getDiscriminator <em>Discriminator</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.datatypes.DatatypesPackage#getUnion()
 * @model
 * @generated
 */
public interface Union extends Classifier {
	/**
	 * Returns the value of the '<em><b>Discriminator</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.StructuralFeature#getDiscriminatedUnion <em>Discriminated Union</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the StructuralFeature instance that serves as the discriminator for the Union.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Discriminator</em>' reference.
	 * @see #setDiscriminator(StructuralFeature)
	 * @see orgomg.cwm.foundation.datatypes.DatatypesPackage#getUnion_Discriminator()
	 * @see orgomg.cwm.objectmodel.core.StructuralFeature#getDiscriminatedUnion
	 * @model opposite="discriminatedUnion"
	 * @generated
	 */
	StructuralFeature getDiscriminator();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.datatypes.Union#getDiscriminator <em>Discriminator</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Discriminator</em>' reference.
	 * @see #getDiscriminator()
	 * @generated
	 */
	void setDiscriminator(StructuralFeature value);

} // Union
