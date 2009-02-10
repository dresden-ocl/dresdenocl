package tudresden.ocl20.pivot.examples.royalsandloyals.constraints;

@Generated
public privileged aspect DeriveAspect1 {

    /**
     * <p>Pointcut for all requests on {@link tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard#printedName}.</p>
     */
    protected pointcut printedNameGetter(tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard aClass) :
    	get(* printedName) && this(aClass);
    
    /**
     * <p>Derives the attribute printedName defined by the constraint
     * <code>context CustomerCard
     *       def: printedName = owner[].title[].concat( ' ').concat( owner[].name[])</code></p>
     */
    before(tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard aClass): printedNameGetter(aClass) {
        aClass.printedName = aClass.owner.title.concat(" ").concat(aClass.owner.name);
    }
}