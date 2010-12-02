/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.ui;

/**
 * A class which can be overridden to customize code completion proposals.
 */
public class OclProposalPostProcessor {
	
	public java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclCompletionProposal> process(java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclCompletionProposal> proposals) {
		// the default implementation does returns the proposals as they are
		return proposals;
	}
	
}
