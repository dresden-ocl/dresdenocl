package tudresden.ocl20.pivot.language.ocl.resource.ocl;

import java.util.List;

import tudresden.ocl20.pivot.essentialocl.expressions.Variable;

/**
 * Holder for implicit and explicit {@link Variable}s that are defined for
 * different contexts.
 * 
 * @author Michael Thiele
 * 
 */
public class Variables {

	private List<Variable> implicitVariables;
	private List<Variable> explicitVariables;

	public List<Variable> getImplicitVariables() {
		return implicitVariables;
	}

	public void setImplicitVariables(List<Variable> implicitVariables) {
		this.implicitVariables = implicitVariables;
	}

	public List<Variable> getExplicitVariables() {
		return explicitVariables;
	}

	public void setExplicitVariables(List<Variable> explicitVariables) {
		this.explicitVariables = explicitVariables;
	}

}
