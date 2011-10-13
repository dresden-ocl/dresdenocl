package tudresden.ocl20.pivot.interpreter.event;

import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.interpreter.event.internal.InterpreterTraceEvent;


/**
 * 
 * @author Ronny
 * TODO: Ronny: document this...
 */
public interface IInterpreterTraceListener {
	
	/**
	 * @param expression
	 * The expression (EObject in general) which is eveluated as reference
	 */
	void interpretationTreeDepthIncreased(EObject expression);
	
	void interpretationTreeDepthDecreased();
	
	void partialInterpretationFinished(InterpreterTraceEvent event);
	
	void interpretationCleared();
}
