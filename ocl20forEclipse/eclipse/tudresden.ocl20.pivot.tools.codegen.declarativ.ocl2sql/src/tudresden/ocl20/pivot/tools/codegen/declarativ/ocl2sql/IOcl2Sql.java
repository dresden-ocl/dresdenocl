package tudresden.ocl20.pivot.tools.codegen.declarativ.ocl2sql;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclCode;

public interface IOcl2Sql extends IOcl2DeclCode {

	public void setInputModel(IModel inputModel);

}
