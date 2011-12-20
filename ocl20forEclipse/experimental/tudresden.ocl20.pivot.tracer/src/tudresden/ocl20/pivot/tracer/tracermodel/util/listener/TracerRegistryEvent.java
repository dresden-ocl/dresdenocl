package tudresden.ocl20.pivot.tracer.tracermodel.util.listener;

import java.util.EventObject;

import tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot;

public class TracerRegistryEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3687475668757628934L;

	/**
	 * 
	 */
	private TracerRoot trace;

	/**
	 * 
	 * @param source
	 * @param trace
	 */
	public TracerRegistryEvent(TracerRegistry source, TracerRoot trace) {

		super(source);
		this.trace = trace;
	}

	/**
	 * 
	 */
	@Override
	public TracerRegistry getSource() {

		return (TracerRegistry) source;
	}

	/**
	 * 
	 * @return
	 */
	public TracerRoot getTrace() {

		return trace;
	}
}
