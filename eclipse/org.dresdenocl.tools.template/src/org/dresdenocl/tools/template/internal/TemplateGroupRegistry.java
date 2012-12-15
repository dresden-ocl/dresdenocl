package org.dresdenocl.tools.template.internal;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.Platform;

import org.dresdenocl.tools.template.ITemplate;
import org.dresdenocl.tools.template.ITemplateEngine;
import org.dresdenocl.tools.template.ITemplateGroup;
import org.dresdenocl.tools.template.ITemplateGroupRegistry;
import org.dresdenocl.tools.template.TemplatePlugin;
import org.dresdenocl.tools.template.event.ITemplateGroupRegistryListener;
import org.dresdenocl.tools.template.event.TemplateGroupRegistryEvent;
import org.dresdenocl.tools.template.exception.TemplateException;

/**
 * Default implementation of the {@link ITemplateGroupRegistry}
 * 
 * @author Bjoern Freitag
 * 
 */
public class TemplateGroupRegistry implements ITemplateGroupRegistry,
IRegistryEventListener {

	/** {@link Logger} for this class. */
	private static final Logger LOGGER = TemplatePlugin
			.getLogger(TemplateGroupRegistry.class);

	private static String DEFAULT_TEMPLATE_ENGINE = "StringTemplate";
	
	private static final String TEMPLATEGROUPS_EXTENSION_POINT_ID = TemplatePlugin.ID
			+ ".templateGroups";

	/**
	 * a map of all {@link ITemplateGroup}
	 */
	private Map<String, ITemplateGroup> templateGroups;

	/** A list of listeners. */
	private ListenerList listeners;

	/**
	 * The constructor of the class.
	 */
	public TemplateGroupRegistry() {

		this.templateGroups = new HashMap<String, ITemplateGroup>();
		
		IExtensionPoint extensionPoint = this.getExtensionPoint();
		if (extensionPoint != null) this.added(extensionPoint.getExtensions());

	}

	/**
	 * @see org.dresdenocl.tools.template.ITemplateGroupRegistry#addTemplateGroup(ITemplateGroup)
	 */
	public void addTemplateGroup(ITemplateGroup templateGroup)
			throws TemplateException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("addTemplateGroup(templateGroup=" + templateGroup + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		if (templateGroup == null) {
			throw new TemplateException(
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
	 * @see org.dresdenocl.tools.template.ITemplateGroupRegistry#dispose()
	 */
	public void dispose() {

		if (this.templateGroups.size() != 0) {
			this.templateGroups.clear();
		}

	}

	/**
	 * @see org.dresdenocl.tools.template.ITemplateGroupRegistry#getTemplateGroup(String)
	 */
	public ITemplateGroup getTemplateGroup(String templateGroupName)
			throws TemplateException {

		if (templateGroupName == null) {
			throw new TemplateException(
					"The parameter templateGroupName must not be null.");
		}
		// no else.
		if (this.templateGroups.containsKey(templateGroupName)) {
			return this.templateGroups.get(templateGroupName);
		}
		else {
			throw new TemplateException("No template group with this name exists.");
		}

	}

	/**
	 * @see org.dresdenocl.tools.template.ITemplateGroupRegistry#getTemplateGroups()
	 */
	public List<ITemplateGroup> getTemplateGroups() {

		List<ITemplateGroup> tempGroup = new ArrayList<ITemplateGroup>();
		for (ITemplateGroup tGroup : this.templateGroups.values()) {
			tempGroup.add(tGroup);
		}
		return tempGroup;
	}

	/**
	 * @see org.dresdenocl.tools.template.ITemplateGroupRegistry#removeTemplateGroup(ITemplateGroup)
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
	 * @see org.dresdenocl.tools.template.ITemplateGroupRegistry#removeTemplateGroup(String)
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
	 * @see org.dresdenocl.tools.template.ITemplateGroupRegistry#addTemplateGroupRegistryListener(ITemplateGroupRegistryListener)
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

			Object[] listeners;
			listeners = this.listeners.getListeners();

			for (int index = 0; index < listeners.length; index++) {

				/* Lazily create the event. */
				if (event == null) {
					event = new TemplateGroupRegistryEvent(this, templateGroup);
				}
				// no else.

				((ITemplateGroupRegistryListener) listeners[index])
						.templateGroupAdded(event);
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

			Object[] listeners;
			listeners = this.listeners.getListeners();

			for (int index = 0; index < listeners.length; index++) {

				/* Lazily create the event. */
				if (event == null) {
					event = new TemplateGroupRegistryEvent(this, templateGroup);
				}
				// no else.

				((ITemplateGroupRegistryListener) listeners[index])
						.templateGroupRemoved(event);
			}
			// end for.
		}
		// no else.
	}

	/**
	 * @see org.dresdenocl.tools.template.ITemplateGroupRegistry#removeTemplateGroupRegistryListener(ITemplateGroupRegistryListener)
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
	 * @see org.dresdenocl.tools.template.ITemplateGroupRegistry#addDefaultTemplateGroup(String,
	 *      ITemplateGroup)
	 */
	public ITemplateGroup addDefaultTemplateGroup(String templateName,
			ITemplateGroup superGroup) throws TemplateException {

		return addDefaultTemplateGroup(templateName, DEFAULT_TEMPLATE_ENGINE,
				superGroup);
	}

	/**
	 * @see org.dresdenocl.tools.template.ITemplateGroupRegistry#addDefaultTemplateGroup(String,
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
	
	public void added(IConfigurationElement configurationElement,IExtension extension) {
		if (configurationElement.getName().equals("templateresource")) {
			ITemplateGroup superGroup = null;
			try {
				superGroup = this.getTemplateGroup(configurationElement.getAttribute("supergroup"));
			} catch (InvalidRegistryObjectException e1) {
				//Do nothing
			} catch (TemplateException e1) {
				//Do nothing
			}
			List<String> streams = new LinkedList<String>();
			if (configurationElement.getAttribute("path") != null) {
				try {
					File file = new File(FileLocator.toFileURL(Platform
							.getBundle(extension.getNamespaceIdentifier())
							.getResource(configurationElement.getAttribute("path"))).getPath());
					if (file.isFile()) {
						streams.add(file.getPath());
					} else {
						File[] files = file.listFiles(new FilenameFilter() {
							@Override
							public boolean accept(File dir, String name) {
								return new File(dir, name).isFile()
										&& name.toLowerCase().endsWith(".stg");
							}
						});
						for (File dirFile : files) {
							streams.add(dirFile.getPath());
						}
					}
				} catch (InvalidRegistryObjectException e) {
					// Do nothing
				} catch (IOException e) {
					// Do nothing
				}
			}
			String name = configurationElement.getAttribute("name");
			try {
				ITemplateGroup group  = this.addDefaultTemplateGroup(name, superGroup);
				group.addFiles(streams);
			} catch (TemplateException e) {
				// Do nothing
			}
		} else if (configurationElement.getName().equals("templategroup")) {
			try {
				ITemplateGroup clazz = ITemplateGroup.class.cast(Platform.getBundle(
						extension.getNamespaceIdentifier()).loadClass(
					configurationElement.getAttribute("class")));
			this.addTemplateGroup(clazz);
			} catch (TemplateException e) {
				// Do nothing
			} catch (ClassNotFoundException e) {
				// Do nothing
			}
		}
	}

	@Override
	public void added(IExtension[] extensions) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("added(extensions=" + extensions + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		// no else.

			for (IExtension extension : extensions) {
				for (IConfigurationElement configurationElement : extension
						.getConfigurationElements()) {				
					added(configurationElement,extension);
					}
				// end for (configurationElements).		
			}
			// end for (extensions).	

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("added(IExtension[]) - exit"); //$NON-NLS-1$
		}
		// no else.

	}
	
	@Override
	public void removed(IExtension[] extensions) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("removed(extensions=" + extensions + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		// no else.

			for (IExtension extension : extensions) {
				for (IConfigurationElement configurationElement : extension
						.getConfigurationElements()) {				
					this.removeTemplateGroup(configurationElement.getAttribute("name"));
					}
				// end for (configurationElements).		
			}
			// end for (extensions).	

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("removed(IExtension[]) - exit"); //$NON-NLS-1$
		}
		// no else.

	}

	@Override
	public void added(IExtensionPoint[] extensionPoints) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removed(IExtensionPoint[] extensionPoints) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * <p>
	 * A helper method to get the worklist task {@link IExtensionPoint}.
	 * </p>
	 * 
	 * @return The {@link IMetamodel} {@link IExtensionPoint}.
	 */
	private IExtensionPoint getExtensionPoint() {

		IExtensionPoint result = null;

		if (Platform.getExtensionRegistry() != null) {
			/* Get the point from the registry. */
			result = Platform.getExtensionRegistry().getExtensionPoint(
					TEMPLATEGROUPS_EXTENSION_POINT_ID);

		}

		return result;
	}

}
