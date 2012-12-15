/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import java.util.*;

import org.sablecc.sablecc.IntegerComparator;
import org.sablecc.sablecc.Production;
import org.sablecc.sablecc.Symbol;



final class Production
{
  final int leftside;
  final int index;
  final String name;

  private final Vector rightside = new Vector();
  private static final Vector productions = new Vector(0);
  private static TreeMap alternatives_ = new TreeMap(IntegerComparator.instance);
  private static boolean modified_ = true;
  private static Production[] productions_;

  public static void reinit()
  {
    productions.removeAllElements();
    alternatives_ = new TreeMap(IntegerComparator.instance);
    productions_ = null;
    modified_ = true;
    productions_ = null;
  }

  private static void computeArray_()
  {
    productions_ = new Production[productions.size()];
    productions.copyInto(productions_);
    modified_ = false;
  }

  private boolean modified = true;
  private Symbol[] rightside_;

  private void computeArray()
  {
    rightside_ = new Symbol[rightside.size()];
    rightside.copyInto(rightside_);
    modified = false;
  }

  Production(int leftside, String name)
  {
    productions.addElement(this);

    this.name = name;
    this.leftside = leftside;
    this.index = productions.indexOf(this);
    modified_ = true;
  }

  Symbol[] rightside()
  {
    if(modified)
    {
      computeArray();
    }

    return rightside_;
  }

  void addSymbol(Symbol s)
  {
    rightside.addElement(s);
    modified = true;
    modified_ = true;
  }

  Symbol rightside(int index)
  {
    return (Symbol) rightside.elementAt(index);
  }

  static Production production(int index)
  {
    return (Production) productions.elementAt(index);
  }

  static Production[] alternatives(int nonterminal)
  {
    if(modified_)
    {
      alternatives_ = new TreeMap(IntegerComparator.instance);
    }

    Production[] result = (Production[]) alternatives_.get(new Integer(nonterminal));

    if(result == null)
    {
      Vector alternatives = new Vector(0);

      for(Enumeration e = productions.elements(); e.hasMoreElements();)
      {
        Production production = (Production) e.nextElement();

        if(production.leftside == nonterminal)
        {
          alternatives.addElement(production);
        }
      }

      result = new Production[alternatives.size()];
      alternatives.copyInto(result);

      alternatives_.put(new Integer(nonterminal), result);
    }

    return result;
  }

  static Production[] productions()
  {
    if(modified_)
    {
      computeArray_();
    }

    return productions_;
  }

  public String toString()
  {
    StringBuffer result = new StringBuffer();

    result.append(Symbol.symbol(leftside, false));

    result.append(" = ");

    Symbol[] symbols = rightside();
    boolean space = false;
    for(int i = 0; i < symbols.length; i++)
    {
      if(space)
      {
        result.append(" ");
      }
      else
      {
        space = true;
      }

      result.append(symbols[i]);
    }

    return result.toString();
  }
}

