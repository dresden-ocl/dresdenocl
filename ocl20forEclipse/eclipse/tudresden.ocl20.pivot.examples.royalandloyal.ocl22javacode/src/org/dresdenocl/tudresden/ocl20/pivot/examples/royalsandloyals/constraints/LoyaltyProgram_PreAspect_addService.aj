package org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect LoyaltyProgram_PreAspect_addService {

    /**
     * <p>Pointcut for all calls on {@link org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram#addService(org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ProgramPartner aPartner, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel aLevel, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Service aService)}.</p>
     */
    protected pointcut addServiceCaller(org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram aClass, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ProgramPartner aPartner, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel aLevel, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Service aService):
    	call(* org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram.addService(org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ProgramPartner, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Service))
    	&& target(aClass) && args(aPartner, aLevel, aService);

    /**
     * <p>Checks a precondition for the {@link LoyaltyProgram#addService(, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ProgramPartner aPartner, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel aLevel, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Service aService)} defined by the constraint
     * <code>context LoyaltyProgram::addService(aPartner: org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ProgramPartner, aLevel: org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel, aService: org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Service) : 
     *       pre: partners->includes(aPartner)</code></p>
     */
    before(org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram aClass, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ProgramPartner aPartner, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel aLevel, org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.Service aService): addServiceCaller(aClass, aPartner, aLevel, aService) {
        /* Disable this constraint for subclasses of LoyaltyProgram. */
        if (aClass.getClass().getCanonicalName().equals("org.dresdenocl.tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram")) {
        if (!tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclCollections.includes(aClass.partners, aPartner)) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'undefined' (pre: partners->includes(aPartner)) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}