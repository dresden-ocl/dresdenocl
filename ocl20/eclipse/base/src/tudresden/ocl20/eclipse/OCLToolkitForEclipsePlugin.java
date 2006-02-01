package tudresden.ocl20.eclipse;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.plugin.*;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.ModelManager;
import tudresden.ocl20.core.NetBeansRepository;
import tudresden.ocl20.core.parser.sablecc.lexer.Lexer;
import tudresden.ocl20.core.parser.sablecc.parser.Parser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * The main plugin class to be used in the desktop.
 */
public class OCLToolkitForEclipsePlugin extends AbstractUIPlugin {
	//The shared instance.
	private static OCLToolkitForEclipsePlugin plugin;
	//Resource bundle.
	private ResourceBundle resourceBundle;
	
	/**
	 * The constructor.
	 */
	public OCLToolkitForEclipsePlugin() {
		super();
		plugin = this;
		try {
			resourceBundle = ResourceBundle.getBundle("tudresden.ocl20.eclipseplugin.OCLToolkitForEclipsePlugin");
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
	}

	/**
	 * This method is called upon plug-in activation. Loads the metamodell if nessecary.  
	 */
	public void start(BundleContext context) throws Exception 
	{
		super.start(context);
		
		System.setProperty("org.openide.util.Lookup", "tudresden.ocl20.eclipse.EclipsePluginCustomLookup");
		Thread.currentThread().setContextClassLoader(EclipsePluginCustomLookup.class.getClassLoader());
		
		File persistentStoreFile = null;
		IPath path = this.getStateLocation().append(IPath.SEPARATOR + "repository" + IPath.SEPARATOR);
		persistentStoreFile = new File(path.toOSString());
		
		System.setProperty(NetBeansRepository.MDR, persistentStoreFile.getAbsolutePath());
		
		if (!persistentStoreFile.exists())
		{	
			persistentStoreFile.mkdirs();
			System.out.println("Load metamodel into repository");
			File metaModelSourceFile = 
				new File(getResource("resources/UML15_plus_OCLMetamodel.xml").getFile());
			ModelManager.getInstance().loadMetaModel(metaModelSourceFile.toURI().toString(),MetaModelConst.UML15);			
		}		
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
		resourceBundle = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static OCLToolkitForEclipsePlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the string from the plugin's resource bundle,
	 * or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = OCLToolkitForEclipsePlugin.getDefault().getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key) : key;
		} catch (MissingResourceException e) {
			return key;
		}
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		try {
			if (resourceBundle == null)
				resourceBundle = ResourceBundle.getBundle("eclipseplugin.OCLToolkitForEclipsePluginResources");
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
		return resourceBundle;
	}
	
	/**
	 * Returns the location of the given resource in the resource folder of the OCLToolkitForEclipse plugin.
	 */
	private URL getResource(String resource)
	{
		Path path = new Path(resource);
		URL fileURL = Platform.find(this.getBundle(), path);
		if (fileURL != null)
		    try 
		    {
				URL localURL = Platform.resolve(fileURL);
				return localURL;
			} 
		    catch (IOException e) 
		    {
				e.printStackTrace();
				return null;
			}
		else
		   	return null;
	}   
}
