package tudresden.ocl20.pivot.tracer.model;

import tudresden.ocl20.pivot.essentialocl.expressions.OclExpression;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;

public class TracerItem {
	private OclAny result;
	private OclExpression expression;
	
	public TracerItem(OclExpression expression, OclAny result) {
		this.result = result;
		this.expression = expression;
	}
	
	public OclExpression getExpression() {
		return expression;
	}
	
	public OclAny getResult() {
		return result;
	}
}
