/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A model captures a view of a physical system. It is an abstraction of the physical system, with a certain purpose. The model completely describes those aspects of the physical system that are relevant to the purpose of the model, at the appropriate level of detail.
 * 
 * In the metamodel, Model is a subclass of Package. It contains a containment hierarchy of ModelElements that together describe the physical system. A Model also contains a set of ModelElements that represents the environment of the system. 
 * 
 * Different Models can be defined for the same physical system, where each model represents a view of the physical system defined by its purpose and abstraction level, e.g. an analysis model, a design model, an implementation model. Typically different models are complementary and defined from the perspectives (viewpoints) of different system stakeholders.
 * <!-- end-model-doc -->
 *
 *
 * @see orgomg.cwm.objectmodel.core.CorePackage#getModel()
 * @model
 * @generated
 */
public interface Model extends orgomg.cwm.objectmodel.core.Package {
} // Model
