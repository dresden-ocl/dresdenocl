/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import java.util.*;
import org.sablecc.sablecc.analysis.*;
import org.sablecc.sablecc.node.*;
import java.io.*;
import java.util.Vector;
import java.util.Enumeration;

public class GenLexer extends AnalysisAdapter
{
  private MacroExpander macros;
  private ResolveIds ids;
  private File pkgDir;
  private String pkgName;
  private AcceptStates[] acceptStatesArray;
  private Transitions transitions;

  public GenLexer(ResolveIds ids)
  {
    this.ids = ids;

    try
    {
      macros = new MacroExpander(
                 new InputStreamReader(
                   getClass().getResourceAsStream("lexer.txt")));
    }
    catch(IOException e)
    {
      throw new RuntimeException("unable to open lexer.txt.");
    }

    pkgDir = new File(ids.pkgDir, "lexer");
    pkgName = ids.pkgName.equals("") ? "lexer" : ids.pkgName + ".lexer";

    if(!pkgDir.exists())
    {
      if(!pkgDir.mkdir())
      {
        throw new RuntimeException("Unable to create " + pkgDir.getAbsolutePath());
      }
    }
  }

  public void caseStart(Start tree)
  {
    String[] names;
    int numStates = Math.max(1, ids.stateList.size());

    acceptStatesArray = new AcceptStates[numStates];
    names = new String[numStates];

    if(ids.stateList.size() == 0)
    {
      names[0] = "INITIAL";
    }
    else
    {
      Iterator iter = ids.stateList.iterator();
      for(int i = 0; i < numStates; i++)
      {
        names[i] = (String) iter.next();
      }
    }

    for(int i = 0; i < numStates; i++)
    {
      System.out.println(" State: " + names[i]);

      System.out.println(" - Constructing NFA.");
      ConstructNFA nfaConstructor = new ConstructNFA(ids, names[i]);
      tree.apply(nfaConstructor);
      System.out.println();

      NFA nfa = (NFA) nfaConstructor.getOut(tree);
      nfaConstructor = null;

      System.out.println(" - Constructing DFA.");
      DFA dfa = new DFA(nfa);
      System.out.println();

      System.out.println(" - resolving ACCEPT states.");
      acceptStatesArray[i] = new AcceptStates(dfa, ids, names[i]);
      tree.apply(acceptStatesArray[i]);
    }

    transitions = new Transitions();
    tree.apply(transitions);

    createLexerException();
    createLexer();
  }

  private void createLexerException()
  {
    BufferedWriter file;

    try
    {
      file = new BufferedWriter(
               new FileWriter(
                 new File(pkgDir, "LexerException.java")));
    }
    catch(IOException e)
    {
      throw new RuntimeException("Unable to create " + new File(pkgDir, "LexerException.java").getAbsolutePath());
    }

    try
    {
      macros.apply(file, "LexerException", new String[] {pkgName});
    }
    catch(IOException e)
    {
      throw new RuntimeException("An error occured while writing to " +
                                 new File(pkgDir, "LexerException.java").getAbsolutePath());
    }

    try
    {
      file.close();
    }
    catch(IOException e)
    {}
  }

