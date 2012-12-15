package org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect ProgramPartner_InvAspect_totalPoints {

    /**
     * <p>Pointcut for all calls on {@link org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ProgramPartner#checkInvariants()}.</p>
     */
    protected pointcut checkInvariantsCaller(org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ProgramPartner aClass):
    	call(void checkInvariants())
    	&& target(aClass);

    /**
     * <p><code>Checks an invariant on the class ProgramPartner defined by the constraint
     * <code>context ProgramPartner
     *       inv totalPoints: deliveredServices.transaction.points->sum() < 10000</code></p>
     */
    after(org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ProgramPartner aClass) : checkInvariantsCaller(aClass) {
        /* Disable this constraint for subclasses of ProgramPartner. */
        if (aClass.getClass().getCanonicalName().equals("org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ProgramPartner")) {
        java.util.ArrayList<org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Transaction> result2;
        result2 = new java.util.ArrayList<org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Transaction>();

        /* Iterator Collect: Iterate through all elements and collect them. Elements which are collections are flattened. */
        for (org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Service anElement1 : aClass.deliveredServices) {
            result2.add(anElement1.transaction);
        }
        java.util.ArrayList<Integer> result1;
        result1 = new java.util.ArrayList<Integer>();

        /* Iterator Collect: Iterate through all elements and collect them. Elements which are collections are flattened. */
        for (org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Transaction anElement2 : result2) {
            result1.add(anElement2.points);
        }

        if (!(new Integer(tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclCollections.sum(result1).intValue()) < new Integer(10000))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'totalPoints' (inv totalPoints: deliveredServices.transaction.points->sum() < 10000) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}