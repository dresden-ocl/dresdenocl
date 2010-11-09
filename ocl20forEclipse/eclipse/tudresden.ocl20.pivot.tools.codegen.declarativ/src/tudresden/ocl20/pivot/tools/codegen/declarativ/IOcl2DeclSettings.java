package tudresden.ocl20.pivot.tools.codegen.declarativ;

import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.tools.codegen.IOcl2CodeSettings;
import tudresden.ocl20.pivot.tools.codegen.declarativ.mapping.IMappedModel;
import tudresden.ocl20.pivot.tools.template.ITemplateGroup;

public interface IOcl2DeclSettings extends IOcl2CodeSettings {

	/**
	 * The modus for one table of every subclasses.
	 */
	public static final int MODUS_TYPED = 1;

	/**
	 * The modus every class have his own table for the own property.
	 */
	public static final int MODUS_VERTICAL = 2;

	/**
	 * Set the mapped model fo generate the sql code.
	 * 
	 * @param mappedModel
	 *          the mapped model of the pivotmodel
	 */
	public void setMappedModel(IMappedModel mappedModel);

	/**
	 * Gives the actual mapped model back.
	 * 
	 * @return the actual mapped model
	 */
	public IMappedModel getMappedModel();

	/**
	 * The modus of class inheritance
	 * 
	 * @param modus
	 *          The modus of class inheritance
	 */
	public void setModus(int modus);

	/**
	 * Returns the actual modus.
	 * 
	 * @return the modus
	 */
	public int getModus();

	/**
	 * Set the table prefix of data schema.
	 * 
	 * @param tablePrefix
	 *          the prefix
	 */
	public void setTablePrefix(String tablePrefix);

	/**
	 * Returns the actual table prefix.
	 * 
	 * @return the table prefix
	 */
	public String getTablePrefix();

	/**
	 * Set a new view prefix
	 * 
	 * @param objectViewPrefix
	 *          the prefix
	 */
	public void setObjectViewPrefix(String objectViewPrefix);

	/**
	 * Returns the actual view prefix
	 * 
	 * @return the prefix
	 */
	public String getObjectViewPrefix();

	/**
	 * Set a new association table prefix
	 * 
	 * @param associationTablePrefix
	 *          the prefix
	 */
	public void setAssociationTablePrefix(String associationTablePrefix);

	/**
	 * Returns the actual association table prefix
	 * 
	 * @return the prefix
	 */
	public String getAssociationTablePrefix();

	/**
	 * Set a new primary key prefix
	 * 
	 * @param primaryKeyPrefix
	 *          the prefix
	 */
	public void setPrimaryKeyPrefix(String primaryKeyPrefix);

	/**
	 * Returns the actual primary key prefix
	 * 
	 * @return the prefix
	 */
	public String getPrimaryKeyPrefix();

	/**
	 * Set a new foreign key prefix
	 * 
	 * @param foreignKeyPrefix
	 *          the prefix
	 */
	public void setForeignKeyPrefix(String foreignKeyPrefix);

	/**
	 * Returns the actual foreign key prefix
	 * 
	 * @return the prefix
	 */
	public String getForeignKeyPrefix();

	/**
	 * Set a new view prefix
	 * 
	 * @param objectViewPrefix
	 *          the prefix
	 */
	public String getUniqueAssociationTableName(Property property);

	/**
	 * 
	 * @return The {@link ITemplateGroup}, where the templates and template engine
	 *         are saved.
	 */
	public ITemplateGroup getTemplateGroup();

	/**
	 * <p>
	 * Sets the The template group, where TemplateEngine und Template are saved.
	 * </p>
	 * 
	 * @param templateGroup
	 *          The template group, where the Templates are saved.
	 */
	public void setTemplateGroup(ITemplateGroup templateGroup);

}
