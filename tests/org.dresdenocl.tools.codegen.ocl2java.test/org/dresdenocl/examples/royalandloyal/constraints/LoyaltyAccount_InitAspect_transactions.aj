package org.dresdenocl.examples.royalandloyal.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect LoyaltyAccount_InitAspect_transactions {

    /**
     * <p>Describes all Constructors of the class {@link org.dresdenocl.examples.royalandloyal.LoyaltyAccount}.</p>
     */
    protected pointcut allLoyaltyAccountConstructors(org.dresdenocl.examples.royalandloyal.LoyaltyAccount aClass):
        execution(org.dresdenocl.examples.royalandloyal.LoyaltyAccount.new(..)) && this(aClass);

    /**
     * <p><code>Initializes the attribute transactions defined by the constraint
     * <code>context LoyaltyAccount::transactions
     *       init: Set{}</code></p>
     */
    after(org.dresdenocl.examples.royalandloyal.LoyaltyAccount aClass) : allLoyaltyAccountConstructors(aClass) {
        java.util.HashSet collection1;
        collection1 = new java.util.HashSet();

        aClass.transactions = collection1;
    }
}