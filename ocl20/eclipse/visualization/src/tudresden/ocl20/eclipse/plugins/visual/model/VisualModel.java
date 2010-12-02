package tudresden.ocl20.eclipse.plugins.visual.model;

import java.util.Collection;

/**
 * Represents a Visual Model. This interface is used by the rest of this plugin.
 * @author Kai-Uwe Gärtner
 *
 */
public interface VisualModel {
	
	/**
	 * Adds a node to the model
	 * @param node the node to add
	 */
	public void addNode(VisualNode node);

	/**
	 * Adds a connection to the model
	 * @param vc connection to add
	 */
	public void addConnection(VisualConnection vc);
	
	/**
	 * Returns a collection of all connections
	 * @return all nodes
	 */
	public Collection getConnections();
	
	/**
	 * Returns a connection by given nodes 
	 * @param n1 node
	 * @param n2 node
	 * @return the connection
	 */
	public VisualConnection getConnection(VisualNode n1, VisualNode n2);
	
	/**
	 * Returns a collection of all nodes
	 * @return all nodes
	 */
	public Collection getNodes();
	
	/**
	 * Finds a Node by its object
	 * @param o the nodes object
	 * @return the node
	 */
	public VisualNode getNodeByObject(Object o);
}
