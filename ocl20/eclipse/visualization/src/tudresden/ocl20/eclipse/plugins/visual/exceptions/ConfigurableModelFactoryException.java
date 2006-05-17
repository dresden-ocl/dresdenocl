package tudresden.ocl20.eclipse.plugins.visual.exceptions;

/**
 * Exception for ConfigurableModelFactory
 * @author Kai-Uwe Gärtner
 *
 */
public class ConfigurableModelFactoryException extends Exception {
	public ConfigurableModelFactoryException(String s){
		super(s);
	}

	public ConfigurableModelFactoryException(String s,Exception e){
		super(s,e);
	}

}
