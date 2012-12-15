/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This file is part of SableCC.                             *
 * See the file "LICENSE" for copyright information and the  *
 * terms and conditions for copying, distribution and        *
 * modification of SableCC.                                  *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.sablecc.sablecc;

import java.io.*;

class DisplayLicense
{
  DisplayLicense()
  {
    try
    {
      BufferedReader in =
        new BufferedReader(
          new InputStreamReader(
            getClass().getResourceAsStream("LICENSE")));
      System.out.println("---- FILE: LICENSE ----");

      String s;
      while((s = in.readLine()) != null)
      {
        System.out.println(s);
      }
      in.close();

      System.out.println("---- END OF FILE: SableVM-LICENSE ----");
      System.out.println();

      System.out.println("---- FILE: AUTHORS ----");
      in =
        new BufferedReader(
          new InputStreamReader(
            getClass().getResourceAsStream("AUTHORS")));

      while((s = in.readLine()) != null)
      {
        System.out.println(s);
      }
      in.close();
      System.out.println("---- END OF FILE: AUTHORS ----");
      System.out.println();

      System.out.println("---- FILE: COPYING-LESSER ----");

      in =
        new BufferedReader(
          new InputStreamReader(
            getClass().getResourceAsStream("COPYING-LESSER")));

      while((s = in.readLine()) != null)
      {
        System.out.println(s);
      }
      in.close();
      System.out.println("---- END OF FILE: COPYING-LESSER ----");
    }
    catch(Exception e)
    {
      System.out.println(e);
      System.exit(1);
    }
  }
}
