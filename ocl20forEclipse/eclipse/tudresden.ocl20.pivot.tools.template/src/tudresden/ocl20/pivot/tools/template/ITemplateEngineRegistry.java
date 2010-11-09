package tudresden.ocl20.pivot.tools.template;

import java.util.List;

import tudresden.ocl20.pivot.tools.template.event.ITemplateEngineRegistryListener;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;

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
	 * Adds or Replace an {@link ITemplateEngine} to this
	 * {@link ITemplateEngineRegistry}.
	 * </p>
	 * 
	 * @param templateEngine
	 *          The {@link ITemplateEngine} that shall be added.
	 * 
	 * @throws TemplateException
	 *           if the templateEngine is null
	 */
	public void addTemplateEngine(ITemplateEngine templateEngine)
			throws TemplateException;

	/**
	 * <p>
	 * Returns a new instance of the {@link ITemplateEngine} with the given name.
	 * </p>
	 * 
	 * @param templateEngineName
	 *          An identifier name for an {@link ITemplateEngine}.
	 * 
	 * @return A new {@link ITemplateEngine} instance.
	 * @throws TemplateException
	 *           if no engine exists with this name.
	 */
	public ITemplateEngine getNewTemplateEngine(String templateEngineName)
			throws TemplateException;

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
