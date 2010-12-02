/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import java.util.*;
import org.sablecc.sablecc.node.*;

public class NodeCast implements Cast
{
  public final static NodeCast instance = new NodeCast();

  private NodeCast()
  {}

  public Object cast(Object o)
  {
    return (Node) o;
  }
}

