/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.core;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.analysis.businessnomenclature.VocabularyElement;

import orgomg.cwm.analysis.informationvisualization.RenderedObject;

import orgomg.cwm.analysis.transformation.DataObjectSet;

import orgomg.cwm.foundation.businessinformation.Description;
import orgomg.cwm.foundation.businessinformation.Document;
import orgomg.cwm.foundation.businessinformation.ResponsibleParty;

import orgomg.cwm.foundation.expressions.ElementNode;

import orgomg.cwm.management.warehouseoperation.ChangeRequest;
import orgomg.cwm.management.warehouseoperation.Measurement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A model element is an element that is an abstraction drawn from the system being modeled.
 * 
 * In the metamodel, a ModelElement is a named entity in a Model. It is the base for all modeling metaclasses in the CWM. All other modeling metaclasses are either direct or indirect subclasses of ModelElement.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.core.ModelElement#getName <em>Name</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.ModelElement#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.ModelElement#getClientDependency <em>Client Dependency</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.ModelElement#getSupplierDependency <em>Supplier Dependency</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.ModelElement#getConstraint <em>Constraint</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.ModelElement#getNamespace <em>Namespace</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.ModelElement#getImporter <em>Importer</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.ModelElement#getStereotype <em>Stereotype</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.ModelElement#getTaggedValue <em>Tagged Value</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.ModelElement#getDocument <em>Document</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.ModelElement#getDescription <em>Description</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.ModelElement#getResponsibleParty <em>Responsible Party</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.ModelElement#getElementNode <em>Element Node</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.ModelElement#getSet <em>Set</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.ModelElement#getRenderedObject <em>Rendered Object</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.ModelElement#getVocabularyElement <em>Vocabulary Element</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.ModelElement#getMeasurement <em>Measurement</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.ModelElement#getChangeRequest <em>Change Request</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.core.CorePackage#getModelElement()
 * @model abstract="true"
 * @generated
 */
public interface ModelElement extends Element {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An identifier for the ModelElement within its containing Namespace.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getModelElement_Name()
	 * @model dataType="orgomg.cwm.objectmodel.core.Name"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.core.ModelElement#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Visibility</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.objectmodel.core.VisibilityKind}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies extent of the visibility of the ModelElement within its owning Namespace.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Visibility</em>' attribute.
	 * @see orgomg.cwm.objectmodel.core.VisibilityKind
	 * @see #setVisibility(VisibilityKind)
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getModelElement_Visibility()
	 * @model
	 * @generated
	 */
	VisibilityKind getVisibility();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.core.ModelElement#getVisibility <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visibility</em>' attribute.
	 * @see orgomg.cwm.objectmodel.core.VisibilityKind
	 * @see #getVisibility()
	 * @generated
	 */
	void setVisibility(VisibilityKind value);

	/**
	 * Returns the value of the '<em><b>Client Dependency</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.Dependency}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Dependency#getClient <em>Client</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies Dependency instances in which the ModelElement acts as a client.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Client Dependency</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getModelElement_ClientDependency()
	 * @see orgomg.cwm.objectmodel.core.Dependency#getClient
	 * @model opposite="client"
	 * @generated
	 */
	EList<Dependency> getClientDependency();

	/**
	 * Returns the value of the '<em><b>Supplier Dependency</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.Dependency}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Dependency#getSupplier <em>Supplier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The DependencySupplier association links Dependency instances with ModelElements that act as suppliers in the represented dependency relationship.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Supplier Dependency</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getModelElement_SupplierDependency()
	 * @see orgomg.cwm.objectmodel.core.Dependency#getSupplier
	 * @model opposite="supplier"
	 * @generated
	 */
	EList<Dependency> getSupplierDependency();

	/**
	 * Returns the value of the '<em><b>Constraint</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.Constraint}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Constraint#getConstrainedElement <em>Constrained Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Constraint instances that restrict the possible states that a ModelElement may take.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Constraint</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getModelElement_Constraint()
	 * @see orgomg.cwm.objectmodel.core.Constraint#getConstrainedElement
	 * @model opposite="constrainedElement"
	 * @generated
	 */
	EList<Constraint> getConstraint();

	/**
	 * Returns the value of the '<em><b>Namespace</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Namespace#getOwnedElement <em>Owned Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Namespace, if any, that owns the ModelElement.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Namespace</em>' container reference.
	 * @see #setNamespace(Namespace)
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getModelElement_Namespace()
	 * @see orgomg.cwm.objectmodel.core.Namespace#getOwnedElement
	 * @model opposite="ownedElement"
	 * @generated
	 */
	Namespace getNamespace();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.core.ModelElement#getNamespace <em>Namespace</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Namespace</em>' container reference.
	 * @see #getNamespace()
	 * @generated
	 */
	void setNamespace(Namespace value);

	/**
	 * Returns the value of the '<em><b>Importer</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.Package}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Package#getImportedElement <em>Imported Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Packages that import a ModelElement.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Importer</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getModelElement_Importer()
	 * @see orgomg.cwm.objectmodel.core.Package#getImportedElement
	 * @model opposite="importedElement"
	 * @generated
	 */
	EList<orgomg.cwm.objectmodel.core.Package> getImporter();

