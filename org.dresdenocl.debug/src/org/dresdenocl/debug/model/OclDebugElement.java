package org.dresdenocl.debug.model;

import org.dresdenocl.debug.OclDebugPlugin;
import org.eclipse.debug.core.model.DebugElement;

public abstract class OclDebugElement extends DebugElement {

	protected OclDebugTarget m_target;
	
	public OclDebugElement(OclDebugTarget target) {
		super(target);
		m_target = target;
	}

	@Override
	public String getModelIdentifier() {
		return OclDebugPlugin.DEBUG_MODEL_ID;
	}
	
	/**
	 * Returns the breakpoint manager.
	 * 
	 * @return the breakpoint manager
	 */
	protected org.eclipse.debug.core.IBreakpointManager getBreakpointManager() {
		return org.eclipse.debug.core.DebugPlugin.getDefault().getBreakpointManager();
	}
	
	protected void abort(String reason, Throwable e) {
		
	}
}
