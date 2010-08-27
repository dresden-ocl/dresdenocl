/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp;

public class OclProblem implements tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclProblem {
	
	private String message;
	private tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemType type;
	private java.util.Collection<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix> quickFixes;
	
	public OclProblem(String message, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemType type) {
		this(message, type, java.util.Collections.<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix>emptySet());
	}
	
	public OclProblem(String message, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemType type, tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix quickFix) {
		this(message, type, java.util.Collections.singleton(quickFix));
	}
	
	public OclProblem(String message, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemType type, java.util.Collection<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix> quickFixes) {
		super();
		this.message = message;
		this.type = type;
		this.quickFixes = new java.util.LinkedHashSet<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix>();
		this.quickFixes.addAll(quickFixes);
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemType getType() {
		return type;
	}
	
	public String getMessage() {
		return message;
	}
	
	public java.util.Collection<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix> getQuickFixes() {
		return quickFixes;
	}
	
}
