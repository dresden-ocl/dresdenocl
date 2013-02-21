package org.dresdenocl.debug.model;

import java.util.List;

import org.dresdenocl.debug.util.OclStringUtil;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IRegisterGroup;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;

public class OclStackFrame extends OclDebugElement implements IStackFrame {

	private String m_resourceURI;
	private String m_name;
	private int m_id;
	private int m_line;
	private int m_charStart;
	private int m_charEnd;

	public OclStackFrame(OclDebugTarget target, String data) {

		super(target);
		List<String> dataParts = OclStringUtil.decode(data, ',');
		m_name = dataParts.get(0);
		m_id = Integer.parseInt(dataParts.get(1));
		m_resourceURI = dataParts.get(2);
		m_line = Integer.parseInt(dataParts.get(3));
		m_charStart = Integer.parseInt(dataParts.get(4));
		m_charEnd = Integer.parseInt(dataParts.get(5));
	}

	@Override
	public IDebugTarget getDebugTarget() {

		return m_debugTarget;
	}

	@Override
	public boolean canStepInto() {

		return getThread().canStepInto();
	}

	@Override
	public boolean canStepOver() {

		return getThread().canStepOver();
	}

	@Override
	public boolean canStepReturn() {

		return getThread().canStepReturn();
	}

	@Override
	public boolean isStepping() {

		return getThread().isStepping();
	}

	@Override
	public void stepInto() throws DebugException {

		getThread().stepInto();
	}

	@Override
	public void stepOver() throws DebugException {

		getThread().stepOver();
	}

	@Override
	public void stepReturn() throws DebugException {

		getThread().stepReturn();
	}

	@Override
	public boolean canResume() {

		return getThread().canResume();
	}

	@Override
	public boolean canSuspend() {

		return getThread().canSuspend();
	}

	@Override
	public boolean isSuspended() {

		return getThread().isSuspended();
	}

	@Override
	public void resume() throws DebugException {

		getThread().resume();
	}

	@Override
	public void suspend() throws DebugException {

		getThread().suspend();
	}

	@Override
	public boolean canTerminate() {

		return getThread().canTerminate();
	}

	@Override
	public boolean isTerminated() {

		return getThread().isTerminated();
	}

	@Override
	public void terminate() throws DebugException {

		getThread().terminate();
	}

	@Override
	public IThread getThread() {

		return m_debugTarget.getThread();
	}

	@Override
	public IVariable[] getVariables() throws DebugException {

		return m_debugTarget.getDebugProxy().getStackVariables(
				Integer.toString(m_id));
	}

	@Override
	public boolean hasVariables() throws DebugException {

		return true;
	}

	@Override
	public int getLineNumber() throws DebugException {

		return m_line;
	}

	@Override
	public int getCharStart() throws DebugException {

		return m_charStart;
	}

	@Override
	public int getCharEnd() throws DebugException {

		return m_charEnd;
	}

	@Override
	public String getName() throws DebugException {

		return m_name;
	}

	@Override
	public IRegisterGroup[] getRegisterGroups() throws DebugException {

		return new IRegisterGroup[0];
	}

	@Override
	public boolean hasRegisterGroups() throws DebugException {

		return false;
	}

	public String getResourceURI() {

		return m_resourceURI;
	}

}
