package tudresden.ocl20.eclipse.plugins.visual.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.graph.Node;
import org.eclipse.swt.graphics.Image;

/**
 * Standard VisualNode implementation.
 * @author Kai-Uwe Gärtner
 *
 */
public class DefaultVisualNode extends Node implements VisualNode{

	private String name;
	private Image icon;
	private List<VisualConnection> incomingConnections=new ArrayList<VisualConnection>();
	private List<VisualConnection> outgoingConnections=new ArrayList<VisualConnection>();
	private Object nodeObject;
	
	public DefaultVisualNode(){
	}
	
	/* (non-Javadoc)
	 * @see tudresden.ocl20.eclipse.plugins.visual.model.VisualNode#getName()
	 */
	public String getName() {
		
		return name;
	}
	
	public Image getIcon() {
		return icon;
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.eclipse.plugins.visual.model.VisualNode#getIncomingConnections()
	 */
	public List<VisualConnection> getIncomingConnections() {
		//System.out.println("GetIncomingConnections: "+getName()+" "+incomingConnections.size());
		return incomingConnections;
	}
	/* (non-Javadoc)
	 * @see tudresden.ocl20.eclipse.plugins.visual.model.VisualNode#getOutgoingConnections()
	 */
	public List<VisualConnection> getOutgoingConnections() {
		//System.out.println("GetOutgoingConnections: "+getName()+" "+outgoingConnections.size());
		return outgoingConnections;
	}
	
	public void addConnection(VisualConnection c){
		if (c.getSource()==this) outgoingConnections.add(c);
		else incomingConnections.add(c);
	}
	
	public Object getObject(){
		return nodeObject;
	}
	
	public void setObject(Object o){
		nodeObject=o;
	}
	/* (non-Javadoc)
	 * @see tudresden.ocl20.eclipse.plugins.visual.model.VisualNode#setIcon(org.eclipse.swt.graphics.Image)
	 */
	public void setIcon(Image icon) {
		this.icon=icon;
		
	}
	/* (non-Javadoc)
	 * @see tudresden.ocl20.eclipse.plugins.visual.model.VisualNode#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name=name;
		
	}
	

}
