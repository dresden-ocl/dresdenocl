/*
 * Created on 06.02.2006
 *
 */
package tudresden.ocl20.transformation.inspect.interfaces;

import java.util.HashMap;
import java.util.Map;

import tudresden.ocl20.codegen.decl.mapping.MappedClass;
import tudresden.ocl20.codegen.decl.mapping.MappedModel;


/**
 * This class implements the interface MappedModel. This implementation is
 * used by the Uml2MappedModel transformation to provide the the 
 * navigation information needed by the DeclarativeCodeGenerator.
 *
 * @author Christian Wende
 */
public class MappedModelImpl implements MappedModel{

	private static final String NO_CLASS_FOR_NAME = "There is no class for the given name";
	private static int id = 0;
	private Map<String,MappedClass> name2mClass;
	
	/**
	 * The standard constructor for a MappedModelImpl.
	 *
	 */
	public MappedModelImpl() {
		name2mClass = new HashMap<String, MappedClass>();
	}
	
	/**
	 * This method returns the MappedClass for the given class name from the MappedModel
	 * 
	 * @param name the name of the class
	 * @return the MappedClass for the given class name from the MappedModel
	 */
	public MappedClass getClass(String name) {
		MappedClass mc = name2mClass.get(name); 
		if (mc == null) {
			throw new IllegalArgumentException(NO_CLASS_FOR_NAME + name);
		}
		return mc;
	}
	
	/**
	 * This method returns true if a mappedClass for the given name exists in the MappedModel.
	 * 
	 * @param name the name of the class 
	 * @return Returns true if a mappedClass for the given name exists in the MappedModel.
	 */
	public boolean isClass(String name) {
		return name2mClass.keySet().contains(name);
	}
 	
	public void addMappedClass(String name, MappedClass mClass) {
		name2mClass.put(name, mClass);
	}

	/**
	 * Returns a unique alias which may be used in the declarative target language
	 * 
	 * @return unique alias which may be used in the declarative target language
	 */
	public String getUniqueAlias() {
		id ++;
		if (id == 1) {
			return "SELF";
		}
		return "ALIAS" + id;
	}

}
