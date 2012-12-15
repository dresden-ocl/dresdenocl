/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp;

public class OclBracketInformationProvider {
	
	public class BracketPair implements tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclBracketPair {
		
		private String opening;
		private String closing;
		private boolean closingEnabledInside;
		
		public BracketPair(String opening, String closing, boolean closingEnabledInside) {
			super();
			this.opening = opening;
			this.closing = closing;
			this.closingEnabledInside = closingEnabledInside;
		}
		
		public String getOpeningBracket() {
			return opening;
		}
		
		public String getClosingBracket() {
			return closing;
		}
		
		public boolean isClosingEnabledInside() {
			return closingEnabledInside;
		}
	}
	
	public java.util.Collection<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclBracketPair> getBracketPairs() {
		java.util.Collection<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclBracketPair> result = new java.util.ArrayList<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclBracketPair>();
		result.add(new BracketPair("(", ")", true));
		result.add(new BracketPair("{", "}", true));
		result.add(new BracketPair("'", "'", false));
		return result;
	}
	
}
