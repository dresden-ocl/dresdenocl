/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.ui;

public class OclHoverTextProvider implements tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclHoverTextProvider {
	
	private tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclDefaultHoverTextProvider defaultProvider = new tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclDefaultHoverTextProvider();
	
	public java.lang.String getHoverText(org.eclipse.emf.ecore.EObject object) {
		// Set option overrideHoverTextProvider to false and customize this method to
		// obtain custom hover texts.
		return defaultProvider.getHoverText(object);
	}
	
}
