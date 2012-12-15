package org.dresdenocl.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Service_PostAspect_upgradePointsEarned {

    /**
     * <p>Pointcut for all calls on {@link org.dresdenocl.examples.royalsandloyals.Service#upgradePointsEarned(Integer amount)}.</p>
     */
    protected pointcut upgradePointsEarnedCaller(org.dresdenocl.examples.royalsandloyals.Service aClass, Integer amount):
    	call(* org.dresdenocl.examples.royalsandloyals.Service.upgradePointsEarned(Integer))
    	&& target(aClass) && args(amount);

    /**
     * <p>Checks a postcondition for the operation {@link Service#upgradePointsEarned(, Integer amount)} defined by the constraint
     * <code>context Service::upgradePointsEarned(amount: Integer) : 
     *       post: calcPoints() = calcPoints@pre() + amount</code></p>
     */
    void around(org.dresdenocl.examples.royalsandloyals.Service aClass, Integer amount): upgradePointsEarnedCaller(aClass, amount) {
        /* Disable this constraint for subclasses of Service. */
        if (aClass.getClass().getCanonicalName().equals("org.dresdenocl.examples.royalsandloyals.Service")) {

        Integer atPreValue1;

        if ((Object) aClass.calcPoints() == null) {
            atPreValue1 = null;
        } else {
            atPreValue1 = new Integer(aClass.calcPoints());
        }

        proceed(aClass, amount);

        if (!((Object) aClass.calcPoints()).equals((atPreValue1 + amount))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'undefined' (post: calcPoints() = calcPoints@pre() + amount) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}