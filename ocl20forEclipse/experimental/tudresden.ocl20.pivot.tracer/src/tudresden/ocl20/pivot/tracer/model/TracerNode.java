package tudresden.ocl20.pivot.tracer.model;

import java.util.ArrayList;

public class TracerNode {
	private ArrayList<TracerNode> children;
	private TracerItem tracerItem;
	private TracerNode parent;
	
	public TracerNode(TracerNode parent, TracerItem tracerItem) {
		this.tracerItem = tracerItem;
		this.parent = parent;
		children = null;
	}
	
	public void addChild(TracerNode node) {
		if(children == null) {
			children = new ArrayList<TracerNode>();
		}
		//no else
		
		children.add(node);
	}
	
	public TracerItem getTracerItem() {
		return tracerItem;
	}
	
	public TracerNode getParent() {
		return parent;
	}
	
	public Object[] getChildren() {
		if(children == null) {
			return new Object[0];
		}
		else {
			return children.toArray();
		}
	}
}
