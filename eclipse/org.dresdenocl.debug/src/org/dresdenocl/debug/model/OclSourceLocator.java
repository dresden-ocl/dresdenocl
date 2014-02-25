package org.dresdenocl.debug.model;

import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupDirector;
import org.eclipse.debug.core.sourcelookup.ISourceLookupParticipant;

public class OclSourceLocator extends AbstractSourceLookupDirector {

	public void initializeParticipants() {

		addParticipants(new ISourceLookupParticipant[] { new OclSourceLookupParticipant() });
	}
}
