/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import org.sablecc.sablecc.BooleanCast;
import org.sablecc.sablecc.NodeCast;
import org.sablecc.sablecc.StringCast;
import org.sablecc.sablecc.StringComparator;
import org.sablecc.sablecc.TypedHashMap;
import org.sablecc.sablecc.TypedLinkedList;
import org.sablecc.sablecc.TypedTreeMap;
import org.sablecc.sablecc.analysis.*;
import org.sablecc.sablecc.node.*;



import java.util.*;
import java.io.*;

public class ResolveIds extends DepthFirstAdapter
{
  public final Map helpers = new TypedTreeMap(
                               StringComparator.instance,
                               StringCast.instance,
                               NodeCast.instance);
  public final Map states = new TypedTreeMap(
                              StringComparator.instance,
                              StringCast.instance,
                              NodeCast.instance);
  public final Map tokens = new TypedTreeMap(
                              StringComparator.instance,
                              StringCast.instance,
                              NodeCast.instance);
  public final Map ignTokens = new TypedTreeMap(
                                 StringComparator.instance,
                                 StringCast.instance,
                                 NodeCast.instance);
  public final Map prods = new TypedTreeMap(
                             StringComparator.instance,
                             StringCast.instance,
                             NodeCast.instance);
  public final Map alts = new TypedTreeMap(
                            StringComparator.instance,
                            StringCast.instance,
                            NodeCast.instance);
  public final Map elems = new TypedTreeMap(
                             StringComparator.instance,
                             StringCast.instance,
                             NodeCast.instance);
  public final Map names = new TypedHashMap(
                             NodeCast.instance,
                             StringCast.instance);
  public final Map errorNames = new TypedHashMap(
                                  NodeCast.instance,
                                  StringCast.instance);
  public final Map elemTypes = new TypedHashMap(
                                 NodeCast.instance,
                                 StringCast.instance);
  public final Map fixedTokens = new TypedHashMap(
                                   NodeCast.instance,
                                   BooleanCast.instance);
  public final List tokenList = new TypedLinkedList(StringCast.instance);
  public final LinkedList stateList = new TypedLinkedList(StringCast.instance);
  public File pkgDir;
  public String pkgName = "";

  private boolean processingStates;
  private boolean processingIgnTokens;

  String currentProd;
  String currentAlt;
  private int lastLine;
  private int lastPos;

  public ResolveIds(File currentDir)
  {
    pkgDir = currentDir;
  }

  public void inAPkgName(APkgName node)
  {
    pkgName = node.getPkgId().getText();
    pkgDir = new File(pkgDir, node.getPkgId().getText());

    if(!pkgDir.exists())
    {
      if(!pkgDir.mkdir())
      {
        throw new RuntimeException("Unable to create " + pkgDir.getAbsolutePath());
      }
    }
  }

  public void inAPkgNameTail(APkgNameTail node)
  {
    pkgName += "." + node.getPkgId().getText();
    pkgDir = new File(pkgDir, node.getPkgId().getText());

    if(!pkgDir.exists())
    {
      if(!pkgDir.mkdir())
      {
        throw new RuntimeException("Unable to create " + pkgDir.getAbsolutePath());
      }
    }
  }

  public void caseAIdBasic(AIdBasic node)
  {
    String name = node.getId().getText();

    if(helpers.get(name) == null)
    {
      error2(node.getId(), name);
    }
  }

  public void outAHelperDef(AHelperDef node)
  {
    String name = node.getId().getText();

    if(helpers.put(name, node) != null)
    {
      error(node.getId(), name);
    }

    names.put(node, name);
  }

  public void outATokenDef(ATokenDef node)
  {
    String name = "T" + name(node.getId().getText());
    String errorName = errorName(node.getId().getText());

    if(tokens.put(name, node) != null)
    {
      error(node.getId(), name);
    }

    names.put(node, name);
    errorNames.put(node, errorName);
    tokenList.add(name);
  }

  public void inASimpleExternalName(ASimpleExternalName node) {
      AGenericExternalName generic = new AGenericExternalName(node.getId());
      node.replaceBy(generic);
  }
  public void inAExtendedExternalName(AExtendedExternalName node) {
      TId tid = new TId( ((TExternalName) node.getExternalName()).getText() );
      AGenericExternalName generic = new AGenericExternalName(tid);
      node.replaceBy(generic);
  }  
  
  public void inAStates(AStates node)
  {
    processingStates = true;
  }

  public void outAStates(AStates node)
  {
    processingStates = false;
  }

  public void inAIgnTokens(AIgnTokens node)
  {
    processingIgnTokens = true;
  }

  public void outAIgnTokens(AIgnTokens node)
  {
    processingIgnTokens = false;
  }

