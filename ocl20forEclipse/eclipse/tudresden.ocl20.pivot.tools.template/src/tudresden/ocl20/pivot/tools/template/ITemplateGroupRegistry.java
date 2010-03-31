package tudresden.ocl20.pivot.tools.template;

import java.util.List;

import tudresden.ocl20.pivot.tools.template.event.ITemplateGroupRegistryListener;

/**
 * <p>
 * The {@link ITemplateGroupRegistry} manages a list of {@link ITemplateGroup}s that are
 * currently available.
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
	 * @param templateGroup
	 *          The {@link ITemplateGroup} that shall be added.
	 */
	public void addTemplateGroup(ITemplateGroup templateGroup);
	
	/**
	 * <p>
	 * Returns an {@link ITemplateGroup} with the given name or <code>null</code> if no
	 * {@link ITemplateGroup} with that name is registered.
	 * </p>
	 * 
	 * @param templateGroupName
	 *          An identifier name for an {@link ITemplateGroup}.
	 * 
	 * @return A new {@link ITemplateGroup} instance or <code>null</code>.
	 */
	public ITemplateGroup getTemplateGroup(String templateGroupName);
	
	/**
	 * <p>
	 * Removes an {@link ITemplatGroup} from this {@link ITemplateGroupRegistry}.
	 * </p>
	 * @param templateGroup
	 *          The {@link ITemplateGroup} that shall be removed.
	 */
	public void removeTemplateGroup(ITemplateGroup templateGroup);

	/**
	 * <p>
	 * Removes an {@link ITemplateGroup} from this {@link ITemplateGroupRegistry}.
	 * </p>
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
	 * Adds an {@link ITemplateGroupRegistryListener} to this {@link ITemplateGroupRegistry}.
	 * </p>
	 * 
	 * @param listener
	 *          The {@link ITemplateGroupRegistryListener} to be added.
	 */
	void addTemplateGroupRegistryListener(ITemplateGroupRegistryListener listener);

	/**
	 * <p>
	 * Removes an {@link ITemplateGroupRegistryListener} from this {@link ITemplateGroupRegistry}.
	 * </p>
	 * 
	 * @param listener
	 *          The {@link ITemplateGroupRegistryListener} to be removed.
	 */
	void removeTemplateGroupRegistryListener(ITemplateGroupRegistryListener listener);

}
