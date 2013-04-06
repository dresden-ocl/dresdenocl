package org.dresdenocl.tools.codegen;

public interface IOcl2CodeSettings {

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
	public int getSaveCode();

	/**
	 * <p>
	 * Enables whether or not the transformed code shall be saved into files.
	 * </p>
	 */
	public void setSaveCode(int saveCode);

}