  public void inAIdList(AIdList node)
  {
    if(processingStates)
    {
      String name = node.getId().getText().toUpperCase();

      if(states.put(name, node.getId()) != null)
      {
        error(node.getId(), name);
      }

      names.put(node.getId(), name);
      stateList.add(name);
    }

    if(processingIgnTokens)
    {
      String name = "T" + name(node.getId().getText());

      if(tokens.get(name) == null)
      {
        error2(node.getId(), name);
      }

      if(ignTokens.put(name, node.getId()) != null)
      {
        error(node.getId(), name);
      }

      names.put(node.getId(), name);
    }
  }

  public void inAIdListTail(AIdListTail node)
  {
    if(processingStates)
    {
      String name = node.getId().getText().toUpperCase();

      if(states.put(name, node.getId()) != null)
      {
        error(node.getId(), name);
      }

      names.put(node.getId(), name);
      stateList.add(name);
    }

    if(processingIgnTokens)
    {
      String name = "T" + name(node.getId().getText());

      if(tokens.get(name) == null)
      {
        error2(node.getId(), name);
      }

      if(ignTokens.put(name, node.getId()) != null)
      {
        error(node.getId(), name);
      }

      names.put(node.getId(), name);
    }
  }

  private Map stateMap;

  public void inAStateList(AStateList node)
  {
    stateMap = new TypedTreeMap(
                 StringComparator.instance,
                 StringCast.instance,
                 NodeCast.instance);

    String name = node.getId().getText().toUpperCase();

    if(states.get(name) == null)
    {
      error2(node.getId(), name);
    }

    if(stateMap.put(name, node) != null)
    {
      error(node.getId(), name);
    }
  }

  public void outAStateList(AStateList node)
  {
    stateMap = null;
  }

  public void inAStateListTail(AStateListTail node)
  {
    String name = node.getId().getText().toUpperCase();

    if(states.get(name) == null)
    {
      error2(node.getId(), name);
    }

    if(stateMap.put(name, node) != null)
    {
      error(node.getId(), name);
    }
  }

  public void inATransition(ATransition node)
  {
    String name = node.getId().getText().toUpperCase();

    if(states.get(name) == null)
    {
      error2(node.getId(), name);
    }
  }

  public void inAProd(AProd node)
  {
    currentProd = name(node.getId().getText());

    String name = "P" + currentProd;

    if(prods.put(name, node) != null)
    {
      error(node.getId(), name);
    }

    names.put(node, name);
  }

  public void inAParsedAlt(AParsedAlt alt)
  {
    if(alt.getAltName() != null)
    {
      currentAlt =
        "A" +
        name(((AAltName) alt.getAltName()).getId().getText()) +
        currentProd;

      if(alts.put(currentAlt, alt) != null)
      {
        error(((AAltName) alt.getAltName()).getId(), currentAlt);
      }

      names.put(alt, currentAlt);
    }
    else
    {
      currentAlt = "A" + currentProd;

      if(alts.put(currentAlt, alt) != null)
      {
        error(currentAlt);
      }

      names.put(alt, currentAlt);
    }
  }

  public void inAIgnoredAlt (AIgnoredAlt  alt)
  {
    if(alt.getAltName() != null)
    {
      currentAlt =
        "A" +
        name(((AAltName) alt.getAltName()).getId().getText()) +
        currentProd;

      if(alts.put(currentAlt, alt) != null)
      {
        error(((AAltName) alt.getAltName()).getId(), currentAlt);
      }

      names.put(alt, currentAlt);
    }
    else
    {
      currentAlt = "A" + currentProd;

      if(alts.put(currentAlt, alt) != null)
      {
        error(currentAlt);
      }

      names.put(alt, currentAlt);
    }
  }

  public void inALookAhead(ALookAhead node)
  {
    Token token = (Token) node.getSlash();

    throw new RuntimeException(
      "[" + token.getLine() + "," + token.getPos() + "] " +
      "Look ahead not yet supported.");
  }

  public void caseAElem(AElem elem)
  {
    if(elem.getElemName() != null)
    {
      String name = currentAlt + "." +
                    name(((AElemName)elem.getElemName()).getId().getText());

      if(elems.put(name, elem) != null)
      {
        error(((AElemName)elem.getElemName()).getId(), name);
      }

      if(((AElemName)elem.getElemName()).getId().getText().equals("class"))
      {
        error5(((AElemName)elem.getElemName()).getId());
      }

      names.put(elem, name(((AElemName)elem.getElemName()).getId().getText()));
    }
    else
    {
      String name = currentAlt + "." +
                    name(elem.getId().getText());

      if(elems.put(name, elem) != null)
      {
        error(elem.getId(), name);
      }

      if(elem.getId().getText().equals("class"))
      {
        error5(elem.getId());
      }

      names.put(elem, name(elem.getId().getText()));
    }
  }

