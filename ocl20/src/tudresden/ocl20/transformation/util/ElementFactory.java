/*
 * Created on 23.01.2006
 *
 */
package tudresden.ocl20.transformation.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.jmi.reflect.RefAssociation;
import javax.jmi.reflect.RefClass;
import javax.jmi.reflect.RefObject;
import javax.jmi.reflect.RefPackage;

import tudresden.ocl20.transformation.exception.CreationException;
import tudresden.ocl20.transformation.interfaces.Trace;
import tudresden.ocl20.transformation.interfaces.TraceType;

/**
 * This class implements a factory for model elements and associations for a given modeltype.
 * This modeltype is represented by its JMI RefPackage. It should be parameterized 
 * when the Factory is instanciated.
 * 
 * The element construction works reflectivy over the JMI interfaces.
 * 
 * @author Christian Wende
 *
 */
public class ElementFactory<MODEL extends RefPackage> {
	
	private static final String ADD_METHOD_NOT_FOUND = "The add-method to add the specified Association could not be found. ";
	private static final String ERROR_LOAD_BASE_PKG = "The base package for the element to be created could not be found. ";
	private static final String ERROR_ADD_ASS = "An internal error occured while adding the association. ";
	private static final String ERROR_CREATE_ELEMENT = "An internal error occured while creating the model element. ";
	private static final String ERROR_SET_FIELD = "An internal error occured while setting an elements field. ";
	private static final String ERROR_FIND_SETTER = "The setter-method for an elements field could not be found.";
	
	/** The concrete instance of modeltype, in which the elements will be created. **/
	MODEL model;
	/** The trace object, that logs the creation of the elements **/
	Trace trace;
	
	/**
	 * The constructor for the ElementFactory.
	 * @param model The concrete model instance, in which the element will be created.
	 * @param trace The Trace object for logging the element creation.
	 */
	public ElementFactory(MODEL model, Trace trace) {
		this.model = model;
		this.trace = trace;
	}
	
	/**
	 * Constructs a new assiciation in the model of the type "type" between the element a and b.
	 * This is done through reflection of the JMI interfaces for the metamodel.
	 * @param mPackage The package in which the assiciation is located in the metamodel
	 * @param type The type of association that should be created.
	 * @param a The first participant of the association
	 * @param b The second participant of the association
	 * @throws CreationException 
	 */
	public void addAssociation(String mPackage, Class type, RefObject a, RefObject b) throws CreationException {
		
		trace.addTrace(TraceType.CREATION, "Create Association of type " + type.getSimpleName() + " in target model.");
		
		RefPackage pkg = loadBasePackage(mPackage);
		Collection<RefAssociation> assos = pkg.refAllAssociations();
		for(RefAssociation ass:assos) {
			/* 
			 * The return type of java.lang.Class.getInterfaces()
			 * has changed in Java 1.6.
			 * To compile this file using Java 1.6 or later, change the
			 * generic of the List 'interfaces' from List<Class> to
			 * List<Class<?>>. 
			 */
			List<Class> interfaces = Arrays.asList(ass.getClass().getInterfaces());
			if(interfaces.contains(type)) {
				Method add = null;
				Method[] methods = ass.getClass().getMethods();
				for(Method m : methods) {
					if (m.getName().equals("add")) {
						add = m;
					}
				}
				
				if (add == null) {
					throw new CreationException(ADD_METHOD_NOT_FOUND + type.getSimpleName());
				}
			
				Object[] parameters = {a,b};
				try {
					add.invoke(ass, parameters);
				} catch (IllegalArgumentException e) {
					throw new CreationException(ERROR_ADD_ASS + type.getSimpleName());
				} catch (IllegalAccessException e) {
					throw new CreationException(ERROR_ADD_ASS + type.getSimpleName());
				} catch (InvocationTargetException e) {
					throw new CreationException(ERROR_ADD_ASS + type.getSimpleName());
				}
			}
			
		}	
	
		
		}
	
	
	private RefPackage loadBasePackage(String mPackage) throws CreationException {
		RefPackage pkg;
		if(!(mPackage == null)  && !mPackage.equals("") ) {
			Class modelClass = model.getClass();
			Method getPackage;
			try {
				getPackage = modelClass.getMethod("get" + mPackage, new Class[]{});
				pkg = (RefPackage) getPackage.invoke(model, new Object[]{});
			} catch (SecurityException e) {
				throw new CreationException(ERROR_LOAD_BASE_PKG + mPackage);
			} catch (NoSuchMethodException e) {
				throw new CreationException(ERROR_LOAD_BASE_PKG + mPackage);
			} catch (IllegalArgumentException e) {
				throw new CreationException(ERROR_LOAD_BASE_PKG + mPackage);
			} catch (IllegalAccessException e) {
				throw new CreationException(ERROR_LOAD_BASE_PKG + mPackage);
			} catch (InvocationTargetException e) {
				throw new CreationException(ERROR_LOAD_BASE_PKG + mPackage);
			}
		}
		else {
			pkg = model;
		}
		return pkg;
	}

