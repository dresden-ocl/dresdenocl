package tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.actions;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.Bundle;

import tudresden.ocl20.eclipse.plugins.ocleditor.extensionpoint.management.ExtensionConfiguration;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.IActionListener;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.AbstractActionEvent;

/**
 * This abstract class is implemented by all actions of the ocl-editor.
 * @author Mirko Stölzel
 *
 */
public abstract class AbstractAction extends Action 
{
	private ArrayList<IActionListener> listener = null;
	protected Object constraint = null;
	protected ExtensionConfiguration extensionConfig = null;	

	/**
	 * Constructor
	 * @param style
	 */
	public AbstractAction(int style) 
	{
		super("", style);
		this.listener = new ArrayList<IActionListener>();		
	}	
	
	/**
	 * Returns the extension configuration which is used for this action.
	 * @return the extension configuration, otherwise (for example abort edit process) null.
	 */
	public ExtensionConfiguration getAction() 
	{
		return extensionConfig;
	}

	/**
	 * Is used to set the extension configuration.
	 * @param extensionConfig
	 */
	public void setAction(ExtensionConfiguration extensionConfig) 
	{
		this.extensionConfig = extensionConfig;
	}
	
	/**
	 * Returns the selected constraint.
	 * @return the selected constraint, otherwise (for example abort edit process) null.
	 */
	public Object getConstraint() 
	{
		return constraint;
	}
	
	/**
	 * Is used to set the selected constraint.
	 * @param constraint
	 */
	public void setConstraint(Object constraint) 
	{
		this.constraint = constraint;
	}
	
	/**
	 * Called after the action button was pushed.
	 * Notifies all registred action listener.
	 */
	public void run()
	{
		Iterator it = this.listener.iterator();
		while (it.hasNext())
			((IActionListener)it.next()).handleAction(this.getActionEvent());		
	}

	/**
	 * Sets the disable image of the action.
	 * @param disabledImage
	 * @param pluginID
	 */
	public void setDisabledImage(String disabledImage,
								 String pluginID) 
	{
		if (pluginID != null && pluginID.length() > 0 &&
			disabledImage != null && disabledImage.length() > 0)
		{
			Bundle bundle = Platform.getBundle(pluginID);		
			Path path = new Path(disabledImage);
			URL u = Platform.find(bundle, path);
			this.setDisabledImageDescriptor(ImageDescriptor.createFromURL(u));		
		}
	}

	/**
	 * Sets the enable image of the action.
	 * @param enabledImage
	 * @param pluginID
	 */
	public void setEnabledImage(String enabledImage,
							    String pluginID)
	{
		if (pluginID != null && pluginID.length() > 0 &&
			enabledImage != null && enabledImage.length() > 0)
		{
			Bundle bundle = Platform.getBundle(pluginID);
			Path path = new Path(enabledImage);	
			URL u = Platform.find(bundle, path);	
			this.setImageDescriptor(ImageDescriptor.createFromURL(u));
		}
	}

	/**
	 * Sets the tooltip of the action.
	 * @param tooltip
	 */
	public void setTooltip(String tooltip)
	{
		setToolTipText(tooltip);
	}
	
	/**
	 * Can be used to add an action listener.
	 * @param listener
	 */
	public void addListener(IActionListener listener)
	{
		this.listener.add(listener);
	}
	
	/**
	 * Is called to clear the extension configuration and the constraint.
	 */
	public void clear()
	{
		this.extensionConfig = null;
		this.constraint = null;
	}
	
	/**
	 * Returns the action events for the action.
	 * @return the action event
	 */
	protected abstract AbstractActionEvent getActionEvent();
}
