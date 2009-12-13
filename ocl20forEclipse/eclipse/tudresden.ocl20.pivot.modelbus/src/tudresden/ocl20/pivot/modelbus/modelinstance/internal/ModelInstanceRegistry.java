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
package tudresden.ocl20.pivot.modelbus.modelinstance.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.ListenerList;

import tudresden.ocl20.pivot.modelbus.event.IModelInstanceRegistryListener;
import tudresden.ocl20.pivot.modelbus.event.ModelInstanceRegistryEvent;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceRegistry;

/**
 * <p>
 * The {@link ModelInstanceRegistry} manages all {@link IModelInstance}s that
 * are currently imported into Dresden OCL2 for Eclipse.
 * </p>
 * 
 * @author Ronny Brandt
 */
public class ModelInstanceRegistry implements IModelInstanceRegistry {

	/** The {@link IModelInstance}s mapped to the corresponding {@link IModel}. */
	private Map<IModel, List<IModelInstance>> modelInstances;

	/** Map of active {@link IModelInstance}s according to their {@link IModel}. */
	private Map<IModel, IModelInstance> activeModelInstances;

	/** The listeners of this {@link ModelInstanceRegistry}. */
	private ListenerList listeners;

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstanceRegistry#addModelInstance(
	 * tudresden.ocl20.pivot.modelbus.IModel,
	 * tudresden.ocl20.pivot.modelbus.IModelInstance)
	 */
	public void addModelInstance(IModel model, IModelInstance modelInstance) {

		if (modelInstance == null) {
			throw new IllegalArgumentException(
					"The parameter 'modelInstance' must not be null.");
		}
		// no else.

		if (model == null) {
			throw new IllegalArgumentException(
					"The parameter 'model' must not be null.");
		}
		// no else.

		/* Lazy initialization of map. */
		if (this.modelInstances == null) {
			this.modelInstances = new HashMap<IModel, List<IModelInstance>>();
		}
		// no else.

		List<IModelInstance> instances;
		instances = this.modelInstances.get(model);

		if (instances == null) {
			instances = new ArrayList<IModelInstance>();
		}
		// no else.

		if (instances.contains(modelInstance)) {
			throw new IllegalArgumentException("ModelInstance '"
					+ modelInstance.getDisplayName() + "' has already been loaded.");
		}
		// no else.

		instances.add(modelInstance);

		this.modelInstances.put(model, instances);

		this.fireModelInstanceAdded(model, modelInstance);
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.IModelInstanceRegistry#
	 * addModelInstanceRegistryListener
	 * (tudresden.ocl20.pivot.modelbus.event.IModelInstanceRegistryListener)
	 */
	public void addModelInstanceRegistryListener(
			IModelInstanceRegistryListener listener) {

		this.getListeners().add(listener);
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstanceRegistry#dispose()
	 */
	public void dispose() {

		if (this.modelInstances != null) {
			this.modelInstances.clear();
			this.modelInstances = null;
		}
		// no else.

		if (this.activeModelInstances != null) {
			this.activeModelInstances.clear();
			this.activeModelInstances = null;
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstanceRegistry#getActiveModelInstance
	 * (tudresden.ocl20.pivot.modelbus.IModel)
	 */
	public IModelInstance getActiveModelInstance(IModel model) {

		return this.activeModelInstances.get(model);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstanceRegistry#getModelInstances
	 * (tudresden.ocl20.pivot.modelbus.IModel)
	 */
	public IModelInstance[] getModelInstances(IModel model) {

		IModelInstance[] result;

		if (this.modelInstances == null) {
			result = new IModelInstance[] {};
		}

		else {
			List<IModelInstance> instances;
			instances = this.modelInstances.get(model);

			if (instances == null) {
				result = new IModelInstance[] {};
			}

			else {
				result = instances.toArray(new IModelInstance[instances.size()]);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.IModelInstanceRegistry#
	 * removeModelInstanceRegistryListener
	 * (tudresden.ocl20.pivot.modelbus.event.IModelInstanceRegistryListener)
	 */
	public void removeModelInstanceRegistryListener(
			IModelInstanceRegistryListener listener) {

		if (this.listeners != null) {
			this.listeners.remove(listener);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstanceRegistry#setActiveModelInstance
	 * (tudresden.ocl20.pivot.modelbus.IModel,
	 * tudresden.ocl20.pivot.modelbus.IModelInstance)
	 */
	public void setActiveModelInstance(IModel model, IModelInstance modelInstance) {

		if (this.modelInstances == null) {
			throw new IllegalArgumentException(
					"The model instance '"
							+ modelInstance.getDisplayName()
							+ "' must be added to the model instance registry first before it can be set active.");
		}
		// no else.

		if (this.modelInstances.get(model) == null) {
			throw new IllegalArgumentException(
					"The model instance '"
							+ modelInstance.getDisplayName()
							+ "' must be added to the model instance registry first before it can be set active.");
		}
		// no else.

		if (!this.modelInstances.get(model).contains(modelInstance)) {
			throw new IllegalArgumentException(
					"The model instance '"
							+ modelInstance.getDisplayName()
							+ "' must be added to the model instance registry first before it can be set active.");
		}
		// no else.

		if (this.activeModelInstances == null) {
			this.activeModelInstances = new HashMap<IModel, IModelInstance>();
		}
		// no else.

		IModelInstance instance;
		instance = activeModelInstances.get(model);

		if (instance == null || !instance.equals(modelInstance)) {
			this.activeModelInstances.put(model, modelInstance);
		}
		// no else.

		this.fireActiveModelInstanceChanged(model, modelInstance);
	}

	/**
	 * <p>
	 * Informs all listeners that the active {@link IModelInstance} has been
	 * changed.
	 * </p>
	 * 
	 * @param model
	 *          The {@link IModel} of the new active {@link IModelInstance}.
	 * @param modelInstance
	 *          The new active {@link IModelInstance}.
	 */
	private void fireActiveModelInstanceChanged(IModel model,
			IModelInstance modelInstance) {

		ModelInstanceRegistryEvent event;
		event = null;

		if (this.listeners != null) {

			Object[] listeners;
			listeners = this.listeners.getListeners();

			for (int index = 0; index < listeners.length; index++) {

				/* Lazily create the event. */
				if (event == null) {
					event = new ModelInstanceRegistryEvent(this, model, modelInstance);
				}
				// no else.

				((IModelInstanceRegistryListener) listeners[index])
						.activeModelInstanceChanged(event);
			}
			// end for.
		}
		// no else.
	}

	/**
	 * <p>
	 * Informs the listeners that a new {@link IModelInstance} has been added.
	 * </p>
	 * 
	 * @param model
	 *          The {@link IModel} of the {@link IModelInstance} that has been
	 *          added.
	 * @param modelInstance
	 *          The {@link IModelInstance} that has been added.
	 */
	private void fireModelInstanceAdded(IModel model, IModelInstance modelInstance) {

		ModelInstanceRegistryEvent event;
		event = null;

		if (this.listeners != null) {
			Object[] listeners;
			listeners = this.listeners.getListeners();

			for (int index = 0; index < listeners.length; index++) {

				/* Lazily create the event. */
				if (event == null) {
					event = new ModelInstanceRegistryEvent(this, model, modelInstance);
				}
				// no else.

				((IModelInstanceRegistryListener) listeners[index])
						.modelInstanceAdded(event);
			}
			// end for.
		}
		// no else.
	}

	/**
	 * <p>
	 * Returns the {@link ListenerList} of this {@link ModelInstanceRegistry}.
	 * </p>
	 * 
	 * @return The {@link ListenerList} of this {@link ModelInstanceRegistry}.
	 */
	private ListenerList getListeners() {

		if (this.listeners == null) {
			this.listeners = new ListenerList(ListenerList.IDENTITY);
		}
		// no else.

		return this.listeners;
	}
}