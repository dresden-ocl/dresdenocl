package org.dresdenocl.debug.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.dresdenocl.debug.OclDebugPlugin;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;

public class OclDebugTarget extends OclDebugElement implements IDebugTarget {

	private IProcess m_process;
	private ILaunch m_launch;
	private String m_name;

	private Socket m_requestSocket;
	private PrintWriter m_requestWriter;
	private BufferedReader m_requestReader;

	private Socket m_eventSocket;
	private BufferedReader m_eventReader;

	private boolean m_suspended = true;
	private boolean m_terminated = false;

	private OclDebugThread m_thread;
	private IThread[] m_threads;

	private EventDispatchJob m_eventDispatch;

	private class EventDispatchJob extends Job {

		public EventDispatchJob() {

			super("Ocl Event Dispatch");
			setSystem(true);
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {

			String event = "";
			while (!isTerminated() && event != null) {
				try {
					event = m_eventReader.readLine();
					if (event != null) {
						m_thread.setBreakpoints(null);
						m_thread.setStepping(false);
						if (event.equals("started")) {
							started();
						}
						else if (event.equals("terminated")) {
							terminated();
						}
						else if (event.startsWith("resumed")) {
							if (event.endsWith("step")) {
								m_thread.setStepping(true);
								resumed(DebugEvent.STEP_OVER);
							}
							else if (event.endsWith("client")) {
								resumed(DebugEvent.CLIENT_REQUEST);
							}
						}
						else if (event.startsWith("suspended")) {
							if (event.endsWith("client")) {
								suspended(DebugEvent.CLIENT_REQUEST);
							}
							else if (event.endsWith("step")) {
								suspended(DebugEvent.STEP_END);
							}
							else if (event.indexOf("breakpoint") >= 0) {
								breakpointHit(event);
							}
						}
					}
				} catch (IOException e) {
					terminated();
				}
			}
			return Status.OK_STATUS;
		}

	}

	public OclDebugTarget(ILaunch launch, IProcess process, int requestPort,
			int eventPort) throws CoreException {

		super(null);
		m_target = this;
		m_launch = launch;
		m_process = process;
		try {
			m_requestSocket = new Socket("localhost", requestPort);
			m_requestWriter = new PrintWriter(m_requestSocket.getOutputStream());
			m_requestReader =
					new BufferedReader(new InputStreamReader(
							m_requestSocket.getInputStream()));
			m_eventSocket = new Socket("localhost", eventPort);
			m_eventReader =
					new BufferedReader(new InputStreamReader(
							m_eventSocket.getInputStream()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m_thread = new OclDebugThread(this);
		m_threads = new IThread[] { m_thread };
		m_eventDispatch = new EventDispatchJob();
		m_eventDispatch.schedule();
		this.getBreakpointManager().addBreakpointListener(this);
	}

	private void started() {

		fireCreationEvent();
		installDeferredBreakPoints();
		try {
			resume();
		} catch (DebugException e) {
			e.printStackTrace();
		}
	}

	private void installDeferredBreakPoints() {

		IBreakpoint[] breakpoints =
				this.getBreakpointManager().getBreakpoints(
						OclDebugPlugin.DEBUG_MODEL_ID);
		for (IBreakpoint breakpoint : breakpoints) {
			breakpointAdded(breakpoint);
		}
	}

	public void resumed(int clientRequest) {

		// TODO Auto-generated method stub

	}

	private void suspended(int stepEnd) {

		// TODO Auto-generated method stub

	}

	private void breakpointHit(String event) {

		// TODO Auto-generated method stub

	}

	private void terminated() {

		m_terminated = true;
		m_suspended = false;
		this.getBreakpointManager().removeBreakpointListener(this);
		fireTerminateEvent();
	}

	@Override
	public boolean canTerminate() {

		return m_process.canTerminate();
	}

	@Override
	public boolean isTerminated() {

		return m_process.isTerminated();
	}

	@Override
	public void terminate() throws DebugException {

		synchronized (m_requestSocket) {
			m_requestWriter.println("exit");
			m_requestWriter.flush();
		}
	}

	@Override
	public boolean canResume() {

		return !isTerminated() && isSuspended();
	}

	@Override
	public boolean canSuspend() {

		return !isTerminated() && !isSuspended();
	}

	@Override
	public boolean isSuspended() {

		return m_suspended;
	}

	@Override
	public void resume() throws DebugException {

		sendRequest("resume");
	}

	private void sendRequest(String request) throws DebugException {

		synchronized (m_requestSocket) {
			m_requestWriter.println(request);
			m_requestWriter.flush();
			try {
				String respone = m_requestReader.readLine();
			} catch (IOException e) {
				abort("Request failed: " + request, e);
			}
		}
	}

	@Override
	public void suspend() throws DebugException {

	}

	@Override
	public void breakpointAdded(IBreakpoint breakpoint) {

		if (supportsBreakpoint(breakpoint)) {
			try {
				if (breakpoint.isEnabled()) {
					try {
						sendRequest("set "
								+ (((ILineBreakpoint) breakpoint).getLineNumber() - 1));
					} catch (DebugException e) {
						e.printStackTrace();
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta) {

		if (supportsBreakpoint(breakpoint)) {
			try {
				sendRequest("clear " + ((ILineBreakpoint) breakpoint).getLineNumber());
			} catch (DebugException e) {
				e.printStackTrace();
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta) {

		if (supportsBreakpoint(breakpoint)) {
			try {
				if (breakpoint.isEnabled()) {
					breakpointAdded(breakpoint);
				}
				else {
					breakpointRemoved(breakpoint, delta);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean canDisconnect() {

		// TODO
		return false;
	}

	@Override
	public void disconnect() throws DebugException {

	}

	@Override
	public boolean isDisconnected() {

		return false;
	}

	@Override
	public boolean supportsStorageRetrieval() {

		return false;
	}

	@Override
	public IMemoryBlock getMemoryBlock(long startAddress, long length)
			throws DebugException {

		return null;
	}

	@Override
	public IProcess getProcess() {

		return m_process;
	}

	@Override
	public IThread[] getThreads() throws DebugException {

		return m_threads;
	}

	@Override
	public boolean hasThreads() throws DebugException {

		return true;
	}

	@Override
	public String getName() throws DebugException {

		if (m_name == null) {
			m_name = "OCL Program";
			// TODO
		}
		return m_name;
	}

	@Override
	public boolean supportsBreakpoint(IBreakpoint breakpoint) {

		if (breakpoint.getModelIdentifier().equals(OclDebugPlugin.DEBUG_MODEL_ID)) {
			// TODO
			return true;
		}
		return false;
	}

	protected IStackFrame[] getStackFrames() throws DebugException {

		synchronized (m_requestSocket) {
			m_requestWriter.println("stack");
			m_requestWriter.flush();
			String framesData;
			try {
				framesData = m_requestReader.readLine();
				if(framesData != null) {
					String[] frames = framesData.split("#");
					IStackFrame[] stackFrames = new IStackFrame[frames.length];
					//TODO
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new IStackFrame[0];
	}
}
