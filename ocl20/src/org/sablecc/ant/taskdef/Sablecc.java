/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of AntTask.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of AntTask.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.ant.taskdef;

/**/
import org.apache.tools.ant.*;
import org.apache.tools.ant.taskdefs.MatchingTask;
import java.io.*;
import java.util.*;

/**
 * Parser generator task based on SableCC parser generator.
 *
 * This task requires the following arguments:
 * <ul>
 * <li>src</li>
 * <li>outputdirectory</li>
 * </ul>
 * and is based on MatchingTask which means includes and exludes 
 * are done in a standard fashion.
 *
 * @author Mariusz Nowostawski [marni] <a href="mailto:mariusz@rakiura.org">mariusz@rakiura.org</a>
 */
public class Sablecc extends MatchingTask {

  private File srcDir;
  private File destDir;

  /**
     Sets the grammars directory name. */
  public void setSrc(String d) {
    srcDir = project.resolveFile(d);
  }
  
  /**
     Sets the destinion where the generated files should be placed. */
  public void setOutputDirectory(String d) {
    destDir = project.resolveFile(d);
  }
  
  public void execute() throws BuildException {

    // first off, make sure that we've got a srcdir and destdir
    if (srcDir == null || destDir == null ) {
      throw new BuildException("srcDir and destDir attributes must be set!");
    }

    DirectoryScanner ds = getDirectoryScanner(srcDir);
    String[] files = ds.getIncludedFiles();

    // compile the source files
    if (files.length > 0) {
      project.log("Compiling with SableCC " + files.length + " source grammar files to " + destDir, project.MSG_INFO);
      doSableccCompile(files);
    }
  }

  /**
   * Peforms a generation of parsers using the SableCC Compiler Compiler.
   */
  private void doSableccCompile(String[] files) {
    project.log("Using now SableCC parser generator...", project.MSG_VERBOSE);
        
    for (int i = 0; i < files.length; i++) {
      project.log("SableCC processing grammar: "+files[i], project.MSG_VERBOSE);
      try{
        org.sablecc.sablecc.SableCC.processGrammar(srcDir+System.getProperty("file.separator")+files[i],
                                                 destDir.getAbsolutePath());   
      }catch(Exception e){
        project.log("SableCC failed.\n"+e.getMessage(), project.MSG_ERR);        
        StringWriter wr  = new StringWriter();
        e.printStackTrace(new PrintWriter(wr));
        project.log("Exception thrown was:\n"+wr.toString(), project.MSG_VERBOSE);
        return;
      }catch(Throwable t){
        project.log("SableCC failed.\n"+t.getMessage(), project.MSG_ERR);
        StringWriter wr  = new StringWriter();
        t.printStackTrace(new PrintWriter(wr));
        project.log("Exception thrown was:\n"+wr.toString(), project.MSG_VERBOSE);
      }
    }
    project.log("Using SableCC parser generator finished succesfully!", project.MSG_VERBOSE);
  }

}
