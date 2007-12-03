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
import org.sablecc.sablecc.LR1ItemSet;
import org.sablecc.sablecc.Production;
import org.sablecc.sablecc.Symbol;

final class LR1ItemSet implements Cloneable, Comparable
{
  private final TreeMap items;
  private int hashCode = 0;

  LR1ItemSet()
  {
    this.items = new TreeMap();
  }

  private LR1ItemSet(LR1ItemSet set
                      )
  {
    this.items = (TreeMap) set.items.clone();
    this.hashCode = set.hashCode;
  }

  void set
    (LR1Item item)
  {
    if(items.put(item, item) == null)
    {
      hashCode += item.hashCode();
      modified_ = true;
    }
  }

  boolean get
    (LR1Item item)
  {
    return items.get(item) != null;
  }

  LR1Item[] items_;
  boolean modified_ = true;

  private void computeArray()
  {
    Vector itemVector = new Vector(0);

    for(Iterator e = items.keySet().iterator(); e.hasNext();)
    {
      itemVector.addElement(e.next());
    }

    items_ = new LR1Item[itemVector.size()];
    itemVector.copyInto(items_);
    modified_ = false;
  }

  LR1Item[] items()
  {
    if(modified_)
    {
      computeArray();
    }

    return items_;
  }

  public String toString()
  {
    String nl = System.getProperty("line.separator");

    StringBuffer result = new StringBuffer();
    result.append("{" + nl + "\t");

    Production[] productions = Production.productions();
    Symbol[] terminals = Symbol.terminals();
    boolean comma = false;
    for(int i = 0; i < productions.length; i++)
    {
      int rightsideLength = productions[i].rightside().length;

      for(int j = 0; j <= rightsideLength; j++)
      {
        LR0Item lr0Item = new LR0Item(productions[i].index, j);

        for(int k = 0; k < terminals.length; k++)
        {
          LR1Item item = new LR1Item(lr0Item, terminals[k].index);
          if(get
              (item))
          {
            if(comma)
            {
              result.append("," + nl + "\t");
            }
            else
            {
              comma = true;
            }

            result.append(item);
          }
        }
      }
    }

    result.append(nl + "}");
    return result.toString();
  }

  public String toString(Symbol lookahead)
  {
    String nl = System.getProperty("line.separator");

    LR1Item[] items = items();
    int length = items.length;

    TreeSet strings = new TreeSet();

    for(int i = 0; i < length; i++)
    {
      String s = items[i].toString(lookahead);

      if(s != null)
      {
        strings.add(s);
      }
    }

    StringBuffer result = new StringBuffer();
    result.append("{");

    boolean colon = false;
    for(Iterator i = strings.iterator(); i.hasNext(); )
    {
      if(colon)
      {
        result.append(",");
        result.append(nl);
      }
      else
      {
        colon = true;
        result.append(nl);
      }

      result.append("\t");
      result.append(i.next());
    }

    result.append(nl);
    result.append("}");

    return result.toString();
  }

  public Object clone()
  {
    return new LR1ItemSet(this);
  }

  public boolean equals(Object obj)
  {
    if((obj == null) ||
        (obj.getClass() != this.getClass()))
    {
      return false;
    }

    LR1ItemSet set
      = (LR1ItemSet) obj;

    if(set.items.size() != items.size())
    {
      return false;
    }

    for(Iterator e = items.keySet().iterator(); e.hasNext();)
    {
      if(!set.get((LR1Item) e.next()))
      {
        return false;
      }
    }

    return true;
  }

  public int hashCode()
  {
    return hashCode;
  }

  public int compareTo(Object object)
  {
    LR1ItemSet set
      = (LR1ItemSet) object;

    int result = items.size() - set.items.size();

    if(result == 0)
    {
      Iterator e = items.keySet().iterator();
      Iterator f = set.items.keySet().iterator();

      while(e.hasNext() && f.hasNext() && (result == 0))
      {
        result = ((LR1Item) e.next()).compareTo(f.next());
      }

      if(result == 0)
      {
        if(e.hasNext())
        {
          return 1;
        }

        if(f.hasNext())
        {
          return -1;
        }
      }
    }

    return result;
  }
}

