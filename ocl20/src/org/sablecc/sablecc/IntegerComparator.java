/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import java.util.*;

public class IntegerComparator implements Comparator
{
  public final static IntegerComparator instance = new IntegerComparator();

  private IntegerComparator()
  {}

  public int compare(Object o1, Object o2)
  {
    return ((Integer) o1).intValue() - ((Integer) o2).intValue();
  }
}

