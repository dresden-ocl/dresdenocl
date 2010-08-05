/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.ui;

/**
 * Helper class to add markers to text files based on EMF's
 * <code>org.eclipse.emf.ecore.resource.Resource.Diagnostic</code>. If a resource
 * contains
 * <code>tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextDiagnostic</code>s
 * it uses the more precise information of this extended diagnostic type.
 */
public class OclMarkerHelper {
	
	public static final String MARKER_TYPE = tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclUIPlugin.PLUGIN_ID + ".problem";
	/**
	 * The total number of markers per file is restricted with this constant.
	 * Restriction is needed because the performance of Eclipse decreases drastically
	 * if large amounts of markes are added to files.
	 */
	public static int MAXIMUM_MARKERS = 500;
	
	/**
	 * Marks a file with markers.
	 * 
	 * @param resource The resource that is the file to mark.
	 * 
	 * @throws org.eclipse.core.runtime.CoreException
	 */
	public static void mark(org.eclipse.emf.ecore.resource.Resource resource) throws org.eclipse.core.runtime.CoreException {
		if (resource == null) {
			return;
		}
		org.eclipse.core.resources.IFile file = (org.eclipse.core.resources.IFile) org.eclipse.core.resources.ResourcesPlugin.getWorkspace().getRoot().findMember(resource.getURI().toPlatformString(true));
		// URI might not point at a platform file
		if (file == null) {
			return;
		}
		createMarkersFromDiagnostics(file, resource.getErrors(), org.eclipse.core.resources.IMarker.SEVERITY_ERROR);
		createMarkersFromDiagnostics(file, resource.getWarnings(), org.eclipse.core.resources.IMarker.SEVERITY_WARNING);
	}
	
	private static void createMarkersFromDiagnostics(org.eclipse.core.resources.IFile file, java.util.List<org.eclipse.emf.ecore.resource.Resource.Diagnostic> diagnostics, int markerSeverity) throws org.eclipse.core.runtime.CoreException {
		
		int createdMarkers = 0;
		for (org.eclipse.emf.ecore.resource.Resource.Diagnostic diagnostic : diagnostics) {
			try {
				org.eclipse.core.resources.IMarker marker = file.createMarker(MARKER_TYPE);
				marker.setAttribute(org.eclipse.core.resources.IMarker.SEVERITY, markerSeverity);
				marker.setAttribute(org.eclipse.core.resources.IMarker.MESSAGE, diagnostic.getMessage());
				if (diagnostic instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextDiagnostic) {
					tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextDiagnostic textDiagnostic = (tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextDiagnostic) diagnostic;
					marker.setAttribute(org.eclipse.core.resources.IMarker.LINE_NUMBER, textDiagnostic.getLine());
					marker.setAttribute(org.eclipse.core.resources.IMarker.CHAR_START, textDiagnostic.getCharStart());
					marker.setAttribute(org.eclipse.core.resources.IMarker.CHAR_END, textDiagnostic.getCharEnd() + 1);
					java.util.Collection<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix> quickFixes = textDiagnostic.getProblem().getQuickFixes();
					java.util.Collection<Object> sourceIDs = new java.util.ArrayList<Object>();
					if (quickFixes != null) {
						for (tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix quickFix : quickFixes) {
							if (quickFix != null) {
								sourceIDs.add(quickFix.getContextAsString());
							}
						}
					}
					if (!sourceIDs.isEmpty()) {
						marker.setAttribute(org.eclipse.core.resources.IMarker.SOURCE_ID, tudresden.ocl20.pivot.language.ocl.resource.ocl.util.OclStringUtil.explode(sourceIDs, "|"));
					}
				}
				else {
					marker.setAttribute(org.eclipse.core.resources.IMarker.CHAR_START, 0);
					marker.setAttribute(org.eclipse.core.resources.IMarker.CHAR_END, 1);
				}
			} catch (org.eclipse.core.runtime.CoreException ce) {
				if (ce.getMessage().matches("Marker.*not found.")) {
					// ignore
				} else {
					ce.printStackTrace();
				}
			}
			createdMarkers++;
			if (createdMarkers >= MAXIMUM_MARKERS) {
				return;
			}
		}
	}
	
	/**
	 * Removes all markers from a given resource.
	 * 
	 * @param resource The resource where to delete markers from
	 * 
	 * @throws org.eclipse.core.runtime.CoreException
	 */
	public static void unmark(org.eclipse.emf.ecore.resource.Resource resource) throws org.eclipse.core.runtime.CoreException {
		org.eclipse.core.resources.IFile file = (org.eclipse.core.resources.IFile) org.eclipse.core.resources.ResourcesPlugin.getWorkspace().getRoot().findMember(resource.getURI().toPlatformString(true));
		if (file != null) {
			file.deleteMarkers(tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclMarkerHelper.MARKER_TYPE, false, org.eclipse.core.resources.IResource.DEPTH_ZERO);
		}
	}
}
