package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testStringCharacters {

    /**
     * <p>Defines the method testStringCharacters(String source) defined by the constraint
     * <code>context Class1
     *       def: testStringCharacters(source: String): Sequence(String) =
    source.characters()</code></p>
     */
    public java.util.List<String> testpackage.Class1.testStringCharacters(String source) {
        java.util.List<String> result1;
        result1 = new java.util.ArrayList<String>();
        
        /* Compute the result of a characters operation. */
        for (String anElement1 : source.split("")) {
            result1.add(anElement1);
        }
        
        /* Remove the first element ''. */
        result1.remove(tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSequences.first(result1));
    
        return result1;
    }
}