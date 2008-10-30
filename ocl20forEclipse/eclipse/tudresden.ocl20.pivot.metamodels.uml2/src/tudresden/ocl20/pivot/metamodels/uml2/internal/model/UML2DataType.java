package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;

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
public class UML2DataType extends AbstractType implements Type {

	/**
	 * Logger for this class
	 * 
	 * @generated
	 */
	private static final Logger logger = Logger.getLogger(UML2DataType.class);

	/**
	 * the adapted DataType class
	 * 
	 * @generated
	 */
	private DataType dataType;

	/**
	 * Creates a new <code>UML2DataType</code> instance.
	 * 
	 * @param dataType
	 *          the {@link DataType} that is adopted by this class
	 * 
	 * @generated
	 */
	public UML2DataType(DataType dataType) {

		if (logger.isDebugEnabled()) {
			logger.debug("UML2DataType(dataType=" + dataType + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dataType = dataType;

		if (logger.isDebugEnabled()) {
			logger.debug("UML2DataType() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {
		return dataType.getName();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getNamespace()
	 * 
	 * @generated NOT
	 */
	@Override
	public Namespace getNamespace() {
		return UML2AdapterFactory.INSTANCE.createNamespace(dataType.getPackage());
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getOwnedPropertyImpl()
	 * 
	 * @generated NOT
	 */
	@Override
	protected List<Property> getOwnedPropertyImpl() {
		List<Property> ownedProperty = new ArrayList<Property>();

		for (org.eclipse.uml2.uml.Property property : dataType.getOwnedAttributes()) {
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

		for (org.eclipse.uml2.uml.Operation operation : dataType
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

		for (Classifier classifier : dataType.allParents()) {
			superType.add(UML2AdapterFactory.INSTANCE.createType(classifier));
		}

		return superType;
	}

}