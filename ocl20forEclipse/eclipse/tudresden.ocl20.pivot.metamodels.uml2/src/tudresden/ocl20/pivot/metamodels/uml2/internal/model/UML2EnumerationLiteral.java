package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;

import tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumerationLiteral;

/**
 * An implementation of the Pivot Model {@link EnumerationLiteral} concept for
 * UML2.
 * 
 * @generated NOT
 */
public class UML2EnumerationLiteral extends AbstractEnumerationLiteral
		implements EnumerationLiteral {

	/**
	 * Logger for this class
	 * 
	 * @generated
	 */
	private static final Logger logger = Logger
			.getLogger(UML2EnumerationLiteral.class);

	/**
	 * the adapted org.eclipse.uml2.uml.EnumerationLiteral data type
	 * 
	 * @generated
	 */
	private org.eclipse.uml2.uml.EnumerationLiteral dslEnumerationLiteral;

	/**
	 * Creates a new <code>UML2EnumerationLiteral</code> instance.
	 * 
	 * @param dslEnumerationLiteral
	 *          the {@link org.eclipse.uml2.uml.EnumerationLiteral} that is
	 *          adopted by this class
	 * 
	 * @generated
	 */
	public UML2EnumerationLiteral(
			org.eclipse.uml2.uml.EnumerationLiteral dslEnumerationLiteral) {

		if (logger.isDebugEnabled()) {
			logger
					.debug("UML2EnumerationLiteral(dslEnumerationLiteral=" + dslEnumerationLiteral + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslEnumerationLiteral = dslEnumerationLiteral;

		if (logger.isDebugEnabled()) {
			logger.debug("org.eclipse.uml2.uml.EnumerationLiteral() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumerationLiteral#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {
		return dslEnumerationLiteral.getName();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumerationLiteral#getEnumeration()
	 * 
	 * @generated NOT
	 */
	@Override
	public Enumeration getEnumeration() {
		return UML2AdapterFactory.INSTANCE.createEnumeration(dslEnumerationLiteral
				.getEnumeration());
	}

}