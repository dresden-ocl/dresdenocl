/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.ui;

public class OclQuickAssistProcessor implements org.eclipse.jface.text.quickassist.IQuickAssistProcessor {
	
	private tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclEditor editor;
	
	public OclQuickAssistProcessor(tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclEditor editor) {
		super();
		this.editor = editor;
	}
	
	public boolean canAssist(org.eclipse.jface.text.quickassist.IQuickAssistInvocationContext invocationContext) {
		return false;
	}
	
	public boolean canFix(org.eclipse.jface.text.source.Annotation annotation) {
		java.util.Collection<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix> quickFixes = getQuickFixes(annotation);
		return quickFixes.size() > 0;
	}
	
	public org.eclipse.jface.text.contentassist.ICompletionProposal[] computeQuickAssistProposals(	org.eclipse.jface.text.quickassist.IQuickAssistInvocationContext invocationContext) {
		org.eclipse.jface.text.source.ISourceViewer sourceViewer = invocationContext.getSourceViewer();
		int offset = -1;
		int length = 0;
		if (invocationContext instanceof org.eclipse.jface.text.source.TextInvocationContext) {
			org.eclipse.jface.text.source.TextInvocationContext textContext = (org.eclipse.jface.text.source.TextInvocationContext) invocationContext;
			offset = textContext.getOffset();
			length = textContext.getLength();
		}
		java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix> quickFixes = getQuickFixes(sourceViewer, offset, length);
		org.eclipse.jface.text.contentassist.ICompletionProposal[] proposals = new org.eclipse.jface.text.contentassist.ICompletionProposal[quickFixes.size()];
		for (int i = 0; i < proposals.length; i++) {
			proposals[i] = createCompletionProposal(sourceViewer, quickFixes.get(i));
		}
		return proposals;
	}
	
	private org.eclipse.jface.text.contentassist.ICompletionProposal createCompletionProposal(final org.eclipse.jface.text.source.ISourceViewer sourceViewer, final tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix quickFix) {
		return new org.eclipse.jface.text.contentassist.ICompletionProposal() {
			
			public org.eclipse.swt.graphics.Point getSelection(org.eclipse.jface.text.IDocument document) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public org.eclipse.swt.graphics.Image getImage() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getDisplayString() {
				return quickFix.getDisplayString();
			}
			
			public org.eclipse.jface.text.contentassist.IContextInformation getContextInformation() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getAdditionalProposalInfo() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public void apply(org.eclipse.jface.text.IDocument document) {
				String currentContent = sourceViewer.getDocument().get();
				String newContent = quickFix.apply(currentContent);
				if (newContent != null) {
					// TODO maybe it is better to replace only the changed
					// part of the document
					sourceViewer.getDocument().set(newContent);
				}
			}
		};
	}
	
	private java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix> getQuickFixes(org.eclipse.jface.text.source.ISourceViewer sourceViewer, int offset, int length) {
		java.util.List<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix> foundFixes = new java.util.ArrayList<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix>();
		org.eclipse.jface.text.source.IAnnotationModel model = getAnnotationModel();
		
		if (model == null) {
			return foundFixes;
		}
		
		java.util.Iterator<?> iter = model.getAnnotationIterator();
		while (iter.hasNext()) {
			org.eclipse.jface.text.source.Annotation annotation = (org.eclipse.jface.text.source.Annotation) iter.next();
			org.eclipse.jface.text.Position position = model.getPosition(annotation);
			if (offset >= 0) {
				if (!position.overlapsWith(offset, length)) {
					continue;
				}
			}
			java.util.Collection<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix> quickFixes = getQuickFixes(annotation);
			if (quickFixes != null) {
				foundFixes.addAll(quickFixes);
			}
		}
		return foundFixes;
	}
	
	private org.eclipse.jface.text.source.IAnnotationModel getAnnotationModel() {
		return editor.getDocumentProvider().getAnnotationModel(editor.getEditorInput());
	}
	
	private java.util.Collection<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix> getQuickFixes(org.eclipse.jface.text.source.Annotation annotation) {
		
		java.util.Collection<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix> foundQuickFixes = new java.util.ArrayList<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix>();
		if (annotation.isMarkedDeleted()) {
			return foundQuickFixes;
		}
		
		if (annotation instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclMarkerAnnotation) {
			tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclMarkerAnnotation markerAnnotation = (tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclMarkerAnnotation) annotation;
			org.eclipse.core.resources.IMarker marker = markerAnnotation.getMarker();
			try {
				Object quickFixValue = marker.getAttribute(org.eclipse.core.resources.IMarker.SOURCE_ID);
				if (quickFixValue != null && quickFixValue instanceof String) {
					String quickFixContexts = (String) quickFixValue;
					String[] quickFixContextParts = quickFixContexts.split("\\|");
					for (String quickFixContext : quickFixContextParts) {
						tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix quickFix = editor.getResource().getQuickFix(quickFixContext);
						if (quickFix != null) {
							foundQuickFixes.add(quickFix);
						}
					}
				}
			} catch (org.eclipse.core.runtime.CoreException ce) {
				if (ce.getMessage().matches("Marker.*not found.")) {
					// ignore
					System.out.println("getQuickFixes() marker not found: " + ce.getMessage());
				} else {
					ce.printStackTrace();
				}
			}
		}
		return foundQuickFixes;
	}
	
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
