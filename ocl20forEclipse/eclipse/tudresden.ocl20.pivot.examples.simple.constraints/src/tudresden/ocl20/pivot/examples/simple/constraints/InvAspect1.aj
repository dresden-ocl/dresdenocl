package tudresden.ocl20.pivot.examples.simple.constraints;

@Generated
public privileged aspect InvAspect1 {

    /**
     * <p>Describes all Constructors of the class {@link tudresden.ocl20.pivot.examples.simple.Person}.</p>
     */
    protected pointcut allPersonConstructors(tudresden.ocl20.pivot.examples.simple.Person aClass):
        execution(tudresden.ocl20.pivot.examples.simple.Person.new(..)) && this(aClass);
    
    /**
     * <p>Pointcut for all changes of the attribute {@link tudresden.ocl20.pivot.examples.simple.Person#age}.</p>
     */
    protected pointcut ageSetter(tudresden.ocl20.pivot.examples.simple.Person aClass) :
        set(* tudresden.ocl20.pivot.examples.simple.Person.age) && this(aClass); 
    
    /**
     * <p>Pointcut to collect all attributeSetters.</p>
     */
    protected pointcut allSetters(tudresden.ocl20.pivot.examples.simple.Person aClass) :
    	ageSetter(aClass);
    /**
     * <p><code>Checks an invariant on the class Person defined by the constraint
     * <code>context Person
     *       inv: age[].>=( 0)</code></p>
     */
    after(tudresden.ocl20.pivot.examples.simple.Person aClass) : allPersonConstructors(aClass) || allSetters(aClass) {
        if (!(aClass.age >= new Integer(0))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	throw new RuntimeException("Error: Constraint was violated.");
        }
        // no else.
    }
}