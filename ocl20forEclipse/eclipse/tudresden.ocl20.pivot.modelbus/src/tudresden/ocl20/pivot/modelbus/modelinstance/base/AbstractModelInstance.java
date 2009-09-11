/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Ronny Brandt (Ronny_Brandt@web.de).                    *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package tudresden.ocl20.pivot.modelbus.modelinstance.base;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceTypeObject;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * An abstract implementation of {@link IModelInstance}.
 * </p>
 * 
 * @author Ronny Brandt: Implemented first version
 * @author Claas Wilke: Refactored and added Java-Doc.
 */
public abstract class AbstractModelInstance implements IModelInstance {

	/** The {@link IModel} of this {@link IModelInstance}. */
	protected IModel myModel;

	/** Contains all {@link IModelInstanceObject}s of this model instance. */
	protected Set<IModelInstanceObject> myModelInstanceObjects =
			new HashSet<IModelInstanceObject>();

	/**
	 * <p>
	 * Contains all {@link IModelInstanceObject}s of this model instance ordered
	 * by their type's name.
	 * </p>
	 * <strong>This map is a {@link WeakHashMap}. If the adapted {@link Type} does
	 * not exist any more, the adapter is also disposed.</strong>
	 */
	protected Map<Type, Set<IModelInstanceObject>> myModelInstanceObjectsByType =
			new WeakHashMap<Type, Set<IModelInstanceObject>>();

	/**
	 * The {@link IModelInstanceFactory} used to created adapters for the
	 * {@link IModelInstanceElement}s.
	 */
	protected IModelInstanceFactory myModelInstanceFactory;

	/**
	 * <p>
	 * Contains all {@link IModelInstanceTypeObject} of this
	 * {@link IModelInstance} identified by their adapted {@link Type}.
	 * </p>
	 * <p>
	 * <strong>This map is a {@link WeakHashMap}. If the adapted {@link Type} does
	 * not exist any more, the adapter is also disposed.</strong>
	 * </p>
	 */
	protected Map<Type, IModelInstanceTypeObject> myModelTypeObjects =
			new WeakHashMap<Type, IModelInstanceTypeObject>();

	/** The name of the model instance. */
	protected String myName;

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance#getAllInstances
	 * (tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	public Set<IModelInstanceObject> getAllInstances(Type type) {

		Set<IModelInstanceObject> result;

		/* If the type has been found, return all implementations. */
		result = this.myModelInstanceObjectsByType.get(type);

		if (result == null) {
			result = new HashSet<IModelInstanceObject>();
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance#getElementTypes
	 * ()
	 */
	public Set<Type> getAllImplementedTypes() {
	
		Set<Type> result = new HashSet<Type>();
	
		for (IModelInstanceElement modelObject : this.myModelInstanceObjects) {
			result.addAll(modelObject.getTypes());
		}
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance#getAllModelInstanceObjects()
	 */
	public List<IModelInstanceObject> getAllModelInstanceObjects() {
	
		return new ArrayList<IModelInstanceObject>(this.myModelInstanceObjects);
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getDisplayName()
	 */
	public String getDisplayName() {

		return this.myName;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstance#getModel()
	 */
	public IModel getModel() {
	
		return this.myModel;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance#
	 * getModelInstanceFactory()
	 */
	public IModelInstanceFactory getModelInstanceFactory() {
	
		return this.myModelInstanceFactory;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#isInstanceOf(tudresden.ocl20
	 * .pivot.modelbus.IModel)
	 */
	public boolean isInstanceOf(IModel aModel) {
	
		return this.myModel.equals(aModel);
	}

	/**
	 * <p>
	 * Adds a given {@link IModelInstanceObject} to the {@link Type} mapping of
	 * this {@link AbstractModelInstance}.
	 * </p>
	 * 
	 * @param modelInstanceObject
	 *          The {@link IModelInstanceObject} that shall be added to the
	 *          {@link Type} mapping.
	 */
	protected void addModelInstanceObjectToCache(
			IModelInstanceObject modelInstanceObject) {

		/* Iterate through all types of the object. */
		for (Type type : modelInstanceObject.getTypes()) {

			if (this.myModelInstanceObjectsByType.containsKey(type)) {
				this.myModelInstanceObjectsByType.get(type).add(modelInstanceObject);
			}

			else {
				Set<IModelInstanceObject> modelObjects;

				modelObjects = new HashSet<IModelInstanceObject>();
				modelObjects.add(modelInstanceObject);

				myModelInstanceObjectsByType.put(type, modelObjects);
			}

		}
		// end for.
	}

	/**
	 * <p>
	 * A helper method that adds all adapted {@link IModelInstanceObject} of this
	 * {@link AbstractModelInstance} contained in the filed
	 * {@link AbstractModelInstance#myModelInstanceObjects} to the {@link Type}
	 * mapping of this {@link AbstractModelInstance}.
	 * </p>
	 */
	protected void initializeTypeMapping() {

		for (IModelInstanceObject modelInstanceObject : this.myModelInstanceObjects) {
			this.addModelInstanceObjectToCache(modelInstanceObject);
		}
	}
}