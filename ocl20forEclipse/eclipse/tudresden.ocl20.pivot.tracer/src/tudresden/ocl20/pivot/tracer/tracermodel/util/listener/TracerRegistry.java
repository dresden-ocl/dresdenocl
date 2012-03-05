/**
 * 
 */
package tudresden.ocl20.pivot.tracer.tracermodel.util.listener;

import tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot;

/**
 * <p>
 * This interface provides the utility to register as listener to the tracer
 * plug-in. The listeners will be informed when the trace changes that they can
 * update their UI.
 * </p>
 * 
 * @author Lars Schütze
 * 
 */
public interface TracerRegistry {

	public void addTracerRegistryListener(TracerRegistryListener listener);

	public void removeTracerRegistryListener(TracerRegistryListener listener);

	/** Indicates that the trace has been updated. */
	public void fireUpdateTrace(TracerRoot trace);
}
