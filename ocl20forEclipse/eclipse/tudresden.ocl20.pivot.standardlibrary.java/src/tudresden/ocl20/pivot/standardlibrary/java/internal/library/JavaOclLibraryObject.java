package tudresden.ocl20.pivot.standardlibrary.java.internal.library;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclLibraryObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.pivotmodel.Operation;


public abstract class JavaOclLibraryObject extends JavaOclAny implements OclLibraryObject {

	public JavaOclLibraryObject(IModelInstanceElement imiElement) {
		super(imiElement);
	}
	
	public OclAny invokeOperation(Operation operation, OclAny... args) {

		// TODO Auto-generated method stub
		return null;
	}

}
