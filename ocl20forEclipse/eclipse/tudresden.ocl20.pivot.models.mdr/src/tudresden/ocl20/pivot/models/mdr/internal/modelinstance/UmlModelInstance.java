/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the UML Meta Model of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.models.mdr.internal.modelinstance;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.AdapterFactory;

import tudresden.ocl20.core.jmi.uml15.impl.modelmanagement.ModelHelper;
import tudresden.ocl20.core.jmi.uml15.modelmanagement.Model;
import tudresden.ocl20.core.jmi.uml15.uml15.Uml15Package;
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
 * Represents instance of {@link IModel}s defined using the UML meta model.
 * </p>
 * 
 * @author Claas Wilke
 */
public class UmlModelInstance extends AbstractModelInstance implements
		IModelInstance {

	/**
	 * The {@link AdapterFactory} used to adapt the model instance to the
	 * standard library.
	 */
	protected static StandardlibraryAdapterFactory DEFAULTSLAF = JavaStandardlibraryAdapterFactory
			.getInstance();

	/**
	 * Contains the canonical names of all types contained in this model
	 * instance.
	 */
	private List<List<String>> allMyObjectKinds;

	/** Contains all {@link Object}s of this model instance. */
	private List<IModelObject> allMyObjects;

	/**
	 * Contains all {@link Object}s of this model instance ordered by their
	 * type's name.
	 */
	private Map<String, List<IModelObject>> allMyObjectsByName;

	/** Contains all OclTypes which have already been loaded. */
	private Map<Class<?>, OclType> myKnownTypes;

	/** The {@link IModel} of which this {@link IModelInstance} is an instance. */
	private IModel myModel;

	/**
	 * The {@link Uml15Package} representing the model of this
	 * {@link IModelInstance}.
	 */
	private Uml15Package myUMLPackage;

	/** The UML {@link Model} of this {@link IModelInstance}. */
	private Model myUMLModel;

	/** The {@link IModelInstanceFactory} of this {@link IModelInstance}. */
	private IModelInstanceFactory myModelInstanceFactory;

	/**
	 * <p>
	 * Creates a new {@link UmlModelInstance}.
	 * </p>
	 * 
	 * @param providerClass
	 *            The provider class providing the Model Objects of this
	 *            {@link IModelInstance}.
	 * @param umlPackage
	 *            The {@link Uml15Package} representing the model of this
	 *            {@link IModelInstance}.
	 * @param aModel
	 *            The {@link IModel} of this {@link IModelInstance}.
	 * @throws ModelAccessException
	 *             Thrown, if an error during initialization of this
	 *             {@link IModelInstance} occurs.
	 */
	@SuppressWarnings("unchecked")
	public UmlModelInstance(Class<?> providerClass, Uml15Package umlPackage,
			IModel aModel) throws ModelAccessException {

		/* Initialize the lists used to optimize the initialization. */
		this.allMyObjectsByName = new HashMap<String, List<IModelObject>>();
		this.allMyObjects = new ArrayList<IModelObject>();
		this.allMyObjectKinds = new ArrayList<List<String>>();
		this.myKnownTypes = new HashMap<Class<?>, OclType>();

		this.instanceName = providerClass.getCanonicalName();
		this.myUMLPackage = umlPackage;
		this.myUMLModel = ModelHelper.getInstance(umlPackage).getTopPackage();

		this.currentSlAF = DEFAULTSLAF;

		this.myModel = aModel;

		/* Try to load the model objects. */
		try {
			Method providerMethod;
			List<Object> adaptedObjects;

			/* Get the provider method. */
			providerMethod = providerClass.getDeclaredMethod("getModelObjects",
					new Class[0]);

			/* Get the Objects which shall be adapted. */
			adaptedObjects = (List<Object>) providerMethod.invoke(null,
					new Object[0]);

			/* Adapt the objects and add them to this IModelInstance. */
			this.addObjects(adaptedObjects);
		}

		catch (Exception e) {
			String msg;

			msg = "Problem retrieving Model Objects.";

			throw new ModelAccessException(msg, e);
		}
	}

	/**
	 * <p>
	 * A helper method to adapt and add a given {@link Object} as
	 * {@link IModelObject} to this {@link IModelInstance}.
	 * </p>
	 * 
	 * @param anObject
	 * @throws ModelAccessException
	 *             Thrown, if an error occurs.
	 */
	private void addObject(Object anObject) throws ModelAccessException {

		UmlModelObject newObject;
		Type anObjectsType;

		List<String> anObjectsTypeName;
		String anObjectsCanonicalName;

		List<IModelObject> allObjectsOfSameType;
		List<String> anObjectsQualifiedName;

		/* Get the name of the object (and its type) as a list. */
		anObjectsTypeName = Arrays.asList(anObject.getClass()
				.getCanonicalName().split("[.]"));

		/* Check if the object's type can be found in the model. */
		if (!this.isObjectOfModel(anObjectsTypeName)) {
			String msg;

			msg = "ModelInstance doesn't match to model. ";
			msg += "Type for " + anObject.getClass();
			msg += " was not found in model " + this.myUMLPackage.toString();

			throw new ModelAccessException(msg);
		}
		// no else.

		anObjectsCanonicalName = anObject.getClass().getCanonicalName();

		/*
		 * Get the list with all objects of the same type and eventually
		 * initialize it.
		 */
		allObjectsOfSameType = this.allMyObjectsByName
				.get(anObjectsCanonicalName);

		if (allObjectsOfSameType == null) {
			allObjectsOfSameType = new ArrayList<IModelObject>();
		}
		// no else.

		anObjectsQualifiedName = Arrays
				.asList((myUMLModel.getName() + "." + anObjectsCanonicalName)
						.split("[.]"));

		/* Get the type of the object. */
		anObjectsType = this.myModel.findType(anObjectsTypeName);
		
		/* Create the new object. */
		newObject = new UmlModelObject(anObject, anObjectsType);

		/* Add the object to the lists of this model instance. */
		allObjectsOfSameType.add(newObject);

		this.allMyObjectsByName.put(anObjectsCanonicalName,
				allObjectsOfSameType);
		this.allMyObjects.add(newObject);

		/*
		 * Eventually add the type's name of the object to the list of object
		 * kinds.
		 */
		if (!this.allMyObjectKinds.contains(anObjectsQualifiedName)) {
			this.allMyObjectKinds.add(anObjectsQualifiedName);
		}
		// no else.
	}

	public OclType findType(List<String> pathName) {
		Class typeClass = null;
		OclType type = null;
		ArrayList<String> list = new ArrayList<String>(pathName);

		if (isObjectOfModel(pathName)) {
			if (myUMLModel.getName().equals(list.get(0)))
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

			type = myKnownTypes.get(typeClass);

			if (type == null) {
				type = (OclType) Platform.getAdapterManager().getAdapter(
						typeClass, OclType.class);
				myKnownTypes.put(typeClass, type);
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
		if (myModelInstanceFactory == null)
			myModelInstanceFactory = new UmlModelInstanceFactory();
		return myModelInstanceFactory;
	}

	public OclTupleType getTupleType(String[] partNames, OclType[] partTypes) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IModelObject> getObjects() {
		return allMyObjects;
	}

	public List<IModelObject> getObjectsOfKind(List<String> typePath) {
		if (typePath != null) {
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
			if (!isObjectOfModel(path)) {
				while (path.size() > 0
						&& !path.get(0).equals(myUMLModel.getName()))
					path.remove(0);
			}
			if (path.get(0).equals(myUMLModel.getName()))
				path.remove(0);
			String type = path.remove(0);
			Iterator<String> it = path.iterator();
			while (it.hasNext())
				type = type + "." + it.next();
			List<IModelObject> temp = allMyObjectsByName.get(type);
			if (temp == null)
				temp = new ArrayList<IModelObject>();
			return temp;
		}
		throw new IllegalArgumentException("TypePath must not be null");
	}

	private boolean isObjectOfModel(List<String> pathName) {
		List<String> modifiedPathname = new ArrayList<String>(pathName);
		if (myUMLModel.getName().equals(modifiedPathname.get(0)))
			modifiedPathname.remove(0);
		modifiedPathname.add(0, modifiedPathname
				.remove(modifiedPathname.size() - 1));
		if (myUMLModel != null)
			if (myUMLModel.findClassifier(modifiedPathname) != null)
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
		return allMyObjectKinds;
	}
}