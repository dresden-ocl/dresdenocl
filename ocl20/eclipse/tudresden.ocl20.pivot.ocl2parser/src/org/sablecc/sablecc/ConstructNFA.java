/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import java.util.*;

import org.sablecc.sablecc.CharSet;
import org.sablecc.sablecc.NFA;
import org.sablecc.sablecc.ResolveIds;
import org.sablecc.sablecc.analysis.*;
import org.sablecc.sablecc.node.*;


public class ConstructNFA extends DepthFirstAdapter
{
  private ResolveIds ids;
  private String stateName;

  private int i;

  ConstructNFA(ResolveIds ids, String stateName)
  {
    this.ids = ids;
    this.stateName = stateName;
  }

  public void outStart(Start node)
  {
    setOut(node, getOut(node.getPGrammar()));

    // free memory
    if(getOut(node.getPGrammar()) != null)
      setOut(node.getPGrammar(), null);
  }

  public void outAGrammar(AGrammar node)
  {
    setOut(node, getOut(node.getTokens()));

    // free memory
    if(getOut(node.getTokens()) != null)
      setOut(node.getTokens(), null);
  }

  public void outAHelperDef(AHelperDef node)
  {
    setOut(node, getOut(node.getRegExp()));

    // free memory
    if(getOut(node.getRegExp()) != null)
      setOut(node.getRegExp(), null);
  }

  public void outATokens(ATokens node)
  {
    ATokenDef[] tokenDefs = (ATokenDef[]) node.getTokenDefs().toArray(new ATokenDef[0]);
    NFA result = null;

    for(int i = tokenDefs.length - 1; i >= 0 ; i--)
    {
      NFA nfa = (NFA) getOut(tokenDefs[i]);
      if(nfa != null)
      {
        if(result == null)
        {
          result = nfa;
        }
        else
        {
          result = nfa.merge(result);
        }

        // free memory
        if(getOut(tokenDefs[i]) != null)
          setOut(tokenDefs[i], null);
      }
    }

    if(result != null)
      setOut(node, result);
  }

  public void outATokenDef(ATokenDef node)
  {
    Set set
      = (Set) getOut(node.getStateList());
    Object o1 = getOut(node.getRegExp());

    if((set
        == null) || (set.size() == 0) || set.contains(stateName))
    {
      //System.out.print("*");

      NFA n1 = (o1 instanceof NFA) ? (NFA) o1 : new NFA((CharSet) o1);
      String name = (String) ids.names.get(node);

      n1.states[n1.states.length - 1].accept = name;
      setOut(node, n1);
    }
    else
    {
      //System.out.print("-");
    }

    // free memory
    if(getOut(node.getStateList()) != null)
      setOut(node.getStateList(), null);
    if(getOut(node.getRegExp()) != null)
      setOut(node.getRegExp(), null);
  }

  public void outAStateList(AStateList node)
  {
    Set set
      = new TreeSet();
    AStateListTail[] stateListTails = (AStateListTail[]) node.getStateLists().toArray(new AStateListTail[0]);

    for(int i = stateListTails.length - 1; i >= 0 ; i--)
    {
      String str = stateListTails[i].getId().getText().toUpperCase();
      set.add(str);
    }

    set.add(node.getId().getText().toUpperCase());
    setOut(node, set
            );
  }

  public void outARegExp(ARegExp node)
  {
    ARegExpTail[] regExpTails = (ARegExpTail[]) node.getConcats().toArray(new ARegExpTail[0]);
    NFA result = null;

    for(int i = regExpTails.length - 1; i >= 0 ; i--)
    {
      Object o = getOut(regExpTails[i]);
      NFA nfa = (o instanceof NFA) ? (NFA) o : new NFA((CharSet) o);

      if(result == null)
      {
        result = nfa;
      }
      else
      {
        result = nfa.alternate(result);
      }

      // free memory
      if(getOut(regExpTails[i]) != null)
        setOut(regExpTails[i], null);
    }

    if(result != null)
    {
      Object o = getOut(node.getConcat());
      NFA nfa = (o instanceof NFA) ? (NFA) o : new NFA((CharSet) o);

      setOut(node, nfa.alternate(result));
    }
    else
    {
      setOut(node, getOut(node.getConcat()));
    }

    // free memory
    if(getOut(node.getConcat()) != null)
      setOut(node.getConcat(), null);
  }

  public void outARegExpTail(ARegExpTail node)
  {
    setOut(node, getOut(node.getConcat()));

    // free memory
    if(getOut(node.getConcat()) != null)
      setOut(node.getConcat(), null);
  }

  public void outAConcat(AConcat node)
  {
    AUnExp[] unExps = (AUnExp[]) node.getUnExps().toArray(new AUnExp[0]);

    if(unExps.length == 0)
    {
      setOut(node, new NFA());
    }
    else if(unExps.length == 1)
    {
      setOut(node, getOut(unExps[0]));

      // free memory
      if(getOut(unExps[0]) != null)
        setOut(unExps[0], null);
    }
    else
    {
      NFA result = null;

      for(int i = unExps.length - 1; i >= 0 ; i--)
      {
        Object o = getOut(unExps[i]);
        NFA nfa = (o instanceof NFA) ? (NFA) o : new NFA((CharSet) o);

        if(result == null)
        {
          result = nfa;
        }
        else
        {
          result = nfa.concatenate(result);
        }

        // free memory
        if(getOut(unExps[i]) != null)
          setOut(unExps[i], null);
      }

      setOut(node, result);
    }
  }

