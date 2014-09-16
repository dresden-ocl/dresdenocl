package org.dresdenocl.debug.events;

import org.dresdenocl.debug.model.OclDebugMessage;

public interface IOclDebugEventListener {

	void handleMessage(OclDebugMessage message);
}
