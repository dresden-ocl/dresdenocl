package tudresden.ocl20.pivot.tools.codegen.declarativ.code;

public interface ISQLCode {

	public void addElement(String name, ISQLCode code);

	public void changeElement(String name, ISQLCode code);

	public String getResult();

	public ISQLCode getElement(String name);

	public String getTemplateName();

}
