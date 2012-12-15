/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;

public class OclParseResult implements org.dresdenocl.language.ocl.resource.ocl.IOclParseResult {
	
	private org.eclipse.emf.ecore.EObject root;
	private java.util.Collection<org.dresdenocl.language.ocl.resource.ocl.IOclCommand<org.dresdenocl.language.ocl.resource.ocl.IOclTextResource>> commands = new java.util.ArrayList<org.dresdenocl.language.ocl.resource.ocl.IOclCommand<org.dresdenocl.language.ocl.resource.ocl.IOclTextResource>>();
	
	public OclParseResult() {
		super();
	}
	
	public void setRoot(org.eclipse.emf.ecore.EObject root) {
		this.root = root;
	}
	
	public org.eclipse.emf.ecore.EObject getRoot() {
		return root;
	}
	
	public java.util.Collection<org.dresdenocl.language.ocl.resource.ocl.IOclCommand<org.dresdenocl.language.ocl.resource.ocl.IOclTextResource>> getPostParseCommands() {
		return commands;
	}
	
}
