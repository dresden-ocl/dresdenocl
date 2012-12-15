package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_InvAspect_testInvariant02 {

    /**
     * <p>Describes all Constructors of the class {@link testpackage.Class1}.</p>
     */
    protected pointcut allClass1Constructors(testpackage.Class1 aClass):
        execution(testpackage.Class1.new(..)) && this(aClass);

    /**
     * <p>Describes all public methods of the class {@link testpackage.Class1}.</p>
     */
    protected pointcut allClass1PublicMethods(testpackage.Class1 aClass):
        execution(public * testpackage.Class1.*(..)) && this(aClass);

    /**
     * <p><code>Checks an invariant on the class Class1 defined by the constraint
     * <code>context Class1
     *       inv testInvariant02: self.isInvariant02Violated</code>
     * before execution of public methods.</p>
     */
    before(testpackage.Class1 aClass) : allClass1PublicMethods(aClass) {
        /* Disable this constraint for subclasses of Class1. */
        if (aClass.getClass().getCanonicalName().equals("testpackage.Class1")) {
        if (!aClass.isInvariant02Violated) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'testInvariant02' (inv testInvariant02: self.isInvariant02Violated) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }

    /**
     * <p><code>Checks an invariant on the class Class1 defined by the constraint
     * <code>context Class1
     *       inv testInvariant02: self.isInvariant02Violated</code>
     * after execution of public methods and constructors.</p>
     */
    after(testpackage.Class1 aClass) : allClass1Constructors(aClass) || allClass1PublicMethods(aClass) {
        /* Disable this constraint for subclasses of Class1. */
        if (aClass.getClass().getCanonicalName().equals("testpackage.Class1")) {
        if (!aClass.isInvariant02Violated) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'testInvariant02' (inv testInvariant02: self.isInvariant02Violated) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }

    /**
     * <p><code>Checks an invariant on the class Class1 defined by the constraint
     * <code>context Class1
     *       inv testInvariant02: self.isInvariant02Violated</code>
     * after execution of public methods throwing any Exception.</p>
     */
    after(testpackage.Class1 aClass) throwing : allClass1PublicMethods(aClass) {
        /* Disable this constraint for subclasses of Class1. */
        if (aClass.getClass().getCanonicalName().equals("testpackage.Class1")) {
        if (!aClass.isInvariant02Violated) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'testInvariant02' (inv testInvariant02: self.isInvariant02Violated) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}