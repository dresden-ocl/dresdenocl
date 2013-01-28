package org.dresdenocl.debug.events;

import org.dresdenocl.interpreter.debug.OclDebugMessage;

public interface IOclDebugEventListener {
	
	void handleMessage(OclDebugMessage message);
}
