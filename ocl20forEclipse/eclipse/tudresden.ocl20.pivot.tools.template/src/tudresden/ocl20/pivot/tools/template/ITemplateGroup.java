package tudresden.ocl20.pivot.tools.template;

import java.net.URL;
import java.util.List;

import tudresden.ocl20.pivot.tools.template.exception.TemplateException;

/**
 * The {@link ITemplateGroup} contains a {@link ITemplateEngine},
 * {@link ITemplate}s and a super {@link ITemplateGroup}. The files can
 * redefined {@link ITemplate}s from the super group. Are {@link ITemplates} not
 * defined in this group get it from the super group.
 * 
 * @author Bjoern Freitag
 * 
 */
public interface ITemplateGroup {

	/**
	 * <p>
	 * Returns an {@link ITemplate} with the given name or <code>null</code> if no
	 * {@link ITemplate} with that name is registered.
	 * </p>
	 * 
	 * @param name
	 *          An identifier name for an {@link ITemplate}.
	 * 
	 * @return A new {@link ITemplate} instance or <code>null</code>.
	 */
	public ITemplate getTemplate(String name);

	/**
	 * Returns the name of the {@link ITemplateGroup}.
	 * 
	 * @return the name of the TemplateGroup
	 */
	public String getDisplayName();

	/**
	 * Returns the super {@link ITemplateGroup} or <code>null</code> is there no
	 * super group.
	 * 
	 * @return the super {@link ITemplateGroup} or <code>null</null>
	 */
	public ITemplateGroup getSuperTemplateGroup();

	/**
	 * Added new template files to this {@link ITemplateGroup}.
	 * 
	 * @param files
	 *          A list of file paths
	 * @throws TemplateException
	 *           If the template engine can't read the files or is a file exits.
	 */
	public void addFiles(List<URL> files) throws TemplateException;

	/**
	 * Added a new template file to this {@link ITemplateGroup}.
	 * 
	 * @param file
	 *          full file path
	 * @throws TemplateException
	 *           If the template engine can't read the file or is the file exits.
	 */
	public void addFile(URL file) throws TemplateException;

}
