package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_defOperation02 {

    /**
     * <p>Defines the method defOperation02(Integer anInt) defined by the constraint
     * <code>context Class1
     *       def: defOperation02(anInt: Integer): Integer =  -anInt</code></p>
     */
    public Integer testpackage.Class1.defOperation02(Integer anInt) {
        return -(anInt);
    }
}