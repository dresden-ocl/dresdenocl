/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp;

public class OclSyntaxElementDecorator {
	
	/**
	 * the syntax element to be decorated
	 */
	private tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclSyntaxElement decoratedElement;
	
	/**
	 * an array of child decorators (one decorator per child of the decorated syntax
	 * element
	 */
	private OclSyntaxElementDecorator[] childDecorators;
	
	/**
	 * a list of the indices that must be printed
	 */
	private java.util.List<java.lang.Integer> indicesToPrint = new java.util.ArrayList<java.lang.Integer>();
	
	public OclSyntaxElementDecorator(tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclSyntaxElement decoratedElement, OclSyntaxElementDecorator[] childDecorators) {
		super();
		this.decoratedElement = decoratedElement;
		this.childDecorators = childDecorators;
	}
	
	public void addIndexToPrint(java.lang.Integer index) {
		indicesToPrint.add(index);
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.grammar.OclSyntaxElement getDecoratedElement() {
		return decoratedElement;
	}
	
	public OclSyntaxElementDecorator[] getChildDecorators() {
		return childDecorators;
	}
	
	public java.lang.Integer getNextIndexToPrint() {
		if (indicesToPrint.size() == 0) {
			return null;
		}
		return indicesToPrint.remove(0);
	}
	
	public String toString() {
		return "" + getDecoratedElement();
	}
	
}
