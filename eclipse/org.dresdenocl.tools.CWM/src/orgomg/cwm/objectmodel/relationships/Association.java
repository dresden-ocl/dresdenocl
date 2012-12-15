/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.relationships;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An association defines a semantic relationship between classifiers. Associations have two or more named ends. Associations with two or more ends are called "n-ary" whereas associations with exactly two ends are called "binary". Each end, depending
 * upon its multiplicity, connects to zero or more instances of some classifier.
 * 
 * In the metamodel, an Association is a declaration of a semantic relationship between Classifiers, such as Classes. Associations must have two, and may have more, association ends. Each end is connected to a Classifier; the same Classifier may be 
 * connected to more than one association end in the same association. (Refer to the ObjectModel’s Instance package, below, for a description of how Associations are instantiated.)
 * 
 * Because Associations are classifiers, they own and order their association ends (which are Attributes) via the ClassifierFeature association. In addition, because Associations are Classes, they can also own more traditional StructuralFeatures such as Attributes. Consequently, they may act in a manner similar to "association classes" described by
 * some other object models.
 * 
 * An association may represent an aggregation (i.e., a whole/part relationship). In this case, the association end attached to the whole element is designated, and the other association end represents the parts of the aggregation.
 * 
 * Associations can be of three different kinds: (1) ordinary association, (2) composite aggregate, and (3) shareable aggregate. Since the aggregate construct can have several different meanings depending on the application area, CWM gives a more precise meaning to two of these constructs (i.e., association and composite aggregate) and leaves the shareable aggregate more loosely defined in between. Only binary Associations can have composite or sharable aggregation semantics.
 * 
 * Composite aggregation is a strong form of aggregation which requires that a part instance be included in at most one composite at a time and that the composite object has sole responsibility for the disposition of its parts. This means that the composite object is responsible for the creation and destruction of the parts. In implementation terms, it is responsible for their memory allocation. If a composite object is destroyed, it must destroy all of its parts. It may remove a part and give it to another composite object, which then assumes responsibility for it. If the multiplicity from a part to composite is zero-to-one, the composite may remove the part and the part may assume responsibility for itself, otherwise it may not live apart from a  composite.
 * 
 * A consequence of these rules is that a composite aggregation implies propagation semantics (i.e., some of the dynamic semantics of the whole is propagated to its parts). For example, if the whole is copied or destroyed, then so are the parts as well (because a part may belong to at most one composite).
 * 
 * A classifier on the composite end of an association may have parts that are classifiers and associations. At the instance level, an instance of a part element is considered "part of" the instance of a composite element. If an association is part of a composite and it connects two classes that are also part of the same composite, then an instance of the association will connect objects that are part of the same composite object of which the instance is part.
 * 
 * A shareable aggregation denotes weak ownership (i.e., the part may be included in several aggregates) and its owner may also change over time. However, the semantics of a shareable aggregation does not imply deletion of the parts when an aggregate
 * referencing it is deleted. Both kinds of aggregations define a transitive, antisymmetric relationship (i.e., the instances form a directed, non-cyclic graph). Composition instances form a strict tree (or rather a forest).
 * <!-- end-model-doc -->
 *
 *
 * @see orgomg.cwm.objectmodel.relationships.RelationshipsPackage#getAssociation()
 * @model
 * @generated
 */
public interface Association extends orgomg.cwm.objectmodel.core.Class {
} // Association
