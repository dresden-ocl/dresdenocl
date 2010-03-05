package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect15 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testStringCharacters(String source)}.</p>
     */
    protected pointcut testStringCharactersCaller(testpackage.Class1 aClass, String source):
    	call(* testpackage.Class1.testStringCharacters(String))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testStringCharacters(String source) defined by the constraint
     * <code>context Class1
     *       def: testStringCharacters = source[].characters()</code></p>
     */
    tudresden.ocl20.pivot.ocl2java.types.OclSequence<String> around(testpackage.Class1 aClass, String source): testStringCharactersCaller(aClass, source) {
        tudresden.ocl20.pivot.ocl2java.types.OclSequence<String> result1;
        result1 = new tudresden.ocl20.pivot.ocl2java.types.OclSequence<String>();
        
        /* Compute the result of a caharacters operation. */
        for (String anElement1 : source.split("")) {
            result1.add(anElement1);
        }
        
        /* Remove the first element ''. */
        result1.remove(result1.first());
    
        return result1;
    }
}