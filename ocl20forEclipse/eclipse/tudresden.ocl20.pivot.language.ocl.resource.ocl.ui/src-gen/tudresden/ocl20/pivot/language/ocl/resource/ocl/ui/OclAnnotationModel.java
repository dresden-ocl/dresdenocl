/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.ui;

public class OclAnnotationModel extends org.eclipse.ui.texteditor.ResourceMarkerAnnotationModel implements org.eclipse.jface.text.source.IAnnotationModel {
	
	public OclAnnotationModel(org.eclipse.core.resources.IResource resource) {
		super(resource);
	}
	
	protected org.eclipse.ui.texteditor.MarkerAnnotation createMarkerAnnotation(org.eclipse.core.resources.IMarker marker) {
		return new tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclMarkerAnnotation(marker);
	}
	
}
