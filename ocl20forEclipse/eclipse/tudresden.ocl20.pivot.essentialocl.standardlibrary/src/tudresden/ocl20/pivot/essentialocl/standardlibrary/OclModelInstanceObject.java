package tudresden.ocl20.pivot.essentialocl.standardlibrary;

import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;

/**
 * A model element that can be used as a standard library element. Inherits from
 * {@link OclAny} to support operations like "oclIsUndefined" on model types.
 * 
 * @author Michael Thiele
 * 
 */
public interface OclModelInstanceObject extends OclAny {

	/**
	 * {@inheritDoc}
	 */
	OclAny invokeOperation(Operation operation, OclAny... args);

	/**
	 * Returns the property value that can be retrieved with the given property.
	 * 
	 * @param property
	 *          the property to call
	 * @return the property value that can be retrieved with the given property
	 */
	OclAny getProperty(Property property);

}
