package org.dresdenocl.debug.model;

import java.util.List;

import org.dresdenocl.debug.events.IOclDebugEventListener;
import org.dresdenocl.debug.util.OclStringUtil;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;

public class OclDebugThread extends OclDebugElement implements IThread,
		IOclDebugEventListener {

	private boolean m_suspended = false;

	public OclDebugThread(OclDebugTarget target) {

		super(target);
	}

	@Override
	public boolean canResume() {

		return m_suspended;
	}

	@Override
	public boolean canSuspend() {

		return !m_suspended;
	}

	@Override
	public boolean isSuspended() {

		return m_suspended;
	}

	@Override
	public void resume() throws DebugException {

		System.out.println("OclDebugThread resume()");
		m_debugTarget.getDebugProxy().resume();
		m_suspended = false;
	}

	@Override
	public void suspend() throws DebugException {

		System.out.println("OclDebugThread suspend()");
		m_suspended = true;
		// fireSuspendEvent(DebugEvent.BREAKPOINT);
		fireSuspendEvent(0);
	}

	@Override
	public boolean canStepInto() {

		return true;
	}

	@Override
	public boolean canStepOver() {

		return true;
	}

	@Override
	public boolean canStepReturn() {

		return true;
	}

	@Override
	public boolean isStepping() {

		return false;
	}

	@Override
	public void stepInto() throws DebugException {

		m_debugTarget.getDebugProxy().stepInto();
	}

	@Override
	public void stepOver() throws DebugException {

		m_debugTarget.getDebugProxy().stepOver();
	}

	@Override
	public void stepReturn() throws DebugException {

		m_debugTarget.getDebugProxy().stepReturn();
	}

	@Override
	public boolean canTerminate() {

		return !isTerminated();
	}

	@Override
	public boolean isTerminated() {

		return m_debugTarget.isTerminated();
	}

	@Override
	public void terminate() throws DebugException {

		m_debugTarget.getDebugProxy().terminate();
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

		return null;
	}

	@Override
	public IStackFrame[] getStackFrames() throws DebugException {

		if (isSuspended()) {
			OclDebugMessage stack = m_debugTarget.getDebugProxy().getStack();
			String framesData = stack.getArgument(0);
			if (framesData != null && !"".equals(framesData)) {
				List<String> frames = OclStringUtil.decode(framesData, '#');
				IStackFrame[] theFrames = new IStackFrame[frames.size()];
				System.out.println("OclDebugThread.getStackFrames() : frames.size() = "
						+ frames.size());
				for (int i = 0; i < frames.size(); i++) {
					String data = frames.get(i);
					theFrames[frames.size() - i - 1] =
							new OclStackFrame(m_debugTarget, data);
				}
				return theFrames;
			}
		}
		return new IStackFrame[0];
	}

	@Override
	public void handleMessage(OclDebugMessage message) {

		System.out.println("OclDebugThread handleMessage( " + message + " )");
		if (message.hasType(EOclDebugMessageType.STARTED)) {
			fireCreationEvent();
		}
		else if (message.hasType(EOclDebugMessageType.RESUMED)) {
			m_suspended = false;
			fireResumeEvent(0);
		}
		else if (message.hasType(EOclDebugMessageType.SUSPENDED)) {
			m_suspended = true;
			// TODO fireSuspendEvent(DebugEvent.BREAKPOINT);
			fireSuspendEvent(0);
		}
		else if (message.hasType(EOclDebugMessageType.TERMINATED)) {
			// ignore this event
		}
		else if (message.hasType(EOclDebugMessageType.CONSTRAINT_INTERPRETED)) {
			// ignore this event
		}
		else {
			System.out.println("ERROR " + this.getClass().getName()
					+ ".handleMessage(" + message + ") unknown event");
		}
	}

}