  public void outAProductions(AProductions prod)
  {
    prod.apply(new DepthFirstAdapter()
               {
                 public void caseAElem(AElem node)
                 {
                   String name = name(node.getId().getText());

                   if(node.getSpecifier() != null)
                   {
                     if(node.getSpecifier() instanceof ATokenSpecifier)
                     {
                       if(tokens.get("T" + name) == null)
                       {
                         error2(node.getId(), "T" + name);
                       }

                       if(ignTokens.get("T" + name) != null)
                       {
                         error3(node.getId(), "T" + name);
                       }

                       elemTypes.put(node, "T" + name);
                     }
                     else
                     {
                       if(prods.get("P" + name) == null)
                       {
                         error2(node.getId(), "P" + name);
                       }

                       elemTypes.put(node, "P" + name);
                     }
                   }
                   else
                   {
                     Object token = tokens.get("T" + name);
                     Object ignToken = ignTokens.get("T" + name);
                     Object production = prods.get("P" + name);

                     if((token == null) && (production == null))
                     {
                       error2(node.getId(), "P" + name + " and T" + name);
                     }

                     if(token != null)
                     {
                       if(production != null)
                       {
                         error4(node.getId(), "P" + name + " and T" + name);
                       }

                       if(ignToken != null)
                       {
                         error3(node.getId(), "T" + name);
                       }

                       elemTypes.put(node, "T" + name);
                     }
                     else
                     {
                       elemTypes.put(node, "P" + name);
                     }
                   }
                 }
               }
              );
  }

  public void defaultcase(Node node)
  {
    if(node instanceof Token)
    {
      Token t = (Token) node;
      lastLine = t.getLine();
      lastPos = t.getPos() + t.getText().length();
    }
  }

  public static String name(String s)
  {
    StringBuffer result = new StringBuffer();
    boolean upcase = true;
    int length = s.length();
    char c;

    for(int i = 0; i < length; i++)
    {
      c = s.charAt(i);
      switch(c)
      {
      case '_':
        upcase = true;
        break;
      default:
        if(upcase)
        {
          result.append(Character.toUpperCase(c));
          upcase = false;
        }
        else
        {
          result.append(c);
        }
        break;
      }
    }

    return result.toString();
  }

  public static String errorName(String s)
  {
    StringBuffer result = new StringBuffer();
    int length = s.length();
    char c;

    for(int i = 0; i < length; i++)
    {
      c = s.charAt(i);
      switch(c)
      {
      case '_':
        {
          result.append(' ');
        }
        break;
      default:
        {
          result.append(c);
        }
        break;
      }
    }

    return result.toString();
  }

  private static void error(Token token, String name)
  {
    throw new RuntimeException(
      "[" + token.getLine() + "," + token.getPos() + "] " +
      "Redefinition of " + name + ".");
  }

  private void error(String name)
  {
    throw new RuntimeException(
      "[" + lastLine + "," + lastPos + "] " +
      "Redefinition of " + name + ".");
  }

  private static void error2(Token token, String name)
  {
    throw new RuntimeException(
      "[" + token.getLine() + "," + token.getPos() + "] " +
      name + " undefined.");
  }

  private static void error3(Token token, String name)
  {
    throw new RuntimeException(
      "[" + token.getLine() + "," + token.getPos() + "] " +
      name + " is ignored.");
  }

  private static void error4(Token token, String name)
  {
    throw new RuntimeException(
      "[" + token.getLine() + "," + token.getPos() + "] " +
      "ambiguous " + name + ".");
  }

  private static void error5(Token token)
  {
    throw new RuntimeException(
      "[" + token.getLine() + "," + token.getPos() + "] " +
      "class is an invalid element name.");
  }

  public String toString()
  {
    StringBuffer s = new StringBuffer();
    String nl = System.getProperty("line.separator");

    s.append("Helpers:");
    s.append(nl);
    s.append(helpers);
    s.append(nl);

    s.append("States:");
    s.append(nl);
    s.append(states);
    s.append(nl);

    s.append("Tokens:");
    s.append(nl);
    s.append(tokens);
    s.append(nl);

    s.append("Ignored Tokens:");
    s.append(nl);
    s.append(ignTokens);
    s.append(nl);

    s.append("Productions:");
    s.append(nl);
    s.append(prods);
    s.append(nl);

    s.append("Alternatives:");
    s.append(nl);
    s.append(alts);
    s.append(nl);

    s.append("Elements:");
    s.append(nl);
    s.append(elems);
    s.append(nl);

    return s.toString();
  }
  
    /**
     * @param analysisPkgName name of "analysis" package
     * @return root package name (parent of sablecc package) including
     * trailing dot
     */
    public static String getRootPackageName(String analysisPkgName) {
        // remove two trailing package name components, but keep the
        // last trailing dot
        int dotIndex = 0;
        String rootPackageName = analysisPkgName;
        dotIndex = rootPackageName.lastIndexOf('.');
        if ( dotIndex > -1 ) {
            rootPackageName = rootPackageName.substring(0, dotIndex);
        }
        dotIndex = rootPackageName.lastIndexOf('.');
        if ( dotIndex > -1 ) {
            // "+1" to keep last dot
            rootPackageName = rootPackageName.substring(0, dotIndex+1);
        }
        return rootPackageName;
    }
  
}

