/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import java.util.*;

import org.sablecc.sablecc.StringComparator;
import org.sablecc.sablecc.Symbol;

final class Symbol implements Comparable
{
  private static Vector terminals;
  private static Vector nonterminals;
  private static TreeMap names;

  private static boolean modified_ = true;
  private static Symbol[] symbols_;
  private static Symbol[] terminals_;
  private static Symbol[] nonterminals_;

  final String name;
  final String errorName;
  final boolean terminal;
  final int index;

  static {
    reinit();
  }

  Symbol(String name, String errorName, boolean terminal)
  {
    if(names.get(name) != null)
    {
      throw new IllegalArgumentException("The symbol " + name + " aready exists.");
    }

    if(terminal)
    {
      terminals.addElement(this);
      this.index = terminals.indexOf(this);
    }
    else
    {
      nonterminals.addElement(this);
      this.index = nonterminals.indexOf(this);
    }

    this.name = name;
    this.errorName = errorName;
    this.terminal = terminal;
    names.put(name, this);
    modified_ = true;
  }

  public static void reinit()
  {
    terminals = new Vector();
    nonterminals = new Vector();
    names = new TreeMap(StringComparator.instance);
    modified_ = true;
    symbols_ = null;
    terminals_ = null;
    nonterminals_ = null;
  }

  static Symbol symbol(String name)
  {
    return (Symbol) names.get(name);
  }

  static Symbol symbol(int index, boolean terminal)
  {
    if(terminal)
    {
      return (Symbol) terminals.elementAt(index);
    }
    else
    {
      return (Symbol) nonterminals.elementAt(index);
    }
  }

  private static void computeArrays()
  {
    symbols_ = new Symbol[terminals.size() + nonterminals.size()];
    terminals_ = new Symbol[terminals.size()];
    nonterminals_ = new Symbol[nonterminals.size()];

    terminals.copyInto(terminals_);
    nonterminals.copyInto(nonterminals_);
    System.arraycopy(terminals_, 0, symbols_, 0, terminals_.length);
    System.arraycopy(nonterminals_, 0, symbols_, terminals_.length, nonterminals_.length);

    modified_ = false;
  }

  static Symbol[] symbols()
  {
    if(modified_)
    {
      computeArrays();
    }

    return symbols_;
  }

  static Symbol[] terminals()
  {
    if(modified_)
    {
      computeArrays();
    }

    return terminals_;
  }

  static Symbol[] nonterminals()
  {
    if(modified_)
    {
      computeArrays();
    }

    return nonterminals_;
  }

  public String toString()
  {
    return name;
  }

  public int compareTo(Object object)
  {
    Symbol symbol = (Symbol) object;

    if(terminal ^ symbol.terminal)
    {
      if(terminal)
      {
        return 1;
      }

      return -1;
    }

    return index - symbol.index;
  }
}

