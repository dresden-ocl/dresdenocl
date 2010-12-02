package tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events;

import tudresden.ocl20.eclipse.plugins.ocleditor.extensionpoint.management.ExtensionConfiguration;

/**
 * This abstract class is implemented by all ocl-model events
 * @author Mirko Stölzel
 *
 */
public abstract class AbstractChangeEvent 
{
	private ExtensionConfiguration extensionConfig = null;

	/**
	 * 
	 * @return
	 */
	public ExtensionConfiguration getExtensionConfig()
	{
		return extensionConfig;
	}

	public void setExtensionConfig(ExtensionConfiguration extensionConfig)
	{
		this.extensionConfig = extensionConfig;
	}
}
