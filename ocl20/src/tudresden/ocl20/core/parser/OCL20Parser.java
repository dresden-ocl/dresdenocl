/*
 * OCL20Parser.java
 *
 * Created on 16. Juni 2004, 12:14
 *
 * Copyright (c) 2004, 2005 Ansgar Konermann
 * Contact: <konermann@itikko.net>
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

import tudresden.ocl20.parser.sablecc.analysis.*;
import tudresden.ocl20.parser.sablecc.lexer.Lexer;
import tudresden.ocl20.parser.sablecc.lexer.LexerException;
import tudresden.ocl20.parser.sablecc.parser.Parser;
import tudresden.ocl20.parser.sablecc.parser.ParserException;
import tudresden.ocl20.parser.sablecc.node.*;

import java.io.*;


/**
 * Test program to run parser and lexer against an OCL2.0 file
 * @author Ansgar Konermann
 */
public class OCL20Parser {
    
    /** Creates a new instance of OCL20Parser */
    public OCL20Parser() {
    }
    
    /**
     * @param args  the command line arguments. Expects exactly one command line
     *              argument, which must be the file name of the OCL2.0 text file
     *              to parse.
     */
    public static void main(String[] args) {

      long start_time, stop_time;   // times compilation

      if (args.length < 1) {
           System.out.println("Usage:");
           System.out.println(" java ocl20.parser.OCL20Parser <filename>");
           System.exit(0);
      }

      try {
           start_time = System.currentTimeMillis();

           // create lexer
           Lexer lexer = new Lexer (new PushbackReader(new BufferedReader(new FileReader(args[0])), 1024));

           // parser program
           Parser parser = new Parser(lexer);

           Start ast = parser.parse();

           // check program semantics
           // ast.apply(new SemanticAnalyser());

           // generate class file
           // ast.apply(new ClassGenerator());
           
           stop_time = System.currentTimeMillis();
           System.out.println("Compilation time: " + (stop_time - start_time) + " ms");
      }
      catch (Exception e) {
           System.out.println(e);
           e.printStackTrace();
      }
    }
}
