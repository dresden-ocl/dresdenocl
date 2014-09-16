package org.dresdenocl.essentialocl.standardlibrary;

import org.dresdenocl.pivotmodel.Operation;


public interface OclLibraryObject extends OclAny {

	OclAny invokeOperation(Operation operation, OclAny... args);
	
}
