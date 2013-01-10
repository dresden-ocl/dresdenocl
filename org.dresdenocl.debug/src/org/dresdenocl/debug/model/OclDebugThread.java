package org.dresdenocl.debug.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;

public class OclDebugThread extends OclDebugElement implements IThread {

	private IBreakpoint[] m_breakpoints;
	private boolean m_stepping = false;

	public OclDebugThread(OclDebugTarget target) {

		super(target);
	}

	@Override
	public boolean canResume() {

		return false;
	}

	@Override
	public boolean canSuspend() {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSuspended() {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resume() throws DebugException {

		// TODO Auto-generated method stub

	}

	@Override
	public void suspend() throws DebugException {

		// TODO Auto-generated method stub

	}

	@Override
	public boolean canStepInto() {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canStepOver() {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canStepReturn() {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isStepping() {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void stepInto() throws DebugException {

		// TODO Auto-generated method stub

	}

	@Override
	public void stepOver() throws DebugException {

		// TODO Auto-generated method stub

	}

	@Override
	public void stepReturn() throws DebugException {

		// TODO Auto-generated method stub

	}

	@Override
	public boolean canTerminate() {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTerminated() {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void terminate() throws DebugException {

		// TODO Auto-generated method stub

	}

	@Override
	public IStackFrame[] getStackFrames() throws DebugException {

		if (isSuspended()) {
			return m_target.getStackFrames();
		}
		else {
			return new IStackFrame[0];
		}
	}

	@Override
	public boolean hasStackFrames() throws DebugException {

		return isSuspended();
	}

	@Override
	public int getPriority() throws DebugException {

		return 0;
	}

	@Override
	public IStackFrame getTopStackFrame() throws DebugException {

		IStackFrame[] frames = getStackFrames();
		if (frames.length > 0) {
			return frames[0];
		}
		return null;
	}

	@Override
	public String getName() throws DebugException {

		return "Thread [main]";
	}

	@Override
	public IBreakpoint[] getBreakpoints() {
		if(m_breakpoints == null) {
			return (m_breakpoints = new IBreakpoint[0]);
		}
		return m_breakpoints;
	}

	public void setStepping(boolean b) {
		m_stepping = b;
	}

	public void setBreakpoints(IBreakpoint[] breakpoints) {
		m_breakpoints = breakpoints;
	}

}
