package tudresden.ocl20.pivot.tracer.model;

import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;

public class TracerItem {
    private OclAny result;
    private EObject expression;

    public TracerItem(EObject expression, OclAny result) {
	this.result = result;
	this.expression = expression;
    }

    public EObject getExpression() {
	return expression;
    }

    public OclAny getResult() {
	return result;
    }

    public void setResult(OclAny result) {
	this.result = result;
    }
}
