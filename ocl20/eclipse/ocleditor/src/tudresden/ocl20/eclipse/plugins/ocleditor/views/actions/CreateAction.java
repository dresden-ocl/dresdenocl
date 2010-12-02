/*
 * Created on 25.10.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tudresden.ocl20.eclipse.plugins.ocleditor.views.actions;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;

import tudresden.ocl20.eclipse.plugins.ocleditor.views.OCLEditor;

/**
 * @author Mirko
 */
public class CreateAction implements IViewActionDelegate
{

	private OCLEditor editor = null;
	
	public void init(IViewPart view) 
	{
		editor = (OCLEditor) view;	
	}
	
	public void run(IAction action) 
	{
		this.editor.newEvent();
	}

	public void selectionChanged(IAction action, ISelection selection) 
	{
		
	}
}
