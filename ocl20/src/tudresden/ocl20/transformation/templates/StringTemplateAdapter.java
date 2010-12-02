package tudresden.ocl20.transformation.templates;

import org.antlr.stringtemplate.StringTemplate;

import tudresden.ocl20.codegen.decl.template.Template;

public class StringTemplateAdapter implements Template {

	private StringTemplate boxedTemplate;
	
	public StringTemplateAdapter(StringTemplate template) {
		this.boxedTemplate = template;
	}
	
	public void setAttribute(String name, String value) {
		boxedTemplate.setAttribute(name, value);
	}

	public String toString() {
		return boxedTemplate.toString();
	}

//	public void setAttribute(String name, Object values) {
//		boxedTemplate.setAttribute(name, values);
//		
//	}
//	
}
