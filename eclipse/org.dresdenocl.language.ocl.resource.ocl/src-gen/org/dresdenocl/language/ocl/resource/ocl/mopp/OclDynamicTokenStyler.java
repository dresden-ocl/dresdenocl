/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp;

public class OclDynamicTokenStyler {
	
	/**
	 * This method is called to dynamically style tokens.
	 * 
	 * @param resource the TextResource that contains the token
	 * @param token the token to obtain a style for
	 * @param staticStyle the token style as set in the editor preferences (is
	 * <code>null</code> if syntax highlighting for the token is disabled)
	 */
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTokenStyle getDynamicTokenStyle(tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextResource resource, tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextToken token, tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTokenStyle staticStyle) {
		// The default implementation returns the static style without any changes. To
		// implement dynamic token styling, set the overrideDynamicTokenStyler option to
		// <code>false</code> and customize this method.
		return staticStyle;
	}
	
}
