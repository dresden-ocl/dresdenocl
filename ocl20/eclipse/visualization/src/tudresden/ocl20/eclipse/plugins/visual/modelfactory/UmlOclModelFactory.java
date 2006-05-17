package tudresden.ocl20.eclipse.plugins.visual.modelfactory;

import java.util.Collection;

import tudresden.ocl20.core.jmi.ocl.commonmodel.Constraint;
import tudresden.ocl20.core.jmi.ocl.expressions.ExpressionInOcl;
import tudresden.ocl20.core.jmi.ocl.expressions.OclExpression;
import tudresden.ocl20.core.jmi.uml15.core.CorePackage;
import tudresden.ocl20.core.jmi.uml15.uml15.Uml15Package;
import tudresden.ocl20.eclipse.plugins.visual.model.DefaultVisualNode;
import tudresden.ocl20.eclipse.plugins.visual.model.VisualNode;
import tudresden.ocl20.eclipse.plugins.visual.modelfactory.Ocl2ToolkitModelFactory;

/**
 * Generates a ModelFactory for OCL-Constraints
 * @author Kai-Uwe Gärtner
 *
 */
public class UmlOclModelFactory extends Ocl2ToolkitModelFactory {

	public void init(){
		doNotCall.add("getNamespace");
		doNotCall.add("getChildren");
		doNotCall.add("getFeature");
		doNotCall.add("getParents");
		doNotCall.add("getAllClassesWithoutSpez");
		doNotCall.add("getGeneralization");
		doNotCall.add("getSpecialisation");
		doNotCall.add("getOwnedElement");
		includeClasses.add(tudresden.ocl20.core.jmi.ocl.commonmodel.ModelElement.class);
		methodPrefix = "get";
		
	}
	
	public Collection getContextList() {
		if ((model!=null)&&(model.getModel() instanceof Uml15Package)){
			Uml15Package up = (Uml15Package) model.getModel();
			CorePackage c = up.getCore();
			Collection conlist = c.getConstraint().refAllOfType();
			return conlist;
		}
		else return null;
	}
	
	public Object getStartObject(Object context){
		try {
			ExpressionInOcl e = (ExpressionInOcl) 
				((Constraint)context).getBodyA();
			OclExpression oclExp = e.getBodyExpression();
			return oclExp;
		}
		catch (ClassCastException e){
			
		}
		return null;
	}
	
	public String getContextDisplayName(Object context) {
		//Use name of constraint if possible
		if (context instanceof Constraint) 
			if ((((Constraint)context).getNameA()!=null)&&(!((Constraint)context).getNameA().equals(""))) return ((Constraint)context).getNameA();
			else return context.toString();
		return "";
	}
}
