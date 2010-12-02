package tudresden.ocl20.eclipse.plugins.ocleditor.widgets.source;

import java.util.ArrayList;

import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.VerticalRuler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;

import tudresden.ocl20.eclipse.plugins.ocleditor.widgets.source.highlighting.OCLScanner;

/**
 * This is a source editor component with syntax highlighting for OCL constraints.
 * @author Mirk
 *
 */
public class SourceEditor extends Composite implements ITextListener 
{
	private SourceViewer sourceViewer = null;
	private ArrayList<ModifyListener> modifyListener = new ArrayList<ModifyListener>();
	private Composite content;
	
	/**
	 * Constructor
	 * @param parent
	 */
	public SourceEditor(Composite parent) 
	{
		super(parent, SWT.NONE);		
		this.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY));
		this.init();
	}
	
	private void init()
	{
		this.content = new Composite(this, SWT.NONE);
		this.setLayout(new FormLayout());
		FormData formData = new FormData();
		formData.left = new FormAttachment(0, 1);
		formData.right = new FormAttachment(100, -1);
		formData.top = new FormAttachment(0, 1);
		formData.bottom = new FormAttachment(100, -1);
		content.setLayoutData(formData);
		content.setLayout(new FillLayout());
		sourceViewer = new SourceViewer(content,
				new VerticalRuler(10), SWT.NONE);
		PresentationReconciler reconciler = new PresentationReconciler();
		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(
				new OCLScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.install(this.sourceViewer);
		sourceViewer.setDocument(new Document());
	}
	
	/**
	 * Returns the document instance of the editor.
	 * @return document
	 */
	public IDocument getDocument()
	{
		return this.sourceViewer.getDocument();
	}
	
	/**
	 * Can be used to add a modify listener.
	 * @param listener
	 */
	public void addModifyListener(ModifyListener listener)
	{
		this.modifyListener.add(listener);
		this.sourceViewer.addTextListener(this);
	}

	public void textChanged(TextEvent event) 
	{
		Event typedEvent = new Event();
		typedEvent.widget = this.sourceViewer.getControl();
		ModifyEvent modifyEvent = new ModifyEvent(typedEvent);
				
		for (int i = 0; i < this.modifyListener.size(); i++)
		{
			this.modifyListener.get(i).modifyText(modifyEvent);
		}
	}	
	
	public boolean setFocus()
	{
		super.setFocus();
		return this.sourceViewer.getTextWidget().setFocus();
	}
}
