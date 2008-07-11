/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import java.util.*;

import org.sablecc.sablecc.Grammar;
import org.sablecc.sablecc.LR0Collection;
import org.sablecc.sablecc.LR0Item;
import org.sablecc.sablecc.LR0ItemAndSetPair;
import org.sablecc.sablecc.LR0ItemSet;
import org.sablecc.sablecc.LR1Item;
import org.sablecc.sablecc.LR1ItemSet;
import org.sablecc.sablecc.Production;
import org.sablecc.sablecc.Symbol;
import org.sablecc.sablecc.SymbolSet;

final class LR1Collection
{
  final LR0Collection collection;
  final TreeMap[] lookaheads;
  private final TreeMap[] propagation;

  LR1Collection(LR0ItemSet set
                 )
  {
    collection = new LR0Collection(set
                                  );

    // Initialize lookaheads to nothing, propagation to nothing
    LR0ItemSet[] sets = collection.sets();
    lookaheads = new TreeMap[sets.length];
    propagation = new TreeMap[sets.length];

    for(int i = 0; i < sets.length; i++)
    {
      System.out.print(".");
      lookaheads[i] = new TreeMap();
      propagation[i] = new TreeMap();

      LR0Item[] items = sets[i].items();
      for(int j = 0; j < items.length; j++)
      {
        lookaheads[i].put(items[j], new SymbolSet());
        propagation[i].put(items[j], new Vector(0));
      }
    }
    System.out.println();

    ((SymbolSet) lookaheads[0].get(set.items()[0])).setTerminal(Grammar.eof);

    for(int i = 0; i < sets.length; i++)
    {
      System.out.print(".");
      LR0Item[] items = sets[i].items();

      for(int j = 0; j < items.length; j++)
      {
        LR1ItemSet lr1Set = new LR1ItemSet();
        lr1Set.set(new LR1Item(items[j], Grammar.dummy));

        LR1Item[] closure = Grammar.CLOSURE(lr1Set).items();

        for(int k = 0; k < closure.length; k++)
        {
          if(closure[k].terminal != Grammar.dummy)
          {
            Symbol[] rightside = Production.
                                 production(closure[k].lr0Item.production).
                                 rightside();

            if(closure[k].lr0Item.position < rightside.length)
            {
              Integer destination = collection.GOTO(i,
                                                    rightside[closure[k].lr0Item.position]);

              if(destination != null)
              {

                ((SymbolSet) lookaheads[destination.intValue()].
                 get
                   (new LR0Item(closure[k].lr0Item.production,
                                closure[k].lr0Item.position + 1))).
                  setTerminal(closure[k].terminal);

                /*((SymbolSet) lookaheads[collection.GOTO(i,
                Production.production(closure[k].lr0Item.production).
                rightside(closure[k].lr0Item.position)).intValue()].
                get(new LR0Item(closure[k].lr0Item.production,
                closure[k].lr0Item.position + 1))).
                setTerminal(closure[k].terminal);*/
              }
            }
          }
          else
          {
            Symbol[] rightside = Production.
                                 production(closure[k].lr0Item.production).
                                 rightside();

            if(closure[k].lr0Item.position < rightside.length)
            {
              Integer destination = collection.GOTO(i,
                                                    rightside[closure[k].lr0Item.position]);

              if(destination != null)
              {
                ((Vector) propagation[i].get(items[j])).
                addElement(new LR0ItemAndSetPair(
                             new LR0Item(closure[k].lr0Item.production,
                                         closure[k].lr0Item.position + 1),
                             destination.intValue()));

                /*((Vector) propagation[i].get(items[j])).
                    addElement(new LR0ItemAndSetPair(
                    new LR0Item(closure[k].lr0Item.production,
                    closure[k].lr0Item.position + 1),
                    collection.GOTO(i,
                    Production.production(closure[k].lr0Item.production).
                    rightside(closure[k].lr0Item.position)).intValue()));*/
              }
            }
          }
        }
      }
    }
    System.out.println();

    boolean changed;
    do
    {
      System.out.print(".");
      changed = false;
      for(int i = 0; i < sets.length; i++)
      {
        LR0Item[] items = sets[i].items();

        for(int j = 0; j < items.length; j++)
        {
          for(Enumeration e = ((Vector) propagation[i].get(items[j])).
                              elements(); e.hasMoreElements();)
          {
            LR0ItemAndSetPair pair = (LR0ItemAndSetPair) e.nextElement();

            SymbolSet before = (SymbolSet)
                               ((SymbolSet) lookaheads[pair.set].get(pair.item)).clone();

            ((SymbolSet) lookaheads[pair.set].get(pair.item)).
            or((SymbolSet) lookaheads[i].get(items[j]));

            if(!before.equals(lookaheads[pair.set].get(pair.item)))
            {
              changed = true;
            }
          }
        }
      }
    }
    while(changed);
    System.out.println();
  }

  public String toString()
  {
    StringBuffer result = new StringBuffer();

    result.append(collection);
    result.append(System.getProperty("line.separator"));

    result.append("Lookaheads" + System.getProperty("line.separator"));
    LR0ItemSet[] sets = collection.sets();

    for(int i = 0; i < sets.length; i++)
    {
      result.append(i + ":" + System.getProperty("line.separator"));
      LR0Item[] items = sets[i].items();

      for(int j = 0; j < items.length; j++)
      {
        result.append(items[j] + ":" + lookaheads[i].get(items[j]) +
                      System.getProperty("line.separator"));
      }
    }

    return result.toString();
  }
}

