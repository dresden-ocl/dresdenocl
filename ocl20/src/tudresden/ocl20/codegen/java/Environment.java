/*

 * Environment.java

 *

 * Created on 5. Juni 2003, 18:00

 */



package tudresden.ocl20.codegen.java;



import java.util.*;

import com.sun.corba.se.spi.orb.Operation;

import tudresden.ocl20.core.jmi.ocl.expressions.OclExpression;
import tudresden.ocl20.core.jmi.ocl.expressions.OperationCallExp;
import tudresden.ocl20.core.jmi.ocl.expressions.VariableDeclaration;
import tudresden.ocl20.core.jmi.ocl.*;

import tudresden.ocl20.core.jmi.ocl.expressions.*;

import tudresden.ocl20.core.jmi.ocl.types.*;

import tudresden.ocl20.core.jmi.ocl.commonmodel.*;
import tudresden.ocl20.core.lib.OclException;



/**

 *

 * @author  Administrator

 *

 * manages identifier creation for a CodeGenerator

 */





class Environment {

    

    Map expressions = new HashMap();

    Map variables = new HashMap();

    Map types = new HashMap();

    

    private Map idNmbrs = new HashMap();

    

    private static final String IDPREFIX = "tudOcl20";

    

    private static final String FACT = IDPREFIX + "Fact";

    private static final String EXP = IDPREFIX + "Exp";

    private static final String VAR = IDPREFIX + "Var";

    private static final String TYPE = IDPREFIX + "Type";

    private static final String PARAM = IDPREFIX + "Param";

    private static final String ITER = IDPREFIX + "Iter";

    private static final String ACCU = IDPREFIX + "Accu";

    private static final String EVAL = IDPREFIX + "Eval";

        

    private String factoryId = null;

        

    /** Creates a new instance of Environment */

    public Environment() {

    }

    

    /**

     *Creates an unique Id with prefix prfx.

     */

    public String createId(String prfx){

        Integer idNmbr = (Integer) idNmbrs.get(prfx);

        if(idNmbr == null){

            idNmbr = new Integer(0);

        }

        String id = prfx + idNmbr;

        idNmbr = new Integer(idNmbr.intValue()+1);

        idNmbrs.put(prfx, idNmbr);

        return id;

    }

     

    public String getFactoryId() {

        return this.factoryId;

    }

    

    public String createFactoryId() {

        factoryId = createId(FACT);

        return factoryId;

    }

       

    public String getExpId(OclExpression exp){

        return (String) expressions.get(exp);

    }

    

    public String createExpId(OclExpression exp){

        String id = createId(EXP);

        expressions.put(exp, id);

        return id;

    }

    

    private void setExpId(OclExpression exp, String id){

        expressions.put(exp,id);

    }

    

    /**

     * sets the id of a variable expression to the one of 

     * the referred variable 

     */

    public void bind(VariableExp exp){

        setExpId(exp, getVarId(exp.getReferredVariable()));

    }
    
    /**
     * sets the id of a variable expression to the id of 
     * the type of the referred variable if @param staticOp
     * is true otherwise to the id of the referred variable
     * @author: Ronny Brandt     
     */
    public void bind(VariableExp exp, Boolean staticOp){
    	if (staticOp)
    		setExpId(exp, getTypeId(exp.getReferredVariable().getType()));
    	else
    		setExpId(exp, getVarId(exp.getReferredVariable()));
    }
    
    /**
     * sets the id of a let expression to the one of the 
     * referred inExpression
     * @author: Ronny Brandt     
     */
    public void bind(LetExp exp) {
    	setExpId(exp, getExpId(exp.getIn()));
    }

