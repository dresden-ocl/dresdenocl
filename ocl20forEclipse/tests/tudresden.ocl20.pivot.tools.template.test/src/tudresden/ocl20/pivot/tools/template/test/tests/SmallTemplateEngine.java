package tudresden.ocl20.pivot.tools.template.test.tests;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import tudresden.ocl20.pivot.tools.template.ITemplate;
import tudresden.ocl20.pivot.tools.template.ITemplateEngine;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;

/**
 * A helper class for the template tests.
 * 
 * @author Bjoern Freitag
 * 
 */
public class SmallTemplateEngine implements ITemplateEngine {

	/**
	 * A constructor
	 */
	public SmallTemplateEngine() {

	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateEngine#addFile(URL)
	 */
	public void addFile(String file) throws TemplateException {

	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateEngine#addFiles(LinkedList)
	 */
	public void addFiles(List<String> files) throws TemplateException {

	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateEngine#getDisplayName()
	 */
	public String getDisplayName() {

		return "otherTemplateEngine";
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateEngine#getTemplate(String)
	 */
	public ITemplate getTemplate(String name) {

		return null;
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateEngine#getVersion()
	 */
	public String getVersion() {

		return "1.2";
	}

}
