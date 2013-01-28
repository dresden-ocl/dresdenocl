package org.dresdenocl.debug.events;

import org.dresdenocl.interpreter.debug.OclDebugMessage;

public interface IDebugEventListener {
	
	void handleMessage(OclDebugMessage message);
}
