package tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events;

import tudresden.ocl20.eclipse.plugins.ocleditor.extensionpoint.management.ExtensionConfiguration;


/**
 * This abstract class is implemented by all action events.
 * @author Mirko Stölzel
 *
 */
public abstract class AbstractActionEvent 
{
	protected Object constraint = null;
	protected ExtensionConfiguration extensionConfig = null;	

	/**
	 * Returns the extension configuration which is registered for the selected constraint. 
	 * @return the extension, otherwise null
	 */
	public ExtensionConfiguration getExtensionConfiguration() 
	{
		return extensionConfig;
	}

	/**
	 * Is used to set the extension configuration. 
	 * @param extensionConfig
	 */
	public void setExtensionConfiguration(ExtensionConfiguration extensionConfig) 
	{
		this.extensionConfig = extensionConfig;
	}
	
	/**
	 * Returns the selected constraint.
	 * @return the constraint, otherwise null.
	 */
	public Object getConstraint() 
	{
		return constraint;
	}
	
	/**
	 * Is used to set the constraint.
	 * @param constraint
	 */
	public void setConstraint(Object constraint) 
	{
		this.constraint = constraint;
	}	
}
