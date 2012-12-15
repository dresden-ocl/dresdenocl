package tudresden.ocl20.pivot.tools.codegen.declarativ.code;

import java.util.List;

public interface IComplexCode extends ICode {

	public void addCode(String name, ICode code);

	public void changeCode(String name, ICode code);
	
	public void moveCode(String oldName, String newName);
	
	public List<ICode> removeCode(String name);

	public String getResult();

	public IComplexCode getComplexCode(String name);
	
	public ICode getAlias();
	
	public ICode getWhere();
	
	public ICode getSelect();
	
	public ICode getFrom();

	public ICode getCode(String name);
	
	public String getTemplateName();

}
