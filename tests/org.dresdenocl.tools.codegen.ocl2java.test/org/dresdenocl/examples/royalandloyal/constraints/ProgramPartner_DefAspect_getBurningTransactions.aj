package org.dresdenocl.examples.royalandloyal.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect ProgramPartner_DefAspect_getBurningTransactions {

    /**
     * <p>Defines the method getBurningTransactions() defined by the constraint
     * <code>context ProgramPartner
     *       def: getBurningTransactions(): Set(Transaction) =   self.deliveredServices.transaction->iterate( t         : Transaction; resultSet : Set(Transaction) = Set{} |          if (t.oclIsTypeOf( Burning )) then       resultSet->including( t )     else       resultSet     endif   )</code></p>
     */
    public java.util.Set<org.dresdenocl.examples.royalandloyal.Transaction> org.dresdenocl.examples.royalandloyal.ProgramPartner.getBurningTransactions() {
    	/* Self variable probably used within the definition. */
    	org.dresdenocl.examples.royalandloyal.ProgramPartner aClass = this;

        java.util.ArrayList<org.dresdenocl.examples.royalandloyal.Transaction> result1;
        result1 = new java.util.ArrayList<org.dresdenocl.examples.royalandloyal.Transaction>();

        /* Iterator Collect: Iterate through all elements and collect them. Elements which are collections are flattened. */
        for (org.dresdenocl.examples.royalandloyal.Service anElement1 : aClass.deliveredServices) {
            result1.add(anElement1.transaction);
        }
        java.util.HashSet collection1;
        collection1 = new java.util.HashSet();
        java.util.Set<org.dresdenocl.examples.royalandloyal.Transaction> result2;
        result2 = collection1;
        /* IterateExp: Iterate through all elements and perform an operation on them. */
        for (org.dresdenocl.examples.royalandloyal.Transaction anElement2 : result1) {
            java.util.Set<org.dresdenocl.examples.royalandloyal.Transaction> ifExpResult1;

            if (anElement2.getClass().getCanonicalName().equals(org.dresdenocl.examples.royalandloyal.Burning.class.getCanonicalName())) {
            ifExpResult1 = org.dresdenocl.tools.codegen.ocl2java.types.util.OclSets.including(result2, anElement2);
            } else {
            ifExpResult1 = result2;
            }
        result2 = ifExpResult1;
        }

        return result2;
    }
}