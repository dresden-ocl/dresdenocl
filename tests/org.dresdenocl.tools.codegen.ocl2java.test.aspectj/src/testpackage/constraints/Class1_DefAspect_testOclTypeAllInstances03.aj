package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOclTypeAllInstances03 {

    /**
     * <p>Defines the method testOclTypeAllInstances03() defined by the constraint
     * <code>context Class1
     *       def: testOclTypeAllInstances03(): Set = OclInvalid.allInstances()</code></p>
     */
    public java.util.Set testpackage.Class1.testOclTypeAllInstances03() {
        return (java.util.Set<org.dresdenocl.tools.codegen.ocl2java.types.OclInvalidException>) org.dresdenocl.tools.codegen.ocl2java.types.OclInvalidException.getInvalidLiteral();
    }
}