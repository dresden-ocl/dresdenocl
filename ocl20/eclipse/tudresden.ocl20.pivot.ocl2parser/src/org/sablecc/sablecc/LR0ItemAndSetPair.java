/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import org.sablecc.sablecc.LR0Item;

final class LR0ItemAndSetPair
{
  public final LR0Item item;
  public final int set
    ;

  LR0ItemAndSetPair(LR0Item item, int set
                     )
  {
    this.item = item;
    this.set = set
                 ;
  }
}

