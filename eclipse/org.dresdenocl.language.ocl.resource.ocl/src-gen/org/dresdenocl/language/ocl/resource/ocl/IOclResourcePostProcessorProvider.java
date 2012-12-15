/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl;

/**
 * Implementors of this interface can provide a post-processor for text resources.
 */
public interface IOclResourcePostProcessorProvider {
	
	/**
	 * Returns the processor that shall be called after text resource are successfully
	 * parsed.
	 */
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclResourcePostProcessor getResourcePostProcessor();
	
}
