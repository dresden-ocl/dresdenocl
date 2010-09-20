package tudresden.ocl20.testautomation.exceptions;


/**
 * Is thrown if something goes wrong creating static or dynamic tests
 * @author franz
 *
 */
public class TestCreationException extends Exception {

	private static final long serialVersionUID = 7103615087183157663L;
	
	public TestCreationException(String message){
		super(message);
	}
	
	public TestCreationException(Throwable cause){
		super(cause.getMessage(), cause);
	}

	public TestCreationException(String msg, TestFileParseException cause) {
super(msg, cause);
	}
	
}
