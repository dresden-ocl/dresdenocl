package org.dresdenocl.tools.template.test.tests;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.dresdenocl.tools.template.ITemplate;
import org.dresdenocl.tools.template.ITemplateEngine;
import org.dresdenocl.tools.template.exception.TemplateException;

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
	 * @see org.dresdenocl.tools.template.ITemplateEngine#addFile(URL)
	 */
	public void addFile(String file) throws TemplateException {

	}

	/**
	 * @see org.dresdenocl.tools.template.ITemplateEngine#addFiles(LinkedList)
	 */
	public void addFiles(List<String> files) throws TemplateException {

	}

	/**
	 * @see org.dresdenocl.tools.template.ITemplateEngine#getDisplayName()
	 */
	public String getDisplayName() {

		return "otherTemplateEngine";
	}

	/**
	 * @see org.dresdenocl.tools.template.ITemplateEngine#getTemplate(String)
	 */
	public ITemplate getTemplate(String name) {

		return null;
	}

	/**
	 * @see org.dresdenocl.tools.template.ITemplateEngine#getVersion()
	 */
	public String getVersion() {

		return "1.2";
	}

}
