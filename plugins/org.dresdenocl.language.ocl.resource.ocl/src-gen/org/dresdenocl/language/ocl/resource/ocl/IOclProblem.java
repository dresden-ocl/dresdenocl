/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl;

import java.util.Collection;

public interface IOclProblem {
	public String getMessage();
	public org.dresdenocl.language.ocl.resource.ocl.OclEProblemSeverity getSeverity();
	public org.dresdenocl.language.ocl.resource.ocl.OclEProblemType getType();
	public Collection<org.dresdenocl.language.ocl.resource.ocl.IOclQuickFix> getQuickFixes();
}
