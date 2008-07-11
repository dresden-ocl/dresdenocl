package tudresden.ocl20.pivot.metamodels.ecore.internal.model;

import org.apache.log4j.Logger;

import org.eclipse.emf.ecore.EParameter;

import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter;


/**
 * An implementation of the Pivot Model {@link Parameter} concept for Ecore. 
 *
 * @author Matthias Braeuer
 * @version 1.0 12.04.2007
 */
public class EcoreParameter extends AbstractParameter implements Parameter {

  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(EcoreParameter.class);

  // the adapted Ecore EParameter
  private EParameter eParameter;
  
  /**
   * 
   */
  public EcoreParameter(EParameter eParameter) {
    if (logger.isDebugEnabled()) {
      logger.debug("EcoreParameter(eParameter=" + eParameter + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    // initialize adapted parameter
    this.eParameter = eParameter;
    
    if (logger.isDebugEnabled()) {
      logger.debug("EcoreParameter() - exit"); //$NON-NLS-1$
    }
  }

  /* (non-Javadoc)
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter#getName()
   */
  @Override
  public String getName() {
    return eParameter.getName();
  }

  /* (non-Javadoc)
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter#getOperation()
   */
  @Override
  public Operation getOperation() {
    return EcoreAdapterFactory.INSTANCE.createOperation(eParameter.getEOperation());
  }

  /* (non-Javadoc)
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractParameter#getType()
   */
  @Override
  public Type getType() {
    return EcoreAdapterFactory.INSTANCE.createType(eParameter.getEType());
  }

  /* (non-Javadoc)
   * @see tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl#isMultiple()
   */
  @Override
  public boolean isMultiple() {
    return eParameter.isMany();
  }

  /* (non-Javadoc)
   * @see tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl#isOrdered()
   */
  @Override
  public boolean isOrdered() {
    return eParameter.isOrdered();
  }

  /* (non-Javadoc)
   * @see tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl#isUnique()
   */
  @Override
  public boolean isUnique() {
    return eParameter.isUnique();
  }  

}
