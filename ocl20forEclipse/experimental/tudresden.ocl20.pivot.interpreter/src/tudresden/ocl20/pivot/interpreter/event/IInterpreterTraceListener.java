package tudresden.ocl20.pivot.interpreter.event;

import java.util.UUID;

import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.interpreter.event.internal.InterpreterTraceEvent;


/**
 * 
 * @author Ronny
 * TODO: Ronny: document this...
 */
public interface IInterpreterTraceListener {
	
	/**
	 * @param guid
	 * The expression (EObject in general) which is eveluated as reference
	 */
	void interpretationTreeDepthIncreased(UUID guid);
	
	void interpretationTreeDepthDecreased();
	
	void partialInterpretationFinished(InterpreterTraceEvent event);
	
	void interpretationCleared();
}
