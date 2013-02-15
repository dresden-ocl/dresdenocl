package org.dresdenocl.debug.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.dresdenocl.debug.OclDebugPlugin;
import org.dresdenocl.debug.events.IOclDebugEventListener;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;

public class OclDebugTarget extends OclDebugElement implements IDebugTarget,
		IOclDebugEventListener {

	private OclDebugProcess m_process;
	private ILaunch m_launch;

	private Socket m_eventSocket;
	private BufferedReader m_eventReader;

	private boolean m_terminated = false;

	private OclDebugThread m_thread;
	private IThread[] m_threads;

	private OclDebugProxy m_debugProxy;

	private EventDispatchJob m_eventDispatch;
	private List<IOclDebugEventListener> m_eventListener;

	private class EventDispatchJob extends Job {

		private OclDebugCommunicationHelper m_communicationHelper =
				new OclDebugCommunicationHelper();

		public EventDispatchJob() {

			super("Ocl Event Dispatch");
			setSystem(true);
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {

			while (!isTerminated()) {
				OclDebugMessage message = m_communicationHelper.receive(m_eventReader);
				if (message != null) {
					notifyListeners(message);
				}
				else {
					terminated();
					break;
				}
			}
			return Status.OK_STATUS;
		}

		private void notifyListeners(OclDebugMessage message) {

			System.out.println("EventDispatch notifyListeners ( " + message + " )");
			Object[] listeners = m_eventListener.toArray();
			for (Object obj : listeners) {
				((IOclDebugEventListener) obj).handleMessage(message);
			}
		}
	}

	public OclDebugTarget(ILaunch launch, OclDebugProcess process,
			int requestPort, int eventPort) throws CoreException {

		super(null);
		m_debugTarget = this;
		m_launch = launch;
		m_process = process;
		m_thread = new OclDebugThread(this);
		m_threads = new IThread[] { m_thread };

		try {
			m_debugProxy = new OclDebugProxy(this, requestPort);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		m_eventListener = new ArrayList<IOclDebugEventListener>();
		this.addEventListener(this);
		this.addEventListener(m_thread);
		this.addEventListener(m_process);

		try {
			m_eventSocket = new Socket("localhost", eventPort);
			m_eventReader =
					new BufferedReader(new InputStreamReader(
							m_eventSocket.getInputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		m_eventDispatch = new EventDispatchJob();
		m_eventDispatch.schedule();

		this.getBreakpointManager().addBreakpointListener(this);
	}

	private void addEventListener(IOclDebugEventListener listener) {

		if (!m_eventListener.contains(listener)) {
			m_eventListener.add(listener);
		}
	}

	private void removeEventListener(IOclDebugEventListener listener) {

		m_eventListener.remove(listener);
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
				getBreakpointManager().getBreakpoints(OclDebugPlugin.DEBUG_MODEL_ID);
		for (IBreakpoint breakpoint : breakpoints) {
			breakpointAdded(breakpoint);
		}
	}

	private synchronized void terminated() {

		System.out.println("OclDebugTarget terminated()");
		m_terminated = true;
		m_threads = new IThread[0];
		fireTerminateEvent();
		removeEventListener(this);
		removeEventListener(m_thread);
		removeEventListener(m_process);
		try {
			getBreakpointManager().removeBreakpointListener(this);
		} catch (NullPointerException e) {
			// do nothing
		}
		m_debugProxy.terminate();
	}

	@Override
	public boolean canTerminate() {

		return m_process.canTerminate();
	}

	@Override
	public boolean isTerminated() {

		return m_terminated || m_process.isTerminated();
	}

	@Override
	public boolean canResume() {

		return m_thread.canResume();
	}

	@Override
	public boolean canSuspend() {

		return m_thread.canSuspend();
	}

	@Override
	public boolean isSuspended() {

		return m_thread.isSuspended();
	}

	@Override
	public void resume() throws DebugException {

		System.out.println("OclDebugTarget resume()");
		m_thread.resume();
	}

	@Override
	public void suspend() throws DebugException {

		m_thread.suspend();
	}

	@Override
	public void terminate() throws DebugException {

		m_thread.terminate();
	}

	@Override
	public void breakpointAdded(IBreakpoint breakpoint) {

		if (supportsBreakpoint(breakpoint)) {
			try {
				if ((breakpoint.isEnabled() && getBreakpointManager().isEnabled())
						|| !breakpoint.isRegistered()) {
					OclLineBreakpoint lineBreakpoint = (OclLineBreakpoint) breakpoint;
					lineBreakpoint.install(this);
				}
				// no else
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta) {

		if (supportsBreakpoint(breakpoint)) {
			try {
				if ((breakpoint.isEnabled() && getBreakpointManager().isEnabled())
						|| !breakpoint.isRegistered()) {
					OclLineBreakpoint lineBreakpoint = (OclLineBreakpoint) breakpoint;
					lineBreakpoint.install(this);
				}
				// no else
			} catch (DebugException e) {
				e.printStackTrace();
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta) {

		// empty
	}

	@Override
	public boolean canDisconnect() {

		return false;
	}

	@Override
	public void disconnect() throws DebugException {

		// empty
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

		return "OCL model";
	}

	@Override
	public boolean supportsBreakpoint(IBreakpoint breakpoint) {

		return breakpoint.getModelIdentifier()
				.equals(OclDebugPlugin.DEBUG_MODEL_ID);
	}

	@Override
	public void handleMessage(OclDebugMessage message) {

		System.out.println("OclDebugTarget handleMessage( " + message + " )");
		try {
			if (message.hasType(EOclDebugMessageType.STARTED)) {
				started();
			}
			else if (message.hasType(EOclDebugMessageType.SUSPENDED)) {
				suspend();
			}
			else if (message.hasType(EOclDebugMessageType.TERMINATED)) {
				terminated();
			}
			else if (message.hasType(EOclDebugMessageType.RESUMED)) {
				// this event is handled by the debug thread
			}
			else {
				System.out.println("ERROR in " + this.getClass().getName()
						+ ".handleMessage(): unknown event: " + message);
			}
		} catch (DebugException e) {
			e.printStackTrace();
		}
	}

	public OclDebugProxy getDebugProxy() {

		return m_debugProxy;
	}

	public IThread getThread() {

		return m_thread;
	}

	public ILaunch getLaunch() {

		return m_launch;
	}
}
