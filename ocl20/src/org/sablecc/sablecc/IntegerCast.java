/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import java.util.*;

public class IntegerCast implements Cast
{
  public final static IntegerCast instance = new IntegerCast();

  private IntegerCast()
  {}

  public  Object cast(Object o)
  {
    return (Integer) o;
  }
}

