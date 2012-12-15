package org.dresdenocl.tools.template;

import java.util.List;

import org.dresdenocl.tools.template.event.ITemplateGroupRegistryListener;
import org.dresdenocl.tools.template.exception.TemplateException;

/**
 * <p>
 * The {@link ITemplateGroupRegistry} manages a list of {@link ITemplateGroup}s
 * that are currently available.
 * </p>
 * 
 * @author Bjoern Freitag
 * 
 */
public interface ITemplateGroupRegistry {

	/**
	 * <p>
	 * Adds an {@link ITemplateGroup} to this {@link ITemplateGroupRegistry}.
	 * </p>
	 * 
	 * @param templateGroup
	 *          The {@link ITemplateGroup} that shall be added.
	 */
	public void addTemplateGroup(ITemplateGroup templateGroup)
			throws TemplateException;

	/**
	 * <p>
	 * Adds an {@link ITemplateGroup} to this {@link ITemplateGroupRegistry} and
	 * return this. The template group use as template engine the String Template.
	 * </p>
	 * 
	 * @param templateName
	 *          The template group name.
	 * @param superGroup
	 *          the super template group of the new template group
	 * @return The new added template group
	 * @throws TemplateException
	 *           if there no template engine or gives a template group with the
	 *           name
	 */
	public ITemplateGroup addDefaultTemplateGroup(String templateName,
			ITemplateGroup superGroup) throws TemplateException;

	/**
	 * <p>
	 * Adds an {@link ITemplateGroup} to this {@link ITemplateGroupRegistry} and
	 * return this. The template group use as template engine the String Template.
	 * </p>
	 * 
	 * @param templateName
	 *          The template group name.
	 * @param templateEngineName
	 *          The template engine name.
	 * @param superGroup
	 *          the super template group of the new template group
	 * @return The new added template group
	 * @throws TemplateException
	 *           if there no template engine or gives a template group with the
	 *           name
	 */
	public ITemplateGroup addDefaultTemplateGroup(String templateName,
			String templateEngineName, ITemplateGroup superGroup)
			throws TemplateException;

	/**
	 * <p>
	 * Returns an {@link ITemplateGroup} with the given name.
	 * </p>
	 * 
	 * @param templateGroupName
	 *          An identifier name for an {@link ITemplateGroup}.
	 * 
	 * @return A new {@link ITemplateGroup}
	 * @throws TemplateException
	 *           if templateGroupName null or no templateGroup with this name
	 *           exists.
	 */
	public ITemplateGroup getTemplateGroup(String templateGroupName)
			throws TemplateException;

	/**
	 * <p>
	 * Removes an {@link ITemplatGroup} from this {@link ITemplateGroupRegistry}.
	 * </p>
	 * 
	 * @param templateGroup
	 *          The {@link ITemplateGroup} that shall be removed.
	 */
	public void removeTemplateGroup(ITemplateGroup templateGroup);

	/**
	 * <p>
	 * Removes an {@link ITemplateGroup} from this {@link ITemplateGroupRegistry}.
	 * </p>
	 * 
	 * @param templateGroupName
	 *          An identifier name for an {@link ITemplateGroup}.
	 */
	public void removeTemplateGroup(String templateGroupName);

	/**
	 * <p>
	 * Disposes the {@link IModelRegistry}.
	 * </p>
	 */
	public void dispose();

	/**
	 * Returns a list of all {@link ITemplateGroup}s back.
	 * 
	 * @return a List of all {@link ITemplateGroup}s.
	 */
	public List<ITemplateGroup> getTemplateGroups();

	/**
	 * <p>
	 * Adds an {@link ITemplateGroupRegistryListener} to this
	 * {@link ITemplateGroupRegistry}.
	 * </p>
	 * 
	 * @param listener
	 *          The {@link ITemplateGroupRegistryListener} to be added.
	 */
	void addTemplateGroupRegistryListener(ITemplateGroupRegistryListener listener);

	/**
	 * <p>
	 * Removes an {@link ITemplateGroupRegistryListener} from this
	 * {@link ITemplateGroupRegistry}.
	 * </p>
	 * 
	 * @param listener
	 *          The {@link ITemplateGroupRegistryListener} to be removed.
	 */
	void removeTemplateGroupRegistryListener(
			ITemplateGroupRegistryListener listener);

}
