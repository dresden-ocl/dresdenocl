/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

public class IntSet
{
  //    public static boolean DEBUG = false;
  private int[] elements = new int[0];

  public IntSet()
  {}

  private IntSet(IntSet set
                  )
  {
    elements = (int[]) set.elements.clone();
  }

  public void and(IntSet set
                   )
  {
    if(set
        == this)
    {
      return;
    }

    int length = 0;

    int l = 0;
    int r = 0;
    while((l < elements.length) && (r < set.elements.length))
    {
      if(elements[l] < set.elements[r])
      {
        l++;
      }
      else if(elements[l] == set.elements[r])
      {
        length++;
        l++;
        r++;
      }
      else
      {
        r++;
      }
    }

    int[] old = elements;
    elements = new int[length];

    length = l = r = 0;
    while((l < old.length) && (r < set.elements.length))
    {
      if(old[l] < set.elements[r])
      {
        l++;
      }
      else if(old[l] == set.elements[r])
      {
        elements[length++] = old[l];
        l++;
        r++;
      }
      else
      {
        r++;
      }
    }

  }

  public void clear(int  bit)
  {
    elements = new int[0];
  }

  public Object clone()
  {
    return new IntSet(this);
  }

  public boolean equals(Object  obj)
  {
    if(obj == null)
    {
      return false;
    }

    if(!(obj instanceof IntSet))
    {
      return false;
    }

    IntSet set
      = (IntSet) obj;

    if(elements.length != set.elements.length)
    {
      return false;
    }

    for(int i = 0; i < elements.length; i++)
    {
      if(elements[i] != set.elements[i])
      {
        return false;
      }
    }

    return true;
  }

  public boolean get
    (int  bit)
  {
    int low = 0;
    int high = elements.length - 1;

    while(low <= high)
    {
      int middle = (low + high) / 2;

      if(bit < elements[middle])
      {
        high = middle - 1;
      }
      else if(bit == elements[middle])
      {
        return true;
      }
      else
      {
        low = middle + 1;
      }
    }

    return false;
  }

  public int hashCode()
  {
    int result = 0;

    for(int i = 0; i < elements.length; i++)
    {
      result += elements[i];
    }

    return result;
  }

  public void or(IntSet  set
                  )
  {
    if(set
        == this)
    {
      return;
    }

    //        if(DEBUG)
    //        {
    //            System.out.println(
    //            " elements.length:" + elements.length +
    //            " set.elements.length:" + set.elements.length);
    //        }

    int length = 0;

    int l = 0;
    int r = 0;
    while((l < elements.length) || (r < set.elements.length))
    {
      //            if(DEBUG)
      //            {
      //                System.out.println(
      //                " l:" + l +
      //                " r:" + r +
      //                " length:" + length);
      //            }

      if((r == set.elements.length) ||
          ((l != elements.length) && (elements[l] < set.elements[r])))
      {
        length++;
        l++;
      }
      else if((l == elements.length) ||
              (elements[l] > set.elements[r]))
      {
        length++;
        r++;
      }
      else
      {
        length++;
        l++;
        r++;
      }
    }

    int[] old = elements;
    elements = new int[length];

    length = l = r = 0;
    while((l < old.length) || (r < set.elements.length))
    {
      //            if(DEBUG)
      //            {
      //                System.out.println(
      //                " l:" + l +
      //                " r:" + r +
      //                " length:" + length);
      //            }

      if((r == set.elements.length) ||
          ((l != old.length) && (old[l] < set.elements[r])))
      {
        elements[length++] = old[l];
        l++;
      }
      else if((l == old.length) ||
              (old[l] > set.elements[r]))
      {
        elements[length++] = set.elements[r];
        r++;
      }
      else
      {
        elements[length++] = old[l];
        l++;
        r++;
      }
    }

  }

  public void set
    (int bit)
  {
    if(!get
        (bit))
    {
      int[] old = elements;
      elements = new int[old.length + 1];

      int old_pos = 0;
      boolean bit_inserted = false;
      int pos = 0;

      while(pos < elements.length)
      {
        if(!bit_inserted)
        {
          if(old_pos < old.length)
          {
            if(bit < old[old_pos])
            {
              elements[pos++] = bit;
              bit_inserted = true;
            }
            else
            {
              elements[pos++] = old[old_pos++];
            }
          }
          else
          {
            elements[pos++] = bit;
            bit_inserted = true;
          }
        }
        else
        {
          elements[pos++] = old[old_pos++];
        }
      }
    }
  }

  public int size()
  {
    if(elements.length == 0)
    {
      return 0;
    }

    return elements[elements.length - 1] + 1;
  }

  public String toString()
  {
    StringBuffer s = new StringBuffer();

    s.append("{");

    boolean comma = false;

    for(int i = 0; i < elements.length; i++)
    {
      if(comma)
      {
        s.append(", ");
      }
      else
      {
        comma = true;
      }

      s.append(elements[i]);
    }
    s.append("}");

    return s.toString();
  }

  public void xor(IntSet  set
                   )
  {
    if(set
        == this)
    {
      set
        = (IntSet) set.clone();
    }

    int length = 0;
    int l = 0;
    int r = 0;

    while((l < elements.length) || (r < set.elements.length))
    {
      if((r == set.elements.length) ||
          ((l != elements.length) && (elements[l] < set.elements[r])))
      {
        length++;
        l++;
      }
      else if((l == elements.length) ||
              (elements[l] > set.elements[r]))
      {
        length++;
        r++;
      }
      else
      {
        l++;
        r++;
      }
    }

    int[] old = elements;
    elements = new int[length];

    length = l = r = 0;
    while((l < old.length) || (r < set.elements.length))
    {
      if((r == set.elements.length) ||
          ((l != old.length) && (old[l] < set.elements[r])))
      {
        elements[length++] = old[l];
        l++;
      }
      else if((l == old.length) ||
              (old[l] > set.elements[r]))
      {
        elements[length++] = set.elements[r];
        r++;
      }
      else
      {
        l++;
        r++;
      }
    }
  }

  public int[] elements()
  {
    return (int[]) elements/*.clone()*/;
  }
}

