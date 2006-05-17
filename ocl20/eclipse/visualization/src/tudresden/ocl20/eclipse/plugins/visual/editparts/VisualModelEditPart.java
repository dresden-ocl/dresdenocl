package tudresden.ocl20.eclipse.plugins.visual.editparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import tudresden.ocl20.eclipse.plugins.visual.model.VisualModel;

/**
 * Content EditPart
 * @author Kai-Uwe Gärtner
 *
 */
public class VisualModelEditPart extends AbstractGraphicalEditPart {

	@Override
	protected IFigure createFigure() {
		Figure f=new Figure();
		f.setOpaque(true);
		f.setLayoutManager(new XYLayout());
		return f;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub

	}
	/**
	 * Return all Elements
	 */
	protected List getModelChildren(){
		List l=new ArrayList();
		if (getModel() instanceof VisualModel){
			l=new ArrayList(((VisualModel)getModel()).getNodes());
			l.addAll(((VisualModel)getModel()).getConnections());
		}
		return l;
	}

}
