/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.ui;

public class OclCompletionProcessor implements org.eclipse.jface.text.contentassist.IContentAssistProcessor {
	
	private org.dresdenocl.language.ocl.resource.ocl.IOclResourceProvider resourceProvider;
	private org.dresdenocl.language.ocl.resource.ocl.ui.IOclBracketHandlerProvider bracketHandlerProvider;
	
	public OclCompletionProcessor(org.dresdenocl.language.ocl.resource.ocl.IOclResourceProvider resourceProvider, org.dresdenocl.language.ocl.resource.ocl.ui.IOclBracketHandlerProvider bracketHandlerProvider) {
		this.resourceProvider = resourceProvider;
		this.bracketHandlerProvider = bracketHandlerProvider;
	}
	
	public org.eclipse.jface.text.contentassist.ICompletionProposal[] computeCompletionProposals(org.eclipse.jface.text.ITextViewer viewer, int offset) {
		org.dresdenocl.language.ocl.resource.ocl.IOclTextResource textResource = resourceProvider.getResource();
		if (textResource == null) {
			return new org.eclipse.jface.text.contentassist.ICompletionProposal[0];
		}
		String content = viewer.getDocument().get();
		org.dresdenocl.language.ocl.resource.ocl.ui.OclCodeCompletionHelper helper = new org.dresdenocl.language.ocl.resource.ocl.ui.OclCodeCompletionHelper();
		org.dresdenocl.language.ocl.resource.ocl.ui.OclCompletionProposal[] computedProposals = helper.computeCompletionProposals(textResource, content, offset);
		
		// call completion proposal post processor to allow for customizing the proposals
		org.dresdenocl.language.ocl.resource.ocl.ui.OclProposalPostProcessor proposalPostProcessor = new org.dresdenocl.language.ocl.resource.ocl.ui.OclProposalPostProcessor();
		java.util.List<org.dresdenocl.language.ocl.resource.ocl.ui.OclCompletionProposal> computedProposalList = java.util.Arrays.asList(computedProposals);
		java.util.List<org.dresdenocl.language.ocl.resource.ocl.ui.OclCompletionProposal> extendedProposalList = proposalPostProcessor.process(computedProposalList);
		if (extendedProposalList == null) {
			extendedProposalList = java.util.Collections.emptyList();
		}
		java.util.List<org.dresdenocl.language.ocl.resource.ocl.ui.OclCompletionProposal> finalProposalList = new java.util.ArrayList<org.dresdenocl.language.ocl.resource.ocl.ui.OclCompletionProposal>();
		for (org.dresdenocl.language.ocl.resource.ocl.ui.OclCompletionProposal proposal : extendedProposalList) {
			if (proposal.getMatchesPrefix()) {
				finalProposalList.add(proposal);
			}
		}
		org.eclipse.jface.text.contentassist.ICompletionProposal[] result = new org.eclipse.jface.text.contentassist.ICompletionProposal[finalProposalList.size()];
		int i = 0;
		for (org.dresdenocl.language.ocl.resource.ocl.ui.OclCompletionProposal proposal : finalProposalList) {
			String proposalString = proposal.getInsertString();
			String displayString = proposal.getDisplayString();
			String prefix = proposal.getPrefix();
			org.eclipse.swt.graphics.Image image = proposal.getImage();
			org.eclipse.jface.text.contentassist.IContextInformation info;
			info = new org.eclipse.jface.text.contentassist.ContextInformation(image, proposalString, proposalString);
			int begin = offset - prefix.length();
			int replacementLength = prefix.length();
			// if a closing bracket was automatically inserted right before, we enlarge the
			// replacement length in order to overwrite the bracket.
			org.dresdenocl.language.ocl.resource.ocl.ui.IOclBracketHandler bracketHandler = bracketHandlerProvider.getBracketHandler();
			String closingBracket = bracketHandler.getClosingBracket();
			if (bracketHandler.addedClosingBracket() && proposalString.endsWith(closingBracket)) {
				replacementLength += closingBracket.length();
			}
			result[i++] = new org.eclipse.jface.text.contentassist.CompletionProposal(proposalString, begin, replacementLength, proposalString.length(), image, displayString, info, proposalString);
		}
		return result;
	}
	
	public org.eclipse.jface.text.contentassist.IContextInformation[] computeContextInformation(org.eclipse.jface.text.ITextViewer viewer, int offset) {
		return null;
	}
	
	public char[] getCompletionProposalAutoActivationCharacters() {
		return null;
	}
	
	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}
	
	public org.eclipse.jface.text.contentassist.IContextInformationValidator getContextInformationValidator() {
		return null;
	}
	
	public String getErrorMessage() {
		return null;
	}
}
