package tudresden.ocl20.pivot.tracer.ui.internal.views.util;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

import tudresden.ocl20.pivot.essentialocl.expressions.BooleanLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl;
import tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp;
import tudresden.ocl20.pivot.essentialocl.expressions.PropertyCallExp;
import tudresden.ocl20.pivot.essentialocl.expressions.Variable;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerItem;

public class TracerItemAdapterFactoryLabelProvider extends
		AdapterFactoryLabelProvider {

	public TracerItemAdapterFactoryLabelProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	public String getText(Object object) {
		if(object instanceof TracerItem) {
			String result = new String();
			EObject expr = ((TracerItem)object).getExpression();
			OclAny res = ((TracerItem)object).getResult();
			
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
				result = expr.getClass().getCanonicalName() + " MOEP";
			}
			
			if(res != null) {
				return result + " " + "[" + res.toString() + "]";
			}
			//no else			
		}
		//no else
		//return super.getText(object);
		return object.toString();
	}
}
