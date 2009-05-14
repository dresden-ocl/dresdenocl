package tudresden.ocl20.pivot.metamodels.ecore.internal.model;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty;

/**
 * <p>
 * An implementation of the Pivot Model {@link Property} concept for Ecore.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public class EcoreProperty extends AbstractProperty implements Property {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = Logger.getLogger(EcoreProperty.class);

	/** The adapted {@link EAttribute} or {@link EReference}. */
	private EStructuralFeature eStructuralFeature;

	/**
	 * <p>
	 * Creates a new {@link EcoreProperty} instance.
	 * </p>
	 * 
	 * @param eStructuralFeature
	 *            The adapted {@link EAttribute} or {@link EReference}.
	 */
	public EcoreProperty(EStructuralFeature eStructuralFeature) {
		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreProperty(";
			msg += "eStructuralFeature = " + eStructuralFeature;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		/* Initialize adapted feature. */
		this.eStructuralFeature = eStructuralFeature;

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreProperty() - exit";

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getName()
	 */
	@Override
	public String getName() {
		return this.eStructuralFeature.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getOwningType()
	 */
	@Override
	public Type getOwningType() {
		return EcoreAdapterFactory.INSTANCE.createType(this.eStructuralFeature
				.getEContainingClass());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getType()
	 */
	@Override
	public Type getType() {
		return EcoreAdapterFactory.INSTANCE.createType(this.eStructuralFeature
				.getEType());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isMultiple()
	 */
	@Override
	public boolean isMultiple() {
		return this.eStructuralFeature.isMany();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isOrdered()
	 */
	@Override
	public boolean isOrdered() {
		return this.eStructuralFeature.isOrdered();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isUnique()
	 */
	@Override
	public boolean isUnique() {
		return this.eStructuralFeature.isUnique();
	}

}
