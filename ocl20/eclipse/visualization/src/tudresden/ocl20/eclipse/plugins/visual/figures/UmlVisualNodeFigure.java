package tudresden.ocl20.eclipse.plugins.visual.figures;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.swt.graphics.Color;

/**
 * Figure for displaying nodes. You can specify a color.
 * @author Kai-Uwe Gärtner
 *
 */
public class UmlVisualNodeFigure extends Label {
	
	public UmlVisualNodeFigure(Color c){
		super();
		this.setBorder(new LineBorder(c));
	}
}
