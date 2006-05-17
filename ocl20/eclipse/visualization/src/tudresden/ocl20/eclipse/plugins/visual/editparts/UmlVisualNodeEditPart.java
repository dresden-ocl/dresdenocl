package tudresden.ocl20.eclipse.plugins.visual.editparts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.Color;

import tudresden.ocl20.core.jmi.uml15.core.Attribute;
import tudresden.ocl20.core.jmi.uml15.core.Method;
import tudresden.ocl20.eclipse.plugins.visual.figures.UmlVisualNodeFigure;
import tudresden.ocl20.eclipse.plugins.visual.model.UmlVisualNode;

/**
 * EditPart to visualize UML elements
 * @author Kai-Uwe Gärtner
 *
 */
public class UmlVisualNodeEditPart extends VisualNodeEditPart{
	@Override
	protected IFigure createFigure() {
		Color c=ColorConstants.black;
		Object o=((UmlVisualNode)getModel()).getObject();
		if (o instanceof Attribute) c=ColorConstants.blue;
		if (o instanceof Method) c=ColorConstants.red;
		System.out.println(c);
		IFigure f=new UmlVisualNodeFigure(c);
		return f;
	}

}
