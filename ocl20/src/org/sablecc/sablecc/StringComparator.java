/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import java.util.*;

public class StringComparator implements Comparator
{
  public final static StringComparator instance = new StringComparator();

  private StringComparator()
  {}

  public int compare(Object o1, Object o2)
  {
    return ((String) o1).compareTo((String) o2);
  }
}

