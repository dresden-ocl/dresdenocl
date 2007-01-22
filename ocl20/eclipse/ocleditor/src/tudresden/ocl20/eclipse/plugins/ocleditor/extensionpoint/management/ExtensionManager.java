package tudresden.ocl20.eclipse.plugins.ocleditor.extensionpoint.management;

import java.util.ArrayList;
import java.util.Iterator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import tudresden.ocl20.eclipse.plugins.ocleditor.extensionpoint.IOCLExtension;

/**
 * An instance of this class is used to manage all registred extensions of the
 * ocl-editor
 * @author Mirko Stölzel
 *
 */
public class ExtensionManager
{
	/**the singleton instance of the extension manager*/
	private static ExtensionManager manager;

	/**contains all found extensions*/
	private ArrayList<ExtensionConfiguration> extensions = null;

	
	/**
	 * Creates a new instance of the extension manager
	 */
	private ExtensionManager()
	{
		this.extensions = new ArrayList<ExtensionConfiguration>();
	}
	
	/**
	 * Returns all found extensions.
	 * @return an ArrayList instance which contains all found extensions
	 */
	public ArrayList<ExtensionConfiguration> getExtensions()
	{
		return this.extensions;
	}
	
	/**
	 * This method is called by the class OCLEditorControler after the selection
	 * has changed in the eclipse IDE
	 * @param sel the selected element
	 * @return an ArrayList instance which contains all extensions registred for the 
	 * actual selection
	 */
	public ArrayList<ExtensionConfiguration> selectionChanged(ISelection sel) 
	{		
		ArrayList<ExtensionConfiguration> result = new ArrayList<ExtensionConfiguration>();
		Iterator it = this.extensions.iterator();
		boolean selChanged = false;
		ExtensionConfiguration extensionConfig = null;
			
		while (it.hasNext()) 
		{			
			extensionConfig = (ExtensionConfiguration) it.next();
			IOCLExtension ext = extensionConfig.getExtension();
			selChanged = ext.setSelection(sel);
			if (selChanged)
				result.add(extensionConfig);
		}
		return result;
	}	
	
	/**
	 * Returns the extension manager instance. If no one exists a new instance will
	 * be created.
	 * @return the extension manager instance
	 */
	public static ExtensionManager create() 
	{
		if (manager == null)
		{
			manager = new ExtensionManager();
			try
			{
				IExtensionRegistry registry = Platform.getExtensionRegistry();
				IExtensionPoint point = registry
						.getExtensionPoint(IOCLExtension.ID);
				IExtension[] extensions = point.getExtensions();
				for (int i = 0; i < extensions.length; i++) 
				{
					IConfigurationElement[] elements = extensions[i].getConfigurationElements();
					for (int j = 0; j < elements.length; j++) 
					{	
						ExtensionConfiguration extension = ExtensionConfiguration.create(elements[j]);
						manager.extensions.add(extension);
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return manager;		
	}	
	
	/** Returns the extension manager instance.
	 * @return the extension manager instance
	 */
	public static ExtensionManager getInstance() 
	{
		if (manager == null)
			create();
		return manager;
	}		
}
