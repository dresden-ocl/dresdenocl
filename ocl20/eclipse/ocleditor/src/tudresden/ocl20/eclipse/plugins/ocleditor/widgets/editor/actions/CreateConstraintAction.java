package tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.actions;

import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.AbstractActionEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.CreateConstraintEvent;

/**
 * The create constraint action.
 * @author Mirko Stölzel
 *
 */
public class CreateConstraintAction extends AbstractAction 
{
	/**
	 * Constructor
	 * @param style
	 */
	public CreateConstraintAction(int style) 
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
