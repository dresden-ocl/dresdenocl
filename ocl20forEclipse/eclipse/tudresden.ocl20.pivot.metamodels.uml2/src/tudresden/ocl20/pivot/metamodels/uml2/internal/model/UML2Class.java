package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.uml2.uml.Class;

import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractType;

/**
 * An implementation of the Pivot Model {@link Type} concept for UML2.
 * 
 * @generated NOT
 */
public class UML2Class extends AbstractType implements Type {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UML2Class.class);

	// the adapted org.eclipse.uml2.uml.Class class
	private org.eclipse.uml2.uml.Class dslClass;

	/**
	 * Creates a new <code>UML2Class</code> instance.
	 * 
	 * @param dslClass
	 *          the {@link org.eclipse.uml2.uml.Class} that is adopted by this
	 *          class
	 * 
	 * @generated
	 */
	public UML2Class(org.eclipse.uml2.uml.Class dslClass) {

		if (logger.isDebugEnabled()) {
			logger.debug("UML2Class(dslClass=" + dslClass + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslClass = dslClass;

		if (logger.isDebugEnabled()) {
			logger.debug("UML2Class() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {
		return dslClass.getName();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getNamespace()
	 * 
	 * @generated NOT
	 */
	@Override
	public Namespace getNamespace() {
		return UML2AdapterFactory.INSTANCE.createNamespace(dslClass.getPackage());
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getOwnedPropertyImpl()
	 * 
	 * @generated NOT
	 */
	@Override
	protected List<Property> getOwnedPropertyImpl() {
		List<Property> ownedProperty = new ArrayList<Property>();

		for (org.eclipse.uml2.uml.Property property : dslClass.getOwnedAttributes()) {
			ownedProperty.add(UML2AdapterFactory.INSTANCE.createProperty(property));
		}

		return ownedProperty;
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getOwnedOperationImpl()
	 * 
	 * @generated NOT
	 */
	@Override
	protected List<Operation> getOwnedOperationImpl() {
		List<Operation> ownedOperation = new ArrayList<Operation>();

		for (org.eclipse.uml2.uml.Operation operation : dslClass
				.getOwnedOperations()) {
			ownedOperation
					.add(UML2AdapterFactory.INSTANCE.createOperation(operation));
		}

		return ownedOperation;
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getSuperTypeImpl()
	 * 
	 * @generated NOT
	 */
	@Override
	protected List<Type> getSuperTypeImpl() {
		List<Type> superType = new ArrayList<Type>();

		for (Class clazz : dslClass.getSuperClasses()) {
			superType.add(UML2AdapterFactory.INSTANCE.createType(clazz));
		}

		return superType;
	}

}