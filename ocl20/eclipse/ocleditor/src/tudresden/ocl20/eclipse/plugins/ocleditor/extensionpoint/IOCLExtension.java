/*
 * Created on 25.10.2005
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tudresden.ocl20.eclipse.plugins.ocleditor.extensionpoint;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;

import tudresden.ocl20.eclipse.plugins.ocleditor.OCLEditorControler;
import tudresden.ocl20.eclipse.plugins.ocleditor.jobs.IParseConfiguration;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.ParseDisableEvent;
import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.actions.events.ParseEnableEvent;
/**
 * Extensionpoint interface which has to be implemented if a plugin wants to use the OCL editor.
 * The OCL editor searches for every implemenetation of this interface and uses the methods
 * of this interface to access the informations of the model of the plugin. 
 * @author Mirko Stölzel
 */
public abstract class IOCLExtension 
{
	/**
	 * the id of the extension-point of the ocl-editor
	 */
	public static final String ID = "tudresden.ocl20.eclipse.ocleditor.OCLEditorExtensionPoint";
	
	/**the actual selection*/
	protected Object selection = null;
	/**the ocl-editor contoler*/
	protected OCLEditorControler editor = null;
	
	/**
	 * Returns the model element is selected.
	 * 
	 * @param sel - the actual selection of the eclipse workbench
	 * @return - the model element is selected else null
	 */
	public boolean setSelection(ISelection sel)
	{
		Object object = this.isValidSelection(sel);
		if (object != null)
		{
			this.selection = object;
			return true;
		}
		return false;
	}	
	
	/**
	 * Is Used to set the ocl-editor controler
	 * @param editor
	 */
	public void setEditor(OCLEditorControler editor) 
	{
		this.editor = editor;
	}
	
	
	/**
	 * Can be used to enable the parse-button of the ocl-editor
	 */
	public void enableParseButton()
	{
		this.editor.handleAction(new ParseEnableEvent());
	}	
	
	/**
	 * Can be used to disable the parse-button of the ocl-editor
	 */
	public void disableParseButton()
	{
		this.editor.handleAction(new ParseDisableEvent());
	}
	
	
	/**
	 * Is called by the setSelection()-method to check whether 
	 * the actual selection is a selection of the plugin which implements
	 * this class.   
	 * @param sel
	 * @return the selected modelelement
	 */
	public abstract Object isValidSelection(ISelection sel);
	
	/**
	 * Is used to check whether an OCL constraint can be defined for
	 * the actual selected modelelement
	 * @return true if you can define an OCL constraint
	 */
	public abstract boolean isEnabled();
	
	/**
	 * This method is used to get all constraints of the selected modelelement.
	 * @return an array which contains the constraints of the modelelement
	 * 		   otherwise an empty array
	 */
	public abstract Object[] getConstraints();
	
	/**
	 * Is used to get the textual representation of the given constraint-object
	 * @param obj - the modelelement which was returned by the getConstraints()-method 
	 * @return the textual representation of the constraint
	 */
	public abstract String getConstraintText(Object obj);
	
	/**
	 * Is called by the ocl-editor to create the gui for the constraint editing
	 * @param parent - the parent composite
	 * @param constraint - the constraint to be edited, otherwise null
	 */
	public abstract void createPartControl(Composite parent, Object constraint);
		
	/**
	 * Called after an existing constraint was edited from the ocl editor
	 * to get the ocl constraint to parse. 
	 * @param constraint - the selected constraint, otherwise null
	 * @return An array of parse-configurations which hold the ModelFacade isntance etc., otherwise an empty array 
	 */
	public abstract IParseConfiguration[] getParseConfigurations(Object constraint);
	
	/**
	 * Creates a new OCL constraint.
	 * @param config - the parse configuration which was returned by the getParseConfigurations()-method
	 */
	public abstract void createOCLConstraint(IParseConfiguration[] config);
	/**
	 * Edits an existing OCL constraint.
	 * @param config 
	 */
	public abstract void editOCLConstraint(IParseConfiguration[] config);
	
	/**
	 * Deletes an existing OCL constraint.
	 * @param constraint 
	 */
	public abstract void deleteOCLConstraint(Object constraint);
	
	/**
	 * Called after the editing process was finished.
	 */
	public abstract void dispose();
}
