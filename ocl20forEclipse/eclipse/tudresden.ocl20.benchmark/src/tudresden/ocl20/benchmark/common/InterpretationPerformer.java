package tudresden.ocl20.benchmark.common;

import java.util.List;

import tudresden.ocl20.interpreter.IOclInterpreter;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Operation;


/**
 * Enables the Testsuite to interpret each added Constraint
 * without stopping when one constraint failes.
 * (Iterator behavior)
 * @author Franz Eichhorn
 */
public class InterpretationPerformer 
{
	//! All constraints to be tested
	protected List<Constraint> constraints;

	//! objects on which all constraints shall be tested
	protected List<IModelObject> modelObjects;
	
	protected IOclInterpreter interpreter;
	
	protected TestPerformer testPerf;
	
	
	protected int curModelObject;
	protected int nextModelObject;
	protected int curConstraint;
	protected int nextConstraint;
	
	
	public InterpretationPerformer(IOclInterpreter interpreter, TestPerformer testPerf)
	{
		this.interpreter = interpreter;
		this.testPerf = testPerf;
		
		this.curModelObject = -1;
		this.nextModelObject = -1;
		this.curConstraint = -1;
		this.nextConstraint = -1;
	}
	
	public void addConstraints(List<Constraint> constraints)
	{
		this.constraints = constraints;
	}
	
	
	public void addModelObjects(List<IModelObject> modelObjects)
	{
		this.modelObjects = modelObjects;
	}
	
	
	protected String getConstraintsElementName(int iConstraint)
	{
		return this.getConstraintsElementName(this.constraints.get(iConstraint));
	}
	
	
	protected String getObjectName(int iObject)
	{
		return this.modelObjects.get(iObject).getQualifiedNameString();
	}
	
	/**
	 * Return the Element name of the constraint's constrained element
	 */
	protected String getConstraintsElementName(Constraint aC)
	{
		NamedElement aConstrainedElement = (NamedElement) aC.getConstrainedElement().get(0);

		/* Check if the constrained element is an operation. */
		if (aConstrainedElement instanceof Operation) {

			Operation anOperation = (Operation) aConstrainedElement;

			/* Use the constrained type instead. */
			aConstrainedElement = anOperation.getOwningType();
		}

		/* Get the constrained element's name. */
		return aConstrainedElement.getQualifiedName();
	}
		
	
	/**
	 * Interates over both lists by incrementing both pointers
	 * @return true if moving has finished successfully, false if there is no next element
	 */
	protected boolean movePointers()
	{
		
		// not initialized
		if(this.modelObjects == null || this.modelObjects.size() == 0
				|| this.constraints == null || this.constraints.size() == 0){
			return false;
		}
		
		// both pointer already at last item
		if(this.curConstraint == (this.constraints.size()) - 1 &&
				this.curModelObject == (this.modelObjects.size() -1)){
			return false;
		}
		
		// pointer on last model, select first again and next constraint
		if(this.curModelObject == this.modelObjects.size()-1){
			this.curModelObject = 0;
			++this.curConstraint;
		}else{
			if(this.curConstraint == -1){
				this.curConstraint = 0;
			}
			++this.curModelObject;
		}
		return true;
	}
		
	protected boolean moveToNext()
	{
		
		while(this.movePointers()){
			String aConstrainedElemsName = this.getConstraintsElementName(this.curConstraint);
			String aModelObjectsName = this.getObjectName(this.curModelObject);
			
			
			// if object matches constraint --> break here
			if (aConstrainedElemsName.equals(aModelObjectsName)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * returns current constraint.
	 * Condition: interpretNextPair-Call has to be done before. 
	 */
	public Constraint getCurrentConstraint()
	{
		return this.constraints.get(this.curConstraint);
	}
	
	/**
	 * returns current model object.
	 * Condition: interpretNextPair-Call has to be done before. 
	 */
	public IModelObject getCurrentModelObject()
	{
		return this.modelObjects.get(this.curModelObject);
	}
		
	/**
	 * Moves on to the next pair of model/constraint, interpret
	 * and return a result. 
	 * @return result of interpretation or null if there is no next pair
	 */
	public OclRoot interpretNextPair()
	{
		if(!this.moveToNext()){
			return null;
		}else{
			return this.interpreter.interpret(this.getCurrentConstraint(), this.getCurrentModelObject());
		}
	}
		
	
		
		

}
