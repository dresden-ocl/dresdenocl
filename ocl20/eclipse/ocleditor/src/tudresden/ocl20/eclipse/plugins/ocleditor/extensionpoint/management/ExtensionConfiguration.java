package tudresden.ocl20.eclipse.plugins.ocleditor.extensionpoint.management;

import org.eclipse.core.runtime.IConfigurationElement;

import tudresden.ocl20.eclipse.plugins.ocleditor.OCLEditorControler;
import tudresden.ocl20.eclipse.plugins.ocleditor.extensionpoint.IOCLExtension;

/**
 * An instance of this class represents an implementation of the extension point 
 * of the ocl-editor.
 * @author Mirko Stölzel
 *
 */
public class ExtensionConfiguration 
{
	/**the implementation of the class IOCLExtension*/
	private IOCLExtension extension = null;
	/**represents the entry of the plugin.xml of the plugin which
	 * implements the ocl-editor extension point*/
	private IConfigurationElement config;
	
	/**
	 * Constructor
	 * @param extension - the implementation of the class IOCLExtension
	 * @param config
	 */
	public ExtensionConfiguration(IOCLExtension extension, IConfigurationElement config)
	{
		this.extension = extension;
		this.config = config;
	}
	
	/**
	 * Returns the icon image entry of the create button which is defined in the
	 * plugin.xml file of the plugin which implements the ocl-editor extension point
	 * @return the create button enable icon
	 */
	public String getIcon() 
	{
		return config.getAttribute("icon");
	}
	
	/**
	 * Returns the disabled icon image entry of the create button which is defined in the
	 * plugin.xml file of the plugin which implements the ocl-editor extension point
	 * @return the create button disable icon
	 */
	public String getDisabledIcon() 
	{
		return config.getAttribute("disabledIcon");
	}
	
	/**
	 * Returns the icon text entry of the create button which is defined in the
	 * plugin.xml file of the plugin which implements the ocl-editor extension point
	 * @return the create button text
	 */
	public String getLabel() 
	{
		return config.getAttribute("label");
	}
	
	/**
	 * Returns the menu group entry of the create button which is defined in the
	 * plugin.xml file of the plugin which implements the ocl-editor extension point
	 * @return the create menu group
	 */
	public String getGroup() 
	{
		return config.getAttribute("group");
	}
	
	/**
	 * Returns the plugin-id of the plugin which implements the ocl-editor extension point
	 * @return the plugin-id
	 */
	public String getPluginID()
	{
		return config.getNamespace();
	}
	
	/**
	 * Returns the id of the implemented extensions-point
	 * @return
	 */
	public Object getID() 
	{
		return config.getAttribute("id");
	}
	
	/**
	 * Can be used to create an instance of this class
	 * @param config - represents the plugin.xml entry
	 * @return an instance of this class
	 * @throws Exception
	 */
	public static ExtensionConfiguration create(IConfigurationElement config) throws Exception
	{
		Object obj = config.createExecutableExtension("class");
		if (obj instanceof IOCLExtension) 
		{
			ExtensionConfiguration extension = new ExtensionConfiguration((IOCLExtension) obj, config);
			extension.getExtension().setEditor(OCLEditorControler.getInstance());
			return extension;
		}
		else
			throw new Exception();
	}

	/**
	 * Returns the instance of the class wich implements the class IOCLExtension
	 * and which is defined in the plugin.xml
	 * @return an instance of the class IOCLExtension 
	 */
	public IOCLExtension getExtension() {
		return extension;
	}	
}
