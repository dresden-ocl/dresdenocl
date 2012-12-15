package org.dresdenocl.essentialocl.expressions;

import org.dresdenocl.pivotmodel.NamedElement;

/**
 * A <code>WellformednessException</code> indicates that the attribute values
 * of an {@link OclExpression} violate one of the wellformedness rules defined
 * in the OCL 2.0 Specification. This exception may be thrown when the type of
 * an expression is requested but cannot properly be determined due to missing
 * or illegal attribute values.
 * 
 * @author Matthias Braeuer
 * @version 1.0 09.04.2007
 */
public class WellformednessException extends RuntimeException {

  // generated serial version id
  private static final long serialVersionUID = -6817394035789746585L;

  /**
   * The OCL expression that is the cause of the wellformedness violation.
   */
  private NamedElement malformedElement;

  /**
   * Creates a <code>WellformednessException</code> and specifies which
   * element is malformed and which wellformedness rule has been violated.
   * 
   * @param source the element causing the error
   * @param message an error message
   */
  public WellformednessException(NamedElement source, String message) {
    super(message);
    malformedElement = source;
  }

  /**
   * Returns the OCL expression violating a wellformedness rule.
   * 
   * @return the malformed expression
   */
  public NamedElement getMalformedElement() {
    return malformedElement;
  }

}
