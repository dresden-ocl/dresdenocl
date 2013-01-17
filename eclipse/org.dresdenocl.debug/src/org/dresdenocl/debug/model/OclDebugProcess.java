package org.dresdenocl.debug.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStreamsProxy;


public class OclDebugProcess extends OclDebugElement implements IProcess {

	private ILaunch m_launch;
	
	public OclDebugProcess(ILaunch launch) {

		super((OclDebugTarget) launch.getDebugTarget());
		m_launch = launch;
	}

	@Override
	public boolean canTerminate() {

		// TODO Auto-generated method stub
		return false;
	}
	
	public ILaunch getLaunch() {
		return m_launch;
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
	public String getLabel() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IStreamsProxy getStreamsProxy() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttribute(String key, String value) {

		// TODO Auto-generated method stub

	}

	@Override
	public String getAttribute(String key) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getExitValue() throws DebugException {

		// TODO Auto-generated method stub
		return 0;
	}

}
