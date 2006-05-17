package tudresden.ocl20.eclipse.plugins.visual.editparts;

import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import tudresden.ocl20.eclipse.plugins.visual.figures.VisualNodeFigure;
import tudresden.ocl20.eclipse.plugins.visual.model.VisualNode;

/**
 * Node EditPart
 * @author Kai-Uwe Gärtner
 *
 */
public class VisualNodeEditPart extends AbstractGraphicalEditPart implements NodeEditPart{
	private static final String FIGURESPACKAGE="figures";
	
	@Override
	protected IFigure createFigure() {
		try {
			//Finding Figure-Class
			String classname=getModel().getClass().getSimpleName();
			String packagename=getModel().getClass().getPackage().getName();
			packagename=packagename.substring(0,packagename.lastIndexOf('.'));
			packagename=packagename+'.'+FIGURESPACKAGE;
			Class partClass=Class.forName(packagename+"."+classname+"Figure");
			return (Figure)partClass.newInstance();
		}catch (Exception e){		
			//using standard
			Figure f=new VisualNodeFigure();
			return f;
		}
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub

	}
	
	protected void refreshVisuals(){
		if (getFigure() instanceof Label){
			Label f=(Label)getFigure();
			f.setText(((VisualNode)getModel()).getName());
			f.setIcon(((VisualNode)getModel()).getIcon());
			((AbstractGraphicalEditPart)getParent()).setLayoutConstraint(this,f,new Rectangle(f.getLocation().x,f.getLocation().y,-1,-1));
			
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.Request)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.Request)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelSourceConnections()
	 */
	@Override
	protected List getModelSourceConnections() {
		// TODO Auto-generated method stub
		return ((VisualNode)getModel()).getOutgoingConnections();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelTargetConnections()
	 */
	@Override
	protected List getModelTargetConnections() {
		// TODO Auto-generated method stub
		return ((VisualNode)getModel()).getIncomingConnections();
	}

}
