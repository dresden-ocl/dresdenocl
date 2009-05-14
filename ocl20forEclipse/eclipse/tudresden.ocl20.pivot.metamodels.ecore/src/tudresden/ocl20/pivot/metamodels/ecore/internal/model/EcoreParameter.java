package tudresden.ocl20.pivot.metamodels.ecore.internal.model;

import org.apache.log4j.Logger;

import org.eclipse.emf.ecore.EParameter;

import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter;

/**
 * <p>
 * An implementation of the Pivot Model {@link Parameter} concept for Ecore.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public class EcoreParameter extends AbstractParameter implements Parameter {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = Logger.getLogger(EcoreParameter.class);

	/** The adapted {@link EParameter}. */
	private EParameter eParameter;

	/**
	 * <p>
	 * Creates a new {@link EcoreParameter}.
	 * </p>
	 * 
	 * @param eParameter
	 *            The adapted {@link EParameter}.
	 */
	public EcoreParameter(EParameter eParameter) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreParameter(";
			msg += "eParameter = " + eParameter;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		/* Initialize adapted EParameter. */
		this.eParameter = eParameter;

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreParameter() - exit";

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter#getName()
	 */
	@Override
	public String getName() {
		return this.eParameter.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter#getOperation()
	 */
	@Override
	public Operation getOperation() {
		return EcoreAdapterFactory.INSTANCE.createOperation(this.eParameter
				.getEOperation());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter#getType()
	 */
	@Override
	public Type getType() {
		return EcoreAdapterFactory.INSTANCE.createType(this.eParameter.getEType());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl#isMultiple()
	 */
	@Override
	public boolean isMultiple() {
		return this.eParameter.isMany();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl#isOrdered()
	 */
	@Override
	public boolean isOrdered() {
		return this.eParameter.isOrdered();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl#isUnique()
	 */
	@Override
	public boolean isUnique() {
		return this.eParameter.isUnique();
	}
}