package tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect LoyaltyAccount_DefAspect_turnover {

    /**
     * <p>Defines the field turnover defined by the constraint
     * <code>context LoyaltyAccount
     *       def: turnover : Real = transactions.amount->sum()</code></p>
     */
    public Float tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount.turnover;
    
    /**
     * <p>Getter method for the attribute turnover.</p>
     */
    public Float tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount.getTurnover() {
        return this.turnover;    
    }
    /**
     * <p>Pointcut for all requests on {@link tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount#turnover}.</p>
     */
    protected pointcut turnoverGetter(tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount aClass) :
    	get(* turnover) && target(aClass);
    
    /**
     * <p>Initializes the attribute turnover defined by the constraint
     * <code>context LoyaltyAccount
     *       def: turnover : Real = transactions.amount->sum()</code></p>
     */
    before(tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount aClass): turnoverGetter(aClass) {
        java.util.ArrayList<Float> result1;
        result1 = new java.util.ArrayList<Float>();
        
        /* Iterator Collect: Iterate through all elements and collect them. Elements which are collections are flattened. */
        for (tudresden.ocl20.pivot.examples.royalsandloyals.Transaction $implicitCollect0$ : aClass.transactions) {
            result1.add($implicitCollect0$.amount);
        }
    
        aClass.turnover = new Float(tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclCollections.sum(result1).floatValue());
    }
}