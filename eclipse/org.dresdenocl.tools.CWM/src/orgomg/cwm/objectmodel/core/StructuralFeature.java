/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.core;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.foundation.datatypes.Union;

import orgomg.cwm.foundation.keysindexes.IndexedFeature;
import orgomg.cwm.foundation.keysindexes.KeyRelationship;
import orgomg.cwm.foundation.keysindexes.UniqueKey;

import orgomg.cwm.objectmodel.instance.Slot;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Structural Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A structural feature refers to a static feature of a model element.
 * 
 * In the metamodel, a StructuralFeature declares a structural aspect of a Classifier that is typed, such as an attribute. For example, it specifies the multiplicity and changeability of the StructuralFeature. StructuralFeature is an abstract metaclass.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.core.StructuralFeature#getChangeability <em>Changeability</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.StructuralFeature#getMultiplicity <em>Multiplicity</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.StructuralFeature#getOrdering <em>Ordering</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.StructuralFeature#getTargetScope <em>Target Scope</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.StructuralFeature#getType <em>Type</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.StructuralFeature#getSlot <em>Slot</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.StructuralFeature#getDiscriminatedUnion <em>Discriminated Union</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.StructuralFeature#getIndexedFeature <em>Indexed Feature</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.StructuralFeature#getKeyRelationship <em>Key Relationship</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.StructuralFeature#getUniqueKey <em>Unique Key</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.core.CorePackage#getStructuralFeature()
 * @model abstract="true"
 * @generated
 */
public interface StructuralFeature extends Feature {
	/**
	 * Returns the value of the '<em><b>Changeability</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.objectmodel.core.ChangeableKind}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies whether the value may be modified after the object is created.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Changeability</em>' attribute.
	 * @see orgomg.cwm.objectmodel.core.ChangeableKind
	 * @see #setChangeability(ChangeableKind)
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getStructuralFeature_Changeability()
	 * @model
	 * @generated
	 */
	ChangeableKind getChangeability();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.core.StructuralFeature#getChangeability <em>Changeability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Changeability</em>' attribute.
	 * @see orgomg.cwm.objectmodel.core.ChangeableKind
	 * @see #getChangeability()
	 * @generated
	 */
	void setChangeability(ChangeableKind value);

	/**
	 * Returns the value of the '<em><b>Multiplicity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The possible number of data values for the feature that may be held by an instance. The cardinality of the set of values is an implicit part of the feature. In the common case in which the multiplicity is 1..1, then the feature is a scalar (i.e., it holds exactly one value).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Multiplicity</em>' containment reference.
	 * @see #setMultiplicity(Multiplicity)
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getStructuralFeature_Multiplicity()
	 * @model containment="true"
	 * @generated
	 */
	Multiplicity getMultiplicity();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.core.StructuralFeature#getMultiplicity <em>Multiplicity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multiplicity</em>' containment reference.
	 * @see #getMultiplicity()
	 * @generated
	 */
	void setMultiplicity(Multiplicity value);

	/**
	 * Returns the value of the '<em><b>Ordering</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.objectmodel.core.OrderingKind}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies whether the set of instances is ordered. The ordering must be determined and maintained by Operations that add values to the feature. This property is only relevant if the multiplicity is greater than one.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ordering</em>' attribute.
	 * @see orgomg.cwm.objectmodel.core.OrderingKind
	 * @see #setOrdering(OrderingKind)
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getStructuralFeature_Ordering()
	 * @model
	 * @generated
	 */
	OrderingKind getOrdering();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.core.StructuralFeature#getOrdering <em>Ordering</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ordering</em>' attribute.
	 * @see orgomg.cwm.objectmodel.core.OrderingKind
	 * @see #getOrdering()
	 * @generated
	 */
	void setOrdering(OrderingKind value);

	/**
	 * Returns the value of the '<em><b>Target Scope</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.objectmodel.core.ScopeKind}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies whether the targets are ordinary Instances or are Classifiers.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target Scope</em>' attribute.
	 * @see orgomg.cwm.objectmodel.core.ScopeKind
	 * @see #setTargetScope(ScopeKind)
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getStructuralFeature_TargetScope()
	 * @model
	 * @generated
	 */
	ScopeKind getTargetScope();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.core.StructuralFeature#getTargetScope <em>Target Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Scope</em>' attribute.
	 * @see orgomg.cwm.objectmodel.core.ScopeKind
	 * @see #getTargetScope()
	 * @generated
	 */
	void setTargetScope(ScopeKind value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Classifier#getStructuralFeature <em>Structural Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Classifier defining the type of a StructuralFeature.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(Classifier)
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getStructuralFeature_Type()
	 * @see orgomg.cwm.objectmodel.core.Classifier#getStructuralFeature
	 * @model opposite="structuralFeature" required="true"
	 * @generated
	 */
	Classifier getType();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.core.StructuralFeature#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(Classifier value);

	/**
	 * Returns the value of the '<em><b>Slot</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.instance.Slot}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.instance.Slot#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the set of Slot instances containing values of the which the StructuralFeature instance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Slot</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getStructuralFeature_Slot()
	 * @see orgomg.cwm.objectmodel.instance.Slot#getFeature
	 * @model opposite="feature"
	 * @generated
	 */
	EList<Slot> getSlot();

	/**
	 * Returns the value of the '<em><b>Discriminated Union</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.datatypes.Union}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.datatypes.Union#getDiscriminator <em>Discriminator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Union instances in which a particular StructuralFeature acts as the discriminator.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Discriminated Union</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getStructuralFeature_DiscriminatedUnion()
	 * @see orgomg.cwm.foundation.datatypes.Union#getDiscriminator
	 * @model opposite="discriminator"
	 * @generated
	 */
	EList<Union> getDiscriminatedUnion();

	/**
	 * Returns the value of the '<em><b>Indexed Feature</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.keysindexes.IndexedFeature}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.keysindexes.IndexedFeature#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the IndexedFeature instances that describe how a particular StructuralFeature is used by the keys of Index instances.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Indexed Feature</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getStructuralFeature_IndexedFeature()
	 * @see orgomg.cwm.foundation.keysindexes.IndexedFeature#getFeature
	 * @model opposite="feature"
	 * @generated
	 */
	EList<IndexedFeature> getIndexedFeature();

	/**
	 * Returns the value of the '<em><b>Key Relationship</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.keysindexes.KeyRelationship}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.keysindexes.KeyRelationship#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the KeyRelationship instances that employ a particular StructuralFeature as part of their key.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Key Relationship</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getStructuralFeature_KeyRelationship()
	 * @see orgomg.cwm.foundation.keysindexes.KeyRelationship#getFeature
	 * @model opposite="feature"
	 * @generated
	 */
	EList<KeyRelationship> getKeyRelationship();

	/**
	 * Returns the value of the '<em><b>Unique Key</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.keysindexes.UniqueKey}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.keysindexes.UniqueKey#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the UniqueKey instances in which a particular StructuralFeature participates.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Unique Key</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getStructuralFeature_UniqueKey()
	 * @see orgomg.cwm.foundation.keysindexes.UniqueKey#getFeature
	 * @model opposite="feature"
	 * @generated
	 */
	EList<UniqueKey> getUniqueKey();

} // StructuralFeature
