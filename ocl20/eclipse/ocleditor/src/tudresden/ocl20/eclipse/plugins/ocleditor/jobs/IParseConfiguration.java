package tudresden.ocl20.eclipse.plugins.ocleditor.jobs;

import tudresden.ocl20.integration.ModelFacade;

/**
 * An instance of this class is used for the consistency check.
 * @author Mirko Stölzel
 *
 */
public class IParseConfiguration 
{
	protected ModelFacade facade;
	protected Object topPackage;
	protected Object constraint;
	protected String constraintText;
	
	/**
	 * Returns the ModelFacade instance which should be used for the consistency check
	 * of a constraint
	 * @return facade
	 */
	public ModelFacade getModelFacade()
	{
		return facade;
	}
	
	/**
	 * Returns the topPackage instance which should be used for the consistency check
	 * of a constraint
	 * @return topPackage
	 */
	public Object getTopPackage()
	{
		return topPackage;
	}
	
	/**
	 * Returns the textual representation of the constraint which consistency should be checked.
	 * @return constraintText
	 */
	public String getConstraintText()
	{
		return constraintText;
	}
	
	/**
	 * Returns the constraint object which consistency should be checked.
	 * @return constraint
	 */
	public Object getConstraint()
	{
		return constraint;
	}

	/**
	 * This method is used to set the ModelFacade instance.
	 * This instance is used for the consistency check.
	 * @param facade
	 */
	public void setModelFacade(ModelFacade facade) {
		this.facade = facade;
	}

	/**
	 * This method is used to set the constraint instance.
	 * @param constraint
	 */
	public void setConstraint(Object constraint) {
		this.constraint = constraint;
	}

	/**
	 * This method is used to set the constraint which consistency should be checked.
	 * @param constraintText
	 */
	public void setConstraintText(String constraintText) {
		this.constraintText = constraintText;
	}

	/**
	 * This method is used to set the topPackage instance.
	 * This instance is used for the consistency check.
	 * @param topPackage
	 */
	public void setTopPackage(Object topPackage) {
		this.topPackage = topPackage;
	}	
}
