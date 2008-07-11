package tudresden.ocl20.pivot.metamodels.ecore.internal.model;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EStructuralFeature;

import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty;

/**
 * An implementation of the Pivot Model {@link Property} concept for Ecore.
 * 
 * @author Matthias Braeuer
 * @version 1.0 12.04.2007
 */
public class EcoreProperty extends AbstractProperty implements Property {

  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(EcoreProperty.class);

  // the adapted Ecore structural feature (EAttribute or EReference)
  private EStructuralFeature eStructuralFeature;

  /**
   * Creates a new <code>EcoreProperty</code> instance.
   */
  public EcoreProperty(EStructuralFeature eStructuralFeature) {
    if (logger.isDebugEnabled()) {
      logger.debug("EcoreProperty(eStructuralFeature=" + eStructuralFeature + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // initialize
    this.eStructuralFeature = eStructuralFeature;

    if (logger.isDebugEnabled()) {
      logger.debug("EcoreProperty() - exit"); //$NON-NLS-1$
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getName()
   */
  @Override
  public String getName() {
    return eStructuralFeature.getName();
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getOwningType()
   */
  @Override
  public Type getOwningType() {
    return EcoreAdapterFactory.INSTANCE.createType(eStructuralFeature.getEContainingClass());
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getType()
   */
  @Override
  public Type getType() {
    return EcoreAdapterFactory.INSTANCE.createType(eStructuralFeature.getEType());
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isMultiple()
   */
  @Override
  public boolean isMultiple() {
    return eStructuralFeature.isMany();
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isOrdered()
   */
  @Override
  public boolean isOrdered() {
    return eStructuralFeature.isOrdered();
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isUnique()
   */
  @Override
  public boolean isUnique() {
    return eStructuralFeature.isUnique();
  }

}
