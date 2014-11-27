/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.ui;

import org.eclipse.emf.ecore.EObject;

public class OclHoverTextProvider implements org.dresdenocl.language.ocl.resource.ocl.IOclHoverTextProvider {
	
	private org.dresdenocl.language.ocl.resource.ocl.ui.OclDefaultHoverTextProvider defaultProvider = new org.dresdenocl.language.ocl.resource.ocl.ui.OclDefaultHoverTextProvider();
	
	public String getHoverText(EObject container, EObject referencedObject) {
		// Set option overrideHoverTextProvider to false and customize this method to
		// obtain custom hover texts.
		return defaultProvider.getHoverText(referencedObject);
	}
	
	public String getHoverText(EObject object) {
		// Set option overrideHoverTextProvider to false and customize this method to
		// obtain custom hover texts.
		return defaultProvider.getHoverText(object);
	}
	
}
