package tudresden.ocl20.pivot.metamodels.xsd.internal.model;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.ecore.XSDEcoreBuilder;

import tudresden.ocl20.pivot.metamodels.ecore.internal.model.EcoreAdapterFactory;
import tudresden.ocl20.pivot.metamodels.xsd.XSDMetamodelPlugin;
import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.model.base.AbstractModel;
import tudresden.ocl20.pivot.pivotmodel.Namespace;

/**
 * If the root of the model is a single {@link XSDSchema}, a corresponding
 * {@link XSDNamespace} adapter will be created. If there are several root
 * packages, a new "virtual" root namespace will be created.
 * 
 * @author Michael Thiele
 * 
 * @generated
 */
public class XSDModel extends AbstractModel implements IModel {

	/**
	 * a logger for this class
	 * 
	 * @generated
	 */
	private static final Logger logger =
			tudresden.ocl20.pivot.metamodels.xsd.XSDMetamodelPlugin
					.getLogger(XSDModel.class);

	// the resource containing the corresponding XSD model
	private org.eclipse.emf.ecore.resource.Resource resource;

	// the adapter for the top package of the associated XSD model
	private Namespace rootNamespace;

	/**
	 * Creates a new <code>XSDModel</code> adapting the given {@link XSDSchema}.
	 * 
	 * @param resource
	 *          the {@link org.eclipse.emf.ecore.resource.Resource} containing the
	 *          model
	 * 
	 * @generated
	 */
	public XSDModel(org.eclipse.emf.ecore.resource.Resource resource) {

		super(resource.getURI().toString(), ModelBusPlugin.getMetamodelRegistry()
				.getMetamodel(XSDMetamodelPlugin.ID));

		// initialize
		this.resource = resource;
	}

	/**
	 * This method lazily creates a {@link Namespace} adapter for the virtual root
	 * package in the associated XSD model. Thus, any possible resource loading
	 * errors will not happen until this method is called for the first time.
	 * 
	 * @throws ModelAccessException
	 *           if an error occurs when creating the adapter for the top
	 *           namespace
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModel#getRootNamespace()
	 * 
	 * @generated
	 */
	public Namespace getRootNamespace() throws ModelAccessException {

		if (rootNamespace == null) {
			rootNamespace = createRootNamespace();
		}

		return rootNamespace;
	}

	/**
	 * Helper method that creates the adapter for the root namespace. If there is
	 * only one top-level namespace possible, then this method should just return
	 * the adapter for the top-level namespace, else it should create a new
	 * "virtual" root namespace.
	 * 
	 * @return a <code>Namespace</code> instance
	 * 
	 * @throws ModelAccessException
	 *           if an error occurs while loading the adapted XSD model
	 * 
	 * @generated NOT
	 */
	protected Namespace createRootNamespace() throws ModelAccessException {

		/*
		 * Code adapted from
		 * http://wiki.eclipse.org/index.php/Generating_Dynamic_Ecore_from_XML_Schema
		 */
		ResourceSet resourceSet = resource.getResourceSet();

		XSDEcoreBuilder xsdEcoreBuilder = new XSDEcoreBuilder();
		Collection<EObject> ePackages = xsdEcoreBuilder.generate(resource.getURI());

		for (EObject element : ePackages) {
			EPackage ePackage = (EPackage) element;
			resourceSet.getPackageRegistry().put(ePackage.getNsURI(), ePackage);
		}
		/*
		 * End: Code adapted from Eclipse Wiki
		 */

		// create a new package to serve as the root package
		EPackage rootPackage = EcoreFactory.eINSTANCE.createEPackage();
		rootPackage.setName(IModelBusConstants.ROOT_PACKAGE_NAME);

		// add all sub-packages and subtypes to the new root package
		for (Object object : ePackages) {

			rootPackage.getESubpackages().add((EPackage) object);
		}

		return EcoreAdapterFactory.INSTANCE.createNamespace(rootPackage);
	}

	/**
	 * Overridden to base equality check on the URI of the associated resource.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 * @generated
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj instanceof XSDModel) {
			return resource.equals(((XSDModel) obj).resource);
		}

		return false;
	}

	/**
	 * Overridden to base the hash code on the URI of the associated resource.
	 * 
	 * @see java.lang.Object#hashCode()
	 * 
	 * @generated
	 */
	@Override
	public int hashCode() {

		return resource.hashCode();
	}

	/**
	 * Returns a String representation of this <code>XSDModel</code>.
	 * 
	 * @see java.lang.Object#toString()
	 * 
	 * @generated
	 */
	@Override
	public String toString() {

		return resource.toString();
	}
}