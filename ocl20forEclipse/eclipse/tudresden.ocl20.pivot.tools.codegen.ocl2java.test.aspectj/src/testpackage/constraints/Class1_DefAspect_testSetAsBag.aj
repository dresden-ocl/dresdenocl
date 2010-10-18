package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testSetAsBag {

    /**
     * <p>Defines the method testSetAsBag(java.util.Set<Object> source) defined by the constraint
     * <code>context Class1
     *       def: testSetAsBag(source: Set(OclAny)): 
      Bag(OclAny) =
    source ->asBag()</code></p>
     */
    public java.util.List<Object> testpackage.Class1.testSetAsBag(java.util.Set<Object> source) {
        return tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSets.asBag(source);
    }
}