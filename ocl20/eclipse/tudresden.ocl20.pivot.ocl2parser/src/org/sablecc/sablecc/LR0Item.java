/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import java.util.*;

import org.sablecc.sablecc.LR0Item;
import org.sablecc.sablecc.Production;

final class LR0Item implements Cloneable, Comparable
{
  final int production;
  final int position;

  LR0Item(int production, int position)
  {
    this.production = production;
    this.position = position;
  }

  public int compareTo(Object object)
  {
    LR0Item item = (LR0Item) object;

    int result = production - item.production;

    if(result == 0)
    {
      result = position - item.position;
    }

    return result;
  }

  public Object clone()
  {
    return new LR0Item(production, position);
  }

  public boolean equals(Object obj)
  {
    if((obj == null) ||
        (obj.getClass() != this.getClass()))
    {
      return false;
    }

    LR0Item item = (LR0Item) obj;

    return (item.production == production) &&
           (item.position == position);
  }

  public int hashCode()
  {
    return (production * 13) ^ (position * 17);
  }

  public String toString()
  {
    StringBuffer result = new StringBuffer();
    String prodStr = (Production.production(production)).toString();
    int pos = 0;

    StringTokenizer list = new StringTokenizer(prodStr, "= ");

    // we know that there is at least one token (lhs)
    result.append(list.nextToken());
    result.append(" =");

    while(list.hasMoreElements())
    {
      String tmp = list.nextToken();

      if(pos == position)
      {
        result.append(" * ");
        result.append(tmp);
      }
      else
      {
        result.append(" ");
        result.append(tmp);
      }

      pos++;
    }

    if(pos == position)
    {
      result.append(" *");
    }

    return "[ " + result + " ]";
  }
}

