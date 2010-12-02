package tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect LoyaltyAccount_DeriveAspect_totalPointsEarned {

    /**
     * <p>Pointcut for all requests on {@link tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount#totalPointsEarned}.</p>
     */
    protected pointcut totalPointsEarnedGetter(tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount aClass) :
    	get(* totalPointsEarned) && target(aClass);
    
    /**
     * <p>Derives the attribute totalPointsEarned defined by the constraint
     * <code>context LoyaltyAccount
     *       derive: transactions->select(
    	oclIsTypeOf(Earning)).points->sum()</code></p>
     */
    before(tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount aClass): totalPointsEarnedGetter(aClass) {
        java.util.HashSet<tudresden.ocl20.pivot.examples.royalsandloyals.Transaction> result2;
        result2 = new java.util.HashSet<tudresden.ocl20.pivot.examples.royalsandloyals.Transaction>();
        
        /* Iterator Select: Select all elements which fulfill the condition. */
        for (tudresden.ocl20.pivot.examples.royalsandloyals.Transaction anElement1 : aClass.transactions) {
            if (anElement1.getClass().getCanonicalName().equals(tudresden.ocl20.pivot.examples.royalsandloyals.Earning.class.getCanonicalName())) {
                result2.add(anElement1);
            }
            // no else
        }
        java.util.ArrayList<Integer> result1;
        result1 = new java.util.ArrayList<Integer>();
        
        /* Iterator Collect: Iterate through all elements and collect them. Elements which are collections are flattened. */
        for (tudresden.ocl20.pivot.examples.royalsandloyals.Transaction anElement2 : result2) {
            result1.add(anElement2.points);
        }
    
        aClass.totalPointsEarned = new Integer(tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclCollections.sum(result1).intValue());
    }
}