  public void outAUnExp(AUnExp node)
  {
    Object o = getOut(node.getBasic());

    char c = ' ';
    if(node.getUnOp() != null)
      c = ((Character) getOut(node.getUnOp())).charValue();

    switch(c)
    {
    case '*':
      {
        NFA n = (o instanceof NFA) ? (NFA) o : new NFA((CharSet) o);
        setOut(node, n.zeroOrMore());
      }
      break;
    case '?':
      {
        NFA n = (o instanceof NFA) ? (NFA) o : new NFA((CharSet) o);
        setOut(node, n.zeroOrOne());
      }
      break;
    case '+':
      {
        NFA n = (o instanceof NFA) ? (NFA) o : new NFA((CharSet) o);
        setOut(node, n.oneOrMore());
      }
      break;
    default:
      {
        setOut(node, o);
      }
      break;
    }

    // free memory
    if(getOut(node.getBasic()) != null)
      setOut(node.getBasic(), null);
    if(getOut(node.getUnOp()) != null)
      setOut(node.getUnOp(), null);
  }

  public void outACharBasic(ACharBasic node)
  {
    char c = ((Character) getOut(node.getChar())).charValue();
    setOut(node, new CharSet(c));

    // free memory
    if(getOut(node.getChar()) != null)
      setOut(node.getChar(), null);
  }

  public void outASetBasic(ASetBasic node)
  {
    setOut(node, getOut(node.getSet()));

    // free memory
    if(getOut(node.getSet()) != null)
      setOut(node.getSet(), null);
  }

  public void outAStringBasic(AStringBasic node)
  {
    String s = node.getString().getText();
    s = s.substring(1, s.length() -1);

    setOut(node, new NFA(s));
  }

  public void outAIdBasic(AIdBasic node)
  {
    Object o = getOut((Node) ids.helpers.get(node.getId().getText()));

    if(o instanceof NFA)
    {
      setOut(node, ((NFA) o).clone());
    }
    else
    {
      setOut(node, ((CharSet) o).clone());
    }
  }

  public void outARegExpBasic(ARegExpBasic node)
  {
    setOut(node, getOut(node.getRegExp()));

    // free memory
    if(getOut(node.getRegExp()) != null)
      setOut(node.getRegExp(), null);
  }

  public void outACharChar(ACharChar node)
  {
    setOut(node, new Character(node.getChar().getText().charAt(1)));
  }

  public void outADecChar(ADecChar node)
  {
    setOut(node, new Character((char) Integer.parseInt(node.getDecChar().getText())));
  }

  public void outAHexChar(AHexChar node)
  {
    setOut(node, new Character((char)
                               Integer.parseInt(node.getHexChar().getText().substring(2), 16)));
  }

  public void outAOperationSet(AOperationSet node)
  {
    try
    {
      CharSet cs1 = (CharSet) getOut(node.getLeft());
      CharSet cs2 = (CharSet) getOut(node.getRight());
      char binop = ((Character) getOut(node.getBinOp())).charValue();

      switch(binop)
      {
      case '+':
        {
          setOut(node, cs1.union(cs2));
        }
        break;
      case '-':
        {
          setOut(node, cs1.diff(cs2));
        }
        break;
      }
    }
    catch(Exception e)
    {
      throw new RuntimeException(node + " is invalid.");
    }

    // free memory
    if(getOut(node.getLeft()) != null)
      setOut(node.getLeft(), null);
    if(getOut(node.getBinOp()) != null)
      setOut(node.getBinOp(), null);
    if(getOut(node.getRight()) != null)
      setOut(node.getRight(), null);
  }

  public void outAIntervalSet(AIntervalSet node)
  {
    char c1 = ((Character) getOut(node.getLeft())).charValue();
    char c2 = ((Character) getOut(node.getRight())).charValue();

    if(c1 > c2)
    {
      throw new RuntimeException(node + " is invalid.");
    }

    setOut(node, new CharSet(c1, c2));

    // free memory
    if(getOut(node.getLeft()) != null)
      setOut(node.getLeft(), null);
    if(getOut(node.getRight()) != null)
      setOut(node.getRight(), null);
  }

  public void outAStarUnOp(AStarUnOp node)
  {
    setOut(node, new Character('*'));
  }

  public void outAQMarkUnOp(AQMarkUnOp node)
  {
    setOut(node, new Character('?'));
  }

  public void outAPlusUnOp(APlusUnOp node)
  {
    setOut(node, new Character('+'));
  }

  public void outAPlusBinOp(APlusBinOp node)
  {
    setOut(node, new Character('+'));
  }

  public void outAMinusBinOp(AMinusBinOp node)
  {
    setOut(node, new Character('-'));
  }

  public Object getOut(Node node)
  {
    if(node == null)
    {
      return null;
    }

    return super.getOut(node);
  }

  public void setOut(Node node, Object out)
  {
    if(node == null)
    {
      throw new NullPointerException();
    }

    super.setOut(node, out);
  }
}

