package tudresden.ocl20.eclipse.plugins.visual.popup.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.PluginAction;

import tudresden.ocl20.eclipse.plugins.visual.views.ASTView;

/**
 * Class for loading ressources
 * @author Kai-Uwe Gärtner
 *
 */
public class LoadFileAction implements IObjectActionDelegate {

	/**
	 * Constructor for Action1.
	 */
	public LoadFileAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		Shell shell = new Shell();
		ASTView viewPart=null;
		try{
			viewPart=(ASTView)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("tudresden.ocl20.eclipse.plugins.visual.views.ASTView");
		}
		catch (Exception e){
			MessageDialog.openInformation(
					shell,
					"Model Visualization",
					"Unable to show view.");
			return;
		}
		viewPart.loadRessource(new java.io.File(((IFile)((StructuredSelection)((PluginAction)action).getSelection()).getFirstElement()).getLocation().toOSString()));
		viewPart.drawGraph();
		
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}
