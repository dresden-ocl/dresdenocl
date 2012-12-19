package org.dresdenocl.examples.royalsandloyals.ocl22javacode.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect LoyaltyProgram_PreAspect2 {

    /**
     * <p>Pointcut for all calls on {@link org.dresdenocl.examples.royalsandloyals.ocl22javacode.LoyaltyProgram#addService(org.dresdenocl.examples.royalsandloyals.ocl22javacode.ProgramPartner aPartner, org.dresdenocl.examples.royalsandloyals.ocl22javacode.ServiceLevel aLevel, org.dresdenocl.examples.royalsandloyals.ocl22javacode.Service aService)}.</p>
     */
    protected pointcut addServiceCaller(org.dresdenocl.examples.royalsandloyals.ocl22javacode.LoyaltyProgram aClass, org.dresdenocl.examples.royalsandloyals.ocl22javacode.ProgramPartner aPartner, org.dresdenocl.examples.royalsandloyals.ocl22javacode.ServiceLevel aLevel, org.dresdenocl.examples.royalsandloyals.ocl22javacode.Service aService):
    	call(* org.dresdenocl.examples.royalsandloyals.ocl22javacode.LoyaltyProgram.addService(org.dresdenocl.examples.royalsandloyals.ocl22javacode.ProgramPartner, org.dresdenocl.examples.royalsandloyals.ocl22javacode.ServiceLevel, org.dresdenocl.examples.royalsandloyals.ocl22javacode.Service))
    	&& target(aClass) && args(aPartner, aLevel, aService);

    /**
     * <p>Checks a precondition for the {@link LoyaltyProgram#addService(, org.dresdenocl.examples.royalsandloyals.ocl22javacode.ProgramPartner aPartner, org.dresdenocl.examples.royalsandloyals.ocl22javacode.ServiceLevel aLevel, org.dresdenocl.examples.royalsandloyals.ocl22javacode.Service aService)} defined by the constraint
     * <code>context LoyaltyProgram::addService(aPartner: org.dresdenocl.examples.royalsandloyals.ocl22javacode.ProgramPartner, aLevel: org.dresdenocl.examples.royalsandloyals.ocl22javacode.ServiceLevel, aService: org.dresdenocl.examples.royalsandloyals.ocl22javacode.Service) : 
     *       pre:  partners->includes(aPartner) and levels ->includes(aLevel)</code></p>
     */
    before(org.dresdenocl.examples.royalsandloyals.ocl22javacode.LoyaltyProgram aClass, org.dresdenocl.examples.royalsandloyals.ocl22javacode.ProgramPartner aPartner, org.dresdenocl.examples.royalsandloyals.ocl22javacode.ServiceLevel aLevel, org.dresdenocl.examples.royalsandloyals.ocl22javacode.Service aService): addServiceCaller(aClass, aPartner, aLevel, aService) {
        /* Disable this constraint for subclasses of LoyaltyProgram. */
        if (aClass.getClass().getCanonicalName().equals("org.dresdenocl.examples.royalsandloyals.ocl22javacode.LoyaltyProgram")) {
        if (!(org.dresdenocl.tools.codegen.ocl2java.types.util.OclCollections.includes(aClass.partners, aPartner) && org.dresdenocl.tools.codegen.ocl2java.types.util.OclCollections.includes(aClass.levels, aLevel))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'undefined' (pre:  partners->includes(aPartner) and levels ->includes(aLevel)) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}