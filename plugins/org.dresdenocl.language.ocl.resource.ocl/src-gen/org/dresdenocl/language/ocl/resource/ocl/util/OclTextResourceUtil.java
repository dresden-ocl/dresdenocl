/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.util;

import java.io.File;
import java.util.Map;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;

/**
 * Class OclTextResourceUtil can be used to perform common tasks on text
 * resources, such as loading and saving resources, as well as, checking them for
 * errors. This class is deprecated and has been replaced by
 * org.dresdenocl.language.ocl.resource.ocl.util.OclResourceUtil.
 */
public class OclTextResourceUtil {
	
	/**
	 * Use org.dresdenocl.language.ocl.resource.ocl.util.OclResourceUtil.getResource()
	 * instead.
	 */
	@Deprecated
	public static org.dresdenocl.language.ocl.resource.ocl.mopp.OclResource getResource(IFile file) {
		return new org.dresdenocl.language.ocl.resource.ocl.util.OclEclipseProxy().getResource(file);
	}
	
	/**
	 * Use org.dresdenocl.language.ocl.resource.ocl.util.OclResourceUtil.getResource()
	 * instead.
	 */
	@Deprecated
	public static org.dresdenocl.language.ocl.resource.ocl.mopp.OclResource getResource(File file, Map<?,?> options) {
		return org.dresdenocl.language.ocl.resource.ocl.util.OclResourceUtil.getResource(file, options);
	}
	
	/**
	 * Use org.dresdenocl.language.ocl.resource.ocl.util.OclResourceUtil.getResource()
	 * instead.
	 */
	@Deprecated
	public static org.dresdenocl.language.ocl.resource.ocl.mopp.OclResource getResource(URI uri) {
		return org.dresdenocl.language.ocl.resource.ocl.util.OclResourceUtil.getResource(uri);
	}
	
	/**
	 * Use org.dresdenocl.language.ocl.resource.ocl.util.OclResourceUtil.getResource()
	 * instead.
	 */
	@Deprecated
	public static org.dresdenocl.language.ocl.resource.ocl.mopp.OclResource getResource(URI uri, Map<?,?> options) {
		return org.dresdenocl.language.ocl.resource.ocl.util.OclResourceUtil.getResource(uri, options);
	}
	
}
