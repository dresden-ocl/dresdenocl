package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.ParameterDirectionKind;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter;

/**
 * An implementation of the Pivot Model {@link Parameter} concept for UML2.
 * 
 * @generated
 */
public class UML2Parameter extends AbstractParameter implements Parameter {

	/**
	 * Logger for this class
	 * 
	 * @generated
	 */
	private static final Logger logger = Logger.getLogger(UML2Parameter.class);

	/**
	 * the adapted UML2 org.eclipse.uml2.uml.Parameter
	 * 
	 * @generated
	 */
	private org.eclipse.uml2.uml.Parameter dslParameter;

	/**
	 * Creates a new <code>UML2Parameter</code> instance.
	 * 
	 * @param dslParameter
	 *            the {@link org.eclipse.uml2.uml.Parameter} that is adopted by
	 *            this class
	 * 
	 * @generated
	 */
	public UML2Parameter(org.eclipse.uml2.uml.Parameter dslParameter) {

		if (logger.isDebugEnabled()) {
			logger
					.debug("UML2Parameter(dslParameter=" + dslParameter + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize adapted parameter
		this.dslParameter = dslParameter;

		if (logger.isDebugEnabled()) {
			logger.debug("UML2Parameter() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {
		return dslParameter.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl#getKind()
	 * 
	 * @generated NOT
	 */
	public ParameterDirectionKind getKind() {

		org.eclipse.uml2.uml.ParameterDirectionKind dslKind;
		dslKind = dslParameter.getDirection();

		if (dslKind.getValue() == org.eclipse.uml2.uml.ParameterDirectionKind.IN) {
			this.kind = ParameterDirectionKind.IN;
		}

		else if (dslKind.getValue() == org.eclipse.uml2.uml.ParameterDirectionKind.INOUT) {
			this.kind = ParameterDirectionKind.INOUT;
		}

		else if (dslKind.getValue() == org.eclipse.uml2.uml.ParameterDirectionKind.OUT) {
			this.kind = ParameterDirectionKind.OUT;
		}

		else if (dslKind.getValue() == org.eclipse.uml2.uml.ParameterDirectionKind.RETURN) {
			this.kind = ParameterDirectionKind.RETURN;
		}

		return this.kind;
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter#getOperation()
	 * 
	 * @generated NOT
	 */
	@Override
	public Operation getOperation() {
		return UML2AdapterFactory.INSTANCE.createOperation(dslParameter
				.getOperation());
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter#getType()
	 * 
	 * @generated NOT
	 */
	@Override
	public Type getType() {
		return UML2AdapterFactory.INSTANCE.createType(dslParameter.getType());
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl#isMultiple()
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isMultiple() {
		return dslParameter.isMultivalued();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl#isOrdered()
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isOrdered() {
		return dslParameter.isOrdered();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl#isUnique()
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isUnique() {
		return dslParameter.isUnique();
	}

}