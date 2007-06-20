package tudresden.ocl20.pivot.modelbus;


/**
 * Thrown to indicate that a type could not be found in the OCL Standard Library or the
 * currently active model.
 *
 * @author Matthias Braeuer
 * @version 1.0 19.06.2007
 */
public class TypeNotFoundException extends Exception {

  /**
   * A generated serial version id.
   */
  private static final long serialVersionUID = -4261184602866203986L;

  /**
   * Creates a <code>TypeNotFoundException</code> with an error message.
   * 
   * @param message an error message
   */
  public TypeNotFoundException(String message) {
    super(message);
  }

  /**
   * Creates a <code>TypeNotFoundException</code> with an error message and a cause for the error.
   * 
   * @param message an error message
   * @param cause a <code>Throwable</code> that is the cause for this exception
   */
  public TypeNotFoundException(String message, Throwable cause) {
    super(message,cause);
  }

}
