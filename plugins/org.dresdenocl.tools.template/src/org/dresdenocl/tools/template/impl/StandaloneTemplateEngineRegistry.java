package org.dresdenocl.tools.template.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import org.dresdenocl.tools.template.ITemplate;
import org.dresdenocl.tools.template.ITemplateEngine;
import org.dresdenocl.tools.template.ITemplateEngineRegistry;
import org.dresdenocl.tools.template.TemplatePlugin;
import org.dresdenocl.tools.template.event.ITemplateEngineRegistryListener;
import org.dresdenocl.tools.template.event.TemplateEngineRegistryEvent;
import org.dresdenocl.tools.template.exception.TemplateException;

/**
 * <p>
 * A simple implementation of the {@link ITemplateEngineRegistry} interface that
 * internally just uses a {@link Map} to keep track of registered template
 * engines.
 * </p>
 * <p>
 * In a stand-alone application of Dresden OCL, this implies that new template
 * engines have to be added by hand to this registry.
 * </p>
 * 
 * @author Bjoern Freitag
 * 
 */
public class StandaloneTemplateEngineRegistry implements
		ITemplateEngineRegistry {

	/** {@link Logger} for this class. */
	private static final Logger LOGGER = TemplatePlugin
			.getLogger(StandaloneTemplateEngineRegistry.class);

	/**
	 * the map of template engines
	 */
	private Map<String, ITemplateEngine> templateEngines;

	/** A list of listeners. */
	private List<ITemplateEngineRegistryListener> listeners;

	/**
	 * The constructor
	 */
	public StandaloneTemplateEngineRegistry() {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("TemplateEngineRegistry() - enter"); //$NON-NLS-1$
		}
		// no else.

		this.templateEngines = new HashMap<String, ITemplateEngine>();

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("TemplateEngineRegistry() - exit"); //$NON-NLS-1$
		}

	}

	/**
	 * @see org.dresdenocl.tools.template.ITemplateEngineRegistry#addTemplateEngine(ITemplateEngine)
	 */
	public void addTemplateEngine(ITemplateEngine templateEngine)
			throws TemplateException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("addTemplateEngine(templateEngine=" + templateEngine + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		if (templateEngine == null) {
			throw new TemplateException(
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
	 * @see org.dresdenocl.tools.template.ITemplateEngineRegistry#dispose()
	 */
	public void dispose() {

		if (this.templateEngines.size() != 0) {
			this.templateEngines.clear();
		}

	}

	/**
	 * @see org.dresdenocl.tools.template.ITemplateEngineRegistry#getNewTemplateEngine(String)
	 */
	public ITemplateEngine getNewTemplateEngine(String templateEngineName)
			throws TemplateException {

		if (templateEngineName == null) {
			throw new TemplateException(
					"The parameter templateEngineName must not be null.");
		}
		// no else.
		ITemplateEngine templateEngine;
		try {
			templateEngine =
					this.templateEngines.get(templateEngineName).getClass().newInstance();
		} catch (InstantiationException e) {
			templateEngine = null;
		} catch (IllegalAccessException e) {
			templateEngine = null;
		} catch (NullPointerException e) {
			templateEngine = null;
		}
		if (templateEngine == null) {
			throw new TemplateException("The template engine can't load.");
		}
		return templateEngine;
	}

	/**
	 * @see org.dresdenocl.tools.template.ITemplateEngineRegistry#getTemplateEngines()
	 */
	public List<ITemplateEngine> getTemplateEngines() {

		List<ITemplateEngine> tempGroup = new ArrayList<ITemplateEngine>();
		for (ITemplateEngine tGroup : this.templateEngines.values()) {
			tempGroup.add(tGroup);
		}
		return tempGroup;
	}

	/**
	 * @see org.dresdenocl.tools.template.ITemplateEngineRegistry#removeTemplateEngine(ITemplateEngine)
	 */
	public void removeTemplateEngine(ITemplateEngine templateEngine) {

		if (templateEngine == null) {
			throw new IllegalArgumentException(
					"The parameter templateEngineName must not be null.");
		}
		// no else.

		this.templateEngines.remove(templateEngine.getDisplayName());

		this.fireTemplateEngineRemoved(this.templateEngines.remove(templateEngine
				.getDisplayName()));

	}

	/**
	 * @see org.dresdenocl.tools.template.ITemplateEngineRegistry#removeTemplateEngine(String)
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
	 * @see org.dresdenocl.tools.template.ITemplateEngineRegistry#addTemplateEngineRegistryListener(ITemplateEngineRegistryListener)
	 */
	public void addTemplateEngineRegistryListener(
			ITemplateEngineRegistryListener listener) {

		this.getListeners().add(listener);

	}

	/**
	 * <p>
	 * A helper method that informs all listeners about an added
	 * {@link ITemplateEngine}.
	 * </p>
	 * 
	 * @param model
	 *          The {@link ITemplateEngine} that has been added.
	 */
	private void fireTemplateEngineAdded(ITemplateEngine model) {

		TemplateEngineRegistryEvent event;
		event = null;

		if (this.listeners != null) {

			for (ITemplateEngineRegistryListener listener : getListeners()) {

				/* Lazily create the event. */
				if (event == null) {
					event = new TemplateEngineRegistryEvent(this, model);
				}
				// no else.

				listener.templateEngineAdded(event);
			}
			// end for.
		}
		// no else.
	}

	/**
	 * <p>
	 * A helper method that informs all listeners about a removed
	 * {@link ITemplateEngine}.
	 * </p>
	 * 
	 * @param templateEngine
	 *          The {@link ITemplate} that has been removed.
	 */
	private void fireTemplateEngineRemoved(ITemplateEngine templateEngine) {

		TemplateEngineRegistryEvent event = null;

		if (this.listeners != null) {

			for (ITemplateEngineRegistryListener listener : getListeners()) {

				/* Lazily create the event. */
				if (event == null) {
					event = new TemplateEngineRegistryEvent(this, templateEngine);
				}
				// no else.

				listener.templateEngineRemoved(event);
			}
			// end for.
		}
		// no else.
	}

	/**
	 * @see org.dresdenocl.tools.template.ITemplateEngineRegistry#removeTemplateEngineRegistryListener(ITemplateEngineRegistryListener)
	 * 
	 */
	public void removeTemplateEngineRegistryListener(
			ITemplateEngineRegistryListener listener) {

		if (this.listeners != null) {
			this.listeners.remove(listener);
		}
		// no else.
	}

	/**
	 * <p>
	 * A helper method that lazily creates a list of
	 * {@link ITemplateEngineRegistryListener}.
	 * </p>
	 */
	private List<ITemplateEngineRegistryListener> getListeners() {

		if (this.listeners == null) {
			this.listeners = new LinkedList<ITemplateEngineRegistryListener>();
		}
		// no else.

		return this.listeners;
	}

}
