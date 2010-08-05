/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.ui;

public class OclMarkerAnnotation extends org.eclipse.ui.texteditor.MarkerAnnotation implements org.eclipse.jface.text.quickassist.IQuickFixableAnnotation {
	
	boolean quickFixableState;
	boolean isQuickFixable;
	
	public OclMarkerAnnotation(org.eclipse.core.resources.IMarker marker) {
		super(marker);
	}
	
	public void setQuickFixable(boolean state) {
		isQuickFixable = state;
		quickFixableState = true;
	}
	
	public boolean isQuickFixableStateSet() {
		return true;
		//return quickFixableState;
	}
	
	public boolean isQuickFixable() {
		try {
			return getMarker().getAttribute(org.eclipse.core.resources.IMarker.SOURCE_ID) != null;
		} catch (org.eclipse.core.runtime.CoreException e) {
			// ignore
		}
		return false;
	}
	
}
