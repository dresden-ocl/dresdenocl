/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import org.sablecc.sablecc.Cast;
import org.sablecc.sablecc.ElemInfo;
import org.sablecc.sablecc.ElemInfoCast;
import org.sablecc.sablecc.MacroExpander;
import org.sablecc.sablecc.ResolveIds;
import org.sablecc.sablecc.TypedLinkedList;
import org.sablecc.sablecc.analysis.*;
import org.sablecc.sablecc.node.*;



import java.util.*;
import java.io.*;

public class GenAlts extends DepthFirstAdapter
{
  private MacroExpander macros;
  private ResolveIds ids;
  private File pkgDir;
  private String pkgName;
  private List elemList;

  private String currentProd;
  ElemInfo info;
  //    final GenAlts instance = this;

  public GenAlts(ResolveIds ids)
  {
    this.ids = ids;

    try
    {
      macros = new MacroExpander(
                 new InputStreamReader(
                   getClass().getResourceAsStream("alternatives.txt")));
    }
    catch(IOException e)
    {
      throw new RuntimeException("unable to open alternatives.txt.");
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
    currentProd = (String) ids.names.get(node);
  }

  public void inAParsedAlt(AParsedAlt node)
  {
    inPAlt(node);
  }

  public void inAIgnoredAlt(AIgnoredAlt node)
  {
    inPAlt(node);
  }

  public void inPAlt(PAlt node)
  {
    elemList = new TypedLinkedList(ElemInfoCast.instance);
  }

  public void inAElem(AElem node)
  {
    info = new ElemInfo();

    info.name = (String) ids.names.get(node);
    info.type = (String) ids.elemTypes.get(node);
    info.operator = ElemInfo.NONE;

    if(node.getUnOp() != null)
    {
      node.getUnOp().apply(new DepthFirstAdapter()
                           {
                             public void caseAStarUnOp(AStarUnOp node)
                             {
                               info.operator = ElemInfo.STAR;
                             }

                             public void caseAQMarkUnOp(AQMarkUnOp node)
                             {
                               info.operator = ElemInfo.QMARK;
                             }

                             public void caseAPlusUnOp(APlusUnOp node)
                             {
                               info.operator = ElemInfo.PLUS;
                             }
                           }
                          );
    }

    elemList.add(info);
    info = null;
  }

  public void outAParsedAlt(AParsedAlt node)
  {
    outPAlt(node);
  }

  public void outAIgnoredAlt(AIgnoredAlt node)
  {
    outPAlt(node);
  }

  public void outPAlt(PAlt node)
  {
    String name = (String) ids.names.get(node);

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
      boolean hasOperator = false;

      macros.apply(file, "AlternativeHeader", new String[] {pkgName,
                   ids.pkgName.equals("") ? "analysis" : ids.pkgName + ".analysis",
                   name, currentProd});

      for(Iterator i = elemList.iterator(); i.hasNext();)
      {
        ElemInfo info = (ElemInfo) i.next();

        switch(info.operator)
        {
        case ElemInfo.QMARK:
        case ElemInfo.NONE:
          {
            macros.apply(file, "NodeElement",
                         new String[] {info.type,
                                       nodeName(info.name)});
          }
          break;
        case ElemInfo.STAR:
        case ElemInfo.PLUS:
          {
            hasOperator = true;
            macros.apply(file, "ListElement",
                         new String[] {info.name, nodeName(info.name)});
          }
          break;
        }
      }

      macros.apply(file, "ConstructorHeader",
                   new String[] {name});
      macros.apply(file, "ConstructorBodyHeader", null);
      macros.apply(file, "ConstructorBodyTail", null);

      if(elemList.size() > 0)
      {
        macros.apply(file, "ConstructorHeader",
                     new String[] {name});

        for(Iterator i = elemList.iterator(); i.hasNext();)
        {
          ElemInfo info = (ElemInfo) i.next();

          switch(info.operator)
          {
          case ElemInfo.QMARK:
          case ElemInfo.NONE:
            {
              macros.apply(file, "ConstructorHeaderDeclNode",
                           new String[] {info.type, nodeName(info.name), i.hasNext() ? "," : ""});
            }
            break;
          case ElemInfo.STAR:
          case ElemInfo.PLUS:
            {
              macros.apply(file, "ConstructorHeaderDeclList",
                           new String[] {"List", nodeName(info.name), i.hasNext() ? "," : ""});
            }
            break;
          }
        }

        macros.apply(file, "ConstructorBodyHeader", null);

        for(Iterator i = elemList.iterator(); i.hasNext();)
        {
          ElemInfo info = (ElemInfo) i.next();

          switch(info.operator)
          {
          case ElemInfo.QMARK:
          case ElemInfo.NONE:
            {
              macros.apply(file, "ConstructorBodyNode",
                           new String[] {info.name, nodeName(info.name)});
            }
            break;
          case ElemInfo.STAR:
          case ElemInfo.PLUS:
            {
              macros.apply(file, "ConstructorBodyList",
                           new String[] {nodeName(info.name)});
            }
            break;
          }
        }

        macros.apply(file, "ConstructorBodyTail", null);
      }

      if(hasOperator)
      {
        macros.apply(file, "ConstructorHeader",
                     new String[] {name});

        for(Iterator i = elemList.iterator(); i.hasNext();)
        {
          ElemInfo info = (ElemInfo) i.next();

          switch(info.operator)
          {
          case ElemInfo.NONE:
          case ElemInfo.QMARK:
            {
              macros.apply(file, "ConstructorHeaderDeclNode",
                           new String[] {info.type, nodeName(info.name), i.hasNext() ? "," : ""});
            }
            break;
          case ElemInfo.STAR:
          case ElemInfo.PLUS:
            {
              macros.apply(file, "ConstructorHeaderDeclList",
                           new String[] {"X" + info.type,
                                         nodeName(info.name), i.hasNext() ? "," : ""});
            }
            break;
          }
        }

        macros.apply(file, "ConstructorBodyHeader", null);

        for(Iterator i = elemList.iterator(); i.hasNext();)
        {
          ElemInfo info = (ElemInfo) i.next();

          switch(info.operator)
          {
          case ElemInfo.NONE:
          case ElemInfo.QMARK:
            {
              macros.apply(file, "ConstructorBodyNode",
                           new String[] {info.name, nodeName(info.name)});
            }
            break;
          case ElemInfo.STAR:
          case ElemInfo.PLUS:
            {
              macros.apply(file, "ConstructorBodyPlus",
                           new String[] {nodeName(info.name), info.type});
            }
            break;
          }
        }

        macros.apply(file, "ConstructorBodyTail", null);
      }

      //****************
      macros.apply(file, "CloneHeader",
                   new String[] {name});

      for(Iterator i = elemList.iterator(); i.hasNext();)
      {
        ElemInfo info = (ElemInfo) i.next();

        switch(info.operator)
        {
        case ElemInfo.QMARK:
        case ElemInfo.NONE:
          {
            macros.apply(file, "CloneBodyNode",
                         new String[] {info.type, nodeName(info.name), i.hasNext() ? "," : ""});
          }
          break;
        case ElemInfo.STAR:
        case ElemInfo.PLUS:
          {
            macros.apply(file, "CloneBodyList",
                         new String[] {nodeName(info.name), i.hasNext() ? "," : ""});
          }
          break;
        }
      }

      macros.apply(file, "CloneTail", null);
      //****************

      macros.apply(file, "Apply", new String[] {name});

      for(Iterator i = elemList.iterator(); i.hasNext();)
      {
        ElemInfo info = (ElemInfo) i.next();

        switch(info.operator)
        {
        case ElemInfo.QMARK:
        case ElemInfo.NONE:
          {
            macros.apply(file, "GetSetNode",
                         new String[] {info.type, info.name, nodeName(info.name)});
          }
          break;
        case ElemInfo.STAR:
        case ElemInfo.PLUS:
          {
            macros.apply(file, "GetSetList",
                         new String[] {info.name, nodeName(info.name)});
          }
          break;
        }
      }

      macros.apply(file, "ToStringHeader", null);
      for(Iterator i = elemList.iterator(); i.hasNext();)
      {
        ElemInfo info = (ElemInfo) i.next();

        switch(info.operator)
        {
        case ElemInfo.QMARK:
        case ElemInfo.NONE:
          {
            macros.apply(file, "ToStringBodyNode",
                         new String[] {nodeName(info.name)});
          }
          break;
        case ElemInfo.STAR:
        case ElemInfo.PLUS:
          {
            macros.apply(file, "ToStringBodyList",
                         new String[] {nodeName(info.name)});
          }
          break;
        }
      }
      macros.apply(file, "ToStringTail", null);

      macros.apply(file, "RemoveChildHeader", null);
      for(Iterator i = elemList.iterator(); i.hasNext();)
      {
        ElemInfo info = (ElemInfo) i.next();

        switch(info.operator)
        {
        case ElemInfo.QMARK:
        case ElemInfo.NONE:
          {
            macros.apply(file, "RemoveChildNode",
                         new String[] {nodeName(info.name)});
          }
          break;
        case ElemInfo.STAR:
        case ElemInfo.PLUS:
          {
            macros.apply(file, "RemoveChildList",
                         new String[] {nodeName(info.name)});
          }
          break;
        }
      }
      macros.apply(file, "RemoveChildTail", null);
      /* */
      macros.apply(file, "ReplaceChildHeader", null);
      for(Iterator i = elemList.iterator(); i.hasNext();)
      {
        ElemInfo info = (ElemInfo) i.next();

        switch(info.operator)
        {
        case ElemInfo.QMARK:
        case ElemInfo.NONE:
          {
            macros.apply(file, "ReplaceChildNode",
                         new String[] {nodeName(info.name), info.name, info.type});
          }
          break;
        case ElemInfo.STAR:
        case ElemInfo.PLUS:
          {
            macros.apply(file, "ReplaceChildList",
                         new String[] {nodeName(info.name)});
          }
          break;
        }
      }
      macros.apply(file, "ReplaceChildTail", null);

      /* */
      for(Iterator i = elemList.iterator(); i.hasNext();)
      {
        ElemInfo info = (ElemInfo) i.next();

        switch(info.operator)
        {
        case ElemInfo.STAR:
        case ElemInfo.PLUS:
          {
            macros.apply(file, "Cast",
                         new String[] {info.name, info.type, name});
          }
          break;
        }
      }

      macros.apply(file, "AlternativeTail", null);
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

    elemList = null;
  }

  public static String nodeName(String s)
  {
    StringBuffer result = new StringBuffer(s);

    if(result.length() > 0)
    {
      result.setCharAt(0, Character.toLowerCase(result.charAt(0)));
    }

    return result.toString();
  }

  private static class ElemInfo
  {
    final static int NONE = 0;
    final static int STAR = 1;
    final static int QMARK = 2;
    final static int PLUS = 3;

    String name;
    String type;
    int operator;
  }

  private static class ElemInfoCast implements Cast
  {
    public final static ElemInfoCast instance = new ElemInfoCast();

    private ElemInfoCast()
    {}

    public    Object cast(Object o)
    {
      return (ElemInfo) o;
    }
  }
}

