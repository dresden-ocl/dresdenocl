package org.dresdenocl.examples.royalandloyal.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect CustomerCard_DefAspect_getTotalPoints {

    /**
     * <p>Defines the method getTotalPoints(org.dresdenocl.examples.royalandloyal.Date d) defined by the constraint
     * <code>context CustomerCard
     *       def: getTotalPoints(d: Date) : Integer = transactions->select(date.isAfter(d)).points->sum()</code></p>
     */
    public Integer org.dresdenocl.examples.royalandloyal.CustomerCard.getTotalPoints(org.dresdenocl.examples.royalandloyal.Date d) {
    	/* Self variable probably used within the definition. */
    	org.dresdenocl.examples.royalandloyal.CustomerCard aClass = this;

        java.util.HashSet<org.dresdenocl.examples.royalandloyal.Transaction> result2;
        result2 = new java.util.HashSet<org.dresdenocl.examples.royalandloyal.Transaction>();

        /* Iterator Select: Select all elements which fulfill the condition. */
        for (org.dresdenocl.examples.royalandloyal.Transaction anElement1 : aClass.transactions) {
            if (anElement1.date.isAfter(d)) {
                result2.add(anElement1);
            }
            // no else
        }
        java.util.ArrayList<Integer> result1;
        result1 = new java.util.ArrayList<Integer>();

        /* Iterator Collect: Iterate through all elements and collect them. Elements which are collections are flattened. */
        for (org.dresdenocl.examples.royalandloyal.Transaction anElement2 : result2) {
            result1.add(anElement2.points);
        }

        return new Integer(org.dresdenocl.tools.codegen.ocl2java.types.util.OclCollections.sum(result1).intValue());
    }
}