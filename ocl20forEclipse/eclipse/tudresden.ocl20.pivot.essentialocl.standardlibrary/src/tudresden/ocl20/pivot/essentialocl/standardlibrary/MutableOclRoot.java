package tudresden.ocl20.pivot.essentialocl.standardlibrary;

/**
 * The <code>MutableOclRoot</code> interface defines additional methods that may be mixed in by
 * implementing classes. These methods allow altering property values of an OCL object. This is
 * required if OCL is used in the context of model transformations, e.g, with the QVT language. The
 * operations cover the two cases
 * 
 * <ul>
 * <li>setting of single- and multi-valued properties
 * <li>adding elements to multi-valued properties
 * </ul>
 * 
 * Both of these cases are supported by corresponding QVT Operational Mappings concrete syntax
 * definitions.
 * 
 * @author Matthias Braeuer
 * @version 1.0 30.03.2007
 */
public interface MutableOclRoot {

  /**
   * Sets the value of a property. In the case of a multivalued property, the whole list is
   * reset before the new value is assigned.
   * 
   * <p>
   * In QVT, the corresponding syntax is
   * 
   * <pre>
   * mysimpleproperty := "hello";
   * mymultivaluedproperty := object Node {...};
   * </pre>
   * </p>
   * 
   * @param propertyName the name of the property to set
   * @param propertyValue the new value
   */
  void setPropertyValue(String propertyName, OclRoot propertyValue);

  /**
   * Adds new values to a multivalued property.
   * 
   * <p>
   * The QVT syntax looks like 
   * 
   * <pre>
   * mymultivaluedproperty += object Node {…}; // additive semantics
   * </pre>
   * </p>
   * 
   * @param propertyName the name of the property to set
   * @param propertyValue the value to append to the multivalued property
   */
  void addPropertyValue(String propertyName, OclRoot propertyValue);

}
