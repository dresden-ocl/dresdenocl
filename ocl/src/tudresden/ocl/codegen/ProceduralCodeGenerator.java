/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Compiler                                                      *
 * Copyright (C) 1999, 2000 Frank Finger (frank@finger.org).         *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Technology, Dresden University Of Technology, Germany             *
 * (http://www-st.inf.tu-dresden.de).  It is understood that any     *
 * modification not identified as such is not covered by the         *
 * preceding statement.                                              *
 *                                                                   *
 * This work is free software; you can redistribute it and/or        *
 * modify it under the terms of the GNU Library General Public       *
 * License as published by the Free Software Foundation; either      *
 * version 2 of the License, or (at your option) any later version.  *
 *                                                                   *
 * This work is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *
 * Library General Public License for more details.                  *
 *                                                                   *
 * You should have received a copy of the GNU Library General Public *
 * License along with this library; if not, write to the             *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *
 * Boston, MA  02111-1307, USA.                                      *
 *                                                                   *
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other projects, please visit the web site:       *
 * http://www-st.inf.tu-dresden.de/ (Chair home page) or             *
 * http://www-st.inf.tu-dresden.de/ocl/ (project home page)          *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package tudresden.ocl.codegen;
import java.util.*;
import tudresden.ocl.OclTree;
import tudresden.ocl.parser.analysis.*;
import tudresden.ocl.parser.node.*;
import tudresden.ocl.NameCreator;
/** Subclasses should overwrite only inXxx/outXxx/caseXxx methods of
 *  nodes &quot;<i>below</i>&quot; AConstraintBody
 *  (below means nodes such that their production can be derived from the
 *  production <code>constraint_body</code>). The methods
 *  <code>inAConstraintBody</code> and <code>outAConstraintBody</code> may be
 *  overwritten, but not <code>caseAConstraintBody</code>. This is <i>not</i>
 *  enforced by declaring methods <code>final</code> to allow those who know
 *  what they are doing to change behaviour in these methods.
 *
 *  <p>After leaving
 *  <code>outAConstraintBody</code> subclasses must leave a
 *  variable name for the <code>AConstraintBody</code>'s Expression object in
 *  the map <code>ncm</code>. This name is then taken as the translation
 *  for the contraint body.
 *
 */
public abstract class ProceduralCodeGenerator extends DepthFirstAdapter implements CodeGenerator {
  protected ArrayList fragments;
  protected LinkedList fragmentStack;
  protected ProceduralCodeFragment topOfStack;
  //protected NodeCodeMap ncm;
  /** maps Nodes to variable names for nodes; can be accessed through
   *  <code>getVariable(Node n)</code> */
  private NodeNameMap nnm;
  protected OclTree tree;
  /** the Java code for the expression
   */
  protected StringBuffer code;
  /** Java code that is nessacary to evaluate @pre time expressions; will
   *  often be <code>null</code> (if the OCL expression contains no @pre)
   */
  protected StringBuffer preCode;
  boolean writeToPreCode=true;
  boolean writeToStandardCode=true;
  boolean preCodeIsValid=false;

  /** the list of variable determined in <CODE>preCode</CODE> and used in
   *  <CODE>code</CODE>
   */
  ArrayList preVariables=new ArrayList();
  /** maps pre variables to their types; the key set of this map must contain
   *  exactly those elements that are in preVariables
   */
  HashMap preVarTypes=new HashMap();

  int indent;
  boolean newLine=true;
  String constrainedType, constrainedOperation;
  /** Formal parameters of the constrainted operation if a operation is
   *  constrainted; <code>null</code> otherwise.<br>
   *  <code>parameters[i][j]</code> contains the i-th parameters name if i==0
   *  and the i-th parameters type if i==1
   */
  String[][] parameters;
  /** one of CodeFragment.PRE, CodeFragment.POST, CodeFragement.INV
   */
  int constraintKind;
  public ProceduralCodeGenerator() {
    fragments=new ArrayList();
    fragmentStack=new LinkedList();
    //ncm=new NodeCodeMap();
    nnm=new NodeNameMap();
  }
  /** @param operation is <code>null</code> for invariants
   */
  protected void beginNewFragment(String name, String type, String operation, int kind) {
    ProceduralCodeFragment pcf=new ProceduralCodeFragment(
      name, type, operation, kind, null
    );
    code=new StringBuffer();
    preCode=new StringBuffer();
    preCodeIsValid=false;
    fragments.add(pcf);
    fragmentStack.addFirst(pcf);
    topOfStack=pcf;
  }
  protected void endFragment(String resultVariable) {
    topOfStack.setResultVariable(resultVariable);
    if (preCodeIsValid) {
      StringBuffer prepend=new StringBuffer();
      StringBuffer transCode=new StringBuffer();
      Iterator iter=preVariables.iterator();
      while (iter.hasNext()) {
        String key=(String) iter.next();
        String val=(String) preVarTypes.get(key);
        transCode.append( getTransferCode(key, val) );
      }
      code.insert(0, prepend);
      ProceduralCodeFragment prep=new ProceduralCodeFragment(
        topOfStack.getName()+"Preparation",
        topOfStack.getType(),
        topOfStack.getOperation(),
        CodeFragment.PREPARATION,
        topOfStack.getResultVariable()
      );
      prep.setCode( preCode.toString() );
      fragments.add(prep);
      ProceduralCodeFragment transfer=new ProceduralCodeFragment(
        topOfStack.getName()+"Transfer",
        topOfStack.getType(),
        topOfStack.getOperation(),
        CodeFragment.TRANSFER,
        null
      );
      transfer.setCode( transCode.toString() );
      fragments.add(transfer);
    }
    topOfStack.setCode( code.toString() );
    fragmentStack.removeFirst();
    if (fragmentStack.isEmpty()) {
      topOfStack=null;
    } else {
      topOfStack=(ProceduralCodeFragment)fragmentStack.getFirst();
    }
  }
  /** get a variable name for a AST node; this method either returns the name stored in
   *  the map <code>nnm</code> (if the map contains a mapping for <code>node</code>) or
   *  creates a new name, stores it in <code>nnm</code> and returns it
   *
   *  @return the name of the variable that is the target code translation of
   *          <code>node</code>
   */
  protected String getVariable(Node n) {
    if (nnm.containsKey(n)) {
      return nnm.get(n);
    } else {
      String name=tree.getNameCreator().getUniqueName("Node");
      nnm.put(n, name);
      return name;
    }
  }
  /** set the variable for a node
   */
  protected void setVariable(Node n, String var) {
    if (nnm.containsKey(n)) {
      throw new RuntimeException(
        "tried to assign second variable to node \""+n+"\"; old variable: "+
         nnm.get(n)+", new variable:"+var);
    }
    nnm.put(n, var);
  }
  /** set the variable name representing node <CODE>from</CODE> to the variable
   *  name of node <CODE>to</CODE>
   */
  protected void reachThrough(Node from, Node to) {
    String varName=nnm.get(to);
    nnm.put(from, varName);
  }
  protected void appendCode(String s) {
    if (newLine) {
      for (int i=0; i<indent; i++) {
        if (writeToStandardCode) code.append(" ");
        if (writeToPreCode) preCode.append(" ");
      }
    }
    if (writeToStandardCode) {
      code.append(s);
    }
    if (writeToPreCode) {
      preCode.append(s);
    }
    newLine=s.endsWith("\n");
  }
  protected void increaseIndent(int i) {
    indent=indent+i;
  }
  protected void decreaseIndent(int i) {
    indent=indent-i;
  }

  // methods to separate constraint into fragments for each constraint_body
  public void inAClassifierContext(AClassifierContext cc) {
    constrainedType=cc.getTypeName().toString().trim();
    parameters=null;
  }

  public void inAOperationContext(AOperationContext oc) {
    constrainedType=oc.getTypeName().toString().trim();
    constrainedOperation=
      oc.getName().toString().trim() +
      "("+
      (oc.getFormalParameterList()==null ? "" : oc.getFormalParameterList().toString().trim() )+
      ")";
    if (oc.getFormalParameterList()==null) {
      parameters=new String[0][0];
    } else {
      AFormalParameterList fpl=(AFormalParameterList)oc.getFormalParameterList();
      ArrayList parameterNames=new ArrayList();
      ArrayList parameterTypes=new ArrayList();
      addParameter(
        parameterNames, parameterTypes,
        (AFormalParameter)fpl.getFormalParameter()
      );
      Iterator paramIter=fpl.getFormalParameterListTail().iterator();
      while (paramIter.hasNext()) {
        AFormalParameter fp=(AFormalParameter)paramIter.next();
        addParameter(parameterNames, parameterTypes, fp);
      }
      parameters=new String[parameterNames.size()][2];
      for (int i=0; i<parameters.length; i++) {
        parameters[i][0]=(String)parameterNames.get(i);
        parameters[i][1]=(String)parameterTypes.get(i);
      }
    }
  }

  private void addParameter(ArrayList parameterNames, ArrayList parameterTypes,
                            AFormalParameter fp) {
    parameterNames.add(fp.getName().toString().trim());
    parameterTypes.add(fp.getTypeName().toString().trim());
  }

  public final void caseAConstraintBody(AConstraintBody cb) {
    cb.getStereotype().apply(this); // determine constraintKind
    nnm.clear();
    String constraintName=cb.getName().toString().trim();
    beginNewFragment( constraintName, constrainedType, constrainedOperation, constraintKind );
    super.caseAConstraintBody(cb); // allows subclasses to work
    endFragment(getVariable(cb.getExpression()));
  }
  public void inAInvStereotype(AInvStereotype is) {
    constraintKind=CodeFragment.INV;
  }
  public void inAPreStereotype(APreStereotype is) {
    constraintKind=CodeFragment.PRE;
  }
  public void inAPostStereotype(APostStereotype is) {
    constraintKind=CodeFragment.POST;
  }

  // to be adapted in subclasses:

  protected abstract String getTransferCode(String var, String type);

  /** gives subclasses the opportunity to check if the OclTree requires some
   *  invariants, using OclTree's requireInvariant() method
   */
  protected abstract void requireTreeInvariants();

  // handle @pre code

  /** make the preCode become an own fragment
   */
  public void assurePreCode() {
    preCodeIsValid=true;
  }

  public void writeToPreCodeOnly() {
    writeToPreCode=true;
    writeToStandardCode=false;
  }

  public void writeToStandardCodeOnly() {
    writeToPreCode=false;
    writeToStandardCode=true;
  }

  public void writeToBothCodes() {
    writeToPreCode=true;
    writeToStandardCode=true;
  }

  public void addPreVariable(String var, String type) {
    preVariables.add(var);
    preVarTypes.put(var, type);
  }


  // CodeGenerator interface
  public CodeFragment[] getCode(OclTree tree) {
    fragments.clear();
    this.tree=tree;
    requireTreeInvariants();
    tree.apply(this);
    return (CodeFragment[]) fragments.toArray(new CodeFragment[fragments.size()]);
  }
} /* end class ProceduralCodeGenerator */
