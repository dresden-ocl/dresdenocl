/*
 * Created on 25.01.2006
 *
 */
package tudresden.ocl20.transformation.interfaces;

/**
 * The class TParameter encapsulates the information for
 * a transformation configuration parameter.
 * 
 * @author Christian Wende
 *
 */
public class TParameter {

	/**
	 * This Option defines a parameter, that value
	 * can be freely entered.
	 */
	public static int FREE = 1;
	/**
	 * This Option defines a parameter with strictly
	 * given options.
	 */
	public static int SELECT = 2;
	
	private String key;
	private String[] options;
	private int type;
	
	
	
	/**
	 * The constructor for a TParameter.
	 * @param key The name of the TParameter.
	 * @param options The options that can be set for the parameter.
	 * @param type The type of the parameter.
	 */
	public TParameter(String key, String[] options, int type) {
		this.key = key;
		this.options = options;
		this.type = type;
	}
	
	
	/**
	 * Returns the name of the TParameter.
	 * @return Returns the name of the TParameter.
	 */
	public String getKey() {
		return key;
	}
	/**
	 * Returns the chooseable options for the TParameter
	 * @return Returns the chooseable options for the TParameter
	 */
	public String[] getOptions() {
		return options;
	}


	/**
	 * Returns the type of the parameter.
	 * @return Returns the type of the parameter.
	 */
	public int getType() {
		return type;
	}
	
}
