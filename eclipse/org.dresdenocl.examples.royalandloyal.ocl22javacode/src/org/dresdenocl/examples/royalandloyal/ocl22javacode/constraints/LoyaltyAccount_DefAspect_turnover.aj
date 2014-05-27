package org.dresdenocl.examples.royalandloyal.ocl22javacode.constraints;

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
    public Float org.dresdenocl.examples.royalandloyal.ocl22javacode.LoyaltyAccount.turnover;

    /**
     * <p>Getter method for the attribute turnover.</p>
     */
    public Float org.dresdenocl.examples.royalandloyal.ocl22javacode.LoyaltyAccount.getTurnover() {
        return this.turnover;    
    }
    /**
     * <p>Pointcut for all requests on {@link org.dresdenocl.examples.royalandloyal.ocl22javacode.LoyaltyAccount#turnover}.</p>
     */
    protected pointcut turnoverGetter(org.dresdenocl.examples.royalandloyal.ocl22javacode.LoyaltyAccount aClass) :
    	get(* turnover) && target(aClass);

    /**
     * <p>Initializes the attribute turnover defined by the constraint
     * <code>context LoyaltyAccount
     *       def: turnover : Real = transactions.amount->sum()</code></p>
     */
    before(org.dresdenocl.examples.royalandloyal.ocl22javacode.LoyaltyAccount aClass): turnoverGetter(aClass) {
        java.util.ArrayList<Float> result1;
        result1 = new java.util.ArrayList<Float>();

        /* Iterator Collect: Iterate through all elements and collect them. Elements which are collections are flattened. */
        for (org.dresdenocl.examples.royalandloyal.ocl22javacode.Transaction anElement1 : aClass.transactions) {
            result1.add(anElement1.amount);
        }

        aClass.turnover = new Float(org.dresdenocl.tools.codegen.ocl2java.types.util.OclCollections.sum(result1).floatValue());
    }
}