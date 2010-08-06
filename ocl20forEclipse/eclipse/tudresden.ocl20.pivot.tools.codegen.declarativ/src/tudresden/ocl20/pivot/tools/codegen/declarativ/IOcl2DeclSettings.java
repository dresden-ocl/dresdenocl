package tudresden.ocl20.pivot.tools.codegen.declarativ;

import tudresden.ocl20.pivot.tools.codegen.IOcl2CodeSettings;
import tudresden.ocl20.pivot.tools.codegen.declarativ.mapping.IMappedModel;

public interface IOcl2DeclSettings extends IOcl2CodeSettings {

	public static final int MODUS_TYPED = 1;
	public static final int MODUS_VERTICAL = 2;

	public void setMappedModel(IMappedModel mappedModel);

	public IMappedModel getMappedModel();

	public void setModus(int modus);

	public int getModus();

	public void setTablePrefix(String tablePrefix);

	public String getTablePrefix();

	public void setObjectViewPrefix(String objectViewPrefix);

	public String getObjectViewPrefix();

	public void setAssociationTablePrefix(String associationTablePrefix);

	public String getAssociationTablePrefix();

	public void setPrimaryKeyPrefix(String primaryKeyPrefix);

	public String getPrimaryKeyPrefix();

	public void setForeignKeyPrefix(String foreignKeyPrefix);

	public String getForeignKeyPrefix();

}
