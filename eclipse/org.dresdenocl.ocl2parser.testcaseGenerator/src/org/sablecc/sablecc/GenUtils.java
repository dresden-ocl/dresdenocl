/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import org.sablecc.sablecc.GenAlts;
import org.sablecc.sablecc.MacroExpander;
import org.sablecc.sablecc.ResolveIds;
import org.sablecc.sablecc.analysis.*;
import org.sablecc.sablecc.node.*;



import java.util.*;
import java.io.*;

public class GenUtils extends DepthFirstAdapter
{
  private MacroExpander macros;
  private ResolveIds ids;
  private File pkgDir;
  private String pkgName;
  private String mainProduction;

  public GenUtils(ResolveIds ids)
  {
    this.ids = ids;

    try
    {
      macros = new MacroExpander(
                 new InputStreamReader(
                   getClass().getResourceAsStream("utils.txt")));
    }
    catch(IOException e)
    {
      throw new RuntimeException("unable to open utils.txt.");
    }

    pkgDir = new File(ids.pkgDir, "node");
    pkgName = ids.pkgName.equals("") ? "node" : ids.pkgName + ".node";

    if(!pkgDir.exists())
    {
      if(!pkgDir.mkdir())
      {
        throw new RuntimeException("Unable to create " + pkgDir.getAbsolutePath());
      }
    }
  }

  public void caseAGrammar(AGrammar node)
  {
    if(node.getProductions() != null)
      node.getProductions().apply(this);
  }

  public void caseAProd(AProd node)
  {
    if(mainProduction == null)
    {
      mainProduction = (String) ids.names.get(node);
    }
  }

  public void outStart(Start node)
  {
    if(mainProduction != null)
    {
      createStart();
    }

    createEOF();
    createNode();
    createNodeCast();
    createToken();
    create("Switch");
    create("SwitchWithReturn");
    create("Switchable");
    create("SwitchableWithReturn");
    create("TypedLinkedList");
    create("Cast");
    create("NoCast");
  }

  public void createStart()
  {
    BufferedWriter file;

    try
    {
      file = new BufferedWriter(
               new FileWriter(
                 new File(pkgDir, "Start.java")));
    }
    catch(IOException e)
    {
      throw new RuntimeException("Unable to create " + new File(pkgDir, "Start.java").getAbsolutePath());
    }

    try
    {
      macros.apply(file, "Start", new String[] {pkgName,
                   ids.pkgName.equals("") ? "analysis" : ids.pkgName + ".analysis",
                   mainProduction, GenAlts.nodeName(mainProduction)});
    }
    catch(IOException e)
    {
      throw new RuntimeException("An error occured while writing to " +
                                 new File(pkgDir, "Start.java").getAbsolutePath());
    }

    try
    {
      file.close();
    }
    catch(IOException e)
    {}
  }

  public void createEOF()
  {
    BufferedWriter file;

    try
    {
      file = new BufferedWriter(
               new FileWriter(
                 new File(pkgDir, "EOF.java")));
    }
    catch(IOException e)
    {
      throw new RuntimeException("Unable to create " + new File(pkgDir, "EOF.java").getAbsolutePath());
    }

    try
    {
      macros.apply(file, "EOF", new String[] {pkgName,
                                              ids.pkgName.equals("") ? "analysis" : ids.pkgName + ".analysis"});
    }
    catch(IOException e)
    {
      throw new RuntimeException("An error occured while writing to " +
                                 new File(pkgDir, "EOF.java").getAbsolutePath());
    }

    try
    {
      file.close();
    }
    catch(IOException e)
    {}
  }

  public void createNode()
  {
    BufferedWriter file;

    try
    {
      file = new BufferedWriter(
               new FileWriter(
                 new File(pkgDir, "Node.java")));
    }
    catch(IOException e)
    {
      throw new RuntimeException("Unable to create " + new File(pkgDir, "Node.java").getAbsolutePath());
    }

    try
    {
      macros.apply(file, "Node", new String[] {pkgName,
                   ids.pkgName.equals("") ? "analysis" : ids.pkgName + ".analysis"});
    }
    catch(IOException e)
    {
      throw new RuntimeException("An error occured while writing to " +
                                 new File(pkgDir, "Node.java").getAbsolutePath());
    }

    try
    {
      file.close();
    }
    catch(IOException e)
    {}
  }

  public void createNodeCast()
  {
    BufferedWriter file;

    try
    {
      file = new BufferedWriter(
               new FileWriter(
                 new File(pkgDir, "NodeCast.java")));
    }
    catch(IOException e)
    {
      throw new RuntimeException("Unable to create " + new File(pkgDir, "NodeCast.java").getAbsolutePath());
    }

    try
    {
      macros.apply(file, "NodeCast", new String[] {pkgName});
    }
    catch(IOException e)
    {
      throw new RuntimeException("An error occured while writing to " +
                                 new File(pkgDir, "NodeCast.java").getAbsolutePath());
    }

    try
    {
      file.close();
    }
    catch(IOException e)
    {}
  }

  public void createToken()
  {
    BufferedWriter file;

    try
    {
      file = new BufferedWriter(
               new FileWriter(
                 new File(pkgDir, "Token.java")));
    }
    catch(IOException e)
    {
      throw new RuntimeException("Unable to create " + new File(pkgDir, "Token.java").getAbsolutePath());
    }

    try
    {
      macros.apply(file, "Token", new String[] {pkgName});
    }
    catch(IOException e)
    {
      throw new RuntimeException("An error occured while writing to " +
                                 new File(pkgDir, "Token.java").getAbsolutePath());
    }

    try
    {
      file.close();
    }
    catch(IOException e)
    {}
  }

  public void create(String cls)
  {
    BufferedWriter file;

    try
    {
      file = new BufferedWriter(
               new FileWriter(
                 new File(pkgDir, cls + ".java")));
    }
    catch(IOException e)
    {
      throw new RuntimeException("Unable to create " + new File(pkgDir, cls + ".java").getAbsolutePath());
    }

    try
    {
      macros.apply(file, cls, new String[] {pkgName, 
        ids.pkgName.equals("") ? "analysis" : ids.pkgName + ".analysis"});
    }
    catch(IOException e)
    {
      throw new RuntimeException("An error occured while writing to " +
                                 new File(pkgDir, cls + ".java").getAbsolutePath());
    }

    try
    {
      file.close();
    }
    catch(IOException e)
    {}
  }
}

