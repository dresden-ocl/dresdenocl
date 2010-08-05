/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl;

public interface IOclQuickFix {
	
	/**
	 * Returns a string that briefly describes the quick fix.
	 * 
	 * @return brief description to display
	 */
	public String getDisplayString();
	
	/**
	 * Applies the fix and returns the new text for the resource. If the fix does not
	 * change the current resource, but others, null must be returned.
	 */
	public String apply(String currentText);
	
	/**
	 * Returns a collection of objects the fix refers to. This collection is used to
	 * check whether the fix is can still be applied even after a workbench restart.
	 */
	public java.util.Collection<org.eclipse.emf.ecore.EObject> getContextObjects();
	
	public String getContextAsString();
	
}
