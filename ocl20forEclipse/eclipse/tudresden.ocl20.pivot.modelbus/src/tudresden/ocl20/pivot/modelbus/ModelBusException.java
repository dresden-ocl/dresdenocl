package tudresden.ocl20.pivot.modelbus;


/**
 * An unchecked exception that is thrown when unexpected errors or misconfigurations occur
 * in the model bus plugin.
 *
 * @author Matthias Braeuer
 * @version 1.0 19.06.2007
 */
public class ModelBusException extends RuntimeException {

  /**
   * A generated serial version id.
   */
  private static final long serialVersionUID = -5558531392214126891L;

  /**
   * Creates a new <code>ModelBusException</code>
   */
  public ModelBusException() {
    // no implementation necessary
  }

  /**
   * Creates a new <code>ModelBusException</code> with an error message.
   * 
   * @param message an error message
   */
  public ModelBusException(String message) {
    super(message);
  }

  /**
   * Creates a new <code>ModelBusException</code> with a root cause.
   * 
   * @param cause a causing <code>Throwable</code>
   */
  public ModelBusException(Throwable cause) {
    super(cause);
  }

  /**
   * Creates a new <code>ModelBusException</code> with an error message and a root cause.
   * 
   * @param message an erroe message
   * @param cause a causing <code>Throwable</code>
   */
  public ModelBusException(String message, Throwable cause) {
    super(message,cause);
  }

}
