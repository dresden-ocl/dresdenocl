/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import java.util.*;

import java.util.Vector;

import org.sablecc.sablecc.LR0Item;
import org.sablecc.sablecc.LR0ItemSet;
import org.sablecc.sablecc.Production;

final class LR0ItemSet implements Cloneable, Comparable
{
  private final TreeMap items;
  private int hashCode;

  LR0ItemSet()
  {
    items = new TreeMap();
  }

  private LR0ItemSet(LR0ItemSet set
                      )
  {
    items = (TreeMap) set.items.clone();
  }

  void set
    (LR0Item item)
  {
    if(items.put(item, item) == null)
    {
      hashCode += item.hashCode();
      modified_ = true;
    }
  }

  boolean get
    (LR0Item item)
  {
    return items.get(item) != null;
  }

  LR0Item[] items_;
  boolean modified_ = true;

  private void computeArray()
  {
    Vector itemVector = new Vector(0);

    for(Iterator e = items.keySet().iterator(); e.hasNext();)
    {
      itemVector.addElement(e.next());
    }

    items_ = new LR0Item[itemVector.size()];
    itemVector.copyInto(items_);
    modified_ = false;
  }

  LR0Item[] items()
  {
    if(modified_)
    {
      computeArray();
    }

    return items_;
  }

  public String toString()
  {
    StringBuffer result = new StringBuffer();
    result.append("{");

    Production[] productions = Production.productions();
    boolean space = false;
    for(int i = 0; i < productions.length; i++)
    {
      int rightsideLength = productions[i].rightside().length;

      for(int j = 0; j <= rightsideLength; j++)
      {
        LR0Item item = new LR0Item(productions[i].index, j);
        if(get
            (item))
        {
          if(space)
          {
            result.append(",");
          }
          else
          {
            space = true;
          }

          result.append(item);
        }
      }

    }

    result.append("}");
    return result.toString();
  }

  public Object clone()
  {
    return new LR0ItemSet(this);
  }

  public boolean equals(Object obj)
  {
    if((obj == null) ||
        (obj.getClass() != this.getClass()))
    {
      return false;
    }

    LR0ItemSet set
      = (LR0ItemSet) obj;

    if(set.items.size() != items.size())
    {
      return false;
    }

    for(Iterator e = items.keySet().iterator(); e.hasNext();)
    {
      if(!set.get((LR0Item) e.next()))
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
    LR0ItemSet set
      = (LR0ItemSet) object;

    int result = items.size() - set.items.size();

    if(result == 0)
    {
      Iterator e = items.keySet().iterator();
      Iterator f = set.items.keySet().iterator();

      while(e.hasNext() && f.hasNext() && (result == 0))
      {
        result = ((LR0Item) e.next()).compareTo(f.next());
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

