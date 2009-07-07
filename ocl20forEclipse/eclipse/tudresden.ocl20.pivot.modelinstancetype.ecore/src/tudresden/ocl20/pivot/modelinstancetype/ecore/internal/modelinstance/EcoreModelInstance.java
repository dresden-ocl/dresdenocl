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
package tudresden.ocl20.pivot.modelinstancetype.ecore.internal.modelinstance;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumLiteral;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.StandardlibraryAdapterFactory;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelInstance;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.standardlibrary.java.internal.factory.JavaStandardlibraryAdapterFactory;

/**
 * <p>
 * Represents instances of EMF Ecore models.
 * </p>
 * 
 * @author Claas Wilke
 */
public class EcoreModelInstance extends AbstractModelInstance implements
		IModelInstance {

	/**
	 * The {@link AdapterFactory} used to adapt the model instance to the standard
	 * library.
	 */
	protected static StandardlibraryAdapterFactory DEFAULTSLAF =
			JavaStandardlibraryAdapterFactory.getInstance();

	/**
	 * The {@link Resource} representing the {@link IModel} of this
	 * {@link IModelInstance}.
	 */
	private Resource myModelInstanceResource;

	/**
	 * <p>
	 * Creates a new Ecore EMF instance.
	 * </p>
	 * 
	 * @param resource
	 *          The XML resource used to load the {@link IModelInstance}.
	 * @param model
	 *          The {@link IModel} belonging to the {@link IModelInstance}.
	 * @throws ModelAccessException
	 */
	public EcoreModelInstance(Resource resource, IModel model)
			throws ModelAccessException {

		this.myModelInstanceResource = resource;
		this.myInstanceName = resource.toString();

		/* Eventually load the resource. */
		if (!resource.isLoaded()) {

			try {
				resource.load(null);
			}

			catch (Exception e) {
				String msg;

				msg = "Problem during retrieving of ModelObjects. Reason: ";
				msg += e.getMessage();

				throw new ModelAccessException(msg, e);
			}
		}

		this.myKnownTypes = new HashMap<Class<?>, OclType>();
		this.myCurrentSlAF = DEFAULTSLAF;

		this.allMyObjectsByType = new HashMap<String, List<IModelObject>>();
		this.allMyObjects = new ArrayList<IModelObject>();
		this.allMyObjectKinds = new ArrayList<List<String>>();

		this.myModel = model;
		this.myRootNamespace = model.getRootNamespace();

		this.createObjects(resource.getContents());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#findEnumLiteral(java.util
	 * .List)
	 */
	public OclEnumLiteral findEnumLiteral(List<String> pathName) {

		OclEnumLiteral result;

		Type modelType;
		String literalName;

		result = null;

		/* Clone the path name. */
		pathName = new ArrayList<String>(pathName);

		/* Remove the last element. Its the literal's name. */
		literalName = pathName.remove(pathName.size() - 1);

		/* Try to find the related type in the model. */
		modelType = this.findTypeInModel(pathName);

		/* Try to find the class of the found enumeration type. */
		if (modelType != null) {

			if (modelType instanceof Enumeration) {

				EList<EObject> modelInstanceElems;
				EObject aModelInstanceElem;
				String aModelInstanceClassName;

				int rootPackageIndex;
				String path;

				Class<?> clazz;
				Class<?> typeClass;

				/*
				 * Use an element of the model instance to get the path of the source
				 * directory.
				 */
				modelInstanceElems = myModelInstanceResource.getContents();

				aModelInstanceElem = modelInstanceElems.get(0);
				aModelInstanceClassName = aModelInstanceElem.getClass().getName();

				rootPackageIndex = aModelInstanceClassName.indexOf(pathName.get(0));

				if (rootPackageIndex > 1) {
					path = aModelInstanceClassName.substring(0, rootPackageIndex - 1);
				}
				else {
					path = "";
				}

				/* Eventually remove the root package from the path. */
				if (pathName.size() > 0
						&& pathName.get(0).equals(IModelBusConstants.ROOT_PACKAGE_NAME)) {
					pathName.remove(0);
				}
				// no else.

				/* Decode the path into an canonical name. */
				while (pathName.size() > 0) {

					if (path.length() > 0) {
						path += ".";
					}
					// no else.

					path += pathName.remove(0);
				}

				/* Try to load the class. */
				clazz = null;

				try {
					clazz =
							aModelInstanceElem.getClass().getClassLoader().loadClass(path);
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

						if (enumObj.toString().equals(literalName)) {

							enumLiteralObj = enumObj;
							break;
						}
					}

					result =
							(OclEnumLiteral) Platform.getAdapterManager().getAdapter(
									enumLiteralObj, OclEnumLiteral.class);
				}
				// no else.
			}
			// no else.
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#findEnumType(java.util.
	 * List)
	 */
	public OclEnumType findEnumType(List<String> pathName) {

		OclEnumType result;

		Type modelType;

		result = null;

		/* Clone the path name. */
		pathName = new ArrayList<String>(pathName);

		/* Try to find the related type in the model. */
		modelType = this.findTypeInModel(pathName);

		/* Try to find the class of the found enumeration type. */
		if (modelType != null) {

			if (modelType instanceof Enumeration) {

				EList<EObject> modelInstanceElems;
				EObject aModelInstanceElem;
				String aModelInstanceClassName;

				int rootPackageIndex;
				String path;

				Class<?> clazz;
				Class<?> typeClass;

				/*
				 * Use an element of the model instance to get the path of the source
				 * directory.
				 */
				modelInstanceElems = myModelInstanceResource.getContents();

				aModelInstanceElem = modelInstanceElems.get(0);
				aModelInstanceClassName = aModelInstanceElem.getClass().getName();

				rootPackageIndex = aModelInstanceClassName.indexOf(pathName.get(0));

				if (rootPackageIndex > 1) {
					path = aModelInstanceClassName.substring(0, rootPackageIndex - 1);
				}
				else {
					path = "";
				}

				/* Eventually remove the root package from the path. */
				if (pathName.size() > 0
						&& pathName.get(0).equals(IModelBusConstants.ROOT_PACKAGE_NAME)) {
					pathName.remove(0);
				}
				// no else.

				/* Decode the path into an canonical name. */
				while (pathName.size() > 0) {

					if (path.length() > 0) {
						path += ".";
					}
					// no else.

					path += pathName.remove(0);
				}

				/* Try to load the class. */
				clazz = null;

				try {
					clazz =
							aModelInstanceElem.getClass().getClassLoader().loadClass(path);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				typeClass = clazz;

				/* If the found type is an enum, adapt it. */
				if (typeClass.isEnum()) {

					result =
							(OclEnumType) Platform.getAdapterManager().getAdapter(clazz,
									OclEnumType.class);
				}
				// no else.
			}
			// no else.
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Returns the {@link OclType} to a give path name.
	 * </p>
	 * 
	 * @param pathName
	 *          A given canonical name as a {@link List} of packages.
	 * @return The {@link OclType} to a given canonical name as a {@link List} of
	 *         packages.
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

			Type modelType;

			modelType = this.findTypeInModel(pathName);

			/* Try to load the class of the found model element. */
			if (modelType != null) {

				List<String> classPath;

				EList<EObject> modelInstanceElems;
				EObject anInstanceElement;
				String anInstanceElemName;

				String path;
				String rootPackageName;
				int rootPackageIndex;

				Class<?> typeClass;
				EObject inner;
				String remaining;

				/* The class path must correspond to the given pathName. */
				classPath = new ArrayList<String>(pathName);

				/*
				 * Get one element of the model instance to detect the model instance
				 * source directory.
				 */
				modelInstanceElems = myModelInstanceResource.getContents();

				anInstanceElement = modelInstanceElems.get(0);
				anInstanceElemName = anInstanceElement.getClass().getName();

				/*
				 * Eventually remove a part from the path before the root package.
				 */
				rootPackageName = pathName.get(0);
				rootPackageIndex = anInstanceElemName.indexOf(rootPackageName);

				if (rootPackageIndex > 1) {
					path = anInstanceElemName.substring(0, rootPackageIndex - 1);
				}

				else {
					path = "";
				}

				/* Eventually remove the root package from the path. */
				if (classPath.size() > 0
						&& classPath.get(0).equals(IModelBusConstants.ROOT_PACKAGE_NAME)) {
					classPath.remove(0);
				}
				// no else.

				/*
				 * Convert the class path into a canonicalName of the instance class.
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
					clazz = anInstanceElement.getClass().getClassLoader().loadClass(path);
				}

				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				typeClass = null;
				inner = null;
				remaining = null;

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

				OclType type = myKnownTypes.get(typeClass);

				if (type == null) {
					type =
							(OclType) Platform.getAdapterManager().getAdapter(typeClass,
									OclType.class);
					myKnownTypes.put(typeClass, type);
				}
				// no else.

				result = type;
			}
			// no else.
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getFactory()
	 */
	public IModelInstanceFactory getFactory() {

		if (this.myModelInstanceFactory == null) {
			this.myModelInstanceFactory = new EcoreModelInstanceFactory();
		}
		// no else.

		return this.myModelInstanceFactory;
	}

	/**
	 * <p>
	 * A helper method which creates the {@link EcoreModelObject}s for a given
	 * {@link List} of {@link EObject}s.
	 * </p>
	 * 
	 * @param eObjects
	 *          The {@link EObject}s {@link EcoreModelObject}s shall be created
	 *          for.
	 */
	private void createObjects(List<EObject> eObjects) {

		/* Iterate through the given list of EObjects. */
		for (EObject currentObject : eObjects) {

			EcoreModelObject aModelObject;

			String anEClassName;
			String aQualifiedName;

			List<String> aTypesQualifiedNameList;
			List<String> aQualifiedNameList;

			List<IModelObject> allObjectsOfSameType;

			Type aType;

			/* Eventually create model objects for all children of this EObject. */
			if (currentObject.eContents().size() > 0) {
				this.createObjects(currentObject.eContents());
			}
			// no else.

			/* Get the EClass of this EObject and its qualified name. */
			anEClassName = currentObject.eClass().getName();

			aQualifiedName = this.findQualifiedPath(anEClassName);

			aTypesQualifiedNameList = Arrays.asList(aQualifiedName.split("\\."));
			aQualifiedNameList = Arrays.asList(aQualifiedName.split("\\."));

			/*
			 * Get all objects of the same type in this instance and eventually
			 * initialize this list.
			 */
			allObjectsOfSameType = this.allMyObjectsByType.get(aQualifiedName);

			if (allObjectsOfSameType == null) {
				allObjectsOfSameType = new ArrayList<IModelObject>();
			}
			// no else.

			/* Try to get the type of the new IModelObject. */
			aType = this.findTypeInModel(aTypesQualifiedNameList);

			aModelObject = new EcoreModelObject(currentObject, aType);

			allObjectsOfSameType.add(aModelObject);

			/* Add the created model object to the lists of this model instance. */
			this.allMyObjects.add(aModelObject);
			this.allMyObjectsByType.put(aQualifiedName, allObjectsOfSameType);

			/* Eventually add the types name to the list of the object kinds. */
			if (!this.allMyObjectKinds.contains(aQualifiedNameList)) {
				this.allMyObjectKinds.add(aQualifiedNameList);
			}
			// no else.
		}
		// end for.
	}

	/**
	 * <p>
	 * A helper methods used to find the {@link Type} to a given path in the
	 * {@link IModel} of this {@link JavaModelInstance}.
	 * </p>
	 * 
	 * @param aPackagePath
	 *          The path of the {@link Type} which shall be searched for.
	 * @return the found {@link Type} or null.
	 * 
	 */
	private Type findTypeInModel(List<String> aPackagePath) {

		Type result;
		List<String> packagePath;

		/* Clone the packagePath; */
		packagePath = new ArrayList<String>(aPackagePath);

		/* Remove the 'root' package. */
		if (packagePath.get(0).equals("root")) {
			packagePath.remove(0);
		}
		// no else.

		try {
			result =
					this
							.findTypeInNamespace(packagePath, this.myModel.getRootNamespace());
		}

		catch (ModelAccessException e) {
			result = null;
		}

		return result;
	}

	/**
	 * <p>
	 * Searches for a given type's name in the {@link IModel} and returns its
	 * fully qualified name if the type has been found. Else <code>null</code>
	 * will be returned.
	 * </p>
	 * 
	 * @param aTypesName
	 *          Whose name shall be found.
	 * @return The fully qualified name.
	 */
	private String findQualifiedPath(String aTypesName) {

		String result;
		List<String> pathList;

		pathList = new ArrayList<String>();
		pathList.add(aTypesName);

		try {
			result = this.myModel.findType(pathList).getQualifiedName();

			if (result.startsWith("root::")) {
				result = result.substring(6);
			}
			// no else

			result = result.replaceAll("::", ".");
		}

		catch (ModelAccessException e1) {
			result = null;
		}

		return result;
	}
}