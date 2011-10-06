package tudresden.ocl20.pivot.tracer;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.interpreter.event.IInterpreterTraceListener;
import tudresden.ocl20.pivot.interpreter.event.internal.InterpreterTraceEvent;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerItem;
import tudresden.ocl20.pivot.tracer.tracermodel.TracermodelFactory;

public class InterpreterRegistryListenerImpl implements IInterpreterTraceListener {
	private TracerItem root;
	private TracerItem currentParent;
	private TracermodelFactory factory;
	
	public InterpreterRegistryListenerImpl() {
		root = currentParent = null;
		factory = TracermodelFactory.eINSTANCE;
	}
	
	public void interpretationTreeDepthIncreased(EObject reference) {
		TracerItem dummy = factory.createTracerItem();
		dummy.setExpression(reference);
		
		if(currentParent == null) {
			//there has been no insertion before
			//this is the first item in the tree
			root = currentParent = dummy;
		} else {
			currentParent.getChildren().add(dummy);
			dummy.setParent(currentParent);
			currentParent = dummy;
		}
	}
	
	public void interpretationTreeDepthDecreased() {
		if(currentParent != null) {
			if(currentParent.getParent() != null) {
				if(currentParent.getParent() instanceof TracerItem) {
					currentParent = (TracerItem)currentParent.getParent();
				}
				//no else
			} else {
				currentParent = root;
			}
		}
		//no else
	}

	public void partialInterpretationFinished(InterpreterTraceEvent event) {
		EList<TracerItem> list = currentParent.getChildren();
		Iterator<TracerItem> it = list.iterator();
		boolean found = false;
		
		while(it.hasNext() && !found) {
			TracerItem item = it.next();
			if(item.getExpression().equals(event.getExpression())) {
				item.setResult(event.getResult());
				found = true;
			}
			//no else
		}
		if(!found) {
			System.out.println("KEINE UEBEREINSTIMMUNG GEFUNDEN");
		}
	}
	
	public TracerItem getTree() {
		return root;
	}
}
