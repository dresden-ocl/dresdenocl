package tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions;

import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.AbstractActionEvent;

/**
 * A listener of the ocl-editor-buttons has to implement this interface.
 * @author Mirko Stölzel
 *
 */
public interface IActionListener 
{
	/**
	 * Is called if a button was pushed.
	 * @param e
	 */
	public void handleAction(AbstractActionEvent e);

}
