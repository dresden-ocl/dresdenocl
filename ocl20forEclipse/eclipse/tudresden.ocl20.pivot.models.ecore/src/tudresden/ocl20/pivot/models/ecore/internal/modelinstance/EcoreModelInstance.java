/*
Copyright (C) 2007-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Ecore Meta Model of Dresden OCL2 for Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
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
import org.eclipse.emf.common.notify.AdapterFactory;
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
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelInstance;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Type;
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

	/**
	 * The {@link AdapterFactory} used to adapt the model instance to the
	 * standard library.
	 */
	protected static StandardlibraryAdapterFactory DEFAULTSLAF = JavaStandardlibraryAdapterFactory
			.getInstance();
	/**
	 * The {@link Resource} representing the {@link IMetaModel} of this
	 * {@link IModelInstance}.
	 */
	private Resource myMetaModelResource;

	/** The {@link IModel} of this {@link IModelInstance}. */
	private IModel myModel;

	/**
	 * The {@link Resource} representing the {@link IModel} of this
	 * {@link IModelInstance}.
	 */
	private Resource myModelResource;

	/**
	 * Contains the qualified names of all {@link Type} to which
	 * {@link IModelObject}s belong in this {@link IModelInstance}.
	 */
	private List<List<String>> myObjectKinds;

	/** Contains all {@link IModelObject}s of this {@link EcoreModelInstance}. */
	private List<IModelObject> myObjects;

	/**
	 * Contains the {@link IModelObject}s of this {@link EcoreModelInstance}
	 * stored by their qualified name.
	 */
	private Map<String, List<IModelObject>> myObjectsByName;

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

		modelPackageElements = myMetaModelResource.getContents();
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
				modelInstanceElems = myModelResource.getContents();

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
			modelPackageElements = myMetaModelResource.getContents();
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
				modelInstanceElems = myModelResource.getContents();

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
	 * <p>
	 * A helper method which creates the {@link EcoreModelObject}s for a given
	 * {@link List} of {@link EObject}s.
	 * </p>
	 * 
	 * @param eObjects
	 *            The {@link EObject}s {@link EcoreModelObject}s shall be
	 *            created for.
	 */
	private void createObjects(List<EObject> eObjects) {

		/* Iterate through the given list of EObjects. */
		for (EObject currentObject : eObjects) {

			EcoreModelObject aModelObject;

			String anEClassName;

			String aTypesQualifiedName;
			String aQualifiedName;

			List<String> aTypesQualifiedNameList;
			List<String> aQualifiedNameList;

			List<IModelObject> allObjectsOfSameType;

			Type aType;

			/*
			 * Eventually create model objects for all child objects of this
			 * EObject.
			 */
			this.createObjects(currentObject.eContents());

			/* Get the EClass of this EObject and its qualified name. */
			anEClassName = currentObject.eClass().getName();

			aTypesQualifiedName = findQualifiedPath(this.myMetaModelResource
					.getContents(), anEClassName);
			aQualifiedName = "root::" + aTypesQualifiedName;

			aTypesQualifiedNameList = Arrays.asList(aTypesQualifiedName
					.split("::"));
			aQualifiedNameList = Arrays.asList(aQualifiedName.split("::"));

			/*
			 * Get all objects of the same type in this instance and eventually
			 * initialize this list.
			 */
			allObjectsOfSameType = this.myObjectsByName.get(aQualifiedName);

			if (allObjectsOfSameType == null) {
				allObjectsOfSameType = new ArrayList<IModelObject>();
			}
			// no else.

			/* Try to get the type of the new IModelObject. */
			try {
				aType = this.myModel.findType(aTypesQualifiedNameList);
			}

			catch (ModelAccessException e) {
				aType = null;
			}

			aModelObject = new EcoreModelObject(currentObject, aType);

			allObjectsOfSameType.add(aModelObject);

			/* Add the created model object to the lists of this model instance. */
			this.myObjects.add(aModelObject);
			this.myObjectsByName.put(aQualifiedName, allObjectsOfSameType);

			/* Eventually add the types name to the list of the object kinds. */
			if (!this.myObjectKinds.contains(aQualifiedNameList)) {
				this.myObjectKinds.add(aQualifiedNameList);
			}
			// no else.
		}
		// end for.
	}
	
	// FIXME Continue refactoring here.

	private IModelInstanceFactory modelInstanceFactory;

	private Map<Class<?>, OclType> knownTypes;

	public EcoreModelInstance(Resource resource, Resource mmResource,
			IModel aModel) {
		this.myModelResource = resource;
		instanceName = resource.toString();
		if (!resource.isLoaded()) {
			try {
				resource.load(null);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		this.myMetaModelResource = mmResource;
		if (!mmResource.isLoaded()) {
			try {
				mmResource.load(null);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		this.knownTypes = new HashMap<Class<?>, OclType>();

		this.currentSlAF = DEFAULTSLAF;
		myObjectsByName = new HashMap<String, List<IModelObject>>();
		myObjects = new ArrayList<IModelObject>();
		myObjectKinds = new ArrayList<List<String>>();

		this.myModel = aModel;

		this.createObjects(resource.getContents());
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

	public List<IModelObject> getObjects() {
		return myObjects;
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
		return myObjectsByName.get(type);
	}

	public OclEnumType findEnumType(List<String> pathName) {
		pathName = new ArrayList<String>(pathName);
		EList<EObject> listMM = myMetaModelResource.getContents();
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

		EList<EObject> list = myModelResource.getContents();
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
		return myObjectKinds;
	}
}
