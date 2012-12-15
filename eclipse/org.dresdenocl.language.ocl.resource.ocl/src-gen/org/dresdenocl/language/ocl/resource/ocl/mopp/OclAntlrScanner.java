/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;

public class OclAntlrScanner implements org.dresdenocl.language.ocl.resource.ocl.IOclTextScanner {
	
	private org.antlr.runtime3_4_0.Lexer antlrLexer;
	
	public OclAntlrScanner(org.antlr.runtime3_4_0.Lexer antlrLexer) {
		this.antlrLexer = antlrLexer;
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.IOclTextToken getNextToken() {
		if (antlrLexer.getCharStream() == null) {
			return null;
		}
		final org.antlr.runtime3_4_0.Token current = antlrLexer.nextToken();
		if (current == null || current.getType() < 0) {
			return null;
		}
		org.dresdenocl.language.ocl.resource.ocl.IOclTextToken result = new org.dresdenocl.language.ocl.resource.ocl.mopp.OclANTLRTextToken(current);
		return result;
	}
	
	public void setText(String text) {
		antlrLexer.setCharStream(new org.antlr.runtime3_4_0.ANTLRStringStream(text));
	}
	
}
