/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.softwaredeployment;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.foundation.typemapping.TypeSystem;

import orgomg.cwm.objectmodel.core.Subsystem;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Software System</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A SoftwareSystem represents a specific release of a software product. It consists of a set of software Components.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getType <em>Type</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getSubtype <em>Subtype</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getSupplier <em>Supplier</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getVersion <em>Version</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getDeployment <em>Deployment</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getTypespace <em>Typespace</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getSoftwareSystem()
 * @model
 * @generated
 */
public interface SoftwareSystem extends Subsystem {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the type of the software product. One of the following predefined values should be used if appropriate:
	 * 
	 *     OS, DBMS, MDDB, FileSystem, ODBC, JDBC or Application.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getSoftwareSystem_Type()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Subtype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This is used in conjunction with the type attribute to provide additional information about the type of the software product.
	 * 
	 * For some of the predefined types, suggested subtype values are listed below:
	 * 
	 *    For an Operating System product (type OS):  
	 *       AIX, Linux, MVS, NT, Solaris, SunOS, VMS or Windows.
	 *    For a Database Management System product (type DBMS): 
	 *       DB2, DMS II, IMS, Informix, Oracle, SQLServer or Sybase.
	 *    For a Multidimensional Database product (type MDDB): 
	 *       Essbase or Express.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Subtype</em>' attribute.
	 * @see #setSubtype(String)
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getSoftwareSystem_Subtype()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getSubtype();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getSubtype <em>Subtype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subtype</em>' attribute.
	 * @see #getSubtype()
	 * @generated
	 */
	void setSubtype(String value);

	/**
	 * Returns the value of the '<em><b>Supplier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The supplier of the software product.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Supplier</em>' attribute.
	 * @see #setSupplier(String)
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getSoftwareSystem_Supplier()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getSupplier();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getSupplier <em>Supplier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Supplier</em>' attribute.
	 * @see #getSupplier()
	 * @generated
	 */
	void setSupplier(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The version identification for the software product.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getSoftwareSystem_Version()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>Deployment</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.softwaredeployment.DeployedSoftwareSystem}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.softwaredeployment.DeployedSoftwareSystem#getSoftwareSystem <em>Software System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the deployments of the SoftwareSystem.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Deployment</em>' reference list.
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getSoftwareSystem_Deployment()
	 * @see orgomg.cwm.foundation.softwaredeployment.DeployedSoftwareSystem#getSoftwareSystem
	 * @model opposite="softwareSystem"
	 * @generated
	 */
	EList<DeployedSoftwareSystem> getDeployment();

	/**
	 * Returns the value of the '<em><b>Typespace</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.typemapping.TypeSystem}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.typemapping.TypeSystem#getSupportingSystem <em>Supporting System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies a TypeSystem containing datatypes supported by the SoftwareSystem.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Typespace</em>' reference list.
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getSoftwareSystem_Typespace()
	 * @see orgomg.cwm.foundation.typemapping.TypeSystem#getSupportingSystem
	 * @model opposite="supportingSystem"
	 * @generated
	 */
	EList<TypeSystem> getTypespace();

} // SoftwareSystem