  private void createLexer()
  {
    BufferedWriter file;

    try
    {
      file = new BufferedWriter(
               new FileWriter(
                 new File(pkgDir, "Lexer.java")));
    }
    catch(IOException e)
    {
      throw new RuntimeException("Unable to create " + new File(pkgDir, "Lexer.java").getAbsolutePath());
    }

    try
    {
      String startState = "INITIAL";
      if(ids.stateList.size() > 0)
      {
        startState = (String) ids.stateList.getFirst();
      }

      macros.apply(file, "LexerHeader", new String[] {pkgName,
                   ids.pkgName.equals("") ? "node" : ids.pkgName + ".node",
                   startState});

      for(ListIterator i = ids.tokenList.listIterator(); i.hasNext();)
      {
        String name = (String) i.next();
        Node node = (Node) ids.tokens.get(name);
        boolean fixed = ((Boolean) ids.fixedTokens.get(node))
                          .booleanValue();

        if(fixed)
        {
          macros.apply(file, "LexerFixedToken",
                       new String[] {"" + i.previousIndex(), name});
        }
        else
        {
          macros.apply(file, "LexerVariableToken",
                       new String[] {"" + i.previousIndex(), name});
        }

        Map map = (Map) transitions.tokenStates.get(node);
        if(map.size() > 0)
        {
          macros.apply(file, "TokenSwitchHeader", null);

          for(Iterator j = map.entrySet().iterator(); j.hasNext();)
          {
            Map.Entry entry = (Map.Entry) j.next();

            macros.apply(file, "TokenCase",
                         new String[] {ids.stateList.indexOf((String) entry.getKey()) + "",
                                       (String) entry.getValue()});
          }

          macros.apply(file, "TokenSwitchTail", null);
        }

        macros.apply(file, "LexerTokenTail", null);
      }

      macros.apply(file, "LexerBody1");

      for(ListIterator i = ids.tokenList.listIterator(); i.hasNext();)
      {
        String name = (String) i.next();
        Node node = (Node) ids.tokens.get(name);
        boolean fixed = ((Boolean) ids.fixedTokens.get(node))
                          .booleanValue();

        if(fixed)
        {
          macros.apply(file, "LexerNewFixedToken",
                       new String[] {"" + i.previousIndex(), name});
        }
        else
        {
          macros.apply(file, "LexerNewVariableToken",
                       new String[] {"" + i.previousIndex(), name});
        }
      }

      macros.apply(file, "LexerBody2");

      DataOutputStream out = new DataOutputStream(
                               new BufferedOutputStream(
                                 new FileOutputStream(
                                   new File(pkgDir, "lexer.dat"))));

      out.writeInt(acceptStatesArray.length);
      for(int accSt = 0; accSt < acceptStatesArray.length; accSt++)
      {
        DFA dfa = acceptStatesArray[accSt].dfa;

        file.write("        { // " + acceptStatesArray[accSt].stateName + System.getProperty("line.separator"));
        Vector outerArray = new Vector();

        for(int i = 0; i < dfa.states.size(); i++)
        {
          Vector innerArray = new Vector();

          DFA.State state = (DFA.State) dfa.states.elementAt(i);
          file.write("            {");

          for(int j = 0; j < state.transitions.size(); j++)
          {
            DFA.Transition transition =
              (DFA.Transition) state.transitions.elementAt(j);

            file.write("{" + ((int) transition.interval().start) + ", " +
                       ((int) transition.interval().end) + ", " +
                       transition.destination + "}, ");

            innerArray.addElement(new int[] {
                                    ((int) transition.interval().start),
                                    ((int) transition.interval().end),
                                    transition.destination});
          }

          file.write("}," + System.getProperty("line.separator"));

          outerArray.addElement(innerArray);
        }
        file.write("        }" + System.getProperty("line.separator"));

        out.writeInt(outerArray.size());
        for(Enumeration e = outerArray.elements(); e.hasMoreElements();)
        {
          Vector innerArray = (Vector) e.nextElement();
          out.writeInt(innerArray.size());
          for(Enumeration n = innerArray.elements(); n.hasMoreElements();)
          {
            int[] array = (int[]) n.nextElement();

            for(int i = 0; i < 3; i++)
            {
              out.writeInt(array[i]);
            }
          }
        }
      }

      macros.apply(file, "LexerAcceptHeader");

      final int stateNumber = acceptStatesArray.length;

      Vector outerArray = new Vector();

      for(int i = 0; i < stateNumber; i++)
      {
        DFA dfa = acceptStatesArray[i].dfa;
        Vector innerArray = new Vector();

        file.write("        // " + acceptStatesArray[i].stateName + System.getProperty("line.separator"));
        file.write("        {");

        for(int j = 0; j < dfa.states.size(); j++)
        {
          DFA.State state = (DFA.State) dfa.states.elementAt(j);

          file.write(state.accept + ", ");
          innerArray.addElement(new Integer(state.accept));
        }

        file.write("}," + System.getProperty("line.separator"));

        outerArray.addElement(innerArray);
      }

      out.writeInt(outerArray.size());
      for(Enumeration e = outerArray.elements(); e.hasMoreElements();)
      {
        Vector innerArray = (Vector) e.nextElement();
        out.writeInt(innerArray.size());
        for(Enumeration n = innerArray.elements(); n.hasMoreElements();)
        {
          Integer i = (Integer) n.nextElement();
          out.writeInt(i.intValue());
        }
      }
      out.close();

      file.write(System.getProperty("line.separator"));

      macros.apply(file, "LexerAcceptTail");

      macros.apply(file, "LexerStateHeader");

      if(ids.stateList.size() > 0)
      {
        for(ListIterator i = ids.stateList.listIterator(); i.hasNext();)
        {
          String s = (String) i.next();

          macros.apply(file, "LexerStateBody",
                       new String[] {s, "" + i.previousIndex()});
        }
      }
      else
      {
        macros.apply(file, "LexerStateBody",
                     new String[] {"INITIAL", "" + 0});
      }

      macros.apply(file, "LexerStateTail");

      macros.apply(file, "LexerTail");
    }
    catch(IOException e)
    {
      throw new RuntimeException("An error occured while writing to " +
                                 new File(pkgDir, "Lexer.java").getAbsolutePath());
    }

    try
    {
      file.close();
    }
    catch(IOException e)
    {}
  }
}

