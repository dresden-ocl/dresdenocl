package tudresden.ocl20.pivot.models.ecore.internal.modelinstance;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollectionType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumLiteral;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclPrimitiveType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclTupleType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.StandardlibraryAdapterFactory;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelInstance;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;
import tudresden.ocl20.pivot.standardlibrary.java.internal.factory.JavaStandardlibraryAdapterFactory;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclVoid;

public class EcoreModelInstance extends AbstractModelInstance implements
		IModelInstance {

	private Resource resource;
	private Resource mmResource;

	private IModelInstanceFactory modelInstanceFactory;

	protected static StandardlibraryAdapterFactory DEFAULTSLAF = JavaStandardlibraryAdapterFactory
			.getInstance();

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

	public EcoreModelInstance(Resource resource, Resource mmResource) {
		this.resource = resource;
		instanceName = resource.toString();
		if (!resource.isLoaded()) {
			try {
				resource.load(null);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		this.mmResource = mmResource;
		if (!mmResource.isLoaded()) {
			try {
				mmResource.load(null);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		this.knownTypes = new HashMap<Class, OclType>();

		this.currentSlAF = DEFAULTSLAF;
		objects = new HashMap<String, List<IModelObject>>();
		allObjects = new ArrayList<IModelObject>();
		objectKinds = new ArrayList<List<String>>();
		createObjects(resource.getContents());
	}

	public OclCollectionType getCollectionType(OclCollectionTypeKind kind,
			OclType elementType) {
		// TODO Auto-generated method stub
		return null;
	}

	public OclTupleType getTupleType(String[] partNames, OclType[] partTypes) {
		// TODO Auto-generated method stub
		return null;
	}

	private Map<Class, OclType> knownTypes;

	public OclType findType(List<String> pathName) {
		if (pathName.size() == 1) {
			OclType primitiveType = getPrimitiveType(pathName.get(0));
			if (primitiveType != null)
				return primitiveType;
		}
		pathName = new ArrayList<String>(pathName);
		EList<EObject> listMM = mmResource.getContents();
		EObject temp = null;
		Iterator<String> it = pathName.iterator();
		boolean root = true;
		while (it.hasNext()) {
			String name = it.next();
			temp = null;
			Iterator<EObject> iter = listMM.iterator();
			while (iter.hasNext() && temp == null) {
				ENamedElement obj = (ENamedElement) iter.next();
				if (obj.getName().equals(name))
					temp = obj;
				else if (root)
					if (name.equals("root"))
						if (obj.getName().equals(it.next())) {
							temp = obj;
							pathName.remove(0);
							it = pathName.iterator();
							it.next();
						}
			}
			if (temp == null) {
				System.out.println("Type not found");
				return null;
			}
			root = false;
			listMM = temp.eContents();
		}
		EObject t2 = null;
		EObject inner = null;
		String remaining = null;

		List<String> classPath = new ArrayList<String>(pathName);

		while (t2 == null) {
			if (temp instanceof EClass) {
				t2 = temp;
			} else if (temp instanceof EPackage) {
				break;
			} else {
				int last = classPath.size() - 1;
				remaining = classPath.remove(last);
				inner = temp;
				temp = temp.eContainer();
			}
		}

		EList<EObject> list = resource.getContents();
		String path = list.get(0).getClass().getName().substring(0,
				list.get(0).getClass().getName().indexOf(pathName.get(0)) - 1);

		while (classPath.size() > 0) {
			if (classPath.size() > 1)
				path = path + "." + classPath.remove(0);
			else
				path = path + ".impl." + classPath.remove(0) + "Impl";
		}

		Class clazz = null;
		try {
			clazz = Class.forName(path);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Class typeClass = null;
		if (inner == null) {
			typeClass = clazz;
		} else {
			if (inner instanceof EAttribute) {
				try {
					Field field = clazz.getDeclaredField(remaining);
					typeClass = field.getType();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		OclType type = knownTypes.get(typeClass);

		if (type == null) {
			type = (OclType) Platform.getAdapterManager().getAdapter(typeClass,
					OclType.class);
			knownTypes.put(typeClass, type);
		}

		return type;
	}

	public IModelInstanceFactory getFactory() {
		if (modelInstanceFactory == null)
			modelInstanceFactory = new EcoreModelInstanceFactory();
		return modelInstanceFactory;
	}

	// List<IModelObject> objects;

	private Map<String, List<IModelObject>> objects;
	private List<IModelObject> allObjects;
	private List<List<String>> objectKinds;

	public List<IModelObject> getObjects() {
		return allObjects;
	}

	private String findQualifiedPath(List<EObject> mmResource, String object) {
		for (EObject currentObject : mmResource) {
			if (currentObject instanceof EPackage) {
				String temp = findQualifiedPath(currentObject.eContents(),
						object);
				if (temp != null)
					return ((EPackage) currentObject).getName() + "::" + temp;
			} else if (currentObject instanceof EClass) {
				if (((EClass) currentObject).getName().equals(object))
					return object;
			}
		}

		return null;
	}

	private void createObjects(List<EObject> contents) {
		for (EObject currentObject : contents) {
			createObjects(currentObject.eContents());
			String key = currentObject.eClass().getName();
			// String key = name.substring(0, name.lastIndexOf("Impl"));
			String qualifiedName = "root::"
					+ findQualifiedPath(mmResource.getContents(), key);
			List<IModelObject> temp = objects.get(qualifiedName);
			if (temp == null)
				temp = new ArrayList<IModelObject>();
			EcoreModelObject o = new EcoreModelObject(currentObject, Arrays
					.asList(qualifiedName.split("::")));
			temp.add(o);
			allObjects.add(o);
			objects.put(qualifiedName, temp);
			if (!objectKinds.contains(Arrays.asList(qualifiedName.split("::"))))
				objectKinds.add(Arrays.asList(qualifiedName.split("::")));
		}
	}

	public List<IModelObject> getObjectsOfKind(List<String> typePath) {
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
		String type = null;
		for (String append : path) {
			if (type == null)
				type = append;
			else
				type = type + "::" + append;
		}
		return objects.get(type);
	}

	public OclEnumType findEnumType(List<String> pathName) {
		pathName = new ArrayList<String>(pathName);
		EList<EObject> listMM = mmResource.getContents();
		EObject temp = null;
		Iterator<String> it = pathName.iterator();
		boolean root = true;
		while (it.hasNext()) {
			String name = it.next();
			temp = null;
			Iterator<EObject> iter = listMM.iterator();
			while (iter.hasNext() && temp == null) {
				ENamedElement obj = (ENamedElement) iter.next();
				if (obj.getName().equals(name))
					temp = obj;
				else if (root)
					if (name.equals("root"))
						if (obj.getName().equals(it.next())) {
							temp = obj;
							pathName.remove(0);
							it = pathName.iterator();
							it.next();
						}
			}
			if (temp == null) {
				System.out.println("Type not found");
				return null;
			}
			root = false;
			listMM = temp.eContents();
		}
		EObject t2 = null;
		EObject inner = null;
		String remaining = null;

		List<String> classPath = new ArrayList<String>(pathName);

		if (temp instanceof EEnumLiteral)
		{
			t2 = ((EEnumLiteral)temp).getEEnum();
			int last = classPath.size() - 1;
			remaining = classPath.remove(last);
			inner = temp;
		}

		EList<EObject> list = resource.getContents();
		String path = list.get(0).getClass().getName().substring(0,
				list.get(0).getClass().getName().indexOf(pathName.get(0)) - 1);

		while (classPath.size() > 0) {
//			if (classPath.size() > 1)
				path = path + "." + classPath.remove(0);
//			else
//				path = path + ".impl." + classPath.remove(0) + "Impl";
		}

		Class clazz = null;
		try {
			clazz = Class.forName(path);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Class typeClass = null;
			typeClass = clazz;

		OclType type = knownTypes.get(typeClass);

		if (type == null || !(type instanceof OclEnumType)) {
			type = (OclType) Platform.getAdapterManager().getAdapter(typeClass,
					OclEnumType.class);
			knownTypes.put(typeClass, type);
		}

		return (OclEnumType)type;
	}

	public OclEnumLiteral findEnumLiteral(List<String> pathName) {
		pathName = new ArrayList<String>(pathName);
		EList<EObject> listMM = mmResource.getContents();
		EObject temp = null;
		Iterator<String> it = pathName.iterator();
		boolean root = true;
		while (it.hasNext()) {
			String name = it.next();
			temp = null;
			Iterator<EObject> iter = listMM.iterator();
			while (iter.hasNext() && temp == null) {
				ENamedElement obj = (ENamedElement) iter.next();
				if (obj.getName().equals(name))
					temp = obj;
				else if (root)
					if (name.equals("root"))
						if (obj.getName().equals(it.next())) {
							temp = obj;
							pathName.remove(0);
							it = pathName.iterator();
							it.next();
						}
			}
			if (temp == null) {
				System.out.println("Type not found");
				return null;
			}
			root = false;
			listMM = temp.eContents();
		}
		EObject t2 = null;
		EObject inner = null;
		String remaining = null;

		List<String> classPath = new ArrayList<String>(pathName);

		if (temp instanceof EEnumLiteral)
		{
			EList<EObject> list = resource.getContents();
			String path = list.get(0).getClass().getName().substring(0,
					list.get(0).getClass().getName().indexOf(pathName.get(0)) - 1);
			
			String enumLiteral = classPath.remove(classPath.size()-1);

			while (classPath.size() > 0) {
//				if (classPath.size() > 1)
					path = path + "." + classPath.remove(0);
//				else
//					path = path + ".impl." + classPath.remove(0) + "Impl";
			}

			Class clazz = null;
			try {
				clazz = Class.forName(path);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Class typeClass = null;
				typeClass = clazz;

			OclEnumLiteral literal = null;

			if (typeClass.isEnum())
			{
				Object[] enums = typeClass.getEnumConstants();
				Object enumLiteralObj = null;
				for (Object enumObj : Arrays.asList(enums)) {
					if (enumObj.toString().equals(enumLiteral))
					{
						enumLiteralObj = enumObj;
						break;
					}
				}
				literal = (OclEnumLiteral) Platform.getAdapterManager().getAdapter(enumLiteralObj,
						OclEnumLiteral.class);
			}

			return literal;
		}
		
		return null;
	}

	public List<List<String>> getObjectKinds() {
		return objectKinds;
	}
}
