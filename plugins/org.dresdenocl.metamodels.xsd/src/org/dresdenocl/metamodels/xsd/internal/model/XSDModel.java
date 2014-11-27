package org.dresdenocl.metamodels.xsd.internal.model;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.ecore.XSDEcoreBuilder;

import org.dresdenocl.metamodels.ecore.internal.model.EcoreAdapterFactory;
import org.dresdenocl.model.IModel;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.model.ModelConstants;
import org.dresdenocl.model.base.AbstractModel;
import org.dresdenocl.model.metamodel.IMetamodel;
import org.dresdenocl.pivotmodel.Namespace;

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

	// the resource containing the corresponding XSD model
	private org.eclipse.emf.ecore.resource.Resource resource;

	// the adapter for the top package of the associated XSD model
	private Namespace rootNamespace;
	
	
	/**
	 * <p>
	 * The {@link EcoreAdapterFactory} used to create nested elements.
	 * </p>
	 */
	private EcoreAdapterFactory factory = new EcoreAdapterFactory();

	/**
	 * Creates a new <code>XSDModel</code> adapting the given {@link XSDSchema}.
	 * 
	 * @param resource
	 *          the {@link org.eclipse.emf.ecore.resource.Resource} containing the
	 *          model
	 * 
	 * @generated
	 */
	public XSDModel(org.eclipse.emf.ecore.resource.Resource resource,
			IMetamodel metamodel) {

		super(resource.getURI().toString(), metamodel);

		// initialize
		this.resource = resource;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.model.IModel#dispose()
	 */
	public void dispose() {

		/* Unload the resource to remove external contents. */
		this.resource.unload();
		/* Reset the root name space to avoid caching. */
		this.rootNamespace = null;
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
	 * @see org.dresdenocl.model.IModel#getRootNamespace()
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
		 * Code adapted fromhttp://wiki.eclipse.org/index.php/
		 * Generating_Dynamic_Ecore_from_XML_Schema
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
		rootPackage.setName(ModelConstants.ROOT_PACKAGE_NAME);

		// add all sub-packages and subtypes to the new root package
		for (Object object : ePackages) {

			rootPackage.getESubpackages().add((EPackage) object);
		}

		return factory.createNamespace(rootPackage);
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