package tudresden.ocl20.eclipse.plugins.ocleditor.utils;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

/**
 * An instance of this class can be used to open a view of the eclipse IDE
 * @author Mirko Stölzel
 */
public class OpenProblemsView implements Runnable 
{
	//the view which wants to open the view
	private ViewPart editor;
	//the id of the view
	private String id;

	/**
	 * Constructor
	 * @param editor
	 */
	public OpenProblemsView(ViewPart editor)
	{
		this.editor = editor;
	}
	
	/**
	 * Opens the view
	 * @param id - the id of the view (for example "org.eclipse.ui.views.ProblemView")
	 * @throws Exception
	 */
	public void open(String id) throws Exception
	{
		this.id = id;
		Display display = this.editor.getViewSite().getShell().getDisplay();
		
		display.asyncExec(this);	
		
	}
	
	public void run() 
	{
		try
		{
			this.editor.getViewSite().getPage().showView (id);
			//this.editor.getViewSite().getPage().activate(this.editor);
		}
		catch (PartInitException e)
	    {
	         e.printStackTrace();
	    }		    	
	}

}
