/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.ui;

public class OclCompletionProcessor implements org.eclipse.jface.text.contentassist.IContentAssistProcessor {
	
	private tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclEditor editor;
	
	public OclCompletionProcessor(tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclEditor editor) {
		this.editor = editor;
	}
	
	public org.eclipse.jface.text.contentassist.ICompletionProposal[] computeCompletionProposals(org.eclipse.jface.text.ITextViewer viewer, int offset) {
		
		org.eclipse.emf.ecore.resource.Resource resource = editor.getResource();
		tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextResource textResource = (tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextResource) resource;
		java.lang.String content = viewer.getDocument().get();
		tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclCodeCompletionHelper helper = new tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclCodeCompletionHelper();
		tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclCompletionProposal[] proposals = helper.computeCompletionProposals(textResource, content, offset);
		
		// call completion proposal post processor to allow for customizing the proposals
		tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclProposalPostProcessor proposalPostProcessor = new tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclProposalPostProcessor();
		proposals = proposalPostProcessor.process(proposals);
		org.eclipse.jface.text.contentassist.ICompletionProposal[] result = new org.eclipse.jface.text.contentassist.ICompletionProposal[proposals.length];
		int i = 0;
		for (tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclCompletionProposal proposal : proposals) {
			java.lang.String proposalString = proposal.getInsertString();
			java.lang.String displayString = proposal.getDisplayString();
			java.lang.String prefix = proposal.getPrefix();
			org.eclipse.swt.graphics.Image image = proposal.getImage();
			org.eclipse.jface.text.contentassist.IContextInformation info;
			info = new org.eclipse.jface.text.contentassist.ContextInformation(image, proposalString, proposalString);
			int begin = offset - prefix.length();
			int replacementLength = prefix.length();
			// if a closing bracket was automatically inserted right before, we enlarge the
			// replacement length in order to overwrite the bracket.
			tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.IOclBracketHandler bracketHandler = editor.getBracketHandler();
			java.lang.String closingBracket = bracketHandler.getClosingBracket();
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
