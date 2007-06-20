package tudresden.ocl20.pivot.modelbus;

import java.util.List;

import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * A <code>ITypeResolver</code> helps in finding types. These can either be located in the
 * {@link OclLibrary OCL library} or in an associated {@link IModel model}.
 * 
 * @author Matthias Braeuer
 * @version 1.0 19.06.2007
 */
public interface ITypeResolver {

  /**
   * Looks up a {@link Type} in the OCL library or in the associated model.
   * 
   * @param pathName the path name of the type to look for
   * 
   * @return a <code>Type</code> instance
   * 
   * @throws IllegalArgumentException if the given pathname is either <code>null</code> or empty
   * @throws ModelAccessException if something goes wrong when accessing the model 
   * @throws TypeNotFoundException if a type with the given path name cannot be found
   * @throws ModelBusException if a general configuration error occurs 
   */
  public Type findType(List<String> pathName) throws TypeNotFoundException, ModelAccessException;
  
}
