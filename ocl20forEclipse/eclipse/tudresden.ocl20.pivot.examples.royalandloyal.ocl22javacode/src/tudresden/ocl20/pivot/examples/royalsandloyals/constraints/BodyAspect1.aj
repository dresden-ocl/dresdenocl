package tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

@Generated
public privileged aspect BodyAspect1 {

    /**
     * <p>Pointcut for all calls on {@link tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount#getCustomerName()}.</p>
     */
    protected pointcut getCustomerNameCaller(tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount aClass):
    	call(* tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount.getCustomerName())
    	&& target(aClass);
    
    /**
     * <p>Defines the body of the method getCustomerName() defined by the constraint
     * <code>context LoyaltyAccount::getCustomerName(): String
     *       body: membership[].card[].owner[].name[]</code></p>
     */
    String around(tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount aClass): getCustomerNameCaller(aClass) {
        return aClass.membership.card.owner.name;
    }
}