/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.informationvisualization;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.foundation.expressions.ExpressionNode;

import orgomg.cwm.objectmodel.core.Feature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rendering</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Rendering is a specification of how an associated RenderedObject is to be "rendered" in some medium.  This usually consists of a projection of an object of arbitrary dimensionality onto a 2-dimensional surface, but it may also include non-physical representations as well (such as audio).
 * 
 * A Rendering is semantically equivalent to a Transformation, in that it transforms a source RenderedObject to some target "presented" object.  An instance of Rendering is fully specified via its formula attribute, which contains an implementation-dependent expression that defines the transformation and tracks transformation lineage.
 * 
 * Possible types of instances of Rendering:
 * Screen, Paper, Voice, Web, HTML Document, XML/XSL, languages based on extensions to XML, SVG, Visual objects, responses to keying (keying interception plus rules), etc.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.Rendering#getFormula <em>Formula</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.Rendering#getAction <em>Action</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.Rendering#getFileName <em>File Name</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.Rendering#getType <em>Type</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.Rendering#getUrl <em>Url</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.Rendering#getDefaultedRenderedObject <em>Defaulted Rendered Object</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.Rendering#getRenderedObjectSet <em>Rendered Object Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage#getRendering()
 * @model
 * @generated
 */
public interface Rendering extends Feature {
	/**
	 * Returns the value of the '<em><b>Formula</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Implementation-dependent procedure for generating the Rendering (e.g., a usage of XSL to generate an HTML document).  Tracks the transformation lineage of the Rendering.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Formula</em>' containment reference.
	 * @see #setFormula(ExpressionNode)
	 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage#getRendering_Formula()
	 * @model containment="true"
	 * @generated
	 */
	ExpressionNode getFormula();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.informationvisualization.Rendering#getFormula <em>Formula</em>}' containment reference.
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
	 * Specifies some implementation-dependent action associated with a Rendering.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Action</em>' attribute.
	 * @see #setAction(String)
	 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage#getRendering_Action()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getAction();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.informationvisualization.Rendering#getAction <em>Action</em>}' attribute.
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
	 * Specifies the name of a file persisting an instance of Rendering.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>File Name</em>' attribute.
	 * @see #setFileName(String)
	 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage#getRendering_FileName()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getFileName();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.informationvisualization.Rendering#getFileName <em>File Name</em>}' attribute.
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
	 * Specifies some implementation-dependent type associated with a Rendering.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage#getRendering_Type()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.informationvisualization.Rendering#getType <em>Type</em>}' attribute.
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
	 * Specifies a URL identifying some instance of Rendering.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Url</em>' attribute.
	 * @see #setUrl(String)
	 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage#getRendering_Url()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getUrl();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.informationvisualization.Rendering#getUrl <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Url</em>' attribute.
	 * @see #getUrl()
	 * @generated
	 */
	void setUrl(String value);

	/**
	 * Returns the value of the '<em><b>Defaulted Rendered Object</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.informationvisualization.RenderedObject}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getDefaultRendering <em>Default Rendering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * RenderedObjects referencing this Rendering as the default Rendering.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Defaulted Rendered Object</em>' reference list.
	 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage#getRendering_DefaultedRenderedObject()
	 * @see orgomg.cwm.analysis.informationvisualization.RenderedObject#getDefaultRendering
	 * @model opposite="defaultRendering"
	 * @generated
	 */
	EList<RenderedObject> getDefaultedRenderedObject();

	/**
	 * Returns the value of the '<em><b>Rendered Object Set</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.informationvisualization.RenderedObjectSet#getRendering <em>Rendering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * RenderedObjectSet owning Renderings.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rendered Object Set</em>' container reference.
	 * @see #setRenderedObjectSet(RenderedObjectSet)
	 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage#getRendering_RenderedObjectSet()
	 * @see orgomg.cwm.analysis.informationvisualization.RenderedObjectSet#getRendering
	 * @model opposite="rendering" required="true"
	 * @generated
	 */
	RenderedObjectSet getRenderedObjectSet();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.informationvisualization.Rendering#getRenderedObjectSet <em>Rendered Object Set</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rendered Object Set</em>' container reference.
	 * @see #getRenderedObjectSet()
	 * @generated
	 */
	void setRenderedObjectSet(RenderedObjectSet value);

} // Rendering
