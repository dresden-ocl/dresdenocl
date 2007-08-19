package tudresden.ocl20.pivot.modelbus.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IOclLibraryProvider;
import tudresden.ocl20.pivot.modelbus.ITypeResolver;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusException;
import tudresden.ocl20.pivot.modelbus.TypeNotFoundException;
import tudresden.ocl20.pivot.pivotmodel.Type;


/**
 * A default implementation of the {@link ITypeResolver} interface.
 *
 * @author Matthias Braeuer
 * @version 1.0 19.06.2007
 */
public class TypeResolver implements ITypeResolver {

  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(TypeResolver.class);
  
  // the associated model
  private IModel model;
  
  // a cache for the primitive types from the OCL Standard library
  private Map<String, Type> primitiveTypes;


  /**
   * Creates a new default <code>TypeResolver</code>.
   * 
   * @param model the associated <code>IModel</code>, must not be <code>null</code>
   */
  public TypeResolver(IModel model) {
    
    // should not happen in the default implementation, but clients may create own type resolvers
    if (model == null) {
      throw new IllegalArgumentException("The reference to the associated model must not be null."); //$NON-NLS-1$
    }
    
    this.model = model;
  }


  /* (non-Javadoc)
   * @see tudresden.ocl20.pivot.modelbus.ITypeResolver#findType(java.util.List)
   */
  public Type findType(List<String> pathName) throws TypeNotFoundException, ModelAccessException {
    if (logger.isDebugEnabled()) {
      logger.debug("findType(pathName=" + pathName + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    Type type = null;

    if (pathName == null || pathName.isEmpty()) {
      throw new IllegalArgumentException("The path name must not be null or empty."); //$NON-NLS-1$
    }
    
    // try to look up a primitive type
    if (pathName.size() == 1) {
      type = getPredefinedTypes().get(pathName.get(0));
    }

    // if no primitive type found, try to look in the model
    if (type == null) {
      type = model.findType(pathName);
    }

    if (type == null) {
      throw new TypeNotFoundException("Unable to find type '" + pathName + "'."); //$NON-NLS-1$//$NON-NLS-2$
    }

    if (logger.isDebugEnabled()) {
      logger.debug("findType() - exit - return value=" + type); //$NON-NLS-1$
    }
    
    return type;
  }
 
  
  /**
   * Helper method to lazily get the OCL standard types.
   */
  private Map<String, Type> getPredefinedTypes(){

    if (primitiveTypes == null) {
      
      // get the OCL library
      IOclLibraryProvider provider = model.getOclLibraryProvider();

      if (provider == null) {
        throw new ModelBusException("The model '" + model.getDisplayName() //$NON-NLS-1$
            + "' did not return a valid provider for the OCL Standard Library."); //$NON-NLS-1$
      }

      OclLibrary oclLibrary = provider.getOclLibrary();

      // instantiate the map for the primitive types and initialize it
      primitiveTypes = new HashMap<String, Type>();
      initializePredefinedTypes(oclLibrary);
    }

    return primitiveTypes;
  }

  
  /**
   * Helper method to initialize the cache with the primitive types.
   * 
   * TODO: These explicit String references to the OCL Standard Library types should be replaced by
   * a generic lookup mechanism, maybe configured via external property files. To see why this is
   * necessary, notice that the type returned by oclLibrary.getOclBoolean() is not necessarily named
   * "Boolean". It is just known that this is the OCL boolean type, the concrete naming is up to the
   * model of the Standard Library provided by the OclLibraryProvider.
   */
  protected void initializePredefinedTypes(OclLibrary oclLibrary) {
    primitiveTypes.put("OclAny",oclLibrary.getOclAny()); //$NON-NLS-1$
    primitiveTypes.put("OclType",oclLibrary.getOclType()); //$NON-NLS-1$
    primitiveTypes.put("OclVoid",oclLibrary.getOclVoid()); //$NON-NLS-1$
    primitiveTypes.put("Invalid",oclLibrary.getOclInvalid()); //$NON-NLS-1$
    primitiveTypes.put("Boolean",oclLibrary.getOclBoolean()); //$NON-NLS-1$
    primitiveTypes.put("Integer",oclLibrary.getOclInteger()); //$NON-NLS-1$
    primitiveTypes.put("Real",oclLibrary.getOclReal()); //$NON-NLS-1$
    primitiveTypes.put("String",oclLibrary.getOclString()); //$NON-NLS-1$
    primitiveTypes.put("Collection",oclLibrary.getOclCollection()); //$NON-NLS-1$
    primitiveTypes.put("Sequence",oclLibrary.getOclSequence()); //$NON-NLS-1$
    primitiveTypes.put("Bag",oclLibrary.getOclBag()); //$NON-NLS-1$
    primitiveTypes.put("Set",oclLibrary.getOclSet()); //$NON-NLS-1$
    primitiveTypes.put("OrderedSet",oclLibrary.getOclOrderedSet()); //$NON-NLS-1$
  }
}
