package tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.actions;

import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.AbortEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.AbstractActionEvent;

/**
 * The editing process abort action.
 * @author Mirko Stölzel
 *
 */
public class AbortAction extends AbstractAction 
{
	/**
	 * Constructor
	 * @param style
	 */
	public AbortAction(int style) 
	{
		super(style);
	}

	protected AbstractActionEvent getActionEvent() 
	{
		return new AbortEvent();
	}

}
