package tudresden.ocl20.pivot.modelbus;

import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;

/**
 * This is a general exception that may be thrown when an error occurs while loading, saving,
 * retrieving or otherwise accessing a {@link IMetamodel metamodel}, {@link IModel model} or
 * {@link IModelInstance model instance}.
 * 
 * @author Matthias Braeuer
 * @version 1.0 10.04.2007
 */
public class ModelAccessException extends Exception {

  // generated serial version id
  private static final long serialVersionUID = 6921256999901133096L;

  /**
   * Creates a new <code>ModelAccessException</code> with an error message.
   * 
   * @param message the message
   */
  public ModelAccessException(String message) {
    super(message);
  }

  /**
   * Creates a new <code>ModelAccessException</code> with an error message and a
   * <code>Throwable</code> that is the root cause of this exception.
   * 
   * @param message the message
   * @param cause the cause of this exception
   */
  public ModelAccessException(String message, Throwable cause) {
    super(message,cause);
  }

}
