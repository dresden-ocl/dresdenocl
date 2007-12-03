/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import java.util.*;

import org.sablecc.sablecc.CharSet;
import org.sablecc.sablecc.NFA;

public class NFA implements Cloneable
{
  public State[] states;

  private NFA(int size)
  {
    System.out.print(".");
    states = new State[size];
  }

  public NFA()
  {
    this(2);
    states[0] = new State();
    states[0].transitions[0] = new Transition(null, 1);
    states[1] = new State();
  }

  public NFA(CharSet chars)
  {
    this(2);
    states[0] = new State();
    states[0].transitions[0] = new Transition(chars, 1);
    states[1] = new State();
  }

  public NFA(String string)
  {
    this(string.length() + 1);

    for(int i = 0; i < string.length(); i++)
    {
      states[i] = new State();
      states[i].transitions[0] = new Transition(new CharSet(string.charAt(i)), i + 1);
    }

    states[string.length()] = new State();
  }

  private NFA(NFA nfa)
  {
    this(nfa.states.length);

    for(int i = 0; i < nfa.states.length; i++)
    {
      states[i] = new State(nfa.states[i]);
    }
  }

  public NFA zeroOrMore()
  {
    NFA nfa = new NFA(states.length + 2);
    nfa.states[0] = new State();
    nfa.states[0].transitions[0] = new Transition(null, 1);
    nfa.states[0].transitions[1] = new Transition(null, states.length + 1);

    for(int i = 0; i < states.length; i++)
    {
      nfa.states[i + 1] = new State(states[i]);

      if(nfa.states[i + 1].transitions[0] != null)
      {
        nfa.states[i + 1].transitions[0].destination += 1;
      }

      if(nfa.states[i + 1].transitions[1] != null)
      {
        nfa.states[i + 1].transitions[1].destination += 1;
      }
    }

    nfa.states[states.length].transitions[0] = new Transition(null, 1);
    nfa.states[states.length].transitions[1] = new Transition(null, states.length + 1);

    nfa.states[states.length + 1] = new State();

    return nfa;
  }

  public NFA zeroOrOne()
  {
    NFA nfa = new NFA(states.length + 2);
    nfa.states[0] = new State();
    nfa.states[0].transitions[0] = new Transition(null, 1);
    nfa.states[0].transitions[1] = new Transition(null, states.length + 1);

    for(int i = 0; i < states.length; i++)
    {
      nfa.states[i + 1] = new State(states[i]);

      if(nfa.states[i + 1].transitions[0] != null)
      {
        nfa.states[i + 1].transitions[0].destination += 1;
      }

      if(nfa.states[i + 1].transitions[1] != null)
      {
        nfa.states[i + 1].transitions[1].destination += 1;
      }
    }

    nfa.states[states.length].transitions[1] = new Transition(null, states.length + 1);

    nfa.states[states.length + 1] = new State();

    return nfa;
  }

  public NFA oneOrMore()
  {
    NFA nfa = new NFA(states.length + 2);
    nfa.states[0] = new State();
    nfa.states[0].transitions[0] = new Transition(null, 1);

    for(int i = 0; i < states.length; i++)
    {
      nfa.states[i + 1] = new State(states[i]);

      if(nfa.states[i + 1].transitions[0] != null)
      {
        nfa.states[i + 1].transitions[0].destination += 1;
      }

      if(nfa.states[i + 1].transitions[1] != null)
      {
        nfa.states[i + 1].transitions[1].destination += 1;
      }
    }

    nfa.states[states.length].transitions[0] = new Transition(null, 1);
    nfa.states[states.length].transitions[1] = new Transition(null, states.length + 1);

    nfa.states[states.length + 1] = new State();

    return nfa;
  }

  public NFA concatenate(NFA next)
  {
    NFA nfa = new NFA(states.length + next.states.length - 1);

    for(int i = 0; i < states.length - 1; i++)
    {
      nfa.states[i] = new State(states[i]);
    }

    for(int i = 0; i < next.states.length; i++)
    {
      nfa.states[states.length + i - 1] = new State(next.states[i]);

      if(nfa.states[states.length + i - 1].transitions[0] != null)
      {
        nfa.states[states.length + i - 1].transitions[0].destination +=
          states.length - 1;
      }

      if(nfa.states[states.length + i - 1].transitions[1] != null)
      {
        nfa.states[states.length + i - 1].transitions[1].destination +=
          states.length - 1;
      }
    }

    return nfa;
  }

