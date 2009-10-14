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
package tudresden.ocl20.pivot.modelbus.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.ListenerList;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.event.IModelInstanceRegistryListener;
import tudresden.ocl20.pivot.modelbus.event.ModelInstanceRegistryEvent;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceRegistry;

/**
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class ModelInstanceRegistry implements IModelInstanceRegistry {

	// The model instances mapped to corresponding model
	private Map<IModel, List<IModelInstance>> modelInstances;

	// Map of active model instances according to the models
	private Map<IModel, IModelInstance> activeModelInstance;

	// The listeners
	private ListenerList listeners;

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstanceRegistry#addModelInstance(tudresden.ocl20.pivot.modelbus.IModel,
	 *      tudresden.ocl20.pivot.modelbus.IModelInstance)
	 */
	public void addModelInstance(IModel model, IModelInstance modelInstance) {
		if (modelInstance == null)
			throw new IllegalArgumentException(
					"The parameter 'modelInstance' must not be null.");

		if (model == null)
			throw new IllegalArgumentException(
					"The parameter 'model' must not be null.");

		if (modelInstances == null)
			modelInstances = new HashMap<IModel, List<IModelInstance>>();

		List<IModelInstance> instances = modelInstances.get(model);

		if (instances == null)
			instances = new ArrayList<IModelInstance>();

		if (instances.contains(modelInstance))
			throw new IllegalArgumentException("ModelInstance '"
					+ modelInstance.getDisplayName() + "' is already loaded.");

		instances.add(modelInstance);

		modelInstances.put(model, instances);

		fireModelInstanceAdded(model, modelInstance);
	}

	/**
	 * Fire model instance added.
	 * 
	 * @param model
	 *            the model
	 * @param modelInstance
	 *            the model instance
	 */
	private void fireModelInstanceAdded(IModel model,
			IModelInstance modelInstance) {
		ModelInstanceRegistryEvent event = null;

		if (listeners != null) {
			Object[] listeners = this.listeners.getListeners();

			for (int i = 0; i < listeners.length; i++) {

				// lazily create the event
				if (event == null) {
					event = new ModelInstanceRegistryEvent(this, model,
							modelInstance);
				}

				((IModelInstanceRegistryListener) listeners[i])
						.modelInstanceAdded(event);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstanceRegistry#addModelInstanceRegistryListener(tudresden.ocl20.pivot.modelbus.event.IModelInstanceRegistryListener)
	 */
	public void addModelInstanceRegistryListener(
			IModelInstanceRegistryListener listener) {

		getListeners().add(listener);
	}

	/**
	 * Gets the listeners.
	 * 
	 * @return the listeners
	 */
	private ListenerList getListeners() {
		if (listeners == null)
			listeners = new ListenerList(ListenerList.IDENTITY);
		return listeners;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstanceRegistry#dispose()
	 */
	public void dispose() {
		if (modelInstances != null) {
			modelInstances.clear();
			modelInstances = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstanceRegistry#getActiveModelInstance(tudresden.ocl20.pivot.modelbus.IModel)
	 */
	public IModelInstance getActiveModelInstance(IModel model) {
		if (activeModelInstance != null)
			return activeModelInstance.get(model);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstanceRegistry#getModelInstances(tudresden.ocl20.pivot.modelbus.IModel)
	 */
	public IModelInstance[] getModelInstances(IModel model) {
		if (modelInstances == null)
			return new IModelInstance[] {};
		List<IModelInstance> instances = modelInstances.get(model);
		if (instances == null)
			return new IModelInstance[] {};
		return instances.toArray(new IModelInstance[instances.size()]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstanceRegistry#removeModelInstanceRegistryListener(tudresden.ocl20.pivot.modelbus.event.IModelInstanceRegistryListener)
	 */
	public void removeModelInstanceRegistryListener(
			IModelInstanceRegistryListener listener) {

		if (listeners != null)
			listeners.remove(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstanceRegistry#setActiveModelInstance(tudresden.ocl20.pivot.modelbus.IModel,
	 *      tudresden.ocl20.pivot.modelbus.IModelInstance)
	 */
	public void setActiveModelInstance(IModel model,
			IModelInstance modelInstance) {
		if (modelInstances == null)
			throw new IllegalArgumentException(
					"The model instance '"
							+ modelInstance.getDisplayName()
							+ "' must be added to the model instance registry first befor it can be set active.");

		if (modelInstances.get(model) == null)
			throw new IllegalArgumentException(
					"The model instance '"
							+ modelInstance.getDisplayName()
							+ "' must be added to the model instance registry first befor it can be set active.");

		if (!modelInstances.get(model).contains(modelInstance))
			throw new IllegalArgumentException(
					"The model instance '"
							+ modelInstance.getDisplayName()
							+ "' must be added to the model instance registry first befor it can be set active.");

		if (activeModelInstance == null)
			activeModelInstance = new HashMap<IModel, IModelInstance>();

		IModelInstance instance = activeModelInstance.get(model);

		if (instance == null || !instance.equals(modelInstance)) {
			activeModelInstance.put(model, modelInstance);
		}

		fireActiveModelInstanceChanged(model, modelInstance);
	}

	/**
	 * Fire active model instance changed.
	 * 
	 * @param model
	 *            the model
	 * @param modelInstance
	 *            the model instance
	 */
	private void fireActiveModelInstanceChanged(IModel model,
			IModelInstance modelInstance) {
		ModelInstanceRegistryEvent event = null;

		if (listeners != null) {
			Object[] listeners = this.listeners.getListeners();

			for (int i = 0; i < listeners.length; i++) {

				// lazily create the event
				if (event == null) {
					event = new ModelInstanceRegistryEvent(this, model,
							modelInstance);
				}

				((IModelInstanceRegistryListener) listeners[i])
						.activeModelInstanceChanged(event);
			}
		}
	}
}
