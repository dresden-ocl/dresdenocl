package tudresden.ocl20.pivot.interpreter.event;

import java.util.List;
import java.util.UUID;

import tudresden.ocl20.pivot.interpreter.event.internal.InterpreterTraceEvent;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * 
 * @author Ronny
 * @author Lars Schütze
 */
public interface IInterpreterTraceListener {

	/**
	 * <p>
	 * Fires an event to identify that the current parent has to be set to the
	 * next level.
	 * </p>
	 * 
	 * @param guid
	 *          The {@link UUID} which is evaluated as reference.
	 */
	void interpretationTreeDepthIncreased(UUID uuid);

	/**
	 * <p>
	 * Fires an event to identify that the current parent has to be set to the
	 * next level. Furthermore this function is thought to be used to get the
	 * tracer
	 * </p>
	 * 
	 * @param uuid
	 *          The {@link UUID} which is evaluated as reference.
	 * 
	 * @param modelInstanceElement
	 *          The {@link IModelInstanceElement} on which the constraint is
	 *          evaluated.
	 */
	void interpretationTreeDepthIncreased(UUID uuid,
			IModelInstanceElement modelInstanceElement);

	/**
	 * <p>
	 * Fires an event to identify that the current parent has to be set to the
	 * previous level.
	 * </p>
	 */
	void interpretationTreeDepthDecreased();

	/**
	 * <p>
	 * Fires an event to identify that a partial interpretation has been finished.
	 * </p>
	 * 
	 * @param event
	 *          The {@link InterpreterTraceEvent} which holds the expression, the
	 *          result and the {@link UUID} to identify the entry in the tree
	 */
	void partialInterpretationFinished(InterpreterTraceEvent event);

	/**
	 * <p>
	 * Fires an event to indicate that the interpretation has to be cleared.
	 * </p>
	 */
	void interpretationCleared();

	/**
	 * <p>
	 * Fires an event to indicate that only the selected {@link Constraint}s have
	 * to be viewed in the trace.
	 * </p>
	 * 
	 * @param constraints
	 *          Holds the array of size 3 containing the
	 *          {@link JavaModelInstanceObject}, the {@link Constraint} and the
	 *          {@link OclAny result}.
	 */
	void traceSelectedConstraints(List<Object[]> constraints);
}
