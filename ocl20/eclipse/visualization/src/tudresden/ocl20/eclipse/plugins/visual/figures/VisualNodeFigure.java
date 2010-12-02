package tudresden.ocl20.eclipse.plugins.visual.figures;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;

/**
 * Figure for displaying nodes. This is just a Label with a Border around it.
 * @author Kai-Uwe Gärtner
 *
 */
public class VisualNodeFigure extends Label {
	
	public VisualNodeFigure(){
		super();
		//this.setIcon(new Image(null, VisualNodeFigure.class.getResourceAsStream("images/plus.gif")));
		this.setBorder(new LineBorder());
	}
}
