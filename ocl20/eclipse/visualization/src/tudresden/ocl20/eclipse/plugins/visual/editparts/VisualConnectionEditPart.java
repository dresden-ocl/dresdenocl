package tudresden.ocl20.eclipse.plugins.visual.editparts;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.swt.graphics.Color;

import tudresden.ocl20.eclipse.plugins.visual.model.VisualConnection;
import tudresden.ocl20.eclipse.plugins.visual.model.VisualNode;

/**
 * EditPart for connections
 * @author Kai-Uwe Gärtner
 *
 */
public class VisualConnectionEditPart extends AbstractConnectionEditPart {

	private static final Color connectionColor=ColorConstants.blue;
	
	@Override
	protected void createEditPolicies() {

	}
	
	/**
	 * Returns the number of a Connection in src oder target connection 
	 * list
	 * @param c
	 * @param isSrc
	 * @return
	 */
	private int getNumber(VisualConnection c,boolean isSrc){
		VisualNode node;
		List connections;
		if (isSrc) {
			node=c.getSource();
			connections=node.getOutgoingConnections();
		}
		else {
			node=c.getTarget();
			connections=node.getIncomingConnections();
		}
		Iterator it=connections.iterator();
		int count=1;
		while (it.hasNext()){
			if (it.next()==c) break;
			/*if (((isSrc)&&(((VisualConnection)c).getTargetLabel().length()!=0))|
				((!isSrc)&&(((VisualConnection)c).getSourceLabel().length()!=0)))*/
			count++;
		}
		return count;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#registerVisuals()
	 */
	@Override
	protected void registerVisuals() {
		VisualConnection visualConnection=(VisualConnection)getModel();
		PolylineConnection connectionFigure = (PolylineConnection) getFigure();

		connectionFigure.setForegroundColor(connectionColor);
		//Target
		ConnectionEndpointLocator targetEndpointLocator = new ConnectionEndpointLocator(
				connectionFigure, true);
		
		targetEndpointLocator.setUDistance(getNumber(visualConnection,false)*10);
		targetEndpointLocator.setVDistance(0);
		Label targetMultiplicityLabel = new Label(visualConnection.getSourceLabel());
		connectionFigure.add(targetMultiplicityLabel, targetEndpointLocator);
		//Source
		ConnectionEndpointLocator sourceEndpointLocator = new ConnectionEndpointLocator(
				connectionFigure, false);
		sourceEndpointLocator.setUDistance(getNumber(visualConnection,true)*10);
		sourceEndpointLocator.setVDistance(0);
		Label sourceMultiplicityLabel = new Label(visualConnection.getTargetLabel());
		connectionFigure.add(sourceMultiplicityLabel, sourceEndpointLocator);

	}

}
