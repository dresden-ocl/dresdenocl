package org.dresdenocl.examples.royalsandloyals.ocl22javacode.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Membership_InvAspect_levelAndColor {

    /**
     * <p>Pointcut for all calls on {@link org.dresdenocl.examples.royalsandloyals.ocl22javacode.Membership#checkInvariants()}.</p>
     */
    protected pointcut checkInvariantsCaller(org.dresdenocl.examples.royalsandloyals.ocl22javacode.Membership aClass):
    	call(void checkInvariants())
    	&& target(aClass);

    /**
     * <p><code>Checks an invariant on the class Membership defined by the constraint
     * <code>context Membership
     *       inv levelAndColor:   (currentLevel.name = 'Silver' implies card.color = Color::silver)   and   (currentLevel.name = 'Gold' implies card.color = Color::gold)</code></p>
     */
    after(org.dresdenocl.examples.royalsandloyals.ocl22javacode.Membership aClass) : checkInvariantsCaller(aClass) {
        /* Disable this constraint for subclasses of Membership. */
        if (aClass.getClass().getCanonicalName().equals("org.dresdenocl.examples.royalsandloyals.ocl22javacode.Membership")) {
        if (!((!aClass.currentLevel.name.equals("Silver") || aClass.card.color.equals(org.dresdenocl.examples.royalsandloyals.ocl22javacode.Color.silver)) && (!aClass.currentLevel.name.equals("Gold") || aClass.card.color.equals(org.dresdenocl.examples.royalsandloyals.ocl22javacode.Color.gold)))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'levelAndColor' (inv levelAndColor:   (currentLevel.name = 'Silver' implies card.color = Color::silver)   and   (currentLevel.name = 'Gold' implies card.color = Color::gold)) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}