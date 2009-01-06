/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import java.util.*;

import org.sablecc.sablecc.Cast;
import org.sablecc.sablecc.ListCast;



public class ListCast implements Cast
{
  public final static ListCast instance = new ListCast();

  private ListCast()
  {}

  public Object cast(Object o)
  {
    return (List) o;
  }
}

