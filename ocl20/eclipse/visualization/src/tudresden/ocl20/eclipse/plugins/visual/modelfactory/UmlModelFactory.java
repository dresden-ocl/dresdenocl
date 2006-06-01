package tudresden.ocl20.eclipse.plugins.visual.modelfactory;

import java.util.Collection;

import javax.jmi.reflect.RefPackage;

import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.ModelManager;
import tudresden.ocl20.core.jmi.uml15.core.Attribute;
import tudresden.ocl20.core.jmi.uml15.core.Method;
import tudresden.ocl20.core.jmi.uml15.core.UmlClass;
import tudresden.ocl20.eclipse.plugins.visual.model.UmlVisualNode;
import tudresden.ocl20.eclipse.plugins.visual.model.VisualNode;
import tudresden.ocl20.eclipse.plugins.visual.util.TopNode;

/**
 * Displays classes, attributes and methods
 * @author Kai-Uwe Gärtner
 *
 */
public class UmlModelFactory extends Ocl2ToolkitModelFactory {

	
	public void init(){
		doNotCall.add("getNamespace");
		doNotCall.add("getChildren");
		doNotCall.add("getAllClassesWithoutSpez");
		doNotCall.add("getGeneralization");
		doNotCall.add("getSpecialisation");
		doNotCall.add("getOwnedElement");
		includeClasses.add(UmlClass.class);
		includeClasses.add(Attribute.class);
		includeClasses.add(Method.class);
		methodPrefix = "get";		
	}
	@Override
	public Object getStartObject(Object context) {
		return new TopNode((RefPackage)context, UmlClass.class);
	}

	@Override
	public Collection getContextList() {
		try{
			return ModelManager.getInstance().getModels(MetaModelConst.UML15);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getContextDisplayName(Object context) {
		return ((RefPackage)context).refMofId();
	}
	/* (non-Javadoc)
	 * @see tudresden.ocl20.eclipse.plugins.visual.modelfactory.ConfigurableModelFactory#getNewNodeInstance()
	 */
	@Override
	public VisualNode getNewNodeInstance() {
		return new UmlVisualNode();
	}

}
