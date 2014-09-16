package org.dresdenocl.examples.royalandloyal.ocl22javacode.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Customer_InvAspect_ofAge {

    /**
     * <p>Describes all Constructors of the class {@link org.dresdenocl.examples.royalandloyal.ocl22javacode.Customer}.</p>
     */
    protected pointcut allCustomerConstructors(org.dresdenocl.examples.royalandloyal.ocl22javacode.Customer aClass):
        execution(org.dresdenocl.examples.royalandloyal.ocl22javacode.Customer.new(..)) && this(aClass);

    /**
     * <p>Describes all public methods of the class {@link org.dresdenocl.examples.royalandloyal.ocl22javacode.Customer}.</p>
     */
    protected pointcut allCustomerPublicMethods(org.dresdenocl.examples.royalandloyal.ocl22javacode.Customer aClass):
        execution(public * org.dresdenocl.examples.royalandloyal.ocl22javacode.Customer.*(..)) && this(aClass);

    /**
     * <p><code>Checks an invariant on the class Customer defined by the constraint
     * <code>context Customer
     *       inv ofAge: age >= 18</code>
     * before execution of public methods.</p>
     */
    before(org.dresdenocl.examples.royalandloyal.ocl22javacode.Customer aClass) : allCustomerPublicMethods(aClass) {
        /* Disable this constraint for subclasses of Customer. */
        if (aClass.getClass().getCanonicalName().equals("org.dresdenocl.examples.royalandloyal.ocl22javacode.Customer")) {
        if (!(aClass.age >= new Integer(18))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'ofAge' (inv ofAge: age >= 18) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }

    /**
     * <p><code>Checks an invariant on the class Customer defined by the constraint
     * <code>context Customer
     *       inv ofAge: age >= 18</code>
     * after execution of public methods and constructors.</p>
     */
    after(org.dresdenocl.examples.royalandloyal.ocl22javacode.Customer aClass) : allCustomerConstructors(aClass) || allCustomerPublicMethods(aClass) {
        /* Disable this constraint for subclasses of Customer. */
        if (aClass.getClass().getCanonicalName().equals("org.dresdenocl.examples.royalandloyal.ocl22javacode.Customer")) {
        if (!(aClass.age >= new Integer(18))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'ofAge' (inv ofAge: age >= 18) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }

    /**
     * <p><code>Checks an invariant on the class Customer defined by the constraint
     * <code>context Customer
     *       inv ofAge: age >= 18</code>
     * after execution of public methods throwing any Exception.</p>
     */
    after(org.dresdenocl.examples.royalandloyal.ocl22javacode.Customer aClass) throwing : allCustomerPublicMethods(aClass) {
        /* Disable this constraint for subclasses of Customer. */
        if (aClass.getClass().getCanonicalName().equals("org.dresdenocl.examples.royalandloyal.ocl22javacode.Customer")) {
        if (!(aClass.age >= new Integer(18))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'ofAge' (inv ofAge: age >= 18) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}