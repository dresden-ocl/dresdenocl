package tudresden.ocl20.pivot.modelbus;

/**
 * A <code>FactoryException</code> is thrown by a {@link IModelFactory model factory} or
 * {@link IModelInstanceFactory model instance factory} if something goes wrong when creating new
 * model or model instance elements.
 * 
 * @author Matthias Braeuer
 * @version 1.0 19.06.2007
 */
public class FactoryException extends Exception {

  /**
   * A generated serial version id.
   */
  private static final long serialVersionUID = 4502845163889282547L;

  /**
   * Creates a new <code>FactoryException</code> with an error message.
   * 
   * @param message an error message
   */
  public FactoryException(String message) {
    super(message);
  }

  /**
   * Creates a new <code>FactoryException</code> with an error message and a root cause.
   * 
   * @param message an error message
   * @param cause a causing <code>Throwable</code>
   */
  public FactoryException(String message, Throwable cause) {
    super(message,cause);
  }

}
