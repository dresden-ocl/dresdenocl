/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.ui;

/**
 * An adapter from the Eclipse
 * <code>org.eclipse.jface.text.rules.ITokenScanner</code> interface to the
 * generated lexer.
 */
public class OclTokenScanner implements org.eclipse.jface.text.rules.ITokenScanner {
	
	private tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextScanner lexer;
	private tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextToken currentToken;
	private int offset;
	private String languageId;
	private org.eclipse.jface.preference.IPreferenceStore store;
	private tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclColorManager colorManager;
	
	/**
	 * 
	 * @param colorManager A manager to obtain color objects
	 */
	public OclTokenScanner(tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclColorManager colorManager) {
		this.lexer = new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclMetaInformation().createLexer();
		this.languageId = new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclMetaInformation().getSyntaxName();
		this.store = tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclUIPlugin.getDefault().getPreferenceStore();
		this.colorManager = colorManager;
	}
	
	public int getTokenLength() {
		return currentToken.getLength();
	}
	
	public int getTokenOffset() {
		return offset + currentToken.getOffset();
	}
	
	public org.eclipse.jface.text.rules.IToken nextToken() {
		currentToken = lexer.getNextToken();
		if (currentToken == null || !currentToken.canBeUsedForSyntaxHighlighting()) {
			return org.eclipse.jface.text.rules.Token.EOF;
		}
		org.eclipse.jface.text.TextAttribute ta = null;
		String tokenName = currentToken.getName();
		if (tokenName != null) {
			String enableKey = tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.StyleProperty.ENABLE);
			if (store.getBoolean(enableKey)) {
				String colorKey = tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.StyleProperty.COLOR);
				org.eclipse.swt.graphics.Color color = colorManager.getColor(org.eclipse.jface.preference.PreferenceConverter.getColor(store, colorKey));
				int style = org.eclipse.swt.SWT.NORMAL;
				if (store.getBoolean(tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.StyleProperty.BOLD))) {
					style = style | org.eclipse.swt.SWT.BOLD;
				}
				if (store.getBoolean(tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.StyleProperty.ITALIC))) {
					style = style | org.eclipse.swt.SWT.ITALIC;
				}
				if (store.getBoolean(tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.StyleProperty.STRIKETHROUGH))) {
					style = style | org.eclipse.jface.text.TextAttribute.STRIKETHROUGH;
				}
				if (store.getBoolean(tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclSyntaxColoringHelper.StyleProperty.UNDERLINE))) {
					style = style | org.eclipse.jface.text.TextAttribute.UNDERLINE;
				}
				ta = new org.eclipse.jface.text.TextAttribute(color, null, style);
			}
		}
		return new org.eclipse.jface.text.rules.Token(ta);
	}
	
	public void setRange(org.eclipse.jface.text.IDocument document, int offset, int length) {
		this.offset = offset;
		try {
			lexer.setText(document.get(offset, length));
		} catch (org.eclipse.jface.text.BadLocationException e) {
			// ignore this error. It might occur during editing when locations are outdated
			// quickly.
		}
	}
	
	public String getTokenText() {
		return currentToken.getText();
	}
}
