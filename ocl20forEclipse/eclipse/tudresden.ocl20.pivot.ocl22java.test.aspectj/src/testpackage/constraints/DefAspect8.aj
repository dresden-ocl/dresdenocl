package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect DefAspect8 {

    protected static java.util.Map<String, java.util.Map> allInstances = new java.util.HashMap<String, java.util.Map>();

    /**
     * <p>Adds all instances of the class {@link testpackage.Class1} to the {@link java.util.Map} allInstances.</p>
     */
    after(testpackage.Class1 aClass) : execution(testpackage.Class1.new(..)) && this(aClass) {
    
        java.util.Map<testpackage.Class1, Object> instanceMap;
    
        instanceMap = (java.util.Map<testpackage.Class1, Object>) allInstances.get(aClass.getClass().getCanonicalName());
    
        if (instanceMap == null) {
            instanceMap = new java.util.WeakHashMap<testpackage.Class1, Object>();
        }
        // no else.
    
        instanceMap.put(aClass, null);
    
        allInstances.put(aClass.getClass().getCanonicalName(), instanceMap);
    }

    /* Declares a new super class containing the new attribute or method. */
    declare parents : testpackage.Class1 extends testpackage.constraints.ExtendedClass1;
    
    /**
     * <p>Pointcut for all calls on {@link testpackage.Class1#testOclAnyAllInstances(testpackage.Class1 source)}.</p>
     */
    protected pointcut testOclAnyAllInstancesCaller(testpackage.Class1 aClass, testpackage.Class1 source):
    	call(* testpackage.Class1.testOclAnyAllInstances(testpackage.Class1))
    	&& target(aClass) && args(source);
    
    /**
     * <p>Defines the method testOclAnyAllInstances(testpackage.Class1 source) defined by the constraint
     * <code>context Class1
     *       def: testOclAnyAllInstances = source[].allInstances()</code></p>
     */
    tudresden.ocl20.pivot.ocl2java.types.OclSet<testpackage.Class1> around(testpackage.Class1 aClass, testpackage.Class1 source): testOclAnyAllInstancesCaller(aClass, source) {
        return (new tudresden.ocl20.pivot.ocl2java.types.OclSet<testpackage.Class1>((java.util.Set<testpackage.Class1>) allInstances.get(testpackage.Class1.class.getCanonicalName()).keySet()));
    }
}