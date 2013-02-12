package org.dresdenocl.debug.model;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

public class OclDebugValue extends OclDebugElement implements IValue {

	private String m_value;
	private String m_referenceTypeName;
	private Map<String, Long> m_children;
	private IVariable[] m_variables;

	public OclDebugValue(OclDebugTarget debugTarget, String id,
			String valueString, String valueRefType, Map<String, Long> childVariables) {

		super(debugTarget);
		m_value = valueString;
		m_referenceTypeName = valueRefType;
		m_children = childVariables;
	}

	@Override
	public String getReferenceTypeName() throws DebugException {

		return m_referenceTypeName;
	}

	@Override
	public String getValueString() throws DebugException {

		return m_value;
	}

	@Override
	public boolean isAllocated() throws DebugException {

		return true;
	}

	@Override
	public IVariable[] getVariables() throws DebugException {

		if (m_variables == null) {
			String[] childIds = m_children.values().toArray(new String[0]);
			m_variables = m_debugTarget.getDebugProxy().getVariables(childIds);
		}
		return m_variables;
	}

	public int getVariableCount() throws DebugException {

		return m_children.keySet().size();
	}

	@Override
	public boolean hasVariables() throws DebugException {

		return m_children.keySet().size() > 0;
	}

	public IVariable getChild(int index) throws DebugException {

		Set<String> keySet = m_children.keySet();
		Iterator<String> it = keySet.iterator();
		String keyAtIndex = it.next();
		for (int i = 0; i < index; i++) {
			keyAtIndex = it.next();
		}
		Long childId = m_children.get(keyAtIndex);
		IVariable[] respone =
				m_debugTarget.getDebugProxy().getVariables(childId.toString());
		return respone[0];
	}

}
