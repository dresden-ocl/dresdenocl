package tudresden.ocl20.pivot.tracer.model;

import java.util.ArrayList;

public class TracerNode {
	private ArrayList<TracerNode> children;
	private TracerItem tracerItem;
	private TracerNode parent;
	private int recognizingHash;
	
	public TracerNode(TracerNode parent, TracerItem tracerItem, int recognizingHash) {
		this.tracerItem = tracerItem;
		this.parent = parent;
		this.children = null;
		this.recognizingHash = recognizingHash;
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
	
	public TracerNode[] getChildren() {
		if(children == null) {
			return new TracerNode[0];
		}
		
		TracerNode[] result = new TracerNode[children.size()];
		result = children.toArray(result);
		return result;
	}
	
	/**
	 * 
	 * @param compareToHash int to compare with
	 * @return true if the local stored hash has the same value as compareToHash
	 * 	otherwise false
	 */
	public boolean isRecognizedByHash(int compareToHash) {
		return recognizingHash == compareToHash;
	}
	
	public void setTracerItem(TracerItem item) {
		this.tracerItem = item;
	}
}
