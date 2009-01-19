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
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollectionType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumLiteral;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclTupleType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.StandardlibraryAdapterFactory;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelInstance;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;
import tudresden.ocl20.pivot.standardlibrary.java.internal.factory.JavaStandardlibraryAdapterFactory;

/**
 * <p>
 * Represents instances of the Ecore meta model.
 * </p>
 * 
 * @author Ronny Brandt
 */
public class EcoreModelInstance extends AbstractModelInstance implements
		IModelInstance {

	private Resource resource;
	private Resource modelResource;

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

	private Map<Class, OclType> knownTypes;

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
		this.modelResource = mmResource;
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

	/**
	 * @param pathName
	 *            A given canonical name as a {@link List} of packages.
	 * @return The {@link OclType} to a given canonical name as a {@link List}
	 *         of packages.
	 */
	public OclType findType(List<String> pathName) {

		OclType result;

		result = null;

		/* Check if the pathName does not denote at least one package. */
		if (pathName.size() == 1) {
			/* If not, its a primitive type. */
			result = getPrimitiveType(pathName.get(0));
		}
		// no else.

		/* Else try to find the type in the model. */
		if (result == null) {

			EList<EObject> modelPackageElements;
			EObject modelElement;

			Iterator<String> pathIt;
			boolean isRootPackage;

			/* Clone the pathName. */
			pathName = new ArrayList<String>(pathName);

			/* Get the model elements of the root package. */
			modelPackageElements = modelResource.getContents();
			modelElement = null;

			pathIt = pathName.iterator();
			isRootPackage = true;

			/*
			 * Iterate over the given path name and try to find a model element
			 * having the same path.
			 */
			while (pathIt.hasNext()) {

				String name;
				Iterator<EObject> modelPackageElemsIt;

				name = pathIt.next();
				modelElement = null;

				modelPackageElemsIt = modelPackageElements.iterator();

				/*
				 * Iterate over all elements of one model package and try to
				 * find a coresponding type or package to the actual part of the
				 * pathName.
				 */
				while (modelPackageElemsIt.hasNext() && modelElement == null) {
					ENamedElement modelElem;

					modelElem = (ENamedElement) modelPackageElemsIt.next();

					/* Try to find the next path element in the model. */
					if (modelElem.getName().equals(name)) {
						modelElement = modelElem;
					}

					/* Eventually ignore the root package. */
					else if (isRootPackage) {

						if (name.equals("root")) {
							if (modelElem.getName().equals(pathIt.next())) {
								modelElement = modelElem;
								pathName.remove(0);
								pathIt = pathName.iterator();
								pathIt.next();
							}
							// no else.
						}
						// no else.
					}
					// no else.

				}

				/* If no type was found, the result is null. */
				if (modelElement == null) {
					result = null;
					break;
				}
				// no else.

				isRootPackage = false;

				/* Get the model elements of the next package. */
				modelPackageElements = modelElement.eContents();
			}
			// end while.

			/* Try to load the class of the found model element. */
			if (modelElement != null) {

				List<String> classPath;

				EObject typeInModel;
				EObject inner;
				String remaining;

				typeInModel = null;
				inner = null;
				remaining = null;

				/* The class path must correspond to the given pathName. */
				classPath = new ArrayList<String>(pathName);

				/* Try to find the type in the model. */
				while (typeInModel == null) {

					/*
					 * If the found modelElement is an EClass the found type is
					 * the same.
					 */
					if (modelElement instanceof EClass) {
						typeInModel = modelElement;
					}

					/* Else if the found type is a package, break. */
					else if (modelElement instanceof EPackage) {
						break;
					}

					/*
					 * Else remove the last package from the classPath and try
					 * to find the parent package.
					 */
					else {
						int lastIndex;

						lastIndex = classPath.size() - 1;
						remaining = classPath.remove(lastIndex);
						inner = modelElement;
						modelElement = modelElement.eContainer();
					}
				}

				EList<EObject> modelInstanceElems;
				EObject anInstanceElement;
				String anInstanceElemName;
				String path;

				/*
				 * Get one element of the model instance to detect the model
				 * instance source directory.
				 */
				modelInstanceElems = resource.getContents();

				anInstanceElement = modelInstanceElems.get(0);
				anInstanceElemName = anInstanceElement.getClass().getName();

				String rootPackageName = pathName.get(0);
				int rootPackageIndex = anInstanceElemName
						.indexOf(rootPackageName);

				if (rootPackageIndex > 1) {
					path = anInstanceElemName
							.substring(0, rootPackageIndex - 1);
				}

				else {
					path = "";
				}

				/*
				 * Convert the class path into a canonicalName of the instance
				 * class.
				 */
				while (classPath.size() > 0) {

					if (classPath.size() > 1) {

						if (path.length() > 0) {
							path += ".";
						}
						// no else.

						path += classPath.remove(0);
					}

					else {
						path += ".impl." + classPath.remove(0) + "Impl";
					}
				}

				/* Try to find the class. */
				Class<?> clazz = null;

				try {
					clazz = anInstanceElement.getClass().getClassLoader()
							.loadClass(path);
				}

				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				Class<?> typeClass = null;

				if (inner == null) {
					typeClass = clazz;
				}

				else {
					if (inner instanceof EAttribute) {
						try {
							Field field = clazz.getDeclaredField(remaining);
							typeClass = field.getType();
						}

						catch (SecurityException e) {
							e.printStackTrace();
						}

						catch (NoSuchFieldException e) {
							e.printStackTrace();
						}
					}
					// no else.
				}

				OclType type = knownTypes.get(typeClass);

				if (type == null) {
					type = (OclType) Platform.getAdapterManager().getAdapter(
							typeClass, OclType.class);
					knownTypes.put(typeClass, type);
				}
				// no else.

				result = type;
			}
			// no else.
		}
		// no else.

		return result;
	}

	/**
	 * @param pathName
	 *            A given canonical name as a {@link List} of packages.
	 * @return The {@link OclEnumLiteral} to a given canonical name as a
	 *         {@link List} of packages.
	 */
	public OclEnumLiteral findEnumLiteral(List<String> pathName) {

		OclEnumLiteral result;

		EList<EObject> modelPackageElements;
		EObject modelElement;

		Iterator<String> pathIt;
		boolean isRootPackage;

		/* Clone the path name. */
		pathName = new ArrayList<String>(pathName);

		modelPackageElements = modelResource.getContents();
		modelElement = null;

		pathIt = pathName.iterator();
		isRootPackage = true;

		result = null;

		/*
		 * Iterate through the canonical name and try to find the same packages
		 * in the model.
		 */
		while (pathIt.hasNext()) {

			String aPackageName;
			Iterator<EObject> modelElemsIt;

			aPackageName = pathIt.next();
			modelElement = null;

			modelElemsIt = modelPackageElements.iterator();

			/* Iterate through the model elements of the current package. */
			while (modelElemsIt.hasNext() && modelElement == null) {
				ENamedElement aModelObject;

				aModelObject = (ENamedElement) modelElemsIt.next();

				if (aModelObject.getName().equals(aPackageName)) {
					modelElement = aModelObject;
				}

				/* Eventually remove the root package from the path. */
				else if (isRootPackage) {
					if (aPackageName.equals("root")) {
						if (aModelObject.getName().equals(pathIt.next())) {
							modelElement = aModelObject;
							pathName.remove(0);
							pathIt = pathName.iterator();
							pathIt.next();
						}
						// no else.
					}
					// no else.
				}
				// no else.
			}
			// end while.

			if (modelElement == null) {
				System.out.println("Type not found");
				result = null;
			}
			// no else.

			isRootPackage = false;

			/* Get the model elements of the next package in the path. */
			modelPackageElements = modelElement.eContents();
		}

		/* Try to find the class of the found enumeration literal. */
		if (modelElement != null) {

			List<String> classPath;

			classPath = new ArrayList<String>(pathName);

			if (modelElement instanceof EEnumLiteral) {

				EList<EObject> modelInstanceElems;
				EObject aModelInstanceElem;
				String aModelInstanceClassName;

				int rootPackageIndex;
				String path;

				Class<?> clazz;
				String enumLiteral;

				Class<?> typeClass;

				/*
				 * Use an element of the model instance to get the path of the
				 * source directory.
				 */
				modelInstanceElems = resource.getContents();

				aModelInstanceElem = modelInstanceElems.get(0);
				aModelInstanceClassName = aModelInstanceElem.getClass()
						.getName();

				rootPackageIndex = aModelInstanceClassName.indexOf(pathName
						.get(0));

				if (rootPackageIndex > 1) {
					path = aModelInstanceClassName.substring(0,
							rootPackageIndex - 1);
				} else {
					path = "";
				}

				/* The last element of the path is the enumeration literal. */
				enumLiteral = classPath.remove(classPath.size() - 1);

				/* Decode the path into an canonical name. */
				while (classPath.size() > 0) {

					if (path.length() > 0) {
						path += ".";
					}
					// no else.

					path += classPath.remove(0);
				}

				/* Try to load the class. */
				clazz = null;

				try {
					clazz = aModelInstanceElem.getClass().getClassLoader()
							.loadClass(path);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				typeClass = clazz;

				/* If the found type is an enum, try to get the literal. */
				if (typeClass.isEnum()) {

					Object[] enums;
					Object enumLiteralObj;

					enums = typeClass.getEnumConstants();
					enumLiteralObj = null;

					for (Object enumObj : Arrays.asList(enums)) {

						if (enumObj.toString().equals(enumLiteral)) {

							enumLiteralObj = enumObj;
							break;
						}
					}

					result = (OclEnumLiteral) Platform.getAdapterManager()
							.getAdapter(enumLiteralObj, OclEnumLiteral.class);
				}
				// no else.
			}
			// no else.
		}
		// no else.

		return result;
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
					+ findQualifiedPath(modelResource.getContents(), key);
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
		EList<EObject> listMM = modelResource.getContents();
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

		if (temp instanceof EEnumLiteral) {
			t2 = ((EEnumLiteral) temp).getEEnum();
			int last = classPath.size() - 1;
			remaining = classPath.remove(last);
			inner = temp;
		}

		EList<EObject> list = resource.getContents();
		String path = list.get(0).getClass().getName().substring(0,
				list.get(0).getClass().getName().indexOf(pathName.get(0)) - 1);

		while (classPath.size() > 0) {
			// if (classPath.size() > 1)
			path = path + "." + classPath.remove(0);
			// else
			// path = path + ".impl." + classPath.remove(0) + "Impl";
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

		return (OclEnumType) type;
	}

	public List<List<String>> getObjectKinds() {
		return objectKinds;
	}
}
