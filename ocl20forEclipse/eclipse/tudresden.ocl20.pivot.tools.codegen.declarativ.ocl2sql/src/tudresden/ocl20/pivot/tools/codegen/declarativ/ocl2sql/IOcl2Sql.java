package tudresden.ocl20.pivot.tools.codegen.declarativ.ocl2sql;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.tools.codegen.IOcl2Code;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclCode;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclSettings;

public interface IOcl2Sql extends IOcl2DeclCode, IOcl2Code<IOcl2DeclSettings> {

	public void setInputModel(IModel inputModel);

}
