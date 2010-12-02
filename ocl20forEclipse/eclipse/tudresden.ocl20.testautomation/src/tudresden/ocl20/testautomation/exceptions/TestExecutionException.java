package tudresden.ocl20.testautomation.exceptions;


public class TestExecutionException extends Exception {

	private static final long serialVersionUID = 8276057934558193117L;
	
	public TestExecutionException(String message){
		super(message);
	}
	
	public TestExecutionException(Throwable cause){
		super(cause.getMessage(), cause);
	}
	
	public TestExecutionException(String message, Throwable cause){
		super(message, cause);
	}

}
