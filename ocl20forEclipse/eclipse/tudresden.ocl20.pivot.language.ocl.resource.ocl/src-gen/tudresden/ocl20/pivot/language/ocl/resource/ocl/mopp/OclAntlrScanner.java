/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp;

public class OclAntlrScanner implements tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextScanner {
	
	private org.antlr.runtime3_4_0.Lexer antlrLexer;
	
	public OclAntlrScanner(org.antlr.runtime3_4_0.Lexer antlrLexer) {
		this.antlrLexer = antlrLexer;
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextToken getNextToken() {
		if (antlrLexer.getCharStream() == null) {
			return null;
		}
		final org.antlr.runtime3_4_0.Token current = antlrLexer.nextToken();
		if (current == null || current.getType() < 0) {
			return null;
		}
		tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextToken result = new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclANTLRTextToken(current);
		return result;
	}
	
	public void setText(String text) {
		antlrLexer.setCharStream(new org.antlr.runtime3_4_0.ANTLRStringStream(text));
	}
	
}
