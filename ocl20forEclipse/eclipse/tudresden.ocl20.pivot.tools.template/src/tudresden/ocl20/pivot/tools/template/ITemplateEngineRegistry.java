package tudresden.ocl20.pivot.tools.template;

import java.util.List;

import tudresden.ocl20.pivot.tools.template.event.ITemplateEngineRegistryListener;

/**
 * <p>
 * The {@link ITemplateEngineRegistry} manages a list of {@link ITemplateEngine}
 * s that are currently available.
 * </p>
 * 
 * @author Bjoern Freitag
 * 
 */
public interface ITemplateEngineRegistry {

	/**
	 * <p>
	 * Adds an {@link ITemplateEngine} to this {@link ITemplateEngineRegistry}.
	 * </p>
	 * 
	 * @param templateEngine
	 *          The {@link ITemplateEngine} that shall be added.
	 */
	public void addTemplateEngine(ITemplateEngine templateEngine);

	/**
	 * <p>
	 * Returns a new instance of the {@link ITemplateEngine} with the given name
	 * or <code>null</code> if no {@link ITemplateEngine} with that name is
	 * registered.
	 * </p>
	 * 
	 * @param templateEngineName
	 *          An identifier name for an {@link ITemplateEngine}.
	 * 
	 * @return A new {@link ITemplateEngine} instance or <code>null</code>.
	 */
	public ITemplateEngine getNewTemplateEngine(String templateEngineName);

	/**
	 * <p>
	 * Removes an {@link ITemplateEngine} from this
	 * {@link ITemplateEngineRegistry}.
	 * </p>
	 * 
	 * @param templateEngine
	 *          The {@link ITemplateEngine} that shall be removed.
	 */
	public void removeTemplateEngine(ITemplateEngine templateEngine);

	/**
	 * <p>
	 * Removes an {@link ITemplateEngine} from this
	 * {@link ITemplateEngineRegistry}.
	 * </p>
	 * 
	 * @param templateEngineName
	 *          An identifier name for an {@link ITemplateEngine}.
	 */
	public void removeTemplateEngine(String templateEngineName);

	/**
	 * <p>
	 * Disposes the {@link ITemplateEngineRegistry}.
	 * </p>
	 */
	public void dispose();

	/**
	 * Returns a list of all {@link ITemplateEngine}s back.
	 * 
	 * @return a List of all {@link ITemplateEngine}s.
	 */
	public List<ITemplateEngine> getTemplateEngines();

	/**
	 * <p>
	 * Adds an {@link ITemplateEngineRegistryListener} to this
	 * {@link ITemplateEngineRegistry}.
	 * </p>
	 * 
	 * @param listener
	 *          The {@link ITemplateEngineRegistryListener} to be added.
	 */
	void addTemplateEngineRegistryListener(
			ITemplateEngineRegistryListener listener);

	/**
	 * <p>
	 * Removes an {@link ITemplateEngineRegistryListener} from this
	 * {@link ITemplateEngineRegistry}.
	 * </p>
	 * 
	 * @param listener
	 *          The {@link ITemplateEngineRegistryListener} to be removed.
	 */
	void removeTemplateEngineRegistryListener(
			ITemplateEngineRegistryListener listener);

}
