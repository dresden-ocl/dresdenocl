package tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model;

import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events.AbstractChangeEvent;


/**
 * A listener of the OCLEditorModel has to implement this interface.
 * @author Mirko Stölzel
 *
 */
public interface IChangeListener 
{
	/**
	 * Is called if the model has changed.
	 * @param e
	 */
	public void handleChangeEvent(AbstractChangeEvent e);

}
