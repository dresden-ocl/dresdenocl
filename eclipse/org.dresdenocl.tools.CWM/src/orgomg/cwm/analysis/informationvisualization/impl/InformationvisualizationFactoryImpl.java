/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.informationvisualization.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import orgomg.cwm.analysis.informationvisualization.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class InformationvisualizationFactoryImpl extends EFactoryImpl implements InformationvisualizationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static InformationvisualizationFactory init() {
		try {
			InformationvisualizationFactory theInformationvisualizationFactory = (InformationvisualizationFactory)EPackage.Registry.INSTANCE.getEFactory("http:///orgomg/cwm/analysis/informationvisualization.ecore"); 
			if (theInformationvisualizationFactory != null) {
				return theInformationvisualizationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new InformationvisualizationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InformationvisualizationFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case InformationvisualizationPackage.RENDERED_OBJECT: return createRenderedObject();
			case InformationvisualizationPackage.RENDERED_OBJECT_SET: return createRenderedObjectSet();
			case InformationvisualizationPackage.RENDERING: return createRendering();
			case InformationvisualizationPackage.XSL_RENDERING: return createXSLRendering();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RenderedObject createRenderedObject() {
		RenderedObjectImpl renderedObject = new RenderedObjectImpl();
		return renderedObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RenderedObjectSet createRenderedObjectSet() {
		RenderedObjectSetImpl renderedObjectSet = new RenderedObjectSetImpl();
		return renderedObjectSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Rendering createRendering() {
		RenderingImpl rendering = new RenderingImpl();
		return rendering;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XSLRendering createXSLRendering() {
		XSLRenderingImpl xslRendering = new XSLRenderingImpl();
		return xslRendering;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InformationvisualizationPackage getInformationvisualizationPackage() {
		return (InformationvisualizationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static InformationvisualizationPackage getPackage() {
		return InformationvisualizationPackage.eINSTANCE;
	}

} //InformationvisualizationFactoryImpl
