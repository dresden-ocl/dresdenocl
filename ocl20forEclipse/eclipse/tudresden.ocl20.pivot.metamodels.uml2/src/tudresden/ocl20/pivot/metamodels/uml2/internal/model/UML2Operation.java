package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;

import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractOperation;

/**
 * An implementation of the Pivot Model {@link Operation} concept for UML2.
 * 
 * @generated
 */
public class UML2Operation extends AbstractOperation implements Operation {

	/**
	 * Logger for this class
	 * 
	 * @generated
	 */
	private static final Logger logger = Logger.getLogger(UML2Operation.class);

	/**
	 * the adapted UML2 org.eclipse.uml2.uml.Operation
	 * 
	 * @generated
	 */
	private org.eclipse.uml2.uml.Operation dslOperation;

	/**
	 * Creates a new <code>UML2Operation</code> instance.
	 * 
	 * @param dslOperation
	 *            the {@link org.eclipse.uml2.uml.Operation} that is adopted by
	 *            this class
	 * 
	 * @generated
	 */
	public UML2Operation(org.eclipse.uml2.uml.Operation dslOperation) {

		if (logger.isDebugEnabled()) {
			logger
					.debug("UML2Operation(dslOperation=" + dslOperation + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslOperation = dslOperation;

		if (logger.isDebugEnabled()) {
			logger.debug("UML2Operation() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractOperation#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {
		return dslOperation.getName();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractOperation#getType()
	 * 
	 * @generated NOT
	 */
	@Override
	public Type getType() {
		return UML2AdapterFactory.INSTANCE.createType(dslOperation.getType());
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#getReturnParameter()
	 * 
	 * @generated NOT
	 */
	public Parameter getReturnParameter() {
		Parameter result;

		result = UML2AdapterFactory.INSTANCE.createParameter(this.dslOperation
				.getReturnResult());
		
		return result;
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractOperation#getOwnedParameter()
	 * 
	 * @generated NOT
	 */
	@Override
	public List<Parameter> getOwnedParameter() {
		List<Parameter> ownedParameter = new ArrayList<Parameter>();

		for (org.eclipse.uml2.uml.Parameter dslOwnedParameter : dslOperation
				.getOwnedParameters()) {
			ownedParameter.add(UML2AdapterFactory.INSTANCE
					.createParameter(dslOwnedParameter));
		}

		return ownedParameter;
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractOperation#getOwningType()
	 * 
	 * @generated NOT
	 */
	@Override
	public Type getOwningType() {
		if (dslOperation.getOwner() instanceof org.eclipse.uml2.uml.Type)
			return UML2AdapterFactory.INSTANCE
					.createType(((org.eclipse.uml2.uml.Type) dslOperation
							.getOwner()));
		else {
			if (logger.isInfoEnabled()) {
				logger.info(NLS.bind(UML2ModelMessages.UML2_GetOwningType, this
						.toString()));
			}
			return null;
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#isMultiple()
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isMultiple() {
		// see: UML Infrastructure (07-11-04), p. 97
		// there is no operation isMultiple(), since Operation does not directly
		// inherit from MultiplicityElement
		return dslOperation.getUpper() > 1
				|| dslOperation.getUpper() == LiteralUnlimitedNatural.UNLIMITED;
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#isOrdered()
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isOrdered() {
		return dslOperation.isOrdered();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.FeatureImpl#isStatic()
	 * 
	 * @generated NOT
	 */
	public boolean isStatic() {
		return dslOperation.isStatic();
	}
	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#isUnique()
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isUnique() {
		return dslOperation.isUnique();
	}

}