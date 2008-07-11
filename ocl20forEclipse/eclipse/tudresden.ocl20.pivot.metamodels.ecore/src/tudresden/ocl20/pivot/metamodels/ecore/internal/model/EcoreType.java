package tudresden.ocl20.pivot.metamodels.ecore.internal.model;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;

import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractType;


/**
 * An implementation of the Pivot Model {@link Type} concept for Ecore. 
 *
 * @author Matthias Braeuer
 * @version 1.0 12.04.2007
 */
public class EcoreType extends AbstractType implements Type {

  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(EcoreType.class);

  // the adapted Ecore EClass 
  private EClass eClass;
  
  /**
   * Creates a new <code>EcoreType</code> instance.
   */
  public EcoreType(EClass eClass) {
    if (logger.isDebugEnabled()) {
      logger.debug("EcoreType(eClass=" + eClass + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // initialize
    this.eClass = eClass;

    if (logger.isDebugEnabled()) {
      logger.debug("EcoreType() - exit"); //$NON-NLS-1$
    }
  }

  /* (non-Javadoc)
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getName()
   */
  @Override
  public String getName() {
    return eClass.getName();
  }

  /* (non-Javadoc)
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getNamespace()
   */
  @Override
  public Namespace getNamespace() {
    return EcoreAdapterFactory.INSTANCE.createNamespace(eClass.getEPackage());
  }

  /* (non-Javadoc)
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getOwnedPropertyImpl()
   */
  @Override
  protected List<Property> getOwnedPropertyImpl() {
    List<Property> ownedProperty = new ArrayList<Property>();
    
    for (EStructuralFeature eStructuralFeature : eClass.getEStructuralFeatures()) {
      ownedProperty.add(EcoreAdapterFactory.INSTANCE.createProperty(eStructuralFeature));
    }
    
    return ownedProperty;
  }

  /* (non-Javadoc)
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getOwnedOperationImpl()
   */
  @Override
  protected List<Operation> getOwnedOperationImpl() {
    List<Operation> ownedOperation = new ArrayList<Operation>();
    
    for (EOperation eOperation : eClass.getEOperations()) {
      ownedOperation.add(EcoreAdapterFactory.INSTANCE.createOperation(eOperation));
    }
    
    return ownedOperation;
  }

  /* (non-Javadoc)
   * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractType#getSuperTypeImpl()
   */
  @Override
  protected List<Type> getSuperTypeImpl() {
    List<Type> superType = new ArrayList<Type>();
    
    for (EClass eSuperType : eClass.getESuperTypes()) {
      superType.add(EcoreAdapterFactory.INSTANCE.createType(eSuperType));
    }
    
    return superType;
  }

}
