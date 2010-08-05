/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.core;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.foundation.keysindexes.Index;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A class is a description of a set of objects that share the same attributes, operations, methods, relationships, and semantics. A class may use a set of interfaces to specify collections of operations it provides to its environment. In the metamodel, a Class describes a set of objects sharing a collection of Features that are common to the set of objects.
 * 
 * The purpose of a Class is to declare a collection of Features that fully describe the structure and behavior of objects. Some Classes may not be directly instantiated. These Classes are said to be abstract and exist only for other Classes to inherit and reuse the Features declared by them. No object may be a direct instance of an abstract Class, although an object may be an indirect instance of one through a subclass that is non-abstract.
 * 
 * A Class acts as the namespace for various kinds of contained elements defined within its scope, including classes, interfaces and associations (note that this is purely a scoping construction and does not imply anything about aggregation). The contained classes can be used as ordinary classes in the container class. If a class inherits another class, the contents of the ancestor are available to its descendents if the visibility of an element is public or protected; however, if the visibility is private, then the element is not visible and therefore not available in the descendant.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.core.Class#getIndex <em>Index</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.core.CorePackage#getClass_()
 * @model
 * @generated
 */
public interface Class extends Classifier {
	/**
	 * Returns the value of the '<em><b>Index</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.keysindexes.Index}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.keysindexes.Index#getSpannedClass <em>Spanned Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies Index instances that span this Class instance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Index</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getClass_Index()
	 * @see orgomg.cwm.foundation.keysindexes.Index#getSpannedClass
	 * @model opposite="spannedClass"
	 * @generated
	 */
	EList<Index> getIndex();

} // Class
