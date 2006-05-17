package tudresden.ocl20.eclipse.plugins.visual.model;

/**
 * Represents a Connection between two VisualNodes
 * @author Kai-Uwe Gärtner
 *
 */
public interface VisualConnection {
	
	/**
	 * Returns the source node
	 * @return source node
	 */
	public VisualNode getSource();
	
	/**
	 * Returns the target node
	 * @return target node
	 */
	public VisualNode getTarget();
	
	/**
	 * Returns true if the connection is bidirectional
	 * @return bidirectional
	 */
	public boolean isBidirectional();
	
	/**
	 * Returns the source connection label
	 * @return source connection label
	 */
	public String getSourceLabel();

	/**
	 * Returns the target connection label
	 * @return target connection label
	 */	
	public String getTargetLabel();
	
	/**
	 * Sets the source label
	 * @param label source label
	 */
	public void setSourceLabel(String label);

	/**
	 * Sets the target label
	 * @param label target label
	 */
	public void setTargetLabel(String label);
}
