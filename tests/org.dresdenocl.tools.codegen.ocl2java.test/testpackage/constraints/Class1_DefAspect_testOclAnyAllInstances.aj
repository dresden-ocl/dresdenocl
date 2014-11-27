package testpackage.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Class1_DefAspect_testOclAnyAllInstances {

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

    /**
     * <p>Defines the method testOclAnyAllInstances() defined by the constraint
     * <code>context Class1
     *       def: testOclAnyAllInstances(): Set(Class1) = Class1.allInstances()</code></p>
     */
    public java.util.Set<testpackage.Class1> testpackage.Class1.testOclAnyAllInstances() {
        return (new java.util.HashSet<testpackage.Class1>((java.util.Set<testpackage.Class1>) allInstances.get(testpackage.Class1.class.getCanonicalName()).keySet()));
    }
}