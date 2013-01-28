package org.dresdenocl.interpreter;


public interface IOclDebuggable extends IOclInterpreter {

	public void terminate();

	public void resume();

	public void stepOver();

	public void stepInto();

	public void stepReturn();

	public void addLineBreakPoint(String location, int line);

	public void removeLineBreakPoint(String location, int line);

	public String[] getStack();

	public String getFrameVariables(String stackFrame);

	public void setDebugMode(boolean debugMode);

	public void setEventPort(int eventPort);
}
