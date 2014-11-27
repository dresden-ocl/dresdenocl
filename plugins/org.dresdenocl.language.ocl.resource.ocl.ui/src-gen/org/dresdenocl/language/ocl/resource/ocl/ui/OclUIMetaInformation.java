/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.ui;

import org.eclipse.core.resources.IResource;

public class OclUIMetaInformation extends org.dresdenocl.language.ocl.resource.ocl.mopp.OclMetaInformation {
	
	public org.dresdenocl.language.ocl.resource.ocl.IOclHoverTextProvider getHoverTextProvider() {
		return new org.dresdenocl.language.ocl.resource.ocl.ui.OclHoverTextProvider();
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.ui.OclImageProvider getImageProvider() {
		return org.dresdenocl.language.ocl.resource.ocl.ui.OclImageProvider.INSTANCE;
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.ui.OclColorManager createColorManager() {
		return new org.dresdenocl.language.ocl.resource.ocl.ui.OclColorManager();
	}
	
	/**
	 * @deprecated this method is only provided to preserve API compatibility. Use
	 * createTokenScanner(org.dresdenocl.language.ocl.resource.ocl.IOclTextResource,
	 * org.dresdenocl.language.ocl.resource.ocl.ui.OclColorManager) instead.
	 */
	public org.dresdenocl.language.ocl.resource.ocl.ui.OclTokenScanner createTokenScanner(org.dresdenocl.language.ocl.resource.ocl.ui.OclColorManager colorManager) {
		return (org.dresdenocl.language.ocl.resource.ocl.ui.OclTokenScanner) createTokenScanner(null, colorManager);
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.ui.IOclTokenScanner createTokenScanner(org.dresdenocl.language.ocl.resource.ocl.IOclTextResource resource, org.dresdenocl.language.ocl.resource.ocl.ui.OclColorManager colorManager) {
		return new org.dresdenocl.language.ocl.resource.ocl.ui.OclTokenScanner(resource, colorManager);
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.ui.OclCodeCompletionHelper createCodeCompletionHelper() {
		return new org.dresdenocl.language.ocl.resource.ocl.ui.OclCodeCompletionHelper();
	}
	
	@SuppressWarnings("rawtypes")
	public Object createResourceAdapter(Object adaptableObject, Class adapterType, IResource resource) {
		return new org.dresdenocl.language.ocl.resource.ocl.ui.debug.OclLineBreakpointAdapter();
	}
	
}