  public NFA alternate(NFA next)
  {
    NFA nfa = new NFA(states.length + next.states.length + 2);

    nfa.states[0] = new State();
    nfa.states[0].transitions[0] = new Transition(null, 1);
    nfa.states[0].transitions[1] = new Transition(null, states.length + 1);

    for(int i = 0; i < states.length; i++)
    {
      nfa.states[i + 1] = new State(states[i]);

      if(nfa.states[i + 1].transitions[0] != null)
      {
        nfa.states[i + 1].transitions[0].destination += 1;
      }

      if(nfa.states[i + 1].transitions[1] != null)
      {
        nfa.states[i + 1].transitions[1].destination += 1;
      }
    }

    nfa.states[states.length].transitions[0] =
      new Transition(null, states.length + next.states.length + 1);

    for(int i = 0; i < next.states.length; i++)
    {
      nfa.states[states.length + i + 1] = new State(next.states[i]);

      if(nfa.states[states.length + i + 1].transitions[0] != null)
      {
        nfa.states[states.length + i + 1].transitions[0].destination +=
          states.length + 1;
      }

      if(nfa.states[states.length + i + 1].transitions[1] != null)
      {
        nfa.states[states.length + i + 1].transitions[1].destination +=
          states.length + 1;
      }
    }

    nfa.states[states.length + next.states.length].transitions[0] =
      new Transition(null, states.length + next.states.length + 1);

    nfa.states[states.length + next.states.length + 1] = new State();

    return nfa;
  }

  public NFA merge(NFA next)
  {
    NFA nfa = new NFA(states.length + next.states.length + 1);

    nfa.states[0] = new State();
    nfa.states[0].transitions[0] = new Transition(null, 1);
    nfa.states[0].transitions[1] = new Transition(null, states.length + 1);

    for(int i = 0; i < states.length; i++)
    {
      nfa.states[i + 1] = new State(states[i]);

      if(nfa.states[i + 1].transitions[0] != null)
      {
        nfa.states[i + 1].transitions[0].destination += 1;
      }

      if(nfa.states[i + 1].transitions[1] != null)
      {
        nfa.states[i + 1].transitions[1].destination += 1;
      }
    }

    for(int i = 0; i < next.states.length; i++)
    {
      nfa.states[states.length + i + 1] = new State(next.states[i]);

      if(nfa.states[states.length + i + 1].transitions[0] != null)
      {
        nfa.states[states.length + i + 1].transitions[0].destination +=
          states.length + 1;
      }

      if(nfa.states[states.length + i + 1].transitions[1] != null)
      {
        nfa.states[states.length + i + 1].transitions[1].destination +=
          states.length + 1;
      }
    }

    return nfa;
  }

  public Object clone()
  {
    return new NFA(this);
  }

  public String toString()
  {
    StringBuffer result = new StringBuffer();
    for(int i = 0; i < states.length; i++)
    {
      result.append(i + ":" + states[i] + System.getProperty("line.separator"));
    }
    return result.toString();
  }

  public static class State
  {
    public String accept;

    public Transition[] transitions = new Transition[2];

    public State()
    {}

    public State(State state)
    {
      if(state.accept != null)
      {
        accept = state.accept;
      }

      if(state.transitions[0] != null)
      {
        transitions[0] = new Transition(state.transitions[0]);
      }

      if(state.transitions[1] != null)
      {
        transitions[1] = new Transition(state.transitions[1]);
      }
    }

    public String toString()
    {
      StringBuffer result = new StringBuffer();
      if(accept != null)
      {
        result.append("(" + accept + ") ");
      }
      if(transitions[0] != null)
      {
        result.append(" " + transitions[0]);
      }
      if(transitions[1] != null)
      {
        result.append(" " + transitions[1]);
      }
      return result.toString();
    }
  }

  public static class Transition
  {
    public CharSet chars;
    public int destination;

    public Transition(CharSet chars, int destination)
    {
      this.chars = chars;
      this.destination = destination;
    }

    public Transition(Transition transition)
    {
      chars = transition.chars;
      destination = transition.destination;
    }

    public String toString()
    {
      return destination + ":{" + chars + "}";
    }
  }
}

