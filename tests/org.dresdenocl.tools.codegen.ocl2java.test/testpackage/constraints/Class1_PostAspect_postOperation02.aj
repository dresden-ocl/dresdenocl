package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_PostAspect_postOperation02 {

    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#postOperation02()}.</p>
     */
    protected pointcut postOperation02Caller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.postOperation02())
    	&& target(aClass);

    /**
     * <p>Checks a postcondition for the operation {@link Class1#postOperation02()} defined by the constraint
     * <code>context Class1::postOperation02() : 
     *       post testAtPre02: self.anInteger01@pre = self.anInteger01 - 1</code></p>
     */
    void around(testpackage.Class1 aClass): postOperation02Caller(aClass) {
        /* Disable this constraint for subclasses of Class1. */
        if (aClass.getClass().getCanonicalName().equals("testpackage.Class1")) {

        Integer atPreValue1;

        if ((Object) aClass.anInteger01 == null) {
            atPreValue1 = null;
        } else {
        atPreValue1 = new Integer(aClass.anInteger01);
        }

        proceed(aClass);

        if (!((Object) atPreValue1).equals((aClass.anInteger01 - new Integer(1)))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'testAtPre02' (post testAtPre02: self.anInteger01@pre = self.anInteger01 - 1) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}