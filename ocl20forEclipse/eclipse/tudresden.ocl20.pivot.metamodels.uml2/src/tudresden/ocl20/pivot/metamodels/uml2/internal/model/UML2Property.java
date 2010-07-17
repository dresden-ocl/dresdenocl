package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.osgi.util.NLS;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.TypedElement;

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.metamodels.uml2.UML2MetamodelPlugin;
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
	 * @generated NOT
	 */
	private static final Logger LOGGER = UML2MetamodelPlugin
			.getLogger(UML2Property.class);

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
	 * The {@link UML2AdapterFactory} used to create nested elements.
	 * </p>
	 * 
	 * @generate NOT
	 */
	private UML2AdapterFactory factory;

	/**
	 * <p>
	 * Creates a new <code>UML2Property</code> instance.
	 * </p>
	 * 
	 * @param dslProperty
	 *            the {@link org.eclipse.uml2.uml.Property} that is adopted by
	 *            this class
	 * @param factory
	 *            The {@link UML2AdapterFactory} used to create nested elements.
	 * 
	 * @generated NOT
	 */
	public UML2Property(org.eclipse.uml2.uml.Property dslProperty,
			UML2AdapterFactory factory) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("UML2Property(dslProperty = " + dslProperty + ", factory = " + factory + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslProperty = dslProperty;
		this.factory = factory;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("UML2Property() - exit"); //$NON-NLS-1$
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

		/*
		 * PrimitiveTypes that are not adapted to primitive types in the IModel
		 * can have properties.
		 */
		if (owner instanceof org.eclipse.uml2.uml.PrimitiveType) {
			org.eclipse.uml2.uml.PrimitiveType primitiveType;
			primitiveType = (org.eclipse.uml2.uml.PrimitiveType) owner;

			result = this.factory.createType(primitiveType);
		}

		else if (owner instanceof TypedElement) {
			TypedElement aTypedElement;

			aTypedElement = (TypedElement) owner;

			result = this.factory.createType(aTypedElement.getType());
		}

		else if (owner instanceof org.eclipse.uml2.uml.Class) {
			org.eclipse.uml2.uml.Class aClass;

			aClass = (org.eclipse.uml2.uml.Class) owner;

			result = this.factory.createType(aClass);
		}

		else if (owner instanceof org.eclipse.uml2.uml.Interface) {
			org.eclipse.uml2.uml.Interface anInterface;

			anInterface = (org.eclipse.uml2.uml.Interface) owner;

			result = this.factory.createType(anInterface);
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

					result = this.factory.createType(anEnd.getType());
					break;
				}
				// no else.
			}
			// end for.
		}

		else {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info(NLS.bind(UML2ModelMessages.UML2_GetOwningType, this
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

		Type result;
		Type elementType;

		elementType = this.factory.createType(this.dslProperty.getType());

		/* Probably adapt type into a collection. */
		if (this.dslProperty.isMultivalued()) {

			if (this.dslProperty.isOrdered()) {

				/* OrderedSet. */
				if (this.dslProperty.isUnique()) {
					result = EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOrderedSetType(elementType);
				}

				/* Sequence. */
				else {
					result = EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getSequenceType(elementType);
				}
			}

			else {
				/* Set. */
				if (this.dslProperty.isUnique()) {
					result = EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getSetType(elementType);
				}

				/* Bag. */
				else {
					result = EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getBagType(elementType);
				}
			}
		}

		else {
			result = elementType;
		}

		return result;
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
}