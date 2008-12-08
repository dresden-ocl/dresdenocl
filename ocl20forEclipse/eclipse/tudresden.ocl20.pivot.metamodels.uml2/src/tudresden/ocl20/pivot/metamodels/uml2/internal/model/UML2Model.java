package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;

import tudresden.ocl20.pivot.metamodels.uml2.UML2MetamodelPlugin;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.base.AbstractModel;
import tudresden.ocl20.pivot.models.uml2.internal.provider.Uml2ModelInstanceProvider;
import tudresden.ocl20.pivot.pivotmodel.Namespace;

/**
 * If the root of the model is a single {@link org.eclipse.uml2.uml.Package}, a
 * corresponding {@link UML2Namespace} adapter will be created. If there are
 * several root packages, a new "virtual" root namespace will be created.
 * 
 * @generated NOT
 */
public class UML2Model extends AbstractModel implements IModel {

	// a logger for this class
	private static final Logger logger = Logger.getLogger(UML2Model.class);

	// the resource containing the corresponding UML2 model
	private Resource resource;

	// the adapter for the top package of the associated UML2 model
	private Namespace rootNamespace;

	/**
	 * Creates a new <code>UML2Model</code> adapting the given
	 * {@link org.eclipse.uml2.uml.Package}.
	 * 
	 * @param resource
	 *          the resource containing the model
	 * 
	 * @generated NOT
	 */
	public UML2Model(Resource resource) {
		super(resource.getURI().toString(), ModelBusPlugin.getMetamodelRegistry()
				.getMetamodel(UML2MetamodelPlugin.ID));

		// initialize
		this.resource = resource;
	}

	/**
	 * @see tudresden.ocl20.pivot.modelbus.IModel#getModelInstanceProvider()
	 * 
	 * @generated NOT
	 */
	public IModelInstanceProvider getModelInstanceProvider() {
		return new Uml2ModelInstanceProvider(this);
	}

	/**
	 * This method lazily creates a {@link Namespace} adapter for the virtual root
	 * package in the associated UML2 model. Thus, any possible resource loading
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
	 *           if an error occurs while loading the adapted UML2 model
	 * 
	 * @generated NOT
	 */
	protected Namespace createRootNamespace() throws ModelAccessException {

		// load the resource
		if (!resource.isLoaded()) {

			if (logger.isInfoEnabled()) {
				logger.info(NLS.bind(UML2ModelMessages.UML2Model_LoadingUML2Model,
						resource.getURI()));
			}

			try {
				resource.load(null);
			} catch (IOException e) {
				throw new ModelAccessException(
						"Error while loading resource from " + resource.getURI(), e); //$NON-NLS-1$
			}

		}

		// get the root packages
		List<EObject> roots = resource.getContents();

		// create a new package to serve as the root package
		org.eclipse.uml2.uml.Package rootPackage = UMLFactory.eINSTANCE
				.createPackage();
		rootPackage.setName("root"); //$NON-NLS-1$

		// add all sub-packages and subtypes to the new root package
		for (EObject eObject : roots) {
			if (eObject instanceof Package)
				rootPackage.getNestedPackages().add((Package) eObject);
			if (eObject instanceof Type)
				rootPackage.getOwnedMembers().add((Type) eObject);
		}

		convertAssociations(rootPackage);

		return UML2AdapterFactory.INSTANCE.createNamespace(rootPackage);

	}

	/**
	 * Processes all UML Associations: since they are treated as Types in the UML
	 * meta model, they have to be mapped to Properties in the Pivot Model.
	 * 
	 * Precondition: binary associations
	 * 
	 * @param rootPackage
	 *          the containing package (namespace)
	 */
	protected void convertAssociations(Package rootPackage) {

		// associations in all nested packages have to converted
		for (Package aPackage : rootPackage.getNestedPackages()) {
			convertAssociations(aPackage);
		}
		for (Type type : rootPackage.getOwnedTypes()) {
			if (type instanceof Association) {
				Association association = (Association) type;

				Property property1 = association.getOwnedEnds().get(0);
				Property property2 = association.getOwnedEnds().get(1);
				// search for navigable elements -> opposite is owner
				if (property1.isNavigable()) {
					UML2AdapterFactory.INSTANCE.createType(property2.getType())
							.addProperty(
									UML2AdapterFactory.INSTANCE.createProperty(property1));
				}
				if (property2.isNavigable()) {
					UML2AdapterFactory.INSTANCE.createType(property1.getType())
							.addProperty(
									UML2AdapterFactory.INSTANCE.createProperty(property2));
				}
				// all other associations are not specified for navigation ->
				// bidirectional
				if (!(property1.isNavigable()) && !(property2.isNavigable())) {
					UML2AdapterFactory.INSTANCE.createType(property2.getType())
							.addProperty(
									UML2AdapterFactory.INSTANCE.createProperty(property1));
					UML2AdapterFactory.INSTANCE.createType(property1.getType())
							.addProperty(
									UML2AdapterFactory.INSTANCE.createProperty(property2));
				}
			}
		}
	}

	/**
	 * Overridden to base equality check on the URI of the associated resource.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj instanceof UML2Model) {
			return resource.getURI().equals(((UML2Model) obj).resource.getURI());
		}

		return false;
	}

	/**
	 * Overridden to base the hash code on the URI of the associated resource.
	 * 
	 * @see java.lang.Object#hashCode()
	 * 
	 * @generated NOT
	 */
	@Override
	public int hashCode() {
		return resource.getURI().hashCode();
	}

	/**
	 * Returns a String representation of this <code>UML2Model</code>.
	 * 
	 * @see java.lang.Object#toString()
	 * 
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return resource.getURI().toString();
	}
}