	/**
	 * Returns the value of the '<em><b>Stereotype</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Stereotype#getExtendedElement <em>Extended Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Stereotype instance that further defines the semantics of the ModelElement.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Stereotype</em>' reference.
	 * @see #setStereotype(Stereotype)
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getModelElement_Stereotype()
	 * @see orgomg.cwm.objectmodel.core.Stereotype#getExtendedElement
	 * @model opposite="extendedElement"
	 * @generated
	 */
	Stereotype getStereotype();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.core.ModelElement#getStereotype <em>Stereotype</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stereotype</em>' reference.
	 * @see #getStereotype()
	 * @generated
	 */
	void setStereotype(Stereotype value);

	/**
	 * Returns the value of the '<em><b>Tagged Value</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.TaggedValue}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.TaggedValue#getModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the set of TaggedValue instances that extend a ModelElement.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Tagged Value</em>' containment reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getModelElement_TaggedValue()
	 * @see orgomg.cwm.objectmodel.core.TaggedValue#getModelElement
	 * @model opposite="modelElement" containment="true"
	 * @generated
	 */
	EList<TaggedValue> getTaggedValue();

	/**
	 * Returns the value of the '<em><b>Document</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.businessinformation.Document}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.businessinformation.Document#getModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Document instances relevant to a particular ModelElement.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Document</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getModelElement_Document()
	 * @see orgomg.cwm.foundation.businessinformation.Document#getModelElement
	 * @model opposite="modelElement"
	 * @generated
	 */
	EList<Document> getDocument();

	/**
	 * Returns the value of the '<em><b>Description</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.businessinformation.Description}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.businessinformation.Description#getModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Description instances relevant for a particular ModelElement instance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Description</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getModelElement_Description()
	 * @see orgomg.cwm.foundation.businessinformation.Description#getModelElement
	 * @model opposite="modelElement"
	 * @generated
	 */
	EList<Description> getDescription();

	/**
	 * Returns the value of the '<em><b>Responsible Party</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.businessinformation.ResponsibleParty}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.businessinformation.ResponsibleParty#getModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the ResponsibleParty instances relevant for a particular ModelElement instance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Responsible Party</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getModelElement_ResponsibleParty()
	 * @see orgomg.cwm.foundation.businessinformation.ResponsibleParty#getModelElement
	 * @model opposite="modelElement"
	 * @generated
	 */
	EList<ResponsibleParty> getResponsibleParty();

	/**
	 * Returns the value of the '<em><b>Element Node</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.expressions.ElementNode}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.expressions.ElementNode#getModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the ElementNode instances that represent a particular ModelElement in expressions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Element Node</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getModelElement_ElementNode()
	 * @see orgomg.cwm.foundation.expressions.ElementNode#getModelElement
	 * @model opposite="modelElement"
	 * @generated
	 */
	EList<ElementNode> getElementNode();

	/**
	 * Returns the value of the '<em><b>Set</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.transformation.DataObjectSet}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.transformation.DataObjectSet#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the DataObjectSet
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Set</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getModelElement_Set()
	 * @see orgomg.cwm.analysis.transformation.DataObjectSet#getElement
	 * @model opposite="element"
	 * @generated
	 */
	EList<DataObjectSet> getSet();

	/**
	 * Returns the value of the '<em><b>Rendered Object</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.informationvisualization.RenderedObject}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Rendered Objects referencing the Model Element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rendered Object</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getModelElement_RenderedObject()
	 * @see orgomg.cwm.analysis.informationvisualization.RenderedObject#getModelElement
	 * @model opposite="modelElement"
	 * @generated
	 */
	EList<RenderedObject> getRenderedObject();

	/**
	 * Returns the value of the '<em><b>Vocabulary Element</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.businessnomenclature.VocabularyElement}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.businessnomenclature.VocabularyElement#getModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies a VocabularyElement.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Vocabulary Element</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getModelElement_VocabularyElement()
	 * @see orgomg.cwm.analysis.businessnomenclature.VocabularyElement#getModelElement
	 * @model opposite="modelElement"
	 * @generated
	 */
	EList<VocabularyElement> getVocabularyElement();

	/**
	 * Returns the value of the '<em><b>Measurement</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.management.warehouseoperation.Measurement}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.management.warehouseoperation.Measurement#getModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies a Measurement for a ModelElement.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Measurement</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getModelElement_Measurement()
	 * @see orgomg.cwm.management.warehouseoperation.Measurement#getModelElement
	 * @model opposite="modelElement"
	 * @generated
	 */
	EList<Measurement> getMeasurement();

	/**
	 * Returns the value of the '<em><b>Change Request</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.management.warehouseoperation.ChangeRequest}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.management.warehouseoperation.ChangeRequest#getModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies a ChangeRequest for a ModelElement.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Change Request</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getModelElement_ChangeRequest()
	 * @see orgomg.cwm.management.warehouseoperation.ChangeRequest#getModelElement
	 * @model opposite="modelElement"
	 * @generated
	 */
	EList<ChangeRequest> getChangeRequest();

} // ModelElement
