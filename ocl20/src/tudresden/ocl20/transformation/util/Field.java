/*
 * Created on 26.01.2006
 *
 */
package tudresden.ocl20.transformation.util;

/**
 * The class Field encapsulates all information for a attribute
 * field of a model element.
 * @author Christian Wende
 *
 */
public class Field {

	private String name;
	private Object value;
	
	/**
	 * The constructor for a field.
	 * @param name The name of the attribute field.
	 * @param value The value of the attribute field.
	 */
	public Field(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * Returns the name of the field.
	 * @return Returns the name of the field.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the value of the field.
	 * @return Returns the value the field.
	 */
	public Object getValue() {
		return value;
	}
	
}
