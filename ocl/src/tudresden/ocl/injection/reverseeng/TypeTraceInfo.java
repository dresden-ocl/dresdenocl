/*
Copyright (C) 2000  Steffen Zschaler

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

/*
 * TypeTraceInfo.java
 *
 * Created on 16. Oktober 2000, 16:38
 */
 
package tudresden.ocl.injection.reverseeng;

import java.io.*;
import java.util.*;

import tudresden.ocl.injection.lib.TypeTracer;

/** 
  * Information gained from an OCL Type Trace log file.
  *
  * @author  sz9
  * @version 
  */
public class TypeTraceInfo extends Object {
  
  private File m_fSource;
  
  private Map m_mpslsAllKeyTypes;
  private Map m_mpslsKeyTypeMinima;
  private Map m_mpslsAllElementTypes;
  private Map m_mpslsElementTypeMinima;
  
  /** 
    * Creates new TypeTraceInfo 
    */
  public TypeTraceInfo (File f) {
    super();
    
    m_fSource = f;
    
    scan();
    
    System.err.println ("All key types: \n" + m_mpslsAllKeyTypes);
    System.err.println ("\nKey type minima: \n" + m_mpslsKeyTypeMinima);
    System.err.println ("\nAll element types: \n" + m_mpslsAllElementTypes);
    System.err.println ("\nElement type minima: \n" + m_mpslsElementTypeMinima + "\n");
  }
  
  /**
    * (Re)scan file contents.
    */
  public void scan() {
    m_mpslsAllKeyTypes = new HashMap();
    m_mpslsKeyTypeMinima = new HashMap();
    m_mpslsAllElementTypes = new HashMap();
    m_mpslsElementTypeMinima = new HashMap();
    
    Map mpslsAllTypes;
    Map mpslsTypeMinima;
    
    try {
      BufferedReader brInput = new BufferedReader (new FileReader (m_fSource));

      String sLine;

      while ((sLine = brInput.readLine()) != null) {
        // Handle each line
        switch (sLine.charAt (0)) {
          case TypeTracer.ADD_ALL:
            // Type being added to types all
          case TypeTracer.ADD_MINIMA:
            // Type being added to types minima
          case TypeTracer.REMOVE_MINIMA:
            // Type being removed from types minima
            
            // Pre-analyze line
            int nPos = sLine.indexOf (TypeTracer.SEPARATOR);
            
            String sFeatureID = sLine.substring (1, nPos);

            int nPos1 = sLine.indexOf (TypeTracer.SEPARATOR, nPos + 1);
            
            String sCategory = sLine.substring (nPos + 1, nPos1);
            
            String sType = sLine.substring (nPos1 + 1);
            
            if (sCategory.equals ("key-type")) {
              mpslsAllTypes = m_mpslsAllKeyTypes;
              mpslsTypeMinima = m_mpslsKeyTypeMinima;
            }
            else {
              mpslsAllTypes = m_mpslsAllElementTypes;
              mpslsTypeMinima = m_mpslsElementTypeMinima;
            }
            
            switch (sLine.charAt (0)) {
              case TypeTracer.ADD_ALL:
                // Type being added to types all
                List lAllTypes = (List) mpslsAllTypes.get (sFeatureID);
                
                if (lAllTypes == null) {
                  lAllTypes = new LinkedList();
                  mpslsAllTypes.put (sFeatureID, lAllTypes);
                }
                
                lAllTypes.add (sType);
                
                break;
                
              case TypeTracer.ADD_MINIMA:
                // Type being added to types minima
                List lTypeMinima = (List) mpslsTypeMinima.get (sFeatureID);
                
                if (lTypeMinima == null) {
                  lTypeMinima = new LinkedList();
                  mpslsTypeMinima.put (sFeatureID, lTypeMinima);
                }
                
                lTypeMinima.add (sType);
                
                break;
                
              case TypeTracer.REMOVE_MINIMA:
                // Type being removed from types minima
                lTypeMinima = (List) mpslsTypeMinima.get (sFeatureID);
                
                if (lTypeMinima != null) {
                  lTypeMinima.remove (sType);
                }
                
                break;
            }

        }
      }
    }
    catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }
  
  public String getName() {
    return m_fSource.getName();
  }
  
  public List getAllElementTypes (String sFeatureID) {
    return (List) m_mpslsAllElementTypes.get (sFeatureID);
  }
  
  public List getElementTypeMinima (String sFeatureID) {
    return (List) m_mpslsElementTypeMinima.get (sFeatureID);
  }
  
  public List getAllKeyTypes (String sFeatureID) {
    return (List) m_mpslsAllKeyTypes.get (sFeatureID);
  }
  
  public List getKeyTypeMinima (String sFeatureID) {
    return (List) m_mpslsKeyTypeMinima.get (sFeatureID);
  }
}