package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.uml2.uml.Classifier;

import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractType;

/**
 * An implementation of the Pivot Model {@link Type} concept for UML2.
 * 
 * @generated
 */
public class UML2Interface extends AbstractType implements Type {

	/**
	 * Logger for this class
	 * 
	 * @generated
	 */
	private static final Logger logger = Logger.getLogger(UML2Interface.class);

	/**
	 * the adapted org.eclipse.uml2.uml.Interface class
	 * 
	 * @generated
	 */
	private org.eclipse.uml2.uml.Interface dslInterface;

	/**
	 * Creates a new <code>UML2Interface</code> instance.
	 * 
	 * @param dslInterface
	 *          the {@link org.eclipse.uml2.uml.Interface} that is adopted by this
	 *          class
	 * 
	 * @generated
	 */
	public UML2Interface(org.eclipse.uml2.uml.Interface dslInterface) {

		if (logger.isDebugEnabled()) {
			logger.debug("UML2Interface(dslInterface=" + dslInterface + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslInterface = dslInterface;

		if (logger.isDebugEnabled()) {
			logger.debug("UML2Interface() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {
		return dslInterface.getName();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getNamespace()
	 * 
	 * @generated
	 */
	@Override
	public Namespace getNamespace() {
		return UML2AdapterFactory.INSTANCE.createNamespace(dslInterface
				.getPackage());
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getOwnedPropertyImpl()
	 * 
	 * @generated NOT
	 */
	@Override
	protected List<Property> getOwnedPropertyImpl() {
		List<Property> ownedProperty = new ArrayList<Property>();

		for (org.eclipse.uml2.uml.Property property : dslInterface
				.getOwnedAttributes()) {
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

		for (org.eclipse.uml2.uml.Operation operation : dslInterface
				.getOwnedOperations()) {
			ownedOperation
					.add(UML2AdapterFactory.INSTANCE.createOperation(operation));
		}

		return ownedOperation;
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getSuperTypeImpl()
	 * 
	 * @generated
	 */
	@Override
	protected List<Type> getSuperTypeImpl() {
		List<Type> superType = new ArrayList<Type>();

		for (Classifier classifier : dslInterface.allParents()) {
			superType.add(UML2AdapterFactory.INSTANCE.createType(classifier));
		}

		return superType;
	}

}