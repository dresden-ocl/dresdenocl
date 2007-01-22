package tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.actions;

import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.AbstractActionEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.CreateConstraintEvent;

/**
 * Opens the create action menu.
 * @author Mirko Stölzel
 *
 */
public class CreateConstraintDropDownAction extends AbstractAction 
{
	/**
	 * Constructor
	 * @param style
	 */
	public CreateConstraintDropDownAction(int style) 
	{
		super(style);
	}	

	protected AbstractActionEvent getActionEvent() 
	{
		CreateConstraintEvent evt = new CreateConstraintEvent();
		evt.setExtensionConfiguration(extensionConfig);
		return evt;
	}
}
