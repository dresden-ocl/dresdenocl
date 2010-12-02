/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dependency</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A dependency states that the implementation or functioning of one or more elements requires the presence of one or more other elements.
 * 
 * In the metamodel, a Dependency is a directed relationship from a client (or clients) to a supplier (or suppliers) stating that the client is dependent on the supplier (i.e., the client element requires the presence and knowledge of the supplier element).
 * 
 * A dependency specifies that the semantics of a set of model elements requires the presence of another set of model elements. This implies that if the source is somehow modified, the dependents probably must be modified. The reason for the dependency can be specified in several different ways (e.g., using natural language or an algorithm) but is often implicit.
 * 
 * Whenever the supplier element of a dependency changes, the client element is potentially invalidated. After such invalidation, a check should be performed followed by possible changes to the derived client element. Such a check should be performed after which action can be taken to change the derived element to validate it again.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.core.Dependency#getKind <em>Kind</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Dependency#getClient <em>Client</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Dependency#getSupplier <em>Supplier</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.core.CorePackage#getDependency()
 * @model
 * @generated
 */
public interface Dependency extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Contains a description of the nature of the dependency relationship between the client and supplier. The list of possible values is open-ended. However, CWM predefines the values "Abstraction" and "Usage".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see #setKind(String)
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getDependency_Kind()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getKind();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.core.Dependency#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see #getKind()
	 * @generated
	 */
	void setKind(String value);

	/**
	 * Returns the value of the '<em><b>Client</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.ModelElement}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.ModelElement#getClientDependency <em>Client Dependency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the ModelElements that are clients of the Dependency instance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Client</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getDependency_Client()
	 * @see orgomg.cwm.objectmodel.core.ModelElement#getClientDependency
	 * @model opposite="clientDependency" required="true"
	 * @generated
	 */
	EList<ModelElement> getClient();

	/**
	 * Returns the value of the '<em><b>Supplier</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.ModelElement}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.ModelElement#getSupplierDependency <em>Supplier Dependency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the ModelElements that are suppliers of the Dependency instance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Supplier</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getDependency_Supplier()
	 * @see orgomg.cwm.objectmodel.core.ModelElement#getSupplierDependency
	 * @model opposite="supplierDependency" required="true"
	 * @generated
	 */
	EList<ModelElement> getSupplier();

} // Dependency
