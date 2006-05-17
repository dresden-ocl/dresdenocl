package tudresden.ocl20.eclipse.plugins.visual.editparts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import tudresden.ocl20.eclipse.plugins.visual.model.VisualConnection;
import tudresden.ocl20.eclipse.plugins.visual.model.VisualModel;
import tudresden.ocl20.eclipse.plugins.visual.model.VisualNode;

/**
 * Factory for constructing EditParts
 * @author Kai-Uwe Gärtner
 *
 */
public class VisualEditPartFactory implements EditPartFactory {
	private static final String EDITPARTPACKAGE="editparts";
	
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part=null;
		//Visual model
		if (model instanceof VisualModel){
			part=new VisualModelEditPart();
			part.setModel(model);
		}else
		//Visual Node
		if (model instanceof VisualNode){
			//Looking for Special EditPart-Class
			try {
				//Finding EditPart-Class
				String classname=model.getClass().getSimpleName();
				String packagename=model.getClass().getPackage().getName();
				packagename=packagename.substring(0,packagename.lastIndexOf('.'));
				packagename=packagename+'.'+EDITPARTPACKAGE;
				Class partClass=Class.forName(packagename+"."+classname+"EditPart");
				part=(EditPart)partClass.newInstance();
				part.setModel(model);
				return part;
			}catch (Exception e){
				//Not found, using Standard Class
				part=new VisualNodeEditPart();
				part.setModel(model);
			}
		}else
		//Visual Connection
		if (model instanceof VisualConnection){
			part=new VisualConnectionEditPart();
			part.setModel(model);
		}
		return part;
	}

}
