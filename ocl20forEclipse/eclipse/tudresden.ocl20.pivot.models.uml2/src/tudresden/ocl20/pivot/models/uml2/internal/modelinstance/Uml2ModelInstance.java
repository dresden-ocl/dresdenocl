package tudresden.ocl20.pivot.models.uml2.internal.modelinstance;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Platform;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollectionType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumLiteral;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclTupleType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.StandardlibraryAdapterFactory;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelInstance;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.standardlibrary.java.internal.factory.JavaStandardlibraryAdapterFactory;

public class Uml2ModelInstance extends AbstractModelInstance implements
		IModelInstance {

	/** The {@link IModel} of this {@link IModelInstance}. */
	private IModel myUmlModel;

	/** The root name space of the meta model. */
	private Namespace rootNamespace;

	/** Contains all OclTypes which have already been loaded. */
	private Map<Class<?>, OclType> knownTypes;

	private Map<String, List<IModelObject>> objects;
	private List<IModelObject> allObjects;
	private List<List<String>> objectKinds;

	/** The model instance factory used to create the model instance objects. */
	private IModelInstanceFactory factory;

	private static Map<Integer, Map<String, String>> operationNames = new HashMap<Integer, Map<String, String>>();

	private ClassLoader myClassLoader;

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

	protected static StandardlibraryAdapterFactory DEFAULTSLAF = JavaStandardlibraryAdapterFactory
			.getInstance();

	/**
	 * <p>
	 * Creates and initializes a new {@link Uml2ModelInstance}.
	 * </p>
	 * 
	 * @param providerClass
	 *            The provider class used to get all type instances of this
	 *            model instance.
	 * @param anUML2Model
	 *            The {@link IModel} of this {@link IModelInstance}.s
	 * @throws ModelAccessException
	 */
	@SuppressWarnings("unchecked")
	public Uml2ModelInstance(Class<?> providerClass, IModel anUML2Model)
			throws ModelAccessException {

		this.myUmlModel = anUML2Model;
		this.rootNamespace = anUML2Model.getRootNamespace();

		this.objects = new HashMap<String, List<IModelObject>>();
		this.allObjects = new ArrayList<IModelObject>();
		this.objectKinds = new ArrayList<List<String>>();
		this.knownTypes = new HashMap<Class<?>, OclType>();
		this.instanceName = providerClass.getCanonicalName();
		this.myClassLoader = providerClass.getClassLoader();

		/*
		 * Register the adapters to enable successfull adaption to OclObject in
		 * the standard library.
		 */
		this.currentSlAF = DEFAULTSLAF;
		this.currentSlAF.registerAdapters();

		try {
			Method m = providerClass.getDeclaredMethod("getModelObjects",
					new Class[0]);
			List<Object> objects = (List<Object>) m.invoke(null, new Object[0]);
			addObjects(objects);
		}

		catch (ModelAccessException e) {
			// FIXME Is this okay?
			e.printStackTrace();
		} catch (SecurityException e) {
			this.initFailed(e);
		} catch (NoSuchMethodException e) {
			this.initFailed(e);
		} catch (IllegalArgumentException e) {
			this.initFailed(e);
		} catch (IllegalAccessException e) {
			this.initFailed(e);
		} catch (InvocationTargetException e) {
			this.initFailed(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#findEnumType(java.util.
	 * List)
	 */
	public OclEnumType findEnumType(List<String> pathName) {
		// TODO Auto-generated method stub - Shall this method be implemented?
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#findEnumLiteral(java.util
	 * .List)
	 */
	public OclEnumLiteral findEnumLiteral(List<String> pathName) {
		// FIXME This method must be implemented!

		OclEnumLiteral result;
		Type aModelsEnum;

		List<String> anEnumsPath;
		String anEnumLiteralsName;

		result = null;

		/* Split the path into the enum's type path and the name of the literal. */
		anEnumsPath = new ArrayList<String>(pathName);
		anEnumLiteralsName = anEnumsPath.remove(anEnumsPath.size() - 1);

		/* Try to find the enum's type from the model. */
		if (this.rootNamespace.getName().equals(anEnumsPath.get(0))) {

			List<String> aTypesPath;

			/* Clone the path list because it will be modified. */
			aTypesPath = new ArrayList<String>(anEnumsPath);
			aTypesPath.remove(0);

			aModelsEnum = this.findTypeInNamespace(aTypesPath,
					this.rootNamespace);
		}

		else {
			aModelsEnum = null;
		}

		/*
		 * Check if the enum's type has been found and if the found type is an
		 * enumeration.
		 */
		if (aModelsEnum != null && aModelsEnum instanceof Enumeration) {

			Class<?> theEnumerationsClass;

			IModelObject aModelObject;
			Uml2ModelObject anUml2ModelObject;

			ClassLoader modelClassLoader;

			String anEnumsCanonicalName;

			/* Convert the enum's package list into a canonical name. */
			anEnumsCanonicalName = null;

			for (String aPackageName : anEnumsPath) {

				if (aPackageName.equals("root") && anEnumsCanonicalName == null) {
					/* Do nothing, ignore the root package. */
				}

				else if (anEnumsCanonicalName == null) {
					anEnumsCanonicalName = aPackageName;
				}

				else {
					anEnumsCanonicalName += "." + aPackageName;
				}
			}

			/* Get the model instances' class loader. */
			aModelObject = this.allObjects.get(0);
			anUml2ModelObject = (Uml2ModelObject) aModelObject;

			modelClassLoader = anUml2ModelObject.object.getClass()
					.getClassLoader();

			/* Try to load the enumeration's class. */
			try {
				theEnumerationsClass = modelClassLoader
						.loadClass(anEnumsCanonicalName);
			}

			catch (ClassNotFoundException e) {
				theEnumerationsClass = null;
			}

			/*
			 * If the found type is an enumeration, try to get the needed
			 * literal.
			 */
			if (theEnumerationsClass.isEnum()) {

				Object[] aClassEnums;
				Object aEnumLiteralObject;

				/* Get all enumeration literals of the found class. */
				aClassEnums = theEnumerationsClass.getEnumConstants();
				aEnumLiteralObject = null;

				/* Search for the needed literal. */
				for (Object anEnumerationObject : Arrays.asList(aClassEnums)) {

					String anEnumObjectsName;

					anEnumObjectsName = anEnumerationObject.toString();

					if (anEnumObjectsName.equals(anEnumLiteralsName)) {

						aEnumLiteralObject = anEnumerationObject;
						break;
					}
					// no else.
				}

				result = (OclEnumLiteral) Platform.getAdapterManager()
						.getAdapter(aEnumLiteralObject, OclEnumLiteral.class);
			}
			// no else.
		}
		// no else.

		return result;
	}

	/**
	 * @param pathName
	 *            A {@link List} of {@link String}s describing the canonical
	 *            name of an {@link OclType} from the root package to class
	 *            name).
	 * @return The {@link OclType} of a given Type (as a {@link List} of
	 *         {@link String}s describing its canonical name from the root
	 *         package to class name).
	 */
	public OclType findType(List<String> pathName) {
		Class<?> typeClass;
		OclType result;
		ArrayList<String> packagelist;

		typeClass = null;
		result = null;
		packagelist = new ArrayList<String>(pathName);

		/* Check if the given pathName leads to a type in the model. */
		if (this.isObjectOfModel(pathName)) {

			String path;
			Iterator<String> pathIterator;

			/* Eventually remove the rootPackage's name from the pathName. */
			if (this.rootNamespace.getName().equals(packagelist.get(0))) {
				packagelist.remove(0);
			}
			// no else.

			path = packagelist.remove(0);
			pathIterator = packagelist.iterator();

			while (pathIterator.hasNext()) {
				path = path + "." + pathIterator.next();
			}

			/* Try to load the class for the given path. */
			try {
				typeClass = this.myClassLoader.loadClass(path);

				/* Check if the loaded types is already known. */
				result = this.knownTypes.get(typeClass);

				/* Check if the loaded types is already known; else load him. */
				if (result == null) {
					result = (OclType) Platform.getAdapterManager().getAdapter(
							typeClass, OclType.class);
					this.knownTypes.put(typeClass, result);
				}
				// no else.
			}

			catch (ClassNotFoundException e) {
				/* Do nothing; return null. */
			}

		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#getCollectionType(tudresden
	 * .ocl20.pivot.modelbus.util.OclCollectionTypeKind,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType)
	 */
	public OclCollectionType getCollectionType(OclCollectionTypeKind kind,
			OclType elementType) {
		// TODO Auto-generated method stub - Shall this method be implemented?
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getFactory()
	 */
	public IModelInstanceFactory getFactory() {

		if (this.factory == null) {
			this.factory = new Uml2ModelInstanceFactory();
		}
		// no else.

		return this.factory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getObjects()
	 */
	public List<IModelObject> getObjects() {
		return this.allObjects;
	}

	/**
	 * @return A {@link List} containing {@link List}s of {@link String}s
	 *         representing the canonical names of all Object kinds which have
	 *         instances in this model instance.
	 */
	public List<List<String>> getObjectKinds() {
		return this.objectKinds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#getObjectsOfKind(java.util
	 * .List)
	 */
	public List<IModelObject> getObjectsOfKind(List<String> typePath) {

		List<IModelObject> result;
		Iterator<String> typePathIterator;

		if (typePath != null) {

			List<String> path;
			Iterator<String> pathIterator;

			String current;
			String type;

			path = new ArrayList<String>();
			typePathIterator = typePath.iterator();

			/* Convert the type path and remove elements containing '('. */
			while (typePathIterator.hasNext()) {

				current = typePathIterator.next();

				if (!current.contains("(")) {
					path.add(current);
				} else {
					break;
				}
			}

			/*
			 * Check if the path is an object of the model and eventually remove
			 * the top package from the path.
			 */
			if (!this.isObjectOfModel(path)) {
				while (path.size() > 0
						&& !path.get(0).equals(this.rootNamespace.getName())) {
					path.remove(0);
				}
			}
			// no else.

			if (path.get(0).equals(this.rootNamespace.getName())) {
				path.remove(0);
			}
			// no else.

			type = path.remove(0);
			pathIterator = path.iterator();

			/* Transform the path into a canonical name. */
			while (pathIterator.hasNext()) {
				type = type + "." + pathIterator.next();
			}

			/* Get all objects of the transformed type. */
			result = this.objects.get(type);

			if (result == null) {
				result = new ArrayList<IModelObject>();
			}
			// no else.
		}

		else {
			throw new IllegalArgumentException("TypePath must not be null");
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#getTupleType(java.lang.
	 * String[], tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType[])
	 */
	public OclTupleType getTupleType(String[] partNames, OclType[] partTypes) {
		// TODO Auto-generated method stub - Shall this method be implemented?
		return null;
	}

	/**
	 * <p>
	 * Adds a given {@link Object} to the objects {@link List}.
	 * </p>
	 * 
	 * @param anObject
	 *            The {@link Object} which shall be added to the objects
	 *            {@link List}.
	 * @throws ModelAccessException
	 *             Thrown, if the given {@link Object} does not math to the
	 *             model.
	 */
	private void addObject(Object anObject) throws ModelAccessException {

		String[] path;
		List<String> pathList;

		String key;
		List<IModelObject> objectList;

		Uml2ModelObject newObject;
		List<String> qualifiedName;

		/* Get the path of the given Object. */
		path = anObject.getClass().getCanonicalName().split("[.]");
		pathList = new ArrayList<String>(Arrays.asList(path));

		/* Check if the given Object belongs to the model. */
		if (!isObjectOfModel(pathList)) {
			String msg;

			msg = "ModelInstance doesn't match to model. ";
			msg += "Type for " + anObject.getClass();
			msg += " not found in model " + myUmlModel.getDisplayName();

			throw new ModelAccessException(msg);
		}
		// no else.

		key = anObject.getClass().getCanonicalName();

		/* Get objects to the given key. */
		objectList = this.objects.get(key);

		/* Eventually create a new object list. */
		if (objectList == null) {
			objectList = new ArrayList<IModelObject>();
		}
		// no else.

		qualifiedName = Arrays
				.asList((this.rootNamespace.getName() + "." + key).split("[.]"));

		newObject = new Uml2ModelObject(anObject, key, qualifiedName);
		objectList.add(newObject);

		/* Add the new created Object to the object list. */
		this.objects.put(key, objectList);
		this.allObjects.add(newObject);

		/* Eventually add the qualified name to the object kinds. */
		if (!this.objectKinds.contains(qualifiedName)) {
			this.objectKinds.add(qualifiedName);
		}
		// no else.
	}

	/**
	 * <p>
	 * Adds a {@link List} of given {@link Object}s to the objects {@link List}.
	 * </p>
	 * 
	 * @param objects
	 *            A {@link List} of {@link Object}s which shall be added to the
	 *            objects {@link List}.
	 * @throws ModelAccessException
	 *             Thrown, if the given {@link Object} does not math to the
	 *             model.
	 */
	private void addObjects(List<Object> objects) throws ModelAccessException {

		Iterator<Object> objectIterator;

		objectIterator = objects.iterator();

		while (objectIterator.hasNext()) {
			this.addObject(objectIterator.next());
		}
	}

	/**
	 * <p>
	 * A helper methods used to find the {@link Type} to a given path in a given
	 * {@link Namespace} of this {@link Uml2ModelInstance}.
	 * </p>
	 * 
	 * @param aPackagePath
	 *            The path of the {@link Type} which shall be searched for.
	 * @param aNamespace
	 *            The {@link Namespace} in which shall be searched for the
	 *            {@link Type}.
	 * @return the found {@link Type} or null.
	 * 
	 */
	private Type findTypeInNamespace(List<String> aPackagePath,
			Namespace aNamespace) {
	
		Type result;
	
		result = null;
	
		/* Check if the searched type is located in a nested name space. */
		if (aPackagePath.size() > 1) {
	
			List<Namespace> nestedNamespaces;
			String currentPackage;
	
			nestedNamespaces = aNamespace.getNestedNamespace();
			currentPackage = aPackagePath.remove(0);
	
			/* Check if any nested name space matches the actual package's name. */
			for (Namespace aNestedNamespace : nestedNamespaces) {
	
				String aNestedNamespacesName;
	
				aNestedNamespacesName = aNestedNamespace.getName();
	
				if (aNestedNamespacesName.equals(currentPackage)) {
	
					result = this.findTypeInNamespace(aPackagePath,
							aNestedNamespace);
					break;
				}
				// no else.
			}
		}
	
		/* Else search for the type in this name space. */
		else {
	
			List<Type> ownedTypes;
	
			ownedTypes = aNamespace.getOwnedType();
	
			/* Check if any type matches the type's name. */
			for (Type aType : ownedTypes) {
	
				String aTypesName;
	
				aTypesName = aType.getName();
	
				if (aTypesName.equals(aPackagePath.get(0))) {
					result = aType;
					break;
				}
				// no else.
			}
			// end for.
		}
		// end else.
	
		return result;
	}

	/**
	 * <p>
	 * Used to throw an Exception if an Exception occurs during initialization.
	 * </p>
	 */
	private void initFailed(Exception e) throws ModelAccessException {

		String msg;

		msg = "Problem during retrieving of ModelObjects. ";
		msg += e.getMessage();

		throw new ModelAccessException(msg, e);
	}

	/**
	 * <p>
	 * Checks if a given model object (as a {@link List} of {@link String}s
	 * representing its canonical name) is object of the given model.
	 * </p>
	 * 
	 * @param pathName
	 *            The canonical name of the model object as a {@link List} of
	 *            {@link String}s.
	 * @return True if the given object is an object of the given model.
	 */
	private boolean isObjectOfModel(List<String> pathName) {

		boolean result;
		List<String> modifiedPathname;

		result = true;

		modifiedPathname = new ArrayList<String>(pathName);

		/* If the rootNamespace is contained in the pathName, remove him. */
		if (this.rootNamespace.getName().equals(modifiedPathname.get(0))) {
			modifiedPathname.remove(0);
		}
		// no else.

		if (this.rootNamespace != null) {

			Namespace aPackage;
			int index;

			index = 0;
			aPackage = this.rootNamespace;

			while (result && index < modifiedPathname.size()) {

				String packageName;

				result = false;

				packageName = modifiedPathname.get(index);

				if (index < modifiedPathname.size() - 1) {

					List<Namespace> nestedNamespaces;

					nestedNamespaces = aPackage.getNestedNamespace();

					/* Search the next sub package. */
					for (Namespace aNamespace : nestedNamespaces) {

						if (aNamespace.getName().equals(packageName)) {
							aPackage = aNamespace;
							result = true;
							break;
						}
					}
				}

				else {
					List<Type> ownedTypes;

					ownedTypes = aPackage.getOwnedType();

					/* Search the next sub package. */
					for (Type aType : ownedTypes) {

						if (aType.getName().equals(packageName)) {
							result = true;
							break;
						}
					}
				}

				index++;
			}
		}

		else {
			result = false;
		}

		return result;
	}
}
