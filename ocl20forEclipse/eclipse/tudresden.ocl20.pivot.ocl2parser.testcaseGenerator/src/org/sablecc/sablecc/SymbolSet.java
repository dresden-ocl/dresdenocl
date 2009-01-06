/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import java.util.*;

import org.sablecc.sablecc.IntSet;
import org.sablecc.sablecc.Symbol;
import org.sablecc.sablecc.SymbolSet;



final class SymbolSet implements Cloneable
{
  private final IntSet terminals;
  private final IntSet nonterminals;
  private boolean empty;

  private boolean modified = true;
  private Symbol[] symbols;

  private void computeArray()
  {
    Vector symbols = new Vector(0);

    int[] elements = terminals.elements();
    for(int i = 0; i < elements.length; i++)
    {
      symbols.addElement(Symbol.symbol(elements[i], true));
    }

    elements = nonterminals.elements();
    for(int i = 0; i < elements.length; i++)
    {
      symbols.addElement(Symbol.symbol(elements[i], false));
    }

    this.symbols = new Symbol[symbols.size()];
    symbols.copyInto(this.symbols);

    modified = false;
  }

  SymbolSet()
  {
    this.terminals = new IntSet();
    this.nonterminals= new IntSet();
  }

  private SymbolSet(SymbolSet set
                     )
  {
    this.terminals = (IntSet) set.terminals.clone();
    this.nonterminals = (IntSet) set.nonterminals.clone();
    this.empty = set.empty;
  }

  void setTerminal(int terminal)
  {
    terminals.set(terminal);
    modified = true;
  }

  void clearTerminal(int terminal)
  {
    terminals.clear(terminal);
    modified = true;
  }

  boolean getTerminal(int terminal)
  {
    return terminals.get(terminal);
  }

  void setNonterminal(int nonterminal)
  {
    nonterminals.set(nonterminal);
    modified = true;
  }

  void clearNonterminal(int nonterminal)
  {
    nonterminals.clear(nonterminal);
    modified = true;
  }

  boolean getNonterminal(int nonterminal)
  {
    return nonterminals.get(nonterminal);
  }

  void setEmpty()
  {
    empty = true;
    modified = true;
  }

  void clearEmpty()
  {
    empty = false;
    modified = true;
  }

  boolean getEmpty()
  {
    return empty;
  }

  void and(SymbolSet s)
  {
    terminals.and(s.terminals);
    nonterminals.and(s.nonterminals);
    empty &= s.empty;
    modified = true;
  }

  void or(SymbolSet s)
  {
    terminals.or(s.terminals);
    nonterminals.or(s.nonterminals);
    empty |= s.empty;
    modified = true;
  }

  void xor(SymbolSet s)
  {
    terminals.xor(s.terminals);
    nonterminals.xor(s.nonterminals);
    empty ^= s.empty;
    modified = true;
  }

  public int hashCode()
  {
    return terminals.hashCode() + nonterminals.hashCode() + new Boolean(empty).hashCode();
  }

  public boolean equals(Object obj)
  {
    if((obj == null) ||
        (obj.getClass() != this.getClass()))
    {
      return false;
    }

    SymbolSet s = (SymbolSet) obj;

    return terminals.equals(s.terminals) &&
           nonterminals.equals(s.nonterminals) &&
           (empty == s.empty);
  }

  Symbol[] getSymbols()
  {
    if(modified)
    {
      computeArray();
    }

    return symbols;
  }

  public String toString()
  {
    StringBuffer result = new StringBuffer();
    result.append("{");

    Symbol[] symbols = getSymbols();
    boolean comma = false;
    for(int i = 0; i < symbols.length; i++)
    {
      if(comma)
      {
        result.append(",");
      }
      else
      {
        comma = true;
      }

      result.append(symbols[i]);
    }

    if(empty)
    {
      if(comma)
      {
        result.append(",");
      }
      else
      {
        comma = true;
      }

      result.append("*empty*");
    }

    result.append("}");

    return result.toString();
  }

  public Object clone()
  {
    return new SymbolSet(this);
  }
}

