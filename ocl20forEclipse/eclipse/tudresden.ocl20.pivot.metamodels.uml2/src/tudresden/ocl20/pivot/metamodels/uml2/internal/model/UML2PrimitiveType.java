package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType;

/**
 * An implementation of the Pivot Model {@link PrimitiveType} concept for UML2.
 * 
 * @generated
 */
public class UML2PrimitiveType extends AbstractPrimitiveType implements
		PrimitiveType {

	/**
	 * Logger for this class
	 * 
	 * @generated
	 */
	private static final Logger logger = Logger
			.getLogger(UML2PrimitiveType.class);

	/**
	 * the adapted org.eclipse.uml2.uml.PrimitiveType data type
	 * 
	 * @generated
	 */
	private org.eclipse.uml2.uml.PrimitiveType dslPrimitiveType;

	/**
	 * Creates a new <code>UML2PrimitiveType</code> instance.
	 * 
	 * @param dslPrimitiveType
	 *            the {@link org.eclipse.uml2.uml.PrimitiveType} that is adopted
	 *            by this class
	 * 
	 * @generated
	 */
	public UML2PrimitiveType(org.eclipse.uml2.uml.PrimitiveType dslPrimitiveType) {

		if (logger.isDebugEnabled()) {
			logger
					.debug("UML2PrimitiveType(dslPrimitiveType=" + dslPrimitiveType + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslPrimitiveType = dslPrimitiveType;

		if (logger.isDebugEnabled()) {
			logger.debug("org.eclipse.uml2.uml.PrimitiveType() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getName()
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {
		return dslPrimitiveType.getName();
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getNamespace()
	 * 
	 * @generated NOT
	 */
	@Override
	public Namespace getNamespace() {
		return UML2AdapterFactory.INSTANCE.createNamespace(dslPrimitiveType
				.getPackage());
	}

	/**
	 * This method implements a type mapping from
	 * org.eclipse.uml2.uml.PrimitiveType types to the predefined primitive
	 * types of the Pivot Model.
	 * 
	 * @generated NOT
	 */
	@Override
	public PrimitiveTypeKind getKind() {

		PrimitiveTypeKind result;

		String integerNames[];
		String realNames[];
		String primitiveTypeName;

		integerNames = new String[] { "int", "short", "long", "byte" };
		realNames = new String[] { "float", "double" };
		primitiveTypeName = dslPrimitiveType.getName();

		result = null;

		if (primitiveTypeName.equalsIgnoreCase("boolean"))
			result = PrimitiveTypeKind.BOOLEAN;

		else if (primitiveTypeName.equalsIgnoreCase("string"))
			result = PrimitiveTypeKind.STRING;

		else {
			for (String integerName : integerNames) {
				if (primitiveTypeName.equalsIgnoreCase(integerName))
					result = PrimitiveTypeKind.INTEGER;
			}
		}

		if (result == null) {
			for (String realName : realNames) {
				if (primitiveTypeName.equalsIgnoreCase(realName))
					return PrimitiveTypeKind.REAL;
			}
		}
		// no else.

		if (result == null) {
			result = PrimitiveTypeKind.UNKNOWN;
		}
		// no else.

		return result;
	}
}