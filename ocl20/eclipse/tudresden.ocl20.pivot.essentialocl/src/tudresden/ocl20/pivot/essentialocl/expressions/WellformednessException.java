package tudresden.ocl20.pivot.essentialocl.expressions;

/**
 * A <code>WellformednessException</code> indicates that the attribute values of an
 * {@link OclExpression} violate one of the wellformedness rules defined in the OCL 2.0
 * Specification. This exception may be thrown when the type of an expression is requested but
 * cannot properly be determined due to missing or illegal attribute values.
 * 
 * @author Matthias Braeuer
 * @version 1.0 09.04.2007
 */
public class WellformednessException extends RuntimeException {

  // generated serial version id
  private static final long serialVersionUID = -6817394035789746585L;

  /**
   * Creates a <code>WellformednessException</code> with an error message describing which
   * wellformedness rule has been violated.
   * 
   * @param message an error message
   */
  public WellformednessException(String message) {
    super(message);
  }
}
