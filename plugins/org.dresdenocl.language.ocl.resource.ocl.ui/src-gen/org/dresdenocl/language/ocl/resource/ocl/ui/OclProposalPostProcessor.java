/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.ui;

import java.util.List;

/**
 * A class which can be overridden to customize code completion proposals.
 */
public class OclProposalPostProcessor {
	
	public List<org.dresdenocl.language.ocl.resource.ocl.ui.OclCompletionProposal> process(List<org.dresdenocl.language.ocl.resource.ocl.ui.OclCompletionProposal> proposals) {
		// the default implementation does returns the proposals as they are
		return proposals;
	}
	
}
