package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.TypedElement;

import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty;

/**
 * An implementation of the Pivot Model {@link Property} concept for UML2.
 * 
 * @generated
 */
public class UML2Property extends AbstractProperty implements Property {

	/**
	 * Logger for this class
	 * 
	 * @generated
	 */
	private static final Logger logger = Logger.getLogger(UML2Property.class);

	/**
	 * the adapted org.eclipse.uml2.uml.Property data type
	 * 
	 * @generated
	 */
	private org.eclipse.uml2.uml.Property dslProperty;

	/**
	 * Creates a new <code>UML2Property</code> instance.
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
		return dslProperty.getName();
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
		return UML2AdapterFactory.INSTANCE.createType(dslProperty.getType());
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isMultiple()
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isMultiple() {
		return dslProperty.isMultivalued();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isOrdered()
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isOrdered() {
		return dslProperty.isOrdered();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isUnique()
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean isUnique() {
		return dslProperty.isUnique();
	}

}