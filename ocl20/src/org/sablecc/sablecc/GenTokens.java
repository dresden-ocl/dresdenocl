/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import org.sablecc.sablecc.analysis.*;
import org.sablecc.sablecc.node.*;
import java.io.*;
import java.util.LinkedList;

public class GenTokens extends DepthFirstAdapter
{
  private MacroExpander macros;
  private ResolveIds ids;
  private File pkgDir;
  private String pkgName;

  //    final GenTokens instance = this;
  String text;

  public GenTokens(ResolveIds ids)
  {
    this.ids = ids;

    try
    {
      macros = new MacroExpander(
                 new InputStreamReader(
                   getClass().getResourceAsStream("tokens.txt")));
    }
    catch(IOException e)
    {
      throw new RuntimeException("unable to open tokens.txt.");
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

  public void inATokenDef(ATokenDef node)
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

    text = null;

    ARegExp regExp = (ARegExp) node.getRegExp();

    AConcat concat = (AConcat) regExp.getConcat();
    LinkedList regExpTails = regExp.getConcats();

    if(regExpTails.size() == 0)
    {
      LinkedList unExps = concat.getUnExps();

      if(unExps.size() == 1)
      {
        AUnExp unExp = (AUnExp) unExps.getFirst();

        PBasic basic = unExp.getBasic();

        if((basic instanceof AStringBasic) &&
            (unExp.getUnOp() == null))
        {
          text = ((AStringBasic) basic).getString().getText();
          text = text.substring(1, text.length() - 1);
        }
        else if((basic instanceof ACharBasic) &&
                (unExp.getUnOp() == null))
        {
          PChar pChar = ((ACharBasic) basic).getChar();

          if(pChar instanceof ACharChar)
          {
            text = ((ACharChar) pChar).getChar().getText();
            text = text.substring(1, text.length() - 1);
          }
        }
      }
    }

    try
    {
      if(text == null)
      {
        ids.fixedTokens.put(node, new Boolean(false));

        macros.apply(file, "VariableTextToken", new String[] { pkgName,
                     ids.pkgName.equals("") ? "analysis" : ids.pkgName + ".analysis",
                     name});
      }
      else
      {
        ids.fixedTokens.put(node, new Boolean(true));

        macros.apply(file, "FixedTextToken", new String[] { pkgName,
                     ids.pkgName.equals("") ? "analysis" : ids.pkgName + ".analysis",
                     name, processText(text)});

        ids.errorNames.put(node, "'" + text + "'");
      }
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

  private String processText(String s)
  {
    StringBuffer result = new StringBuffer();

    for(int i = 0; i < s.length(); i++)
    {
      char c = s.charAt(i);

      switch(c)
      {
      case '\b':
        {
          result.append("\\t");
          break;
        }
      case '\t':
        {
          result.append("\\t");
          break;
        }
      case '\n':
        {
          result.append("\\n");
          break;
        }
      case '\f':
        {
          result.append("\\f");
          break;
        }
      case '\r':
        {
          result.append("\\r");
          break;
        }
      case '\"':
        {
          result.append("\\\"");
          break;
        }
      case '\'':
        {
          result.append("\\\'");
          break;
        }
      case '\\':
        {
          result.append("\\\\");
          break;
        }
      default:
        {
          result.append(c);
        }
      }
    }

    return result.toString();
  }
}

