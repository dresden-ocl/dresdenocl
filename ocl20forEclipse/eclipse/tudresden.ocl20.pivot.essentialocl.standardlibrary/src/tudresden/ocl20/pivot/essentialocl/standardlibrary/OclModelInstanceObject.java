package tudresden.ocl20.pivot.essentialocl.standardlibrary;

import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;


public interface OclModelInstanceObject extends OclAny {

	OclAny invokeOperation(Operation operation, OclAny...args);
	
	OclAny getProperty(Property property);
	
}
