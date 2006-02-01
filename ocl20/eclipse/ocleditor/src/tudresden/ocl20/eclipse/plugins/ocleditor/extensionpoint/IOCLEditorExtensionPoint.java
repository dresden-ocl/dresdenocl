/*
 * Created on 25.10.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tudresden.ocl20.eclipse.plugins.ocleditor.extensionpoint;

import org.eclipse.jface.viewers.ISelection;


import tudresden.ocl20.integration.ModelFacade;
import tudresden.ocl20.eclipse.plugins.ocleditor.views.OCLEditor;

/**
 * Extensionpoint interface which has to be implemented if a plugin wants to use the OCL editor.
 * The OCL editor searches for every implemenetation of this interface and uses the methods
 * of this interface to access the informations of the model of the plugin. 
 * @author Mirko
 */
public interface IOCLEditorExtensionPoint 
{
	public static final String ID = "tudresden.ocl20.eclipse.ocleditor.OCLEditorExtensionPoint";
	
	/**
	 * Returns the selected modelelement.
	 * 
	 * @param sel - the actual selection of the eclipse workbench
	 * @return - the selected element if the actual selection is an element of a plugin else null
	 */
	public Object isValidSelection(ISelection sel);	
	/**
	 * Returns all existing OCL constraints of the selected element as object array.	 
	 */
	public Object[] getOCLConstraints(Object selectedElement);
	/**
	 * Returns an OCL constraint as String.
	 */
	public String getText(Object obj);
	/**
	 * Creates a new OCL constraint.
	 */
	public void createOCLConstraint(Object selectedElement, String text);
	/**
	 * Edits an existing OCL constraint.
	 */
	public void editOCLConstraint(Object constraint, String text);
	/**
	 * Deletes an existing OCL constraint.
	 */
	public void deleteOCLConstraint(Object selectedElement, Object constraint);
	/**
	 * Return the top element of the UML model.
	 */
	public Object getTopPackage();
	/**
	 * Return the instance of the implementation of the class ModelFacade.
	 */
	public ModelFacade getModelFacade();
	/**
	 * Sets the OCL editor.
	 */
	public void setOCLEditor(OCLEditor editor);
	/**
	 * Has to be used to repaint the OCL editor.
	 */
	public void refreshEditor();

}
