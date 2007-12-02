/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import java.io.*;
import java.awt.*;
import java.util.*;

import org.sablecc.sablecc.DisplayLicense;
import org.sablecc.sablecc.GenAlts;
import org.sablecc.sablecc.GenAnalyses;
import org.sablecc.sablecc.GenLexer;
import org.sablecc.sablecc.GenParser;
import org.sablecc.sablecc.GenProds;
import org.sablecc.sablecc.GenTokens;
import org.sablecc.sablecc.GenUtils;
import org.sablecc.sablecc.Grammar;
import org.sablecc.sablecc.LR0Collection;
import org.sablecc.sablecc.Production;
import org.sablecc.sablecc.ResolveIds;
import org.sablecc.sablecc.Symbol;
import org.sablecc.sablecc.Version;
import org.sablecc.sablecc.node.*;
import org.sablecc.sablecc.analysis.*;
import org.sablecc.sablecc.lexer.*;
import org.sablecc.sablecc.parser.*;


import java.util.Vector;

public class SableCC
{
  private static void displayCopyright()
  {
    System.out.println();
    System.out.println("SableCC version " + Version.VERSION);
    System.out.println("Copyright (C) 1997-2003 Etienne M. Gagnon <etienne.gagnon@uqam.ca> and");
    System.out.println("others.  All rights reserved.");
    System.out.println("Extended by Ansgar Konermann");
    System.out.println();
    System.out.println("This software comes with ABSOLUTELY NO WARRANTY.  This is free software,");
    System.out.println("and you are welcome to redistribute it under certain conditions.");
    System.out.println();
    System.out.println("Type 'sablecc -license' to view");
    System.out.println("the complete copyright notice and license.");
    System.out.println();
  }

  private static void displayUsage()
  {
    System.out.println("Usage:");
    System.out.println("  sablecc [-d destination] filename [filename]...");
    System.out.println("  sablecc -license");
  }

  public static void main(String[] arguments)
  {
    String d_option = null;
    Vector filename = new Vector();

    if(arguments.length == 0)
    {
      displayCopyright();
      displayUsage();
      System.exit(1);
    }

    if((arguments.length == 1) && (arguments[0].equals("-license")))
    {
      new DisplayLicense();
      System.exit(0);
    }

    displayCopyright();

    {
      int arg = 0;
      while(arg < arguments.length)
      {
        if(arguments[arg].equals("-d"))
        {
          if((d_option == null) && (++arg < arguments.length))
          {
            d_option = arguments[arg];
          }
          else
          {
            displayUsage();
            System.exit(1);
          }
        }
        else
        {
          filename.addElement(arguments[arg]);
        }
        arg++;
      }

      if(filename.size() == 0)
      {
        displayUsage();
        System.exit(1);
      }
    }

    try
    {
      for(int i=0; i<filename.size(); i++)
      {
        processGrammar((String)filename.elementAt(i), d_option);
      }
    }
    catch(Exception e)
    {
      System.out.println(e);
      System.exit(1);
    }
    catch(Throwable e)
    {
      System.out.println(e);
      System.exit(1);
    }
    finally
    {
      System.exit(0);
    }
  }

  /**
   * The main method for processing grammar file and generating the parser/lexer.
   * @param in input grammar file
   * @param dir output directory
   */
  public static void processGrammar(String grammar, String destDir) throws Exception, Throwable
  {
    File in;
    File dir;

    in = new File(grammar);
    in = new File(in.getAbsolutePath());

    if(destDir == null)
    {
      dir = new File(in.getParent());
    }
    else
    {
      dir = new File(destDir);
      dir = new File(dir.getAbsolutePath());
    }

    processGrammar(in, dir);
  }

  /**
   * The main method for processing grammar file and generating the parser/lexer.
   * @param in input grammar file
   * @param dir output directory
   */
  public static void processGrammar(File in,  File dir) throws Exception, Throwable
  {
    if(!in.exists())
    {
      System.out.println("ERROR: grammar file "+in.getName()+" does not exist.");
      System.exit(1);
    }
    if(!dir.exists())
    {
      System.out.println("ERROR: destination directory "+dir.getName()+" does not exist.");
      System.exit(1);
    }

    // re-initialize all static structures in the engine
    LR0Collection.reinit();
    Symbol.reinit();
    Production.reinit();
    Grammar.reinit();

    System.out.println("\n -- Generating parser for "+in.getName()+" in "+dir.getPath());

    FileReader temp;

    // Build the AST
    Start tree = new Parser(new Lexer(new PushbackReader(
                                        new BufferedReader(temp = new FileReader(in)), 1000))).parse();
    temp.close();

    System.out.println("Verifying identifiers.");
    ResolveIds ids = new ResolveIds(dir);
    tree.apply(ids);

    // Create the node.* and analysis.* files
    System.out.println("Generating token classes.");
    tree.apply(new GenTokens(ids));

    System.out.println("Generating production classes.");
    tree.apply(new GenProds(ids));

    System.out.println("Generating alternative classes.");
    tree.apply(new GenAlts(ids));

    System.out.println("Generating analysis classes.");
    try {
        tree.apply(new GenAnalyses(ids));
    } catch (Exception e) {
        e.printStackTrace();
    }

    System.out.println("Generating utility classes.");
    tree.apply(new GenUtils(ids));

    try
    {
      System.out.println("Generating the lexer.");
      tree.apply(new GenLexer(ids));
    }
    catch(Exception e)
    {
      System.out.println(e.getMessage());
      throw e;
    }

    try
    {
      System.out.println("Generating the parser.");
      tree.apply(new GenParser(ids));
    }
    catch(Exception e)
    {
      System.out.println(e.getMessage());
      throw e;
    }
  }

}