    /**
     * sets the id of a OperationCallExpression to the one of the 
     * VariableExpression (needed for atPre-Operation)
     * @author: Ronny Brandt     
     */
    public void bind(OperationCallExp exp) {
    	setExpId(exp, getExpId(exp.getSource()));
    }
    
    
    /**
     * selects method for recursive bind
     * @author: Ronny Brandt     
     */
    private void recursiveBind(OclExpression exp, VariableDeclaration vd) {
    	if (exp instanceof VariableExp)
    		recursiveBind((VariableExp)exp, vd);
    	else if (exp instanceof OperationCallExp)
    		recursiveBind((OperationCallExp)exp, vd);
    	else if (exp instanceof IterateExp)
    		recursiveBind((IterateExp)exp, vd);
    	else if (exp instanceof IteratorExp)
    		recursiveBind((IteratorExp)exp, vd);
    	else if (exp instanceof AttributeCallExp)
    		recursiveBind((PropertyCallExp)exp, vd);
    	else if (exp instanceof AssociationEndCallExp)
    		recursiveBind((PropertyCallExp)exp, vd);
    	else if (exp.getClass().toString().contains("Literal"))
    		;
    	else
    		System.err.println("No recursiveBind for " + exp.getClass());
    }
    
    /**
     * @author: Ronny Brandt     
     */
    private void recursiveBind(VariableExp exp, VariableDeclaration vd) {
    	if (exp.getReferredVariable().getNameA().equals(vd.getNameA()))
    		setExpId(exp, getVarId(vd));
    }
    
    /**
     * @author: Ronny Brandt     
     */
    private void recursiveBind(OperationCallExp exp, VariableDeclaration vd) {
    	recursiveBind(exp.getSource(), vd);
    	List arguments = exp.getArguments();
    	String argExpVarName = null;
    	for(int i=0; i<arguments.size(); i++){
            OclExpression argExp = (OclExpression) arguments.get(i);
    		recursiveBind(argExp, vd);
        }
    }
    
    /**
     * @author: Ronny Brandt     
     */
    private void recursiveBind(PropertyCallExp exp, VariableDeclaration vd) {
    	recursiveBind(exp.getSource(), vd);
    }
    
    /**
     * @author: Ronny Brandt     
     */
    private void recursiveBind(IteratorExp exp, VariableDeclaration vd) {
    	recursiveBind(exp.getSource(), vd);
    	recursiveBind(exp.getBody(), vd);
    }
    
    /**
     * @author: Ronny Brandt     
     */
    private void recursiveBind(IterateExp exp, VariableDeclaration vd) {
    	recursiveBind(exp.getSource(), vd);
    	recursiveBind(exp.getBody(), vd);
    }
    
    /**
     * sets the id of the variables in a body expression of an
     * IteratorExpression to the one of the VariableDeclaration 
     * of the IteratorVariable if the names are equal
     * (uses recursiveBind methods)
     * @author: Ronny Brandt     
     */
    public void bind(IteratorExp exp) {
    	OclExpression src = exp.getBody();
    	Iterator itVars = exp.getIterators().iterator();
    	while (itVars.hasNext())
    		recursiveBind(src, (VariableDeclaration)itVars.next());
    }
    
    /**
     * sets the id of the variables in a body expression of an
     * IterateExpression to the one of the VariableDeclaration 
     * of the IteratorVariable if the names are equal
     * (uses recursiveBind methods)
     * @author: Ronny Brandt     
     */
    public void bind(IterateExp exp) {
    	OclExpression src = exp.getBody();
    	Iterator itVars = exp.getIterators().iterator();
    	while (itVars.hasNext())
    		recursiveBind(src, (VariableDeclaration)itVars.next());
    }
    

    public String getVarId(VariableDeclaration vd){

        return (String) variables.get(vd);

    }

    

    public String createVarId(VariableDeclaration vd){

        String id = createId(VAR);

        variables.put(vd, id);

        return id;

    }

    

    public void setVarId(VariableDeclaration vd, String id){

        variables.put(vd,id);

    }

    

    /**

     * sets the id of a variable to the one of its init expression

     */

    public void bind(VariableDeclaration vd, OclExpression exp){

        setVarId(vd, getExpId(exp));

    }

    

    

    public String getTypeId(Classifier type){

        return (String) types.get(type);

    }

    

    public String createTypeId(Classifier type){

        String id = createId(TYPE);

        types.put(type, id);

        return id;

    }

    

    protected String createParamId(){

        String id = createId(PARAM);

        return id;

    }

    

    protected String createIteratorId(){

        String id = createId(ITER);

        return id;

    }

    

    protected String createAccuId(){

        String id = createId(ACCU);

        return id;

    }

    

    protected String createEvalId(){

        String id = createId(EVAL);

        return id;

    }

    

}

