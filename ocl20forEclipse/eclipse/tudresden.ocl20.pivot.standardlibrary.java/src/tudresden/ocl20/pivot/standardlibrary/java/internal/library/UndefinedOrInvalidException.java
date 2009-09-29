package tudresden.ocl20.pivot.standardlibrary.java.internal.library;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;


public class UndefinedOrInvalidException extends RuntimeException {

	private OclAny reason;
	
	private static final long serialVersionUID = 2860657452868584307L;
	
	@SuppressWarnings("unused")
	private UndefinedOrInvalidException(){};
	
	public UndefinedOrInvalidException(OclAny reason) {
		this.reason = reason;
	}
	
	public OclAny getReason() {
		return reason;
	}

}
