/*
 * Created on 25.01.2006
 *
 */
package tudresden.ocl20.transformation.templates;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import tudresden.ocl20.codegen.decl.template.TemplateEngine;
import tudresden.ocl20.transformation.TransformationEngine;
import tudresden.ocl20.transformation.exception.TemplateException;

public class StringTemplateEngineAdapter implements TemplateEngine {
	
	public static final String MY_SQL = "MySQL";
	public static final String POSTGRES = "PostgreSQL 8.1";
	public static final String STANDARD = "Standard";
	public static final String ORACLE = "Oracle 8i";
	
	private static final String ERROR_LOAD_FILE = null;
	private StringTemplateGroup group;
	
	
	
	public StringTemplateEngineAdapter(String destLanguage) throws TemplateException {
		 String templatePath = "";
		 templatePath = TransformationEngine.getInstance().getResourcePath() + "templates/"+destLanguage+".stg";
	     System.out.println(" Search in " + templatePath);
	    try {	
	   	 FileReader fReader = new FileReader(new java.io.File(templatePath));
	   	 group = new StringTemplateGroup(fReader);
	    	 
	     } catch (FileNotFoundException e) {
			 throw new TemplateException(ERROR_LOAD_FILE);
		 } 
		 
	}
		
	public StringTemplateAdapter getTemplate(String name) {
		StringTemplate template = group.getInstanceOf(name);
		return new StringTemplateAdapter(template);
	}
}