	/**
	 * This method created a new model element for the given type. It uses reflection of the JMI interfaces
	 * of the metamodel.
	 * @param mPackage The package in which the type can be found in the metamodel
	 * @param type The type of the modelelement that should be created
	 * @param fields Contains the names and values for the element attributes that should be set after instanciation.
	 * @return The instanciated element.
	 * @throws CreationException 
	 */
	public <T> T createObject(String mPackage,Class<T> type, Field[] fields) throws CreationException {
		trace.addTrace(TraceType.CREATION, "Create Element of type " + type.getSimpleName() + " in target model.");
		
		T instance = null;
		Class modelClass = model.getClass();
		RefPackage pkg = loadBasePackage(mPackage);
		
		try {
			Method getXClass = pkg.getClass().getMethod(
					"get" + type.getSimpleName(), new Class[] {});
			RefClass xClass = (RefClass) getXClass.invoke(pkg, new Object[] {});

			Method createXInstance = xClass.getClass().getMethod(
					"create" + type.getSimpleName(), new Class[] {});
			instance = (T) createXInstance.invoke(xClass, new Object[] {});
			
		} catch (SecurityException e) {
			throw new CreationException(ERROR_CREATE_ELEMENT + type.getSimpleName());
		} catch (NoSuchMethodException e) {
			throw new CreationException(ERROR_CREATE_ELEMENT + type.getSimpleName());
		} catch (IllegalArgumentException e) {
			throw new CreationException(ERROR_CREATE_ELEMENT + type.getSimpleName());
		} catch (IllegalAccessException e) {
			throw new CreationException(ERROR_CREATE_ELEMENT + type.getSimpleName());
		} catch (InvocationTargetException e) {
			throw new CreationException(ERROR_CREATE_ELEMENT + type.getSimpleName());
		}	
		
		for (Field field : fields) {
			// TODO Add more type savety!
			Method[] methods = instance.getClass().getMethods();
			Method setter = null;
			for(Method m : methods) {
				if (m.getName().equals("set" + field.getName())) {
					setter = m;
				}
			}
			
			if(setter != null) {
				try {
					setter.invoke(instance, new Object[]{field.getValue()});
				} catch (IllegalArgumentException e) {
					throw new CreationException(ERROR_SET_FIELD + "[" + type.getSimpleName()+", " + field.getName() + "]");
				} catch (IllegalAccessException e) {
					throw new CreationException(ERROR_SET_FIELD + "[" + type.getSimpleName()+", " + field.getName() + "]");
				} catch (InvocationTargetException e) {
					throw new CreationException(ERROR_SET_FIELD + "[" + type.getSimpleName()+", " + field.getName() + "]");
				}
			}
			else {
				throw new CreationException(ERROR_FIND_SETTER + "[" + type.getSimpleName()+", " + field.getName() + "]");
			}
		}
		return instance;
	} 

	/**
	 * This method is a proxy for the method that creates model elements. It can be used when the 
	 * elementtype can be found in the "main" package of the metamodel.
	 * @param type The type of the modelelement that should be created
	 * @param fields Contains the names and values for the element attributes that should be set after instanciation.
	 * @return The instanciated element.
	 * @throws CreationException 
	 */
	public <T extends RefObject> T createObject(Class<T> type, Field[] fields) throws CreationException {
		return this.<T>createObject(null, type, fields);
	}
	
	/**
	 * This method is a proxy for the method that creates model elements. It can be used when 
	 * no atttributes should be set.
	 * @param mPackage The package in which the type can be found in the metamodel.
	 * @param type The type of the modelelement that should be created
	 * @return The instanciated element.
	 * @throws CreationException 
	 */
	public <T extends RefObject> T createObject(String mPackage, Class<T> type) throws CreationException {
		return this.<T>createObject(mPackage, type, new Field[] {});
	}
	
	/**
	 * This method is a proxy for the method that creates model elements. It can be used when the 
	 * elementtype can be found in the "main" package of the metamodel and no attributes should be set.
	 * @param type The type of the modelelement that should be created
	 * @return The instanciated element.
	 * @throws CreationException 
	 */
	public <T extends RefObject> T createObject(Class<T> type) throws CreationException {
		return this.<T>createObject(null, type, new Field[] {});
	}

}
