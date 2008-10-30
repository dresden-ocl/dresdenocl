package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Package;

import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace;

/**
 * An implementation of the Pivot Model {@link Namespace} concept for UML2.
 * 
 * @generated NOT
 */
public class UML2Package extends AbstractNamespace implements Namespace {

	/**
	 * Logger for this class
	 * 
	 * @generated
	 */
	private static final Logger logger = Logger.getLogger(UML2Package.class);

	/**
	 * the adapted org.eclipse.uml2.uml.Package data type
	 * 
	 * @generated
	 */
	private org.eclipse.uml2.uml.Package dslPackage;

	/**
	 * Creates a new <code>UML2Package</code> instance.
	 * 
	 * @param dslPackage
	 *          the {@link org.eclipse.uml2.uml.Package} that is adopted by this
	 *          class
	 * 
	 * @generated
	 */
	public UML2Package(org.eclipse.uml2.uml.Package dslPackage) {

		if (logger.isDebugEnabled()) {
			logger.debug("UML2Package(dslPackage=" + dslPackage + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslPackage = dslPackage;

		if (logger.isDebugEnabled()) {
			logger.debug("org.eclipse.uml2.uml.Package() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {
		return dslPackage.getName();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getNestedNamespaceImpl()
	 * 
	 * @generated NOT
	 */
	@Override
	protected List<Namespace> getNestedNamespaceImpl() {
		List<Namespace> nestedNamespace = new ArrayList<Namespace>();

		for (Package nestedDslNamespace : dslPackage.getNestedPackages()) {
			nestedNamespace.add(UML2AdapterFactory.INSTANCE
					.createNamespace(nestedDslNamespace));
		}

		return nestedNamespace;
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getNestingNamespace()
	 * 
	 * @generated NOT
	 */
	@Override
	public Namespace getNestingNamespace() {
		return (dslPackage.getNestingPackage() != null) ? UML2AdapterFactory.INSTANCE
				.createNamespace(dslPackage.getNestingPackage())
				: null;
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractNamespace#getOwnedTypeImpl()
	 * 
	 * @generated NOT
	 */
	@Override
	public List<Type> getOwnedType() {
		List<Type> ownedType = new ArrayList<Type>();

		for (org.eclipse.uml2.uml.Type dslOwnedType : dslPackage.getOwnedTypes()) {
			// Associations are Types in the UML model, but are translated into
			// Properties in
			// tudresden.ocl20.pivot.metamodels.uml2.internal.model.UML2Model#createRootNamespace(),
			// so they can be ignored
			if (!(dslOwnedType instanceof Association))
				ownedType.add(UML2AdapterFactory.INSTANCE.createType(dslOwnedType));
		}

		return ownedType;
	}

}