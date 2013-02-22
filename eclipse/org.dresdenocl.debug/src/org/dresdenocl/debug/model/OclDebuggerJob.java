package org.dresdenocl.debug.model;

import java.util.Collection;
import java.util.List;

import org.dresdenocl.debug.IOclDebuggable;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.pivotmodel.Constraint;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

public class OclDebuggerJob extends Job {

	private IOclDebuggable m_debuggable;
	private int m_eventPort;
	private Collection<Constraint> m_constraints;
	private List<IModelInstanceElement> m_miElements;

	public OclDebuggerJob(String name, int eventPort, IOclDebuggable debuggable,
			Collection<Constraint> constraints, List<IModelInstanceElement> miElements) {

		super(name);
		m_eventPort = eventPort;
		m_debuggable = debuggable;
		m_constraints = constraints;
		m_miElements = miElements;
	}

	public IOclDebuggable getDebuggable() {

		return m_debuggable;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {

		m_debuggable.setDebugMode(true);
		m_debuggable.setEventPort(m_eventPort);

		for (Constraint c : m_constraints) {
			if (c.hasStaticContext()) {
				m_debuggable.interpretConstraint(c, null);
			}
			else {
				for (IModelInstanceElement mie : m_miElements) {
					m_debuggable.interpretConstraint(c, mie);
				}
			}
		}
		return Status.OK_STATUS;
	}
}
