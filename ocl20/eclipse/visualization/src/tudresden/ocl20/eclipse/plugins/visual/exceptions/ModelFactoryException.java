package tudresden.ocl20.eclipse.plugins.visual.exceptions;

/**
 * Exception for ModelFactorys
 * @author Kai-Uwe Gärtner
 *
 */
public class ModelFactoryException extends Exception {
	public ModelFactoryException(String s){
		super(s);
	}
	
	public ModelFactoryException(Exception e){
		super(e);
	}

	public ModelFactoryException(String s,Exception e){
		super(s,e);
	}

}
