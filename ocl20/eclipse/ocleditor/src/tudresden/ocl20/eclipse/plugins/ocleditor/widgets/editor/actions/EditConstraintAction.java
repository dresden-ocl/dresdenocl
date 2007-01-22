package tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.actions;

import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.AbstractActionEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.EditConstraintEvent;

/**
 * The editing constraint action.
 * @author Mirko Stölzel
 *
 */
public class EditConstraintAction extends AbstractAction 
{
	/**
	 * Constructor
	 * @param style
	 */
	public EditConstraintAction(int style) 
	{
		super(style);
	}

	protected AbstractActionEvent getActionEvent() 
	{
		EditConstraintEvent evt = new EditConstraintEvent();
		evt.setConstraint(constraint);
		evt.setExtensionConfiguration(extensionConfig);
		return evt;
	}
}
