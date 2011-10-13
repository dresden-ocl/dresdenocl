package tudresden.ocl20.pivot.tracer;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.interpreter.event.IInterpreterTraceListener;
import tudresden.ocl20.pivot.interpreter.event.internal.InterpreterTraceEvent;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerItem;
import tudresden.ocl20.pivot.tracer.tracermodel.TracermodelFactory;
import tudresden.ocl20.pivot.tracer.tracermodel.impl.TracermodelPackageImpl;


public class InterpreterRegistryListenerImpl implements IInterpreterTraceListener {
	private TracerItem root;
	private TracerItem currentParent;
	private TracermodelFactory factory;
	
	public InterpreterRegistryListenerImpl() {
		root = null;
		currentParent = null;
		TracermodelPackageImpl.init();
		factory = TracermodelFactory.eINSTANCE;
	}
	
	public void interpretationTreeDepthIncreased(EObject reference) {
		TracerItem dummy = factory.createTracerItem();
		dummy.setExpression(reference);
		
		if((currentParent == null) && (root == null)) {
			//there has been no insertion before
			//this is the first item in the tree
			root = dummy;
			currentParent = dummy;
		}
		else {
			currentParent.getChildren().add(dummy);
			dummy.setParent(currentParent);
			currentParent = dummy;
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
				currentParent = root;
			}
		}
		//no else
	}
	
	
	
	public void partialInterpretationFinished(InterpreterTraceEvent event) {
		//check if it is the root element
		if(currentParent == root) {
			if(currentParent.getExpression() == event.getExpression()) {
				currentParent.setResult(event.getResult());
			}
			//no else
		}
		else {
			Iterator<TracerItem> it = currentParent.getChildren().iterator();
			boolean found = false;
			
			while(it.hasNext() && !found) {
				TracerItem item = it.next();
				if(item.getExpression() == event.getExpression()) {
					item.setResult(event.getResult());
					found = true;
				}
				//no else
			}
		}
	}
	
	public TracerItem getTree() {
		return root;
	}

	public void interpretationCleared() {	
		Iterator<EObject> it = factory.eContents().iterator();
		
		//clear all saved TracerItems due to memory leaks
		while(it.hasNext()) {
			it.remove();
		}

		root = null;
		currentParent = null;		
	}
}
