/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl;

public interface IOclProblem {
	public String getMessage();
	public org.dresdenocl.language.ocl.resource.ocl.OclEProblemSeverity getSeverity();
	public org.dresdenocl.language.ocl.resource.ocl.OclEProblemType getType();
	public java.util.Collection<org.dresdenocl.language.ocl.resource.ocl.IOclQuickFix> getQuickFixes();
}
