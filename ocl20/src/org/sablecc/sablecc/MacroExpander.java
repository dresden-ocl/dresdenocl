/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import java.io.*;
import java.util.*;

public class MacroExpander
{
  private static final String MACRO = "Macro:";
  private static final String lineSeparator = System.getProperty("line.separator");

  private Map macros = new TypedTreeMap(
                         StringComparator.instance,
                         StringCast.instance,
                         ListCast.instance);

  public MacroExpander(Reader in) throws IOException {
    BufferedReader br = new BufferedReader(in);
    while(readInMacro(br))
      ;
    in.close();
  }

  private boolean readInMacro(BufferedReader in) throws IOException {
    String line;
    while((line = in.readLine()) != null)
    {
      if(line.startsWith(MACRO))
      {
        // added ".trim()" call to remove blanks after last name character
        String name = line.substring(MACRO.length()).trim();
        List macro = new TypedLinkedList(StringCast.instance);

        while((line = in.readLine()) != null)
        {
          if(line.equals("$"))
          {
            macros.put(name, macro);
            return true;
          }

          macro.add(line);
        }

        macros.put(name, macro);
        return false;
      }
    }

    return false;
  }

  public String toString()
  {
    return this.getClass().getName() + macros;
  }

  public void apply(BufferedWriter out, String macroName) throws IOException
  {
    apply(out, macroName, null);
  }

  public void apply(BufferedWriter out, String macroName, String[] arguments) throws IOException
  {
    List macro = (List) macros.get(macroName);
    if ( macro == null ) {
        throw new RuntimeException("No macro with that name: '" + macroName + "'");
    }

    for(ListIterator li = macro.listIterator(); li.hasNext();)
    {
      if(li.nextIndex() != 0)
      {
        out.newLine();
      }

      String line = (String) li.next();
      char c;

      for(int i = 0; i < line.length(); i++)
      {
        if((c = line.charAt(i)) == '$')
        {
          StringBuffer index = new StringBuffer();

          while((c = line.charAt(++i)) != '$')
          {
            index.append(c);
          }

          if(index.length() == 0) {
            out.write('$');
          } else {
              int idxNo = Integer.parseInt(index.toString());
              String value = arguments[idxNo];
              if ( value != null ) {
                  out.write(value);
              } else {
                  throw new RuntimeException("Value for argument " + index + " is null inside " + macroName );                  
              }
          }
        }
        else
        {
          out.write(c);
        }
      }
    }
  }
}

