/**
 * <copyright>
 * </copyright>
 *
 * 
 */
/**
 * This class can be used to configure options that must be used when resources
 * are loaded. This is similar to using the extension point
 * 'tudresden.ocl20.pivot.language.ocl.resource.ocl.default_load_options' with the
 * difference that the options defined in this class are used even if no Eclipse
 * platform is running.
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp;

public class OclOptionProvider implements tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclOptionProvider {
	
	public java.util.Map<?,?> getOptions() {
		// create a map with static option providers here
		return java.util.Collections.emptyMap();
	}
	
}
