package org.dresdenocl.debug.model;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupParticipant;

public class OclSourceLookupParticipant extends AbstractSourceLookupParticipant {

	@Override
	public String getSourceName(Object object) throws CoreException {

		if (object instanceof OclStackFrame) {
			OclStackFrame frame = (OclStackFrame) object;
			return frame.getResourceURI();
		}
		return null;
	}

}
