/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl;

/**
 * An interface used to access the result of parsing a document.
 */
public interface IOclParseResult {
	
	/**
	 * Returns the root object of the document.
	 */
	public org.eclipse.emf.ecore.EObject getRoot();
	
	/**
	 * Returns a list of commands that must be executed after parsing the document.
	 */
	public java.util.Collection<org.dresdenocl.language.ocl.resource.ocl.IOclCommand<org.dresdenocl.language.ocl.resource.ocl.IOclTextResource>> getPostParseCommands();
	
}
