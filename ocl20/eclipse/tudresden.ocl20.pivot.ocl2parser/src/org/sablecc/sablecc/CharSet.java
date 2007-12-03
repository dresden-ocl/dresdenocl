/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import java.util.*;

import org.sablecc.sablecc.Cast;
import org.sablecc.sablecc.CharSet;

public class CharSet implements Cloneable
{
  private final Vector intervals = new Vector(0);

  public CharSet(char c)
  {
    intervals.addElement(new Interval(c, c));
  }

  public CharSet(char start, char end)
  {
    intervals.addElement(new Interval(start, end));
  }

  private CharSet(Vector intervals)
  {
    for(Enumeration e = intervals.elements(); e.hasMoreElements();)
    {
      this.intervals.addElement(((Interval) e.nextElement()).clone());
    }
  }

  public Object clone()
  {
    return new CharSet(intervals);
  }

  public Interval findOverlap(Interval interval1)
  {
    int low = 0;
    int high = intervals.size() - 1;
    Interval interval2;
    Interval result = null;

    while(high >= low)
    {
      int middle = (high + low) / 2;

      interval2 = (Interval) intervals.elementAt(middle);

      if(interval1.start <= interval2.end)
      {
        if(interval1.end >= interval2.start)
        {
          result = interval2;
          // we continue, to find the lowest matching interval!
        }

        high = middle - 1;
      }
      else
      {
        low = middle + 1;
      }
    }

    return result;
  }

  private void remove
    (Interval interval)
  {
    intervals.removeElement(interval);
  }

  private void add
    (Interval interval)
  {
    for(int i = 0; i < intervals.size(); i++)
    {
      Interval iv = (Interval) intervals.elementAt(i);

      if(iv.start > interval.start)
      {
        intervals.insertElementAt(interval, i);
        return;
      }
    }

    intervals.addElement(interval);
  }

  public CharSet union(CharSet chars)
  {
    CharSet result = (CharSet) clone();

    Interval interval;
    Interval largeInterval;
    Interval overlap;

    for(Enumeration e = chars.intervals.elements(); e.hasMoreElements();)
    {
      interval = (Interval) ((Interval) e.nextElement()).clone();

      do
      {
        largeInterval = new Interval(
                          (interval.start == 0) ? (char) 0 : (char) (interval.start - 1),
                          (interval.end == 0xffff) ? (char) 0xffff : (char) (interval.end + 1));

        overlap = result.findOverlap(largeInterval);
        if(overlap != null)
        {
          result.remove(overlap);
          interval.start = (char) Math.min(interval.start, overlap.start);
          interval.end = (char) Math.max(interval.end, overlap.end);
        }
      }
      while(overlap != null);

      result.add(interval);
    }

    return result;
  }

  public CharSet diff(CharSet chars)
  {
    CharSet result = (CharSet) clone();

    Interval interval;
    Interval overlap;

    for(Enumeration e = chars.intervals.elements(); e.hasMoreElements();)
    {
      interval = (Interval) ((Interval) e.nextElement()).clone();

      do
      {
        overlap = result.findOverlap(interval);
        if(overlap != null)
        {
          result.remove(overlap);
          if(overlap.start < interval.start)
          {
            result.add(new Interval(overlap.start, (char) (interval.start - 1)));
          }
          if(overlap.end > interval.end)
          {
            result.add(new Interval((char) (interval.end + 1), overlap.end));
          }
        }
      }
      while(overlap != null);
    }

    return result;
  }

  public String toString()
  {
    StringBuffer result = new StringBuffer();

    for(Enumeration e = intervals.elements(); e.hasMoreElements();)
    {
      result.append("[" + e.nextElement() + "] ");
    }

    return "" + result;
  }

  public static class Interval implements Cloneable
  {
    public Interval(char start, char end)
    {
      this.start = start;
      this.end = end;
    }

    public Object clone()
    {
      return new Interval(start, end);
    }

    private String c(char c)
    {
      if((c >= 32) && (c < 127))
      {
        return "" + c;
      }

      return "" + ((int) c);
    }

    public String toString()
    {
      if(start < end)
      {
        return c(start) + " .. " + c(end);
      }
      else
      {
        return c(start);
      }
    }

    public char start;
    public char end;
  }

  public static class IntervalCast implements Cast
  {
    public final static IntervalCast instance = new IntervalCast();

    private IntervalCast()
    {}

    public Object cast(Object o)
    {
      return (Interval) o;
    }
  }
}

