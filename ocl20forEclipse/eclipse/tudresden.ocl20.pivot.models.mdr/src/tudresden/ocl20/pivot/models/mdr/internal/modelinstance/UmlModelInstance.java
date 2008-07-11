package tudresden.ocl20.pivot.models.mdr.internal.modelinstance;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Platform;

import tudresden.ocl20.core.jmi.uml15.impl.modelmanagement.ModelHelper;
import tudresden.ocl20.core.jmi.uml15.modelmanagement.Model;
import tudresden.ocl20.core.jmi.uml15.uml15.Uml15Package;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollectionType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumLiteral;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclTupleType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.StandardlibraryAdapterFactory;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelInstance;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;
import tudresden.ocl20.pivot.standardlibrary.java.internal.factory.JavaStandardlibraryAdapterFactory;

public class UmlModelInstance extends AbstractModelInstance implements IModelInstance {

	private Uml15Package umlPackage;
	
	private Model toppackage;
	
	private Map<String, List<IModelObject>> objects;
	private List<IModelObject> allObjects;
	private List<List<String>> objectKinds;
	
	private IModelInstanceFactory factory;
	
	private Map<Class, OclType> knownTypes;
		
	private static Map<Integer, Map<String, String>> operationNames = new HashMap<Integer, Map<String,String>>();
	
	static {
		Map<String, String> binaryOperations = new HashMap<String, String>();
		Map<String, String> unaryOperations = new HashMap<String, String>();
		binaryOperations.put("<=", "isLessEqual");
		binaryOperations.put("<", "isLessThan");
		binaryOperations.put("=", "isEqualTo");
		binaryOperations.put(">=", "isGreaterEqual");
		binaryOperations.put(">", "isGreaterThan");
		binaryOperations.put("-", "subtract");
		binaryOperations.put("+", "add");
		binaryOperations.put("*", "multiply");
		binaryOperations.put("/", "divide");
		binaryOperations.put(".", "getPropertyValue");
		binaryOperations.put("->", "asSet");
		operationNames.put(2, binaryOperations);
		unaryOperations.put("-", "negative");
		operationNames.put(1, unaryOperations);
	}
	
	protected static StandardlibraryAdapterFactory DEFAULTSLAF = 
		JavaStandardlibraryAdapterFactory.getInstance();
	
	public UmlModelInstance(Class providerClass, Uml15Package umlPackage) throws ModelAccessException {
		objects = new HashMap<String, List<IModelObject>>();
		allObjects = new ArrayList<IModelObject>();
		objectKinds = new ArrayList<List<String>>();
		knownTypes = new HashMap<Class, OclType>();
		instanceName = providerClass.getCanonicalName();
		this.umlPackage = umlPackage;
		this.toppackage = ModelHelper.getInstance(umlPackage).getTopPackage();

		this.currentSlAF = DEFAULTSLAF;
		try {
			Method m = providerClass.getDeclaredMethod("getModelObjects", new Class[0]);
			List<Object> objects = (List<Object>)m.invoke(null, new Object[0]);
			addObjects(objects);
		} catch (ModelAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			throw new ModelAccessException("Problem retrieving ModelObjects", e);
		} catch (NoSuchMethodException e) {
			throw new ModelAccessException("Problem retrieving ModelObjects", e);
		} catch (IllegalArgumentException e) {
			throw new ModelAccessException("Problem retrieving ModelObjects", e);
		} catch (IllegalAccessException e) {
			throw new ModelAccessException("Problem retrieving ModelObjects", e);
		} catch (InvocationTargetException e) {
			throw new ModelAccessException("Problem retrieving ModelObjects", e);
		}		
	}

	public OclType findType(List<String> pathName) {
		Class typeClass = null;
		OclType type = null;
		ArrayList<String> list = new ArrayList<String>(pathName);
		
		if (isObjectOfModel(pathName))
		{
			if (toppackage.getName().equals(list.get(0)))
				list.remove(0);

			String path = list.remove(0);
			
			Iterator<String> it = list.iterator();
			
			while (it.hasNext())
				path = path + "." + it.next();
			
			try {
				typeClass = Class.forName(path);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			type = knownTypes.get(typeClass);
			
			if (type == null) {
				type = (OclType)Platform.getAdapterManager().getAdapter(typeClass, OclType.class);
				knownTypes.put(typeClass, type);
			}
		}
		
		return type;
	}

	public OclCollectionType getCollectionType(OclCollectionTypeKind kind,
			OclType elementType) {
		// TODO Auto-generated method stub
		return null;
	}

	public IModelInstanceFactory getFactory() {
		if (factory == null)
			factory = new UmlModelInstanceFactory();
		return factory;
	}

	public OclTupleType getTupleType(String[] partNames, OclType[] partTypes) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<IModelObject> getObjects() {
		return allObjects;
	}

	public List<IModelObject> getObjectsOfKind(List<String> typePath) {
		if (typePath != null)
		{	
			List<String> path = null;
			path = new ArrayList<String>();
			Iterator<String> tpIt = typePath.iterator();
			while (tpIt.hasNext()) {
				String current = tpIt.next();
				if (!current.contains("("))
					path.add(current);
				else
					break;
			}
			if (!isObjectOfModel(path))
			{
				while (path.size() > 0 && !path.get(0).equals(toppackage.getName()))
					path.remove(0);
			}
			if (path.get(0).equals(toppackage.getName()))
				path.remove(0);
			String type = path.remove(0);
			Iterator<String> it = path.iterator();
			while (it.hasNext())
				type = type + "." + it.next();
			List<IModelObject> temp = objects.get(type);
			if (temp == null)
				temp = new ArrayList<IModelObject>();
			return temp;
		}
		throw new IllegalArgumentException("TypePath must not be null");
	}
	
	private void addObject(Object o) throws ModelAccessException {
		String[] path = o.getClass().getCanonicalName().split("[.]");
		if (!isObjectOfModel(new ArrayList<String>(Arrays.asList(path))))
			throw new ModelAccessException("ModelInstance doesn't match to model. " +
					"Type for " + o.getClass() + " not found in model " + umlPackage.toString());
		String key = o.getClass().getCanonicalName();
		List<IModelObject> temp = objects.get(key);
		if (temp == null)
			temp = new ArrayList<IModelObject>();
		List<String> qualifiedName = Arrays.asList((toppackage.getName() + "." + key).split("[.]"));
		UmlModelObject newObject = new UmlModelObject(o, key, qualifiedName);
		temp.add(newObject);
		objects.put(key, temp);
		allObjects.add(newObject);
		if (!objectKinds.contains(qualifiedName))
			objectKinds.add(qualifiedName);
	}
	
	private boolean isObjectOfModel(List<String> pathName) {
		List<String> modifiedPathname = new ArrayList<String>(pathName);
		if (toppackage.getName().equals(modifiedPathname.get(0)))
			modifiedPathname.remove(0);
		modifiedPathname.add(0,modifiedPathname.remove(modifiedPathname.size()-1));
		if (toppackage != null)
			if (toppackage.findClassifier(modifiedPathname) != null)
				return true;
		return false;
	}

	private void addObjects(List<Object> objects) throws ModelAccessException {
		Iterator<Object> it = objects.iterator();
		while (it.hasNext())
			addObject(it.next());
	}

	public OclEnumType findEnumType(List<String> pathName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public OclEnumLiteral findEnumLiteral(List<String> pathName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<List<String>> getObjectKinds() {
		return objectKinds;
	}
}
