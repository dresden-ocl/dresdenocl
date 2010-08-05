/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.ui;

/**
 * A proposal for completing an incomplete document.
 */
public class OclCompletionProposal implements java.lang.Comparable<OclCompletionProposal> {
	private java.lang.String insertString;
	private java.lang.String displayString;
	private java.lang.String prefix;
	private boolean startsWithPrefix;
	private org.eclipse.emf.ecore.EStructuralFeature structuralFeature;
	private org.eclipse.emf.ecore.EObject container;
	private org.eclipse.swt.graphics.Image image;
	
	public OclCompletionProposal(java.lang.String insertString, java.lang.String prefix, boolean startsWithPrefix, org.eclipse.emf.ecore.EStructuralFeature structuralFeature, org.eclipse.emf.ecore.EObject container) {
		super();
		this.insertString = insertString;
		this.prefix = prefix;
		this.startsWithPrefix = startsWithPrefix;
		this.structuralFeature = structuralFeature;
		this.container = container;
	}
	
	public OclCompletionProposal(java.lang.String insertString, java.lang.String prefix, boolean startsWithPrefix, org.eclipse.emf.ecore.EStructuralFeature structuralFeature, org.eclipse.emf.ecore.EObject container, org.eclipse.swt.graphics.Image image) {
		this(insertString, prefix, startsWithPrefix, structuralFeature, container);
		this.image = image;
	}
	
	public OclCompletionProposal(java.lang.String insertString, java.lang.String prefix, boolean startsWithPrefix, org.eclipse.emf.ecore.EStructuralFeature structuralFeature, org.eclipse.emf.ecore.EObject container, org.eclipse.swt.graphics.Image image, String displayString) {
		this(insertString, prefix, startsWithPrefix, structuralFeature, container, image);
		this.displayString = displayString;
	}
	
	public java.lang.String getInsertString() {
		return insertString;
	}
	
	public java.lang.String getDisplayString() {
		return displayString;
	}
	
	public java.lang.String getPrefix() {
		return prefix;
	}
	
	public boolean getStartsWithPrefix() {
		return startsWithPrefix;
	}
	
	public org.eclipse.swt.graphics.Image getImage() {
		return image;
	}
	
	public boolean isStructuralFeature() {
		return structuralFeature != null;
	}
	
	public org.eclipse.emf.ecore.EStructuralFeature getStructuralFeature() {
		return structuralFeature;
	}
	
	public org.eclipse.emf.ecore.EObject getContainer() {
		return container;
	}
	
	public boolean equals(Object object) {
		if (object instanceof OclCompletionProposal) {
			OclCompletionProposal other = (OclCompletionProposal) object;
			return other.getInsertString().equals(getInsertString());
		}
		return false;
	}
	
	public int hashCode() {
		return getInsertString().hashCode();
	}
	
	public int compareTo(OclCompletionProposal object) {
		if (object instanceof OclCompletionProposal) {
			OclCompletionProposal other = (OclCompletionProposal) object;
			// proposals that start with the prefix are preferred over the ones that do not
			int startCompare = (startsWithPrefix ? 1 : 0) - (other.getStartsWithPrefix() ? 1 : 0);
			// if both proposals start with the prefix of both do not the insert string is
			// compared
			return startCompare == 0 ? getInsertString().compareTo(other.getInsertString()) : -startCompare;
		}
		return -1;
	}
	
}
