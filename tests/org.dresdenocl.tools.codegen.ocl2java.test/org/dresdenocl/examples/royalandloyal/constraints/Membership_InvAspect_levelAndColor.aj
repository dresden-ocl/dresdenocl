package org.dresdenocl.examples.royalandloyal.constraints;

/**
 * <p>Generated Aspect to enforce OCL constraint.</p>
 *
 * @author OCL22Java of Dresden OCL2 for Eclipse
 * @Generated
 */
public privileged aspect Membership_InvAspect_levelAndColor {

    declare parents: org.dresdenocl.examples.royalandloyal.Membership implements org.dresdenocl.tools.codegen.ocl2java.types.OclCheckable;

    public void org.dresdenocl.examples.royalandloyal.Membership.checkInvariants() {
        /* Remains empty. Is only filled with behavior by advice(s). */
    }

    /**
     * <p>Pointcut for all calls on {@link org.dresdenocl.examples.royalandloyal.Membership#checkInvariants()}.</p>
     */
    protected pointcut checkInvariantsCaller(org.dresdenocl.examples.royalandloyal.Membership aClass):
    	call(void checkInvariants())
    	&& target(aClass);

    /**
     * <p><code>Checks an invariant on the class Membership defined by the constraint
     * <code>context Membership
     *       inv levelAndColor:   (currentLevel.name = 'Silver' implies card.color = Color::silver)   and   (currentLevel.name = 'Gold' implies card.color = Color::gold)</code></p>
     */
    after(org.dresdenocl.examples.royalandloyal.Membership aClass) : checkInvariantsCaller(aClass) {
        /* Disable this constraint for subclasses of Membership. */
        if (aClass.getClass().getCanonicalName().equals("org.dresdenocl.examples.royalandloyal.Membership")) {
        if (!((!aClass.currentLevel.name.equals("Silver") || aClass.card.color.equals(org.dresdenocl.examples.royalandloyal.Color.silver)) && (!aClass.currentLevel.name.equals("Gold") || aClass.card.color.equals(org.dresdenocl.examples.royalandloyal.Color.gold)))) {
        	// TODO Auto-generated code executed when constraint is violated.
        	String msg = "Error: Constraint 'levelAndColor' (inv levelAndColor:   (currentLevel.name = 'Silver' implies card.color = Color::silver)   and   (currentLevel.name = 'Gold' implies card.color = Color::gold)) was violated for Object " + aClass.toString() + ".";
        	throw new RuntimeException(msg);
        }
        // no else.
        }
        // no else.
    }
}