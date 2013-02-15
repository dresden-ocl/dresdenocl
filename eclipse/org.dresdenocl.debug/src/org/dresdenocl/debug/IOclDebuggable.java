package org.dresdenocl.debug;

import java.util.Map;

import org.dresdenocl.interpreter.IOclInterpreter;
import org.eclipse.emf.ecore.EObject;

public interface IOclDebuggable extends IOclInterpreter {

	public void terminate();

	public void resume();

	public void stepOver();

	public void stepInto();

	public void stepReturn();

	public void addLineBreakPoint(String location, int line);

	public void removeLineBreakPoint(String location, int line);

	public String[] getStack();

	public Map<String, Object> getFrameVariables(String stackFrame);

	public void setDebugMode(boolean debugMode);

	public void setEventPort(int eventPort);

	public boolean isLineBreakPointElement(EObject element);
}
