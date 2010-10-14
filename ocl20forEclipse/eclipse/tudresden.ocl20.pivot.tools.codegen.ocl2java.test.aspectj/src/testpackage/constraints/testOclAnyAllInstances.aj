package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect testOclAnyAllInstances {

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
     * <p>Pointcut for all calls on {@link testpackage.Class1#testOclAnyAllInstances()}.</p>
     */
    protected pointcut testOclAnyAllInstancesCaller(testpackage.Class1 aClass):
    	call(* testpackage.Class1.testOclAnyAllInstances())
    	&& target(aClass);
    
    /**
     * <p>Defines the method testOclAnyAllInstances() defined by the constraint
     * <code>context Class1
     *       def: testOclAnyAllInstances(): Set(Class1) =
    Class1.allInstances()</code></p>
     */
    java.util.Set<testpackage.Class1> around(testpackage.Class1 aClass): testOclAnyAllInstancesCaller(aClass) {
        return (new java.util.HashSet<testpackage.Class1>((java.util.Set<testpackage.Class1>) allInstances.get(testpackage.Class1.class.getCanonicalName()).keySet()));
    }
}