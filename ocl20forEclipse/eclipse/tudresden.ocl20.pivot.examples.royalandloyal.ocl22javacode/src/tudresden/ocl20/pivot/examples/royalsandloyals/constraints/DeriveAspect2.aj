package tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DeriveAspect2 {

    /**
     * <p>Pointcut for all requests on {@link tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount#totalPointsEarned}.</p>
     */
    protected pointcut totalPointsEarnedGetter(tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount aClass) :
    	get(* totalPointsEarned) && this(aClass);
    
    /**
     * <p>Derives the attribute totalPointsEarned defined by the constraint
     * <code>context LoyaltyAccount
     *       def: totalPointsEarned = transactions[] -> select (  | oclIsTypeOf( Earning[])).points[].sum()</code></p>
     */
    before(tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount aClass): totalPointsEarnedGetter(aClass) {
        tudresden.ocl20.pivot.ocl2java.types.OclSet<tudresden.ocl20.pivot.examples.royalsandloyals.Transaction> result2;
        result2 = new tudresden.ocl20.pivot.ocl2java.types.OclSet<tudresden.ocl20.pivot.examples.royalsandloyals.Transaction>();
        
        /* Iterator Select: Select all elements which fulfill the condition. */
        for (tudresden.ocl20.pivot.examples.royalsandloyals.Transaction anElement2 : aClass.transactions) {
            if (anElement2.getClass().getCanonicalName().equals("tudresden.ocl20.pivot.examples.royalsandloyals.Earning")) {
                result2.add(anElement2);
            }
            // no else
        }
        tudresden.ocl20.pivot.ocl2java.types.OclBag<Integer> result1;
        result1 = new tudresden.ocl20.pivot.ocl2java.types.OclBag<Integer>();
        
        /* Iterator Collect: Iterate through all elements and collect them. Elements which are collections are flattened. */
        for (tudresden.ocl20.pivot.examples.royalsandloyals.Transaction anElement1 : result2) {
            result1.add(anElement1.points);
        }

        Integer result3;
        result3 = new Integer(0);
        
        /* Compute the result of a sum operation. */
        for (Integer anElement3 : result1) {
            result3 += anElement3;
        }
    
        aClass.totalPointsEarned = result3;
    }
}