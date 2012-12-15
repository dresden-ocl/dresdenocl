/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.relationships;

import orgomg.cwm.objectmodel.core.StructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Association End</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An association end is an endpoint of an association, which connects the association to a classifier. Each association end is part of one association. The association ends of each association are ordered.
 * 
 * In the metamodel, an AssociationEnd is part of an Association and specifies the connection of an Association to some other Classifier. Because AssociationEnds are a kind of StructuralFeature, they are owned and ordered by Association instances via the ClassifierFeature association. The StructuralFeatureType association is used to identify the Classifier to which the AssociationEnd is attached. Each AssociationEnd has a name and defines a set of properties of the connection.
 * 
 * The multiplicity property of an association end specifies how many instances of the classifier at a given end (the one bearing the multiplicity value) may be associated with a single instance of the classifier at the other end. The association end also states whether or not the connection may be traversed towards the instance playing that role in the connection (the isNavigable attribute), that is, if the instance is directly reachable via the association.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.relationships.AssociationEnd#getAggregation <em>Aggregation</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.relationships.AssociationEnd#isIsNavigable <em>Is Navigable</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.relationships.RelationshipsPackage#getAssociationEnd()
 * @model
 * @generated
 */
public interface AssociationEnd extends StructuralFeature {
	/**
	 * Returns the value of the '<em><b>Aggregation</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.objectmodel.relationships.AggregationKind}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When placed on one end (the "target" end), specifies whether the class on the target end is an aggregation with respect to the class on the other end (the "source" end). Only one end of an association can be an aggregation.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Aggregation</em>' attribute.
	 * @see orgomg.cwm.objectmodel.relationships.AggregationKind
	 * @see #setAggregation(AggregationKind)
	 * @see orgomg.cwm.objectmodel.relationships.RelationshipsPackage#getAssociationEnd_Aggregation()
	 * @model
	 * @generated
	 */
	AggregationKind getAggregation();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.relationships.AssociationEnd#getAggregation <em>Aggregation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Aggregation</em>' attribute.
	 * @see orgomg.cwm.objectmodel.relationships.AggregationKind
	 * @see #getAggregation()
	 * @generated
	 */
	void setAggregation(AggregationKind value);

	/**
	 * Returns the value of the '<em><b>Is Navigable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When placed on a target end, specifies whether traversal from a source instance to its associated target instances is possible. A value of true means that the association can be navigated by the source class and the target rolename can be used in navigation expressions. Specification of navigability for each direction is defined independently.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Navigable</em>' attribute.
	 * @see #setIsNavigable(boolean)
	 * @see orgomg.cwm.objectmodel.relationships.RelationshipsPackage#getAssociationEnd_IsNavigable()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsNavigable();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.relationships.AssociationEnd#isIsNavigable <em>Is Navigable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Navigable</em>' attribute.
	 * @see #isIsNavigable()
	 * @generated
	 */
	void setIsNavigable(boolean value);

} // AssociationEnd
