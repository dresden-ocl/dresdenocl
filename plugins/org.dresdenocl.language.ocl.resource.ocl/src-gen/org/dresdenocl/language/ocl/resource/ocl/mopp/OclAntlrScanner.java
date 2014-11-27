/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;

import org.antlr.runtime3_4_0.ANTLRStringStream;
import org.antlr.runtime3_4_0.Lexer;
import org.antlr.runtime3_4_0.Token;

public class OclAntlrScanner implements org.dresdenocl.language.ocl.resource.ocl.IOclTextScanner {
	
	private Lexer antlrLexer;
	
	public OclAntlrScanner(Lexer antlrLexer) {
		this.antlrLexer = antlrLexer;
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.IOclTextToken getNextToken() {
		if (antlrLexer.getCharStream() == null) {
			return null;
		}
		final Token current = antlrLexer.nextToken();
		if (current == null || current.getType() < 0) {
			return null;
		}
		org.dresdenocl.language.ocl.resource.ocl.IOclTextToken result = new org.dresdenocl.language.ocl.resource.ocl.mopp.OclANTLRTextToken(current);
		return result;
	}
	
	public void setText(String text) {
		antlrLexer.setCharStream(new ANTLRStringStream(text));
	}
	
}
