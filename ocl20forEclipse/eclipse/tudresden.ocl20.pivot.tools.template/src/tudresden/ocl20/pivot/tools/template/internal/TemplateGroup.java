package tudresden.ocl20.pivot.tools.template.internal;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import tudresden.ocl20.pivot.tools.template.ITemplate;
import tudresden.ocl20.pivot.tools.template.ITemplateEngine;
import tudresden.ocl20.pivot.tools.template.ITemplateGroup;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;

/**
 * A default implementation of the {@link ITemplateGroup}
 * 
 * @author Bjoern Freitag
 *
 */
public class TemplateGroup implements ITemplateGroup {

	/**
	 * The name of the template group
	 */
	protected String name;
	
	/**
	 * the super group of this template group
	 */
	protected ITemplateGroup superGroup;
	
	/**
	 * the template engine of the super group
	 */
	protected ITemplateEngine templateEngine;
	
	/**
	 * the full path file list
	 */
	protected List<URL> files;
	
	
	/**
	 * The constructor for a new Template group.
	 * 	
	 * @param name
	 * 			the name of the template group
	 * @param superGroup
	 * 			the super group of this template group or <code>null</code> is there no super group
	 * @param templateEngine
	 * 			the template engine of the template group
	 * @throws TemplateException
	 * 			if the engine can't read the files
	 */
	public TemplateGroup(String name, ITemplateGroup superGroup, ITemplateEngine templateEngine) throws TemplateException {
		this.name = name;
		this.superGroup = superGroup;
		this.files = new LinkedList<URL>();
		this.templateEngine = templateEngine;
	}
		
	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateGroup#getDisplayName()
	 */
	public String getDisplayName() {
		// TODO Auto-generated method stub
		return name;
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateGroup#getSuperTemplateGroup()
	 */
	public ITemplateGroup getSuperTemplateGroup() {
		// TODO Auto-generated method stub
		return superGroup;
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateGroup#getTemplate(String)
	 */
	public ITemplate getTemplate(String name) {
		ITemplate template = templateEngine.getTemplate(name);
		if (template == null && superGroup != null) {
			template = superGroup.getTemplate(name);
		}
		return template;
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateGroup#addFile(URL)
	 */
	public void addFile(URL file) throws TemplateException {
		if (!files.contains(file)) {
			files.add(file);
			templateEngine.addFile(file);
		}
	}

	/**
	 * @see tudresden.ocl20.pivot.tools.template.ITemplateGroup#addFiles(List)
	 */
	public void addFiles(List<URL> files) throws TemplateException{
		files.addAll(files);
		templateEngine.addFiles((LinkedList<URL>) files);
	}

	
	

}
