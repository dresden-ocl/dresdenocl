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
	private tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemSeverity severity;
	private java.util.Collection<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix> quickFixes;
	
	public OclProblem(String message, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemType type, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemSeverity severity) {
		this(message, type, severity, java.util.Collections.<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix>emptySet());
	}
	
	public OclProblem(String message, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemType type, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemSeverity severity, tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix quickFix) {
		this(message, type, severity, java.util.Collections.singleton(quickFix));
	}
	
	public OclProblem(String message, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemType type, tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemSeverity severity, java.util.Collection<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix> quickFixes) {
		super();
		this.message = message;
		this.type = type;
		this.severity = severity;
		this.quickFixes = new java.util.LinkedHashSet<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix>();
		this.quickFixes.addAll(quickFixes);
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemType getType() {
		return type;
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.OclEProblemSeverity getSeverity() {
		return severity;
	}
	
	public String getMessage() {
		return message;
	}
	
	public java.util.Collection<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix> getQuickFixes() {
		return quickFixes;
	}
	
}
