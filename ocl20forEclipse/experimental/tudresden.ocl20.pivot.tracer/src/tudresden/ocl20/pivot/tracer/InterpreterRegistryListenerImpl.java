package tudresden.ocl20.pivot.tracer;

import java.util.Iterator;

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
	
	public void interpretationTreeDepthIncreased(EObject reference) {
		TracerItem dummy = factory.createTracerItem();
		dummy.setExpression(reference);
		
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
		if(event.getResult() == null) {
			System.out.println("event.getResult == null");
			return;
		}
		
		Iterator<TracerItem> iterator;
		boolean found = false;
		
		if(currentParent == null) {
			iterator = roots.getRootItems().iterator();
			while(!found && iterator.hasNext()) {
				TracerItem item = iterator.next();
				if(item.getExpression() == event.getExpression()) {
					item.setResult(event.getResult());
					found = true;
				}
				//no else
			}
			//end while
		}
		else {		
			if(!found && (currentParent.getExpression() == event.getExpression())) {
				currentParent.setResult(event.getResult());
				found = true;
			}
			//no else
			
			iterator = currentParent.getChildren().iterator();
			
			while(!found && iterator.hasNext()) {
				TracerItem item = iterator.next();
				if(item.getExpression() == event.getExpression()) {
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
