package tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.actions;

import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.AbstractActionEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.ParseConstraintEvent;

/**
 * The parse action.
 * @author Mirko Stölzel
 *
 */
public class ParseConstraintAction extends AbstractAction 
{
	/**
	 * Constructor
	 * @param style
	 */
	public ParseConstraintAction(int style) 
	{
		super(style);
	}
	
	protected AbstractActionEvent getActionEvent() 
	{
		ParseConstraintEvent evt = new ParseConstraintEvent();
		evt.setConstraint(constraint);
		evt.setExtensionConfiguration(extensionConfig);
		return evt;
	}
}
