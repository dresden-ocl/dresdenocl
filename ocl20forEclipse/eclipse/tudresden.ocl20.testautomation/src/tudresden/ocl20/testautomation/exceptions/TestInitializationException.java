package tudresden.ocl20.testautomation.exceptions;


/**
 * thrown if something goes wrong initializing anything with the tests.
 * @TODO rename
 * @author franz
 *
 */
public class TestInitializationException extends Exception {

	private static final long serialVersionUID = 3669892073035715664L;
	
	public TestInitializationException(String message){
		super(message);
	}
	
	public TestInitializationException(Throwable cause){
		super(cause.getMessage(), cause);
	}

}
