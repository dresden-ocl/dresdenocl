package org.dresdenocl.tools.codegen.declarativ.code.impl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.dresdenocl.tools.codegen.declarativ.IOcl2DeclSettings;
import org.dresdenocl.tools.codegen.declarativ.code.ICode;
import org.dresdenocl.tools.codegen.declarativ.code.IComplexCode;
import org.dresdenocl.tools.codegen.declarativ.mapping.Guide;
import org.dresdenocl.tools.template.ITemplate;

public class Code implements IComplexCode {

	private ITemplate template;

	private final static String aliasName = "temp";

	private final static String selfName = "self";

	private final static String[] aliase = { "alias", "alias1", "alias2" };

	private Map<String, List<ICode>> attributes;
	
	private Guide guide;

	private final static List<String> aliasList = new LinkedList<String>();
	
	public Code(IComplexCode code,String templateName, IOcl2DeclSettings mySettings) {
		this(templateName,mySettings);
		if (code instanceof Code) {
			attributes.putAll(((Code)code).attributes);
			this.guide = ((Code)code).guide;
			guide.reset();
		}
		
	}
	
	public Code (String templateName, IOcl2DeclSettings mySettings,Guide guide) {
		this(templateName,mySettings);
		this.guide = guide;
	}
	
	public Code (String templateName, IOcl2DeclSettings mySettings) {
		ITemplate template = mySettings.getTemplateGroup().getTemplate(templateName);
		if (template == null)
			throw new NullPointerException();
		setTemplate(template);
		attributes = new TreeMap<String, List<ICode>>();
		guide = null;
	}
	
	@Deprecated
	public Code(ITemplate template) {
		if (template == null)
			throw new NullPointerException();
		setTemplate(template);
		attributes = new TreeMap<String, List<ICode>>();
		guide = null;
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

	public void addCode(String text, ICode code) {

		if (attributes.containsKey(text)) {
			attributes.get(text).add(code);
		}
		else {
			changeCode(text, code);
		}

	}

	public void changeCode(String text, ICode code) {

		List<ICode> codes = new LinkedList<ICode>();
		codes.add(code);
		attributes.put(text, codes);

	}

	public String toString() {

		template.reset();
		for (String s : attributes.keySet()) {
			for (ICode code : attributes.get(s)) {
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

	public IComplexCode getComplexCode(String name) {

		if (!attributes.containsKey(name) || !(attributes.get(name).get(0) instanceof IComplexCode))
			return null;
		return (IComplexCode)attributes.get(name).get(0);
	}
	
	public ICode getCode(String name) {

		if (!attributes.containsKey(name))
			return null;
		return attributes.get(name).get(0);
	}

	protected void readAllAlias() {

		for (String s : aliase) {
			if (attributes.containsKey(s)) {
				if (getCode(s) != null
						&& !aliasList.contains(getCode(s).getResult())) {
					if (attributes.get(s).size() != 0) {
						if (!getCode(s).toString().equals(selfName))
							aliasList.add(getCode(s).toString());
					}
				}
			}
		}
		for (List<ICode> codes : attributes.values()) {
			for (ICode code : codes) {
				if (code instanceof Code)
					((Code) code).readAllAlias();
			}
		}
	}

	public void moveCode(String oldName, String newName) {
		attributes.put(newName, removeCode(oldName));
	}

	public List<ICode> removeCode(String name) {
		return attributes.remove(name);
	}

	public ICode getAlias() {
		return getCode("alias");
	}

	public ICode getWhere() {
		if (guide != null) {
			return new CodeString(guide.getWhere());
		}
		return null;
	}

	public ICode getSelect() {
		if (guide != null) {
			return new CodeString(guide.getSelect());
		}
		return null;
	}

	public ICode getFrom() {
		if (guide != null) {
			return new CodeString(guide.getFrom());
		}
		return null;
	}


}
