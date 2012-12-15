package org.dresdenocl.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect LoyaltyAccount_DeriveAspect_totalPointsEarned {

    /**
     * <p>Pointcut for all requests on {@link org.dresdenocl.examples.royalsandloyals.LoyaltyAccount#totalPointsEarned}.</p>
     */
    protected pointcut totalPointsEarnedGetter(org.dresdenocl.examples.royalsandloyals.LoyaltyAccount aClass) :
    	get(* totalPointsEarned) && target(aClass);

    /**
     * <p>Derives the attribute totalPointsEarned defined by the constraint
     * <code>context LoyaltyAccount
     *       derive: transactions->select( 	oclIsTypeOf(Earning)).points->sum()</code></p>
     */
    before(org.dresdenocl.examples.royalsandloyals.LoyaltyAccount aClass): totalPointsEarnedGetter(aClass) {
        java.util.HashSet<org.dresdenocl.examples.royalsandloyals.Transaction> result2;
        result2 = new java.util.HashSet<org.dresdenocl.examples.royalsandloyals.Transaction>();

        /* Iterator Select: Select all elements which fulfill the condition. */
        for (org.dresdenocl.examples.royalsandloyals.Transaction anElement1 : aClass.transactions) {
            if (anElement1.getClass().getCanonicalName().equals(org.dresdenocl.examples.royalsandloyals.Earning.class.getCanonicalName())) {
                result2.add(anElement1);
            }
            // no else
        }
        java.util.ArrayList<Integer> result1;
        result1 = new java.util.ArrayList<Integer>();

        /* Iterator Collect: Iterate through all elements and collect them. Elements which are collections are flattened. */
        for (org.dresdenocl.examples.royalsandloyals.Transaction anElement2 : result2) {
            result1.add(anElement2.points);
        }

        aClass.totalPointsEarned = new Integer(org.dresdenocl.tools.codegen.ocl2java.types.util.OclCollections.sum(result1).intValue());
    }
}