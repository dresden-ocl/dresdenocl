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

package tudresden.ocl.check.bootstrap;

import tudresden.ocl.*;
import tudresden.ocl.check.types.*;
import tudresden.ocl.codegen.*;
import java.io.*;
import java.util.*;
import tudresden.ocl.normalize.PreconditionViolatedException;

public class BootstrapGenerator {

  OclTree[] constraints;
  /** contains the constraints in text from (i.e. with line feeds); may
   *  be <code>null</code>
   */
  String[] constraintText;

  String javaPackage, javaClass, javaFile;

  JavaCodeGenerator javaCodeGenerator=new JavaCodeGenerator("node", "result");

  private HashMap nodeBuffers=new HashMap();

  protected static String imports="import tudresden.ocl.lib.*;\n"+
                        "import tudresden.ocl.parser.node.*;\n"+
                        "import tudresden.ocl.parser.analysis.*;\n\n";

  /** all constraints of the argument array should use the same
   *  instance of NameCreator
   *
   *  @see tudresden.ocl.OclTree#setNameCreator(NameCreator nc)
   */
  public BootstrapGenerator(OclTree[] constraints, String javaPackage, String javaFile) {
    this.constraints=constraints;
    this.javaPackage=javaPackage;
    this.javaFile=javaFile;
    this.javaClass=javaFile.substring(
      javaFile.lastIndexOf( File.separator )+1,
      javaFile.lastIndexOf(".")
    );
  }

  public BootstrapGenerator(String constraintFile, String javaPackage, String javaFile)
                                                                      throws IOException {
    this((OclTree[])null, javaPackage, javaFile);
    loadConstraints(constraintFile);
  }

  protected void loadConstraints(String fileName) throws IOException {
    File in=new File(fileName);
    BufferedReader br=new BufferedReader( new FileReader(in) );
    ArrayList constraintList=new ArrayList();
    String nextLine;
    String nextConstraint="";
    do {
      nextLine=br.readLine();
      if (nextLine==null || nextLine.trim().equals("")) {
        if (!nextConstraint.equals("")) {
          constraintList.add(nextConstraint);
        }
        nextConstraint="";
      } else {
        nextConstraint=nextConstraint+"\n"+nextLine;
      }
    } while (nextLine!=null);
    constraintText=(String[])constraintList.toArray(new String[constraintList.size()]);
    constraints=new OclTree[constraintText.length];

    String[] packageNames={"tudresden.ocl.parser.node"};
    ModelFacade mf=new SableReflectionFacade(
      packageNames,
      new DefaultReflectionAdapter(),
      new SableNameAdapter()
    );
    for (int i=0; i<constraintText.length; i++) {
      constraints[i]=OclTree.createTree(
        constraintText[i],
        mf
      );
    }
  }

  public void writeToFile(File output) throws IOException {
    FileWriter fw=new FileWriter(output);
    BufferedWriter bw=new BufferedWriter(fw);
    bw.write( getCheckingCode() );
    bw.flush();
    bw.close();
    fw.close();
  }

  public void writeToFile() throws IOException {
    writeToFile(new File(javaFile));
  }

