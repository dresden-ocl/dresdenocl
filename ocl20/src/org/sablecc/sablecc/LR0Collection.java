/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import java.util.*;
import java.util.Vector;

final class LR0Collection
{
  private final Vector sets = new Vector(0);
  private final TreeMap setIndices = new TreeMap();
  private final Vector GOTO = new Vector(0);
  final Vector names = new Vector(0);

  LR0Collection(LR0ItemSet set
                 )
  {
    add
      (set
          , -1, null);

    for(int i = 0; i < sets.size(); i++)
    {
      System.out.print(".");
      Symbol[] symbols = Symbol.symbols();

      for(int j = 0; j < symbols.length; j++)
      {
        addGoto(i, symbols[j], Grammar.GOTO(set
                                            (i), symbols[j]));
      }
    }
    System.out.println();
  }

  private int add
    (LR0ItemSet set
          , int from, Symbol symbol)
    {
      Integer result = set
                         (set
                         );

      if(result == null)
      {
        result = new Integer(sets.size());

        setIndices.put(set
                       , result);
        sets.addElement(set
                       );
        GOTO.addElement(new TreeMap());
        if(from == -1)
        {
          names.addElement(" ");
        }
        else
        {
          names.addElement(names.elementAt(from) + "" + symbol + " ");
        }
      }

      return result.intValue();
    }

  private static LR0ItemSet empty = new LR0ItemSet();

  public static void reinit()
  {
    empty = new LR0ItemSet();
  }

  private void addGoto(int from, Symbol symbol, LR0ItemSet to)
  {
    if(!to.equals(empty))
    {
      ((TreeMap) GOTO.elementAt(from)).put(symbol, new Integer(add
                                           (to, from, symbol)));
    }
  }

  private Integer set
    (LR0ItemSet set
      )
    {
      return (Integer) setIndices.get(set
                                     );
    }

  private LR0ItemSet set
    (int index)
  {
    return (LR0ItemSet) sets.elementAt(index);
  }

  LR0ItemSet[] sets()
  {
    LR0ItemSet[] result = new LR0ItemSet[sets.size()];
    sets.copyInto(result);

    return result;
  }

  Integer GOTO(int set
                 , Symbol symbol)
  {
    return (Integer) ((TreeMap) GOTO.elementAt(set
                                              )).get(symbol);
  }

  public String toString()
  {
    StringBuffer result = new StringBuffer();

    result.append("{[LR0ItemCollection]" + System.getProperty("line.separator"));
    LR0ItemSet[] sets = sets();
    Symbol[] symbols = Symbol.symbols();

    for(int i = 0; i < sets.length; i++)
    {
      result.append(i + ":" + Grammar.CLOSURE(sets[i]));
      result.append(System.getProperty("line.separator"));

      for(int j = 0; j < symbols.length; j++)
      {
        if(GOTO(i, symbols[j]) != null)
        {
          result.append("[");
          result.append(symbols[j]);
          result.append(":");
          result.append(GOTO(i, symbols[j]));
          result.append("]");
        }
      }

      result.append(System.getProperty("line.separator"));
    }

    result.append("}");
    return result.toString();
  }
}

