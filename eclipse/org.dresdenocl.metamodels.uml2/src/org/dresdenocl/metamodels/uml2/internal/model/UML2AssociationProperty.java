package org.dresdenocl.metamodels.uml2.internal.model;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import org.dresdenocl.metamodels.uml2.UML2MetamodelPlugin;
import org.dresdenocl.pivotmodel.AssociationProperty;
import org.dresdenocl.pivotmodel.Property;

/**
 * <p>
 * An implementation of the Pivot Model {@link Property} concept for UML2.
 * </p>
 * 
 * @author Bjoern Freitag
 * 
 * @generated NOT
 */
public class UML2AssociationProperty extends UML2Property implements AssociationProperty {

	/**
	 * <p>
	 * Logger for this class
	 * </p>
	 * 
	 * @generated NOT
	 */
	private static final Logger LOGGER =
			UML2MetamodelPlugin.getLogger(UML2AssociationProperty.class);

	/**
	 * 
	 * @generated NOT
	 */
	private List<AssociationProperty> inverseAssociationProperties;

	/**
	 * <p>
	 * Creates a new <code>UML2AssociationProperty</code> instance.
	 * </p>
	 * 
	 * @param dslProperty
	 *          the {@link org.eclipse.uml2.uml.Property} that is adopted by this
	 *          class
	 * @param factory
	 *            The {@link UML2AdapterFactory} used to create nested elements.
	 * 
	 * 
	 * @generated NOT
	 */
	public UML2AssociationProperty(org.eclipse.uml2.uml.Property dslProperty, UML2AdapterFactory factory) {

		super(dslProperty, factory);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("UML2AssociationProperty(dslProperty=" + dslProperty + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("UML2AssociationProperty() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see org.dresdenocl.pivotmodel.AssociationProperty#addAssociation(AssociationProperty)
	 * 
	 * @generated NOT
	 */
	public void addAssociation(AssociationProperty bProperty) {
		if (!isInverseAssociation(bProperty) && bProperty != this && bProperty != null) this.getInverseAssociationProperties().add(bProperty);	
	}
	
	/**
	 * @see org.dresdenocl.pivotmodel.AssociationProperty#getAssociation(String)
	 * 
	 * @generated NOT
	 */
	public AssociationProperty getAssociation(String propName) {
		AssociationProperty property = null;
		for (AssociationProperty prop : getInverseAssociationProperties()) {
			if (prop.getName().equals(propName)) {
				property = prop;
				break;
			}
		}
		return property;
	}

	/**
	 * @see org.dresdenocl.pivotmodel.AssociationProperty#removeAssociation(AssociationProperty)
	 * 
	 * @generated NOT
	 */
	public void removeAssociation(AssociationProperty bProperty) {
		this.getInverseAssociationProperties().remove(bProperty);
		
	}

	/**
	 * @see org.dresdenocl.pivotmodel.AssociationProperty#getInverseAssociationProperties()
	 * 
	 * @generated NOT
	 */
	public List<AssociationProperty> getInverseAssociationProperties() {
		if (this.inverseAssociationProperties == null) {
			this.inverseAssociationProperties = new LinkedList<AssociationProperty>();
		}
		return this.inverseAssociationProperties;
	}

	/**
	 * @see org.dresdenocl.pivotmodel.AssociationProperty#isInverseAssociation(AssociationProperty)
	 * 
	 * @generated NOT
	 */
	public boolean isInverseAssociation(AssociationProperty bProperty) {
		return getInverseAssociationProperties().contains(bProperty);
	}

	/**
	 * @see org.dresdenocl.pivotmodel.AssociationProperty#addAssociations(List)
	 * 
	 * @generated NOT
	 */
	public void addAssociations(List<AssociationProperty> bProperty) {
		for (AssociationProperty prob : bProperty) {
			addAssociation(prob);
		}
	}

	public boolean isNavigable() {
		return dslProperty.isNavigable();
	}
}