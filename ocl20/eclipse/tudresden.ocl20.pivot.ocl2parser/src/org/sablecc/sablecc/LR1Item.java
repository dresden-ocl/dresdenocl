/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import java.util.*;

import org.sablecc.sablecc.LR0Item;
import org.sablecc.sablecc.LR1Item;
import org.sablecc.sablecc.Production;
import org.sablecc.sablecc.Symbol;

final class LR1Item implements Cloneable, Comparable
{
  final LR0Item lr0Item;
  final int terminal;

  LR1Item(LR0Item lr0Item, int terminal)
  {
    this.lr0Item = lr0Item;
    this.terminal = terminal;
  }

  public Object clone()
  {
    return new LR1Item(lr0Item, terminal);
  }

  public boolean equals(Object obj)
  {
    if((obj == null) ||
        (obj.getClass() != this.getClass()))
    {
      return false;
    }

    LR1Item item = (LR1Item) obj;

    return (item.lr0Item.equals(lr0Item)) &&
           (item.terminal == terminal);
  }

  public int hashCode()
  {
    return lr0Item.hashCode() * (terminal + 1) * 37;
  }

  public String toString()
  {
    return lr0Item + ":" + Symbol.symbol(terminal, true);
  }

  public String toString(Symbol lookahead)
  {
    // two cases:
    // (1) we are facing a reduction, and the lookahed
    //     should match
    // (2) we are in the middle of a production.  The
    //     next element should match.

    Symbol[] rightside = Production.production(lr0Item.production).rightside();

    if(lr0Item.position == rightside.length)
    {
      Symbol term = Symbol.symbol(terminal, true);

      if(term == lookahead)
      {
        return lr0Item + " followed by " + term + " (reduce)";
      }
      else
      {
        return null;
      }
    }

    if(rightside[lr0Item.position] == lookahead)
    {
      return lr0Item + " (shift)";
    }
    else
    {
      return null;
    }
  }

  public int compareTo(Object object)
  {
    LR1Item item = (LR1Item) object;

    int result = lr0Item.compareTo(item.lr0Item);

    if(result == 0)
    {
      result = terminal - item.terminal;
    }

    return result;
  }
}

