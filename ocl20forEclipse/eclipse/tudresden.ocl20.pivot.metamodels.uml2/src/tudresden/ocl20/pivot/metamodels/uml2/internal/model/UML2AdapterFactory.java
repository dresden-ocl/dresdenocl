package tudresden.ocl20.pivot.metamodels.uml2.internal.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Interface;

import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * Factory for Pivot Model types.
 * 
 * @generated NOT
 */
public class UML2AdapterFactory {

	/**
	 * logger for this class
	 * 
	 * @generated
	 */
	private static final Logger logger = Logger
      .getLogger(UML2AdapterFactory.class);

	/**
	 * The Singleton instance of the factory.
	 * 
	 * @generated
	 */
	public static UML2AdapterFactory INSTANCE = new UML2AdapterFactory();

	/**
	 * a cache for previously created adapters
	 * 
	 * @generated
	 */
	private Map<org.eclipse.uml2.uml.NamedElement, NamedElement> adapters;

	/**
	 * Clients are not supposed to instantiate this class.
	 * 
	 * @generated
	 */
	private UML2AdapterFactory() {
    adapters = new HashMap<org.eclipse.uml2.uml.NamedElement, NamedElement>();
  }

	/**
	 * Creates a {@link Namespace} adapter for an
	 * {@link org.eclipse.uml2.uml.Package}.
	 * 
	 * @generated NOT
	 */
	public Namespace createNamespace(org.eclipse.uml2.uml.Package dslPackage) {
    if (logger.isDebugEnabled()) {
      logger.debug("createNamespace(dslPackage=" + dslPackage +
        ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    if (dslPackage == null) {
      if (logger.isDebugEnabled())
        logger.debug("createNamespace() - exit: dslPackage is null");
      return null;
    }

    Namespace namespace = (Namespace) adapters.get(dslPackage);

    if (namespace == null) {
      if (logger.isInfoEnabled()) {
          logger.info(NLS.bind(UML2ModelMessages.UML2AdapterFactory_CreatingPivotModelAdapter,
              "org.eclipse.uml2.uml.Package", dslPackage.getName())); //$NON-NLS-1$
      }
      namespace = new UML2Package(dslPackage);
      adapters.put(dslPackage, namespace);
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createNamespace() - exit - return value=" + namespace); //$NON-NLS-1$
    }

    return namespace;
  }

	/**
	 * Creates a {@link Type} adapter for a {@link org.eclipse.uml2.uml.Class}.
	 * 
	 * @generated
	 */
	public Type createType(org.eclipse.uml2.uml.Class dslClass) {
    if (logger.isDebugEnabled()) {
      logger.debug("createType(dslClass=" + dslClass +
        ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    if (dslClass == null) {
      if (logger.isDebugEnabled())
        logger.debug("createType() - exit: dslClass is null");
      return null;
    }

    Type type = (Type) adapters.get(dslClass);

    if (type == null) {
      type = new UML2Class(dslClass);
      adapters.put(dslClass, type);
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createType() - exit - return value=" + type); //$NON-NLS-1$
    }

    return type;
  }

  /**
   * Creates a {@link Type} adapter for a {@link org.eclipse.uml2.uml.Interface}.
   *
   * @generated
   */
  public Type createType(org.eclipse.uml2.uml.Interface dslInterface) {
    if (logger.isDebugEnabled()) {
      logger.debug("createType(dslInterface=" + dslInterface +
        ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    if (dslInterface == null) {
      if (logger.isDebugEnabled())
        logger.debug("createType() - exit: dslInterface is null");
      return null;
    }

    Type type = (Type) adapters.get(dslInterface);

    if (type == null) {
      type = new UML2Interface(dslInterface);
      adapters.put(dslInterface, type);
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createType() - exit - return value=" + type); //$NON-NLS-1$
    }

    return type;
  }
  
  /**
   * Creates a {@link Type} adapter for a {@link DataType}.
   *
   * @generated
   */
  public Type createType(DataType dataType) {
    if (logger.isDebugEnabled()) {
      logger.debug("createType(dataType=" + dataType +
        ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    if (dataType == null) {
      if (logger.isDebugEnabled())
        logger.debug("createType() - exit: dataType is null");
      return null;
    }

    Type type = (Type) adapters.get(dataType);

    if (type == null) {
      type = new UML2DataType(dataType);
      adapters.put(dataType, type);
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createType() - exit - return value=" + type); //$NON-NLS-1$
    }

    return type;
  }
	
	/**
	 * Creates a specific Type.
	 * 
	 * @param dslType
	 * @return
	 * 
	 * @generated NOT
	 */
	public Type createType(org.eclipse.uml2.uml.Type dslType) {
		Type type = null;

		if (dslType == null)
			return null;

		if (dslType instanceof Class)
			type = createType((Class) dslType);

		else if (dslType instanceof org.eclipse.uml2.uml.PrimitiveType)
			type = createPrimitiveType((org.eclipse.uml2.uml.PrimitiveType) dslType);

		else if (dslType instanceof org.eclipse.uml2.uml.Enumeration)
			type = createEnumeration((org.eclipse.uml2.uml.Enumeration) dslType);
		
		else if (dslType instanceof DataType)
			type = createType((DataType) dslType);
		
		else if (dslType instanceof Interface)
			type = createType(dslType);
		
		else {
			// should not happen
			throw new IllegalArgumentException("Unknown Type: " + dslType); //$NON-NLS-1$
		}

		return type;
	}

	/**
	 * Creates an {@link Enumeration} adapter for an
	 * {@link org.eclipse.uml2.uml.Enumeration}.
	 * 
	 * @generated
	 */
	public Enumeration createEnumeration(
			org.eclipse.uml2.uml.Enumeration dslEnumeration) {
    if (logger.isDebugEnabled()) {
      logger.debug("createEnumeration(dslEnumeration=" + dslEnumeration +
        ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    if (dslEnumeration == null) {
      if (logger.isDebugEnabled())
        logger.debug("createEnumeration() - exit: dslEnumeration is null");
      return null;
    }
    
    Enumeration enumeration = (Enumeration) adapters.get(dslEnumeration);

    if (enumeration == null) {
      enumeration = new UML2Enumeration(dslEnumeration);
      adapters.put(dslEnumeration, enumeration);
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createEnumeration() - exit - return value=" + enumeration); //$NON-NLS-1$
    }

    return enumeration;
  }

	/**
	 * Creates an {@link EnumerationLiteral} adapter for an
	 * {@link org.eclipse.uml2.uml.EnumerationLiteral}.
	 * 
	 * @generated
	 */
	public EnumerationLiteral createEnumerationLiteral(
			org.eclipse.uml2.uml.EnumerationLiteral dslEnumerationLiteral) {
    if (logger.isDebugEnabled()) {
      logger.debug("createEnumerationLiteral(dslEnumerationLiteral=" + dslEnumerationLiteral + 
        ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    if (dslEnumerationLiteral == null) {
      if (logger.isDebugEnabled())
        logger.debug("createEnumerationLiteral() - exit: dslEnumerationLiteral is null");
      return null;
    }
    
    EnumerationLiteral literal = (EnumerationLiteral) adapters
        .get(dslEnumerationLiteral);

    if (literal == null) {
      literal = new UML2EnumerationLiteral(dslEnumerationLiteral);
      adapters.put(dslEnumerationLiteral, literal);
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createEnumerationLiteral() - exit - return value=" + literal); //$NON-NLS-1$
    }

    return literal;
  }

	/**
	 * Creates a {@link PrimitiveType} adapter for a
	 * {@link org.eclipse.uml2.uml.PrimitiveType}.
	 * 
	 * @generated
	 */
	public PrimitiveType createPrimitiveType(
			org.eclipse.uml2.uml.PrimitiveType dslPrimitiveType) {
    if (logger.isDebugEnabled()) {
      logger.debug("createPrimitiveType(dslPrimitiveType=" + dslPrimitiveType + 
        ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }
        
    if (dslPrimitiveType == null) {
      if (logger.isDebugEnabled())
        logger.debug("createPrimitiveType() - exit: dslPrimitiveType is null");
      return null;
    }

    PrimitiveType primitiveType = (PrimitiveType) adapters.get(dslPrimitiveType);

    if (primitiveType == null) {
      primitiveType = new UML2PrimitiveType(dslPrimitiveType);
      adapters.put(dslPrimitiveType, primitiveType);
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createPrimitiveType() - exit - return value=" + primitiveType); //$NON-NLS-1$
    }

    return primitiveType;
  }

	/**
	 * Creates a {@link Property} adapter for a
	 * {@link org.eclipse.uml2.uml.Property}.
	 * 
	 * @generated
	 */
	public Property createProperty(org.eclipse.uml2.uml.Property dslProperty) {
    if (logger.isDebugEnabled()) {
      logger.debug("createProperty(dslProperty=" + dslProperty +
        ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }
        
    if (dslProperty == null) {
      if (logger.isDebugEnabled())
        logger.debug("createProperty() - exit: dslProperty is null");
      return null;
    }

    Property property = (Property) adapters.get(dslProperty);

    if (property == null) {
      property = new UML2Property(dslProperty);
      adapters.put(dslProperty, property);
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createProperty() - exit - return value=" + property); //$NON-NLS-1$
    }

    return property;
  }
	
	/**
	 * Creates a {@link Property} adapter for a
	 * {@link org.eclipse.uml2.uml.Association}.
	 * 
	 * @generated
	 */
	public Property createProperty(org.eclipse.uml2.uml.Association dslProperty) {
    if (logger.isDebugEnabled()) {
      logger.debug("createProperty(dslProperty=" + dslProperty +
        ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }
        
    if (dslProperty == null) {
      if (logger.isDebugEnabled())
        logger.debug("createProperty() - exit: dslProperty is null");
      return null;
    }

    Property property = (Property) adapters.get(dslProperty);

    if (property == null) {
      property = new UML2Association(dslProperty);
      adapters.put(dslProperty, property);
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createProperty() - exit - return value=" + property); //$NON-NLS-1$
    }

    return property;
  }

	/**
	 * Creates an {@link Operation} adapter for an
	 * {@link org.eclipse.uml2.uml.Operation}.
	 * 
	 * @generated
	 */
	public Operation createOperation(org.eclipse.uml2.uml.Operation dslOperation) {
    if (logger.isDebugEnabled()) {
      logger.debug("createOperation(dslOperation=" + dslOperation +
        ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }
        
    if (dslOperation == null) {
      if (logger.isDebugEnabled())
        logger.debug("createOperation() - exit: dslOperation is null");
      return null;
    }

    Operation operation = (Operation) adapters.get(dslOperation);

    if (operation == null) {
      operation = new UML2Operation(dslOperation);
      adapters.put(dslOperation, operation);
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createOperation() - exit - return value=" + operation); //$NON-NLS-1$
    }

    return operation;
  }

	/**
	 * Creates a {@link Parameter} adapter for a
	 * {@link org.eclipse.uml2.uml.Parameter}.
	 * 
	 * @generated
	 */
	public Parameter createParameter(org.eclipse.uml2.uml.Parameter dslParameter) {
    if (logger.isDebugEnabled()) {
      logger.debug("createParameter(dslParameter=" + dslParameter +
        ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }
        
    if (dslParameter == null) {
      if (logger.isDebugEnabled())
        logger.debug("createParameter() - exit: dslParameter is null");
      return null;
    }

    Parameter parameter = (Parameter) adapters.get(dslParameter);

    if (parameter == null) {
      parameter = new UML2Parameter(dslParameter);
      adapters.put(dslParameter, parameter);
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createParameter() - exit - return value=" + parameter); //$NON-NLS-1$
    }

    return parameter;
  }

}