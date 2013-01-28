package org.dresdenocl.debug.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

public class OclDebugVariable extends OclDebugElement implements IVariable {

	public OclDebugVariable(OclDebugTarget debugTarget, String variableName,
			IValue value, String variableRefType) {
		super(debugTarget);
	}

	@Override
	public String getModelIdentifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDebugTarget getDebugTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILaunch getLaunch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(String expression) throws DebugException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValue(IValue value) throws DebugException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supportsValueModification() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verifyValue(String expression) throws DebugException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verifyValue(IValue value) throws DebugException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IValue getValue() throws DebugException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() throws DebugException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReferenceTypeName() throws DebugException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasValueChanged() throws DebugException {
		// TODO Auto-generated method stub
		return false;
	}

}
