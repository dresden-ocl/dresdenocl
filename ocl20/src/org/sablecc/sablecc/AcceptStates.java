/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import org.sablecc.sablecc.analysis.*;
import org.sablecc.sablecc.node.*;
import java.util.*;

public class AcceptStates extends DepthFirstAdapter
{
  public DFA dfa;
  public String stateName;
  private ResolveIds ids;

  public AcceptStates(DFA dfa, ResolveIds ids, String stateName)
  {
    this.dfa = dfa;
    this.ids = ids;
    this.stateName = stateName;
  }

  public void caseStart(Start node)
  {
    for(int i = 0; i < dfa.states.size(); i++)
    {
      DFA.State state = (DFA.State) dfa.states.elementAt(i);
      state.accept = -1;

      int accept = -1;

      for(int k = 0; k < state.nfaStates.size(); k++)
      {
        if(state.nfaStates.get(k))
        {
          if(dfa.nfa.states[k].accept != null)
          {
            if(accept == -1)
            {
              accept = ids.tokenList.indexOf(dfa.nfa.states[k].accept);
            }
            else
            {
              accept = Math.min(
                         ids.tokenList.indexOf(dfa.nfa.states[k].accept),
                         accept);
            }
          }
        }
      }

      state.accept = accept;
    }
  }
}
