package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.metamodels.uml2.UML2MetamodelPlugin;
import tudresden.ocl20.pivot.pivotmodel.NDirectionalProperty;
import tudresden.ocl20.pivot.pivotmodel.Property;

/**
 * <p>
 * An implementation of the Pivot Model {@link Property} concept for UML2.
 * </p>
 * 
 * @author Michael Thiele
 * 
 * @generated NOT
 */
public class UML2NDirectionalProperty extends UML2Property implements NDirectionalProperty {

	/**
	 * <p>
	 * Logger for this class
	 * </p>
	 * 
	 * @generated NOT
	 */
	private static final Logger LOGGER =
			UML2MetamodelPlugin.getLogger(UML2NDirectionalProperty.class);

	/**
	 * 
	 * @generated NOT
	 */
	private List<NDirectionalProperty> inverseNDirectionalProperties;

	/**
	 * <p>
	 * Creates a new <code>UML2NDirectionalProperty</code> instance.
	 * </p>
	 * 
	 * @param dslProperty
	 *          the {@link org.eclipse.uml2.uml.Property} that is adopted by this
	 *          class
	 * 
	 * @generated NOT
	 */
	public UML2NDirectionalProperty(org.eclipse.uml2.uml.Property dslProperty) {

		super(dslProperty);
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("UML2NDirectionalProperty(dslProperty=" + dslProperty + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("UML2NDirectionalProperty() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.NDirectionalProperty#addAssociation(NDirectionalProperty)
	 * 
	 * @generated NOT
	 */
	public void addAssociation(NDirectionalProperty bProperty) {
		if (!isInverseAssociation(bProperty) && bProperty != this && bProperty != null) this.getInverseNDirectionalProperties().add(bProperty);	
	}
	
	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.NDirectionalProperty#getAssociation(String)
	 * 
	 * @generated NOT
	 */
	public NDirectionalProperty getAssociation(String propName) {
		NDirectionalProperty property = null;
		for (NDirectionalProperty prop : getInverseNDirectionalProperties()) {
			if (prop.getName().equals(propName)) {
				property = prop;
				break;
			}
		}
		return property;
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.NDirectionalProperty#removeAssociation(NDirectionalProperty)
	 * 
	 * @generated NOT
	 */
	public void removeAssociation(NDirectionalProperty bProperty) {
		this.getInverseNDirectionalProperties().remove(bProperty);
		
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.NDirectionalProperty#getInverseNDirectionalProperties()
	 * 
	 * @generated NOT
	 */
	public List<NDirectionalProperty> getInverseNDirectionalProperties() {
		if (this.inverseNDirectionalProperties == null) {
			this.inverseNDirectionalProperties = new LinkedList<NDirectionalProperty>();
		}
		return this.inverseNDirectionalProperties;
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.NDirectionalProperty#isInverseAssociation(NDirectionalProperty)
	 * 
	 * @generated NOT
	 */
	public boolean isInverseAssociation(NDirectionalProperty bProperty) {
		return getInverseNDirectionalProperties().contains(bProperty);
	}

	/**
	 * @see tudresden.ocl20.pivot.pivotmodel.NDirectionalProperty#addAssociations(List)
	 * 
	 * @generated NOT
	 */
	public void addAssociations(List<NDirectionalProperty> bProperty) {
		for (NDirectionalProperty prob : bProperty) {
			addAssociation(prob);
		}
	}

}