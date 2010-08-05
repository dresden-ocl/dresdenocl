/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.ui;

public class OclMarkerResolutionGenerator implements org.eclipse.ui.IMarkerResolutionGenerator {
	
	public org.eclipse.ui.IMarkerResolution[] getResolutions(org.eclipse.core.resources.IMarker marker) {
		try {
			org.eclipse.core.resources.IResource resource = marker.getResource();
			if (resource instanceof org.eclipse.core.resources.IFile) {
				// load model
				final org.eclipse.core.resources.IFile file = (org.eclipse.core.resources.IFile) resource;
				org.eclipse.emf.common.util.URI uri = org.eclipse.emf.common.util.URI.createPlatformResourceURI(file.getFullPath().toString(), true);
				org.eclipse.emf.ecore.resource.ResourceSet rs = new org.eclipse.emf.ecore.resource.impl.ResourceSetImpl();
				org.eclipse.emf.ecore.resource.Resource emfResource = rs.getResource(uri, true);
				if (emfResource instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResource) {
					tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResource customResource = (tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResource) emfResource;
					org.eclipse.emf.ecore.util.EcoreUtil.resolveAll(customResource);
					// get data from marker (quick fix type and context object URIs)
					Object sourceIdObject = marker.getAttribute(org.eclipse.core.resources.IMarker.SOURCE_ID);
					String quickFixContext = null;
					if (sourceIdObject instanceof String) {
						quickFixContext = (String) sourceIdObject;
					}
					
					final tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclQuickFix quickFix = customResource.getQuickFix(quickFixContext);
					return new org.eclipse.ui.IMarkerResolution[] {
						new org.eclipse.ui.IMarkerResolution() {
							
							public void run(org.eclipse.core.resources.IMarker marker) {
								String newText = quickFix.apply(null);
								// set new text as content for resource
								try {
									file.setContents(new java.io.ByteArrayInputStream(newText.getBytes()), true, true, null);
								} catch (org.eclipse.core.runtime.CoreException e) {
									tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclUIPlugin.logError("Exception while applying quick fix", e);
								}
							}
							
							public String getLabel() {
								return quickFix.getDisplayString();
							}
						}
					};
				}
			}
		} catch (java.lang.Exception e) {
			tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.OclUIPlugin.logError("Exception while applying quick fix", e);
		}
		return new org.eclipse.ui.IMarkerResolution[] {};
		
	}
	
}
