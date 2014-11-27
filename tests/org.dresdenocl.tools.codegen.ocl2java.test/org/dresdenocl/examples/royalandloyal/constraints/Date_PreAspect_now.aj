package org.dresdenocl.examples.royalandloyal.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Date_PreAspect_now {

    /**
     * <p>Pointcut for all calls on {@link org.dresdenocl.examples.royalandloyal.Date#now()}.</p>
     */
    protected pointcut nowCaller():
    	execution(* org.dresdenocl.examples.royalandloyal.Date.now());

    /**
     * <p>Checks a precondition for the {@link Date#now()} defined by the constraint
     * <code>context Date::now() : org.dresdenocl.examples.royalandloyal.Date
     *       pre: true</code></p>
     */
    before(): nowCaller() {
        if (!new Boolean(true)) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'undefined' (pre: true) was violated for Object static field or operation.";
        	throw new RuntimeException(msg);
        }
        // no else.
    }
}