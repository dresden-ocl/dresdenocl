/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp;

public class OclAntlrScanner implements tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextScanner {
	
	private org.antlr.runtime3_2_0.Lexer antlrLexer;
	
	public OclAntlrScanner(org.antlr.runtime3_2_0.Lexer antlrLexer) {
		this.antlrLexer = antlrLexer;
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextToken getNextToken() {
		if (antlrLexer.getCharStream() == null) {
			return null;
		}
		final org.antlr.runtime3_2_0.Token current = antlrLexer.nextToken();
		tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextToken result = new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclTextToken(current);
		return result;
	}
	
	public void setText(java.lang.String text) {
		antlrLexer.setCharStream(new org.antlr.runtime3_2_0.ANTLRStringStream(text));
	}
	
}
