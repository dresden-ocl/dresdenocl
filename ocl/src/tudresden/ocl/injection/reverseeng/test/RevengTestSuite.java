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
 * RevengTestSuite.java
 *
 * Created on 19. Oktober 2000, 14:08
 */
 
package tudresden.ocl.injection.reverseeng.test;

import junit.framework.*;
import junit.swingui.*;

import java.io.*;
import java.util.*;

import tudresden.ocl.injection.*;
import tudresden.ocl.injection.reverseeng.*;

/** 
  * Testsuite testing the vital parts of the reverseeng stuff.
  *
  * @author  sz9
  * @version 1.0
  */
public class RevengTestSuite extends TestCase {

  private static class WrappedException extends Error {
    
    protected Throwable m_tWrapped;
    
    public WrappedException (Throwable tWrapped) {
      super();
      
      m_tWrapped = tWrapped;
    }
    
    public String getMessage() {
      return m_tWrapped.getMessage();
    }
    
    public String getLocalizedMessage() {
      return m_tWrapped.getLocalizedMessage();
    }
    
    public String toString() {
      return m_tWrapped.toString();
    }
    
    public void printStackTrace() {
      m_tWrapped.printStackTrace();
    }
    
    public void printStackTrace (PrintStream ps) {
      m_tWrapped.printStackTrace (ps);
    }
    
    public void printStackTrace (PrintWriter pw) {
      m_tWrapped.printStackTrace (pw);
    }
    
    public Throwable fillInStackTrace() {
      // No fill in, as we want to maintain the original stack trace
      return this;
    }
  }
  
  protected InputStream m_isSource;
  protected Reader m_rSource;

  protected InputStream m_isSource2;
  protected Reader m_rSource2;
  
  protected Reader m_rModificationTemplate;
  
  /** Creates new RevengTestSuite */
  public RevengTestSuite (String sName) {
    super (sName);
  }
  
  protected void setUp() {
    // Setup files to be handled by the tests
    m_isSource = getClass().getResourceAsStream ("ShortExample.java");
    m_rSource = new BufferedReader (new InputStreamReader (m_isSource));

    m_isSource2 = getClass().getResourceAsStream ("ShortExample.java");
    m_rSource2 = new BufferedReader (new InputStreamReader (m_isSource2));
    
    // For comparison in testSave
    m_rModificationTemplate = new BufferedReader (new InputStreamReader (getClass().getResourceAsStream ("ShortExample.testmodified")));
    
  }
  
  protected void tearDown() {
    try {
      m_rSource.close();
      m_isSource.close();

      m_rSource2.close();
      m_isSource2.close();
    }
    catch (IOException ioe) {}
  }

  public void testAnalysis() {
    AnalysisConsumer ac = new AnalysisConsumer ("ShortExample.java");
    
    try {
      new Injector (m_rSource, null, ac).parseFile();
    }
    catch (Throwable t) {
      throw new WrappedException (t);
    }
    
    String sCompare = "[Collection<null> m_llCollectionTester at comment ID 2, Collection<null> m_alCollectionTesterNoComment at comment ID 3, Map<null -> null> m_mMapTester at comment ID 4, Map<null -> null> m_hmMapTesterNoComment at comment ID 5, Collection<null> m_sBag at comment ID 6]";
    
    assertEquals (ac.getAllFeatures().toString(), sCompare);
  }
  
  public void testSaving() {
    // First read in file
    AnalysisConsumer ac = new AnalysisConsumer ("ShortExample.java");
    
    try {
      new Injector (m_rSource, null, ac).parseFile();
    }
    catch (Throwable t) {
      throw new WrappedException (t);
    }
    
    // Now manipulate one feature
    List lFeatures = ac.getAllFeatures();
    
    CollectionDescriptor cdWithComments = (CollectionDescriptor) lFeatures.get (0);
    CollectionDescriptor cdNoComments = (CollectionDescriptor) lFeatures.get (1);
    
    cdWithComments.setElementType ("CommentCollectionElementType");
    cdNoComments.setElementType ("NoCommentCollectionElementType");
    
    // Now test saving...
    try {
      
      // Compare stream written to stream previously written...
      class ComparisonWriter extends Writer {
        int m_nLine = 1;
        int m_nColumn = 1;
        
        public void write (char[] cbuf, int off, int len) {
          try {
            // Compare and conditionally throw exception
            for (int i = 0; i < len; i++) {
              int nChar = m_rModificationTemplate.read();
              assert ("Attempt to write wrong character.\nGot \'" + cbuf[off + i] + "\' expected \'" + (char)nChar + "\' at " +
                      "(" + m_nLine + ", " + m_nColumn + ").",
                       (cbuf[off + i] == nChar));
              
              if (cbuf[off + i] != '\n') {
                m_nColumn ++;
              }
              else {
                m_nColumn = 1;
                m_nLine++;
              }
            }
          }
          catch (AssertionFailedError afe) {
            throw afe;
          }
          catch (Throwable t) {
            throw new WrappedException (t);
          }
        }
        
        public void flush() {}
        
        public void close() {
          try {
            assert ("Premature end of stream!",
                     m_rModificationTemplate.read() == -1);
          }
          catch (AssertionFailedError afe) {
            throw afe;
          }
          catch (Throwable t) {
            throw new WrappedException (t);
          }
        }
      }
      
      IndentAwareWriter iaw = new IndentAwareWriter (new ComparisonWriter());
    
      new Injector (m_rSource2, iaw, new FileSaveConsumer (iaw, ac)).parseFile();
      
      iaw.close();
      iaw = null;
    }
    catch (AssertionFailedError afe) {
      throw afe;
    }
    catch (Throwable t) {
      throw new WrappedException (t);
    }
  }
  
  public static Test suite() {
    return new TestSuite (RevengTestSuite.class);
  }
  
  public static void main (String[] args) {
    TestRunner tr = new junit.swingui.TestRunner();
    tr.main (new String[] {RevengTestSuite.class.getName()});
  }
}