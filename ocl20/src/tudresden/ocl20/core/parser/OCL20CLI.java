/*
 * OCL20GUI.java
 *
 * Created on 2005-07-05
 *
 * Copyright (c) 2004, 2005 Ansgar Konermann
 * Copyright (c) 2005 Achim D. Brucker
 *
 * This file is part of the OCL2.0 parser and compiler libraries
 * created at Technische Universitaet Dresden (TUD), Germany.
 * Visit http://dresden-ocl.sourceforge.net/ for details.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA  02111-1307  USA
 *
 */

package tudresden.ocl20.parser;

import tudresden.ocl20.parser.util.*;

import tudresden.ocl20.parser.sablecc.analysis.*;
import tudresden.ocl20.parser.sablecc.lexer.Lexer;
import tudresden.ocl20.parser.sablecc.lexer.LexerException;
import tudresden.ocl20.parser.sablecc.parser.Parser;
import tudresden.ocl20.parser.sablecc.parser.ParserException;
import tudresden.ocl20.parser.sablecc.node.*;

import tudresden.ocl20.parser.util.TextualCSTBuilder;
import tudresden.ocl20.parser.astgen.LAttrAstGenerator;
import tudresden.ocl20.parser.astgen.Heritage;
import tudresden.ocl20.parser.astgen.NodeFactory;

import tudresden.ocl20.*;

import tudresden.ocl20.jmi.ocl.*;
import tudresden.ocl20.jmi.ocl.types.*;
import tudresden.ocl20.jmi.ocl.expressions.*;
import tudresden.ocl20.jmi.ocl.commonmodel.*;

import javax.jmi.reflect.*;


import java.io.*;
import java.util.*;


/**
 *
 * Simple GUI for the experimental OCL20 parser.
 * 
 * @author Achim D. Brucker
 * @author Ansgar Konermann
 */
public class OCL20CLI {
    private Start cst = null;
    private OclModel oclModel = null;
    private boolean verbose = false;

    private void setOclModel(OclModel model, String modelXmiPath) {
        this.oclModel = model;
    }

    private OclModel getOclModel() {
        return this.oclModel;
    }


    private boolean generateAst() {
	boolean retval = false;
        if ( cst == null ) {
            System.err.println("Please parse a constraint first.");
            return retval;
        }
        if ( oclModel == null ) {
            System.err.println("Please load a model first.");
            return retval;
        }

        LAttrAstGenerator astgen = new LAttrAstGenerator(oclModel);
        Heritage hrtg = new Heritage();

        try {
            oclModel.beginTrans(true);
            cst.apply(astgen, hrtg);
            oclModel.endTrans(false);
            System.err.println("Attribute evaluation (OCL type-check) completed successfully.");
	    retval=true;
        } catch (AttrEvalException evex) {
            oclModel.endTrans(true);
            String message = evex.getMessage();
            String position = null;
            Token tk = astgen.getCurrentToken();
            if ( tk != null ) {
                position = "" + tk.getLine() + ":" + tk.getPos();
            } else {
                position = "<unknown>";
            }
            System.err.println("===[ Contextual Analysis Error at " + position + "]===");
            System.err.println(message);
            StringWriter swriter = new StringWriter(1024);
            PrintWriter pwriter = new PrintWriter(swriter);
            evex.printStackTrace(pwriter);
            pwriter.flush();
            swriter.flush();
            System.err.println(swriter.toString());
            Throwable cause = evex.getCause();
            if ( cause != null ) {
                System.err.println("Cause: " + cause.getMessage());
                swriter = new StringWriter(1024);
                pwriter = new PrintWriter(swriter);
                cause.printStackTrace(pwriter);
                pwriter.flush();
                swriter.flush();
                System.err.println(swriter.toString());
            }
            System.err.println("=======");
        } catch ( JmiException jmix ) {
            oclModel.endTrans(true);
            String message = jmix.getMessage();
            String position = null;
            Token tk = astgen.getCurrentToken();
            if ( tk != null ) {
                position = "" + tk.getLine() + ":" + tk.getPos();
            } else {
                position = "<unknown>";
            }
            System.err.println("===[ JMI Exception during contextual analysis at " + position + "]===");
            RefObject elInErr = jmix.getElementInError();
            Object objInErr = jmix.getObjectInError();
            if ( objInErr != null ) {
                System.err.println("Object in error (class name): " + objInErr.getClass().getName());
                System.err.println("Object in error (toString): " + objInErr);
            }
            if ( elInErr != null ) {
                System.err.println("Element in error (clas name): " + elInErr.getClass().getName());
                System.err.println("Element in error (MOF Id): " + elInErr.refMofId());
                System.err.println("Element in error (toString) " + elInErr);
            }

            System.err.println("=== Exception message and stack trace follows ===");
            System.err.println(message);
            StringWriter swriter = new StringWriter(1024);
            PrintWriter pwriter = new PrintWriter(swriter);
            jmix.printStackTrace(pwriter);
            pwriter.flush();
            swriter.flush();
            System.err.println(swriter.toString());
        } catch (Throwable t) {
            oclModel.endTrans(true);
            String message = t.getMessage();
            if ( message == null ) {
                message = "No exception detail message available";
            }
            String position = null;
            Token tk = astgen.getCurrentToken();
            if ( tk != null ) {
                position = "" + tk.getLine() + ":" + tk.getPos();
            } else {
                position = "<unknown>";
            }
            System.err.println("===[ Exception during contextual analysis at " + position + "]===");
            System.err.println(message);
            StringWriter swriter = new StringWriter(1024);
            PrintWriter pwriter = new PrintWriter(swriter);
            t.printStackTrace(pwriter);
            pwriter.flush();
            swriter.flush();
            System.err.println(swriter.toString());
            System.err.println("=======");
        }
	return retval;

    }


