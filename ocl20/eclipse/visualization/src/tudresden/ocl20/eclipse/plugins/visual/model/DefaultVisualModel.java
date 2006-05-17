package tudresden.ocl20.eclipse.plugins.visual.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Standard VisualModel implementation. It contains a set of nodes and connections.
 * @author Kai-Uwe Gärtner
 *
 */
public class DefaultVisualModel implements VisualModel {

	private List<VisualNode> nodes;
	private List<VisualConnection> connections;
	
	public DefaultVisualModel(){
		nodes=new ArrayList<VisualNode>();
		connections=new ArrayList<VisualConnection>();
	}
	
	public List getConnections() {
		//System.out.println("GetConnections");
		
		return new ArrayList();
	}

	public void addNode(VisualNode node){
		nodes.add(node);
	}
	
	public void addConnection(VisualConnection vc){
		connections.add(vc);
	}
	
	public List getNodes() {
		return nodes;
	}
	
	public VisualNode getNodeByObject(Object o) {
		Iterator it=nodes.iterator();
		while (it.hasNext()){
			VisualNode vn=(VisualNode)it.next();
			if (vn.getObject()==o) return vn;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.eclipse.plugins.visual.model.VisualModel#getConnection(tudresden.ocl20.eclipse.plugins.visual.model.VisualNode, tudresden.ocl20.eclipse.plugins.visual.model.VisualNode)
	 */
	public VisualConnection getConnection(VisualNode n1, VisualNode n2) {
		Iterator it=connections.iterator();
		while (it.hasNext()){
			VisualConnection vc=(VisualConnection)it.next();
			if (((vc.getSource()==n1)&&(vc.getTarget()==n2)/*||((vc.getSource()==n2)&&(vc.getDest()==n1))*/)) return vc;		
		}
		return null;
	}

}
