package tudresden.ocl20.pivot.interpreter.event;

import tudresden.ocl20.pivot.interpreter.event.internal.InterpreterTraceEvent;


/**
 * 
 * @author Ronny
 * TODO: Ronny: document this...
 */
public interface IInterpreterTraceListener {
	
	/**
	 * 
	 */
	void interpretationTreeDepthIncreased();
	
	void interpretationTreeDepthDecreased();
	
	void partialInterpretationFinished(InterpreterTraceEvent event);
}
