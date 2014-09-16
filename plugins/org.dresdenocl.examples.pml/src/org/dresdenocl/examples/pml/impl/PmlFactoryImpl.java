/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.examples.pml.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.dresdenocl.examples.pml.Extension;
import org.dresdenocl.examples.pml.ExtensionPoint;
import org.dresdenocl.examples.pml.Feature;
import org.dresdenocl.examples.pml.JavaType;
import org.dresdenocl.examples.pml.Operation;
import org.dresdenocl.examples.pml.Parameter;
import org.dresdenocl.examples.pml.Plugin;
import org.dresdenocl.examples.pml.PluginPackage;
import org.dresdenocl.examples.pml.PmlFactory;
import org.dresdenocl.examples.pml.PmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PmlFactoryImpl extends EFactoryImpl implements PmlFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PmlFactory init() {
		try {
			PmlFactory thePmlFactory = (PmlFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.tu-dresden.de/ocl20/pivot/examples/pml"); 
			if (thePmlFactory != null) {
				return thePmlFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PmlFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PmlFactoryImpl() {
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
			case PmlPackage.FEATURE: return createFeature();
			case PmlPackage.PLUGIN: return createPlugin();
			case PmlPackage.EXTENSION_POINT: return createExtensionPoint();
			case PmlPackage.EXTENSION: return createExtension();
			case PmlPackage.JAVA_TYPE: return createJavaType();
			case PmlPackage.OPERATION: return createOperation();
			case PmlPackage.PARAMETER: return createParameter();
			case PmlPackage.PLUGIN_PACKAGE: return createPluginPackage();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Plugin createPlugin() {
		PluginImpl plugin = new PluginImpl();
		return plugin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Feature createFeature() {
		FeatureImpl feature = new FeatureImpl();
		return feature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtensionPoint createExtensionPoint() {
		ExtensionPointImpl extensionPoint = new ExtensionPointImpl();
		return extensionPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Extension createExtension() {
		ExtensionImpl extension = new ExtensionImpl();
		return extension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaType createJavaType() {
		JavaTypeImpl javaType = new JavaTypeImpl();
		return javaType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation createOperation() {
		OperationImpl operation = new OperationImpl();
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parameter createParameter() {
		ParameterImpl parameter = new ParameterImpl();
		return parameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PluginPackage createPluginPackage() {
		PluginPackageImpl pluginPackage = new PluginPackageImpl();
		return pluginPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PmlPackage getPmlPackage() {
		return (PmlPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PmlPackage getPackage() {
		return PmlPackage.eINSTANCE;
	}

} //PmlFactoryImpl
