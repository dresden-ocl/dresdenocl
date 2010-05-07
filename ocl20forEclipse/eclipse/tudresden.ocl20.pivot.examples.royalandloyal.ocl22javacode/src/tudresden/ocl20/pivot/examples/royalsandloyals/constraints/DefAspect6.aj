package tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect6 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard extends tudresden.ocl20.pivot.examples.royalsandloyals.constraints.ExtendedCustomerCard;
    
    /**
     * <p>Pointcut for all calls on {@link tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard#getTotalPoints(tudresden.ocl20.pivot.examples.royalsandloyals.Date d)}.</p>
     */
    protected pointcut getTotalPointsCaller(tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard aClass, tudresden.ocl20.pivot.examples.royalsandloyals.Date d):
    	call(* tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard.getTotalPoints(tudresden.ocl20.pivot.examples.royalsandloyals.Date))
    	&& target(aClass) && args(d);
    
    /**
     * <p>Defines the method getTotalPoints(tudresden.ocl20.pivot.examples.royalsandloyals.Date d) defined by the constraint
     * <code>context CustomerCard
     *       def: getTotalPoints = transactions[] -> select (  | date[].isAfter( d[])).points[].sum()</code></p>
     */
    Integer around(tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard aClass, tudresden.ocl20.pivot.examples.royalsandloyals.Date d): getTotalPointsCaller(aClass, d) {
        java.util.HashSet<tudresden.ocl20.pivot.examples.royalsandloyals.Transaction> result2;
        result2 = new java.util.HashSet<tudresden.ocl20.pivot.examples.royalsandloyals.Transaction>();
        
        /* Iterator Select: Select all elements which fulfill the condition. */
        for (tudresden.ocl20.pivot.examples.royalsandloyals.Transaction anElement2 : aClass.transactions) {
            if (anElement2.date.isAfter(d)) {
                result2.add(anElement2);
            }
            // no else
        }
        java.util.ArrayList<Integer> result1;
        result1 = new java.util.ArrayList<Integer>();
        
        /* Iterator Collect: Iterate through all elements and collect them. Elements which are collections are flattened. */
        for (tudresden.ocl20.pivot.examples.royalsandloyals.Transaction anElement1 : result2) {
            result1.add(anElement1.points);
        }
    
        return tudresden.ocl20.pivot.ocl22java.types.util.OclCollections.sum(result1);
    }
}