package org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect LoyaltyProgram_PostAspect_addService {

    /**
     * <p>Pointcut for all calls on {@link org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram#addService(org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ProgramPartner aPartner, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel aLevel, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Service aService)}.</p>
     */
    protected pointcut addServiceCaller(org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram aClass, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ProgramPartner aPartner, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel aLevel, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Service aService):
    	call(* org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram.addService(org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ProgramPartner, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Service))
    	&& target(aClass) && args(aPartner, aLevel, aService);

    /**
     * <p>Checks a postcondition for the operation {@link LoyaltyProgram#addService(, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ProgramPartner aPartner, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel aLevel, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Service aService)} defined by the constraint
     * <code>context LoyaltyProgram::addService(aPartner: org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ProgramPartner, aLevel: org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel, aService: org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Service) : 
     *       post: partners.deliveredServices->includes(aService)</code></p>
     */
    void around(org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram aClass, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ProgramPartner aPartner, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel aLevel, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Service aService): addServiceCaller(aClass, aPartner, aLevel, aService) {
        /* Disable this constraint for subclasses of LoyaltyProgram. */
        if (aClass.getClass().getCanonicalName().equals("org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram")) {

        proceed(aClass, aPartner, aLevel, aService);

        java.util.ArrayList<org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Service> result1;
        result1 = new java.util.ArrayList<org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Service>();

        /* Iterator Collect: Iterate through all elements and collect them. Elements which are collections are flattened. */
        for (org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ProgramPartner anElement1 : aClass.partners) {
            result1.addAll(anElement1.deliveredServices);
        }

        if (!tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclCollections.includes(result1, aService)) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'undefined' (post: partners.deliveredServices->includes(aService)) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}