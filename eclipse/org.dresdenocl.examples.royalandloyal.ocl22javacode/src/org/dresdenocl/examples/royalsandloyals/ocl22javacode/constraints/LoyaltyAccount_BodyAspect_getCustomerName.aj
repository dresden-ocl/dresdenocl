package org.dresdenocl.examples.royalsandloyals.ocl22javacode.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect LoyaltyAccount_BodyAspect_getCustomerName {

    /**
     * <p>Pointcut for all calls on {@link org.dresdenocl.examples.royalsandloyals.ocl22javacode.LoyaltyAccount#getCustomerName()}.</p>
     */
    protected pointcut getCustomerNameCaller(org.dresdenocl.examples.royalsandloyals.ocl22javacode.LoyaltyAccount aClass):
    	call(* org.dresdenocl.examples.royalsandloyals.ocl22javacode.LoyaltyAccount.getCustomerName())
    	&& target(aClass);

    /**
     * <p>Defines the body of the method getCustomerName() defined by the constraint
     * <code>context LoyaltyAccount::getCustomerName(): String
     *       body: membership.card.owner.name</code></p>
     */
    String around(org.dresdenocl.examples.royalsandloyals.ocl22javacode.LoyaltyAccount aClass): getCustomerNameCaller(aClass) {
        return aClass.membership.card.owner.name;
    }
}