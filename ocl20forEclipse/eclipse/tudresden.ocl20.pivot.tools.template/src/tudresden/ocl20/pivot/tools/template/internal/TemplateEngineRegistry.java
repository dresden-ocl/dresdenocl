package tudresden.ocl20.pivot.tools.template.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.Platform;

import tudresden.ocl20.pivot.tools.template.ITemplate;
import tudresden.ocl20.pivot.tools.template.ITemplateEngine;
import tudresden.ocl20.pivot.tools.template.ITemplateEngineRegistry;
import tudresden.ocl20.pivot.tools.template.TemplatePlugin;
import tudresden.ocl20.pivot.tools.template.event.ITemplateEngineRegistryListener;
import tudresden.ocl20.pivot.tools.template.event.TemplateEngineRegistryEvent;

/**
 * Default implementation of the {@link ITemplateEngineRegistr}
 * 
 * @author Bjoern Freitag
 *
 */
public class TemplateEngineRegistry implements ITemplateEngineRegistry, IRegistryEventListener {
	
	/** {@link Logger} for this class. */
	private static final Logger LOGGER =
			TemplatePlugin.getLogger(TemplateEngineRegistry.class);
	
	/**
	 * the map of template engines
	 */
	private Map<String,ITemplateEngine> templateEngines;
	
	/** The full identifier of the {@link ITemplateEngine}s' extension point. */
	private static final String TEMPLATEENGINE_EXTENSION_POINT_ID =
			TemplatePlugin.ID + ".templateEngines";
	
	/** A list of listeners. */
	private ListenerList listeners;
	
	/**
	 * The constructor
	 */
	public TemplateEngineRegistry() {
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("TemplateEngineRegistry() - enter"); //$NON-NLS-1$
		}
		// no else.
		
		
		
