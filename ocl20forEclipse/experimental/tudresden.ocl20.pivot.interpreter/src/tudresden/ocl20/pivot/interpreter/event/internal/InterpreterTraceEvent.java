package tudresden.ocl20.pivot.interpreter.event.internal;

import java.util.EventObject;
import java.util.UUID;

import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.interpreter.IInterpreterRegistry;

/**
 * 
 * @author Ronny
 * TODO: Ronny Document this
 */
public class InterpreterTraceEvent extends EventObject{
	
	private static final long serialVersionUID = -7975866761417314905L;
	
	private OclAny result;
	private EObject expression;
	private UUID guid;

	public InterpreterTraceEvent(IInterpreterRegistry source, EObject expression, OclAny result, UUID guid) {
		super(source);
		
		this.expression = expression;
		this.result = result;
		this.guid = guid;
	}
	
	public OclAny getResult(){
		return this.result;
	}
	
	public EObject getExpression() {
		return this.expression;
	}
	
	public UUID getUUID() {
		return this.guid;
	}
	
	@Override
	public IInterpreterRegistry getSource() {
		return (IInterpreterRegistry) super.getSource();
	}
	
}
