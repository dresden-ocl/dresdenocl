package org.dresdenocl.debug.model;

import org.dresdenocl.debug.OclDebugPlugin;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

public class OclDebugVariable extends OclDebugElement implements IVariable {

	private String m_name;
	private IValue m_value;
	private String m_referenceTypeName;

	public OclDebugVariable(OclDebugTarget debugTarget, String variableName,
			IValue value, String variableRefType) {

		super(debugTarget);
		m_name = variableName;
		m_value = value;
		m_referenceTypeName = variableRefType;
	}

	@Override
	public void setValue(String expression) throws DebugException {

		throw new org.eclipse.debug.core.DebugException(
				new org.eclipse.core.runtime.Status(
						org.eclipse.core.runtime.IStatus.ERROR, OclDebugPlugin.PLUGIN_ID,
						"Cannot set variable."));
	}

	@Override
	public void setValue(IValue value) throws DebugException {

		throw new org.eclipse.debug.core.DebugException(
				new org.eclipse.core.runtime.Status(
						org.eclipse.core.runtime.IStatus.ERROR, OclDebugPlugin.PLUGIN_ID,
						"Cannot set variable."));
	}

	@Override
	public boolean supportsValueModification() {

		return false;
	}

	@Override
	public boolean verifyValue(String expression) throws DebugException {

		throw new org.eclipse.debug.core.DebugException(
				new org.eclipse.core.runtime.Status(
						org.eclipse.core.runtime.IStatus.ERROR, OclDebugPlugin.PLUGIN_ID,
						"Cannot set variable."));
	}

	@Override
	public boolean verifyValue(IValue value) throws DebugException {

		throw new org.eclipse.debug.core.DebugException(
				new org.eclipse.core.runtime.Status(
						org.eclipse.core.runtime.IStatus.ERROR, OclDebugPlugin.PLUGIN_ID,
						"Cannot set variable."));
	}

	@Override
	public IValue getValue() throws DebugException {

		return m_value;
	}

	@Override
	public String getName() throws DebugException {

		return m_name;
	}

	@Override
	public String getReferenceTypeName() throws DebugException {

		return m_referenceTypeName;
	}

	@Override
	public boolean hasValueChanged() throws DebugException {

		return true;
	}

}
