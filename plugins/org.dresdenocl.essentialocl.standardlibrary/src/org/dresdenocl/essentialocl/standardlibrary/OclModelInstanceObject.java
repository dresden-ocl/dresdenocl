package org.dresdenocl.essentialocl.standardlibrary;

import org.dresdenocl.modelinstancetype.types.IModelInstanceObject;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.Property;

/**
 * A model element that can be used as a standard library element. Inherits from
 * {@link OclAny} to support operations like "oclIsUndefined" on model types.
 * 
 * @author Michael Thiele
 * 
 */
public interface OclModelInstanceObject extends OclAny {

	/**
	 * Returns the adapted {@link IModelInstanceObject}.
	 * 
	 * @return the adapted {@link IModelInstanceObject}
	 */
	IModelInstanceObject getModelInstanceObject();

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
