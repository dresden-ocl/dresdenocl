package tudresden.ocl20.eclipse.extensionpoint;

import java.io.File;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.ModelManager;
import tudresden.ocl20.core.NetBeansRepository;
import tudresden.ocl20.eclipse.OCLToolkitForEclipsePlugin;

/**
 * An instance of this class can be used to initialize the MDR.
 * @author Mirko
 *
 */
public class InitMDRJob extends Job {

	public static final int FAILED = -1;
	public static final int DONE = 0;
	private boolean loadMetamodel = false;

	public InitMDRJob() 
	{
		super("Initializing MDR");		
		this.setUser(true);	    
	}	
	
	protected IStatus run(IProgressMonitor monitor) 
	{
		monitor.beginTask("Create MDR-Directory", 10);
		//if MDR directory exists don't inititalize MDR
		File mdrDirectory = OCLToolkitForEclipsePlugin.getDefault().getDefaultMDRDirectory();
		if (mdrDirectory.exists())
		{
			monitor.worked(10);			
			monitor.done();		
			System.setProperty(NetBeansRepository.MDR, mdrDirectory.getAbsolutePath());
			return Status.OK_STATUS;
		}
		
		try
		{
			this.createMDRDirectory();
			System.setProperty(NetBeansRepository.MDR, mdrDirectory.getAbsolutePath());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return new Status(IStatus.OK,
							  "tudresden.ocl20.eclipse.ocleditor",
							  FAILED,
							  "Errors occured while creating the MDR Directory",
							  e);
		}
		
		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;
		
		monitor.worked(2);
		
		monitor.setTaskName("Load UML-Metamodel");
		
		try
		{
			this.loadMetamodel();	
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return new Status(IStatus.OK,
							  "tudresden.ocl20.eclipse.ocleditor",
							  FAILED,
							  "Errors occured while creating the MDR Directory",
							  e);
		}
		
		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;
		
		monitor.worked(10);			
		monitor.done();	
		System.setProperty(NetBeansRepository.MDR, mdrDirectory.getAbsolutePath());
		return Status.OK_STATUS;
	}
	
	private void createMDRDirectory() throws Exception
	{
		File mdrDirectory = OCLToolkitForEclipsePlugin.getDefault().getDefaultMDRDirectory();
		mdrDirectory.mkdirs();
	}
	
	private void loadMetamodel() throws Exception
	{
		File metaModelFile = OCLToolkitForEclipsePlugin.getDefault().getMetamodel();
		if (metaModelFile != null)
		{
			ModelManager mm = ModelManager.getInstance();
			mm.beginTrans(true);
			mm.loadMetaModel(metaModelFile.toURI().toString(),MetaModelConst.UML15);
			mm.endTrans(false);    	
		}
		else
			throw new Exception("Couldn't find metamodel file");		
	}
	

}
