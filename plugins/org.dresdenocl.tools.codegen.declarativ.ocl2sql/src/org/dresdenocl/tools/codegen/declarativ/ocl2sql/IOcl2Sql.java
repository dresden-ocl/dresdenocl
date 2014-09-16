package org.dresdenocl.tools.codegen.declarativ.ocl2sql;

import org.dresdenocl.model.IModel;
import org.dresdenocl.tools.codegen.declarativ.IOcl2DeclCode;

public interface IOcl2Sql extends IOcl2DeclCode {

	public void setInputModel(IModel inputModel);

}
