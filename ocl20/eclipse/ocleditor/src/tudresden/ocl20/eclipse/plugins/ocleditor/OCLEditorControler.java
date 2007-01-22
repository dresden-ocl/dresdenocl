/*
 * Created on 02.11.2005
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tudresden.ocl20.eclipse.plugins.ocleditor;

import java.util.ArrayList;

import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import tudresden.ocl20.eclipse.OCLToolkitForEclipsePlugin;
import tudresden.ocl20.eclipse.plugins.ocleditor.extension.IMDRInitializerListener;
import tudresden.ocl20.eclipse.plugins.ocleditor.extension.MDRInitializer;
import tudresden.ocl20.eclipse.plugins.ocleditor.extensionpoint.IOCLExtension;
import tudresden.ocl20.eclipse.plugins.ocleditor.extensionpoint.management.ExtensionConfiguration;
import tudresden.ocl20.eclipse.plugins.ocleditor.extensionpoint.management.ExtensionManager;
import tudresden.ocl20.eclipse.plugins.ocleditor.jobs.IParseConfiguration;
import tudresden.ocl20.eclipse.plugins.ocleditor.jobs.ParseJob;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.OCLEditor;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.IActionListener;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.AbortEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.AbstractActionEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.CreateConstraintEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.DeleteConstraintEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.EditConstraintEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.ParseConstraintEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.ParseDisableEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.ParseEnableEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.SelectionEvent;

/**
 * This class represents the OCL editor.
 * @author Mirko
 *
 * 
 */
public class OCLEditorControler extends ViewPart implements IJobChangeListener, 
													   ISelectionListener,
													   IActionListener, IMDRInitializerListener
{	
	//the ocl-editor component
	private OCLEditor editor;
	//the extension manager
	private ExtensionManager extensionManager;		
	//symbolize whether the mdr was inited or not
	private boolean mdrInit = false;
	//all enabled extensions
	private ArrayList<ExtensionConfiguration> enabledActions;	
	//lastParseEvent used in done()-method
	private ParseConstraintEvent lastParseEvent;
	//lastSelection 
	private ISelection lastSelection;
	
	private static OCLEditorControler instance;
	
	/**
	 * Constructor
	 */
	public OCLEditorControler()
	{}
	
	public void createPartControl(Composite parent) 
	{	
		instance = this;
		OCLToolkitForEclipsePlugin.getDefault();
		this.extensionManager = ExtensionManager.create();
		this.editor = new OCLEditor(parent, this);
		this.getSite().getPage().addSelectionListener(this);		
	}	

	public void setFocus() 
	{}
	
	/**
	 * Deletes all markers.
	 */
	public void dispose()
	{
		this.editor.dispose();
		super.dispose();
	}	
	
	public void aboutToRun(IJobChangeEvent event) {}

	public void awake(IJobChangeEvent event) {}

	/**
	 * Called after some constraints were parsed or after the
	 * MDR of the Dresden OCL Toolkit was initialized
	 */
	public void done(final IJobChangeEvent event) 
	{
		Display display = this.getViewSite().getShell().getDisplay();
		display.asyncExec(new Runnable()
		{
			public void run()
			{				
				Job job = event.getJob();
				if (job instanceof ParseJob)
				{
					ParseJob parseJob = (ParseJob) job;
					String problem = ((ParseJob)job).getProblem();
					if (problem == null)
					{
						IOCLExtension action = lastParseEvent.getExtensionConfiguration().getExtension();
						IParseConfiguration[] configs = parseJob.getParseConfigs();
						boolean edit = true;
						for (int i = 0; i < configs.length; i++)
						{
							IParseConfiguration config = configs[i];
							if (config.getConstraint() == null)
								edit = false;
						}
						if (edit)
							action.editOCLConstraint(configs);
						else
							action.createOCLConstraint(configs);			
						
						editor.getModel().fireParseEvent(null);			   					    	
					}
					else
					{
						editor.getModel().fireParseEvent(problem);
					}
					
				}
			}
		});
		
	}

	public void running(IJobChangeEvent event) {}

	public void scheduled(IJobChangeEvent event) {}

	public void sleeping(IJobChangeEvent event) {}	
	
	/**
	 * Called after the selection of the eclipse IDE has changed
	 */
	public void selectionChanged(IWorkbenchPart part, final ISelection sel) 
	{		
		Display display = this.getViewSite().getShell().getDisplay();
		display.asyncExec(new Runnable()
		{
			public void run()
			{
				if (lastSelection != sel)
				{
					ArrayList<ExtensionConfiguration> extensions = extensionManager.selectionChanged(sel);
					if (extensions.size() > 0)
					{
						enabledActions = extensions;
						lastSelection = sel;
						if (mdrInit);
						{
							editor.getModel().setActions(extensions);
						}		
					}
					
				}
			}
		});
	}
	
	/**
	 * Handles the ocl-editor-actions
	 */
	public void handleAction(final AbstractActionEvent e) 
	{
		Display display = this.getViewSite().getShell().getDisplay();
		final OCLEditorControler controler = this;
		display.asyncExec(new Runnable()
		{
			public void run()
			{
				if (e instanceof CreateConstraintEvent)
				{
					editor.getModel().fireCreateEvent(e.getExtensionConfiguration());
				}
				if (e instanceof EditConstraintEvent)
				{
					editor.getModel().fireEditEvent(e.getExtensionConfiguration(), e.getConstraint());
				}
				if (e instanceof ParseConstraintEvent)
				{
					//Required for constraint editing after the parse job is done
					lastParseEvent = (ParseConstraintEvent)e;
					IOCLExtension action = e.getExtensionConfiguration().getExtension();
					
					IParseConfiguration[] parseConfig = action.getParseConfigurations(e.getConstraint());
					
					ParseJob parseJob = new ParseJob(parseConfig);
					parseJob.addJobChangeListener(controler);
					editor.getModel().fireDisableAllEvent();
					parseJob.schedule();				
				}
				if (e instanceof DeleteConstraintEvent)
				{
					Object constraint = e.getConstraint();
					IOCLExtension action = e.getExtensionConfiguration().getExtension();
					action.deleteOCLConstraint(constraint);
					editor.getModel().fireDeletedEvent();
				}
				if (e instanceof AbortEvent)
				{
					editor.getModel().fireAbortEvent();
				}
				if (e instanceof SelectionEvent)
				{
					editor.getModel().fireSelectionChangeEvent();
				}
				if (e instanceof ParseEnableEvent)
				{
					editor.getModel().fireParseEnable();
				}
				if (e instanceof ParseDisableEvent)
				{
					editor.getModel().fireParseDisable();
				}
			}
		});
	}	
	
	/**
	 * Returns the singleton instance of this class
	 * @return
	 */
	public static OCLEditorControler getInstance() {
		return instance;
	}

	public void mdrInitialized(String directory)
	{
		mdrInit = true;
		if (enabledActions != null)
			editor.getModel().setActions(enabledActions);		
	}
}
