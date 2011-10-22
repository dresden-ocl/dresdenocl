package tudresden.ocl20.pivot.tracer;

import java.util.Iterator;
import java.util.UUID;

import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.interpreter.event.IInterpreterTraceListener;
import tudresden.ocl20.pivot.interpreter.event.internal.InterpreterTraceEvent;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerItem;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot;
import tudresden.ocl20.pivot.tracer.tracermodel.TracermodelFactory;
import tudresden.ocl20.pivot.tracer.tracermodel.impl.TracermodelPackageImpl;


public class InterpreterRegistryListenerImpl implements IInterpreterTraceListener {
	private TracerRoot roots;
	private TracerItem currentParent;
	private TracermodelFactory factory;
	
	public InterpreterRegistryListenerImpl() {
		roots = null;
		currentParent = null;
		TracermodelPackageImpl.init();
		factory = TracermodelFactory.eINSTANCE;
	}
	
	public void interpretationTreeDepthIncreased(UUID guid) {
		TracerItem dummy = factory.createTracerItem();
		dummy.setGuid(guid);
		
		if((currentParent == null) && (roots == null)) {
			//there has been no insertion before
			//this is the first item in the tree
			roots = factory.createTracerRoot();
			roots.getRootItems().add(dummy);
			currentParent = dummy;
		}
		else {
			if(currentParent == null) {
				roots.getRootItems().add(dummy);
				currentParent = dummy;
			}
			else {
				currentParent.getChildren().add(dummy);
				dummy.setParent(currentParent);
				currentParent = dummy;
			}
		}
	}
	
	public void interpretationTreeDepthDecreased() {
		//check if the tree has been initialized
		if(currentParent != null) {
			//check if currentParent is the root element
			if(currentParent.getParent() != null) {
				currentParent = currentParent.getParent();
			}
			else {
				currentParent = null;
			}
		}
		//no else
		/* this should normally not happen that {@see interpretationTreeDepthIncrease}
		 * is called when currentParent == null
		 */
	}
	
	
	
	public void partialInterpretationFinished(InterpreterTraceEvent event) {
		if((event.getResult() == null) && (event.getExpression() == null)) {
			//TODO: Why it happens that either expression and
			//result is null and the interpreter just leave interpretConstraint
			//
			//find now the element and delete it
			Iterator<TracerItem> iter = roots.getRootItems().iterator();
			
			while(iter.hasNext()) {
				TracerItem item = iter.next();
				if(item.getGuid() == event.getUUID()) {
					iter.remove();
				}
				//no else
			}
		}
		
		Iterator<TracerItem> iterator;
		boolean found = false;
		
		if(currentParent == null) {
			iterator = roots.getRootItems().iterator();
			while(!found && iterator.hasNext()) {
				TracerItem item = iterator.next();
				if(item.getGuid() == event.getUUID()) {
					//set the values for this item
					//
					item.setExpression(event.getExpression());
					item.setResult(event.getResult());
					found = true;
				}
				//no else
			}
			//end while
		}
		else {		
			if(!found && (currentParent.getGuid() == event.getUUID())) {
				currentParent.setExpression(event.getExpression());
				currentParent.setResult(event.getResult());
				found = true;
			}
			//no else
			
			iterator = currentParent.getChildren().iterator();
			
			while(!found && iterator.hasNext()) {
				TracerItem item = iterator.next();
				if(item.getGuid() == event.getUUID()) {
					item.setExpression(event.getExpression());
					item.setResult(event.getResult());
					found = true;
				}
				//no else
			}
			//end while
		}
		//end else
	}
	
	public TracerRoot getTree() {
		return roots;
	}

	public void interpretationCleared() {	
		Iterator<EObject> it = factory.eContents().iterator();
		
		//clear all saved TracerItems due to memory leaks
		while(it.hasNext()) {
			it.remove();
		}

		roots = null;
		currentParent = null;		
	}
}
