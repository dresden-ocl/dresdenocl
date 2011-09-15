package tudresden.ocl20.pivot.tracer;

import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.interpreter.event.IInterpreterTraceListener;
import tudresden.ocl20.pivot.interpreter.event.internal.InterpreterTraceEvent;
import tudresden.ocl20.pivot.tracer.model.TracerItem;
import tudresden.ocl20.pivot.tracer.model.TracerNode;
import tudresden.ocl20.pivot.tracer.model.TracerTree;

public class InterpreterRegistryListenerImpl implements IInterpreterTraceListener {
	private TracerTree tree;
	private TracerNode currentParent;
	
	//Just for debugging
	//TODO: Ronny: REMOVE, Just for debugging purposes!
	private int depth;
	
	public InterpreterRegistryListenerImpl() {
		System.out.println("Tracer: New!");
		
		//TODO: Remove!
		depth=0;
		tree = null;
		currentParent = null;
	}
	
	
	/*
	 * This method places dummy entries into the tree to
	 * set up the tree structure. Each entry is recognized
	 * by a hash.
	 * This has to be done since the results for this entry
	 * are sent later on with the @see{partialInterpretationFinished}
	 * notification to be completed with information regarding
	 * this interpretation.
	 */
	public void interpretationTreeDepthIncreased(EObject reference) {
		
		System.out.println("Tracer: Increase[" + ++depth + "]" );
		
		/* Add a dummy node to the tree */
		TracerNode dummyNode = new TracerNode(currentParent, null, reference);
		
		/* This is the first item inserted into the tree */
		if(tree == null) {
			tree = new TracerTree(dummyNode);
		}
		else {
			currentParent.addChild(dummyNode);
		}
		currentParent = dummyNode;
	}
	
	public void interpretationTreeDepthDecreased() {
	
		System.out.println("Tracer: Decrease[" + --depth + "]" );
		
		if(currentParent != null) {
			if(currentParent.getParent() != null) {
				currentParent = currentParent.getParent();
			}
			else {
				currentParent = tree.getRootElement();
			}
		}
		//no else
	}

	public void partialInterpretationFinished(InterpreterTraceEvent event) {
		
		System.out.println("Tracer: " + (event==null?"null":event.getResult()) + "[" +depth+ "]");
		
		TracerNode[] tmpArray = currentParent.getChildren();
		TracerItem item = new TracerItem(event.getExpression(), event.getResult());
		boolean found = false;
		
		for(TracerNode n : tmpArray) {
			/* check which child is recognized by this expression*/
			if(n.isReference(event.getExpression())) {
				/* set the item into the dummy */
				n.setTracerItem(item);
				found = true;
			}
		}
		/* Check if the item has to be added in the current level */
		if(!found) {
			TracerNode node = new TracerNode(currentParent, item, event.getExpression());
			currentParent.addChild(node);
		}
	}
	
	public TracerTree getTree() {
		return tree;
	}
}
