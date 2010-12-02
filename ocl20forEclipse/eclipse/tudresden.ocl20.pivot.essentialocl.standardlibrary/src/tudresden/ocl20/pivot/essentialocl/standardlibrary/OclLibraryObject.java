package tudresden.ocl20.pivot.essentialocl.standardlibrary;

import tudresden.ocl20.pivot.pivotmodel.Operation;


public interface OclLibraryObject extends OclAny {

	OclAny invokeOperation(Operation operation, OclAny... args);
	
}
