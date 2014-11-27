/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;

import java.util.ArrayList;
import java.util.Collection;
import org.eclipse.emf.ecore.EObject;

public class OclParseResult implements org.dresdenocl.language.ocl.resource.ocl.IOclParseResult {
	
	private EObject root;
	
	private org.dresdenocl.language.ocl.resource.ocl.IOclLocationMap locationMap;
	
	private Collection<org.dresdenocl.language.ocl.resource.ocl.IOclCommand<org.dresdenocl.language.ocl.resource.ocl.IOclTextResource>> commands = new ArrayList<org.dresdenocl.language.ocl.resource.ocl.IOclCommand<org.dresdenocl.language.ocl.resource.ocl.IOclTextResource>>();
	
	public OclParseResult() {
		super();
	}
	
	public EObject getRoot() {
		return root;
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.IOclLocationMap getLocationMap() {
		return locationMap;
	}
	
	public void setRoot(EObject root) {
		this.root = root;
	}
	
	public void setLocationMap(org.dresdenocl.language.ocl.resource.ocl.IOclLocationMap locationMap) {
		this.locationMap = locationMap;
	}
	
	public Collection<org.dresdenocl.language.ocl.resource.ocl.IOclCommand<org.dresdenocl.language.ocl.resource.ocl.IOclTextResource>> getPostParseCommands() {
		return commands;
	}
	
}
