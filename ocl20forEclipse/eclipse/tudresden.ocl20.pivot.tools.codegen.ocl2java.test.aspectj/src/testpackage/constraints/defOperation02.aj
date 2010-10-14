package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect defOperation02 {

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#defOperation02(Integer anInt)}.</p>
     */
    protected pointcut defOperation02Caller(testpackage.Class1 aClass, Integer anInt):
    	call(* testpackage.Class1.defOperation02(Integer))
    	&& target(aClass) && args(anInt);
    
    /**
     * <p>Defines the method defOperation02(Integer anInt) defined by the constraint
     * <code>context Class1
     *       def: defOperation02(anInt: Integer): Integer = 
    -anInt</code></p>
     */
    Integer around(testpackage.Class1 aClass, Integer anInt): defOperation02Caller(aClass, anInt) {
        return -(anInt);
    }
}