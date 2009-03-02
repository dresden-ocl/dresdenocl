package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.osgi.util.NLS;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.TypedElement;

import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty;

/**
 * <p>
 * An implementation of the Pivot Model {@link Property} concept for UML2.
 * </p>
 * 
 * @author Michael Thiele
 * 
 * @generated
 */
public class UML2Property extends AbstractProperty implements Property {

	/**
	 * <p>
	 * Logger for this class
	 * </p>
	 * 
	 * @generated
	 */
	private static final Logger logger = Logger.getLogger(UML2Property.class);

	/**
	 * <p>
	 * the adapted org.eclipse.uml2.uml.Property data type
	 * </p>
	 * 
	 * @generated
	 */
	private org.eclipse.uml2.uml.Property dslProperty;

	/**
	 * <p>
	 * Creates a new <code>UML2Property</code> instance.
	 * </p>
	 * 
	 * @param dslProperty
	 *            the {@link org.eclipse.uml2.uml.Property} that is adopted by
	 *            this class
	 * 
	 * @generated
	 */
	public UML2Property(org.eclipse.uml2.uml.Property dslProperty) {

		if (logger.isDebugEnabled()) {
			logger
					.debug("UML2Property(dslProperty=" + dslProperty + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslProperty = dslProperty;

		if (logger.isDebugEnabled()) {
			logger.debug("UML2Property() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {
		return this.dslProperty.getName();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getOwningType()
	 * 
	 * @generated NOT
	 */
	@Override
	public Type getOwningType() {

		Type result;
		Element owner;

		result = null;
		owner = this.dslProperty.getOwner();

		if (owner instanceof TypedElement) {
			TypedElement aTypedElement;

			aTypedElement = (TypedElement) owner;

			result = UML2AdapterFactory.INSTANCE.createType(aTypedElement
					.getType());
		}

		else if (owner instanceof org.eclipse.uml2.uml.Class) {
			org.eclipse.uml2.uml.Class aClass;

			aClass = (org.eclipse.uml2.uml.Class) owner;

			result = UML2AdapterFactory.INSTANCE.createType(aClass);
		}

		else if (owner instanceof org.eclipse.uml2.uml.Association) {
			org.eclipse.uml2.uml.Association anAssociation;

			anAssociation = (org.eclipse.uml2.uml.Association) owner;

			EList<org.eclipse.uml2.uml.Property> associationEnds = anAssociation
					.getOwnedEnds();

			/* Does only work for binary associations! */
			for (org.eclipse.uml2.uml.Property anEnd : associationEnds) {

				/*
				 * Return the type of the end which does not represent this
				 * property.
				 */
				if (!anEnd.equals(this.dslProperty)) {

					result = UML2AdapterFactory.INSTANCE.createType(anEnd
							.getType());
					break;
				}
				// no else.

			}
		}

		else {
			if (logger.isInfoEnabled()) {
				logger.info(NLS.bind(UML2ModelMessages.UML2_GetOwningType, this
						.toString()));
			}
			// no else.
		}

		return result;
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getType()
	 * 
	 * @generated NOT
	 */
	@Override
	public Type getType() {
		return UML2AdapterFactory.INSTANCE.createType(this.dslProperty
				.getType());
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isMultiple()
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isMultiple() {
		return this.dslProperty.isMultivalued();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isOrdered()
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isOrdered() {
		return this.dslProperty.isOrdered();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.FeatureImpl#isStatic()
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isStatic() {
		return this.dslProperty.isStatic();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isUnique()
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isUnique() {
		return this.dslProperty.isUnique();
	}
}