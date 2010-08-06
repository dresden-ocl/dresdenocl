package tudresden.ocl20.pivot.tools.codegen;

import tudresden.ocl20.pivot.tools.template.ITemplateGroup;

public interface IOcl2CodeSettings {

	/**
	 * 
	 * @return The {@link ITemplateGroup}, where the templates and template engine
	 *         are saved.
	 */
	public ITemplateGroup getTemplateGroup();

	/**
	 * @return The location, where transformed constraints shall be saved
	 *         relatively to the constrained class.
	 */
	public String getConstraintDirectory();

	/**
	 * @return The Location, where transformed code shall be saved.
	 */
	public String getSourceDirectory();

	/**
	 * <p>
	 * Sets the The template group, where TemplateEngine und Template are saved.
	 * </p>
	 * 
	 * @param templateGroup
	 *          The template group, where the Templates are saved.
	 */
	public void setTemplateGroup(ITemplateGroup templateGroup);

	/**
	 * <p>
	 * Sets the The location, where transformed constraints shall be saved
	 * relatively to the constrained class.
	 * </p>
	 * 
	 * @param folderName
	 *          The location, where transformed constraints shall be saved
	 *          relatively to the constrained class.
	 */
	public void setConstraintDirectory(String folderName);

	/**
	 * <p>
	 * Sets the Location, where transformed code shall be saved.
	 * </p>
	 * 
	 * @param path
	 *          The Location, where transformed code shall be saved.
	 */
	public void setSourceDirectory(String path);

	/**
	 * @return Whether or not the transformed code shall be saved into files.
	 */
	public boolean isSaveCode();

	/**
	 * <p>
	 * Enables whether or not the transformed code shall be saved into files.
	 * </p>
	 */
	public void setSaveCode(boolean saveCode);

}
