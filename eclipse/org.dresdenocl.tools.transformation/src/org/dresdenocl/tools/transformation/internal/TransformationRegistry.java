package org.dresdenocl.tools.transformation.internal;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.Platform;

import org.dresdenocl.tools.transformation.ITransformation;
import org.dresdenocl.tools.transformation.ITransformationRegistry;
import org.dresdenocl.tools.transformation.ParallelTransformation;
import org.dresdenocl.tools.transformation.TransformationPlugin;
import org.dresdenocl.tools.transformation.event.ITransformationRegistryListener;
import org.dresdenocl.tools.transformation.event.TransformationRegistryEvent;

/**
 * Default implementation of the {@link ITransformationRegistr}
 * 
 * @author Bjoern Freitag
 * 
 */
public class TransformationRegistry implements ITransformationRegistry,
		IRegistryEventListener {

	/** {@link Logger} for this class. */
	private static final Logger LOGGER = TransformationPlugin
			.getLogger(TransformationRegistry.class);

	/**
	 * the map of template engines
	 */
	private Map<String, Class<?>> transformations;

	/** The full identifier of the {@link ITransformation}s' extension point. */
	private static final String TRANSFORMATION_EXTENSION_POINT_ID = TransformationPlugin.ID
			+ ".transformations";

	/** A list of listeners. */
	private ListenerList listeners;

	/**
	 * The constructor
	 */
	public TransformationRegistry() {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("TransformationRegistry() - enter"); //$NON-NLS-1$
		}
		// no else.

		this.transformations = new HashMap<String, Class<?>>();

		if (Platform.isRunning()) {
			this.added(this.getExtensionPoint().getExtensions());

			/* Register this registry as a listener for plug-in events. */
			Platform.getExtensionRegistry().addListener(this,
					TRANSFORMATION_EXTENSION_POINT_ID);
		}

		else {
			LOGGER.warn("Plarform is not running. Please register transformations manually.");
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("TransformationRegistry() - exit"); //$NON-NLS-1$
		}

	}

	/**
	 * @see org.dresdenocl.tools.transformation.ITransformationRegistry#addTransformation(ITransformation)
	 */
	public void addTransformation(ITransformation<?, ?, ?> transformation) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("addTransformation(transformation=" + transformation + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		if (transformation == null) {
			throw new IllegalArgumentException(
					"The parameter 'transformation' must not be null."); //$NON-NLS-1$
		}
		// no else.

		/*
		 * Check if model is already contained in the registry; this is meant to
		 * be captured and dealt with on the UI, e.g., by showing an error
		 * message; this is better than silently do nothing.
		 */
		if (this.transformations.containsValue(transformation)) {
			LOGGER.warn("Transformation '" + transformation.getClass().getSimpleName() + "' is already loaded. The transformation will be replaced."); //$NON-NLS-1$//$NON-NLS-2$
		}
		// no else.

		addTransformation(transformation.getClass());

		/* Inform listeners. */
		this.fireTransformationAdded(transformation.getClass());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("addTransformation() - exit"); //$NON-NLS-1$
		}
		// no else.
	}

	private void addTransformation(Class<?> clazz) {

		this.transformations.put(clazz.getSimpleName(), clazz);
	}

	/**
	 * @see org.dresdenocl.tools.transformation.ITransformationRegistry#dispose()
	 */
	public void dispose() {

		if (this.transformations.size() != 0) {
			this.transformations.clear();
		}

	}

	/**
	 * @see org.dresdenocl.tools.template.ITransformationRegistry#getTransformationClass(String)
	 */
	public Class<?> getTransformationClass(String transformationName) {

		if (transformationName == null) {
			throw new IllegalArgumentException(
					"The parameter transformationName must not be null.");
		}
		// no else.
		return this.transformations.get(transformationName);
	}

	/**
	 * @see org.dresdenocl.tools.template.ITransformationRegistry#removeTransformation(ITransformation)
	 */
	public void removeTransformation(ITransformation<?, ?, ?> transformation) {

		if (transformation == null) {
			throw new IllegalArgumentException(
					"The parameter transformationName must not be null.");
		}
		// no else.

		this.fireTransformationRemoved(this.transformations
				.remove(transformation.getClass().getSimpleName()));

	}

	/**
	 * @see org.dresdenocl.tools.template.ITransformationRegistry#removeTransformation(String)
	 */
	public void removeTransformation(String transformationName) {

		if (transformationName == null) {
			throw new IllegalArgumentException(
					"The parameter transformationName must not be null.");
		}
		// no else.

		this.transformations.remove(transformationName);
	}

	/**
	 * @see org.dresdenocl.tools.template.ITransformationRegistry#addTransformationRegistryListener(ITransformationRegistryListener)
	 */
	public void addTransformationRegistryListener(
			ITransformationRegistryListener listener) {

		this.getListeners().add(listener);

	}

	/**
	 * <p>
	 * A helper method that informs all listeners about an added
	 * {@link ITransformation}.
	 * </p>
	 * 
	 * @param transformation
	 *            The {@link ITransformation} that has been added.
	 */
	private void fireTransformationAdded(Class<?> transformation) {

		TransformationRegistryEvent event;
		event = null;

		if (this.listeners != null) {

			Object[] listeners;
			listeners = this.listeners.getListeners();

			for (int index = 0; index < listeners.length; index++) {

				/* Lazily create the event. */
				if (event == null) {
					event = new TransformationRegistryEvent(this,
							transformation);
				}
				// no else.

				((ITransformationRegistryListener) listeners[index])
						.transformationAdded(event);
			}
			// end for.
		}
		// no else.
	}

	/**
	 * <p>
	 * A helper method that informs all listeners about a removed
	 * {@link ITransformation}.
	 * </p>
	 * 
	 * @param transformation
	 *            The {@link ITemplate} that has been removed.
	 */
	private void fireTransformationRemoved(Class<?> transformation) {

		TransformationRegistryEvent event = null;

		if (this.listeners != null) {

			Object[] listeners;
			listeners = this.listeners.getListeners();

			for (int index = 0; index < listeners.length; index++) {

				/* Lazily create the event. */
				if (event == null) {
					event = new TransformationRegistryEvent(this,
							transformation);
				}
				// no else.

				((ITransformationRegistryListener) listeners[index])
						.transformationRemoved(event);
			}
			// end for.
		}
		// no else.
	}

	/**
	 * @see org.dresdenocl.tools.template.ITransformationRegistry#removeTransformationRegistryListener(ITransformationRegistryListener)
	 * 
	 */
	public void removeTransformationRegistryListener(
			ITransformationRegistryListener listener) {

		if (this.listeners != null) {
			this.listeners.remove(listener);
		}
		// no else.
	}

	/**
	 * <p>
	 * A helper method that lazily creates the {@link ListenerList}.
	 * </p>
	 */
	private ListenerList getListeners() {

		if (this.listeners == null) {
			this.listeners = new ListenerList(ListenerList.IDENTITY);
		}
		// no else.

		return this.listeners;
	}

	/**
	 * Add a new {@link ITransformation} from an extension
	 * 
	 * @see org.eclipse.core.runtime.IRegistryEventListener#added(IExtension[])
	 */
	public void added(IExtension[] extensions) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("added(extensions=" + extensions + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		// no else.

		/* Remove all registered objects from the meta-model cache. */
		if (this.transformations != null) {
			for (IExtension extension : extensions) {

				for (IConfigurationElement configurationElement : extension
						.getConfigurationElements()) {
					try {
						Class<?> clazz = Platform.getBundle(
								extension.getNamespaceIdentifier()).loadClass(
								configurationElement.getAttribute("class"));
						this.addTransformation(clazz);
					} catch (InvalidRegistryObjectException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

					// no else.
				}
				// end for (configurationElements).
			}
			// end for (extensions).
		}
		// no else.

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("added(IExtension[]) - exit"); //$NON-NLS-1$
		}
		// no else.

	}

	/**
	 * This method do nothing.
	 * 
	 * @see org.eclipse.core.runtime.IRegistryEventListener#added(IExtensionPoint[])
	 */
	public void added(IExtensionPoint[] extensionPoints) {

		// Do nothing
	}

	/**
	 * Remove an {@link ITransformation} from the extension
	 * 
	 * @see org.eclipse.core.runtime.IRegistryEventListener#removed(IExtension[])
	 */
	public void removed(IExtension[] extensions) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("removed(extensions=" + extensions + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		// no else.

		/* Remove all registered objects from the meta-model cache. */
		if (this.transformations != null) {

			for (IExtension extension : extensions) {

				for (IConfigurationElement configurationElement : extension
						.getConfigurationElements()) {

					String transformationID;
					try {
						transformationID = ((ITransformation<?, ?, ?>) configurationElement
								.createExecutableExtension("class"))
								.getDisplayName();
					} catch (CoreException e) {
						continue;
					}

					this.removeTransformation(transformationID);
				}
				// end for (configurationElements).
			}
			// end for (extensions).
		}
		// no else.

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("removed(IExtension[]) - exit"); //$NON-NLS-1$
		}
		// no else.
	}

	/**
	 * This method do nothing.
	 * 
	 * @see org.eclipse.core.runtime.IRegistryEventListener#removed(IExtensionPoint[])
	 */
	public void removed(IExtensionPoint[] extensionPoints) {

		// Do nothing
	}

	/**
	 * <p>
	 * A helper method to get the worklist task {@link IExtensionPoint}.
	 * </p>
	 * 
	 * @return The {@link ITranformation} {@link IExtensionPoint}.
	 */
	private IExtensionPoint getExtensionPoint() {

		IExtensionPoint result;

		/* Get the point from the registry. */
		result = Platform.getExtensionRegistry().getExtensionPoint(
				TRANSFORMATION_EXTENSION_POINT_ID);

		/* This should not happen unless the id changes. */
		if (result == null) {
			throw new IllegalStateException(
					"The extension point for new metamodels could not be found under the id " //$NON-NLS-1$
							+ TRANSFORMATION_EXTENSION_POINT_ID);
		}
		// no else.

		return result;
	}

	public List<String> getTransformationList() {

		return new LinkedList<String>(this.transformations.keySet());
	}

	public List<String> getTransformationList(Class<?> modelIn,
			Class<?> modelOut, Class<?> settings) {

		List<String> itransList = new ArrayList<String>();
		for (String s : this.transformations.keySet()) {
			Class<?> clazz = this.transformations.get(s);
			ParameterizedType superclass = ((ParameterizedType) clazz
					.getGenericSuperclass());
			Type[] types = superclass.getActualTypeArguments();
			if (types[0].equals(modelIn)) {
				if (types[1].equals(settings)) {
					if (superclass.getRawType().equals(
							ParallelTransformation.class)) {
						if (types[2].equals(modelOut)) {
							itransList.add(s);
						} else if (types[3].equals(modelOut)) {
							itransList.add(s);
						}
					} else {
						if (types[types.length - 1].equals(modelOut)) {
							itransList.add(s);
						}
					}
				}
			}
		}
		return itransList;
	}

}
