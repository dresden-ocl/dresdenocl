package tudresden.ocl20.pivot.tools.template.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.tools.template.ITemplate;
import tudresden.ocl20.pivot.tools.template.ITemplateEngine;
import tudresden.ocl20.pivot.tools.template.ITemplateGroup;
import tudresden.ocl20.pivot.tools.template.ITemplateGroupRegistry;
import tudresden.ocl20.pivot.tools.template.TemplatePlugin;
import tudresden.ocl20.pivot.tools.template.event.ITemplateGroupRegistryListener;
import tudresden.ocl20.pivot.tools.template.event.TemplateGroupRegistryEvent;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;
import tudresden.ocl20.pivot.tools.template.internal.TemplateGroup;

/**
 * <p>
 * A simple implementation of the {@link ITemplateGroupRegistry} interface that
 * internally just uses a {@link Map} to keep track of registered template
 * groups.
 * </p>
 * <p>
 * In a stand-alone application of DresdenOCL, this implies that new template
 * groups have to be added by hand to this registry.
 * </p>
 * 
 * @author Bjoern Freitag
 * 
 */
public class StandaloneTemplateGroupRegistry implements ITemplateGroupRegistry {

	/** {@link Logger} for this class. */
	private static final Logger LOGGER = TemplatePlugin
			.getLogger(StandaloneTemplateGroupRegistry.class);

	/**
	 * a map of all {@link ITemplateGroup}
	 */
	private Map<String, ITemplateGroup> templateGroups;

	/** A list of listeners. */
	private List<ITemplateGroupRegistryListener> listeners;

	/**
	 * The constructor of the class.
	 */
	public StandaloneTemplateGroupRegistry() {

		this.templateGroups = new HashMap<String, ITemplateGroup>();

	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateGroupRegistry#addTemplateGroup(ITemplateGroup)
	 */
	public void addTemplateGroup(ITemplateGroup templateGroup) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("addTemplateGroup(templateGroup=" + templateGroup + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		if (templateGroup == null) {
			throw new IllegalArgumentException(
					"The parameter 'templateGroup' must not be null."); //$NON-NLS-1$
		}
		// no else.

		/*
		 * Check if model is already contained in the registry; this is meant to be
		 * captured and dealt with on the UI, e.g., by showing an error message;
		 * this is better than silently do nothing.
		 */
		if (this.templateGroups.containsValue(templateGroup)) {
			LOGGER
					.warn("TemplateGroup '" + templateGroup.getDisplayName() + "' is already loaded. The templateGroup will be replaced."); //$NON-NLS-1$//$NON-NLS-2$
		}
		// no else.

		/* Add the model. */
		this.templateGroups.put(templateGroup.getDisplayName(), templateGroup);

		/* Inform listeners. */
		this.fireTemplateGroupAdded(templateGroup);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("addTemplateGroup() - exit"); //$NON-NLS-1$
		}
		// no else.
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateGroupRegistry#dispose()
	 */
	public void dispose() {

		if (this.templateGroups.size() != 0) {
			this.templateGroups.clear();
		}

	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateGroupRegistry#getTemplateGroup(String)
	 */
	public ITemplateGroup getTemplateGroup(String templateGroupName) {

		if (templateGroupName == null) {
			throw new IllegalArgumentException(
					"The parameter templateGroupName must not be null.");
		}
		// no else.
		return this.templateGroups.get(templateGroupName);
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateGroupRegistry#getTemplateGroups()
	 */
	public List<ITemplateGroup> getTemplateGroups() {

		List<ITemplateGroup> tempGroup = new ArrayList<ITemplateGroup>();
		for (ITemplateGroup tGroup : this.templateGroups.values()) {
			tempGroup.add(tGroup);
		}
		return tempGroup;
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateGroupRegistry#removeTemplateGroup(ITemplateGroup)
	 */
	public void removeTemplateGroup(ITemplateGroup templateGroup) {

		if (templateGroup == null) {
			throw new IllegalArgumentException(
					"The parameter templateGroupName must not be null.");
		}
		// no else.

		this.templateGroups.remove(templateGroup.getDisplayName());

		this.fireTemplateGroupRemoved(this.templateGroups.remove(templateGroup
				.getDisplayName()));
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateGroupRegistry#removeTemplateGroup(String)
	 */
	public void removeTemplateGroup(String templateGroupName) {

		if (templateGroupName == null) {
			throw new IllegalArgumentException(
					"The parameter templateGroupName must not be null.");
		}
		// no else.

		this.templateGroups.remove(templateGroupName);
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateGroupRegistry#addTemplateGroupRegistryListener(ITemplateGroupRegistryListener)
	 */
	public void addTemplateGroupRegistryListener(
			ITemplateGroupRegistryListener listener) {

		this.getListeners().add(listener);

	}

	/**
	 * <p>
	 * A helper method that informs all listeners about an added
	 * {@link ITemplateGroup}.
	 * </p>
	 * 
	 * @param templateGroup
	 *          The {@link ITemplateGroup} that has been added.
	 */
	private void fireTemplateGroupAdded(ITemplateGroup templateGroup) {

		TemplateGroupRegistryEvent event;
		event = null;

		if (this.listeners != null) {

			for (ITemplateGroupRegistryListener listener : getListeners()) {

				/* Lazily create the event. */
				if (event == null) {
					event = new TemplateGroupRegistryEvent(this, templateGroup);
				}
				// no else.

				listener.templateGroupAdded(event);
			}
			// end for.
		}
		// no else.
	}

	/**
	 * <p>
	 * A helper method that informs all listeners about a removed
	 * {@link ITemplateGroup}.
	 * </p>
	 * 
	 * @param templateGroup
	 *          The {@link ITemplate} that has been removed.
	 */
	private void fireTemplateGroupRemoved(ITemplateGroup templateGroup) {

		TemplateGroupRegistryEvent event = null;

		if (this.listeners != null) {

			for (ITemplateGroupRegistryListener listener : getListeners()) {

				/* Lazily create the event. */
				if (event == null) {
					event = new TemplateGroupRegistryEvent(this, templateGroup);
				}
				// no else.

				listener.templateGroupRemoved(event);
			}
			// end for.
		}
		// no else.
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateGroupRegistry#removeTemplateGroupRegistryListener(ITemplateGroupRegistryListener)
	 * 
	 */
	public void removeTemplateGroupRegistryListener(
			ITemplateGroupRegistryListener listener) {

		if (this.listeners != null) {
			this.listeners.remove(listener);
		}
		// no else.
	}

	/**
	 * <p>
	 * A helper method that lazily creates the a list of
	 * {@link ITemplateGroupRegistryListener}.
	 * </p>
	 */
	private List<ITemplateGroupRegistryListener> getListeners() {

		if (this.listeners == null) {
			this.listeners = new LinkedList<ITemplateGroupRegistryListener>();
		}
		// no else.

		return this.listeners;
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateGroupRegistry#addDefaultTemplateGroup(String,
	 *      String, ITemplateGroup)
	 */
	public ITemplateGroup addDefaultTemplateGroup(String templateName,
			String templateEngineName, ITemplateGroup superGroup)
			throws TemplateException {

		ITemplateEngine templateEngine =
				TemplatePlugin.getTemplateEngineRegistry().getNewTemplateEngine(
						templateEngineName);
		if (templateEngine == null)
			throw new TemplateException("No template engine with this name");
		if (templateGroups.containsKey(templateName))
			throw new TemplateException("The template group name is existing.");
		ITemplateGroup returnGroup =
				new TemplateGroup(templateName, superGroup, templateEngine);
		this.addTemplateGroup(returnGroup);
		return returnGroup;
	}

}
