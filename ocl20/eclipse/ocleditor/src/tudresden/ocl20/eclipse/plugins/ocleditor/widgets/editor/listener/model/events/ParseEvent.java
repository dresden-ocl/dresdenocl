package tudresden.ocl20.eclipse.plugins.ocleditor.widgets.editor.listener.model.events;

/**
 * This event is thrown if an OCL constraint was parsed.
 * @author Mirko Stölzel
 *
 */
public class ParseEvent  extends AbstractChangeEvent
{
	private String problem = null;

	/**
	 * Returns whether a problem occured while parsing the constraint.
	 * @return true if a problem occured, otherwise false
	 */
	public boolean problemsOccured() 
	{
		if (problem == null)
			return false;
		return true;
	}

	/**
	 * Is used to set a problem.
	 * @param problem
	 */
	public void setProblem(String problem) {
		this.problem = problem;
	}
	
	/**
	 * Returns the problem.
	 * @return the problem, otherwise null
	 */
	public String getProblem() {
		return this.problem;
	}
	
}
