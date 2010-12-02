package tudresden.ocl20.pivot.tools.codegen.declarativ.code;

public class SQLString implements ISQLCode {

	private String code;

	public SQLString(String text) {

		code = text;
	}

	public void addElement(String text, ISQLCode code) {

	}

	public void changeElement(String text, ISQLCode code) {

	}

	public String getResult() {

		return toString();
	}

	public ISQLCode getElement(String name) {

		return new SQLString(code);
	}

	public String toString() {

		return code;
	}

	public String getTemplateName() {

		return "";
	}

}
