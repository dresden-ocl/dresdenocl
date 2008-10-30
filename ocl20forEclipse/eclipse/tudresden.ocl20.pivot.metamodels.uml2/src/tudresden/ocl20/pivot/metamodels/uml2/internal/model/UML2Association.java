package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty;

/**
 * An implementation of the Pivot Model {@link Property} concept for UML2.
 * 
 * @generated NOT
 */
public class UML2Association extends AbstractProperty implements Property {

	/**
	 * Logger for this class
	 * 
	 * @generated
	 */
	private static final Logger logger = Logger.getLogger(UML2Association.class);

	/**
	 * the adapted org.eclipse.uml2.uml.Property data type
	 * 
	 * @generated
	 */
	private org.eclipse.uml2.uml.Association dslProperty;

	/**
	 * Creates a new <code>UML2Association</code> instance.
	 * 
	 * @param dslProperty
	 *          the {@link org.eclipse.uml2.uml.Association} that is adopted by
	 *          this class
	 * 
	 * @generated
	 */
	public UML2Association(org.eclipse.uml2.uml.Association dslProperty) {

		if (logger.isDebugEnabled()) {
			logger.debug("UML2Association(dslProperty=" + dslProperty + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslProperty = dslProperty;

		if (logger.isDebugEnabled()) {
			logger.debug("UML2Association() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {
		return dslProperty.getName();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getOwningType()
	 * 
	 * @generated NOT
	 */
	@Override
	public Type getOwningType() {
		if (dslProperty.getOwner() instanceof org.eclipse.uml2.uml.TypedElement)
			return UML2AdapterFactory.INSTANCE
					.createType(((org.eclipse.uml2.uml.TypedElement) dslProperty
							.getOwner()).getType());
		else {
			if (logger.isInfoEnabled()) {
				logger.info(NLS.bind(UML2ModelMessages.UML2_GetOwningType, this
						.toString()));
			}
			return null;
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getType()
	 * 
	 * @generated NOT
	 */
	@Override
	public Type getType() {
		// TODO: not tested yet!
		return UML2AdapterFactory.INSTANCE.createType(dslProperty.getEndTypes().get(0));
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isMultiple()
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isMultiple() {
		// TODO: not tested yet!
		return dslProperty.getMemberEnds().get(0).isMultivalued();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isOrdered()
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isOrdered() {
		// TODO: not tested yet!
		return dslProperty.getMemberEnds().get(0).isOrdered();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isUnique()
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isUnique() {
		// TODO: not tested yet!
		return dslProperty.getMemberEnds().get(0).isUnique();
	}

}