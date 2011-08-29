package tudresden.ocl20.pivot.tracer;

import tudresden.ocl20.pivot.interpreter.event.IInterpreterTraceListener;
import tudresden.ocl20.pivot.interpreter.event.internal.InterpreterTraceEvent;
import tudresden.ocl20.pivot.tracer.model.TracerItem;
import tudresden.ocl20.pivot.tracer.model.TracerNode;
import tudresden.ocl20.pivot.tracer.model.TracerTree;

public class InterpreterRegistryListenerImpl implements IInterpreterTraceListener {
	private TracerTree tree;
	private TracerNode currentParent;
	private boolean nextInsertIsNewParent;
	
	public InterpreterRegistryListenerImpl() {
		tree = null;
		currentParent = null;
		nextInsertIsNewParent = false;
	}

	public void interpretationTreeDepthIncreased() {
		nextInsertIsNewParent = true;
	}
	
	public void interpretationTreeDepthDecreased() {
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
		TracerItem item = new TracerItem(
				event.getExpression(), event.getResult());
		/* DEBUG */
		System.out.println(event.getExpression());
		/* DEBUG */
		
		/* This is the first item inserted into the tree */
		if(tree == null) {
			TracerNode node = new TracerNode(null, item);
			tree = new TracerTree(node);
			currentParent = node;
		}
		else {
			TracerNode node = new TracerNode(currentParent, item);
			/* DEBUG */
			System.out.println(currentParent);
			/* DEBUG */
			currentParent.addChild(node);
			
			/* Item is new actual parent */
			if(nextInsertIsNewParent) {
				currentParent = node;
				nextInsertIsNewParent = false;
			}
			// no else			
		}
	}
	
	public TracerTree getTree() {
		return tree;
	}
}
