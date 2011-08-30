package tudresden.ocl20.pivot.interpreter.event;

import tudresden.ocl20.pivot.interpreter.event.internal.InterpreterTraceEvent;


/**
 * 
 * @author Ronny
 * TODO: Ronny: document this...
 */
public interface IInterpreterTraceListener {
	
	/**
	 * @param hash 
	 * 
	 */
	void interpretationTreeDepthIncreased(int hash);
	
	void interpretationTreeDepthDecreased();
	
	void partialInterpretationFinished(InterpreterTraceEvent event);
}
