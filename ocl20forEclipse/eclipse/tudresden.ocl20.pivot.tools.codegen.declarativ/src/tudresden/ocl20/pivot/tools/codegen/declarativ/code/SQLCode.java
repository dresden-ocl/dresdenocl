package tudresden.ocl20.pivot.tools.codegen.declarativ.code;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import tudresden.ocl20.pivot.tools.template.ITemplate;

public class SQLCode implements ISQLCode {

	private ITemplate template;

	private final static String aliasName = "temp";

	private final static String selfName = "self";

	private final static String[] aliase = { "alias", "alias1", "alias2" };

	private Map<String, List<ISQLCode>> attributes;

	private final static List<String> aliasList = new LinkedList<String>();;

	public SQLCode(ITemplate template) {

		if (template == null)
			throw new NullPointerException();
		setTemplate(template);
		attributes = new TreeMap<String, List<ISQLCode>>();
	}

	public String getTemplateName() {

		return template.getName();
	}

	public String getResult() {

		this.readAllAlias();
		String result = toString();
		aliasList.clear();
		return result;
	}

	private String readAlias(String name) {

		if (aliasList.contains(name))
			return aliasName + (aliasList.indexOf(name) + 1);
		else
			return name;
	}

	public void setTemplate(ITemplate template) {

		this.template = template;

	}

	public void addElement(String text, ISQLCode code) {

		if (attributes.containsKey(text)) {
			attributes.get(text).add(code);
		}
		else {
			changeElement(text, code);
		}

	}

	public void changeElement(String text, ISQLCode code) {

		List<ISQLCode> codes = new LinkedList<ISQLCode>();
		codes.add(code);
		attributes.put(text, codes);

	}

	public String toString() {

		template.reset();
		for (String s : attributes.keySet()) {
			for (ISQLCode code : attributes.get(s)) {
				if (code != null) {
					String text = code.toString();
					if (text != null) {
						if (Arrays.asList(aliase).contains(s)) {
							template.setAttribute(s, readAlias(text));
						}
						else {
							template.setAttribute(s, text);
						}
					}
				}
			}
		}
		return template.toString();
	}

	public ISQLCode getElement(String name) {

		if (!attributes.containsKey(name))
			return null;
		return attributes.get(name).get(0);
	}

	protected void readAllAlias() {

		for (String s : aliase) {
			if (attributes.containsKey(s)) {
				if (getElement(s) != null
						&& !aliasList.contains(getElement(s).getResult())) {
					if (attributes.get(s).size() != 0) {
						if (!getElement(s).toString().equals(selfName))
							aliasList.add(getElement(s).toString());
					}
				}
			}
		}
		for (List<ISQLCode> codes : attributes.values()) {
			for (ISQLCode code : codes) {
				if (code instanceof SQLCode)
					((SQLCode) code).readAllAlias();
			}
		}
	}

}
