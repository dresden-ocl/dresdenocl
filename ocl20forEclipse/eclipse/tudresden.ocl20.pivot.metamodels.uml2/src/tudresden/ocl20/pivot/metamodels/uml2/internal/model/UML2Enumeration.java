package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration;

/**
 * An implementation of the Pivot Model {@link Enumeration} concept for UML2.
 * 
 * @generated NOT
 */
public class UML2Enumeration extends AbstractEnumeration implements Enumeration {

	/**
	 * Logger for this class
	 * 
	 * @generated
	 */
	private static final Logger logger = Logger.getLogger(UML2Enumeration.class);

	/**
	 * the adapted org.eclipse.uml2.uml.Enumeration data type
	 * 
	 * @generated
	 */
	private org.eclipse.uml2.uml.Enumeration dslEnumeration;

	/**
	 * Creates a new <code>UML2Enumeration</code> instance.
	 * 
	 * @param dslEnumeration
	 *          the {@link org.eclipse.uml2.uml.Enumeration} that is adopted by
	 *          this class
	 * 
	 * @generated
	 */
	public UML2Enumeration(org.eclipse.uml2.uml.Enumeration dslEnumeration) {

		if (logger.isDebugEnabled()) {
			logger
					.debug("UML2Enumeration(dslEnumeration=" + dslEnumeration + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslEnumeration = dslEnumeration;

		if (logger.isDebugEnabled()) {
			logger.debug("UML2Enumeration() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {
		return dslEnumeration.getName();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration#getNamespace()
	 * 
	 * @generated NOT
	 */
	@Override
	public Namespace getNamespace() {
		return UML2AdapterFactory.INSTANCE.createNamespace(dslEnumeration
				.getPackage());
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractEnumeration#getOwnedLiteral()
	 * 
	 * @generated NOT
	 */
	@Override
	public List<EnumerationLiteral> getOwnedLiteral() {
		List<EnumerationLiteral> literals = new ArrayList<EnumerationLiteral>();

		for (org.eclipse.uml2.uml.EnumerationLiteral dslEnumerationLiteral : dslEnumeration
				.getOwnedLiterals()) {
			literals.add(UML2AdapterFactory.INSTANCE
					.createEnumerationLiteral(dslEnumerationLiteral));
		}

		return literals;
	}
}