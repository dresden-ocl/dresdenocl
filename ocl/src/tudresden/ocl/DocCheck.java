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
package tudresden.ocl;

import java.io.*;
import java.util.*;

public class DocCheck {

  boolean verbose;
  boolean confirm;

  /** take a list of documents as parameter and check OCL constraints contained in them
   *  options -v  verbose mode on
   *          -q  verbose mode off
   *          -c  confirm parser errors
   *          -r  continue after parser error
   */
  public static void main(String[] args) {
    if (args.length==0) {
      System.out.println("give latex documents as parameter");
    }
    else {
      DocCheck dc=new DocCheck();
      dc.checkDocumentList(args);
    }
  }

  protected void checkDocumentList(String[] args) {
    for (int i=0; i<args.length; i++) {
      if (args[i].equals("-v")) {
        verbose=true;
      }
      if (args[i].equals("-c")) {
        confirm=true;
      }
      if (args[i].equals("-q")) {
        verbose=false;
      }
      if (args[i].equals("-r")) {
        confirm=false;
      }
      File f=new File(args[i]);
      if (f.exists()) {
        try {
          report("checking "+f+" ...");
          checkDocument(f);
        } catch (IOException e) {
          report("IOException while reading "+f);
          report(e.getMessage());
        }
      } else {
        report("file "+args[i]+" not found");
      }
    }
  }

  protected void checkDocument(File f) throws IOException {
    if (f.getName().toLowerCase().endsWith(".tex")) {
      checkLatex(f);
    } else {
      report("unknown document type: "+f);
    }
  }

  protected void checkLatex(File f) throws IOException {
    String text=readFile(f);
    int start=0;
    while (start>=0) {
      start=text.indexOf("% OCL BEGIN", start);
      if (start>=0) {
        int end=text.indexOf("% OCL END", start);
        if (end>=0) {
          String oclExpr=text.substring(start+"% OCL BEGIN".length(), end).trim();
          if (oclExpr.startsWith("\\begin{verbatim}")) {
            oclExpr=oclExpr.substring("\\begin{verbatim}".length());
          }
          if (oclExpr.endsWith("\\end{verbatim}")) {
            oclExpr=oclExpr.substring(0, oclExpr.indexOf("\\end{verbatim}"));
          }
          checkConstraint(oclExpr.trim());
        } else {
          report("missing % OCL END");
        }
        start++;
      }
    }
  }

  protected static tudresden.ocl.check.types.ModelFacade getDefaultModelFacade() {
    if (System.getProperty("xmi_file")!=null) {
      try {
        return tudresden.ocl.check.types.xmifacade.XmiParser.getModel(
          System.getProperty("xmi_file"),
          "System.getProperty(\"xmi_file\")"
        );
      }
      catch (org.xml.sax.SAXException e) {}
      catch (java.io.IOException e) {}
    }
    return new tudresden.ocl.check.types.testfacade.TestModelFacade();
  }

  /** consider using the more elaborate version
   *  <CODE>createTree(String, ModelFacade)</CODE> except for test reasons;
   *  this method returns
   *  an <code>OclTree</code> that uses a
   *  default model facade
   *  to query type information
   */
  public static OclTree createTree(String oclExpression) throws
               tudresden.ocl.parser.OclParserException,
               IOException {
    return OclTree.createTree(
      oclExpression,
      getDefaultModelFacade()
    );
  }

  protected void checkConstraint(String constraint) {
    try {
      if (verbose) {
        report("----  checking: \n"+constraint+"\n----");
      }
      OclTree tree=createTree(constraint);
      if (verbose) {
        report("----  OK  ----");
      }
    }
    catch (Exception e) {
      report ("failed to parse:\n"+constraint);
      report (e.getClass().getName()+": "+e.getMessage());
      report ("press enter to continue");
      if (confirm) {
        try {
          System.in.read();
        }
        catch (IOException whocares) {}
      }
    }
  }

  protected void report(String msg) {
    System.out.println(msg);
  }

  protected String readFile(File f) throws IOException {
    FileReader fr=new FileReader(f);
    BufferedReader br=new BufferedReader(fr);
    StringBuffer sbuf=new StringBuffer( (int)f.length() );
    String next;
    do {
      next=br.readLine();
      if (next!=null) sbuf.append(next+"\n");
    } while (next!=null);
    return sbuf.toString();
  }
}

