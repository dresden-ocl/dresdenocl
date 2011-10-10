package tudresden.ocl20.pivot.tracer.ui.internal.views.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import tudresden.ocl20.pivot.essentialocl.expressions.*;
import tudresden.ocl20.pivot.essentialocl.types.*;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.tracer.model.TracerItem;
import tudresden.ocl20.pivot.tracer.model.TracerNode;

public class TracerLabelProvider implements ILabelProvider {

	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	public Image getImage(Object element) {
		return null;
	}
	
	public String getText(Object element) {
		String result = new String();
		
		if(element instanceof TracerNode) {
			EObject expr = ((TracerItem)element).getExpression();
			OclAny res = ((TracerItem)element).getResult();
			
			//check the type of the expression
			if(expr instanceof Variable) {
				result = ((Variable)expr).getName();
			}
			else if(expr instanceof PropertyCallExp) {
				result = ((PropertyCallExp)expr)
						.getReferredProperty().getName();
			}
			else if(expr instanceof OperationCallExp) {
				OperationCallExp tmp = (OperationCallExp)expr;
				result = (tmp.getReferredOperation() != null) ?
						tmp.getReferredOperation().getName() : tmp.getName();
			}
			else if(expr instanceof BooleanLiteralExp) {
				result = ((BooleanLiteralExp)expr).getName();
			}
			else if(expr instanceof ExpressionInOcl) {
				result = ((ExpressionInOcl)expr)
						.getBody().trim();
			}
			else if(expr instanceof Constraint) {
				result = ((Constraint)expr)
						.getSpecification().getBody().trim();
			}
			/* Show the canonical class name for debug purpose */
			else {
				result = expr.getClass().getCanonicalName();
			}
			
			if(res != null) {
				return result + " = " + "[" + res.toString() + "]";
			} else {
				return result + "NPE";
			}
		}
		//no else
		return element.getClass().getCanonicalName();
	}
}
