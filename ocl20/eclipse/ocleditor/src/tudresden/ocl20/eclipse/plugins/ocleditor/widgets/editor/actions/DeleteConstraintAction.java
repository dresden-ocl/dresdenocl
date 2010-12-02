package tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.actions;

import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.AbstractActionEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.DeleteConstraintEvent;

/**
 * The delete constraint action.
 * @author Mirko Stölzel
 *
 */
public class DeleteConstraintAction extends AbstractAction 
{
	/**
	 * Constructor
	 * @param style
	 */
	public DeleteConstraintAction(int style) 
	{
		super(style);
	}


	protected AbstractActionEvent getActionEvent() 
	{
		DeleteConstraintEvent evt = new DeleteConstraintEvent();
		evt.setConstraint(constraint);
		evt.setExtensionConfiguration(extensionConfig);
		return evt;
	}
}
