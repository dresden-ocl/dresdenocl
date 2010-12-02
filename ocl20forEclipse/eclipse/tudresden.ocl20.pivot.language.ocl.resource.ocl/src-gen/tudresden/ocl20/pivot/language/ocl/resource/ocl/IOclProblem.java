/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl;

public interface IOclProblem {
	public String getMessage();
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemType getType();
	public java.util.Collection<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix> getQuickFixes();
}
