/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import org.sablecc.sablecc.analysis.*;
import org.sablecc.sablecc.node.*;
import java.util.*;
import java.io.*;

public class GenProds extends DepthFirstAdapter
{
  private MacroExpander macros;
  private ResolveIds ids;
  private File pkgDir;
  private String pkgName;
  private Map hiddenProds = new TypedTreeMap(
                              StringComparator.instance,
                              StringCast.instance,
                              NodeCast.instance);

  public GenProds(ResolveIds ids)
  {
    this.ids = ids;

    try
    {
      macros = new MacroExpander(
                 new InputStreamReader(
                   getClass().getResourceAsStream("productions.txt")));
    }
    catch(IOException e)
    {
      throw new RuntimeException("unable to open productions.txt.");
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

  public void inAProd(AProd node)
  {
    String name = (String) ids.names.get(node);

    createProduction(name);
  }

  private void createProduction(String name)
  {
    BufferedWriter file;

    try
    {
      file = new BufferedWriter(
               new FileWriter(
                 new File(pkgDir, name + ".java")));
    }
    catch(IOException e)
    {
      throw new RuntimeException("Unable to create " + new File(pkgDir, name + ".java").getAbsolutePath());
    }

    try
    {
      macros.apply(file, "Production", new String[] {pkgName, name});
    }
    catch(IOException e)
    {
      throw new RuntimeException("An error occured while writing to " +
                                 new File(pkgDir, name + ".java").getAbsolutePath());
    }

    try
    {
      file.close();
    }
    catch(IOException e)
    {}
  }

  public void inAStarUnOp(AStarUnOp node)
  {
    if(node.parent() instanceof AElem)
    {
      AElem elem = (AElem) node.parent();

      String name = (String) ids.elemTypes.get(elem);

      if(hiddenProds.put("X" + name, elem) == null)
      {
        createProduction("X" + name);

        createAlternative(
          "X1" + name,
          "HiddenAlternative2",
          new String[] {pkgName,
                        ids.pkgName.equals("") ? "analysis" : ids.pkgName + ".analysis",
                        "X1" + name,
                        "X" + name,
                        name,
                        GenAlts.nodeName(name),
                        GenAlts.nodeName("X" + name)});

        createAlternative(
          "X2" + name,
          "HiddenAlternative1",
          new String[] {pkgName,
                        ids.pkgName.equals("") ? "analysis" : ids.pkgName + ".analysis",
                        "X2" + name,
                        "X" + name,
                        name,
                        GenAlts.nodeName(name)});
      }
    }
  }

  public void inAPlusUnOp(APlusUnOp node)
  {
    if(node.parent() instanceof AElem)
    {
      AElem elem = (AElem) node.parent();

      String name = (String) ids.elemTypes.get(elem);

      if(hiddenProds.put("X" + name, elem) == null)
      {
        createProduction("X" + name);

        createAlternative(
          "X1" + name,
          "HiddenAlternative2",
          new String[] {pkgName,
                        ids.pkgName.equals("") ? "analysis" : ids.pkgName + ".analysis",
                        "X1" + name,
                        "X" + name,
                        name,
                        GenAlts.nodeName(name),
                        GenAlts.nodeName("X" + name)});

        createAlternative(
          "X2" + name,
          "HiddenAlternative1",
          new String[] {pkgName,
                        ids.pkgName.equals("") ? "analysis" : ids.pkgName + ".analysis",
                        "X2" + name,
                        "X" + name,
                        name,
                        GenAlts.nodeName(name)});
      }
    }
  }

  private void createAlternative(String name, String macro, String[] arg)
  {
    BufferedWriter file;

    try
    {
      file = new BufferedWriter(
               new FileWriter(
                 new File(pkgDir, name + ".java")));
    }
    catch(IOException e)
    {
      throw new RuntimeException("Unable to create " + new File(pkgDir, name + ".java").getAbsolutePath());
    }

    try
    {
      macros.apply(file, macro, arg);
    }
    catch(IOException e)
    {
      throw new RuntimeException("An error occured while writing to " +
                                 new File(pkgDir, name + ".java").getAbsolutePath());
    }

    try
    {
      file.close();
    }
    catch(IOException e)
    {}
  }
}