		this.templateEngines = new HashMap<String,ITemplateEngine>();
		this.added(this.getExtensionPoint().getExtensions());
		
	
		/* Register this registry as a listener for plug-in events. */
		Platform.getExtensionRegistry().addListener(this,
				TEMPLATEENGINE_EXTENSION_POINT_ID);
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("TemplateEngineRegistry() - exit"); //$NON-NLS-1$
		}
		
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateEngineRegistry#addTemplateEngine(ITemplateEngine)
	 */
	public void addTemplateEngine(ITemplateEngine templateEngine) {
		if (LOGGER.isDebugEnabled() || true) {
			LOGGER.debug("addTemplateEngine(templateEngine=" + templateEngine + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		if (templateEngine == null) {
			throw new IllegalArgumentException(
					"The parameter 'templateEngine' must not be null."); //$NON-NLS-1$
		}
		// no else.

		/*
		 * Check if model is already contained in the registry; this is meant to be
		 * captured and dealt with on the UI, e.g., by showing an error message;
		 * this is better than silently do nothing.
		 */
		if (this.templateEngines.containsValue(templateEngine)) {
			LOGGER
					.warn("TemplateEngine '" + templateEngine.getDisplayName() + "' is already loaded. The templateEngine will be replaced."); //$NON-NLS-1$//$NON-NLS-2$
		}
		// no else.

		/* Add the model. */
		this.templateEngines.put(templateEngine.getDisplayName(), templateEngine);

		/* Inform listeners. */
		this.fireTemplateEngineAdded(templateEngine);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("addTemplateEngine() - exit"); //$NON-NLS-1$
		}
		// no else.
	}



	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateEngineRegistry#dispose()
	 */
	public void dispose() {
		
		if (this.templateEngines.size() != 0) {
			this.templateEngines.clear();
		}

	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateEngineRegistry#getNewTemplateEngine(String)
	 */
	public ITemplateEngine getNewTemplateEngine(String templateEngineName) {
		if (templateEngineName == null) {
			throw new IllegalArgumentException(
					"The parameter templateEngineName must not be null.");
		}
		// no else.
		ITemplateEngine templateEngine;
		try {
			templateEngine = this.templateEngines.get(templateEngineName).getClass().newInstance();
		} catch (InstantiationException e) {
			templateEngine = null;
		} catch (IllegalAccessException e) {
			templateEngine = null;
		} catch (NullPointerException e) {
			templateEngine = null;
		}
		
		return templateEngine;
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateEngineRegistry#getTemplateEngines()
	 */
	public List<ITemplateEngine> getTemplateEngines() {
		List<ITemplateEngine> tempGroup = new ArrayList<ITemplateEngine>();
		for (ITemplateEngine tGroup : this.templateEngines.values()) {
			tempGroup.add(tGroup);
		}
		return tempGroup;
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateEngineRegistry#removeTemplateEngine(ITemplateEngine)
	 */
	public void removeTemplateEngine(ITemplateEngine templateEngine) {
		if (templateEngine == null) {
			throw new IllegalArgumentException(
					"The parameter templateEngineName must not be null.");
		}
		// no else.

		this.templateEngines.remove(templateEngine.getDisplayName());
		
		this.fireTemplateEngineRemoved(this.templateEngines.remove(templateEngine.getDisplayName()));
	
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateEngineRegistry#removeTemplateEngine(String)
	 */
	public void removeTemplateEngine(String templateEngineName) {
		if (templateEngineName == null) {
			throw new IllegalArgumentException(
					"The parameter templateEngineName must not be null.");
		}
		// no else.

		this.templateEngines.remove(templateEngineName);
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateEngineRegistry#addTemplateEngineRegistryListener(ITemplateEngineRegistryListener)
	 */
	public void addTemplateEngineRegistryListener(ITemplateEngineRegistryListener listener) {
		this.getListeners().add(listener);
		
	}
	
	/**
	 * <p>
	 * A helper method that informs all listeners about an added {@link ITemplateEngine}.
	 * </p>
	 * 
	 * @param model
	 *          The {@link ITemplateEngine} that has been added.
	 */
	private void fireTemplateEngineAdded(ITemplateEngine model) {

		TemplateEngineRegistryEvent event;
		event = null;

		if (this.listeners != null) {

			Object[] listeners;
			listeners = this.listeners.getListeners();

			for (int index = 0; index < listeners.length; index++) {

				/* Lazily create the event. */
				if (event == null) {
					event = new TemplateEngineRegistryEvent(this, model);
				}
				// no else.

				((ITemplateEngineRegistryListener) listeners[index]).templateEngineAdded(event);
			}
			// end for.
		}
		// no else.
	}
	
	/**
	 * <p>
	 * A helper method that informs all listeners about a removed {@link ITemplateEngine}.
	 * </p>
	 * 
	 * @param templateEngine
	 *          The {@link ITemplate} that has been removed.
	 */
	private void fireTemplateEngineRemoved(ITemplateEngine templateEngine) {

		TemplateEngineRegistryEvent event = null;

		if (this.listeners != null) {

			Object[] listeners;
			listeners = this.listeners.getListeners();

			for (int index = 0; index < listeners.length; index++) {

				/* Lazily create the event. */
				if (event == null) {
					event = new TemplateEngineRegistryEvent(this, templateEngine);
				}
				// no else.

				((ITemplateEngineRegistryListener) listeners[index]).templateEngineRemoved(event);
			}
			// end for.
		}
		// no else.
	}
	
	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateEngineRegistry#removeTemplateEngineRegistryListener(ITemplateEngineRegistryListener)
	 * 
	 */
	public void removeTemplateEngineRegistryListener(ITemplateEngineRegistryListener listener) {

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
	 * Add a new {@link ITemplateEngine} from an extension
	 * 
	 * @see org.eclipse.core.runtime.IRegistryEventListener#added(IExtension[])
	 */
	public void added(IExtension[] extensions) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("removed(extensions=" + extensions + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		// no else.

		/* Remove all registered objects from the meta-model cache. */
		if (this.templateEngines != null) {

			for (IExtension extension : extensions) {

				for (IConfigurationElement configurationElement : extension
						.getConfigurationElements()) {

					ITemplateEngine templateEngine;
					try {
						templateEngine = (ITemplateEngine)(Class.forName(this.getAttribute("class", configurationElement)).newInstance());
					} catch (InstantiationException e) {
						templateEngine = null;
					} catch (IllegalAccessException e) {
						templateEngine = null;
					} catch (ClassNotFoundException e) {
						templateEngine = null;
					}

					if (templateEngine != null) {
						this.addTemplateEngine(templateEngine);
					}
					// no else.
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
	 * @see org.eclipse.core.runtime.IRegistryEventListener#added(IExtensionPoint[])
	 */
	public void added(IExtensionPoint[] extensionPoints) {
		// Do nothing
	}

	/**
	 * Remove an {@link ITemplateEngine} from the extension
	 * 
	 * @see org.eclipse.core.runtime.IRegistryEventListener#removed(IExtension[])
	 */
	public void removed(IExtension[] extensions) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("removed(extensions=" + extensions + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		// no else.

		/* Remove all registered objects from the meta-model cache. */
		if (this.templateEngines != null) {

			for (IExtension extension : extensions) {

				for (IConfigurationElement configurationElement : extension
						.getConfigurationElements()) {

					String templateEngineID;
					try {
						templateEngineID =
								((ITemplateEngine)(Class.forName(this.getAttribute("class", configurationElement)).newInstance())).getDisplayName();
					} catch (InstantiationException e) {
						templateEngineID = null;
					} catch (IllegalAccessException e) {
						templateEngineID = null;
					} catch (ClassNotFoundException e) {
						templateEngineID = null;
					}

					if (templateEngineID != null) {
						this.removeTemplateEngine(templateEngineID);
					}
					// no else.
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
		//Do nothing	
	}
	
	/**
	 * <p>
	 * A helper method to get the worklist task {@link IExtensionPoint}.
	 * </p>
	 * 
	 * @return The {@link ITemplateEngine} {@link IExtensionPoint}.
	 */
	private IExtensionPoint getExtensionPoint() {

		IExtensionPoint result;

		/* Get the point from the registry. */
		result =
				Platform.getExtensionRegistry().getExtensionPoint(
						TEMPLATEENGINE_EXTENSION_POINT_ID);

		/* This should not happen unless the id changes. */
		if (result == null) {
			throw new IllegalStateException(
					"The extension point for new template engines could not be found under the id " //$NON-NLS-1$
							+ TEMPLATEENGINE_EXTENSION_POINT_ID);
		}
		// no else.

		return result;
	}
	
	/**
	 * <p>
	 * Helper method that returns the value of an attribute of the given
	 * {@link IConfigurationElement}. Throws an {@link InvalidDescriptorException}
	 * if the attribute is empty and required.
	 * </p>
	 * 
	 * @param attributeName
	 *          The name of the extension point attribute.
	 * @param configurationElement
	 *          The {@link IllegalClassException} whose attribute shall be
	 *          returned.
	 * 
	 * @throws InvalidDescriptorException
	 *           If the value of the attribute is invalid.
	 */
	private String getAttribute(String attributeName,
			IConfigurationElement configurationElement) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("getAttribute(attributeName=" + attributeName + ", configurationElement=" + configurationElement //$NON-NLS-1$ //$NON-NLS-2$
							+ ") - enter"); //$NON-NLS-1$
		}
		// no else.

		String value = configurationElement.getAttribute(attributeName);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getAttribute() - exit - return value=" + value); //$NON-NLS-1$
		}
		// no else.

		return value;
	}

}
