package tudresden.ocl20.eclipse.plugins.visual.model;

import java.util.List;

import org.eclipse.swt.graphics.Image;

/**
 * Interface for Nodes
 * @author Kai-Uwe Gärtner
 *
 */
public interface VisualNode {
	
	/**
	 * Returns the name
	 * @return the name
	 */
	public String getName();
	
	public void setName(String name);

	/**
	 * Returns the icon
	 * @return the icon
	 */

	public Image getIcon();
	
	public void setIcon(Image icon);
	
	/**
	 * Returns all incomming connections
	 * @return incomming connections
	 */
	public List getIncomingConnections();

	/**
	 * Returns all outgoing connections
	 * @return outgoing connections
	 */
	public List getOutgoingConnections();
	
	/**
	 * Adds a connection
	 * @param c the connection
	 */
	public void addConnection(VisualConnection c);
	
	/**
	 * Returns the node's object
	 * @return the object
	 */
	public Object getObject();
	
	/**
	 * Sets the node's data object
	 * @param o object
	 */
	public void setObject(Object o);
}
