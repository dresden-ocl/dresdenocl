package tudresden.ocl20.eclipse.extensionpoint;

import java.util.ArrayList;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;

import tudresden.ocl20.eclipse.OCLToolkitForEclipsePlugin;

public class MDRInitializerFactory implements IJobChangeListener
{
	private static MDRInitializerFactory instance = null;

	public static MDRInitializerFactory getInstance()
	{
		if (instance == null)
			instance = new MDRInitializerFactory();
		return instance;
	}	
	
	private ArrayList<IMDRInitializer> elements = new ArrayList<IMDRInitializer>();
	
	public MDRInitializerFactory()
	{
		this.registerInitializer();
	}
	
	private void registerInitializer()
	{
		try
		{
			IExtensionRegistry registry = Platform.getExtensionRegistry();
			IExtensionPoint point = registry.getExtensionPoint(IMDRInitializer.ID);
			IExtension[] extensions = point.getExtensions();
			for (int i = 0; i < extensions.length; i++) 
			{
				IConfigurationElement[] elements = extensions[i].getConfigurationElements();
				for (int j = 0; j < elements.length; j++) 
				{	
					Object obj = elements[j].createExecutableExtension("class");
					if (obj instanceof IMDRInitializer) 
					{
						this.elements.add((IMDRInitializer) obj);
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void initMDR()
	{
		InitMDRJob initMDRJob = new InitMDRJob();
		initMDRJob.addJobChangeListener(this);
		initMDRJob.schedule();
	}
	
	public void fireMDRInitialized(String path)
	{
		for (int i = 0; i < this.elements.size(); i++)
			this.elements.get(i).mdrInitialized(path);
	}

	public void aboutToRun(IJobChangeEvent event)
	{}

	public void awake(IJobChangeEvent event)
	{}

	public void done(IJobChangeEvent event)
	{
		String path = OCLToolkitForEclipsePlugin.getDefault().getDefaultMDRDirectory().getAbsolutePath();
		this.fireMDRInitialized(path);
	}

	public void running(IJobChangeEvent event)
	{}

	public void scheduled(IJobChangeEvent event)
	{}

	public void sleeping(IJobChangeEvent event)
	{} 
}
