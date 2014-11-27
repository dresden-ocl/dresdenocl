/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.ui;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.quickassist.IQuickAssistAssistant;
import org.eclipse.jface.text.reconciler.IReconciler;
import org.eclipse.jface.text.reconciler.IReconcilingStrategy;
import org.eclipse.jface.text.reconciler.MonoReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.source.DefaultAnnotationHover;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditorPreferenceConstants;
import org.eclipse.ui.texteditor.spelling.ISpellingProblemCollector;
import org.eclipse.ui.texteditor.spelling.SpellingProblem;
import org.eclipse.ui.texteditor.spelling.SpellingReconcileStrategy;
import org.eclipse.ui.texteditor.spelling.SpellingService;

/**
 * This class provides the configuration for the generated editor. It registers
 * content assistance and syntax highlighting.
 */
public class OclSourceViewerConfiguration extends TextSourceViewerConfiguration {
	
	private org.dresdenocl.language.ocl.resource.ocl.ui.OclColorManager colorManager;
	private org.dresdenocl.language.ocl.resource.ocl.IOclResourceProvider resourceProvider;
	private org.dresdenocl.language.ocl.resource.ocl.ui.IOclAnnotationModelProvider annotationModelProvider;
	private org.dresdenocl.language.ocl.resource.ocl.ui.OclQuickAssistAssistant quickAssistAssistant;
	private org.dresdenocl.language.ocl.resource.ocl.ui.IOclTokenScanner tokenScanner;
	
	/**
	 * <p>
	 * Creates a new editor configuration.
	 * </p>
	 * 
	 * @param resourceProvider the provider for the resource (usually this is the
	 * editor)
	 * @param colorManager the color manager to use
	 */
	public OclSourceViewerConfiguration(org.dresdenocl.language.ocl.resource.ocl.IOclResourceProvider resourceProvider, org.dresdenocl.language.ocl.resource.ocl.ui.IOclAnnotationModelProvider annotationModelProvider, org.dresdenocl.language.ocl.resource.ocl.ui.OclColorManager colorManager) {
		super(org.dresdenocl.language.ocl.resource.ocl.ui.OclUIPlugin.getDefault().getPreferenceStore());
		this.fPreferenceStore.setDefault(SpellingService.PREFERENCE_SPELLING_ENABLED, true);
		this.fPreferenceStore.setDefault(AbstractDecoratedTextEditorPreferenceConstants.EDITOR_TAB_WIDTH, 4);
		this.fPreferenceStore.setDefault(AbstractDecoratedTextEditorPreferenceConstants.EDITOR_HYPERLINK_KEY_MODIFIER, Action.findModifierString(SWT.MOD1));
		this.resourceProvider = resourceProvider;
		this.annotationModelProvider = annotationModelProvider;
		this.colorManager = colorManager;
	}
	
	/**
	 * Returns an instance of class
	 * org.dresdenocl.language.ocl.resource.ocl.ui.OclAutoEditStrategy.
	 */
	public IAutoEditStrategy[] getAutoEditStrategies(ISourceViewer sourceViewer, String contentType) {
		return new IAutoEditStrategy[] {new org.dresdenocl.language.ocl.resource.ocl.ui.OclAutoEditStrategy()};
	}
	
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		
		ContentAssistant assistant = new ContentAssistant();
		assistant.setContentAssistProcessor(new org.dresdenocl.language.ocl.resource.ocl.ui.OclCompletionProcessor(resourceProvider), IDocument.DEFAULT_CONTENT_TYPE);
		assistant.enableAutoActivation(true);
		assistant.setAutoActivationDelay(500);
		assistant.setProposalPopupOrientation(IContentAssistant.PROPOSAL_OVERLAY);
		assistant.setContextInformationPopupOrientation(IContentAssistant.CONTEXT_INFO_ABOVE);
		
		return assistant;
	}
	
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
		};
	}
	
	protected ITokenScanner getScanner() {
		if (tokenScanner == null) {
			tokenScanner = new org.dresdenocl.language.ocl.resource.ocl.ui.OclUIMetaInformation().createTokenScanner(resourceProvider.getResource(), colorManager);
		}
		return tokenScanner;
	}
	
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		
		PresentationReconciler reconciler = new PresentationReconciler();
		DefaultDamagerRepairer repairer = new DefaultDamagerRepairer(getScanner());
		reconciler.setDamager(repairer, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(repairer, IDocument.DEFAULT_CONTENT_TYPE);
		
		return reconciler;
	}
	
	public IAnnotationHover getAnnotationHover(ISourceViewer sourceViewer) {
		return new DefaultAnnotationHover();
	}
	
	public ITextHover getTextHover(ISourceViewer sourceViewer, String contentType) {
		return new org.dresdenocl.language.ocl.resource.ocl.ui.OclTextHover(resourceProvider);
	}
	
	public IHyperlinkDetector[] getHyperlinkDetectors(ISourceViewer sourceViewer) {
		if (sourceViewer == null) {
			return null;
		}
		return new IHyperlinkDetector[] { new org.dresdenocl.language.ocl.resource.ocl.ui.OclHyperlinkDetector(resourceProvider.getResource()) };
	}
	
	public IQuickAssistAssistant getQuickAssistAssistant(ISourceViewer sourceViewer) {
		if (quickAssistAssistant == null) {
			quickAssistAssistant = new org.dresdenocl.language.ocl.resource.ocl.ui.OclQuickAssistAssistant(resourceProvider, annotationModelProvider);
		}
		return quickAssistAssistant;
	}
	
	public IReconciler getReconciler(final ISourceViewer sourceViewer) {
		if (fPreferenceStore == null || !fPreferenceStore.getBoolean(SpellingService.PREFERENCE_SPELLING_ENABLED)) {
			return null;
		}
		
		SpellingService spellingService = EditorsUI.getSpellingService();
		if (spellingService.getActiveSpellingEngineDescriptor(fPreferenceStore) == null) {
			return null;
		}
		
		IReconcilingStrategy strategy = new SpellingReconcileStrategy(sourceViewer, spellingService) {
			
			@Override
			protected ISpellingProblemCollector createSpellingProblemCollector() {
				final ISpellingProblemCollector collector = super.createSpellingProblemCollector();
				
				return new ISpellingProblemCollector() {
					
					public void accept(SpellingProblem problem) {
						int offset = problem.getOffset();
						int length = problem.getLength();
						if (sourceViewer == null) {
							return;
						}
						IDocument document = sourceViewer.getDocument();
						if (document == null) {
							return;
						}
						String text;
						try {
							text = document.get(offset, length);
						} catch (BadLocationException e) {
							return;
						}
						if (new org.dresdenocl.language.ocl.resource.ocl.ui.OclIgnoredWordsFilter().ignoreWord(text)) {
							return;
						}
						collector.accept(problem);
					}
					
					public void beginCollecting() {
						collector.beginCollecting();
					}
					
					public void endCollecting() {
						collector.endCollecting();
					}
				};
			}
		};
		
		MonoReconciler reconciler = new MonoReconciler(strategy, false);
		reconciler.setDelay(500);
		return reconciler;
	}
	
	@Override
	
	public String[] getDefaultPrefixes(ISourceViewer sourceViewer, String contentType) {
		return new String[] { "//" };
	}
	
}
