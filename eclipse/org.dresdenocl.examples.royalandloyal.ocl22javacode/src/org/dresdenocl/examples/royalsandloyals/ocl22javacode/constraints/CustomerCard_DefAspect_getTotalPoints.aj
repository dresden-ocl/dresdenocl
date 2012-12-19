package org.dresdenocl.examples.royalsandloyals.ocl22javacode.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect CustomerCard_DefAspect_getTotalPoints {

    /**
     * <p>Defines the method getTotalPoints(org.dresdenocl.examples.royalsandloyals.ocl22javacode.Date d) defined by the constraint
     * <code>context CustomerCard
     *       def: getTotalPoints(d: Date) : Integer = transactions->select(date.isAfter(d)).points->sum()</code></p>
     */
    public Integer org.dresdenocl.examples.royalsandloyals.ocl22javacode.CustomerCard.getTotalPoints(org.dresdenocl.examples.royalsandloyals.ocl22javacode.Date d) {
    	/* Self variable probably used within the definition. */
    	org.dresdenocl.examples.royalsandloyals.ocl22javacode.CustomerCard aClass = this;
    	
        java.util.HashSet<org.dresdenocl.examples.royalsandloyals.ocl22javacode.Transaction> result2;
        result2 = new java.util.HashSet<org.dresdenocl.examples.royalsandloyals.ocl22javacode.Transaction>();

        /* Iterator Select: Select all elements which fulfill the condition. */
        for (org.dresdenocl.examples.royalsandloyals.ocl22javacode.Transaction anElement1 : aClass.transactions) {
            if (anElement1.date.isAfter(d)) {
                result2.add(anElement1);
            }
            // no else
        }
        java.util.ArrayList<Integer> result1;
        result1 = new java.util.ArrayList<Integer>();

        /* Iterator Collect: Iterate through all elements and collect them. Elements which are collections are flattened. */
        for (org.dresdenocl.examples.royalsandloyals.ocl22javacode.Transaction anElement2 : result2) {
            result1.add(anElement2.points);
        }

        return new Integer(org.dresdenocl.tools.codegen.ocl2java.types.util.OclCollections.sum(result1).intValue());
    }
}