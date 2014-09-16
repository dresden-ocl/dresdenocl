package org.dresdenocl.debug.model;

import org.dresdenocl.debug.events.IOclDebugEventListener;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStreamsProxy;

public class OclDebugProcess extends OclDebugElement implements IProcess,
		IOclDebugEventListener {

	private ILaunch m_launch;
	private boolean m_terminated;

	public OclDebugProcess(ILaunch launch) {

		super((OclDebugTarget) launch.getDebugTarget());
		m_launch = launch;
		m_terminated = false;
	}

	@Override
	public boolean canTerminate() {

		return !m_terminated;
	}

	public ILaunch getLaunch() {

		return m_launch;
	}

	@Override
	public boolean isTerminated() {

		return m_terminated;
	}

	@Override
	public void terminate() throws DebugException {

		m_terminated = true;
	}

	@Override
	public String getLabel() {

		return null;
	}

	@Override
	public IStreamsProxy getStreamsProxy() {

		return null;
	}

	@Override
	public void setAttribute(String key, String value) {

		// do nothing
	}

	@Override
	public String getAttribute(String key) {

		return null;
	}

	@Override
	public int getExitValue() throws DebugException {

		return 0;
	}

	@Override
	public void handleMessage(OclDebugMessage message) {

		if (message.hasType(EOclDebugMessageType.TERMINATED)) {
			m_terminated = true;
		}
		// no else
	}

}
