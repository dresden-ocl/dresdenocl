package tudresden.ocl20.eclipse.plugins.visual.model;

/**
 * Standard Visual Connection
 * @author Kai-Uwe Gärtner
 *
 */
public class DefaultVisualConnection implements VisualConnection {

	private VisualNode source;
	private VisualNode target;
	private String sourceLabel;
	private String targetLabel;
	
	public DefaultVisualConnection(VisualNode src, VisualNode target){
		source=src;
		this.target=target;
		//Adds itself to src and target
		src.addConnection(this);
		target.addConnection(this);
	}
	
	public VisualNode getSource() {
		return source;
	}

	public VisualNode getTarget() {
		return target;
	}

	public boolean isBidirectional() {
		return false;
	}

	/**
	 * @return Returns the targetLabel.
	 */
	public String getTargetLabel() {
		return targetLabel;
	}

	/**
	 * @param targetLabel The targetLabel to set.
	 */
	public void setTargetLabel(String targetLabel) {
		this.targetLabel = targetLabel;
	}

	/**
	 * @return Returns the sourceLabel.
	 */
	public String getSourceLabel() {
		return sourceLabel;
	}

	/**
	 * @param sourceLabel The sourceLabel to set.
	 */
	public void setSourceLabel(String sourceLabel) {
		this.sourceLabel = sourceLabel;
	}

	
}