  /** produce the complete Java code for all constraints
   */
  public String getCheckingCode() {
    nodeBuffers.clear();

    StringBuffer headerCode =    new StringBuffer();
    StringBuffer checkingCode =  new StringBuffer();

    ArrayList codeFragments=transformToFragments(constraints);


    headerCode.append("package "+javaPackage+";\n\n");
    headerCode.append(imports);
    headerCode.append("public class "+javaClass+" extends DepthFirstAdapter {\n\n");
    headerCode.append("  public boolean reportProgress=false;\n\n");

    Iterator iter=codeFragments.iterator();
    while (iter.hasNext()) {
      AnnotatedCodeFragment next=(AnnotatedCodeFragment) iter.next();
      if (next.fragment.getKind()!=CodeFragment.INV) {
        throw new RuntimeException("unexpected fragment kind");
      }
      StringBuffer traversalCode=getNodeBuffer(next.fragment.getConstrainedType());
      traversalCode.append("    reportProgress(\"starting "
                            +next.proposedMethodName+"\");\n");
      traversalCode.append("    "+next.proposedMethodName+"(node);\n");

      checkingCode.append( "protected void " );
      checkingCode.append( next.proposedMethodName );
      checkingCode.append( "(final "+next.fragment.getConstrainedType()+" node) {\n");
      checkingCode.append( "  /* "+next.annotation+" */\n" );
      checkingCode.append( next.fragment.getCode() );
      checkingCode.append( "  if ( !"+next.fragment.getResultVariable()+".isTrue() ) {\n");
      checkingCode.append( "    throw new GeneratedTestFailedException(\n");
      checkingCode.append( "      \"generated test failed:\\n"+next.annotation+"\\n\"+\n" );
      checkingCode.append( "      \"violating node:\\n\"+node\n" );
      checkingCode.append( "    );\n" );
      checkingCode.append( "  }\n" );
      checkingCode.append( "}\n\n" );
    }
    StringBuffer output=new StringBuffer();
    output.append(headerCode);
    iter=nodeBuffers.keySet().iterator();
    while (iter.hasNext()) {
      Object node=iter.next();
      output.append("  public void in"+node+"("+node+" node) {\n");
      output.append(nodeBuffers.get(node));
      output.append("  }\n\n");
    }
    output.append("  protected void reportProgress(String msg) {\n");
    output.append("    if (reportProgress) System.out.println(msg);\n");
    output.append("  }\n\n");
    output.append(checkingCode);
    output.append("}\n\n");
    output.append("class GeneratedTestFailedException extends tudresden.ocl.OclException {\n\n");
    output.append("  GeneratedTestFailedException(String msg) {\n");
    output.append("    super(msg);\n");
    output.append("  }\n");
    output.append("}\n");
    return output.toString();
  }

  protected StringBuffer getNodeBuffer(String nodeType) {
    StringBuffer result=(StringBuffer)nodeBuffers.get(nodeType);
    if (result==null) {
      result=new StringBuffer();
      nodeBuffers.put(nodeType, result);
    }
    return result;
  }

  protected ArrayList transformToFragments(OclTree[] constraints) {
    ArrayList codeFragments=new ArrayList();

    for (int i=0; i<constraints.length; i++) {
      try {
        constraints[i].applyDefaultNormalizations();
      }
      catch (Exception e) {
        throw new PreconditionViolatedException(
          "cannot normalize: "+constraints[i].getExpression()
        );
      }
    }

    for (int i=0; i<constraints.length; i++) {
      CodeFragment[] frags=javaCodeGenerator.getCode(constraints[i]);
      for (int j=0; j<frags.length; j++) {
        AnnotatedCodeFragment acf=new AnnotatedCodeFragment();
        acf.fragment=frags[j];
        acf.annotation=constraints[i].getExpression();
        acf.proposedMethodName=constraints[0].getNameCreator().getUniqueName("CheckMethod");
        codeFragments.add(acf);
      }
    }

    return codeFragments;
  }

  public static void main(String[] args) {
    String constraintFile, javaPackageName, targetFile;
    PrintStream output=System.err;
    if (args.length<3 || args.length>4) {
      reportParameters(output);
      return;
    }
    int indexConstraintFile;
    if (args.length==3) {
      indexConstraintFile=0;
    } else {
      indexConstraintFile=1;
      if (args[0].equals("-o")) {
        output=System.out;
      } else {
        reportParameters(output);
        return;
      }
    }
    try {
      BootstrapGenerator bcg=new BootstrapGenerator(
        args[0+indexConstraintFile],
        args[1+indexConstraintFile],
        args[2+indexConstraintFile]
      );
      bcg.writeToFile();
    }
    catch (Exception e) {
      e.printStackTrace();
      reportParameters(output);
    }
  }

  protected static void reportParameters(PrintStream output) {
    output.println(
      "usage: java tudresden.ocl.check.bootstrap.BootstrapGenerator "+
      "[-o] <constraint_file> <java_package_name> <target_file>"
    );
    output.println("-o redirects error messages to standard out");
    output.println(
      "The name of the generated Java class is extracted from the target file name."
    );
  }

}


class AnnotatedCodeFragment {

  CodeFragment fragment;
  String annotation;
  String proposedMethodName;
}
