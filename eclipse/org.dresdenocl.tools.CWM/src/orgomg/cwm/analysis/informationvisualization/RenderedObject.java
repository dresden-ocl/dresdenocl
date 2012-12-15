/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.informationvisualization;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.foundation.expressions.ExpressionNode;

import orgomg.cwm.objectmodel.core.Classifier;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rendered Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * RenderedObject serves as a logical "proxy" for an arbitrary ModelElement that is to be
 * rendered.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getFormula <em>Formula</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getAction <em>Action</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getFileName <em>File Name</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getType <em>Type</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getUrl <em>Url</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getComposite <em>Composite</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getComponent <em>Component</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getDefaultRendering <em>Default Rendering</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getModelElement <em>Model Element</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getNeighbor <em>Neighbor</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getReferencingNeighbor <em>Referencing Neighbor</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage#getRenderedObject()
 * @model
 * @generated
 */
public interface RenderedObject extends Classifier {
	/**
	 * Returns the value of the '<em><b>Formula</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Allows for the specification of any implementation-dependent expression that completes the definition of a RenderedObject.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Formula</em>' containment reference.
	 * @see #setFormula(ExpressionNode)
	 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage#getRenderedObject_Formula()
	 * @model containment="true"
	 * @generated
	 */
	ExpressionNode getFormula();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getFormula <em>Formula</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Formula</em>' containment reference.
	 * @see #getFormula()
	 * @generated
	 */
	void setFormula(ExpressionNode value);

	/**
	 * Returns the value of the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies some implementation-dependent action associated with a RenderedObject.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Action</em>' attribute.
	 * @see #setAction(String)
	 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage#getRenderedObject_Action()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getAction();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getAction <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' attribute.
	 * @see #getAction()
	 * @generated
	 */
	void setAction(String value);

	/**
	 * Returns the value of the '<em><b>File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the name of a file persisting an instance of RenderedObject.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>File Name</em>' attribute.
	 * @see #setFileName(String)
	 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage#getRenderedObject_FileName()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getFileName();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getFileName <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Name</em>' attribute.
	 * @see #getFileName()
	 * @generated
	 */
	void setFileName(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies some implementation-dependent type associated with a RenderedObject
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage#getRenderedObject_Type()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies a URL identifying some instance of RenderedObject.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Url</em>' attribute.
	 * @see #setUrl(String)
	 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage#getRenderedObject_Url()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getUrl();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getUrl <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Url</em>' attribute.
	 * @see #getUrl()
	 * @generated
	 */
	void setUrl(String value);

	/**
	 * Returns the value of the '<em><b>Composite</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.informationvisualization.RenderedObject}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * "Composite" RenderedObjects referencing "component" RenderedObjects.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Composite</em>' reference list.
	 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage#getRenderedObject_Composite()
	 * @see orgomg.cwm.analysis.informationvisualization.RenderedObject#getComponent
	 * @model opposite="component"
	 * @generated
	 */
	EList<RenderedObject> getComposite();

	/**
	 * Returns the value of the '<em><b>Component</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.informationvisualization.RenderedObject}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getComposite <em>Composite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * "Component" RenderedObjects referenced by "composite" RenderedObjects.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Component</em>' reference list.
	 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage#getRenderedObject_Component()
	 * @see orgomg.cwm.analysis.informationvisualization.RenderedObject#getComposite
	 * @model opposite="composite"
	 * @generated
	 */
	EList<RenderedObject> getComponent();

	/**
	 * Returns the value of the '<em><b>Default Rendering</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.informationvisualization.Rendering#getDefaultedRenderedObject <em>Defaulted Rendered Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Rendering referenced by one or more RenderedObjects as the default Rendering.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default Rendering</em>' reference.
	 * @see #setDefaultRendering(Rendering)
	 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage#getRenderedObject_DefaultRendering()
	 * @see orgomg.cwm.analysis.informationvisualization.Rendering#getDefaultedRenderedObject
	 * @model opposite="defaultedRenderedObject"
	 * @generated
	 */
	Rendering getDefaultRendering();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getDefaultRendering <em>Default Rendering</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Rendering</em>' reference.
	 * @see #getDefaultRendering()
	 * @generated
	 */
	void setDefaultRendering(Rendering value);

	/**
	 * Returns the value of the '<em><b>Model Element</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.ModelElement#getRenderedObject <em>Rendered Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Model Element referenced by Rendered Objects.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Model Element</em>' reference.
	 * @see #setModelElement(ModelElement)
	 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage#getRenderedObject_ModelElement()
	 * @see orgomg.cwm.objectmodel.core.ModelElement#getRenderedObject
	 * @model opposite="renderedObject"
	 * @generated
	 */
	ModelElement getModelElement();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getModelElement <em>Model Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Element</em>' reference.
	 * @see #getModelElement()
	 * @generated
	 */
	void setModelElement(ModelElement value);

	/**
	 * Returns the value of the '<em><b>Neighbor</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.informationvisualization.RenderedObject}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getReferencingNeighbor <em>Referencing Neighbor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * RenderedObjects referenced by this RenderedObject as its "neighbor" (or neighboring object).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Neighbor</em>' reference list.
	 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage#getRenderedObject_Neighbor()
	 * @see orgomg.cwm.analysis.informationvisualization.RenderedObject#getReferencingNeighbor
	 * @model opposite="referencingNeighbor"
	 * @generated
	 */
	EList<RenderedObject> getNeighbor();

	/**
	 * Returns the value of the '<em><b>Referencing Neighbor</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.informationvisualization.RenderedObject}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getNeighbor <em>Neighbor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * RenderedObjects referencing this RenderedObject as its "neighbor".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Referencing Neighbor</em>' reference list.
	 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage#getRenderedObject_ReferencingNeighbor()
	 * @see orgomg.cwm.analysis.informationvisualization.RenderedObject#getNeighbor
	 * @model opposite="neighbor"
	 * @generated
	 */
	EList<RenderedObject> getReferencingNeighbor();

} // RenderedObject
