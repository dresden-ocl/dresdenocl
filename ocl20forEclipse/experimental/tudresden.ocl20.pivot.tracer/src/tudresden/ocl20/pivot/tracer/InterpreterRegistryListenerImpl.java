package tudresden.ocl20.pivot.tracer;

import tudresden.ocl20.pivot.interpreter.event.IInterpreterTraceListener;
import tudresden.ocl20.pivot.interpreter.event.internal.InterpreterTraceEvent;
import tudresden.ocl20.pivot.tracer.model.TracerItem;
import tudresden.ocl20.pivot.tracer.model.TracerNode;
import tudresden.ocl20.pivot.tracer.model.TracerTree;

public class InterpreterRegistryListenerImpl implements IInterpreterTraceListener {
	private TracerTree tree;
	private TracerNode actualParentNode;
	private boolean nextInsertIsNewParent;
	
	public InterpreterRegistryListenerImpl() {
		tree = null;
		actualParentNode = null;
		nextInsertIsNewParent = false;
	}
	
	public void interpretationTreeDepthIncreased() {
		nextInsertIsNewParent = true;
	}
	
	public void interpretationTreeDepthDecreased() {
		if(actualParentNode != null) {
			actualParentNode = actualParentNode.getParent();
		}
		//no else
	}

	public void partialInterpretationFinished(InterpreterTraceEvent event) {
		TracerItem item = new TracerItem(
				event.getExpression(), event.getResult());
		
		TracerNode node = null;
		/* there has been no insertion before */
		if(tree == null) {
			node = new TracerNode(null, item);
			tree = new TracerTree(node);
			
			/* setting the top node to new parent node */
			actualParentNode = node;
		}
		else {
			try {
				node = new TracerNode(actualParentNode, item);
				actualParentNode.addChild(node);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(nextInsertIsNewParent) {
			actualParentNode = node;
			nextInsertIsNewParent = false;
		}
		//no else
	}
	
	public TracerTree getTree() {
		return tree;
	}
}