    private String loadOclFile(java.io.File file) {
        String path = file.getAbsolutePath();
        boolean canRead = file.canRead();
	String constraints = "";
        if ( ! canRead ) {
            System.err.println("Cannot read file " + path + "! Aborting load.");
        } else {
            try {
                java.io.BufferedReader br = new java.io.BufferedReader(
                                                new java.io.InputStreamReader(new java.io.FileInputStream(path)));
                String line = br.readLine();
                while ( line != null ) {
		    constraints = constraints + "\n"+line;
		    line = br.readLine();
		}
		
            } catch (java.io.IOException ex) {
                System.err.println("Error reading file " + path + "! Check system error stream for details.");
                ex.printStackTrace(System.err);
            }
        }
	return constraints;
    }

    private void saveModelXmi(java.io.File file) {
        OclModel model = this.getOclModel();
        if ( model == null ) {
            System.err.println("No model loaded, cannot save.");
            return;
        }

        String path = file.getAbsolutePath();
        boolean exists = file.exists();
        boolean canWrite = file.canWrite();

        if ( ! exists || canWrite ) {
            try {
                model.save(path);
            } catch (OclModelException ex) {
                System.err.println("Error saving OCL model. " + ex.getMessage()
                                   + ", check system error stream for details.");
                ex.printStackTrace(System.err);
            }
        } else {
            System.err.println("Cannot write to file " + path + "! Aborting save.");
        }
    }

    private void loadModelXmi(String metaModelName, java.io.File file) {
        String path = file.getAbsolutePath();
        String modelUrl = "file:" + path;
        boolean canRead = file.canRead();

        if ( ! canRead ) {
            System.err.println("Cannot read model file " + path + "! Aborting load.");
        } else {
            System.out.println("Reading model from URL " + modelUrl);
            try {
                OclModel oldModel = this.getOclModel();
                if ( oldModel != null ) {
                    oldModel.delete();
                }
                OclModel newModel = new OclModel(metaModelName, modelUrl);
                this.setOclModel(newModel, modelUrl);

                tudresden.ocl20.jmi.ocl.commonmodel.Package topPkg = newModel.getTopPackage();
                String topName = topPkg.getNameA();
                System.out.println("Top package name: '" + topName + "'");
            } catch (OclModelException ex) {
                System.err.println("Error loading OCL model. " + ex.getMessage()
                                   + ", check system error stream for details.");
                ex.printStackTrace(System.err);
            }

        }
    }


    /**
     * @param args the command line arguments
     */
    public OCL20CLI(String args[]) {
        int i = 0, j;
        String arg;
        String outputfile = "";
        String XmiFilename = "";
        String OclFilename = "";
        String constraints = "";
        String metaModelName = null;


        while (i < args.length && args[i].startsWith("-")) {
            arg = args[i++];

            if (arg.equals("--verbose")) {
                System.out.println("verbose mode on");
                verbose = true;
            } else if (arg.equals("--outfile")) {
                if (i < args.length)
                    outputfile = args[i++];
                else
                    System.err.println("--outfile requires a filename");
                if (verbose)
                    System.out.println("output file = " + outputfile);
            } else if (arg.equals("--mmodel")) {
                String model = "UML15";
                if (i < args.length)
                    model = args[i++];
                else {
                    System.err.println("--mmodel requires a metamodel (MOF14|UML15)");
                    System.exit(1);
                }
                if (model.equals("UML15"))
                    metaModelName = MetaModelConst.UML15;
                else if (model.equals("MOF14"))
                    metaModelName = MetaModelConst.MOF14;
                else {
                    System.err.println("Unsupported meta model selected");
                    System.exit(1);
                }


                if (verbose)
                    System.out.println("meta model = " + outputfile);
            }
        }
        if (i != args.length - 2) {
	    System.err.println(" i = " +i+" arg = "+args.length);
            System.err.println("Usage: OCL2CLI [--verbose] [--mmodel MOF14 | UML15] [--outfile output] xmi-file ocl-file");
            System.exit(1);
        } else {
            XmiFilename = args[i++];
            OclFilename = args[i++];
        }


        metaModelName = MetaModelConst.UML15;

	if(verbose) System.out.println("Loading XMI file");
        loadModelXmi(metaModelName,new File(XmiFilename));

	if(verbose) System.out.println("Loading OCL file");
	constraints = loadOclFile(new File(OclFilename));

	if(verbose) System.out.println("Parsing concrete syntax of OCL constraints");
        runParser(constraints);

	if(verbose) System.out.println("Generate abstract syntax tree (includes OCL type-checking)");
        generateAst();


        if (!outputfile.equals("")) {
	    if(verbose) System.out.println("Storing XMI file");
            saveModelXmi(new File(outputfile));
        }
        if ( oclModel != null ) {
            oclModel.closeAndShutDown();
        }
        System.exit(0);

    }

    private void runParser(String text) {

        try {
            Lexer lexer   = new Lexer (new PushbackReader(new StringReader(text)));
            Parser parser = new Parser(lexer);
            cst    = parser.parse();
        } catch (Exception e) {
            System.err.println("Cannot parse OCL constraint: " + e.getLocalizedMessage());
	    // e.printStackTrace(System.err);
        }

    }


    public static void main(String[] args) {
        new OCL20CLI(args);
    }


}