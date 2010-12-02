/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Subsystem</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A subsystem is a grouping of model elements that represents a behavioral unit in a physical system. A subsystem offers interfaces and has operations.
 * 
 * In the metamodel, Subsystem is a subclass of both Package and Classifier. As such it may have a set of Features.
 * 
 * The purpose of the subsystem construct is to provide a grouping mechanism for specifying a behavioral unit of a physical system. Apart from defining a namespace for its contents, a subsystem serves as a specification unit for the behavior of its contained model elements.
 * 
 * The contents of a subsystem is defined in the same way as for a package, thus it
 * consists of owned elements and imported elements, with unique names within the
 * subsystem.
 * <!-- end-model-doc -->
 *
 *
 * @see orgomg.cwm.objectmodel.core.CorePackage#getSubsystem()
 * @model
 * @generated
 */
public interface Subsystem extends Classifier, orgomg.cwm.objectmodel.core.Package {
} // Subsystem
