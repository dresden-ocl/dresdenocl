package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_InvAspect_testInvariant01 {

    /**
     * <p>Describes all Constructors of the class {@link testpackage.Class1}.</p>
     */
    protected pointcut allClass1Constructors(testpackage.Class1 aClass):
        execution(testpackage.Class1.new(..)) && this(aClass);

    /**
     * <p>Pointcut for all changes of the attribute {@link testpackage.Class1#isInvariant01Violated}.</p>
     */
    protected pointcut isInvariant01ViolatedSetter(testpackage.Class1 aClass) :
        set(* testpackage.Class1.isInvariant01Violated) && target(aClass); 

    /**
     * <p>Pointcut to collect all attributeSetters.</p>
     */
    protected pointcut allSetters(testpackage.Class1 aClass) :
    	isInvariant01ViolatedSetter(aClass);
    /**
     * <p><code>Checks an invariant on the class Class1 defined by the constraint
     * <code>context Class1
     *       inv testInvariant01: self.isInvariant01Violated</code></p>
     */
    after(testpackage.Class1 aClass) : allClass1Constructors(aClass) || allSetters(aClass) {
        /* Disable this constraint for subclasses of Class1. */
        if (aClass.getClass().getCanonicalName().equals("testpackage.Class1")) {
        if (!aClass.isInvariant01Violated) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'testInvariant01' (inv testInvariant01: self.isInvariant01Violated) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}