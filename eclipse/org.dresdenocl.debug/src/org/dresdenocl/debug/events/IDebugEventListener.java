package org.dresdenocl.debug.events;

public interface IDebugEventListener {
	
	void handleMessage(OclDebugMessage message);
}
