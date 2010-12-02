/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl;

/**
 * A simple interface for commands that can be executed and that return
 * information about the success of their execution.
 */
public interface IOclCommand<ContextType> {
	
	public boolean execute(ContextType context);
}
